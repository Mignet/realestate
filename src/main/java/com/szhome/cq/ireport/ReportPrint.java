package com.szhome.cq.ireport;

import java.util.List;

import net.sf.jasperreports.engine.JasperPrint;

public class ReportPrint {
	JasperPrint jasperPrint = null;
	List<JasperPrint> jasperList = null;

	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}

	public void setJasperPrint(JasperPrint jasperPrint) {
		this.jasperPrint = jasperPrint;
	}

	public List<JasperPrint> getJasperList() {
		return jasperList;
	}

	public void setJasperList(List<JasperPrint> jasperList) {
		this.jasperList = jasperList;
	}
	
	
	
}

