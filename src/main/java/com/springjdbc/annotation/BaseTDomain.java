package com.springjdbc.annotation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.util.Assert;

/**
 * 该类的主要职责：
 * 1）单表的增删改查
 * 2）多表或单表的查询
 * 3）存储过程与数据库函数的调用
 * @author Mignet
 * @version 2.0
 * @param <T>
 */
public class BaseTDomain<T> implements Serializable {
	protected final Log logger = LogFactory.getLog(getClass());

	private static final long serialVersionUID = 1L;

	protected SimpleJdbcTemplate jdbcTemplate;

	protected DataSource dataSource;

	protected Class<T> t;

	private final static String QUERY_PAGE_PREFIX = " select * from (SELECT aa.*, rownum r FROM ( ";

	private final static String QUERY_PAGE_SUFFIX1 = " ) aa WHERE rownum <=";

	private final static String QUERY_PAGE_SUFFIX2 = " )    where r >";

	private final static String QUERY_PAGE_TOTALSIZE_PREFIX = " SELECT count(1) as totalsize from (";

	private final static String QUERY_PAGE_TOTALSIZE_SUFFIX = " )aa";

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new SimpleJdbcTemplate(this.dataSource);
	}

	/**
	 * 显示每个属性值
	 * 
	 * @param t
	 * @return
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

	/**
	 * 查询单个对象,规则如下：
	 * 1） 查询不到则返回空 
	 * 2） 查询到多条则抛出 IncorrectResultSizeDataAccessException 异常
	 * @param id
	 * @return
	 */
	public T get(T t) {
		try {
			return this.jdbcTemplate.queryForObject(AnnotationSqlUtil.getSql(this.getClass()),
					resultBeanMapper(this.t), paramBeanMapper(t));
		} catch (EmptyResultDataAccessException ede) {
			logger.error(ede);
			return null;
		}
	}

	/**
	 * 查询单个对象
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
		this.saveObject(t);
	}

	/**
	 * 更新单个对象
	 * 
	 * @param entity
	 */
	public void update(T t) {
		this.updateObject(t);
	}

	/**
	 * 删除单个对象
	 * 
	 * @param entity
	 */
	public void delete(T t) {
		this.update(AnnotationSqlUtil.getDeleteSql(this.getClass()), t);
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
		return this.queryListForSql(getLocalTableSql(whereSql), paramsObject);
	}

	/**
	 * 单表分页查询
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<T> getPage(int pageNo, int pageSize) {
		return this.getPage(null, null, pageNo, pageSize);
	}

	/**
	 * 带条件的单表分页查询where sql 的写法 "where x=:x and y:=y"
	 * @param whereSql
	 * @param paramsObject
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<T> getPage(String whereSql, Object paramsObject, int pageNo, int pageSize) {
		List<T> list = this.queryList(getPageSqlForOracle(getLocalTableSql(whereSql), pageNo, pageSize), paramsObject,
				this.t);
		return new Page<T>(list, pageNo, pageSize, queryPageTotalSizeForSql(getLocalTableSql(whereSql), paramsObject));
	}

	/**
	 * 拷贝源对象到自身
	 * 将源对象的属性拷贝到目标对象中
	 * 支持源对象为MAP的拷贝
	 * @param srcObject
	 */
	public void copyProperties(Object dest, Object src) {
		BeanUtil.copyProperties(dest, src);
	}


	/**
	 * 查询对象列表，
	 * @param <T>
	 * @param sql
	 * @param paramsObject
	 * @param returnType
	 * @return
	 */
	protected <T> List<T> queryList(String sql, Object paramsObject, Class<T> returnType) {
		logger.debug("method  queryObjectListForSql sql:" + sql);
		if (this.isPrimitiveClass(returnType) || this.isUtilClass(returnType))
			throw new AnnotationExceptoin("调用异常:该接口不支持JAVA内置类型和MAP List 类型的返回参数,请调用本类的其它接口处理!");
		if (paramsObject == null) {
			Map map = null;
			return this.jdbcTemplate.query(sql, resultBeanMapper(returnType), map);
		} else if (paramsObject instanceof Map)
			return this.jdbcTemplate.query(sql, resultBeanMapper(returnType), (Map) paramsObject);
		else
			return this.jdbcTemplate.query(sql, resultBeanMapper(returnType), paramBeanMapper(paramsObject));
	}

	/**
	 * 查询单个对象
	 * @param <T>
	 * @param sql
	 * @param paramsObject
	 * @param returnType
	 * @return
	 */
	protected <T> T queryOneValue(String sql, Object paramsObject, Class<T> returnType) {
		if (paramsObject == null) {
			Map map = null;
			return this.jdbcTemplate.queryForObject(sql, returnType, map);
		} else if (paramsObject instanceof Map)
			return this.jdbcTemplate.queryForObject(sql, returnType, (Map) paramsObject);
		else
			return this.jdbcTemplate.queryForObject(sql, returnType, paramBeanMapper(paramsObject));

	}

	/**
	 * <T> ORACLE分页查询
	 * @param <T>
	 * @param key
	 * @param whereSql
	 * @param paramsObject
	 * @param returnType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	protected <T> Page<T> queryPage(String sql, Object paramsObject, Class<T> returnType, int pageNo, int pageSize) {
		List<T> list = this.queryList(getPageSqlForOracle(sql, pageNo, pageSize), paramsObject, returnType);
		return new Page<T>(list, pageNo, pageSize, queryPageTotalSize(sql, paramsObject));
	}

	/**
	 * 查询MAP列表
	 * @param sql
	 * @param paramsObject
	 * @return
	 */
	protected List<Map<String, Object>> queryMapList(String sql, Object paramsObject) {
		logger.debug("method  queryMapList sql:" + sql);
		if (paramsObject == null) {
			Map<String, Object> map = null;
			return this.jdbcTemplate.queryForList(sql, map);
		} else if (paramsObject instanceof Map)
			return this.jdbcTemplate.queryForList(sql, (Map) paramsObject);
		else
			return this.jdbcTemplate.queryForList(sql, paramBeanMapper(paramsObject));
	}

	/**
	 * 查询单个MAP 对象
	 * 
	 * @param key
	 * @param paramsObject
	 * @return
	 */
	protected Map<String, Object> queryMap(String sql, Object paramsObject) {
		List<Map<String, Object>> list = this.queryMapList(sql, paramsObject);
		if (list == null || list.size() < 1)
			return null;
		else if (list.size() == 1)
			return list.get(0);
		else
			throw new AnnotationExceptoin("异常:存在" + list.size() + "条记录!");
	}

	/*
	 * oracle 数据库 分页查询 
	 * @param key
	 * @param whereSql
	 * @param paramsObject
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	protected Page<Map<String, Object>> queryMapPage(String sql, Object paramsObject, int pageNo, int pageSize) {
		List<Map<String, Object>> list = this.queryMapList(getPageSqlForOracle(sql, pageNo, pageSize), paramsObject);
		return new Page<Map<String, Object>>(list, pageNo, pageSize, queryPageTotalSize(sql, paramsObject));
	}

	/**
	 * 批量更新SQL 语句，用于大批量在表之间进行数据更新的业务逻辑
	 * @param sql
	 * @param paramsObject
	 * @return
	 */
	protected int updateSql(String sql, Object paramsObject) {
		return this.jdbcUpdateSql(sql, paramsObject);
	}

	/**
	 * 批量执行更新sql
	 * @param sql
	 * @param paramsObject
	 * @return
	 */
	protected int[] jdbcBatchUpdate(String sql, List<Object> paramsObject) {
		SqlParameterSource[] batchparams = SqlParameterSourceUtils.createBatch(paramsObject.toArray());
		return this.jdbcTemplate.batchUpdate(sql, batchparams);
	}

	/**
	 * 批量执行更新sql
	 * @param sql
	 * @param paramsObject
	 * @return
	 */
	protected int[] jdbcBatchUpdateByMap(String sql, List<Map<String, Object>> mapParams) {
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
	 * 查询分页总数量
	 * @param key
	 * @param paramsObject
	 * @return
	 */
	private int queryPageTotalSize(String sql, Object paramsObject) {
		return queryPageTotalSizeForSql(sql, paramsObject);
	}

	private int queryPageTotalSizeForSql(String sql, Object paramsObject) {
		sql = QUERY_PAGE_TOTALSIZE_PREFIX + sql + QUERY_PAGE_TOTALSIZE_SUFFIX;
		logger.debug("total size page sql:" + sql);
		return this.queryOneValue(sql, paramsObject, Integer.class).intValue();
	}

	private String getPageSqlForOracle(String sql, int pageNo, int pageSize) {
		int endSize = pageNo * pageSize;
		int startSize = (pageNo - 1) * pageSize;
		sql = QUERY_PAGE_PREFIX + sql + QUERY_PAGE_SUFFIX1 + endSize + QUERY_PAGE_SUFFIX2 + startSize;
		logger.debug("oracle page sql:" + sql);
		return sql;
	}

	/**
	 * 简化将ResultSet反射到Bean的定义.
	 */
	private <T> ParameterizedBeanPropertyRowMapper<T> resultBeanMapper(Class<T> clazz) {
		return ParameterizedBeanPropertyRowMapper.newInstance(clazz);
	}

	/**
	 * 简化将Bean反射到SQL参数的定义.
	 */
	private BeanPropertySqlParameterSource paramBeanMapper(Object object) {
		BeanPropertySqlParameterSource destObj = new BeanPropertySqlParameterSource(object);
		return destObj;
	}

	/**
	 * 更新sql
	 * @param sql
	 * @param entity
	 */
	private void update(String sql, Object entity) {
		Assert.notNull(entity);
		int size = this.jdbcUpdateSql(sql, entity);
		Assert.isTrue(1 == size);
	}

	/**
	 * 得到单表所有记录的sql
	 * @return
	 */
	private String getAllSql() {
		return AnnotationSqlUtil.getAllSql(this.t);
	}

	/**
	 * 保存对象
	 * @param obj
	 */
	private void saveObject(Object obj) {
		this.update(AnnotationSqlUtil.getSaveSql(this.getClass()), obj);
	}

	/**
	 * 查询单表所有记录
	 * @param sql
	 * @param paramsObject
	 * @return
	 */
	private List<T> queryListForSql(String sql, Object paramsObject) {
		return this.queryList(sql, paramsObject, this.t);
	}

	/**
	 * 执行更新sql
	 * @param sql
	 * @param paramsObject
	 * @return
	 */
	private int jdbcUpdateSql(String sql, Object paramsObject) {
		if (paramsObject == null) {
			Map map = null;
			return this.jdbcTemplate.update(sql, map);
		} else if (paramsObject instanceof Map)
			return this.jdbcTemplate.update(sql, (Map) paramsObject);
		else
			return this.jdbcTemplate.update(sql, paramBeanMapper(paramsObject));
	}

	/**
	 * 获取单表的sql语句
	 * @param whereSql
	 * @return
	 */
	private String getLocalTableSql(String whereSql) {
		if (whereSql == null || whereSql.length() < 1) {
			whereSql = "";
		}
		String sql = getAllSql() + " " + whereSql;
		return sql;
	}

	/**
	 * 检查是否为基本内置数数据类型
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
	 * 检查是否为UTIL包中的类
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
	 * 更新对象
	 * @param obj
	 */
	private void updateObject(Object obj) {
		this.update(AnnotationSqlUtil.getUpdateSql(this.getClass()), obj);
	}

}

