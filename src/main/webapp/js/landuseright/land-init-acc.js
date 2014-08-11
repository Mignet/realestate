//初始化加载.	
var proc;
proc=this.parent.proc;

var serialNumber ={num1:"登记编号",num2:"归档号",num3:"房地产证号"};

var activName = proc.activName;
//var activName = '${activName}';
//var activName = "初审";
//var proc_id =  '${procId}';
var proc_id = proc.procId;
//var proc_id = 1000016990;
var proc_node=proc
var _init_form_data;							//初始化时数据   用于判断当前页面数据是否己修改
var _cur_form_data;								//验证时数据   用于判断当前页面数据是否己修改
var procNode = {
		//受理
		accepted: enumdata.ACCEPTED,
		//初审
		examine : enumdata.EXAMINE,
		//复审
		reexamine : enumdata.REEXAMINE,
		//核准
		approved : enumdata.APPROVED,
		//公告
		bulletin : enumdata.BULLETIN,
		//初步审查
		initialexamine : enumdata.INITIALEXAMINE,
		//初步审核
		initialaudit : enumdata.INITIALAUDIT,
		//初步审定
		initialvalidation: enumdata.INITIALVALIDATION,
		//收费
		charge : enumdata.CHARGE,		
		//缮证
		certificate : enumdata.CERTIFICATE,
		//发文
		posting : enumdata.POSTING,
		//归档
		file: enumdata.FILE,

	};

