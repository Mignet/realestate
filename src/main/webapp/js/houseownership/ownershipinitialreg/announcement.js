var proc;
proc=this.parent.proc;

var proc_id = proc.procId;
//var proc_id = 1000016427;
var activName = proc.activName;
//var activName = "����";
var procdefId = proc.procdefId;
//���̽ڵ㼯��
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
		string12: "�ⶨ����",
		string13: "����"
	};
//��ʼ����ʼ
$(function(){
	setState(activName);
	getAnnounce();  
	if(activName == state1.string13){
		
		$("#ggry").val(user);
		
	}
	
   
      	
        //��ȡ�����¼�
   		$('#ggnr').focus(function(){
   		
   			focus();
   		});
   		$('#ggcsyj').focus(function(){
   			focus();
   		});
   		$('textarea#ggfsyj').focus(function(){
   			focus();
   		});
   		$('textarea#ggshyj').focus(function(){
   			focus();
   		});
   		$('textarea#gghzyj').focus(function(){
   			focus();
   		});
   		
   		//ʧȥ�����¼� 		
   		$('textarea#ggnr').blur(function(){
   			blur();
   		});
   		$('textarea#ggcsyj').blur(function(){
   			blur();
   		});
   		$('textarea#ggfsyj').blur(function(){
   			blur();
   		});
   		$('textarea#ggshyj').blur(function(){
   			blur();
   		});
   		$('textarea#gghzyj').blur(function(){
   			blur();
   		});
   		
   		
 }); //��ʼ������  
//��ȡ��ַ������
function GetQueryString(name)
{
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r!=null) return unescape(r[2]);
	return null;
}

//���������²���
function submit(){
	 if(activName == state1.string12){
			
		   saveAnnounce();
	   };
	   if(activName == state1.string13){
			//alert("���湫����Ϣ");
		   saveAnnouncePub();
	   };
	   if(activName == state1.string5){
		
		   saveExamFirst();
	   };
	   if(activName == state1.string6){
		   saveExamSecond();
	   };
	   if(activName == state1.string7){
		   saveExamThird();
	   };
	   if(activName == state1.string8){
		   saveExamForth();
	   };
    
	   return true;
};
 //�������̽ڵ����ÿؼ�Ȩ��
function setState(activName){
	    // alert(activName);
	      $(":input").attr("disabled", "disabled");   
	       if(activName == state1.string13){
	    	   $(".test").removeAttr("disabled");
	    	   $(".combo-text").removeAttr("disabled");
	    	   $(".spinner-text").removeAttr("disabled");
	    	   
	    	  
	    	   
	       }
	      if(activName == state1.string5){
	    	   
	    	  $("#ggcsyj").removeAttr("disabled");
	    	  $("#ggcscyy").removeAttr("disabled");
	    	  $("#ggsj").combo("disable");
	    	  $("#fbsj").combo("disable");
	    	  $("#cbwdzrq").combo("disable");
	    	
	      };
	      if(activName == state1.string6){
	    	   
	    	  $("#ggfsyj").removeAttr("disabled");
	    	  $("#ggfscyy").removeAttr("disabled");
	    	  $("#ggsj").combo("disable");
	    	  $("#fbsj").combo("disable");
	    	  $("#cbwdzrq").combo("disable");
	      };
	      if(activName == state1.string7){
		       
	    	  
	    	  $("#ggshyj").removeAttr("disabled");
	    	  $("#ggshcyy").removeAttr("disabled");
	    	  $("#ggsj").combo("disable");
	    	  $("#fbsj").combo("disable");
	    	  $("#cbwdzrq").combo("disable");
		      };
	      if(activName == state1.string8){
	       
	    	   
	    	  $("#gghzyj").removeAttr("disabled");
	    	  $("#gghzcyy").removeAttr("disabled");
	    	  $("#ggsj").combo("disable");
	    	  $("#fbsj").combo("disable");
	    	  $("#cbwdzrq").combo("disable");
	      };
	      if(activName == state1.string12){
	    	 
	    	  $("#ggnr").removeAttr("disabled");
	    	  $("#ggmb").removeAttr("disabled");
	    	  $("#ggsj").combo("disable");
	    	  $("#fbsj").combo("disable");
	    	  $("#cbwdzrq").combo("disable");
	      };
	      if(!(activName == state1.string5 || activName == state1.string6 ||activName == state1.string7 ||activName == state1.string8 || activName == state1.string12 ||activName == state1.string13)){
	    	  $(":input").attr("disabled", "disabled"); 
	    	  $("#ggsj").combo("disable");
	    	  $("#fbsj").combo("disable");
	    	  $("#cbwdzrq").combo("disable");
	    	  
	    	  
	      }
	  
	  };
