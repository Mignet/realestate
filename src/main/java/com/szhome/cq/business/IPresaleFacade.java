/**
 * Project Name:dxtx_re
 * File Name:IPresaleFacade.java
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
 * ClassName:IPresaleFacade <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-4-2 ����11:50:32 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IPresaleFacade {
	/**
	 * 
	 * getPreSaleInfo:(��ȡԤ�۱�����Ϣ).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getPreSaleInfo(String proc_id);
	
	/**
	 * 
	 * getPreSaler:(��ȡԤ�۷���Ϣ).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getPreSaler(String proc_id);
	
	/**
	 * 
	 * setPreSaleState:(�޸ı���״̬  Ϊ������).
	 *
	 * @author Joyon
	 * @since JDK 1.6
	 */
	public void setPreSaleState(String proc_id,String state) throws BusinessException;
	
	/**
	 * 
	 * isRecorded:(�ж�Ԥ�۱���״̬�Ƿ��Ѿ��Ǳ���״̬ ). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public boolean isRecorded(String proc_id) throws BusinessException;
	
	/**
	 * getPresalesBydemurrerId:���Ԥ�۱�����Ϣ����Ԥ�۱���Id
	 * @param wheresql
	 * @param valueMap
	 * @return
	 */
	public Page<Map<String, Object>> getAllPresalesByParam(Map<String, Object> paramMap);
	/**
	 * getPresalesBydemurrerId:���Ԥ�۱�����Ϣ����Ԥ�۱���Id
	 * @param wheresql
	 * @param valueMap
	 * @return
	 */
	public List<Map<String, Object>> getPresalesBypreRecId(Map<String,Object> sqlMap,
				Map<String, Object> valueMap);
	
	/**
	 * 
	 * ��ȡԤ������Ϣ
	 *
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return Ԥ������Ϣ
	 * @since JDK 1.6
	 */
	public List getPreBuyerInfo(String proc_id);
	
	/**
	 * 
	 * Ԥ�۱�������ǰ���ʱ����� ����һ���г��ӿ������ݵ�����
	 *
	 * @author Joyon
	 * @param proc_id
	 * @since JDK 1.6
	 */
	public void savePresaleTodb(String proc_id) throws BusinessException;
}


