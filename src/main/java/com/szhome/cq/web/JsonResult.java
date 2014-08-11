/**
 * Project Name:dxtx_re
 * File Name:JsonResult.java
 * Package Name:com.szhome.cq.web
 * Date:2013-12-26ÉÏÎç10:40:38
*/

package com.szhome.cq.web;

import java.util.Map;

public class JsonResult
{

    public JsonResult()
    {
        status = 0;
    }

    public JsonResult(boolean success, String tipMessage)
    {
        status = 0;
        this.success = success;
        this.tipMessage = tipMessage;
    }
    
    public JsonResult(boolean success, String tipMessage,String operType,int state)
    {
    	this.status = state;
    	this.success = success;
    	this.tipMessage = tipMessage;
    	this.operType  = operType;
    }

    public JsonResult(boolean success, String tipMessage, String errorMessage)
    {
        status = 0;
        this.success = success;
        this.tipMessage = tipMessage;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getTipMessage()
    {
        return tipMessage;
    }

    public void setTipMessage(String tipMessage)
    {
        this.tipMessage = tipMessage;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }
    
    public String getOperType() {
  		return operType;
  	}

  	public void setOperType(String operType) {
  		this.operType = operType;
  	}
    public String toJsonString()
    {
        return (new StringBuilder("{\"success\":")).append(success).append(",\"tipMessage\":\"").append(tipMessage).append("\",\"errorMessage\":\"").append(errorMessage).append("\",\"status\":\"").append(status).append("\"}").toString();
    }
    
    public String toJsonString(Map<String,Object> m,String[] keys){
    	 StringBuilder  sbr = new StringBuilder();
    	 sbr.append("{\"success\":");
    	 sbr.append(success);
    	 sbr.append(",\"tipMessage\":\"");
    	 sbr.append(tipMessage);
    	 sbr.append("\",\"errorMessage\":\"");
    	 sbr.append(errorMessage);
    	 sbr.append("\",\"status\":\"");
    	 sbr.append(status);
    	 sbr.append("\",\"operType\":\"");
    	 sbr.append(operType);
    	 if(keys != null){
    		 for(int i=0;i<keys.length;i++){
    			 sbr.append("\",\""+keys[i]+"\":\"");
    			 sbr.append(m.get(keys[i]));
    		 }
    	 }
    	 sbr.append("\"}");
    	 return sbr.toString();
    }

    private boolean success;
    private String tipMessage;
    private String errorMessage;
    private String operType;
	private int status;
}

