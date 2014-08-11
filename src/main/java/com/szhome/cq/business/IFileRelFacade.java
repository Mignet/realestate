/**
 * Project Name:dxtx_re
 * File Name:IFileRelFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-6-4下午6:00:19
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.List;

import com.szhome.cq.domain.model.FileRel;

/**
 * 文件关联表服务
 * Date:     2014-6-4 下午6:00:19 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IFileRelFacade {
	/**
	 * 
	 * 保存文件关联表
	 *
	 * @author Joyon
	 * @param fileRel
	 * @since JDK 1.6
	 */
	public void saveFileRel(FileRel fileRel) throws BusinessException;
	
	/**
	 * 
	 * 更新文件关联表
	 *
	 * @author Joyon
	 * @param fileRel
	 * @since JDK 1.6
	 */
	public void updateFileRel(FileRel fileRel) throws BusinessException;
	
	/**
	 * 
	 * 通过实体获取文件关联表数据
	 *
	 * @author Joyon
	 * @param fileRel
	 * @return
	 * @since JDK 1.6
	 */
	public FileRel getFileRel(FileRel fileRel) throws BusinessException;
	
	/**
	 * 
	 * 通过id获取文件关联表
	 *
	 * @author Joyon
	 * @param id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public FileRel getFileRelById(String id) throws BusinessException;
	
	/**
	 * 
	 * 通过意见ID获取文件关联数据
	 *
	 * @author Joyon
	 * @param opinionId
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public List  getFileRelListByOpinionId(String opinionId)  throws BusinessException;
	
	/**
	 * 
	 * 删除文件关联表
	 *
	 * @author Joyon
	 * @param fileRel
	 * @since JDK 1.6
	 */
	public void deleteFileRel(FileRel fileRel) throws BusinessException;
	
	/**
	 * 
	 * 删除文件关联表
	 *
	 * @author Joyon
	 * @param id
	 * @since JDK 1.6
	 */
	public void deleteFileRelById(String id) throws BusinessException;
	
	/**
	 * 
	 * 
	 * 通过上传附件ID和 意见ID获取惟一文件关联表数据
	 * 
	 *
	 * @author Joyon
	 * @param opinionId
	 * @param uploadId
	 * @return
	 * @since JDK 1.6
	 */
	public FileRel getFileRelByOpinionidAndUploadid(String opinionId,String uploadId) throws BusinessException;
}


