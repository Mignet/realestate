<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新增建设项目</title>
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp" %>
  <script type="text/javascript" src="${ctx}/js/basedata/project-add.js"></script>
  <script type="text/javascript">
  var ctx = '${ctx}';
  </script>
    </head>
    <body>
    	<div class="plui-panel" data-options="fit:true,border:false">
	    	<form id="add_project_form" method="post"></form>
	        <div style="text-align:center">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="$('#add_project_form').submit();">保存</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('add_project_win');">取消</a>
	        </div>
		</div>
    </body>
</html>
