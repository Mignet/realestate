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

public class UserRoleManageServiceUtil extends BaseServiceUtil {
	
  	
	 static UserRoleManageServiceUtil urmServiceUtil  = null;
	 public static UserRoleManageServiceUtil getInstance() {
			if(urmServiceUtil == null) {			
				urmServiceUtil 	= new UserRoleManageServiceUtil();				
			}
			
			return urmServiceUtil;
		}
	 
	 /**
	 * getUsRlMngSQLCriteria
	 * @param param
	 * @param options
	 * @return SQLCriteriaBean ����(sqlMap and valueLst)����sqlMap�е�key[combinationwheresql]��ȡwhere����sql,��ʽ���� and ��ͷ��<where statement...>
	 * ,��sqlMap�����������ֶζ�Ӧ��sql where ���� ,valueLst������Ӧ������ֵ
	 */
	public static SQLCriteriaBean getUsRlMngSQLCriteria(Map<String,Object> param,String[][] options){
		    Map<String,Object> sqlMap = null;
	    	String inwhere = "vurr.user_id in (select userid from t_Role_r_User where roleid #CONDITION# )";
	    	param.put("inwhere", inwhere);
		
		       Map<String,Object> valueMap = new HashMap<String,Object>();
		       sqlMap = generateSQLCriteria(valueMap, param, options);
				    
		    //set return value;		
			SQLCriteriaBean	cBean = new SQLCriteriaBean();
			cBean.setSqlMap(sqlMap);
			cBean.setValueMap(valueMap);
			return cBean;
	 }
}