//��ȡϵͳ��ǰʱ��
	  function getTime(){
	  var myDate = new Date();
	  var year = myDate.getFullYear();
	  var month = myDate.getMonth()+1;
	  var date = myDate.getDate(); 
      var mytime=myDate.toLocaleTimeString(); //��ȡ��ǰʱ��	  
	  var time = year+"-"+month+"-"+date+" "+mytime;
	  return time;
 };

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
//ѡ�񹫸�ģ��
function selectGgmb(){
	 //alert("ѡ������");
	// window.open(ctx+"/houseownership/ownershipinitialreg/announceselect.action","newindow","width=600,height=400,toolbar=no,scrollbars=no");
	 openInTopWindow({
			// ����Ԫ�ص�id
			id : 'anmodel',
			// ����iframe��src
			src : ctx+'/jsp/houseownership/ownershipinitialreg/announce-select.jsp?time='+new Date(),
			// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy : true,
			// ���ڱ���
			title : 'ѡ�񹫸�ģ��',
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
	 
} 

//��������ȡ����ʱ�����浱ǰ�û��͵�ǰʱ��
function focus1(){
	if(activName == state1.string5){
		
		//  ��ȡ��ǰʱ��
	         $("#ggndsj").val(getTime());
	         //��ȡ��ǰ������
	        $("#ggndr").val(user); 
	        //  ��ȡ��ǰʱ��
	        
 };
	
}
function focus(){
if(activName == state1.string12){
		
		//  ��ȡ��ǰʱ��
	         $("#ggndsj").val(getTime());
	         //��ȡ��ǰ������
	        $("#ggndr").val(user); 
	        //  ��ȡ��ǰʱ��
	        
 };
	
	 if(activName == state1.string5){
			
			//  ��ȡ��ǰʱ��
		         $("#ggcssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#ggcsr").val(user); 
		    
	 };
	 if(activName == state1.string6){
			
			//  ��ȡ��ǰʱ��
		         $("#ggfssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#ggfsr").val(user); 
	 };
	 if(activName == state1.string7){
			
			//  ��ȡ��ǰʱ��
		         $("#ggshsj").val(getTime());
		         //��ȡ��ǰ������
		        $("#ggshr").val(user); 
	 };
	 if(activName == state1.string8){
			//  ��ȡ��ǰʱ��
		         $("#gghzsj").val(getTime());
		         //��ȡ��ǰ������
		        $("#gghzr").val(user); 
	 };
};




//��������ȡ����ʱ�����浱ǰ�û��͵�ǰʱ��
function blur1(){
	if(activName == state1.string5){
		
		//  ��ȡ��ǰʱ��
	         $("#ggndsj").val(getTime());
	         //��ȡ��ǰ������
	        $("#ggndr").val(user); 
	        //  ��ȡ��ǰʱ��
	        
 };
	
}

function blur(){
	if(activName == state1.string12){
		
		//  ��ȡ��ǰʱ��
	         $("#ggndsj").val(getTime());
	         //��ȡ��ǰ������
	        $("#ggndr").val(user); 
	        //  ��ȡ��ǰʱ��
	        
 };
	
	 if(activName == state1.string5){
			
			//  ��ȡ��ǰʱ��
		         $("#ggcssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#ggcsr").val(user); 
		        //  ��ȡ��ǰʱ��
		        
	 };
	 if(activName == state1.string6){
			
			//  ��ȡ��ǰʱ��
		         $("#ggfssj").val(getTime());
		         //��ȡ��ǰ������
		        $("#ggfsr").val(user); 
	 };
	 if(activName == state1.string7){
			
			//  ��ȡ��ǰʱ��
		         $("#ggshsj").val(getTime());
		         //��ȡ��ǰ������
		        $("#ggshr").val(user); 
	 };
	 if(activName == state1.string8){
			//  ��ȡ��ǰʱ��
		         $("#gghzsj").val(getTime());
		         //��ȡ��ǰ������
		        $("#gghzr").val(user); 
	 };
	
};
//��ȡ������Ϣ
function getAnnounce(){
	
	$.ajax({
		    dataType: 'json',
			url:ctx+"/common/exam!getAnnounceByid.action?proc_id="+proc_id+"&time="+new Date(),
			success:function(data){
				if(data){
					 if(data.annouce){
						 var ggndsj = data.annouce.notice_pro_time;
						 if(ggndsj != null){
						 $("#ggndsj").val(ggndsj.substr(0,ggndsj.length-2));
						 }
						 $("#ggndr").val(data.annouce.notice_pro_person);
		   			 	 $("#ggnr").val(data.annouce.notice_content);
		   			 	 $("#ggbh").val(data.annouce.notice_code);
		   			 	 if(data.annouce.notice_person){
		   			 		 
		   			 		$("#ggry").val(data.annouce.notice_person); 
		   			 		 
		   			 	 }else{
		   			 		//$("#ggry").val(user); 
		   			 		 
		   			 	 }
		   			 	 						 
		   			 	 $("#ggqx").val(data.annouce.notice_limit);
		   			 	 var ggsj = data.annouce.noticie_date;
		   			 	 if(ggsj != null){
		   			 		//$("#ggsj").val(ggsj.substr(0,ggsj.length-2)); 
		   			 	   $('#ggsj').datetimebox('setValue',ggsj.substr(0,ggsj.length-2));
		   			 		 
		   			 	 }
		   			 	 
		   			 	 $("#fbdw").val(data.annouce.notice_pub_off);
		   			 	
		   			 	 
		   			 	 var fbsj = data.annouce.notice_pub_time;
		   			 	 if(fbsj != null){
		   			 		//$("#fbsj").val(fbsj.substr(0,fbsj.length-2));
		   			 	$('#fbsj').datetimebox('setValue',fbsj.substr(0,fbsj.length-2));
		   			// $('#fbsj').datetimebox('setValue', '6/1/2012 12:30:56');	
		   			 		 
		   			 	 }
		   			 	 		   		 
		   			 	 $("#cbwmc").val(data.annouce.pub_name_date);
		   			 	 var cbsj = data.annouce.pub_date;
		   			 	 if(cbsj != null){
		   			 		 
		   			 		//$("#cbwdzrq").val(cbsj.substr(0,cbsj.length-2)); 
		   			 		$('#cbwdzrq').datetimebox('setValue',cbsj.substr(0,cbsj.length-2));
		   			 	 }
		   			 	 
		   			 	 
					 };
					 if(data.examf){
                         var cssj = data.examf.opinion_time;
                         if(cssj != null){
                        	 $("#ggcssj").val(cssj.substr(0,cssj.length-2));  
                         }
						 
						 $("#ggcsyj").val(data.examf.opinion_content);
		   			 	 $("#ggcsr").val(data.examf.checker_no);
						 
					 };
					 if(data.exams){
						 
						 var fssj =data.exams.opinion_time;
						 if(fssj != null){
							 $("#ggfssj").val(fssj.substr(0,fssj.length-2));  
						 }
						 $("#ggfsyj").val(data.exams.opinion_content);
		   			 	$("#ggfsr").val(data.exams.checker_no);
		   			
						 
					 };
					 if(data.examt){
						 var shsj = data.examt.opinion_time;
						 if(shsj != null){
							 $("#ggshsj").val(shsj.substr(0,shsj.length-2));  
							 
						 }
						 
						 $("#ggshr").val(data.examt.checker_no);
		   			 		$("#ggshyj").val(data.examt.opinion_content);
					 };
					 if(data.examfo){
						 var hzsj = data.examfo.opinion_time;
						 if(hzsj != null){
							 $("#gghzsj").val(hzsj.substr(0,hzsj.length-2)); 
							 
						 }
						 
						
						 $("#gghzr").val(data.examfo.checker_no);
		   			 	 $("#gghzyj").val(data.examfo.opinion_content);
					 }
					                				
				}
			}
});


}
//���湫�漰�����Ϣ
function saveAnnounce(){
	var yj = $("#ggnr").val()
	var sj = $("#ggndsj").val()
	var r = $("#ggndr").val()
	if($.trim(yj).length== 0){
		
		 top.$.messager.confirm('��ʾ','�������ݲ���Ϊ�գ���¼�빫�����ݻ�ѡ�񹫸�ģ�棡',function(){});
		
	}else{
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveAnnounce.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"an.notice_pro_person":r,"an.notice_content":yj,"an.notice_pro_time":sj},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getAnnounce();
			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
				}
	   		}
	   	});
	}
}


