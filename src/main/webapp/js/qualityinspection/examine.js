//var proc;
//proc=this.parent.proc;
//alert(proc);
//var proc_id ;//= proc.procId;
var activName ;//= proc.activName;
var procdefId ;//= proc.procdefId;
var serialNumber ={num1:"�ǼǱ��",num2:"�鵵��",num3:"���ز�֤��"};
var _init_form_data;							//��ʼ��ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var _cur_form_data;								//��֤ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var option_type;								//��ǰ��������
var examine_hidden_element;
var state1 = {
		string1 : "���",
		string2 : "����������",
		string3 : "�а��˴���",
		string4 : "һ������",
		string5 : "��������",
		string6 : "����",
		string7 : "����",
		string8 : "���",
		string9 : "��׼"
	};
var title1 = {
		string1 : '���������������Ա��',
		string2 : '�����������׼��Ա��',
		string3 : '�������������쵼��',
		string4 : '��׼����������쵼��'
}

function examineInit(){
	//alert(" proc.procId"+ proc.procId);
	proc_id = proc.procId;
	activName = proc.activName;
	//activName = state1.string4;//����
	procdefId = proc.procdefId;
	option_type = proc.activName;
	//option_type = activName;//����
	//setTimeout(alert(proc),3000);
	
	focus();
    setState(activName);	
    getExamine();	
	    //��ȡ�����¼�
		//$('textarea').focus(function(){
			//focus();
		//});
		//ʧȥ�����¼�
		//$('textarea').blur(function(){
			//blur();
		//});   
}
//$(function(){
//			alert(proc);
//	        setState(activName);	
//	        getExamine();	
//      	    //��ȡ�����¼�
//       		$('textarea').focus(function(){
//       			focus();
//       		});
//       		//ʧȥ�����¼�
//       		$('textarea').blur(function(){
//       			blur();
//       		});   
//       });
//       
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
	saveExamine();
 };
