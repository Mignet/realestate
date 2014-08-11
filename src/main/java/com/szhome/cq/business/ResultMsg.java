/**
 * Project Name:dxtx_re
 * File Name:ResultMsg.java
 * Package Name:com.szhome.cq.business
 * Date:2013-12-24下午2:55:02
 * Copyright (c) 2013, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.szhome.cq.business;
/**
 * ClassName:ResultMsg <br/>
 * Function: 业务操作结果信息. <br/>
 * Reason:	 通用. <br/>
 * Date:     2013-12-24 下午2:55:02 <br/>
 * @author   PANDA
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ResultMsg {
	
	public static final String CODE_SUCCESS = "00";
	public static final String CODE_FAIL = "01";
	
	private String returnCode;
	private String returnMsg;
	
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	
}


