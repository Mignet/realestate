/*********************************************************************************
*��  ��  ��  ��: mortacc.js
*��  ��  ��  ��: ��ѺȨ������js
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: panda
*��  ��  ��  �ڣ� 2014-02-28
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/

var proc;
proc=this.parent.proc;

var proc_id = proc.procId;
var procdefId = proc.procdefId;
//var proc_id = 1000016371;
//var activName="��׼";
//var procdefId="1244";
var activName = proc.activName;

var _init_form_data;							//��ʼ��ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var _cur_form_data;								//��֤ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var state1 = {
		string0: "����",
		string1 : "����",
		string2 : "����",
		string3 : "���",
		string4 : "��׼",
		string5 : "������",
		string6 : "������",
		string7: "����������",
		string8 : "����������",		
		string9 : "��֤",
		string10 : "��֤",
		string11: "�鵵",
		String12:"�ⶨ����"
	};
/**********************************************************************************
*��������: 
*����˵��: ҳ���ʼ��
*��������: panda
*��������: 2014-02-28
*�޸���ʷ: 
***********************************************************************************/
$(function(){
	
	        setState(activName);	
       		//wiId=GetQueryString("wiId");
	        getExam();	
    	    //��ȡ�����¼�
       		$('textarea').focus(function(){
       			focus();
       		});
       		//ʧȥ�����¼�
       		
       		$('textarea').blur(function(){
       			blur();
       		});   
       
       });     
/**********************************************************************************
*��������: submit
*����˵��: ���ύ����
*����˵��: ��
*�� �� ֵ: ��
*��������: panda
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function submit(){  
	
	if(activName == state1.string1){
		saveExamFirst();
	  
	   };
	   if(activName == state1.string4){
		   saveExamForth();
	   };


 };
 /**********************************************************************************
	*��������: setState
	*����˵��: �������̽ڵ㣬����ҳ��ؼ���Ȩ��
	*����˵��: proc_node�����̽ڵ�����
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
	***********************************************************************************/
function setState(activName){
	       
	      if(activName == state1.string1){
	       
	      // $("#fsyj").attr("disabled","disabled");
	      // $("#shyj").attr("disabled","disabled");
	       $("#hzyj").attr("disabled","disabled");
	      // $("#fscyy").attr("disabled","disabled");
	      // $("#shcyy").attr("disabled","disabled");
	       $("#hzcyy").attr("disabled","disabled");
	      };
	      if(activName == state1.string2){
//	    	  $("#shyj").attr("disabled","disabled");
//	    	  $("#shcyy").attr("disabled","disabled");
//	       $("#csyj").attr("disabled","disabled");
//	       $("#hzyj").attr("disabled","disabled");
//	       $("#cscyy").attr("disabled","disabled");
//	       $("#hzcyy").attr("disabled","disabled");
	      };
	      if(activName == state1.string3){
//	    	  $("#fsyj").attr("disabled","disabled");
//	    	  $("#fscyy").attr("disabled","disabled");
//		       $("#csyj").attr("disabled","disabled");
//		       $("#hzyj").attr("disabled","disabled");
//		       $("#cscyy").attr("disabled","disabled");
//		       $("#hzcyy").attr("disabled","disabled");
		      };
	      if(activName == state1.string4){
	       
	       $("#csyj").attr("disabled","disabled");
	      // $("#shyj").attr("disabled","disabled");
	     //  $("#fsyj").attr("disabled","disabled");
	       $("#cscyy").attr("disabled","disabled");
	      // $("#shcyy").attr("disabled","disabled");
	      // $("#fscyy").attr("disabled","disabled");
	      };
	      if(!(activName == state1.string1 || activName == state1.string2 ||activName == state1.string3 ||activName == state1.string4)){
	    	  $(":input").attr("disabled", "disabled");  
	    	  
	    	  
	      }
	  
	  
	  }
/**********************************************************************************
*��������: getTime
*����˵��: ��ȡ��ǰϵͳʱ��
*����˵��:
*�� �� ֵ: ��
*��������: panda
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
*��������: selectCyy
*����˵��: ѡ������
*����˵��: 
*�� �� ֵ: ��
*��������: panda
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
 function selectCyy(){	 
	 openInTopWindow({
			// ����Ԫ�ص�id
			id : 'comlan',
			// ����iframe��src
			src : ctx+'/jsp/common/comlangu/lanselect.jsp?time='+new Date(),
			// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy : true,
			// ���ڱ���
			title : 'ѡ������',
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
 /**********************************************************************************
		 *��������: focus
		 *����˵��: ��������ȡ����ʱ�����浱ǰ�û��͵�ǰʱ��
		 *����˵��: 
		 *�� �� ֵ: ��
		 *��������: panda
		 *��������: 2014-03-01
		 *�޸���ʷ: 
***********************************************************************************/
function focus(){
	
	  if(activName == state1.string1){
		    
			//  ��ȡ��ǰʱ��
		         $("#cssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#csr").val(user); 
	 };
	 if(activName == state1.string4){
			
			//  ��ȡ��ǰʱ��
		         $("#hzsj").val(getTime());
		         //��ȡ��ǰ������
		        $("#hzr").val(user); 
	 };
	
};