//�������̽ڵ����ÿؼ�Ȩ��
function setState(activName){
	      if(activName == state1.string1){
		       inputDisable_Style("#fsyj");
		       inputDisable_Style("#shyj1");
		       inputDisable_Style("#yjspyj");
		       inputDisable_Style("#ejspyj");
	      }
	      else if(activName == state1.string2){
	    	  inputDisable_Style("#csyj");
		      inputDisable_Style("#shyj1");
		      inputDisable_Style("#yjspyj");
		      inputDisable_Style("#ejspyj");
	      }
	      else if(activName == state1.string3){
	    	  inputDisable_Style("#csyj");
	    	  inputDisable_Style("#fsyj");
	    	  inputDisable_Style("#yjspyj");
		      inputDisable_Style("#ejspyj");
		  }
	      else if(activName == state1.string4){
	    	  inputDisable_Style("#csyj");
	    	  inputDisable_Style("#fsyj");
	    	  inputDisable_Style("#shyj1");
		      inputDisable_Style("#ejspyj");
		  }
	      else if(activName == state1.string5){
	    	  inputDisable_Style("#csyj");
	    	  inputDisable_Style("#fsyj");
	    	  inputDisable_Style("#shyj1");
	    	  inputDisable_Style("#yjspyj");
		  }
	      else if(activName == state1.string6){
		       inputDisable_Style("#fsyj");
		       inputDisable_Style("#shyj1");
		       inputDisable_Style("#hzyj");
		       inputDisable_Style("#yjspyj");
		       inputDisable_Style("#ejspyj");
	      }
	      else if(activName == state1.string7){
	    	  inputDisable_Style("#csyj");
		      inputDisable_Style("#shyj1");
		      inputDisable_Style("#hzyj");
		      inputDisable_Style("#yjspyj");
		      inputDisable_Style("#ejspyj");
	      }
	      else if(activName == state1.string8){
	    	  inputDisable_Style("#csyj");
	    	  inputDisable_Style("#fsyj");
	    	  inputDisable_Style("#hzyj");
	    	  inputDisable_Style("#yjspyj");
		      inputDisable_Style("#ejspyj");
		  }
	      else if(activName == state1.string9){
	    	  inputDisable_Style("#csyj");
	    	  inputDisable_Style("#fsyj");
	    	  inputDisable_Style("#shyj1");
	    	  inputDisable_Style("#yjspyj");
	    	  inputDisable_Style("#ejspyj");
	      }
	      else{
	    	 // inputDisable_Style(":input").attr("disabled", "disabled");  
	    	  inputDisable_Style("#csyj");
	    	  inputDisable_Style("#fsyj");
		      inputDisable_Style("#shyj1");
		      inputDisable_Style("#hzyj");
		      inputDisable_Style("#yjspyj");
		      inputDisable_Style("#ejspyj");
	       }
	      /*(!(activName == state1.string1 
	    		  || activName == state1.string2 
	    		  || activName == state1.string3 
	    		  || activName == state1.string4 
	    		  || activName == state1.string5 
	    		  || activName == state1.string6 
	    		  || activName == state1.string7 
	    		  || activName == state1.string8))*/
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
				//��ȡ��ҳ���ֵ
				  $("#csyj").text(value);
				//  ��ȡ��ǰʱ��
			         $("#cssj").val(getTime());
			         //��ȡ��ǰ������
			        $("#csr").val(user); 
		 }
	   else if(activName == state1.string2){
				//��ȡ��ҳ���ֵ
				  $("#fsyj").text(value);
				//  ��ȡ��ǰʱ��
			         $("#fssj").val(getTime());
			         //��ȡ��ǰ������
			        $("#fsr").val(user); 
		 }
	   else if(activName == state1.string3){
				//��ȡ��ҳ���ֵ
				  $("#shyj").text(value);
				//  ��ȡ��ǰʱ��
			         $("#shsj").val(getTime());
			         //��ȡ��ǰ������
			        $("#shr").val(user); 
		 }
	   else if(activName == state1.string4){
				//��ȡ��ҳ���ֵ
				  $("#yjspyj").text(value);
				//  ��ȡ��ǰʱ��
			         $("#yjspsj").val(getTime());
			         //��ȡ��ǰ������
			        $("#yjspr").val(user); 
		 }
	   else if(activName == state1.string5){
				//��ȡ��ҳ���ֵ
				  $("#ejspyj").text(value);
				//  ��ȡ��ǰʱ��
			         $("#ejspsj").val(getTime());
			         //��ȡ��ǰ������
			        $("#ejspr").val(user); 
	   }
	   else if(activName == state1.string6){
			//��ȡ��ҳ���ֵ
			  $("#csyj").text(value);
			//  ��ȡ��ǰʱ��
		         $("#cssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#csr").val(user); 
	 }
     else if(activName == state1.string7){
			//��ȡ��ҳ���ֵ
			  $("#fsyj").text(value);
			//  ��ȡ��ǰʱ��
		         $("#fssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#fsr").val(user); 
	 }
     else if(activName == state1.string8){
			//��ȡ��ҳ���ֵ
			  $("#shyj1").text(value);
			//  ��ȡ��ǰʱ��
		         $("#shsj1").val(getTime());
		         //��ȡ��ǰ������
		        $("#shr1").val(user); 
	 }
     else if(activName == state1.string9){
    	 //��ȡ��ҳ���ֵ
    	 $("#hzyj").text(value);
    	 //  ��ȡ��ǰʱ��
    	 $("#hzsj").val(getTime());
    	 //��ȡ��ǰ������
    	 $("#hzr").val(user); 
     }
	         
};

//��������ȡ����ʱ�����浱ǰ�û��͵�ǰʱ��
function focus(){
	
	  if(activName == state1.string1){
		    
			//  ��ȡ��ǰʱ��
		         $("#cssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#csr").val(user); 
		        _init_form_data = $("#csyj").val();
	 }
	 else if(activName == state1.string2){
			//  ��ȡ��ǰʱ��
		         $("#fssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#fsr").val(user); 
	 }
	 else if(activName == state1.string3){
	
			//  ��ȡ��ǰʱ��
		         $("#shsj1").val(getTime());
		         //��ȡ��ǰ������
		        $("#shr1").val(user); 
	 }
	 else if(activName == state1.string4){
			
			//  ��ȡ��ǰʱ��
		         $("#yjspsj").val(getTime());
		         //��ȡ��ǰ������
		        $("#yjspr").val(user); 
	 }
	 else if(activName == state1.string5){
			
			//  ��ȡ��ǰʱ��
		         $("#ejspsj").val(getTime());
		         //��ȡ��ǰ������
		        $("#ejspr").val(user); 
	 }
	 else if(activName == state1.string6){
		    
			//  ��ȡ��ǰʱ��
		         $("#cssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#csr").val(user); 
		        _init_form_data = $("#csyj").val();
	 }
	 else if(activName == state1.string7){
			//  ��ȡ��ǰʱ��
		         $("#fssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#fsr").val(user); 
	 }
	 else if(activName == state1.string8){
	
			//  ��ȡ��ǰʱ��
		         $("#shsj1").val(getTime());
		         //��ȡ��ǰ������
		        $("#shr1").val(user); 
	 }
	 else if(activName == state1.string9){
		 
		 //  ��ȡ��ǰʱ��
		 $("#hzsj").val(getTime());
		 //��ȡ��ǰ������
		 $("#hzr").val(user); 
	 }
	
};




