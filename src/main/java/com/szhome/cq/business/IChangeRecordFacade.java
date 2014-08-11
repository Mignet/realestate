/**
 * Project Name:dxtx_re
 * File Name:IDictFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-1-2下午3:30:02
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
 * Date:     2014-1-2 下午3:30:02 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IChangeRecordFacade {
	/**
	 * 
	 * applyEdit:(保存编辑数据). 
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map applyEdit(Map paraMap);
	
	
	/**
	 * 
	 * getChangeRecordMapListByProcId:(获取变更信息Map list).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getChangeRecordMapListByProcId(String proc_id);

	/**
	 * 
	 * getChangeRecordListByCodeAndBusId:(按当前业务Id 和变更项code获取变更数据List).
	 *
	 * @author Joyon
	 * @param code
	 * @param bus_id
	 * @since JDK 1.6
	 */
	public List<ChangeRecord> getChangeRecordListByCodeAndBusId(String code, String bus_id);
	
	/**
	 * 
	 * saveChangeRecord:(保存变更数据).
	 * @author Joyon
	 * @param changeRecocrd
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void saveChangeRecord(ChangeRecord changeRecocrd) throws BusinessException;
	
	/**
	 * 
	 * updateChangeRecord:(更新变更数据).
	 * @author Joyon
	 * @param changeRecocrd
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void updateChangeRecord(ChangeRecord changeRecocrd) throws BusinessException;
	
	/**
	 * 
	 * saveBKOwnerShipToBusOwnerShip:(保存前登记簿吕有交往的数据到登记信息中).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @since JDK 1.6
	 */
	public void saveBKOwnerShipToBusOwnerShip(String proc_id);

	/**
	 * 
	 * saveBKUserightShipToBusUserightShip:(保存前登记簿吕有交往的数据到登记信息中).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @since JDK 1.6
	 */
	public void saveBKUserightShipToBusUserightShip(String proc_id);
	
	/**
	 * 
	 * getRegInfoMapByProcId:(获取登记信息). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getRegInfoMapByProcId(String proc_id) throws BusinessException;
}


