var proc;
proc=this.parent.proc;

var proc_id = proc.procId;
var activName = proc.activName;
var procdefId = proc.procdefId;
var serialNumber ={num1:"�ǼǱ��",num2:"�鵵��",num3:"���ز�֤��"};
var _init_form_data;							//��ʼ��ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var _cur_form_data;								//��֤ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var opinion;							//���
var opiniontime;							//���¼��ʱ��
var opinionpeople;						//�����
var opiniontype;						//�������

var state1 = {
		string0: "����",
		string1 : "����1",
		string2 : "����1",
		string3 : "���1",
		string4 : "����2",
		string5 : "���2",
		string6 : "��׼",
		string7: "�鵵",
	};
$(function(){
	        setState(activName);	
	        getExamine();	
	        initial();
       		//ʧȥ�����¼�
       		$('textarea').blur(function(){
       			blur();
       		});   
       });
       
//��ȡ��ַ������
function GetQueryString(name)
	{
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r!=null) return unescape(r[2]);
		return null;
};
       
//���������²���
function submit(){  
	return saveExamine();
 };
//�������̽ڵ����ÿؼ�Ȩ��
function setState(activName){
	      if(activName == state1.string1){
		        $("#fsyj").attr("disabled","disabled");
		        $("#csyj2").attr("disabled","disabled");
		        $("#shyj1").attr("disabled","disabled");
		        $("#shyj2").attr("disabled","disabled");
		        $("#hzyj").attr("disabled","disabled");
		        $("#fscyy").attr("disabled","disabled");
		        $("#cscyy2").attr("disabled","disabled");
		        $("#shcyy2").attr("disabled","disabled");
		        $("#shcyy1").attr("disabled","disabled");
		        $("#hzcyy").attr("disabled","disabled");
	      };
	      if(activName == state1.string2){
	    	  $("#csyj1").attr("disabled","disabled");
		       $("#csyj2").attr("disabled","disabled");
		       $("#shyj1").attr("disabled","disabled");
		       $("#shyj2").attr("disabled","disabled");
		       $("#hzyj").attr("disabled","disabled");
		       $("#cscyy1").attr("disabled","disabled");
		       $("#cscyy2").attr("disabled","disabled");
		       $("#shcyy2").attr("disabled","disabled");
		       $("#shcyy1").attr("disabled","disabled");
		       $("#hzcyy").attr("disabled","disabled");
	      };
	      if(activName == state1.string3){
	    	  $("#csyj1").attr("disabled","disabled");
	    	  $("#fsyj").attr("disabled","disabled");
		       $("#csyj2").attr("disabled","disabled");
		       $("#shyj2").attr("disabled","disabled");
		       $("#hzyj").attr("disabled","disabled");
		       $("#fscyy").attr("disabled","disabled");
		       $("#cscyy1").attr("disabled","disabled");
		       $("#cscyy2").attr("disabled","disabled");
		       $("#shcyy2").attr("disabled","disabled");
		       $("#hzcyy").attr("disabled","disabled");
		      };
	      if(activName == state1.string4){
	    	  $("#csyj1").attr("disabled","disabled");
	    	  $("#shyj1").attr("disabled","disabled");
	    	  $("#fsyj").attr("disabled","disabled");
		       $("#shyj2").attr("disabled","disabled");
		       $("#hzyj").attr("disabled","disabled");
		       $("#shcyy1").attr("disabled","disabled");
		       $("#fscyy").attr("disabled","disabled");
		       $("#cscyy1").attr("disabled","disabled");
		       $("#shcyy2").attr("disabled","disabled");
		       $("#hzcyy").attr("disabled","disabled");
	      };
	      if(activName == state1.string5){
	    	   $("#csyj1").attr("disabled","disabled");
	    	   $("#shyj1").attr("disabled","disabled");
	    	   $("#csyj2").attr("disabled","disabled");
	    	   $("#fsyj").attr("disabled","disabled");
		       $("#hzyj").attr("disabled","disabled");
		       $("#cscyy2").attr("disabled","disabled");
		       $("#shcyy1").attr("disabled","disabled");
		       $("#fscyy").attr("disabled","disabled");
		       $("#cscyy1").attr("disabled","disabled");
		       $("#hzcyy").attr("disabled","disabled");
	      };
	      if(activName == state1.string6){
	    	  $("#csyj1").attr("disabled","disabled");
	    	  $("#shyj1").attr("disabled","disabled");
	    	  $("#csyj2").attr("disabled","disabled");
	    	  $("#fsyj").attr("disabled","disabled");
	    	  $("#shyj2").attr("disabled","disabled");
		       $("#cscyy2").attr("disabled","disabled");
		       $("#shcyy1").attr("disabled","disabled");
		       $("#fscyy").attr("disabled","disabled");
		       $("#cscyy1").attr("disabled","disabled");
		       $("#shcyy2").attr("disabled","disabled");
	      };
	      if(!(activName == state1.string1 || activName == state1.string2 ||activName == state1.string3 ||activName == state1.string4||activName == state1.string5||activName == state1.string6)){
	    	  $(":input").attr("disabled", "disabled");  
	      }
	  }
