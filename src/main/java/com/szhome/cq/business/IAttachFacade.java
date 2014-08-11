/**
 * Project Name:dxtx_re
 * File Name:IAttachFacade.java
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
 * InterfaceName:IAttachFacade <br/>
 * Date:     2014-04-09����6:17:25 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IAttachFacade {
	
	  /**
     * 
     * getAllAttachsByParam:���ݲ�������������в����Ϣ. <br/>
     * @author Sam
     * @param paramMap
     * @since JDK 1.6
     */
    public Page<Map<String,Object>> getAllAttachsByParam(Map<String,Object> paramMap);
    
    /**
     * getAttachsBydistressId:��ò��Ǽ���Ϣ�ɲ��Id
     * @param wheresql
     * @param valueMap
     * @return
     */
    public List<Map<String, Object>> getAttachsBydistressId(Map<String,Object> sqlMap,
			List<Map<String, Object>> valueLst);
}

