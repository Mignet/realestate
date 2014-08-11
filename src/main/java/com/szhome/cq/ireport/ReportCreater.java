package com.szhome.cq.ireport;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReportCreater {
	
	private static ReportCreater instance = null;
	private ReportCreater(){}
	public static ReportCreater getInstance() {
	    if(instance==null)
	    instance=new ReportCreater();
	    return instance;
	}
	
	private static final Log logger = LogFactory.getLog(ReportCreater.class);
	private String jasperReportPath = null;//�����ģ���ļ����·�������classpath��ͨ��springע�룩
	/**
	 * jasperDesignMap��Ϊһ���������洢������JasperReportģ��
	 */
	private Map<String, JasperReport> jasperDesignMap = new ConcurrentHashMap<String, JasperReport>();
	
	public void resetJasperDesignCache() {
		jasperDesignMap.clear();
	}
	
	/**
	 * controller���ø÷���������ReportPrint����
	 */
	public ReportPrint createReport(final String reportKey, final ResultSet rs, Map<String, Object> reportParams) throws ReportException {
		try {
			return _createReport(reportKey, rs, reportParams);
		} catch (JRException e) {
			logger.error(null, e);
			throw new ReportException("�����������" + reportKey);
		}
	}
	
	/**
	 * controller���ø÷���������ReportPrint����
	 */
	public ReportPrint createReport(final String reportKey, Map<String, Object> reportParams,final Connection conn) throws ReportException {
		try {
			JasperReport jasperReport = getJasperReport(reportKey);		
			ReportPrint reportPrint = new ReportPrint();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParams, conn);
			reportPrint.setJasperPrint(jasperPrint);
			return reportPrint;
		} catch (JRException e) {
			logger.error(null, e);
			throw new ReportException("�����������" + reportKey);
		}
	}
	
	private ReportPrint _createReport(final String reportKey, final ResultSet rs, Map<String, Object> reportParams) throws ReportException, JRException {
		JasperReport jasperReport = getJasperReport(reportKey);		
		ReportPrint reportPrint = new ReportPrint();
		JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParams, resultSetDataSource);
		reportPrint.setJasperPrint(jasperPrint);
		
		return reportPrint;
	}
	
	private JasperReport getJasperReport(final String reportKey) {
		try {
			return _getJasperReport(reportKey);
		} catch (IOException e) {
			logger.error(null, e);
			throw new ReportException("�ر��ļ����쳣:" + reportKey);
		} catch (JRException e) {
			logger.error(null, e);
			throw new ReportException("���������쳣:" + reportKey);
		}
	}
	
	private JasperReport _getJasperReport(final String reportKey) throws IOException, JRException {
		JasperReport jasperReport = null;
		if (jasperDesignMap.containsKey(reportKey)) {
			jasperReport = jasperDesignMap.get(reportKey);
		} else {
			jasperReport = getJasperReportFromFile(reportKey);
			jasperDesignMap.put(reportKey, jasperReport);
		}
		
		return jasperReport;
	}
	
	/**
	 * ��ģ���ļ�������ģ�����
	 */
	private JasperReport getJasperReportFromFile(final String reportKey)  throws IOException, JRException {
		String jrxmlPath = jasperReportPath + "/" + reportKey + ".jrxml";//ͼʡ��ֻ֧��jrxml��
//		InputStream jasperFileIS = null;
//		JasperReport jasperReport = null;
		JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlPath);
//		try {
//			jasperFileIS = this.getClass().getClassLoader().getResourceAsStream(filePath);
//			if (jasperFileIS == null) {
//				throw new ReportException("�����ļ�������:" + filePath);
//			}
//			
//			JasperDesign jasperDesign = JRXmlLoader.load(jasperFileIS);
//			jasperReport = JasperCompileManager.compileReport(jasperDesign);
//		} finally {
//			if (jasperFileIS != null) {
//				jasperFileIS.close();
//			}
//		}
		
		return jasperReport;
	}
	
	public String getJasperReportPath() {
		return jasperReportPath;
	}
	
	public void setJasperReportPath(String jasperReportPath) {
		this.jasperReportPath = jasperReportPath;
	}
	
	public static void main(String[] argv) {
	
	}
	
}

