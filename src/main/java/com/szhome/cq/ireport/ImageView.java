package com.szhome.cq.ireport;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.view.AbstractView;

public class ImageView extends AbstractView  {
	private static final Log logger = LogFactory.getLog(ImageView.class);
	
	
	private static  final String IMAGE_NAME = "image";
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request,
			HttpServletResponse response) {
		byte[] imageData = (byte[]) model.get(IMAGE_NAME);//ͼƬ����������
			response.setContentType("image/gif");
			response.setCharacterEncoding("GBK");
			ByteArrayInputStream in = new ByteArrayInputStream(imageData);    //��b��Ϊ��������
			BufferedImage bufferImage = null;
			try {
				bufferImage = ImageIO.read(in);
				OutputStream out = response.getOutputStream();
				
				ImageIO.write(bufferImage,"GIF",out);
				
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
				
			}     //��in��Ϊ����������ȡͼƬ��
	}
	
	
}

