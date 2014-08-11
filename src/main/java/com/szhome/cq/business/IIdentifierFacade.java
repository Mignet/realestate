/**
 * Project Name:dxtx_re
 * File Name:IIdentifier.java
 * Package Name:com.szhome.cq.business
 * Date:2013-12-24下午8:11:58
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
 * Date:     2013-12-24 下午8:11:58 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IIdentifierFacade {
	
	/**
	 * 
	 * 获取编号treeData
	 *
	 * @author Joyon
	 * @param list
	 * @return
	 * @since JDK 1.6
	 */
	public List getIdentifierTree();
	
	/**
	 * 
	 * 保存编号配置信息
	 *
	 * @author Joyon
	 * @param identifier
	 * @since JDK 1.6
	 */
	public Identifier saveIdentifer(Identifier identifier) throws GeneralException;
	
	/**
	 * 
	 * 更新编号配置信息
	 *
	 * @author Joyon
	 * @param identifier
	 * @since JDK 1.6
	 */
	public void updateIdentifier(Identifier identifier) throws GeneralException;
	
	
	/**
	 * 
	 * 删除编号信息
	 *
	 * @author Joyon
	 * @param identifier
	 * @throws GeneralException
	 * @since JDK 1.6
	 */
	public void deleteIdentifier(Identifier identifier) throws GeneralException;
	
	/**
	 * 
	 * 获取编号信息
	 *
	 * @author Joyon
	 * @param identifier  编号实体  必须要有主键
	 * @return
	 * @since JDK 1.6
	 */
	public Identifier getIdentifier(Identifier identifier) throws GeneralException;
	
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
	public Identifier getIdentifierById(String id) throws GeneralException;
	

	/**
	 * 
	 * 获取编号
	 *
	 * @author Joyon
	 * @param row (name：编号名称    必须    
	 *       	   area：区  传字典项代码 
	 *       	   household:户籍 (深户或非深户) WbfConstants.HOUSEHOLD_OTHER
	 *            )   
	 * @return  编号
	 * @since JDK 1.6
	 */
	public String getSerialNumber(Row row);
	
	/**
	 * 
	 * 删除序列
	 *
	 * @author Joyon
	 * @param squenceName 序列名
	 * @return
	 * @since JDK 1.6
	 */
	public boolean deleteSquence(String squenceName);
	
	/**
	 * 
	 * 创建序列
	 *
	 * @author Joyon
	 * @param squenceName 序列名
	 * @return
	 * @since JDK 1.6
	 */
	public boolean createSquence(String squenceName);
	
}


