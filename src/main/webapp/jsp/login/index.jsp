<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <title>�����в������Ǽ�ϵͳ</title>
     <%@ include file="/base/taglibs.jsp" %>
     <%@ include file="/base/prefix.jsp" %>
     <link rel="stylesheet" type="text/css" href="${ctx}/css/index.css">
     <script type="text/javascript" src="${ctx}/js/plui/jslib/fisheye.js"></script>
     <script type="text/javascript" src="${ctx}/js/plui/jslib/event.js"></script>  
     <script type="text/javascript" src="${ctx}/js/login/index.js"></script>
   </head>
<body class="plui-layout">
		<!-- �Ϸ���'north')ͷ������ -->
    	<div id="top" data-options="region:'north',href:'login!home.action?u=north',onLoad:northLoad"  style="height:88px;overflow:hidden;"></div>
		<div data-options="region:'west',split:true" title="�����˵�" style="width:210px;">
			<ul id="tt" class="plui-tree" data-options="url:'../leftmenu/menu!getFormTreeJson.action?time=new Date()',onClick:function(node){showDoc(node);},onLoadSuccess:function(node, data){$('#tt').tree('collapseAll');}"></ul>
			<!-- �˵�Ԫ�ء���index.jsp��loadSuccess()�����м��ز˵� -->
		      <!--   <div id="menu"></div>  -->
		     <!-- <ul id="menu"></ul>  -->
		</div>
		<div data-options="region:'center'">
		<div id="tabs" class="plui-tabs" data-options="fit:true,border:false,plain:true,tools:'#tab-tools'">
			<div title="��ҳ" data-options="closable:true" style="overflow:hidden">
				<iframe scrolling="yes" frameborder="0"  src="login!home.action?u=home" style="width:100%;height:100%;"></iframe>
			</div>
		</div>
		 <!-- ѡ��Ĺ����� -->
        <div id="tab-tools" >
        	<!-- plui��ť��linkbutton����� -->
            <a href="javascript:reloadTad();" class="plui-linkbutton" title="�������뵱ǰѡ�" data-options="plain:true,iconCls:'icon-reload'"></a>
            <!-- plui��ť��linkbutton����� -->
            <a href="javascript:closeThis();" title="�رյ�ǰѡ�" id="mb" class="plui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" ></a>
            <!-- plui�����˵���menubutton�������������
            	menu: css/jQueryѡ������ѡ��ҳ���һ��Ԫ����Ϊ�˲˵������������塣�˴�ѡ����idΪmm��Ԫ��
             -->
            <a class="plui-menubutton" title="����" data-options="menu:'#mm',plain:true" onclick=""></a>
            <!-- �˵�������� -->
            <div id="mm" style="width:100px!important;">
                <div><a style="color:#000;text-decoration:none;" href="javascript:closeOthers();">�ر�����</a></div>
                <div><a style="color:#000;text-decoration:none;" href="javascript:closeAll();">ȫ���ر�</a></div>
            </div>
        </div>

		</div>
		<!-- �·���'south')�ײ����򣨰�Ȩ�� -->
    	<div data-options="region:'south',href:'login!home.action?u=south'" style="height:20px;"></div>
		
</body>
</html>