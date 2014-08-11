package com.szhome.cq.web.sysmanage;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plan.commons.Row;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IUserRoleManageFacade;
import com.szhome.cq.common.SQLCriteriaBean;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.utils.DateUtils;
import com.szhome.cq.utils.JsonUtil;
import com.szhome.cq.utils.Util;
import com.szhome.cq.utils.web.UserRoleManageServiceUtil;
import com.szhome.cq.web.JsonResult;

/**
 * 
 * @author user
 *
 */
public class UserRoleManageAction extends BaseDelegate{

	IUserRoleManageFacade urmFacade = FacadeFactory.getUserRoleManageFacade();
	
	//二元数组,数组值意义,1.数据表里的字段,2.实体对象对应字段,3.类型区分【1:代表字符串类型,2:代表时间类型,3:代表日期加时间,4:代表金额类型,5:时间范围,6:SQl语句之in条件,7:SQL语句之like条件】
    static final String[][] options = {{"vurr.user_name","username","7"},
				                      {"vurr.createdate","createdateL","5.1"},
				                      {"vurr.createdate","createdateR","5.2"},
				                      {"vurr.roleid","roleids","6"}
    };
	
	public String getUserRoleList(Row row){
		List<Map<String,Object>> userroleList = urmFacade.queryAllUserRoles();
		for(Map<String,Object> urmap:userroleList){
			urmap.put("createdate",DateUtils.date2String((Date)urmap.get("createdate"),DateUtils.DATE_FORMAT_DEFAULT));
		}
		return JsonUtil.list2json(userroleList);
	}
	
	public JsonResult updateUserRole(Row row){
		Object roleid = row.getObject("roleid");
		String user_id = row.getString("user_id");
		if(user_id == null || user_id.trim().length() == 0){
			return new JsonResult(false,"授权失败！");
		}
		Map<String,Object> param = new HashMap<String, Object>();
		param.put(user_id, roleid);
		try {
			urmFacade.updateUserRole(user_id, param);
		} catch (Exception e) {
			return new JsonResult(false,"授权失败！",e.getMessage());
		}
		return new JsonResult(true, "授权成功！");
	}
	
	public String searchUserListByOptions(Row row){
		Map<String,Object> param = new HashMap<String,Object>();
		String[] roleids = new String[1];
		String userName = row.getString("user_name");
		String createdateL = row.getString("createdateL");
		String createdateR = row.getString("createdateR");
		Object roleid      = row.getObject("roleid[]");
		if(roleid instanceof String){
			roleids[0] = (String)roleid;
		}else if(roleid instanceof String[]){
			roleids = (String[])roleid;
		}
		param.put("username", userName);
		param.put("createdateL", createdateL);
		param.put("createdateR", createdateR);
		param.put("roleids", roleids);
		
		SQLCriteriaBean sbean = UserRoleManageServiceUtil.getUsRlMngSQLCriteria(param, options);
		List<Map<String,Object>> userroleList = null;
		try {
			Object obj = sbean.getSqlMap().get("combinationwheresql");
			String whereSql = "where 1=1";
			if(Util.isNotNull2Empty(obj))
			  whereSql = whereSql+obj.toString();
		    userroleList = urmFacade.searchByParam(sbean.getValueMap(),whereSql);
			for(Map<String,Object> urmap:userroleList){
				urmap.put("createdate",DateUtils.date2String((Date)urmap.get("createdate"),DateUtils.DATE_FORMAT_DEFAULT));
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return JsonUtil.list2json(userroleList);
	}
	
	

}

