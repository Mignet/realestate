<%@ page language="java" import="java.util.*" pageEncoding="GBK"
	contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList" import="com.szhome.security.ext.UserInfo"%>
<%
  UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
	String userName=userInfo.getUserName();
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�û�����</title>
       	<%@ include file="/base/taglibs.jsp"%>
    	<%@ include file="/base/prefix.jsp"%>
    	 <script type="text/javascript">
    	var ctx = '${ctx}';</script>
    	<script type="text/javascript" src="${ctx}/js/qualityinspection/work-window.js">
    	
    	</script>
<style type="text/css">
body,html {
	margin: 0px;
	font-family: Arial;
	font-size: 12px;
	color: #333333;
	padding: 5px !important;
}

.tip {
	color: #3CF;
}
</style>
</head>
<body class="plui-layout" >
	<!-- ��ʼ��ѯ�������ڿ�������ѯ���ĳ�ʼ״̬ ע�������������ʽsearchrow��
        3. ��
    -->
	<!-- �Ա���ÿ���ؼ�Ԫ�أ������������Ҳ���PLUIcombo�����������Ӧ������ʽ"combo"��
        	 �Ա���ÿ���ؼ�Ԫ�أ������combo�������Ӧ��������"width="80""��-->
       <div data-options="region:'north'" border=false> 	
       	
			<div id="operation"
				style="width: 100%; height: 30px; background-color: #eeeeee;text-align:right;" >				
				<label style="float:left;margin:4px 0px 4px 4px;">��ǰ����Ϊ��</label>
				<label id="note" style="float:left;margin:4px 0px 0px 0px;"> </label>
				
			</div>
	
        </div>	 
    	 
	<div data-options="region:'west',split:true,title:'����Ҫ��',border:false"
		style="width: 200px;">
		<ul id="bustree"  />
	</div>
	<div id="iframe" data-options="region:'center'" border=false>	
		
		<!-- <iframe  id="in" name="in" height="800" width="900"
			allowtransparency="true" scrolling="yes" frameborder="0"></iframe> -->
	</div>

</body>
</html>
