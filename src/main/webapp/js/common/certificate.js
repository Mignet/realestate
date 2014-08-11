/*********************************************************************************
*文  件  名  称: certificate.js
*功  能  描  述: 缮证表
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Joyon
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
var proc=this.parent.proc;						//当前流程对象
var proc_id = proc.procId;						//当前流程实例ID
var CER_STATE = {
	"DONE":"059001",
	"UN_DONE":"059002"
	};
var _init_form_data;							//初始化时数据   用于判断当前页面数据是否己修改
var _cur_form_data;								//验证时数据   用于判断当前页面数据是否己修改
var app_cer_type_dict_data;							//申请人证件类型字典项数据
var app_type_dict_data;								//申请人类型字典项数据
var naturalDataGrid;							//自然信息datagrid
var holderDatagrid;								//权利人datagrid
var selectedHolderData = {};							//选中权利人的数据
var selectedNaturalData;						//选中自然信息 

/**********************************************************************************
*函数名称: 
*功能说明: js加载时运行  初始化当前页面数据
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
$(function(){
	//getApp_cer_type_dict_data();				//获取申请人证件类型字典项
	//getApp_type_dict_data();					////获取申请人类型字典项
	
	//设置文本框只读
	$(":input").attr("disabled", "disabled");
	$("#CERTIFICATE_TYPE").combodict("setValue","038005");
	$("#CERTIFICATE_TYPE").combo("enable");
	//初始化缮证界面
	//创建自然信息表
	initNaturalDatagrid();
	//创建权利人信息表
	initHolderdatagrid();
	
	//获取房地产证表其它登记信息  并加载到当前页面
//	$.ajax({
//	    dataType: 'json',
//		url:"certificate!getCertificateInfo.action",
//		data:{"proc_id":proc_id,"time":new Date()},
//		success:function(data){
//			//alert(JSOn.stringify(data));
//			//alert(JSON.stringify(data.holders[0]));
//		 	if(data){
//		 		//alert(JSON.stringify(data));
//		 		//$('#table_holders').datagrid('load', data.holders[0]);  
//		 		$('#table_holders').datagrid({
//		 			data: data.holders
//		 		        });
//		 		$('#tab_reg_info').form("load",data.regInfo);
//		 		$('#tab_land').form("load",data.naturalInfo);
//				$('#tab_house').form("load",data.naturalInfo);
//				$('#tab_fdcz').form("load",data.certificateInfo);
//		 	//alert(JSON.stringify(data.certificateInfo));
//		 		if(data.certificateInfo.CER_STATE == CER_STATE.DONE){
//		 			 $("#submit").linkbutton("disable");
//		 		    $("#cancel").linkbutton("enable");
//		 		}else{
//		 			 $("#submit").linkbutton("enable");
//			 		    $("#cancel").linkbutton("disable");
//		 		}
//		 	}
//		 	_init_form_data = $("#main_form").serializeJson(); 		//重新获取当前行页面初始化值
//		
//		}
//	});
	 document.getElementById("submit").onclick = function() {
		   submit(CER_STATE.DONE);
	   };
	   document.getElementById("cancel").onclick = function() {
		   submit(CER_STATE.UN_DONE);
	   };
	
});//初始化结束
/**********************************************************************************
*函数名称: loadForm
*功能说明: 初始化form
*参数说明: 
*返 回 值: 时间
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
function loadForm(data){
	$('#tab_land').form("load",data);
	$('#tab_house').form("load",data);
	$('#tab_certificate').form("load",data);
	
}

/**********************************************************************************
*函数名称: initNaturalDatagrid()
*功能说明: 初始化自然信息datagrid
*参数说明: 
*返 回 值: 时间
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
function initNaturalDatagrid(){
	naturalDataGrid = $('#table_natural').datagrid({
		title:'房地产信息',
		height:180,
		url:ctx+"/common/certificate!getCertificateNatural.action?time="+new Date()+"&proc_id="+proc_id,
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

		     		{field:'ck',checkbox:true},
		     		{
		     			title : '登记编 号',
		     			field : 'REG_CODE',
		     			width:80
		     			
		     		},{
		     			title : '业务类型',
		     			field : 'BUS_TYPE_NAME',
		     			width:80
		     			
		     		},{
		     			title : '证件类型',
		     			field : 'CER_TYPE',
		     			width:80,formatter:function(value){
		     				return "房屋所有权证";
		     			}
		     			
		     		},{
		     			title : '证件编号',
		     			field : 'CER_NO',
		     			width:80
		     			
		     		},{
		     			title : '房地产名称',
		     			field : 'EST_NAME',
		     			width:80
		     			
		     		},{
		     			title : '办理状态',
		     			field : 'CER_STATE',
		     			width:80,formatter:function(value){
		     				if(value=='059001'){
		     					return "己缮证";
		     				}else{
		     					return "未缮证";
		     				}
		     			}
		     			
		     		},{
		     			title : '缴费状态',
		     			field : 'TAX_STATE',
		     			width:80,formatter:function(value){
		     				if(!value){
		     					return "无税费";
		     				}
		     			}
		     			
		     		},
		     		
		     		 {
		     			hidden: true,
		     			field : 'TYPE',
		     			width:100
		     			
		     		}, {
		     			hidden: true,
		     			field : 'CODE',
		     			width:100
		     			
		     		}
		     		

		     		] ],
		     		// 表头，添加工具栏。
		     		onClickCell:function(rowIndex, field, value){
		     			if(field=="button"){
		    				$('#table_house').datagrid('selectRow',rowIndex);
		    				dowatch(this);
		    			}
		    		},
		    		onClickRow:naturalClickRow,
		     		onLoadSuccess : function(data) {
		     			//加载成功选中第一行
		     			if(data){
		     				naturalDataGrid.datagrid('selectRow',0);
		     				naturalClickRow(0);
		     			}
		     			//$('.editcls').linkbutton({text:'查看'});
		     			
		     		}
	});
}
/**********************************************************************************
*函数名称: naturalClickRow()
*功能说明: 自然datagrid row点击事件
*参数说明: 
*返 回 值: 时间
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
function naturalClickRow(rowIndex){

		
		var selectedRow = naturalDataGrid.datagrid('getSelected',rowIndex);
		
		setButtonstate(selectedRow.CER_STATE);								//设置缮证  和取消  按扭状态
		//document.write($.toJSON(selectedRow));
//		alert($.toJSON(selectedRow.holder));
		selectedNaturalData = selectedRow;									//给自然信息赋值
		selectedHolderData.total = selectedRow.holder.length;
		selectedHolderData.rows = selectedRow.holder;
		loadForm(selectedRow);
		holderDatagrid.datagrid("loadData",selectedHolderData);				//权利人Datagrid加载数据
		//alert($.toJSON(selectedRow));
}

/**********************************************************************************
*函数名称: initHolderdatagrid()
*功能说明: 初始化权利人datagrid
*参数说明: 
*返 回 值: 时间
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
function initHolderdatagrid(){
		holderDatagrid = $('#table_holders').datagrid({
		title:'权利人信息',
		//fit:true,
		//表格数据来源
		//url:'certificate!getRegPreAplPerInfo.action?lcslbid='+lcslbid+"&time="+new Date(),
		//表格每列宽度自动适应表格总宽度
		height:150,
		fitColumns: true,
		//去掉边框
		border : true,
		//是否有翻页栏
		pagination:true,
		//每页行数
		pageSize:20,
		//是否在最左增加一列显示行号的列
		rownumbers:true,
		//主键值所在行。在使用复选框时必须设置此项。
		//idField: 'user_id',
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
			{field:'HOL_NAME', title:'权利人姓名', width:100, sortable:true},
			{field:'HOL_TYPE', title:'权利人性质', width:100, sortable:true,formatter : dicts.format.app_type_format
			},
//			{field:'HOL_CER_TYPE', title:'证件类型', width:100, sortable:true ,formatter : function(value) {
//				var tmpData = app_cer_type_dict_data;
//				for(var i =0;i<tmpData.length;i++){
//					if(tmpData[i].value == value){
//						return  tmpData[i].name;
//					}
//				}
//			  }
//			},
			{field:'HOL_CER_NO', title:'证件编号', width:200, sortable:true }
		]]
	});
}

/**********************************************************************************
*函数名称: getTime()
*功能说明: 获取系统当前时间
*参数说明: 
*返 回 值: 时间
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
function getTime(){
	var myDate = new Date();
	var year = myDate.getFullYear();
	var month = myDate.getMonth()+1;
	var date = myDate.getDate(); 
	var mytime=myDate.toLocaleTimeString();     //获取当前时间
	var time = year+"-"+month+"-"+date+" "+mytime;
	return time;
}

/**********************************************************************************
*函数名称: submit()
*功能说明: 表单提交方法  需要验证 验证后提交
*参数说明: 
*返 回 值: 时间
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
function submit(cer_state){
	var reg_unit_code="";
	if(selectedNaturalData){
		 reg_unit_code = selectedNaturalData.REG_UNIT_CODE;
	}
	
	var result = true;;
	  
	   //判断验证是否通过  通过则执行下一步操作 不通过 直接return
//	   if(!validate()){
//		 return;
//	   }
	
		setButtonstate(cer_state);
	  
	   
	   //设置缮证时间
	   
	   $("#PRINT_DATE").val(getTime());
	   $("#PRINTER").val(user);
	  // $("#CER_STATE").combodict("setValue",cer_state);
	   
	   _init_form_data = $("#main_form").serializeJson(); 		//重新获取当前行页面数据初始化值
	   //var cer_state = $("#CER_STATE").combo("getValue");											//缮证状态
	   var printer=$("#PRINTER").val();																//缮证人
	   var printer_date=$("#PRINT_DATE").val();														//缮证时间
	   var certificate_type = $("#CERTIFICATE_TYPE").combo("getValue");		
	   //房地产证类型
	   var obj={
		    dataType: 'json',
			data:{"certificate.reg_unit_code":reg_unit_code,"certificate.printer":printer,"certificate.print_date":printer_date,"certificate.cer_state":cer_state,"certificate.certificate_type":certificate_type,"proc_id":proc_id,"time":new Date()},
		   // data:$("#cer").serialize(),
			url:'certificate!updateCertificate.action',
			contentType: "application/json; charset=GBK",
			success:function(data){
					if (data.success) {
					top.$.messager.alert('正确提示',data.tipMessage,'info',function(){
					
					});				
					} else {
						top.$.messager.alert('错误提示',data.errorMessage,'error');
					}
		  },error:function(){
			  result = false;
		  }
	  };
	  $.ajax(obj);	
	  
	  return result;
}
/**********************************************************************************
*函数名称: setButtonstate
*功能说明: 设置缮证和取消按扭状态      
*参数说明: tmp_cer_state
*返 回 值: true/false
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function setButtonstate(tmp_cer_state){
	  if(tmp_cer_state==CER_STATE.UN_DONE){
		   $("#submit").linkbutton("enable");
		    $("#cancel").linkbutton("disable");
	   }else if(tmp_cer_state==CER_STATE.DONE){
		   $("#submit").linkbutton("disable");
		    $("#cancel").linkbutton("enable");
	   }
}
/**********************************************************************************
*函数名称: validate
*功能说明: 验证页面上的非空  及数据格式   
*参数说明: 
*返 回 值: true/false
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
	//提示消息 
	var message;
		//提交时才判断当前页面是否修改
		if(!v_flag){
//			var cer_state = $("#CER_STATE").combo("getValue");	
//			if(cer_state != CER_STATE.DONE){
//				message = '请先缮证后再提交!';
//				result.message = message;
//				return result;
//			}
//			 _cur_form_data = $("#main_form").serializeJson(); 
//				var r = equal(_init_form_data,_cur_form_data);
//				if(!r){
//					message = '数据己修改！请先保存后提交！';
//					result.message = message;
//					return result;
//			}
		}else{
			 _init_form_data = $("#main_form").serializeJson(); 		//重新获取当前行页面数据初始化值
		}
		 
		
	result.result = true;
	return result;
}


