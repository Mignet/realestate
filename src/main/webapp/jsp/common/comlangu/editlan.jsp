<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>编辑常用语</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp"%>
<script type="text/javascript" src="${ctx}/js/common/comlangu/editlan.js"></script>        
<script type="text/javascript">
var ctx = '${ctx}';
</script>
    </head>
    <body>
    	<div class="plui-panel" data-options="fit:true,border:false">
	    	<form id="edit_user_form" method="post">  
	    	<input type="hidden"  id="mc1" name="co.language_name" />
	    	<input type="hidden"  id="nr1" name="co.language_content" />
	    	<input type="hidden"  id="cyyid1" name="co.language_id" />
	    	<input type="hidden"  id="bus_type_id" name="co.bus_type_id" />
	    	<input type="hidden"  id="mblx1" name="co.temp_type" />
	    	  	
	        	       	
	        </form>
	        <div style="text-align:center">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="submit()">保存</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('edit_user_win');">取消</a>
		    </div>
	    </div>
    </body>
</html>

