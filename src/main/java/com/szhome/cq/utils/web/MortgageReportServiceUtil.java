package com.szhome.cq.utils.web;

import java.util.HashMap;
import java.util.Map;

import com.szhome.cq.common.SQLCriteriaBean;
import com.szhome.cq.utils.BaseServiceUtil;

public class MortgageReportServiceUtil extends BaseServiceUtil {
	
  	
	 static MortgageReportServiceUtil mortgagereportServiceUtil  = null;
	 public static MortgageReportServiceUtil getInstance() {
			if(mortgagereportServiceUtil == null) {			
				mortgagereportServiceUtil 	= new MortgageReportServiceUtil();				
			}
			
			return mortgagereportServiceUtil;
		}
	 
	 /**
	  * getMortReportSQLCriteria
	 * @param paramMap
	 * @param parameters
	 * @param options
	 * @return SQLCriteriaBean
	 */
	public static SQLCriteriaBean getMortReportSQLCriteria(Map<String,Object> paramMap,Map<String,Object> parameters,String[][] options){
		    Map<String,Object> valueMap = new HashMap<String,Object>();
		    Map<String,Object> sqlMap = generateSQLCriteria(valueMap, parameters, paramMap, options);
		    //set return value;		
			SQLCriteriaBean	cBean = new SQLCriteriaBean();
			cBean.setSqlMap(sqlMap);
			cBean.setValueMap(valueMap);
			return cBean;
	 }
}

