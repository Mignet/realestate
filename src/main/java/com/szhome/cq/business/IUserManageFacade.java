package com.szhome.cq.business;

import java.util.List;

import org.json.JSONException;

import com.springjdbc.annotation.Page;
import com.szhome.cq.domain.model.User;


/**
 * 用户管理
 * @author Mignet
 *
 */
public interface IUserManageFacade {

	Page<User> getAllUsers(int pageNo, int pageSize);
	
	List<User> queryAllUsers();

	void saveUsers(String datas) throws JSONException;
	
}