//��ȡϵͳ��ǰʱ��
function getTime(){
	  var myDate = new Date();
	  var year = myDate.getFullYear();
	  var month = myDate.getMonth()+1;
	  var date = myDate.getDate(); 
     var mytime=myDate.toLocaleTimeString();     //��ȡ��ǰʱ��	  
	 var time = year+"-"+month+"-"+date+" "+mytime;
	 return time;
 }
//ѡ������
 function selectCyy(){ 
	 openInTopWindow({
			// ����Ԫ�ص�id
			id : 'comlan',
			// ����iframe��src
			src : ctx+'/jsp/common/comlangu/lanselect.jsp?time='+new Date(),
			// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy : true,
			// ���ڱ���
			title : 'ѡ�����ģ��',
			// ���ڿ�
			width : 600,
			// ���ڸ�
			height : 400,
			modal : true,
			// ������iframe��window�����onLoad�ص���������
			onLoad : function() {
				// �˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
				// ��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
				this.openerWindow = window;
				// ����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
				this.args = {
					//userDataGrid : userDataGrid
				};
				this.init(activName,procdefId);
			}
		});
		 }; 
		
//��ȡ��ҳ���ֵ
function  getNewLinkValue(value){
	
	if(activName == state1.string1){
		$("#csyj1").text(value);
		//  ��ȡ��ǰʱ��
	    $("#cssj1").val(getTime());
	     //��ȡ��ǰ������
	    $("#csr1").val(user); 
	 };
	 if(activName == state1.string2){
		 $("#fsyj").text(value);
		 //  ��ȡ��ǰʱ��
		 $("#fssj").val(getTime());
		 //��ȡ��ǰ������
		 $("#fsr").val(user); 
	 };
	 if(activName == state1.string3){
		 $("#shyj1").text(value);
		 //  ��ȡ��ǰʱ��
		 $("#shsj1").val(getTime());
		 //��ȡ��ǰ������
		 $("#shr1").val(user); 
	 };
	 if(activName == state1.string4){
		 $("#csyj2").text(value);
		 //  ��ȡ��ǰʱ��
		 $("#cssj2").val(getTime());
		 //��ȡ��ǰ������
		 $("#csr2").val(user); 
	 };
	 if(activName == state1.string5){
		 $("#shyj2").text(value);
		 //  ��ȡ��ǰʱ��
		 $("#shsj2").val(getTime());
		 //��ȡ��ǰ������
		 $("#shr2").val(user); 
	 };
	 if(activName == state1.string6){
		 $("#hzyj").text(value);
		 //  ��ȡ��ǰʱ��
		 $("#hzsj").val(getTime());
		 //��ȡ��ǰ������
		 $("#hzr").val(user); 
	 };
};

//��ʼ����ǰ��¼���Լ�����
function initial(){
	
	if(activName == state1.string1){
		//  ��ȡ��ǰʱ��
	    $("#cssj1").val(getTime());
	     //��ȡ��ǰ������
	    $("#csr1").val(user); 
	 };
	 if(activName == state1.string2){
		 //  ��ȡ��ǰʱ��
		 $("#fssj").val(getTime());
		 //��ȡ��ǰ������
		 $("#fsr").val(user); 
	 };
	 if(activName == state1.string3){
		 //  ��ȡ��ǰʱ��
		 $("#shsj1").val(getTime());
		 //��ȡ��ǰ������
		 $("#shr1").val(user); 
	 };
	 if(activName == state1.string4){
		 //  ��ȡ��ǰʱ��
		 $("#cssj2").val(getTime());
		 //��ȡ��ǰ������
		 $("#csr2").val(user); 
	 };
	 if(activName == state1.string5){
		 //  ��ȡ��ǰʱ��
		 $("#shsj2").val(getTime());
		 //��ȡ��ǰ������
		 $("#shr2").val(user); 
	 };
	 if(activName == state1.string6){
		 //  ��ȡ��ǰʱ��
		 $("#hzsj").val(getTime());
		 //��ȡ��ǰ������
		 $("#hzr").val(user); 
	 };
	
};




