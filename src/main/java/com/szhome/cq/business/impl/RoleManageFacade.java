package com.szhome.cq.business.impl;

import groovy.lang.Sequence;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.szhome.cq.business.IRoleManageFacade;
import com.szhome.cq.domain.model.Role;
import com.szhome.cq.utils.SequenceUtil;

/**
 * Ω«…´π‹¿ÌService
 * [‘§¡Ù]
 * @author Mignet
 */
@Component
@Transactional
@Scope("prototype")
public class RoleManageFacade implements IRoleManageFacade {

	@Autowired
	private Role roleDao;
	
	@Override
	public List<Role> queryRolesByUserId(String userID) {
		return null;
	}

	@Override
	public List<Role> queryAllRoles() {
		return roleDao.getAll();
	}
	
	@Override
	public List<Map<String, Object>> getAllCheckRolesByUserId(Map<String,String> param) {
		return roleDao.queryMapListByKey("UserRole.checkUserRoles", param);
	}

	@Override
	public boolean updateRole(Role role) throws Exception {
		boolean flag = false;
		try {
			roleDao.update(role);
			flag = true;
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	@Override
	public boolean addRole(Role role) throws Exception {
		boolean flag = false;
		try {
			String roleid = SequenceUtil.getGlobalSeqID();
			role.setRoleid(roleid);
			roleDao.save(role);
			flag = true;
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	@Override
	public boolean delRole(Role role) throws Exception {
		boolean flag = false;
		try {
			roleDao.delete(role);
			flag = true;
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}
}

