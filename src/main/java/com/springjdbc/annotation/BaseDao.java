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
	 * 查询单个对象,规则如下： 1） 查询不到则返回空 2） 查询到多条则抛出
	 * IncorrectResultSizeDataAccessException 异常
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
	 * 查询单个对象
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
			throw new AnnotationExceptoin("异常：查询到多条记录!");
		return list.get(0);
	}

	/**
	 * 保存单个对象
	 * 
	 * @param entity
	 */
	public void save(T t) {
		this.update(DomainSqlUtil.getSaveSql(t), t);
	}

	/**
	 * 更新单个对象
	 * 
	 * @param entity
	 */
	public void update(T t) {
		this.update(DomainSqlUtil.getUpdateSql(t), t);
	}

	/**
	 * 删除单个对象
	 * 
	 * @param entity
	 */
	public void delete(T t) {
		this.update(DomainSqlUtil.getDeleteSql(t), t);
	}

	/**
	 * 得到该对象全部列表
	 * 
	 * @return
	 */
	public List<T> getAll() {
		return this.getAll(null, null);
	}

	/**
	 * 得到符合条件的该对象列表 where sql 的写法 "where x=:x and y:=y"
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
			throw new AnnotationExceptoin("InstantiationException异常:"
					+ e.getMessage());
		} catch (IllegalAccessException e) {
			throw new AnnotationExceptoin("IllegalAccessException异常:"
					+ e.getMessage());
		}
		return this.queryList(sql, paramsObject, this.t);

	}

	/**
	 * 更新sql
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
	 * 批量执行更新sql,传入对象列表参数
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
	 * 批量更新sql，传入MAP列表参数
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
	 * 查询单个对象，只支持JAVA内置类型
	 * 
	 * @param <T>
	 * @param sqlOrFromSql
	 *            传入SQL语句，或者 从 from开始的SQL语句
	 * @param paramsMap
	 * @param returnType
	 * @return
	 */
	public <T> T queryObject(String sqlOrFromSql, Object paramsObject,
			Class<T> returnType) {
		if (!(this.isPrimitiveClass(returnType) || this.isUtilClass(returnType)))
			throw new AnnotationExceptoin(
					"调用异常:该接口只支持JAVA内置类型的返回参数,请调用本类的其它接口处理!");
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
	 * 查询对象列表，不支持JAVA内置类型的列表查询
	 * 
	 * @param <T>
	 * @param sqlOrFromSql
	 *            传入SQL语句，或者 从 from开始的SQL语句
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
					"调用异常:该接口不支持JAVA内置类型和MAP List 类型的返回参数,请调用本类的其它接口处理!");
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
	 * 查询MAP对象列表，用于对不想构造返回对象的列表查询
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
	 * 自动拼凑SQL 语句
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
	 * 检查是否为UTIL包中的类
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
	 * 检查是否为基本内置数数据类型
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
	 * 简化将ResultSet反射到Bean的定义.
	 */
	private <T> ParameterizedBeanPropertyRowMapper<T> resultBeanMapper(
			Class<T> clazz) {
		return ParameterizedBeanPropertyRowMapper.newInstance(clazz);
	}

	/**
	 * 简化将Bean反射到SQL参数的定义.
	 */
	private BeanPropertySqlParameterSource paramBeanMapper(Object object) {
		BeanPropertySqlParameterSource destObj = new BeanPropertySqlParameterSource(
				object);
		return destObj;
	}

}

