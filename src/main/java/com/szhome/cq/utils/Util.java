package com.szhome.cq.utils;

import java.lang.reflect.Field;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.collect.ImmutableMap;
import com.plan.commons.Row;


public class Util {
	private static Log log				= LogFactory.getLog(Util.class);
	
	
	public static boolean notNullEmpty(String in) {
		boolean result = false;
		if(( in!=null && in.trim().length()>0) ) {
			result = true;
		}

		return result;
	}

	/**
	 * @param module
	 * @param msgKey
	 * @return
	 */
	public static String getMessage(String module, String msgKey) {
   		MessageUtil msgUtil = MessageUtil.getInstance();

   		return msgUtil.getMessage(module, msgKey);
   	}
	
    public static String getJasperFileFullPath(HttpServlet servlet, String jasperPosFile, String jasperFileName) {
        ServletContext context 		= servlet.getServletConfig().getServletContext();
        String jasperFullPath		= "";

        log.info("getJasperFileFullPath.jasperFileName="+jasperFileName+",jasperPosFile="+jasperPosFile);
        if(Util.notNullEmpty(jasperPosFile)) {
        	jasperFullPath = context.getRealPath(jasperPosFile);
        }
        log.info("getJasperFileFullPath.jasperFullPath="+jasperFullPath);

        if(Util.notNullEmpty(jasperFileName)) {
        	URL jasperFileUrl 	= Util.class.getClassLoader().getResource(jasperFileName);
        	if (jasperFileUrl!= null ) {
        		jasperFullPath = jasperFileUrl.getFile();
            	jasperFullPath = context.getRealPath(jasperFileName);
        	}
        }
        log.info("getJasperFileFullPath.jasperFullPath="+jasperFullPath);

    	return jasperFullPath;
    }
    
    
    
    public static String formatDate(Date date){
        return formatDate(date, Constants.CHINESEDATEFMT);
    }
    
    /*
	* Format Date value into String
	*/
    public static String formatDate(Date date, String format){

    	if(date == null){
            return "";
        }

        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }catch (Exception e){
        }
        return "";
    }
    
    /**
   	 * 判断对象是否为null or empty
   	 * @param obj
   	 * @return
   	 */
   	public static boolean isNotNull2Empty(Object obj){
   		 if(obj == null) return false;
   		 else if(obj instanceof String[] && ((String[])obj)[0] == null) return false;
   		 return obj.toString().trim().equals("")?false:true;
   	 }
   	
	/**
   	 * @param obj
   	 * @return
   	 */
   	public static String emptyNull2Zero(Object obj){
           if(obj==null || obj.toString().trim().length()==0)
               return "0";
           else
               return obj.toString();
       }

   	/**
   	 * 将实体对象转换成Map
   	 * @param obj
   	 * @return Map
   	 * @Date 2014-04-01 11:11:22
   	 * @author Sam
   	 */
	public static Map<String,Object> ConvertObjToMap(Object obj){
		  Map<String,Object> reMap = new HashMap<String,Object>();
		  if (obj == null) 
		   return null;
		  Field[] fields = obj.getClass().getDeclaredFields();
		  try {
			   for(int i=0;i<fields.length;i++){
				    try {
				       Field f = obj.getClass().getDeclaredField(fields[i].getName());
				       f.setAccessible(true);
				       Object o = f.get(obj);
				       //if(isNotNull2Empty(o))
				       reMap.put(fields[i].getName(), o);
				    } catch (NoSuchFieldException e) {
				         e.printStackTrace();
				    } catch (IllegalArgumentException e) {
				    	 e.printStackTrace();
				    } catch (IllegalAccessException e) {
				    	 e.printStackTrace();
				    }
			   }
		  } catch (SecurityException e) {
			  e.printStackTrace();
		  } 
		  return reMap;
	 }
	
	/**
	 *  得到子查询In的条件选项
	 * @param protoStr 字符串用逗号隔开,没有加单引号
	 * @return 带有单引号且字符串用逗号隔开的字符串
	 */
	public static String getInSubselectOptions(String protoStr){
		String returnStr = new String();
		String[] tempStrs = null;
		if(Util.isNotNull2Empty(protoStr)){
			tempStrs = protoStr.split(",");
		}
		if(Util.isNotNull2Empty(tempStrs) && tempStrs.length > 0){
			//returnStr += "',";
			for(int i=0 ; i < tempStrs.length; i++){
				//returnStr += "'"+tempStrs[i]+"',";
				returnStr += tempStrs[i]+",";
			}
			  // returnStr +="'"; 
		}
		return returnStr.substring(0, returnStr.length()-1);
	}
	/**
	 *  得到子查询In的条件选项
	 * @param protoStr 字符串用逗号隔开,没有加单引号
	 * @return 带有单引号且字符串用逗号隔开的字符串
	 */
	public static String getInSubselectOptionsByArray(String[] protoStr){
		String returnStr = new String();
		if(Util.isNotNull2Empty(protoStr) && protoStr.length > 0){
			//returnStr += "',";
			for(int i=0 ; i < protoStr.length; i++){
				//returnStr += "'"+protoStr[i]+"',";
				returnStr += protoStr[i]+",";
			}
			// returnStr +="'"; 
		}
		return returnStr.substring(0, returnStr.length()-1);
	}

	/**
	 * 将row中的对象按照<b>objname</b>的方式重新组织<br>
	 * 比如：Util.ConvertObjToMap(row,"srejectioni")<br>
	 * 表示将row中srejectioni.rej_no,srejectioni.bus_id转为rej_no,bus_id
	 * @author dxtx
	 * @param row
	 * @return
	 * @since JDK 1.6
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> ConvertObjToMap(Row row, String objname) {
		Map<String,Object> map = new HashMap<String,Object>();
		Iterator<Entry<String, Object>> it = row.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, Object> entry = (Entry<String, Object>)it.next();
			String key = entry.getKey(); //返回与此项对应的键
			Object value = entry.getValue(); //返回与此项对应的值
			if(entry.getKey().startsWith(objname)){
				map.put(key.replace(objname+".", ""), value);
			}else{
				map.put(key, value);
			}
		}
		return map;
	}
	/**
	 * regexp Replace Tag. 2014-08-07 Sam
	 * @param inputStr
	 * @param patternStr
	 * @param replaceStr
	 * @return
	 */
	public static String replaceTagbyRegExp(String inputStr, String patternStr, String replaceStr) {

		Pattern pattern = Pattern.compile("#"+patternStr.toUpperCase()+"#");
	    Matcher matcher = pattern.matcher(inputStr.toUpperCase());

	    return matcher.replaceAll(replaceStr);
	}
	
}

