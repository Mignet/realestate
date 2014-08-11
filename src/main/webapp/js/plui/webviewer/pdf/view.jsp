<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%
	/*
	String mRecordID = request.getParameter("mRecordID");
	String mTemplate = request.getParameter("mTemplate");
	String mFileName = request.getParameter("mFileName");
	String mFileType = request.getParameter("mFileType");
	String mEditType = request.getParameter("mEditType");
	String mUserName = request.getParameter("mUserName");
	String mExtParam = request.getParameter("mExtParam");
	*/
	String mUserName = request.getParameter("mUserName");
	String fileInfo = request.getParameter("fileInfo");
	
	String urlStr = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>文件内容</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
        
        <jsp:include page="../../head.jsp" />
        <script type="text/javascript">		
			$(function() {
				
				$('#contentDiv').panel({
					content : '<OBJECT id="WebPDF" width="100%" height="100%" classid="clsid:39E08D82-C8AC-4934-BE07-F6E816FD47A1" codebase="iWebPDF.cab#version=8,0,0,538" ></OBJECT>'
				});
				
				var webPDF = document.all.WebPDF;

				webPDF.attachEvent('OnToolsClick', function(vIndex, vCaption) {
					if (vIndex == '1') {
						webPDF.WebPrint(0, '', 0, 0, true);
						$.ajax({
		        			url : $.contextPath + '/'+$.filereader.actionURL+'?method=print',
		        			type : 'post',
		        			data : webPDF.fileInfo
		        		});
					}
				});

				/*
				var script = [];
				script.push('<script language="javascript" for="WebPDF" event="OnToolsClick(vIndex, vCaption)">');
				script.push('alert(1);');
				script.push('</');
				script.push('script>');
				alert(script.join(''));
				*/
				
				/*
				var s = document.createElement('script');
				s.language = 'javascript';
				s.htmlFor = 'WebPDF';
				s.event = 'OnToolsClick(vIndex, vCaption)';
				s.innerHTML = 'var aaa;';
				
				alert(s.outerHTML);
				*/
				//script.appendTo('body');
				
				var fileInfo = $.evalJSON(decodeURI('<%= fileInfo %>'));
				webPDF.fileInfo = fileInfo;
				//以下属性必须设置，实始化iWebPDF
				webPDF.WebUrl= '<%=urlStr%>' + $.filereader.contextPath + '/' + $.filereader.actionURL + '?method=process';	//WebUrl:系统服务器路径，与服务器文件交互操作，如保存、打开文档，重要文件
				webPDF.RecordID=fileInfo.id;	//RecordID:本文档记录编号
				//webPDF.Template=fileInfo.template;	//Template:模板编号
				webPDF.FileName=fileInfo.fileName;	//FileName:文档名称
				webPDF.FileType="."+fileInfo.fileExt;	//FileType:文档类型  .doc  .xls  .wps
				//webPDF.EditType=fileInfo.editType;	//EditType:编辑类型  方式一、方式二  <参考技术文档>
				webPDF.UserName="<%=mUserName%>";	//UserName:操作用户名，痕迹保留需要
				webPDF.ExtParam=decodeURI('<%=fileInfo%>');
				
				//禁用PrScrn键拷屏
				webPDF.PrnScreen = false;
				
				//初始权限
				//webPDF.PrintRight = '0';
				//webPDF.ShowTools = '0';
				webPDF.ShowSigns = '0';
				webPDF.ShowMenus = '0';
				//webPDF.ShowMarks = '0';
				webPDF.ShowSides = '0';
				
				//设置权限
				setFilePermission();
				
				webPDF.WebOpen();
				
				/**
				 * 设置文件权限
				 */
				function setFilePermission() {
					
					var tools = ['打开文档', '保存文档', '另存为', '关闭文档', '文档属性', '搜索文本', '快照工具', '打印文档'];
					
					//webPDF.EnableTools('打开文档;保存文档;另存为;关闭文档;打印文档;文档属性;搜索文本;文本选择;快照工具;', false);
					
					//设置文件可写
					if (fileInfo.permission.indexOf('C') == -1) {
						tools.push('文本选择');
					}
					if (fileInfo.permission.indexOf('P') != -1) {
						webPDF.AppendTools('1', '打印', 16);
					}
					webPDF.EnableTools(tools.join(';'), false);
				};
			});
			
        </script>
    </head>

    <body>  
	    <div id="contentDiv" class="plui-panel" fit="true" border=false>
   		</div>
    </body>
</html>
