<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK" import="com.szhome.security.ext.UserInfo"%>
<%
  UserInfo user=(UserInfo)session.getAttribute("userInfo");
  String userName=user.getUserName();
  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新增用户</title>
<meta http-equiv="content-type" content="text/html;charset=GBK">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="">
 <%@ include file="/base/taglibs.jsp"%>
  <%@ include file="/base/prefix.jsp"%>
<script type="text/javascript" src="${ctx}/js/common/officeconfig/addOfficeConfig.js"></script>	
<script type="text/javascript"> 		
  var ctx = '${ctx}';	 
</script>
    </head>
    <body>
    	<div data-options="region:'center',border:true">	 
         <div class="page_con" style="width:800px">        
		<form id="add_app_form" class="searchrow" method="post">
          <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">表单信息</label>
		</div> 	
		<div class="datagrid-wrap panel-body">
			<table class="edit_tab" style="width:100%;">
				<tr>
					<td class="title bg1" style="width:100px;">业务类型：</td>
					<td class="td_1">
					<select id="selectbustype" style="width:150px;" disabled="disabled"> 
					<!-- <option  value="1217">房屋所有权初始登记</option> -->
					</select></td>
					<td class="title bg1" style="width:100px;">表单类型：</td>
					<td class="td_2"> <select id="selecttype" style="width:150px;"> 
					<option  value="0">表单</option>
					<option  value="1">报表</option>
					</select>
					</td>
				</tr>
				<tr>
					<td class="title bg2" style="width:100px;">表单名称：</td>
					<td class="td_1"><input value="" id="name" name="name" style="width:150px;" class="plui-validatebox input" /></td>					
					</td>
					<td class="title bg2" style="width:100px;">表单URL：</td>
					<td class="td_2"><input value="" id="url" name="url" style="width:150px;" class="plui-validatebox input"/></td>
			</table>
			</div>       
		</form>
		</div>
	</div>
    	<div class="plui-panel" data-options="fit:true,border:false">
	    	<form id="add_app_form" method="post">
	    	<input type="hidden"  id="form" />
	    	
	    	</form>
	        <div style="text-align:center">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="submit()">保存</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('add_user_win');">取消</a>
	        </div>
		</div>
    </body>
</html>

