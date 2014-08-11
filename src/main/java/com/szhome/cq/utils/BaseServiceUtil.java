package com.szhome.cq.utils;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Sam
 *
 */
public class BaseServiceUtil {
	
	private static Log log				= LogFactory.getLog(BaseServiceUtil.class);
	/**
	 * set report captions.
	 * @param request
	 * @param parameters
	 * @param captionKeys
	 * @throws Exception
	 */
	public static void setReportCaptions(String module, Map<String,Object> parameters, String[] captionKeys)throws Exception{
		MessageUtil msgUtil = MessageUtil.getInstance();
		
		for(int i=0; captionKeys!=null&&i<captionKeys.length;i++){
			String key 		= captionKeys[i];
			String value 	= msgUtil.getMessage(module, key);
			parameters.put(key, value);
		}		
	}
	
	/**
	 * get Jasper File Full Path
	 * @param servlet
	 * @param jasperPosFile
	 * @param jasperFileName
	 * @return
	 */
	   public static String getJasperFileFullPath(HttpServletRequest request, String jasperPosFile, String jasperFileName) {
	        ServletContext context 		= request.getSession().getServletContext();
	        String jasperFullPath		= "";

	        log.info("getJasperFileFullPath.jasperFileName="+jasperFileName+",jasperPosFile="+jasperPosFile);
	        if(Util.notNullEmpty(jasperPosFile)) {
	        	jasperFullPath = context.getRealPath(jasperPosFile);
	        	log.info("getJasperFileFullPath.jasperFullPath="+jasperFullPath);
	        }else if(Util.notNullEmpty(jasperFileName)) {
	        	URL jasperFileUrl 	= BaseServiceUtil.class.getClassLoader().getResource(jasperFileName);
	        	if (jasperFileUrl!= null ) {
	        		jasperPosFile = jasperFileUrl.getFile();
	            	jasperFullPath = context.getRealPath(jasperPosFile);
	        	}
	        }
	        log.info("getJasperFileFullPath.jasperFullPath="+jasperFullPath);
	    	return jasperFullPath;
	    }
	   
