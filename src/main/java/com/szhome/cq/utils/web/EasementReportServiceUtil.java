package com.szhome.cq.utils.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.szhome.cq.common.SQLCriteriaBean;
import com.szhome.cq.utils.BaseServiceUtil;
import com.szhome.cq.utils.Util;

public class EasementReportServiceUtil extends BaseServiceUtil {
	
  	
	 static EasementReportServiceUtil demurrerreportServiceUtil  = null;
	 public static EasementReportServiceUtil getInstance() {
			if(demurrerreportServiceUtil == null) {			
				demurrerreportServiceUtil 	= new EasementReportServiceUtil();				
			}
			
			return demurrerreportServiceUtil;
		}
	 
	 public static SQLCriteriaBean getDemurrerReportSQLCriteria(Map<String,Object> paramMap,Map<String,Object> parameters,String[][] options){
		 StringBuffer jsonStr = new StringBuffer();
		   List<Map<String,Object>> valueLst = new ArrayList<Map<String,Object>>();
		    Map<String,Object> sqlMap = null;
		    Object objitems = paramMap.get("items");
		    JSONArray json = null;
		    try {
				if(Util.isNotNull2Empty(objitems)){
				    jsonStr.append("[");
				    jsonStr.append(objitems);
				    jsonStr.append("]");
					json =new JSONArray(jsonStr.toString());
				    for(int i=0;i<json.length();i++){
				       JSONObject jobj = json.getJSONObject(i);
				       String demurrerId = jobj.getString("demurrerId");
				       String reg_unit_type = jobj.getString("reg_unit_type");
				       paramMap.put("demurrerId", demurrerId);
				       paramMap.put("reg_unit_type", reg_unit_type);
				       Map<String,Object> valueMap = new HashMap<String,Object>();
				       sqlMap = generateSQLCriteria(valueMap, parameters, paramMap, options);
				       valueLst.add(valueMap);
				    }
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		    //set return value;		
			SQLCriteriaBean	cBean = new SQLCriteriaBean();
			cBean.setSqlMap(sqlMap);
			cBean.setValueLst(valueLst);
			return cBean;
	 }
	 public static SQLCriteriaBean getDemurrerSQLCriteria(Map<String,Object> paramMap,Map<String,Object> parameters,String[][] options){
		 Map<String,Object> valueMap = new HashMap<String,Object>();
		 Map<String,Object> sqlMap = generateSQLCriteria(valueMap, parameters, paramMap, options);
		 //set return value;		
		 SQLCriteriaBean	cBean = new SQLCriteriaBean();
		 cBean.setSqlMap(sqlMap);
		 cBean.setValueMap(valueMap);
		 return cBean;
	 }
}

