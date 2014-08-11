<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>默认异常</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
        
        <%@ include file="/base/prefix.jsp"%>
                
        <script>
			$(function(){
				$('#submit').bind('click',function(){
					$.ajax({
						//提交的数据
						data:{
							index: 2
						},
						url: '../../errorDelegate/throwSpecifiedException.run',
						success: function(data){}
					});
				});
			});
        </script>
    </head>
    <body>    	
		<a id="submit" iconCls="icon-save" href="#" class="plui-linkbutton">测试</a>
    </body>
</html>

