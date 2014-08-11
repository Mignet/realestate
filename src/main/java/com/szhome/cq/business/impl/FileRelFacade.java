/**
 * Project Name:dxtx_re
 * File Name:FileRelFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-6-4下午6:00:36
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.IFileRelFacade;
import com.szhome.cq.domain.model.FileRel;

/**
 * 文件关联  服务
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
@Transactional
@Scope("prototype")
public class FileRelFacade implements IFileRelFacade{
	@Autowired
	private FileRel fileRelDao;
	
	/**
	 * 
	 * 保存文件关联表
	 *
	 * @author Joyon
	 * @param fileRel
	 * @since JDK 1.6
	 */
	public void saveFileRel(FileRel fileRel) throws BusinessException{
		if(fileRel==null){
			return ;
		}
		if(fileRel.getId()==null){
			fileRel.setId(fileRelDao.getSeqId());
		}
		fileRelDao.save(fileRel);
	}
	
	/**
	 * 
	 * 更新文件关联表
	 *
	 * @author Joyon
	 * @param fileRel
	 * @since JDK 1.6
	 */
	public void updateFileRel(FileRel fileRel) throws BusinessException{
		fileRelDao.update(fileRel);
	}
	
	/**
	 * 
	 * 通过实体获取文件关联表数据
	 *
	 * @author Joyon
	 * @param fileRel
	 * @return
	 * @since JDK 1.6
	 */
	public FileRel getFileRel(FileRel fileRel) throws BusinessException{
		return fileRelDao.get(fileRel);
	}
	
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
	public FileRel getFileRelById(String id) throws BusinessException{
		FileRel fileRel = new FileRel();
		fileRel.setId(id);
		return fileRelDao.get(fileRel);
	}
	
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
	public List  getFileRelListByOpinionId(String opinionId)  throws BusinessException{
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("opinionId",opinionId);
		List list = fileRelDao.getAll(" where opinion_id=:opinionId",paraMap);
		return list;
	}
	
	/**
	 * 
	 * 删除文件关联表
	 *
	 * @author Joyon
	 * @param fileRel
	 * @since JDK 1.6
	 */
	public void deleteFileRel(FileRel fileRel) throws BusinessException{
		fileRelDao.delete(fileRel);
	}
	
	/**
	 * 
	 * 删除文件关联表
	 *
	 * @author Joyon
	 * @param id
	 * @since JDK 1.6
	 */
	public void deleteFileRelById(String id) throws BusinessException{
		FileRel fileRel = new FileRel();
		fileRel.setId(id);
		fileRelDao.delete(fileRel);
	}
	
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
	public FileRel getFileRelByOpinionidAndUploadid(String opinionId,String uploadId) throws BusinessException{
		Map paraMap = new HashMap();
		paraMap.put("opinion_id", opinionId);
		paraMap.put("uploadId", uploadId);
		return fileRelDao.get("where opinion_id=:opinion_id and uploadId=:uploadId", paraMap);
	}
}


