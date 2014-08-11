/*********************************************************************************
*文  件  名  称: land_shift_acc.js
*功  能  描  述: 土地使用权转移登记
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Joyon
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
var proc;											//当前流程对象
proc=this.parent.proc;								//流程赋值
var proc_node = proc.activName;						//流程节点
var proc_id = proc.procId;							//流程实例ID
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
var userDataGrid;   					//创建申请人信息表
var transferorDataGrid;					//转让方
var houseDataGrid;						//房地产信息表
var _init_form_data;							//初始化时数据   用于判断当前页面数据是否己修改
var _cur_form_data;								//验证时数据   用于判断当前页面数据是否己修改
/**********************************************************************************
*函数名称: 
*功能说明: js加载时运行  加载变更数据项  保存登记簿中数据到申请表  获取房屋性质字典项   加载房地产信息  加载申请人信息
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
$(function() {
	
	
	if(proc_node == state1.string0){
		saveBKUserightShipToBusUserightShip();
	}
	//房地产信息Datagrid
	houseDataGrid = $('#table_house').datagrid({
		title:'土地信息',
		height:120,
		url:ctx+"/houseownership/shiftreg!getHouseInfo.action?time="+new Date()+"&proc_id="+proc_id,
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
		     		{
		     			title : '宗地号',
		     			field : 'PARCEL_CODE',
		     			width:140
		     		}, {
		     			title : '楼名及栋号',
		     			field : 'BUILDING_NAME',
		     			width:140
		     		}, {
		     			title : '房号',
		     			field : 'ROOMNAME',
		     			width:140
		     		}, {
		     			title : '地址',
		     			field : 'HOUSE_LOCATION',
		     			width:140
		     		}, {
		     			title : '项目名称',
		     			field : 'PRO_NAME',
		     			width:140
		     		}
		     		] ],
		     		// 表头，添加工具栏。
		     		onClickRow : function() {
		     		},
		     		onLoadSuccess : function() {
		     		}
	});
	//转让方datagrid
	transferorDataGrid = $('#table_transferor').datagrid({
		//fit : true,
		title:'转让方',
		height:140,
		// 表格数据来源
		url :ctx+"/houseownership/shiftreg!getHolder.action?time="+new Date()+"&proc_id="+proc_id,
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
		     		{
		     			title : '申请人',
		     			field : 'HOL_NAME'
		     		}, {
		     			title : '申请人类型',
		     			field : 'HOL_TYPE',formatter :dicts.format.app_type_format
		     		}, {
		     			title : '证件类型',
		     			field : 'HOL_CER_TYPE',formatter : dicts.format.app_cer_type_format
		     		}, {
		     			title : '证件编号',
		     			field : 'HOL_CER_NO'
		     		}, {
		     			title : '份额',
		     			field : 'PORTION'
		     		}, {
		     			title : '地址',
		     			
		     		}, {
		     			title : '法定代表人',
		     			field : 'LEGAL_NAME'
		     		},  {
		     			title : '代理人',
		     			field : 'AGENT_NAME'
		     		},{
		     			title : '代理人证件类型',
		     			field : 'AGENT_CER_TYPE'
		     		},{
		     			title : '代理人身份证号',
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
	//创建申请人信息表
	 userDataGrid = $('#table_user').datagrid({
		//fit : true,
		title:'受让方',
		height:200,
		// 表格数据来源
		url :ctx+"/houseownership/initialreg!getAppMessage.action?time="+new Date()+"&proc_id="+proc_id,
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
		// 是否在点选表中一行时同时选中复选框
		// checkOnSelect:true,
		// 是否在选中复选框时同时点选表中一行
		// selectOnCheck:true,
		// 列属性设置
		columns : [ [
		// 每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
		// {field:'ck',checkbox:true},
		
		{
			title : '申请人',
			field : 'app_name'
		}, {
			title : '申请人类型',
			field : 'app_type',formatter : dicts.format.app_type_format
		}, {
			title : '证件类型',
			field : 'app_cer_type',formatter : dicts.format.app_cer_type_format
		}, {
			title : '证件编号',
			field : 'app_cer_no'
		}, {
			title : '份额',
			field : 'app_port'
		}, {
			title : '地址',
			field : 'app_address'
		}, {
			title : '联系电话',
			field : 'app_tel'
		}, {
			title : '法定代表人',
			field : 'legal_name'
		},  {
			title : '代理人',
			field : 'agent_name'
		}, 
		{
			title : '代理人证件类型',
			field : 'agent_cer_type'
		},{
			title : '代理人身份证号',
			field : 'agent_cer'
		}, {
			title : '代理人联系电话',
			field : 'agent_tel'
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
			//点击列时激活“编辑”、“删除”按钮 只在受理和初审环节激活
			if(proc_node==state1.string0){
				$('#user_edit').linkbutton('enable');
				$('#user_delete').linkbutton('enable');
			}
		},
		onLoadSuccess : function(data) {
			//加载完毕禁用“编辑”、“删除”按钮
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
		}
	});
	 //获得登记信息
	 getPreRegMess();
	//设置权限状态
	setState(proc_node);
	//给提交按扭绑定事件 
//	document.getElementById("submit").onclick = function() {
//		submit();
//    };
});
/**********************************************************************************
*函数名称: getSelected(func)
*功能说明: 选择表格中某一行的数据。 选中申请人表一行数据
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/	
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
*函数名称: doAdd()
*功能说明: 申请人新增方法
*参数说明: 需要选中申请人中一行数据
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function doAdd() {
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
			this.init(proc_id);
		}
	});
}
/**********************************************************************************
*函数名称: doEdit()
*功能说明: 申请人编辑方法
*参数说明: 需要选中申请人中一行数据
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
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
}
/**********************************************************************************
*函数名称: doDelete()
*功能说明: 申请人删除方法
*参数说明: 需要选中申请人中一行数据
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function doDelete() {
	var row = userDataGrid.datagrid('getSelected');
	top.$.messager.confirm('确认', '确定要删除申请人名称为[' + row.app_name + ']？', function(
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
/**********************************************************************************
*函数名称: setState(proc_node)
*功能说明: 设置当前页面组件状态   主要根据流程节点来判断
*参数说明: 传入当前流程节点 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function setState(proc_node) {
	
	if(proc_node == state1.string0){
		$(".first_hidden").css("display","none");
		//$("#tr_").css("display","none");
		$("#REG_STATION").combo('disable');
		$("#REG_TYPE").combo('disable');
		
		
		$("#HOUSE_ATTR").combo('enable');
		$("#GET_MODE").combo('enable');
	}
	if(proc_node == state1.string1){
		//$("#REG_STATION").combo('disable');
		$(":input").attr("disabled", "disabled");
		$("#REG_TYPE").combo('disable');
		$("#HOUSE_ATTR").combo('enable');
		$("#GET_MODE").combo('enable');
		$("#LOCATION_REG_UNIT").combo('enable');
		
		$("#REG_STATION").combo('disable');
		$("#REG_VALUE").removeAttr("disabled"); 
		
		//$("#FLAT_POSITION").combo('disable');
		$("#EXCURSUS").removeAttr("disabled"); 	
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		
		
	}
	if (!(proc_node == state1.string0)&&!(proc_node == state1.string1)) {
		$("#REG_STATION").combo('disable');
		$("#HOUSE_ATTR").combo('disable');
		$("#GET_MODE").combo('disable');
		$("#FLAT_POSITION").combo('disable');
		$("#REG_TYPE").combo('disable');
		$("#LOCATION_REG_UNIT").combo('disable');
		
		$(":input").attr("disabled", "disabled");
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		

	}

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
function submit(){
	_init_form_data = $("#main_form").serializeJson(); 
//	var validateResult = validate();
//	if(!validateResult.result){
//		return false;
//	}
 	if(proc_node == state1.string0){
		return saveRegInfo();		
	}
	if(proc_node == state1.string1){
//		if(!saveExcursus()){
//			return false;
//		}	
		return saveRegInfo();
	}
	return true;
}

// 登记信息保存更新操作
/**********************************************************************************
*函数名称: saveRegInfo
*功能说明: 登记信息保存更新操作
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function saveRegInfo() {
	var result = true;
	//alert("saveRegInfo...");
	//var get_mode = $("#GET_MODE").combo('getValue');
	var reg_value = $("#REG_VALUE").numberbox('getValue');
	var reg_station = $("#REG_STATION").combo('getValue');
	//var house_attr = $("#HOUSE_ATTR").combo('getValue');
	var location_reg_unit = $("#LOCATION_REG_UNIT").combo('getValue');
	
	 var EXCURSUS = $("#EXCURSUS").val();
	  $.ajax({
	   		dataType:'json',
	   		url:ctx+"/houseownership/shiftreg!saveLandShiftRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"reg_value":reg_value,"reg_station":reg_station,"excursus":EXCURSUS,"location_reg_unit":location_reg_unit},
	   		success:function(data){
	   			//alert(JSON.stringify(data));
			 	if(data){
			 		top.$.messager.alert('保存成功提示',data.result,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',"保存失败",'error');
				}
	   		},error:function(data){
	   			top.$.messager.alert('保存失败提示',"保存失败!"+JSON.stringify(data),'error');
	   			result = false;
	   		}
	   	});  
	  
	  return result;
}
/**********************************************************************************
*函数名称: saveExcursus()
*功能说明: 保存房地产证附记到缮证表中
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function  saveExcursus(){
	 var EXCURSUS = $("#EXCURSUS").val();
		var result = true;
		 
		//var result = validate(); 
		
			
			 $.ajax({
			   		dataType:'json',
			   		url:ctx+"/common/certificate!saveExcursus.action?time="+new Date()+"&proc_id="+proc_id,
			   		contentType:"application/x-www-form-urlencoded; charset=GBK",
			   		//表单的序列化操作
			   		data:{"excursus":EXCURSUS},
			   		success:function(data){
					 	if(data){
					 		top.$.messager.alert('保存成功提示',"保存成功",'info',function(){
							});	
					 		
					 	}else {
							top.$.messager.alert('保存失败提示',"保存失败",'error');
						}
			   		},error:function(data){
			   			result = false;
			   		}
			   	});  
		
		
		     return result;
};
/**********************************************************************************
*函数名称: getPreRegMess()
*功能说明: 获取从受理前置窗口传递的登记信息
*参数说明: 
*返 回 值: 无返回值  直接回填到当前页面
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getPreRegMess(){
	$.ajax({
		    dataType: 'json',
			url:ctx+"/houseownership/shiftreg!getLandShiftRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
			success:function(data){
			 	if(data){
			 		//data = data.RegInfo;
			 		//alert(JSON.stringify(data));
			 		$("#REG_CODE").val(data.REG_CODE);
			 		$("#REG_STATION").combodict("setValue",data.REG_STATION);
			 		$("#BUS_DETAIL").val(data.PROC_NAME);
			 		$("#REG_TYPE").combodict("setValue",data.REG_TYPE);
			 		
			 		$("#EXCURSUS").text(data.EXCURSUS);
			 		//$("#HOUSE_ATTR").val(data.HOUSE_KIND);
			 		$("#HOUSE_ATTR").combodict("setValue",data.HOUSE_ATTR);
			 		if(data.GET_MODE){
			 			//alert($("#GET_MODE").combo("getValue"));
			 			$("#GET_MODE").combodict("setValue",data.GET_MODE);
			 		}
			 		if(data.GET_PRICE){
			 			$("#REG_VALUE").numberbox('setValue', data.GET_PRICE);
			 		}
			 		if(data.LOCATION_REG_UNIT){
			 			$("#LOCATION_REG_UNIT").combodict("setValue",data.LOCATION_REG_UNIT);
			 		}
			 		if(data.naturalInfo){
			 			var n_data = data.naturalInfo;
			 			//alert(n_data.BUILDING_NAME)
			 			//alert(JSON.stringify(n_data));
			 			$('#table_house').datagrid('load',n_data);
			 		}
			 	}
			 	_init_form_data = $("#main_form").serializeJson(); 		//重新获取当前行页面初始化值
			}
		});
}
/**********************************************************************************
*函数名称: 页面校验方法
*功能说明: 验证页面上的非空  及数据格式   
*参数说明: flag 1代表保存 提交不传值 用来区分保存和提交
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
	//提示消息 
	var message;
	if(proc_node==state1.string1){
		message = "房地产证附记！";
		var vEXCURSUS = new tt.Field(message,"EXCURSUS");  
	    //非空检验
	    tt.vf.req.add(vEXCURSUS);
		
	    var valResult = tt.validate();
		if(!valResult.result){
			 result.message=valResult.message;
			 result.result=false;
			 return result;
		} 
	}
	
	//受理环节验证
	if(proc_node==state1.string0){
		
		var location_reg_unit =  $("#LOCATION_REG_UNIT").combo('getValue');
		if($.trim(location_reg_unit).length==0){
			message = '请选择房地产所在区！';
			result.message=message;
			 result.result=false;
			 return result;

		}
		
//		var house_attr = $("#HOUSE_ATTR").combo('getValue');
//		if($.trim(house_attr).length==0){
//			message = '请选择购房性质！';
//			result.message=message;
//			 result.result=false;
//			 return result;
//
//		}
		
//		var get_mode = $("#GET_MODE").combo('getValue');
//		
//		if($.trim(get_mode).length==0){
//			message = '请选择转移方式！';
//			result.message=message;
//			 result.result=false;
//			 return result;
//
//		}
		var reg_value = $("#REG_VALUE").numberbox('getValue');
		if($.trim(reg_value).length==0){
			message = '请输入登记价款！';
//			top.$.messager.alert('提示',message, 'info',
//					function() {
//						
//					});	
			result.message=message;
			 result.result=false;
			 return result;
	
		}
		
		
		//验证受让方
		var tmpData = $('#table_user').datagrid("getRows");
		if(tmpData.length==0){
			message = '请录入受让方！';
//			top.$.messager.alert('提示',message, 'info',
//					function() {
//						
//					});	
			result.message=message;
			 result.result=false;
			 return result;
		}

	}
	//有值  代表当前的是保存操作  重新给变量赋值
	if(v_flag){
		_init_form_data = $("#main_form").serializeJson(); 
	}
	
	//判断数据项是否己修改  如果己修改  则提示是否保存未保存数据
	_cur_form_data = $("#main_form").serializeJson(); 
	
	var r = equal(_init_form_data,_cur_form_data);
	if(!r){
		var flag= 0 ;//用来确认 是否用户已经点击放弃保存  未点击  代表是在外面调用     返回false
		message = '数据己修改！请先保存后提交！';
		 if(flag){
			 
		 }else{
			 result.message=message;
			 result.result=false; 
		 }
		 return result;
	}
	return result;
}


/**********************************************************************************
*函数名称:getApp_type_dict
*功能说明:获取申请人类型字典项值  供修改时 下拉框选择
*参数说明: 
*返 回 值: 直接给app_type_dict_data赋值 并无返回值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/

function getApp_type_dict_data(){
	if(!app_type_dict_data){
		$.ajax({
			url:ctx+'/common/dict!getNewDictByCode.action?code=043',  
			dataType : 'json',
			type : 'post',
			data : {"proc_id":proc_id},
			async:false,
			success : function(data) {
				app_type_dict_data = data;
			}
		});
	}
}
/**********************************************************************************
*函数名称:getApp_cer_type_dict_data
*功能说明:获取申请人证件类型字典项值  供修改时 下拉框选择
*参数说明: 
*返 回 值: 直接给app_cer_type_dict_data赋值 并无返回值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getApp_cer_type_dict_data(){
	if(!app_cer_type_dict_data){
		$.ajax({
			url:ctx+'/common/dict!getNewDictByCode.action?code=002',  
			dataType : 'json',
			type : 'post',
			data : {"proc_id":proc_id},
			async:false,
			success : function(data) {
				app_cer_type_dict_data = data;
			}
		});
	}
}

/**********************************************************************************
*函数名称: 保存前一次使用权有效登记簿信息到申请表中
*功能说明: 保存前一次使用权有效登记簿信息到申请表中
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function saveBKUserightShipToBusUserightShip(){
	$.ajax({
		url:ctx+"/houseownership/correction!saveBKUserightShipToBusUserightShip.action",
		dataType : 'json',
		type : 'post',
		data : {"proc_id":proc_id},
		async:false,
		success : function(data) {
			
		}
	});
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
		_cur_form_data = $("#main_form").serializeJson(); 
		
		var r = equal(_init_form_data,_cur_form_data);
		//如果不相等返回  页面数据己修改  返回true
		if(!r){
		  return true;
		}
	}
	return false;
}

