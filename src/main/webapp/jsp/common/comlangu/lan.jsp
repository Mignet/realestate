<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"
import="com.szhome.security.ext.UserInfo"%>
	<%
  UserInfo user=(UserInfo)session.getAttribute("userInfo");
  String userName=user.getUserName();
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>常用语配置</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp"%>  
<script type="text/javascript" src="${ctx}/js/common/comlangu/lan.js"></script>     
<script type="text/javascript">
var user = '<%=userName%>';	
var ctx = '${ctx}';
</script>
</head>
<body class="plui-layout">
	<div data-options="region:'west',split:true,title:'业务列表'" style="width:200px;">
		<ul id="productTree" />
	</div>
	<div data-options="region:'center'" border=false>
		<table id="table_user"></table>
	</div>
	<!-- 
	<div id="mm" class="plui-menu" style="width:120px;display:none">
	    <div onclick="append()" data-options="iconCls:'icon-add'">添加子节点</div>
	    <div onclick="remove()" data-options="iconCls:'icon-remove'">删除子节点</div>
    </div>
	 -->
	
</body>
</html>
