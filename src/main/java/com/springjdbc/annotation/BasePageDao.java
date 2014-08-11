package com.springjdbc.annotation;

import java.util.List;
import java.util.Map;

public class BasePageDao<T> extends BaseDao<T> {
	private final static String QUERY_PAGE_PREFIX = " select * from (SELECT aa.*, rownum r FROM ( ";

	private final static String QUERY_PAGE_SUFFIX1 = " ) aa WHERE rownum <=";

	private final static String QUERY_PAGE_SUFFIX2 = " )    where r >";

	private final static String QUERY_PAGE_TOTALSIZE_PREFIX = " SELECT count(1) as totalsize from (";

	private final static String QUERY_PAGE_TOTALSIZE_SUFFIX = " )aa";

	/**
	 * �����ҳ��ѯ
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<T> getPage(int pageNo, int pageSize) {
		return this.getPage(null, null, pageNo, pageSize);
	}

	/**
	 * �������ĵ����ҳ��ѯwhere sql ��д�� "where x=:x and y:=y"
	 * @param whereSql
	 * @param paramsObject
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<T> getPage(String whereSql, Object paramsObject, int pageNo, int pageSize) {
		String localsql;
		try {
			localsql = DomainSqlUtil.getAllSql(super.t.newInstance()) + (whereSql == null ? "" : whereSql);
		} catch (InstantiationException e) {
			throw new AnnotationExceptoin("InstantiationException�쳣:" + e.getMessage());
		} catch (IllegalAccessException e) {
			throw new AnnotationExceptoin("IllegalAccessException�쳣:" + e.getMessage());
		}
		List<T> list = super.queryList(getPageSqlForOracle(localsql, pageNo, pageSize), paramsObject, super.t);
		return new Page<T>(list, pageNo, pageSize, queryPageTotalSizeForSql(localsql, paramsObject));
	}

	/**
	 * <T> �����ҳ��ѯ
	 * @param <T>
	 * @param sqlOrFromSql ����SQL��䣬���� �� from��ʼ��SQL���
	 * @param whereSql
	 * @param paramsObject
	 * @param returnType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public <T> Page<T> queryPage(String sqlOrFromSql, Object paramsObject, Class<T> returnType, int pageNo, int pageSize) {
		String sql= super.contractSql(sqlOrFromSql, returnType);
		List<T> list = super.queryList(getPageSqlForOracle(sql, pageNo, pageSize), paramsObject, returnType);
		return new Page<T>(list, pageNo, pageSize, queryPageTotalSizeForSql(sql, paramsObject));
	}

	/**
	 * MAP ��ҳ��ѯ
	 * @param sql
	 * @param paramsObject
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Map<String, Object>> queryMapPage(String sql, Object paramsObject, int pageNo, int pageSize) {
		List<Map<String, Object>> list = super.queryMapList(getPageSqlForOracle(sql, pageNo, pageSize), paramsObject);
		return new Page<Map<String, Object>>(list, pageNo, pageSize, queryPageTotalSizeForSql(sql, paramsObject));
	}

	private String getPageSqlForOracle(String sql, int pageNo, int pageSize) {
		int endSize = pageNo * pageSize;
		int startSize = (pageNo - 1) * pageSize;
		sql = QUERY_PAGE_PREFIX + sql + QUERY_PAGE_SUFFIX1 + endSize + QUERY_PAGE_SUFFIX2 + startSize;
		logger.debug("oracle page sql:" + sql);
		return sql;
	}

	private int queryPageTotalSizeForSql(String sql, Object paramsObject) {
		sql = QUERY_PAGE_TOTALSIZE_PREFIX + sql + QUERY_PAGE_TOTALSIZE_SUFFIX;
		logger.debug("total size page sql:" + sql);
		return super.queryObject(sql, paramsObject, Integer.class).intValue();
	}
}

