<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp"%>
<!DOCTYPE HTML>
<html>
  <head>
  	<meta charset="utf-8">
    <title>House Profile</title>
	<meta name="viewport" content="width=device-width" />
    <meta name="Language" content="zh-CN" />
    <script src="${ctx}/js/basedata/raphael-min.js" charset="UTF-8"></script>
    <script src="${ctx}/js/plui/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/js/basedata/building-show.js"></script>
  </head>
  
  <body>
  <input id="projectid" type="hidden" name="projectid" value="${projectid}">
    <div id="holder"></div>
  </body>

</html>