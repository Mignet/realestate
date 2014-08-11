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
    	<script type="text/javascript" src="${ctx}/js/qualityinspection/reminder-index.js"></script>
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
					<td class="td_2" colspan="3"><input class="plui-datebox" data-options="editable:false" value=""  id="acc_date" name="acc_date"  class="plui-validatebox input" />
					<td class="title bg1" >登记编号：</td>
					<td class="td_1" colspan="3"><input value="" id="reg_code" name="reg_code"  class="plui-validatebox input"/></td>
					
			
				</tr>
				<tr>
					<td class="title bg1" >业务描述：</td>
					<td class="td_1" colspan="3"><input value="" id="bus_detail" name="bus_detail"  class="plui-validatebox input"/></td>
					<td>&nbsp;</td>
					<td class="title bg1" >核准日期：</td>
					<td class="td_1" colspan="3"><input  class="plui-datebox" data-options="editable:false" id="app_date" name="app_date" class="plui-validatebox input"/>
					<td class="title bg1" >承办人：</td>
					<td class="td_1" colspan="3"><input  id="person_id" name="person_id" class="plui-validatebox input"/>
				</tr>
				<tr>
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
					<td class="title bg1" >登记文类型：</td>
					<td class="td_1" colspan="3"><input id="bus_type" name=""bus_type"" class="plui-validatebox input"/>
					</td>
				</tr>
				<tr>
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
					</td>
					<td class="td_1" colspan="3">	
						<a id="btn_search" class="plui-linkbutton" iconCls="icon-search" onclick="querySamle()" >查询</a>
						<a id="btn_search" class="plui-linkbutton" iconCls="icon-redo" onclick="reset()" >重置</a>
					</td>
			
				</tr>
			</table>
			</form>
			</div>
			
			</br>
       		<table id="table_business" ></table>
      		<div id="div_dialog" data-options="iconCls:'icon-save'" style="padding:5px;width:400px;height:200px;">  
      		<table>
      			<tr>
      				<td>督办方式：</td>
      				<td><input type="radio" id="message" name="type" value="message" checked/><label for="message" >短信</label></td>
      				<td> <input type="radio" name="type" id="email" value="email"/><label for="email">邮件</label></td>
      				<td></td>
      			</tr>
      			
      			<tr>
      				<td colspan = "4">督办意见：</td>
      			</tr>
      			<tr>
      				<td colspan="3"><textarea id="txt_message" style="height:88px;width:200px;font-size:14px"></textarea></td>
      				<td><a id="selectWord" class="plui-linkbutton" iconCls="icon-save" onclick="" >常用语</a></td>
      			</tr>
      			<tr>
      				<td>是否需要回执：</td>
      				<td> <input type="radio" name="is_re" id="yes" value="yes" /><label for="yes">是</label></td>
      				<td>  <input type="radio" name="is_re" id="no" value="no" checked/><label for="no" >否</label></td>
      				<td></td>
      			</tr>
      		</table>
      		<!-- 
		        <p>督办方式：
		        <input type="radio" id="message" name="type"/><label for="message">短信</label>
		        <input type="radio" name="type" id="email"/><label for="email">邮件</label>
		        </p>  
		        <p>督办意见：</p>  
		        <p>
		        <textarea style="height:88px;width:266px;font-size:14px"></textarea>
		       	<a id="selectWord" class="plui-linkbutton" iconCls="icon-save" onclick="" >常用语</a>
		        </p>  
		          <p>是否需要回执：
		        <input type="radio" name="is_re" id="yes" /><label for="yes">是</label>
		        <input type="radio" name="is_re" id="no"/><label for="no">否</label>
		        </p>  
		         -->  
		    </div>  
		    
			</br>
			<!-- 
             <div style="text-align:center">
	           	<a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="saveSelectsamle()" >确定</a>
	        </div>
	         -->
</body>
</html>
