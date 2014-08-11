/*********************************************************************************
*文  件  名  称: acc.js
*功  能  描  述: 更正登记申请表
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Joyon
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
var proc;
proc=this.parent.proc;
var proc_node = proc.activName;

var proc_id = proc.procId;
//var proc_id = '1000016570';//1000016366;
//var proc_node = "受理";
var changeLastEditIndex;	//用来控制修改内容的最后一行

var appLastEditIndex		//申请人最后一行

var old_data;										//数据库中的变更前数据    从房屋  宗地   楼宇  登记簿（所有权  和使用权）   权利人中取的   

var change_data_list; 								//从字典表中取出来需要变更的数据项

var change_dict_class_code = '060';					//变更更正数据项字典代码(获取变更字典代码  扩展  直接在字典项中添加就行)

var change_data_item = {};							//变更数据项

var user_load_count	=0;								//用户datagrid  加载次数     用来判断历史权利人是否改变过    如果改变过   则在变更记录中记录下当前改变的数据

var hisHolderData;									//历史权利人(未改变过的权利人)

var changedHolderData;								//改变过后的权利人

var cur_cbx_id;										//当前选中的复选框id  用来判断是否使用字典项

var is_combo_dict ='text';							//记录选中变更项   editor是否是字典项    是字典项     返回字典项object    否则使用默认 'text'

var house_attr_dict_data;							//房屋性质字典项数据
var flatlet_usage_dict_data;						//房屋用途字典项数据
var use_per_dict_data;								//使用年限字典项数据
var real_usage_dict_data;							//土地用途花花太岁 数据 


change_data_item.PRODUCT_NAME ='060001';			//房地产名称
change_data_item.HOUSE_LOCATION='060002';			//房屋坐落
change_data_item.BUILD_AREA='060003';				//建筑面积
change_data_item.TAONEI_AREA='060004';				//套内面积
change_data_item.FLATLET_USAGE='060005';			//房屋用途
change_data_item.HOUSE_ATTR='060006';				//购房性质
change_data_item.REG_VALUE='060007';				//登记价格
change_data_item.HOL_NAME='060016';					//权利人名称
change_data_item.HOL_CER_NO='060017';				//身份证号码
change_data_item.PARCEL_CODE='060008';				//宗地号
change_data_item.PARCEL_AREA='060009';				//宗地面积
change_data_item.REAL_USAGE='060010';				//土地用途	
change_data_item.LOCATION_AREA='060011';			//所在区
change_data_item.LAND_ADDRESS='060012';				//土地位置
change_data_item.USE_PER='060013';					//使用年限
change_data_item.PAR_REG_VALUE='060014';			//土地价款
change_data_item.ADD_PARCEL_PRICE='060015';			//补地价款

var reg_unit_type ={};
reg_unit_type.PARCEL ='009003';						//登记单元编号 宗地
reg_unit_type.BUILDING ='009002';					//登记单元编号  楼宇
reg_unit_type.HOUSE ='009001';						//登记单元编号 房屋

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
var _init_form_data;							//初始化时数据   用于判断当前页面数据是否己修改
var _cur_form_data;								//验证时数据   用于判断当前页面数据是否己修改


$(function() {
	 
	//获取需要变更的数据项 
	getChangeOldInfo();
	
		
		//判断登记是房屋还 宗地  分别调用不同的登记簿信息  并获取各自的字典项数据
		if(old_data.reg_unit_type == reg_unit_type.HOUSE){
			if(proc_node == state1.string0){
				saveBKOwnerShipToBusOwnerShip();
			}
			//获取房屋性质字典项list
			gethouse_attr_dict_data();
			//获取房屋用途字典项
			getflatlet_usage_dict_data();
		}else if(old_data.reg_unit_type == reg_unit_type.PARCEL){
			if(proc_node == state1.string0){
				saveBKUserightShipToBusUserightShip();
			}
			//获取土地使用年限字典项
			getuse_per_dict_data() ;
			//获取土地用途字典项
			getreal_usage_dict_data();
		}
		
	

	
	//获取变更内容  数据项    添加checbox  复选框
	getchange_data_list();
	
	/**注释掉申请人和房地产证信息  2014年7月21日 10:06:40 **/
	houseDataGrid = $('#table_house').datagrid({
		title:'房地产信息',
		height:120,
		url:ctx+"/houseownership/shiftreg!getHouseInfo.action?time="+new Date()+"&proc_id="+proc_id,
		// 表格每列宽度自动适应表格总宽度
		autoRowHeight : true,
		fitColumns : true,
		// 去掉边框
		border : true,
		striped : true,
		// 是否有翻页栏
		pagination : false,
		// pagePosition:'top',
		// 每页行数
		//pageSize : 10,
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
	//创建申请人信息表
	 userDataGrid = $('#table_user').datagrid({
		//fit : true,
		title:'申请人',
		height:200,
		// 表格数据来源
		url :ctx+"/houseownership/correction!getAppMessage.action?time="+new Date()+"&proc_id="+proc_id,
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
			
		},
		
		
		{
			title : '代理人身份证号',
			field : 'agent_cer'
			
		}, {
			title : '代理人联系电话',
			field : 'agent_tel'
			
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
			//点击列时激活“编辑”、“删除”按钮 只在受理和初审环节激活
			if(proc_node==state1.string0){
				$('#user_edit').linkbutton('enable');
				$('#user_delete').linkbutton('enable');
			}
		},
		onLoadSuccess : function(data) {
			
			//alert(JSON.stringify(data));
			//加载完毕禁用“编辑”、“删除”按钮
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
			
			//只有在受理环节中才对权利人变更进行记录  其它环节不允许权利人变更
			if(proc_node=='受理'){
				//alert(user_load_count);
				//第一次加载时   把当前权利人保存进历史权利人
				if(user_load_count == 0){
					//获取数据中更正数据项数据
					var dbChangeRecordInfo = getChangeRecordInfo();
					//如果没有数据说明未进行过权利人和权利人证号数据改变
					if(dbChangeRecordInfo.total==0){
						hisHolderData = data.rows;
					}else{		//有过权利人和权利人证号数据改变   循环  并把数据中的历史数据项 赋值给历史权利人
						
						tmpCompareHolderData =$('#table_user').datagrid("getRows");// data.rows;	
						
						//--尝试复制  直接复制原始类型  防止复制引用  导致改变hisHolderData后   data.rows跟着变
						hisHolderData = $.extend(true,[],tmpCompareHolderData);
						//hisHolderData[0].app_tel='6666666666';
						dbChangeRecordInfo = dbChangeRecordInfo.rows;
						for(var i = 0 ;i<dbChangeRecordInfo.length;i++){
							//只循环比较 数据项为权利人名称及权利人证件编号的数据项     -----先判断是否改变了权利人证号
							if(dbChangeRecordInfo[i].CHANGE_CODE==change_data_item.HOL_CER_NO ){
								for(var j=0;j<hisHolderData.length;j++){
									if(dbChangeRecordInfo[i].OLD_DATA==hisHolderData[j].app_cer_no || dbChangeRecordInfo[i].NEW_DATA==hisHolderData[j].app_cer_no){
										hisHolderData[j].app_cer_no = dbChangeRecordInfo[i].OLD_DATA;
									}
								}
							//----再比较是否改变了权利人名称     如果相等则修改该行数据
							}else if( dbChangeRecordInfo[i].CHANGE_CODE==change_data_item.HOL_NAME){
								for(var j=0;j<hisHolderData.length;j++){
									if(dbChangeRecordInfo[i].OLD_DATA==hisHolderData[j].app_name || dbChangeRecordInfo[i].NEW_DATA==hisHolderData[j].app_name){
										hisHolderData[j].app_name = dbChangeRecordInfo[i].OLD_DATA;
									}
								}
							}
							
						}
					}
					
				//其它情况下保存到改变后的权利人中	
				}else{
					
					changedHolderData = data.rows;
					
					//alert(JSON.stringify(changedHolderData));
					
					//alert(hisHolderData[0].app_name+changedHolderData[0].app_name);
					
					//判断权利人(名称、身份证号)是否已经改变 如果改变则在变更更正记录表中插入一行数据
					checkHolderIsChanged(hisHolderData,changedHolderData);
				}
				user_load_count++;
			}
		}

	});
	/**
	注释掉申请人和房地产证信息  2014年7月21日 10:06:40
	**/
	
	$('#div_change_detail').datagrid({
		title:'更正内容',
		height:300,
		url:ctx+"/houseownership/correction!getChangeRecord.action?time="+new Date()+"&proc_id="+proc_id,
		// 表格每列宽度自动适应表格总宽度
		autoRowHeight : true,
		fitColumns : true,
		// 去掉边框
		border : true,
		striped : true,
		// 是否有翻页栏
		pagination : false,
		// pagePosition:'top',
		// 每页行数
		//pageSize : 10,
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
		     			title : '更正数据项',
		     			field : 'CHANGE_CODE',
		     			width:200,formatter : function(value) {
		     				//alert(JSON.stringify(change_data_list));
		     				for(var i =0;i<change_data_list.length;i++){
		     					if(change_data_list[i].value == value){
		     						return  change_data_list[i].name;
		     					}
		     				}
		    				
		    			}
		     			
		     		}, {
		     			title : '更正前',
		     			field : 'OLD_DATA',
		     			width:200,formatter : old_new_data_format
		     			
		     		}, {
		     			title : '更正后',
		     			field : 'NEW_DATA',
		     			width:200,editor:is_combo_dict,formatter : old_new_data_format
		     			
		     		}

		     		] ],
		     		toolbar : [{
		    			id : 'change_save',
		    			text : '保存',
		    			iconCls : 'icon-ok',
		    			disabled : true,
		    			handler : saveChangeInfo
		    		}],
		     		// 表头，添加工具栏。
		     		onClickRow : changeRowClick,
		     		onLoadSuccess : function(data) {
		     			
		     			//数据加载成功时  checkbox 数据回填
		     			//alert(JSON.stringify(data.rows));
		     			var rows = data.rows;
		     			for(var i = 0;i<rows.length;i++){
		     				for(var j=0;j<change_data_list.length;j++){
		     					if(rows[i].CHANGE_CODE == change_data_list[j].value){
		     						$("#"+change_data_list[j].value).attr("checked","checked");
		     					}
		     					
		     				}
		     				
		     				//如果有房屋属性   则把新房屋属性value赋值
//		     				if(rows[i].CHANGE_CODE==change_data_item.HOUSE_ATTR){
//		     					new_house_attr.value=rows[i].NEW_DATA;
//		     				}
		     			}
		     			
		     		}
	});
	 
	 getPreRegMess();
	//设置权限状态
	setState(proc_node);
	
