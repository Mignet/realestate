var proc;
proc=this.parent.proc;

var proc_id = proc.procId;
//var proc_id = 3;


var activName = proc.activName;
var procdefId = proc.procdefId;
var serialNumber ={num1:"�ǼǱ��",num2:"�鵵��",num3:"���ز�֤��"};
var opintion;                         //�������������ﷵ��ֵ�趨�Ŀؼ�ID
var _init_form_data;							//��ʼ��ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var _cur_form_data;								//��֤ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
//var activName = "����";
var state1 = {
		string0: "����",
		string1 : "����",
		string2 : "����",
		string3 : "�շ�",
		string4 : "��׼",
		string5 : "�������",
		string6 : "�������",
		string7: "������",		
		string9 : "��֤",
		string10 : "����",
		string11: "�鵵",
		string12: "����",
	};
$(function(){
	
	        setState(activName);	
      	    //��ȡ�����¼�
	        initial();
	        getExamine();
	        //��ȡ������Ϣ
	        getAnnounce();
	      //ʧȥ�����¼�
       		$('textarea').blur(function(){
       			blur();
       		});   
       		//_init_form_data = $("#main_form").serializeJson(); 
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
	if(activName == state1.string12)
	{
		saveAnnounce();
		return true;
	}
	else
	{
		return saveExamine();
	}
	
 };
//���湫�漰�����Ϣ
 function saveAnnounce(){
	 	var yj = $("#ggnr").val();
		//var sj = $("#ggndsj").val();
		//var r = $("#ggndr").val();
		//alert($("ggndr").val());\
		var result;
		var url;
		if(activName == state1.string12)
	   	{
			result=$("#main_form").serialize();
			url=ctx+"/common/exam!saveAnnouncePub.action?time="+new Date()+"&proc_id="+proc_id;
	   	}
	   	else
	   	{
	   		result={"notice_content":yj};
	   		url=ctx+"/common/exam!saveAnnounce.action?time="+new Date()+"&proc_id="+proc_id;
	   	}
		if($.trim(yj).length== 0){
			
			 top.$.messager.confirm('��ʾ','�������ݲ���Ϊ�գ���¼�빫�����ݻ�ѡ�񹫸�ģ�棡',function(){});
			
		}else{
 		$.ajax({
 	   		dataType:'json',
 	   		url:url,
 	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
 	   		//�������л�����
 	   		data:result,
 	   		success:function(data){
 			 	if(data){
 			 		//alert(data);
 			 		getAnnounce();
 			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
 					});	
 			 		
 			 	}else {
 					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
 				}
 			 	_init_form_data = $("#main_form").serializeJson(); 
 	   		}
 	   	});
		}
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
		 		if(activName == state1.string5)
		 		{
		 			saveAnnounce();
		 		}
		 		else
		 		{
		 			top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
					});
		 		}
		 		//alert(data);
		 			
		 		
		 	}else {
				top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
			}
   		},error:function(data){
   			result = false;
   		}
   	});
	return result;
}

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
						 $("#ggndsj").val(ggndsj.substr(0,20));
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
		   			 	   $('#ggsj').datetimebox('setValue',ggsj.substr(0,10));
		   			 	 }
		   			 	 $("#fbdw").val(data.annouce.notice_pub_off);
		   			 	 var fbsj = data.annouce.notice_pub_time;
		   			 	 if(fbsj != null){
		   			 	$('#fbsj').datetimebox('setValue',fbsj.substr(0,10));
		   			 	 }
		   			 	 $("#cbwmc").val(data.annouce.pub_name_date);
		   			 	 var cbsj = data.annouce.pub_date;
		   			 	 if(cbsj != null){
		   			 		$('#cbwdzrq').datetimebox('setValue',cbsj.substr(0,10));
		   			 	 }
					 };
				}
			}
});
}


 
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
   			 			//�������
   			 			if(data[i].opinion_type==state1.string5)
   			 			{
                          	$("#cbscsj").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-3));  
      			 		    $("#cbsc").val(data[i].opinion_content);
      			 		    $("#cbscr").val(data[i].checker_no);
   			 			}
   			 			//�������
   			 			if(data[i].opinion_type==state1.string6)
   			 			{
	   			 			$("#cbshsj").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-3));  
	  			 		    $("#cbsh").val(data[i].opinion_content);
	  			 		    $("#cbshr").val(data[i].checker_no);
   			 			}
   			 			//������
   			 			if(data[i].opinion_type==state1.string7)
   			 			{
	   			 			$("#cbsdsj").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-3));  
	  			 		    $("#cbsd").val(data[i].opinion_content);
	  			 		    $("#cbsdr").val(data[i].checker_no);
   			 			}
   			 			//����
   			 			if(data[i].opinion_type==state1.string1)
   			 			{
	   			 			$("#cssj").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-3));  
	  			 		    $("#cs").val(data[i].opinion_content);
	  			 		    $("#csr").val(data[i].checker_no);
   			 			}
   			 			//����
   			 			if(data[i].opinion_type==state1.string2)
   			 			{
	   			 			$("#fssj").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-3));  
	  			 		    $("#fs").val(data[i].opinion_content);
	  			 		    $("#fsr").val(data[i].checker_no);
   			 			}
   			 			//��׼
   			 			if(data[i].opinion_type==state1.string4)
   			 			{
	   			 			$("#hzsj").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-3));  
	  			 		    $("#hz").val(data[i].opinion_content);
	  			 		    $("#hzr").val(data[i].checker_no);
   			 			}
   			 		}
   			 	}
   			 _init_form_data = $("#main_form").serializeJson(); 
   				 
   			}
   		});
	
	
}


