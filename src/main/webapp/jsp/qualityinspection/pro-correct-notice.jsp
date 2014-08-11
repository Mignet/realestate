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
		
    	<script type="text/javascript" src="${ctx}/js/qualityinspection/statistics.js"></script> 
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
					<td class="title bg1"  >整改通知书内容：</td>
					<td class="td_2" colspan="3"><input class="plui-combodict" value="" code="054"  id="reg_station" name="reg_station"  class="plui-validatebox input" /></td>
			
					<td class="title bg1" >承办日期：</td>
					<td class="td_1" style="width:60px"><input class="plui-datebox" style="width:90px" value="" id="start_date" name="start_date"  /></td>
					<td class="td_1" style="width:10px"><label style="width:10px">至</label></td>
					<td class="td_1" style="width:60px"><input class="plui-datebox" style="width:90px" value="" id="end_date" name="end_date"  /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4" scope="col"><textarea value="" id="fdczfj"
							onKeyDown="limitLength(this,400)"
							onKeyUp="limitLength(this,400)"
							onPaste="limitLength(this,400)" name="oivo.excursus"
							style="height: 80px; width: 100%; font-size: 14px"
							disabled="disabled"></textarea></td>
				</tr>
				<tr>
					<td class="td_1" colspan="3">	
						<a id="btn_submit" class="plui-linkbutton" iconCls="icon-save" onclick="submit()" >保存</a>
						<a id="btn_submit" class="plui-linkbutton" iconCls="icon-save" onclick="submit()" >预览</a>
					</td>
			
				</tr>
			</table>
			</br>
			</br>
</body>
</html>
