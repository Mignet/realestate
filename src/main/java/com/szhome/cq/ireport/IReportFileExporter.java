package com.szhome.cq.ireport;

import java.io.OutputStream;

import net.sf.jasperreports.engine.JRException;

public interface IReportFileExporter {
	public void export(ReportPrint reportPrint, OutputStream os) throws JRException;
}

