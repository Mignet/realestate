/*********************************************************************************
*文  件  名  称: project-main.js
*功  能  描  述: 建筑物项目管理
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Sam
*创  建  日  期： 2014-08-07
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
/**********************************************************************************
*函数名称: JQuery DOM Ready(Shortcut)
*功能说明: 页面初始化
*函数作者: Sam
*创建日期: 2014-08-07
*修改历史: 
***********************************************************************************/
//var row;
//初始化加载.		
$(function(){
	$('#searchpanel').searchpanel({
		//此参数是查询栏面板的css样式设置
		containerStyle: {
			margin: '5px auto 5px auto',
			width: $('body').width()-2
		},
		//此参数为css选择器，选择初始的未伸展查询栏。
		searchbox: '#simpleform',
		/******************* 下列参数为panel组件参数 *******************/
		fit:true,
		doSize: true,
		title: '查询栏',
		border: true,
		collapsible: true,
		/******************* 下列参数为tableform组件参数 *******************/
		errorCode: 700,
		//表单元素表格的列数（通常设为两列）
		colnum: 3,
		//表单控件标题列宽
		titleWidth: 100,
		//表单控件输入框列宽
		cellWidth: 200,
		//表单控件input、select、combo等输入框宽
		inputWidth:	150,
		//textarea控件输入框宽
		textareaWidth: 500,
		//textarea控件输入框高
		textareaHeight: 90,
		//是否设置fieldset标签
		fieldset:false	,
		//表单元素参数数组。数组中每个对象构成一个表单元素
		inputs: [{
			tag: 'input',
			title: '项目编号',
			name: 'project_no',
			id: 'project_no'
		},{
			tag: 'input',
			title: '项目名称',
			name: 'project_name',
			id: 'project_name'
		},{
			tag: 'input',
			title: '坐落',
			name: 'location',
			id: 'location'
		},{
			tag: 'input',
			name:'queryCondition',
			hidden:true,
			id : 'queryCondition',
			attributes: {
				value: 'project_no like ? and project_name like ? and location = ?'
			}
		}],
		url: 'project!getProjectList.action',
		dataType: 'json',
		success: function(data){
			projectDataGrid.datagrid('loadData',data);							
		},
		onExpand : function() {
			var northpanel = $(document.body).layout('panel', 'north');
			northpanel.panel('resize', {height : 200});
			$(document.body).layout('resize');
		},
		onCollapse : function() {
			var northpanel = $(document.body).layout('panel', 'north');
			northpanel.panel('resize', {height : 40});
			$(document.body).layout('resize');
		}
	});
	
	var projectDataGrid = $('#table_project').datagrid({
		fit:true,
		//表格数据来源
		url:'../project!getProjectList.action',
		//表格每列宽度自动适应表格总宽度
		fitColumns: true,
		//去掉边框
		border : false,
		//是否有翻页栏
		pagination:true,
		//每页行数
		pageSize:20,
		emptyMsg : '查无记录。',
		//是否在最左增加一列显示行号的列
		rownumbers:true,
		//主键值所在行。在使用复选框时必须设置此项。
		//idField: 'project_id',
		//表格的行是否交替显示不同背景色					
		striped:true,
		//只允许单选一行
		singleSelect:true,
		//是否在点选表中一行时同时选中复选框
		//checkOnSelect:true,
		//是否在选中复选框时同时点选表中一行
		//selectOnCheck:true,
		//列属性设置
		columns:[[
			//每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
			{field:'prj_id', title:'建设项目记录流水号 ', width:70, sortable:true},
			{field:'project_no', title:'项目编码 ', width:70, sortable:true},
			{field:'project_name', title:'项目名称 ', width:70, sortable:true},
			{field:'conscertificatenu', title:'建筑物许可证号 ', width:70, sortable:true},
			{field:'contract_no', title:'土地使用合同  ', width:70, sortable:true},
			{field:'project_dis', title:'项目描叙 ', width:70, sortable:true},
			{field:'building_num', title:'建筑物数量 ', width:70, sortable:true},
			{field:'location', title:'地址 ', width:70, sortable:true},
			{field:'glebe_area', title:'用地面积（规划） ', width:70, sortable:true},
			{field:'floor_area', title:'总建筑面积（规划） ', width:70, sortable:true},
			{field:'build_bestrow_per', title:'建筑物覆盖率（规划） ', width:70, sortable:true},
			{field:'build_unit', title:'建设单位 ', width:70, sortable:true},
		]],
		//表头，添加工具栏。
		toolbar : [{
			id : 'project_add',
			text:'新增',
			iconCls:'icon-add',
			handler:doAdd			
		},'-',{
			id : 'project_edit',
			text:'编辑',
			iconCls:'icon-pencil',
			disabled : true,
			handler:doEdit
		}
		,'-',{
			id : 'project_delete',
			text:'删除',
			iconCls:'icon-remove',
			disabled : true,
			handler : doDelete
		}/*,'->',{
			xtype : 'searchbox',
			prompt : '输入项目编号、项目名称、坐落检索',
			width : 200,
			searcher : function(value) {
				projectDataGrid.datagrid('load', {
					searchStr : value
				});
			}
		}*/],
		onClickRow : function() {
			//点击列时激活“编辑”、“删除”按钮
			$('#project_edit').linkbutton('enable');
			$('#project_delete').linkbutton('enable');
		},
		onLoadSuccess : function() {
			//加载完毕禁用“编辑”、“删除”按钮
			$('#project_edit').linkbutton('disable');
			$('#project_delete').linkbutton('disable');
		}
	});
	
	//选择表格中某一行的数据。
	function getSelected(func){
		var selectedrow = $('#table_project').datagrid('getSelected');
		
		if (selectedrow){
			row = selectedrow;
			//调用相关函数
			func.call(this,selectedrow);
		}
		else{
			
			$.messager.alert('提示：','请点击选中表格中的某一行.');
		}
	};

	//新增
	function doAdd() {		
		openInTopWindow({
			//窗口元素的id
			id: 'add_project_win',
			//窗口iframe的src
			src: ctx+'/jsp/basedata/project-add.jsp',
			//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy: true,
			//窗口标题
			title: '新增建设项目',
			//窗口宽
			width: 800,
			//窗口高
			height: 420,
			modal: true,
			//窗口中iframe的window对象的onLoad回调函数设置
			onLoad:	function(){
				//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
				//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
				this.openerWindow = window;
				//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
				this.args = {
					projectDataGrid: projectDataGrid
				};
				this.init();
			}
		});
	};

	//编辑
	function doEdit() {
		var row = projectDataGrid.datagrid('getSelected');
		
		openInTopWindow({
			//窗口元素的id
			id: 'edit_project_win',
			//窗口iframe的src
			src: ctx+'/jsp/basedata/project-editMain.jsp',
			//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy: true,
			//窗口标题
			title: '编辑建筑相关信息' + row.project_name,
			//窗口宽
			width: 800,
			//窗口高
			height: 420,
			modal: true,
			//窗口中iframe的window对象的onLoad回调函数设置
			onLoad:	function(){
				//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
				//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
				this.openerWindow = window;
				//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
				this.args = {
					project : row,
					projectDataGrid : projectDataGrid
				};
				this.init();
			}
		});
	};
	
	//删除
	function doDelete(){
		var row = projectDataGrid.datagrid('getSelected');
		top.$.messager.confirm('确认', '确定要删除建设项目[' + row.project_name + ']？', function(result){
			if (result) {
				$.ajax({
					url : 'project!deleteProject.action',
					type : 'post',
					data : {
						'prj_id' : row.prj_id
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							top.$.messager.alert('提示', '建设项目删除成功！', 'info', function() {
								projectDataGrid.datagrid('reload');
							});
						} else {
							top.$.messager.alert('提示', '建设项目删除错误！', 'error');
						}
					}
				});
			}
		});
	};

	//双击表格中某一行的触发的事件
	function rowDblclick(rowIndex,row){
		var i = 0;
		var props = [];
		
		for(var p in row){
			props[i++]= p+' = '+row[p];
			
		}
		alert(props.join(',\n'));
		//info(row);
	};

	//定义流程实例查询
	function searchProcint(){
		//$('#searchform').form()
		//$('#dg_proctask').datagrid('load',{});

		var fields = $("#procinstSearchform").serializeArray();
		var o = {};
		jQuery.each( fields, function(i, field){
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
				
			}
		});
		//console.debug(o);
		$('#dg_procinst').datagrid('load',o);
		//console.info("form : "+ o.procName );
	};	
	
	$('#simpleform').form({
		dataType: 'json',
		url: 'project!getProjectList.action',
		success: function(data){
			projectDataGrid.datagrid('loadData',data);
		}
	});
	
});

function submit1(){
	$('#simpleform').submit();
};
