package com.szhome.cq.sqlfileexport;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public abstract class AbstractExcelReader {

  private final int ITEM_LEN=60;
  
  protected String loadCellString(HSSFCell s2){
    String str = "";
    if (s2 != null){
      if(s2.getCellType() ==HSSFCell.CELL_TYPE_STRING)
        str = s2.getRichStringCellValue().getString();
      else if(s2.getCellType()==HSSFCell.CELL_TYPE_NUMERIC)
        str=String.valueOf(s2.getNumericCellValue());
    }
    return str;
  }
  protected HSSFSheet loadSheet(String upload,int sheetindex)throws Exception{
    InputStream myxls = new FileInputStream(upload);
    HSSFWorkbook wb = new HSSFWorkbook(myxls);
    HSSFSheet sheet = wb.getSheetAt(sheetindex);
    return sheet;
  }
  protected String parseStr(String pompt,String[][] data)throws Exception{
    String msg="";
    int i=1;
    for(String[] items:data){
      String s="";
      if(items[0]==null)continue;
      for(String ele:items){
        String tmp=ele==null?"":ele;
        s=s+tmp+"  ";
      }
      msg=msg+pompt+s+" ";
      if(msg.length()>(i*ITEM_LEN)){
        msg=msg+";";
        i++;
      }
    }
    return msg;
  }
}

