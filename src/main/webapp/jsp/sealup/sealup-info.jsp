<%@ page language="java" import="java.util.*" pageEncoding="GBK"
	contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>登记信息</title>
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
<script type="text/javascript">
var ctx = '${ctx}';
</script>
<script type="text/javascript" src="${ctx}/js/sealup/sealup-info.js"></script>
</head>
<body class="body_set">
<div  class="plui-layout" style="width:100%;height:100%;">  
	<div data-options="region:'center',border:true">	 
         <div class="page_con">        
		<form id="attach" class="searchrow" method="post">		
		<div class="datagrid-wrap panel-body">
		</br>
			<!-- 查封相关信息 -->
			<div class="datagrid-wrap panel-body">
			<div>
			<table class="attach_tab" style="width: 100%;"  disabled="disabled">
			    <tr>
					<td class="title bg1" style="width:100px;">查封机关：</td>
					<td class="td_2"><input id="dis_off" name="dis_off" style="width:200px;" class="plui-validatebox input" data-options="required:true,validType:'intOrFloat'" />
                     <td class="title bg1" style="width:100px;">查封文号：</td>
					<td class="td_2"><input  id="dis_no" name="dis_no" style="width:200px;" class="plui-validatebox input" data-options="required:true,validType:'chinese'"/></td>
				</tr>
				<tr>
					<td class="title bg2" style="width:100px;">法律文书：</td>
					<td class="td_1"><input  id="law_doc" name="law_doc" style="width:200px;" class="plui-validatebox input" data-options="required:true"/></td>
					<td class="title bg2" style="width:100px;">被查封人：</td>
					<td class="td_2"><input id="lim_holder" name="lim_holder" style="width:200px;" class="plui-validatebox input" data-options="required:true,validType:'intOrFloat'" />
					</td>
				</tr>
				
				<tr>
					<td class="title bg1" style="width:100px;">查封日期：</td>
					<td class="td_1"><input  id="dis_date" name="dis_date" style="width:200px;" class="plui-datebox input com" data-options="required:true"/></td>
					<td class="title bg1" style="width:100px;">查封期限：</td>
					<td class="td_2"><input  id="dis_limit" name="dis_limit" style="width:200px;" class="plui-validatebox input" data-options="required:true"/></td>
					</td>
				</tr>
				 <tr>
					<td class="title bg2" style="width:100px;">查封范围：</td>
					<td class="td_2"><input id="dis_type" name="distrain.dis_range" style="width:200px;" class="plui-validatebox input" data-options="required:true,validType:'intOrFloat'" />
					<td class="title bg2" style="width:100px;">预售合同号：</td>
					<td class="td_2"><input id="pre_con_no" name="distrain.pre_con_no" style="width:200px;" class="plui-validatebox input" data-options="required:true,validType:'intOrFloat'" />
				<tr>
					<td class="title bg1" style="width:100px;">起始日期：</td>
					<td class="td_1"><input  id="start_date" name="start_date" style="width:200px;" class="plui-datebox input com" data-options="required:true"/></td>
					</td>
					<td class="title bg1" style="width:100px;">终止日期：</td>
					<td class="td_2"><input  id="end_date" name="end_date" style="width:200px;" class="plui-datebox input com" data-options="required:true"/></td>
				</tr>
				<tr>
					<td class="title bg2" style="width:100px;">预售合同号：</td>
					<td class="td_2"><input id="pre_con_no" name="distrain.pre_con_no" style="width:200px;" class="plui-validatebox input" data-options="required:true,validType:'intOrFloat'" />
				</tr>
				<tr>
					<td class="title bg1" style="width:100px;">送达人：</td>
					<td class="td_1"><input  id="service_name" name="service_name" style="width:200px;" class="plui-validatebox input" data-options="required:true"/></td>
					<td class="title bg1" style="width:100px;">联系方式：</td>
					<td class="td_2"><input id="dis_per_tel" name="dis_per_tel" style="width:200px;" class="plui-numberbox input" data-options="required:true,validType:'intOrFloat'" />
					</td>
				</tr>
				<tr>
					<td class="title bg2" style="width:100px;">工作证号：</td>
					<td class="td_1"><input  id="workid" name="workid" style="width:200px;" class="plui-validatebox input" data-options="required:true"/></td>
					<td class="title bg2" style="width:100px;">送达日期：</td>
					<td class="td_2"><input  id="service_date" name="service_date" style="width:200px;" class="plui-datebox input com" data-options="required:true"/></td>
					</td>
				</tr>
				<tr>
				<td class="title bg1" style="width:100px;">查封备注：</td>
					<td colspan="4" scope="col" >
					<textarea id="remark" value="" name="remark" style="height:80px;width:87%;font-size:14px" ></textarea>
	             </td>
				</tr>
			</table>
			</br>
			</div>
		</div>
			</div>
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
