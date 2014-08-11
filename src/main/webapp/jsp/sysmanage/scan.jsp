<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" import="com.szhome.security.ext.UserInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>深圳市不动产权登记系统</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%> 
    <script type="text/javascript" src="${ctx}/js/sysmanage/jQuery.json.js"></script>
    <script type="text/javascript" src="${ctx}/js/sysmanage/scan.js"></script>
    <script type="text/javascript">
	  var ctx = '${ctx}';
    </script>
	<style type="text/css">
	   th{
	    text-align:right;
	   }
	</style>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">   
</head>
<body class="plui-layout">
        <div data-options="region:'center'" border=false>
			<div class="plui-layout" fit=true>
				<div data-options="region:'center',border:false">
				      <object id="NKOSmartScan" classid="clsid:DD02961E-E2F3-41a5-AACB-010EF4A70E9F" style="width:779px; height:559px">
                      </object>
			    </div>
			</div>
	 </div>
</body>
</html>
