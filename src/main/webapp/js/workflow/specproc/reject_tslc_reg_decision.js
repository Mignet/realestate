var ishavedata = 'true';
$(function(){
	$('#rejectionForm').form({
		url :  ctx+"/workflow/workflow!modifyRejectionData.action?time="+new Date(),
		success : function(data) {
			var data = $.parseJSON(data);
			if (data.success) {
				if(data.operType == enumdata.ADD)
				top.$.messager.alert('提示', data.tipMessage, 'info', function(){
					  $("#bus_id").val(data.bus_id);
					  $("#rej_id").val(data.rej_id);
					  $("#rej_no").val(data.rej_no);
					  $("#rej_code").text(data.rej_no);
					  $("#regCode").val(data.rej_no);
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
function initEditRejectionForm(procId){
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
	    		 $('#rej_status').val(enumdata.PROCTOBECREATE);
	    		 detectionRejectionHistoryWithoutCreate(data.BUS_ID);
	    	 }else{
	    		 $('#rej_no').val(data.REG_CODE);
		    	 $('#lastbusId').val(data.LAST_BUS_ID);
	    		 gainRegcodeBylastbusId(data.LAST_BUS_ID);
	    		 getRejectionInfoByParam();
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
function validateRejectionForm(){
	//返回结果对象
	var result ={
			result:true,
			message:'',
			page_name:'驳回申请表'
	}
	var rej_id = $('#rej_id').val();
	if(empty(rej_id)){
		result.result = false;
		result.message = '请先保存数据，再进行提交申请！';
	}else{
		getRejectionInfoByParam();
		if(ishavedata == 'false'){
			result.result = false;
			result.message = '请先保存数据，再进行提交申请！';
		}
	}
	return result;
} 
//
function getRejectionInfoByParam(){
	   var bus_id;
	   if(!empty(init_add) && init_add == 'true'){
		   bus_id = $('#bus_id').val();
	   }else{
		   bus_id = $('#lastbusId').val();
	   }
	   var rej_no = $('#rej_no').val();
	   if(empty(bus_id)){
		   if(empty(rej_no)){
			   return;
		   }
	   }
	   $.ajax({
			url: ctx+'/workflow/workflow!loadRejectionData.action?time='+new Date(),
			type:'post',
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"bus_id":bus_id,"rej_no":rej_no},
			success: function(data){
				if(empty(data))
					ishavedata = 'false';
				setRejectionData(data);											//添加查封信息
			}
	  });
}

function setLaw(){
	var law01 = $('#law01').val();
	var law02 = $('#law02').val();
	if(empty(law02)){
		$('#law').val(law01);
	}else{
		$('#law').val(law01+law02);
	}
}

//
function setRejectionData(data){
	   //$("#reg_code").val(data.reg_code);
	if(data){
	   //$("#bus_id").val(data.bus_id);
	   $("#rej_id").val(data.rej_id);
	   $("#rej_no").val(data.rej_no);
	   $("#rej_code").text(empty(data.rej_no)?"*************":data.rej_no);
	   $("#regCode").val(data.rej_no);//regCode
	   $("#bus_des").val(data.bus_des);
	   $("#app").val(data.app);
	   $("#app_date").val(checkDate(new Date(data.app_date)));
	   var date = (empty(data.app_date))?new Date():new Date(data.app_date);
	   $("#app_date_str").val(formatDateII(date,'China'));
	   $("#rej_reason").val(data.rej_reason);
	   var law = data.law;
	   if(!empty(law))
	   if(law.length > 40){
		   $("#law01").val(law.substring(0,40));
		   $("#law02").val(law.substring(40,law.length));
	   }else{
		   $("#law01").val(law);
	   }
	   $('#rej_status').val(empty(data.rej_status)?enumdata.PROCTOBECREATE:data.rej_status);
	  
	}
}
//
function detectionRejectionHistoryWithoutCreate(bus_id){
	if(empty(bus_id))
		return;
	var handleblock={
			url:ctx+'/workflow/workflow!loadRejectionLstForFirst.action?time='+new Date(),
			type:'post',
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"srejectioni.bus_id":bus_id},
			success: function(data){
				setRejectionData(data);
			}
	}
	$.ajax(handleblock);
}

	

	
