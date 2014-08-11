package com.szhome.cq.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Logger;

import com.plan.commons.Row;
import com.szhome.cq.domain.model.Bk_Certificate;
import com.szhome.cq.domain.model.BusType;
import com.springjdbc.annotation.BaseDomain;

public class WebUtil {
    static Logger logger = Logger.getLogger(WebUtil.class.getName());
    public static String getBusName(BaseDomain<BusType> dao,Map<String,Object> paramMap){
    	String bus_name = dao.queryObjectByKey("Common.getBusName", paramMap, String.class);
    	return bus_name;
    }
    
    public static String getCerCode(BaseDomain<Bk_Certificate> dao,Map<String,Object> paramMap){
    	String cer_code = dao.queryObjectByKey("Common.getCerCode", paramMap, String.class);
    	return cer_code;
    }
    
    public static Object packageEntity(String ClassName,Row row,String prefix){
    	String fullPath = "com.szhome.cq.business.vo.";
    	Object obj = null;
    	if(prefix == null) prefix = "";else prefix = prefix+".";
    	
    	try {
			Class c = Class.forName(fullPath+ClassName);
			logger.info("Class:"+c.getName());
			 // ͨ��Ĭ�Ϲ��췽������һ���µĶ���            
		     obj = c.getConstructor(new Class[] {}).newInstance(new Object[] {});    
			// ��ö������������           
			Field fields[] = c.getDeclaredFields(); 
			 for (int i = 0; i < fields.length; i++) {                
				 Field field = fields[i];                 
				 String fieldName = field.getName();                 
				 String firstLetter = fieldName.substring(0, 1).toUpperCase();                 
				 String key = prefix+fieldName;
				 if(!row.containsKey(key))
					 continue;
				 String value = row.getString(key);
				 if(!Util.notNullEmpty(value))
					 continue;
				 /* // ��ú����Զ�Ӧ��getXXX()����������                 
				 String getMethodName = "get" + firstLetter + fieldName.substring(1);  */               
				 // ��ú����Զ�Ӧ��setXXX()����������                 
				 String setMethodName = "set" + firstLetter + fieldName.substring(1);                 
				/* // ��ú����Զ�Ӧ��getXXX()����                 
				 Method getMethod = c.getMethod(getMethodName,new Class[] {});    */             
				 // ��ú����Զ�Ӧ��setXXX()����                 
				 Method setMethod = c.getMethod(setMethodName,new Class[] { field.getType() });  
				/* // ����ԭ�����getXXX()����                
				 Object value = getMethod.invoke(object, new Object[] {});   */
				 // ���ø��ƶ����setXXX()����                
				 setMethod.invoke(obj, new Object[] { value });     
				 logger.info(key+":"+value);  
			}     
		} catch (ClassNotFoundException e) {
			logger.info("packageEntity -- ClassNotFoundException"+e.getMessage());
			e.printStackTrace();
		} catch (InstantiationException e) {
			logger.info("packageEntity -- InstantiationException"+e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			logger.info("packageEntity -- IllegalAccessException"+e.getMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			logger.info("packageEntity -- IllegalArgumentException"+e.getMessage());
			e.printStackTrace();
		} catch (SecurityException e) {
			logger.info("packageEntity -- SecurityException"+e.getMessage());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			logger.info("packageEntity -- InvocationTargetException"+e.getMessage());
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			logger.info("packageEntity -- NoSuchMethodException"+e.getMessage());
			e.printStackTrace();
		}
    	return obj;
    }
    public static void fillOverallVariables(Object obj,Row row){
    	try {
    		Class c = obj.getClass();   
    		// ��ö������������           
    		Field fields[] = c.getDeclaredFields(); 
    		for (int i = 0; i < fields.length; i++) {                
    			Field field = fields[i];                 
    			String fieldName = field.getName();  
                //����row��ֵ
    			String key = fieldName;
    			if(!row.containsKey(key))
    				continue;
    			String value = row.getString(key);
    			if(!Util.notNullEmpty(value))
    				continue;
    			field.getType();
    			String firstLetter = fieldName.substring(0, 1).toUpperCase();   
    			// ��ú����Զ�Ӧ��setXXX()����������                 
			    String setMethodName = "set" + firstLetter + fieldName.substring(1);   
			    // ��ú����Զ�Ӧ��setXXX()����                 
				Method setMethod = c.getMethod(setMethodName,new Class[] { field.getType() }); 
				// ���ø��ƶ����setXXX()����                
				setMethod.invoke(obj, new Object[] { value });     
    			logger.info(key+":"+value);  
    		}     
    	} catch (IllegalAccessException e) {
    		logger.info("packageEntity -- IllegalAccessException"+e.getMessage());
    		e.printStackTrace();
    	} catch (IllegalArgumentException e) {
    		logger.info("packageEntity -- IllegalArgumentException"+e.getMessage());
    		e.printStackTrace();
    	} catch (SecurityException e) {
    		logger.info("packageEntity -- SecurityException"+e.getMessage());
    		e.printStackTrace();
    	} catch (InvocationTargetException e) {
    		logger.info("packageEntity -- InvocationTargetException"+e.getMessage());
    		e.printStackTrace();
    	} catch (NoSuchMethodException e) {
    		logger.info("packageEntity -- NoSuchMethodException"+e.getMessage());
    		e.printStackTrace();
    	}
    }
}

