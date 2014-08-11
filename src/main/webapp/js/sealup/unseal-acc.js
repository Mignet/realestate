//初始化加载.	
var proc=this.parent.proc;
//var serialNumber ={num1:"登记编号",num2:"归档号",num3:"房地产证号"};
//
var proc_node = proc.activName;
var proc_id = proc.procId;
var reg_code;       //登记编号
var _init_form_data;							//初始化时数据   用于判断当前页面数据是否己修改
var _cur_form_data;								//验证时数据   用于判断当前页面数据是否己修改
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
var attachDataGrid;
var houseDataGrid;
$(function() {
	houseDataGrid = $('#table_house').datagrid({
		title:'房地产信息',
		height:240,
		url:ctx+"/mortgage/morsetup!getHouseInfo.action?time="+new Date()+"&proc_id="+proc_id,
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
		     		onClickRow : function(rowIndex, field, value) {
		     			if(field=="button"){
		    				$('#table_house').datagrid('selectRow',rowIndex);
		    				dowatch(this);
		    			}
		     		},
		     		onLoadSuccess : function() {
		     			$('.editcls').linkbutton({text:'查看'});
		     		}
	});
	

	 getPreRegMess();
	 //getAttach();
	 getUnAttach();
	//设置权限状态
	setState(proc_node);
	_init_form_data = $("#attach").serializeJson(); 
	document.getElementById("submit").onclick = function() {
		submit();
    };
	
	
});


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

//获取查封,或者轮候查封等信息
function setAttachInfo(url,title)
{
	attachDataGrid = $('#table_attach').datagrid({
		title:title,
		height:240,
		url:url,//ctx+"/sealup/sealup!getAttachByRegcode.action?time="+new Date()+"&reg_code="+result,
		// 表格每列宽度自动适应表格总宽度
		autoRowHeight : true,
		fitColumns : true,
		// 去掉边框
		border : true,
		checkbox:'CK',
		idField: 'REG_CODE',
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
		singleSelect : false,
		
		columns : [ [
		     		// 每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。

		     		 {field:'ck',checkbox:true},
		     		{
		     			title : '查封机关',
		     			field : 'DIS_OFF',
		     			width:80
		     			
		     		}, {
		     			title : '查封文号',
		     			field : 'DIS_NO',
		     			width:100
		     			
		     		},
		     		{
		     			title : '法律文书',
		     			field : 'LAW_DOC',
		     				width:100
		     		}, 
		     		{
		     			title : '被查封人',
		     			field : 'LIM_HOLDER',
		     			width:50
		     			
		     		}, 
		     		{
		     			title : '送达人',
		     			field : 'SERVICE_NAME',
		     			width:50
		     			
		     		},  {
		     			title : '联系方式',
		     			field : 'DIS_PER_TEL',
		     			width:100
		     			
		     		},
		     		{
		     			title : '操作',		     		
		     			field:'button',
		     			formatter:function(value,rec){
		     				 var btn = '<a class="watch" onclick="" href="javascript:void(0)">查看</a>';  
		                     return btn;
		     				;}
		     		}
		     		] ],
		     		// 表头，添加工具栏。
		     		onClickRow : function(rowIndex, field, value) {
		     			if(field=="button"){
		    				$('#table_attach').datagrid('selectRow',rowIndex);
		    				dowatchAttach(this);
		    			}
		     		},
		     		onLoadSuccess : function() {
		     			$('.watch').linkbutton({text:'查看'});
		     			//为false时表示异议登记获取查封信息
		     			_init_form_data = $("#attach").serializeJson(); 
		     		}
	});
}

