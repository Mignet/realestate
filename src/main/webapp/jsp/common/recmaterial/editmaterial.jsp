<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK" import="com.szhome.security.ext.UserInfo"%>
<%
  UserInfo user=(UserInfo)session.getAttribute("userInfo");
  String userName=user.getUserName();
  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>�༭�û�</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
<%@ include file="/base/taglibs.jsp"%>
  <%@ include file="/base/prefix.jsp"%>
  <script type="text/javascript" src="${ctx}/js/common/recmaterial/editmaterial.js"></script>	
    <script type="text/javascript"> 	
 var user1 = '<%=userName%>';	
  var ctx = '${ctx}';	 
</script>
    </head>
    <body>
    	<div class="plui-panel" data-options="fit:true,border:false">
	    	<form id="edit_user_form" method="post">   
	    	<input type="hidden"  id="mc1" name="rmc.cfig_rec_name"/>	
	    	<input type="hidden"  id="lx1" name="rmc.cfig_rec_type"/>	
	    	<input type="hidden"  id="zl1" name="rmc.cfig_rec_style"/>	
	    	<input type="hidden"  id="fs1" name="rmc.cfig_rec_copy"/>	
	    	<input type="hidden"  id="pzr1" name="rmc.cfig_person"/>	
	    	<input type="hidden"  id="pzsj1" name="rmc.cfig_date"/>	
	    	<input type="hidden"  id="ywlxid" name="rmc.bus_type_id"/>
	    	<input type="hidden"  id="jjclpzbid"	name="rmc.cfig_receival_id"/>
	    	<input type="hidden"  id="ys"	name="rmc.cfig_page"/>
	        <input type="hidden"  id="rec_type_flag" name="rmc.rec_type_flag"/>		       	
	        </form>
	        <div style="text-align:center">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="submit()">����</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('edit_user_win');">ȡ��</a>
		    </div>
	    </div>
    </body>
</html>

