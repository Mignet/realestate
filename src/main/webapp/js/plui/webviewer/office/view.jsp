<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%
	/* 
	String mRecordID = request.getParameter("mRecordID");
	String mTemplate = request.getParameter("mTemplate");
	String mFileName = request.getParameter("mFileName");
	String mFileType = request.getParameter("mFileType");
	String mEditType = request.getParameter("mEditType");
	
	String mExtParam = request.getParameter("mExtParam");
	*/
	String mUserName = request.getParameter("mUserName");
	String fileInfo = request.getParameter("fileInfo");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>�ļ�����</title>
        
        <jsp:include page="../../head.jsp" />
        <script type="text/javascript">		
			$(function(){
				var fileInfo = $.evalJSON(decodeURI('<%= fileInfo %>'));
				//�������Ա������ã�ʵʼ��iWebOffice
				var webOffice = document.all.WebOffice;
				webOffice.fileInfo = fileInfo;
				webOffice.WebUrl=$.filereader.actionURL + '?method=process';		//WebUrl:ϵͳ������·������������ļ������������籣�桢���ĵ�����Ҫ�ļ� 
				webOffice.RecordID=fileInfo.id;	//RecordID:���ĵ���¼���
				webOffice.Template=fileInfo.template;	//Template:ģ����
				webOffice.FileName=fileInfo.fileName;	//FileName:�ĵ�����
				webOffice.FileType="."+fileInfo.fileExt;	//FileType:�ĵ�����  .doc  .xls  .wps
				//webOffice.EditType=fileInfo.editType;	//EditType:�༭����  ��ʽһ����ʽ��  <�ο������ĵ�>
				webOffice.EditType="0,0";
				webOffice.UserName="<%=mUserName%>";	//UserName:�����û������ۼ�������Ҫ
				webOffice.ExtParam=decodeURI('<%=fileInfo%>');
				//webOffice.Print = "1";
				//webOffice.ShowToolBar = "2";
				/*
				 * ���ز˵�����Office������
				 * �����Զ��幤�����в���Ҫ�İ�ť
				 */
				 //���ز˵���
				webOffice.ShowMenu = '0';
				//��ʾ�Զ��幤����������office������
				webOffice.ShowToolBar = '3';
				//�����Զ��幤�����в���Ҫ�İ�ť
				webOffice.VisibleTools('�½��ļ�', false);
				webOffice.VisibleTools('���ļ�', false);
				webOffice.VisibleTools('�����ļ�', false);
				webOffice.VisibleTools('������ע', false);
				webOffice.VisibleTools('��д��ע', false);
				webOffice.VisibleTools('�ĵ����', false);
				webOffice.VisibleTools('������ע', false);
				//webOffice.DisableTools('�����ļ�', true);
				//webOffice.AppendTools('1', '��ӡ�ĵ�', 16);
				
				//����Ȩ��
				setFilePermission();
				
				webOffice.WebOpen();
				//StatusMsg(webOffice.Status);			//״̬��Ϣ
				
				/**
				 * �����ļ�Ȩ��
				 */
				function setFilePermission() {
					//�����ļ���д
					if (fileInfo.permission.indexOf('C') != -1) {
						webOffice.EditType = '4,0';
					}
					if (fileInfo.permission.indexOf('P') != -1) {
						webOffice.AppendTools('1', '��ӡ�ĵ�', 16);
					}
				};
				
			});
			
        </script>
        <script language="javascript" for="WebOffice" event="OnToolsClick(vIndex, vCaption)">
        	if (vIndex == '1') {
        		WebOpenPrint();
        		$.ajax({
        			url : $.filereader.contextPath + '/' + $.filereader.actionURL + '?method=print',
        			type : 'post',
        			data : fileInfo
        		});
        	}
        </script>
    </head>

    <body>
    	<div id="contentDIV" class="plui-panel" fit="true" border=false>
    		<object id="WebOffice" width="100%" height="100%" classid="clsid:8B23EA28-723C-402F-92C4-59BE0E063499" codebase="iWebOffice2006.cab#version=9,3,0,0"></object>
    	</div>
    </body>
</html>
