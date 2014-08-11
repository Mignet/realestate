package com.szhome.cq.common;

import java.util.List;
import java.util.Map;

/**
 * @author Sam
 *
 */
public class SQLCriteriaBean {
	Map<String,Object> valueMap;
	Map<String,Object> sqlMap;
	List<Map<String,Object>> valueLst;
		

	public List<Map<String, Object>> getValueLst() {
		return valueLst;
	}
	public void setValueLst(List<Map<String, Object>> valueLst) {
		this.valueLst = valueLst;
	}
	public Map<String, Object> getValueMap() {
		return valueMap;
	}
	public void setValueMap(Map<String, Object> valueMap) {
		this.valueMap = valueMap;
	}
	public Map<String, Object> getSqlMap() {
		return sqlMap;
	}
	public void setSqlMap(Map<String, Object> sqlMap) {
		this.sqlMap = sqlMap;
	}

}

