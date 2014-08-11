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
public class Procedure1Dao extends BaseProcedureCallDao {
	public Procedure1Dao(){
		super("P_TEST.readmeun");
	}

}
 */
public class BaseProcedureCallDao {
	protected final Log logger = LogFactory.getLog(getClass());

	private static final long serialVersionUID = 2L;

	protected JdbcTemplate jdbcTemplate;

	protected DataSource dataSource;

	protected SimpleJdbcCall jdbcCall;

	private String procedureName;

	public BaseProcedureCallDao() {

	}

	public BaseProcedureCallDao(String procedureName) {
		this.procedureName = procedureName;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(this.dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		if (procedureName.indexOf(".") > -1) {
			String packagename = procedureName.substring(0, procedureName.indexOf("."));
			String proname = procedureName.substring(procedureName.indexOf(".") + 1, procedureName.length());
			jdbcCall = new SimpleJdbcCall(jdbcTemplate).withCatalogName(packagename).withProcedureName(proname);
		} else {
			jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procedureName);
		}

	}

	public Map<String, Object> call(Map<String, Object> params) {
		return jdbcCall.execute(new MapSqlParameterSource().addValues(params));
	}

}

