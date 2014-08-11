package com.szhome.cq.utils;

import java.util.HashMap;
import java.util.Map;

public class SQLUtils {
     /**
     * @param dataMap 数据Map
     * @param fieldoptions field Keys 数组，根据fieldoptions的值得到dataMap中对应的值
     * @param selflag
     * @return
     */
    public static Map<String,Object> growWhereStatment(Map<String,Object> dataMap,String[][] fieldoptions,String[] selflag){
    	 if(dataMap == null) return null;
    	 StringBuffer whereSql = new StringBuffer("where 1=1 ");
    	 StringBuffer whereSqllike = new StringBuffer("where 1=1 ");
    	 Map<String,Object>  returnMap = new HashMap<String, Object>();
    	 Map<String,Object> fieldMap = new HashMap<String, Object>();
    	 for(int i = 0;i < fieldoptions.length; i++){
    		 if(dataMap.containsKey(fieldoptions[i][1])){
    			 Object obj = dataMap.get(fieldoptions[i][1]);
    			 if(!Util.isNotNull2Empty(obj)){
    				 continue;
    			 }
    			 whereSqllike.append(" and ");
    			 whereSqllike.append(fieldoptions[i][0]);
    			 whereSql.append(" and ");
    			 whereSql.append(fieldoptions[i][0]);
    			 if(fieldoptions[i][2].trim().equals("2")){
    				 obj = DateUtils.string2Date(obj.toString(), Constants.CHINESEDATEFMT);
    				 whereSql.append(" = :"); 
        			 whereSql.append(fieldoptions[i][1]);
    			 }else if(fieldoptions[i][2].trim().equals("3")){
    				 obj = DateUtils.string2Date(obj.toString(), Constants.CHINESEDATETIMEFMT);
    				 whereSql.append(" = :"); 
        			 whereSql.append(fieldoptions[i][1]);
    			 }else if(fieldoptions[i][2].trim().equals("4")){
    				 whereSql.append(" = :"); 
        			 whereSql.append(fieldoptions[i][1]);
    			 }else{
    				 whereSqllike.append(" like "); 
        			 whereSqllike.append(wherelike(fieldoptions[i][1]));
        			 whereSql.append(" = :"); 
        			 whereSql.append(fieldoptions[i][1]);
    			 }
    			 whereSqllike.append(" ");
    			 whereSql.append(" ");
    			 fieldMap.put(fieldoptions[i][1], obj);
    		 }
    	 }
    	 returnMap.put("SQLWHERE", whereSql);
    	 returnMap.put("SQLWHERELIKE", whereSqllike);
    	 returnMap.put("OPTIONS", fieldMap);
    	 return returnMap;
     }
     public static String wherelike(Object obj){
    	 StringBuffer rtnstr = new StringBuffer();
    	 if(obj==null) return "";
    	 String str = obj.toString();
    	 if(str.trim().equals(""))return "";
    	 rtnstr.append("'%'||");
    	 rtnstr.append(":");
    	 rtnstr.append(str);
    	 rtnstr.append("||'%'");
    	 return rtnstr.toString();
     }
     public static String wherelikeR(Object obj){
    	 StringBuffer rtnstr = new StringBuffer();
    	 if(obj==null) return "";
    	 String str = obj.toString();
    	 if(str.trim().equals(""))return "";
    	 rtnstr.append(":");
    	 rtnstr.append(str);
    	 rtnstr.append("||'%'");
    	 return rtnstr.toString();
     }
     public static String wherelikeL(Object obj){
    	 StringBuffer rtnstr = new StringBuffer();
    	 if(obj==null) return "";
    	 String str = obj.toString();
    	 if(str.trim().equals(""))return "";
    	 rtnstr.append("'%'||");
    	 rtnstr.append(":");
    	 rtnstr.append(str);
    	 return rtnstr.toString();
     }
}

