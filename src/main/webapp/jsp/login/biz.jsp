<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK" 
 import="com.szhome.security.ext.UserInfo"
 import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
	String userName=userInfo.getUserName();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <title>深圳市不动产登记系统</title>
     <%@ include file="/base/taglibs.jsp" %>
     <%@ include file="/base/prefix.jsp" %>
     <script type="text/javascript">
		var user = '<%=userName%>';	
		var ctx = '${ctx}';
	</script>
    <script type="text/javascript" src="${ctx}/js/common/acceptpage.js"></script>
     <link rel="stylesheet" type="text/css" href="${ctx}/css/index.css">
     <script type="text/javascript" src="${ctx}/js/plui/jslib/fisheye.js"></script>
     <script type="text/javascript" src="${ctx}/js/plui/jslib/event.js"></script>  
     <script type="text/javascript" src="${ctx}/js/login/index.js"></script>
     <script type="text/javascript">
     $(function(){
    	 var center_width = 800;
    	 $('#mainPanel').width(center_width);
    	 $('#eastPanel').width(window.screen.availWidth - 210 -center_width);
    	 $('#subPanel').layout('panel', 'center').panel('resize',{width:$('#mainPanel').width()});
    	 $('#subPanel').layout('panel', 'east').panel('resize',{width:$('#eastPanel').width()});
    	 $('#subPanel').layout('resize');
     });
     </script>
   </head>
<body class="plui-layout" id="subPanel">
		<div id="mainPanel" data-options="region:'center',split:true,minWidth:600,maxWidth:800">
			<div id="tabsI" class="plui-tabs" data-options="fit:true,border:false,plain:true">
				<div title="受理" style="overflow:hidden">
					<!-- <iframe scrolling="yes" frameborder="0"  src="../common/acceptpage.jsp" style="width:100%;height:100%;"></iframe> -->
					<jsp:include page="../common/acceptpage.jsp" />
				</div>
			</div>
		</div>
		<div id="eastPanel" data-options="region:'east',split:true,minWidth:200,maxWidth:600" style="width:300px;">
			<div id="tabsII" class="plui-tabs" data-options="fit:true,border:false,plain:true">
				<!-- <div title="打印" style="overflow:hidden">
					<iframe id="printframe" name="printframe" scrolling="yes" frameborder="0" style="width:100%;height:100%;"></iframe>
					 src="" 
				</div> -->
			</div>
		</div>
		
</body>
</html>