<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>选择接收人</title>
       	<%@ include file="/base/taglibs.jsp"%>
    	<%@ include file="/base/prefix.jsp"%>
    	    	 <script type="text/javascript">
    	var ctx = '${ctx}';</script>
    	<script type="text/javascript" src="${ctx}/js/common/partici-pants.js"></script>      
<style type="text/css">
#footer{
	position:absolute;
	left:60px;
	top:220px;
	
}
</style>
</head>
<body>
	<div id="divParticpants" >
	
	</div>
	<div id="footer" >
	<a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="javascript:submit()">提交</a>
	<a id="cancle" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('particiPants','destroy')">取消</a></div>
</body>
</html>
