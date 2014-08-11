/**
 * Project Name:dxtx_re
 * File Name:IDissentFacade.java
 * Package Name:com.szhome.cq.business
 * Date: 2014-04-10 ����9:17:25
 * Copyright (c) 2013, All Rights Reserved.
 *
*/
package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.springjdbc.annotation.Page;

/**
 * InterfaceName:IDissentFacade <br/>
 * Date:     2014-04-10 ����9:17:25 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IDissentFacade {
	
	 /**
     * 
     * getAllDissentsByParam:���ݲ��������������������Ϣ. <br/>
     * @author Sam
     * @param paramMap
     * @since JDK 1.6
     */
    public Page<Map<String,Object>> getAllDissentsByParam(Map<String,Object> paramMap);
    
    /**
     * getDemurrerBydemurrerId:��������ID�������Ǽ���Ϣ
     * @param wheresql
     * @param valueMap
     * @return
     */
    public List<Map<String, Object>> getDemurrersBydemurrerId(Map<String, Object> sqlMap,
			List<Map<String, Object>> valueLst);
}

