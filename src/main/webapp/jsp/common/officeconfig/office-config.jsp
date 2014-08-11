<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>表单及报表配置</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
        
<%@ include file="/base/taglibs.jsp"%>
  <%@ include file="/base/prefix.jsp"%>
  <script type="text/javascript" src="${ctx}/js/common/officeconfig/officeConfig.js"></script>	
    <script type="text/javascript"> 	

  var ctx = '${ctx}';	 
</script>
</head>
<body class="plui-layout">
	<div data-options="region:'west',split:true,title:'表单报表列表'" style="width:200px;">
		<ul id="getProcName" />
	</div>
	<div data-options="region:'center'" border=false>
		<table id="table_user"></table>
	</div>
</body>
</html>
