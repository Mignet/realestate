<%@ page language="java"  pageEncoding="GBK"%>
<!-- ��ҳ���μ��� ��ҳ�������ʱ���Ķ�����С��֮������ֻ����һ��-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">	
    <head> 
        <title>����ceb</title>         
	    
	    <jsp:include page="../../head.jsp" />
	    <script type="text/javascript" src="../js/commonFileReader.js"></script>
	    
	    <script type="text/javascript">
		    var fileInfo = $.evalJSON(decodeURI('<%= request.getParameter("fileInfo") %>'));
			var url = $.filereader.actionURL + '?method=defaultView&' + jQuery.param(fileInfo);
		    
			$(function() {
				$('#cebviewer').attr('src',url);
			});
		</script>
	    
    </head> 
    <body>
		<iframe id="cebviewer" frameborder="no" border="0" width="100%" height="100%"></iframe>
   </body>
</html>