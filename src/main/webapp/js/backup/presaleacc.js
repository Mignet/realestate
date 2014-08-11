/*********************************************************************************
*文  件  名  称: presaleacc.js
*功  能  描  述: 预售备案申请表
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Joyon
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/

//初始化加载.	
var proc=this.parent.proc;
//var serialNumber ={num1:"登记编号",num2:"归档号",num3:"房地产证号"};
//
var proc_node ="受理";// proc.activName;
var proc_id = proc.procId;

var tmpPreSaler;
//var proc_id = 4;//1000016366;
//var proc_node = "初审";
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
var transferorDataGrid;
var houseDataGrid;
$(function() {
	
	 
	
	houseDataGrid = $('#table_house').datagrid({
		title:'房屋信息',
		height:240,
		//url:ctx+"/mortgage/morsetup!getHouseInfo.action?time="+new Date()+"&proc_id="+proc_id,
		
		url:ctx+"/backup/presale!getPreSaleInfo.action?time="+new Date()+"&proc_id="+proc_id,
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
		     			title : '项目名称',
		     			field : 'LAND_ADDRESS',
		     			width:100
		     			
		     		},
		     		
		     		
		     		{
		     			title : '栋号',
		     			field : 'BUILDING_NAME',
		     				width:100
		     		}, 
		     		{
		     			title : '房号',
		     			field : 'BUILD_NO',
		     			width:80
		     			
		     		}, 
		     		{
		     			title : '预售许可证号',
		     			field : 'cerno',
		     			width:100
		     			
		     		},  {
		     			title : '预售合同号',
		     			field : 'con',
		     			width:100
		     			
		     		},
		     		 {
		     			title : '预售人',
		     			field : 'presale',
		     			width:50
		     			
		     		}, {
		     			title : '预购人',
		     			field : 'prebuy',
		     			width:50
		     			
		     		},{
		     			title : '是否己备案',
		     			field : 'rec_status',
		     		},
		     		{
		     			title : '操作',		     		
		     			field:'button',
		     			formatter:function(value,rec){return '<span><input  type="button" value="查看" onclick=""/></span>';}
		     		}
		     		

		     		] ],
		     		// 表头，添加工具栏。
		     		onClickRow : function() {
		     		},
		     		onLoadSuccess : function(data) {
		     			
		     		},
		     		onClickCell:function(rowIndex, field, value){
		    			//点击预购人时打开预购人详情页
		    			if(field=="prebuy"){
		    				//alert();
		    				//$('#table_user').datagrid('selectRow',rowIndex);
		    				//btn_bl(this);
		    				var obj={	
		    						id:"open_prebyer",
		    						src:ctx+"/jsp/backup/pre-buyer.jsp",
		    						//窗口宽
		    						width: 800,
		    						//窗口高
		    						title:'预购方信息',
		    						height: 566,
		    						modal: true,	
		    						destroy:true,
		    						onLoad:	function(){
		    							//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
		    							//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
		    							//this.openerWindow = window;
		    							//this.document.getElementById('name').value='名称';
		    							//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
//		    							this.args = {
//		    								user: selectedrow,
//		    								userDataGrid: userDataGrid
//		    							};			
		    							this.init(proc);
		    						}
		    					};	
		    				
		    				openInTopWindow(obj);
		    			}
		    			
		    			if(field=="button"){
		    				$('#table_house').datagrid('selectRow',rowIndex);
		    				dowatch(this);
		    			}
		    			
		    		}
	});
	
	/*
	transferorDataGrid = $('#table_transferor').datagrid({
		title:'抵押人',
		height:200,
		// 表格数据来源
		url :ctx+"/mortgage/morsetup!getHolder.action?time="+new Date()+"&proc_id="+proc_id,
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
		     			title : '预售方',
		     			field : 'HOL_NAME'
		     			
		     		}, {
		     			title : '预售方类型',
		     			field : 'HOL_TYPE'
		     			
		     		}, {
		     			title : '证件类型',
		     			field : 'HOL_CER_TYPE'
		     			
		     		}, {
		     			title : '证件编号',
		     			field : 'HOL_CER_NO'
		     			
		     		}, {
		     			title : '份额',
		     			field : 'PORTION'
		     			
		     		}, {
		     			title : '地址',
		     			field : 'HOL_ADDRESS'
		     			
		     		}, {
		     			title : '法定代表人',
		     			field : 'LEGAL_NAME'
		     		
		     		},  {
		     			title : '代理人',
		     			field : 'AGENT_NAME'
		     			
		     		}, 
		     		{
		     			title : '代理人证件类型',
		     			field : 'AGENT_CER_TYPE'
		     			
		     		},
		     		
		     		
		     		{
		     			title : '代理人证件号码',
		     			field : 'AGENT_CER'
		     			
		     		}, {
		     			title : '代理人联系电话',
		     			field : 'AGENT_TEL '
		     			
		     		}

		     		] ],
		     		// 表头，添加工具栏。
		     		onClickRow : function() {
		     		},
		     		onLoadSuccess : function() {
		     			
		     			
		     		}
	});
	*/
	
	//创建预售方信息表
	 userDataGrid = $('#table_user').datagrid({
		//fit : true,
		title:'预售方信息',
		height:200,
		// 表格数据来源
		url :ctx+"/backup/presale!getPreSaler.action?time="+new Date()+"&proc_id="+proc_id,
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
			title : '预售方',
			field : 'app_name',
			width:80
			
		},{
			title : '法定代表人',
			field : 'legal_name',
			width:100
		
		}, 
		{
			title : '证件类型',
			field : 'app_cer_type',
			width:100
			
		}, {
			title : '证件编号',
			field : 'app_cer_no',
			width:100
			
		},    {
			title : '代理人',
			field : 'agent_name',
			width:50
			
		}, 
		{
			title : '代理人证件类型',
			field : 'agent_cer_type',
			width:100
			
		},	{
			title : '代理人证件号码',
			field : 'agent_cer',
			width:100
			
		}, {
			title : '联系电话',
			field : 'agent_tel',
			width:100
			
		}

		] ],
		// 表头，添加工具栏。
		toolbar : [ {
			id : 'user_add',
			text : '新增',
			iconCls : 'icon-add',
			handler : doAdd
		}, '-', {
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

	// 新增
	function doAdd() {
		openInTopWindow({
			// 窗口元素的id
			id : 'add_user_win',
			// 窗口iframe的src
			src : ctx+'/jsp/common/applicant/addapplicant.jsp?time='+new Date(),
			// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy : true,
			// 窗口标题
			title : '新增预售方',
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
				this.init(proc_id,'063014');
			}
		});
	}

	// 编辑
	function doEdit() {
		var row = userDataGrid.datagrid('getSelected');

		openInTopWindow({
			// 窗口元素的id
			id : 'edit_user_win',
			// 窗口iframe的src
			src : ctx+'/jsp/common/applicant/editapplicant.jsp',
			// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy : true,
			// 窗口标题
			title : '编辑预售方',
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

	// 删除
	function doDelete() {
		var row = userDataGrid.datagrid('getSelected');
		top.$.messager.confirm('确认', '确定要删除预售方名称为[' + row.app_name + ']？', function(
				result) {
			if (result) {
				$.ajax({
					url : ctx+"/houseownership/initialreg!deleteApplicant.action?time="+new Date(),
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
	

	// 定义流程实例查询
	function searchProcint() {
		var fields = $("#procinstSearchform").serializeArray();
		var o = {};
		jQuery.each(fields, function(i, field) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];

			}
		});
		// console.debug(o);
		$('#dg_procinst').datagrid('load', o);

	}
	

	function test() {
		var panel = $('.plui-layout').layout('panel', 'north');
		panel.panel({
			height : 143
		});
		$('.plui-layout').layout('resize');
	}
	




function setState(proc_node) {
	
	if(proc_node == state1.string0){
		$("#REG_STATION").combo('disable');
	} 
	if(proc_node == state1.string1){
		$("#REG_STATION").combo('disable');
		//$(":input").attr("disabled", "disabled");
		$(".edit_table").attr("disabled", "disabled");	
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		
	}
	if (!(proc_node == state1.string0)&&!(proc_node == state1.string1)) {
		$("#REG_STATION").combo('disable');
		$(":input").attr("disabled", "disabled");
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		
		
		

	}

}
//判断执行的是saveDjxx()还是saveFdccfj(proc_node)
function submit(){
	if(proc_node == state1.string0){
		saveRegInfo();		
	}	
	
}

// 登记信息保存更新操作
function saveRegInfo() {
	var reg_station = $("#REG_STATION").combo('getValue');
	var reg_code = $("#REG_CODE").val();
	var reg_type = $("#REG_TYPE").combo('getValue');
	var proc_name = $("#BUS_DETAIL").val();
	var location_reg_unit = $("#LOCATION_REG_UNIT").combo('getValue');
	if($.trim(reg_station).length==0){
		top.$.messager.alert('提示', '请选择登记点！', 'info',
				function() {
					
				});	
		return;

	}
	
	  $.ajax({
	   		dataType:'json',
	   		url:ctx+"/mortgage/morsetup!saveRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"oivo.reg_station":reg_station,"oivo.reg_code":reg_code,"oivo.reg_type":reg_type,"oivo.proc_name":proc_name,"oivo.location_reg_unit":location_reg_unit},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		top.$.messager.alert('保存成功提示',"保存成功",'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',"保存失败",'error');
				}
	   		}
	   	});  
	

}
//获取从受理前置窗口传递的登记信息
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
			 		if(data.LOCATION_REG_UNIT){
			 			$("#LOCATION_REG_UNIT").combodict("setValue",data.LOCATION_REG_UNIT);
			 		}else{
			 			
			 		}
			 	}
				}
		});
	
}



