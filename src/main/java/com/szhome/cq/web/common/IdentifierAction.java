/**
 * Project Name:dxtx_re
 * File Name:IdentifierAction.java
 * Package Name:com.szhome.cq.web.identifier
 * Date:2013-12-24下午8:07:13
 * Copyright (c) 2013, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.szhome.cq.web.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;

import com.plan.commons.Row;
import com.plan.exceptions.GeneralException;
import com.plan.util.json.JsonParserJsonlibImpl;
import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.FacadeFactory;
import com.szhome.cq.business.IIdentifierFacade;
import com.szhome.cq.delegate.BaseDelegate;
import com.szhome.cq.domain.model.Identifier;
import com.szhome.cq.utils.JsonUtil;
import com.szhome.cq.utils.WbfConstants;
import com.szhome.security.ext.UserInfo;

/**
 * 编号配置
 * 
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class IdentifierAction extends BaseDelegate{
	private IIdentifierFacade identifierFacade=FacadeFactory.getIdentifierFacade();
	
	
	/**
	 * 
	 * 获取编号规则树菜单
	 *
	 * @author Joyon
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public String getIdentifierTree(Row row){
			List  resultList = identifierFacade.getIdentifierTree();
			return JsonUtil.object2json(resultList);
	}
	
	
	/**
	 * 
	 * 保存编号 信息如果编号信息已经存在则更新
	 *
	 * @author Joyon
	 * @param row
	 * @return
	 * @throws GeneralException 
	 * @since JDK 1.6
	 */
	public String saveRule(Row row) throws GeneralException{
		String code_id = row.getString("code_id");
		String rule = row.getString("rule");
		
	
		System.out.println(rule.length());

		
		try {
			Identifier identifier = identifierFacade.getIdentifierById(code_id);
			
			//如果数据库中不存在则保存 否则更新 
			if(identifier==null){
				identifier = new Identifier();
				identifier.setRule_exper(rule);
				identifierFacade.saveIdentifer(identifier);
			}else{
				identifier.setRule_exper(rule);
				identifierFacade.updateIdentifier(identifier);
			}
			
		} catch (GeneralException e) {
			
			// TODO Auto-generated catch block
			//e.printStackTrace();
			LogUtil.error("保存规则出错:"+e.getMessage());
			throw new GeneralException("保存规则 出错："+e.getMessage());
			
		}
		
		Map resultMap = new HashMap();
		resultMap.put("result", "保存成功!");
		
		return JsonUtil.object2json(resultMap);
	}
	
	
	/**
	 * 
	 * 创建编号  创建序列
	 *
	 * @author Joyon
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public String createRule(Row row){
		//String code_id = row.getString("code_id");
		String ruleName = row.getString("ruleName");
		String parentId = row.getString("parentId");
		
		Identifier identifier = new Identifier();
		identifier.setRule_name(ruleName);
		identifier.setParent_id(parentId);
		try {
			identifier = identifierFacade.saveIdentifer(identifier);
			
			//保存成功后   创建序列  并把序列保存到数据库   序列规则--SEQ_表名+主键
			String squenceName = "SEQ_CFIG_CODE_"+identifier.getCode_cfig_id();
			Boolean isCreate = identifierFacade.createSquence(squenceName);
			//if(isCreate){
				identifier.setSeq_name(squenceName);
				
				identifierFacade.updateIdentifier(identifier);
			//}
			
		} catch (GeneralException e) {
			
			LogUtil.error("创建规则失败:"+e.getMessage());
			
		}
		return JsonUtil.object2json(identifier);
	}
	
	/**
	 * 
	 * 编辑规则
	 *
	 * @author Joyon
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	public String editRule(Row row){
		String code_id = row.getString("code_id");
		String ruleName = row.getString("ruleName");
		Identifier identifier = null;
		try {
			identifier = identifierFacade.getIdentifierById(code_id);
			identifier.setRule_name(ruleName);
			identifierFacade.updateIdentifier(identifier);
		} catch (GeneralException e) {
			
			LogUtil.error("更新失败!"+e.getMessage());
			
		}
		
		return null;
	}
	
	/**
	 * 
	 * 获取编号规则配置
	 *
	 * @author Joyon
	 * @param row
	 * @return
	 * @throws GeneralException
	 * @since JDK 1.6
	 */
	public String getRule(Row row) throws GeneralException{
		Identifier identifier = null;
		try {
			 identifier = identifierFacade.getIdentifierById(row.getString("code_id"));
		} catch (GeneralException e) {
			LogUtil.error("获取规则出错:"+e.getMessage());
			throw new GeneralException("获取规则 出错："+e.getMessage());
			
		}
		return JsonUtil.object2json(identifier);
	}
	
	/**
	 * 
	 * 删除规则   删除编号   删除编号序列
	 *
	 * @author Joyon
	 * @param row
	 * @return
	 * @throws GeneralException
	 * @since JDK 1.6
	 */
	public String removeRule(Row row) throws GeneralException{
		Identifier identifier = null;
		Map resultMap = new HashMap();
		try {
			 identifier = identifierFacade.getIdentifierById(row.getString("code_id"));
			 
			 if(identifier == null){
				 resultMap.put("msg","删除失败！");
				 resultMap.put("sign","fail");
				 return JsonUtil.object2json(resultMap);
			 }
			 identifierFacade.deleteSquence(identifier.getSeq_name());		//先删除序列
			 
			 identifierFacade.deleteIdentifier(identifier);					//再删除该编号
		} catch (GeneralException e) {
			LogUtil.error("获取规则出错:"+e.getMessage());
			throw new GeneralException("获取规则 出错："+e.getMessage());
			
		}
		
		 resultMap.put("msg","删除成功！");
		 resultMap.put("sign","success");
		 return JsonUtil.object2json(resultMap);
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
	public String getNum(Row row){
		//row.put("name", "新增");
		row.put("household", WbfConstants.HOUSEHOLD_SHENZHEN);
		String  number = identifierFacade.getSerialNumber(row);
		return number;
	}

}


