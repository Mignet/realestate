var userroleLst;
$.fn.datebox.defaults.formatter = function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	if(y < 100){
		y = 1900 + y;
	}
	if(m < 10){
		m = "0" + m;
	}
	if(d < 10){
		d = "0" + d;
	}
	return y+'-'+m+'-'+d;
}
$(function(){
	userroleLst = $('#userroleLst').datagrid({
		//分页功能
		loadFilter:pagerFilter,//该方法在enum-data.js中
	    title:'用户信息',
		//数据请求地址
		url : ctx+'/sysmanage/user-role-manage!searchUserListByOptions.action?time=' + new Date(),
		fit:true,
		//起始页
		//pageNumber:1,
		//页码集合
		//pageList:[8,16,24,32,40],
		//每页行数
		pageSize:8,
		//表格每列宽度自动适应表格总宽度
		fitColumns: true,
		//去掉边框
		border : false,
		//是否有翻页栏
		pagination:true,
		//是否在最左增加一列显示行号的列
		rownumbers:true,
		//主键值所在行。在使用复选框时必须设置此项。
		idField: 'user_id',
		//表格的行是否交替显示不同背景色					
		striped:true,
		//只允许单选一行
		singleSelect:true,
		//是否在点选表中一行时同时选中复选框
		//checkOnSelect:true,
		//是否在选中复选框时同时点选表中一行
		//selectOnCheck:true,
		//列属性设置
		height:390,
		sortName:'CREATEDATE',
		sortOrder:'desc',
		columns :[[ 
			{ title : '用户ID', field : 'USER_ID', width : 80 },
			{ title : '用户名', field : 'USER_NAME', width : 80 },
			{ title : '创建人', field : 'CREATOR', width : 80 },
			{ title : '创建时间', field : 'CREATEDATE', width : 100 ,sortable:true},
			{ title : '角色', field : 'ROLENAME', width : 100 },
			{ hidden:'true',field : 'ROLEID', width : 100 }
		]],
		toolbar : [{
			id : 'userrole_edit',
			text : '编辑',
			disabled : true,
			iconCls : 'icon-edit',
			handler : editUserRole
		}],
		onClickRow : function() {
			//单击行时激活“编辑”
			$('#userrole_edit').linkbutton('enable');
		}
	});
	
/*	$('#simpleform').form({
		url: ctx + '/sysmanage/user-role-manage!searchUserListByOptions.action?time=' + new Date(),
		success:function(data){
			data = $.parseJSON(data);
			if (data.success) {
				top.$.messager.alert('提示',data.tipMessage,'info',function(){
					$('#userroleLst').datagrid('reload');
				});	
			}
		}
 	});*/
	/**
	 * 编辑用户
	 */
	function editUserRole() {
		//获取选中行
		var selectedNode = $('#userroleLst').datagrid('getSelected');
		//打开顶层窗口
		openInTopWindow({
			id : 'edit_userrole_win',
			src : ctx+'/jsp/sysmanage/edituserrole.jsp',
			destroy : true,
			title : '编辑用户:' + selectedNode.USER_NAME,
			width : 280,
			height : 400,
			modal: true,
			onLoad: function(){
				//给子窗口相关对象赋值
				this.openerWindow = window;
				var _this = this;
				//加载获取到的数据
				_this.$('#userrole_edit_form').form('load', selectedNode);
				this.init(selectedNode);
			}
		}); 
	};
});

function submit1(){
	    $('#userroleLst').datagrid('loadData',{total:0,rows:[]});
	    $('#userroleLst').datagrid('load',$('#simpleform').serializeJson());
}
function clear1(){
    $('#simpleform')[0].reset();
}
