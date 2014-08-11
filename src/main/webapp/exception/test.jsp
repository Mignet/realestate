<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>异常测试</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
        
        <%@ include file="/base/prefix.jsp"%>
                
        <script>
			$(function(){
				$('#tab').tabs();
			});
        </script>
    </head>
    <body>    	
		<div id="tab" fit=true border=false style="margin:0;">
        	<div title="测试0" type="frame" href="error0.jsp"></div>
            <div title="测试1" type="frame" href="error1.jsp"></div>
            <div title="测试2" type="frame" href="error2.jsp"></div>
            <div title="测试3" type="frame" href="error3.jsp"></div>
            <div title="默认" type="frame" href="default.jsp"></div>
            <div title="表单" type="frame" href="form.jsp"></div>
        </div>
    </body>
</html>

