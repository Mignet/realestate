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
public class AnnotationSqlUtil {
	protected static final Log logger = LogFactory.getLog(AnnotationSqlUtil.class);
	private final static String GET = "GET_";
	private final static String GETALL = "GETALL_";
	private final static String UPDATE = "UPDATE_";
	private final static String DELETE = "DELETE_";
	private final static String SAVE = "SAVE_";
	private static Map<String, String> sqlMaplist = new ConcurrentHashMap<String, String>();

	public static String getSql(Class clazz) {
		String classname = clazz.getName();
		String destsql = sqlMaplist.get(GET + classname);
		if (!isNull(destsql))
			return destsql;
		String tablename = getTableName(clazz);
		List<String> idList = getObjectId(clazz);
		Assert.notNull(tablename);
		Assert.notEmpty(idList);
		String sql = "select " + listToSqlString(getObjectAllFieldName(clazz)) + " from " + tablename + " where "
				+ getAndUpdateSqlSuffix(idList, " and ");
		logger.debug(GET + classname + " sql:" + sql);
		sqlMaplist.put(GET + classname, sql);
		return sql;

	}

	public static String getAllSql(Class clazz) {
		String classname = clazz.getName();
		String destsql = sqlMaplist.get(GETALL + classname);
		if (!isNull(destsql))
			return destsql;
		String tablename = getTableName(clazz);
		List<String> idList = getObjectId(clazz);
		Assert.notNull(tablename);
		Assert.notEmpty(idList);
		String sql = "select " + listToSqlString(getObjectAllFieldName(clazz)) + " from " + tablename;
		logger.debug(GETALL + classname + " sql:" + sql);
		sqlMaplist.put(GETALL + classname, sql);
		return sql;
	}

	public static String getSaveSql(Class clazz) {
		String classname = clazz.getName();
		String destsql = sqlMaplist.get(SAVE + classname);
		if (!isNull(destsql))
			return destsql;
		String tablename = getTableName(clazz);
		List<String> idList = getObjectId(clazz);
		Assert.notNull(tablename);
		Assert.notEmpty(idList);
		List<String> fieldlist = getObjectAllFieldName(clazz);
		String sql = " insert into " + tablename + "(" + listToSqlString(fieldlist) + ") values ("
				+ listToSqlString(fieldlist, ":") + ")";
		logger.debug(SAVE + classname + " sql:" + sql);
		sqlMaplist.put(SAVE + classname, sql);
		return sql;
	}
	
	/*public static String getSaveSql(Class clazz,String id) {
		String classname = clazz.getName();
		String destsql = sqlMaplist.get(SAVE + classname);
		if (!isNull(destsql)&&!destsql.startsWith(" insert"))
			return destsql;
		String tablename = getTableName(clazz);
		List<String> idList = getObjectId(clazz);
		Assert.notNull(tablename);
		Assert.notEmpty(idList);
		List<String> fieldlist = getObjectAllFieldName(clazz);
		*//**
		 * 触发主键自增功能
		 * insert into tablename (field...) values (:field...)
		 *//*
		String sql = " insert into " + tablename + "(" + listToSqlString(fieldlist) + ") values ("
				+ listToSqlString(fieldlist, ":") + ")";
		
		sql = sql.replace(":"+idList.get(0)+",",id+",");
		logger.debug(SAVE + classname + " sql:" + sql);
		sqlMaplist.put(SAVE + classname, sql);
		return sql;
	}*/

	public static String getUpdateSql(Class clazz) {
		String classname = clazz.getName();
		String destsql = sqlMaplist.get(UPDATE +classname);
		if (!isNull(destsql))
			return destsql;
		String tablename = getTableName(clazz);
		List<String> idList = getObjectId(clazz);
		Assert.notNull(tablename);
		Assert.notEmpty(idList);
		List<String> fieldlist = getObjectAllFieldName(clazz);
		String sql = " update  " + tablename + " set " + getAndUpdateSqlSuffix(fieldlist, ",") + " where "
				+ getAndUpdateSqlSuffix(idList, " and ");
		logger.debug(UPDATE + classname + " sql:" + sql);
		sqlMaplist.put(UPDATE + classname, sql);
		return sql;

	}

	public static String getDeleteSql(Class clazz) {
		String classname = clazz.getName();
		String destsql = sqlMaplist.get(DELETE + classname);
		if (!isNull(destsql))
			return destsql;
		String tablename = getTableName(clazz);
		List<String> idList = getObjectId(clazz);
		Assert.notNull(tablename);
		Assert.notEmpty(idList);
		String sql = " delete from " + tablename + " where " + getAndUpdateSqlSuffix(idList, " and ");
		logger.debug(DELETE + classname + " sql:" + sql);
		sqlMaplist.put(DELETE + classname, sql);
		return sql;

	}
	
	public static Field[] getObjectAllField(Class clazz) {
		Field[] fields = clazz.getDeclaredFields();
		AccessibleObject.setAccessible(fields, true);
		return fields;
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

	private static String getAndUpdateSqlSuffix(List<String> idList, String suffix) {
		if (idList.size() < 2)
			return idList.get(0) + "=:" + idList.get(0);
		StringBuffer sb = new StringBuffer();
		for (int i = 0, j = idList.size(); i < (j - 1); i++) {
			sb.append(idList.get(i)).append("=:").append(idList.get(i)).append(suffix);
		}
		sb.append(idList.get(idList.size() - 1)).append("=:").append(idList.get(idList.size() - 1));
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

	private static boolean isNull(String str) {
		if (str == null || str.length() < 1)
			return true;
		return false;
	}
}

