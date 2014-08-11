<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" import="com.szhome.security.ext.UserInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>深圳市不动产权登记系统--驳回登记决定书</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <script type="text/javascript" src="${ctx}/js/workflow/specproc/delay_history_tab.js"></script>
    <script type="text/javascript">
	  var ctx = '${ctx}';
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
     <style type="text/css">
	 body,html {
			margin: 0px;
			font-family: Arial;
			font-size: 12px;
			color: #333333;
			padding: 5px !important;
    }
    </style>   
</head>
<body class="plui-layout">
    <div data-options="region:'center'" border=false>
		   <div class="plui-layout" fit=true>
		        <div data-options="region:'center'" border=false>
		           <table id="delay_his_tab"></table>
		        </div>
		   </div>
	</div>
</body>
</html>


