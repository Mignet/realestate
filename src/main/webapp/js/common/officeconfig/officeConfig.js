
	//主键值数组，记录复选框选中的行（包括翻页）主键值
	window.seq_id_set = [];
var nodeid;

var selectedNode;//选中的左侧菜单业务类型
$(function(){
   	var userDataGrid = $('#table_user').datagrid({
   		//分页功能
		loadFilter:pagerFilter,//该方法在enum-data.js中
		fit:true,
		//表格数据来源ctx+"/common/certificate!getProcName.action",
		url : ctx+"/common/certificate!getFormById.action?time="+new Date(),
		//表格每列宽度自动适应表格总宽度
		autoRowHeight:false,
		fitColumns: true,
		//去掉边框
		border : false,
		//是否有翻页栏
		pagination:true,
		//pagePosition:'top',
		//每页行数
		pageSize:20,
		//是否在最左增加一列显示行号的列
		rownumbers:true,
		//主键值所在行。在使用复选框时必须设置此项。
		idField: 'OFFICE_ID',
		//表格的行是否交替显示不同背景色					
		striped:true,
		checkbox:'CK',
		//只允许单选一行
		singleSelect:true,
		//是否在点选表中一行时同时选中复选框
		checkOnSelect:false,
		//是否在选中复选框时同时点选表中一行
		selectOnCheck:false,
		//列属性设置
		queryParams : {
			//ywlxid : '-1'
		},
		columns:[[
			//每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。			
			{ field:'CK',checkbox:true},
			{ title : 'ID', field : 'OFFICE_ID', width : 15 },
			{ title : '业务类型ID', field : 'BUS_TYPE_ID', width : 50 },
			{ title : '类别', field : 'OFFICE_TYPE', width : 50 },
			{ title : '名称', field : 'OFFICE_NAME', width : 50 },
			{ title : 'URL', field : 'OFFICE_URL', width : 300 }
		]],
		//将选中复选框的记录主键值记入数组seq_id_set
		onCheck: function(index,row){
			if(nodeid!=null&&nodeid!="")
			{
				$.ajax({
					url : ctx+"/common/certificate!insertNode.action?time="+new Date(),
					type : 'post',
					data : {"nodeid":nodeid,"bus_typeid":row.BUS_TYPE_ID,"officeid":row.OFFICE_ID},
					dataType : 'json',
					success : function(data) {
					
					}			
				});
			}
			//seq_id_set[seq_id_set.length] = row.OFFICE_ID;
		},
		//将取消选中复选框的记录主键值从数组seq_id_set删除
		onUncheck: function(index,row){
			if(nodeid!=null&&nodeid!="")
			{
				$.ajax({
					url : ctx+"/common/certificate!deleteNode.action?time="+new Date(),
					type : 'post',
					data : {"nodeid":nodeid,"bus_typeid":row.BUS_TYPE_ID,"officeid":row.OFFICE_ID},
					dataType : 'json',
					success : function(data) {
					
					}			
				});
			}
			//seq_id_set.splice(seq_id_set.indexOf(row.OFFICE_ID),1);
		},
		//当全选时将所有主键值加入数组seq_id_set
		onCheckAll: function(rows){
			for (var i=0;i<rows.length;i++){
				if(nodeid!=null&&nodeid!="")
				{
					$.ajax({
						url : ctx+"/common/certificate!insertNode.action?time="+new Date(),
						type : 'post',
						data : {"nodeid":nodeid,"bus_typeid":rows[i].BUS_TYPE_ID,"officeid":rows[i].OFFICE_ID},
						dataType : 'json',
						success : function(data) {
						
						}			
					});
				}
			}
		},
		//当取消全选时将所有主键值从数组seq_id_set中删除
		onUncheckAll: function(rows){
			for (var i=0;i<rows.length;i++){
				for (var i=0;i<rows.length;i++){
					if(nodeid!=null&&nodeid!="")
					{
						$.ajax({
							url : ctx+"/common/certificate!insertNode.action?time="+new Date(),
							type : 'post',
							data : {"nodeid":nodeid,"bus_typeid":rows[i].BUS_TYPE_ID,"officeid":rows[i].OFFICE_ID},
							dataType : 'json',
							success : function(data) {
							
							}			
						});
					}
				}
			}
		},
		//表头，添加工具栏。
		toolbar : [{
			id : 'user_add',
			text:'新增',
			iconCls:'icon-add',
			handler:doAdd			
		},'-',{
			id : 'user_edit',
			text:'编辑',
			iconCls:'icon-pencil',
			disabled : true,
			handler:doEdit
		}
		,'-',{
			id : 'user_delete',
			text:'删除',
			iconCls:'icon-remove',
			disabled : true,
			handler : doDelete
		}],
		onClickRow : function() {
			//点击列时激活“编辑”、“删除”按钮
			$('#user_edit').linkbutton('enable');
			$('#user_delete').linkbutton('enable');
		},
		onLoadSuccess : function(data) {
			var target = userDataGrid[0];
			var state = $.data(target, 'datagrid');
			var opts = state.options;
			var rows = state.data.rows;
			var dc = state.dc;
			var hck = dc.header1.add(dc.header2).find('input[type=checkbox]');
			var bck = opts.finder.getTr(target, '', 'allbody').find('div.datagrid-cell-check input[type=checkbox]');
			hck.add(bck)._propAttr('checked', false);
			state.checkedRows = [];
			state.selectedRows = [];
			bck.each(function(index,o){
				if(o.value){
					o.checked = true;
				}
			});
			//加载完毕禁用“编辑”、“删除”按钮
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
		}
	});
	//初始化产品树
	var productTree = $('#getProcName').tree({
		url : ctx+"/common/certificate!getProcName.action",
		onLoadSuccess : function() {
			//$('#getProcName').tree('select', $('#getProcName').tree('find', '-1').target);
		},
		onSelect : function(node) {
			$('#table_user').datagrid('load',{'nodeid':node.id,'bus_typeid':node.iconCls});
			nodeid=node.id;
			selectedNode = node;
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
		if(selectedNode == null){
			alert("请选择业务类型");
			return;
		}
		//alert("#djlx_id").val();
		openInTopWindow({
			//窗口元素的id
			id: 'add_user_win',
			//窗口iframe的src
			src:ctx+'/jsp/common/officeconfig/add-office-config.jsp',
			//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy: true,
			//窗口标题
			title: '新增表单及报表',
			//窗口宽
			width: 700,
			//窗口高
			height: 400,
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
				this.init(selectedNode);
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
			src: ctx+'/jsp/common/officeconfig/edit-office-config.jsp',
			//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy: true,
			//窗口标题
			title: '编辑表单及报表信息',
			//窗口宽
			width: 700,
			//窗口高
			height: 400,
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
				this.init(row);
			}
		});
	};
	
	//删除
	function doDelete(){
		var row = userDataGrid.datagrid('getSelected');
		top.$.messager.confirm('确认', '确定要删除表单名称为[' + row.OFFICE_NAME + ']？', function(result){
			if (result) {
				$.ajax({
					url : ctx+"/common/certificate!deleteForm.action?time="+new Date(),
					type : 'post',
					data : {"officeid":row.OFFICE_ID},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							top.$.messager.alert('提示', '删除成功！', 'info', function() {
								userDataGrid.datagrid('reload');
							});
						} else {
							top.$.messager.alert('提示', '删除错误！', 'error');
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
		url: 'appDelegate/getUserList.run',
		success: function(data){
			userDataGrid.datagrid('loadData',data);
		}
	});
	
});

function submit1(){
	$('#simpleform').submit();
}
