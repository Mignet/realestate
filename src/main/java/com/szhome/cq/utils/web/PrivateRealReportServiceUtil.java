package com.szhome.cq.utils.web;

import java.util.HashMap;
import java.util.Map;

import com.szhome.cq.common.SQLCriteriaBean;
import com.szhome.cq.utils.BaseServiceUtil;

public class PrivateRealReportServiceUtil extends BaseServiceUtil {
	
  	
	 static PrivateRealReportServiceUtil privaterealreportServiceUtil  = null;
	 public static PrivateRealReportServiceUtil getInstance() {
			if(privaterealreportServiceUtil == null) {			
				privaterealreportServiceUtil 	= new PrivateRealReportServiceUtil();				
			}
			
			return privaterealreportServiceUtil;
		}
	 
	 public static SQLCriteriaBean getPrivateRealReportSQLCriteria(Map<String,Object> paramMap,Map<String,Object> parameters,String[][] options){
		    Map<String,Object> valueMap = new HashMap<String,Object>();
		    Map<String,Object> sqlMap = generateSQLCriteria(valueMap, parameters, paramMap, options);
		    //set return value;		
			SQLCriteriaBean	cBean = new SQLCriteriaBean();
			cBean.setSqlMap(sqlMap);
			cBean.setValueMap(valueMap);
			return cBean;
	 }
}

