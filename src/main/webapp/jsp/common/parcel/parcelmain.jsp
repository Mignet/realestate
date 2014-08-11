<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" import="com.szhome.security.ext.UserInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>深圳市不动产权登记系统</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <script type="text/javascript" src="${ctx}/js/common/parcel/parcelmain.js"></script>
    <script type="text/javascript">
	  var ctx = '${ctx}';
    </script>
	<style type="text/css">
	   th{
	    text-align:right;
	   }
	   #mytable th{
	     width:110px;
	   }
	   #mytable td{
	      width:230px;
	   }
	</style>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">   
</head>
<body class="plui-layout">
     <div id="searchpanel" data-options="region:'center'" border=false>
		<div class="plui-layout" fit=true>
			<div data-options="region:'north',split:true,border:false" style="height:auto;">
				<form id="simpleform" class="searchrow" method="post">
					 <table id="mytable">
					  <!--   <tr><td colspan=2><input type="hidden" name="queryCondition" value="user_name like ?"/></td></tr>
						对表单的每个控件元素，如果是输入框，且并非PLUIcombo类型组件，则应采用样式"combo"。
							 对表单的每个控件元素，如果是combo组件，则应设置属性"width="80""。
						 -->
						<tr>
							<th> 权利人名称：</th>
							<td>
							  <input type="text" name="sovo.holdername" />
							</td>
							<th>证件号码：</th>
							<td><input type="text" name="sovo.idno" /></td>
						</tr>
						<tr>
						    <th>房地产证号：</th>
							<td >
							    <input type="text" name="sovo.cerno" />
							 </td>
							 <th>宗地号：</th>
							<td><input type="text" name="sovo.parcelcode" /></td>
						</tr>
						<tr>
							<th>宗地坐落：</th>
							<td >
								<input type="text" name="sovo.parceladdr" /> 
							</td>
							<th>宗地用途：</th>
							<td>
							    <input type="text" name="sovo.parcelusage"/>
							</td>
						</tr>
						<tr>
						   <th>登记编号：</th>
							<td><input type="text" name="sovo.regcode" /></td>
							<th>产权状态：</th>
							<td><input type="text" name="sovo.rightstate" /></td>
						</tr>
						<tr>
						    <td colspan=3>&nbsp;</td>
							<td>
								<a class="plui-linkbutton" href="javascript:;" iconCls='icon-search' onclick="submit1();">查询</a>
								<a class="plui-linkbutton" href="javascript:;" iconCls='icon-undo' onclick="clear1();">清空</a>
							</td>
						</tr>
					</table>
	            </form>
	            <br/>
			</div>
			<div data-options="region:'center',border:false">
				<table id="parcelLst"></table>
			</div>
		</div>
	</div>
</body>
</html>
