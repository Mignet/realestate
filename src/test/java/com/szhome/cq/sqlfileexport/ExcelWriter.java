package com.szhome.cq.sqlfileexport;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExcelWriter {

  public boolean writeToExcel(String[] titles, String[][] datas, String fileName, String root) throws Exception {
    HSSFWorkbook wb = new HSSFWorkbook();
    HSSFSheet sheet = wb.createSheet("原始数据");
    HSSFRow row = null;
    HSSFCell cell = null;
    HSSFRichTextString value = null;
    HSSFFont font = wb.createFont();
    HSSFCellStyle style = wb.createCellStyle();
    // 标题行
    row = sheet.createRow(0);
    font.setFontName("黑体");
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    style.setFont(font);
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    String[] title = titles;
    for (int k = 0; k <= title.length - 1; k++) {
      cell = row.createCell(k);
      value = new HSSFRichTextString(title[k]);
      cell.setCellValue(value);
      cell.setCellStyle(style);
    }

    // 将内容写入Excel
    String[][] data = datas;
    for (int k = 0; k <= data.length - 1; k++) {
      row = sheet.createRow(k + 1);
      for (int j = 0; j <= data[k].length - 1; j++) {
        cell = row.createCell(j);
        value = new HSSFRichTextString(data[k][j]);
        cell.setCellValue(value);
      }
    }

    FileOutputStream fileout = new FileOutputStream(root + "\\" + fileName);
    wb.write(fileout);
    fileout.close();
    return true;
  }
}