//	document.getElementById("submit").onclick = function() {
//		submit();
//    };
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

	// 删除
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



function setState(proc_node) {
	
	if(proc_node == state1.string0){
		$("#REG_STATION").combo('disable');
		$("#REG_TYPE").combo('disable');
		$('#app_save').linkbutton('enable');
		//$('#change_div').css("visibility","hidden");
		$('#change_div').css("display","none");
		$('#tr_excursus').css("display","none");
		
	}
	if(proc_node == state1.string1){
		$("#LOCATION_REG_UNIT").combo('disable');
		$("#REG_STATION").combo('disable');
		$("#REG_TYPE").combo('disable');
		$("#select_develop").attr('disabled','disabled');
		$('#change_save').linkbutton('enable');
		//$(":input").attr("disabled", "disabled");
		
		$("#EXCURSUS").removeAttr("disabled"); 	
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		
	}
	if (!(proc_node == state1.string0)&&!(proc_node == state1.string1)) {
		$("#LOCATION_REG_UNIT").combo('disable');
		$("#REG_STATION").combo('disable');
		$("#REG_TYPE").combo('disable');
		$(":input").attr("disabled", "disabled");
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		

	}
	
}
/**********************************************************************************
*函数名称: 保存按扭 主要保存房地产证附记
*功能说明: 在初审环节保存房地产证附记
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function submit(){
	_init_form_data = $("#main_form").serializeJson(); 
//	var result = validate();
//	if(!result.result){
//		return false;
//	}
	if(proc_node == state1.string0){
		return saveRegInfo();		
	}
	if(proc_node == state1.string1){
		//alert();
		return saveExcursus();		
	}
}
/**********************************************************************************
*函数名称: saveRegInfo
*功能说明: 保存登记信息
*参数说明: 
*返 回 值: true/false
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function saveRegInfo(){
	var  result = true;
	var location_reg_unit = $("#LOCATION_REG_UNIT").combo("getValue");
	 $.ajax({
	   		dataType:'json',
	   		url:ctx+"/houseownership/correction!saveRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"location_reg_unit":location_reg_unit},
	   		success:function(data){
			 	if(data.result=="success"){
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
}

/**********************************************************************************
*函数名称: saveExcursus
*功能说明: 在初审环节保存房地产证附记
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function  saveExcursus(){
	 var EXCURSUS = $("#EXCURSUS").val();
	var result = true; 
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
		   			top.$.messager.alert('保存失败提示',"保存失败",'error');
		   		}
		   	});  
	return result;
};
/**********************************************************************************
*函数名称: getPreRegMess
*功能说明: 获取从受理前置窗口传递的登记信息
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getPreRegMess(){
	$.ajax({
		    dataType: 'json',
			url:ctx+"/houseownership/correction!getRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
			success:function(data){
			 	if(data){
			 		//alert(JSON.stringify(data));
			 		$("#REG_CODE").val(data.REG_CODE);
			 		$("#REG_STATION").combodict("setValue",data.REG_STATION);
			 		$("#BUS_DETAIL").val(data.PROC_NAME);
			 		$("#REG_TYPE").combodict("setValue",data.REG_TYPE);
			 		$("#EXCURSUS").text(data.EXCURSUS);
			 		$("#HOUSE_ATTR").val(data.HOUSE_KIND);
			 		if(data.GET_MODE){
			 			//alert($("#GET_MODE").combo("getValue"));
			 			$("#GET_MODE").combodict("setValue",data.GET_MODE);
			 		}
			 		if(data.REG_VALUE){
			 			$("#REG_VALUE").val(data.REG_VALUE);
			 		}
			 		if(data.naturalInfo){
			 			var n_data = data.naturalInfo;
			 			//alert(n_data.BUILDING_NAME)
			 			//alert(JSON.stringify(n_data));
			 			$('#table_house').datagrid('load',n_data);
			 		}if(data.LOCATION_REG_UNIT){
			 			$("#LOCATION_REG_UNIT").combodict("setValue",data.LOCATION_REG_UNIT);
			 		}
			 	}
			 	_init_form_data =  $("#main_form").serializeJson();
				}
		});
}
/**********************************************************************************
*函数名称: 数据项筛选框点击事件
*功能说明: 复选框选中事件  选中时  添加新一行   未选中时   删除这一行
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function cbxClick(cbx){
	var cbx_id;
	var change_name;
	var old_value;
		cbx_id = cbx.id;
		change_name = $('#lbl_'+cbx_id).text();
		
		
		//alert("old_data.PARCEL_CODE:"+old_data.PARCEL_CODE);
		
		//点击时给old_value赋值
		if(cbx_id == change_data_item.PRODUCT_NAME){			//项目名称
			old_value= old_data.naturaInfo.PRO_NAME;
		}else if(cbx_id == change_data_item.TAONEI_AREA){		//套内面积
			old_value= old_data.naturaInfo.TAONEI_AREA;
		}else if(cbx_id == change_data_item.BUILD_AREA){		//建筑面积
			old_value= old_data.naturaInfo.BUILD_AREA;
		}else if(cbx_id == change_data_item.FLATLET_USAGE){		//房屋用途
			old_value= old_data.naturaInfo.FLATLET_USAGE;
		}else if(cbx_id == change_data_item.REG_VALUE){			//房屋价格
			old_value= old_data.regBookInfo.REG_VALUE;
		}else if(cbx_id == change_data_item.HOUSE_LOCATION){	//房屋坐落
			old_value= old_data.naturaInfo.HOUSE_LOCATION;
		}else if(cbx_id == change_data_item.HOUSE_ATTR){		//购房性质
			old_value= old_data.regBookInfo.HOUSE_ATTR;
		}else if(cbx_id == change_data_item.PARCEL_CODE){		//宗地号
			old_value= old_data.naturaInfo.PARCEL_CODE;
		}else if(cbx_id == change_data_item.PARCEL_AREA){		//宗地面积
			old_value= old_data.naturaInfo.PARCEL_AREA;
		}else if(cbx_id == change_data_item.REAL_USAGE){		//土地用途	
			old_value= old_data.naturaInfo.REAL_USAGE;
		}else if(cbx_id == change_data_item.LAND_ADDRESS){		//土地位置
			old_value= old_data.naturaInfo.LAND_ADDRESS;
		}else if(cbx_id == change_data_item.USE_PER){			//使用年限
			old_value= old_data.naturaInfo.USE_PER;
		}else if(cbx_id == change_data_item.PAR_REG_VALUE){		//土地价款
			old_value= old_data.regBookInfo.REG_VALUE;
		}
		//还少一个补地价款
		//else if(cbx_id == change_data_item.LAND_ADDRESS){			//所在区
//			old_value= old_data.LAND_ADDRESS;
//		}
		
//		change_data_item.TAONEI_AREA='060015';				//补地价款
		if(cbx.checked){
			cur_cbx_id = cbx_id;
			 $('#div_change_detail').datagrid('appendRow',{  
				 CHANGE_CODE:cbx_id,OLD_DATA:old_value,NEW_DATA:''
            });
			 

             changeLastEditIndex = $('#div_change_detail').datagrid('getRows').length-1; 
             //编辑行时点击保存  会取不到数据  解决
        	 var rows = $('#div_change_detail').datagrid('getRows');
             for ( var i = 0; i < rows.length; i++) {
             	 $('#div_change_detail').datagrid('endEdit', i);
             }
             
             $('#div_change_detail').datagrid('selectRow', changeLastEditIndex);  
             $('#div_change_detail').datagrid('beginEdit', changeLastEditIndex);  
             
             //设置字典项   根据cbx_id判断
             setEditorCombobox(cbx_id,changeLastEditIndex);
            
		}else{
			cur_cbx_id = "";
			top.$.messager.confirm('确认', '是否取消 '+change_name+' 变更', function(result){
				if (result) {
					var rows =  $('#div_change_detail').datagrid('getRows');
					for(var i = 0;i<rows.length;i++){
						
						if(cbx_id == rows[i].CHANGE_CODE){
							//alert(rows[i].change_name);
							$('#div_change_detail').datagrid('deleteRow',i);
						}
					}
				}else{
					$('#'+cbx_id).attr("checked","chedked");
				}
			});
		}
}

//行点击事件
function changeRowClick(rowIndex, rowData){
	//点击列时激活编辑状态    只在初审环节激活
	if(proc_node!= state1.string1){
		return;
	}
	cur_cbx_id = rowData.CHANGE_CODE;
	//如果是权利人或权利人证号   则不提供编辑
	if(cur_cbx_id == change_data_item.HOL_CER_NO || cur_cbx_id == change_data_item.HOL_NAME){
		return;
	}
	
	if (changeLastEditIndex != rowIndex) {
		 $('#div_change_detail').datagrid('endEdit', changeLastEditIndex);
		 $('#div_change_detail').datagrid('beginEdit', rowIndex);
		
	}
	changeLastEditIndex = rowIndex;
	
	setEditorCombobox(cur_cbx_id,rowIndex);
	
}

//获得变更前的数据
function getChangeOldInfo(){
	//alert('getChangeOldInfo..');
	$.ajax({
		url:ctx+"/houseownership/correction!getChangeOldInfo.action",
		//url:ctx+"/houseownership/shiftreg!getRegInfo.action,
		data:{"proc_id":proc_id,"time":new Date()},
		dataType:"json",
		type:"post",
		async:false,
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			old_data = data;
		}
	});
}




/**
 * saveAppInfo
 */
