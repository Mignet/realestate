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
<script type="text/javascript" src="${ctx}/js/common/menu/editmenu.js"></script>
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
<title>编辑菜单</title>
</head>
<body>
	<div class="plui-panel" data-options="fit:true,border:false">
		<form id="product_edit_form" method="post">
			<input type="hidden" name="menu.menu_name" id="mc1"/>
			<input type="hidden" name="menu.parent_id" id="ss1"/>
			<input type="hidden" name="menu.url"  id="url1"/>
			<input type="hidden" name="menu.creator" id="cjr1"/>
			<input type="hidden" name="menu.create_date" id="cjsj1"/>
			<input type="hidden" name="menu.menu_id" id="id1"/>
			<input type="hidden" name="menu_id" id="id"/>
			<input type="hidden" name="menu.menu_order" id="menu_order1"/>
			<table class="formtable">
				<tr>
					<td class="title">菜单名称：</td>
					<td><input id="mc" class="plui-validatebox" name="menu_name" data-options="required:true" /></td>
					<td class="title">所属菜单：</td>
					<td><input id="parent_id" name="parent_id" type="text" class="plui-combotree" url="../../leftmenu/menu!getFormTreeJson.action" panelHeight="180" /></td>
				</tr>
				<tr>
					<td class="title">链接地址：</td>
					<td><input id="url" class="plui-validatebox" name="url" data-options="required:true" /></td>
					<td class="title">修改人：</td>
					<td><input id="cjr" class="plui-validatebox" name="creator" data-options="required:true"  readonly="readonly" /></td>
				</tr>
				<tr>
					<td class="title">修改时间：</td>
					<td><input id="cjsj" name="create_date" class="plui-datetimebox" readonly="readonly" /></td>	
					<td class="title">序号：</td>
					<td><input id="menu_order" name="menu_order" class="plui-validatebox" readonly="readonly"/></td>
									
				</tr>				
			</table>
		</form>
		<div style="text-align:center">
            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="submit()">保存</a>
            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('edit_product_win');">取消</a>
        </div> 
	</div>
</body>
</html>
