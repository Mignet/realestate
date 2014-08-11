/**
 * Project Name:dxtx_re
 * File Name:IDissentFacade.java
 * Package Name:com.szhome.cq.business
 * Date: 2014-04-10 上午9:17:25
 * Copyright (c) 2013, All Rights Reserved.
 *
*/
package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.springjdbc.annotation.Page;

/**
 * InterfaceName:IDissentFacade <br/>
 * Date:     2014-04-10 上午9:17:25 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IDissentFacade {
	
	 /**
     * 
     * getAllDissentsByParam:根据参数条件获得所有异议信息. <br/>
     * @author Sam
     * @param paramMap
     * @since JDK 1.6
     */
    public Page<Map<String,Object>> getAllDissentsByParam(Map<String,Object> paramMap);
    
    /**
     * getDemurrerBydemurrerId:根据异议ID获得异议登记信息
     * @param wheresql
     * @param valueMap
     * @return
     */
    public List<Map<String, Object>> getDemurrersBydemurrerId(Map<String, Object> sqlMap,
			List<Map<String, Object>> valueLst);
}

