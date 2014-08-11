/**
 * Project Name:dxtx_re
 * File Name:ParcelFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-3-14 下午11:08:39
 * Copyright (c) 2014, dxtx All Rights Reserved.
 *
*/
package com.szhome.cq.business.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.szhome.cq.business.IParcelFacade;
import com.szhome.cq.domain.model.Land;
import com.szhome.cq.utils.Util;
import com.szhome.cq.utils.SQLUtils;
import com.springjdbc.annotation.Page;

/**
 * 赛地facade
 * Date:     2014-3-14 下午11:08:39 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6	 
 */
@Component
@Transactional
@Scope("prototype")
public class ParcelFacade implements IParcelFacade {
    
	
	@Autowired
	Land landDao;
	
	//二元数组,数组值意义,1.数据表里的字段,2.实体对象对应字段,3.类型区分【1:代表字符串类型,2:代表时间类型,3:代表日期加时间,4:代表金额类型】
	static final String[][] parcelOptions = {{"tab.r_coll_names","holdername","1"},
		                                     {"tab.r_coll_cerno","idno","1"},
		                                     {"tab.cer_code","cerno","1"},
		                                     {"tab.parcel_code","parcelcode","1"},
		                                     {"tab.land_address","parceladdr","1"},
		                                     {"tab.real_real_usage","parcelusage","1"},
		                                     {"tab.reg_code","regcode","1"},
		                                     {"tab.right_status","rightstate","1"}};
	
    
	
	@Override
	public List<Map<String, Object>> getAllParcelInfo() throws Exception {
		return landDao.queryMapListByKey("Book.getParcelsforSearch", null);
	}

	@Override
	public List<Map<String, Object>> getParcelInfosByOptions(Map<String, Object> pmmap)
			throws Exception {
		String sqlwhere = null;
		int pageNo = 1;
		int pageSize = 10;
		Map<String,Object> resultMap = SQLUtils.growWhereStatment(Util.ConvertObjToMap(pmmap.get("DXTXOBJECT")), parcelOptions,null);
		Map<String,Object> fieldMap = null;
		if(resultMap!=null){
			fieldMap = (Map<String,Object>)resultMap.get("OPTIONS");
			sqlwhere = resultMap.get("SQLWHERELIKE").toString();
			/*if(fieldMap.size() == 1){
				sqlwhere = resultMap.get("SQLWHERELIKE").toString();
			}else if(fieldMap.size() > 0){
				sqlwhere = resultMap.get("SQLWHERE").toString();
			}
			if(sqlwhere!=null)
			sqlwhere = sqlwhere + "order by tab.book_code";*/
			
		}
		if(Util.isNotNull2Empty(pmmap.get("PAGENO"))){
			try {
				pageNo = Integer.parseInt((pmmap.get("PAGENO").toString() == null || pmmap.get("PAGENO").toString().equals("0"))?"1":pmmap.get("PAGENO").toString());
			} catch (Exception e) {
				pageNo = 1;
			}
		}
		if(Util.isNotNull2Empty(pmmap.get("PAGESIZE"))){
			try {
				pageSize = Integer.parseInt((pmmap.get("PAGESIZE").toString() == null || pmmap.get("PAGESIZE").toString().equals("0")?"10":pmmap.get("PAGESIZE").toString()));
			} catch (Exception e) {
				pageSize = 10;
			}
		}
		Page<Map<String, Object>> p = landDao.queryMapPageBykeyForOracle("Book.getParcelsforSearch", sqlwhere, fieldMap,pageNo,pageSize);
		return p.getList();
	}

}

