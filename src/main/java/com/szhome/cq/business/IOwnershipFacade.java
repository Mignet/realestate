/**
 * Project Name:dxtx_re
 * File Name:IOwnershipFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-04-09����6:17:25
 * Copyright (c) 2013, All Rights Reserved.
 *
*/
package com.szhome.cq.business;

import java.util.Map;

import com.springjdbc.annotation.Page;

/**
 * InterfaceName:IOwnershipFacade <br/>
 * Date:     2014-04-09����6:17:25 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IOwnershipFacade {
	
	  /**
     * 
     * getAllOwnershipByParam:���ݲ�������������в����Ϣ. <br/>
     * @author Sam
     * @param paramMap
     * @since JDK 1.6
     */
    public Page<Map<String,Object>> getAllOwnershipByParam(Map<String,Object> paramMap);
}

