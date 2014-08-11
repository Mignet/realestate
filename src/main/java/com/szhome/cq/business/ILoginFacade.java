/**
 * Project Name:dxtx_re
 * File Name:ILoginFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2013-12-24ионГ9:44:25
 * Copyright (c) 2013, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import com.szhome.cq.business.vo.UserVo;
import com.szhome.security.ext.UserInfo;

/**
 * ClassName:ILoginFacade <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-12-24 ионГ9:44:25 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface ILoginFacade {
	
	public UserVo login(String userAccount,String password);
}


