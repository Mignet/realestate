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
        <title>����ͳ��</title>
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
					<td class="title bg1">��������</td>
					<td class="td_2" colspan="3"><input class="plui-combodict" value="" code="065"  id="location" name="location"  class="plui-validatebox input" /></td>
					<td class="title bg1" >�а����ڣ�</td>
					<td class="td_1" style="width:60px"><input class="plui-datebox" style="width:90px" value="" id="start_date" name="start_date"  /></td>
					<td class="td_1" style="width:10px"><label style="width:10px">��</label></td>
					<td class="td_1" style="width:60px"><input class="plui-datebox" style="width:90px" value="" id="end_date" name="end_date"  /></td>
					<td>&nbsp;</td>
					<td class="td_1" colspan="3">	
						<a id="btn_search" class="plui-linkbutton" iconCls="icon-search" onclick="queryBydate()" >��ѯ</a>
					</td>
			
				</tr>
			</table>
			</br>
			<div style="text-align:right;padding-right:100px;"><input type="button" id="btn_print" value="��ӡ" onclick="doPrint('print')"/>
				<!-- 
				<input type="button" id="btn_print" value="��ӡԤ��" onclick="doPrintPreview()"/> -->
			</div>
			<div id="print">
			<div style="text-align:center;">
					<h2><label id="title">��������������ͳ�Ʊ�</label></h2>
				</div>
       		<table id="table_business"  style="width:1000px;text-align:center;BORDER-COLLAPSE: collapse;" borderColor=#000000  cellPadding=1  align=center border=1>
    			<tr>
    				<td colspan="2" rowspan="2">����/����</td>
    				<td colspan="2">��������</td>
    				<td colspan="2">��ʼ�Ǽ�</td>
    				<td colspan="2">����ת��</td>
    				<td colspan="2">����ת��</td>
    				<td colspan="2">Ԥ�۵�Ѻ</td>
    				<td colspan="2">���۷���Ѻ</td>
    				<td colspan="2">���������</td>
    				<td colspan="2">���ӷ���֤</td>
    				<td colspan="2">Ԥ�ۺ�ͬ����</td>
    				<td colspan="2">��Ȩ�ۺϰ���</td>
    				<td colspan="2">����Ǽ�</td>
    				<td colspan="2">�����Ǽ�</td>
    				<td colspan="3">�ϼ�</td>
    				
    			</tr>
      			<tr>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >�����</td>
      				<td >Ŀ������</td>
      				<td >ʵ�ʳ����</td>
      				<td >�����</td>
      			</tr>
      			</table>
      			<div style="text-align:right;width:1000px;">
      				<label style="margin-right:200px;">���������ǩ�գ�</label> <label id="cur_date"></label>
      			</div>
      		</div>
			</br>
</body>
</html>