$(document).ready(function() {

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
			     		//每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
			     		{
			     			title : '宗地号',
			     			field : 'PARCEL_CODE',
			     			width:80
			     			
			     		}, {
			     			title : '登记单元类型',
			     			field : 'TYPE',formatter : dicts.format.reg_unit_type_format,
			     			width:100
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
			     			field : 'CODE',
			     			
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
			     			
			     			//为false时表示异议登记获取查封信息
			     			_init_form_data = $("#attach").serializeJson(); 
			     		}
		});
	
	//创建申请人信息表
	var userDataGrid = $('#table_user').datagrid({
		//fit : true,
		title:'申请人信息',
		height:240,
		// 表格数据来源
		url :ctx+"/landuseright/landinitialreg!getAppMessage.action?time="+new Date()+"&proc_id="+proc_id,
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
			field : 'app_type'
			
		}, {
			title : '证件类型',
			field : 'app_cer_type'
			
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
			$('#user_edit').linkbutton('enable');
			$('#user_delete').linkbutton('enable');
		},
		onLoadSuccess : function() {
			//加载完毕禁用“编辑”、“删除”按钮
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
			getPreRegMess();
			getuseright();
			setState(activName);
			_init_form_data = $("#acceptance").serializeJson();
		}
		
	});
	
	/**********************************************************************************
	*函数名称: getSelected
	*功能说明: 选择表格中某一行的数据。
	*参数说明: 
	*返 回 值: 
	*函数作者: xuzz
	*创建日期: 2014-03-17
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
	};

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
				height : 700,
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
	
	
	/**********************************************************************************
	*函数名称: doAdd
	*功能说明: 新增申请人
	*参数说明: 
	*返 回 值: 
	*函数作者: xuzz
	*创建日期: 2014-03-17
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
	};

	/**********************************************************************************
	*函数名称: doEdit
	*功能说明: 编辑申请人
	*参数说明: 
	*返 回 值: 
	*函数作者: xuzz
	*创建日期: 2014-03-17
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
	};

	/**********************************************************************************
	*函数名称: doDelete
	*功能说明: 删除申请人
	*参数说明: 
	*返 回 值: 
	*函数作者: xuzz
	*创建日期: 2014-03-17
	*修改历史: 
	***********************************************************************************/
	function doDelete() {
		var row = userDataGrid.datagrid('getSelected');
		top.$.messager.confirm('确认', '确定要删除申请人名称为[' + row.app_name + ']？', function(
				result) {
			if (result) {
				$.ajax({
					url : ctx+"/landuseright/landinitialreg!deleteApplicant.action?time="+new Date(),
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
	};
	_init_form_data = $("#acceptance").serializeJson();

});

/**********************************************************************************
*函数名称: setState
*功能说明: 设置页面状态
*参数说明: 流程定义名称activName
*返 回 值: 
*函数作者: xuzz
*创建日期: 2014-03-17
*修改历史: 
***********************************************************************************/
function setState(activName) {

	if(activName == procNode.initialexamine){
		$("#djd").combo('disable');
		$(".reg").attr("disabled", "disabled");
		
		$("#fdczfj").removeAttr("disabled"); 	
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
	
		
	};
	if (!(activName == procNode.accepted)&&!(activName == procNode.initialexamine)) {
		$("#djd").combo('disable');
		$(":input").attr("disabled", "disabled");
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		

	}
	;
	/*if (activName == procNode.string4) {
		$('#user_edit').linkbutton('enable');
		$('#user_delete').linkbutton('enable');

		$('#user_add').linkbutton('enable');

	}*/
	if(activName != procNode.accepted){
		
		$(".initreg").css({display:"block"});
	}
	_init_form_data = $("#acceptance").serializeJson();
}
/**********************************************************************************
*函数名称: GetQueryString
*功能说明:获取地址栏参数
*参数说明: 从地址栏传过来的参数
*返 回 值: 
*函数作者: xuzz
*创建日期: 2014-03-17
*修改历史: 
***********************************************************************************/
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
/**********************************************************************************
*函数名称: submit
*功能说明: 保存操作
*参数说明: 
*返 回 值: 
*函数作者: xuzz
*创建日期: 2014-03-17
*修改历史: 
***********************************************************************************/
function submit(){
	var data;
	if(activName == procNode.initialexamine){
		saveFdccfj();	
		//saveOwnership();
	};
	return true;
}

/**********************************************************************************
*函数名称: saveFdccfj
*功能说明: 保存房地产证附记到缮证表中
*参数说明: 
*返 回 值: 
*函数作者: xuzz
*创建日期: 2014-03-17
*修改历史: 
***********************************************************************************/
function  saveFdccfj(){
	var result;
	 var fdczfj = $("#fdczfj").val();
	 if($.trim(fdczfj).length==0){
			top.$.messager.alert('提示', '请输入房地产证附记！', 'info',
					function() {
						
					});	
			return;
		}
	 
		$("#fdczfj1").val(fdczfj);
		
	 $.ajax({
	   		dataType:'json',
	   		url:ctx+"/landuseright/landinitialreg!saveCerRemark.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"excursus":fdczfj},
	   		data:$("#acceptance").serialize(),
	   		success:function(data){
	   			 result=data;
	   			
			 	if(data){
			 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
					});	
			 		return result;
			 	}else {
					top.$.messager.alert('保存失败提示',data.errorMessage,'error');
				}
	   		}
	   	});  
	     
}

/**********************************************************************************
*函数名称: getPreRegMess
*功能说明: 获取从受理前置窗口传递的登记信息
*参数说明: 
*返 回 值: 
*函数作者: xuzz
*创建日期: 2014-03-17
*修改历史: 
***********************************************************************************/
function getPreRegMess(){
	$.ajax({
		    dataType: 'json',
			url:ctx+"/landuseright/landinitialreg!getRegMessage.action?time="+new Date()+"&proc_id="+proc_id,
			success:function(data){
			 	if(data){
			 		$("#djbh").val(data.RegInfo.REG_CODE);
			 		$("#djd").combodict('setValue',data.RegInfo.REG_STATION);
			 		$("#ywms").val(data.RegInfo.PROC_NAME);			 					 			
			 	   //$("#xmmc").val(data.PRO_NAME);	
			 		$("#djlx").combodict('setValue',data.RegInfo.REG_TYPE);
			 		$("#fdczfj").text(data.excursus);
			 	}
			 	_init_form_data = $("#acceptance").serializeJson();
			}
	
			
		});
}

/**********************************************************************************
*函数名称: saveOwnership
*功能说明: 保存土地使用权相关登记信息
*参数说明: 
*返 回 值: 
*函数作者: xuzz
*创建日期: 2014-03-17
*修改历史: 
***********************************************************************************/
function saveOwnership(){
	 //alert($("#acceptance").serialize());
	 $.ajax({
	   		dataType:'json',
	   		url:ctx+"/landuseright/landinitialreg!saveUseright.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:$("#acceptance").serialize(),
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		top.$.messager.alert('保存成功提示',"保存成功！",'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',"保存失败！",'error');
				}
	   		}
	   	});  
}

