/**
 * Project Name:dxtx_re
 * File Name:ImageFacade.java
 * Package Name:com.szhome.cq.business.impl
 * Date:2014-5-30����4:33:17
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.business.impl;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.szhome.commons.log.LogUtil;
import com.szhome.cq.business.BusinessException;
import com.szhome.cq.business.IImageFacade;
import com.szhome.cq.domain.model.Image;

/**
 * ͼƬ����
 */
@Component
@Transactional
@Scope("prototype")
public class ImageFacade implements IImageFacade{
	@Autowired
	private Image imageDao;
	@Autowired
	private DataSource dataSource;
	
	
	
	/**
	 * 
	 * ����ͼƬ����Ϣ
	 *
	 * @author Joyon
	 * @param image
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Image saveImage(Image image) throws BusinessException{
		if(image.getImg_id()==null){
			image.setImg_id(imageDao.getSeqId());
		}
		
		try {
			Connection conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();
			String flagSql = "select count(*) from bus_image where img_id='"
					+ image.getImg_id() + "'";
			ResultSet rs = st.executeQuery(flagSql);
			rs.next();
			int num = rs.getInt(1);
			if (num >= 1) {
				// flag = true;
				//�Ѿ�����ʱ���Ƚ����ÿգ����������ص��Ĳ���
				String sql = "UPDATE bus_image w SET w.image_data = Empty_BLOB()  WHERE w.img_id ='"+ image.getImg_id()+"'";
			st.executeUpdate(sql);
			} else {
				// �ȵ���Empty_BLOB()��������ն���
				String sql = "INSERT INTO bus_image(img_id, image_data) "
						+ "VALUES ('"
						+ image.getImg_id()
						+ "',Empty_BLOB())";
				st.executeUpdate(sql);
			}
			String sql = "select * from bus_image where img_id = '"
					+ image.getImg_id() + "' for update";
			// ���еķ�ʽ����
			rs = st.executeQuery(sql);
			if (rs.next()) {
				try {
					Object obj = rs.getBlob("image_data");
		            Class clazz = obj.getClass();
		            Method method = clazz.getMethod("getBinaryOutputStream", new Class[]{});
					OutputStream os = (OutputStream)method.invoke(obj, new Object[]{});
					BufferedOutputStream bos = new BufferedOutputStream(os);
					
					byte[] data = (byte[]) image.getImage_data();
					
					bos.write(data,0,data.length);
					//System.out.println("ԴXML��\n"+flowcssxml.getAttributexml());
					// ������Ļ���
					bos.flush();
					bos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			conn.commit();
			conn.setAutoCommit(true);
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return image;
		//imageDao.save(image);
	}
	
	/**
	 * 
	 * ����ͼƬ
	 *
	 * @author Joyon
	 * @param image
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void updateImage(Image image) throws BusinessException{
		imageDao.update(image);
	}
	
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
	public Image getImage(Image image) throws BusinessException{
		try{
		Connection conn = dataSource.getConnection();
		Statement st = conn.createStatement();
		String sql = "select * from bus_image where img_id='"+image.getImg_id()+"'";
		ResultSet rs = st.executeQuery(sql);
      	while(rs.next()){
      		
      		Object obj = rs.getBlob("IMAGE_DATA");
            Class clazz = obj.getClass();
            Method method = clazz.getMethod("getBinaryStream", new Class[]{});
            InputStream is = (InputStream)method.invoke(obj, new Object[]{});
            int length = (int)rs.getBlob("IMAGE_DATA").length();
            byte[] buffer = new byte[length];
            is.read(buffer);
            is.close();
           // String xml = new String(buffer,"GBK");
  			//xml = xml.replaceAll("\r\n","");
  			image.setImage_data(buffer);
      	}
      	}catch (Exception e) {
			LogUtil.error("ImageFacade.getImage ��ȡͼƬ��Ϣ����:"+e.getMessage());
      	}
		return image;
	}
	
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
	public Image getImageByImgId(String imageId) throws BusinessException{
		Image image = new Image();
		image.setImg_id(imageId);
		return getImage(image);
	}
	
	
}


