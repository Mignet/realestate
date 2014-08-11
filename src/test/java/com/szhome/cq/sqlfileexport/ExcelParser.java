package com.szhome.cq.sqlfileexport;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class ExcelParser {

  private static String file = "E:/JP/word.xls";
  private static String lesson = "E:/JP/lesson.xls";
  private final int ITEM_LEN=60;
  public String[][] readFromExcel(String upload, int seet, int brow,
      int bcol, int erow, int ecol) throws Exception {
    // 判断所上传文件的类型是否是Excel
    String[][] data;
      InputStream myxls = new FileInputStream(upload);
      HSSFWorkbook wb = new HSSFWorkbook(myxls);
      HSSFSheet sheet = wb.getSheetAt(seet - 1);
      data = new String[erow - brow + 1][ecol - bcol + 1];
      for (int j = brow - 1; j < erow; j++) {
        HSSFRow row = sheet.getRow(j);
        int r_ind = j - brow + 1;
        if (row != null) {
          for (int i = bcol - 1; i < ecol; i++) {
            int c_ind = i - bcol + 1;
            HSSFCell s2 = row.getCell(i);
            String str = "";
            if (s2 != null){
              if(s2.getCellType() ==HSSFCell.CELL_TYPE_STRING)
                str = s2.getRichStringCellValue().getString();
              else if(s2.getCellType()==HSSFCell.CELL_TYPE_NUMERIC)
                str=String.valueOf(s2.getNumericCellValue());
            }
            data[r_ind][c_ind] = str;
          }
        }
      }
    return data;
  }
  public String[] readFromExcel(String upload, int col) throws Exception {
    // 判断所上传文件的类型是否是Excel
      InputStream myxls = new FileInputStream(upload);
      HSSFWorkbook wb = new HSSFWorkbook(myxls);
      HSSFSheet sheet = wb.getSheetAt(0);
      String[] data = new String[sheet.getPhysicalNumberOfRows()];
      for (int j = 0; j < data.length; j++) {
        HSSFRow row = sheet.getRow(j);
        if (row != null) {
            HSSFCell s2 = row.getCell(col);
            String str = "";
            if (s2 != null){
              if(s2.getCellType() ==HSSFCell.CELL_TYPE_STRING)
                str = s2.getRichStringCellValue().getString();
              else if(s2.getCellType()==HSSFCell.CELL_TYPE_NUMERIC)
                str=String.valueOf(s2.getNumericCellValue());
            }
            data[j] = str;
          }
        }
    return data;
  }
  private String parseStr(String less,String file,int[] para)throws Exception{
    ExcelParser par = new ExcelParser();
    String[][] data = par.readFromExcel(file, para[0], para[1], para[2],
        para[3], para[4]);
    String msg="";
    int i=1;
    for(String[] items:data){
      String s="";
      for(String ele:items){
        s=s+ele+"  ";
      }
      msg=msg+less+s+".. ";
      if(msg.length()>(i*ITEM_LEN)){
        msg=msg+";";
        i++;
      }
    }
    return msg;
  }
  private String parseStr(String file,int time) throws Exception{
    ExcelParser par = new ExcelParser();
    String[] data = par.readFromExcel(file,time-1);
    String msg="";
    int i=1;
    for(String items:data){
      if("".equals(items))continue;
      msg=msg+time+items+"  ";
      if(msg.length()>(i*ITEM_LEN)){
        msg=msg+";";
        i++;
      }
    }
    return msg;
  }
  public String loadLesson(String file)throws Exception{
    int[] para = { 1, 1, 13, 66, 15 };
    return parseStr("5",file, para);
  }

  public String loadGeneral(String file)throws Exception{
    int[] para = { 2, 1, 1, 11, 12 };
    return parseStr("",file, para);
  }
  public String loadLesson1(String file)throws Exception{
    return parseStr(file, 5);
  }
  public static void main(String[] args) throws Exception {
    ExcelParser par=new ExcelParser();
    //String str=par.loadLesson();
    //String str=par.loadLesson1();
    String str=par.loadLesson1(lesson);
    System.out.println(str);
  }
}

