/*********************************************************************************
*��  ��  ��  ��: realestate-cancel.js
*��  ��  ��  ��: ���ز�֤ע���Ǽ������js
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: xuzz
*��  ��  ��  �ڣ� 2014-02-28
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
//��ʼ������.	
var proc=this.parent.proc;
var proc_node = proc.activName;
var proc_id = proc.procId;
//var proc_id = 4;//1000016366;
//var proc_node = "����";
//��ǰ��Ԫ�ص�ֵ
var _init_form_data;							//��ʼ��ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var _cur_form_data;								//��֤ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
//��ʼ��ʱ��Ԫ�ص�ֵ
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
var userDataGrid;
var mortgager;
var houseDataGrid;
/**********************************************************************************
*��������: 
*����˵��: ҳ���ʼ��
*��������: xuzz
*��������: 2014-02-28
*�޸���ʷ: 
***********************************************************************************/
$(function() {
	houseDataGrid = $('#table_house').datagrid({
		title:'���ز���Ϣ',
		height:240,
		url:ctx+"/mortgage/morsetup!getRegunitMess.action?time="+new Date()+"&proc_id="+proc_id,
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

		     		// {field:'ck',checkbox:true},
		     		
		     		{
		     			title : '�ڵغ�',
		     			field : 'PARCEL_CODE',
		     			width:80
		     			
		     		}, {
		     			title : '�Ǽǵ�Ԫ����',
		     			field : 'TYPE',formatter : dicts.format.reg_unit_type_format,
		     			width:100
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
		     			field : 'CODE',
		     			
		     		},
		     		{
		     			title : '����',		     		
		     			field:'button',
		     			formatter:function(value,rec){
		     				 var btn = '<a class="editcls" onclick="" href="javascript:void(0)">�鿴</a>';  
		                     return btn;
		     			
		     			}
		     		}
		     		] ],
		     		// ��ͷ����ӹ�������
		     		onClickCell:function(rowIndex, field, value){
		     			if(field=="button"){
		    				$('#table_house').datagrid('selectRow',rowIndex);
		    				dowatch(this);
		    			}
		    		},
		     		onLoadSuccess : function() {
		     			$('.editcls').linkbutton({text:'�鿴'});
		     			//Ϊfalseʱ��ʾ����Ǽǻ�ȡ��������
		     			//getRevokeapproval(false);
		     			_init_form_data = $("#add_app_form").serializeJson(); 
		     		}
	});
	
	//������������Ϣ��
	 userDataGrid = $('#table_user').datagrid({
		fit : true,
		title:'������',
		height:200,
		// ���������Դ
		url :ctx+"/houseownership/initialreg!getAppMessage.action?time="+new Date()+"&proc_id="+proc_id,
		// ���ÿ�п���Զ���Ӧ����ܿ��
		autoRowHeight : true,
		fitColumns : false,
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
		// �Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		// checkOnSelect:true,
		// �Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		// selectOnCheck:true,
		// ����������
		columns : [ [
		// ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������

		// {field:'ck',checkbox:true},
		{
			title : '������',
			field : 'app_name'
			
		}, {
			title : '����������',
			field : 'app_type',formatter : dicts.format.app_type_format
			
		}, {
			title : '֤������',
			field : 'app_cer_type',formatter : dicts.format.app_cer_type_format
			
		}, {
			title : '֤�����',
			field : 'app_cer_no'
			
		}, {
			title : '�ݶ�',
			field : 'app_port'
			
		}, {
			title : '��ַ',
			field : 'app_address'
			
		}, {
			title : '��ϵ�绰',
			field : 'app_tel'
			
		}, {
			title : '����������',
			field : 'legal_name'
		
		},  {
			title : '������',
			field : 'agent_name'
			
		}, 
		{
			title : '������֤������',
			field : 'agent_cer_type'
			
		},
		
		
		{
			title : '���������֤��',
			field : 'agent_cer'
			
		}, {
			title : '��������ϵ�绰',
			field : 'agent_tel'
			
		}

		] ],
		// ��ͷ����ӹ�������
		// ��ͷ����ӹ�������
		toolbar : [ {
			id : 'user_add',
			text : '����',
			iconCls : 'icon-add',
			handler : doAdd
		}, '-', {
			id : 'user_edit',
			text : '�༭',
			iconCls : 'icon-pencil',
			disabled : true,
			handler : doEdit
		}, '-', {
			id : 'user_delete',
			text : 'ɾ��',
			iconCls : 'icon-remove',
			disabled : true,
			handler : doDelete
		}],
		onClickRow : function() {
			//�����ʱ����༭������ɾ������ť ֻ������ͳ��󻷽ڼ���
			if(proc_node==state1.string0){
				$('#user_edit').linkbutton('enable');
				$('#user_delete').linkbutton('enable');
			}
		},
		onLoadSuccess : function(data) {
			//������Ͻ��á��༭������ɾ������ť
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
			
			//ֻ�����������вŶ�Ȩ���˱�����м�¼  �������ڲ�����Ȩ���˱��
		}

	});
	 getPreRegMess();
	 
	//����Ȩ��״̬
	setState(proc_node);
	//_init_form_data = $("#add_app_form").serializeJson(); 
});

