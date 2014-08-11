/**
 * Project Name:dxtx_re
 * File Name:IRegisterFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-1-8上午11:04:55
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.szhome.cq.domain.model.Reg_Useright;
import com.szhome.cq.domain.model.Reg_ownership;

/**
 * ClassName:IRegisterFacade <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-8 上午11:04:55 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IRegisterFacade {
	/**
	 * 
	 * registerPreview:(登记簿预览). 
	 *
	 * @author Joyon
	 * @param paramMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String, Object> registerPreview(Map<String, Object> paramMap);
	
	/**
	 * 
	 * getOwnershipInfo:(获取所有权信息).
	 * @author Joyon
	 * @param paraMap reg_id 登记编号
	 * @return
	 * @since JDK 1.6
	 */
	public  Map<String, Object> getOwnershipInfo( Map<String, Object> paraMap);
	
	
	
	/**
	 * 
	 * getregisterInfo:(登记簿预览，通用.). <br/>
	 * @author xuzz
	 * @param useMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getregisterInfo(Map<String ,Object> map);
	
	
	/**
	 * 
	 * getuserInfo:(登记簿预览使用权部分.). <br/>
	 * @author xuzz
	 * @param useMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getuserInfo(Map<String ,Object> useMap);
	
	
	/**
	 * 
	 * registerSave:(登记簿信息保存). 
	 *
	 * @author Joyon
	 * @param paraMap reg_id  登记编号
	 * @return
	 * @since JDK 1.6
	 */
	public  Map<String, Object> registerSave( Map<String, Object> paraMap);

	/**
	 * 
	 * checkBus:(检查业务数据是否可以受理). <br/>
	 * @author xuzz
	 * @since JDK 1.6  
	 */
	public Map checkBusData(String reg_code,String regtype,String bustype);
	
	
	/**
	 * 
	 * getProcid:(查询业务表的流程定义ID). <br/>
	 * @author xuzz
	 * @param procid
	 * @return
	 * @since JDK 1.6
	 */
	public String getProcid(String procid);

	
	/**
	 * 
	 * getBusTypeParentNameByRegId:(通过登记编号获取  业务类型的父类型). 
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public String getBusTypeParentNameByRegId(Map<String, Object> paraMap);
	
	
	/**
	 * 
	 * getNaturalInfo:(获取自然信息 )
	 *
	 * @author Joyon
	 * @param paraMap.put--	reg_code:登记编号
	 * @return
	 * @since JDK 1.6
	 */
	public  Map<String, Object> getNaturalInfo( Map<String, Object> paraMap);
	
	/**
	 * 
	 * getHolderInfo:(获取权利人信息). 
	 *
	 * @author Joyon
	 * @param paraMap reg_code 登记编号
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String, Object>> getHolderInfoByRegId(Map paraMap);
	
	/**
	 * 
	 * getRegTypeByRegCode:(通过登记编号获取登记类型名). 
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public String getRegTypeByRegCode(Map<String, Object> paraMap);
	
	/**
	 * 根据流程实例ID 获取当前 业务前一次业务的权利人信息
	 */
	public List<Map<String,Object>> getHistoryHolderMapListByProcId(String proc_id);
	
	/**
	 * 
	 * saveRegister:(登记簿保存). <br/>
	 * @author xuzz
	 * @param paraMap
	 * @return
	 * @since JDK 1.6  getuserInfoByRegId
	 */
	public Map<String,Object> saveRegister(Map<String,Object> paraMap);
	
	/**
	 * 登簿成功后设置产权状态为有效
	 * @param paraMap  （reg_code,reg_unit_code）
	 */
	public void updateRegUnitState(Map paraMap);
	
	/**
	 * 
	 * getMortMess:根据bus_id获取抵押权过程信息. <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getMortMess(Map m);
	
	/**
	 * 
	 * getBusTypeParentIdByRegId:(通过登记编号获取  业务类型的父类型). 
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public String getBusTypeParentIdByRegId(Map<String, Object> paraMap);
	
	/**
	 * 
	 * getLastRegOnwershipMapByProcId:(通过流程实例ID获取上一次业务的登记簿所有权信息Map).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getLastRegOnwershipMapByProcId(String proc_id) throws BusinessException;
	
	/**
	 * 
	 * saveHistoryHolderToApp:(把历史权利人保存到申请表中). 
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> saveHistoryHolderToApp(String proc_id) throws BusinessException;
	

	/**
	 * 
	 * getEffectiveReg_OwnershipByProcId:(通过流程实例ID获取有效的登记簿所有权部分信息)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public Reg_ownership getEffectiveReg_OwnershipByProcId(String proc_id) throws BusinessException;
	
	/**
	 * 
	 * getRegUnitRelMapByRegId:(通过当前登记编号 登记单元关联表Map).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getRegUnitRelMapByRegId(Map<String,Object> paraMap);

	/**
	 * 
	 * getEffectiveReg_userightMapByProcId:(通过流程实例ID获取有效的登记簿使用权部分信息)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getEffectiveReg_userightMapByProcId(String proc_id) throws BusinessException;
	
	/**
	 * 
	 * getEffectiveReg_userightByRegUnitCode:(获取当前登记单元编号下生效的使用权数据)
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return Reg_Useright
	 * @since JDK 1.6
	 */
	public Reg_Useright getEffectiveReg_userightByRegUnitCode(String reg_unit_code);
	
	/**
	 * 
	 * getEffictiveHolderMapListByRegUnitCode:(获取当前登记单元生效的权利人).
	 *
	 * @author Joyon
	 * @param reg_unit_code  hol_rel_code(权利人关系类型code   为""时，查询所有权利人   )
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getEffictiveHolderMapListByRegUnitCode(String reg_unit_code,String hol_rel_code);
	
	/**
	 * 
	 * getEffectiveReg_OwnershipMapByProcId:(通过流程实例ID获取有效的登记簿所有权部分信息)
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object> getEffectiveReg_OwnershipMapByProcId(String proc_id); 
	
	/**
	 * 
	 * getEffectiveReg_OwnershipByRegUnitCode:(获取当前登记单元编号下生效的使用权数据)
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return Reg_Ownership
	 * @since JDK 1.6
	 */
	public Reg_ownership getEffectiveReg_OwnershipByRegUnitCode(String reg_unit_code);
	
	
	/**
	 * 
	 * getRegBookInfoForMort:抵押登记簿预览时，获取页面左侧树结构 <br/>
	 * @author PANDA
	 * @param m
	 * @return
	 * @since JDK 1.6
	 */	
	public List<Map<String,Object>> getRegBookTreeForMort(Map m);
	
	/**
	 * 
	 * isRegisterSave:(判断是否已经登簿     已经登簿返回true   未登簿返回false  各大类只需重写 else if中的判断  就行).
	 *
	 * @author Joyon
	 * @param paraMap reg_code 登记编号  
	 * @return
	 * @since JDK 1.6
	 */
	public boolean isRegisterSave(Map paraMap);
	
	/**
	 * 
	 * getNaturalInfoMapByRegUnitCode:(根据登记单元编号获取自然信息 )
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return 自然信息Map
	 * @since JDK 1.6
	 */
	public  Map<String, Object> getNaturalInfoMapByRegUnitCode(String reg_unit_code);
	
	/**
	 * 
	 * getEffectiveCerNoByRegUnitCode:(获取当前登记单元编号下生效的房地产证号)
	 *
	 * @author Joyon
	 * @param reg_unit_code
	 * @return String  cer_no
	 * @since JDK 1.6
	 */
	public String getEffectiveCerNoByRegUnitCode(String reg_unit_code) throws BusinessException;
	
	/**
	 * 根据流程实例ID 获取当前业务的权利人信息
	 */
	public List<Map<String,Object>> getCurrentHolderMapListByProcId(Map<String,Object> params);
}


