package com.szhome.cq.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.springjdbc.annotation.Page;
import com.szhome.cq.business.IPropertyRightFacade;
import com.szhome.cq.domain.model.Reg_Useright;
import com.szhome.cq.domain.model.Reg_ownership;
import com.szhome.cq.utils.SQLUtils;
import com.szhome.cq.utils.Util;

/**
 * ClassName:PropertyRightFacade <br/>
 * Date:     2014-04-21 下午5:15:39 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class PropertyRightFacade implements IPropertyRightFacade {

	@Autowired
	Reg_Useright userrDao;
	@Autowired
	Reg_ownership ownerDao;
	
	//二元数组,数组值意义,1.数据表里的字段,2.实体对象对应字段,3.类型区分【1:代表字符串类型,2:代表时间类型,3:代表日期加时间,4:代表金额类型】
	static final String[][] proprightOptions = {{"tab.reg_code","regcode","1"},
		                                     {"tab.roomname","houseno","1"},
		                                     {"tab.address","address","1"},
		                                     {"tab.pro_name","proname","1"},
		                                     {"tab.ashiftmode","shiftmode","1"},
		                                     {"tab.parcel_code","parcelcode","1"},
		                                     {"tab.building_name || tab.build_no","nameandno","1"},
		                                     {"tab.holder_no","idno","1"}};
	@Override
	public Page<Map<String, Object>> getAllPropRightsByParam(
			Map<String, Object> paramMap) {
		String sqlwhere = null;
		int pageNo = 1;
		int pageSize = 3;
		Map<String,Object> resultMap = SQLUtils.growWhereStatment(Util.ConvertObjToMap(paramMap.get("DXTXOBJECT")), proprightOptions,null);
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
		return userrDao.queryMapPageBykeyForOracle("Book.getAllPropRightsByParam", sqlwhere, fieldMap,pageNo,pageSize);
	}

	@Override
	public List<Map<String, Object>> getPropRightsByrightId(Map<String, Object> sqlMap,
			Map<String, Object> valueMap) {
		List<Map<String,Object>> retnLst = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> a = null;
		List<Map<String,Object>> b = null;
		if(Util.isNotNull2Empty(sqlMap.get("userightId"))){
			String wheresql1 = "where 1=1 " + sqlMap.get("userightId").toString();
		    a = userrDao.queryMapListByKey("Userright.getAllO", wheresql1, valueMap);
		}
		if(Util.isNotNull2Empty(sqlMap.get("ownershipId"))){
			String wheresql2 = "where 1=1 " + sqlMap.get("ownershipId").toString();
		    b = ownerDao.queryMapListByKey("Ownership.getAllO", wheresql2, valueMap);
		}
		if(a!=null)
		retnLst.addAll(a);
		if(b!=null)
		retnLst.addAll(b);
		return retnLst;
	}
    
}

