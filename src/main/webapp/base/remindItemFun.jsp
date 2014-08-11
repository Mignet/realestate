<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/base/taglibs.jsp"%>
<script language="javascript">
    //提醒统计框（点击框架页顶页的”提醒“项会弹出;进入首页时会弹出），点击其中的统计项，调整到相应页面
	//跳转到记事本页面
 function goNotepadPage(){
    window.location = "${ctx}/sysmanage/notepad/notepad.action";
 }
  //跳转到待签收工作页面
 function goWaitForSignPage(){
    window.location = "${ctx}/sysmanage/workflow/workflow-main!goInitPage.action";
 }
 
 //跳转到待处理工作页面
 function goWaitForDealPage(){
    window.location = "${ctx}/sysmanage/workflow/workflow-main!goInitPage.action?showFlag=1";
 }	
</script>


