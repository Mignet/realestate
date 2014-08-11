package com.szhome.cq.web;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.web.servlet.ModelAndView;

import com.plan.commons.Row;
import com.plan.exceptions.GeneralException;
import com.plan.util.json.JsonParser;
import com.plan.web.CommonMultiController;
import com.plan.web.JsonResult;

public class BaseController extends CommonMultiController
{
  private Log logger = LogFactory.getLog(getClass());
  private Map<String, Integer> exceptionStatusMap;
  private JsonParser jsonParser;

  public BaseController()
  {
    this.exceptionStatusMap = new HashMap<String,Integer>();
    this.exceptionStatusMap.put(Exception.class.getName(), Integer.valueOf(500));
  }

  public Map<String, Integer> getExceptionStatusMap() {
    return this.exceptionStatusMap;
  }

  public void setExceptionStatusMap(Map<String, Integer> exceptionStatusMap) {
    this.exceptionStatusMap = exceptionStatusMap;
  }

  public Log getLogger()
  {
    return this.logger;
  }

  public void setLogger(Log logger) {
    this.logger = logger;
  }

  public JsonParser getJsonParser()
  {
    return this.jsonParser;
  }

  public void setJsonParser(JsonParser jsonParser)
  {
    this.jsonParser = jsonParser;
  }

  public void writeJson(HttpServletResponse response, String jsonString)
    throws Exception
  {
    writeJson(response, "GBK", jsonString);
  }

  protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    try
    {
      String requestURI = request.getRequestURI();
      String[] uris = requestURI.split("/");
      String[] uriseg = uris[(uris.length - 1)].split("!");
      String delegateObjectName = uriseg[(uriseg.length - 2)];
      String delegateMethodName = uriseg[(uriseg.length - 1)].replace(".action", "");

      this.logger.info("处理的请求URI = " + request.getRequestURI());

      Object delegateObject = null;
      try
      {
        delegateObject = getWebApplicationContext().getBean(delegateObjectName);
      } catch (BeansException bex) {
        throw new GeneralException("PLAT-00005", "代理对象" + delegateObjectName + "不存在，请检容器配置", bex);
      }

      Row rowParamOfDelegateMethod = parseRowParamOfDelegateMethod(request);

      checkPerms(request, response, delegateObjectName, delegateMethodName);

      preProcess(request, response, delegateObjectName, delegateMethodName);

      Object result = invokeMyDelegateMethod(delegateObject, delegateObjectName, delegateMethodName, rowParamOfDelegateMethod, response);

      postProcess(request, response, result);
      if(result==null){
    	  //worst:we got nothing
    	  return null;
      }
      else if(result instanceof ModelAndView){
    	  return (ModelAndView)result;
      }else{
    	  writeResponse(response, result);
      }
    }
    catch (Exception ex)
    {
      this.logger.error(ex);

      if ((ex instanceof GeneralException)) {
        GeneralException gex = (GeneralException)ex;
        String code = gex.getEndUserTipCode();
        if (code == null) {
          code = "00000";
        }
        String message = gex.getEndUserTip();
        writeExceptionUserTipToResponse(code, message, request, response);
      }
      else {
        writeExceptionUserTipToResponse("00000", "未知异常、请联系管理员", request, response);
      }

      processExceptionLogic(ex, request, response);
    }
    return null;
  }
  protected final Object invokeMyDelegateMethod(Object delegateObject, String delegateObjectName, String delegateMethodName, Row wrappedRow, HttpServletResponse response)
   throws Exception
  {
     try{
    	 Class<?>[] parameterTypes = { Row.class };
    	 Method method = delegateObject.getClass().getMethod(delegateMethodName, parameterTypes);
    	 
    	 return method.invoke(delegateObject, new Object[] { wrappedRow });
    }catch (NoSuchMethodException nmex){
    	String errorMsg = "代理对象" + delegateObjectName + "中没有" + delegateMethodName + "方法，或者参数错误。";
    	throw new GeneralException("PLAT-0006", errorMsg, nmex);
     }catch (InvocationTargetException itex)
    {
       throw ((Exception)itex.getCause());
    }
    catch (Exception ex)
   {
      throw ex;
   }
  }
  public void writeResponse(HttpServletResponse response, Object result)
     throws Exception
   {
     String jsonString = "{}";
     if ((result instanceof Collection))
       jsonString = this.jsonParser.toJson4Collection((Collection)result);
    else if ((result instanceof JsonResult)) {
       jsonString = ((JsonResult)result).toJsonString();
    }
    else if (((result instanceof String)) || ((result instanceof Number)))
    	jsonString = result.toString();
    else {
    	jsonString = this.jsonParser.toJson(result);
    }
     writeJson(response, jsonString);
  }
  protected void writeExceptionUserTipToResponse(String errorCode, String errorMsg, HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      String userAgent = request.getHeader("user-agent");
      response.setContentType("text/html; charset=GBK"); 
      response.setHeader("Charset","GBK");
      this.logger.info("user-agen === \n" + userAgent);

      boolean isIE = userAgent.indexOf("MSIE") > -1;

      if (isIE)
      {
        response.addHeader("error_msg", new String(errorMsg.getBytes("GBK"), "ISO8859-1"));
      }
      else
      {
//    	  errorMsg = new String(errorMsg.getBytes("GBK"), "GBK");
//    	  response.addHeader("error_msg", new String(errorMsg.getBytes("GBK"), "ISO8859-1"));
        response.addHeader("error_msg", new String(errorMsg.getBytes("GBK"), "ISO8859-1"));
//        response.addHeader("error_msg", new String(errorMsg.getBytes(), "GBK"));
//        response.addHeader("error_msg", "=?GBK?q?"+errorMsg+"?=");
      }
      response.addHeader("error_code", errorCode);

      response.sendError(500);
    }
    catch (Exception ex)
    {
      this.logger.error("写错误提示信息出现异常", ex);
    }
  }

}

