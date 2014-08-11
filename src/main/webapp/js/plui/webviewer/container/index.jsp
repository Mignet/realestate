<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>常规布局</title>
    
    <jsp:include page="../../head.jsp" />    
    
	<style type="text/css">
        div#top div.title{
        	margin:15px 40px;
			font-size:20px;
			font-family:Arial, Helvetica, sans-serif;
			font-weight:bold;
			color:#2A1FFF;
        }
        div#top span#ver{
			font-size:14px;
			margin-left: 30px;
			font:"Times New Roman", Times, serif;
        }
    </style>

	<script type="text/javascript">
	
		var options = $.evalJSON(decodeURI(decodeURI('<%= request.getParameter("options") %>')));		
		
		$(function(){
			$('body').filereader(options);
		});
		
	</script>

</head>

<body></body>

</html>
