<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK" 
import="com.szhome.security.ext.UserInfo"%>
	<%
  UserInfo user=(UserInfo)session.getAttribute("userInfo");
  String userName=user.getUserName();
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>抽检样本选择</title>
        <%@ include file="/base/taglibs.jsp"%>
    	<%@ include file="/base/prefix.jsp"%>
    	<script type="text/javascript">
		var ctx = '${ctx}';
		var user = '<%=userName%>';	
		</script>
    	<script type="text/javascript" src="${ctx}/js/qualityinspection/reminder-statistics.js"></script>
         <style type="text/css">
    	body,html{margin:0px; font-family:Arial; font-size:12px; color:#333333;overflow:auto;}
		.tip{color:#3CF;}
		.title {text-align: right;}	
		.bg1 {background: none repeat scroll 0 0 #E0ECFF;}	
		.bg2 { background: none repeat scroll 0 0 #F4F4F4;}
		.panel-body {
    	background: none repeat scroll 0 0 #F8FAFF;
		}
    </style>        
</head>
<body>
		  <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">&nbsp;</label>
		</div> 
		
		<div class="datagrid-wrap panel-body">
		<form id="m_search_form" action="${ctx}/qualityinspection/qualityinspection!querySamele.action" method="post">
        <table id="tab_reg_info" style="width: 100%;">
				<tr>
					<td class="title bg1"  >登记类型：</td>
					<td class="td_2" colspan="3"><input class="plui-combodict" value="" code="061"  id="reg_type" name="reg_type" class="plui-validatebox input" /></td>
					<td>&nbsp;</td>
					<td class="title bg1"  >受理日期：</td>
					<td class="td_1" style="width:60px"><input class="plui-datebox" data-options="editable:false" style="width:90px" readonly="readonly"  value="" id="acc_start_date" name="acc_start_date"  /></td>
					<td class="td_1" style="width:10px"><label style="width:10px">至</label></td>
					<td class="td_1" style="width:60px"><input class="plui-datebox" data-options="editable:false" style="width:90px" readonly="readonly"  value="" id="acc_end_date" name="acc_end_date"  /></td>
					<td>&nbsp;</td>
					
					<td class="title bg1" >登记编号：</td>
					<td class="td_1" colspan="3"><input value="" id="reg_code" name="reg_code"  class="plui-validatebox input"/></td>
					
			
				</tr>
				<tr>
					<td class="title bg1" >业务描述：</td>
					<td class="td_1" colspan="3"><input value="" id="bus_detail" name="bus_detail"  class="plui-validatebox input"/></td>
					<td>&nbsp;</td>
					<td class="title bg1" >核准日期：</td>
					<td class="td_1" style="width:60px"><input class="plui-datebox" data-options="editable:false" style="width:90px" readonly="readonly"  value="" id="app_start_date" name="app_start_date"  /></td>
					<td class="td_1" style="width:10px"><label style="width:10px">至</label></td>
					<td class="td_1" style="width:60px"><input class="plui-datebox" data-options="editable:false" style="width:90px" readonly="readonly"  value="" id="app_end_date" name="app_end_date"  /></td>
					<td>&nbsp;</td>
					<td class="title bg1" >承办人：</td>
					<td class="td_1" colspan="3"><input  id="person_id" name="person_id" class="plui-validatebox input"/>
				</tr>
				<tr>
				<!--
					<td class="title bg1" >抽检环节：</td>
					<td class="td_1" colspan="3"><input value="" id="ins_process_node" name="ins_process_node"  class="plui-validatebox input"/></td>
					<td>&nbsp;</td>
					<td class="title bg1" >环节剩余时间：</td>
					<td class="td_1" colspan="3">
					<select id="node_remaining_time" class="plui-combobox" name="node_remaining_time"  style="width:155px">  
						<option > </option>  
					    <option value="0">0</option>  
					    <option value="1">1</option>  
					    <option value="2">2</option>  
					    <option value="3">3</option>  
					    <option value="4">4</option>  
					</select> 
					</td>
				 -->
				 	<td class="title bg1" >总剩余时间：</td>
					<td class="td_1" colspan="3">
					<select id="remaining_time" class="plui-combobox" name="remaining_time"  style="width:155px">  
						<option > </option>  
					    <option value="0">0</option>  
					    <option value="1">1</option>  
					    <option value="2">2</option>  
					    <option value="3">3</option>  
					    <option value="4">4</option>  
					</select>  
					<td>&nbsp;</td>
					</td>
					<td class="title bg1" >登记文类型：</td>
					<td class="td_1" colspan="3"><input id="bus_type" name=""bus_type"" class="plui-validatebox input"/>
					</td>
					<td colspan="3">
						<a id="btn_search" class="plui-linkbutton" iconCls="icon-search" onclick="querySamle()" >统计</a>
						<a id="btn_search" class="plui-linkbutton" iconCls="icon-redo" onclick="reset()" >重置</a>
					</td>
				</tr>
			</table>
			</form>
			</div>
			<div style="text-align:right;padding-right:100px;">
			 <input type="radio" name="sta" id="sta_table" value="table" onclick="radioClick(this)" checked/><label for="sta_table">统计表</label>
			 <input type="radio" name="sta" id="sta_report" value="report" onclick="radioClick(this)" /><label for="sta_report">统计图</label>
			</div>
			</br>
			<!-- 统计表 -->
			<div id="div_table">
       			<table id="table_business" ></table>
       		</div>
       		<!-- 统计图 -->
       		<div id="div_report" style="display:none;overflow:auto;height:700px;" >
       			统计图
       		</div>
			</br>
			<!-- 
             <div style="text-align:center">
	           	<a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="saveSelectsamle()" >确定</a>
	        </div>
	         -->
</body>
</html>
