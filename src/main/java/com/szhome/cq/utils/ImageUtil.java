/**
 * Project Name:dxtx_re
 * File Name:ImageUtil.java
 * Package Name:com.szhome.cq.utils
 * Date:2014-6-17����11:58:00
 * Copyright (c) 2014, DXTX All Rights Reserved.
 *
*/

package com.szhome.cq.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * ImageUtil ͼ�񹤾���. 
 * 
 * @author   dxtx
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ImageUtil {
	
	
	/**
	 * Scale <b>srcImg</b> and write to <b>os</b>.
	 *
	 * @param srcImg
	 * @param os
	 * 			OutputStream
	 * @param width
	 * @param height
	 * @param suffix
	 *			ͼƬ�ĸ�ʽ gif JPG ��png  
	 * @since JDK 1.6
	 */
	public static void createThumbnail(BufferedImage srcImg, OutputStream os, int width,int height, String suffix) {
		try {
	        double Ratio = 0.0;  
	        Image Itemp = srcImg.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);  
	        if ((srcImg.getHeight() > width) || (srcImg.getWidth() > height)) {  
	            if (srcImg.getHeight() > srcImg.getWidth())  
	                Ratio = (double)width / srcImg.getHeight();  
	            else  
	                Ratio = (double)height / srcImg.getWidth();  
	        }  
	        AffineTransformOp op = new AffineTransformOp(AffineTransform  
	                .getScaleInstance(Ratio, Ratio), null);  
	        Itemp = op.filter(srcImg, null);  
	        ImageIO.write((BufferedImage) Itemp, suffix, os);  

			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	 /** 
     * ��ԭͼƬ��BufferedImage������������ͼ 
     * @param source��ԭͼƬ��BufferedImage���� 
     * @param targetW:����ͼ�Ŀ� 
     * @param targetH:����ͼ�ĸ� 
	 * @param os 
     */  
    public static void resize(BufferedImage source,OutputStream os, int targetW,int targetH,boolean equalProportion){  
        int type=source.getType();  
        BufferedImage target=null;  
        double sx=(double)targetW/source.getWidth();  
        double sy=(double)targetH/source.getHeight();  
        //������ʵ����targetW��targetH��Χ��ʵ�ֵȱ���������  
        if(equalProportion){  
            if(sx>sy){  
                sx=sy;  
                targetW=(int)(sx*source.getWidth());  
            }else{  
                sy=sx;  
                targetH=(int)(sx*source.getHeight());  
            }  
        }  
        if(type==BufferedImage.TYPE_CUSTOM){  
            ColorModel cm=source.getColorModel();  
            WritableRaster raster=cm.createCompatibleWritableRaster(targetW,targetH);  
            boolean alphaPremultiplied=cm.isAlphaPremultiplied();  
            target=new BufferedImage(cm,raster,alphaPremultiplied,null);  
        }else{  
            target=new BufferedImage(targetW,targetH,type);  
            Graphics2D g=target.createGraphics();  
            g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);  
            g.drawRenderedImage(source,AffineTransform.getScaleInstance(sx,sy));  
            g.dispose();  
        }  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
		try {
			encoder.encode(target);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        //return target;  
    }  
}


