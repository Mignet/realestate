package com.springjdbc.annotation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

@SuppressWarnings("unchecked")
public class DomainSqlUtil {
	private final static String GET = "GET_";
	private final static String GETALL = "GETALL_";
	private final static String GETALLVO = "GETALLVO_";
	private final static String UPDATE = "UPDATE_";
	private final static String DELETE = "DELETE_";
	private final static String SAVE = "SAVE_";
	protected static final Log logger = LogFactory.getLog(DomainSqlUtil.class);
	private static Map<String, String> sqlMaplist = new ConcurrentHashMap<String, String>();

	public static String getSql(Object obj) {
		Class clazz = obj.getClass();
		String destsql = sqlMaplist.get(GET + clazz.getName());
		if (!isNull(destsql))
			return destsql;
		String tablename = getTableName(clazz);
		List<String> idList = getObjectId(clazz);
		Assert.notNull(tablename);
		Assert.notEmpty(idList);
		String sql = "select " + listToSqlString(getObjectAllFieldName(clazz))
				+ " from " + tablename + " where "
				+ getAndUpdateSqlSuffix(idList, " and ");
		logger.debug(GET + clazz.getName() + " sql:" + sql);
		sqlMaplist.put(GET + clazz.getName(), sql);
		return sql;

	}

	public static String getAllSql(Object obj) {
		Class clazz = obj.getClass();
		String destsql = sqlMaplist.get(GETALL + clazz.getName());
		if (!isNull(destsql))
			return destsql;
		String tablename = getTableName(clazz);
		List<String> idList = getObjectId(clazz);
		Assert.notNull(tablename);
		Assert.notEmpty(idList);
		String sql = "select " + listToSqlString(getObjectAllFieldName(clazz))
				+ " from " + tablename;
		logger.debug(GETALL + clazz.getName() + " sql:" + sql);
		sqlMaplist.put(GETALL + clazz.getName(), sql);
		return sql;
	}

	public static String getVoAllSql(Class clazz) {
		String destsql = sqlMaplist.get(GETALLVO + clazz.getName());
		if (!isNull(destsql))
			return destsql;
		String sql = "select " + listToSqlString(getObjectAllFieldName(clazz));
		logger.debug(GETALLVO + clazz.getName() + " sql:" + sql);
		sqlMaplist.put(GETALLVO + clazz.getName(), sql);
		return sql;
	}

	public static String getSaveSql(Object obj) {
		Class clazz = obj.getClass();
		String destsql = sqlMaplist.get(SAVE + clazz.getName());
		if (!isNull(destsql))
			return destsql;
		String tablename = getTableName(clazz);
		List<String> idList = getObjectId(clazz);
		Assert.notNull(tablename);
		Assert.notEmpty(idList);
		List<String> fieldlist = getObjectAllFieldName(clazz);
		String sql = " insert into " + tablename + "("
				+ listToSqlString(fieldlist) + ") values ("
				+ listToSqlString(fieldlist, ":") + ")";
		logger.debug(SAVE + clazz.getName() + " sql:" + sql);
		sqlMaplist.put(SAVE + clazz.getName(), sql);
		return sql;
	}

	public static String getUpdateSql(Object obj) {
		Class clazz = obj.getClass();
		String destsql = sqlMaplist.get(UPDATE + clazz.getName());
		if (!isNull(destsql))
			return destsql;
		String tablename = getTableName(clazz);
		List<String> idList = getObjectId(clazz);
		Assert.notNull(tablename);
		Assert.notEmpty(idList);
		List<String> fieldlist = getObjectAllFieldName(clazz);
		String sql = " update  " + tablename + " set "
				+ getAndUpdateSqlSuffix(fieldlist, ",") + " where "
				+ getAndUpdateSqlSuffix(idList, " and ");
		logger.debug(UPDATE + clazz.getName() + " sql:" + sql);
		sqlMaplist.put(UPDATE + clazz.getName(), sql);
		return sql;

	}

	public static String getDeleteSql(Object obj) {
		Class clazz = obj.getClass();
		String destsql = sqlMaplist.get(DELETE + clazz.getName());
		if (!isNull(destsql))
			return destsql;
		String tablename = getTableName(clazz);
		List<String> idList = getObjectId(clazz);
		Assert.notNull(tablename);
		Assert.notEmpty(idList);
		String sql = " delete from " + tablename + " where "
				+ getAndUpdateSqlSuffix(idList, " and ");
		logger.debug(DELETE + clazz.getName() + " sql:" + sql);
		sqlMaplist.put(DELETE + clazz.getName(), sql);
		return sql;

	}

	private static String getTableName(Class clazz) {
		boolean flag = clazz.isAnnotationPresent(Entity.class);
		if (!flag)
			return null;
		flag = clazz.isAnnotationPresent(Table.class);
		if (!flag)
			return null;
		Table table = (Table) clazz.getAnnotation(Table.class);
		String tablename = table.name();
		if (tablename == null || tablename.length() < 1)
			tablename = clazz.getName();
		return tablename;

	}

	private static boolean isNull(String str) {
		if (str == null || str.length() < 1)
			return true;
		return false;
	}

	private static String listToSqlString(List<String> list) {
		return listToSqlString(list, "");
	}

	private static String listToSqlString(List<String> list, String prefix) {
		StringBuffer sb = new StringBuffer();
		if (list == null || list.size() < 1)
			return null;
		for (int i = 0, j = list.size(); i < j - 1; i++) {
			sb.append(prefix).append(list.get(i)).append(",");
		}
		sb.append(prefix).append(list.get(list.size() - 1)).append(" ");
		return sb.toString();
	}

	private static String getAndUpdateSqlSuffix(List<String> idList,
			String suffix) {
		if (idList.size() < 2)
			return idList.get(0) + "=:" + idList.get(0);
		StringBuffer sb = new StringBuffer();
		for (int i = 0, j = idList.size(); i < (j - 1); i++) {
			sb.append(idList.get(i)).append("=:").append(idList.get(i)).append(
					suffix);
		}
		sb.append(idList.get(idList.size() - 1)).append("=:").append(
				idList.get(idList.size() - 1));
		return sb.toString();
	}

	private static List<String> getObjectAllFieldName(Class clazz) {
		List<String> fieldArray = new ArrayList<String>();
		Field[] fields = clazz.getDeclaredFields();
		AccessibleObject.setAccessible(fields, true);
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			boolean tmpflag = field.isAnnotationPresent(Transient.class);
			if (!tmpflag) {
				fieldArray.add(field.getName());
			}
		}
		return fieldArray;

	}

	private static List<String> getObjectId(Class clazz) {
		List<String> list = new ArrayList<String>();
		Field[] fields = clazz.getDeclaredFields();
		AccessibleObject.setAccessible(fields, true);
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			boolean idflag = field.isAnnotationPresent(Id.class);
			if (idflag) {
				list.add(field.getName());
			}
		}
		return list;

	}
}

