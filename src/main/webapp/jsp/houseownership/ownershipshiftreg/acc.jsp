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
<script type="text/javascript" src="${ctx}/js/houseownership/ownershipshiftreg/acc.js"></script>
</head>
<body class="body_set">
<form id="main_form" style="width:100%;height:100%;">
<div  class="plui-layout" style="width:100%;height:100%;">  

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
					<td class="td_1"><input class="plui-combodict" value="" code="061" id="REG_TYPE" name="REG_TYPE" style="width:200px;" class="plui-validatebox input"/>
					</td>
				</tr>
				<tr>
					<td class="title bg2" style="width:200px;">房地产所属区：</td>
					<td class="td_1"><input class="plui-combodict" value="" code="065" id="LOCATION_REG_UNIT" name="LOCATION_REG_UNIT" style="width:200px;" class="plui-validatebox input"/>
					</td>
					<td class="title bg2" style="width:200px;">业务描述：</td>
					<td class="td_2"><input value="" id="BUS_DETAIL" name="BUS_DETAIL" style="width:200px;" class="plui-validatebox input" disabled="disabled"/></td>
				</tr>
				<tr>
				<td class="title bg1" style="width:200px;">登记点：</td>
					<td class="td_1"><input class="plui-combodict" value="" code="054" id="REG_STATION" name="REG_STATION" style="width:200px;" class="plui-validatebox input"/>
					</td>
					
                    <td class="title bg1" style="width:200px;">购房性质：</td>
                    <td class="td_1"><input class="plui-combodict" code="021" id="HOUSE_ATTR" name="HOUSE_ATTR" style="width:200px;" class="plui-validatebox input"/>
					</td>
				</tr>
				<tr>
					<td class="title bg2" style="width:200px;">转移方式：</td>
					<td class="td_1"><input class="plui-combodict" code="055" value="" id="GET_MODE" name="GET_MODE" style="width:200px;" class="plui-validatebox input" /></td>
					<!-- 原型上暂时没有登记价款 暂时留着-->
					<td class="title bg2" style="width:200px;">登记价款：</td>
					<td class="td_1"><input type="text" class="plui-numberbox plui-validatebox input"  data-options="min:0,precision:2" groupSeparator="," id="REG_VALUE" name="REG_VALUE" style="width:200px;" maxlength="15" /></td>
					 
				</tr>
				<tr id="div_exccursus">
			      	<td class="bg1"><div style="margin-top:-38px;text-align:right">房地产证附记：</div></td>
            	  <td colspan="3" scope="col" >
					<textarea  value="" id="EXCURSUS" wrap="virtual" onKeyDown="limitLength(this,400)" onKeyUp="limitLength(this,400)" onPaste="limitLength(this,400)" name="EXCURSUS" style="height:80px;width:100%;font-size:14px" disabled="disabled"  maxlength="100" ></textarea>
	             </td>
		     </tr>
			</table>
			
			</br>
			</div>
			
			<table id="table_house"></table>
			
			<table id="table_transferor"></table>
			
            <table id="table_user"></table>
            
		     <!-- 
		     <div style="text-align:center">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" >保存</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('add_user_win');">取消</a>
	        </div>
              -->  
		</div>
	</div>
	
	</div>
</form>
</body>
</html>
