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
<script type="text/javascript" src="${ctx}/js/common/menu/addmenu.js"></script>
  <script type="text/javascript">
  var ctx = '${ctx}';
  var user = '<%=userName%>';	
  </script>
<style type="text/css">
.formtable input{
	width:150px
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
<title>菜单</title>
</head>
<body>
	<div class="plui-panel" data-options="fit:true,border:false">
		<form id="product_add_form" method="post">
			<table class="formtable">
				<tr>
					<td class="title">菜单名称：</td>
					<td><input class="plui-validatebox" name="menu.menu_name" data-options="required:true" /></td>
					<td class="title">所属菜单：</td>
					<td><input id="parent_id" name="menu.parent_id" type="text" class="plui-combotree" url="../../leftmenu/menu!getFormTreeJson.action" panelHeight="180" /></td>
				</tr>
				<tr>
					<td class="title">链接地址：</td>
					<td><input class="plui-validatebox" name="menu.url" data-options="required:true" /></td>
					<td class="title">创建人：</td>
					<td><input id="creator" class="plui-validatebox" name="menu.creator" data-options="required:true" readonly="readonly"/></td>
				</tr>
				<tr>
					<td class="title">创建时间：</td>
					<td><input id="create_date" name="menu.create_date" class="plui-datetimebox" readonly="readonly" /></td>
					<td class="title">序号：</td>
					<td><input id="menu_order" name="menu.menu_order" class="tr.plui-datebox" readonly="readonly"/></td>
					
				</tr>
				
			</table>
		</form>
		<div style="text-align:center">
            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="submit()">保存</a>
            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('add_product_win');">取消</a>
        </div> 
	</div>
</body>
</html>
