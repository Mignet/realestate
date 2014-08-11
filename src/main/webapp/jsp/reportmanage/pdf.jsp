<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" import="com.szhome.security.ext.UserInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>打印预览</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp"%>
<script type="text/javascript"  src="${ctx}/js/reportmanage/pdf.js"></script>
<script type="text/javascript">
	  var ctx = '${ctx}';
</script>
</head>
<body>
	 <iframe id="printed" name="printed"  width="100%" height="560px"></iframe>
</body>
</html>