function saveAppInfo(){
	 $('#table_transferor').datagrid('endEdit', appLastEditIndex);
	 changeLastEditIndex = -1;
	var inserted =  $('#table_transferor').datagrid('getChanges', 'inserted');
	var deleted =  $('#table_transferor').datagrid('getChanges', 'deleted');
	var updated =  $('#table_transferor').datagrid('getChanges', 'updated');
	//请求后台保存数据
	$.ajax({
		//url :  ctx+'/common/dict!applyEdit.action',
		dataType : 'json',
		type : 'post',
		data : {
			//将数据拼装成JSON字符串传递到后台
			datas :$.toJSON({
				inserted : inserted,
				deleted : deleted,
				updated : updated
			})
		},
		success : function(data) {
			if (data.success) {
				top.$.messager.alert('提示', '数据保存成功！', 'info', function(){
					//将表格置为不可编缉
					itemCanEdit = false;
					//撤销时启用“编辑按钮”
					$('#dictitem_edit').linkbutton('enable');
					//撤销时重新加载表格数据
					itemDataGrid.datagrid('reload');
				});
			}else{
				top.$.messager.alert('提示', '数据保存失败！', 'info', function(){});
			}
		}
	});
}

/**
 * 保存更改后的数据
 * 将div_change_detail表格中增、删、改的数据应用到后台
 */
