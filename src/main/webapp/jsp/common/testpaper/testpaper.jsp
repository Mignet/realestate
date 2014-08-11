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

 <script type="text/javascript"> 	
	var user = '<%=userName%>';	
  	var ctx = '${ctx}';	 
</script>
<script type="text/javascript" src="${ctx}/js/common/testpaper/testpaper.js"></script>
</head>
<body style="padding:5px;">
	<div class="page_con" style="width:800px">
	<div class="datagrid-wrap panel-body">
	 <div style="background-color: rgb(224, 236, 255);line-height:20px" class="panel-header">
		 &nbsp; &nbsp;<label style="font-size: 14px;font-weight: bold;">组卷信息</label>
		</div>  
		</br>
    	<form id="userForm" name="userForm" method="post">
        	<input type="hidden" name="reg_code" id="reg_code"></input>
        <table id="tab_zj" style="width: 100%;">
				
				<tr>
					<td class="title bg1" style="width:200px;">组卷人：</td>
					<td class="td_1"><input  value="" id="arranger" name="testpaper.arranger" style="width:200px;" class="plui-validatebox input" disabled="disabled"/>
					</td>
					<td class="title bg2" style="width:200px;">组卷日期：</td>
					<td class="td_2"><input value="" id="arrange_time" name="testpaper.arrange_time" style="width:200px;" class="plui-validatebox input" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="title bg1" style="width:200px;">组卷状态：</td>
					<td class="td_1"><input  value="" id="arrange_state" name="testpaper.arrange_state" style="width:200px;" class="plui-validatebox input" disabled="disabled"/>
					</td>
				</tr>
				
			</table>
        
        <br/>
          <div style=" text-align:center;">
           <a id="submit" class="plui-linkbutton" iconCls="icon-save">组卷</a>
            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="cancelSave()">取消</a>
        </div>
        </br>
        </form>
    </div>
</div>    
</body>
</html>