/**********************************************************************************
*函数名称: 页面校验方法
*功能说明: 验证页面上的非空  及数据格式   
*参数说明: v_flag 有值代表是保存操作
*返 回 值: obj  result(true通过  false不通过) message(消息)  page_name(当前页面名字)
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function validate(v_flag){
	//返回结果对象
	var result ={
			result:true,
			message:'',
			page_name:'申请表'
	}
	var location_reg_unit =  $("#LOCATION_REG_UNIT").combo('getValue');
	if($.trim(location_reg_unit).length==0){
		message = '请选择房地产所在区！';
		result.message=message;
		 result.result=false;
		 return result;

	}
	//提示消息 
	var message;
	
	return result;
}


/**********************************************************************************
*函数名称: dowatch
*功能说明: 查看登记单元详细信息
*参数说明: 无
*返 回 值: 无
*函数作者: xuzz
*创建日期: 2014-03-27
*修改历史: 
***********************************************************************************/
function dowatch(button){
		var row = $('#table_house').datagrid('getSelected');
		var obj={};
		 	obj.WHERE_CODE=row.CODE;
			obj.REG_UNIT_TYPE=row.TYPE;
		//alert(JSON.stringify(row));
		openInTopWindow({
			// 窗口元素的id
			id : 'add_user_win',
			// 窗口iframe的src
			src : ctx+'/bookmanage/book-manage!home.action?reg_unit_type='+row.TYPE+'&time='+new Date(),
			// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy : true,
			// 窗口标题
			title : '房地产信息',
			// 窗口宽
			width : 950,
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
				this.init(obj);
			}
		});
}




