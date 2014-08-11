<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ include file="/base/taglibs.jsp"%>
<! DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" > 
<html xmlns ="http://www.w3.org/1999/xhtml" > 
<head> 
    <title>上传成功</title > 
</head> 
<body> 
    <div style ="padding: 3px; border: solid 1px #cccccc; text-align: center" > 
    <s:iterator value="uploadFileName" id="filename">
        <img src ='${ctx}/upload/<s:property value ="filename" /> ' width="200px" height="200px"/>
        <br /> 
        <!--<s:property value ="filename" />--> 
    </s:iterator>
    </div > 
</body > 
</html > 
