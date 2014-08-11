/**
 * Project Name:dxtx_re
 * File Name:IFileRelFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-6-4����6:00:19
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import java.util.List;

import com.szhome.cq.domain.model.FileRel;

/**
 * �ļ����������
 * Date:     2014-6-4 ����6:00:19 <br/>
 * @author   Joyon
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IFileRelFacade {
	/**
	 * 
	 * �����ļ�������
	 *
	 * @author Joyon
	 * @param fileRel
	 * @since JDK 1.6
	 */
	public void saveFileRel(FileRel fileRel) throws BusinessException;
	
	/**
	 * 
	 * �����ļ�������
	 *
	 * @author Joyon
	 * @param fileRel
	 * @since JDK 1.6
	 */
	public void updateFileRel(FileRel fileRel) throws BusinessException;
	
	/**
	 * 
	 * ͨ��ʵ���ȡ�ļ�����������
	 *
	 * @author Joyon
	 * @param fileRel
	 * @return
	 * @since JDK 1.6
	 */
	public FileRel getFileRel(FileRel fileRel) throws BusinessException;
	
	/**
	 * 
	 * ͨ��id��ȡ�ļ�������
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
	 * ͨ�����ID��ȡ�ļ���������
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
	 * ɾ���ļ�������
	 *
	 * @author Joyon
	 * @param fileRel
	 * @since JDK 1.6
	 */
	public void deleteFileRel(FileRel fileRel) throws BusinessException;
	
	/**
	 * 
	 * ɾ���ļ�������
	 *
	 * @author Joyon
	 * @param id
	 * @since JDK 1.6
	 */
	public void deleteFileRelById(String id) throws BusinessException;
	
	/**
	 * 
	 * 
	 * ͨ���ϴ�����ID�� ���ID��ȡΩһ�ļ�����������
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


