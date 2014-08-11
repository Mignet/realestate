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
			title: '项目名称',
			name: 'project_name',
			id: 'project_name'
		},{
			tag: 'input',
			title: '楼名及栋号',
			name: 'bldg_name_no',
			id: 'bldg_name_no'
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
				value: 'project_name like ? and bldg_name_no like ? and location like ?'
			}
		}],
		url: 'building!getBuildingList.action',
		dataType: 'json',
		success: function(data){
			buildingDataGrid.datagrid('loadData',data);							
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
	
	var buildingDataGrid = $('#table_building').datagrid({
		fit:true,
		//表格数据来源
		url:'../building!getBuildingList.action',
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
		//idField: 'building_id',
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
			{field:'bldg_id', title:'建筑物记录流水号', width:140, sortable:true},
			{field:'bldg_code_sz', title:'建筑物编码[深标]', width:130, sortable:true},
			{field:'bldg_code_gb', title:'建筑物编码[国标]', width:130, sortable:true},
			{field:'bldg_code_tmp', title:'建筑物编码[临时]', width:130, sortable:true},
			{field:'par_id', title:'宗地.地块编号 ', width:100, sortable:true},
			{field:'project_name', title:'项目名称 ', width:120, sortable:true},
			{field:'bldg_name_no', title:'楼名称及栋号 ', width:120, sortable:true},
			{field:'division', title:'所在行政区 ', width:100, sortable:true},
			{field:'rise_no', title:'栋号 ', width:60, sortable:true},
			/* {field:'road', title:'地址（路） ', width:70, sortable:true},
			{field:'street', title:'所在街道（街） ', width:70, sortable:true},
			{field:'house_num', title:'地址（门牌号） ', width:70, sortable:true},
			{field:'location', title:'坐落 ', width:70, sortable:true},
			{field:'par_no_subno', title:'宗地号及支号 ', width:70, sortable:true},
			{field:'bldg_attr', title:'房屋性质 ', width:70, sortable:true},
			{field:'bldg_type', title:'房屋类型 ', width:70, sortable:true},
			{field:'bldg_usage', title:'房屋用途（规划） ', width:70, sortable:true},
			{field:'bldg_usage_now', title:'房屋用途（现状） ', width:70, sortable:true},
			{field:'bldg_structure', title:'房屋结构 ', width:70, sortable:true},
			{field:'bldg_floors', title:'房屋层数 ', width:70, sortable:true},
			{field:'const_startdate', title:'开工日期 ', width:70, sortable:true},
			{field:'const_enddate', title:'竣工日期 ', width:70, sortable:true},
			{field:'lu_term', title:'使用年限 ', width:70, sortable:true},
			{field:'start_date', title:'起始日期 ', width:70, sortable:true},
			{field:'end_date', title:'终止日期 ', width:70, sortable:true},
			{field:'base_area', title:'基底面积 ', width:70, sortable:true},
			{field:'built_up_area', title:'建筑面积 ', width:70, sortable:true},
			{field:'ther_area', title:'其他面积 ', width:70, sortable:true},
			{field:'shared_lu_area', title:'分摊用地面积 ', width:70, sortable:true},
			{field:'b_jkc_count', title:'架空层数 ', width:70, sortable:true},
			{field:'b_zhc_count', title:'转换层数 ', width:70, sortable:true},
			{field:'b_sbc_count', title:'设备层数 ', width:70, sortable:true},
			{field:'b_bnc_count', title:'避难层数 ', width:70, sortable:true},
			{field:'b_up_build_area', title:'地面以上面积 ', width:70, sortable:true},
			{field:'b_ather_build_area', title:'半地下室面积 ', width:70, sortable:true},
			{field:'b_down_build_area', title:'地下室面积 ', width:70, sortable:true},
			{field:'b_build_pos_x', title:'建筑物X坐标 ', width:70, sortable:true},
			{field:'b_build_pos_y', title:'建筑物Y坐标 ', width:70, sortable:true},
			{field:'b_build_height', title:'建筑高度 ', width:70, sortable:true},
			{field:'b_skirt_count', title:'裙楼层数 ', width:70, sortable:true},
			{field:'b_tower_count', title:'塔楼层数 ', width:70, sortable:true},
			{field:'b_common_area', title:'公用建筑面积合计 ', width:70, sortable:true},
			{field:'b_apportion_common_area', title:'应分摊公用建筑面积 ', width:70, sortable:true},
			{field:'b_noapportion_common_area', title:'不应分摊公用建筑面积 ', width:70, sortable:true},
			{field:'b_up_build_floor', title:'地上总层数 ', width:70, sortable:true},
			{field:'b_down_build_floor', title:'地下总层数 ', width:70, sortable:true},
			{field:'ather_build_floor', title:'半地下室层数 ', width:70, sortable:true},
			{field:'unit_suits', title:'房屋套数 ', width:70, sortable:true},
			{field:'rptdate', title:'报建时间 ', width:70, sortable:true},
			{field:'rptid', title:'报建证号 ', width:70, sortable:true},
			{field:'inputer_no', title:'创建人id ', width:70, sortable:true},
			{field:'inputer', title:'创建人姓名 ', width:70, sortable:true},
			{field:'input_date', title:'创建日期 ', width:70, sortable:true},
			{field:'modifier_no', title:'最后修改人雇员号 ', width:70, sortable:true},
			{field:'modifier', title:'最后修改人 ', width:70, sortable:true},
			{field:'modify_date', title:'最后修改日期 ', width:70, sortable:true},*/
			{field:'bldg_status', title:'楼盘表状态 ', width:80, sortable:true},
			{field:'data_state', title:'记录状态 ', width:70, sortable:true},
			{field:'data_from', title:'数据来源 ', width:70, sortable:true}, 
			{field:'yxt_id', title:'影像图ID ', width:70, sortable:true},
			{field:'fcch_bldg_id', title:'测绘建筑物ID ', width:70, sortable:true}
		]],
		//表头，添加工具栏。
		toolbar : [{
			id : 'building_add',
			text:'新增',
			iconCls:'icon-add',
			handler:doAdd			
		},'-',{
			id : 'building_edit',
			text:'编辑',
			iconCls:'icon-pencil',
			disabled : true,
			handler:doEdit
		}
		,'-',{
			id : 'building_delete',
			text:'删除',
			iconCls:'icon-remove',
			disabled : true,
			handler : doDelete
		}/*,'->',{
			xtype : 'searchbox',
			prompt : '输入项目名称、楼名及栋号、坐落检索',
			width : 200,
			searcher : function(value) {
				buildingDataGrid.datagrid('load', {
					searchStr : value
				});
			}
		}*/],
		onClickRow : function() {
			//点击列时激活“编辑”、“删除”按钮
			$('#building_edit').linkbutton('enable');
			$('#building_delete').linkbutton('enable');
		},
		onLoadSuccess : function() {
			//加载完毕禁用“编辑”、“删除”按钮
			$('#building_edit').linkbutton('disable');
			$('#building_delete').linkbutton('disable');
		}
	});
	
	//选择表格中某一行的数据。
	function getSelected(func){
		var selectedrow = $('#table_building').datagrid('getSelected');
		
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
			id: 'add_building_win',
			//窗口iframe的src
			src: ctx+'/jsp/basedata/building-addMain.jsp',
			//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy: true,
			//窗口标题
			title: '新增建筑',
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
					buildingDataGrid: buildingDataGrid
				};
				this.init();
			}
		});
	};

	//编辑
	function doEdit() {
		var row = buildingDataGrid.datagrid('getSelected');
		
		openInTopWindow({
			//窗口元素的id
			id: 'edit_building_win',
			//窗口iframe的src
			src: ctx+'/jsp/basedata/building-editMain.jsp',
			//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy: true,
			//窗口标题
			title: '编辑建筑相关信息' + row.bldg_name_no,
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
					building : row,
					buildingDataGrid : buildingDataGrid
				};
				this.init();
			}
		});
	};
	
	//删除
	function doDelete(){
		var row = buildingDataGrid.datagrid('getSelected');
		top.$.messager.confirm('确认', '确定要删除建筑[' + row.building_name + ']？', function(result){
			if (result) {
				$.ajax({
					url : 'building!deleteBuilding.action',
					type : 'post',
					data : {
						building_id : row.building_id
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							top.$.messager.alert('提示', '建筑删除成功！', 'info', function() {
								buildingDataGrid.datagrid('reload');
							});
						} else {
							top.$.messager.alert('提示', '建筑删除错误！', 'error');
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
		url: 'building!getBuildingList.action',
		success: function(data){
			buildingDataGrid.datagrid('loadData',data);
		}
	});
	
});

function submit1(){
	$('#simpleform').submit();
};
