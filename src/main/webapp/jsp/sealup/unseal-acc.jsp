<%@ page language="java" import="java.util.*" pageEncoding="GBK"
	contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>登记信息</title>
<meta http-equiv="content-type" content="text/html;charset=GBK">
	<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">
				<meta http-equiv="keywords" content="">

		 <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
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
<link rel="stylesheet" type="text/css" href="../../plui/demo/demo.css">
<link rel="stylesheet" type="text/css" href="../../plui/themes/gray/plui.css">
<script type="text/javascript">
	var ctx = '${ctx}';
</script>
<script type="text/javascript" src="${ctx}/js/sealup/unseal-acc.js"></script>
</head>
<body class="body_set">
<div  class="plui-layout" style="width:100%;height:100%;">  
	<div data-options="region:'center',border:true">	 
         <div class="page_con">        
		<form id="add_app_form" class="searchrow" method="post">		
          <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">登记信息</label>
		</div> 
		
		<div class="datagrid-wrap panel-body">
		</br>
			<table class="edit_table" style="width: 100%;">
				<tr>
					<td class="title bg1" style="width:100px;">登记编号：</td>
					<td class="td_1"><input value="" id="REG_CODE" name="REG_CODE" style="width:200px;" class="plui-validatebox input" disabled="disabled"/></td>
					<td class="title bg1" style="width:100px;">登记类型：</td>
					<td class="td_1"><input class="plui-combodict" value="" code="061" id="REG_TYPE" name="REG_TYPE" style="width:200px;" class="plui-validatebox input" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td class="title bg2" style="width:100px;">登记点：</td>
					<td class="td_1"><input class="plui-combodict" value="" code="054" id="REG_STATION" name="REG_STATION" style="width:200px;" class="plui-validatebox input" />
					</td>
					<td class="title bg2" style="width:100px;">业务描述：</td>
					<td class="td_2"><input value="" id="BUS_DETAIL" name="BUS_DETAIL" style="width:200px;" class="plui-validatebox input" disabled="disabled"/></td>
				</tr>
				<br/>
			</table>
			<table  class="edit_table" style="width: 100%;">
			<tr>
					<td class="title bg1" style="width:100px;">解封机关：</td>
					<td class="td_2"><input id="redis_off" name="distrain.dis_off" style="width:200px;" class="plui-validatebox input" data-options="required:true,validType:'intOrFloat'" />
                     <td class="title bg1" style="width:100px;">解封文号：</td>
					<td class="td_2"><input  id="redis_no" name="distrain.dis_no" style="width:200px;" class="plui-validatebox input" data-options="required:true,validType:'chinese'"/></td>
				</tr>
				<tr>
					<td class="title bg2" style="width:100px;">法律文书：</td>
					<td class="td_1"><input  id="relaw_doc" name="distrain.law_doc" style="width:200px;" class="plui-validatebox input" data-options="required:true"/></td>
					<td class="title bg2" style="width:100px;">解封时间：</td>
					<td class="td_1"><input  id="redis_date" name="distrain.dis_date" style="width:200px;" class="plui-datebox input com" data-options="required:true"/></td>
					</td>
				</tr>
				<tr>
                     <td class="title bg1" style="width:100px;">查封类型：</td>
					<td class="td_2"><input class="plui-combodict" code="067" id="redis_type" name="distrain.dis_type" style="width:200px;" class="plui-combodict input" data-options="required:true,validType:'chinese'"/></td>
				</tr>
				<tr>
					<td class="title bg2" style="width:100px;">送达人：</td>
					<td class="td_1"><input  id="reservice_name" name="distrain.service_name" style="width:200px;" class="plui-validatebox input" data-options="required:true"/></td>
					<td class="title bg2" style="width:100px;">联系方式：</td>
					<td class="td_2"><input id="redis_per_tel" name="distrain.dis_per_tel" style="width:200px;" class="plui-numberbox input" data-options="required:true,validType:'intOrFloat'" />
					</td>
				</tr>
				<tr>
					<td class="title bg1" style="width:100px;">工作证号：</td>
					<td class="td_1"><input  id="reworkid" name="distrain.workid" style="width:200px;" class="plui-validatebox input" data-options="required:true"/></td>
					<td class="title bg1" style="width:100px;">送达日期：</td>
					<td class="td_2"><input  id="reservice_date" name="distrain.service_date" style="width:200px;" class="plui-datebox input com" data-options="required:true"/></td>
					</td>
				</tr>
			</table>
		</div>
		</form>
		<form id="attach_form" class="searchrow" method="post">		
         
		<table id="table_attach"></table>
		<table id="table_house"></table>
		     <div style="text-align:center;display:none">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" >保存</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('add_user_win');">取消</a>
	        </div>
               
		</form>
		</div>
	</div>
</div>
</body>
</html>
