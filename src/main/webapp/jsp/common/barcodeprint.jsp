<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" import="com.szhome.security.ext.UserInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>条码打印</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%> 
    <script type="text/javascript" src="${ctx}/js/common/barcodeprint.js"></script>
    <script type="text/javascript">
	  var ctx = '${ctx}';
    </script>
	<style type="text/css">
	   th{
	    text-align:right;
	   }
	</style>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">   
</head>
<body class="plui-layout">
        <div data-options="region:'center'" border=false>
			<div class="plui-layout" fit=true>
				<div data-options="region:'center',border:false">
					     <center><font style="width:100px;height: 100px;font-size: 36px">条码生成打印</font></center>
					     <br/>
					     <center><input name="regcode" id="regcode" style="width:200px;height: 20px;"/></center>
					        <br/>
					     <center><input type="button" id="printview"  style="width:80px;height: 20px;" value="打印预览" /></center>
			    </div>
			</div>
	 </div>
</body>
</html>
