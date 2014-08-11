var ishavedata = 'true';
$(function(){
	$('#suspendForm').form({
		url :  ctx+"/workflow/workflow!modifySuspendData.action?time="+new Date(),
		success : function(data) {
			var data = $.parseJSON(data);
			if (data.success) {
				if(data.operType == enumdata.ADD)
				top.$.messager.alert('提示', data.tipMessage, 'info', function(){
					  $("#bus_id").val(data.bus_id);
					  $("#sus_id").val(data.sus_id);
					  $("#sus_no").val(data.sus_no);
					  $("#regCode").val(data.sus_no);
				});
			} else {
				top.$.messager.alert('错误', data.errorMessage, 'error', function(){});
			}
		}
	});
	
	$('#showhanguprecords').click(function(){
    	var objWindow2 = {
				//窗口元素的id
				id: 'wind_djby3',
				//窗口iframe的src
				src: ctx+'/jsp/workflow/specproc/hangup_history_tab.jsp?time='+new Date(),
				//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
				destroy: true,
				//窗口标题
				title: '挂起历史信息记录',
				//窗口宽
				width: 600,
				//窗口高
				height: 498,
				modal: true,
				//窗口中iframe的window对象的onLoad回调函数设置
				onLoad:	function(){
				   var bus_id = $('#lastbusId').val();
				   var _status = enumdata.PROCONTHEMARCH;
				   this.init({'bus_id':bus_id,'status':_status});
				}
			};
		   openInTopWindow(objWindow2);
    });
});

$.fn.datebox.defaults.formatter = function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return formatDate(y,m,d,'');
}
//
function initEditSuspendForm(procId){
      if(empty(procId))
    	  return;
      var process1 = {
 		 url: ctx + '/workflow/workflow!getBusIdAndRegCode.action?time='+new Date(),
 		 type:'post',
 		 dataType:'json',
 		 contentType:"application/x-www-form-urlencoded; charset=GBK",
 		 data:{"procId":procId,"time":new Date()},
 		 success:function(data){
 			 $('#bus_id').val(data.BUS_ID); 
	    	 if(!empty(init_add) && init_add == 'true'){
	    		 $('#reg_code').val(data.REG_CODE);
	    		 $('#sus_status').val(enumdata.PROCTOBECREATE);
	    		 detectionSuspendHistoryWithoutCreate(data.BUS_ID);
	    	 }else{
	    		 $('#sus_no').val(data.REG_CODE);
		    	 $('#lastbusId').val(data.LAST_BUS_ID);
	    		 gainRegcodeBylastbusId(data.LAST_BUS_ID);
	    		 getSuspendInfoByParam();
	    	 }
 		 }
 	  }
   $.ajax(process1);
}
//
function gainRegcodeBylastbusId(busId){
	if(empty(busId))
  	  return;
    $.ajax({
		 url: ctx + '/workflow/workflow!getRegCodeByBusId.action?time='+new Date(),
		 type:'post',
		 dataType:'json',
		 contentType:"application/x-www-form-urlencoded; charset=GBK",
		 data:{"busId":busId,"time":new Date()},
		 success:function(data){
	    	 $('#reg_code').val(data.REG_CODE);
		 }
	 });
}
//
function validateSuspendForm(){
	//返回结果对象
	var result ={
			result:true,
			message:'',
			page_name:'挂起申请表'
	}
	var sus_id = $('#sus_id').val();
	if(empty(sus_id)){
		result.result = false;
		result.message = '请先保存数据，再进行提交申请！';
	}else{
		getSuspendInfoByParam();
		if(ishavedata == 'false'){
			result.result = false;
			result.message = '请先保存数据，再进行提交申请！';
		}
	}
	return result;
} 
//
function getSuspendInfoByParam(){
	   var bus_id;
	   if(!empty(init_add) && init_add == 'true'){
		   bus_id = $('#bus_id').val();
	   }else{
		   bus_id = $('#lastbusId').val();
	   }
	   var sus_no = $('#sus_no').val();
	   if(empty(bus_id)){
		   if(empty(sus_no)){
			   return;
		   }
	   }
	   $.ajax({
			url: ctx+'/workflow/workflow!loadSuspendData.action?time='+new Date(),
			type:'post',
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"ssuspendi.bus_id":bus_id,"ssuspendi.sus_no":sus_no},
			success: function(data){
				if(empty(data))
					ishavedata = 'false';
				setSuspendData(data);											//添加查封信息
			}
	  });
}

function setSuspendData(data){
	   //$("#reg_code").val(data.reg_code);
	if(data){
	   //$("#bus_id").val(data.bus_id);
	   $("#sus_id").val(data.sus_id);
	   $("#sus_no").val(empty(data.sus_no)?"****************":data.sus_no);
	   $("#regCode").val(data.sus_no);//regCode
	   $("#bus_des").val(data.bus_des);
	   $("#reg_app").val(data.reg_app);
	   $("#sus_reason").val(data.sus_reason);
	   $("#app_off").val(data.app_off);
	   $("#sus_app").val(data.sus_app);
	   $('#sus_status').val(empty(data.sus_status)?enumdata.PROCTOBECREATE:data.sus_status);
	}
}
//
function detectionSuspendHistoryWithoutCreate(bus_id){
	if(empty(bus_id))
		return;
	var handleblock={
			url:ctx+'/workflow/workflow!loadSuspendLstForFirst.action?time='+new Date(),
			type:'post',
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"ssuspendi.bus_id":bus_id},
			success: function(data){
				setSuspendData(data);
			}
	}
	$.ajax(handleblock);
}
	

	