//��������ȡ����ʱ�����浱ǰ�û��͵�ǰʱ��

function blur(){
	
	  if(activName == state1.string1){
		    
			//  ��ȡ��ǰʱ��
		        $("#cssj").val(getTime());
		        //��ȡ��ǰ������
		        $("#csr").val(user); 
	 }
	  else if(activName == state1.string2){
			//  ��ȡ��ǰʱ��
		         $("#fssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#fsr").val(user); 
	 }
	  else if(activName == state1.string3){
	
			//  ��ȡ��ǰʱ��
		         $("#shsj").val(getTime());
		         //��ȡ��ǰ������
		        $("#shr").val(user); 
	 }
	  else if(activName == state1.string4){
			
			//  ��ȡ��ǰʱ��
		         $("#yjspsj").val(getTime());
		         //��ȡ��ǰ������
		        $("#yjspr").val(user); 
	 }
	  else if(activName == state1.string5){
			
			//  ��ȡ��ǰʱ��
		         $("#ejspsj").val(getTime());
		         //��ȡ��ǰ������
		        $("#ejspr").val(user); 
	 }
	  else if(activName == state1.string6){
		    
			//  ��ȡ��ǰʱ��
		        $("#cssj").val(getTime());
		        //��ȡ��ǰ������
		        $("#csr").val(user); 
	 }
	  else if(activName == state1.string7){
			//  ��ȡ��ǰʱ��
		         $("#fssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#fsr").val(user); 
	 }
	  else if(activName == state1.string8){
	
			//  ��ȡ��ǰʱ��
		         $("#shsj1").val(getTime());
		         //��ȡ��ǰ������
		        $("#shr1").val(user); 
	 }
	  else if(activName == state1.string8){
		  
		  //  ��ȡ��ǰʱ��
		  $("#hzsj").val(getTime());
		  //��ȡ��ǰ������
		  $("#hzr").val(user); 
	  }
};

//��ȡ��������Ϣ
function getExamine(){
	
	  //��ʼ����������Ϣ   
  	$.ajax({
   		    dataType: 'json',
   			url:ctx+"/common/exam!getExamineById.action?proc_id="+proc_id+"&time="+new Date(),
   			success:function(data){
   			 	if(data){
   			 		//alert($.toJSON(data));
   			 		for(var i=0;i<data.length;i++)
   			 		{
	   			 		if(data[i].opinion_type == state1.string1){
	   	   					$("#csyj").val(data[i].opinion_content);
	   	   					$("#cssj").val(data[i].opinion_timestr);
	   	   					$("#csr").val(data[i].checker_no);
	   	   				}else if(data[i].opinion_type == state1.string2){
	   	   					$("#fsyj").val(data[i].opinion_content);
	   	   					 $("#fssj").val(data[i].opinion_timestr);
	   	   					$("#fsr").val(data[i].checker_no);
	   	   				}else if(data[i].opinion_type == state1.string3){
	   	   					$("#shyj1").val(data[i].opinion_content);
	   	   					$("#shsj1").val(data[i].opinion_timestr);
	   	   					$("#shr1").val(data[i].checker_no);
	   	   				}else if(data[i].opinion_type == state1.string4){
	   	   					$("#yjspyj").val(data[i].opinion_content);
	   	   					 $("#yjspsj").val(data[i].opinion_timestr);
	   	   					$("#yjspr").val(data[i].checker_no);
	   	   				}else if(data[i].opinion_type == state1.string5){
	   	   					$("#ejspyj").val(data[i].opinion_content);
	   	   					$("#ejspsj").val(data[i].opinion_timestr);
	   	   					$("#ejspr").val(data[i].checker_no);
	   	   				}else if(data[i].opinion_type == state1.string6){
	   	   					$("#csyj").val(data[i].opinion_content);
	   	   					$("#cssj").val(data[i].opinion_timestr);
	   	   					$("#csr").val(data[i].checker_no);
	   	   				}else if(data[i].opinion_type == state1.string7){
	   	   					$("#fsyj").val(data[i].opinion_content);
	   	   					 $("#fssj").val(data[i].opinion_timestr);
	   	   					$("#fsr").val(data[i].checker_no);
	   	   				}else if(data[i].opinion_type == state1.string8){
	   	   					$("#shyj1").val(data[i].opinion_content);
	   	   					$("#shsj1").val(data[i].opinion_timestr);
	   	   					$("#shr1").val(data[i].checker_no);
	   			 		}else if(data[i].opinion_type == state1.string9){
	   			 			$("#hzyj").val(data[i].opinion_content);
	   			 			$("#hzsj").val(data[i].opinion_timestr);
	   			 			$("#hzr").val(data[i].checker_no);
	   			 		}
   			 		}
   			 		/*	if(data.examf){
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
		 				
		 			}*/
   			 	}
   			 _init_form_data = getexamineInitdata();
   				 
   			}
   		});
	
	
}



  


