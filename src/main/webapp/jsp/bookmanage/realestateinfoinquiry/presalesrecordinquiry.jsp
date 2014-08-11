<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" import="com.szhome.security.ext.UserInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>深圳市不动产权登记系统</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <script type="text/javascript" src="${ctx}/js/bookmanage/realestateinfoinquiry/presalesrecordinquiry.js"></script>
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
     <div data-options="region:'center'" border=false>
		<div class="plui-layout" fit=true>
			<div data-options="region:'north',split:true,border:false" style="height:auto;">
				  <form id="simpleform" class="searchrow" method="post">
					 <table id="mytable">
					  <!--   <tr><td colspan=2><input type="hidden" name="queryCondition" value="user_name like ?"/></td></tr>
						对表单的每个控件元素，如果是输入框，且并非PLUIcombo类型组件，则应采用样式"combo"。
							 对表单的每个控件元素，如果是combo组件，则应设置属性"width="80""。
						 -->
						<tr>
							<th>预售许可证号：</th>
							<td>
							  <input type="text" name="sovo.holdername" />
							</td>
							<th>项目名称：</th>
							<td><input type="text" name="sovo.idno" /></td>
						</tr>
						<tr>
						    <th>预售合同号：</th>
							<td >
							    <input type="text" name="sovo.cerno" />
							 </td>
							 <th>房地产名称：</th>
							<td><input type="text" name="sovo.parcelcode" /></td>
						</tr>
						<tr>
						    <td colspan=3>&nbsp;</td>
							<td>
							    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
								<a class="plui-linkbutton" href="javascript:;" iconCls='icon-search' onclick="submit1();">查询</a>
							</td>
						</tr>
					</table>
					</form>
                              <br/>
			</div>
			<div data-options="region:'center',border:false">
				<table id="presalesLst"></table>
			</div>
			<div id="printdiv" style="text-align:right;">
				<span style="margin-left:7px;margin-right:7px;"><a class="plui-linkbutton" href="javascript:;" iconCls='icon-print' id="presale_print">打 印</a></span>
			</div>
		</div>
	</div>
</body>
</html>
