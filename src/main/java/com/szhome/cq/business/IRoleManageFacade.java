package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.szhome.cq.domain.model.Role;

/**
 * 角色管理
 * @author Mignet
 *
 */
public interface IRoleManageFacade {

	/**
	 * 查询用户的角色列表
	 * queryRolesByUserId <br/>
	 *
	 * @author dxtx
	 * @param userID
	 * @return
	 * @since JDK 1.6
	 */
	public List<Role> queryRolesByUserId(String userID);

	/**
	 * 查询角色列表
	 * queryAllRoles <br/>
	 * @author dxtx
	 * @return
	 * @since JDK 1.6
	 */
	public List<Role> queryAllRoles();
	
	/**
	 * 得到所有选择的角色关联用户ID
	 * getAllCheckRolesByUserId  <br/>
	 * @author dxtx
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getAllCheckRolesByUserId(Map<String,String> param);
	
	/**
	 * 更新角色
	 * updateRole  <br/>
	 * @author dxtx
	 * @return role
	 * @since JDK 1.6
	 */
	public boolean updateRole(Role role) throws Exception;
	/**
	 * 增加角色
	 * updateRole  <br/>
	 * @author dxtx
	 * @return role
	 * @since JDK 1.6
	 */
	public boolean addRole(Role role) throws Exception;
	/**
	 * 删除角色
	 * updateRole  <br/>
	 * @author dxtx
	 * @return role
	 * @since JDK 1.6
	 */
	public boolean delRole(Role role) throws Exception;

}

