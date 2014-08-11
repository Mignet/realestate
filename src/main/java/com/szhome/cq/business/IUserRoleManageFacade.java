package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.szhome.cq.domain.model.Role;

/**
 * �û���ɫ�������
 * @author Mignet
 *
 */
public interface IUserRoleManageFacade {

	/**
	 * ��ѯ�û��Ľ�ɫ�б����û�Id
	 * queryUserRoleByUserId <br/>
	 *
	 * @author dxtx
	 * @param userID
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> queryUserRoleByUserId(String userID);

	/**
	 * ��ѯ�����û��Ľ�ɫ�б�
	 * queryAllUserRoles <br/>
	 * @author dxtx
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> queryAllUserRoles();
	
	
	/**
	 * �����û���ɫ
	 * updateUserRole
	 * @author dxtx
	 * @return
	 * @since JDK 1.6
	 */
     public Boolean updateUserRole(String key,Map<String,Object> urmap) throws Exception; 
     
     /**
      * �����û���ɫ
      * updateUserRole
      * @author dxtx
      * @return
      * @since JDK 1.6
      */
     public  List<Map<String,Object>> searchByParam(Map<String,Object> param,String wheresql) throws Exception; 
}

