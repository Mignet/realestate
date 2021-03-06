package com.springjdbc.annotation;


import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 * 数据库函数的调用
 * @author Mignet
 * 该基础类继承使用
 * 例子如下：
 *@Component
public class Function1Dao extends BaseFunctionCallDao {
	public Function1Dao() {
		super("P_TEST.f_readmeun");
	}
}
 */
public class BaseFunctionCallDao{
	protected final Log logger = LogFactory.getLog(getClass());

	private static final long serialVersionUID = 2L;

	protected JdbcTemplate jdbcTemplate;

	protected DataSource dataSource;

	protected SimpleJdbcCall jdbcCall;

	private String functionName;

	public BaseFunctionCallDao() {

	}

	public BaseFunctionCallDao(String functionName) {
		this.functionName = functionName;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(this.dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		if (functionName.indexOf(".") > -1) {
			String packagename = functionName.substring(0, functionName.indexOf("."));
			String froname = functionName.substring(functionName.indexOf(".") + 1, functionName.length());
			jdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName(packagename).withFunctionName(froname);
		} else {
			jdbcCall = new SimpleJdbcCall(jdbcTemplate).withFunctionName(functionName);
		}
	}

	public <T> T call(Map<String, Object> params, Class<T> returnType) {
		return jdbcCall.executeFunction(returnType, new MapSqlParameterSource().addValues(params));
	}
}

