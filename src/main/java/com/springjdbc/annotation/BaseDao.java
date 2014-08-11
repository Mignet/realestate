package com.springjdbc.annotation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.util.Assert;

@SuppressWarnings("unchecked")
public class BaseDao<T> {
	private static final long serialVersionUID = 1L;
	protected final Log logger = LogFactory.getLog(getClass());
	protected SimpleJdbcTemplate jdbcTemplate;
	protected DataSource dataSource;
	protected Class<T> t;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(this.dataSource);
	}

	public BaseDao() {
		this.t = BeanUtil.getSuperClassGenricType(getClass());
		// logger.debug("t:"+this.t.getName());
	}

	/**
	 * ��ѯ��������,�������£� 1�� ��ѯ�����򷵻ؿ� 2�� ��ѯ���������׳�
	 * IncorrectResultSizeDataAccessException �쳣
	 * 
	 * @param id
	 * @return
	 */
	public T get(T t) {
		try {
			return this.jdbcTemplate.queryForObject(DomainSqlUtil.getSql(t),
					resultBeanMapper(this.t), paramBeanMapper(t));
		} catch (EmptyResultDataAccessException ede) {
			logger.error(ede);
			return null;
		}
	}

	/**
	 * ��ѯ��������
	 * 
	 * @param whereSql
	 * @param paramsObject
	 * @return
	 */
	public T get(String whereSql, Object paramsObject) {
		List<T> list = this.getAll(whereSql, paramsObject);
		if (list == null || list.size() < 1)
			return null;
		if (list.size() > 1)
			throw new AnnotationExceptoin("�쳣����ѯ��������¼!");
		return list.get(0);
	}

	/**
	 * ���浥������
	 * 
	 * @param entity
	 */
	public void save(T t) {
		this.update(DomainSqlUtil.getSaveSql(t), t);
	}

	/**
	 * ���µ�������
	 * 
	 * @param entity
	 */
	public void update(T t) {
		this.update(DomainSqlUtil.getUpdateSql(t), t);
	}

	/**
	 * ɾ����������
	 * 
	 * @param entity
	 */
	public void delete(T t) {
		this.update(DomainSqlUtil.getDeleteSql(t), t);
	}

	/**
	 * �õ��ö���ȫ���б�
	 * 
	 * @return
	 */
	public List<T> getAll() {
		return this.getAll(null, null);
	}

	/**
	 * �õ����������ĸö����б� where sql ��д�� "where x=:x and y:=y"
	 * 
	 * @param whereSql
	 * @param paramsObject
	 * @return
	 */
	public List<T> getAll(String whereSql, Object paramsObject) {
		String sql = "";
		try {
			sql = DomainSqlUtil.getAllSql(this.t.newInstance())
					+ (whereSql == null ? "" : whereSql);
		} catch (InstantiationException e) {
			throw new AnnotationExceptoin("InstantiationException�쳣:"
					+ e.getMessage());
		} catch (IllegalAccessException e) {
			throw new AnnotationExceptoin("IllegalAccessException�쳣:"
					+ e.getMessage());
		}
		return this.queryList(sql, paramsObject, this.t);

	}

	/**
	 * ����sql
	 * 
	 * @param sql
	 * @param entity
	 */
	public void update(String sql, Object paramObject) {
		Assert.notNull(paramObject);
		int size = 0;
		if (paramObject == null || paramObject instanceof Map) {
			size = this.jdbcTemplate.update(sql, (Map) paramObject);
		} else {
			size = this.jdbcTemplate.update(sql, paramBeanMapper(paramObject));
		}
		Assert.isTrue(1 == size);
	}

	/**
	 * ����ִ�и���sql,��������б����
	 * 
	 * @param sql
	 * @param paramsObject
	 * @return
	 */
	public int[] batchUpdate(String sql, List<Object> paramsObject) {
		SqlParameterSource[] batchparams = SqlParameterSourceUtils
				.createBatch(paramsObject.toArray());
		return this.jdbcTemplate.batchUpdate(sql, batchparams);
	}

	/**
	 * ��������sql������MAP�б����
	 * 
	 * @param sql
	 * @param mapParams
	 * @return
	 */
	public int[] batchUpdateByMap(String sql,
			List<Map<String, Object>> mapParams) {
		Map<String, Object>[] maplist = null;
		if (mapParams != null && mapParams.size() > 0) {
			maplist = new HashMap[mapParams.size()];
			for (int i = 0; i < mapParams.size(); i++) {
				maplist[i] = mapParams.get(i);
			}
		}
		return this.jdbcTemplate.batchUpdate(sql, maplist);
	}

	/**
	 * ��ѯ��������ֻ֧��JAVA��������
	 * 
	 * @param <T>
	 * @param sqlOrFromSql
	 *            ����SQL��䣬���� �� from��ʼ��SQL���
	 * @param paramsMap
	 * @param returnType
	 * @return
	 */
	public <T> T queryObject(String sqlOrFromSql, Object paramsObject,
			Class<T> returnType) {
		if (!(this.isPrimitiveClass(returnType) || this.isUtilClass(returnType)))
			throw new AnnotationExceptoin(
					"�����쳣:�ýӿ�ֻ֧��JAVA�������͵ķ��ز���,����ñ���������ӿڴ���!");
		String sql = contractSql(sqlOrFromSql, returnType);
		if (paramsObject == null || paramsObject instanceof Map) {
			return this.jdbcTemplate.queryForObject(sql, returnType,
					(Map) paramsObject);
		} else {
			return this.jdbcTemplate.queryForObject(sql, returnType,
					paramBeanMapper(paramsObject));
		}

	}

	/**
	 * ��ѯ�����б���֧��JAVA�������͵��б��ѯ
	 * 
	 * @param <T>
	 * @param sqlOrFromSql
	 *            ����SQL��䣬���� �� from��ʼ��SQL���
	 * @param paramsObject
	 * @param returnType
	 * @return
	 */
	public <T> List<T> queryList(String sqlOrFromSql, Object paramsObject,
			Class<T> returnType) {
		String sql = contractSql(sqlOrFromSql, returnType);
		logger.debug("method  queryObjectListForSql sql:" + sql);
		if (this.isPrimitiveClass(returnType) || this.isUtilClass(returnType))
			throw new AnnotationExceptoin(
					"�����쳣:�ýӿڲ�֧��JAVA�������ͺ�MAP List ���͵ķ��ز���,����ñ���������ӿڴ���!");
		if (paramsObject == null) {
			Map map = null;
			return this.jdbcTemplate.query(sql, resultBeanMapper(returnType),
					map);
		} else if (paramsObject instanceof Map)
			return this.jdbcTemplate.query(sql, resultBeanMapper(returnType),
					(Map) paramsObject);
		else
			return this.jdbcTemplate.query(sql, resultBeanMapper(returnType),
					paramBeanMapper(paramsObject));
	}

	/**
	 * ��ѯMAP�����б����ڶԲ��빹�췵�ض�����б��ѯ
	 * 
	 * @param sql
	 * @param paramsObject
	 * @return
	 */
	public List<Map<String, Object>> queryMapList(String sql,
			Object paramsObject) {
		if (paramsObject == null || paramsObject instanceof Map) {
			return this.jdbcTemplate.queryForList(sql, (Map) paramsObject);
		} else {
			return this.jdbcTemplate.queryForList(sql,
					paramBeanMapper(paramsObject));
		}
	}

	/**
	 * �Զ�ƴ��SQL ���
	 * 
	 * @param sql
	 * @param returnType
	 * @return
	 */
	protected <T> String contractSql(String sql, Class<T> returnType) {
		if (sql.trim().toLowerCase().indexOf("from") == 0) {
			return DomainSqlUtil.getVoAllSql(returnType) + "  " + sql;
		}
		return sql;
	}

	/**
	 * ����Ƿ�ΪUTIL���е���
	 * 
	 * @param <T>
	 * @param returnType
	 * @return
	 */
	private <T> boolean isUtilClass(Class<T> returnType) {
		try {
			if ("java.util".equalsIgnoreCase(returnType.getPackage().getName()))
				return true;
		} catch (Exception ex) {
			logger.error("isPrimitiveClass():" + ex.getMessage(), ex);
		}
		return false;
	}

	/**
	 * ����Ƿ�Ϊ������������������
	 * 
	 * @param <T>
	 * @param returnType
	 * @return
	 */
	private <T> boolean isPrimitiveClass(Class<T> returnType) {
		try {
			if ("java.lang".equalsIgnoreCase(returnType.getPackage().getName())
					|| (returnType.newInstance() instanceof java.util.Date))
				return true;
		} catch (Exception ex) {
			logger.error("isPrimitiveClass():" + ex.getMessage(), ex);
		}
		return false;
	}

	/**
	 * �򻯽�ResultSet���䵽Bean�Ķ���.
	 */
	private <T> ParameterizedBeanPropertyRowMapper<T> resultBeanMapper(
			Class<T> clazz) {
		return ParameterizedBeanPropertyRowMapper.newInstance(clazz);
	}

	/**
	 * �򻯽�Bean���䵽SQL�����Ķ���.
	 */
	private BeanPropertySqlParameterSource paramBeanMapper(Object object) {
		BeanPropertySqlParameterSource destObj = new BeanPropertySqlParameterSource(
				object);
		return destObj;
	}

}