//�������̽ڵ����ÿؼ�Ȩ��
 function setState(activName){
      $(":input").attr("disabled", "disabled");
 	      if(activName == state1.string5)
 	      {
 	    	 opintion="cbsc";
 	    	 $("#cbsc").removeAttr("disabled","disabled");
 	    	 $("#cbsccyy").removeAttr("disabled","disabled");
 	    	 $("#ggnr").removeAttr("disabled", "disabled");
	    	 $("#ggmb").removeAttr("disabled", "disabled");
 	      };
 	      if(activName == state1.string6)
 	      {
 	    	 opintion="cbsh";
 	    	 $("#cbsh").removeAttr("disabled","disabled");
 	    	 $("#cbshcyy").removeAttr("disabled","disabled");
 	      };
 	      if(activName == state1.string7)
 	      {
 	    	 opintion="cbsd";
 	    	 $("#cbsd").removeAttr("disabled","disabled");
 	    	 $("#cbsdcyy").removeAttr("disabled","disabled");
 		  };
 	      if(activName == state1.string12)
 	      {
 	    	 $("#ggbh").removeAttr("disabled", "disabled");
 	    	 $("#ggry").removeAttr("disabled", "disabled");
 	    	 $("#ggqx").removeAttr("disabled", "disabled");
 	    	 $("#ggsj").removeAttr("disabled", "disabled");
 	    	 $("#fbdw").removeAttr("disabled", "disabled");
 	    	 $("#fbsj").removeAttr("disabled", "disabled");
 	    	 $("#cbwdzrq").removeAttr("disabled", "disabled");
 	    	 $("#cbwmc").removeAttr("disabled", "disabled");
 	      };
 	      if(activName == state1.string1)
 	      {
 	    	 opintion="cs";
 	    	 $("#cs").removeAttr("disabled","disabled");
 	    	 $("#cscyy").removeAttr("disabled","disabled");
 	      };
 	      if(activName == state1.string2)
 	      {
 	    	 opintion="fs";
 	    	 $("#fs").removeAttr("disabled","disabled");
 	    	 $("#fscyy").removeAttr("disabled","disabled");
 	      };
 	      if(activName == state1.string4)
 	      {
 	    	 opintion="hz";
 	    	  $("#hz").removeAttr("disabled","disabled");
 	    	 $("#hzcyy").removeAttr("disabled","disabled");
	      };
 	      /*if(!(activName == state1.string1 || activName == state1.string2 ||activName == state1.string3 ||activName == state1.string7||activName == state1.string5||activName == state1.string6)){
 	    	  $(":input").attr("disabled", "disabled");  
 	      }*/
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
				this.init(activName,procdefId,opintion);
			}
		});
	 
	 
		 }; 

