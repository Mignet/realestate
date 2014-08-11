package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.springjdbc.annotation.Page;
import com.szhome.cq.domain.model.Role;
import com.szhome.cq.domain.model.TreeMenu;

/**
 * 菜单管理
 * @author Mignet
 *
 */
public interface IMenuFacade {

	public List<TreeMenu> listTrees();
	

	public Page<TreeMenu> listTrees(int start, int number);
	
	
	
	public List<Map<String, Object>> getTreeList(String key,String parent_id);

	/**
	 * queryMenusByUserID:查询用户关联菜单
	 * @author dxtx
	 * @param userid
	 * @return
	 * @since JDK 1.6
	 */
	public List<TreeMenu> queryMenusByUserID(String userid);


	/**
	 * queryMenusByRoleID:(角色菜单). <br/>
	 *
	 * @author dxtx
	 * @param roleid
	 * @return
	 * @since JDK 1.6
	 */
	public List<TreeMenu> queryMenusByRoleID(String roleid);

	/**
	 * saveRoleMenus:(保存角色菜单关系). <br/>
	 *
	 * @author dxtx
	 * @param roleid
	 * @param menuids
	 * @return
	 * @since JDK 1.6
	 */
	public void saveRoleMenus(String roleid, String menuids);
	/**
	 * delRoleMenus:(删除角色菜单关系). <br/>
	 *
	 * @author dxtx
	 * @param roleid
	 * @return false|true
	 * @since JDK 1.6
	 */
	public boolean delRoleMenus(String roleid) throws Exception;

}

