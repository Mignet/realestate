/**
 * Project Name:dxtx_re
 * File Name:RecMatConfigureFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-1-15����11:10:43
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
 * �Ӽ���������facade
 * Date:     2014-1-15 ����11:10:43 <br/>
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
	private RecMatConfigure recMatConDao;								//�����������ʵ��
	
	/**
	 * 
	 * getRecMatConMapListByRegId:(ͨ���ǼǱ�Ż�ȡ��ǰҵ��Ĳ�������).
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
				throw new BusinessException("��ȡ�Ӽ��������ó��� ��������Ϊ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("��ȡ�Ӽ��������ó���"+e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 
	 * getRecMatConAsRecMapListByRegId:(ͨ���ǼǱ�Ż�ȡ��ǰҵ��Ĳ������� AS �Ӽ�����).
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
				throw new BusinessException("��ȡ�Ӽ��������ó��� ��������Ϊ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("��ȡ�Ӽ��������ó���"+e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 
	 * getRecMatConfigureListByRegCode:(���ݵǼǱ�Ż�ȡ�Ӽ���������List).
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