//��������ȡ����ʱ�����浱ǰ�û��͵�ǰʱ��

function blur(){
	
	  if(activName == state1.string1){
		    
			//  ��ȡ��ǰʱ��
		        $("#cssj").val(getTime());
		        //��ȡ��ǰ������
		        $("#csr").val(user); 
	 };
	 if(activName == state1.string2){
			//  ��ȡ��ǰʱ��
		         $("#fssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#fsr").val(user); 
	 };
	 if(activName == state1.string3){
	
			//  ��ȡ��ǰʱ��
		         $("#shsj").val(getTime());
		         //��ȡ��ǰ������
		        $("#shr").val(user); 
	 };
	 if(activName == state1.string4){
			
			//  ��ȡ��ǰʱ��
		         $("#hzsj").val(getTime());
		         //��ȡ��ǰ������
		        $("#hzr").val(user); 
	 };
	
};

/**********************************************************************************
 *��������: getExamine
 *����˵��: ��ȡ��������Ϣ
 *����˵��: 
 *�� �� ֵ:
 *��������: xuzz
 *��������: 2014-04-22
 *�޸���ʷ: 
***********************************************************************************/
function getExamine(){
	
	  //��ʼ����������Ϣ   
  	$.ajax({
   		    dataType: 'json',
   			url:ctx+"/common/exam!getExamineById.action?proc_id="+proc_id+"&time="+new Date(),
   			success:function(data){
   			 	if(data){
   			 		for(var i=0;i<data.length;i++)
   			 		{
   			 			//����1
   			 			if(data[i].opinion_type==state1.string1)
   			 			{
                          	$("#cssj1").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-2));  
      			 		    $("#csyj1").val(data[i].opinion_content);
      			 		    $("#csr1").val(data[i].checker_no);
   			 			}
   			 			//����1
   			 			if(data[i].opinion_type==state1.string2)
   			 			{
	   			 			$("#fssj").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-2));  
	  			 		    $("#fsyj").val(data[i].opinion_content);
	  			 		    $("#fsr").val(data[i].checker_no);
   			 			}
   			 			//���1
   			 			if(data[i].opinion_type==state1.string3)
   			 			{
	   			 			$("#shsj1").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-2));  
	  			 		    $("#shyj1").val(data[i].opinion_content);
	  			 		    $("#shr1").val(data[i].checker_no);
   			 			}
   			 			//����2
   			 			if(data[i].opinion_type==state1.string4)
   			 			{
	   			 			$("#cssj2").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-2));  
	  			 		    $("#csyj2").val(data[i].opinion_content);
	  			 		    $("#csr2").val(data[i].checker_no);
   			 			}
   			 			//���2
   			 			if(data[i].opinion_type==state1.string5)
   			 			{
	   			 			$("#shsj2").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-2));  
	  			 		    $("#shyj2").val(data[i].opinion_content);
	  			 		    $("#shr2").val(data[i].checker_no);
   			 			}
   			 			//��׼
   			 			if(data[i].opinion_type==state1.string6)
   			 			{
	   			 			$("#hzsj").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-2));  
	  			 		    $("#hzyj").val(data[i].opinion_content);
	  			 		    $("#hzr").val(data[i].checker_no);
   			 			}
   			 		}
   			 	}
   			 _init_form_data = $("#main_form").serializeJson(); 
   				 
   			}
   		});
	
	
}


