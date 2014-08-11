/**
 * Project Name:dxtx_re
 * File Name:IdentifierFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2013-12-24下午8:13:25
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
 * 编号facade
 * Date:     2013-12-24 下午8:13:25 <br/>
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
	
	/*************************************  重新修改  **********************************************/
	
	/**
	 * 
	 * 创建序列
	 *
	 * @author Joyon
	 * @param squenceName 序列名
	 * @return
	 * @since JDK 1.6
	 */
	public boolean createSquence(String squenceName){
		
		String sql="create  sequence "+squenceName+" minvalue 1    maxvalue 999999999   start with 1          increment by 1  nocache ";
		try {
//			planSupportDao.getPlanJdbcTemplate().execute(sql);
			identifierDao.executeQuery(sql);
		} catch (Exception e) {
			LogUtil.error("创建序列失败！"+e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * 删除序列
	 *
	 * @author Joyon
	 * @param squenceName 序列名
	 * @return
	 * @since JDK 1.6
	 */
	public boolean deleteSquence(String squenceName){
		String sql = "DROP SEQUENCE "+squenceName;
		try {
			//planSupportDao.getPlanJdbcTemplate().execute(sql);
			identifierDao.executeQuery(sql);
		} catch (Exception e) {
			LogUtil.error("删除序列失败！"+e.getMessage());
			return false;
		}
		return true;
	}
	
	
	
	/**
	 * 
	 * 获取编号treeData
	 *
	 * @author Joyon
	 * @param list
	 * @return
	 * @since JDK 1.6
	 */
	public List getIdentifierTree(){
		List<Identifier> list = identifierDao.queryListByKey("Identifier.getCfigCode", null);
		
		//如果第一次进来   数据库中没有任何数据 则插入一条初始化数据
		if(list == null || list.size()==0){
			Identifier initIdentifier = new Identifier();
			initIdentifier.setCode_cfig_id("0");
			initIdentifier.setRule_name("系统编号");
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
	 * 获取编号treeData字节点   递归方法
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
				//回调自己继续找子节点
				getTreeChirldData(identifier.getCode_cfig_id(),list,treeChildrenMap);
			}
		}
		
		treeMap.put("children", treeChildList);
	}
	
	
	/**
	 * 
	 * 保存编号配置信息
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
	 * 更新编号配置信息
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
	 * 删除编号信息
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
	 * 获取编号信息
	 *
	 * @author Joyon
	 * @param identifier  编号实体  必须要有主键
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
	 * 获取编号信息  通过主键Id
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
	 * 获取编号
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
		//非深户  
		if(household!=null && household.equals(WbfConstants.HOUSEHOLD_OTHER)){
			
			household =WbfConstants.HOUSEHOLD_OTHER_CODE;
		//不传时  和深户
		}else{
			household =WbfConstants.HOUSEHOLD_SHENZHEN_CODE;
		}
		
		//设置区号
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
				area = WbfConstants.LUOHU_CODE;			//如果未传入值  则赋值为 罗湖区
			}
		}else{
			area=WbfConstants.LUOHU_CODE;
		}
		
		//获取数据库中规则
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
		
		//拼编号规则字符串
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
				
				//序列加零 
				String zero="";
				for(int i=0;i<leng;i++){
					zero+="0";
				}
				
				String strSquence = identifier.getSeq_name();			//序列名
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


