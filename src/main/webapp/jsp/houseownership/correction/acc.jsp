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
<script type="text/javascript" src="${ctx}/js/houseownership/correction/acc.js"></script>
</head>
<body class="body_set">
<div  class="plui-layout" style="width:100%;height:100%;">  
<form id="main_form">
	<div data-options="region:'center',border:true">	 
         <div class="page_con">        
		<input type="hidden" id="djbh1" name="oivo.reg_code"/>
		<input type="hidden" id="djlx1" name="oivo.reg_type"/>
		<input type="hidden" id="djd1" name="oivo.reg_station"/>
		<input type="hidden" id="ywms1" name="oivo.proc_name"/>
		<input type="hidden" id="xmmc1" name="oivo.proj_name"/>
	
		<input type="hidden" id="fdczfj1" name="oivo.excursus"/>
		
		
		
		
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
					<td class="title bg2" style="width:200px;">登记点：</td>
					<td class="td_1"><input class="plui-combodict" value="" code="054" id="REG_STATION" name="REG_STATION" style="width:200px;" class="plui-validatebox input"/>
					</td>
					<td class="title bg2" style="width:200px;">业务描述：</td>
					<td class="td_2"><input value="" id="BUS_DETAIL" name="BUS_DETAIL" style="width:200px;" class="plui-validatebox input" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="title bg1" style="width:200px;">房地产所属区：</td>
					<td class="td_1"><input class="plui-combodict" value="" code="065" id="LOCATION_REG_UNIT" name="LOCATION_REG_UNIT" style="width:200px;" class="plui-validatebox input"/>
					</td>
				</tr>
				<!--
				<tr>
					 
					<td class="title bg1" style="width:200px;">转移方式：</td>
					<td class="td_1"><input class="plui-combodict" code="house_get_mode" value="" id="GET_MODE" name="GET_MODE" style="width:200px;" class="plui-validatebox input" /></td>
                    
                    <td class="title bg1" style="width:200px;">业务主办部门：</td>
                    <td class="td_1">
                    	<select id="select_develop" style="width:200px">
                    		<option>登记中心</option>
                    	</select>
					</td>
				</tr>
				-->
				<tr id="tr_excursus">
					<td class="bg2"><div style="margin-top:-40px;text-align:right">房地产证附记：</div></td>
					<td colspan="3">
						<textarea type="textarea" wrap="soft" onKeyDown="limitLength(this,400)"
                 onKeyUp="limitLength(this,400)" onPaste="limitLength(this,400)"  value="" id="EXCURSUS" name="EXCURSUS" style="height:80px;width:100%;font-size:14px"  maxlength="100" disabled="disabled"></textarea>
					</td>
				</tr>
				
				<!-- 
				<tr>
					<td class="title bg1" style="width:200px;">登记价款：</td>
					<td class="td_1"><input   class="plui-numberbox" value="" id="REG_VALUE" name="REG_VALUE" style="width:200px;" data-options="min:0,precision:2" class="plui-validatebox input" /></td>
				</tr>
				 -->
			</table>
			
			</br>
			</div>
			
			<table id="table_house"></table>
			
			
            <table id="table_user"></table>
            
		     <div id="change_div">
		     <div id="change_div_header"class="panel-header" >
		          &nbsp;<label style="font-size: 12px;font-weight: bold;">更正内容</label>
		        </div> 
		        <div class="datagrid-wrap panel-body">
		     	<table width="100%" id="table_cbx">
		     		<!-- 
		     		<tr>
		     			<td>
		     				<input id="house_name"  type="checkbox" onclick="cbxClick(this)"/><label for="house_name" id="lbl_house_name">房地产名称</label>
		     			</td>
		     			<td>
		     				<input id="build_area"  type="checkbox" onclick="cbxClick(this)"/><label for="build_area" id="lbl_build_area">建筑面积</label>
		     			</td>
		     			<td>
		     				<input id="taonei_area"  type="checkbox" onclick="cbxClick(this)"/><label for="taonei_area" id="lbl_taonei_area">套内面积</label>
		     			</td>
		     			<td>
		     				<input id="useage"  type="checkbox" onclick="cbxClick(this)"/><label for="useage" id="lbl_useage">用途变更</label>
		     			</td>
		     			<td>
		     				<input id="reg_value"  type="checkbox" onclick="cbxClick(this)"/><label for="reg_value" id="lbl_reg_value">登记价格变更</label>
		     			</td>
		     			
		     		</tr>
		     		<tr>
		     			<td>
		     				<input id="house_location"  type="checkbox" onclick="cbxClick(this)"/><label for="house_location" id="lbl_house_location">房屋坐落变更</label> 
		     			</td>
		     			<td>
		     				<input id="house_attr"  type="checkbox" onclick="cbxClick(this)"/><label for="house_attr" id="lbl_house_attr">购房性质</label>
		     			</td>
		     			<td>
		     				<input id="holder_name"  type="checkbox" onclick="cbxClick(this)"/><label for="holder_name" id="lbl_holder_name">权利人名称变更</label>
		     			</td>
		     			<td>
		     				<input id="id_no"  type="checkbox" onclick="cbxClick(this)"/><label for="id_no" id="lbl_id_no">身份证号变更</label>
		     			</td>
		     		</tr>
		     	-->
		     	
		     	</table>
		     	
		     	
		     	<div id="div_change_detail"></div>
		     </div>
		     </div>
		     <!--  
		     <div style="text-align:center">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" >保存</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('add_user_win');">取消</a>
	        </div>
             -->  
		</div>
	</div>
 </form>
 </div>
</body>
</html>
