/**
 * Project Name:dxtx_re
 * File Name:PreadviceFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-04-10 上午09:44:39
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

import com.springjdbc.annotation.Page;
import com.szhome.cq.business.IPreadviceFacade;
import com.szhome.cq.domain.model.Reg_Advnotice;
import com.szhome.cq.utils.SQLUtils;
import com.szhome.cq.utils.Util;

/**
 * ClassName:PreadviceFacade <br/>
 * Date:    2014-04-10 上午09:44:39<br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6	 
 */
@Component
@Transactional
@Scope("prototype")
public class PreadviceFacade implements IPreadviceFacade{
    
	
	@Autowired
	Reg_Advnotice advDao;
	
	//二元数组,数组值意义,1.数据表里的字段,2.实体对象对应字段,3.类型区分【1:代表字符串类型,2:代表时间类型,3:代表日期加时间,4:代表金额类型】
	static final String[][] preadviceOptions = {{"tab.holder_name","holdername","1"},
		                                     {"tab.aadv_type","advtype","1"},
		                                     {"tab.cer_no","cerno","1"},
		                                     {"tab.adv_no","advno","1"},
		                                     {"tab.real_usage","parcelusage","1"},
		                                     {"tab.reg_code","regcode","1"},
		                                     {"tab.areg_date","regdate","1"}};

	@Override
	public Page<Map<String, Object>> getAllPreadvicesByParam(
			Map<String, Object> paramMap) {
		String sqlwhere = null;
		int pageNo = 1;
		int pageSize = 3;
		Map<String,Object> resultMap = SQLUtils.growWhereStatment(Util.ConvertObjToMap(paramMap.get("DXTXOBJECT")), preadviceOptions,null);
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
		return advDao.queryMapPageBykeyForOracle("Preadvice.getAllPreadviceInfos", sqlwhere, fieldMap,pageNo,pageSize);
	}

	@Override
	public List<Map<String, Object>> getPreadvicesByPreadviceId(
			Map<String, Object> sqlMap, List<Map<String, Object>> valueLst) {
		// TODO Auto-generated method stub
		return null;
	}
	
    
	


	

}

