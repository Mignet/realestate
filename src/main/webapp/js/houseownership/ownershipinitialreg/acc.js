//初始化加载.	
var proc;
proc=this.parent.proc;

var serialNumber ={num1:"登记编号",num2:"归档号",num3:"房地产证号"};

var activName = "受理";//proc.activName;
var proc_node = "受理";//proc.activName;
//var activName = "初审";
var proc_id = 1000025915;//proc.procId;
//var proc_id = 1000016427;
//var proc_id = 1;
var _init_form_data;							//初始化时数据   用于判断当前页面数据是否己修改
var _cur_form_data;								//验证时数据   用于判断当前页面数据是否己修改
var house_attr_dict_data;						//房屋性质

var state1 = {
		string0: "受理",
		string1 : "初审",
		string2 : "复审",
		string3 : "收费",
		string4 : "核准",
		string5 : "初步审查",
		string6 : "初步审核",
		string7: "初步审定",	
		string9 : "缮证",
		string10 : "发文",
		string11: "归档",
		string12: "公告",
	};
$(function() {
	house_attr_dict_data = dicts.getDict_data_by_code('021');
	//alert($.toJSON(house_attr_dict_data));
	getPreRegMess();
	getBusownership();
	
    houseDataGrid = $('#table_house').datagrid({
		title:'房地产信息',
		height:240,
		width:785,
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
		     			
		     		}
//		     		,  {
//		     			title : '项目名称',
//		     			field : 'PRO_NAME',
//		     			width:100
//		     			
//		     		}
		     		, {
		     			hidden: true,
		     			field : 'CODE',
		     			
		     		},{
		     			field:"house_attr",
		     			width:70,
		     			title:"房屋性质",editor:{
		     				type:'combobox',
    						options:{
    							 valueField:'value',  
    							 textField:'name', 
    							 data:house_attr_dict_data,
    							 required:true 
    						}
    					}
		     		},{
		     			field:"reg_value",
		     			title:"登记价款",editor:'text'
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
		    		onClickRow:function(rowIndex){
		    			houseDataGrid.datagrid('beginEdit', rowIndex);
		    		},
		     		onLoadSuccess : function() {
		     			$('.editcls').linkbutton({text:'查看'});
		     			
		     			//为false时表示异议登记获取查封信息
		     			//_init_form_data = $("#attach").serializeJson(); 
		     		}
	});
	
	
	
		//创建申请人信息表
	var userDataGrid = $('#table_user').datagrid({
		//fit : true,
		title:'权利人信息',
		height:240,
		width:785,
		// 表格数据来源
		url :ctx+"/houseownership/initialreg!getAppMessage.action?time="+new Date()+"&proc_id="+proc_id,
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
			field : 'agent_cer_type',
				formatter : function(value) {
					if(value == '001'){
						
						return '身份证';
					};
					if(value == '002'){
						
						return '军官证';
					}
			
				}
			
		},
		
		
		{
			title : '代理人证件号码',
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
			if(activName == state1.string0){
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
	//设置权限状态
	setState(activName);
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
	;

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
	};

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
	;

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
	;

	$('#simpleform').form({
		dataType : 'json',
		url : 'appDelegate/getUserList.run',
		success : function(data) {
			userDataGrid.datagrid('loadData', data);
		}
	});
	test();

	function test() {
		var panel = $('.plui-layout').layout('panel', 'north');
		panel.panel({
			height : 143
		});
		$('.plui-layout').layout('resize');
	}
	;

});


function setState(activName) {

	if(activName == state1.string5){
		$("#djd").combo('disable');
		$(".reg").attr("disabled", "disabled");
		
		$("#fdczfj").removeAttr("disabled"); 	
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
	
		
	};
	if (!(activName == state1.string0)&&!(activName == state1.string5)) {
		$("#djd").combo('disable');
		$("#qdfs").combo('disable');
		$("#qsrq").combo('disable');
		$("#zzrq").combo('disable');
		$("#fwxz").combo('disable');
		$("#yt").combo('disable');
		$(":input").attr("disabled", "disabled");
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		

	}
	;
//	if (activName == state1.string4) {
//		$('#user_edit').linkbutton('enable');
//		$('#user_delete').linkbutton('enable');
//
//		$('#user_add').linkbutton('enable');
//
//	}
	if(activName != state1.string0){
		
		$(".initreg").css({display:"block"});
		$(".remark").css({display:"block"});
		//$("#pric").css({display:"block"});
	}
   if(activName == state1.string0){
		
		//$(".initreg").css({display:"block"});
		//$(".remark").css({display:"block"});
		$(".tt").css({display:"none"});
	}

}

// 获取地址栏参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
//判断执行的是saveDjxx()还是saveFdccfj(activName)
function submit(){
//	var result = validate();
//	if(!result.result){
//		return false;
//	}
	if(activName == state1.string0){
		return saveDjxx();		
	};
	if(activName == state1.string5){
		//saveFdccfj();	
		return saveOwnership();
	};
	
	
}

// 登记信息保存更新操作
function saveDjxx() {
	var result =true;
	var djbh = $("#djbh").val();
	var djlx = $('input[name="djlx"]').val();
	var djd = $('input[name="djd"]').val();
	var ywms = $("#ywms").val();
	var xmmc = $("#xmmc").val();
	
	
	$("#djbh1").val(djbh);
	$("#djlx1").val(djlx);
	$("#djd1").val(djd);
	$("#ywms1").val(ywms);
	$("#xmmc1").val(xmmc);
	  $.ajax({
	   		dataType:'json',
	   		url:ctx+"/houseownership/initialreg!saveRegMessage.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"oivo.reg_code":djbh,"oivo.reg_type":djlx,"oivo.reg_station":djd,"oivo.proc_name":ywms},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',data.errorMessage,'error');
				}
	   		},error:function(data){
	   			result = false;
	   		}
	   	});  
	return result;
}

