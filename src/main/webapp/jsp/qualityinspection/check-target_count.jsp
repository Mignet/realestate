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
        <title>抽检量计算</title>
        <%@ include file="/base/taglibs.jsp"%>
    	<%@ include file="/base/prefix.jsp"%>
    	<script type="text/javascript">
		var ctx = '${ctx}';
		var user = '<%=userName%>';	
		</script>
		
    	<script type="text/javascript" src="${ctx}/js/qualityinspection/check-target_count.js"></script> 
    	<script type="text/javascript" src="${ctx}/js/date/WdatePicker.js"></script> 
         <style type="text/css">
    	html,body {height:100%; width:100%; margin:0; padding:0;overflow:auto;font-family:Arial; font-size:12px;}
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
		<div style="width: 100%;margin-top:30px;"></div>
        <table  id="tab_reg_info" style="margin:auto;">
				<tr>
					<td class="title bg1" >月份：</td>
					<td class="td_1" style="width:60px">
					<!-- <input class="plui-calendar" style="width:90px" value="" id="year" name="year"  /> -->
					<input type="text" id="month" name="month" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" class="Wdate"/>
					</td>
					<td>&nbsp;</td>
					<td class="td_1" colspan="3">	
						<a id="btn_search" class="plui-linkbutton" iconCls="icon-search" onclick="queryBydate()" >查询</a>
					</td>
			
				</tr>
			</table>
			</br>
			<div style="padding-right:100px;">
				<input type="button" id="btn_print" value="打印" onclick="doPrint('print')" style="display:none;float:right;"/>
			</div>
			<div id="print" style="clear:both;">
				<div style="text-align:center;">
					<h2><label id="title">日常督查目标抽检量计算表</label></h2>
				</div>
			
			
       		<table id="table_business"  style="width:1000px;text-align:center;BORDER-COLLAPSE: collapse;" borderColor=#000000  cellPadding=1  align=center border=1>
      			<tr>
      				<td >科室</td>
      				<td >月办文量</td>
      				<td >月工作日</td>
      				<td >月收文*0.03</td>
      				<td >月收文*0.03/工作日</td>
      				<td >目标日均抽检量</td>
      				<td >目标月抽检量</td>
      			</tr>
      			</table>
      			<div style="text-align:right;width:1000px;">
      				<label style="margin-right:200px;"></label> <label id="cur_date"></label>
      			</div>
      		</div>
			</br>
</body>
</html>
