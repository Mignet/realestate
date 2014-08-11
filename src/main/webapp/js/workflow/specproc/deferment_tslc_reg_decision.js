var ishavedata = 'true';
$(function(){
	$('#respiteForm').form({
		url :  ctx+"/workflow/workflow!modifyRespiteData.action?time="+new Date(),
		success : function(data) {
			var data = $.parseJSON(data);
			if (data.success) {
				if(data.operType == enumdata.ADD)
				top.$.messager.alert('提示', data.tipMessage, 'info', function(){
					  $("#bus_id").val(data.bus_id);
					  $("#res_id").val(data.res_id);
					  $("#res_no").val(data.res_no);
					  $("#res_code").text(data.res_no);
					  $("#regCode").val(data.res_no);
				});
			} else {
				top.$.messager.alert('错误', data.errorMessage, 'error', function(){});
			}
		}
	});	
});

$.fn.datebox.defaults.formatter = function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return formatDate(y,m,d,'');
}
//
function initEditRespiteForm(procId){
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
	    		 $('#res_status').val(enumdata.PROCTOBECREATE);
	    		 detectionRespiteHistoryWithoutCreate(data.BUS_ID);
	    	 }else{
	    		 $('#res_no').val(data.REG_CODE);
		    	 $('#lastbusId').val(data.LAST_BUS_ID);
	    		 gainRegcodeBylastbusId(data.LAST_BUS_ID);
	    		 getRespiteInfoByParam();
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
function validateRespiteForm(){
	//返回结果对象
	var result ={
			result:true,
			message:'',
			page_name:'暂缓决定书'
	}
	var res_id = $('#res_id').val();
	if(empty(res_id)){
		result.result = false;
		result.message = '请先保存数据，再进行提交申请！';
	}else{
		getRespiteInfoByParam();
		if(ishavedata == 'false'){
			result.result = false;
			result.message = '请先保存数据，再进行提交申请！';
		}
	}
	return result;
} 
//
function getRespiteInfoByParam(){
	   var bus_id;
	   if(!empty(init_add) && init_add == 'true'){
		   bus_id = $('#bus_id').val();
	   }else{
		   bus_id = $('#lastbusId').val();
	   }
	   var res_no = $('#res_no').val();
	   if(empty(bus_id)){
		   if(empty(res_no)){
			   return;
		   }
	   }
	   $.ajax({
			url: ctx+'/workflow/workflow!loadRespiteData.action?time='+new Date(),
			type:'post',
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"srespitei.bus_id":bus_id,"srespitei.res_no":res_no},
			success: function(data){
				if(empty(data))
					ishavedata = 'false';
				setRespiteData(data);											//添加查封信息
			}
	  });
}
//
function setRespiteData(data){
	   //$("#reg_code").val(data.reg_code);
	if(data){
	   //$("#bus_id").val(data.bus_id);
	   $("#res_id").val(data.res_id);
	   $("#res_no").val(data.res_no);
	   $("#res_code").text(empty(data.res_no)?"*************":data.res_no);
	   $("#regCode").val(data.res_no);//regCode
	   $("#bus_des").val(data.bus_des);
	   $("#app").val(data.app);
	   $("#app_date").val(formatDateII(new Date(data.app_date)));
	   var date = (empty(data.app_date))?new Date():new Date(data.app_date);
	   $("#app_date_str").val(formatDateII(date,'China'));
	   $("#res_reason").val(data.res_reason);
	   $('#res_status').val(empty(data.res_status)?enumdata.PROCTOBECREATE:data.res_status);
	}
}
//
function detectionRespiteHistoryWithoutCreate(bus_id){
	if(empty(bus_id))
		return;
	var handleblock={
			url:ctx+'/workflow/workflow!loadRespiteLstForFirst.action?time='+new Date(),
			type:'post',
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"srespitei.bus_id":bus_id},
			success: function(data){
				setRespiteData(data);
			}
	}
	$.ajax(handleblock);
}
	

	