//保存房地产证附记到缮证表中
function  saveFdccfj(){
	
		//$("#fdczfj1").val(fdczfj);
		
	 $.ajax({
	   		dataType:'json',
	   		url:ctx+"/houseownership/initialreg!saveCerRemark.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"oivo.excursus":fdczfj},
	   		success:function(data){
			 	if(data){
			 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',data.errorMessage,'error');
				}
	   		}
	   	});  
	     
};
//获取从受理前置窗口传递的登记信息
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
			 		/*$("#djbh").val(data.reg_code);
			 		$("#djd").combodict('setValue',data.reg_station);
			 		$("#ywms").val(data.proc_name);			 					 			
			 	    //$("#xmmc").val(data.pro_name);	
			 		//$("#djlx").val(data.reg_type);
			 		$("#djlx").combodict('setValue',data.reg_type);
			 		$("#fdczfj").text(data.excursus);*/
			 		
			 	 
			 	}
				}
		});
	
	
	
}
//保存房屋所有权相关登记信息
function saveOwnership(){
	var result = true;
	 $.ajax({
	   		dataType:'json',
	   		url:ctx+"/houseownership/initialreg!saveOwnership.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:$("#add_app_form").serialize(),
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',data.errorMessage,'error');
				}
	   		},error:function(data){
	   			result = false;
	   		}
	   	});  
	return result;
}

