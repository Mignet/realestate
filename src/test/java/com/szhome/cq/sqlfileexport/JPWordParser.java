  package com.szhome.cq.sqlfileexport;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class JPWordParser extends AbstractExcelReader {

  private final String file = "F:/temp/jp∑÷¿‡.xls";
  private final int lesson_cell=3;
  private final int cell_len=3;

  public String parseWord(double less)throws Exception {
    HSSFSheet sheet=super.loadSheet(file, 0);
    String[][] data = new String[sheet.getPhysicalNumberOfRows()][3];
    for (int j = 0; j < data.length; j++) {
      HSSFRow row = sheet.getRow(j);
      if (row != null) {
        HSSFCell s2 = row.getCell(lesson_cell);
        String ls=super.loadCellString(s2);
        if("".equals(ls))continue;
        if(less==Double.valueOf(ls)){
          for(int i=0;i<cell_len;i++){
            HSSFCell s=row.getCell(i);
            data[j][i] = super.loadCellString(s);
          }
        }        
      }
    }
    String str=super.parseStr(String.valueOf(less),data);
    return str;
  }
  public static void main(String[] args) throws Exception {
    JPWordParser parser=new JPWordParser();
    String rtn="";
    rtn=parser.parseWord(9.0);
    System.out.println(rtn);
  }
}

