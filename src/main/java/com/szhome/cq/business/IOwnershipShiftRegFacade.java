/**
 * Project Name:dxtx_re
 * File Name:IOwnershipShiftRegFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-1-22����3:50:05
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.Map;

import com.szhome.cq.domain.model.BusOwnership;
import com.szhome.cq.domain.model.BusinessMain;
import com.szhome.cq.domain.model.Bususeright;
import com.szhome.cq.domain.model.Certificate;

/**
 * ClassName:IOwnershipShiftRegFacade <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-22 ����3:50:05 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IOwnershipShiftRegFacade {
	/**
	 * 
	 * getRegInfoMapByProcId:(ͨ������ʵ��ID ��ȡ�Ǽ���Ϣ).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getRegInfoMapByProcId(String proc_id);
	
	/**
	 * 
	 * saveRegInfo:(���淿������Ȩ�Ǽ���Ϣ). 
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> saveRegInfo(Map<String,Object> paraMap);
	
	
	/**
	 * 
	 * saveLandShipRegInfo:(������һ�仰�����������������)
	 *
	 * @author Joyon
	 * @param businessMain
	 * @param bususeright
	 * @param certificate
	 * @since JDK 1.6
	 */
	public void saveLandShipRegInfo(BusinessMain businessMain,Bususeright bususeright,Certificate certificate) throws BusinessException;
	
	/**
	 * 
	 * getLandShiftRegInfoMapByProcId:(��ȡʹ��Ȩ�Ǽ���Ϣ). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getLandShiftRegInfoMapByProcId(String proc_id) throws BusinessException;
		
}


