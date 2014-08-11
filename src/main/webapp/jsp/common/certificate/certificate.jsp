<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>编辑用户</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
        
 <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
   <script type="text/javascript">
  var ctx = '${ctx}';
  </script>   

<script type="text/javascript" src="${ctx}/js/common/certificate/certificate.js"></script>
    </head>
    <body>
    	<div class="plui-panel" data-options="fit:true,border:false">
	    	<form id="edit_user_form" method="post">    	
	        	       	<input type="hidden" id="sqrbid" name="app.applicant_id"/>  
	        	       	<input type="hidden" id="bus_id" name="app.bus_id"/>  
	        	       	<input type="hidden" id="zjlx1" name="app.app_cer_type"/>  
	        	       	<input type="hidden" id="sqr1" name="app.app_name"/>  
	        	       	<input type="hidden" id="sqrlx1" name="app.app_type"/>  
	        	       	<input type="hidden" id="fddbr1" name="app.legal_name"/>  
	        	       	<input type="hidden" id="zjbh1" name="app.app_cer_no"/>  
	        	       	<input type="hidden" id="fe1" name="app.app_port"/>  
	        	       	<input type="hidden" id="dz1" name="app.app_address"/>  
	        	       	<input type="hidden" id="lxdh1" name="app.app_tel"/>   
	        	       	<input type="hidden" id="dlr1" name="app.agent_name"/>   
	        	       	<input type="hidden" id="dlrsfzh1" name="app.agent_cer"/>   
	        	       	<input type="hidden" id="dlrdh1" name="app.agent_tel"/> 
	        	       	<input type="hidden" id="dlrzjlx1" name="app.agent_cer_type"/>    
	        	       	<input type="hidden" id="gx" name="app.hol_rel"/>    
	        	       	<input type="hidden" id="djdybh" name="app.reg_unit_code"/>    
	        </form>
	        <div style="text-align:center">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="submit();">保存</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('edit_user_win');">取消</a>
		    </div>
	    </div>
    </body>
</html>

