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
	$('#dictclass_add_form').form({
		url : "../dict!saveClass.action",
		success : function(data) {
			var data = $.parseJSON(data);
			if (data.result) {
				top.$.messager.alert('�ɹ�',"����ɹ���","info",function(){
					openerWindow.dictDataGrid.datagrid('reload');
					closeInTopWindow('add_dictclass_win');
					
				});
					
				
			} else {
				top.$.messager.alert('����', data.errorMessage, 'error', function(){});
			}
		}
	});
});
</script>
<title>�����ֵ�����</title>
</head>
<body>
	<div class="plui-panel" data-options="fit:true,border:false">
		<form id="dictclass_add_form"  method="post">
			<table class="formtable">
				<tr>
					<td class="title">�������ƣ�</td>
					<td><input name="class_name" class="plui-validatebox" data-options="required:true" /></td>
					<td class="title">������룺</td>
					<td><input name="class_code" class="plui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td class="title">������Ʒ��</td>
					<td colspan="3"><input id="product_id" name="product_id" class="plui-combotree" url="productDelegate/getProductTreeJson.run" panelHeight="150" /></td>
				</tr>
				<tr>
					<td class="title">��ע��</td>
					<td colspan="3">
						<textarea name="remark" rows="5" style="width:400px;"></textarea>
					</td>
				</tr>
			</table>
		</form>
		<div style="text-align:center">
            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="$('#dictclass_add_form').submit();">����</a>
            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('add_dictclass_win');">ȡ��</a>
        </div>
	</div>
</body>
</html>
