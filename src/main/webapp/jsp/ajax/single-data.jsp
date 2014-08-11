<%@ page language="java" 
	contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","No-cache"); 
	response.setDateHeader("Expires", 0); 
%>${singleData}
