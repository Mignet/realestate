/**
 * Project Name:dxtx_re
 * File Name:IAttachFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-04-09下午6:17:25
 * Copyright (c) 2013, All Rights Reserved.
 *
*/
package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.springjdbc.annotation.Page;

/**
 * InterfaceName:IAttachFacade <br/>
 * Date:     2014-04-09下午6:17:25 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IAttachFacade {
	
	  /**
     * 
     * getAllAttachsByParam:根据参数条件获得所有查封信息. <br/>
     * @author Sam
     * @param paramMap
     * @since JDK 1.6
     */
    public Page<Map<String,Object>> getAllAttachsByParam(Map<String,Object> paramMap);
    
    /**
     * getAttachsBydistressId:获得查封登记信息由查封Id
     * @param wheresql
     * @param valueMap
     * @return
     */
    public List<Map<String, Object>> getAttachsBydistressId(Map<String,Object> sqlMap,
			List<Map<String, Object>> valueLst);
}

