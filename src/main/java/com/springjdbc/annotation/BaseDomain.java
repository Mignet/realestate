package com.springjdbc.annotation;

import java.io.Serializable;
import java.sql.SQLException;
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

import com.szhome.commons.database.SequenceManager;
import com.szhome.commons.log.LogUtil;

/**
 * BaseDomain is DAO of all base table
 * <ol>
 * <li>CRUD of single table</li>
 * <li>any enquire</li>
 * <li>call procedure or function</li>
 * </ol>
 * @author Mignet
 *
 * @param <T>
 */
public class BaseDomain<T> implements Serializable {
	protected final Log logger = LogFactory.getLog(getClass());

	private static final long serialVersionUID = 1L;

	protected SimpleJdbcTemplate jdbcTemplate;

	protected DataSource dataSource;

	protected Class<T> t;

	private final static String QUERY_PAGE_PREFIX = " select * from (SELECT aa.*, rownum r FROM ( ";
	private final static String QUERY_PAGE_SUFFIX1 = " ) aa WHERE rownum <=";
	private final static String QUERY_PAGE_SUFFIX2 = " )    where r >";

	private final static String QUERY_PAGE_TOTALSIZE_PREFIX = " SELECT count(1) as totalsize from (";

	private final static String QUERY_PAGE_TOTALSIZE_SUFFIX = " )  total";

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
	 * 
	 * executeQuery:(查询序列). <br/>
	 * (仅适用于特定序列查询).<br/>
	 *
	 * @author Mignet
	 * @param sql
	 * @return
	 * @since JDK 1.6
	 */
	public String executeQuery(String sql){
		logger.debug("method  queryObjectForSql sql:" + sql);
		return this.queryObjectForSql(sql, null, String.class);
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
		List<T> list = this.queryObjectListForSql(getPageSqlForLocal(getLocalTableSql(whereSql), pageNo, pageSize),
				paramsObject, this.t);
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
	 * oracle 数据库 分页查询
	 * @param key
	 * @param returnClass
	 * @param paramsObject
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<T> queryDomainPageBykeyForOracle(String key, Object paramsObject, int pageNo, int pageSize) {
		List<T> list = this.queryListForSql(getPageSql(key, null, pageNo, pageSize), paramsObject);
		return new Page<T>(list, pageNo, pageSize, queryPageTotalSize(key, null, paramsObject));
	}

	/**
	 * oracle 数据库 分页查询 where sql 的写法 "where x=:x and y:=y"
	 * @param key
	 * @param whereSql
	 * @param paramsObject
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<T> queryDomainPageBykeyForOracle(String key, String whereSql, Object paramsObject, int pageNo,
			int pageSize) {
		List<T> list = this.queryListForSql(getPageSql(key, whereSql, pageNo, pageSize), paramsObject);
		return new Page<T>(list, pageNo, pageSize, queryPageTotalSize(key, whereSql, paramsObject));
	}

	/**
	 * oracle 数据库 分页查询
	 * @param key
	 * @param returnClass
	 * @param paramsObject
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Map<String, Object>> queryMapPageBykeyForOracle(String key, Object paramsObject, int pageNo,
			int pageSize) {
		List<Map<String, Object>> list = this.queryMapListForSql(getPageSql(key, null, pageNo, pageSize), paramsObject);
		return new Page<Map<String, Object>>(list, pageNo, pageSize, queryPageTotalSize(key, null, paramsObject));
	}

	/**
	 * oracle 数据库 分页查询 where sql 的写法 "where x=:x and y:=y"
	 * @param key
	 * @param whereSql
	 * @param paramsObject
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Map<String, Object>> queryMapPageBykeyForOracle(String key, String whereSql, Object paramsObject,
			int pageNo, int pageSize) {
		List<Map<String, Object>> list = this.queryMapListForSql(getPageSql(key, whereSql, pageNo, pageSize),
				paramsObject);
		return new Page<Map<String, Object>>(list, pageNo, pageSize, queryPageTotalSize(key, whereSql, paramsObject));
	}

	/**
	 * 查询对象列表
	 * 
	 * @param key
	 * @param returnClass
	 * @param paramsObject
	 * @return
	 */
	public List<T> queryListByKey(String key, Object paramsObject) {
		return this.queryListForSql(this.getSqlByKey(key), paramsObject);

	}

	/**
	 * 查询对象列表 where sql 的写法 "where x=:x and y:=y"
	 * @param key
	 * @param whereSql
	 * @param paramsObject
	 * @return
	 */
	public List<T> queryListByKey(String key, String whereSql, Object paramsObject) {
		return this.queryListForSql(concatsql(key, whereSql), paramsObject);
	}

	/**
	 * 查询Map 列表
	 * @param key
	 * @param returnClass
	 * @param paramsObject
	 * @return
	 */
	public List<Map<String, Object>> queryMapListByKey(String key, Object paramsObject) {
		return this.queryMapListByKey(key, "", paramsObject);

	}

	/**
	 * 查询Map 列表 where sql 的写法 "where x=:x and y:=y"
	 * @param key
	 * @param whereSql
	 * @param paramsObject
	 * @return
	 */
	public List<Map<String, Object>> queryMapListByKey(String key, String whereSql, Object paramsObject) {
		return this.queryMapListForSql(concatsql(key, whereSql), paramsObject);

	}

	/**
	 * 执行jdbc的 update insert delete sql
	 * @param paramsObject
	 *          传入的参数对象,可以是Map
	 */
	public int jdbcUpdateByKey(String key, Object paramsObject) {
		return this.jdbcUpdateSql(getSqlByKey(key), paramsObject);
	}

	/**
	 * 执行jdbc的 update insert delete sql where sql 的写法 "where x=:x and y:=y"
	 * @param paramsObject
	 *          传入的参数对象,可以是Map
	 */
	public int jdbcUpdateByKey(String key, String whereSql, Object paramsObject) {
		return this.jdbcUpdateSql(concatsql(key, whereSql), paramsObject);
	}

	/**
	 * 批量更新SQL 语句，该接口请慎用，只能用于大批量在表之间进行数据更新的业务逻辑
	 * @param sql
	 * @param paramsObject
	 * @return
	 */
	protected int jdbcBatchUpdateBySql(String sql, Object paramsObject) {
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
	 * 查询单个MAP 对象
	 * 
	 * @param key
	 * @param paramsObject
	 * @return
	 */
	public Map<String, Object> queryMapByKey(String key, Object paramsObject) {
		List<Map<String, Object>> list = this.queryMapListByKey(key, paramsObject);
		if (list == null || list.size() < 1)
			return null;
		else if (list.size() == 1)
			return list.get(0);
		else
			throw new AnnotationExceptoin("异常:存在" + list.size() + "条记录!");
	}
	
	/**
	 * 查询单个MAP 对象
	 * 
	 * @param key
	 * @param paramsObject
	 * @return
	 */
	public Map<String, Object> queryMapByKey(String key, String whereSql,Object paramsObject) {
		List<Map<String, Object>> list = this.queryMapListByKey(key,whereSql,paramsObject);
		if (list == null || list.size() < 1)
			return null;
		else if (list.size() == 1)
			return list.get(0);
		else
			throw new AnnotationExceptoin("异常:存在" + list.size() + "条记录!");
	}

	/**
	 * 查询单个对象
	 * @param key
	 * @param returnClass
	 * @param paramsObject
	 * @return
	 */
	public T queryDomainBykey(String key, Object paramsObject) {
		List<T> list = this.queryListByKey(key, paramsObject);
		if (list == null || list.size() < 1)
			return null;
		else if (list.size() == 1)
			return list.get(0);
		else
			throw new AnnotationExceptoin("异常:存在" + list.size() + "条记录!");
	}

	/**
	 * 调用存储过程
	 * 例子：
	 *  create or replace procedure readmeun(
	 *	pid in  integer,
	 *	ooid out VARCHAR2,
	 *	oname out VARCHAR2) is
	 *	begin
	 *	select  t.fatherid,menuname into ooid , oname from t_menu t where menuid=pid;
	 *	end readmeun;
	 *
	 * 调用方法:	
	 *   Map<String,Object> paramsMap =new HashMap<String,Object>();
		paramsMap.put("pid", new java.math.BigDecimal(1200102100));
		Map<String,Object> map=us.callProcedure("P_TEST.readmeun", paramsMap);
		System.out.println((String)map.get("ooid"));
		System.out.println((String)map.get("oname"));
		
		
	 * @param procedureName
	 * @param paramsVale
	 * @param paramsType
	 * @return
	 */
	public Map<String, Object> callProcedure(String procedureName, Map<String, Object> params) {
		JdbcTemplate jt = new JdbcTemplate(this.dataSource);
		jt.setResultsMapCaseInsensitive(true);
		SimpleJdbcCall jdbcCall = null;
		if (procedureName.indexOf(".") > -1) {
			String packagename = procedureName.substring(0, procedureName.indexOf("."));
			String proname = procedureName.substring(procedureName.indexOf(".") + 1, procedureName.length());
			jdbcCall = new SimpleJdbcCall(jt).withCatalogName(packagename).withProcedureName(proname);
		} else {
			jdbcCall = new SimpleJdbcCall(jt).withProcedureName(procedureName);
		}
		return jdbcCall.execute(new MapSqlParameterSource().addValues(params));
	}

	/**
	 * 调用数据库函数
	 * 
	 * 例子:
	 * create or replace function f_readmeun(pid in integer, ooid in varchar2) return varchar2 is
	 * value1 varchar2(100);
	 * begin
	 * select menuname into value1 from t_menu t where menuid=pid and fatherid=ooid;
	 * return(value1);
	 * end f_readmeun;
	 * 
	 * 调用方法:
	 * Map<String,Object> paramsMap =new HashMap<String,Object>();
	 *	paramsMap.put("pid", new java.math.BigDecimal(1200102100));
	 *	paramsMap.put("ooid", "1200100000");
	 *	String value=us.callFunction("P_TEST.f_readmeun", paramsMap,String.class);
	 *	System.out.println(value);
	 * 
	 * 
	 * @param <T>
	 * @param functionName
	 * @param params
	 * @param returnType
	 * @return
	 */
	public <T> T callFunction(String functionName, Map<String, Object> params, Class<T> returnType) {
		JdbcTemplate jt = new JdbcTemplate(this.dataSource);
		jt.setResultsMapCaseInsensitive(true);
		SimpleJdbcCall jdbcCall = null;
		if (functionName.indexOf(".") > -1) {
			String packagename = functionName.substring(0, functionName.indexOf("."));
			String froname = functionName.substring(functionName.indexOf(".") + 1, functionName.length());
			jdbcCall = new SimpleJdbcCall(jt).withCatalogName(packagename).withFunctionName(froname);
		} else {
			jdbcCall = new SimpleJdbcCall(jt).withFunctionName(functionName);
		}
		return jdbcCall.executeFunction(returnType, new MapSqlParameterSource().addValues(params));
	}

	/**
	 * <T>分页查询
	 * @param <T>
	 * @param key
	 * @param paramsObject
	 * @param returnType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public <T> Page<T> queryObjectPageBykeyForOracle(String key, Object paramsObject, Class<T> returnType, int pageNo,
			int pageSize) {
		List<T> list = this.queryObjectListForSql(getPageSql(key, null, pageNo, pageSize), paramsObject, returnType);
		return new Page<T>(list, pageNo, pageSize, queryPageTotalSize(key, null, paramsObject));
	}

	/**
	 * <T>带where语句的分页查询 where sql 的写法 "where x=:x and y:=y"
	 * @param <T>
	 * @param key
	 * @param whereSql
	 * @param paramsObject
	 * @param returnType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public <T> Page<T> queryObjectPageBykeyForOracle(String key, String whereSql, Object paramsObject,
			Class<T> returnType, int pageNo, int pageSize) {
		List<T> list = this
				.queryObjectListForSql(getPageSql(key, whereSql, pageNo, pageSize), paramsObject, returnType);
		return new Page<T>(list, pageNo, pageSize, queryPageTotalSize(key, whereSql, paramsObject));
	}

	/**
	 * <T>查询单个对象值
	 * @param <T>
	 * @param key
	 * @param paramsObject
	 * @param returnType
	 * @return
	 */
	public <T> T queryObjectByKey(String key, Object paramsObject, Class<T> returnType) {
		return this.queryObjectByKey(key, "", paramsObject, returnType);
	}

	/**
	 *  <T>查询单个对象 where sql 的写法 "where x=:x and y:=y"
	 * @param <T>
	 * @param key
	 * @param whereSql
	 * @param paramsObject
	 * @param returnType
	 * @return
	 */
	public <T> T queryObjectByKey(String key, String whereSql, Object paramsObject, Class<T> returnType) {
		if (isPrimitiveClass(returnType)) {
			return this.queryObjectForSql(concatsql(key, whereSql), paramsObject, returnType);
		} else {
			List<T> list = queryObjectListByKey(key, whereSql, paramsObject, returnType);
			if (list == null || list.size() < 1)
				return null;
			else if (list.size() == 1)
				return list.get(0);
			else
				throw new AnnotationExceptoin("异常:存在" + list.size() + "条记录!");
		}
	}

	/**
	 * <T>查询对象列表
	 * @param <T>
	 * @param key
	 * @param paramsObject
	 * @param returnType
	 * @return
	 */
	public <T> List<T> queryObjectListByKey(String key, Object paramsObject, Class<T> returnType) {
		return queryObjectListByKey(key, "", paramsObject, returnType);
	}

	/**
	 * <T>查询对象列表 where sql 的写法 "where x=:x and y:=y"
	 * @param <T>
	 * @param key
	 * @param whereSql
	 * @param paramsObject
	 * @param returnType
	 * @return
	 */
	public <T> List<T> queryObjectListByKey(String key, String whereSql, Object paramsObject, Class<T> returnType) {
		return this.queryObjectListForSql(concatsql(key, whereSql), paramsObject, returnType);
	}

	/**
	 * 简化将ResultSet反射到Bean的定义.
	 */
	protected <T> ParameterizedBeanPropertyRowMapper<T> resultBeanMapper(Class<T> clazz) {
		return ParameterizedBeanPropertyRowMapper.newInstance(clazz);
	}

	/**
	 * 简化将Bean反射到SQL参数的定义.
	 */
	protected BeanPropertySqlParameterSource paramBeanMapper(Object object) {
		BeanPropertySqlParameterSource destObj = new BeanPropertySqlParameterSource(object);
		return destObj;
	}

	/**
	 * 链接sql语句并校验wheresql 参数
	 * @param key
	 * @param whereSql
	 * @return
	 */
	private String concatsql(String key, String whereSql) {
		if (whereSql == null || whereSql.length() < 1) {
			whereSql = "";
		}
		whereSql = whereSql.trim();
		String sql = this.getSqlByKey(key);
//		if (whereSql.length() > 0) {
//			if (sql.toLowerCase().indexOf("where") > -1)
//				throw new AnnotationExceptoin(" sql 解析异常:[sql key:" + key + "],不能调用该方法,sql 中的 where 冲突!");
//		}
		return sql + " " + whereSql;
	}

	/**
	 * 查询对象列表，，该接口请慎用，只用于有嵌套的SQL语句并且无法分离在XML中的业务逻辑
	 * @param <T>
	 * @param sql
	 * @param paramsObject
	 * @param returnType
	 * @return
	 */
	protected <T> List<T> queryObjectListForSql(String sql, Object paramsObject, Class<T> returnType) {
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
	 * 查询单个对象，该接口请慎用，只用于有嵌套的SQL语句并且无法分离在XML中的业务逻辑
	 * @param <T>
	 * @param sql
	 * @param paramsObject
	 * @param returnType
	 * @return
	 */
	protected <T> T queryObjectForSql(String sql, Object paramsObject, Class<T> returnType) {
		if (paramsObject == null) {
			Map map = null;
			return this.jdbcTemplate.queryForObject(sql, returnType, map);
		} else if (paramsObject instanceof Map)
			return this.jdbcTemplate.queryForObject(sql, returnType, (Map) paramsObject);
		else
			return this.jdbcTemplate.queryForObject(sql, returnType, paramBeanMapper(paramsObject));

	}
	
	protected <T>Page<T> queryObjectPageForSql(String sql, Object paramsObject, Class<T> returnType,int pageNo,int pageSize){
		List<T> list = this.queryObjectListForSql(getPageSqlForLocal(sql, pageNo, pageSize), paramsObject,returnType);
		return new Page<T>(list, pageNo, pageSize, queryPageTotalSizeForSql(sql,  paramsObject));
	}
	
	/**
	 * 执行更新sql
	 * @param sql
	 * @param paramsObject
	 * @return
	 */
	protected int jdbcUpdateSql(String sql, Object paramsObject) {
		if (paramsObject == null) {
			Map map = null;
			return this.jdbcTemplate.update(sql, map);
		} else if (paramsObject instanceof Map)
			return this.jdbcTemplate.update(sql, (Map) paramsObject);
		else
			return this.jdbcTemplate.update(sql, paramBeanMapper(paramsObject));
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
	 * 通过key值获取配置在xml中的sql语句.
	 */
	private String getSqlByKey(String key) {
		return XmlSqlUtils.getInstance().getSqlFormXmlBykey(key);
	}

	/**
	 * 得到单表所有记录的sql
	 * @return
	 */
	private String getAllSql() {
		return AnnotationSqlUtil.getAllSql(this.t);
	}

	/**
	 * 查询分页总数量
	 * @param key
	 * @param paramsObject
	 * @return
	 */
	private int queryPageTotalSize(String key, String whereSql, Object paramsObject) {
		String sql = this.concatsql(key, whereSql);
		return queryPageTotalSizeForSql(sql, paramsObject);
	}

	private int queryPageTotalSizeForSql(String sql, Object paramsObject) {
		sql = QUERY_PAGE_TOTALSIZE_PREFIX + sql + QUERY_PAGE_TOTALSIZE_SUFFIX;
		logger.debug("total size page sql:" + sql);
		return this.queryObjectForSql(sql, paramsObject, Integer.class).intValue();
	}

	/**使用平台的主键管理**/
	public String getSeqId(){
		String tablename = getTableName(this.getClass());
		/*String sql = "select SEQ_"+tablename.toUpperCase()+"_ID.nextval from dual";
		logger.debug("tablename:"+tablename+" seq sql:" + sql);
		String seqId = this.queryObjectForSql(sql,null,String.class);*/
		String seqId = "";
		  try {
			  seqId= String.valueOf(SequenceManager.getInstance().getNextSequence(
		        		  tablename));
		      }
		      catch (SQLException ex) {
		        //throw new GeneralFailureException("生成" + tablename + "的主键错误！", ex);
		    	  LogUtil.debug("生成" + tablename + "的主键错误！"+ex.getMessage());
		    	  return seqId;
		      }

		return seqId;
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
	/**
	 * 保存对象
	 * @param obj
	 */
	private void saveObject(Object obj) {
		this.update(AnnotationSqlUtil.getSaveSql(this.getClass()), obj);
	}

	/**
	 * 获取分页sql
	 * @param key
	 * @param whereSql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	private String getPageSql(String key, String whereSql, int pageNo, int pageSize) {
		String sql = this.concatsql(key, whereSql);
		return getPageSqlForLocal(sql, pageNo, pageSize);
	}

	private String getPageSqlForLocal(String sql, int pageNo, int pageSize) {
		int endSize = pageNo * pageSize;
		int startSize = (pageNo - 1) * pageSize;
		sql = QUERY_PAGE_PREFIX + sql + QUERY_PAGE_SUFFIX1 + endSize + QUERY_PAGE_SUFFIX2 + startSize;
//		sql  = sql + " limit "+pageSize +" offset "+startSize;
		logger.debug("page sql:" + sql);
		return sql;
	}

	/**
	 * 查询单表所有记录
	 * @param sql
	 * @param paramsObject
	 * @return
	 */
	private List<T> queryListForSql(String sql, Object paramsObject) {
		return this.queryObjectListForSql(sql, paramsObject, this.t);
	}

	/**
	 * 查询MAP列表
	 * @param sql
	 * @param paramsObject
	 * @return
	 */
	private List<Map<String, Object>> queryMapListForSql(String sql, Object paramsObject) {
		logger.debug("method  queryMapListForSql sql:" + sql);
		if (paramsObject == null) {
			Map map = null;
			return this.jdbcTemplate.queryForList(sql, map);
		} else if (paramsObject instanceof Map)
			return this.jdbcTemplate.queryForList(sql, (Map) paramsObject);
		else
			return this.jdbcTemplate.queryForList(sql, paramBeanMapper(paramsObject));
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

	private void updateObject(Object obj) {
		this.update(AnnotationSqlUtil.getUpdateSql(this.getClass()), obj);
	}

}

