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
<script type="text/javascript" src="${ctx}/js/remark/remarkacc.js"></script>
</head>
<body class="body_set">
<form id="add_app_form" class="searchrow" method="post" style="width:100%;height:100%;">	
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
			<!-- 备注登记信息 -->
			<div>
			<table class="remark_tab" style="width: 100%;" >
			    <tr>
					<td class="title bg1" style="width:100px;">录入人：</td>
					<td class="td_2"><input id="RECORDER" name="remarkInfo.recorder" style="width:200px;" class="plui-validatebox input" data-options="required:true,validType:'intOrFloat'" />
                     <td class="title bg1" style="width:100px;">录入日期：</td>
					<td class="td_2"><input  id="REG_DATE" name="remarkInfo.reg_date" style="width:200px;" class="plui-datebox input com" data-options="required:true"/></td>
				</tr>
				<tr>
					<td class="title bg2" style="width:100px;">申请备注机关：</td>
					<td class="td_1"><input id="REMARK_OFFICE" name="remarkInfo.remark_office" style="width:200px;" class="plui-validatebox input" data-options="required:true,validType:'intOrFloat'" />
					<td class="title bg2" style="width:100px;">备注依据文号：</td>
					<td class="td_2"><input  id="REMARK_NO" name="remarkInfo.remark_no" style="width:200px;" class="plui-validatebox input" data-options="required:true"/></td>
					</td>
				</tr>
				<tr>
					<td class="title bg2" style="width:100px;">被备注人：</td>
					<td class="td_2"><input  id="REMARK_PERSON" name="remarkInfo.remark_person" style="width:200px;" class="plui-validatebox input" data-options="required:true"/></td>
					<td class="title bg2" style="width:100px;">					
					备注类型：
					</td>
					<td class="td_1" visible="false">
					<input  class="plui-combodict" value="" code="072" id="REMARK_TYPE" name="REMARK_TYPE" style="width:200px;" class="plui-validatebox input" />
					</td>
				</tr>
				<tr>
				<td class="title bg1" style="width:100px;">备注内容：</td>
					<td colspan="4" scope="col" >
					<textarea value="" id="REMARK_CONTENT" name="remarkInfo.remark_content" style="height:80px;width:86%;font-size:14px" ></textarea>
	             </td>
				</tr>
			</table>
			</br>
			</div>
			</div>
			<table id="table_house"></table>
			<table id="table_user"></table>
		     <div style="text-align:center;display:none">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" >保存</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('add_user_win');">取消</a>
	        </div>
               
		
		</div>
	</div>
</div>
</form>
</body>
</html>
