<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>业务事项配置</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
        
<%@ include file="/base/taglibs.jsp"%>
  <%@ include file="/base/prefix.jsp"%>
  <script type="text/javascript" src="${ctx}/js/common/busmatter/busmatter.js"></script>	
    <script type="text/javascript"> 	

  var ctx = '${ctx}';	 
</script>
</head>
<body class="plui-layout">
	<div data-options="region:'west',split:true,title:'业务事项列表'" style="width:200px;">
		<ul id="getProcName" />
		<div id="mm" class="plui-menu" style="width:120px;">  
        <div onClick="append()" data-options="iconCls:'icon-add'">添加</div>  
        <div id="delete" onClick="removeIt()" data-options="iconCls:'icon-remove'">删除</div>  
    </div> 
	</div>
	    
	<div data-options="region:'center'" border=false>
		<table class="edit_tab" style="width: 100%;">
							<tr>
								<td class="title bg1" style="width: 100px;">业务事项名称：</td>
								<td class="td_1"><input value="" id="name" name="name" style="width: 150px;" class="plui-validatebox input reg"  /></td>
								<td class="title bg1" style="width: 100px;">产权种类：</td>
								<td class="td_2"><input class="plui-combodict" value="" id="pro_type" classcode="120" name="pro_type" style="width: 150px;" class="plui-validatebox input reg"  /></td>
							</tr>
							<tr>
								<td class="title bg1" style="width: 100px;">流程：</td>
								<td>
								<input id="cc" class="plui-combobox" name="dept" /> 
								<!-- <input id="parent_id" name="proc" type="text" class="plui-combotree" url="../../leftmenu/menu!getFormTreeJson.action" panelHeight="180" /> -->
								</td>
								<td class="title bg1" style="width: 100px;">排序：</td>
								<td class="td_2"><input value="" id="sort" name="sort" style="width: 150px;" class="plui-validatebox input reg"  /></td>
							</tr>
						</table>
						<br>
						<div style="text-align:center">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="updateIt()" disabled="disabled">保存</a>
	        </div>
	</div>
</body>
</html>
