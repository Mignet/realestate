var ishavedata = 'true';
$(function(){
	$('#suspendForm').form({
		url :  ctx+"/workflow/workflow!modifySuspendData.action?time="+new Date(),
		success : function(data) {
			var data = $.parseJSON(data);
			if (data.success) {
				if(data.operType == enumdata.ADD)
				top.$.messager.alert('��ʾ', data.tipMessage, 'info', function(){
					  $("#bus_id").val(data.bus_id);
					  $("#sus_id").val(data.sus_id);
					  $("#sus_no").val(data.sus_no);
					  $("#regCode").val(data.sus_no);
				});
			} else {
				top.$.messager.alert('����', data.errorMessage, 'error', function(){});
			}
		}
	});
	
	$('#showhanguprecords').click(function(){
    	var objWindow2 = {
				//����Ԫ�ص�id
				id: 'wind_djby3',
				//����iframe��src
				src: ctx+'/jsp/workflow/specproc/hangup_history_tab.jsp?time='+new Date(),
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
	//���ؽ������
	var result ={
			result:true,
			message:'',
			page_name:'���������'
	}
	var sus_id = $('#sus_id').val();
	if(empty(sus_id)){
		result.result = false;
		result.message = '���ȱ������ݣ��ٽ����ύ���룡';
	}else{
		getSuspendInfoByParam();
		if(ishavedata == 'false'){
			result.result = false;
			result.message = '���ȱ������ݣ��ٽ����ύ���룡';
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
				setSuspendData(data);											//��Ӳ����Ϣ
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
	

	
