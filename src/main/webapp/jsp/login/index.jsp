<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <title>深圳市不动产登记系统</title>
     <%@ include file="/base/taglibs.jsp" %>
     <%@ include file="/base/prefix.jsp" %>
     <link rel="stylesheet" type="text/css" href="${ctx}/css/index.css">
     <script type="text/javascript" src="${ctx}/js/plui/jslib/fisheye.js"></script>
     <script type="text/javascript" src="${ctx}/js/plui/jslib/event.js"></script>  
     <script type="text/javascript" src="${ctx}/js/login/index.js"></script>
   </head>
<body class="plui-layout">
		<!-- 上方（'north')头部区域 -->
    	<div id="top" data-options="region:'north',href:'login!home.action?u=north',onLoad:northLoad"  style="height:88px;overflow:hidden;"></div>
		<div data-options="region:'west',split:true" title="导航菜单" style="width:210px;">
			<ul id="tt" class="plui-tree" data-options="url:'../leftmenu/menu!getFormTreeJson.action?time=new Date()',onClick:function(node){showDoc(node);},onLoadSuccess:function(node, data){$('#tt').tree('collapseAll');}"></ul>
			<!-- 菜单元素。在index.jsp的loadSuccess()方法中加载菜单 -->
		      <!--   <div id="menu"></div>  -->
		     <!-- <ul id="menu"></ul>  -->
		</div>
		<div data-options="region:'center'">
		<div id="tabs" class="plui-tabs" data-options="fit:true,border:false,plain:true,tools:'#tab-tools'">
			<div title="主页" data-options="closable:true" style="overflow:hidden">
				<iframe scrolling="yes" frameborder="0"  src="login!home.action?u=home" style="width:100%;height:100%;"></iframe>
			</div>
		</div>
		 <!-- 选项卡的工具栏 -->
        <div id="tab-tools" >
        	<!-- plui按钮（linkbutton）组件 -->
            <a href="javascript:reloadTad();" class="plui-linkbutton" title="重新载入当前选项卡" data-options="plain:true,iconCls:'icon-reload'"></a>
            <!-- plui按钮（linkbutton）组件 -->
            <a href="javascript:closeThis();" title="关闭当前选项卡" id="mb" class="plui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" ></a>
            <!-- plui下拉菜单（menubutton）组件。参数：
            	menu: css/jQuery选择器，选择页面的一个元素作为此菜单组件的下拉面板。此处选择了id为mm的元素
             -->
            <a class="plui-menubutton" title="更多" data-options="menu:'#mm',plain:true" onclick=""></a>
            <!-- 菜单下拉面板 -->
            <div id="mm" style="width:100px!important;">
                <div><a style="color:#000;text-decoration:none;" href="javascript:closeOthers();">关闭其他</a></div>
                <div><a style="color:#000;text-decoration:none;" href="javascript:closeAll();">全部关闭</a></div>
            </div>
        </div>

		</div>
		<!-- 下方（'south')底部区域（版权） -->
    	<div data-options="region:'south',href:'login!home.action?u=south'" style="height:20px;"></div>
		
</body>
</html>