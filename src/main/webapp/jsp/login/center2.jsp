<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
 <%@ include file="/base/taglibs.jsp"%>
<!-- 此页面为布局中部区域，分为左侧菜单栏和右侧内容区域两部分 -->
<div class="center">
	<!-- 左侧菜单栏 -->
    <div class="cleft" id="cleft">
    	<!-- 菜单元素。在index.jsp的loadSuccess()方法中加载菜单 -->
        <div id="menu"></div>
    </div>
    <!-- 右侧内容区域 -->
    <div class="cright" id="cright">
    	<!-- plui选项卡（tabs）组件。点击左侧菜单时，会在此组件中添加选项卡。此功能于菜单组件的实现js（frameMenu.js）中实现。参数： 
        	tools: css/jQuery选择器，选择页面的一个元素作为此选项卡组件的工具栏。此处选择了id为tab-tools的元素
        -->
        <div id="tabs" class="plui-tabs" data-options="fit:true,border:false,tools:'#tab-tools'"></div>
        <!-- 选项卡的工具栏 -->
        <div id="tab-tools">
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
</div>
<a class="switch_close" style="left:203px;" id="switch"></a>

<script>

</script>

