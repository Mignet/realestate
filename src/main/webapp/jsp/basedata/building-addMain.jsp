<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>新增建筑物</title>
        <%@ include file="/base/taglibs.jsp"%>
		<%@ include file="/base/prefix.jsp" %>
	  <script type="text/javascript">
	  var ctx = '${ctx}';
	  function init(){
			$('#addmain').tabs();
		};
	  </script>    
    </head>
    <body>
      <div id="addmain" fit=true border=false style="margin:0;">
      	<div title="基本信息" type="frame" href="building-add.jsp" data-options="args:args"></div>
          <!-- <div title="建筑物立面展示" type="frame" href="building-show.jsp" data-options="args:args"></div> -->
         <!--  <div title="关联逻辑栋" type="frame" href="relDept.jsp" data-options="args:args"></div>
          <div title="关联项目" type="frame" href="relRole.jsp" data-options="args:args"></div>-->
      </div>  
    </body>
</html>
