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
        <title>�������ѡ��</title>
        <%@ include file="/base/taglibs.jsp"%>
    	<%@ include file="/base/prefix.jsp"%>
    	<script type="text/javascript">
		var ctx = '${ctx}';
		var user = '<%=userName%>';	
		</script>
    	<script type="text/javascript" src="${ctx}/js/qualityinspection/selectsample.js"></script>
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
					<td class="title bg1" >ѡ�ķ�ʽ��</td>
					<td class="td_1" colspan="3">
						<select  class="plui-combobox" style="width:152px" value="" id="select_type" name="select_type"  >
							<option value="0" selected>�˹�ѡ��</option>
							<option value="1">���ѡ��</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="title bg1" >�ǼǱ�ţ�</td>
					<td class="td_1" colspan="3"><input value="" id="reg_code" name="reg_code"  class="plui-validatebox input"/></td>
					<td>&nbsp;</td>
					<td class="title bg1"  >�Ǽ����ͣ�</td>
					<td class="td_2" colspan="3"><input class="plui-combodict" value="" code="061"  id="reg_type" name="reg_type" class="plui-validatebox input" /></td>
					<td class="title bg1"  >���ң�</td>
					<td class="td_2" colspan="3"><input value=""  id="dept" name="dept"  class="plui-validatebox input" />
						<!-- <input value=""  id="deptuser" name="dept"  class="plui-validatebox input" /> -->
						</td>
			
				</tr>
				<tr>
					<td class="title bg1" >ҵ��������</td>
					<td class="td_1" colspan="3"><input value="" id="bus_detail" name="bus_detail"  class="plui-validatebox input"/></td>
					<td>&nbsp;</td>
					<td class="title bg1" >���ز���������</td>
					<td class="td_1" colspan="3"><input class="plui-combodict" value="" code="065" id="location_reg_unit" name="location_reg_unit" class="plui-validatebox input"/>
					</td>
					<td class="title bg1"  >���״̬��</td>
					<td class="td_2" colspan="3">
						<select  class="plui-combobox" style="width:152px" value="" id="inspection_state" name="inspection_state"   >
							<option value="0" selected>δ���</option>
							<option value="1">�����</option>
						</select>
					</td>
			
				</tr>
				<tr>
					<td class="title bg1" >��컷�ڣ�</td>
					<td class="td_1" colspan="3"><input value="" id="ins_process_node" name="ins_process_node"  class="plui-validatebox input"/></td>
					<td>&nbsp;</td>
					<td class="title bg1" >����ʣ��ʱ�䣺</td>
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
			
				</tr>
				<tr>
					<td class="title bg1" >���������</td>
					<td class="td_1" colspan="3"><input value="" id="ins_num" name="ins_num"  class="plui-validatebox input"/></td>
					<td>&nbsp;</td>
					<td class="title bg1" >��������</td>
					<td class="td_1" colspan="3"><input  id="ins_proportion" name="ins_proportion" class="plui-validatebox input"/>
					</td>
			
				</tr>
				<tr>
					<td class="title bg1" >�а����ڣ�</td>
					<td class="td_1" style="width:60px"><input class="plui-datebox" style="width:90px" value="" id="start_date" name="start_date"  /></td>
					<td class="td_1" style="width:10px"><label style="width:10px">��</label></td>
					<td class="td_1" style="width:60px"><input class="plui-datebox" style="width:90px" value="" id="end_date" name="end_date"  /></td>
					<td>&nbsp;</td>
					<td class="title bg1" >�а��ˣ�</td>
					<td class="td_1" colspan="3"><input  id="person_id" name="person_id" class="plui-validatebox input"/>
					</td>
					<td class="td_1" colspan="3">	
						<a id="btn_search" class="plui-linkbutton" iconCls="icon-search" onclick="querySamle()" >��ѯ</a>
					</td>
			
				</tr>
			</table>
			</form>
			</div>
			</br>
       		<table id="table_business" ></table>
    
      
			</br>
             <div style="text-align:center">
	           	<a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="saveSelectsamle()" >ȷ��</a>
	        </div>
</body>
</html>
