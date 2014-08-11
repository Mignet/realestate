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
        <title>文件内容</title>
        
        <jsp:include page="../../head.jsp" />
        <script type="text/javascript">		
			$(function(){
				var fileInfo = $.evalJSON(decodeURI('<%= fileInfo %>'));
				//以下属性必须设置，实始化iWebOffice
				var webOffice = document.all.WebOffice;
				webOffice.fileInfo = fileInfo;
				webOffice.WebUrl=$.filereader.actionURL + '?method=process';		//WebUrl:系统服务器路径，与服务器文件交互操作，如保存、打开文档，重要文件 
				webOffice.RecordID=fileInfo.id;	//RecordID:本文档记录编号
				webOffice.Template=fileInfo.template;	//Template:模板编号
				webOffice.FileName=fileInfo.fileName;	//FileName:文档名称
				webOffice.FileType="."+fileInfo.fileExt;	//FileType:文档类型  .doc  .xls  .wps
				//webOffice.EditType=fileInfo.editType;	//EditType:编辑类型  方式一、方式二  <参考技术文档>
				webOffice.EditType="0,0";
				webOffice.UserName="<%=mUserName%>";	//UserName:操作用户名，痕迹保留需要
				webOffice.ExtParam=decodeURI('<%=fileInfo%>');
				//webOffice.Print = "1";
				//webOffice.ShowToolBar = "2";
				/*
				 * 隐藏菜单栏、Office工具栏
				 * 隐藏自定义工具栏中不需要的按钮
				 */
				 //隐藏菜单栏
				webOffice.ShowMenu = '0';
				//显示自定义工具栏、隐藏office工具栏
				webOffice.ShowToolBar = '3';
				//隐藏自定义工具栏中不需要的按钮
				webOffice.VisibleTools('新建文件', false);
				webOffice.VisibleTools('打开文件', false);
				webOffice.VisibleTools('保存文件', false);
				webOffice.VisibleTools('文字批注', false);
				webOffice.VisibleTools('手写批注', false);
				webOffice.VisibleTools('文档清稿', false);
				webOffice.VisibleTools('重新批注', false);
				//webOffice.DisableTools('保存文件', true);
				//webOffice.AppendTools('1', '打印文档', 16);
				
				//设置权限
				setFilePermission();
				
				webOffice.WebOpen();
				//StatusMsg(webOffice.Status);			//状态信息
				
				/**
				 * 设置文件权限
				 */
				function setFilePermission() {
					//设置文件可写
					if (fileInfo.permission.indexOf('C') != -1) {
						webOffice.EditType = '4,0';
					}
					if (fileInfo.permission.indexOf('P') != -1) {
						webOffice.AppendTools('1', '打印文档', 16);
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