function saveChangeInfo() {
	
	 $('#div_change_detail').datagrid('endEdit', changeLastEditIndex);
	 changeLastEditIndex = -1;
	 
	 //编辑行时点击保存  会取不到数据  解决
	 var rows = $('#div_change_detail').datagrid('getRows');
     for ( var i = 0; i < rows.length; i++) {
     	 $('#itemDataGrid').datagrid('endEdit', i);
     }
	 
	var inserted =  $('#div_change_detail').datagrid('getChanges', 'inserted');
	var deleted =  $('#div_change_detail').datagrid('getChanges', 'deleted');
	var updated =  $('#div_change_detail').datagrid('getChanges', 'updated');
	//验证 更正后数据项是否非空填写  只验证新增和更新的
	if(inserted){
		for(var i=0;i<inserted.length;i++){
			if(inserted[i].NEW_DATA.length==0){
				top.$.messager.alert('提示', '更正后数据不能为空！', 'info',
						function() {
							
						});	
				return false;
			}
		}
	}
	if(updated){
		for(var i=0;i<updated.length;i++){
			if(updated[i].NEW_DATA.length==0){
				top.$.messager.alert('提示', '更正后数据不能为空！', 'info',
						function() {
							
						});	
				return false;
			}
		}
	}
	//请求后台保存数据
	$.ajax({
		url :  ctx+"/houseownership/correction!applyEditChangeRecord.action",
		dataType : 'json',
		type : 'post',
		data : {
			//将数据拼装成JSON字符串传递到后台
			datas :$.toJSON({
				inserted : inserted,
				deleted : deleted,
				updated : updated
			}),proc_id:proc_id
		},
		success : function(data) {
			if (data.success) {
				top.$.messager.alert('提示', '数据保存成功！', 'info', function(){
					//将表格置为不可编缉
					itemCanEdit = false;
					//撤销时启用“编辑按钮”
					$('#dictitem_edit').linkbutton('enable');
					//撤销时重新加载表格数据
					 $('#div_change_detail').datagrid('reload');
				});
			}else{
				top.$.messager.alert('提示', '数据保存失败！', 'info', function(){});
			}
		}
	});
};


