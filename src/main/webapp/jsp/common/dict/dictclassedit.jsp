<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp" %>
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
<script type="text/javascript">
$(function() {
	//init form
	$('#dictclass_edit_form').form({
		url : "../dict!updateClass.action",
		success : function(data) {
			var data = $.parseJSON(data);
			if (data.result) {
				top.$.messager.alert('提示', data.message, 'info', function(){
					openerWindow.dictDataGrid.datagrid('reload');
					closeInTopWindow('edit_dictclass_win');
					//openerWindow.$('#productTree').tree('select', openerWindow.$('#productTree').tree('find', $('#product_id').combotree('getValue')).target);
				});
			} else {
				top.$.messager.alert('错误', data.errorMessage, 'error', function(){});
			}
		}
	});
	
});
</script>
<title>新增字典类型</title>
</head>
<body>
	<div class="plui-panel" data-options="fit:true,border:false">
		<form id="dictclass_edit_form" method="post">
			<input type="hidden" name="class_id" />
			<table class="formtable">
				
				<tr>
					<td class="title" style="width:100px;">分类名称：</td>
					<td><input name="class_name" type="text" /></td>
					<td class="title" style="width:100px;">分类代码：</td>
					<td><input name="class_code" type="text" /></td>
				</tr>
				<tr>
					<td class="title">所属产品：</td>
					<td colspan="3"><input id="product_id" name="product_id" class="plui-combotree" url="productDelegate/getProductTreeJson.run" panelHeight="120" /></td>
				</tr>
				<tr>
					<td class="title">备注：</td>
					<td colspan="3">
						<textarea name="remark" rows="5" style="width:400px;"></textarea>
					</td>
				</tr>
			</table>
		</form>
		<div style="text-align:center">
            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="$('#dictclass_edit_form').submit();">保存</a>
            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('edit_dictclass_win');">取消</a>
        </div> 
	</div>
</body>
</html>