/**********************************************************************************
 *��������: blur
 *����˵��: ��������ȡ����ʱ�����浱ǰ�û��͵�ǰʱ��
 *����˵��: 
 *�� �� ֵ: ��
 *��������: panda
 *��������: 2014-03-01
 *�޸���ʷ: 
***********************************************************************************/
function blur(){
	
	  if(activName == state1.string1){
		    
			//  ��ȡ��ǰʱ��
		         $("#cssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#csr").val(user); 
	 };
	 if(activName == state1.string4){
			
			//  ��ȡ��ǰʱ��
		         $("#hzsj").val(getTime());
		         //��ȡ��ǰ������
		        $("#hzr").val(user); 
	 };
	
};
/**********************************************************************************
 *��������: getExam
 *����˵��: ��ȡ��������Ϣ
 *����˵��: 
 *�� �� ֵ: ��
 *��������: panda
 *��������: 2014-03-01
 *�޸���ʷ: 
***********************************************************************************/
function getExam(){
	
	  //��ʼ����������Ϣ   
  	$.ajax({
   		    dataType: 'json',
   			url:ctx+"/common/exam!getExamByid.action?proc_id="+proc_id+"&time="+new Date(),
   			success:function(data){
   			 	if(data){
   			 			if(data.examf){
   			 			var cssj = data.examf.opinion_time;
                        if(cssj != null){
                       	 $("#cssj").val(cssj.substr(0,cssj.length-2));  
                        }
   			 			
   			 		    $("#csyj").val(data.examf.opinion_content);
   			 		    $("#csr").val(data.examf.checker_no);
   			 				
   			 			}
   			       if(data.examfo){
   			    	var hzsj = data.examfo.opinion_time;
					 if(hzsj != null){
						 $("#hzsj").val(hzsj.substr(0,hzsj.length-2)); 
						 
					 }
		 		    $("#hzyj").val(data.examfo.opinion_content);
		 		    $("#hzr").val(data.examfo.checker_no);
		 				
		 			}
   			 	}
   			 _init_form_data = $("#main_form").serializeJson(); 
   			}
   		});
	
	
}
/**********************************************************************************
 *��������: saveExamFirst
 *����˵��: ����������
 *����˵��: 
 *�� �� ֵ: ��
 *��������: panda
 *��������: 2014-03-01
 *�޸���ʷ: 
***********************************************************************************/
function saveExamFirst(){
	var yj = $("#csyj").val()
	var sj = $("#cssj").val()
	var r = $("#csr").val()
	if($.trim(yj).length== 0){
		
		 top.$.messager.confirm('��ʾ','�����������Ϊ�գ���¼����������ѡ�����',function(){});
		
	}else{
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaFirst.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"exf.checker_no":r,"exf.opinion_content":yj,"exf.opinion_time":sj,"exf.opinion_type":activName},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getExam();
			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
				}
	   		}
	   	});
	}
}
/**********************************************************************************
 *��������: saveExamForth
 *����˵��: �����׼���
 *����˵��: 
 *�� �� ֵ: ��
 *��������: panda
 *��������: 2014-03-01
 *�޸���ʷ: 
***********************************************************************************/
function saveExamForth(){
	var yj = $("#hzyj").val()
	var sj = $("#hzsj").val()
	var r = $("#hzr").val()
	if($.trim(yj).length== 0){
		
		 top.$.messager.confirm('��ʾ','��׼�������Ϊ�գ���¼���׼�����ѡ�����',function(){});
		
	}else{
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaForth.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"exfo.checker_no":r,"exfo.opinion_content":yj,"exfo.opinion_time":sj,"exfo.opinion_type":activName},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getExam();
			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
				}
	   		}
	   	});
	}
}
/**********************************************************************************
*��������: validate()
*����˵��: ����У�鷽��  ����ʱ��֤ �������ڷ���true
*����˵��: 
*�� �� ֵ: obj  result(trueͨ��  false��ͨ��) message(��Ϣ)  page_name(��ǰҳ������)
*��������: xuzz
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
		var yj = $("#csyj").val();
		if($.trim(yj).length== 0){
			message = '�����������Ϊ�գ���¼����������ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 
			 result.message=message;
			 result.result=false;
			 return result;
		}
		
	}
	if(activName == state1.string4){
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


	  
