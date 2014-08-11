/**
 * Project Name:dxtx_re
 * File Name:LoginFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2013-12-24上午9:50:33
 * Copyright (c) 2013,dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.szhome.cq.business.ILoginFacade;
import com.szhome.cq.business.vo.UserVo;
import com.szhome.cq.domain.model.Role;
import com.szhome.cq.utils.MD5Util;
import com.szhome.security.ext.UserInfo;

/**
 * 登录Facade
 * Date:     2013-12-24 上午9:50:33 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class LoginFacade implements ILoginFacade {
	@Autowired
	private Role roleDao; 
	
	/**
	 * 
	 *  登录方法
	 * @see com.szhome.cq.business.ILoginFacade#login(java.lang.String, java.lang.String)
	 */
	public UserVo login(String userAccount, String password) {
		if(userAccount==null || password==null){
			return null;
		}
		 String md5Pwd=MD5Util.string2MD5(password);
		 
		 //System.out.println("_________md5 pwd:"+md5Pwd);
		 Map paraMap=new HashMap();
		 paraMap.put("account", userAccount);
		 
		 
		 //paraMap.put("password", md5Pwd);
		 
		 paraMap.put("password", md5Pwd);
		 //查询数据库里是否有用户名和密码
		 Map map=roleDao.queryMapByKey("Login.login", paraMap);
		 
		 //[String sql = "select password  from sec_users where account='" + userAccount + "'";]
		 if(map==null){
			 return null;
		 }
		 
		 
		UserInfo userInfo=new UserInfo();
		
		userInfo.setUserID(map.get("USER_ID").toString());
		userInfo.setUserAccount(userAccount);
		userInfo.setUserName(map.get("USER_NAME").toString());
		UserVo userVo=new UserVo();
		userVo.setUserInfo(userInfo);
		return userVo;
	}

	

}


