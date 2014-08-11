<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp" %>
<style type="text/css">
.form_table {
}
table, td, input, textarea, select{
	margin-left:0;
	margin-right:0;
	padding-left:0;
	padding-right:0;
}
input, textarea, select{
	margin-top:5px;
	margin-bottom:5px;
}

.form_table .title{
	text-align:right;
	font-size:13px;
}
.form_table .text, .form_table .textarea{
	text-align:left;				
	font-size:13px;
}
.form_table input{
	border:solid 1px #95b8e7;
	height:18px;
}
.form_table textarea{
	border:solid 1px #95b8e7;
}
</style>
<script type="text/javascript">
	$(function() {
		//init form
		$('#dictitem_add_form').form({
			url : "../dict!saveDicItem.action",
			success : function(data) {
				var data = $.parseJSON(data);
				if (data.result) {
					top.$.messager.alert('成功',"保存成功！","info",function(){
						openerWindow.itemDataGrid.datagrid('reload');
						closeInTopWindow('add_dictitem_win');
						
					});
				} else {
					top.$.messager.alert('错误', '数据保存失败！', 'error', function(){});
				}
			}
		});
		
	});
</script>
<title>新增字典项</title>
</head>
<body>
	<div class="plui-panel" data-options="fit:true,border:false">
		<form id="dictitem_add_form" method="post">
			<input type="hidden" name="dic_type_id" />
			<table class="form_table">
				<tr>
					<td class="title" style="width:100px;">名称：</td>
					<td><input name="dic_item_value" type="text" class="plui-validatebox" data-options="required:true"/></td>
					<td class="title" style="width:100px; ">值：</td>
					<td><input name="dic_item_code" type="text" class="plui-validatebox" data-options="required:true"/></td>
				</tr>
				<tr>
					<td class="title">是否有效：</td>
					<td>
						<select name="b_deleteflag" class="plui-combobox" editable="false" panelHeight="50">
							<option value="1">有效</option>
							<option value="2">无效</option>
						</select>
					</td>
					
				</tr>
				
				<tr>
				<td colspan="3">
					<div style="text-align:center">
			            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="$('#dictitem_add_form').submit();">保存</a>
			            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('add_dictitem_win');">取消</a>
			        </div>
		        </td>
		        </tr>
			</table>
		</form>
	</div>
</body>
</html>
