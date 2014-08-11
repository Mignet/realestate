package com.springjdbc.annotation;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {

  private List<T> list;

  private int pageNo;

  private int pageSize=10;

  private int totalSize;

  private int totalPage;

  public int getTotalSize() {
    return totalSize;
  }

  public int getTotalPage() {
    return totalPage;
  }

  public Page(List<T> list, int pageNo, int pageSize, int totalSize) {
    this.list = list;
    this.pageNo = pageNo;
    this.pageSize = pageSize;
    this.totalSize = totalSize;
    this.totalPage = this.queryTotalPage(this.totalSize,this.pageSize);
  }

  private int queryTotalPage(int totalsize,int pagesize) {
    if (totalSize % pageSize == 0)
      return totalSize / pageSize;
    else
      return totalSize / pageSize + 1;
  }

  public List<T> getList() {
    return list;
  }

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
}

