<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
 <%@ include file="/base/taglibs.jsp"%>
<!-- ��ҳ��Ϊ�����в����򣬷�Ϊ���˵������Ҳ��������������� -->
<div class="center">
	<!-- ���˵��� -->
    <div class="cleft" id="cleft">
    	<!-- �˵�Ԫ�ء���index.jsp��loadSuccess()�����м��ز˵� -->
        <div id="menu"></div>
    </div>
    <!-- �Ҳ��������� -->
    <div class="cright" id="cright">
    	<!-- pluiѡ���tabs�������������˵�ʱ�����ڴ���������ѡ����˹����ڲ˵������ʵ��js��frameMenu.js����ʵ�֡������� 
        	tools: css/jQueryѡ������ѡ��ҳ���һ��Ԫ����Ϊ��ѡ�����Ĺ��������˴�ѡ����idΪtab-tools��Ԫ��
        -->
        <div id="tabs" class="plui-tabs" data-options="fit:true,border:false,tools:'#tab-tools'"></div>
        <!-- ѡ��Ĺ����� -->
        <div id="tab-tools">
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
</div>
<a class="switch_close" style="left:203px;" id="switch"></a>

<script>

</script>

