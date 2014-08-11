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
<script type="text/javascript" src="${ctx}/js/landuseright/land-init-acc.js"></script>
</head>
<body class="body_set">
<div  class="plui-layout" style="width:100%;height:100%;">  
	<div data-options="region:'center',border:true">	 
         <div class="page_con">        
		<form id="acceptance" class="searchrow" method="post">
	
		<input type="hidden" id="fdczfj1" name="excursus"/>
		
		
		
		
          <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">登记信息</label>
		</div> 
		
		<div class="datagrid-wrap panel-body">
		</br>
			<table class="reg_tab" style="width: 100%;">
				<tr>
					<td class="title bg1" style="width:200px;">登记编号：</td>
					<td class="td_1"><input value="" id="djbh" name="djbh" style="width:200px;" class="plui-validatebox input reg" disabled="disabled"/></td>
					<td class="title bg1" style="width:200px;">登记类型：</td>
					<td class="td_2"><input  class="plui-combodict" value="" id="djlx" code="061"  name="djlx" style="width:200px;" class="plui-validatebox input reg" disabled="disabled"/>


					</td>
				</tr>
				<tr>
					<td class="title bg2" style="width:200px;">登记点：</td>
					<td class="td_1"><input class="plui-combodict" value="龙岗" code="054" id="djd" name="djd" style="width:200px;" class="plui-validatebox input"/>
					</td>
					<td class="title bg2" style="width:200px;">业务描述：</td>
					<td class="td_2"><input value="" id="ywms" name="ywms" style="width:200px;" class="plui-validatebox input reg" disabled="disabled"/></td>
				</tr>
				<!-- <tr>
					<td class="title bg1" style="width:200px;">项目名称：</td>
					<td class="td_1"><input value="" id="xmmc" name="xmmc" style="width:200px;" class="plui-validatebox input reg" disabled="disabled"/></td>
                    
				</tr> -->
			</table>
			
			</br>
			</div>
			<!-- 初始登记信息 -->
			<div style="display:none"  class="datagrid-wrap panel-body initreg">
			</br>
			<table class="init_tab" style="width: 100%;">
				<tr>
				<td class="title bg1" style="width:200px;">用途：</td>
					<td class="td_1"><input value="" id="land_use" name="land_use" code="015" style="width:200px;" class="plui-combodict input" /></td>
					<td class="title bg1" style="width:200px;">取得价格：</td>
					<td class="td_2"><input value="" id="get_price" name="get_price" style="width:200px;" class="plui-numberbox input" />
					</td>
				</tr>
				<tr>
				<td class="title bg1" style="width:200px;">使用权类型：</td>
					<td class="td_1"><input value="" id="use_type" name="useright_type" code="014" style="width:200px;" class="plui-combodict input" /></td>
					<td class="title bg2" style="width:200px;">使用期限：</td>
					<td class="td_2"><input value="" id="use_limit" name="use_limit" style="width:200px;" class="plui-numberbox input"/></td>
				</tr>
				<tr>
				<td class="title bg1" style="width:200px;">起始日期：</td>
					<td class="td_1"><input value="" id="start_date" name="start_date" style="width:200px;" class="plui-datebox input" /></td>
				<td class="title bg1" style="width:200px;">终止日期：</td>
					<td class="td_1"><input value="" id="end_date" name="end_date" style="width:200px;" class="plui-datebox input" /></td>
					
				</tr>
			</table>
			</br>
			</div>
			
			<table id="table_house"></table>
            <table id="table_user"></table>
            <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		          &nbsp;<label style="font-size: 12px;font-weight: bold;">房地产证附记</label>
		        </div> 
		        <div class="datagrid-wrap panel-body">
		      <table id="tab_fdcz" style="width: 100%;">
		         <tr>
              <td colspan="4" scope="col" >
					<textarea value="" id="fdczfj"  onKeyDown="limitLength(this,400)" onKeyUp="limitLength(this,400)" onPaste="limitLength(this,400)"
					name="fdczfj" style="height:80px;width:100%;font-size:14px" disabled="disabled"></textarea>
	             </td>
               </tr>   
		     </table>
		     
		     </div>
               
		</form>
		</div>
	</div>
</div>
</body>
</html>
