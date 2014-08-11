<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%
	String contextPath = request.getContextPath();
%>

<meta http-equiv="content-type" content="text/html;charset=GBK">
<meta http-equiv="x-ua-compatible" content="ie=8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="">

<!-- 导入plui组件的代码开始 -->
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/plui2/themes/default/plui.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/plui2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/plui2/themes/form.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/plui2/css/form.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/plui2/css/uploader.css">

<script type="text/javascript" src="<%=contextPath%>/plui2/jquery.js"></script>
<script type="text/javascript" src="<%=contextPath%>/plui2/jquery.json-2.4.js"></script>
<%-- <script type="text/javascript" src="<%=contextPath%>/plui2/jquery.bgiframe.js"></script> --%>

<script type="text/javascript">
	$.contextPath = <%="'"+contextPath+"'"%>;
</script>

<script type="text/javascript" src="<%=contextPath%>/plui2/plui.core.js"></script>
<script type="text/javascript" src="<%=contextPath%>/plui2/jslib/ajax.config.js"></script>
<script type="text/javascript" src="<%=contextPath%>/plui2/jslib/commonFileReader.js"></script>
<script type="text/javascript" src="<%=contextPath%>/plui2/jslib/event.js"></script>
<script type="text/javascript" src="<%=contextPath%>/plui2/plui.plugin.js"></script>

<!-- 导入plui组件的代码结束 -->
