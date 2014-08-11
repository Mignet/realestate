/**
 * Project Name:dxtx_re
 * File Name:IImageFacade.java
 * Package Name:com.szhome.cq.business
 * Date:2014-5-30下午4:31:59
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.business;

import com.szhome.cq.domain.model.Image;

/**
 * 图片服务	 
 */
public interface IImageFacade {
	
	/**
	 * 
	 * 保存图片表信息
	 *
	 * @author Joyon
	 * @param image
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Image saveImage(Image image) throws BusinessException;
	
	/**
	 * 
	 * 更新图片
	 *
	 * @author Joyon
	 * @param image
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void updateImage(Image image) throws BusinessException;
	
	/**
	 * 
	 * 获取图片
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
	 * 通过图片ID  获取图片
	 *
	 * @author Joyon
	 * @param imageId
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Image getImageByImgId(String imageId) throws BusinessException;
}


