/**
 * Project Name:dxtx_re
 * File Name:IPrivateRealFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-4-2����11:50:32
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.springjdbc.annotation.Page;

/**
 * ClassName:IPrivateRealFacade <br/>
 * ���˷���Facade�ӿ�
 * Date:     2014-4-21 ����10:50:32 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IPrivateRealFacade {
	
	/**
     * 
     * getAllPrivateRealsByParam:���ݲ�������������и��˷�����Ϣ. <br/>
     * @author Sam
     * @param paramMap
     * @since JDK 1.6
     */
    public Page<Map<String,Object>> getAllPrivateRealsByParam(Map<String,Object> paramMap);
    
	/**
	 * @param wheresql
	 * @param valueMap
	 * @return
	 */
	public Map<String, Object> getPrivateRealsById(Map<String,Object> sqlMap,
				Map<String, Object> valueMap);
}


