package com.szhome.cq.utils.web;

import java.util.HashMap;
import java.util.Map;

import com.szhome.cq.common.SQLCriteriaBean;
import com.szhome.cq.utils.BaseServiceUtil;

public class PresaleReportServiceUtil extends BaseServiceUtil {
	
  	
	 static PresaleReportServiceUtil presalereportServiceUtil  = null;
	 public static PresaleReportServiceUtil getInstance() {
			if(presalereportServiceUtil == null) {			
				presalereportServiceUtil 	= new PresaleReportServiceUtil();				
			}
			
			return presalereportServiceUtil;
		}
	 
	 public static SQLCriteriaBean getPresaleReportSQLCriteria(Map<String,Object> paramMap,Map<String,Object> parameters,String[][] options){
		    Map<String,Object> valueMap = new HashMap<String,Object>();
		    Map<String,Object> sqlMap = generateSQLCriteria(valueMap, parameters, paramMap, options);
		    //set return value;		
			SQLCriteriaBean	cBean = new SQLCriteriaBean();
			cBean.setSqlMap(sqlMap);
			cBean.setValueMap(valueMap);
			return cBean;
	 }
}

