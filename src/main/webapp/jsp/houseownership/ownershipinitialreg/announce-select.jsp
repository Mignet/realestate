<%@ page language="java" import="java.util.*" pageEncoding="GBK"
	contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>选择公告模版</title>
<meta http-equiv="content-type" content="text/html;charset=GBK">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="">
 <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
<script type="text/javascript" src="${ctx}/js/houseownership/ownershipinitialreg/announce-select.js"></script>	
<script type="text/javascript">
var ctx = '${ctx}';
</script>
</head>
<body  class="body_set">
	<div  class="plui-layout" style="width:100%;height:100%;">  
	<div data-options="region:'center'" border=false>
          <table id="table_cyy"></table>   
  	</div>
  	<div data-options="region:'south'" style="height:28px;">
  	
  		 <div style="text-align:center">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="submit();">确定</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('anmodel');">取消</a>
	        </div>
  	
  	
  	</div>     
  	</div>
</body>
</html>
