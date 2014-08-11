/**
 * Project Name:dxtx_re
 * File Name:IRecmaterialFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-1-15上午9:54:11
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.szhome.cq.domain.model.MaterialReplenish;
import com.szhome.cq.domain.model.RecScanner;
import com.szhome.security.ext.UserInfo;

/**
 * ClassName:IRecmaterialFacade <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-15 上午9:54:11 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IRecmaterialFacade {
	/**
	 * 
	 * getRecmaterialMapListByRegId:(通过登记编号获取当前业务的接件材料).
	 *
	 * @author Joyon
	 * @param paraMap reg_id
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getRecmaterialMapListByRegId(Map<String,Object> paraMap);
	
	/**
	 * 
	 * applyEdit:(将字接件材料编辑后的数据应用到后台数据库). 
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public Map applyEdit(Map paraMap);
	
	/**
	 * 
	 * getScannerListMapByProcId:(通过流程实例ID获取当前业务扫描件List).
	 *
	 * @author Joyon
	 * @param proc_id
	 * @return
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getScannerListMapByProcId(String proc_id);
	
	/**
	 * 
	 * getRecScanner:(获取材料扫描数据). 
	 *
	 * @author Joyon
	 * @param recScanner
	 * @return
	 * @since JDK 1.6
	 */
	public RecScanner getRecScanner(RecScanner recScanner);
	
	/**
	 * 
	 * modifyScanner:(修改材料扫描数据). 
	 * @author Joyon
	 * @param recScanner
	 * @return
	 * @since JDK 1.6
	 */
	public Map<String,Object>  modifyRecScanner(RecScanner recScanner);
	
	/**
	 * 
	 * 
	 *
	 * @author Joyon
	 * @param rep_proc_id  需要补正的流程ID
	 * @since JDK 1.6
	 */
	public Map<String,Object> startMaterialReplenishProc(String rep_proc_id,UserInfo userInfo) throws BusinessException;
	
	/**
	 * 
	 * 更新补正材料信息
	 *
	 * @author Joyon
	 * @param materialReplenish
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void updateMaterialReplenish(MaterialReplenish materialReplenish) throws BusinessException;
	

	/**
	 * 
	 * 保存补正材料信息
	 *
	 * @author Joyon
	 * @param materialReplenish
	 * @since JDK 1.6
	 */
	public void saveMaterialReplenish(MaterialReplenish materialReplenish) throws BusinessException;
	
	
	/**
	 * 
	 * 通过登记编号获取补正材料
	 *
	 * @author Joyon
	 * @param reg_code  需要补正材料业务的登记编号
	 * @since JDK 1.6
	 */
	public MaterialReplenish getMaterialReplenishByregcode(String reg_code) throws BusinessException;
	
	/**
	 * 
	 * 通过登记编号获取补正材料ID
	 *
	 * @author Sam
	 * @param reg_code  需要补正材料业务的登记编号
	 * @since JDK 1.6
	 */
	public List<Map<String,Object>> getMaterialIdListByregcode(Map<String,Object> paramMap) throws BusinessException;
}


