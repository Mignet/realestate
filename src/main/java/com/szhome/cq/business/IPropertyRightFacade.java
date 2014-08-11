/**
 * Project Name:dxtx_re
 * File Name:IPropertyRightFacade.java
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
 * InterfaceName:IPropertyRightFacade <br/>
 * Date:     2014-04-18上午11:37:25 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IPropertyRightFacade {
	
	  /**
     * 
     * getAllPropRightsByParam:根据参数条件获得所有产权信息. <br/>
     * @author Sam
     * @param paramMap
     * @since JDK 1.6
     */
    public Page<Map<String,Object>> getAllPropRightsByParam(Map<String,Object> paramMap);
    
    /**
     * getPropRightsByrightId:获得产权登记信息由权利Id
     * @param wheresql
     * @param valueMap
     * @return
     */
    public List<Map<String, Object>> getPropRightsByrightId(Map<String,Object> sqlMap,
			Map<String, Object> valueMap);
}

