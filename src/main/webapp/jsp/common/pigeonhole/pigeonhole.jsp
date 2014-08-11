<%@ page language="java" import="java.util.*" pageEncoding="GBK" import="com.szhome.security.ext.UserInfo"%>
<%
  UserInfo user=(UserInfo)session.getAttribute("userInfo");
  String userName=user.getUserName();
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <meta http-equiv="x-ua-compatible" content="ie=7" />
	<title>表单模板</title>
	<link rel="stylesheet" type="text/css" href="../../plui/themes/default/plui.css">
	<link rel="stylesheet" type="text/css" href="../../plui/themes/icon.css">
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
 <%@ include file="/base/taglibs.jsp"%>
  <%@ include file="/base/prefix.jsp"%>
  <script type="text/javascript" src="${ctx}/js/common/pigeonhole/pigeonhole.js"></script>	
    <script type="text/javascript"> 	
var user = '<%=userName%>';	
  var ctx = '${ctx}';	 
</script>
</head>
<body style="padding:5px;">
	<div class="page_con" style="width:800px">
	<div class="datagrid-wrap panel-body">
	 <div style="background-color: rgb(224, 236, 255);line-height:20px" class="panel-header">
		 &nbsp; &nbsp;<label style="font-size: 14px;font-weight: bold;">归档信息</label>
		</div>  
		</br>
    	<form id="main_form" name="userForm" method="post">
    	
        
        <table class="tab_gd" style="width: 100%;">
				<tr>
					<td class="title bg1" style="width:150px;">登记编号：</td>
					<td class="td_1"><input value="" id="reg_code" name="reg_code" style="width:200px;" class="plui-validatebox input" disabled="disabled"/></td>
					<td class="title bg1" style="width:150px;">归档号：</td>
					<td class="td_2"><input value=""  id=arch_no name="arch_no" style="width:200px;" class="plui-validatebox input" disabled="disabled"/>


					</td>
				</tr>
				<tr>
					<td class="title bg2" style="width:150px;">移交人：</td>
					<td class="td_1"><input  value="" id="transfer" name="transfer" style="width:200px;" class="plui-validatebox input" disabled="disabled"/>
					</td>
					<td class="title bg2" style="width:150px;">移交日期：</td>
					<td class="td_2"><input value="" id="transfer_date" name="transfer_date" style="width:200px;" class="plui-validatebox input" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="title bg1" style="width:150px;">归档人：</td>
					<td class="td_1"><input value="" id="arch_handler" name="arch_handler" style="width:200px;" class="plui-validatebox input" disabled="disabled"/></td>
                     <td class="title bg1" style="width:150px;">归档日期：</td>
					<td class="td_2"><input value="" id="arch_date" name="arch_date" style="width:200px;" class="plui-validatebox input" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="title bg1" style="width:150px;">档案盒编号：</td>
					<td class="td_1"><input value="" id="arbox_code" name="arbox_code" style="width:200px;" data-options="required:true" class="plui-validatebox input"/></td>
                     <td class="title bg1" style="width:150px;">归档人编号：</td>
					<td class="td_2"><input value="" id="arch_handler_no" name="arch_handler_no" style="width:200px;" data-options="required:true" class="plui-validatebox input" disabled="disabled"/></td>
				</tr>
			</table>
        
        <br/>
        <div style=" text-align:center;">
           <a id="submit" class="plui-linkbutton" iconCls="icon-save">归档</a>
            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="cancelSave()">取消</a>
        </div>
        </br>
        </form>
    </div>
</div>    
</body>
</html>