/**********************************************************************************
*函数名称: getuseright
*功能说明: 获取土地使用权登记信息  
*参数说明: 
*返 回 值: 
*函数作者: xuzz
*创建日期: 2014-03-17
*修改历史: 
***********************************************************************************/
function getuseright(){
	$.ajax({
	    dataType: 'json',
		url:ctx+"/landuseright/landinitialreg!getUseright.action?time="+new Date()+"&proc_id="+proc_id,
		success:function(data){
		 	if(data){
		 		//alert(data.useright_type);
		 		//$("#get_price").val(data.get_price);
		 		//$("#use_limit").val(data.use_limit);
		 		//$("#use_type").val(data.useright_type);
		 		if(data.use_limit){
		 			
		 			
		 			$("#use_limit").numberbox('setValue',data.use_limit);
		 		}if(data.get_price){
		 			
		 			
		 			$("#get_price").numberbox('setValue',data.get_price);
		 		}
		 		if(data.start_date){
		 			var start_date = data.start_date;
		 			
		 			$("#start_date").datebox('setValue',start_date.substr(0,10));
		 		}
		 		
		 		if(data.end_date){
		 			var end_date = data.end_date;
		 			$("#end_date").datebox('setValue',end_date.substr(0,10));	
		 			
		 		}
		 		$("#land_use").combodict('setValue',data.land_use);
		 		$("#use_type").combodict('setValue',data.useright_type);
		 		
		 		
		 	 
		 	}
		 	_init_form_data = $("#acceptance").serializeJson();
		}
	
	
	});
}
/**********************************************************************************
*函数名称: 页面校验方法
*功能说明: 验证页面上的非空  及数据格式   
*参数说明: flag 1代表保存 提交不传值 用来区分保存和提交
*返 回 值: obj  result(true通过  false不通过) message(消息)  page_name(当前页面名字)
*函数作者: xuzz
*创建日期: 2014-03-17
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
	if(activName==procNode.initialexamine){
		message = "房地产证附记！";
		var vEXCURSUS = new tt.Field(message,"fdczfj");  
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
	if(activName==procNode.initialexamine){
		var land_use=$("#land_use").combo('getValue');
		//var house_attr = $("#HOUSE_ATTR").combo('getValue');
		if($.trim(land_use).length==0){
			message = '请选择土地用途！';
			result.message=message;
			 result.result=false;
			 return result;
		}
		var get_price=$("#get_price").numberbox('getValue');
		if($.trim(get_price).length==0){
			message = '请输入取得价格！';
			result.message=message;
			 result.result=false;
			 return result;
	
		}
		var use_type = $("#use_type").combo('getValue');
		
		if($.trim(use_type).length==0){
			message = '请选择使用权类型！';
			result.message=message;
			 result.result=false;
			 return result;

		}
		var use_limit = $("#use_limit").numberbox('getValue');
		if($.trim(use_limit).length==0){
			message = '请输入使用期限！';
			result.message=message;
			 result.result=false;
			 return result;
		}
		var start_date = $("#start_date").datebox('getValue');
		if($.trim(start_date).length==0){
			message = '请输入起始日期！';
			result.message=message;
			 result.result=false;
			 return result;
		}
		var end_date = $("#end_date").datebox('getValue');
		if($.trim(end_date).length==0){
			message = '请输入终止日期！';
			result.message=message;
			 result.result=false;
			 return result;
		}
		

	}
	/*if(v_flag){
		_init_form_data = $("#acceptance").serializeJson(); 
	}*/
	
	//判断数据项是否己修改  如果己修改  则提示是否保存未保存数据
	//_cur_form_data = $("#acceptance").serializeJson(); 
	
	//var r = equal(_init_form_data,_cur_form_data);
	/*if(!r){
		var flag= 0 ;//用来确认 是否用户已经点击放弃保存  未点击  代表是在外面调用     返回false
		message = '数据己修改！是否放弃保存？';
		 if(flag){
			 
		 }else{
			 result.message=message;
			 result.result=false; 
		 }
		 return result;
	}*/
	return result;
}

/**********************************************************************************
*函数名称: pageDataIsChange
*功能说明: 判断当前页面数据是否已经修改
*参数说明: 
*返 回 值: 修改返回true   未修改返回false
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
/*function pageDataIsChange(){
	if(proc_node == procNode.ACCEPTED || proc_node == procNode.string1){
		_cur_form_data = $("#main_form").serializeJson(); 
		
		var r = equal(_init_form_data,_cur_form_data);
		//如果相等返回  页面数据未修改  返回false
		if(r){
		  return false;
		}
	}
	return true;
}*/






