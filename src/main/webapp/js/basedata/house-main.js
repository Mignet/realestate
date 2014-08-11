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
			title: '房屋编号',
			name: 'ho_id',
			id: 'ho_id'
		},{
			tag: 'input',
			title: '自然房号',
			name: 'house_no_num',
			id: 'house_no_num'
		},{
			tag: 'input',
			title: '座号',
			name: 'seat_no',
			id: 'seat_no'
		},{
			tag: 'input',
			name:'queryCondition',
			hidden:true,
			id : 'queryCondition',
			attributes: {
				value: 'ho_id like ? and house_no_num like ?  and seat_no like ?'
			}
		}],
		url: 'house!getHouseList.action',
		dataType: 'json',
		success: function(data){
			houseDataGrid.datagrid('loadData',data);							
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
	
	var houseDataGrid = $('#table_house').datagrid({
		fit:true,
		//表格数据来源
		url:'../house!getHouseList.action',
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
		//idField: 'house_id',
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
			{field:'ho_id', title:'房屋编号', width:70, sortable:true},
			{field:'house_code_sz', title:'房屋编码（依深标扩展）', width:70, sortable:true},
			{field:'house_code_gb', title:'房屋编码（国标）', width:70, sortable:true},
			{field:'house_code_tmp', title:'房屋编码（临时编码）', width:70, sortable:true},
			{field:'parcel_no_subno', title:'宗地支号', width:70, sortable:true},
			{field:'unit_no', title:'房号', width:70, sortable:true},
			{field:'house_no_num', title:'自然房号', width:70, sortable:true},
			{field:'seat_no', title:'座号', width:70, sortable:true},
			{field:'house_style', title:'户型', width:70, sortable:true},
			{field:'floor_no', title:'所在楼层', width:70, sortable:true},
			/*{field:'layer_no', title:'自然层（实际层）', width:70, sortable:true},
			{field:'layer_no_num', title:'自然层（数字）', width:70, sortable:true},
			{field:'layer_used', title:'所占层数', width:70, sortable:true},
			{field:'house_attr', title:'房屋性质', width:70, sortable:true},
			{field:'house_type', title:'房屋类型', width:70, sortable:true},
			{field:'house_usage', title:'用途', width:70, sortable:true},
			{field:'house_structure', title:'结构', width:70, sortable:true},
			{field:'lu_term', title:'使用年限', width:70, sortable:true},
			{field:'start_date', title:'起始日期', width:70, sortable:true},
			{field:'end_date', title:'终止日期', width:70, sortable:true},
			{field:'built_in_area', title:'房屋面积', width:70, sortable:true},
			{field:'house_in_area', title:'套内房屋面积', width:70, sortable:true},
			{field:'house_comm_area', title:'', width:70, sortable:true},
			{field:'floor_area', title:'使用面积', width:70, sortable:true},
			{field:'lu_area', title:'（分摊）用地面积', width:70, sortable:true},
			{field:'shared_com_area', title:'分摊公用面积', width:70, sortable:true},
			{field:'shared_base_area', title:'分摊基底面积', width:70, sortable:true},
			{field:'other_area', title:'其它面积', width:70, sortable:true},
			{field:'inputer_no', title:'录入员雇员号', width:70, sortable:true},
			{field:'inputer', title:'录入员', width:70, sortable:true},
			{field:'input_date', title:'录入日期', width:70, sortable:true},
			{field:'modifier_no', title:'修改人雇员号', width:70, sortable:true},
			{field:'modifier', title:'修改人', width:70, sortable:true},
			{field:'modify_date', title:'修改日期', width:70, sortable:true},
			{field:'rise_id', title:'楼宇.楼宇编号', width:70, sortable:true},
			{field:'data_status', title:'记录状态', width:70, sortable:true},
			{field:'data_from', title:'数据来源', width:70, sortable:true},
			{field:'approver_no', title:'数据核准人', width:70, sortable:true},
			{field:'appr_date', title:'数据核准日期', width:70, sortable:true},
			{field:'appr_opinion', title:'数据核准意见', width:70, sortable:true},
			{field:'appr_result', title:'数据核准结果', width:70, sortable:true},
			{field:'hxt_id', title:'户型图id', width:70, sortable:true},*/
			{field:'fcch_flatlet_id', title:'测绘房屋ID', width:70, sortable:true}
		]],
		//表头，添加工具栏。
		toolbar : [{
			id : 'house_add',
			text:'新增',
			iconCls:'icon-add',
			handler:doAdd			
		},'-',{
			id : 'house_edit',
			text:'编辑',
			iconCls:'icon-pencil',
			disabled : true,
			handler:doEdit
		}
		,'-',{
			id : 'house_delete',
			text:'删除',
			iconCls:'icon-remove',
			disabled : true,
			handler : doDelete
		}/*,'->',{
			xtype : 'searchbox',
			prompt : '输入房屋编号、自然房号、座号',
			width : 200,
			searcher : function(value) {
				houseDataGrid.datagrid('load', {
					searchStr : value
				});
			}
		}*/],
		onClickRow : function() {
			//点击列时激活“编辑”、“删除”按钮
			$('#house_edit').linkbutton('enable');
			$('#house_delete').linkbutton('enable');
		},
		onLoadSuccess : function() {
			//加载完毕禁用“编辑”、“删除”按钮
			$('#house_edit').linkbutton('disable');
			$('#house_delete').linkbutton('disable');
		}
	});
	
	//选择表格中某一行的数据。
	function getSelected(func){
		var selectedrow = $('#table_house').datagrid('getSelected');
		
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
			id: 'add_house_win',
			//窗口iframe的src
			src: ctx+'/jsp/basedata/house-add.jsp',
			//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy: true,
			//窗口标题
			title: '新增房屋',
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
					houseDataGrid: houseDataGrid
				};
				this.init();
			}
		});
	};

	//编辑
	function doEdit() {
		var row = houseDataGrid.datagrid('getSelected');
		
		openInTopWindow({
			//窗口元素的id
			id: 'edit_house_win',
			//窗口iframe的src
			src: ctx+'/jsp/basedata/house-editMain.jsp',
			//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy: true,
			//窗口标题
			title: '编辑房屋相关信息',
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
					house : row,
					houseDataGrid : houseDataGrid
				};
				this.init();
			}
		});
	};
	
	//删除
	function doDelete(){
		var row = houseDataGrid.datagrid('getSelected');
		top.$.messager.confirm('确认', '确定要删除该房屋？', function(result){
			if (result) {
				$.ajax({
					url : 'house!deleteHouse.action',
					type : 'post',
					data : {
						house_id : row.ho_id
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							top.$.messager.alert('提示', '房屋删除成功！', 'info', function() {
								houseDataGrid.datagrid('reload');
							});
						} else {
							top.$.messager.alert('提示', '房屋删除错误！', 'error');
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
		url: 'house!getHouseList.action',
		success: function(data){
			houseDataGrid.datagrid('loadData',data);
		}
	});
	
});

function submit1(){
	$('#simpleform').submit();
};
