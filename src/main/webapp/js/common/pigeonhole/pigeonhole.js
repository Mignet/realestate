/*********************************************************************************
*��  ��  ��  ��: pigeonhole.js
*��  ��  ��  ��: �鵵��
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Joyon
*��  ��  ��  �ڣ� 2014-03-01
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
var flag;
var serialNumber ={num1:"�ǼǱ��",num2:"�鵵��"};
var arch_no;										//�鵵��
var proc=this.parent.proc;						//�����ڵ���������
var proc_node = proc.activName;					//���̽ڵ�
var proc_id = proc.procId;
var _init_form_data;							//��ʼ��ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var _cur_form_data;								//��֤ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
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
	   //submit��Ť���¼�
	   document.getElementById("submit").onclick = function() {
			   submitConf();
		   };
	   getPige();
	   $("#cancel").linkbutton("disable");
});
/**********************************************************************************
*��������: submitConf()
*����˵��: �ύȷ��  ��У��ҳ������   ͨ��  ��У���Ƿ�ȷ�Ϲ鵵
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/  
function submitConf(){
	_init_form_data = $("#main_form").serializeJson(); 
	var result = validate()
 	 if(!result.result){
 		top.$.messager.alert('��ȷ��ʾ',result.message, 'info',
					function() {	
				
					});
 		 return;
 	 }
// 	if(!arch_handler_no || arch_handler_no.length<1){
//		 alert("�鵵�˱�Ų���Ϊ�գ�");
//		 return;
//		 
//	 }
	$.messager.confirm('�鵵ȷ��', 'ȷ����Ϣ���� �鵵��', function(r){
		if (r) {
			$("#submit").linkbutton("disable");
		    $("#cancel").linkbutton("disable");
		    submit();
		}
	});
}    
/**********************************************************************************
*��������: submitConf()
*����˵��: ���������²���
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
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
*��������: cancelSave()
*����˵��: ȡ������ 
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
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
				top.$.messager.alert('��ȷ��ʾ',"�鵵ȡ���ɹ�", 'info',
						function() {	
					
					$('#tab_zj').form("load",data);
						});
			} else {
				top.$.messager.alert('������ʾ', data.errorMessage, 'error');
	   			}
	   		}
	    	  
	 });	     
}	  
/**********************************************************************************
*��������: getTime()
*����˵��: ��ȡϵͳ��ǰʱ��
*����˵��: 
*�� �� ֵ: ��ǰtime �ַ���
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
*��������: getTime()
*����˵��: ��ȡϵͳ��ǰʱ��
*����˵��: 
*�� �� ֵ: ��ǰtime �ַ���
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
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
//			 		//�Զ���ȡ�鵵���
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
*��������: getPige()
*����˵��: ��ȡ�鵵��Ϣ
*����˵��: 
*�� �� ֵ: �޷���ֵ   ����ǰ�����ݸ�ֵ 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/  
function getPige(){
	 //��ѯ�鵵��Ϣ�Ƿ����
  	$.ajax({
   		    dataType: 'json',
   		    url:ctx+"/common/certificate!getPigeByid.action?proc_id="+proc_id+"&time="+new Date(),
   			success:function(data){
   				//alert("��ȡ�鵵��Ϣ");
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
	//У��鵵�������Ѿ����
	var arbox_code =  $.trim($("#arbox_code").val());
	 if(!arbox_code || arbox_code.length<1){
		 message = "�鵵�б�Ų���Ϊ�գ�";
		//alert("�鵵�б�Ų���Ϊ�գ�");
		 result.message=message;
		 result.result=false; 
		 return result;
	 }
	 
	//��ֵ  ����ǰ���Ǳ������  ���¸�������ֵ
	if(v_flag){
		_init_form_data = $("#main_form").serializeJson(); 
	}
	//�ж��������Ƿ��޸�  ������޸�  ����ʾ�Ƿ񱣴�δ��������
	_cur_form_data = $("#main_form").serializeJson(); 
	var r = equal(_init_form_data,_cur_form_data);
	if(!r){
		var flag= 0 ;//����ȷ�� �Ƿ��û��Ѿ������������  δ���  ���������������     ����false
		message = '���ݼ��޸ģ����ȱ�����ύ��';
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
*��������: pageDataIsChange
*����˵��: �жϵ�ǰҳ�������Ƿ��Ѿ��޸�
*����˵��: 
*�� �� ֵ: ���޸ķ���true   δ�޸ķ���false
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function pageDataIsChange(){
	_cur_form_data = $("#main_form").serializeJson(); 
	
	var r = equal(_init_form_data,_cur_form_data);
	//�������ȷ���  ҳ�����ݼ��޸�  ����true
	if(!r){
	  return true;
	}
	return false;
}









