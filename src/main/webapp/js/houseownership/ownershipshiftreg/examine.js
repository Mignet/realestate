/*********************************************************************************
*��  ��  ��  ��: examine.js
*��  ��  ��  ��: ������js
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Joyon
*��  ��  ��  �ڣ� 2014-03-01
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
var proc;
proc=this.parent.proc;

var proc_id = proc.procId;


var activName = proc.activName;

var serialNumber ={num1:"�ǼǱ��",num2:"�鵵��",num3:"���ز�֤��"};
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
*����˵��: js����ʱ����  
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
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
*��������: submit()
*����˵��: ���������²����ÿؼ�Ȩ��
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function submit(){  
	
//	var result = validate();
//	if(!result.result){
//		return false;
//	}
	
		if(activName == state1.string1){
			return saveExamFirst();
	  
	   };
	   if(activName == state1.string2){
		   return saveExamSecond();
		  
	   };
	   if(activName == state1.string3){
		 // return  saveExamThird();
		 
	   };
	   if(activName == state1.string4){
		  return saveExamForth();
	   };


 };
/**********************************************************************************
*��������: setState()
*����˵��: �������̽ڵ����ÿؼ�Ȩ��
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function setState(activName){
	       
	      if(activName == state1.string1){
	       
	       $("#fsyj").attr("disabled","disabled");
	       $("#shyj").attr("disabled","disabled");
	       $("#hzyj").attr("disabled","disabled");
	       $("#fscyy").attr("disabled","disabled");
	       $("#shcyy").attr("disabled","disabled");
	       $("#hzcyy").attr("disabled","disabled");
	       
	      //$("#csfjsc").removeAttr("disabled");
	      };
	      if(activName == state1.string2){
	    	  $("#shyj").attr("disabled","disabled");
	    	  $("#shcyy").attr("disabled","disabled");
	       $("#csyj").attr("disabled","disabled");
	       $("#hzyj").attr("disabled","disabled");
	       $("#cscyy").attr("disabled","disabled");
	       $("#hzcyy").attr("disabled","disabled");
	       
	       //$("#fsfjsc").removeAttr("disabled");
	      };
	      if(activName == state1.string3){
	    	  $("#fsyj").attr("disabled","disabled");
	    	  $("#fscyy").attr("disabled","disabled");
		       $("#csyj").attr("disabled","disabled");
		       $("#hzyj").attr("disabled","disabled");
		       $("#cscyy").attr("disabled","disabled");
		       $("#hzcyy").attr("disabled","disabled");
		      };
	      if(activName == state1.string4){
	       
	       $("#csyj").attr("disabled","disabled");
	       $("#shyj").attr("disabled","disabled");
	       $("#fsyj").attr("disabled","disabled");
	       $("#cscyy").attr("disabled","disabled");
	       $("#shcyy").attr("disabled","disabled");
	       $("#fscyy").attr("disabled","disabled");
	       
	       //$("#hjfjsc").removeAttr("disabled");
	      };
	      if(!(activName == state1.string1 || activName == state1.string2 ||activName == state1.string3 ||activName == state1.string4)){
	    	  $(":input").attr("disabled", "disabled");  
	      }
}
/**********************************************************************************
*��������: getTime()
*����˵��: ��ȡϵͳ��ǰʱ����
*����˵��: 
*�� �� ֵ: 
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
*��������: selectCyy()
*����˵��: ѡ������
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
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
			this.init(activName);
		}
	});
 
 
}; 
		
/**********************************************************************************
*��������: getNewLinkValue()
*����˵��: ��ȡ��ҳ���ֵ�͵�ǰʱ��
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function  getNewLinkValue(value){
	   if(activName == state1.string1){
				//��ȡ��ҳ���ֵ
				  $("#csyj").text(value);
				//  ��ȡ��ǰʱ��
			         $("#cssj").val(getTime());
			         //��ȡ��ǰ������
			        $("#csr").val(user); 
		 };
		 if(activName == state1.string2){
				//��ȡ��ҳ���ֵ
				  $("#fsyj").text(value);
				//  ��ȡ��ǰʱ��
			         $("#fssj").val(getTime());
			         //��ȡ��ǰ������
			        $("#fsr").val(user); 
		 };
		 if(activName == state1.string3){
				//��ȡ��ҳ���ֵ
				  $("#shyj").text(value);
				//  ��ȡ��ǰʱ��
			         $("#shsj").val(getTime());
			         //��ȡ��ǰ������
			        $("#shr").val(user); 
		 };
		 if(activName == state1.string4){
				//��ȡ��ҳ���ֵ
				  $("#hzyj").text(value);
				//  ��ȡ��ǰʱ��
			         $("#hzsj").val(getTime());
			         //��ȡ��ǰ������
			        $("#hzr").val(user); 
		 };
	         
};

/**********************************************************************************
*��������: focus()
*����˵��: ��������ȡ����ʱ�����浱ǰ�û��͵�ǰʱ��
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
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
*��������: blur()
*����˵��: ��������ȡ����ʱ�����浱ǰ�û��͵�ǰʱ��
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
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
*��������: getExam()
*����˵��: ��ȡ��������Ϣ���
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
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
   			 		if(data.exams){
   			 			
   			 		var fssj =data.exams.opinion_time;
					 if(fssj != null){
						 $("#fssj").val(fssj.substr(0,fssj.length-2));  
					 }
   			 		    $("#fsyj").val(data.exams.opinion_content);
   			 		    $("#fsr").val(data.exams.checker_no);
   			 				
   			 			}
   			 	   if(data.examt){
   			 		 var shsj = data.examt.opinion_time;
					 if(shsj != null){
						 $("#shsj").val(shsj.substr(0,shsj.length-2));  
						 
					 }
			 		    $("#shyj").val(data.examt.opinion_content);
			 		    $("#shr").val(data.examt.checker_no);
			 				
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
*��������: saveExamFirst()
*����˵��: ����������
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function saveExamFirst(){
	var result = true;
	var yj = $("#csyj").val()
	var sj = $("#cssj").val()
	var r = $("#csr").val()
	
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaFirst.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"exf.checker_no":r,"exf.opinion_content":yj,"exf.opinion_time":sj,"exf.opinion_type":"����"},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getExam();
			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
				}
	   		},error:function(data){
	   			result = false;
	   			top.$.messager.alert('����ʧ����ʾ',"����ʧ��"+data,'error');
	   		}
	   	});
	
	
	return result;
}

/**********************************************************************************
*��������: saveExamSecond()
*����˵��: ���渴�����
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function saveExamSecond(){
	var result = true;
	var yj = $("#fsyj").val()
	var sj = $("#fssj").val()
	var r = $("#fsr").val()
	
	
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaSecond.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"exs.checker_no":r,"exs.opinion_content":yj,"exs.opinion_time":sj,"exs.opinion_type":"����"},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getExam();
			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
				}
	   		},error:function(data){
	   			result = false;
	   			top.$.messager.alert('����ʧ����ʾ',"����ʧ��"+data,'error');
	   		}
	   	});
	return result;
}
/**********************************************************************************
*��������: saveExamThird()
*����˵��: ����������
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function saveExamThird(){
	var yj = $("#shyj").val()
	var sj = $("#shsj").val()
	var r = $("#shr").val()
	if($.trim(yj).length== 0){
		
		 top.$.messager.confirm('��ʾ','����������Ϊ�գ���¼����������ѡ�����',function(){});
		
	}else{
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaThird.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"ext.checker_no":r,"ext.opinion_content":yj,"ext.opinion_time":sj,"ext.opinion_type":"���"},
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
	};
}

/**********************************************************************************
*��������: saveExamForth()
*����˵��: �����׼���
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function saveExamForth(){
	var result = true;
	var yj = $("#hzyj").val()
	var sj = $("#hzsj").val()
	var r = $("#hzr").val()
	
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaForth.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"exfo.checker_no":r,"exfo.opinion_content":yj,"exfo.opinion_time":sj,"exfo.opinion_type":"��׼"},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getExam();
			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
				}
	   		},error:function(data){
	   			result = false;
	   			top.$.messager.alert('����ʧ����ʾ',"����ʧ��"+data,'error');
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
		var yj = $("#csyj").val();
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
	}if(activName == state1.string4){
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
	if(!r){
		var flag= 0 ;//����ȷ�� �Ƿ��û��Ѿ������������  δ���  ���������������     ����false
		message = '���ݼ��޸ģ����ȱ�����ύ��';
//		 $.messager.confirm('����ȷ��',message,function(r){  
//			    if (r){  
//			    	result.message=message;
//			    	flag=1;
//			    }else{
//			    	 result.message=message;
//					 result.result=false;
//					 flag=1;
//			    }
//		 });
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
*��������: uploadFile
*����˵��: �ϴ�����  �����ϴ�ҳ��
*����˵��: 
*�� �� ֵ: ���޸ķ���true   δ�޸ķ���false
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function uploadFile(proc_node){
	proc.openNode = proc_node;		//������ʶ������ĸ����ڵĸ���
	 openInTopWindow({
			// ����Ԫ�ص�id
			id : 'upload_window_id',
			// ����iframe��src
			src : ctx+'/jsp/fileupload/uploadFile.jsp',
			// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy : true,
			// ���ڱ���
			title : proc_node+'����',
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
				this.init(proc);
			}
		});
	//window.open(ctx+'/fileupload/uploadFile.action', proc_node+'�����ϴ�', 'height=480, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=true,location=n o, status=no');
}

/**********************************************************************************
*��������: getUploadFile
*����˵��: ��ȡ��û��ڹ����ĸ���
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function getUploadFile(proc_node){
	$.ajax({
   		dataType:'json',
   		url:ctx+"/common/filerel!getFileRelView.action?time="+new Date(),
   		contentType:"application/x-www-form-urlencoded; charset=GBK",
   		//�������л�����
   		data:{proc_id:proc.procId,proc_node:proc_node},
   		success:function(data){
   			//alert($.toJSON(data));
		 	if(data.idList){
		 		//location.href= ctx+'/commons/fileupload/upload.do?method=imgView&uploadId='+data.uploadId+'&type=flash&idList='+data.idList+'&index=1#p=1';
		 		
		 		 openInTopWindow({
		 			// ����Ԫ�ص�id
		 			id : 'upload_pre_window',
		 			// ����iframe��src
		 			src : ctx+'/commons/fileupload/upload.do?method=imgView&uploadId='+data.uploadId+'&type=flash&idList='+data.idList+'&index=1#p=1',
		 			// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
		 			destroy : true,
		 			// ���ڱ���
		 			title : proc_node+'�����鿴',
		 			// ���ڿ�
		 			width : 1050,
		 			// ���ڸ�
		 			height : 600,
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
		 			}
		 		});
		 		
		 	}else {
				top.$.messager.alert('��ʾ',data.errorMessage,'info');
			}
   		},error:function(data){
   			result = false;
   			top.$.messager.alert('����ʧ����ʾ',"����ʧ��"+data,'error');
   		}
   		
   	
   	});
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
	//if(proc_node == state1.string0 || proc_node == state1.string1){
		_cur_form_data = $("#main_form").serializeJson(); 
		
		var r = equal(_init_form_data,_cur_form_data);
		//�������ȷ���  ҳ�����ݼ��޸�  ����true
		if(!r){
		  return true;
		}
	//}
	return false;
}

	  
