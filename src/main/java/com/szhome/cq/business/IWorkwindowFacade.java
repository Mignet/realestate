/**
 * Project Name:dxtx_re
 * File Name:IFormFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2013-12-25下午2:59:48
 * Copyright (c) 2013, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.szhome.cq.domain.model.ConOffice;
import com.szhome.security.ext.UserInfo;

/**
 * ClassName:IFormFacade <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-12-25 下午2:59:48 <br/>
 * @author   xuzz
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IWorkwindowFacade  {
	
	public List<ConOffice> getOfficeByNodeid(Map map);
	public Map getFormTreeurl(String formid);
	/**
	 * 初始化办公页面
	 * @return
	 * @throws Exception
	 */
	public Map getFormAndsubmitbtn(UserInfo userInfo,Long procdefId,String activdefId);
}


