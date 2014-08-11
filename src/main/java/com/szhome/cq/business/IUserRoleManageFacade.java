package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.szhome.cq.domain.model.Role;

/**
 * 用户角色分配管理
 * @author Mignet
 *
 */
public interface IUserRoleManageFacade {

	/**
	 * 查询用户的角色列表由用户Id
	 * queryUserRoleByUserId <br/>
	 *
	 * @author dxtx
	 * @param userID
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> queryUserRoleByUserId(String userID);

	/**
	 * 查询所有用户的角色列表
	 * queryAllUserRoles <br/>
	 * @author dxtx
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> queryAllUserRoles();
	
	
	/**
	 * 更新用户角色
	 * updateUserRole
	 * @author dxtx
	 * @return
	 * @since JDK 1.6
	 */
     public Boolean updateUserRole(String key,Map<String,Object> urmap) throws Exception; 
     
     /**
      * 更新用户角色
      * updateUserRole
      * @author dxtx
      * @return
      * @since JDK 1.6
      */
     public  List<Map<String,Object>> searchByParam(Map<String,Object> param,String wheresql) throws Exception; 
}

