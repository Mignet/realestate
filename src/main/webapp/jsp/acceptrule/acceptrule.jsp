<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>表单及报表配置</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
        
<%@ include file="/base/taglibs.jsp"%>
  <%@ include file="/base/prefix.jsp"%>
  <script type="text/javascript" src="${ctx}/js/acceptrule/acceptrule.js"></script>	
    <script type="text/javascript"> 	
  var ctx = '${ctx}';	 
</script>
         <style type="text/css">
    	body,html{margin:0px; font-family:Arial; font-size:12px; color:#333333;}
		.tip{color:#3CF;}
		.title {text-align: right;}	
		.bg1 {background: none repeat scroll 0 0 #E0ECFF;}	
		.bg2 { background: none repeat scroll 0 0 #F4F4F4;}
		.panel-body {
    background: none repeat scroll 0 0 #F8FAFF;
}
    </style> 
</head>
<body class="plui-layout">
	<div data-options="region:'west',split:true,title:'表单报表列表'" style="width:200px;">
		<ul id="getProcName" />
	</div>
	<div data-options="region:'center'" border=false>
	<div class="datagrid-wrap panel-body"  style="height:100%;">
		</br>
		</br>
		</br>
        <table id="tab_djxx" style="width: 100%;  height:80%;" border=1px>
				<tr style="height:15%;">
					<td class="title bg1" style="width:140px;font-size:20px;" >前置条件:</td>
					<td  class="td_1" id="preaudit"></td>				
				</tr>
				<tr style="height:15%;">
					<td class="title bg1" style="width:140px;font-size:20px;">限制条件:</td>
					<td  class="td_1" id="limit"></td>							
				</tr>
				<tr style="height:15%;">
					<td class="title bg1" style="width:140px;font-size:20px;">提示:</td>
					<td  class="td_1" id="message"></td>							
				</tr>
			</table>
			</div>
			</div>
	<!-- <div data-options="region:'center'" border=false>
		<table id="table_user">
		<tr style="width:500px;">
		<td >前置条件:</td>
		<td id="preaudit"><input type="checkbox">qqqq</input></td>
		</tr>
		<tr>
		<td>限制条件:</td>
		<td id="limit"><input type="checkbox">123</input><input type="checkbox">123</input></td>
		<tr>
		<td>提示:</td>
		<td id="message"><input type="checkbox">321</input><input type="checkbox">321</input></td>
		</tr>
		</table>
	</div> -->
</body>
</html>
