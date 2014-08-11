<%@ page language="java" import="java.util.*,com.plan.exceptions.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<% 
	Object ex = request.getAttribute("exception");
	String msg;
	if (ex instanceof GeneralException){
		GeneralException gex = (GeneralException) ex;
		msg = "errorCode:"+gex.getEndUserTipCode()+";"+"errorMsg:"+gex.getEndUserTip()+";";
	}else {
		msg = "errorCode:无;errorMsg:后台出错！请联系管理员！;";
	}
%>
<html>
<body>
<%=msg%>
</body>
</html>
