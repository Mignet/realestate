<%@ page language="java" import="java.util.*" pageEncoding="GBK"
	contentType="text/html;charset=GBK" import="com.szhome.security.ext.UserInfo"%>
	<%
  UserInfo user=(UserInfo)session.getAttribute("userInfo");
  String userName=user.getUserName();
  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>审批表</title>
<meta http-equiv="content-type" content="text/html;charset=GBK">
	<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">
				<meta http-equiv="keywords" content="">
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

<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp"%>
<script type="text/javascript" src="${ctx}/js/mortgage/examine.js"></script>
<script type="text/javascript"> 	
var user = '<%=userName%>';	
var ctx = '${ctx}';		 
</script>
</head>
<body class="body_set">
<div  class="plui-layout" style="width:100%;height:100%;">  
<form id="main_form">
<div data-options="region:'center'">
	
        <div class="page_con">
<!--  	<div class="plui-panel" data-options="fit:fasle,border:true" style="background-color:gray">-->

		<form id="add_user_form" method="post" >	
		<!-- 初审意见 -->
		 	   <div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;" >初审意见</label>
		</div> 
		<div class="datagrid-wrap panel-body">
			<table  >
				<tr>
					<th colspan="8" scope="col" style="width:auto">
					<textarea colspan="8" id="csyj"
							name="ex.csyj" style="height:100px;width:700px" ></textarea>
					</th>
					<th width="65" scope="col">
							<input type="button" name="Submit" id="cscyy" value="常用语" onclick="selectCyy()"/>
						</th>
				</tr>
				<tr >
					<th width="123" scope="row">&nbsp;</th>
					<td width="71">&nbsp;</td>
					<td width="69">&nbsp;</td>
					<td width="76">&nbsp;</td>
					<td width="50" sytle="text-align:right">签名：</td>
					<td width="107"><input type="text" name="csr" id="csr"
						class="plui-validatebox input" data-options="required:true"
						style="width:140px;" value="" disabled="disabled"/>
					</td>
					<td width="78" sytle="text-align:right">签名日期：</td>
					<td width="84"><input id="cssj" name="cssj" disabled="disabled" value=""
						style="width:140px;" />
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			</div>
		
         <!-- 复审意见 -->	
         <div style="display:none">
			 <div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;" >复审意见</label>
		</div> 
		  <div class="datagrid-wrap panel-body" >
			<table >
				<tr>
					<th colspan="8" scope="col" style="width:auto">
					<textarea colspan="8" id="fsyj" 
							name="ex.fsyj" style="height:100px;width:700px"></textarea>
					</th>
					<th width="65" scope="col">
							<input type="button" name="Submit"  id="fscyy" value="常用语" onclick="selectCyy()"/>
						</th>
				</tr>
				<tr >
					<th width="123" scope="row">&nbsp;</th>
					<td width="71">&nbsp;</td>
					<td width="69">&nbsp;</td>
					<td width="76">&nbsp;</td>
					<td width="50" sytle="text-align:right">签名：</td>
					<td width="107"><input type="text" name="fsr" id="fsr"
						class="plui-validatebox input" data-options="required:true"
						style="width:140px;" disabled="disabled"/>
					</td>
					<td width="78" sytle="text-align:right">签名日期：</td>
					<td width="84"><input id="fssj" name="fssj" disabled="disabled"
						style="width:140px;" />
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
         </div>
         </div>
         
         <!-- 核准意见 -->
          <div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;" >核准意见</label>
		</div> 
		<div class="datagrid-wrap panel-body">
			<table >
				<tr>
					<th colspan="8" scope="col" style="width:auto">
					<textarea colspan="8" id="hzyj"
							name="ex.hzyj" style="height:100px;width:700px"></textarea>
					</th>
					<th width="65" scope="col">
							<input type="button" name="Submit" id="hzcyy" value="常用语" onclick="selectCyy()"/>
						</th>
				</tr>
				<tr >
					<th width="123" scope="row">&nbsp;</th>
					<td width="71">&nbsp;</td>
					<td width="69">&nbsp;</td>
					<td width="76">&nbsp;</td>
					<td width="50" sytle="text-align:right">签名：</td>
					<td width="107"><input type="text" name="hzr" id="hzr"
						class="plui-validatebox input" data-options="required:true"
						style="width:140px;" disabled="disabled">
					</td>
					<td width="78" sytle="text-align:right">签名日期：</td>
					<td width="84"><input id="hzsj" name="hzsj" disabled="disabled"
						style="width:140px;" />
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			</div>
		</form>
		</br>
		 <div style="text-align:center;display:none">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="submit()">保存</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('add_user_win');">取消</a>
	        </div>
</div>
</div>   
</form>
</div>
</body>
</html>