//��ʼ����ǰ��¼���Լ�����
function initial(){
	if(activName == state1.string5){
		//  ��ȡ��ǰʱ��
	    $("#cbscsj").val(getTime());
	     //��ȡ��ǰ������
	    $("#cbscr").val(user); 
	    //  ��ȡ��ǰʱ��
		 $("#ggndsj").val(getTime());
		 //��ȡ��ǰ������
		 $("#ggndr").val(user); 
	 };
	 if(activName == state1.string6){
		 //  ��ȡ��ǰʱ��
		 $("#cbshsj").val(getTime());
		 //��ȡ��ǰ������
		 $("#cbshr").val(user); 
	 };
	 if(activName == state1.string7){
		 //  ��ȡ��ǰʱ��
		 $("#cbsdsj").val(getTime());
		 //��ȡ��ǰ������
		 $("#cbsdr").val(user); 
	 };
	 if(activName == state1.string12){
		     //��ȡ��ǰ������
		    $("#ggry").val(user); 
		 };
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
	 if(activName == state1.string4){
		 //  ��ȡ��ǰʱ��
		 $("#hzsj").val(getTime());
		 //��ȡ��ǰ������
		 $("#hzr").val(user); 
	 };
	
};
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
	  if(activName == state1.string5){
	   	    opinion = $("#cbsc").val();
	   	    opiniontime = $("#cbscsj").val(getTime());
	   	    opinionpeople = $("#cbscr").val();
	   	    opiniontype = state1.string5;
    };
    if(activName == state1.string6){
	       opinion = $("#cbsh").val();
	   	   opiniontime = $("#cbshsj").val(getTime());
	   	   opinionpeople = $("#cbshr").val();
	   	   opiniontype = state1.string6;
    };
    if(activName == state1.string7){
	       opinion = $("#cbsd").val();
	   	   opiniontime = $("#cbsdsj").val(getTime());
	   	   opinionpeople = $("#cbsdr").val();
	   	   opiniontype = state1.string7;
	      };
    if(activName == state1.string1){
    	opinion = $("#cs").val();
	   	   opiniontime = $("#cssj").val(getTime());
	   	   opinionpeople = $("#csr").val();
	   	   opiniontype = state1.string1;
    };
    if(activName == state1.string2){
	       opinion = $("#fs").val();
	   	   opiniontime = $("#fssj").val(getTime());
	   	   opinionpeople = $("#fssj").val();
	   	   opiniontype = state1.string2;
    };
    if(activName == state1.string4){
	       opinion = $("#hz").val();
	   	   opiniontime = $("#hzsj").val(getTime());
	   	   opinionpeople = $("#hzr").val();
	   	   opiniontype = state1.string4;
    };
  }



//��������ȡ����ʱ�����浱ǰ�û��͵�ǰʱ��

function blur(){
	
	if(activName == state1.string5){
		//  ��ȡ��ǰʱ��
	    $("#cbscsj").val(getTime());
	    $("#ggndsj").val(getTime());
	     //��ȡ��ǰ������
	    $("#cbscr").val(user); 
	    $("#ggndr").val(user);
	 };
	 if(activName == state1.string6){
		 //  ��ȡ��ǰʱ��
		 $("#cbshsj").val(getTime());
		 //��ȡ��ǰ������
		 $("#cbshr").val(user); 
	 };
	 if(activName == state1.string7){
		 //  ��ȡ��ǰʱ��
		 $("#cbsdsj").val(getTime());
		 //��ȡ��ǰ������
		 $("#cbsdr").val(user); 
	 };
	 if(activName == state1.string12){
		 //  ��ȡ��ǰʱ��
		 //$("#cbsdsj").val(getTime());
		 //��ȡ��ǰ������
		 $("#ggry").val(user); 
	 };
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
	 if(activName == state1.string4){
		 //  ��ȡ��ǰʱ��
		 $("#hzsj").val(getTime());
		 //��ȡ��ǰ������
		 $("#hzr").val(user); 
	 };
	
};
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
	if(activName == state1.string6){
		var yj = $("#cbsh").val()
		if($.trim(yj).length== 0){
			message = '��������������Ϊ�գ���¼����������ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}
	if(activName == state1.string7){
		var yj = $("#cbsd").val()
		if($.trim(yj).length== 0){
			message = '�������������Ϊ�գ���¼����������ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}
	
	if(activName == state1.string1){
		var yj = $("#cs").val();
		if($.trim(yj).length== 0){
			message = '�����������Ϊ�գ���¼����������ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 
			 result.message=message;
			 result.result=false;
			 return result;
		}
		
	}if(activName == state1.string2){
		var yj = $("#fs").val()
		if($.trim(yj).length== 0){
			message = '�����������Ϊ�գ���¼�븴�������ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}if(activName == state1.string5){
		var yj = $("#cbsc").val()
		if($.trim(yj).length== 0){
			message = '��������������Ϊ�գ���¼����������ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
		var yj = $("#ggnr").val()
		if($.trim(yj).length== 0){
			message = '�ⶨ�����������Ϊ�գ���¼����������ѡ�����';
//			 top.$.messager.confirm('��ʾ',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}if(activName == state1.string4){
		var yj = $("#hz").val()
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

	  
