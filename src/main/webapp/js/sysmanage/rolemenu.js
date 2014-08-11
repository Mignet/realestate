/*********************************************************************************
*文  件  名  称: rolemenu.js
*功  能  描  述: 角色菜单授权
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Mignet
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
var saveoptions;
/**********************************************************************************
*函数名称: JQuery DOM Ready(Shortcut)
*功能说明: 页面初始化
*函数作者: Mignet
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
$(function(){
	var roleDataGrid = $('#table_roles').datagrid({
		title:'角色信息',
		//fit:true,
		//表格数据来源
		url:'role-manage!getAllRoles.action',
		//表格每列宽度自动适应表格总宽度
		height:300,
		//fitColumns: true,
		//去掉边框
		border : true,
		//是否有翻页栏
		pagination:true,
		//每页行数
		pageSize:20,
		//是否在最左增加一列显示行号的列
		rownumbers:true,
		//主键值所在行。在使用复选框时必须设置此项。
		//idField: 'user_id',
		//表格的行是否交替显示不同背景色					
		striped:true,
		//只允许单选一行
		singleSelect:true,
		//是否在点选表中一行时同时选中复选框
		//checkOnSelect:true,
		//是否在选中复选框时同时点选表中一行
		//selectOnCheck:true,
		 remoteSort: false,
		 idField:'roleid',  
         pagination:true,  
         rownumbers:true,  
         sortName:'createdatestr',
         sortOrder:'desc',
		//列属性设置
		columns:[[
			{field:'roleid', title:'角色ID', width:140, sortable:true},
			{field:'rolename', title:'角色名称', width:100, sortable:true},
			//{field:'parentid', title:'父级角色', width:100, sortable:true},
			{field:'operatorcode', title:'创建人', width:100, sortable:true},
			{field:'createdatestr', title:'创建时间', width:120, sortable:true},
			{field:'attribute', title:'角色性质', width:100,formatter:Status, sortable:true},
			{field:'begintimestr', title:'生效日期', width:120, sortable:true},
			{field:'endtimestr', title:'终止日期', width:120, sortable:true},
			{field:'keepflag', title:'固定角色', width:100,formatter:Keep, sortable:true},
			{field:'effectflag', title:'有效标志', width:100,formatter:YesOrNo,sortable:true},
			{field:'remark', title:'备注', width:100, sortable:false}
		]],
		toolbar:[{
					id : 'role_add',
					text : '添加',
					iconCls : 'icon-edit_add',
					disabled : false,
					handler : doRoleAdd
				},{
					id : 'role_del',
					text : '删除',
					iconCls : 'icon-edit_remove',
					disabled : false,
					handler : doRoleDel
				}
		],
	    onClickRow : function() {
	    	doRoleEditAndTreeSet();
	    },onLoadSuccess : function(data) {
	    	if(data){
	    	    $('#table_roles').datagrid('selectRow',0);
	    	    doRoleEditAndTreeSet();
	    	}
 		}
	});
	
	$('#role_simple_form').form({
		url:"role-manage!updateandAddRole.action?time="+new Date(),
		success:function(data){
			data = $.parseJSON(data);
			if (data.success) {
				top.$.messager.alert('提示',data.tipMessage,'info',function(){
					$('#table_roles').datagrid('reload');
				});	
			}
		}
	});
	function Status(value){
		return value == '01' ? '正常' : '临时';
	}
	function Keep(value){
		return value == '01' ? '固定' : '可变';
	}
	function YesOrNo(value){
		return value == '01' ? '有效' : '无效';
	}
	//菜单树
    $('#tt').tree({    
    	url:'role-manage!getAllMenus.action'
    });   
});//初始化结束

/**********************************************************************************
*函数名称: collapseAll
*功能说明: 树全部收缩
*参数说明: 无
*返 回 值: 无
*函数作者: Mignet
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function collapseAll(){  
    $('#tt').tree('collapseAll');  
}
/**********************************************************************************
*函数名称: expandAll
*功能说明: 树全部展开
*参数说明: 无
*返 回 值: 无
*函数作者: Mignet
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function expandAll(){  
    $('#tt').tree('expandAll');  
}
/**********************************************************************************
*函数名称: saveRM
*功能说明: 保存授权关系
*参数说明: 无
*返 回 值: 成功or失败
*函数作者: Mignet
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function saveRM(){  
	var selected = $('#table_roles').datagrid('getSelected');  
    if (selected){  
	    var nodes = $('#tt').tree('getChecked');  
	    var s = '';  
	    for(var i=0; i<nodes.length; i++){  
	        if (s != '') s += ',';  
	        s += nodes[i].id;  
	    }  
		$.ajax({
			url : 'role-manage!saveRM.action',
			dataType : 'json',
			type : 'post',
			data : {
				"roleid":selected.roleid,
				"menuids":s
			},
			success : function(data) {
				if(data.success){
			 		top.$.messager.alert('提示',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('提示',data.errorMessage,'error');
				}
			}
		});
    }else{
    	alert("请先选择需要授权的角色！");
    }
} 
/**********************************************************************************
*函数名称: doRoleAdd
*功能说明: 角色添加
*参数说明: 无
*返 回 值: 成功or失败
*函数作者: Sam
*创建日期: 2014-08-05
*修改历史: 
***********************************************************************************/
function doRoleAdd(){
	 $('#roleid').text('**********');
	 $('#role_id').val('');
	 $('#role_simple_form')[0].reset();
	 saveoptions = 'add';
	 $('#createdatestr').datetimebox('setValue',getCurTime());
	 $('#operatorcode').val('999999999');
	 $('#keepflag').combobox('setValue','00');
	 $('#effectflag').combobox('setValue','01');
	 $('#attribute').combobox('setValue','00');
	 
};

