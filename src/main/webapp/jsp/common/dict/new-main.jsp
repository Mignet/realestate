<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<html>
<head>

<title>数据字典管理</title>
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp" %>
	  <script>	
        	var ctx ='${ctx}';
      </script>
	<script type="text/javascript" src="${ctx}/js/common/dict/new-main.js"></script>
	
	
</head>
<body>
		<div class="plui-layout" fit=true>
			<div data-options="region:'north',split:true,border:false" style="height:300px;">
				<table id="dictDataGrid"></table>
			</div>
			<div data-options="region:'center',border:false">
				<table id="itemDataGrid"></table>
			</div>
		</div>
</body>
</html>
