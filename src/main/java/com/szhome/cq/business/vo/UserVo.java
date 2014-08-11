/**
 * Project Name:dxtx_re
 * File Name:UserVo.java
 * Package Name:com.szhome.cq.business.vo
 * Date:2013-12-24ионГ10:22:08
 * Copyright (c) 2013, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.business.vo;

import java.util.List;

import com.szhome.cq.domain.model.Role;
import com.szhome.security.ext.UserInfo;

/**
 * ClassName:UserVo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-12-24 ионГ10:22:08 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class UserVo {
	private UserInfo userInfo;
	private List<Role> listRole;
	
	
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public List<Role> getListRole() {
		return listRole;
	}
	public void setListRole(List<Role> listRole) {
		this.listRole = listRole;
	}
	
	
}


