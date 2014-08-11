<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>新增建筑角色关联</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
        
        <jsp:include page="/plui2/head.jsp" />
        
<script>
/**  */
$(function() {
	
	//初始化角色数据表
	$('#roleDataGrid').datagrid({
		title : '角色列表',
		fit : true,
		singleSelect : false,
		fitColumns : true,
		url : 'roleDelegate/getRoleByProductId.run',
		pagination:true,
		//每页行数
		pageSize:50,
		queryParams : {
			product_id : '-1'
		},
		columns : [[
			{field:'ck',checkbox:true},
			{ title : '角色ID', field : 'role_id', width : 50 },
			{ title : '角色名称', field : 'role_name', width : 100 },
			{ title : '角色代码', field : 'role_code', width : 100 },
			{ title : '所属产品', field : 'product_name', width : 100 }
			
		]],
		toolbar : ['->',{
			xtype : 'searchbox',
			prompt : '输入角色名称或代码',
			searcher : function(value, name) {
				var queryParams = {
					searchStr : value
				};
				if (name === 'sub') {
					$.extend(queryParams, {
						product_id : $('#productTree').tree('getSelected').id
					});
				}
				dictDataGrid.datagrid({
					url : 'dictDelegate/searchDictClass.run',
					queryParams : queryParams
				});
			},
			menu : $('<div><div name="all">所有数据</div><div name="sub">节点数据</div></div>').appendTo($(document.body))
		}],
		onLoadSuccess : function() {
			$('#role_update').linkbutton('disable');
			$('#role_delete').linkbutton('disable');
		},
		onClickRow : function(rowIndex, rowData) {
			$('#role_update').linkbutton('enable');
			$('#role_delete').linkbutton('enable');
		},
		onCheck: function(rowIndex,rowData){
			//$("#selected_roles").
			var opt = document.createElement("OPTION");
			opt.value = rowData.role_id;
			opt.text = rowData.role_name+'（'+rowData.product_name+'）';
			document.getElementById("selected_roles").options.add(opt);
			//document.getElementById("selected_roles").options.add(new Option("text","value"));
		},
		onUncheck:function(rowIndex,rowData){
			var opt = document.createElement("OPTION");
			opt.value = rowData.role_id;
			opt.text = rowData.role_name+'（'+rowData.product_name+'）';
			document.getElementById("selected_roles").options.remove(opt);
		}
	});
	
	//初始化产品树
	$('#productTree').tree({
		border:false,
		url : 'productDelegate/getProductTreeJson.run',
		onLoadSuccess : function() {
			$('#productTree').tree('select', $('#productTree').tree('find', '-1').target);
		},
		onSelect : function(node) {
			$('#roleDataGrid').datagrid({
				url : 'roleDelegate/getRoleByProductId.run',
				queryParams : {
					product_id : node.id,
				}
			});
		}
	});

	/**
	$('#save_urr_form').form({
		url: '../userRoleRelDelegate/saveUserRoleRelFromUser.run',
		dataType: 'json',
		success: function(result){
			alert(result);
			var data = $.parseJSON(result); 
			
			//window.parentWindow.$('#table_user').datagrid('reload');
		}
	});
	*/
});

//保存建筑角色关联
function saveUserRoleRels(){
	var opts =document.getElementById("selected_roles").options;
	//提示
	if(opts.length<1){
		alert("请选中角色");
		return ;
	}
	//分析出角色ID
	var role_ids = "";
	for(var i=0;i<opts.length;i++){
		role_ids +=","+opts[i].value;
	}
	role_ids = role_ids.substring(1);
	
	//alert(role_ids);
	//组成参数
	var params = {"user_id":this.user_id,"role_ids":role_ids,"remark":$("#remark").attr("value")};
	//提交后台
	$.ajax({
		type:"POST",
		url:"../userRoleRelDelegate/saveUserRoleRelFromUser.run",
		data:params,
		success:function(data){
			//alert("新增角色关联成果");
			
			close();
		}
	});
	
}

//关闭窗口
function close(){
	
	closeInTopWindow('plat_addRelRole');
}

</script>
    </head>
<body class="plui-layout" >
	<div data-options="region:'west',split:true,title:'产品列表'" style="width:300px;">
		<ul id="productTree" />
	</div>
	<div data-options="region:'center'" border=false>
		<table id="roleDataGrid"></table>
	</div>

	<div data-options="region:'south'" border=false style="height:150px">
<table border="0">
<tr>
<td>
 已选角色：
		<select id="selected_roles" name="selected_roles" multiple="multiple" size="8" style="width:300px"></select>
</td>
<td>
 备注：<textarea rows="8" cols="30" id="remark" name="remark"></textarea>

</td>
<td>
	<a id="saveBtn" href="javascript:saveUserRoleRels();" class="plui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
<a id="closeBtn" href="javascript:close();" class="plui-linkbutton" data-options="iconCls:'icon-cansle'">关闭</a>
</td>
</tr>
</table>
	

	
	</div>
</body>

</html>
