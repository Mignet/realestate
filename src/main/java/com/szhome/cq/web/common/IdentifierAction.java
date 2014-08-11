/**
 * Project Name:dxtx_re
 * File Name:IdentifierAction.java
 * Package Name:com.szhome.cq.web.identifier
 * Date:2013-12-24����8:07:13
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
 * �������
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
	 * ��ȡ��Ź������˵�
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
	 * ������ ��Ϣ��������Ϣ�Ѿ����������
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
			
			//������ݿ��в������򱣴� ������� 
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
			LogUtil.error("����������:"+e.getMessage());
			throw new GeneralException("������� ����"+e.getMessage());
			
		}
		
		Map resultMap = new HashMap();
		resultMap.put("result", "����ɹ�!");
		
		return JsonUtil.object2json(resultMap);
	}
	
	
	/**
	 * 
	 * �������  ��������
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
			
			//����ɹ���   ��������  �������б��浽���ݿ�   ���й���--SEQ_����+����
			String squenceName = "SEQ_CFIG_CODE_"+identifier.getCode_cfig_id();
			Boolean isCreate = identifierFacade.createSquence(squenceName);
			//if(isCreate){
				identifier.setSeq_name(squenceName);
				
				identifierFacade.updateIdentifier(identifier);
			//}
			
		} catch (GeneralException e) {
			
			LogUtil.error("��������ʧ��:"+e.getMessage());
			
		}
		return JsonUtil.object2json(identifier);
	}
	
	/**
	 * 
	 * �༭����
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
			
			LogUtil.error("����ʧ��!"+e.getMessage());
			
		}
		
		return null;
	}
	
	/**
	 * 
	 * ��ȡ��Ź�������
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
			LogUtil.error("��ȡ�������:"+e.getMessage());
			throw new GeneralException("��ȡ���� ����"+e.getMessage());
			
		}
		return JsonUtil.object2json(identifier);
	}
	
	/**
	 * 
	 * ɾ������   ɾ�����   ɾ���������
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
				 resultMap.put("msg","ɾ��ʧ�ܣ�");
				 resultMap.put("sign","fail");
				 return JsonUtil.object2json(resultMap);
			 }
			 identifierFacade.deleteSquence(identifier.getSeq_name());		//��ɾ������
			 
			 identifierFacade.deleteIdentifier(identifier);					//��ɾ���ñ��
		} catch (GeneralException e) {
			LogUtil.error("��ȡ�������:"+e.getMessage());
			throw new GeneralException("��ȡ���� ����"+e.getMessage());
			
		}
		
		 resultMap.put("msg","ɾ���ɹ���");
		 resultMap.put("sign","success");
		 return JsonUtil.object2json(resultMap);
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
	public String getNum(Row row){
		//row.put("name", "����");
		row.put("household", WbfConstants.HOUSEHOLD_SHENZHEN);
		String  number = identifierFacade.getSerialNumber(row);
		return number;
	}

}


