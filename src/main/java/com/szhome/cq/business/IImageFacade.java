/**
 * Project Name:dxtx_re
 * File Name:IImageFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-5-30����4:31:59
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import com.szhome.cq.domain.model.Image;

/**
 * ͼƬ����	 
 */
public interface IImageFacade {
	
	/**
	 * 
	 * ����ͼƬ����Ϣ
	 *
	 * @author Joyon
	 * @param image
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Image saveImage(Image image) throws BusinessException;
	
	/**
	 * 
	 * ����ͼƬ
	 *
	 * @author Joyon
	 * @param image
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void updateImage(Image image) throws BusinessException;
	
	/**
	 * 
	 * ��ȡͼƬ
	 *
	 * @author Joyon
	 * @param image
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Image getImage(Image image) throws BusinessException;
	
	/**
	 * 
	 * ͨ��ͼƬID  ��ȡͼƬ
	 *
	 * @author Joyon
	 * @param imageId
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Image getImageByImgId(String imageId) throws BusinessException;
}


