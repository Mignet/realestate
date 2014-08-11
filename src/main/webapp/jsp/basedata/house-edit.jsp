<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>编辑房屋</title>
        <%@ include file="/base/taglibs.jsp"%>
		<%@ include file="/base/prefix.jsp" %>
      <script type="text/javascript" src="${ctx}/js/basedata/house-edit.js"></script>
	  <script type="text/javascript">
	  var ctx = '${ctx}';
	  </script>  
    </head>
    <body>
    	<div class="plui-panel" data-options="fit:true,border:false">
	    	<form id="edit_house_form" method="post">    	
	        	<input type="hidden" id="bldg_id" name="bldg_id"/>        	
	        </form>
	        <div style="text-align:center">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="$('#edit_house_form').submit();">保存</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('edit_house_win');">取消</a>
		    </div>
	    </div>
    </body>
</html>
