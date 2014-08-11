/**
 * Project Name:dxtx_re
 * File Name:IEasementFacade.java
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
 * InterfaceName:IEasementFacade <br/>
 * Date:     2014-04-10 上午9:17:25 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IEasementFacade {
	
	  /**
     * 
     * getAllEasementsByParam:根据参数条件获得所有地役权信息. <br/>
     * @author Sam
     * @param paramMap
     * @since JDK 1.6
     */
    public Page<Map<String,Object>> getAllEasementsByParam(Map<String,Object> paramMap);
    
    /**
     * getEasementsByEasementId:根据地役权ID获得地役权登记信息
     * @param sqlMap
     * @param valueLst
     * @return
     */
    public List<Map<String, Object>> getEasementsByEasementId(Map<String, Object> sqlMap,
			List<Map<String, Object>> valueLst);
}

