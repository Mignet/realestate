package com.szhome.cq.ireport;

import java.io.OutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class ReportPdfExporter implements IReportFileExporter {

	public void export(ReportPrint reportPrint, OutputStream os) throws JRException {
		JRPdfExporter exporter = new JRPdfExporter();
		
		if(reportPrint.getJasperPrint()!=null){
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, reportPrint.getJasperPrint());
		}else if(reportPrint.getJasperList().size()>0){
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,reportPrint.getJasperList());
		}
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
		exporter.exportReport();
	}

}

