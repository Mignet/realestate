/**
 * Project Name:dxtx_re
 * File Name:IPresaleFacade.java
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
 * ClassName:IPresaleFacade <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-4-2 上午11:50:32 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IPresaleFacade {
	/**
	 * 
	 * getPreSaleInfo:(获取预售备案信息).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getPreSaleInfo(String proc_id);
	
	/**
	 * 
	 * getPreSaler:(获取预售方信息).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getPreSaler(String proc_id);
	
	/**
	 * 
	 * setPreSaleState:(修改备案状态  为己备案).
	 *
	 * @author Joyon
	 * @since JDK 1.6
	 */
	public void setPreSaleState(String proc_id,String state) throws BusinessException;
	
	/**
	 * 
	 * isRecorded:(判断预售备案状态是否已经是备案状态 ). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public boolean isRecorded(String proc_id) throws BusinessException;
	
	/**
	 * getPresalesBydemurrerId:获得预售备案信息根据预售备案Id
	 * @param wheresql
	 * @param valueMap
	 * @return
	 */
	public Page<Map<String, Object>> getAllPresalesByParam(Map<String, Object> paramMap);
	/**
	 * getPresalesBydemurrerId:获得预售备案信息根据预售备案Id
	 * @param wheresql
	 * @param valueMap
	 * @return
	 */
	public List<Map<String, Object>> getPresalesBypreRecId(Map<String,Object> sqlMap,
				Map<String, Object> valueMap);
	
	/**
	 * 
	 * 获取预购方信息
	 *
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return 预购方信息
	 * @since JDK 1.6
	 */
	public List getPreBuyerInfo(String proc_id);
	
	/**
	 * 
	 * 预售备案受理前审核时需调用 保存一份市场接口中数据到本地
	 *
	 * @author Joyon
	 * @param proc_id
	 * @since JDK 1.6
	 */
	public void savePresaleTodb(String proc_id) throws BusinessException;
}


