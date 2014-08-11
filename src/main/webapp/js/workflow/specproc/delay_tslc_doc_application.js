var ishavedata = 'true';
$(function(){
	$('#delayForm').form({
		url :  ctx+"/workflow/workflow!modifyDelayData.action?time="+new Date(),
		success : function(data) {
			var data = $.parseJSON(data);
			if (data.success) {
				if(data.operType == enumdata.ADD)
				top.$.messager.alert('��ʾ', data.tipMessage, 'info', function(){
					  $("#bus_id").val(data.bus_id);
					  $("#delay_id").val(data.delay_id);
					  $("#delay_no").val(data.delay_no);
					  $("#regCode").val(data.delay_no);
					/*  $("#app_days").val( $("#app_days").val()+"��������");
					  $("#total_limit").val($("#total_limit").val()+"��������");
					  $("#approval_days").val($("#approval_days").val()+"��������");
					  $("#remainder_days").val($("#remainder_days").val()+"��������");*/
				});
			} else {
				top.$.messager.alert('����', data.errorMessage, 'error', function(){});
			}
		}
	});	
	
    $('#showhisdelayrecords').click(function(){
    	var objWindow2 = {
				//����Ԫ�ص�id
				id: 'wind_djby3',
				//����iframe��src
				src: ctx+'/jsp/workflow/specproc/delay_history_tab.jsp?time='+new Date(),
				//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
				destroy: true,
				//���ڱ���
				title: '������ʷ��Ϣ��¼',
				//���ڿ�
				width: 600,
				//���ڸ�
				height: 498,
				modal: true,
				//������iframe��window�����onLoad�ص���������
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
function initEditDelayForm(procId){
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
	    		 $('#delay_status').val(enumdata.PROCTOBECREATE);
	    		 detectionDelayHistoryWithoutCreate(data.BUS_ID);
	    	 }else{
	    		 $('#delay_no').val(data.REG_CODE);
		    	 $('#lastbusId').val(data.LAST_BUS_ID);
	    		 gainRegcodeBylastbusId(data.LAST_BUS_ID);
	    		 getDelayInfoByParam();
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
function validateDelayForm(){
	//���ؽ������
	var result ={
			result:true,
			message:'',
			page_name:'���������'
	}
	var delay_id = $('#delay_id').val();
	if(empty(delay_id)){
		result.result = false;
		result.message = '���ȱ������ݣ��ٽ����ύ���룡';
	}else{
		getDelayInfoByParam();
		if(ishavedata == 'false'){
			result.result = false;
			result.message = '���ȱ������ݣ��ٽ����ύ���룡';
		}
	}
	return result;
} 
//
function getDelayInfoByParam(){
	   var bus_id;
	   if(!empty(init_add) && init_add == 'true'){
		   bus_id = $('#bus_id').val();
	   }else{
		   bus_id = $('#lastbusId').val();
	   }
	   var delay_no = $('#delay_no').val();
	   if(empty(bus_id)){
		   if(empty(delay_no)){
			   return;
		   }
	   }
	   $.ajax({
			url: ctx+'/workflow/workflow!loadDelayData.action?time='+new Date(),
			type:'post',
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"sdelayi.bus_id":bus_id,"sdelayi.delay_no":delay_no},
			success: function(data){
				if(empty(data))
					ishavedata = 'false';
				setDelayData(data);											//��Ӳ����Ϣ
			}
	  });
}

function setDelayData(data){
	   //$("#reg_code").val(data.reg_code);
	if(data){
	   //$("#bus_id").val(data.bus_id);
	   $("#delay_id").val(data.delay_id);
	   $("#delay_no").val(empty(data.delay_no)?"****************":data.delay_no);
	   $("#regCode").val(data.delay_no);//regCode
	   $("#delay_app_part").val(data.delay_app_part);
	   $("#bus_des").val(data.bus_des);
	   $("#reg_app").val(data.reg_app);
	   $("#delay_app").val(data.delay_app);
	   $("#isappanddays").val(data.isappanddays);
	   $("#app_days").val(data.app_days);
	   $("#total_limit").val(data.total_limit);
	   $("#delay_reason").val(data.delay_reason);
	   $("#approval_days").val(data.approval_days);
	   $("#remainder_days").val(data.remainder_days);
	   $("#delay_type").val(data.delay_type);
	   if(data.monitor == "01")
	      $("#monitor01").attr('checked','checked');
	   else if(data.monitor == "02")
		   $("#monitor02").attr('checked','checked');
	   $('#delay_status').val(empty(data.delay_status)?enumdata.PROCTOBECREATE:data.delay_status);
	}
}

function detectionDelayHistoryWithoutCreate(bus_id){
	if(empty(bus_id))
		return;
	var handleblock={
			url:ctx+'/workflow/workflow!loadDelayLstForFirst.action?time='+new Date(),
			type:'post',
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"sdelayi.bus_id":bus_id},
			success: function(data){
				setDelayData(data);
			}
	}
	$.ajax(handleblock);
}

	

	
