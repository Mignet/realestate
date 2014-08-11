	var userDataGrid;	
//var row;
//初始化加载.		
$(function(){	
	 userDataGrid = $('#table_user').datagrid({
		//分页功能
		loadFilter:pagerFilter,//该方法在enum-data.js中
		//fit:true,
		height:470,
		//表格数据来源
		url:'workflow!getfinishworkflowList.action',
		//表格每列宽度自动适应表格总宽度
		fitColumns: true,
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
		//列属性设置
		columns:[[
			//每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
			{field:'busno', title:'业务编号',align :'center', width:60, sortable:true},
			{field:'procName', title:'业务描述',align :'left', width:120},	
			{field:'totallimit',	title:'总期限',align :'center', width:30},
			{field:'receive',	title:'是否签收',align :'center', width:30},
			{field:'committime',	title:'提交时间',align :'center', width:70},
			{field:'nextnode',	title:'下一环节',align :'center', width:60},
			{field:'person',	title:'下一环节接收人',align :'center', width:70},
			{field:'button',align :'center',formatter:function(value,rec){return '<span><input  type="button" value="查看" onclick=""/></span>';}}
		]],
		onClickRow : function() {
			//点击列时激活“编辑”、“删除”按钮
			//alert("onClickRow  event");
			//btn_test_flow()
			$('#user_edit').linkbutton('enable');
			$('#user_delete').linkbutton('enable');
		},
		onClickCell:function(rowIndex, field, value){
			//alert("onclickcell event"+rowIndex+field+value);
			if(field=="button"){
				$('#table_user').datagrid('selectRow',rowIndex);
				//btn_bl(this);
			}
		},
		onLoadSuccess : function() {
			//加载完毕禁用“编辑”、“删除”按钮
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
		}
	});
	
	//选择表格中某一行的数据。
	function getSelected(func){
		var selectedrow = $('#table_user').datagrid('getSelected');
		
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
			id: 'add_user_win',
			//窗口iframe的src
			src: '../user/add.jsp',
			//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy: true,
			//窗口标题
			title: '新增用户',
			//窗口宽
			width: 700,
			//窗口高
			height: 550,
			modal: true,
			//窗口中iframe的window对象的onLoad回调函数设置
			onLoad:	function(){
				//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
				//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
				this.openerWindow = window;
				//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
				this.args = {
					userDataGrid: userDataGrid
				};
				this.init();
			}
		});
	};

	//编辑
	function doEdit() {
		var row = userDataGrid.datagrid('getSelected');
		
		openInTopWindow({
			//窗口元素的id
			id: 'edit_user_win',
			//窗口iframe的src
			src: '../user/editMain.jsp',
			//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy: true,
			//窗口标题
			title: '编辑用户相关信息' + row.user_name,
			//窗口宽
			width: 800,
			//窗口高
			height: 600,
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
				this.init();
			}
		});
	};
	
	//删除
	function doDelete(){
		var row = userDataGrid.datagrid('getSelected');
		top.$.messager.confirm('确认', '确定要删除用户[' + row.user_name + ']？', function(result){
			if (result) {
				$.ajax({
					url : 'workflowDelegate/deleteUser.run',
					type : 'post',
					data : {
						user_id : row.user_id
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							top.$.messager.alert('提示', '用户删除成功！', 'info', function() {
								userDataGrid.datagrid('reload');
							});
						} else {
							top.$.messager.alert('提示', '角色删除错误！', 'error');
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
		url: 'workflowDelegate/test.run',
		success: function(data){
			
			userDataGrid.datagrid('loadData',data);
		}
	});
	
	
	
});

function submit1(){
	$('#simpleform').submit();
}

function btn_bl(button){	
		//alert("办理按扭事件");
		var selectedrow = $('#table_user').datagrid('getSelected');
		//alert(selectedrow);
		//var selectedrow = $('#table_user').datagrid('getSelections');
		//alert(JSON.stringify(selectedrow)+'-----');
		//打开窗口  obj对象
		var obj={	
			id:"open_app",
			//窗口宽
			width: 1200,
			//窗口高
			title:'办公页面',
			height: 680,
			modal: true,	
			destroy:true,
			onLoad:	function(){
				//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
				//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
				this.openerWindow = window;
				//this.document.getElementById('name').value='名称';
				//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
				this.args = {
					user: selectedrow,
					userDataGrid: userDataGrid
				};			
				this.init(selectedrow);
			}
		};				
		
				//alert(selectedrow[0].wiId+"++++");
				var objs={
					
					url:"workflow!test.action",
					type:'post',
					data:{'wiId':selectedrow.wiId},
					success:function(data){
						if(!data){
							alert("该地址不存在！"+data);							
						}
						else if(data=='null'||data==null||data==''||data==undefined )
						{
							alert("地址为null");
						}
						else
						{													
							//window.showModalDialog(data+'?wiId='+selectedrow[0].wiId,'NEW','dialogHeight: 600px; dialogWidth: 1200px; edge: Raised; center: Yes; help: No; resizable: no; status: no;');
							//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
							//alert(data);
							obj.src=data+'?wiId='+selectedrow.wiId;
								openInTopWindow(obj);		
						}
						//this.init(selectedrow);
					}
					
					
				};
				
				$.ajax(objs);
			
		
}
