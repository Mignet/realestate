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
	 * ��ʾÿ������ֵ
	 * 
	 * @param t
	 * @return
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

	/**
	 * ��ѯ��������,�������£�
	 * 1�� ��ѯ�����򷵻ؿ� 
	 * 2�� ��ѯ���������׳� IncorrectResultSizeDataAccessException �쳣
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
	 * ��ѯ��������
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
		this.saveObject(t);
	}

	/**
	 * ���µ�������
	 * 
	 * @param entity
	 */
	public void update(T t) {
		this.updateObject(t);
	}

	/**
	 * ɾ����������
	 * 
	 * @param entity
	 */
	public void delete(T t) {
		this.update(AnnotationSqlUtil.getDeleteSql(this.getClass()), t);
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
	 * 
	 * executeQuery:(��ѯ����). <br/>
	 * (���������ض����в�ѯ).<br/>
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
	 * �õ����������ĸö����б� where sql ��д�� "where x=:x and y:=y"
	 * 
	 * @param whereSql
	 * @param paramsObject
	 * @return
	 */
	public List<T> getAll(String whereSql, Object paramsObject) {
		return this.queryListForSql(getLocalTableSql(whereSql), paramsObject);
	}

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
		List<T> list = this.queryObjectListForSql(getPageSqlForLocal(getLocalTableSql(whereSql), pageNo, pageSize),
				paramsObject, this.t);
		return new Page<T>(list, pageNo, pageSize, queryPageTotalSizeForSql(getLocalTableSql(whereSql), paramsObject));
	}

	/**
	 * ����Դ��������
	 * ��Դ��������Կ�����Ŀ�������
	 * ֧��Դ����ΪMAP�Ŀ���
	 * @param srcObject
	 */
	public void copyProperties(Object dest, Object src) {
		BeanUtil.copyProperties(dest, src);
	}

	/**
	 * oracle ���ݿ� ��ҳ��ѯ
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
	 * oracle ���ݿ� ��ҳ��ѯ where sql ��д�� "where x=:x and y:=y"
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
	 * oracle ���ݿ� ��ҳ��ѯ
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
	 * oracle ���ݿ� ��ҳ��ѯ where sql ��д�� "where x=:x and y:=y"
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
	 * ��ѯ�����б�
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
	 * ��ѯ�����б� where sql ��д�� "where x=:x and y:=y"
	 * @param key
	 * @param whereSql
	 * @param paramsObject
	 * @return
	 */
	public List<T> queryListByKey(String key, String whereSql, Object paramsObject) {
		return this.queryListForSql(concatsql(key, whereSql), paramsObject);
	}

	/**
	 * ��ѯMap �б�
	 * @param key
	 * @param returnClass
	 * @param paramsObject
	 * @return
	 */
	public List<Map<String, Object>> queryMapListByKey(String key, Object paramsObject) {
		return this.queryMapListByKey(key, "", paramsObject);

	}

	/**
	 * ��ѯMap �б� where sql ��д�� "where x=:x and y:=y"
	 * @param key
	 * @param whereSql
	 * @param paramsObject
	 * @return
	 */
	public List<Map<String, Object>> queryMapListByKey(String key, String whereSql, Object paramsObject) {
		return this.queryMapListForSql(concatsql(key, whereSql), paramsObject);

	}

	/**
	 * ִ��jdbc�� update insert delete sql
	 * @param paramsObject
	 *          ����Ĳ�������,������Map
	 */
	public int jdbcUpdateByKey(String key, Object paramsObject) {
		return this.jdbcUpdateSql(getSqlByKey(key), paramsObject);
	}

	/**
	 * ִ��jdbc�� update insert delete sql where sql ��д�� "where x=:x and y:=y"
	 * @param paramsObject
	 *          ����Ĳ�������,������Map
	 */
	public int jdbcUpdateByKey(String key, String whereSql, Object paramsObject) {
		return this.jdbcUpdateSql(concatsql(key, whereSql), paramsObject);
	}

	/**
	 * ��������SQL ��䣬�ýӿ������ã�ֻ�����ڴ������ڱ�֮��������ݸ��µ�ҵ���߼�
	 * @param sql
	 * @param paramsObject
	 * @return
	 */
	protected int jdbcBatchUpdateBySql(String sql, Object paramsObject) {
		return this.jdbcUpdateSql(sql, paramsObject);
	}

	/**
	 * ����ִ�и���sql
	 * @param sql
	 * @param paramsObject
	 * @return
	 */
	protected int[] jdbcBatchUpdate(String sql, List<Object> paramsObject) {
		SqlParameterSource[] batchparams = SqlParameterSourceUtils.createBatch(paramsObject.toArray());
		return this.jdbcTemplate.batchUpdate(sql, batchparams);
	}

	/**
	 * ����ִ�и���sql
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
	 * ��ѯ����MAP ����
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
			throw new AnnotationExceptoin("�쳣:����" + list.size() + "����¼!");
	}
	
	/**
	 * ��ѯ����MAP ����
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
			throw new AnnotationExceptoin("�쳣:����" + list.size() + "����¼!");
	}

	/**
	 * ��ѯ��������
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
			throw new AnnotationExceptoin("�쳣:����" + list.size() + "����¼!");
	}

	/**
	 * ���ô洢����
	 * ���ӣ�
	 *  create or replace procedure readmeun(
	 *	pid in  integer,
	 *	ooid out VARCHAR2,
	 *	oname out VARCHAR2) is
	 *	begin
	 *	select  t.fatherid,menuname into ooid , oname from t_menu t where menuid=pid;
	 *	end readmeun;
	 *
	 * ���÷���:	
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
	 * �������ݿ⺯��
	 * 
	 * ����:
	 * create or replace function f_readmeun(pid in integer, ooid in varchar2) return varchar2 is
	 * value1 varchar2(100);
	 * begin
	 * select menuname into value1 from t_menu t where menuid=pid and fatherid=ooid;
	 * return(value1);
	 * end f_readmeun;
	 * 
	 * ���÷���:
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
	 * <T>��ҳ��ѯ
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
	 * <T>��where���ķ�ҳ��ѯ where sql ��д�� "where x=:x and y:=y"
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
	 * <T>��ѯ��������ֵ
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
	 *  <T>��ѯ�������� where sql ��д�� "where x=:x and y:=y"
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
				throw new AnnotationExceptoin("�쳣:����" + list.size() + "����¼!");
		}
	}

	/**
	 * <T>��ѯ�����б�
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
	 * <T>��ѯ�����б� where sql ��д�� "where x=:x and y:=y"
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
	 * �򻯽�ResultSet���䵽Bean�Ķ���.
	 */
	protected <T> ParameterizedBeanPropertyRowMapper<T> resultBeanMapper(Class<T> clazz) {
		return ParameterizedBeanPropertyRowMapper.newInstance(clazz);
	}

	/**
	 * �򻯽�Bean���䵽SQL�����Ķ���.
	 */
	protected BeanPropertySqlParameterSource paramBeanMapper(Object object) {
		BeanPropertySqlParameterSource destObj = new BeanPropertySqlParameterSource(object);
		return destObj;
	}

	/**
	 * ����sql��䲢У��wheresql ����
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
//				throw new AnnotationExceptoin(" sql �����쳣:[sql key:" + key + "],���ܵ��ø÷���,sql �е� where ��ͻ!");
//		}
		return sql + " " + whereSql;
	}

	/**
	 * ��ѯ�����б����ýӿ������ã�ֻ������Ƕ�׵�SQL��䲢���޷�������XML�е�ҵ���߼�
	 * @param <T>
	 * @param sql
	 * @param paramsObject
	 * @param returnType
	 * @return
	 */
	protected <T> List<T> queryObjectListForSql(String sql, Object paramsObject, Class<T> returnType) {
		logger.debug("method  queryObjectListForSql sql:" + sql);
		if (this.isPrimitiveClass(returnType) || this.isUtilClass(returnType))
			throw new AnnotationExceptoin("�����쳣:�ýӿڲ�֧��JAVA�������ͺ�MAP List ���͵ķ��ز���,����ñ���������ӿڴ���!");
		if (paramsObject == null) {
			Map map = null;
			return this.jdbcTemplate.query(sql, resultBeanMapper(returnType), map);
		} else if (paramsObject instanceof Map)
			return this.jdbcTemplate.query(sql, resultBeanMapper(returnType), (Map) paramsObject);
		else
			return this.jdbcTemplate.query(sql, resultBeanMapper(returnType), paramBeanMapper(paramsObject));
	}

	/**
	 * ��ѯ�������󣬸ýӿ������ã�ֻ������Ƕ�׵�SQL��䲢���޷�������XML�е�ҵ���߼�
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
	 * ִ�и���sql
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
	 * ����sql
	 * @param sql
	 * @param entity
	 */
	private void update(String sql, Object entity) {
		Assert.notNull(entity);
		int size = this.jdbcUpdateSql(sql, entity);
		Assert.isTrue(1 == size);
	}

	/**
	 * ͨ��keyֵ��ȡ������xml�е�sql���.
	 */
	private String getSqlByKey(String key) {
		return XmlSqlUtils.getInstance().getSqlFormXmlBykey(key);
	}

	/**
	 * �õ��������м�¼��sql
	 * @return
	 */
	private String getAllSql() {
		return AnnotationSqlUtil.getAllSql(this.t);
	}

	/**
	 * ��ѯ��ҳ������
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

	/**ʹ��ƽ̨����������**/
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
		        //throw new GeneralFailureException("����" + tablename + "����������", ex);
		    	  LogUtil.debug("����" + tablename + "����������"+ex.getMessage());
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
	 * �������
	 * @param obj
	 */
	private void saveObject(Object obj) {
		this.update(AnnotationSqlUtil.getSaveSql(this.getClass()), obj);
	}

	/**
	 * ��ȡ��ҳsql
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
	 * ��ѯ�������м�¼
	 * @param sql
	 * @param paramsObject
	 * @return
	 */
	private List<T> queryListForSql(String sql, Object paramsObject) {
		return this.queryObjectListForSql(sql, paramsObject, this.t);
	}

	/**
	 * ��ѯMAP�б�
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
	 * ��ȡ�����sql���
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
	 * ����Ƿ�Ϊ������������������
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
	 * ����Ƿ�ΪUTIL���е���
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

