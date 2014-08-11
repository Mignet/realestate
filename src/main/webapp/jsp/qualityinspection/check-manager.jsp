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
    	<script type="text/javascript" src="${ctx}/js/qualityinspection/check-manager.js"></script>
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
		
		<div class="datagrid-wrap panel-body" style="">
		<form id="m_search_form" action="${ctx}/qualityinspection/qualityinspection!querySamele.action" method="post">
        <table id="tab_reg_info" style="width: 100%;">
				<tr>
					<td class="title bg1"  >科室：</td>
					<td class="td_2" colspan="3"><input value=""  id="dept" name="dept"  class="plui-validatebox input" />
					</td>
					<td class="title bg1" >承办人：</td>
					<td class="td_1" colspan="3"><input  id="person_id" name="person_id" class="plui-validatebox input"/>
					</td>
					<td class="title bg1" >抽检日期：</td>
					<td class="td_1" style="width:60px"><input class="plui-datebox" style="width:90px" value="" id="ch_start_date" name="ch_start_date"  /></td>
					<td class="td_1" style="width:10px"><label style="width:10px">至</label></td>
					<td class="td_1" style="width:60px"><input class="plui-datebox" style="width:90px" value="" id="ch_end_date" name="ch_end_date"  /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>	
					<td class="td_1" colspan="12" style="text-align:right;padding-right:50px;">	
						<a id="btn_search" class="plui-linkbutton" iconCls="icon-search" onclick="querySamle()" >查询</a>
					</td>
			
				</tr>
			</table>
			</form>
			</div>
			</br>
       		<table id="table_business" ></table>
    
      
			</br>
			<!-- 
             <div style="text-align:center">
	           	<a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="saveSelectsamle()" >确定</a>
	        </div>
	        -->
</body>
</html>
