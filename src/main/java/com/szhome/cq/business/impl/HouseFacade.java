/**
 * Project Name:dxtx_re
 * File Name:HouseFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-3-37 下午15:02:39
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

import com.szhome.cq.business.IHouseFacade;
import com.szhome.cq.domain.model.House;
import com.szhome.cq.utils.Util;
import com.szhome.cq.utils.SQLUtils;
import com.springjdbc.annotation.Page;

/**
 * 房屋facade
 * Date:     2014-3-27 下午15:03:39
 * @author   Sam
 * @version  
 * @since    JDK 1.6	 
 */
@Component
@Transactional
@Scope("prototype")
public class HouseFacade implements IHouseFacade {
	@Autowired
	House houseDao;
	//二元数组,数组值意义,1.数据表里的字段,2.实体对象对应字段,3.类型区分【1:代表字符串类型,2:代表时间类型,3:代表日期加时间,4:代表金额类型】
	static final String[][] houseOptions = {{"tab.r_coll_names","holdername","1"},
		                                    {"tab.r_coll_cerno","idno","1"},
											{"tab.cer_code","cerno","1"},
											{"tab.parcel_code","parcelcode","1"},
											{"tab.house_code","housecode","1"},
											{"tab.unit_no","houseno","1"},
											{"tab.build_no","buildingno","1"},
											{"tab.building_name","buildingname","1"}
											/*,
											{"","precontractno"},
											{"","contractno"}*/
											};
	/**
	 * 
	 * 通过条件获取房屋信息
	 * @see com.szhome.cq.business.IHouseFacade#getHouseInfosByOptions(java.util.Map)
	 */
	public List<Map<String, Object>> getHouseInfosByOptions(
			Map<String, Object> pmmap) throws Exception {
		String sqlwhere = null;
		int pageNo = 1;
		int pageSize = 10;
		Map<String,Object> resultMap = SQLUtils.growWhereStatment(Util.ConvertObjToMap(pmmap.get("DXTXOBJECT")), houseOptions,null);
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
			sqlwhere = sqlwhere + " order by tab.book_code";*/
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
		Page<Map<String, Object>> p = houseDao.queryMapPageBykeyForOracle("Book.getHousesforSearch", sqlwhere, fieldMap,pageNo,pageSize);
		return p.getList();
	}

}

