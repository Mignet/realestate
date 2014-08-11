package com.szhome.cq.sqlfileexport;

/**
 * 什么地方用过相应的sql
 * @author Mignet
 * 
 */
public class SqlUserVo {

	
	/**
	 * 文件名
	 */
	private String file;
	/**
	 * 行数
	 */
	private int lineNum;
	/**
	 * 注释
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
		if(remark)rtn=rtn+": (注释)";
		return rtn;
	}
	
}