	 /**
	  * 生成Where SQL语句及对应条件Map,以及填充where条件值到打印参数Map中
	 * @param retnMap where条件对应值Map
	 * @param parameters 报表参数Map
	 * @param paramMap 服务层传递过来的参数Map
	 * @param options where条件对应的二维数组
	 * @return
	 */
	protected static Map<String,Object> generateSQLCriteria(Map<String,Object> retnMap,Map<String,Object> parameters,Map<String,Object> paramMap,String[][] options){
		   Map<String, Object> SqlMap = new HashMap<String, Object>();
		   StringBuffer whereSql = null;
		   for(int i = 0;i < options.length; i++){
	    		 if(paramMap.containsKey(options[i][1])){
	    			 whereSql = new StringBuffer();
	    			 Object obj = paramMap.get(options[i][1]);
	    			 if(!Util.isNotNull2Empty(obj)){
	    				 continue;
	    			 }
	    			 whereSql.append(" and ");
	    			 whereSql.append(options[i][0]);
	    			 if(options[i][2].trim().equals("2")){
	    				 obj = DateUtils.string2Date(obj.toString(), Constants.CHINESEDATEFMT);
      			 }else if(options[i][2].trim().equals("3")){
      				 obj = DateUtils.string2Date(obj.toString(), Constants.CHINESEDATETIMEFMT);
      			 }else if(options[i][2].trim().equals("4")){
      				 
      			 }
  				 if(Util.isNotNull2Empty(paramMap.get("flag")) && paramMap.get("flag").equals(Constants.CRITERIA_INSUBSELECT)){
  					 whereSql.append(" in (");
  					 String[] strs = (String[])obj;
  					 String s = new String();
  					 for(int j=0;j<strs.length;j++){
  						 s += ":";
  						 StringBuffer sbr = new StringBuffer();
  						 sbr.append("sub");
  						 sbr.append(options[i][1]);
  						 sbr.append(j);
  						 s +=sbr.toString()+",";
  						 retnMap.put(sbr.toString(), strs[j]);
  					 }
          			 whereSql.append(s.substring(0, s.length()-1)); 
          			 whereSql.append(" )"); 
	    			 }else if(Util.isNotNull2Empty(paramMap.get("flag")) && paramMap.get("flag").equals(Constants.CRITERIA_LIKESELECT)){
	    				 whereSql.append(" like "); 
	        			 whereSql.append(SQLUtils.wherelike(options[i][1]));
	    			 }else{
	    				 whereSql.append(" = :"); 
          			     whereSql.append(options[i][1]); 
	    			 }
  				   SqlMap.put(options[i][1], whereSql);
  				   retnMap.put(options[i][1], obj);
  				   parameters.put(options[i][1], obj);
	    		 }
		    }
		   return SqlMap;
	   }
	/**
	 * 生成Where SQL语句及对应条件Map,以及填充where条件值到打印参数Map中
	 * @param retnMap where条件对应值Map
	 * @param paramMap 服务层传递过来的参数Map
	 * @param options where条件对应的二维数组
	 * @return
	 */
	protected static Map<String,Object> generateSQLCriteria(Map<String,Object> retnMap,Map<String,Object> param,String[][] options){
		Map<String, Object> SqlMap = new HashMap<String, Object>();
		StringBuffer combinationwheresql = new StringBuffer();
		StringBuffer whereSql = null;
		for(int i = 0;i < options.length; i++){
			if(param.containsKey(options[i][1])){
				whereSql = new StringBuffer();
				Object obj = param.get(options[i][1]);
				if(!Util.isNotNull2Empty(obj)){
					continue;
				}
				whereSql.append(" and ");
				Object inwhere = param.get("inwhere");
				if(options[i][2].trim().equals("6")){
					if(Util.isNotNull2Empty(inwhere)){
						whereSql.append(inwhere);
					}else{
						whereSql.append(options[i][0]);
					}
				}else{
				   whereSql.append(options[i][0]);
				}
				//
				if(options[i][2].trim().equals("2")){
					obj = DateUtils.string2Date(obj.toString(), Constants.CHINESEDATEFMT);
				}else if(options[i][2].trim().equals("3")){
					obj = DateUtils.string2Date(obj.toString(), Constants.CHINESEDATETIMEFMT);
				}else if(options[i][2].trim().equals("4")){
					
				}else if(options[i][2].trim().contains("5")){
					obj = DateUtils.string2Date(obj.toString(), Constants.CHINESEDATEFMT);
				}
				
				
				if(options[i][2].trim().equals("5.1")){
					whereSql.append(" >= :"); 
					whereSql.append(options[i][1]); 
				}else if(options[i][2].trim().equals("5.2")){
					whereSql.append(" <= :"); 
					whereSql.append(options[i][1]); 
				}else if(options[i][2].trim().equals("6")){
					StringBuffer subinwhereSql = new StringBuffer();
					subinwhereSql.append(" in (");
					String[] strs = (String[])obj;
					String s = new String();
					for(int j=0;j<strs.length;j++){
						s += ":";
						StringBuffer sbr = new StringBuffer();
						sbr.append("sub");
						sbr.append(options[i][1]);
						sbr.append(j);
						s +=sbr.toString()+",";
						retnMap.put(sbr.toString(), strs[j]);
					}
					subinwhereSql.append(s.substring(0, s.length()-1)); 
					subinwhereSql.append(" )"); 
					if(Util.isNotNull2Empty(inwhere)){
						String inputStr = whereSql.toString();
						whereSql = new StringBuffer().append(Util.replaceTagbyRegExp(inputStr, "CONDITION", subinwhereSql.toString()));
					}else{
						whereSql.append(subinwhereSql);
					}
				}else if(options[i][2].trim().equals("7")){
					whereSql.append(" like "); 
					whereSql.append(SQLUtils.wherelike(options[i][1]));
				}else{
					whereSql.append(" = :"); 
					whereSql.append(options[i][1]); 
				}
				SqlMap.put(options[i][1], whereSql);
				retnMap.put(options[i][1], obj);
				combinationwheresql.append(whereSql);
			}
			SqlMap.put("combinationwheresql", combinationwheresql);
		}
		return SqlMap;
	}
}

