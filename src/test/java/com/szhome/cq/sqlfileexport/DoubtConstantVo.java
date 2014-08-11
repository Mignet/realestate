package com.szhome.cq.sqlfileexport;

public class DoubtConstantVo {

  private String file;
  private String lineStr;
  private int lineNum;
  private String fileAuthor;
  private String modifyAuthor;
  private String isRemark;
  
  
  public DoubtConstantVo(String file, String lineStr, int lineNum,String fileAuthor,String modifyAuthor,String isRemark) {
    this.file = file;
    this.lineStr = lineStr;
    this.lineNum = lineNum;
    this.fileAuthor=fileAuthor;
    this.modifyAuthor=modifyAuthor;
    this.isRemark=isRemark;
  }
  public String getFile() {
    return file;
  }
  public void setFile(String file) {
    this.file = file;
  }
  public String getLineStr() {
    return lineStr;
  }
  public void setLineStr(String lineStr) {
    this.lineStr = lineStr;
  }
  public int getLineNum() {
    return lineNum;
  }
  public void setLineNum(int lineNum) {
    this.lineNum = lineNum;
  }
  public String getModifyAuthor() {
    return modifyAuthor;
  }
  public void setModifyAuthor(String modifyAuthor) {
    this.modifyAuthor = modifyAuthor;
  }
  
  public String getFileAuthor() {
    return fileAuthor;
  }
  public void setFileAuthor(String fileAuthor) {
    this.fileAuthor = fileAuthor;
  }
  
  public String getIsRemark() {
    return isRemark;
  }
  public void setIsRemark(String isRemark) {
    this.isRemark = isRemark;
  }
  @Override
  public String toString() {
    return file+" "+modifyAuthor+" "+lineNum+" : "+lineStr.trim();
  }
  
  
  
}

