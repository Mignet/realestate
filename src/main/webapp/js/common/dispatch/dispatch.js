//var procId =1000016365 ;
//var procId= GetQueryString("procId");
var proc;
proc=this.parent.proc;
var procId = proc.procId;

var _init_form_data;							//��ʼ��ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var _cur_form_data;								//��֤ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�

$(function(){
	
	//��ʼ����֤����Ϣ
	getDis();
	
	//�����Ǽ���Ϣ��
	/*var userDataGrid = $('#table_djxx').datagrid({
		title:'�Ǽ���Ϣ',
		//fit:true,
		height:200,
		//���������Դ
		url:ctx+"/common/certificate!getRegsMessage.action?procId="+procId+"&time="+new Date(),
		//���ÿ�п���Զ���Ӧ����ܿ��
		fitColumns: true,
		//ȥ���߿�
		border:true,
		//�Ƿ��з�ҳ��
		pagination:true,
		//ÿҳ����
		pageSize:20,
		//�Ƿ�����������һ����ʾ�кŵ���
		rownumbers:true,
		//����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		//idField: 'user_id',
		//�������Ƿ�����ʾ��ͬ����ɫ
		striped:true,
		//ֻ����ѡһ��
		singleSelect:true,
		//�Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		//checkOnSelect:true,
		//�Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		//selectOnCheck:true,
		//����������
		columns:[[
			//ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
			{field:'BUS_ID', title:'ҵ����', width:70, sortable:true},
			{field:'DJLX', title:'ҵ������', width:80, sortable:true},	
			{field:'SQR', title:'Ȩ����', width:30, sortable:true },
			{field:'ZJLX', title:'֤������', width:50},
			{field:'ZJBH', title:'֤�����', width:100},
			{field:'FDCMC', title:'���ز�����', width:100},						
			{field:'BLZT',	title:'����״̬', width:50},
			{field:'JFZT',	title:'�ɷ�״̬', width:50}
			
		]]
	});*/
	
	
	
			
}); //��ʼ������
//������֤����Ϣ
//���������²���


/*     	<form id="userForm" name="userForm" method="post">
<input type="hidden" id="rec_type" name="c.rec_type"/> 
<input type="hidden" id="rec_person" name="c.rec_person"/> 
<input type="hidden" id="rec_cer_type" name="c.rec_cer_type"/> 
<input type="hidden" id="rec_cer_no" name="c.rec_cer_no"/> 
<input type="hidden" id="rec_date" name="c.rec_date"/> */

function submit(){
	return true;
}


function submit1() {
	_init_form_data = $("#main_form").serializeJson(); 
	var v_result = validate(1);
	if(!v_result.result){
		top.$.messager.alert('��ʾ',v_result.message, 'info',
				function() {
					
				});	
		return ;
	}
    //��ȡ������Ϣ 
	var rec_person = $("#rec_person").val();
	var rec_type = $("#rec_type").val();
	var rec_cer_type = $("#rec_cer_type").val();
	var rec_cer_no = $("#rec_cer_no").val();
	var rec_date = $("#rec_date").val();
	
	if($.trim(rec_person).length!=0 && $.trim(rec_cer_no).length!=0){
		
		//������֤������
		$("#rec_date").val(getTime());
		rec_date = $("#rec_date").val();
		//������֤�ˡ�֤�����Ϊ���ܲ���
		$("#rec_cer_no").attr("disabled", "disabled");
		$("#rec_person").attr("disabled", "disabled");
		$('#rec_type').combo('disable');
		$("#rec_cer_type").combo('disable');
		
		
	}
	/*$("#rec_person1").val(rec_person);
	$("#rec_type1").val(rec_type);
	$("#rec_cer_type1").val(rec_cer_type);
	$("#rec_cer_no1").val(rec_cer_no);
	$("#rec_date1").val(rec_date);
	$("#procId").val(procId);*/
	var obj = {
		dataType : 'json',
		data : $("#userForm").serialize(),
		url:ctx+"/common/certificate!saveDispatch.action",
		data:{"procid":procId,"recperson":rec_person,"reccerno":rec_cer_no,"rectype":rec_type,"reccertype":rec_cer_type,"time":new Date()},
		contentType : "application/json; charset=GBK",
		success : function(data) {
			if (data.success) {
				top.$.messager.alert('��ȷ��ʾ', data.tipMessage, 'info',
						function() {
							
							
						});
			} else {
				top.$.messager.alert('������ʾ', data.errorMessage, 'error');
			}
		}
	};


	$.ajax(obj);
};
//ȡ��������²���
function cancelSave() {
	
	//����ȡ��ʱ��Ĭ��ֵ
	$("#rec_person").val("");
	$("#rec_cer_no").val("");
	$("#rec_date").val(getTime());
	
	//��ȡ������Ϣ
	var rec_person = $("#rec_person").val();
	var rec_type = $("#rec_type").val();
	var rec_cer_type = $("#rec_cer_type").val();
	var rec_cer_no = $("#rec_cer_no").val();
	var rec_date = $("#rec_date").val();
	//������֤�� ��֤��֤�����
	$("#rec_person").removeAttr("disabled"); 
	$("#rec_cer_no").removeAttr("disabled"); 
	$('#rec_type').combo('enable');
	$("#rec_cer_type").combo('enable'); 
/*	//��������ֵ
	$("#rec_person1").val(rec_person);
	$("#rec_type1").val(rec_type);
	$("#rec_cer_type1").val(rec_cer_type);
	$("#rec_cer_no1").val(rec_cer_no);
	$("#rec_date1").val(rec_date);
	$("#procId").val(procId);*/

	var obj = {
			dataType : 'json',
			data : $("#userForm").serialize(),
			url:ctx+"/common/certificate!saveDispatch.action",
			data:{"procid":procId,"recperson":rec_person,"reccerno":rec_cer_no,"rectype":rec_type,"reccertype":rec_cer_type,"time":new Date()},
			contentType : "application/json; charset=GBK",
			success : function(data) {
				if (data.success) {
					top.$.messager.alert('��ȷ��ʾ',"ȡ���ɹ�", 'info',
							function() {
								
								
							});
				} else {
					top.$.messager.alert('������ʾ', data.errorMessage, 'error');
				}
			}
		};


	$.ajax(obj);
};

