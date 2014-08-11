<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/base/taglibs.jsp"%>
<%
Object beginstr=request.getAttribute("actionExecuteBeginTime");
long usetime=0;
if(beginstr!=null){
	String b_str=(String)beginstr;
	long begin=Long.valueOf(b_str);
	long end = System.currentTimeMillis();
	usetime=(end-begin);
%>
<script language="javascript">
	var usedTime ="²Ù×÷ÓÃÊ±:<%=usetime%> ºÁÃë";
	window.status = usedTime;		
</script>
<%}%>

