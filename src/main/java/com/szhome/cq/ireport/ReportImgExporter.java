package com.szhome.cq.ireport;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ReportImgExporter implements IReportFileExporter {

	public void export(ReportPrint reportPrint, OutputStream os) throws JRException {
		List<JasperPrint> jasperPrintList = reportPrint.getJasperList();
		JasperPrint jasperPrint = (JasperPrint) jasperPrintList.get(0);
		JRGraphics2DExporter exporter = new JRGraphics2DExporter();
		//创建graphics输出器  
		//创建一个影像对象  
		int targetW = 1200;//jasperPrint.getPageWidth()*4;//1200;
		int targetH = 600;//jasperPrint.getPageHeight()*4;//600;
		//确定缩放比例
		float zoom_ratio = 4.0f;
		BufferedImage bufferedImage = new BufferedImage(jasperPrint.getPageWidth()*2, jasperPrint.getPageHeight()*2, BufferedImage.TYPE_INT_RGB);  
		System.out.println("Page width:"+jasperPrint.getPageWidth()*4+"______________Page height:"+jasperPrint.getPageHeight()*4);
		/*float sx=(float)jasperPrint.getPageWidth()/targetW;  
        float sy=(float)jasperPrint.getPageHeight()/targetH;  
        //实现在targetW，targetH范围内等比例的缩放  
        if(true){  
            if(sx>sy){  
            	zoom_ratio = sx=sy;  
            }else{  
            	zoom_ratio = sy=sx;  
            }  
        } */
		/*if ((bufferedImage.getHeight() > width) || (bufferedImage.getWidth() > height)) {  
            if (bufferedImage.getHeight() > bufferedImage.getWidth())  
            	zoom_ratio = (float)width / bufferedImage.getHeight();  
            else  
            	zoom_ratio = (float)height / bufferedImage.getWidth();  
        }  */
		//取graphics  
		Graphics2D g = (Graphics2D) bufferedImage.getGraphics();  
		//设置相应参数信息  
		exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, g);  
		exporter.setParameter(JRGraphics2DExporterParameter.ZOOM_RATIO, 2.f);  
		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);  
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
		exporter.exportReport();  
		g.dispose();//释放
		
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
		try {
			encoder.encode(bufferedImage);
		} catch (Exception e) {
			throw new JRException(e.getMessage());
		}
		/*try {
			ImageIO.write(tagImage,"GIF",os);
		} catch (IOException e) {
			throw new JRException(e.getMessage());
		}*/
	}

}

