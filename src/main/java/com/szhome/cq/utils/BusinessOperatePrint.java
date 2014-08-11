package com.szhome.cq.utils;

public class BusinessOperatePrint extends BusinessOperateReturn {

	private String printType;
	private Object printvo;
	
	public BusinessOperatePrint(String message, String returnPath, int showButtonType,String printType,Object printvo) {
		super("²Ù×÷³É¹¦",message,returnPath,showButtonType);
		this.printType=printType;
		this.printvo=printvo;
	}

	public String getPrintType() {
		return printType;
	}

	public void setPrintType(String printType) {
		this.printType = printType;
	}

	public Object getPrintvo() {
		return printvo;
	}

	public void setPrintvo(Object printvo) {
		this.printvo = printvo;
	}
	
	
	
}
	
