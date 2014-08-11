/*********************************************************************************
*文  件  名  称: mort-change-acc.js
*功  能  描  述: 一般抵押权变更登记申请表js
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: panda
*创  建  日  期： 2014年4月9日 10:33:28
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
//初始化加载.	
var proc=this.parent.proc;
var proc_node = proc.activName;
var proc_id = proc.procId;
//var proc_id = 4;//1000016366;
//var proc_node = "初审";
//当前表单元素的值
var _cur_form_data;
//初始化时表单元素的值
var _init_form_data;
var state1 = {
		string0: "受理",
		string1 : "初审",
		string2 : "复审",
		string3 : "审核",
		string4 : "核准",
		string5 : "初步审定",
		string6 : "公告审定",
		string7: "公告结果初审",
		string8 : "公告结果复审",		
		string9 : "缮证",
		string10 : "发证",
		string11: "归档",
		string12: "公告",
		string13:"拟定公告"
	};
var userDataGrid;
var mortgager;
var houseDataGrid;
/**********************************************************************************
*函数名称: 
*功能说明: 页面初始化
*函数作者: panda
*创建日期: 2014-02-28
*修改历史: 
***********************************************************************************/
$(function() {
	houseDataGrid = $('#table_house').datagrid({
		title:'房地产信息',
		height:240,
		url:ctx+"/mortgage/morsetup!getRegunitMess.action?time="+new Date()+"&proc_id="+proc_id,
		// 表格每列宽度自动适应表格总宽度
		autoRowHeight : true,
		fitColumns : true,
		// 去掉边框
		border : true,
		striped : true,
		// 是否有翻页栏
		pagination : true,
		// pagePosition:'top',
		// 每页行数
		pageSize : 10,
		// 是否在最左增加一列显示行号的列
		rownumbers : true,
		// 主键值所在行。在使用复选框时必须设置此项。
		//idField : 'jjclmc',
		// 表格的行是否交替显示不同背景色
		striped : true,
		// 只允许单选一行
		singleSelect : true,
		
		columns : [ [
		     		// 每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。

		     		// {field:'ck',checkbox:true},
		     		
		     		{
		     			title : '宗地号',
		     			field : 'PARCEL_CODE',
		     			width:80
		     			
		     		}, {
		     			title : '宗地坐落',
		     			field : 'LAND_ADDRESS',
		     			width:100
		     			
		     		},
		     		
		     		
		     		{
		     			title : '建筑物名称',
		     			field : 'BUILDING_NAME',
		     				width:100
		     		}, 
		     		{
		     			title : '栋号',
		     			field : 'BUILD_NO',
		     			width:50
		     			
		     		}, 
		     		{
		     			title : '房号',
		     			field : 'ROOMNAME',
		     			width:50
		     			
		     		},  {
		     			title : '项目名称',
		     			field : 'PRO_NAME',
		     			width:100
		     			
		     		}, {
		     			hidden: true,
		     			field : 'TYPE',
		     			width:100
		     			
		     		}, {
		     			hidden: true,
		     			field : 'CODE',
		     			width:100
		     			
		     		},
		     		{
		     			title : '操作',		     		
		     			field:'button',
		     			formatter:function(value,rec){
		     				 var btn = '<a class="editcls" onclick="" href="javascript:void(0)">查看</a>';  
		                     return btn;
		     			
		     			}
		     		}
		     		

		     		] ],
		     		// 表头，添加工具栏。
		     		onClickCell:function(rowIndex, field, value){
		     			if(field=="button"){
		    				$('#table_house').datagrid('selectRow',rowIndex);
		    				dowatch(this);
		    			}
		    		},
		     		onLoadSuccess : function() {
		     			$('.editcls').linkbutton({text:'查看'});
		     			
		     		}
	});
	
	
	mortgager = $('#table_transferor').datagrid({
		title:'抵押人',
		height:200,
		// 表格数据来源
		url :ctx+"/mortgage/morsetup!getChangeMortgager.action?time="+new Date()+"&proc_id="+proc_id+"&apptype=063003",
		// 表格每列宽度自动适应表格总宽度
		autoRowHeight : true,
		fitColumns : false,
		// 去掉边框
		border : true,
		striped : true,
		// 是否有翻页栏
		pagination : true,
		// pagePosition:'top',
		// 每页行数
		pageSize : 10,
		// 是否在最左增加一列显示行号的列
		rownumbers : true,
		// 主键值所在行。在使用复选框时必须设置此项。
		//idField : 'jjclmc',
		// 表格的行是否交替显示不同背景色
		striped : true,
		// 只允许单选一行
		singleSelect : true,
		
		columns : [ [
		     		// 每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。

		     		// {field:'ck',checkbox:true},
		     		{
		            	hidden:  true,
		               	field : 'HOL_REL'
			
		                   },
		                   {
				            hidden:  true,
				            field : 'REG_UNIT_CODE'
					
				           },
		                   {
		           			hidden:  true,
		           			field : 'APPLICANT_ID'
		           			
		           		}, 
		      		{
		     			title : '申请人',
		     			field : 'APP_NAME'
		     			
		     		}, {
		     			title : '申请人类型',
		     			field : 'APP_TYPE',
		     			formatter : function(value) {
		    				if(value == '043001'){
		    					
		    					return '个人';
		    				};
		    				if(value == '043002'){
		    					
		    					return '单位';
		    				}
		    		
		    			}
		     			
		     		}, {
		     			title : '证件类型',
		     			field : 'APP_CER_TYPE',
		     			formatter : function(value) {
		    				if(value == '002001'){
		    					
		    					return '身份证';
		    				};
		    				if(value == '002002'){
		    					
		    					return '军官证';
		    				}
		    		
		    			}
		     			
		     		}, {
		     			title : '证件编号',
		     			field : 'APP_CER_NO'
		     			
		     		}, {
		     			title : '份额',
		     			field : 'APP_PORT'
		     			
		     		}, {
		     			title : '地址',
		     			field : 'APP_ADDRESS'
		     			
		     		}, {
		     			title : '法定代表人',
		     			field : 'LEGAL_NAME'
		     		
		     		},  {
		     			title : '代理人',
		     			field : 'AGENT_NAME'
		     			
		     		}, 
		     		{
		     			title : '代理人证件类型',
		     			field : 'AGENT_CER_TYPE',
		     			formatter : function(value) {
		    				if(value == '002001'){
		    					
		    					return '身份证';
		    				};
		    				if(value == '002002'){
		    					
		    					return '军官证';
		    				}
		    		
		    			}
		     			
		     		},
		     		
		     		
		     		{
		     			title : '代理人证件号码',
		     			field : 'AGENT_CER'
		     			
		     		}, {
		     			title : '代理人联系电话',
		     			field : 'AGENT_TEL '
		     			
		     		}

		     		] ],
		     		toolbar : [ {
		    			id : 'holder_edit',
		    			text : '编辑',
		    			iconCls : 'icon-pencil',
		    			disabled : true,
		    			handler : mortgagerEdit
		    		}, '-', {
		    			id : 'holder_delete',
		    			text : '删除',
		    			iconCls : 'icon-remove',
		    			disabled : true,
		    			handler : mortgagerDel
		    		}],
		     		// 表头，添加工具栏。
		     		onClickRow : function() {
		     			if(proc_node == state1.string0){
		     				$('#holder_edit').linkbutton('enable');
		     				$('#holder_delete').linkbutton('enable');
		     				}
		     			
		     		},
		     		onLoadSuccess : function() {
		     			
		     			
		     		}
	});
	/**********************************************************************************
	*函数名称: mortgagerEdit
	*功能说明: 编辑抵押人
	*参数说明: 无
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/
	function mortgagerEdit() {
		var row = mortgager.datagrid('getSelected');
    // alert(JSON.stringify(row));
		openInTopWindow({
			// 窗口元素的id
			id : 'edit_user_win',
			// 窗口iframe的src
			src : ctx+'/jsp/common/applicant/editapplicant.jsp',
			// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy : true,
			// 窗口标题
			title : '编辑申请人',
			// 窗口宽
			width : 700,
			// 窗口高
			height : 400,
			modal : true,
			// 窗口中iframe的window对象的onLoad回调函数设置
			onLoad : function() {
				// 此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
				// 因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
				this.openerWindow = window;
				// 将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
				this.args = {
					user : row,
					userDataGrid : mortgager
				};
				this.init(row);
			}
		});
	};
	/**********************************************************************************
	*函数名称: mortgagerDel
	*功能说明: 删除抵押人
	*参数说明: 无
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/
	
	function mortgagerDel() {
		var row = mortgager.datagrid('getSelected');
		top.$.messager.confirm('确认', '确定要删除申请人名称为[' + row.APP_NAME + ']？', function(
				result) {
			if (result) {
				$.ajax({
					url : ctx+"/houseownership/initialreg!deleteApplicant.action?time="+new Date(),
					type : 'post',
					data : {
						applicant_id : row.APPLICANT_ID
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							top.$.messager.alert('提示', data.tipMessage, 'info',
									function() {
								        //alert("删除之后刷新");
								     mortgager.datagrid('reload');
									});
						} else {
							top.$.messager.alert('提示', data.errorMessage, 'error');
						}
					}
				});
			}
		});
	}
	//创建抵押权人信息表
	 userDataGrid = $('#table_user').datagrid({
		//fit : true,
		title:'抵押权人',
		height:200,
		// 表格数据来源
		url :ctx+"/mortgage/morsetup!getChangeMortgagee.action?time="+new Date()+"&proc_id="+proc_id+"&apptype=063004",
		// 表格每列宽度自动适应表格总宽度
		autoRowHeight : true,
		fitColumns : false,
		// 去掉边框
		border : true,
		striped : true,
		// 是否有翻页栏
		pagination : true,
		// pagePosition:'top',
		// 每页行数
		pageSize : 10,
		// 是否在最左增加一列显示行号的列
		rownumbers : true,
		// 主键值所在行。在使用复选框时必须设置此项。
		//idField : 'jjclmc',
		// 表格的行是否交替显示不同背景色
		striped : true,
		// 只允许单选一行
		singleSelect : true,
		// 是否在点选表中一行时同时选中复选框
		// checkOnSelect:true,
		// 是否在选中复选框时同时点选表中一行
		// selectOnCheck:true,
		// 列属性设置
		columns : [ [
		// 每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。

		// {field:'ck',checkbox:true},
		{
			hidden:  true,
			field : 'HOL_REL'
			
		}, 
		{
			hidden:  true,
			field : 'APPLICANT_ID'
			
		}, 
		{
			title : '申请人',
			field : 'APP_NAME'
			
		}, {
			title : '申请人类型',
			field : 'APP_TYPE',
			formatter : function(value) {
				if(value == '043001'){
					
					return '个人';
				};
				if(value == '043002'){
					
					return '单位';
				}
		
			}
			
		}, {
			title : '证件类型',
			field : 'APP_CER_TYPE',
			formatter : function(value) {
				if(value == '002001'){
					
					return '身份证';
				};
				if(value == '002002'){
					
					return '军官证';
				}
		
			}
			
		}, {
			title : '证件编号',
			field : 'APP_CER_NO'
			
		}, {
			title : '份额',
			field : 'APP_PORT'
			
		}, {
			title : '地址',
			field : 'APP_ADDRESS'
			
		}, {
			title : '联系电话',
			field : 'APP_TEL'
			
		}, {
			title : '法定代表人',
			field : 'LEGAL_NAME'
		
		},  {
			title : '代理人',
			field : 'AGENT_NAME'
			
		}, 
		{
			title : '代理人证件类型',
			field : 'AGENT_CER_TYPE',
			formatter : function(value) {
				if(value == '002001'){
					
					return '身份证';
				};
				if(value == '002002'){
					
					return '军官证';
				}
		
			}
			
		},
		
		
		{
			title : '代理人证件号码',
			field : 'AGENT_CER'
			
		}, {
			title : '代理人联系电话',
			field : 'AGENT_TEL'
			
		}

		] ],
		// 表头，添加工具栏。
		toolbar : [ 
//		            {
//			id : 'user_add',
//			text : '新增',
//			iconCls : 'icon-add',
//			handler : doAdd
//		}, '-',
			{
			id : 'user_edit',
			text : '编辑',
			iconCls : 'icon-pencil',
			disabled : true,
			handler : doEdit
		}, '-', {
			id : 'user_delete',
			text : '删除',
			iconCls : 'icon-remove',
			disabled : true,
			handler : doDelete
		}],
		onClickRow : function() {
			//点击列时激活“编辑”、“删除”按钮
			if(proc_node == state1.string0){
			$('#user_edit').linkbutton('enable');
			$('#user_delete').linkbutton('enable');
			}
		},
		onLoadSuccess : function() {
			//加载完毕禁用“编辑”、“删除”按钮
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
			
			
		}

	});
	
	 
	 
	 getPreRegMess();
	 getMortMess();

	//设置权限状态
	setState(proc_node);
	
	document.getElementById("submit").onclick = function() {
		submit();
    };
	
	
});
	
	// 选择表格中某一行的数据。
	function getSelected(func) {
		var selectedrow = $('#table_user').datagrid('getSelected');

		if (selectedrow) {
			row = selectedrow;
			// 调用相关函数
			func.call(this, selectedrow);
		} else {

			$.messager.alert('提示：', '请点击选中表格中的某一行.');
		}
	}
	/**********************************************************************************
	*函数名称: dowatch
	*功能说明: 查看登记单元详细信息
	*参数说明: 无
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/
		function dowatch(button){
			var row = $('#table_house').datagrid('getSelected');
			//alert(JSON.stringify(row));
			openInTopWindow({
				// 窗口元素的id
				id : 'add_user_win',
				// 窗口iframe的src
				//src : ctx+'/common/applicant/addapplicant.action?time='+new Date(),
				// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
				destroy : true,
				// 窗口标题
				title : '房地产信息',
				// 窗口宽
				width : 800,
				// 窗口高
				height : 600,
				modal : true,
				// 窗口中iframe的window对象的onLoad回调函数设置
				onLoad : function() {
					// 此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
					// 因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
					this.openerWindow = window;
					// 将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
					this.args = {
						userDataGrid : userDataGrid,
						regunit:row
					};
					this.init(proc_id);
				}
			});
		}
	/**********************************************************************************
	*函数名称: doAdd
	*功能说明: 新增抵押权人
	*参数说明: 无
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/
	function doAdd() {
		var hol_rel = "063004";							//默认权利人关系为抵押权人
		openInTopWindow({
			// 窗口元素的id
			id : 'add_user_win',
			// 窗口iframe的src
			src : ctx+'/jsp/common/applicant/addapplicant.jsp?time='+new Date(),
			// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy : true,
			// 窗口标题
			title : '新增申请人',
			// 窗口宽
			width : 700,
			// 窗口高
			height : 400,
			modal : true,
			// 窗口中iframe的window对象的onLoad回调函数设置
			onLoad : function() {
				// 此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
				// 因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
				this.openerWindow = window;
				// 将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
				this.args = {
					userDataGrid : userDataGrid
				};
				this.init(proc_id,hol_rel);
			}
		});
	}

	/**********************************************************************************
	*函数名称: doAdd
	*功能说明: 编辑抵押权人
	*参数说明: 无
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/
	function doEdit() {
		var row = userDataGrid.datagrid('getSelected');
    // alert(JSON.stringify(row));
		openInTopWindow({
			// 窗口元素的id
			id : 'edit_user_win',
			// 窗口iframe的src
			src : ctx+'/jsp/common/applicant/editapplicant.jsp',
			// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy : true,
			// 窗口标题
			title : '编辑申请人',
			// 窗口宽
			width : 700,
			// 窗口高
			height : 400,
			modal : true,
			// 窗口中iframe的window对象的onLoad回调函数设置
			onLoad : function() {
				// 此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
				// 因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
				this.openerWindow = window;
				// 将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
				this.args = {
					user : row,
					userDataGrid : userDataGrid
				};
				this.init(row);
			}
		});
	};

	/**********************************************************************************
	*函数名称: doAdd
	*功能说明: 删除抵押权人
	*参数说明: 无
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/
	function doDelete() {
		var row = userDataGrid.datagrid('getSelected');
		top.$.messager.confirm('确认', '确定要删除申请人名称为[' + row.APP_NAME + ']？', function(
				result) {
			if (result) {
				$.ajax({
					url : ctx+"/houseownership/initialreg!deleteApplicant.action?time="+new Date(),
					type : 'post',
					data : {
						applicant_id : row.APPLICANT_ID
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
			}
		});
	}

	// 双击表格中某一行的触发的事件
	function rowDblclick(rowIndex, row) {
		var i = 0;
		var props = [];

		for ( var p in row) {
			props[i++] = p + ' = ' + row[p];

		}
		alert(props.join(',\n'));
		// info(row);
	}
	
	/**********************************************************************************
	*函数名称: setState
	*功能说明: 根据流程节点，设置页面控件的权限
	*参数说明: proc_node：流程节点名称
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/
function setState(proc_node) {
	
	
	if(proc_node == state1.string1){
		$("#REG_STATION").combo('disable');
		$(".edit_table").attr("disabled", "disabled");	
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');
		$('#user_add').linkbutton('disable');
		
		if($('#mort_reg_date').val()==null){
		$('#mort_reg_date').val(getTime());
		}
		
	}
	if (!(proc_node == state1.string0)&&!(proc_node == state1.string1)) {
		$("#REG_STATION").combo('disable');
		$(":input").attr("disabled", "disabled");
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		$("#mort_type").combo('disable');
		$(".com").combo('disable');
	}
	
	if (proc_node == state1.string4) {
		$('#user_edit').linkbutton('enable');
		$('#user_delete').linkbutton('enable');

		//$('#user_add').linkbutton('enable');

	}
    if(proc_node != state1.string0){
		
		$(".mortreg").css({display:"block"});
		//$(".remark").css({display:"block"});
	}
    
}
/**********************************************************************************
*函数名称: submit
*功能说明: 表单提交操作
*参数说明: 
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function submit(){	
    if(proc_node == state1.string0){
		
    	return checkMortgagee();
		
	}
	if(proc_node == state1.string1){
		
		return saveMortMess();
		
	}
	return true;
}



/**********************************************************************************
*函数名称: getPreRegMess
*功能说明: 获取登记基本信息，例如登记点、业务描述、登记类型、登记编号等
*参数说明: 
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getPreRegMess(){
	$.ajax({
		    dataType: 'json',
			url:ctx+"/mortgage/morsetup!getRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
			success:function(data){
			 	if(data){
			 		//alert(JSON.stringify(data));
			 		$("#REG_CODE").val(data.REG_CODE);
			 		$("#REG_STATION").combodict("setValue",data.REG_STATION);
			 		$("#BUS_DETAIL").val(data.PROC_NAME);
			 		$("#REG_TYPE").combodict("setValue",data.REG_TYPE);
			 		//$("#EXCURSUS").text(data.EXCURSUS);
			 		//$("#HOUSE_ATTR").val(data.HOUSE_KIND);
			 		if(data.MORT_MODE){
			 			//alert($("#GET_MODE").combo("getValue"));
			 			//$("#MORT_MODE").combodict("setValue",data.MORT_MODE);
			 			
			 		}
			 		
			 	 
			 	}
				}
		});
	
}
/**********************************************************************************
*函数名称: getMortMess
*功能说明: 获取抵押登记信息
*参数说明: 
*返 回 值: 抵押登记信息json数据
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getMortMess(){
	$.ajax({
	    dataType: 'json',
		url:ctx+"/mortgage/morsetup!getChangeMortgage.action?time="+new Date()+"&proc_id="+proc_id,
		success:function(data){
		 	if(data){
		 		 $("#mort_type").combodict('setValue',data.mort_type);
		 		$("#loan_usage").val(data.loan_usage);
		 		 $("#mort_con_no").val(data.mort_con_no);
		 		//$("#rel_orig_value").val(data.rel_orig_value);
		 		//$("#mort_assure_right").val(data.mort_assure_right);
		 		 //$("#assess_price").val(data.assess_price);
		 		//$("#agreed_price").val(data.agreed_price);
		 		$("#borrower").val(data.borrower);
		 		$("#mort_port").val(data.mort_port);
		 		$("#assuer_range").val(data.assuer_range);
		 		$("#mort_seq").val(data.mort_seq);
		 		$("#max_amount").val(data.max_amount);
		 		 if(data.rel_orig_value){
			 			$("#rel_orig_value").numberbox('setValue',data.rel_orig_value);
			 		}
		 		
                if(data.mort_assure_right){
		 			
		 			
		 			$("#mort_assure_right").numberbox('setValue',data.mort_assure_right);
		 		}
		 		
		 		if(data.assess_price){
		 			
		 			
		 			$("#assess_price").numberbox('setValue',data.assess_price);
		 		}
		 		
                 if(data.agreed_price){
		 			
		 			$("#agreed_price").numberbox('setValue',data.agreed_price);
		 			
		 		}
		 		if(data.sure_amount){
		 			
		 			$("#sure_amount").numberbox('setValue',data.sure_amount);
		 			
		 		}
		 		
		 		if(data.mort_reg_date){
		 			var djrq = data.mort_reg_date;
		 			//$("#mort_reg_date").datebox('setValue',djrq.substr(0,djrq.length-11));
		 			$("#mort_reg_date").val(djrq.substr(0,djrq.length-11));
		 			
		 		}
		 		if(data.creditor_start_date){
		 			var qsrq = data.creditor_start_date;
		 			$("#creditor_start_date").datebox('setValue',qsrq.substr(0,qsrq.length-11));	
		 			
		 		}
		 		if(data.creditor_end_date){
		 			var zzrq = data.creditor_end_date;
		 			$("#creditor_end_date").datebox('setValue',zzrq.substr(0,zzrq.length-11));	
		 			
		 		}
	
		 		
		 	}
		 	//初始化表单赋值
		 	_init_form_data = $("#add_app_form").serializeJson();
		 
		}
	});
	
	
	
	
}
/**********************************************************************************
*函数名称: saveMortMess
*功能说明: 保存抵押登记信息
*参数说明: 
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function saveMortMess(){
	var result = true;
	//数据校验
//	if(!checkMortMess()){
//		
//		return;
//	}
	 $.ajax({
	   		dataType:'json',
	   		url:ctx+"/mortgage/morsetup!saveMortMess.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:$("#add_app_form").serialize(),
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		
			 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
			 			getMortMess();
					});	
			 		
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',data.errorMessage,'error');
				}
	   		},error:function(){
	   			result = false;
	   		}
	   	});  
	 
	 return result;

}
/**********************************************************************************
*函数名称: checkMortMess
*功能说明: 检查抵押权人是否已存在数据
*参数说明: 
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function checkMortMess(){
		var result ={
				result:false,
				message:""
		};
	 	var dylx = $("#mort_type").combodict('getValue');
		
		if($.trim(dylx).length==0){
			result.message = '请录入抵押类型！';
			return result;

		}
		var dkyt = $("#loan_usage").val();
		if($.trim(dkyt).length==0){
			result.message = '请录入贷款用途！';
			return result;

		}
		var dyhth = $("#mort_con_no").val();
		if($.trim(dyhth).length==0){
			result.message = '请录入抵押合同号！';
			return result;

		}
		var fdcyz = $("#rel_orig_value").val();
		if($.trim(fdcyz).length==0){
			result.message = '请录入房地产原值！';
			return result;

		}
		var zzq = $("#mort_assure_right").val();
		
		if($.trim(zzq).length==0){
			result.message = '请录入主债权数额！';
			return result;

		}
		var pgj = $("#assess_price").val();
		if($.trim(pgj).length==0){
			result.message = '请录入评估价！';
			return result;

		}
		var ydj = $("#agreed_price").val();
		if($.trim(ydj).length==0){
			result.message = '请录入议定价！';
			return result;

		}
//		var djrq = $("#mort_reg_date").datebox('getValue');
//		if($.trim(djrq).length==0){
//			result.message = '请录入登记日期！';
//			return result;
//
//		}
//		
		var sure_amount =  	$("#sure_amount").numberbox('getValue');
		if($.trim(sure_amount).length==0){
			result.message =  '请录入确定担保的债权数额！';
			return result;

		}
		
		var qsrq =   $("#creditor_start_date").datebox('getValue');
		if($.trim(qsrq).length==0){
			result.message =  '请录入抵押起始日期！';
			return result;

		}
		var zzrq =   $("#creditor_end_date").datebox('getValue');
		if($.trim(zzrq).length==0){
			result.message =  '请录入抵押终止日期！';
			return result;

		}
		var jkr = $("#borrower").val();
		if($.trim(jkr).length==0){
			result.message =  '请录入借款人！';
			return result;

		}
		var dyfe = $("#mort_port").val();
		if($.trim(dyfe).length==0){
			result.message =  '请录入抵押份额！';
			return result;

		}
		
		var max_amount = $("#max_amount").val();
		if($.trim(max_amount).length==0){
			result.message =  '请录入最高债权确定事实！';
			return result;

		}
	result.result=true;
	return result;
	
	
}
/**********************************************************************************
*函数名称: checkMortgagee
*功能说明: 检查抵押权人是否已存在数据
*参数说明: 
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function checkMortgagee(){
	
	var rowlen  = userDataGrid.datagrid('getRows').length;
	 var  mortrowlen = mortgager.datagrid('getRows').length;
	if(rowlen == 0){
		top.$.messager.alert('提示', '抵押权人信息为空！', 'info',
				function() {
		        
				});	
		return false;		
		
	}
	if(mortrowlen == 0){
		top.$.messager.alert('提示', '抵押人信息为空！', 'info',
				function() {
					
				});	
		return false;
		
		
	}
	return true;
	
}
/**********************************************************************************
*函数名称: validate
*功能说明: 抵押登记信息格式校验
*参数说明: 
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function validate(v_flag){
	//返回结果对象
	var result ={
			result:false,
			message:'',
			page_name:'申请表'
	}
	//提示消息 
	var message;
	//校验抵押申请人和抵押权人是否为空
//    if(proc_node == state1.string0){
//    	var rowlen  = userDataGrid.datagrid('getRows').length;
//   	 	var  mortrowlen = mortgager.datagrid('getRows').length;
//   		if(rowlen == 0){
//		   		message ='抵押权人信息为空！';
//		   		result.message = message;
//				return result;
//	   	}
//	   	if(mortrowlen == 0){
//	   		message = '抵押人信息为空！';
//	   		result.message = message;
//			return result;
//	   		
//	   		
//	   	}
//	}else 
	if(proc_node == state1.string1){
		//校验抵押信息是否为空
		var tmp_result= checkMortMess();
			
	     if(!tmp_result.result){
	    	 message =tmp_result.message;
	     	 result.message = message;
	 		 return result;
		}
	}
	
     //点保存按扭  初始化变量
     if(v_flag){
			_init_form_data = $("#add_app_form").serializeJson(); 
		}
	//序列化当前表单的值
	_cur_form_data = $("#add_app_form").serializeJson();
	//alert(JSON.stringify(_init_form_data)+" "+JSON.stringify(_cur_form_data));
	var r = equal(_init_form_data,_cur_form_data);
	if(!r){
		message ="表单元素已修改，请保存后提交！";
     	result.message = message;
 		return result;
	}	
	
	result.result=true;
	return result;

/**
	//抵押类型
	var mort_type = new tt.Field("抵押类型","mort.mort_type"); 
	//贷款用途	
	var loan_usage = new tt.Field("贷款用途","mort.loan_usage"); 
	//抵押合同号
	var mort_con_no = new tt.Field("抵押合同号","mort.mort_con_no"); 
	//房地产原值
	var rel_orig_value = new tt.Field("房地产原值","mort.rel_orig_value"); 
	//抵押担保的主债权数额
	var mort_assure_right = new tt.Field("抵押担保的主债权数额","mort.mort_assure_right"); 
	//评估价
	var assess_price = new tt.Field("评估价","mort.assess_price"); 
	//议定价
	var agreed_price = new tt.Field("议定价","mort.agreed_price");
	//抵押登记日期
	var mort_reg_date = new tt.Field("抵押登记日期","mort.mort_reg_date"); 
	//债权起始日期
	var creditor_start_date = new tt.Field("债权起始日期","mort.creditor_start_date"); 
	//债权终止日期
	var creditor_end_date = new tt.Field("债权终止日期","mort.creditor_end_date"); 
	//借款人
	var borrower = new tt.Field("借款人","mort.borrower"); 
	//抵押份额
	var mort_port = new tt.Field("抵押份额","mort.mort_port"); 
	//担保范围
	var assuer_range = new tt.Field("担保范围","mort.assuer_range"); 
	//抵押顺位
	var mort_seq = new tt.Field("抵押顺位","mort.mort_seq"); 
	//最高债权确定事实
	var max_amount = new tt.Field("最高债权确定事实","mort.max_amount"); 
	//确定担保的债权数额
	var sure_amount = new tt.Field("确定担保的债权数额","mort.sure_amount"); 
	//非空验证
	tt.vf.req.add(mort_type,loan_usage,mort_con_no,rel_orig_value,mort_assure_right,assess_price,agreed_price,mort_reg_date,creditor_start_date,creditor_end_date,borrower,
			mort_port,assuer_range,mort_seq,max_amount,sure_amount);
	//债权起始日期和终止日期比较 
	new tt.CV().add(creditor_start_date).set('v', "<=", creditor_end_date); 
	
	return tt.validate(); 
*/	
}
/**********************************************************************************
*函数名称: getTime
*功能说明: 获取当前系统时间
*参数说明: 无
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getTime(){
	  var myDate = new Date();
	  var year = myDate.getFullYear();
	  var month = myDate.getMonth()+1;
	  var date = myDate.getDate(); 
     var mytime=myDate.toLocaleTimeString();     //获取当前时间	  
	var time = year+"-"+month+"-"+date;
	return time;
	
}

/**********************************************************************************
*函数名称: pageDataIsChange
*功能说明: 判断当前页面数据是否已经修改
*参数说明: 
*返 回 值: 己修改返回true   未修改返回false
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function pageDataIsChange(){
	if(proc_node == state1.string0 || proc_node == state1.string1){
		_cur_form_data = $("#add_app_form").serializeJson(); 
		
		var r = equal(_init_form_data,_cur_form_data);
		//如果不相等返回  页面数据己修改  返回true
		if(!r){
		  return true;
		}
	}
	return false;
}






