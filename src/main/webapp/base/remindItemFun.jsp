<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/base/taglibs.jsp"%>
<script language="javascript">
    //����ͳ�ƿ򣨵�����ҳ��ҳ�ġ����ѡ���ᵯ��;������ҳʱ�ᵯ������������е�ͳ�����������Ӧҳ��
	//��ת�����±�ҳ��
 function goNotepadPage(){
    window.location = "${ctx}/sysmanage/notepad/notepad.action";
 }
  //��ת����ǩ�չ���ҳ��
 function goWaitForSignPage(){
    window.location = "${ctx}/sysmanage/workflow/workflow-main!goInitPage.action";
 }
 
 //��ת����������ҳ��
 function goWaitForDealPage(){
    window.location = "${ctx}/sysmanage/workflow/workflow-main!goInitPage.action?showFlag=1";
 }	
</script>


