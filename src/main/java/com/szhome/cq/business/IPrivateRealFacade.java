/**
 * Project Name:dxtx_re
 * File Name:IPrivateRealFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-4-2上午11:50:32
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.springjdbc.annotation.Page;

/**
 * ClassName:IPrivateRealFacade <br/>
 * 个人房产Facade接口
 * Date:     2014-4-21 上午10:50:32 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IPrivateRealFacade {
	
	/**
     * 
     * getAllPrivateRealsByParam:根据参数条件获得所有个人房产信息. <br/>
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


