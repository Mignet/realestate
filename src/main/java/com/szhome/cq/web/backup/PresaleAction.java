/**
 * Project Name:dxtx_re
 * File Name:PresaleAction.java
 * Package Name:com.szhome.cq.web.backup
 * Date:2014-4-2上午11:41:44
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.web.backup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plan.commons.Row;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.utils.JsonUtil;

/**
 * 预售备案
 * Date:     2014-4-2 上午11:41:44 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class PresaleAction extends BaseDelegate{
	
	/**
	 * 
	 * getPreSaleInfo:(获取预售信息). 
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getPreSaleInfo(Row row){
		Map<String, Object> resultMap = null;
		String proc_id = row.getString("proc_id");
		try {
			 resultMap = FacadeFactory.getPresaleFacade()
					.getPreSaleInfo(proc_id);
		} catch (Exception e) {
			LogUtil.error("PresaleAction.getPreSaleInfo():获取预售信息出错"+e.getMessage());
		}
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		resultList.add(resultMap);
		return JsonUtil.object2json(resultList);
	}
	
	/**
	 * 
	 * 获取预售方信息
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getPreSaler(Row row){
		List<Map<String, Object>> resultList = null;
		try {
			String proc_id = row.getString("proc_id");
			 resultList = FacadeFactory
					.getPresaleFacade().getPreSaler(proc_id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return JsonUtil.object2json(resultList);
	}
	
	/**
	 * 
	 * setPreSaleState:(设置预售备案状态).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String setPreSaleState(Row row){
		try {
			String proc_id = row.getString("proc_id");
			String state = row.getString("state");
			FacadeFactory.getPresaleFacade().setPreSaleState(proc_id,state);
		} catch (Exception e) {
			LogUtil.error("PresaleAction.setPreSaleState()  修改预售备案状态失败");
		}
		return null;
	}
	
	/**
	 * 
	 * isRecorded:(判断当前预售备案是否己备案).
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String isRecorded(Row row){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		boolean result = false;
		try {
			String proc_id = row.getString("proc_id");
			result = FacadeFactory.getPresaleFacade().isRecorded(proc_id);
		} catch (Exception e) {
			LogUtil.error("presaleAction isRecorded() 获取备案状态出错"+e.getMessage());
		}
		//map 返到前端时  前端true和false判断不出来  所以用1代表己备案  0代表未备案
		if(result){
			resultMap.put("result", "1");
		}else{
			resultMap.put("result", "0");
		}
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * 获取预售人信息
	 *
	 * @author Joyon
	 * @return
	 * @since JDK 1.6
	 */
	public String getPreBuyerInfo(Row row){
		String proc_id = row.getString("proc_id");
		List resultList = FacadeFactory.getPresaleFacade().getPreBuyerInfo(proc_id);
		
		return JsonUtil.object2json(resultList);
	}
	
	/**
	 * 
	 * 预售备案受理前审核时需调用 保存一份市场接口中数据到本地
	 *
	 * @author Joyon
	 * @param proc_id
	 * @since JDK 1.6
	 */
	public void savePresaleTodb(Row row){
		String proc_id = row.getString("proc_id");
		FacadeFactory.getPresaleFacade().savePresaleTodb(proc_id );
	}

}


