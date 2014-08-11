package com.szhome.cq.sqlfileexport;

/**
 * ʲô�ط��ù���Ӧ��sql
 * @author Mignet
 * 
 */
public class SqlUserVo {

	
	/**
	 * �ļ���
	 */
	private String file;
	/**
	 * ����
	 */
	private int lineNum;
	/**
	 * ע��
	 */
	private boolean remark;
	
	
	
	public SqlUserVo(String file, int lineNum,boolean remark) {
		this.file = file;
		this.lineNum = lineNum;
		this.remark=remark;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public int getLineNum() {
		return lineNum;
	}
	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}
	
	public boolean getRemark() {
		return remark;
	}
	public void setRemark(boolean remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		String rtn=file+":"+lineNum;
		if(remark)rtn=rtn+": (ע��)";
		return rtn;
	}
	
}

