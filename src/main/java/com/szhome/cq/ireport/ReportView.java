package com.szhome.cq.ireport;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.view.AbstractView;

public class ReportView extends AbstractView  {
	private static final Log logger = LogFactory.getLog(ReportView.class);
	public static final String XLS = "xls";
	public static final String PDF = "pdf";
	public static final String IMG = "img";
	public static final String CSV = "csv";
	public static final String HTML = "html";
	public static final String REPORT_NAME = "reportName";
	public static final String FORMAT = "format";
	public static final String REPORT_PRINT = "reportPrint";
	
	private static Map<String, IReportFileExporter> EXPORTER_MAP =
			new HashMap<String, IReportFileExporter>(4);
	
	static {
		EXPORTER_MAP.put(XLS, new ReportXlsExporter());
		EXPORTER_MAP.put(PDF, new ReportPdfExporter());
		EXPORTER_MAP.put(CSV, new ReportCsvExporter());
		EXPORTER_MAP.put(IMG, new ReportImgExporter());
		EXPORTER_MAP.put(HTML, new ReportHtmlExporter());
	}
	
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request,
			HttpServletResponse response) {
		String reportName = (String) model.get(REPORT_NAME);//������ļ���
		String format = (String) model.get(FORMAT);//����ĸ�ʽpdf xls .....
		ReportPrint reportPrint = (ReportPrint) model.get(REPORT_PRINT);//�����֮ǰ���ɵ��м��ļ�
		if(PDF.equals(format)){
			response.setContentType("application/pdf");
			response.setCharacterEncoding("GBK");
		}else if(IMG.equals(format)){
			response.setContentType("image/jpg");
			response.setCharacterEncoding("GBK");
		}else{
			response.setContentType("application/x-msdown;charset=GBK");
		}
//		try {
//			/* httpͷ����ļ���ò�Ʋ�֧��GBK��gbk֮��ı��룬��Ҫת��һ��
//			 * ���ⷢ�������new String(reportName.getBytes("GBK"), "iso-8859-1")�Ļ�Chrome��FF��
//			 * ���ضԻ�����ļ����������ģ�IEȴ�����룬ֻ����GBK������
//			 */
//			response.setHeader("Content-Disposition","attachment;filename=\"" + 
//					new String(reportName.getBytes("GBK"), "iso-8859-1") + "\"");
//		} catch (UnsupportedEncodingException e) {
//			logger.error(null, e);
//		}
		exportFile(reportPrint, format, response);
	}
	
	private void exportFile(ReportPrint reportPrint, String format, HttpServletResponse response) {
		try {
			_exportFile(reportPrint, format, response);
		} catch (JRException e) {
			logger.error("���������쳣", e);
		} catch (IOException e) {
			logger.error(null, e);
		}
	}
	
	private void _exportFile(ReportPrint reportPrint, String format, HttpServletResponse response) throws IOException, JRException {
		OutputStream buffOS = null;
		
		try {
			buffOS = new BufferedOutputStream(response.getOutputStream());
			IReportFileExporter exporter = null;
			
			if (EXPORTER_MAP.containsKey(format)) {
				exporter = EXPORTER_MAP.get(format);//��ȡ��Ҫ��ʽ�ĵ�����
				exporter.export(reportPrint, buffOS);
				buffOS.flush();
			} else {
				logger.error("����ı����ʽ:" + format);
			}
		} finally {
			if (buffOS != null) {
				buffOS.close();
			}
		}
	}
	
}

