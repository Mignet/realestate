package com.szhome.cq.delegate;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.plan.delegate.CommonDelegate;
import com.plan.exceptions.GeneralException;
import com.springjdbc.annotation.Page;
import com.szhome.cq.ireport.ReportPrint;
import com.szhome.cq.utils.DateUtils;
import com.szhome.cq.utils.JsonUtil;
import com.szhome.security.ext.UserInfo;

/**
 * 此代理类用于本项目，作为项目代理基类。在开发时每个项目应该编写一个此命名的类（BaseDelegate），作为项目内部的公共基类。
 * @author Mignet
 */

public class BaseDelegate extends CommonDelegate {

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	protected Map pageResultToMap(Object[] arr){
		Map<String,Object> map = new HashMap<String,Object>();
		//记录了查询总数
		map.put("total", arr[1]);
		//记录了当前页的数据
		map.put("rows", arr[0]);
		
		return map;
	}
	protected UserInfo getOperatorInfo() throws GeneralException{
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		return getUserInfoFromSession(request);
	}
	protected UserInfo getUserInfoFromSession(HttpServletRequest request) 
			throws GeneralException
	{
		UserInfo userInfo = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			userInfo = (UserInfo)session.getAttribute("userInfo");
		}
		if ((session == null) || (userInfo == null)) {
			throw new GeneralException("PLAT-0001", "未登录、或登录超时。", null);
		}
		return userInfo;
	}
	
	protected String setActionJsonObject(String str) {
		return str;
	}
	protected String pageResultToJson(Page<?> page){
		Map<String,Object> map = new HashMap<String,Object>();
		if(page!=null){
			//记录了查询总数
			map.put("total", page.getTotalSize());
			//记录了当前页的数据
			map.put("rows", page.getList());
		}
		return JsonUtil.map2json(map);
	}
	/**
	 * resultToPDF:(导出PDF文档). <br/>
	 *
	 * @author dxtx
	 * @param jasperFilename 模版名称
	 * @param Filename 导出文件名
	 * @param datas List<Map> 数据来源
	 * @return
	 * @since JDK 1.6
	 */
	protected ModelAndView resultToPDF(String reportName,String jasperFilePath,String jrxmlFilePath,Map<String,Object> parameters,List<Map<String,Object>> datas)throws JRException{
		//定义JasperPrint对象
		JasperPrint jasperPrint = null;
		//定义ModelAndView对象
		ModelAndView mv = null;
		//定义jasper打印报表集合
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		//报表打印对象声明
		ReportPrint reportPrint = new ReportPrint();
		//获得request对象
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//获得容器上下文servletContext对象
		ServletContext servletContext =request.getSession().getServletContext();
		parameters.put("printdate", DateUtils.date2String(new Date(), DateUtils.DATE_FORMAT_DAY));
		parameters.put("printdatetime", DateUtils.date2String(new Date(), DateUtils.DATE_FORMAT_DEFAULT));
		parameters.put("SUBREPORT_DIR", servletContext.getRealPath("/report"));
		
		try {
			JRDataSource dataSource = new JRBeanCollectionDataSource(datas);
			File file = new File(jasperFilePath);
			if(!file.exists()){
			   JasperReport report = JasperCompileManager.compileReport(jrxmlFilePath);
			   jasperPrint = JasperFillManager.fillReport(report,parameters,dataSource);
			}else{
				jasperPrint = JasperFillManager.fillReport(jasperFilePath,parameters,dataSource);	
			}
			jasperPrintList.add(jasperPrint);
			reportPrint.setJasperList(jasperPrintList);		
		} catch (JRException e) {
			throw e;
		}
		parameters.put("reportPrint",reportPrint);  
		//报表设置
		parameters.put("reportName",reportName);
		parameters.put("format","pdf");
		mv = new ModelAndView(reportName+".report",parameters);
		return mv;
	}
	
	/**
	 * resultToPDF:(导出PDF文档). <br/>
	 *
	 * @author dxtx
	 * @param jasperFilename 模版名称
	 * @param Filename 导出文件名
	 * @param datas List<Map> 数据来源
	 * @return
	 * @since JDK 1.6
	 */
	protected String resultToPDF(String jasperFilename,String Filename,List<Map> datas) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		String jrxmlPath = request.getSession().getServletContext().getRealPath("/report/"+jasperFilename+".jrxml");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", request.getSession().getServletContext().getRealPath("/report"));
		try {
			JRDataSource dataSource = new JRBeanCollectionDataSource(datas);
			JasperReport report = JasperCompileManager.compileReport(jrxmlPath);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report,parameters, dataSource);
