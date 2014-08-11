/**
 * Project Name:dxtx_re
 * File Name:IPreadviceFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-04-10 ����9:17:25
 * Copyright (c) 2013, All Rights Reserved.
 *
*/
package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.springjdbc.annotation.Page;

/**
 * InterfaceName:IPreadviceFacade <br/>
 * Date:     2014-04-10 ����9:17:25 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IPreadviceFacade {
	
	 /**
     * 
     * getAllPreadviceByParam:���ݲ��������������Ԥ����Ϣ. <br/>
     * @author Sam
     * @param paramMap
     * @since JDK 1.6
     */
    public Page<Map<String,Object>> getAllPreadvicesByParam(Map<String,Object> paramMap);
    
    /**
     * getPreadvicesByPreadviceId:����Ԥ��ID���Ԥ��Ǽ���Ϣ
     * @param sqlMap
     * @param valueLst
     * @return
     */
    public List<Map<String, Object>> getPreadvicesByPreadviceId(Map<String, Object> sqlMap,
			List<Map<String, Object>> valueLst);
}

