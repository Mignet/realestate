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
 * �˴��������ڱ���Ŀ����Ϊ��Ŀ������ࡣ�ڿ���ʱÿ����ĿӦ�ñ�дһ�����������ࣨBaseDelegate������Ϊ��Ŀ�ڲ��Ĺ������ࡣ
 * @author Mignet
 */

public class BaseDelegate extends CommonDelegate {

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	protected Map pageResultToMap(Object[] arr){
		Map<String,Object> map = new HashMap<String,Object>();
		//��¼�˲�ѯ����
		map.put("total", arr[1]);
		//��¼�˵�ǰҳ������
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
			throw new GeneralException("PLAT-0001", "δ��¼�����¼��ʱ��", null);
		}
		return userInfo;
	}
	
	protected String setActionJsonObject(String str) {
		return str;
	}
	protected String pageResultToJson(Page<?> page){
		Map<String,Object> map = new HashMap<String,Object>();
		if(page!=null){
			//��¼�˲�ѯ����
			map.put("total", page.getTotalSize());
			//��¼�˵�ǰҳ������
			map.put("rows", page.getList());
		}
		return JsonUtil.map2json(map);
	}
	/**
	 * resultToPDF:(����PDF�ĵ�). <br/>
	 *
	 * @author dxtx
	 * @param jasperFilename ģ������
	 * @param Filename �����ļ���
	 * @param datas List<Map> ������Դ
	 * @return
	 * @since JDK 1.6
	 */
	protected ModelAndView resultToPDF(String reportName,String jasperFilePath,String jrxmlFilePath,Map<String,Object> parameters,List<Map<String,Object>> datas)throws JRException{
		//����JasperPrint����
		JasperPrint jasperPrint = null;
		//����ModelAndView����
		ModelAndView mv = null;
		//����jasper��ӡ������
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		//�����ӡ��������
		ReportPrint reportPrint = new ReportPrint();
		//���request����
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//�������������servletContext����
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
		//��������
		parameters.put("reportName",reportName);
		parameters.put("format","pdf");
		mv = new ModelAndView(reportName+".report",parameters);
		return mv;
	}
	
	/**
	 * resultToPDF:(����PDF�ĵ�). <br/>
	 *
	 * @author dxtx
	 * @param jasperFilename ģ������
	 * @param Filename �����ļ���
	 * @param datas List<Map> ������Դ
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
			// ������Ӧ�������Ը�����ʽ����PDF
			response.setContentType("application/pdf");
			response.setCharacterEncoding("GBK");
			/*response.setHeader("Content-Disposition", "attachment; filename=\""
					+ URLEncoder.encode(Filename, "GBK") + ".pdf\"");*/
			// ʹ��JRPdfExproter����������pdf
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			exporter.exportReport();// ����
			ouputStream.close();// �ر���
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
	 * resultToPDF:(����PDF�ĵ�). <br/>
	 *
	 * @author dxtx
	 * @param jasperFilePath �����������ļ�·��
	 * @param jrxmlFilePath  ģ���ļ�·��
	 * @param parameters<Map> ������Դ
	 * @param datas List<Map> ������Դ
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
			// ������Ӧ�������Ը�����ʽ����PDF
			response.setContentType("application/pdf");
			response.setCharacterEncoding("GBK");
			// ʹ��JRPdfExproter����������pdf
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			exporter.exportReport();// ����
			ouputStream.close();// �ر���
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
	 * resultToPDF:(����PDF�ĵ�). <br/>
	 *
	 * @author dxtx
	 * @param jasperFilename ģ������
	 * @param Filename �����ļ���
	 * @param datas List<Map> ������Դ
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
			// ������Ӧ�������Ը�����ʽ����PDF
			response.setContentType("application/pdf");
			response.setCharacterEncoding("GBK");
			/*response.setHeader("Content-Disposition", "attachment; filename=\""
					+ URLEncoder.encode(Filename, "GBK") + ".pdf\"");*/
			// ʹ��JRPdfExproter����������pdf
			JRPdfExporter exporter = new JRPdfExporter();
//			JRHtmlExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			exporter.exportReport();// ����
			ouputStream.flush();
			ouputStream.close();// �ر���
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
	 * ���ireport ͼƬ
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
			//����graphics�����  
			//����һ��Ӱ�����  
				
			BufferedImage bufferedImage = new BufferedImage(jasperPrint.getPageWidth()*4, jasperPrint.getPageHeight()*4, BufferedImage.TYPE_INT_RGB);  
			System.out.println(jasperPrint.getPageWidth()*4+"_______________________-"+jasperPrint.getPageHeight()*4);
			//ȡgraphics  
			Graphics2D g = (Graphics2D) bufferedImage.getGraphics();  
			//������Ӧ������Ϣ  
			exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, g);  
			exporter.setParameter(JRGraphics2DExporterParameter.ZOOM_RATIO, Float.valueOf(4));  
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);  
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			exporter.exportReport();  
			g.dispose();//�ͷ���Դ��Ϣ  
			int tmpwidth = 1200;//jasperPrint.getPageWidth()*4;//1200;
			int tmpheight = 600;//jasperPrint.getPageHeight()*4;//600;
			  BufferedImage tagImage = new BufferedImage(tmpwidth, tmpheight,
		                BufferedImage.TYPE_INT_RGB);
		        // tagImage.getGraphics().drawImage(srcImage, 0, 0, null);
		        tagImage.getGraphics().drawImage(bufferedImage.getScaledInstance(tmpwidth, tmpheight,BufferedImage.SCALE_SMOOTH), 0, 0, null);
			//bufferedImage.setRGB(1000, 500,  BufferedImage.TYPE_INT_RGB);
			ImageIO.write(tagImage,"GIF",response.getOutputStream());
			
			
			
//			ChartUtilities.writeChartAsJPEG(
//					response.getOutputStream(), // ������ĸ������
//							1, // JPEGͼƬ��������0~1֮��
//							chart, // ͳ��ͼ�����
//							800, // ��
//							600,// ��
//							null // ChartRenderingInfo ��Ϣ
//							);

			//ouputStream.flush();    
			//ouputStream.close();
			//�����bufferedImage�������յ�Ӱ��ͼ����Ϣ,����ͨ����������뵽cm����.  
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
			// ������Ӧ�������Ը�����ʽ����PDF
			response.setContentType("application/pdf");
			response.setCharacterEncoding("GBK");
			/*
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ URLEncoder.encode("doc", "GBK") + ".pdf\"");*/
			// ʹ��JRPdfExproter����������pdf
			JRPdfExporter exporter = new JRPdfExporter();
//			new JR
//			JRHtmlExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,jasperPrintList);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			exporter.exportReport();// ����
			ouputStream.flush();    
			ouputStream.close();// �ر���
			/*
			ByteArrayOutputStream baos = new ByteArrayOutputStream();  
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
			
			exporter.exportReport();
			byte[] bytes= baos.toByteArray();//�õ������ 
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
			// ������Ӧ�������Ը�����ʽ����XLS  
	        response.setContentType("application/vnd.excel");  
			response.setCharacterEncoding("GBK");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ URLEncoder.encode(Filename, "GBK") + ".xls\"");
			// ʹ��JRXlsExporter����������xls  
	        JRXlsExporter exporter = new JRXlsExporter();  
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
	        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);  
	        exporter.exportReport();// ����  
	        ouputStream.close();// �ر��� 
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

