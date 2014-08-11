/**
 * Project Name:dxtx_re
 * File Name:IDictFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-1-2����3:30:02
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.plan.commons.Row;
import com.plan.web.JsonResult;
import com.szhome.cq.domain.model.ChangeRecord;

/**
 * ClassName:IDictFacade <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-2 ����3:30:02 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IChangeRecordFacade {
	/**
	 * 
	 * applyEdit:(����༭����). 
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map applyEdit(Map paraMap);
	
	
	/**
	 * 
	 * getChangeRecordMapListByProcId:(��ȡ�����ϢMap list).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getChangeRecordMapListByProcId(String proc_id);

	/**
	 * 
	 * getChangeRecordListByCodeAndBusId:(����ǰҵ��Id �ͱ����code��ȡ�������List).
	 *
	 * @author Joyon
	 * @param code
	 * @param bus_id
	 * @since JDK 1.6
	 */
	public List<ChangeRecord> getChangeRecordListByCodeAndBusId(String code, String bus_id);
	
	/**
	 * 
	 * saveChangeRecord:(����������).
	 * @author Joyon
	 * @param changeRecocrd
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void saveChangeRecord(ChangeRecord changeRecocrd) throws BusinessException;
	
	/**
	 * 
	 * updateChangeRecord:(���±������).
	 * @author Joyon
	 * @param changeRecocrd
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void updateChangeRecord(ChangeRecord changeRecocrd) throws BusinessException;
	
	/**
	 * 
	 * saveBKOwnerShipToBusOwnerShip:(����ǰ�Ǽǲ����н��������ݵ��Ǽ���Ϣ��).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @since JDK 1.6
	 */
	public void saveBKOwnerShipToBusOwnerShip(String proc_id);

	/**
	 * 
	 * saveBKUserightShipToBusUserightShip:(����ǰ�Ǽǲ����н��������ݵ��Ǽ���Ϣ��).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @since JDK 1.6
	 */
	public void saveBKUserightShipToBusUserightShip(String proc_id);
	
	/**
	 * 
	 * getRegInfoMapByProcId:(��ȡ�Ǽ���Ϣ). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getRegInfoMapByProcId(String proc_id) throws BusinessException;
}


