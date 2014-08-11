package com.szhome.cq.business.impl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.springjdbc.annotation.Page;
import com.szhome.cq.business.IUserManageFacade;
import com.szhome.cq.domain.model.User;

/**
 * 用户管理
 * ClassName: UserRoleManageFacade <br/>
 * date: 2014-3-28 上午11:29:15 <br/>
 *
 * @author dxtx
 * @version 
 * @since JDK 1.6
 */
@Component
@Transactional
@Scope("prototype")
public class UserManageFacade implements IUserManageFacade {

	@Autowired
	private User userDao;
	@Override
	public List<User> queryAllUsers() {
		return userDao.queryListByKey("User.getAllUsers", null);
	}
	@Override
	public Page<User> getAllUsers(int pageNo, int pageSize){
		return userDao.queryDomainPageBykeyForOracle("User.getAllUsers", null, pageNo, pageSize);
	}
	
	@Override
	public void saveUsers(String datas) throws JSONException {
			JSONArray jsonArray = new JSONArray(datas); 
			 for(int i=0;i<jsonArray.length(); i++){
			   JSONObject user = jsonArray.getJSONObject(i);
			   User u = userDao.get(new User(user.getString("user_id")));
			   u.setWeight(user.getDouble("weight"));
			   u.setUser_name(user.getString("user_name"));
			   u.setAccount(user.getString("account"));
			   u.setGender(user.getString("gender"));
			   u.setStatus(user.getString("status"));
			   userDao.update(u);
			 }
	}
	
	
}

