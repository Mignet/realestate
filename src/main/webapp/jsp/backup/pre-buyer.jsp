<%@ page language="java" import="java.util.*" pageEncoding="GBK"
	contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>登记信息</title>
<meta http-equiv="content-type" content="text/html;charset=GBK">
	<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">
				<meta http-equiv="keywords" content="">

		 <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
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
<link rel="stylesheet" type="text/css" href="../../plui/demo/demo.css">
<link rel="stylesheet" type="text/css" href="../../plui/themes/gray/plui.css">
<script type="text/javascript">
var ctx = '${ctx}';
</script>
<script type="text/javascript" src="${ctx}/js/backup/pre-buyer.js"></script>
</head>
<body>
	<div id='datagrid_user'></div>
	
	 <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">预购方详细信息</label>
		</div> 
		
		<div class="datagrid-wrap panel-body">
		<form id="edit_user_form" method="post">    	
		        	       	<input type="hidden" id="sqrbid" name="applicant_id"/>  
		        	       	<input type="hidden" id="bus_id" name="bus_id"/>  
		        	       	<input type="hidden" id="zjlx1" name="app_cer_type"/>  
		        	       	<input type="hidden" id="sqr1" name="app_name"/>  
		        	       	<input type="hidden" id="sqrlx1" name="app_type"/>  
		        	       	<input type="hidden" id="fddbr1" name="legal_name"/>  
		        	       	<input type="hidden" id="zjbh1" name="app_cer_no"/>  
		        	       	<input type="hidden" id="fe1" name="app_port"/>  
		        	       	<input type="hidden" id="dz1" name="app_address"/>  
		        	       	<input type="hidden" id="lxdh1" name="app_tel"/>   
		        	       	<input type="hidden" id="dlr1" name="agent_name"/>   
		        	       	<input type="hidden" id="dlrsfzh1" name="agent_cer"/>   
		        	       	<input type="hidden" id="dlrdh1" name="agent_tel"/> 
		        	       	<input type="hidden" id="dlrzjlx1" name="agent_cer_type"/>    
		        	       	<input type="hidden" id="djdybh" name="reg_unit_code"/>    
		</form>
	</div>
</body>
</html>