/**********************************************************************************
 *函数名称: doRoleDel
 *功能说明: 角色删除
 *参数说明: 无
 *返 回 值: 成功or失败
 *函数作者: Sam
 *创建日期: 2014-08-05
 *修改历史: 
 ***********************************************************************************/
function doRoleDel(){
	var roleid;
	var row = $('#table_roles').datagrid('getSelected');  
	if (row){
		var obj = {
				url:"role-manage!deleteRole.action?time="+new Date(),
				data:{"roleid":row.roleid},
				success:function(data){
					data = $.parseJSON(data);
					if (data.success) {
						top.$.messager.alert('提示',data.tipMessage,'info',function(){
							$('#table_roles').datagrid('reload');
						});	
					}
				}
		};
		top.$.messager.confirm('提示','确定要删除这一行吗？',function(r){
			if(r){
				var index = $('#table_roles').datagrid('getRowIndex', row);  
				$('#table_roles').datagrid('deleteRow', index);    
				$.ajax(obj);
			}
		});
	}else{   
		top.$.messager.confirm('提示','请选择要删除的行！！');             	
	}  
};

/**********************************************************************************
*函数名称: doRoleEditAndTreeSet
*功能说明: 角色编辑和树菜单设置
*参数说明: 无
*返 回 值: 成功or失败
*函数作者: Sam
*创建日期: 2014-08-05
*修改历史: 
***********************************************************************************/
function doRoleEditAndTreeSet(){
	var selected = $('#table_roles').datagrid('getSelected');  
    if (selected){  
      //菜单树
        $('#tt').tree({    
        	url:'role-manage!getCheckedMenus.action?roleid='+selected.roleid
        })
    }
    $('#role_simple_form').form('load',selected);
    $('#roleid').text($('#role_id').val());
    saveoptions = 'update';
};
/**********************************************************************************
*函数名称: submit
*功能说明: 表单提交
*参数说明: 无
*返 回 值: 成功or失败
*函数作者: Sam
*创建日期: 2014-08-05
*修改历史: 
***********************************************************************************/
function submit(){
	if(saveoptions){
		if(saveoptions == 'update'){
			top.$.messager.confirm('提示',"您确定要更新“"+$('#rolename').val()+"”吗？",function(r){
				if(r){
					$('#role_simple_form').submit();
				}
			});
		}
		else if(saveoptions == 'add'){
			
			top.$.messager.confirm('提示',"您确定要增加“"+$('#rolename').val()+"”吗？",function(r){
				if(r){
					$('#role_simple_form').submit();
				}
			});
		}
	}
};
