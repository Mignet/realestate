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
<script type="text/javascript"> 	
var user = '<%=userName%>';	
var ctx = '${ctx}';	 
</script>
</head>
<body class="body_set">
<div  class="plui-layout" style="width:100%;height:100%;">  
<div data-options="region:'center'">
        <div class="page_con">
<!--  	<div class="plui-panel" data-options="fit:fasle,border:true" style="background-color:gray">-->
		<form id="add_gg_form" method="post">
		<input type="hidden" id="lcslbid" name="a.lcslbid"/> 
		<input type="hidden" id="ggr1" name="a.ggr"/> 
		<input type="hidden" id="ggsj1" name="a.ggsj"/> 
		<input type="hidden" id="ggcsr1" name="a.ggcsr"/> 
		<input type="hidden" id="ggcssj1" name="a.ggcssj"/> 
		<input type="hidden" id="ggfsr1" name="a.ggfsr"/> 
		<input type="hidden" id="ggfssj1" name="a.ggfssj"/> 
		<input type="hidden" id="ggshr1" name="a.ggshr"/> 
		<input type="hidden" id="ggshsj1" name="a.ggshsj"/> 
		<input type="hidden" id="gghzr1" name="a.gghzr"/> 
		<input type="hidden" id="gghzsj1" name="a.gghzsj"/> 
	
		
		
		
		
		
		<!-- 公告模版 -->
		<div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;" >草拟公告</label>
		</div> 
		<div class="datagrid-wrap panel-body">
			<table  >
				<tr>
					<th colspan="8" scope="col" style="width:auto">
					<textarea colspan="8" id="ggnr"
							name="a.ggnr" style="height:100px;width:700px" ></textarea>
					</th>
					<th width="65" scope="col">
							<input type="button" name="Submit" id="ggmb" value="公告模版" onclick="selectGgmb()"/>
						</th>
				</tr>
				<tr >
					<th width="123" scope="row">&nbsp;</th>
					<td width="71">&nbsp;</td>
					<td width="69">&nbsp;</td>
					<td width="76">&nbsp;</td>
					<td width="50" sytle="text-align:right">签名：</td>
					<td width="107"><input type="text" name="ggr" id="ggr"
						class="plui-validatebox input" data-options="required:true"
						style="width:140px;" value="" disabled="disabled" />
					</td>
					<td width="78" sytle="text-align:right">签名日期：</td>
					<td width="84"><input id="ggsj" name="ggsj" disabled="disabled"
						style="width:140px;" value=""/>
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			</div>
		<!-- 初步审定意见 -->	
		<div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;" >初步审定意见 </label>
		</div> 
		<div class="datagrid-wrap panel-body">
			<table  >
				<tr>
					<th colspan="8" scope="col" style="width:auto">
					<textarea colspan="8" id="ggcsyj"
							name="a.ggcsyj" style="height:100px;width:700px" ></textarea>
					</th>
					<th width="65" scope="col">
							<input type="button" name="Submit" id="ggcscyy" value="常用语" onclick="selectCyy()"/>
						</th>
				</tr>
				<tr >
					<th width="123" scope="row">&nbsp;</th>
					<td width="71">&nbsp;</td>
					<td width="69">&nbsp;</td>
					<td width="76">&nbsp;</td>
					<td width="50" sytle="text-align:right">签名：</td>
					<td width="107"><input type="text" name="ggcsr" id="ggcsr"
						class="plui-validatebox input" data-options="required:true"
						style="width:140px;" value="" disabled="disabled"/>
					</td>
					<td width="78" sytle="text-align:right">签名日期：</td>
					<td width="84"><input id="ggcssj" name="ggcssj" disabled="disabled"
						style="width:140px;" />
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			</div>
		
			<!-- 公告审定意见 -->	
			 <div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;" >公告审定意见</label>
		</div> 
		  <div class="datagrid-wrap panel-body">
			<table >
				<tr>
					<th colspan="8" scope="col" style="width:auto">
					<textarea colspan="8" id="ggfsyj"
							name="a.ggfsyj" style="height:100px;width:700px"></textarea>
					</th>
					<th width="65" scope="col">
							<input type="button" name="Submit"  id="ggfscyy" value="常用语" onclick="selectCyy()"/>
						</th>
				</tr>
				<tr >
					<th width="123" scope="row">&nbsp;</th>
					<td width="71">&nbsp;</td>
					<td width="69">&nbsp;</td>
					<td width="76">&nbsp;</td>
					<td width="50" sytle="text-align:right">签名：</td>
					<td width="107"><input type="text" name="ggfsr" id="ggfsr"
						class="plui-validatebox input" data-options="required:true"
						style="width:140px;" disabled="disabled"/>
					</td>
					<td width="78" sytle="text-align:right">签名日期：</td>
					<td width="84"><input id="ggfssj" name="ggfssj" disabled="disabled"
						style="width:140px;" />
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
         </div>
         <!-- 公告结果初审意见 -->	
			 <div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;" >公告结果初审意见</label>
		</div> 
		  <div class="datagrid-wrap panel-body">
			<table >
				<tr>
					<th colspan="8" scope="col" style="width:auto">
					<textarea colspan="8" id="ggshyj"
							name="a.ggshyj" style="height:100px;width:700px"></textarea>
					</th>
					<th width="65" scope="col">
							<input type="button" name="Submit"  id="ggshcyy" value="常用语" onclick="selectCyy()"/>
						</th>
				</tr>
				<tr >
					<th width="123" scope="row">&nbsp;</th>
					<td width="71">&nbsp;</td>
					<td width="69">&nbsp;</td>
					<td width="76">&nbsp;</td>
					<td width="50" sytle="text-align:right">签名：</td>
					<td width="107"><input type="text" name="ggshr" id="ggshr"
						class="plui-validatebox input" data-options="required:true"
						style="width:140px;" disabled="disabled"/>
					</td>
					<td width="78" sytle="text-align:right">签名日期：</td>
					<td width="84"><input id="ggshsj" name="ggshsj" disabled="disabled"
						style="width:140px;" />
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
         </div>
         <!-- 公告结果复审意见 -->	
          <div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;" >公告结果复审意见</label>
		</div> 
		<div class="datagrid-wrap panel-body">
			<table >
				<tr>
					<th colspan="8" scope="col" style="width:auto">
					<textarea colspan="8" id="gghzyj"
							name="a.gghzyj" style="height:100px;width:700px"></textarea>
					</th>
					<th width="65" scope="col">
							<input type="button" name="Submit" id="gghzcyy" value="常用语" onclick="selectCyy()"/>
						</th>
				</tr>
				<tr >
					<th width="123" scope="row">&nbsp;</th>
					<td width="71">&nbsp;</td>
					<td width="69">&nbsp;</td>
					<td width="76">&nbsp;</td>
					<td width="50" sytle="text-align:right">签名：</td>
					<td width="107"><input type="text" name="gghzr" id="gghzr"
						class="plui-validatebox input" data-options="required:true"
						style="width:140px;" disabled="disabled">
					</td>
					<td width="78" sytle="text-align:right">签名日期：</td>
					<td width="84"><input id="gghzsj" name="gghzsj" disabled="disabled"
						style="width:140px;" />
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			</div>
		</form>
		</br>
		  <div style="text-align:center">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="submit1()">保存</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('add_user_win');">取消</a>
	        </div>
</div>
</div>   
</div>
</body>
</html>
