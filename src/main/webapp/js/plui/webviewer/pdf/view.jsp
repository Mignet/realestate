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
        <title>�ļ�����</title>
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
				//�������Ա������ã�ʵʼ��iWebPDF
				webPDF.WebUrl= '<%=urlStr%>' + $.filereader.contextPath + '/' + $.filereader.actionURL + '?method=process';	//WebUrl:ϵͳ������·������������ļ������������籣�桢���ĵ�����Ҫ�ļ�
				webPDF.RecordID=fileInfo.id;	//RecordID:���ĵ���¼���
				//webPDF.Template=fileInfo.template;	//Template:ģ����
				webPDF.FileName=fileInfo.fileName;	//FileName:�ĵ�����
				webPDF.FileType="."+fileInfo.fileExt;	//FileType:�ĵ�����  .doc  .xls  .wps
				//webPDF.EditType=fileInfo.editType;	//EditType:�༭����  ��ʽһ����ʽ��  <�ο������ĵ�>
				webPDF.UserName="<%=mUserName%>";	//UserName:�����û������ۼ�������Ҫ
				webPDF.ExtParam=decodeURI('<%=fileInfo%>');
				
				//����PrScrn������
				webPDF.PrnScreen = false;
				
				//��ʼȨ��
				//webPDF.PrintRight = '0';
				//webPDF.ShowTools = '0';
				webPDF.ShowSigns = '0';
				webPDF.ShowMenus = '0';
				//webPDF.ShowMarks = '0';
				webPDF.ShowSides = '0';
				
				//����Ȩ��
				setFilePermission();
				
				webPDF.WebOpen();
				
				/**
				 * �����ļ�Ȩ��
				 */
				function setFilePermission() {
					
					var tools = ['���ĵ�', '�����ĵ�', '���Ϊ', '�ر��ĵ�', '�ĵ�����', '�����ı�', '���չ���', '��ӡ�ĵ�'];
					
					//webPDF.EnableTools('���ĵ�;�����ĵ�;���Ϊ;�ر��ĵ�;��ӡ�ĵ�;�ĵ�����;�����ı�;�ı�ѡ��;���չ���;', false);
					
					//�����ļ���д
					if (fileInfo.permission.indexOf('C') == -1) {
						tools.push('�ı�ѡ��');
					}
					if (fileInfo.permission.indexOf('P') != -1) {
						webPDF.AppendTools('1', '��ӡ', 16);
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
