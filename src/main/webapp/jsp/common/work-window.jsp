<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <title>深圳市不动产登记系统</title>
     <%@ include file="/base/taglibs.jsp" %>
     <%@ include file="/base/prefix.jsp" %>
    	 <script type="text/javascript">
    	var ctx = '${ctx}';
    	var nodeid = '${nodeid}';var procdefId = '${procdefId}';
        $(function(){
       	 $('#mainPanel').width(820);
       	 $('#eastPanel').width(window.screen.availWidth - 210 -820);
       	 $('#subPanel').layout('panel', 'center').panel('resize',{width:$('#mainPanel').width()});
       	 $('#subPanel').layout('panel', 'east').panel('resize',{width:$('#eastPanel').width()});
       	 $('#subPanel').layout('resize');
        });
    	</script>
    	<script type="text/javascript" src="${ctx}/js/common/work-window.js"></script>
</head>

<body class="plui-layout" id="subPanel">
		<div data-options="region:'center'" style="overflow:hidden" id="mainPanel" >
		<div id="ttleft" class="plui-tabs" style="overflow:hidden" data-options="fit:true,border:false,plain:true">
		</div>
		</div>
		<div data-options="region:'east',split:true" style="overflow:hidden"  id="eastPanel">
			<div id="ttright" class="plui-tabs" style="overflow:hidden" data-options="fit:true,border:false,plain:true">
		</div>
		</div>
		 <div data-options="region:'south',border:false"  style="overflow: hidden;">
            <table style="width: 780px;"><!-- 设置宽度780 -->
               <tr>
                  <td style="width: 50%;padding:6px 6px 6px 6px;">
                    <input type="button" onclick="" value="挂起" style="margin-right: 5px;width:60px;height:25px"/>
                    <input type="button" onclick="" value="暂缓" style="margin-right: 5px;width:80px;height:25px"/>
                  </td>               
                  <td style="width: 50%;text-align:right;padding:6px 6px 6px 6px;">
                      <input type="button" onclick="" value="保存" style="margin-right: 5px;width:60px;height:25px"/>
                      <input type="button" onclick="" value="提交" style="margin-right: 5px;width:60px;height:25px"/>
                  </td>               
               </tr>
            </table>
          </div>
</body>
<!-- <body> 
<div class="cleft" id="cleft">id="ttleft" 
<div id="ttleft" class="plui-tabs" data-options="region:'center'" style="width:800px;height:800px"></div>
</div>
<div class="cright" id="cright">
<div id="ttright" class="plui-tabs" data-options="region:'east'" style="width:800px;height:800px"></div>
</div>
</body> -->
</html>