//获取房屋所有权登记信息
function getBusownership(){
	$.ajax({
	    dataType: 'json',
		url:ctx+"/houseownership/initialreg!getBusownership.action?time="+new Date()+"&proc_id="+proc_id,
		success:function(data){
		 	if(data){
		 		//alert(JSON.stringify(data));
		 		$("#fdczh").val(data.cer_no);
		 		$("#djjk").val(data.reg_value);
		 		$("#qdfs").combodict('setValue',data.get_mode);
		 		$("#synx").val(data.lu_term);
		 		
		 		if(data.start_date){
		 			var qsrq = data.start_date;
		 			
		 			$("#qsrq").datebox('setValue',qsrq.substr(0,10));
		 		}
		 		
		 		if(data.end_date){
		 			var zzrq = data.end_date;
		 			$("#zzrq").datebox('setValue',zzrq.substr(0,10));	
		 			
		 		}
		 		$("#fwxz").combodict('setValue',data.house_attr);
		 		$("#yt").combodict('setValue',data.house_usage);
		 		
		 		
		 	 
		 	}
		 	 //_init_form_data = $("#add_app_form").serializeJson();  	
		}
	});
}
/**********************************************************************************
*函数名称: 页面校验方法
*功能说明: 验证页面上的非空  及数据格式   
*参数说明: v_flag 1代表保存 提交不传值 用来区分保存和提交
*返 回 值: obj  result(true通过  false不通过) message(消息)  page_name(当前页面名字)
*函数作者: Joyon
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
	var message;
	
	if(activName == state1.string0){
		var rowlen  = $('#table_user').datagrid('getRows').length;
		if(rowlen == 0){
			message= '请录入申请人！';
			 result.message=message;
			 return result;
		}
		
		var djbh = $("#djbh").val();
		var djlx = $('input[name="djlx"]').val();
		var djd = $('input[name="djd"]').val();
		var ywms = $("#ywms").val();
		var xmmc = $("#xmmc").val();
		if($.trim(djlx).length==0){
			message= '请选择登记类型！';
			 result.message=message;
			 return result;

		}
		if($.trim(djd).length==0){
			message= '请选择登记点！';
			 result.message=message;
			 return result;
		}
		if($.trim(ywms).length==0){
			message= '请输入业务描述！';
			 result.message=message;
			 return result;
		}
		/*if($.trim(xmmc).length==0){
			message= '请输入项目名称！';
			 result.message=message;
			 return result;
		}*/
		
		//如果是保存  重新序列化一次 数据初始化变量
		if(v_flag){
			_init_form_data = "";//$("#main_form").serializeJson(); 
		}
		//判断数据项是否己修改  如果己修改  则提示是否保存未保存数据
		_cur_form_data = "";//$("#main_form").serializeJson(); 
		
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
	}else if(activName == state1.string5){
		// alert($("#add_app_form").serialize());
		var djjk = $("#djjk").val();
		var qdfs = $('input[name="get_mode"]').val();
		var synx = $("#synx").val();
		var qsrq = $('input[name="start_date"]').val();
		var zzrq = $('input[name="end_date"]').val();
		var fwxz = $('input[name="house_attr"]').val();
		var yt = $('input[name="house_usage"]').val();
		 var fdczfj = $("#fdczfj").val();
//		if($.trim(djjk).length==0){
//			
//			message= '请录入登记价款!';
//			 result.message=message;
//			 result.result=false;
//			 return result;
//	
//		}
		if($.trim(qdfs).length==0){
			message= '请选择取得方式！';
			 result.message=message;
			 result.result=false;
			 return result;
			
		}
//		if($.trim(synx).length==0){
//			message= '请录入使用年限！';
//			 result.message=message;
//			 result.result=false;
//			 return result;
//		}
//		if($.trim(qsrq).length==0){
//			message= '请选择起始日期！';
//			 result.message=message;
//			 result.result=false;
//			 return result;
//			
//		}
//		if($.trim(zzrq).length==0){
//			
//			message= '请选择终止日期！';
//			 result.message=message;
//			 result.result=false;
//			 return result;
//			
//		}
//		if($.trim(fwxz).length==0){
//			message= '请选择房屋性质！';
//			 result.message=message;
//			 result.result=false;
//			 return result;
//		}
//		if($.trim(yt).length==0){
//			message= '请选择用途！';
//			 result.message=message;
//			 result.result=false;
//			 return result;
//		}
		
		
		 if($.trim(fdczfj).length==0){
				message= '请输入房地产证附记！';
				 result.message=message;
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
			_init_form_data = "";//$("#main_form").serializeJson(); 
		}
	
	}
	result.result=true;
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
		_cur_form_data = "";//$("#main_form").serializeJson(); 
		
		var r = equal(_init_form_data,_cur_form_data);
		//如果不相等返回  页面数据己修改  返回true
		if(!r){
		  return true;
		}
	}
	return false;
}