//�������
function saveExamine(){
	var vaResult = validate(1);
	//��֤��ͨ��
	if(!vaResult.result){
		top.$.messager.alert('��ʾ',vaResult.message,'info',null);
		return;
	}
	//alert(proc_id);
	//return;
	var yj ;//= $("#csyj").val()
	var sj ;//= $("#cssj").val()
	var r ;//= $("#csr").val()
	
	var result = true; 
	if(option_type == state1.string1){
		yj = $("#csyj").val()
		sj = $("#cssj").val()
		r = $("#csr").val()
	}else if(option_type == state1.string2){
		yj = $("#fsyj").val()
		sj = $("#fssj").val()
		r = $("#fsr").val()
	}else if(option_type == state1.string3){
		yj = $("#shyj1").val()
		sj = $("#shsj1").val()
		r = $("#shr1").val()
	}else if(option_type == state1.string4){
		yj = $("#yjspyj").val()
		sj = $("#yjspsj").val()
		r = $("#yjspr").val()
	}else if(option_type == state1.string5){
		yj = $("#ejspyj").val()
		sj = $("#ejspsj").val()
		r = $("#ejspr").val()
	}else if(option_type == state1.string6){
		yj = $("#csyj").val()
		sj = $("#cssj").val()
		r = $("#csr").val()
	}else if(option_type == state1.string7){
		yj = $("#fsyj").val()
		sj = $("#fssj").val()
		r = $("#fsr").val()
	}else if(option_type == state1.string8){
		yj = $("#shyj1").val()
		sj = $("#shsj1").val()
		r = $("#shr1").val()
	}else if(option_type == state1.string9){
		yj = $("#hzyj").val()
		sj = $("#hzsj").val()
		r = $("#hzr").val()
	}
	
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaine.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"examine.checker_no":r,"examine.opinion_content":yj,"examine.opinion_time":sj,"examine.opinion_type":option_type},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getexamineInitdata();
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
		var yj = $("#csyj").val();
		if($.trim(yj).length== 0){
			message = '�������������� ����Ϊ�գ���¼��������������� ��ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 
			 result.message=message;
			 result.result=false;
			 return result;
		}
		
	}else if(activName == state1.string2){
		var yj = $("#fsyj").val()
		if($.trim(yj).length== 0){
			message = '���Ҹ��������� ����Ϊ�գ���¼����Ҹ�����������ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}else if(activName == state1.string3){
		var yj = $("#shyj1").val()
		if($.trim(yj).length== 0){
			message = ' ������ ����Ϊ�գ���¼�� ������ ��ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}else if(activName == state1.string4){
		var yj = $("#yjspyj").val()
		if($.trim(yj).length== 0){
			message = ' һ��������� ����Ϊ�գ���¼�� һ��������� ��ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}else if(activName == state1.string5){
		var yj = $("#ejspyj").val()
		if($.trim(yj).length== 0){
			message = ' ���������������Ϊ�գ���¼�� ����������� ��ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}else if(activName == state1.string6){
		var yj = $("#csyj").val();
		if($.trim(yj).length== 0){
			message = '��������������� ����Ϊ�գ���¼���������������� ��ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 
			 result.message=message;
			 result.result=false;
			 return result;
		}
		
	}else if(activName == state1.string7){
		var yj = $("#fsyj").val()
		if($.trim(yj).length== 0){
			message = '��������������� ����Ϊ�գ���¼�븴������������� ��ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}else if(activName == state1.string8){
		var yj = $("#shyj1").val()
		if($.trim(yj).length== 0){
			message = '��˽����������� ����Ϊ�գ���¼����˽����������� ��ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}else if(activName == state1.string9){
		var yj = $("#hzyj").val()
		if($.trim(yj).length== 0){
			message = '��׼������������ ����Ϊ�գ���¼���׼������������ ��ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}
	//���Ǳ���������ж�  ҳ�������޸�
	if(!v_flag){
	//�ж��������Ƿ��޸�  ������޸�  ����ʾ�Ƿ񱣴�δ��������
		_cur_form_data = getexamineInitdata(); 
		
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
	}else{
		_init_form_data = getexamineInitdata(); 
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
/**********************************************************************************
*��������: getexamineInitdata
*����˵��: ��ʼ����ǰ���ʱ   ��ȡ��ʼֵ   �����뿪ҳ��ʱ�����Ƿ񱣴�
*����˵��: ���ص�ǰ�������
*�� �� ֵ: ���޸ķ���true   δ�޸ķ���false
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function getexamineInitdata(){
	var result;
	  if(activName == state1.string1){
		    
			//  ��ȡ��ǰʱ��
		         $("#cssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#csr").val(user); 
		        result = $("#csyj").val();
	 }
	  else if(activName == state1.string2){
			//  ��ȡ��ǰʱ��
		         $("#fssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#fsr").val(user); 
		        result = $("#fsyj").val();
	 }
	  else if(activName == state1.string3){
	
			//  ��ȡ��ǰʱ��
		         $("#shsj1").val(getTime());
		         //��ȡ��ǰ������
		        $("#shr1").val(user); 
		        result = $("#shyj1").val();
	 }
	  else if(activName == state1.string4){
			//  ��ȡ��ǰʱ��
			 $("#yjspsj").val(getTime());
			 //��ȡ��ǰ������
			$("#yjspr").val(user); 
			result = $("#yjspyj").val();
	 }
	  else if(activName == state1.string5){
	
			//  ��ȡ��ǰʱ��
		 $("#ejspsj").val(getTime());
		 //��ȡ��ǰ������
		$("#ejspr").val(user); 
		result = $("#ejspyj").val();
	}
	  else if(activName == state1.string6){
	    
		//  ��ȡ��ǰʱ��
	         $("#cssj").val(getTime());
	         //��ȡ��ǰ������
	        $("#csr").val(user); 
	        result = $("#csyj").val();
	 }
	  else if(activName == state1.string7){
			//  ��ȡ��ǰʱ��
		         $("#fssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#fsr").val(user); 
		        result = $("#fsyj").val();
	 }
	  else if(activName == state1.string8){
	
			//  ��ȡ��ǰʱ��
		         $("#shsj1").val(getTime());
		         //��ȡ��ǰ������
		        $("#shr1").val(user); 
		        result = $("#shyj1").val();
	 }
	  else if(activName == state1.string9){
		  
		  //  ��ȡ��ǰʱ��
		  $("#hzsj").val(getTime());
		  //��ȡ��ǰ������
		  $("#hzr").val(user); 
		  result = $("#hzyj").val();
	  }
	 return result;
}

function examineHiddenElement(){
	if(!jds_sqb_tw){
		jds_sqb_tw = getChinaTip($.getUrlParam('jds_sqs_tw'));
	}
	$('#handling_suggestion01').text(title1.string1);
	$('#handling_suggestion02').text(title1.string2);
	$('#handling_suggestion03').text(title1.string3);
	if(jds_sqb_tw == enumdata.BACKLANGUAGESQS){
		$('#handling_opinions01').css('display','none');
	}else if(jds_sqb_tw == enumdata.DELAYSQS){
		$('#handling_opinions01').css('display','none');
	}else if(jds_sqb_tw == enumdata.HANGUPSQS){
		$('#handling_opinions01').css('display','none');
	}
	if(jds_sqb_tw == enumdata.REJECTJDS){
		$('#handling_opinions02').css('display','none');
	}else{
		$('#handling_opinions02').css('display','block');
	}
	$('#handling_suggestion04').text(title1.string4);
	$('#handling_opinions03').css('display','none');
	$('#submit').css('display','none');
	$('#cancel').css('display','none');
}
function inputDisable_Style(val){
	$(val).attr("disabled","disabled");
}
	  
