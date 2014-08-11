/**
 * Project Name:dxtx_re
 * File Name:IdentifierFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2013-12-24����8:13:25
 * Copyright (c) 2013, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plan.commons.Row;
import com.plan.dao.PlanSupportDao;
import com.plan.dao.PlanSupportDaoImpl;
import com.plan.delegate.PlanDelegate;
import com.plan.exceptions.GeneralException;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.IIdentifierFacade;
import com.szhome.cq.domain.model.Identifier;
import com.szhome.cq.domain.model.IdentifierRule;
import com.szhome.cq.domain.model.IdentifierRuleSort;
import com.szhome.cq.utils.WbfConstants;


/**
 * ���facade
 * Date:     2013-12-24 ����8:13:25 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class IdentifierFacade implements IIdentifierFacade {
	@Autowired
	private Identifier identifierDao;
	
	@Autowired
	private IdentifierRule identifierRuleDao;
	
	@Autowired
	private IdentifierRuleSort identifierRuleSortDao;
	
	/*************************************  �����޸�  **********************************************/
	
	/**
	 * 
	 * ��������
	 *
	 * @author Joyon
	 * @param squenceName ������
	 * @return
	 * @since JDK 1.6
	 */
	public boolean createSquence(String squenceName){
		
		String sql="create  sequence "+squenceName+" minvalue 1    maxvalue 999999999   start with 1          increment by 1  nocache ";
		try {
//			planSupportDao.getPlanJdbcTemplate().execute(sql);
			identifierDao.executeQuery(sql);
		} catch (Exception e) {
			LogUtil.error("��������ʧ�ܣ�"+e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * ɾ������
	 *
	 * @author Joyon
	 * @param squenceName ������
	 * @return
	 * @since JDK 1.6
	 */
	public boolean deleteSquence(String squenceName){
		String sql = "DROP SEQUENCE "+squenceName;
		try {
			//planSupportDao.getPlanJdbcTemplate().execute(sql);
			identifierDao.executeQuery(sql);
		} catch (Exception e) {
			LogUtil.error("ɾ������ʧ�ܣ�"+e.getMessage());
			return false;
		}
		return true;
	}
	
	
	
	/**
	 * 
	 * ��ȡ���treeData
	 *
	 * @author Joyon
	 * @param list
	 * @return
	 * @since JDK 1.6
	 */
	public List getIdentifierTree(){
		List<Identifier> list = identifierDao.queryListByKey("Identifier.getCfigCode", null);
		
		//�����һ�ν���   ���ݿ���û���κ����� �����һ����ʼ������
		if(list == null || list.size()==0){
			Identifier initIdentifier = new Identifier();
			initIdentifier.setCode_cfig_id("0");
			initIdentifier.setRule_name("ϵͳ���");
			initIdentifier.setParent_id("-1");
			identifierDao.save(initIdentifier);
			
			list = identifierDao.queryListByKey("Identifier.getCfigCode", null);
		}
		
		List treeList  = new ArrayList();
		Map treeMap = null;
		for(Identifier identifier:list){
			String parentId = identifier.getParent_id();
			if(parentId.equals("-1")){
				treeMap = new HashMap();
				treeMap.put("id", identifier.getCode_cfig_id());
				treeMap.put("text", identifier.getRule_name());
				//treeMap.put("", value)
				
				
				getTreeChirldData(identifier.getCode_cfig_id(),list,treeMap);
				
				treeList.add(treeMap);
			}
		}
		
		return treeList;
	}
	
	/**
	 * 
	 * ��ȡ���treeData�ֽڵ�   �ݹ鷽��
	 *
	 * @author Joyon
	 * @param parentId
	 * @param list
	 * @param treeMap
	 * @since JDK 1.6
	 */
	private void getTreeChirldData(String parentId,List<Identifier> list,Map treeMap){
		Map treeChildrenMap = null;
		List treeChildList = new ArrayList();
		for(Identifier identifier:list){
			if(parentId.equals(identifier.getParent_id())){
				treeChildrenMap = new HashMap();
				treeChildrenMap.put("id",identifier.getCode_cfig_id());
				treeChildrenMap.put("text", identifier.getRule_name());
				treeChildList.add(treeChildrenMap);
				//�ص��Լ��������ӽڵ�
				getTreeChirldData(identifier.getCode_cfig_id(),list,treeChildrenMap);
			}
		}
		
		treeMap.put("children", treeChildList);
	}
	
	
	/**
	 * 
	 * ������������Ϣ
	 *
	 * @author Joyon
	 * @param identifier
	 * @since JDK 1.6
	 */
	public Identifier saveIdentifer(Identifier identifier) throws GeneralException{
		if(identifier.getCode_cfig_id()==null){
			identifier.setCode_cfig_id(identifierDao.getSeqId());
		}
		identifierDao.save(identifier);
		return identifier;
	}
	
	/**
	 * 
	 * ���±��������Ϣ
	 *
	 * @author Joyon
	 * @param identifier
	 * @since JDK 1.6
	 */
	public void updateIdentifier(Identifier identifier) throws GeneralException{
		identifierDao.update(identifier);
	}
	
	/**
	 * 
	 * ɾ�������Ϣ
	 *
	 * @author Joyon
	 * @param identifier
	 * @throws GeneralException
	 * @since JDK 1.6
	 */
	public void deleteIdentifier(Identifier identifier) throws GeneralException{
		identifierDao.delete(identifier);
	}
	
	/**
	 * 
	 * ��ȡ�����Ϣ
	 *
	 * @author Joyon
	 * @param identifier  ���ʵ��  ����Ҫ������
	 * @return
	 * @since JDK 1.6
	 */
	public Identifier getIdentifier(Identifier identifier) throws GeneralException{
		return identifierDao.get(identifier);
	}
	
	private Identifier getIdentifierByRulename(String ruleName){
		Map paraMap = new HashMap();
		paraMap.put("ruleName", ruleName);
		Identifier identifier = identifierDao.get("where rule_name=:ruleName",paraMap);
		return identifier;
	}
	
	/**
	 * 
	 * ��ȡ�����Ϣ  ͨ������Id
	 *
	 * @author Joyon
	 * @param id
	 * @return
	 * @throws GeneralException
	 * @since JDK 1.6
	 */
	public Identifier getIdentifierById(String id) throws GeneralException{
		Identifier identifier = new Identifier();
		identifier.setCode_cfig_id(id);
		return identifierDao.get(identifier);
	}
	
	/**
	 * 
	 * ��ȡ���
	 *
	 * @author Joyon
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public String getSerialNumber(Row row){
		String ruleName = row.getString("name");
		String area = row.getString("area");
		String household = row.getString("household");
		//���  
		if(household!=null && household.equals(WbfConstants.HOUSEHOLD_OTHER)){
			
			household =WbfConstants.HOUSEHOLD_OTHER_CODE;
		//����ʱ  ���
		}else{
			household =WbfConstants.HOUSEHOLD_SHENZHEN_CODE;
		}
		
		//��������
		if(area!=null){
			
			
			if(area.equals(WbfConstants.LUOHU)){
				area=WbfConstants.LUOHU_CODE;
			}else if(area.equals(WbfConstants.FUTIAN)){
				area=WbfConstants.FUTIAN_CODE;
			}else if(area.equals(WbfConstants.NANSHAN)){
				area=WbfConstants.NANSHAN_CODE;
			}else if(area.equals(WbfConstants.BAOAN)){
				area=WbfConstants.BAOAN;
			}else if(area.equals(WbfConstants.LONGGANG)){
				area=WbfConstants.LONGGANG_CODE;
			}else if(area.equals(WbfConstants.LONGHUA)){
				area=WbfConstants.LONGHUA_CODE;
			}else if(area.equals(WbfConstants.YANTIAN)){
				area=WbfConstants.YANTIAN_CODE;
			}else if(area.equals(WbfConstants.GUANGMING)){
				area=WbfConstants.GUANGMING_CODE;
			}else if(area.equals(WbfConstants.PINGSHAN)){
				area=WbfConstants.PINGSHAN_CODE;
			}else if(area.equals(WbfConstants.DAPENG)){
				area=WbfConstants.DAPENG_CODE;
			}else {
				area = WbfConstants.LUOHU_CODE;			//���δ����ֵ  ��ֵΪ �޺���
			}
		}else{
			area=WbfConstants.LUOHU_CODE;
		}
		
		//��ȡ���ݿ��й���
		Identifier identifier = getIdentifierByRulename(ruleName);
		if(identifier==null)
			return null;
		
		String rule_exper = identifier.getRule_exper();
		JSONArray   ruleJSONArray = JSONArray.fromObject(rule_exper);
		List<Map> list = JSONArray.toList(ruleJSONArray, Map.class);
		System.out.println(list);
		
		String ruleStr = "select ''";
//		
//		for(int i=0;i<list.size();i++){
//			if(list[i])
//		}
		
		//ƴ��Ź����ַ���
		String tmpIdStr ="";
		for(Map tmpMap :list){
			tmpIdStr= tmpMap.get("id").toString();
			if(tmpIdStr.equals("household")){
				ruleStr+="||'"+household+"'";
			}else if(tmpIdStr.equals("area")){
				ruleStr+="||'"+area+"'";
			}else if(tmpIdStr.equals("char")){
				ruleStr+="||'"+tmpMap.get("text").toString()+"'";
			}else if(tmpIdStr.equals("year")){
				ruleStr+="||to_char(sysdate,'"+tmpMap.get("text").toString()+"')";
			}else if(tmpIdStr.equals("squence")){
				String length = tmpMap.get("text").toString();
				int leng = Integer.valueOf(length);
				
				//���м��� 
				String zero="";
				for(int i=0;i<leng;i++){
					zero+="0";
				}
				
				String strSquence = identifier.getSeq_name();			//������
				//substr('00000',0,5-length(SEQ_ARCH_NO.nextval))|| SEQ_ARCH_NO.currval
				ruleStr+="||substr('"+zero+"',0,"+leng+"-length("+strSquence+".nextval))||"+strSquence+".currval";
			}
		}
		ruleStr+=" from dual";
		System.out.println(ruleStr);
		String code = identifierDao.executeQuery(ruleStr);
		System.out.println(code);
		return code;
	}


}


