<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK" import="com.szhome.security.ext.UserInfo"%>
<%
  UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
	String userName=userInfo.getUserName();
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp"%>
<script type="text/javascript" src="${ctx}/js/sysmanage/edituserrole.js"></script>
  <script type="text/javascript">
  var ctx = '${ctx}';
  var user = '<%=userName%>';
  var userid = $('#user_id').val();
  </script>
<style type="text/css">
.formtable input{
	width:150px;
}
.formtable .text{
	width:150px;
}
.formtable  .title{
	text-align:right;
	width:80px;
	font-size:13px;
}
</style>
<title>编辑用户</title>
</head>
<body>
	<div class="plui-panel" data-options="fit:true,border:false">
		<form id="userrole_edit_form" method="post">
			<table class="formtable">
				<tr>
					<td class="title">用户ID：</td>
					<td><input readonly id="userid" name="user_id" data-options="required:true" /></td>
				</tr>
				<tr>
					<td class="title">用户名称：</td>
					<td><input readonly id="username" name="user_name" type="text"/></td>
				</tr>
				<tr id="trcreator">
					<td class="title">创建人：</td>
					<td><input readonly id="creator" name="creator" data-options="required:true" value="999999999"/></td>
				</tr>
				<tr id="trcreatedate">
					<td class="title">创建时间：</td>
					<td><input type="text" id="createdate" name="createdate" style="width:150px" /></td>	
				</tr>
				<tr>
					<td class="title">角色：</td>
					<td><input id="roleid" name="roleid" data-options="cascadeCheck:false" multiple style="width:155px" panelHeight="100" /></td>	
				</tr>
			</table>
		</form>
		<br/><br/>
		<div style="text-align:center">
            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="submit()">授权</a>
            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('edit_userrole_win');">取消</a>
        </div> 
	</div>
</body>
</html>
