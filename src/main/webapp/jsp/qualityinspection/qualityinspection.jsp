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
        <title>抽检</title>
        <%@ include file="/base/taglibs.jsp"%>
    	<%@ include file="/base/prefix.jsp"%>
    	<script type="text/javascript">
		var ctx = '${ctx}';
		var user = '<%=userName%>';	
		</script>
    	<script type="text/javascript" src="${ctx}/js/qualityinspection/qualityinspection.js"></script>
         <style type="text/css">
    	body,html{margin:0px; font-family:Arial; font-size:12px; color:#333333;}
		.tip{color:#3CF;}
		.title {text-align: right;width:120px}	
		.bg1 {background: none repeat scroll 0 0 #E0ECFF;}	
		.bg2 { background: none repeat scroll 0 0 #F4F4F4;}
		.panel-body {
    	background: none repeat scroll 0 0 #F8FAFF;
		}
    </style>        
</head>
<body>
<form id="main_form">
		 <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">&nbsp;</label>
		</div> 
		<div class="datagrid-wrap panel-body">
		   <table style="width: 100%;">
		   <tr>
		   	<td class="title bg1" >月目标抽检量：</td>
			<td  style="width:165px;"><input value="" id="month_target" name="month_target"  readonly="readonly"/></td>
			<td class="title bg1"  >已抽检量：</td>
			<td style="width:161px;"><input  id="inspectioned" name="inspectioned" readonly="readonly"/></td>
		    <td>
		    	<a id="submit" class="plui-linkbutton" iconCls="icon-save" href="./selectsample.jsp">选择样本</a>
		    </td>
		   	
		   </tr>
			</table> 
		</div>
		<!-- 
		  <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">&nbsp;</label>
		</div> 
		
		<div class="datagrid-wrap panel-body">
        <table id="tab_reg_info" style="width: 100%;">
        		<tr>
					<td class="title bg1" >选文方式：</td>
					<td class="td_1" colspan="3">
						<select style="width:152px" value="" id="select_type" name="select_type"  class="plui-validatebox input">
							<option value="0" selected> </option>
							<option value="1">人工选文</option>
							<option value="2">随机选文</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="title bg1" >登记编号：</td>
					<td class="td_1" colspan="3"><input value="" id="REG_CODE" name="REG_CODE"  class="plui-validatebox input"/></td>
					<td>&nbsp;</td>
					<td class="title bg1"  >登记类型：</td>
					<td class="td_2" colspan="3"><input class="plui-combodict" value="" code="061"  id="REG_TYPE_NAME" name="REG_TYPE_NAME" class="plui-validatebox input" /></td>
					<td class="title bg1"  >科室：</td>
					<td class="td_2" colspan="3"><input value=""  id="REG_TYPE_NAME" name="REG_TYPE_NAME"  class="plui-validatebox input" /></td>
			
				</tr>
				<tr>
					<td class="title bg1" >业务描述：</td>
					<td class="td_1" colspan="3"><input value="" id="BUS_DETAIL" name="BUS_DETAIL"  class="plui-validatebox input"/></td>
					<td>&nbsp;</td>
					<td class="title bg1" >房地产所属区：</td>
					<td class="td_1" colspan="3"><input class="plui-combodict" value="" code="065" id="LOCATION_REG_UNIT" name="LOCATION_REG_UNIT" class="plui-validatebox input"/>
					</td>
					<td class="title bg1"  >抽检状态：</td>
					<td class="td_2" colspan="3"><input value=""  id="INSPECTION_STATE" name="INSPECTION_STATE"  class="plui-validatebox input" /></td>
			
				</tr>
				<tr>
					<td class="title bg1" >抽检环节：</td>
					<td class="td_1" colspan="3"><input value="" id="INS_PROCESS_NODE" name="INS_PROCESS_NODE"  class="plui-validatebox input"/></td>
					<td>&nbsp;</td>
					<td class="title bg1" >环节剩余时间：</td>
					<td class="td_1" colspan="3"><input id="NODE_REMAINING_TIME" name=""NODE_REMAINING_TIME"" class="plui-validatebox input"/>
					</td>
			
				</tr>
				<tr>
					<td class="title bg1" >抽检数量：</td>
					<td class="td_1" colspan="3"><input value="" id="INS_NUM" name="INS_NUM"  class="plui-validatebox input"/></td>
					<td>&nbsp;</td>
					<td class="title bg1" >抽检比例：</td>
					<td class="td_1" colspan="3"><input  id="INS_PROPORTION" name="INS_PROPORTION" class="plui-validatebox input"/>
					</td>
			
				</tr>
				<tr>
					<td class="title bg1" >承办日期：</td>
					<td class="td_1" style="width:60px"><input style="width:60px" value="" id="START_DATE" name="START_DATE"  class="plui-validatebox input"/></td>
					<td class="td_1" style="width:10px"><label style="width:10px">至</label></td>
					<td class="td_1" style="width:60px"><input style="width:60px" value="" id="END_DATE" name="END_DATE"  class="plui-validatebox input"/></td>
					<td>&nbsp;</td>
					<td class="title bg1" >承办人：</td>
					<td class="td_1" colspan="3"><input  id="PERSON_NAME" name="PERSON_NAME" class="plui-validatebox input"/>
					</td>
			
				</tr>
			</table>
			</div>
			 -->
			</br>
       		<table id="table_business"  ></table>
    
      
			</br>
</form>
</body>
</html>
