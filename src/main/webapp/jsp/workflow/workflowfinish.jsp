<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>����������</title>
        <%@ include file="/base/taglibs.jsp"%>
    	<%@ include file="/base/prefix.jsp"%>
        <script type="text/javascript" src="${ctx}/js/workflow/workflowfinish.js"></script>      
</head>
<body>
	<!-- ��ʼ��ѯ�������ڿ�������ѯ���ĳ�ʼ״̬ ע�������������ʽsearchrow��
        3. ��
    -->	    
    <form id="simpleform" class="searchrow" method="post">    	
    	<input type="hidden" name="queryCondition" value="user_name like ?"/>
        <!-- �Ա���ÿ���ؼ�Ԫ�أ������������Ҳ���PLUIcombo�����������Ӧ������ʽ"combo"��
        	 �Ա���ÿ���ؼ�Ԫ�أ������combo�������Ӧ��������"width="80""��
         -->
        &nbsp;ҵ���ţ�<input type="text" name="user_name" class="combo" />
        <a class="plui-linkbutton" href="javascript:;" iconCls='icon-search' onclick="submit1();">��ѯ</a>
    </form>
    <!-- ��������ѯ��ʹ�õı�ǩ -->        
    <div id="searchpanel"></div>
	<table id="table_user"  />
</body>
</html>
