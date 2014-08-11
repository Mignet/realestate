package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.szhome.cq.domain.model.Role;

/**
 * ��ɫ����
 * @author Mignet
 *
 */
public interface IRoleManageFacade {

	/**
	 * ��ѯ�û��Ľ�ɫ�б�
	 * queryRolesByUserId <br/>
	 *
	 * @author dxtx
	 * @param userID
	 * @return
	 * @since JDK 1.6
	 */
	public List<Role> queryRolesByUserId(String userID);

	/**
	 * ��ѯ��ɫ�б�
	 * queryAllRoles <br/>
	 * @author dxtx
	 * @return
	 * @since JDK 1.6
	 */
	public List<Role> queryAllRoles();
	
	/**
	 * �õ�����ѡ��Ľ�ɫ�����û�ID
	 * getAllCheckRolesByUserId  <br/>
	 * @author dxtx
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getAllCheckRolesByUserId(Map<String,String> param);
	
	/**
	 * ���½�ɫ
	 * updateRole  <br/>
	 * @author dxtx
	 * @return role
	 * @since JDK 1.6
	 */
	public boolean updateRole(Role role) throws Exception;
	/**
	 * ���ӽ�ɫ
	 * updateRole  <br/>
	 * @author dxtx
	 * @return role
	 * @since JDK 1.6
	 */
	public boolean addRole(Role role) throws Exception;
	/**
	 * ɾ����ɫ
	 * updateRole  <br/>
	 * @author dxtx
	 * @return role
	 * @since JDK 1.6
	 */
	public boolean delRole(Role role) throws Exception;

}

