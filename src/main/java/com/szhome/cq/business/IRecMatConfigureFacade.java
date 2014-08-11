/**
 * Project Name:dxtx_re
 * File Name:IRecMatConfigureFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-1-15����11:10:13
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.szhome.cq.domain.model.RecMatConfigure;

/**
 * ClassName:IRecMatConfigureFacade <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-15 ����11:10:13 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */


public interface IRecMatConfigureFacade {
	
	/**
	 * 
	 * getRecMatConMapListByRegId:(ͨ���ǼǱ�Ż�ȡ��ǰҵ��Ĳ�������).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getRecMatConMapListByRegCode(Map<String,Object> paraMap);
	

	/**
	 * 
	 * getRecMatConAsRecMapListByRegId:(ͨ���ǼǱ�Ż�ȡ��ǰҵ��Ĳ������� AS �Ӽ�����).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getRecMatConAsRecMapListByRegCode(Map<String,Object> paraMap);
	
	/**
	 * 
	 * getRecMatConfigureListByRegCode:(���ݵǼǱ�Ż�ȡ�Ӽ���������List).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public List<RecMatConfigure> getRecMatConfigureListByRegCode(Map<String,Object> paraMap);
}