// ����
function doAdd() {
	openInTopWindow({
		// ����Ԫ�ص�id
		id : 'add_user_win',
		// ����iframe��src
		src : ctx+'/jsp/common/applicant/addapplicant.jsp?time='+new Date(),
		// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
		destroy : true,
		// ���ڱ���
		title : '����������',
		// ���ڿ�
		width : 700,
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
				userDataGrid : userDataGrid
			};
			this.init(proc_id);
		}
	});
};

// �༭
function doEdit() {
	var row = userDataGrid.datagrid('getSelected');

	openInTopWindow({
		// ����Ԫ�ص�id
		id : 'edit_user_win',
		// ����iframe��src
		src : ctx+'/jsp/common/applicant/editapplicant.jsp',
		// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
		destroy : true,
		// ���ڱ���
		title : '�༭������',
		// ���ڿ�
		width : 700,
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
				user : row,
				userDataGrid : userDataGrid
			};
			this.init(row);
		}
	});
	_init_form_data = $("#add_app_form").serializeJson(); 
};


// ɾ��
function doDelete() {
	var row = userDataGrid.datagrid('getSelected');
	top.$.messager.confirm('ȷ��', 'ȷ��Ҫɾ������������Ϊ[' + row.app_name + ']��', function(
			result) {
		if (result) {
			$.ajax({
				url : ctx+"/houseownership/initialreg!deleteApplicant.action?time="+new Date(),
				type : 'post',
				data : {
					applicant_id : row.applicant_id
				},
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						top.$.messager.alert('��ʾ', data.tipMessage, 'info',
								function() {
							        //alert("ɾ��֮��ˢ��");
									userDataGrid.datagrid('reload');
								});
					} else {
						top.$.messager.alert('��ʾ', data.errorMessage, 'error');
					}
				}
			});
		}
	});

};



