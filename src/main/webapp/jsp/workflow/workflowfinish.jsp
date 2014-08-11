<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>工作流管理</title>
        <%@ include file="/base/taglibs.jsp"%>
    	<%@ include file="/base/prefix.jsp"%>
        <script type="text/javascript" src="${ctx}/js/workflow/workflowfinish.js"></script>      
</head>
<body>
	<!-- 初始查询表单，用于可伸缩查询栏的初始状态 注意这里采用了样式searchrow。
        3. 。
    -->	    
    <form id="simpleform" class="searchrow" method="post">    	
    	<input type="hidden" name="queryCondition" value="user_name like ?"/>
        <!-- 对表单的每个控件元素，如果是输入框，且并非PLUIcombo类型组件，则应采用样式"combo"。
        	 对表单的每个控件元素，如果是combo组件，则应设置属性"width="80""。
         -->
        &nbsp;业务编号：<input type="text" name="user_name" class="combo" />
        <a class="plui-linkbutton" href="javascript:;" iconCls='icon-search' onclick="submit1();">查询</a>
    </form>
    <!-- 可伸缩查询栏使用的标签 -->        
    <div id="searchpanel"></div>
	<table id="table_user"  />
</body>
</html>
