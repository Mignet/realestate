<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>�༭������Ŀ����</title>
        <%@ include file="/base/taglibs.jsp"%>
		<%@ include file="/base/prefix.jsp" %>
	  <script type="text/javascript" src="${ctx}/js/basedata/project-editMain.js"></script>
	  <script type="text/javascript">
	  var ctx = '${ctx}';
	  </script>    
    </head>
    <body>
      <div id="editmain" fit=true border=false style="margin:0;">
          <div title="������Ϣ" type="frame" href="project-edit.jsp" data-options="args:args"></div>
         <!--  <div title="����������" type="frame" href="project-show.jsp" data-options="args:args"></div> -->
         <!--  <div title="�����߼���" type="frame" href="relDept.jsp" data-options="args:args"></div>
          <div title="������Ŀ" type="frame" href="relRole.jsp" data-options="args:args"></div>-->
      </div>  
    </body>
</html>