/**********************************************************************************
*��������: saveRevokeapproval
*����˵��: �������ڱ��泷��ԭ��
*����˵��: 
*�� �� ֵ: 
*��������: xuzz
*��������: 2014-03-27
*�޸���ʷ: 
***********************************************************************************/
function  saveRevokeapproval(){
	var Revokeapproval = $("#CAN_REASON").val();
	var can_type=$("#CAN_TYPE").combodict('getValue');
	//var selectedrow = $('#table_house').datagrid('getSelected');
	var url;
	url= ctx+"/houseownership/initialreg!saveRevokeapproval.action?time="+new Date()+"&proc_id="+proc_id;
	var result = true; 
		 $.ajax({
		   		dataType:'json',
		   		url:url,
		   		contentType:"application/x-www-form-urlencoded; charset=GBK",
		   		//�������л�����
		   		data:{"revo":Revokeapproval,"cantype":can_type},
		   		success:function(data){
				 	if(data){
				 		top.$.messager.alert('����ɹ���ʾ',"����ɹ�",'info',function(){
						});	
				 	}else {
						top.$.messager.alert('����ʧ����ʾ',"����ʧ��",'error');
					}
		   		},error:function(data){
		   			result = false;
		   			top.$.messager.alert('����ʧ����ʾ',"����ʧ��",'error');
		   		}
		   	});  
	return result;
};


	/**********************************************************************************
	*��������: dowatch
	*����˵��: �鿴�Ǽǵ�Ԫ��ϸ��Ϣ
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: xuzz
	*��������: 2014-03-27
	*�޸���ʷ: 
	***********************************************************************************/
		function dowatch(button){
			var row = $('#table_house').datagrid('getSelected');
			var obj={};
			 	obj.WHERE_CODE=row.CODE;
				obj.REG_UNIT_TYPE=row.TYPE;
			//alert(JSON.stringify(row));
			openInTopWindow({
				// ����Ԫ�ص�id
				id : 'add_user_win',
				// ����iframe��src
				src : ctx+'/bookmanage/book-manage!home.action?reg_unit_type='+row.TYPE+'&time='+new Date(),
				// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
				destroy : true,
				// ���ڱ���
				title : '���ز���Ϣ',
				// ���ڿ�
				width : 988,
				// ���ڸ�
				height : 598,
				modal : true,
				// ������iframe��window�����onLoad�ص���������
				onLoad : function() {
					// �˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
					// ��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
					this.openerWindow = window;
					// ����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
					this.args = {
						userDataGrid : userDataGrid,
						regunit:row
					};
					this.init(obj);
				}
			});
		}


	/**********************************************************************************
	*��������: setState
	*����˵��: �������̽ڵ㣬����ҳ��ؼ���Ȩ��
	*����˵��: proc_node�����̽ڵ�����
	*�� �� ֵ: ��
	*��������: xuzz
	*��������: 2014-03-27
	*�޸���ʷ: 
	***********************************************************************************/
function setState(proc_node) {
	if(proc_node == state1.string1){
		$("#REG_STATION").combo('disable');
		$(".edit_table").attr("disabled", "disabled");	
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');
		$('#user_add').linkbutton('disable');
		$("#CAN_REASON").attr("disabled", "disabled");
		if($('#mort_reg_date').val()==null){
		$('#mort_reg_date').val(getTime());
		}
	}
	if (!(proc_node == state1.string0)&&!(proc_node == state1.string1)) {
		$("#REG_STATION").combo('disable');
		$(":input").attr("disabled", "disabled");
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');
		$('#user_add').linkbutton('disable');
		$("#mort_type").combo('disable');
		$(".com").combo('disable');
	}
	
	if (proc_node == state1.string4) {
		$('#user_edit').linkbutton('enable');
		$('#user_delete').linkbutton('enable');

		//$('#user_add').linkbutton('enable');

	}
    if(proc_node != state1.string0){
		
		$(".mortreg").css({display:"block"});
		//$(".remark").css({display:"block"});
	}

}
/**********************************************************************************
*��������: submit
*����˵��: ���ύ����
*����˵��: 
*�� �� ֵ: ��
*��������: xuzz
*��������: 2014-03-27
*�޸���ʷ: 
***********************************************************************************/
function submit(){	
	//_init_form_data = $("#acceptance").serializeJson();
	saveRevokeapproval();
		
}

