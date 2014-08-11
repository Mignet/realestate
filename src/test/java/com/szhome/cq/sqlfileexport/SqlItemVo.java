package com.szhome.cq.sqlfileexport;

public class SqlItemVo {
	private String sql;
	private String sqlId;
	private String auth;
	private String function;
	//sql涉及的表的数量
	private int relTablesCount;
	//sql涉及参数表的数量
	private int relParamTableCount;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
	  this.computeRefTableCount(sql);
	  this.computeParamTableCount(sql);
		this.sql = sql;
	}

	public String getSqlId() {
		return sqlId;
	}

	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}
	
	
	public int getRelTablesCount() {
    return relTablesCount;
  }

  public void setRelTablesCount(int relTablesCount) {
    this.relTablesCount = relTablesCount;
  }
  

  public int getRelParamTableCount() {
    return relParamTableCount;
  }

  public void setRelParamTableCount(int relParamTableCount) {
    this.relParamTableCount = relParamTableCount;
  }

  private void computeRefTableCount(String sql){
    String[] ucasestr=sql.toLowerCase().split("t_");
    int count=ucasestr.length-1;
    this.relTablesCount=count;
	}
  private void computeParamTableCount(String sql){
    String[] uparamstr=sql.toLowerCase().split("t_systemserviceparam");
    this.relParamTableCount=uparamstr.length-1;
  }
	
}

