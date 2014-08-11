/*********************************************************************************
*文  件  名  称: pigeonhole.js
*功  能  描  述: 归档表
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Joyon
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
var flag;
var serialNumber ={num1:"登记编号",num2:"归档号"};
var arch_no;										//归档号
var proc=this.parent.proc;						//父窗口的流程属性
var proc_node = proc.activName;					//流程节点
var proc_id = proc.procId;
var _init_form_data;							//初始化时数据   用于判断当前页面数据是否己修改
var _cur_form_data;								//验证时数据   用于判断当前页面数据是否己修改
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
	   //submit按扭绑定事件
	   document.getElementById("submit").onclick = function() {
			   submitConf();
		   };
	   getPige();
	   $("#cancel").linkbutton("disable");
});
/**********************************************************************************
*函数名称: submitConf()
*功能说明: 提交确认  先校验页面数据   通过  再校验是否确认归档
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
function submitConf(){
	_init_form_data = $("#main_form").serializeJson(); 
	var result = validate()
 	 if(!result.result){
 		top.$.messager.alert('正确提示',result.message, 'info',
					function() {	
				
					});
 		 return;
 	 }
// 	if(!arch_handler_no || arch_handler_no.length<1){
//		 alert("归档人编号不能为空！");
//		 return;
//		 
//	 }
	$.messager.confirm('归档确认', '确认信息无误 归档？', function(r){
		if (r) {
			$("#submit").linkbutton("disable");
		    $("#cancel").linkbutton("disable");
		    submit();
		}
	});
}    
/**********************************************************************************
*函数名称: submitConf()
*功能说明: 意见保存更新操作
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
 function submit(){
	 var result = true;
    $("#arch_handler_no").attr("disabled","disabled");
    $("#arbox_code").attr("disabled","disabled");
 	var data= {
 		"proc_id":proc_id,
 		"pigeonhole.arch_handler":$("#arch_handler").val(),
 		"pigeonhole.arch_no":$("#arch_no").val(),
 		"pigeonhole.arch_date":$("#arch_date").val(),
 		"pigeonhole.transfer":$("#transfer").val(),
 		"pigeonhole.transfer_date":$("#transfer_date").val(),
 		"pigeonhole.arch_handler_no":$("#arch_handler_no").val(),
 		"pigeonhole.arbox_code":$("#arbox_code").val()
 	};
      $.ajax({
    	   dataType: 'json', 
    	   data:data,
    	   contentType:"application/json; charset=GBK",
    	   url:ctx+"/common/certificate!savePigeoMess.action",
    	   success : function(data) {
	   			if (data.success) {
	   				top.$.messager.alert('正确提示', data.tipMessage, 'info',
	   						function() {	
	   					
	   					$('#tab_zj').form("load",data);
	   						});
	   			} else {
	   				top.$.messager.alert('错误提示', data.errorMessage, 'error');
	   			}
   			},error:function(){
   				result = false;
   			}
    	  
       });	     
      return result;
};
/**********************************************************************************
*函数名称: cancelSave()
*功能说明: 取消操作 
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  	  
function cancelSave(){
		
	$("#arch_handler").val(user);
	$("#arch_date").val(getTime());
	$("#arch_handler1").val(user);
	$("#arch_no1").val($("#arch_no").val()); 
	$("#arch_date1").val(getTime()); 
	$("#lcslbid").val(lcslbid);
	$("#transfer1").val($("#transfer").val()); 
	$("#transfer_date1").val($("#transfer_date").val());
  
	  var data= {
	 		"proc_id":proc_id,
	 		"pigeonhole.arch_handler":$("#arch_handler").val(),
	 		"pigeonhole.arch_no":$("#arch_no").val(),
	 		"pigeonhole.arch_date":$("#arch_date").val(),
	 		"pigeonhole.transfer":$("#transfer").val(),
	 		"pigeonhole.transfer_date":$("#transfer_date").val(),
	 		"pigeonhole.arch_handler_no":$("#arch_handler_no").val(),
	 		"pigeonhole.arbox_code":$("#arbox_code").val()
	 	};
	  $.ajax({
		   dataType: 'json', 
		   data:$("#userForm").serialize(),
		   contentType:"application/json; charset=GBK",
		   url:ctx+"/common/certificate!savePigeoMess.action",
		   success : function(data) {
			if (data.success) {
				top.$.messager.alert('正确提示',"归档取消成功", 'info',
						function() {	
					
					$('#tab_zj').form("load",data);
						});
			} else {
				top.$.messager.alert('错误提示', data.errorMessage, 'error');
	   			}
	   		}
	    	  
	 });	     
}	  
/**********************************************************************************
*函数名称: getTime()
*功能说明: 获取系统当前时间
*参数说明: 
*返 回 值: 当前time 字符串
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
*函数名称: getTime()
*功能说明: 获取系统当前时间
*参数说明: 
*返 回 值: 当前time 字符串
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
//function checkarch_no(){
//	$.ajax({
//		    dataType: 'json',
//			url:"gdDelegate/getFileByreg_code.run?lcslbid="+lcslbid+"&random="+Math.random(),
//			success:function(data){
//				//alert(data);
//			 	if(data){
//			 		return;
//			 	}else{
//			 		//自动获取归档编号
//			   		$.ajax({
//			   		    dataType: 'json',
//			   			url:"idenitifierDelegate/getSerialNumber.run?serialName="+serialNumber.num2+"&random="+Math.random(),
//			   			success:function(data){
//			   			 	if(data){
//			   			 		//alert(data);
//			   			 		$("#arch_no").val(data.serialNumber);
//			   			 	};
//			   			}
//			   		});
//			 	}
//				$('#userForm').form("load",data);
//			}
//		});	
//}
		
/**********************************************************************************
*函数名称: getPige()
*功能说明: 获取归档信息
*参数说明: 
*返 回 值: 无返回值   给当前表单数据赋值 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
function getPige(){
	 //查询归档信息是否存在
  	$.ajax({
   		    dataType: 'json',
   		    url:ctx+"/common/certificate!getPigeByid.action?proc_id="+proc_id+"&time="+new Date(),
   			success:function(data){
   				//alert("获取归档信息");
   				//alert(JSON.stringify(data));
   				
   				if(data.transfer_date != null){
   					var transfer_date=data.transfer_date;//.substr(0,data.transfer_date.length-2);
   			 	    $("#transfer_date").val(transfer_date);
   				};
   				if(data.arch_date != null){
   					var arch_date=data.arch_date;//.substr(0,data.arch_date.length-2);
   			 	    $("#arch_date").val(arch_date);
   				}else{
   					$("#arch_date").val(getTime());
   				};
   				if(data.arch_handler){
   					$("#arch_handler").val(data.arch_handler);
   				}else{
   					$("#arch_handler").val(user);
   				}
   				if(data.arbox_code){
   					$("#arbox_code").val(data.arbox_code);
   					$("#submit").linkbutton("disable");
   				    $("#cancel").linkbutton("disable");
   				}else{
   					$("#arbox_code").removeAttr("disabled");
   				}
   				if(data.arch_handler_no){
   					$("#arch_handler_no").val(data.arch_handler_no);
   				}else{
   					
   					$("#arch_handler_no").removeAttr("disabled");
   				}
   				   $("#arch_no").val(data.arch_no);
	   			   $("#transfer").val(data.transfer);
	   			   
	   			   $("#reg_code").val(data.reg_code);
	   			_init_form_data = $("#main_form").serializeJson(); 
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
	  var result ={
	   			result:true,
	   			message:'',
	   			page_name:'受理通知书'
	   		}
	//校验归档号是澡已经填好
	var arbox_code =  $.trim($("#arbox_code").val());
	 if(!arbox_code || arbox_code.length<1){
		 message = "归档盒编号不能为空！";
		//alert("归档盒编号不能为空！");
		 result.message=message;
		 result.result=false; 
		 return result;
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
*函数名称: pageDataIsChange
*功能说明: 判断当前页面数据是否已经修改
*参数说明: 
*返 回 值: 己修改返回true   未修改返回false
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function pageDataIsChange(){
	_cur_form_data = $("#main_form").serializeJson(); 
	
	var r = equal(_init_form_data,_cur_form_data);
	//如果不相等返回  页面数据己修改  返回true
	if(!r){
	  return true;
	}
	return false;
}









