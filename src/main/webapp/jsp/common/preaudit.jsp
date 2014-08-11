<%@ page language="java" import="java.util.*" pageEncoding="GBK"
	contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>登记信息</title>
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp" %>
<script type="text/javascript">
var ctx = '${ctx}';
</script>
<script type="text/javascript" src="${ctx}/js/common/preaudit.js"></script>
</head>
<body class="plui-layout">
	<div data-options="region:'center',border:true">	 
        <!--  <div class="page_con" style="width:800px">      -->   
		<!-- <form id="add_app_form" class="searchrow" method="post"> -->
		<!-- <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 &nbsp; --><!-- <label style="font-size: 12px;font-weight: bold;">登记类型</label>
		 <table class="edit_tab" style="width: 100%;">
				<tr>
					<td class="title bg1" style="width:150px;">业务类型：</td>
					<td class="td_1"><input value="" id="bustype" name="bustype" style="width:150px;" class="plui-validatebox input" disabled="disabled"/></td>					
					<td  style="width:200px;"></td>
					<td ></td>
				</tr>
										
			</table> -->
          <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">查询条件</label>
		</div> 		
		<div class="datagrid-wrap panel-body">
		</br>
			<table id="house" class="edit_tab" style="width: 100%;">
				<tr>
					<td class="title bg1" style="width:150px;">宗地号：</td>
					<td class="td_1"><input value="" id="parcelcode" name="parcelcode" style="width:150px;" class="plui-validatebox input" /></td>
					<td class="title bg1" style="width:150px;">登记单元编号：</td>
					<td class="td_2"><input value="" id="housecode" name="housecode" style="width:150px;" class="plui-validatebox input"/></td>
					</td>
				</tr>
				<tr>
					<td class="title bg2" style="width:150px;">项目名称：</td>
					<td class="td_1"><input value="" id="proname" name="proname" style="width:150px;" class="plui-validatebox input" /></td>					
					</td>
					<td class="title bg2" style="width:150px;">坐落：</td>
					<td class="td_2"><input value="" id="houselocation" name=""houselocation"" style="width:150px;" class="plui-validatebox input"/></td>
				</tr>
			</table>
			<table id="land" class="edit_tab" style="width: 100%;display:none;" >
				<tr>
					<td class="title bg1" style="width:150px;">宗地号：</td>
					<td class="td_1"><input value="" id="parcelcode1" name="parcelcode1" style="width:150px;" class="plui-validatebox input" /></td>
					<td class="title bg1" style="width:150px;">坐落：</td>
					<td class="td_2"><input value="" id="location" name="location" style="width:150px;" class="plui-validatebox input"/></td>
					</td>
				</tr>
				<tr>
			</table>
			<table class="edit_tab" style="width: 100%;">
			<tr>
				<td class="title bg2" style="width:150px;">登记单元类型：</td>
					<td>
						<input class="plui-combodict" value="" classCode="009" id="reg_type" name="reg_type" style="width:150px;" class="plui-validatebox input" onChange="selectChange()"/>
					</td>	
				</tr>
			</table>
			<div style="text-align: right;"><a id="search" class="plui-linkbutton" iconCls="icon-search"
					onclick="javascript:searchStr()">查询</a>  </div>
			<div id="divHouse" class="plui-panel" data-options="iconCls:'icon-save',collapsible:true,minimizable:true,maximizable:true,closable:true"> 
				<table id="table_user_house"></table>
			</div>
			
             <div id="divLand" class="plui-panel"   data-options="iconCls:'icon-save',collapsible:true,minimizable:true,maximizable:true,closable:true"> 
            	<table id="table_user_land"></table>
            </div>
            <div id="divBuild" class="plui-panel"   data-options="iconCls:'icon-save',collapsible:true,minimizable:true,maximizable:true,closable:true"> 
            	<table id="table_user_build"></table>
            </div>
            <div id="divRegCode" class="plui-panel"   data-options="iconCls:'icon-save',collapsible:true,minimizable:true,maximizable:true,closable:true"> 
            	<table id="table_user_regcode"></table>
            </div>
            <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header" >
		          &nbsp;<label style="font-size: 12px;font-weight: bold;">温馨提示：</label>
		        </div> 
		        <div class="datagrid-wrap panel-body">
		      <table id="tab_fdcz" style="width: 99%;">
		         <tr>
              <td colspan="4" scope="col" >
              <textarea colspan="8" id="discription"
							name="discription" style="height:100px;width:100%" disabled="disabled"></textarea>
	             </td>
               </tr>   
		     </table>
		     <!--  <div id="divselect" class="plui-panel"   data-options="iconCls:'icon-save',collapsible:true,minimizable:true,maximizable:true,closable:true"> 
            	<table id="table_user_select"></table>
            </div> -->
		     </div>
              
		<!-- </form> -->
		</div>
		 <div style=" text-align:right;">
           <a id="accept" class="plui-linkbutton" iconCls="icon-save" onclick="addRegunit()">确定</a>
         </div>  
	<!-- </div> -->

</body>
</html>
