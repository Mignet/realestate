<%@ page language="java" import="java.util.*" pageEncoding="GBK"
	contentType="text/html;charset=GBK" import="com.szhome.security.ext.UserInfo"%>
	<%
  UserInfo user=(UserInfo)session.getAttribute("userInfo");
  String userName=user.getUserName();
  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>������</title>
<meta http-equiv="content-type" content="text/html;charset=GBK">
	<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">
				<meta http-equiv="keywords" content="">
				 <style type="text/css">
    	body,html{margin:0px; font-family:Arial; font-size:12px; color:#333333; overflow:auto;}
		.tip{color:#3CF;}
		.title {text-align: right;}	
		.bg1 {background: none repeat scroll 0 0 #E0ECFF;}	
		.bg2 { background: none repeat scroll 0 0 #F4F4F4;}
		.panel-body {
    background: none repeat scroll 0 0 #F8FAFF;
}
    </style> 

<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp"%>
<script type="text/javascript" src="${ctx}/js/common/exam/examine.js"></script>
<script type="text/javascript"> 	
var user = '<%=userName%>';	
var ctx = '${ctx}';		 
</script>
</head>
<body class="plui-layout" style='height:500px;'>
<form id="main_form">
<div data-options="region:'center'">
        <div class="page_con"  style="width:800px">
<!--  	<div class="plui-panel" data-options="fit:fasle,border:true" style="background-color:gray">-->

		<form id="add_user_form" method="post" >
		<!-- ����ģ�� -->
		<div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;" >���⹫��</label>
		</div> 
		<div class="datagrid-wrap panel-body">
			<table  >
				<tr>
					<th colspan="8" scope="col" style="width:auto">
					<textarea colspan="8" id="ggnr" name="notice_content" style="height:100px;width:700px" ></textarea>
					</th>
					<th width="65" scope="col">
							<input type="button" name="Submit" id="ggmb" value="����ģ��" onclick="selectGgmb()"/>
						</th>
				</tr>
				<tr >
					<th width="123" scope="row">&nbsp;</th>
					<td width="71">&nbsp;</td>
					<td width="69">&nbsp;</td>
					<td width="76">&nbsp;</td>
					<td width="50" sytle="text-align:right">ǩ����</td>
					<td width="107"><input type="text" name="notice_pro_person" id="ggndr"
						class="plui-validatebox input" data-options="required:true"
						style="width:140px;" value=""  />
					</td>
					<td width="78" sytle="text-align:right">ǩ�����ڣ�</td>
					<td width="84"><input id="ggndsj" name="notice_pro_time" 
						style="width:140px;" value=""/>
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			</div>
		<!-- ���������� -->	
		<div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;" >������� </label>
		</div> 
		<div class="datagrid-wrap panel-body">
			<table  >
				<tr>
					<th colspan="8" scope="col" style="width:auto">
					<textarea colspan="8" id="cbsc"
							name="cbsc" style="height:100px;width:700px" ></textarea>
					</th>
					<th width="65" scope="col">
							<input type="button" name="Submit" id="cbsccyy" value="������" onclick="selectCyy()"/>
						</th>
				</tr>
				<tr >
					<th width="123" scope="row">&nbsp;</th>
					<td width="71">&nbsp;</td>
					<td width="69">&nbsp;</td>
					<td width="76">&nbsp;</td>
					<td width="50" sytle="text-align:right">ǩ����</td>
					<td width="107"><input type="text" name="cbscr" id="cbscr"
						class="plui-validatebox input" data-options="required:true"
						style="width:140px;" value="" disabled="disabled"/>
					</td>
					<td width="78" sytle="text-align:right">ǩ�����ڣ�</td>
					<td width="84"><input id="cbscsj" name="cbscsj" disabled="disabled"
						style="width:140px;" />
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			</div>
		
			<!-- �������-->	
			 <div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;" >�������</label>
		</div> 
		  <div class="datagrid-wrap panel-body">
			<table >
				<tr>
					<th colspan="8" scope="col" style="width:auto">
					<textarea colspan="8" id="cbsh"
							name="cbsh" style="height:100px;width:700px"></textarea>
					</th>
					<th width="65" scope="col">
							<input type="button" name="Submit"  id="cbshcyy" value="������" onclick="selectCyy()"/>
						</th>
				</tr>
				<tr >
					<th width="123" scope="row">&nbsp;</th>
					<td width="71">&nbsp;</td>
					<td width="69">&nbsp;</td>
					<td width="76">&nbsp;</td>
					<td width="50" sytle="text-align:right">ǩ����</td>
					<td width="107"><input type="text" name="cbshr" id="cbshr"
						class="plui-validatebox input" data-options="required:true"
						style="width:140px;" disabled="disabled"/>
					</td>
					<td width="78" sytle="text-align:right">ǩ�����ڣ�</td>
					<td width="84"><input id="cbshsj" name="cbshsj" disabled="disabled"
						style="width:140px;" />
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
         </div>
		<!-- ������ -->
		 	   <div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;" >������</label>
		</div> 
		<div class="datagrid-wrap panel-body">
			<table  >
				<tr>
					<th colspan="8" scope="col" style="width:auto">
					<textarea colspan="8" id="cbsd"
							name="cbsd" style="height:100px;width:700px" ></textarea>
					</th>
					<th width="65" scope="col">
							<input type="button" name="Submit" id="cbsdcyy" value="������" onclick="selectCyy()"/>
						</th>
				</tr>
				<tr >
					<th width="123" scope="row">&nbsp;</th>
					<td width="71">&nbsp;</td>
					<td width="69">&nbsp;</td>
					<td width="76">&nbsp;</td>
					<td width="50" sytle="text-align:right">ǩ����</td>
					<td width="107"><input type="text" name="cbsdr" id="cbsdr"
						class="plui-validatebox input" data-options="required:true"
						style="width:140px;" value="" disabled="disabled"/>
					</td>
					<td width="78" sytle="text-align:right">ǩ�����ڣ�</td>
					<td width="84"><input id="cbsdsj" name="cbsdsj" disabled="disabled" value=""
						style="width:140px;" />
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			</div>
		
		 <!-- ���������� -->
          <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">����</label>
		</div> 
		
		<div class="datagrid-wrap panel-body">
		</br>
			<table id="tab_pub" style="width: 100%;">
				<tr>
					<td class="title bg1" style="width:100px;">�����ţ�</td>
					<td class="td_1"><input value="" id="ggbh" name="notice_code" style="width:200px;" class="plui-validatebox input test" data-options="required:true" readonly="readonly"/></td>
					<td class="title bg1" style="width:100px;">�����ˣ�</td>
					<td class="td_1"><input value="" id="ggry" name="notice_person" style="width:200px;" class="plui-validatebox input test" data-options="required:true" readonly="readonly"/></td>

					</td>
				</tr>
				<tr>
					<td class="title bg2" style="width:100px;">��������(��)��</td>
					<td class="td_1"><input value=""  id="ggqx" name="notice_limit" style="width:200px;" class="plui-validatebox input test" data-options="required:true"/>
					</td>
					<td class="title bg2" style="width:100px;">����ʱ�䣺</td>
					<td class="td_2"><input value="" id="ggsj" name="noticie_date" style="width:200px;" class="plui-datetimebox test" data-options="required:true"/>
					
					
				</tr>
				<tr>
				   <td class="title bg1" style="width:100px;">������λ��</td>
					<td class="td_2"><input value="" id="fbdw" name="notice_pub_off" style="width:200px;" class="plui-validatebox input test" data-options="required:true"/></td>
					<td class="title bg1" style="width:100px;">����ʱ�䣺</td>
					<td class="td_1"><input value="" id="fbsj" name="notice_pub_time" style="width:200px;" class="plui-datetimebox test" data-options="required:true" /></td>
                    
				</tr>
				<tr>
					<td class="title bg2" style="width:100px;">������������ڣ�</td>
					<td class="td_1"><input value="" id="cbwdzrq" name="pub_date" style="width:200px;" class="plui-datetimebox test" data-options="required:true"/></td>                  
				    <td class="title bg2" style="width:100px;">���������Ƽ����ڣ�</td>
					<td class="td_1"><input value="" id="cbwmc" name="pub_name_date" style="width:200px;" class="plui-validatebox input test" data-options="required:true"/></td>
				</tr>
				
				
				
			</table>
			
			</br>
			</div>
		
		         <!-- ���� -->
          <div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;" >����</label>
		</div> 
		  <div class="datagrid-wrap panel-body">
			<table >
				<tr>
					<th colspan="8" scope="col" style="width:auto">
					<textarea colspan="8" id="cs" 
							name="cs" style="height:100px;width:700px"></textarea>
					</th>
					<th width="65" scope="col">
							<input type="button" name="Submit"  id="cscyy" value="������" onclick="selectCyy()"/>
						</th>
				</tr>
				<tr >
					<th width="123" scope="row">&nbsp;</th>
					<td width="71">&nbsp;</td>
					<td width="69">&nbsp;</td>
					<td width="76">&nbsp;</td>
					<td width="50" sytle="text-align:right">ǩ����</td>
					<td width="107"><input type="text" name="csr" id="csr"
						class="plui-validatebox input" data-options="required:true"
						style="width:140px;" disabled="disabled"/>
					</td>
					<td width="78" sytle="text-align:right">ǩ�����ڣ�</td>
					<td width="84"><input id="cssj" name="cssj" disabled="disabled"
						style="width:140px;" />
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
         </div>
		
		
		
		<!-- ������� -->	
			 <div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;" >����</label>
		</div> 
		  <div class="datagrid-wrap panel-body">
			<table >
				<tr>
					<th colspan="8" scope="col" style="width:auto">
					<textarea colspan="8" id="fs" 
							name="fs" style="height:100px;width:700px"></textarea>
					</th>
					<th width="65" scope="col">
							<input type="button" name="Submit"  id="fscyy" value="������" onclick="selectCyy()"/>
						</th>
				</tr>
				<tr >
					<th width="123" scope="row">&nbsp;</th>
					<td width="71">&nbsp;</td>
					<td width="69">&nbsp;</td>
					<td width="76">&nbsp;</td>
					<td width="50" sytle="text-align:right">ǩ����</td>
					<td width="107"><input type="text" name="fsr" id="fsr"
						class="plui-validatebox input" data-options="required:true"
						style="width:140px;" disabled="disabled"/>
					</td>
					<td width="78" sytle="text-align:right">ǩ�����ڣ�</td>
					<td width="84"><input id="fssj" name="fssj" disabled="disabled"
						style="width:140px;" />
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
         </div>

         
         
         
         <!-- ��׼��� -->
          <div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;" >��׼</label>
		</div> 
		<div class="datagrid-wrap panel-body">
			<table >
				<tr>
					<th colspan="8" scope="col" style="width:auto">
					<textarea colspan="8" id="hz"
							name="hz" style="height:100px;width:700px"></textarea>
					</th>
					<th width="65" scope="col">
							<input type="button" name="Submit" id="hzcyy" value="������" onclick="selectCyy()"/>
						</th>
				</tr>
				<tr >
					<th width="123" scope="row">&nbsp;</th>
					<td width="71">&nbsp;</td>
					<td width="69">&nbsp;</td>
					<td width="76">&nbsp;</td>
					<td width="50" sytle="text-align:right">ǩ����</td>
					<td width="107"><input type="text" name="hzr" id="hzr"
						class="plui-validatebox input" data-options="required:true"
						style="width:140px;" disabled="disabled">
					</td>
					<td width="78" sytle="text-align:right">ǩ�����ڣ�</td>
					<td width="84"><input id="hzsj" name="hzsj" disabled="disabled"
						style="width:140px;" />
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			</div>
		</form>
		</br>
		 <div style="text-align:center;display:none">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="submit()">����</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('add_user_win');">ȡ��</a>
	        </div>
</div>
</div>   

<div id="dd"></div> 
</form>
</body>
</html>