/**********************************************************************************
*函数名称: 获取变更checkbox填充内容
*功能说明: 变更内容 checkbox  填充内容     （内容为字典项    ---然后筛选出属于房屋的变更登记）
*参数说明: 
*返 回 值: 直接在界面上添加复先框 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getchange_data_list(){
	
	$.ajax({
		url :  ctx+'/common/dict!getDictByCode.action',
		dataType : 'json',
		type : 'post',
		data : {code:change_dict_class_code},
		async:false,
		success : function(data) {
			change_data_list =data;
			//alert(JSON.stringify(data));
			var count = 0;					//用来记录多少个数据项  以便输出换行
			//后台取字典项成功后  循环append到table中     分列
			var dataColumn = 6;				//分的列数
			var str = '';					//table append 字符串
			var code;						//用来储存临时的数据项code
			for(var i=0;i<data.length;i++){
				code = data[i].value;
					
				//筛选属于房地产变更登记的  数据项  显示
				if(code == change_data_item.PRODUCT_NAME || code == change_data_item.HOUSE_LOCATION || code == change_data_item.BUILD_AREA || code == change_data_item.FLATLET_USAGE || code == change_data_item.TAONEI_AREA || code == change_data_item.HOUSE_ATTR || code == change_data_item.REG_VALUE){
					if(old_data.reg_unit_type == reg_unit_type.PARCEL){
						continue;
					}
					if(count%dataColumn==0){
						str+='<tr><td><input id="'+data[i].value+'"  type="checkbox" onclick="cbxClick(this)"/>'+
						'<label for="'+data[i].value+'" id="lbl_'+data[i].value+'">'+data[i].name+'</label></td>';
					}else if(count%dataColumn==5){
						str+='<td><input id="'+data[i].value+'"  type="checkbox" onclick="cbxClick(this)"/>'+
						'<label for="'+data[i].value+'" id="lbl_'+data[i].value+'">'+data[i].name+'</label></td></tr>';
					}else{
						str+='<td><input id="'+data[i].value+'"  type="checkbox" onclick="cbxClick(this)"/>'+
						'<label for="'+data[i].value+'" id="lbl_'+data[i].value+'">'+data[i].name+'</label></td>';
					}
					
					//不需要显示的信息
				}else if(code == change_data_item.HOL_NAME || code == change_data_item.HOL_CER_NO || code == change_data_item.LOCATION_AREA ){
					continue;
				}else{			//宗地更正信息
					if(old_data.reg_unit_type == reg_unit_type.HOUSE){
						continue;
					}
					if(count%dataColumn==0){
						str+='<tr><td><input id="'+data[i].value+'"  type="checkbox" onclick="cbxClick(this)"/>'+
						'<label for="'+data[i].value+'" id="lbl_'+data[i].value+'">'+data[i].name+'</label></td>';
						
					}else if(count%dataColumn==5){
						str+='<td><input id="'+data[i].value+'"  type="checkbox" onclick="cbxClick(this)"/>'+
						'<label for="'+data[i].value+'" id="lbl_'+data[i].value+'">'+data[i].name+'</label></td></tr>';
					}else{
						str+='<td><input id="'+data[i].value+'"  type="checkbox" onclick="cbxClick(this)"/>'+
						'<label for="'+data[i].value+'" id="lbl_'+data[i].value+'">'+data[i].name+'</label></td>';
					}
				}
				count++;
			}
			$('#table_cbx').append(str);						//把数据项添加到table中
		}
	});
}


/**********************************************************************************
*函数名称: 判断权利人(名称、身份证号)是否已经改变 如果改变则在变更更正记录表中插入一行数据  
*功能说明: 判断权利人(名称、身份证号)是否已经改变 如果改变则在变更更正记录表中插入一行数据  
*参数说明: hisHolderData(历史权利人) changedHolderData(改变后的权利人)
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function checkHolderIsChanged(hisHolderData,changedHolderData){
	for(var i =0;i<hisHolderData.length;i++){
		for(var j = 0;j<changedHolderData.length;j++){
			if(hisHolderData[i].applicant_id == changedHolderData[j].applicant_id){
				//权利人名称改变  保存进
				if(hisHolderData[i].app_name != changedHolderData[j].app_name){
					saveChangeRecord(change_data_item.HOL_NAME,hisHolderData[i].app_name,changedHolderData[j].app_name);
				}
				if(hisHolderData[i].app_cer_no != changedHolderData[j].app_cer_no){
					saveChangeRecord(change_data_item.HOL_CER_NO,hisHolderData[i].app_cer_no,changedHolderData[j].app_cer_no);
				}
			}
		}
	}
}

/**********************************************************************************
*函数名称: 保存变更数据项到变更记录表
*功能说明: 保存变更数据项到变更记录表  保存成功后刷新一次当前datagrid
*参数说明: code(字典项code) old_data(更正前数据) new_data(更正后数据)
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function saveChangeRecord(code,old_data,new_data){
	$.ajax({
		url:ctx+"/houseownership/correction!saveChangeRecord.action",
		//url:ctx+"/houseownership/shiftreg!getRegInfo.action,
		data:{"proc_id":proc_id,"code":code,"old_data":old_data,"new_data":new_data,"time":new Date()},
		dataType:"json",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			if(data.result=="success"){
				$('#div_change_detail').datagrid('reload');
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
	//提示消息 
	var message;
	if(proc_node==state1.string0){
		var location_reg_unit = $("#LOCATION_REG_UNIT").combodict("getValue");
		if($.trim(location_reg_unit).length==0){
			message = '请选择房地产所在区！';
			result.message=message;
			 result.result=false;
			 return result;

		}
	}
	if(proc_node==state1.string1){
		//非空检验
		message = "房地产证附记！";
		var vEXCURSUS = new tt.Field(message,"EXCURSUS")  
	    tt.vf.req.add(vEXCURSUS);
		
		var valResult = tt.validate();
		if(!valResult.result){
			 result.message=valResult.message;
			 result.result=false;
			 return result;
		} 
		//提交才进行判断  保存时初始化比对变量
		if(!v_flag){
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
		}else{
			_init_form_data = $("#main_form").serializeJson(); 
		}
		 var tmpData = $('#div_change_detail').datagrid('getRows');
		 if(tmpData.length<0){
			 message = '更正数据项为空！请先保存后提交！';
				 result.message=message;
				 result.result=false; 
			 return result; 
		 }
		
		//更正内容数据项校验
		 var tmpData = $('#div_change_detail').datagrid('getChanges');
		 if(tmpData.length>0){
			 var flag= 0 ;//用来确认 是否用户已经点击放弃保存  未点击  代表是在外面调用     返回false
			 message = '更正数据项己修改！请先保存后提交！';
			 if(flag){
				 
			 }else{
				 result.message=message;
				 result.result=false; 
			 }
			 return result;
		 }
	}
	
	return result;
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
		
		//更正内容数据项校验
		 var tmpData = $('#div_change_detail').datagrid('getChanges');
		 if(tmpData.length>0){
			return true;
		 }	
	}
	return false;
}

/**********************************************************************************
*函数名称: 保存前一次所有权有效登记簿信息到申请表中
*功能说明:保存前一次所有权有效登记簿信息到申请表中
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function saveBKOwnerShipToBusOwnerShip(){
	$.ajax({
		url:ctx+"/houseownership/correction!saveBKOwnerShipToBusOwnerShip.action",
		dataType : 'json',
		type : 'post',
		data : {"proc_id":proc_id},
		async:false,
		success : function(data) {
			
		}
	});
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
*函数名称:获取房屋性质字典项数据
*功能说明:获取到房屋属性字典项值  供修改时 下拉框选择
*参数说明: 
*返 回 值: 直接给house_attr_dict_data赋值 并无返回值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function gethouse_attr_dict_data(){
	if(!house_attr_dict_data){
		$.ajax({
			url:ctx+'/common/dict!getNewDictByCode.action?code=021',  
			dataType : 'json',
			type : 'post',
			data : {"proc_id":proc_id},
			async:false,
			success : function(data) {
				house_attr_dict_data = data;
			}
		});
	}
}
/**********************************************************************************
*函数名称:getflatlet_usage_dict_data()
*功能说明:获取到房屋用途字典项值  供修改时 下拉框选择
*参数说明: 
*返 回 值: 直接给flatlet_usage_dict_data赋值 并无返回值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getflatlet_usage_dict_data(){
	if(!flatlet_usage_dict_data){
		$.ajax({
			url:ctx+'/common/dict!getNewDictByCode.action?code=062',  
			dataType : 'json',
			type : 'post',
			data : {"proc_id":proc_id},
			async:false,
			success : function(data) {
				flatlet_usage_dict_data = data;
			}
		});
	}
}
/**********************************************************************************
*函数名称:getreal_usage_dict_data()
*功能说明:获取到土地用途字典项值  供修改时 下拉框选择
*参数说明: 
*返 回 值: 直接给real_usage_dict_data赋值 并无返回值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getreal_usage_dict_data(){
	if(!flatlet_usage_dict_data){
		$.ajax({
			url:ctx+'/common/dict!getNewDictByCode.action?code=015',  
			dataType : 'json',
			type : 'post',
			data : {"proc_id":proc_id},
			async:false,
			success : function(data) {
				real_usage_dict_data = data;
			}
		});
	}
}
/**********************************************************************************
*函数名称:getuse_per_dict_data()
*功能说明:获取使用年限字典项值  供修改时 下拉框选择
*参数说明: 
*返 回 值: 直接给use_per_dict_data赋值 并无返回值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getuse_per_dict_data(){
	if(!flatlet_usage_dict_data){
		$.ajax({
			url:ctx+'/common/dict!getNewDictByCode.action?code=006',  
			dataType : 'json',
			type : 'post',
			data : {"proc_id":proc_id},
			async:false,
			success : function(data) {
				use_per_dict_data = data;
			}
		});
	}
}
/**********************************************************************************
*函数名称:old_new_data_format()
*功能说明:用来返回字典项的name  
*参数说明: value  当前列的值 
*返 回 值: 字典项的name  或者当前的value
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function old_new_data_format(value) {
		
		if(house_attr_dict_data){					//房屋性质字典项
			var tmpData = house_attr_dict_data;
			for(var i =0;i<tmpData.length;i++){
				if(tmpData[i].value == value){
					return  tmpData[i].name;
				}
			}
		}if(flatlet_usage_dict_data){				//房屋用途字典项
			var tmpData = flatlet_usage_dict_data;
			for(var i =0;i<tmpData.length;i++){
				if(tmpData[i].value == value){
					return  tmpData[i].name;
				}
			}
		}if(use_per_dict_data){						//使用年限字典项
			var tmpData = use_per_dict_data;
			for(var i =0;i<tmpData.length;i++){
				if(tmpData[i].value == value){
					return  tmpData[i].name;
				}
			}
		}if(real_usage_dict_data){					//土地用途字典项
			var tmpData = real_usage_dict_data;
			for(var i =0;i<tmpData.length;i++){
				if(tmpData[i].value == value){
					return  tmpData[i].name;
				}
			}
		}
	return value;
}


/**********************************************************************************
*函数名称: 根据   cbx_id--字典项中取得   获取到指定的Editor  并把它设置为combobox   
*功能说明: 设置需要字典项选择的变更项  为字典项
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function setEditorCombobox(cbx_id,row_index){
	//gethouse_attr_dict_data();
	 //判断  需要用字典项的选项 则获取editor  把editor变为字典项(combodict)
    if(cur_cbx_id == change_data_item.HOUSE_ATTR){				//购房性质
	   		var house_attr_editor = $('#div_change_detail').datagrid('getEditor', {index:row_index,field:'NEW_DATA'});
	   		var tempCombo =  $(house_attr_editor.target).combodict({  
			    code:'021',
			    width:'100%',
			    onSelect: function(rec){  
			    	house_attr_editor.target.val(rec.value);
		        }
			}); 
	}else if(cur_cbx_id == change_data_item.FLATLET_USAGE){ 	//房屋用途
		var flatlet_usage_editor = $('#div_change_detail').datagrid('getEditor', {index:row_index,field:'NEW_DATA'});
   		var tempCombo =  $(flatlet_usage_editor.target).combodict({  
		    code:'062',
		    width:'100%',
		    onSelect: function(rec){  
		    	flatlet_usage_editor.target.val(rec.value);
	        }
   		});
	}else if(cur_cbx_id == change_data_item.REAL_USAGE){		//土地用途
		var real_usage_editor = $('#div_change_detail').datagrid('getEditor', {index:row_index,field:'NEW_DATA'});
   		var tempCombo =  $(real_usage_editor.target).combodict({  
		    code:'015',
		    width:'100%',
		    onSelect: function(rec){  
		    	real_usage_editor.target.val(rec.value);
	        }
   		});
	}else if(cur_cbx_id == change_data_item.USE_PER){			//使用年限
		var use_per_editor = $('#div_change_detail').datagrid('getEditor', {index:row_index,field:'NEW_DATA'});
   		var tempCombo =  $(use_per_editor.target).combodict({  
		    code:'006',
		    width:'100%',
		    onSelect: function(rec){  
		    	use_per_editor.target.val(rec.value);
	        }
   		});
	}
    
    
}

/**********************************************************************************
*函数名称:getChangeRecordInfo
*功能说明:获取数据库中当前业务更正数据项记录
*参数说明: 
*返 回 值: 数据库中更正后数据项
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getChangeRecordInfo(){
	var result;
	$.ajax({
		url:ctx+"/houseownership/correction!getChangeRecord.action?time="+new Date(),
		dataType : 'json',
		type : 'post',
		data : {"proc_id":proc_id},
		async:false,
		success : function(data) {
			result = data;
		}
	});
	
	return result;
}


