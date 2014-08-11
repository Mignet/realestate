var ishavedata = 'true';
$(function(){
	$('#refundForm').form({
		url :  ctx+"/workflow/workflow!modifyRefundData.action?time="+new Date(),
		success : function(data) {
			var data = $.parseJSON(data);
			if (data.success) {
				if(data.operType == enumdata.ADD)
				top.$.messager.alert('提示', data.tipMessage, 'info', function(){
					  $("#bus_id").val(data.bus_id);
					  $("#refund_id").val(data.refund_id);
					  $("#refund_no").val(data.refund_no);
					  $("#regCode").val(data.refund_no);
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
function initEditRefundForm(procId){ 
	  $('#refund_caption').val(enumdata.INSTITUTIONAL_CAPTION);
	  $('#refund_caption_floor').val(enumdata.INSTITUTIONAL_CAPTION);
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
	    		 $('#refund_no').val(data.REG_CODE);
	    		 $('#refund_status').val(enumdata.PROCTOBECREATE);
	    		 $('#refund_date').text(convertDateFromNumToChinese());
	    		 detectionRefundHistoryWithoutCreate(data.BUS_ID);
	    	 }else{
	    		 $('#refund_no').val(data.REG_CODE);
		    	 $('#lastbusId').val(data.LAST_BUS_ID);
		    	 $('#refund_prt').css('display','block');
		 		 $('#notice_note').css('display','none'); 
	    		 //gainRegcodeBylastbusId(data.LAST_BUS_ID);
		 		 $('#reg_code').val(data.REG_CODE);
	    		 getRefundInfoByParam();
	    		// getPreProcId();
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
function validateRefundForm(){
	//返回结果对象
	var result ={
			result:true,
			message:'',
			page_name:'退文申请表'
	}
	var refund_id = $('#refund_id').val();
	if(empty(refund_id)){
		result.result = false;
		result.message = '请先保存数据，再进行提交申请！';
	}else{
		getRefundInfoByParam();
		if(ishavedata == 'false'){
			result.result = false;
			result.message = '请先保存数据，再进行提交申请！';
		}
	}
	return result;
} 
//
function getRefundInfoByParam(){
	   var bus_id;
	   if(!empty(init_add) && init_add == 'true'){
		   bus_id = $('#bus_id').val();
	   }else{
		   bus_id = $('#lastbusId').val();
	   }
	   var refund_no = $('#refund_no').val();
	   if(empty(bus_id)){
		   if(empty(refund_no)){
			   return;
		   }
	   }
	   $.ajax({
			url: ctx+'/workflow/workflow!loadRefundData.action?time='+new Date(),
			type:'post',
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"srefundi.bus_id":bus_id,"srefundi.refund_no":refund_no},
			success: function(data){
				if(empty(data))
					ishavedata = 'false';
				setRefundData(data);											//添加查封信息
			}
	  });
}

function setRefundData(data){
	   //$("#reg_code").val(data.reg_code);
	if(data){
	   //$("#bus_id").val(data.bus_id);
	   $("#refund_id").val(data.refund_id);
	   $("#refund_no").val(data.refund_no);
	   $("#regCode").val(data.refund_no);//regCode
	   $("#reg_item").val(data.reg_item);
	   $("#reg_app").val(data.reg_app);
	   $("#notice_date").datebox('setValue',subDatePart(data.notice_date));
	   $("#refund_reason").val(data.refund_reason);
	   $("#refund_app").val(data.refund_app);
	   if(!empty(data.refund_app)){
		   $('#rd_app').text(data.refund_app); 
	   }
	   $("#refund_app_idno").val(data.refund_app_idno);
	   $('#refund_status').val(empty(data.refund_status)?enumdata.PROCTOBECREATE:data.refund_status);
	   $('#notice_note').val(data.notice_note);
	   if(!empty(data.notice_note) && empty(init_add)){
		   $('#refund_notice').html('<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+data.notice_note+'</p>');
	   }
	   if(!empty(data.notice_date)){
		   $('#refund_date').text(convertDateFromNumToChinese(data.notice_date));
	   }
	}
}
//
function detectionRefundHistoryWithoutCreate(bus_id){
	if(empty(bus_id))
		return;
	var handleblock={
			url:ctx+'/workflow/workflow!loadRefundLstForFirst.action?time='+new Date(),
			type:'post',
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"srefundi.bus_id":bus_id},
			success: function(data){
				setRefundData(data);
			}
	}
	$.ajax(handleblock);
}
//
/*function detectionRefundHistoryRecords(bus_id){
	if(empty(bus_id))
		return;
	var handleblock={
			url:ctx+'/workflow/workflow!loadRefundLst.action?time='+new Date(),
			type:'post',
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"srefundi.bus_id":bus_id},
			success: function(data){
				
			}
	}
	$.ajax(handleblock);
}*/

function setrfApp(val){
	$('#rd_app').text(val);
}

	

	
