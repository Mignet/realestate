/**
 * Project Name:dxtx_re
 * File Name:RecMatConfigureFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-1-15上午11:10:43
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.IRecMatConfigureFacade;
import com.szhome.cq.domain.model.RecMatConfigure;

/**
 * 接件材料配置facade
 * Date:     2014-1-15 上午11:10:43 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class RecMatConfigureFacade implements IRecMatConfigureFacade {
	@Autowired
	private RecMatConfigure recMatConDao;								//接年材料配置实体
	
	/**
	 * 
	 * getRecMatConMapListByRegId:(通过登记编号获取当前业务的材料配置).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	@Transactional
	public List<Map<String,Object>> getRecMatConMapListByRegCode(Map<String,Object> paraMap){
		List<Map<String,Object>> resultList = null;
		try {
			resultList = recMatConDao.queryMapListByKey("RecMatConfigure.getRecMatConMapListByRegCode", paraMap);
			if(resultList == null || resultList.isEmpty()){
				throw new BusinessException("获取接件材料配置出错 配置数据为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("获取接件材料配置出错"+e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 
	 * getRecMatConAsRecMapListByRegId:(通过登记编号获取当前业务的材料配置 AS 接件材料).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	@Transactional
	public List<Map<String,Object>> getRecMatConAsRecMapListByRegCode(Map<String,Object> paraMap){
		List<Map<String,Object>> resultList = null;
		try {
			resultList = recMatConDao.queryMapListByKey("RecMatConfigure.getRecMatConAsRecMapListByRegCode", paraMap);
			if(resultList == null || resultList.isEmpty()){
				throw new BusinessException("获取接件材料配置出错 配置数据为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("获取接件材料配置出错"+e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 
	 * getRecMatConfigureListByRegCode:(根据登记编号获取接件材料配置List).
	 *
	 * @author Joyon
	 * @param paraMap
	 * @return
	 * @since JDK 1.6
	 */
	public List<RecMatConfigure> getRecMatConfigureListByRegCode(Map<String,Object> paraMap){
		List<RecMatConfigure> resultList = null;
		try {
			resultList = recMatConDao
					.getAll("where bus_type_id=(select reg_type from bus_main where reg_code=:reg_code) and rec_type_flag=:rec_type_flag",
							paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
}


