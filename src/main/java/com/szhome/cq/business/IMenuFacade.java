package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.springjdbc.annotation.Page;
import com.szhome.cq.domain.model.Role;
import com.szhome.cq.domain.model.TreeMenu;

/**
 * �˵�����
 * @author Mignet
 *
 */
public interface IMenuFacade {

	public List<TreeMenu> listTrees();
	

	public Page<TreeMenu> listTrees(int start, int number);
	
	
	
	public List<Map<String, Object>> getTreeList(String key,String parent_id);

	/**
	 * queryMenusByUserID:��ѯ�û������˵�
	 * @author dxtx
	 * @param userid
	 * @return
	 * @since JDK 1.6
	 */
	public List<TreeMenu> queryMenusByUserID(String userid);


	/**
	 * queryMenusByRoleID:(��ɫ�˵�). <br/>
	 *
	 * @author dxtx
	 * @param roleid
	 * @return
	 * @since JDK 1.6
	 */
	public List<TreeMenu> queryMenusByRoleID(String roleid);

	/**
	 * saveRoleMenus:(�����ɫ�˵���ϵ). <br/>
	 *
	 * @author dxtx
	 * @param roleid
	 * @param menuids
	 * @return
	 * @since JDK 1.6
	 */
	public void saveRoleMenus(String roleid, String menuids);
	/**
	 * delRoleMenus:(ɾ����ɫ�˵���ϵ). <br/>
	 *
	 * @author dxtx
	 * @param roleid
	 * @return false|true
	 * @since JDK 1.6
	 */
	public boolean delRoleMenus(String roleid) throws Exception;

}

