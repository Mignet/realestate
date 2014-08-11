		
//var row;
//初始化加载.		
/*$(function(){	
	var userDataGrid = $('#table_user').datagrid({
		fit:true,
		//表格数据来源
		url:'workflow!queryProcessdef.action',
		//表格每列宽度自动适应表格总宽度
		fitColumns: true,
		//去掉边框
		border : false,
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
		remoteSort:false,
		//是否在点选表中一行时同时选中复选框
		//checkOnSelect:true,
		//是否在选中复选框时同时点选表中一行
		//selectOnCheck:true,
		//列属性设置
		columns:[[
			//每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
			{field:'procdefId', title:'ID',align :'center', width:30, sortable:true,hidden:true},
			{field:'name', title:'业务名称',align :'left', width:80, sortable:true},	
			{field:'createDate',	title:'创建日期', align :'center',width:50},
			{field:'lastEditDate',	title:'修改日期',align :'center', width:50},
			
		]],
		//表头，添加工具栏。
		toolbar : ['->','-',{
			id : 'user_edit',
			text:'受理',
			iconCls:'icon-pencil',
			disabled : true,
			handler:doEdit
		}
		
		],
		onClickRow : function() {
			//点击列时激活“编辑”、“删除”按钮
			$('#user_edit').linkbutton('enable');
			//$('#user_delete').linkbutton('enable');
		},
		onLoadSuccess : function() {
			//加载完毕禁用“编辑”、“删除”按钮
			$('#user_edit').linkbutton('disable');
			//$('#user_delete').linkbutton('disable');
		}
	});*/


/*$(function(){
	$.ajax({
		url : ctx+'/common/configur!getBusProcList.action?time='+new Date(),
		type : 'post',
		data : {
			applicant_id : row.applicant_id
		},
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				top.$.messager.alert('提示', data.tipMessage, 'info',
						function() {
					        //alert("删除之后刷新");
							userDataGrid.datagrid('reload');
						});
			} else {
				top.$.messager.alert('提示', data.errorMessage, 'error');
			}
		}
	});
	
});*/

	 var userDataGrid;   
	
	$(function(){
		userDataGrid =$('#table_user').treegrid({
			//数据请求地址
			url : ctx+'/common/configur!getBusProcList.action?time='+new Date(),
			//标识字段
			idField : 'id',
			fit : true,
			border : false,
			//是否在最左增加一列显示行号的列
			rownumbers:true,
			fitColumns : true,
			pagination:true,
			//pageSize:20,
			treeField : 'name',
			columns :[[ 
				//每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
				{field:'name', title:'业务名称',align :'left', width:70,},	
				{field:'procdefid', title:'流程定义ID',align :'center', width:30,},
				{field:'create_date',	title:'创建日期', align :'center',width:30},
				{field:'change_date',	title:'修改日期',align :'center', width:30},
			]],
			//表头，添加工具栏。
			toolbar :'#tb'
				
//				[
//			     {
//				id : 'user_edit',
//				text:'受理',
//				iconCls:'icon-pencil',
//				disabled : true,
//				handler:doEdit
//			}
//			]
				,
			onClickRow : function(rowIndex, rowData) {
				//点击列时激活“编辑”、“删除”按钮
				if(rowIndex.procdefid==null)
				{
					$('#user_edit').linkbutton('disable');
				}
				else
				{
					$('#user_edit').linkbutton('enable');
				}
				//userDataGrid.datagrid('getSelected');
				//$('#user_delete').linkbutton('enable');
			},
			onLoadSuccess : function(rowIndex, rowData) {
				//加载完毕禁用“编辑”、“删除”按钮
				$('#user_edit').linkbutton('disable');
			//	$('#user_edit').css('float','right');
				//$('#user_edit').css('text-align','right');
				//$('#user_delete').linkbutton('disable');
			}
		});
	
	
	
	//选择表格中某一行的数据。
	function getSelected(func){
		var selectedrow = userDataGrid.datagrid('getSelected');
		
		if (selectedrow){
			row = selectedrow;
			//调用相关函数
			func.call(this,selectedrow);
		}
		else{
			
			$.messager.alert('提示：','请点击选中表格中的某一行.');
		}
	};

	

	
	


	//双击表格中某一行的触发的事件
	function rowDblclick(rowIndex,row){
		var i = 0;
		var props = [];
		
		for(var p in row){
			props[i++]= p+' = '+row[p];
			
		}
		//alert(props.join(',\n'));
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
		//url: 'userDelegate/getUserList.run',
		success: function(data){
			userDataGrid.datagrid('loadData',data);
		}
	});
	
});

	//受理
	function doEdit() {
		var row = $('#table_user').datagrid('getSelected');
		var user;
		//alert(row);
		openInTopWindow({
			title:'受理前审核',
			//窗口元素的id
			id: 'edit_user_win',
			//窗口iframe的src
			src: '../jsp/common/preaudit.jsp',
			//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy: true,
			//窗口宽
			width: 900,
			//窗口高
			height: 650,
			modal: true,
			//窗口中iframe的window对象的onLoad回调函数设置
			onLoad:	function(){
				//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
				//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
				this.openerWindow = window;
				//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
				this.args = {
					user : row,
					userDataGrid : userDataGrid
				};
				//alert(JSON.stringify(row));
				this.init(row);
				//this.document.getElementById('name').value='名称';
			}
		});
	};
	
function submit1(){
	$('#simpleform').submit();
}
