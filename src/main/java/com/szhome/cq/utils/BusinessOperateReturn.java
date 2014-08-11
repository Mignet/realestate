package com.szhome.cq.utils;

public  class BusinessOperateReturn {

	/******显示按钮的常量********/
	//显示返回和关闭按钮
	public static final int BOTH = 0;
	//显示返回按钮
	public static final int RETURN = 1;
	//显示关闭按钮
	public static final int CLOSE = 2;
	//不显示返回和关闭按钮
	public static final int NOSEE = -1;

	//成功后提示标题
	protected String title = "操作结束";
	//成功后提示内容
	protected String message = "你的信息处理结束";
	//返回地址
	protected String returnPath = "/";
	//显示按钮的类型，默认显示返回按钮，0 显示返回和关闭按钮；1 显示返回按钮；2 显示关闭按钮
	protected int showButtonType = RETURN;
	
	public BusinessOperateReturn() {

	}

	public BusinessOperateReturn(String title, String message, String returnPath, int showButtonType) {
		this.title = title;
		this.message = message;
		this.returnPath = returnPath;
		this.showButtonType = showButtonType;
	}
	@Deprecated
	public BusinessOperateReturn(String message, String returnPath) {
		this.message = message;
		this.returnPath = returnPath;
	}
	@Deprecated
	public BusinessOperateReturn(String returnPath) {
		this.returnPath = returnPath;
	}
	@Deprecated
	public BusinessOperateReturn(int showButton) {
		this.showButtonType = showButton;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReturnPath() {
		return returnPath;
	}

	public void setReturnPath(String returnPath) {
		this.returnPath = returnPath;
	}

	public int getShowButtonType() {
		return showButtonType;
	}

	public void setShowButtonType(int showButtonType) {
		this.showButtonType = showButtonType;
	}

}

