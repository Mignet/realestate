/*********************************************************************************
*��  ��  ��  ��:dispatch.js
*��  ��  ��  ��: ���Ĺ���
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: panda
*��  ��  ��  �ڣ� 2014-02-28
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
var proc=this.parent.proc;
var proc_node = proc.activName;//��ȡ��������
var proc_id = proc.procId;//��ȡ���̽ڵ�id
//����ֵ���飬��¼��ѡ��ѡ�е��У�������ҳ������ֵ
window.seq_id_set = [];
/**********************************************************************************
*��������: 
*����˵��: ҳ���ʼ��
*��������: panda
*��������: 2014-02-28
*�޸���ʷ: 
***********************************************************************************/
$(function(){
	
	
	//�����Ǽ���Ϣ��
	var userDataGrid = $('#table_djxx').datagrid({
		title:'�Ǽ���Ϣ',
		//fit:true,
		height:300,
		//���������Դ
		url:ctx+"/mortgage/morsetup!getRegunitMess.action?time="+new Date()+"&proc_id="+proc_id,
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
		idField: 'CODE',
		//�������Ƿ�����ʾ��ͬ����ɫ
		striped:true,
		//ֻ����ѡһ��
		singleSelect:false,
		//�Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		checkOnSelect:false,
		//�Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		selectOnCheck:true,
		//����������
		columns:[[
			//ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
			{field:'ck',checkbox:true},
			{
     			title : '�ڵغ�',
     			field : 'PARCEL_CODE',
     			width:80
     			
     		}, {
     			title : '�ڵ�����',
     			field : 'LAND_ADDRESS',
     			width:100
     			
     		},
     		
     		
     		{
     			title : '����������',
     			field : 'BUILDING_NAME',
     				width:100
     		}, 
     		{
     			title : '����',
     			field : 'BUILD_NO',
     			width:50
     			
     		}, 
     		{
     			title : '����',
     			field : 'ROOMNAME',
     			width:50
     			
     		},  {
     			title : '��Ŀ����',
     			field : 'PRO_NAME',
     			width:100
     			
     		}, {
     			hidden: true,
     			field : 'TYPE',
     			width:100
     			
     		}, {
     			hidden: true,
     			field : ' ',
     			width:100
     			
     		},
			
		]],
 		onLoadSuccess : function() {
 			
 			//��ʼ����֤����Ϣ
 			getDis();
 		}
		
		
		
	});//���ر�����
	
	
	//�ж���֤�����Ƿ���ڣ���������,���ó�ʼֵ
	
	if($.trim($("#rec_date").val()).length == 0){
	   //$("#rec_date").val(getTime()); 
	  
	}
	
			
}); //��ʼ������
/**********************************************************************************
*��������: submit
*����˵��: ���淢֤��Ϣ
*����˵��: ��
*�� �� ֵ: ��
*��������: panda
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function submit() {
	var result = true;
	var rows = $('#table_djxx').datagrid('getSelections');
	//ѭ�����ύɾ��������ֵ(���ַ�����)
	
	//alert(JSON.stringify(rows));
	rows = $.toJSON(rows);
	//alert(rows.length);
    if(rows.length <= 2){
		top.$.messager.alert('��ȷ��ʾ', '��ѡ����Ҫ��֤�ĵǼǵ�Ԫ', 'info',
				function() {
					
					
				});
		return false;
		
	}
    //��ȡ������Ϣ 
	var rec_person = $("#rec_person").val();
	var rec_type = $("#rec_type").combodict('getValue');
	var rec_cer_type = $("#rec_cer_type").combodict('getValue');
	var rec_cer_no = $("#rec_cer_no").val();
	var rec_date = $("#rec_date").val();
	//��֤�ǿ���Ϣ
	if($.trim(rec_person).length==0 || $.trim(rec_cer_no).length==0){
		top.$.messager.alert('��ʾ', '�������Ϊ�գ������룡', 'info',
				function() {					
		});	
		return false;
	} 
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
	$.ajax({
		dataType : 'json',
		url:ctx+"/mortgage/morsetup!saveDispathcInfo.action",
		contentType : "application/x-www-form-urlencoded; charset=GBK",
		//data:{"regUnitList":rows,"proc_id":proc_id},
		data:{"regUnitList":rows,"proc_id":proc_id,"cer.rec_person":rec_person,"cer.rec_cer_no":rec_cer_no,"cer.rec_type":rec_type,"cer.rec_cer_type":rec_cer_type,"cer.rec_date":rec_date,"time":new Date()},
		success : function(data) {
			if (data.success) {
				top.$.messager.alert('��ȷ��ʾ', data.tipMessage, 'info',
						function() {
							
							
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
*��������: validate
*����˵��: ��Ѻ�Ǽ���Ϣ��ʽУ��
*����˵��: 
*�� �� ֵ: ��
*��������: panda
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function validate(v_flag){
	//���ؽ������
	var result ={
			result:false,
			message:'',
			page_name:'��֤��'
	}
	//��ʾ��Ϣ 
	var message;
	var rows = $('#table_djxx').datagrid('getSelections');
    if(rows.length==0){
//		top.$.messager.alert('��ȷ��ʾ', '��ѡ����Ҫ��֤�ĵǼǵ�Ԫ', 'info',
//				function() {
//					
//					
//				});
    	result.message="��ѡ����Ҫ��֤�ĵǼǵ�Ԫ";
		return result;
		
	}
    //��ȡ������Ϣ 
	var rec_person = $("#rec_person").val();
	var rec_type = $("#rec_type").combodict('getValue');
	var rec_cer_type = $("#rec_cer_type").combodict('getValue');
	var rec_cer_no = $("#rec_cer_no").val();
	var rec_date = $("#rec_date").val();
	//��֤�ǿ���Ϣ
	if($.trim(rec_person).length==0 || $.trim(rec_cer_no).length==0){
//		top.$.messager.alert('��ʾ', '�������Ϊ�գ������룡', 'info',
//				function() {					
//		});	
		result.message="�������Ϊ�գ������룡";
		return result;
	} 
	
	result.result=true;
	return result;
	
}
/**********************************************************************************
*��������: cancelSave
*����˵��: ȡ����֤
*����˵��: ��
*�� �� ֵ: ��
*��������: panda
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function cancelSave() {
	var rows = $('#table_djxx').datagrid('getSelections');
	if(rows==null){
		
		top.$.messager.alert('��ȷ��ʾ', '��ѡ��ȡ����֤�ĵǼǵ�Ԫ', 'info',
				function() {
					
					
				});
		return ;
		
	}
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

	var obj = {
			dataType : 'json',
			data : $("#userForm").serialize(),
			url:ctx+"/common/certificate!saveDispatch.action",
			data:{"proc_id":proc_id,"recperson":rec_person,"reccerno":rec_cer_no,"rectype":rec_type,"reccertype":rec_cer_type,"time":new Date()},
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
/**********************************************************************************
*��������: getTime
*����˵��: ��ȡϵͳ��ǰʱ��
*����˵��: ��
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
*��������: getTime
*����˵��: ��ʼ�����ز�֤��Ϣ
*����˵��: ��
*�� �� ֵ: ��
*��������: panda
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/

function getDis(){
	//alert("getDis...");
	//��������ʵ��id����ѯ
	$.ajax({
	    dataType: 'json',
		url:ctx+"/common/certificate!getCertificateByProcId.action?proc_id="+proc_id+"&time="+new Date(),
		success:function(data){
			//alert(data);
			//alert($.toJSON(data));
		 	if(data){
		 		//data = data[0];
		 		 if(data.rec_person != null){	
		 			$('#table_djxx').datagrid('checkAll');
		 			var datagridLength = $('#table_djxx').datagrid('getRows').length;
		 			alert($.toJSON(datagridLength) );
		 			for(var i=0;i<datagridLength;i++){
		 				$('#table_djxx').datagrid('checkRow',i);
		 			}
		 			
		 			     if(data.rec_date){
	   			 		var rec_date=data.rec_date;//.substr(0,data.rec_date.length-2);
	   			 		//alert(rec_date instanceof Date);
	   			 		//alert(rec_date.Format("yyyy-MM-dd"));
	   			 	    $("#rec_date").val(rec_date);
		 		          } 
	   		   			//������֤�ˡ�֤�����Ϊ���ܲ���
		   				$("#rec_cer_no").attr("disabled", "disabled");
		   				$("#rec_person").attr("disabled", "disabled");
		   				$('#rec_type').combo('disable');
		   				$("#rec_cer_type").combo('disable');
	   			 		};
	   			 	if(data.rec_type){
	   			 		
	   			 	$("#rec_type").combodict('setValue',data.rec_type);	
	   			 	}
	   			 	
	   			    $("#rec_person").val(data.rec_person);
	   			    if(data.rec_cer_type){
	   			    	$("#rec_cer_type").combodict('setValue',data.rec_cer_type);
	   			    }
	   			    
	   			    $("#rec_cer_no").val(data.rec_cer_no);
		 	}else{
		 		
		 		$("#rec_date").val(getTime()); 	 	
		 	}
		}
	});
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


		
