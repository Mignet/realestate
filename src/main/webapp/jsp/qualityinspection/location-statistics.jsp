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
        <title>督查统计</title>
        <%@ include file="/base/taglibs.jsp"%>
    	<%@ include file="/base/prefix.jsp"%>
    	<script type="text/javascript">
		var ctx = '${ctx}';
		var user = '<%=userName%>';	
		</script>
		
    	<script type="text/javascript" src="${ctx}/js/qualityinspection/location-statistics.js"></script> 
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
		
        <table id="tab_reg_info" style="width: 100%;margin-top:30px;margin-left:20px;">
					
				<tr>
					<td class="title bg1">所属区：</td>
					<td class="td_2" colspan="3"><input class="plui-combodict" value="" code="065"  id="location" name="location"  class="plui-validatebox input" /></td>
					<td class="title bg1" >承办日期：</td>
					<td class="td_1" style="width:60px"><input class="plui-datebox" style="width:90px" value="" id="start_date" name="start_date"  /></td>
					<td class="td_1" style="width:10px"><label style="width:10px">至</label></td>
					<td class="td_1" style="width:60px"><input class="plui-datebox" style="width:90px" value="" id="end_date" name="end_date"  /></td>
					<td>&nbsp;</td>
					<td class="td_1" colspan="3">	
						<a id="btn_search" class="plui-linkbutton" iconCls="icon-search" onclick="queryBydate()" >查询</a>
					</td>
			
				</tr>
			</table>
			</br>
			<div style="text-align:right;padding-right:100px;"><input type="button" id="btn_print" value="打印" onclick="doPrint('print')"/>
				<!-- 
				<input type="button" id="btn_print" value="打印预览" onclick="doPrintPreview()"/> -->
			</div>
			<div id="print">
			<div style="text-align:center;">
					<h2><label id="title">办文质量检查情况统计表</label></h2>
				</div>
       		<table id="table_business"  style="width:1000px;text-align:center;BORDER-COLLAPSE: collapse;" borderColor=#000000  cellPadding=1  align=center border=1>
    			<tr>
    				<td colspan="2" rowspan="2">日期/类型</td>
    				<td colspan="2">窗口收文</td>
    				<td colspan="2">初始登记</td>
    				<td colspan="2">二级转移</td>
    				<td colspan="2">三级转移</td>
    				<td colspan="2">预售抵押</td>
    				<td colspan="2">现售房抵押</td>
    				<td colspan="2">变更及其它</td>
    				<td colspan="2">安居房换证</td>
    				<td colspan="2">预售合同备案</td>
    				<td colspan="2">产权综合办文</td>
    				<td colspan="2">两规登记</td>
    				<td colspan="2">其它登记</td>
    				<td colspan="3">合计</td>
    				
    			</tr>
      			<tr>
      				<td >抽检量</td>
      				<td >差错量</td>
      				<td >抽检量</td>
      				<td >差错量</td>
      				<td >抽检量</td>
      				<td >差错量</td>
      				<td >抽检量</td>
      				<td >差错量</td>
      				<td >抽检量</td>
      				<td >差错量</td>
      				<td >抽检量</td>
      				<td >差错量</td>
      				<td >抽检量</td>
      				<td >差错量</td>
      				<td >抽检量</td>
      				<td >差错量</td>
      				<td >抽检量</td>
      				<td >差错量</td>
      				<td >抽检量</td>
      				<td >差错量</td>
      				<td >抽检量</td>
      				<td >差错量</td>
      				<td >抽检量</td>
      				<td >差错量</td>
      				<td >目标抽检量</td>
      				<td >实际抽检量</td>
      				<td >差错量</td>
      			</tr>
      			</table>
      			<div style="text-align:right;width:1000px;">
      				<label style="margin-right:200px;">质量督查科签收：</label> <label id="cur_date"></label>
      			</div>
      		</div>
			</br>
</body>
</html>
