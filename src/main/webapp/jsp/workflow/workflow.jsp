<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <title>�����в�����Ȩ�Ǽ�ϵͳ</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <script type="text/javascript">
     var ctx = '${ctx}';</script>
    <script type="text/javascript" src="${ctx}/js/workflow/workflow.js">
   
    </script>
    
</head>
<body class="plui-layout">
    <div data-options="region:'center'" border=false>
			<div class="plui-layout" fit=true>
				<div data-options="region:'north',split:false,border:false" style="height:30px;">
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
                <!-- <div id="searchpanel"></div> -->
             </div>
             <div data-options="region:'center',border:false">
					<table id="table_user"  />
			</div>
		</div>
	</div>
   
	
</body>
</html>
