/**
 * Project Name:dxtx_re
 * File Name:IPropertyRightFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-04-09����6:17:25
 * Copyright (c) 2013, All Rights Reserved.
 *
*/
package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.springjdbc.annotation.Page;

/**
 * InterfaceName:IPropertyRightFacade <br/>
 * Date:     2014-04-18����11:37:25 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IPropertyRightFacade {
	
	  /**
     * 
     * getAllPropRightsByParam:���ݲ�������������в�Ȩ��Ϣ. <br/>
     * @author Sam
     * @param paramMap
     * @since JDK 1.6
     */
    public Page<Map<String,Object>> getAllPropRightsByParam(Map<String,Object> paramMap);
    
    /**
     * getPropRightsByrightId:��ò�Ȩ�Ǽ���Ϣ��Ȩ��Id
     * @param wheresql
     * @param valueMap
     * @return
     */
    public List<Map<String, Object>> getPropRightsByrightId(Map<String,Object> sqlMap,
			Map<String, Object> valueMap);
}