/**********************************************************************************
*��������: getRevokeapproval
*����˵��: ��ȡ����ԭ��
*����˵��: 
*�� �� ֵ: ��
*��������: xuzz
*��������: 2014-03-27
*�޸���ʷ: 
***********************************************************************************/
function getRevokeapproval(){
	var url;
	url= ctx+"/houseownership/initialreg!getRevokeapproval.action?time="+new Date()+"&proc_id="+proc_id;
	$.ajax({
		    dataType: 'json',
			url:url,
			success:function(data)
			{
			 	if(data)
			 	{
			 		$("#CAN_REASON").text(data.can_reason);
			 		$("#CAN_TYPE").combodict('setValue',data.can_type);
			 		//$("#CAN_TYPE").val(data.can_type);
			 	}
			 	_init_form_data = $("#add_app_form").serializeJson(); 
			}
		});
}




/**********************************************************************************
*��������: getPreRegMess
*����˵��: ��ȡ�Ǽǻ�����Ϣ������Ǽǵ㡢ҵ���������Ǽ����͡��ǼǱ�ŵ�
*����˵��: 
*�� �� ֵ: ��
*��������: xuzz
*��������: 2014-03-27
*�޸���ʷ: 
***********************************************************************************/
function getPreRegMess(){
	$.ajax({
		    dataType: 'json',
			url:ctx+"/mortgage/morsetup!getRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
			success:function(data){
			 	if(data){
			 		//alert(JSON.stringify(data));
			 		$("#REG_CODE").val(data.REG_CODE);
			 		$("#REG_STATION").combodict("setValue",data.REG_STATION);
			 		$("#BUS_DETAIL").val(data.PROC_NAME);
			 		$("#REG_TYPE").combodict("setValue",data.REG_TYPE);
			 		if(data.MORT_MODE){
			 			//$("#MORT_MODE").combodict("setValue",data.MORT_MODE);
			 			
			 		}
			 		getRevokeapproval();
			 	 
			 	}
			 	
				}
		});
	
}
/**********************************************************************************
*��������: validate
*����˵��: �Ǽ���Ϣ��ʽУ��
*����˵��: 
*�� �� ֵ: ��
*��������: xuzz
*��������: 2014-03-27
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
	if(proc_node==state1.string0){
		message = "ע��ԭ��";
		var Revokeapproval = new tt.Field(message,"CAN_REASON");  
	    //�ǿռ���
	    tt.vf.req.add(Revokeapproval);
		
	    var valResult = tt.validate();
		if(!valResult.result){
			 result.message=valResult.message;
			 result.result=false;
			 return result;
		} 
	}
     //�㱣�水Ť  ��ʼ������
     if(v_flag){
			_init_form_data = $("#add_app_form").serializeJson(); 
		}
	//���л���ǰ����ֵ
	_cur_form_data = $("#add_app_form").serializeJson();
	//alert(JSON.stringify(_init_form_data)+" "+JSON.stringify(_cur_form_data));
	var r = equal(_init_form_data,_cur_form_data);
	if(!r){
		message ="��Ԫ�����޸ģ��뱣����ύ��";
     	result.message = message;
 		return result;
	}	
	
	result.result=true;
	return result;
}
/**********************************************************************************
*��������: getTime
*����˵��: ��ȡ��ǰϵͳʱ��
*����˵��: ��
*�� �� ֵ: ��
*��������: xuzz
*��������: 2014-03-27
*�޸���ʷ: 
***********************************************************************************/
function getTime(){
	  var myDate = new Date();
	  var year = myDate.getFullYear();
	  var month = myDate.getMonth()+1;
	  var date = myDate.getDate(); 
     var mytime=myDate.toLocaleTimeString();     //��ȡ��ǰʱ��	  
	var time = year+"-"+month+"-"+date;
	return time;
	
}
/**********************************************************************************
*��������: pageDataIsChange
*����˵��: �жϵ�ǰҳ�������Ƿ��Ѿ��޸�
*����˵��: 
*�� �� ֵ: �޸ķ���true   δ�޸ķ���false
*��������: xuzz
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function pageDataIsChange(){
	_cur_form_data = $("#add_app_form").serializeJson(); 
	
	var r = equal(_init_form_data,_cur_form_data);
	//�����ȷ���  ҳ������δ�޸�  ����false
	if(r){
	  return false;
	}
	return true;
}






