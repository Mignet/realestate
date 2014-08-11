/*********************************************************************************
*��  ��  ��  ��: testpaper.js
*��  ��  ��  ��: ����
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Joyon
*��  ��  ��  �ڣ� 2014-03-01
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
var flag;
var zjbid;
var state={state1:"�����",state2:"δ���"};
var proc=this.parent.proc;
var proc_id = proc.procId;
var reg_code;
/**********************************************************************************
*��������: 
*����˵��: js����ʱ����  ��ʼ����ǰҳ������
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
$(function(){
   //����ȡ����ť
   document.getElementById("submit").onclick = function() {
	   submit();
   };
   //��ʼ����֤��Ϣ
   getPaper();
});
/**********************************************************************************
*��������: ���������²���
*����˵��: ���������²���
*����˵��: 
*�� �� ֵ: ���ص�ǰʱ��
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
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
		   				top.$.messager.alert('��ȷ��ʾ', data.tipMessage, 'info',
		   						function() {	
		   					
		   					$('#tab_zj').form("load",data);
		   						});
		   			} else {
		   				top.$.messager.alert('������ʾ', data.errorMessage, 'error');
		   			}
	   			},error:function(){
	   				 result = false;
	   			}
	       });	   
	       
	       return result;
};
/**********************************************************************************
*��������: ��ȡϵͳ��ǰʱ��
*����˵��: ��ȡϵͳ��ǰʱ��
*����˵��: 
*�� �� ֵ: ���ص�ǰʱ��
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function getTime(){
	  var myDate = new Date();
	  var year = myDate.getFullYear();
	  var month = myDate.getMonth()+1;
	  var date = myDate.getDate(); 
     var mytime=myDate.toLocaleTimeString();     //��ȡ��ǰʱ��	  
     var time = year+"-"+month+"-"+date+" "+mytime;
     return time;
}
/**********************************************************************************
*��������: ȡ������������
*����˵��: ȡ������������
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
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
				top.$.messager.alert('��ȷ��ʾ',"���ȡ���ɹ�", 'info',
						function() {	
							$('#tab_zj').form("load",data);
						});
			} else {
				top.$.messager.alert('������ʾ', data.errorMessage, 'error');
			}
		}
	};
	$.ajax(obj);
};

/**********************************************************************************
*��������: ��ʼ����֤��
*����˵��: ��ʼ����֤��
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
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
		 		if(data.arrange_state =="δ���"){
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
*��������: ҳ��У�鷽��
*����˵��: ��֤ҳ���ϵķǿ�  �����ݸ�ʽ   
*����˵��: v_flag ��ֵ�����Ǳ������
*�� �� ֵ: obj  result(trueͨ��  false��ͨ��) message(��Ϣ)  page_name(��ǰҳ������)
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function validate(v_flag){
	 var result ={
	   			result:true,
	   			message:'',
	   			page_name:'����֪ͨ��'
	   		}
	 //����Ǳ���  �ȸı�״̬
	 if(v_flag){
		 $("#arrange_state").val(state.state1);
	 }
	if( $("#arrange_state").val()==state.state2){
		message = '�����������ύ����һ���ڣ�';
//		top.$.messager.alert('��ʾ', '�����������ύ����һ���ڣ�', 'info',
//				function() {
//					
//				});	
		result.message=message;
		 result.result=false; 
		 return result; 
	}
	
	return result; 
	
}

