/*********************************************************************************
*文  件  名  称: testpaper.js
*功  能  描  述: 组卷表
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Joyon
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
var flag;
var zjbid;
var state={state1:"已组卷",state2:"未组卷"};
var proc=this.parent.proc;
var proc_id = proc.procId;
var reg_code;
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
   //禁用取消按钮
   document.getElementById("submit").onclick = function() {
	   submit();
   };
   //初始化缮证信息
   getPaper();
});
/**********************************************************************************
*函数名称: 意见保存更新操作
*功能说明: 意见保存更新操作
*参数说明: 
*返 回 值: 返回当前时间
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function submit(){
		var result = true; 
	   $("#submit").linkbutton("disable");
	    $("#cancel").linkbutton("enable");
	   $("#arrange_state").val(state.state1);
	    var paraData={
	    		"proc_id":proc_id,
	    		"testpaper.arrange_time":getTime(),
	    		"testpaper.arranger":user,
	    		"testpaper.arrange_state":state.state1
	    };
	       $.ajax({
	    	   dataType: 'json', 
	    	   data:paraData,
	    	   contentType:"application/json; charset=GBK",
	    	   url:ctx+"/common/certificate!saveFileMess.action",
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
*函数名称: 获取系统当前时间
*功能说明: 获取系统当前时间
*参数说明: 
*返 回 值: 返回当前时间
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
*函数名称: 取消组卷操作操作
*功能说明: 取消组卷操作操作
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function cancelSave() {	
	 $("#cancel").linkbutton("disable");
	 $("#submit").linkbutton("enable");
	 $("#arrange_state").val(state.state2);
	 var paraData={
	    		"proc_id":proc_id,
	    		"testpaper.arrange_time":getTime(),
	    		"testpaper.arranger":user,
	    		"testpaper.arrange_state":state.state2
	    };
	var obj = {
		dataType : 'json',
		data:paraData,
		url:ctx+"/common/certificate!saveFileMess.action",
		contentType : "application/x-www-form-urlencoded; charset=GBK",
		success : function(data) {
			if (data.success) {
				top.$.messager.alert('正确提示',"组卷取消成功", 'info',
						function() {	
							$('#tab_zj').form("load",data);
						});
			} else {
				top.$.messager.alert('错误提示', data.errorMessage, 'error');
			}
		}
	};
	$.ajax(obj);
};

/**********************************************************************************
*函数名称: 初始化缮证表
*功能说明: 初始化缮证表
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getPaper(){
	$.ajax({
	    dataType: 'json',
		url:ctx+"/common/certificate!getFileByid.action?proc_id="+proc_id+"&time="+new Date(),
		success:function(data){
		 	if(data){
		 		if(data.sign){
		 			$("#arranger").val(user);
		 		    $("#arrange_time").val(getTime());
		 		    $("#arrange_state").val(state.state2);
		 		    return;
		 		}
		 		if(data.arrange_time != null){
   			 		var arrange_time=data.arrange_time.substr(0,data.arrange_time.length-2);
   			 	    $("#arrange_time").val(arrange_time);
   			 	};
		 		$("#arranger").val(data.arranger);
		 		$("#arrange_state").val(data.arrange_state);
		 		if(data.arrange_state =="未组卷"){
			 		$("#cancel").linkbutton("disable");
   			       $("#submit").linkbutton("enable");
		 		}else{
	 			
			 		$("#cancel").linkbutton("enable");
   			        $("#submit").linkbutton("disable");
		 		}
	 		}else{
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
	 var result ={
	   			result:true,
	   			message:'',
	   			page_name:'受理通知书'
	   		}
	 //如果是保存  先改变状态
	 if(v_flag){
		 $("#arrange_state").val(state.state1);
	 }
	if( $("#arrange_state").val()==state.state2){
		message = '请先组卷后再提交到下一环节！';
//		top.$.messager.alert('提示', '请先组卷后再提交到下一环节！', 'info',
//				function() {
//					
//				});	
		result.message=message;
		 result.result=false; 
		 return result; 
	}
	
	return result; 
	
}

