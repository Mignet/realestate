<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<html>
<head>

<title>数据字典管理</title>
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp" %>
	  <script>	
        	var ctx ='${ctx}';
      </script>
	<script type="text/javascript" src="${ctx}/js/common/dict/main.js"></script>
	
	
</head>
<body class="plui-layout">
	<div data-options="region:'center'" border=false>
		<div class="plui-layout" fit=true>
			<div data-options="region:'north',split:true,border:false" style="height:380px;">
				<table id="dictDataGrid"></table>
			</div>
			<div data-options="region:'center',border:false">
				<table id="itemDataGrid"></table>
			</div>
		</div>
	</div>
</body>
</html>
