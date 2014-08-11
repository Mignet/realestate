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
<script type="text/javascript" src="${ctx}/js/reissue/reissue.js"></script>
</head>
<body class="body_set">
<div  class="plui-layout" style="width:100%;height:100%;">  
<form id="main_form">
	<div data-options="region:'center',border:true">	 
         <div class="page_con">        
          <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">登记信息</label>
		</div> 
		
		<div class="datagrid-wrap panel-body">
		</br>
			<table class="edit_table" style="width: 100%;">
				<tr>
					<td class="title bg1" style="width:200px;">登记编号：</td>
					<td class="td_1"><input value="" id="REG_CODE" name="REG_CODE" style="width:200px;" class="plui-validatebox input" disabled="disabled"/></td>
					<td class="title bg1" style="width:200px;">登记类型：</td>
					<td class="td_1"><input class="plui-combodict" value="" code="061" id="REG_TYPE" name="REG_TYPE" style="width:200px;" class="plui-validatebox input" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td class="title bg2" style="width:200px;">登记点：</td>
					<td class="td_1"><input class="plui-combodict" value="" code="054" id="REG_STATION" name="REG_STATION" style="width:200px;" class="plui-validatebox input" disabled="disabled" />
					</td>
					<td class="title bg2" style="width:200px;">业务描述：</td>
					<td class="td_2"><input value="" id="BUS_DETAIL" name="BUS_DETAIL" style="width:200px;" class="plui-validatebox input" disabled="disabled"/></td>
				</tr>
				<tr id="tr_excursus">
					<td class="bg1"><div style="margin-top:-40px;text-align:right">房地产证附记：</div></td>
					<td colspan="3" >
						<textarea type="textarea" wrap="soft" onKeyDown="limitLength(this,100)"
                 onKeyUp="limitLength(this,100)" onPaste="limitLength(this,100)"  value="" id="EXCURSUS" name="EXCURSUS" style="height:80px;width:95%;font-size:14px;"  maxlength="100" disabled="disabled"></textarea>
					</td>
				</tr>
				<br/>
				
			</table>
			</div>
			</div>
			<div>
			 <table id="table_house"></table>
			<table id="table_user"></table>
               
		</div>
	</div>
</form>
</div>
</body>
</html>
