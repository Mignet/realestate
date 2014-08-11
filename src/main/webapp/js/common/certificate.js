/*********************************************************************************
*��  ��  ��  ��: certificate.js
*��  ��  ��  ��: ��֤��
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Joyon
*��  ��  ��  �ڣ� 2014-03-01
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
var proc=this.parent.proc;						//��ǰ���̶���
var proc_id = proc.procId;						//��ǰ����ʵ��ID
var CER_STATE = {
	"DONE":"059001",
	"UN_DONE":"059002"
	};
var _init_form_data;							//��ʼ��ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var _cur_form_data;								//��֤ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var app_cer_type_dict_data;							//������֤�������ֵ�������
var app_type_dict_data;								//�����������ֵ�������
var naturalDataGrid;							//��Ȼ��Ϣdatagrid
var holderDatagrid;								//Ȩ����datagrid
var selectedHolderData = {};							//ѡ��Ȩ���˵�����
var selectedNaturalData;						//ѡ����Ȼ��Ϣ 

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
	//getApp_cer_type_dict_data();				//��ȡ������֤�������ֵ���
	//getApp_type_dict_data();					////��ȡ�����������ֵ���
	
	//�����ı���ֻ��
	$(":input").attr("disabled", "disabled");
	$("#CERTIFICATE_TYPE").combodict("setValue","038005");
	$("#CERTIFICATE_TYPE").combo("enable");
	//��ʼ����֤����
	//������Ȼ��Ϣ��
	initNaturalDatagrid();
	//����Ȩ������Ϣ��
	initHolderdatagrid();
	
	//��ȡ���ز�֤�������Ǽ���Ϣ  �����ص���ǰҳ��
//	$.ajax({
//	    dataType: 'json',
//		url:"certificate!getCertificateInfo.action",
//		data:{"proc_id":proc_id,"time":new Date()},
//		success:function(data){
//			//alert(JSOn.stringify(data));
//			//alert(JSON.stringify(data.holders[0]));
//		 	if(data){
//		 		//alert(JSON.stringify(data));
//		 		//$('#table_holders').datagrid('load', data.holders[0]);  
//		 		$('#table_holders').datagrid({
//		 			data: data.holders
//		 		        });
//		 		$('#tab_reg_info').form("load",data.regInfo);
//		 		$('#tab_land').form("load",data.naturalInfo);
//				$('#tab_house').form("load",data.naturalInfo);
//				$('#tab_fdcz').form("load",data.certificateInfo);
//		 	//alert(JSON.stringify(data.certificateInfo));
//		 		if(data.certificateInfo.CER_STATE == CER_STATE.DONE){
//		 			 $("#submit").linkbutton("disable");
//		 		    $("#cancel").linkbutton("enable");
//		 		}else{
//		 			 $("#submit").linkbutton("enable");
//			 		    $("#cancel").linkbutton("disable");
//		 		}
//		 	}
//		 	_init_form_data = $("#main_form").serializeJson(); 		//���»�ȡ��ǰ��ҳ���ʼ��ֵ
//		
//		}
//	});
	 document.getElementById("submit").onclick = function() {
		   submit(CER_STATE.DONE);
	   };
	   document.getElementById("cancel").onclick = function() {
		   submit(CER_STATE.UN_DONE);
	   };
	
});//��ʼ������
/**********************************************************************************
*��������: loadForm
*����˵��: ��ʼ��form
*����˵��: 
*�� �� ֵ: ʱ��
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/  
function loadForm(data){
	$('#tab_land').form("load",data);
	$('#tab_house').form("load",data);
	$('#tab_certificate').form("load",data);
	
}

/**********************************************************************************
*��������: initNaturalDatagrid()
*����˵��: ��ʼ����Ȼ��Ϣdatagrid
*����˵��: 
*�� �� ֵ: ʱ��
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/  
function initNaturalDatagrid(){
	naturalDataGrid = $('#table_natural').datagrid({
		title:'���ز���Ϣ',
		height:180,
		url:ctx+"/common/certificate!getCertificateNatural.action?time="+new Date()+"&proc_id="+proc_id,
		// ���ÿ�п���Զ���Ӧ����ܿ��
		autoRowHeight : true,
		fitColumns : true,
		// ȥ���߿�
		border : true,
		striped : true,
		// �Ƿ��з�ҳ��
		pagination : true,
		// pagePosition:'top',
		// ÿҳ����
		pageSize : 10,
		// �Ƿ�����������һ����ʾ�кŵ���
		rownumbers : true,
		// ����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		//idField : 'jjclmc',
		// �������Ƿ�����ʾ��ͬ����ɫ
		striped : true,
		// ֻ����ѡһ��
		singleSelect : true,
		
		columns : [ [
		     		// ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������

		     		{field:'ck',checkbox:true},
		     		{
		     			title : '�ǼǱ� ��',
		     			field : 'REG_CODE',
		     			width:80
		     			
		     		},{
		     			title : 'ҵ������',
		     			field : 'BUS_TYPE_NAME',
		     			width:80
		     			
		     		},{
		     			title : '֤������',
		     			field : 'CER_TYPE',
		     			width:80,formatter:function(value){
		     				return "��������Ȩ֤";
		     			}
		     			
		     		},{
		     			title : '֤�����',
		     			field : 'CER_NO',
		     			width:80
		     			
		     		},{
		     			title : '���ز�����',
		     			field : 'EST_NAME',
		     			width:80
		     			
		     		},{
		     			title : '����״̬',
		     			field : 'CER_STATE',
		     			width:80,formatter:function(value){
		     				if(value=='059001'){
		     					return "����֤";
		     				}else{
		     					return "δ��֤";
		     				}
		     			}
		     			
		     		},{
		     			title : '�ɷ�״̬',
		     			field : 'TAX_STATE',
		     			width:80,formatter:function(value){
		     				if(!value){
		     					return "��˰��";
		     				}
		     			}
		     			
		     		},
		     		
		     		 {
		     			hidden: true,
		     			field : 'TYPE',
		     			width:100
		     			
		     		}, {
		     			hidden: true,
		     			field : 'CODE',
		     			width:100
		     			
		     		}
		     		

		     		] ],
		     		// ��ͷ����ӹ�������
		     		onClickCell:function(rowIndex, field, value){
		     			if(field=="button"){
		    				$('#table_house').datagrid('selectRow',rowIndex);
		    				dowatch(this);
		    			}
		    		},
		    		onClickRow:naturalClickRow,
		     		onLoadSuccess : function(data) {
		     			//���سɹ�ѡ�е�һ��
		     			if(data){
		     				naturalDataGrid.datagrid('selectRow',0);
		     				naturalClickRow(0);
		     			}
		     			//$('.editcls').linkbutton({text:'�鿴'});
		     			
		     		}
	});
}
/**********************************************************************************
*��������: naturalClickRow()
*����˵��: ��Ȼdatagrid row����¼�
*����˵��: 
*�� �� ֵ: ʱ��
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/  
function naturalClickRow(rowIndex){

		
		var selectedRow = naturalDataGrid.datagrid('getSelected',rowIndex);
		
		setButtonstate(selectedRow.CER_STATE);								//������֤  ��ȡ��  ��Ť״̬
		//document.write($.toJSON(selectedRow));
//		alert($.toJSON(selectedRow.holder));
		selectedNaturalData = selectedRow;									//����Ȼ��Ϣ��ֵ
		selectedHolderData.total = selectedRow.holder.length;
		selectedHolderData.rows = selectedRow.holder;
		loadForm(selectedRow);
		holderDatagrid.datagrid("loadData",selectedHolderData);				//Ȩ����Datagrid��������
		//alert($.toJSON(selectedRow));
}

/**********************************************************************************
*��������: initHolderdatagrid()
*����˵��: ��ʼ��Ȩ����datagrid
*����˵��: 
*�� �� ֵ: ʱ��
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/  
function initHolderdatagrid(){
		holderDatagrid = $('#table_holders').datagrid({
		title:'Ȩ������Ϣ',
		//fit:true,
		//���������Դ
		//url:'certificate!getRegPreAplPerInfo.action?lcslbid='+lcslbid+"&time="+new Date(),
		//���ÿ�п���Զ���Ӧ����ܿ��
		height:150,
		fitColumns: true,
		//ȥ���߿�
		border : true,
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
			{field:'HOL_NAME', title:'Ȩ��������', width:100, sortable:true},
			{field:'HOL_TYPE', title:'Ȩ��������', width:100, sortable:true,formatter : dicts.format.app_type_format
			},
//			{field:'HOL_CER_TYPE', title:'֤������', width:100, sortable:true ,formatter : function(value) {
//				var tmpData = app_cer_type_dict_data;
//				for(var i =0;i<tmpData.length;i++){
//					if(tmpData[i].value == value){
//						return  tmpData[i].name;
//					}
//				}
//			  }
//			},
			{field:'HOL_CER_NO', title:'֤�����', width:200, sortable:true }
		]]
	});
}

/**********************************************************************************
*��������: getTime()
*����˵��: ��ȡϵͳ��ǰʱ��
*����˵��: 
*�� �� ֵ: ʱ��
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
*��������: submit()
*����˵��: ���ύ����  ��Ҫ��֤ ��֤���ύ
*����˵��: 
*�� �� ֵ: ʱ��
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/  
function submit(cer_state){
	var reg_unit_code="";
	if(selectedNaturalData){
		 reg_unit_code = selectedNaturalData.REG_UNIT_CODE;
	}
	
	var result = true;;
	  
	   //�ж���֤�Ƿ�ͨ��  ͨ����ִ����һ������ ��ͨ�� ֱ��return
//	   if(!validate()){
//		 return;
//	   }
	
		setButtonstate(cer_state);
	  
	   
	   //������֤ʱ��
	   
	   $("#PRINT_DATE").val(getTime());
	   $("#PRINTER").val(user);
	  // $("#CER_STATE").combodict("setValue",cer_state);
	   
	   _init_form_data = $("#main_form").serializeJson(); 		//���»�ȡ��ǰ��ҳ�����ݳ�ʼ��ֵ
	   //var cer_state = $("#CER_STATE").combo("getValue");											//��֤״̬
	   var printer=$("#PRINTER").val();																//��֤��
	   var printer_date=$("#PRINT_DATE").val();														//��֤ʱ��
	   var certificate_type = $("#CERTIFICATE_TYPE").combo("getValue");		
	   //���ز�֤����
	   var obj={
		    dataType: 'json',
			data:{"certificate.reg_unit_code":reg_unit_code,"certificate.printer":printer,"certificate.print_date":printer_date,"certificate.cer_state":cer_state,"certificate.certificate_type":certificate_type,"proc_id":proc_id,"time":new Date()},
		   // data:$("#cer").serialize(),
			url:'certificate!updateCertificate.action',
			contentType: "application/json; charset=GBK",
			success:function(data){
					if (data.success) {
					top.$.messager.alert('��ȷ��ʾ',data.tipMessage,'info',function(){
					
					});				
					} else {
						top.$.messager.alert('������ʾ',data.errorMessage,'error');
					}
		  },error:function(){
			  result = false;
		  }
	  };
	  $.ajax(obj);	
	  
	  return result;
}
/**********************************************************************************
*��������: setButtonstate
*����˵��: ������֤��ȡ����Ť״̬      
*����˵��: tmp_cer_state
*�� �� ֵ: true/false
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function setButtonstate(tmp_cer_state){
	  if(tmp_cer_state==CER_STATE.UN_DONE){
		   $("#submit").linkbutton("enable");
		    $("#cancel").linkbutton("disable");
	   }else if(tmp_cer_state==CER_STATE.DONE){
		   $("#submit").linkbutton("disable");
		    $("#cancel").linkbutton("enable");
	   }
}
/**********************************************************************************
*��������: validate
*����˵��: ��֤ҳ���ϵķǿ�  �����ݸ�ʽ   
*����˵��: 
*�� �� ֵ: true/false
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
	//��ʾ��Ϣ 
	var message;
		//�ύʱ���жϵ�ǰҳ���Ƿ��޸�
		if(!v_flag){
//			var cer_state = $("#CER_STATE").combo("getValue");	
//			if(cer_state != CER_STATE.DONE){
//				message = '������֤�����ύ!';
//				result.message = message;
//				return result;
//			}
//			 _cur_form_data = $("#main_form").serializeJson(); 
//				var r = equal(_init_form_data,_cur_form_data);
//				if(!r){
//					message = '���ݼ��޸ģ����ȱ�����ύ��';
//					result.message = message;
//					return result;
//			}
		}else{
			 _init_form_data = $("#main_form").serializeJson(); 		//���»�ȡ��ǰ��ҳ�����ݳ�ʼ��ֵ
		}
		 
		
	result.result = true;
	return result;
}


