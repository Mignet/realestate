/**
 * Project Name:dxtx_re
 * File Name:IIdentifier.java
 * Package Name:com.szhome.cq.business
 * Date:2013-12-24����8:11:58
 * Copyright (c) 2013, dxtx All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.List;
import java.util.Map;

import com.plan.commons.Row;
import com.plan.exceptions.GeneralException;
import com.szhome.cq.domain.model.Identifier;
import com.szhome.cq.domain.model.IdentifierRule;
import com.szhome.cq.utils.WbfConstants;

/**
 * ClassName:IIdentifier <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2013-12-24 ����8:11:58 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IIdentifierFacade {
	
	/**
	 * 
	 * ��ȡ���treeData
	 *
	 * @author Joyon
	 * @param list
	 * @return
	 * @since JDK 1.6
	 */
	public List getIdentifierTree();
	
	/**
	 * 
	 * ������������Ϣ
	 *
	 * @author Joyon
	 * @param identifier
	 * @since JDK 1.6
	 */
	public Identifier saveIdentifer(Identifier identifier) throws GeneralException;
	
	/**
	 * 
	 * ���±��������Ϣ
	 *
	 * @author Joyon
	 * @param identifier
	 * @since JDK 1.6
	 */
	public void updateIdentifier(Identifier identifier) throws GeneralException;
	
	
	/**
	 * 
	 * ɾ�������Ϣ
	 *
	 * @author Joyon
	 * @param identifier
	 * @throws GeneralException
	 * @since JDK 1.6
	 */
	public void deleteIdentifier(Identifier identifier) throws GeneralException;
	
	/**
	 * 
	 * ��ȡ�����Ϣ
	 *
	 * @author Joyon
	 * @param identifier  ���ʵ��  ����Ҫ������
	 * @return
	 * @since JDK 1.6
	 */
	public Identifier getIdentifier(Identifier identifier) throws GeneralException;
	
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
	public Identifier getIdentifierById(String id) throws GeneralException;
	

	/**
	 * 
	 * ��ȡ���
	 *
	 * @author Joyon
	 * @param row (name���������    ����    
	 *       	   area����  ���ֵ������ 
	 *       	   household:���� (�����) WbfConstants.HOUSEHOLD_OTHER
	 *            )   
	 * @return  ���
	 * @since JDK 1.6
	 */
	public String getSerialNumber(Row row);
	
	/**
	 * 
	 * ɾ������
	 *
	 * @author Joyon
	 * @param squenceName ������
	 * @return
	 * @since JDK 1.6
	 */
	public boolean deleteSquence(String squenceName);
	
	/**
	 * 
	 * ��������
	 *
	 * @author Joyon
	 * @param squenceName ������
	 * @return
	 * @since JDK 1.6
	 */
	public boolean createSquence(String squenceName);
	
}


