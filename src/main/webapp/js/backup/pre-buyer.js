/*********************************************************************************
*文  件  名  称: pre-buyer.js
*功  能  描  述: 预购方人信息.js
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Joyon
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
//初始化加载.	
var proc;//=this.parent.proc;
//var serialNumber ={num1:"登记编号",num2:"归档号",num3:"房地产证号"};
//
var proc_node ="";// proc.activName;
var proc_id ="";// proc.procId;
var userDataGrid;
var form;

//window.onload = function(){
//	init();
//};
function init(cur_proc){
	proc = cur_proc;
	proc_id = proc.procId;
	initForm();
	initDatagrid();
};
/**********************************************************************************
*函数名称: initForm
*功能说明: 初始化表格
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014年5月12日 19:21:25
*修改历史: 
***********************************************************************************/
function initForm(){
	 form = $('#edit_user_form').tableform({
		errorCode: 700,
		//表单元素表格的列数（通常设为两列）
		colnum: 2,
		//表单控件标题列宽
		titleWidth: 120,
		//表单控件输入框列宽
		cellWidth: 250,
		//表单控件input、select、combo等输入框宽
		inputWidth:	200,
		//textarea控件输入框宽
		textareaWidth: 500,
		//textarea控件输入框高
		textareaHeight: 90,
		//是否设置fieldset标签
		fieldset:false	,
		//表单元素参数数组。数组中每个对象构成一个表单元素
		inputs: [{
			tag: 'input',
			title: '预购人',
			name: 'app_name',
			id: 'sqr',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
		},{
			tag: 'input',
			title: '预购人类型',
			name: 'app_type',
			id: 'sqrlx',
			type: 'combodict',
			options: {
				code: '043',
			}
		},
		{
			tag: 'input',
			title: '证件类型',
			name: 'app_cer_type',
			id: 'zjlx',
			type: 'combodict',
			options: {
				code: '002',
				
			}
		},{
			tag: 'input',
			title: '法定代表人',
			name: 'legal_name',
			id: 'fddbr'
		     
		},{
			tag: 'input',
			title: '证件号码',
			name: 'app_cer_no',
			id: 'zjbh',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
		},{
			tag: 'input',
			title: '份额',
			name: 'app_port',
			id: 'fe',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
			},{
			tag: 'input',
			title: '地址',
			name: 'app_address',
			id: 'dz',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
			},{
			tag: 'input',
			title: '联系电话',
			name: 'app_tel',
			id: 'lxdh'
		} ,{
			tag: 'input',
			title: '代理人',
			name: 'agent_name',
			id: 'dlr'
		    
		},
		{
			tag: 'input',
			title: '代理人证件类型',
			name: 'agent_cer_type',
			id: 'dlr',
			type: 'combodict',
			options: {
				code: '002',
				
			}
		    
		},
		
		{
			tag: 'input',
			title: '代理人证件号码',
			name: 'agent_cer',
			id: 'dlrsfzh'
		    
		},{
			tag: 'input',
			title: '代理人联系电话',
			name: 'agent_tel',
			id: 'dlrdh'
		    
		}
		,{
			tag: 'input',
			title: '预购人关系',
			name: 'app.hol_rel',
			id: 'gx',
			type: 'combodict',
			options: {
				code: '063',
				
			}
		    
		}],
		url: ctx+'/houseownership/ownershipinitialreg/initialreg!updateApplicant.action?time='+new Date(),
		dataType : 'json',
		success: function(data){
			if (data.success) {
				top.$.messager.alert('更新用户提示',data.tipMessage,'info',function(){
					args.userDataGrid.datagrid('reload');
					closeInTopWindow('edit_user_win');
					
				});				
			} else {
				top.$.messager.alert('更新用户错误',data.errorMessage,'error');
			}
		}
		
	});
}
/**********************************************************************************
*函数名称: initDatagrid
*功能说明: 初始化datagrid
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014年5月12日 19:21:21
*修改历史: 
***********************************************************************************/
function initDatagrid(){
	//创建预购人信息表
	 userDataGrid = $('#datagrid_user').datagrid({
		//fit : true,
		title:'预购方列表',
		height:200,
		// 表格数据来源
		url :ctx+"/backup/presale!getPreBuyerInfo.action?time="+new Date()+"&proc_id="+proc_id,
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
			title : '预购人',
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
			width:100,formatter : dicts.format.app_cer_type_format
			
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
			width:100,formatter : dicts.format.app_cer_type_format
			
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
			id : 'user_edit',
			text : '编辑',
			iconCls : 'icon-pencil',
			disabled : true,
			handler : doEdit
		}],
		onClickRow : function() {
			//点击列时激活“编辑”、“删除”按钮
			var user = userDataGrid.datagrid("getSelected");
			form.form("load",user);	
			$('#user_edit').linkbutton('enable');
		},
		onLoadSuccess : function(data) {
			data = data.rows;
			if(data){
				userDataGrid.datagrid("selectRow",0);
				form.form("load",data[0]);	
			}
			//加载完毕禁用“编辑”、“删除”按钮
			//$('#user_edit').linkbutton('disable');
			//$('#user_delete').linkbutton('disable');
			
			
		}

	});
}

//编辑
function doEdit() {
	var row = userDataGrid.datagrid('getSelected');

	openInTopWindow({
		// 窗口元素的id
		id : 'edit_user_win',
		// 窗口iframe的src
		src : ctx+'/common/applicant/editapplicant.jsp',
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
$(function(){
	$.extend($.fn.validatebox.defaults.rules , {
		confirmPassword: {
	        validator: function(value,param){
	            return value == $(param[0]).val();
	        },
	        message: '两次密码输入不匹配。'
	    }
	});
});