//������������
function saveExamFirst(){
	var yj = $("#ggcsyj").val()
	var sj = $("#ggcssj").val()
	var r = $("#ggcsr").val()
	if($.trim(yj).length== 0){
		
		 top.$.messager.confirm('��ʾ','�������������Ϊ�գ���¼�������ѡ�����',function(){});
		
	}else{
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaFirst.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"exf.checker_no":r,"exf.opinion_content":yj,"exf.opinion_time":sj,"exf.opinion_type":"������"},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getAnnounce();
			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
				}
	   		}
	   	});
	}
}

//���湫�������
function saveExamSecond(){
	var yj = $("#ggfsyj").val()
	var sj = $("#ggfssj").val()
	var r = $("#ggfsr").val()
	if($.trim(yj).length== 0){
		
		 top.$.messager.confirm('��ʾ','�������������Ϊ�գ���¼�������ѡ�����',function(){});
		
	}else{
	
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaSecond.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"exs.checker_no":r,"exs.opinion_content":yj,"exs.opinion_time":sj,"exs.opinion_type":"������"},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getAnnounce();
			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
				}
	   		}
	   	});
	};
}

//���湫�����������
function saveExamThird(){
	var yj = $("#ggshyj").val()
	var sj = $("#ggshsj").val()
	var r = $("#ggshr").val()
	if($.trim(yj).length== 0){
		
		 top.$.messager.confirm('��ʾ','��������������Ϊ�գ���¼����������ѡ�����',function(){});
		
	}else{
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaThird.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"ext.checker_no":r,"ext.opinion_content":yj,"ext.opinion_time":sj,"ext.opinion_type":"����������"},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getAnnounce();
			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
				}
	   		}
	   	});
	};
}
//���湫�����������
function saveExamForth(){
	
	var yj = $("#gghzyj").val()
	var sj = $("#gghzsj").val()
	var r = $("#gghzr").val()
	if($.trim(yj).length== 0){
		
		 top.$.messager.confirm('��ʾ','�����������������Ϊ�գ���¼�������ѡ�����',function(){});
		
	}else{
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaForth.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"exfo.checker_no":r,"exfo.opinion_content":yj,"exfo.opinion_time":sj,"exfo.opinion_type":"����������"},
	   		success:function(data){
			 	if(data){
			 	
			 		//alert(data);
			 		getAnnounce();
			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
				}
	   		}
	   	});
	}
}
	   			 		
//���湫�淢����Ϣ
function saveAnnouncePub(){
	   
	    
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveAnnouncePub.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		//data:{"exfo.checker_no":r,"exfo.opinion_content":yj,"exfo.opinion_time":sj,"exfo.opinion_type":"����������"},
	   		
	   		data:$("#add_gg_form").serialize(),
	   		success:function(data){
			 	if(data){
			 		
			 		getAnnounce();
			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
				}
	   		}
	   	});
	
}
//���ɳ��������

	  
/**********************************************************************************
*��������: ҳ��У�鷽��
*����˵��: ��֤ҳ���ϵķǿ�  �����ݸ�ʽ   
*����˵��: flag 1������ �ύ����ֵ �������ֱ�����ύ
*�� �� ֵ: obj  result(trueͨ��  false��ͨ��) message(��Ϣ)  page_name(��ǰҳ������)
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function validate(v_flag){
	//���ؽ������
	var result ={
			result:false,
			message:'',
			page_name:'�����'
	}
	var message;
	
	result.result = true;
	return result;
}

	  
	  
