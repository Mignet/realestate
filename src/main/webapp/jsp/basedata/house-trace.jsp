<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <title>�����в�����Ȩ�Ǽ�ϵͳ</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <script type="text/javascript">
    	var ctx = '${ctx}';
    </script>
    <script type="text/javascript" src="${ctx}/js/common/raphael-min.js"></script>
    <script type="text/javascript" src="${ctx}/js/plui/plui.extend.js"></script>
    <script type="text/javascript" src="${ctx}/js/basedata/house-trace.js"></script>
<style type="text/css" media="screen">
html,body {height:100%; width:100%; margin:0; padding:0;overflow:auto;}
#holder {
    float:left;
}
</style>
</head>
<body>  
<div style="float:left;">
	<table id="tt" style="width:564px;height:auto"></table>
	<table id="tt1" style="height:auto"></table>
</div>
<div id="holder"></div>
</body>
</html>