//��ȡ��ַ������
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
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
//��ʼ�����ز�֤��Ϣ
function getDis(){
	//��������ʵ��id����ѯ
	$.ajax({
	    dataType: 'json',
		url:ctx+"/common/certificate!getCertificateByid.action?proc_id="+procId+"&time="+new Date(),
		success:function(data){
		 	if(data){
		 		 if(data.rec_person != null){		 			
	   			 		var rec_date=data.rec_date.substr(0,data.rec_date.length-2);
	   			 	    $("#rec_date").val(rec_date);
	   		   			//������֤�ˡ�֤�����Ϊ���ܲ���
		   				$("#rec_cer_no").attr("disabled", "disabled");
		   				$("#rec_person").attr("disabled", "disabled");
		   				$('#rec_type').combo('disable');
		   				$("#rec_cer_type").combo('disable');
	   			 		};
	   			 	$("#rec_type").val(data.rec_type);
	   			    $("#rec_person").val(data.rec_person);
	   			    $("#rec_cer_type").val(data.rec_cer_type);
	   			    $("#rec_cer_no").val(data.rec_cer_no);
		 	}
		 	
		 	_init_form_data = $("#main_form").serializeJson(); 
		}
	});
}



//��ʼ���Ǽ���Ϣ


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
	//��֤�ǿ���Ϣ
		var rec_person = $("#rec_person").val();
		var rec_cer_no = $("#rec_cer_no").val();
		if($.trim(rec_person).length==0 || $.trim(rec_cer_no).length==0){
			result.message='�������Ϊ�գ������룡';
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
			message = '���ݼ��޸ģ��Ƿ�������棿';
			 if(flag){
				 
			 }else{
				 result.message=message;
				 result.result=false; 
			 }
			 return result;
		}
	
	return result; 
	
}

//�����ic����ť����ȡ���֤����Ϣ
function setid(){
	ClearIDCard(); //��ǰ�������������
	if(!readIDCard()){
		top.$.messager.alert('������ʾ','ʶ��ʧ��','info',function(){});
		return
	}
	 var pName=CVR_IDCard.NameL; //var pNameL=CVR_IDCard.NameL;
	 var pSex=CVR_IDCard.SexL;   //var pSexL=CVR_IDCard.SexL;
	 var pNation=CVR_IDCard.NationL;  //var pNationL=CVR_IDCard.NationL;
	 var pBorn=CVR_IDCard.BornL;      //var pBornL=CVR_IDCard.BornL;
	 var pAddress=CVR_IDCard.Address;
	 var pCardNo=CVR_IDCard.CardNo;
	 var pPolice=CVR_IDCard.Police;
	 var pActivity=CVR_IDCard.Activity;
	 var pNewAddr=CVR_IDCard.NewAddr;  
	 var pActivityLFrom=CVR_IDCard.ActivityLFrom; 
	 var pActivityLTo=CVR_IDCard.ActivityLTo; 
	 var pPhotoBuffer=CVR_IDCard.GetPhotoBuffer; 
	  
	  $("#rec_cer_no").val(pCardNo);
	  $("#rec_person").val(pName);
	
	  ClearIDCard(); //�����������������
	//CVR_IDCard.DoStopRead; //ֹͣ����
}

function readIDCard(){
	var strReadResult=CVR_IDCard.ReadCard;
	if(strReadResult == "0"){
		return true;
	}
	return false;
}
function ClearIDCard() {
	   CVR_IDCard.Name="";
	   CVR_IDCard.NameL="";
	   CVR_IDCard.Sex="";   
	   //CVR_IDCard.SexL="";   
	   CVR_IDCard.Nation="";
	   //CVR_IDCard.NationL="";
	   CVR_IDCard.Born="";
	   //CVR_IDCard.BornL="";
	   CVR_IDCard.Address="";
	   CVR_IDCard.CardNo="";
	   CVR_IDCard.Police="";
	   CVR_IDCard.Activity="";
	   CVR_IDCard.NewAddr="";
	  
	   return true;
}

		
