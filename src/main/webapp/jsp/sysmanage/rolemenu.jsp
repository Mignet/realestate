<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <title>深圳市不动产权登记系统</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <script type="text/javascript" src="${ctx}/js/sysmanage/rolemenu.js"></script>
</head>
<body class="plui-layout" style="width:100%;height:100%;">  
<div data-options="region:'center'" border="false">
	<div class="plui-layout" fit=true>
	    <div data-options="region:'center'" border="false">
	       <table id="table_roles" ></table>
	    </div>
	   <div data-options="region:'south'" border="false" style="height:300px;padding: 10px 10px 10px 10px;" align="center">
	       <form id="role_simple_form" method="post">
	              <input type="hidden" name="roleid" id="role_id"/>
				  <table style="width:100%;" class="edit_tab" title="角色编辑表格" border="0">
				      <tr>
				           <td class="title bg1">角色ID</td>      
				           <td class="td_1"><span id="roleid">**********</span></td>      
				           <td class="title bg1">角色名称</td>      
				           <td class="td_1"><input id="rolename" name="rolename" style="width:200px;" class="plui-validatebox"/></td>      
				      </tr>
				      <tr>
				           <td class="title bg2">创建人</td>      
				           <td class="td_1"><input id="operatorcode" name="operatorcode" style="width:200px;" class="plui-validatebox"/></td>      
				           <td class="title bg2">创建时间</td>      
				           <td class="td_1"><input id="createdatestr" name="createdatestr" class="plui-datetimebox plui-validatebox" /></td>      
				      </tr>
				      <tr>
				           <td class="title bg1">固定角色</td>      
				           <td class="td_1"><input class="plui-combobox plui-validatebox"  id="keepflag" name="keepflag" data-options="valueField: 'value',textField: 'label',data: [{label: '固定',value: '01'},{label: '可变',value: '00'}]"  style="width:200px;" /></td>      
				           <td class="title bg1">有效标志</td>      
				           <td class="td_1"><input class="plui-combobox plui-validatebox" id="effectflag" name="effectflag" data-options="valueField: 'value',textField: 'label',data: [{label: '有效',value: '01'},{label: '无效',value: '00'}]"  style="width:200px;" /></td>   
					  </tr>   
				      <tr>
				           <td class="title bg2">生效日期</td>      
				           <td class="td_1"><input id="begintimestr" name="begintimestr" class="plui-datetimebox plui-validatebox" /></td>      
				           <td class="title bg2">终止日期</td>      
				           <td class="td_1"><input id="endtimestr" name="endtimestr" class="plui-datetimebox plui-validatebox" /></td>      
				      </tr>
				      <tr>
				       <td class="title bg1" style="padding: 0;margin: 0">角色性质</td>      
					   <td colspan=3 class="td_1"><input class="plui-combobox plui-validatebox" id="attribute" name="attribute" data-options="valueField: 'value',textField: 'label',data: [{label: '正常',value: '01'},{label: '临时',value: '00'}]"  style="width:200px;"/></td>      
				      </tr>
				      <tr>
				           <td class="title bg2">备注</td>      
				           <td colspan=3 class="td_1">
				            <textarea rows="5" cols="50" id="remark" name='remark'></textarea>
                           </td>      
				      </tr>
				  </table>
				  <div style="width:100%;" align="center">
	                  	<span style="margin-left:7px;margin-right:7px;"><a class="plui-linkbutton" href="javascript:;" iconCls='icon-filesave' onclick="submit()">保存</a></span>  
				  </div>
			</form>
	   </div>
	</div>
</div>
<div data-options="region:'east'"
		style="width: 280px;"  border="false">
	<div class="plui-layout" fit=true>
		   <div data-options="region:'north'" border=false style="height:50px">
		       <div style="margin:10px 0;">  
			        <a href="#" class="plui-linkbutton" data-options="iconCls:'icon-remove'" onClick="collapseAll()">收缩</a>  
			        <a href="#" class="plui-linkbutton" data-options="iconCls:'icon-add'" onClick="expandAll()">展开</a>  
			        <a href="#" class="plui-linkbutton" data-options="iconCls:'icon-save'" onClick="saveRM()">授 权</a>  
		       </div> 
		   </div>
		   <div data-options="region:'center'" border="false">
		    <ul id="tt" class="plui-tree" data-options="checkbox:true"></ul>  
		   </div>
		   <div data-options="region:'south'" border=false style="height:50px">
		       <div style="margin:10px 0;">  
			        <a href="#" class="plui-linkbutton" data-options="iconCls:'icon-remove'" onClick="collapseAll()">收缩</a>  
			        <a href="#" class="plui-linkbutton" data-options="iconCls:'icon-add'" onClick="expandAll()">展开</a>  
			        <a href="#" class="plui-linkbutton" data-options="iconCls:'icon-save'" onClick="saveRM()">授 权</a>  
		       </div> 
		   </div>
	</div>
</div>
</body>
</html>