function dowatchAttach()
{
	var row = attachDataGrid.datagrid('getSelected');
	openInTopWindow({
		// 窗口元素的id
		id : 'edit_user_win',
		// 窗口iframe的src
		src : ctx+'/jsp/personalOffice/attach/attachInfo.jsp',
		// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
		destroy : true,
		// 窗口标题
		title : '查看信息',
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
	*函数名称: setState
	*功能说明: 设置状态
	*参数说明: 
	*返 回 值: 无
	*函数作者: xuzz
	*创建日期: 2014-04-08
	*修改历史: 
	***********************************************************************************/
function setState(proc_node) {

	
	$(".attach_tab").attr("disabled", "disabled");	
	if(proc_node == state1.string1){
		//$("#REG_STATION").combo('disable');
		//$(":input").attr("disabled", "disabled");
		$(".edit_table").attr("disabled", "disabled");	
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		
	}
	if (!(proc_node == state1.string0)&&!(proc_node == state1.string1)) {
		//$(".attach_tab").attr("disabled", "disabled");	
		$("#REG_STATION").combo('disable');
		$(":input").attr("disabled", "disabled");
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');
		
		$('#user_add').linkbutton('disable');
		//$("#mort_type").combo('disable');
		$(".com").combo('disable');
	}
	
	if (proc_node == state1.string4) {
		$('#user_edit').linkbutton('enable');
		$('#user_delete').linkbutton('enable');
		$(".edit_table").attr("disabled", "disabled");
		$('#user_add').linkbutton('enable');

	}
	_init_form_data = $("#attach").serializeJson(); 
}
/**********************************************************************************
*函数名称: submit
*功能说明: 保存操作
*参数说明: 
*返 回 值: 无
*函数作者: xuzz
*创建日期: 2014-04-08
*修改历史: 
***********************************************************************************/
function submit(){
		saveRegInfo();			
		//saveAttach();
}
/**********************************************************************************
*函数名称: saveRegInfo
*功能说明: 登记信息保存更新操作
*参数说明: 
*返 回 值: 无
*函数作者: xuzz
*创建日期: 2014-04-08
*修改历史: 
***********************************************************************************/
function saveRegInfo() {
	var reg_station = $("#REG_STATION").combo('getValue');
	var reg_type = $("#REG_TYPE").combo('getValue');
	var proc_name = $("#BUS_DETAIL").val();
	if($.trim(reg_station).length==0){
		top.$.messager.alert('提示', '请选择登记点！', 'info',
				function() {
					
				});	
		return;

	}
	  $.ajax({
	   		dataType:'json',
	   		url:ctx+"/sealup/sealup!saveRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"oivo.reg_station":reg_station,"oivo.reg_code":reg_code,"oivo.reg_type":reg_type,"oivo.proc_name":proc_name},
	   		data:$("#add_app_form").serialize(),
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
/**********************************************************************************
*函数名称: getPreRegMess
*功能说明: 获取从受理前置窗口传递的登记信息
*参数说明: 
*返 回 值: 无
*函数作者: xuzz
*创建日期: 2014-04-08
*修改历史: 
***********************************************************************************/
function getPreRegMess(){
	$.ajax({
		    dataType: 'json',
			url:ctx+"/sealup/sealup!getRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
			success:function(data){
			 	if(data){
			 		//alert(JSON.stringify(data));
			 		$("#REG_CODE").val(data.REG_CODE);
			 		$("#REG_STATION").combodict("setValue",data.REG_STATION);
			 		$("#BUS_DETAIL").val(data.PROC_NAME);
			 		$("#REG_TYPE").combodict("setValue",data.REG_TYPE);
			 		reg_code = data.REG_CODE;
			 		var url=ctx+"/sealup/sealup!getUnAttachByRegcode.action?time="+new Date()+"&reg_code="+reg_code+"&attachType="+enumdata.unattach;;
			 		setAttachInfo(url,"查封信息");
			 	}
				}
		});
	
}

/**********************************************************************************
*函数名称: getUnAttach
*功能说明: 获取解封登记信息
*参数说明: 
*返 回 值: 无
*函数作者: xuzz
*创建日期: 2014-04-08
*修改历史: 
***********************************************************************************/
function getUnAttach(){
	$.ajax({
	    dataType: 'json',
		url:ctx+"/sealup/sealup!getAttachById.action?time="+new Date()+"&proc_id="+proc_id,
		success:function(data){
		 	if(data){
		 		$("#redis_off").val(data.dis_off);
		 		$("#redis_no").val(data.dis_no);
		 		$("#relaw_doc").val(data.law_doc);
		 		$("#reservice_name").val(data.service_name);
		 		if(data.dis_type)
		 		{
		 			$("#redis_type").combodict('setValue',data.dis_type);
		 			$("#redis_type").combodict({value:data.dis_type,onChange: selectChange});
		 		}
		 		$("#redis_per_tel").numberbox('setValue',data.dis_per_tel);
		 		$("#reservice_name").val(data.service_name);
		 		$("#reworkid").val(data.workid);
		 		if(data.dis_date)
		 		{
		 			$("#redis_date").datebox('setValue',data.dis_date.substr(0,data.dis_date.length-11));	
		 		}
		 		if(data.service_date){
		 			$("#reservice_date").datebox('setValue',data.service_date.substr(0,data.service_date.length-11));	
		 		}
		 	}
		 	else
		 	{
		 		$("#redis_type").combodict('setValue',enumdata.unattach);
	 			$("#redis_type").combodict({value:enumdata.unattach,onChange: selectChange});
		 	}
			}
	});
}


/**********************************************************************************
*函数名称: selectChange
*功能说明: 点击下拉框事件
*参数说明: 
*返 回 值: 
*函数作者: xuzz
*创建日期: 2014-04-09
*修改历史: 
***********************************************************************************/
function selectChange()
{
	var url;
	var title;
	//轮候查封转查封
	if($("#redis_type").combodict("getValue")==enumdata.unchattach)
	{
		url=ctx+"/sealup/sealup!getUnAttachByRegcode.action?time="+new Date()+"&reg_code="+reg_code+"&attachType="+enumdata.unchattach;
		title="轮候查封信息";
	}
	//解封
	else if($("#redis_type").combodict("getValue")==enumdata.unattach)
	{
		url=ctx+"/sealup/sealup!getUnAttachByRegcode.action?time="+new Date()+"&reg_code="+reg_code+"&attachType="+enumdata.unattach;
		title="查封信息";
	}
	//解除轮候查封
	else if($("#redis_type").combodict("getValue")==enumdata.unreattach)
	{
		url=ctx+"/sealup/sealup!getUnAttachByRegcode.action?time="+new Date()+"&reg_code="+reg_code+"&attachType="+enumdata.unreattach;
		title="轮候查封信息";
	}
	else
	{
		setDataIsNull();
	}
	
	setAttachInfo(url,title);
}

/**********************************************************************************
*函数名称: setDataIsNull
*功能说明: 设置解封数据为空
*参数说明: 
*返 回 值: 无
*函数作者: xuzz
*创建日期: 2014-04-08
*修改历史: 
***********************************************************************************/
function setDataIsNull()
{
	$("#redis_off").val("");
		$("#redis_no").val("");
		$("#relaw_doc").val("");
		$("#reservice_name").val("");
		$("#redis_per_tel").numberbox('setValue',"");
		$("#reworkid").val("");
		$("#redis_date").datebox('setValue',"");	
		$("#reservice_date").datebox('setValue',"");	
}

/**********************************************************************************
*函数名称: saveAttach
*功能说明: 保存查封登记信息
*参数说明: 
*返 回 值: 无
*函数作者: xuzz
*创建日期: 2014-04-08
*修改历史: 
***********************************************************************************/
function saveAttach(){
	 $.ajax({
	   		dataType:'json',
	   		url:ctx+"/sealup/sealup!saveUnAttach.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:$("#add_app_form").serialize(),
	   		success:function(data){
			 	if(data){
			 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',data.errorMessage,'error');
				}
	   		}
	   	});  
}
/**********************************************************************************
*函数名称: 页面校验方法
*功能说明: 验证页面上的非空  及数据格式   
*参数说明: 
*返 回 值: 
*函数作者: xuzz
*创建日期: 2014-03-19
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
	
	//受理环节验证
	if(proc_node==state1.string0){
		var land_use=$("#land_use").val();
		var dis_off=$("#redis_off").val();
	 	var dis_no=$("#redis_no").val();
	 	var law_doc=$("#relaw_doc").val();
	 	var lim_holder=$("#relim_holder").val();
	 	var service_name=$("#reservice_name").val();
	 	var dis_per_tel=$("#redis_per_tel").numberbox('getValue');
	 	var workid=$("#reworkid").val();
	 	var service_date=$("#reservice_date").datebox('getValue');	
		if($.trim(dis_off).length==0){
			message = '请输入解封机关！';
			result.message=message;
			 result.result=false;
			 return result;
		}
		if($.trim(dis_no).length==0){
			message = '请输入解封文号！';
			result.message=message;
			 result.result=false;
			 return result;
	
		}
		if($.trim(law_doc).length==0){
			message = '请输入法律文书！';
			result.message=message;
			 result.result=false;
			 return result;

		}
		if($.trim(service_name).length==0){
			message = '请输入送达人！';
			result.message=message;
			 result.result=false;
			 return result;
		}
		if($.trim(dis_per_tel).length==0){
			message = '请输入联系方式！';
			result.message=message;
			 result.result=false;
			 return result;
		}
		if($.trim(workid).length==0){
			message = '请输入工作证号！';
			result.message=message;
			 result.result=false;
			 return result;

		}
		if($.trim(service_date).length==0){
			message = '请输入送达日期！';
			result.message=message;
			 result.result=false;
			 return result;
		}
	}
	if(v_flag){
		_init_form_data = $("#attach").serializeJson(); 
	}
	
	//判断数据项是否己修改  如果己修改  则提示是否保存未保存数据
	_cur_form_data = $("#attach").serializeJson(); 
	
	var r = equal(_init_form_data,_cur_form_data);
	if(!r){
		var flag= 0 ;//用来确认 是否用户已经点击放弃保存  未点击  代表是在外面调用     返回false
		message = '数据己修改！是否放弃保存？';
		 if(flag){
			 
		 }else{
			 result.message=message;
			 result.result=false; 
		 }
		 return result;
	}
	return result;
}









