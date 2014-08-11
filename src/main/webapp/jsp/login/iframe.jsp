<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>深圳市不动产权登记系统</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
</head>
<body>
<!--
	登录页跳转页	
 -->
<script type="text/javascript">
	function getBrowserVersion() {
		var browserVersion="";
		var Sys = {}; 
		var ua = navigator.userAgent.toLowerCase(); 
		window.ActiveXObject ? Sys.ie = ua.match(/msie ([\d.]+)/)[1] : 
		document.getBoxObjectFor ? Sys.firefox = ua.match(/firefox\/([\d.]+)/)[1] : 
		window.MessageEvent && !document.getBoxObjectFor ? Sys.chrome = ua.match(/chrome\/([\d.]+)/)[1] : 
		window.opera ? Sys.opera = ua.match(/opera.([\d.]+)/)[1] : 
		window.openDatabase ? Sys.safari = ua.match(/version\/([\d.]+)/)[1] : 0;
				
		if(Sys.ie) browserVersion='IE'+Sys.ie; 
		if(Sys.firefox) browserVersion='Firefox: '+Sys.firefox; 
		if(Sys.chrome) browserVersion='Chrome: '+Sys.chrome; 
		if(Sys.opera) browserVersion='Opera: '+Sys.opera; 
		if(Sys.safari) browserVersion='Safari: '+Sys.safari; 	
		return browserVersion;
	} 
$(document).ready( function(){
	/* if(getBrowserVersion()!='IE6.0') {	 */	
		//window.location='./index.action';
		window.location.assign('./login!home.action?u=index');
	/* }else {
	    window.open('./login!home.action?u=index','','menubar=no,status=yes,top=0,left=0,title="深圳市不动产权登记系统"');    
	    window.opener=null;
	    window.close();
	}  */
})
</script>

</body>
</html>
