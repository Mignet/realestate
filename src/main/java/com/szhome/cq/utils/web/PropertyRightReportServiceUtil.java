package com.szhome.cq.utils.web;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.szhome.cq.common.SQLCriteriaBean;
import com.szhome.cq.utils.BaseServiceUtil;
import com.szhome.cq.utils.Util;
import com.szhome.cq.utils.WbfConstants;

public class PropertyRightReportServiceUtil extends BaseServiceUtil {
	
  	
	 static PropertyRightReportServiceUtil mortgagereportServiceUtil  = null;
	 public static PropertyRightReportServiceUtil getInstance() {
			if(mortgagereportServiceUtil == null) {			
				mortgagereportServiceUtil 	= new PropertyRightReportServiceUtil();				
			}
			
			return mortgagereportServiceUtil;
		}
	 
	 public static SQLCriteriaBean getPropertyRightReportSQLCriteria(Map<String,Object> paramMap,Map<String,Object> parameters,String[][] options){
		    StringBuffer jsonStr = new StringBuffer();
		    Map<String,Object> valueMap = new HashMap<String,Object>();
		    Object objitems = paramMap.get("items");
		    JSONArray json = null;
		    StringBuffer userightIds = new StringBuffer();
		    StringBuffer ownershipIds = new StringBuffer();
		    try {
				if(Util.isNotNull2Empty(objitems)){
				    jsonStr.append("[");
				    jsonStr.append(objitems);
				    jsonStr.append("]");
					json =new JSONArray(jsonStr.toString());
				    for(int i=0;i<json.length();i++){
				       JSONObject jobj = json.getJSONObject(i);
				       String rightId = jobj.getString("rightId");
				       String reg_unit_type = jobj.getString("reg_unit_type");
				       if(reg_unit_type.equals(WbfConstants.PARCEL)){
				    	  userightIds.append(rightId);
				    	  userightIds.append(",");
				       }else if(reg_unit_type.equals(WbfConstants.HOUSE)){
				    	   ownershipIds.append(rightId);
				    	   ownershipIds.append(",");
				       }
				    }
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		    if(Util.isNotNull2Empty(userightIds))
		    paramMap.put("userightId", userightIds.substring(0, userightIds.length()-1).split(","));
		    if(Util.isNotNull2Empty(ownershipIds))
		    paramMap.put("ownershipId", ownershipIds.substring(0, ownershipIds.length()-1).split(","));
		    Map<String,Object> sqlMap = generateSQLCriteria(valueMap, parameters, paramMap, options);
		    //set return value;		
			SQLCriteriaBean	cBean = new SQLCriteriaBean();
			cBean.setSqlMap(sqlMap);
			cBean.setValueMap(valueMap);
			return cBean;
	 }
	 public static SQLCriteriaBean getHolderSQLCriteria(Map<String,Object> paramMap,Map<String,Object> parameters,String[][] options){
		 Map<String,Object> valueMap = new HashMap<String,Object>();
		 Map<String,Object> sqlMap = generateSQLCriteria(valueMap, parameters, paramMap, options);
		 //set return value;		
		 SQLCriteriaBean	cBean = new SQLCriteriaBean();
		 cBean.setSqlMap(sqlMap);
		 cBean.setValueMap(valueMap);
		 return cBean;
	 }
}

