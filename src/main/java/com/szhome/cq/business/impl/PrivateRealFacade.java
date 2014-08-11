package com.szhome.cq.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.springjdbc.annotation.Page;
import com.szhome.cq.business.IPrivateRealFacade;
import com.szhome.cq.domain.model.Holder;
import com.szhome.cq.utils.SQLUtils;
import com.szhome.cq.utils.Util;

/**
 * ClassName:PrivateRealFacade <br/>
 * Date:     2014-04-21 ����5:15:39 <br/>
 * @author   Sam
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class PrivateRealFacade implements IPrivateRealFacade {
	@Autowired
	Holder        holderDao;
	
	
	//��Ԫ����,����ֵ����,1.���ݱ�����ֶ�,2.ʵ������Ӧ�ֶ�,3.�������֡�1:�����ַ�������,2:����ʱ������,3:�������ڼ�ʱ��,4:���������͡�
	static final String[][] privaterealOptions = {{"tab.hol_cer_type","idtype","1"},
		                                     {"tab.hol_cer_no","idno","1"},
		                                     {"tab.hol_name","holdername","1"}};
	@Override
	public Map<String, Object> getPrivateRealsById(Map<String,Object> sqlMap,
			Map<String, Object> valueMap) {
			   Map<String,Object> retnMap = null;
			   StringBuffer wheresql = new StringBuffer("where 1=1 ");
			   wheresql.append(sqlMap.get("holderId"));
			   retnMap = holderDao.queryMapByKey("Book.getPersonhouseright", wheresql.toString(), valueMap);
			   return retnMap;
	}

	@Override
	public Page<Map<String, Object>> getAllPrivateRealsByParam(
			Map<String, Object> paramMap) {
		String sqlwhere = null;
		int pageNo = 1;
		int pageSize = 3;
		Map<String,Object> resultMap = SQLUtils.growWhereStatment(Util.ConvertObjToMap(paramMap.get("DXTXOBJECT")), privaterealOptions,null);
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
		return holderDao.queryMapPageBykeyForOracle("Book.getPersonhouseright", sqlwhere, fieldMap,pageNo,pageSize);
	}

}

