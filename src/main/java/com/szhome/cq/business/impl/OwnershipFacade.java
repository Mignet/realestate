/**
 * Project Name:dxtx_re
 * File Name:OwnershipFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-04-10 ����09:44:39
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/
package com.szhome.cq.business.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.springjdbc.annotation.Page;
import com.szhome.cq.business.IOwnershipFacade;
import com.szhome.cq.domain.model.Reg_ownership;
import com.szhome.cq.utils.SQLUtils;
import com.szhome.cq.utils.Util;

/**
 * ����Ȩfacade
 * Date:     2014-04-10 ����09:44:39<br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6	 
 */
@Component
@Transactional
@Scope("prototype")
public class OwnershipFacade implements IOwnershipFacade {
    
	
	@Autowired
	Reg_ownership ownershipDao;
	
	//��Ԫ����,����ֵ����,1.���ݱ�����ֶ�,2.ʵ������Ӧ�ֶ�,3.�������֡�1:�����ַ�������,2:����ʱ������,3:�������ڼ�ʱ��,4:���������͡�
	static final String[][] ownershipOptions = {{"tab.r_coll_names","holdername","1"},
		                                     {"tab.r_coll_cerno","idno","1"},
		                                     {"tab.cer_no","cerno","1"},
		                                     {"tab.parcel_code","parcelcode","1"},
		                                     {"tab.land_address","parceladdr","1"},
		                                     {"tab.real_usage","parcelusage","1"},
		                                     {"tab.reg_code","regcode","1"},
		                                     {"tab.right_state","rightstate","1"}};

	@Override
	public Page<Map<String, Object>> getAllOwnershipByParam(
			Map<String, Object> paramMap) {
		String sqlwhere = null;
		int pageNo = 1;
		int pageSize = 3;
		Map<String,Object> resultMap = SQLUtils.growWhereStatment(Util.ConvertObjToMap(paramMap.get("DXTXOBJECT")), ownershipOptions,null);
		Map<String,Object> fieldMap = null;
		if(resultMap!=null){
			sqlwhere = resultMap.get("SQLWHERELIKE").toString();
			fieldMap = (Map<String, Object>) resultMap.get("OPTIONS");
		}
		if(Util.isNotNull2Empty(paramMap.get("PAGENO"))){
			try {
				pageNo = Integer.parseInt((paramMap.get("PAGENO").toString() == null || paramMap.get("PAGENO").toString().equals("0"))?"1":paramMap.get("PAGENO").toString());
			} catch (Exception e) {
				pageNo = 1;
			}
		}
		if(Util.isNotNull2Empty(paramMap.get("PAGESIZE"))){
			try {
				pageSize = Integer.parseInt((paramMap.get("PAGESIZE").toString() == null || paramMap.get("PAGESIZE").toString().equals("0")?"10":paramMap.get("PAGESIZE").toString()));
			} catch (Exception e) {
				pageSize = 10;
			}
		}
		return ownershipDao.queryMapPageBykeyForOracle("Ownership.getAllOwnershipInfos", sqlwhere, fieldMap,pageNo,pageSize);
	}
	
    
	


	

}

