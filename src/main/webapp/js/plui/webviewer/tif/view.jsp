<%@ page language="java"  pageEncoding="GBK"%>
<!-- 多页单次加载 在页面加载完时，阅读器大小随之调整，只调整一次-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">	
    <head> 
        <title>查阅TIF</title>         
	    
	    <jsp:include page="../../head.jsp" />
	    <script type="text/javascript" src="../js/commonFileReader.js"></script>
	    
	    <script type="text/javascript">
		    var __fileInfo = $.evalJSON(decodeURI('<%= request.getParameter("fileInfo") %>'));
			var urls = [];
			urls.push($.filereader.contextPath, '/',  $.filereader.actionURL);
			urls.push('?', 'method=tifView&', jQuery.param(__fileInfo));
			var tifurl = urls.join('');
			
			//window.open(tifurl);
			
			$(function() {
				
				var objhtml = [];
				objhtml.push('<object id="tifviewer" width="100%" height="100%" classid="clsid:106E49CF-797A-11D2-81A2-00E02C015623">');
				objhtml.push('<param name="mousemode" value="pan" />');
				objhtml.push('<param name="toolbar" value="on" />');
				objhtml.push('<param name="src" value="' + tifurl + '" />');
				objhtml.push('</object>');
				$(document.body).append(objhtml.join(''));
				
				//var objTag = $('<object />');
				//objTag.attr({ width : '100%', height : '100%', classid : 'clsid:106E49CF-797A-11D2-81A2-00E02C015623' });
				//$('<param />').appendTo(objTag).attr({ name : 'mousemode', value : 'none' });
				//$('<param />').appendTo(objTag).attr({ name : 'toolbar', value : 'on' });
				//$('<param />').appendTo(objTag).attr({ name : 'src', value : tifurl });
				
				//alert(objTag[0].outerHTML);
				//objTag.appendTo($(document.body));
				
			});
		</script>
	    
    </head> 
    <body>
		<!-- <object id="tifviewer" width="100%" height="100%"
			classid="clsid:106E49CF-797A-11D2-81A2-00E02C015623">
			<param name="mousemode" value="none" />
			<param name="toolbar" value="on" />
			<param name="src" value="" />
		</object> -->
		<!-- <embed width="100%" height="100%" src="sample.tif" type="application/x-alternatiff" /> -->
		<!-- <embed width="100%" height="100%" src="sample.tif" type="image/tiff"> -->
   </body>
</html>