/**********************************************************************************
 *��������: getOpinion
 *����˵��: ��ȡ���
 *����˵��: 
 *�� �� ֵ:
 *��������: xuzz
 *��������: 2014-04-21
 *�޸���ʷ: 
 ***********************************************************************************/
  function getOpinion()
  {
	  if(activName == state1.string1){
	   	    opinion = $("#csyj1").val();
	   	    opiniontime = $("#cssj1").val(getTime());
	   	    opinionpeople = $("#csr1").val();
	   	    opiniontype = state1.string1;
    };
    if(activName == state1.string2){
	       opinion = $("#fsyj").val();
	   	   opiniontime = $("#fssj").val(getTime());
	   	   opinionpeople = $("#fsr").val();
	   	   opiniontype = state1.string2;
    };
    if(activName == state1.string3){
	       opinion = $("#shyj1").val();
	   	   opiniontime = $("#shsj1").val(getTime());
	   	   opinionpeople = $("#shr1").val();
	   	   opiniontype = state1.string3;
	      };
    if(activName == state1.string4){
	       opinion = $("#csyj2").val();
	   	   opiniontime = $("#cssj2").val(getTime());
	   	   opinionpeople = $("#csr2").val();
	   	   opiniontype = state1.string4;
    };
    if(activName == state1.string5){
	       opinion = $("#shyj2").val();
	   	   opiniontime = $("#shsj2").val(getTime());
	   	   opinionpeople = $("#shr2").val();
	   	   opiniontype = state1.string5;
    };
    if(activName == state1.string6){
	       opinion = $("#hezyj").val();
	   	   opiniontime = $("#hzsj").val(getTime());
	   	   opinionpeople = $("#hzr").val();
	   	   opiniontype = state1.string6;
    };
  }


  /**********************************************************************************
  *��������: saveExamine
  *����˵��: �������
  *����˵��: 
  *�� �� ֵ:
  *��������: xuzz
  *��������: 2014-04-21
  *�޸���ʷ: 
  ***********************************************************************************/
function saveExamine(){
	var result = true; 
	getOpinion();
	
	$.ajax({
   		dataType:'json',
   		url:ctx+"/common/exam!saveExaine.action?time="+new Date()+"&proc_id="+proc_id,
   		contentType:"application/x-www-form-urlencoded; charset=GBK",
   		//�������л�����
   		data:{"examine.checker_no":opinionpeople,"examine.opinion_content":opinion,"examine.opinion_type":opiniontype},
   		success:function(data){
		 	if(data){
		 		//alert(data);
		 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
				});	
		 		
		 	}else {
				top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
			}
   		},error:function(data){
   			result = false;
   		}
   	});
	return result;
}
/**********************************************************************************
*��������: validate()
*����˵��: ����У�鷽��  ����ʱ��֤ �������ڷ���true
*����˵��: 
*�� �� ֵ: obj  result(trueͨ��  false��ͨ��) message(��Ϣ)  page_name(��ǰҳ������)
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function validate(v_flag){
	//���ؽ������
	var result ={
			result:true,
			message:'',
			page_name:'������'
	}
	//��ʾ��Ϣ 
	var message;
	
	if(activName == state1.string1){
		var yj = $("#csyj1").val();
		if($.trim(yj).length== 0){
			message = '�����������Ϊ�գ���¼����������ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 
			 result.message=message;
			 result.result=false;
			 return result;
		}
		
	}if(activName == state1.string2){
		var yj = $("#fsyj").val()
		if($.trim(yj).length== 0){
			message = '�����������Ϊ�գ���¼�븴�������ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}if(activName == state1.string3){
		var yj = $("#shyj1").val()
		if($.trim(yj).length== 0){
			message = '����������Ϊ�գ���¼����������ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}if(activName == state1.string4){
		var yj = $("#csyj2").val()
		if($.trim(yj).length== 0){
			message = '�����������Ϊ�գ���¼����������ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}
	if(activName == state1.string5){
		var yj = $("#shyj2").val()
		if($.trim(yj).length== 0){
			message = '����������Ϊ�գ���¼����������ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}
	if(activName == state1.string6){
		var yj = $("#hzyj").val()
		if($.trim(yj).length== 0){
			message = '��׼�������Ϊ�գ���¼���׼�����ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}
	
	if(v_flag){
		_init_form_data = $("#main_form").serializeJson(); 
	}
	//�ж��������Ƿ��޸�  ������޸�  ����ʾ�Ƿ񱣴�δ��������
	_cur_form_data = $("#main_form").serializeJson(); 
	
	var r = equal(_init_form_data,_cur_form_data);
	//alert(JSON.stringify(_cur_form_data));
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

	  
