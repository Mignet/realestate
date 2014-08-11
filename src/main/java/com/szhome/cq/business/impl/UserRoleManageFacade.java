package com.szhome.cq.business.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.szhome.cq.business.IUserRoleManageFacade;
import com.szhome.cq.domain.model.Role;
import com.szhome.cq.domain.model.RoleRUser;
import com.szhome.cq.utils.DateUtils;
import com.szhome.cq.utils.Util;

/**
 * 用户角色管理Service
 * [预留]
 * @author sam
 */
@Component
@Transactional
@Scope("prototype")
public class UserRoleManageFacade implements IUserRoleManageFacade {

	@Autowired
	private Role roleDao;
	@Autowired
	private RoleRUser roleruserDao;

	@Override
	public List<Map<String,Object>> queryUserRoleByUserId(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String,Object>> queryAllUserRoles() {
	   List<Map<String,Object>> userroleList = roleDao.queryMapListByKey("UserRole.getAllUserRoles", null);
	   //String 
	   for(int i = 0;i < userroleList.size();i++){
		   Map<String,Object> urmap = userroleList.get(i);
		  // urmap.
	   }
	   return userroleList;
	}

	@Override
	public Boolean updateUserRole(String key,Map<String,Object> urmap) throws Exception {
		Boolean flag = false;
		String roleIdstr = null;
		String[] roleids = null;
		if(key != null && key.trim().length() > 0  ){
			RoleRUser roleRUser = new RoleRUser(key);//设置删除条件
			try {
				Object tempobj = urmap.get(key);
				if(Util.isNotNull2Empty(tempobj)){
					if(tempobj instanceof String){
						roleIdstr = (String)tempobj;
					}else if(tempobj instanceof String[]){
						roleids = (String[])tempobj;
					}
				}
				//先删除原先的数据
//				List<RoleRUser> rruLst = roleruserDao.queryListByKey("UserRole.queryUserRoleByUserid", roleRUser);
//				if(rruLst != null && rruLst.size() > 0)
				  roleruserDao.jdbcUpdateByKey("UserRole.deleteUserRoleByUserid", roleRUser);
                //重新增加新的数据
				if(Util.notNullEmpty(roleIdstr)){
					roleRUser = new RoleRUser(key);
					roleRUser.setCreator("999999999");//系统管理员
					roleRUser.setCreatedate(DateUtils.getCurTime());
					roleRUser.setRoleid(roleIdstr.trim());
					roleruserDao.save(roleRUser);
				}else if(Util.isNotNull2Empty(roleids)){
					for(int i = 0;!roleids[0].equals("") && i < roleids.length;i++){
						roleRUser = new RoleRUser(key);
						roleRUser.setCreator("999999999");//系统管理员
						roleRUser.setCreatedate(DateUtils.getCurTime());
						roleRUser.setRoleid(roleids[i].trim());
						roleruserDao.save(roleRUser);
					}
				}
				flag = true;
			} catch (Exception e) {
				throw e;
			}
		}
		return flag;
	}

	@Override
	public List<Map<String, Object>> searchByParam(Map<String, Object> param,String whereSql)
			throws Exception {
		return roleruserDao.queryMapListByKey("UserRole.searchByparam", whereSql, param);
	}
	
	
}

