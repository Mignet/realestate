package com.szhome.cq.utils;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;

/**
 * ���봦��
 * @author Mignet
 */
public class CodeUtil {
	
	//������־
   Logger log = Logger.getLogger(CodeUtil.class);
  
  public static String cn(String str) {

    try {
      if(str.equals(""))
      {
        str = null;
      }
      byte[] temp = str.getBytes("ISO-8859-1");
      return new String(temp,"GBK");
    } catch (Exception e) {
      return null;
    }
  }
  
  public static String toUtf8String(String s){
      StringBuffer sb = new StringBuffer();
      for (int i=0;i<s.length();i++){
          char c = s.charAt(i);
         if (c >= 0 && c <= 255){sb.append(c);}
         else{
             byte[] b;
             try { b = Character.toString(c).getBytes("GBK");}
             catch (Exception ex) {
                 System.out.println(ex);
                 b = new byte[0];
             }
             for (int j = 0; j < b.length; j++) {
                 int k = b[j];
                 if (k < 0) k += 256;
                 sb.append("%" + Integer.toHexString(k).toUpperCase());
             }
         }
     }
     return sb.toString();
  }
  /**
   * �Ѷ���ת��ΪString��ʾ
   * @author Mignet
   */
  public static String Object2String(Object obj) {
    return ToStringBuilder.reflectionToString(obj, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

