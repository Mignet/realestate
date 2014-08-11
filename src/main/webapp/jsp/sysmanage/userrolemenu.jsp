<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" import="com.szhome.security.ext.UserInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>深圳市不动产权登记系统</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <script type="text/javascript" src="${ctx}/js/sysmanage/userrolemenu.js"></script>
    <script type="text/javascript">
	  var ctx = '${ctx}';
    </script>
	<style type="text/css">
	   th{
	    text-align:right;
	   }
	</style>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">   
</head>
<body class="plui-layout">
        <div data-options="region:'center'" border=false>
			<div class="plui-layout" fit=true>
				<div data-options="region:'north',split:true,border:false" style="height:auto;">
					<form id="simpleform" class="searchrow" method="post">
						<table>
						    <tr><td colspan=2><input type="hidden" name="queryCondition" value="user_name like ?"/></td></tr>
							<!-- 对表单的每个控件元素，如果是输入框，且并非PLUIcombo类型组件，则应采用样式"combo"。
								 对表单的每个控件元素，如果是combo组件，则应设置属性"width="80""。
							 -->
							<tr>
								<th>
								  &nbsp;&nbsp;&nbsp;用&nbsp;户&nbsp;名：
								</th>
								<td>
								  <input type="text" name="user_name" id="user_name"/>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
							    <th>
							      &nbsp;创建时间：
							     </th>
								<td colspan=2>
								   <input name="createdateL" id="createdateL" class="plui-datebox"/>
								  -<input name="createdateR" id="createdateR" class="plui-datebox"/>
								 </td>
							</tr>
							<tr>
								<th>
								   &nbsp;角&nbsp;&nbsp;&nbsp;&nbsp;色：
								</th>
								<td colspan=2>
									 <select name="roleid" id="roleid" class="plui-combotree" url="../sysmanage/role-manage!getAllRoles2GrowTree.action" data-options="cascadeCheck:false" multiple style="width:155px" panelHeight="100"/></select>
								</td>
							</tr>
							<tr>
							    <td>&nbsp;</td>
								<td colspan=2>
									<a class="plui-linkbutton" href="javascript:;" iconCls='icon-search' onclick="submit1();">查询</a>
									<a class="plui-linkbutton" href="javascript:;" iconCls='icon-undo' onclick="clear1();">清空</a>
								</td>
							</tr>
						</table>
					</form>
					 <br/>
				</div>
				<div data-options="region:'center',border:false">
				     <table id="userroleLst"></table>
			    </div>
			</div>
	 </div>
</body>
</html>
