package com.szhome.cq.utils;

public  class BusinessOperateReturn {

	/******��ʾ��ť�ĳ���********/
	//��ʾ���غ͹رհ�ť
	public static final int BOTH = 0;
	//��ʾ���ذ�ť
	public static final int RETURN = 1;
	//��ʾ�رհ�ť
	public static final int CLOSE = 2;
	//����ʾ���غ͹رհ�ť
	public static final int NOSEE = -1;

	//�ɹ�����ʾ����
	protected String title = "��������";
	//�ɹ�����ʾ����
	protected String message = "�����Ϣ�������";
	//���ص�ַ
	protected String returnPath = "/";
	//��ʾ��ť�����ͣ�Ĭ����ʾ���ذ�ť��0 ��ʾ���غ͹رհ�ť��1 ��ʾ���ذ�ť��2 ��ʾ�رհ�ť
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

