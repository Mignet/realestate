/**
 * Project Name:dxtx_re
 * File Name:PresaleAction.java
 * Package Name:com.szhome.cq.web.backup
 * Date:2014-4-2����11:41:44
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
 * Ԥ�۱���
 * Date:     2014-4-2 ����11:41:44 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class PresaleAction extends BaseDelegate{
	
	/**
	 * 
	 * getPreSaleInfo:(��ȡԤ����Ϣ). 
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
			LogUtil.error("PresaleAction.getPreSaleInfo():��ȡԤ����Ϣ����"+e.getMessage());
		}
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		resultList.add(resultMap);
		return JsonUtil.object2json(resultList);
	}
	
	/**
	 * 
	 * ��ȡԤ�۷���Ϣ
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
	 * setPreSaleState:(����Ԥ�۱���״̬).
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
			LogUtil.error("PresaleAction.setPreSaleState()  �޸�Ԥ�۱���״̬ʧ��");
		}
		return null;
	}
	
	/**
	 * 
	 * isRecorded:(�жϵ�ǰԤ�۱����Ƿ񼺱���).
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
			LogUtil.error("presaleAction isRecorded() ��ȡ����״̬����"+e.getMessage());
		}
		//map ����ǰ��ʱ  ǰ��true��false�жϲ�����  ������1��������  0����δ����
		if(result){
			resultMap.put("result", "1");
		}else{
			resultMap.put("result", "0");
		}
		return JsonUtil.object2json(resultMap);
	}
	
	/**
	 * 
	 * ��ȡԤ������Ϣ
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
	 * Ԥ�۱�������ǰ���ʱ����� ����һ���г��ӿ������ݵ�����
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


