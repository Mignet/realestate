<%@ page language="java" import="java.util.*" pageEncoding="GBK"
	contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp"%>
<title>登记信息</title>
					<style type="text/css">
body,html {
	margin: 0px;
	font-family: Arial;
	font-size: 12px;
	color: #333333;
}

.tip {
	color: #3CF;
}

.title {
	text-align: right;
}

.bg1 {
	background: none repeat scroll 0 0 #E0ECFF;
}

.bg2 {
	background: none repeat scroll 0 0 #F4F4F4;
}

.panel-body {
	background: none repeat scroll 0 0 #F8FAFF;
}
</style>
<script type="text/javascript">
	var ctx = '${ctx}';
</script>
<script type="text/javascript"
	src="${ctx}/js/houseownership/ownershipinitialreg/acc.js"></script>
</head>
<body  class="body_set">
	<div  class="plui-layout" style="width:800px;height:660px;">  
		<div data-options="region:'center',border:true">
			<div class="page_con">
				<form id="add_app_form" class="searchrow" method="post">
					<div
						style="background-color: rgb(224, 236, 255); line-height: 18px"
						class="panel-header">
						&nbsp;<label style="font-size: 12px; font-weight: bold;">登记信息</label>
					</div>

					<div class="datagrid-wrap panel-body">
						</br>
						<table class="edit_tab" style="width: 100%;">
							<tr>
								<td class="title bg1" style="width: 100px;">登记编号：</td>
								<td class="td_1"><input value="" id="djbh" name="djbh" style="width: 150px;" class="plui-validatebox input reg" disabled="disabled" /></td>
								<td class="title bg1" style="width: 100px;">登记类型：</td>
								<td class="td_2"><input class="plui-combodict" value="" id="djlx" code="061" name="djlx" style="width: 150px;" class="plui-validatebox input reg" disabled="disabled" /></td>
							</tr>
							<tr>
								<td class="title bg1" style="width: 100px;">房地产所属区：</td>
								<td class="td_1"><input class="plui-combodict" value="" code="054" id="djd" name="djd" style="width: 150px;" class="plui-validatebox input" /></td>
								<td class="title bg1" style="width: 100px;">业务描述：</td>
								<td class="td_2"><input value="" id="ywms" name="ywms" style="width: 150px;" class="plui-validatebox input reg" disabled="disabled" /></td>
							</tr>
							<tr>
									<td class="title bg1" style="width:100px;">取得方式：</td>
									<td class="td_1"><input class="plui-combodict" value="" code="004" id="qdfs" name="get_mode" style="width: 150px;" />
									</td>
							</tr>
						</table>
					</div>
					<table id="table_house"></table>
					<table id="table_user"></table>
					<div
						style="background-color: rgb(224, 236, 255); line-height: 18px; display: none"
						class="panel-header remark">
						&nbsp;<label style="font-size: 12px; font-weight: bold;">房地产证附记</label>
					</div>
					<div class="datagrid-wrap panel-body remark" style="display: none">
						<table id="tab_fdcz" style="width: 100%;">
							<tr>
								<td colspan="4" scope="col"><textarea value="" id="fdczfj"
										onKeyDown="limitLength(this,400)"
										onKeyUp="limitLength(this,400)"
										onPaste="limitLength(this,400)" name="excursus"
										style="height: 80px; width: 100%; font-size: 14px"
										disabled="disabled"></textarea></td>
							</tr>
						</table>

					</div>

					<div style="text-align: center; display: none">
						<a id="submit" class="plui-linkbutton" iconCls="icon-save"
							onclick="submit()">保存</a>
					</div>

				</form>
			</div>
		</div>
	</div>
</body>
</html>
