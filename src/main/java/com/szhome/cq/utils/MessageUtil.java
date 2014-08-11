package com.szhome.cq.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Message Utility
 * @author sen
 * @version $Revision: 1.1.1.1 $ $Date: 2008/09/01 07:56:06 $
 */
public final class MessageUtil{
  
	private Log log 						= LogFactory.getLog(this.getClass());
		
	private static MessageUtil 	 msgUtil	= null;
	
	static HashMap resourcesMap				= new HashMap();			
	
	//get instance.
	public static MessageUtil getInstance() {
		if(msgUtil==null) msgUtil = new MessageUtil();
		return msgUtil;
	}
	
	/**
	 * get message resource
	 * @param request
	 * @param module
	 * @param key
	 * @return
	 */
	public static String getMessage(String module, String key) {
		Properties msgProperties = new  Properties();
		if(resourcesMap!=null && resourcesMap.containsKey(module) ) {
			msgProperties = (Properties)resourcesMap.get(module);
		} else {
			msgProperties = getResource(module);
			resourcesMap.put(module, msgProperties); 
		}
		return (String)msgProperties.getProperty(key);
	}
	
	/**
	 * get message according to locale
	 * @param localeString
	 * @param module
	 * @param key
	 * @return
	 */
	public static String getMessage(String localeString, String module, String key) {
		if( !Util.isNotNull2Empty(localeString) ) localeString = "en_US";//set default.
		
		String language			= localeString.substring(0, localeString.indexOf("_") );
		String country			= localeString.substring(localeString.indexOf("_")+1 );
   
		Properties msgProperties = new  Properties();
		if(resourcesMap!=null && resourcesMap.containsKey(module+"_"+language+"_"+country) ) {
			msgProperties = (Properties)resourcesMap.get(module+"_"+language+"_"+country);
		} else {
			msgProperties = getResource(module);
			resourcesMap.put(module+"_"+language+"_"+country, msgProperties); 
		}
		
		String msg = (String)msgProperties.getProperty(key);
		if(msg == null) msg = "";
		
		return msg;
	}	
	
	private static Properties getResource(String module) {
		Properties prop = new Properties();	
		if(msgUtil == null) msgUtil = getInstance();
		try {
			InputStream is	= msgUtil.getClass().getResourceAsStream("/resource_"+module+".properties");			
			prop.load(is);
			is.close();
		}catch(Exception ex){
			ex.printStackTrace();   			
   		}
		return prop;
	}
}