//			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream ouputStream = response.getOutputStream();
			// 设置相应参数，以附件形式保存PDF
			response.setContentType("application/pdf");
			response.setCharacterEncoding("GBK");
			/*response.setHeader("Content-Disposition", "attachment; filename=\""
					+ URLEncoder.encode(Filename, "GBK") + ".pdf\"");*/
			// 使用JRPdfExproter导出器导出pdf
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			exporter.exportReport();// 导出
			ouputStream.close();// 关闭流
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * resultToPDF:(导出PDF文档). <br/>
	 *
	 * @author dxtx
	 * @param jasperFilePath 编译后的运行文件路径
	 * @param jrxmlFilePath  模板文件路径
	 * @param parameters<Map> 参数来源
	 * @param datas List<Map> 数据来源
	 * @return
	 * @since JDK 1.6
	 */
	protected String resultToPDF(String jasperFilePath,String jrxmlFilePath,Map<String,Object> parameters,List<Map<String,Object>> datas) {
		//String jasperPath = ServletActionContext.getServletContext().getRealPath("/report/"+jasperFilename+".jasper");
		//String jrxmlPath = ServletActionContext.getServletContext().getRealPath("/report/source/"+jasperFilename+".jrxml");
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		parameters.put("printdate", DateUtils.date2String(new Date(), DateUtils.DATE_FORMAT_DAY));
		parameters.put("printdatetime", DateUtils.date2String(new Date(), DateUtils.DATE_FORMAT_DEFAULT));
		parameters.put("SUBREPORT_DIR", request.getSession().getServletContext().getRealPath("/report"));
		try {
			JRDataSource dataSource = new JRBeanCollectionDataSource(datas);
			JasperPrint jasperPrint = null;
			File file = new File(jasperFilePath);
			if(!file.exists()){
			   JasperReport report = JasperCompileManager.compileReport(jrxmlFilePath);
			   jasperPrint = JasperFillManager.fillReport(report,parameters,dataSource);
			}else{
				jasperPrint = JasperFillManager.fillReport(jasperFilePath,parameters,dataSource);	
			}
//			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream ouputStream = response.getOutputStream();
			// 设置相应参数，以附件形式保存PDF
			response.setContentType("application/pdf");
			response.setCharacterEncoding("GBK");
			// 使用JRPdfExproter导出器导出pdf
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			exporter.exportReport();// 导出
			ouputStream.close();// 关闭流
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * resultToPDF:(导出PDF文档). <br/>
	 *
	 * @author dxtx
	 * @param jasperFilename 模版名称
	 * @param Filename 导出文件名
	 * @param datas List<Map> 数据来源
	 * @return
	 * @since JDK 1.6
	 */
	protected String resultToPDF(String jasperFilename,String Filename,Map parameters,Connection conn,HttpServletRequest request,HttpServletResponse response) {
//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		String jrxmlPath = request.getSession().getServletContext().getRealPath("/report/"+jasperFilename+".jrxml");
		//Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("SUBREPORT_DIR", request.getSession().getServletContext().getRealPath("/report"));
		try {
			//JRDataSource dataSource = new JRBeanCollectionDataSource(datas);
			JasperReport report = JasperCompileManager.compileReport(jrxmlPath);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report,parameters, conn);
//			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream ouputStream = response.getOutputStream();
			// 设置相应参数，以附件形式保存PDF
			response.setContentType("application/pdf");
			response.setCharacterEncoding("GBK");
			/*response.setHeader("Content-Disposition", "attachment; filename=\""
					+ URLEncoder.encode(Filename, "GBK") + ".pdf\"");*/
			// 使用JRPdfExproter导出器导出pdf
			JRPdfExporter exporter = new JRPdfExporter();
//			JRHtmlExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			exporter.exportReport();// 导出
			ouputStream.flush();
			ouputStream.close();// 关闭流
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 输出ireport 图片
	 * @param jasperPrintList
	 * @return
	 */
	protected String resultListToImage(List jasperPrintList){
		try {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
//			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream ouputStream = response.getOutputStream();
			response.setContentType("image/gif");
			response.setCharacterEncoding("GBK");
			
			
			JasperPrint jasperPrint = (JasperPrint) jasperPrintList.get(0);
			JRGraphics2DExporter exporter;
				
			
//			JasperPrint tmp = null;
//				for(int i=0;i<jasperPrintList.size();i++){
//					 tmp = (JasperPrint) jasperPrintList.get(i);
//					 tmpheight+=tmp.getPageHeight()*4;
//				}
				exporter = new JRGraphics2DExporter();
			//创建graphics输出器  
			//创建一个影像对象  
				
			BufferedImage bufferedImage = new BufferedImage(jasperPrint.getPageWidth()*4, jasperPrint.getPageHeight()*4, BufferedImage.TYPE_INT_RGB);  
			System.out.println(jasperPrint.getPageWidth()*4+"_______________________-"+jasperPrint.getPageHeight()*4);
			//取graphics  
			Graphics2D g = (Graphics2D) bufferedImage.getGraphics();  
			//设置相应参数信息  
			exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, g);  
			exporter.setParameter(JRGraphics2DExporterParameter.ZOOM_RATIO, Float.valueOf(4));  
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);  
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			exporter.exportReport();  
			g.dispose();//释放资源信息  
			int tmpwidth = 1200;//jasperPrint.getPageWidth()*4;//1200;
			int tmpheight = 600;//jasperPrint.getPageHeight()*4;//600;
			  BufferedImage tagImage = new BufferedImage(tmpwidth, tmpheight,
		                BufferedImage.TYPE_INT_RGB);
		        // tagImage.getGraphics().drawImage(srcImage, 0, 0, null);
		        tagImage.getGraphics().drawImage(bufferedImage.getScaledInstance(tmpwidth, tmpheight,BufferedImage.SCALE_SMOOTH), 0, 0, null);
			//bufferedImage.setRGB(1000, 500,  BufferedImage.TYPE_INT_RGB);
			ImageIO.write(tagImage,"GIF",response.getOutputStream());
			
			
			
//			ChartUtilities.writeChartAsJPEG(
//					response.getOutputStream(), // 输出到哪个输出流
//							1, // JPEG图片的质量，0~1之间
//							chart, // 统计图标对象
//							800, // 宽
//							600,// 宽
//							null // ChartRenderingInfo 信息
//							);

			//ouputStream.flush();    
			//ouputStream.close();
			//这里的bufferedImage就是最终的影像图像信息,可以通过这个对象导入到cm中了.  
			//ImageIO.write(bufferedImage, "JPEG", new File("d:/aa.jpg"));  
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected String resultListToPDF(List jasperPrintList ) {
		
		try {
			HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
//			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream ouputStream = response.getOutputStream();
			// 设置相应参数，以附件形式保存PDF
			response.setContentType("application/pdf");
			response.setCharacterEncoding("GBK");
			/*
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ URLEncoder.encode("doc", "GBK") + ".pdf\"");*/
			// 使用JRPdfExproter导出器导出pdf
			JRPdfExporter exporter = new JRPdfExporter();
//			new JR
//			JRHtmlExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,jasperPrintList);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			exporter.exportReport();// 导出
			ouputStream.flush();    
			ouputStream.close();// 关闭流
			/*
			ByteArrayOutputStream baos = new ByteArrayOutputStream();  
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
			
			exporter.exportReport();
			byte[] bytes= baos.toByteArray();//得到这个流 
			response.setContentType("application/pdf");  
			response.setContentLength(bytes.length);  
			ServletOutputStream ouputStream = response.getOutputStream();  
			ouputStream.write(bytes, 0, bytes.length);  
			ouputStream.flush();  
			ouputStream.close();  
			*/
			
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected String resultToXLS(JasperPrint jasperPrint,String Filename) {
		HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		try {
			OutputStream ouputStream = response.getOutputStream();
			// 设置相应参数，以附件形式保存XLS  
	        response.setContentType("application/vnd.excel");  
			response.setCharacterEncoding("GBK");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ URLEncoder.encode(Filename, "GBK") + ".xls\"");
			// 使用JRXlsExporter导出器导出xls  
	        JRXlsExporter exporter = new JRXlsExporter();  
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
	        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);  
	        exporter.exportReport();// 导出  
	        ouputStream.close();// 关闭流 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

