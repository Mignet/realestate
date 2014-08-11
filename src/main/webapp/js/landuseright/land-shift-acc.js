/*********************************************************************************
*��  ��  ��  ��: land_shift_acc.js
*��  ��  ��  ��: ����ʹ��Ȩת�ƵǼ�
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Joyon
*��  ��  ��  �ڣ� 2014-03-01
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
var proc;											//��ǰ���̶���
proc=this.parent.proc;								//���̸�ֵ
var proc_node = proc.activName;						//���̽ڵ�
var proc_id = proc.procId;							//����ʵ��ID
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
		string12: "����",
		string13:"�ⶨ����"
	};
var userDataGrid;   					//������������Ϣ��
var transferorDataGrid;					//ת�÷�
var houseDataGrid;						//���ز���Ϣ��
var _init_form_data;							//��ʼ��ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var _cur_form_data;								//��֤ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
/**********************************************************************************
*��������: 
*����˵��: js����ʱ����  ���ر��������  ����Ǽǲ������ݵ������  ��ȡ���������ֵ���   ���ط��ز���Ϣ  ������������Ϣ
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/  
$(function() {
	
	
	if(proc_node == state1.string0){
		saveBKUserightShipToBusUserightShip();
	}
	//���ز���ϢDatagrid
	houseDataGrid = $('#table_house').datagrid({
		title:'������Ϣ',
		height:120,
		url:ctx+"/houseownership/shiftreg!getHouseInfo.action?time="+new Date()+"&proc_id="+proc_id,
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
		     		{
		     			title : '�ڵغ�',
		     			field : 'PARCEL_CODE',
		     			width:140
		     		}, {
		     			title : '¥��������',
		     			field : 'BUILDING_NAME',
		     			width:140
		     		}, {
		     			title : '����',
		     			field : 'ROOMNAME',
		     			width:140
		     		}, {
		     			title : '��ַ',
		     			field : 'HOUSE_LOCATION',
		     			width:140
		     		}, {
		     			title : '��Ŀ����',
		     			field : 'PRO_NAME',
		     			width:140
		     		}
		     		] ],
		     		// ��ͷ����ӹ�������
		     		onClickRow : function() {
		     		},
		     		onLoadSuccess : function() {
		     		}
	});
	//ת�÷�datagrid
	transferorDataGrid = $('#table_transferor').datagrid({
		//fit : true,
		title:'ת�÷�',
		height:140,
		// ���������Դ
		url :ctx+"/houseownership/shiftreg!getHolder.action?time="+new Date()+"&proc_id="+proc_id,
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
		     		{
		     			title : '������',
		     			field : 'HOL_NAME'
		     		}, {
		     			title : '����������',
		     			field : 'HOL_TYPE',formatter :dicts.format.app_type_format
		     		}, {
		     			title : '֤������',
		     			field : 'HOL_CER_TYPE',formatter : dicts.format.app_cer_type_format
		     		}, {
		     			title : '֤�����',
		     			field : 'HOL_CER_NO'
		     		}, {
		     			title : '�ݶ�',
		     			field : 'PORTION'
		     		}, {
		     			title : '��ַ',
		     			
		     		}, {
		     			title : '����������',
		     			field : 'LEGAL_NAME'
		     		},  {
		     			title : '������',
		     			field : 'AGENT_NAME'
		     		},{
		     			title : '������֤������',
		     			field : 'AGENT_CER_TYPE'
		     		},{
		     			title : '���������֤��',
		     			field : 'AGENT_CER'
		     		}, {
		     			title : '��������ϵ�绰',
		     			field : 'AGENT_TEL '
		     		}
		     		] ],
		     		// ��ͷ����ӹ�������
		     		onClickRow : function() {
		     		},
		     		onLoadSuccess : function() {
		     		}
	});
	//������������Ϣ��
	 userDataGrid = $('#table_user').datagrid({
		//fit : true,
		title:'���÷�',
		height:200,
		// ���������Դ
		url :ctx+"/houseownership/initialreg!getAppMessage.action?time="+new Date()+"&proc_id="+proc_id,
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
		},{
			title : '���������֤��',
			field : 'agent_cer'
		}, {
			title : '��������ϵ�绰',
			field : 'agent_tel'
		}
		] ],
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
		}
	});
	 //��õǼ���Ϣ
	 getPreRegMess();
	//����Ȩ��״̬
	setState(proc_node);
	//���ύ��Ť���¼� 
//	document.getElementById("submit").onclick = function() {
//		submit();
//    };
});
/**********************************************************************************
*��������: getSelected(func)
*����˵��: ѡ������ĳһ�е����ݡ� ѡ�������˱�һ������
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/	
function getSelected(func) {
	var selectedrow = $('#table_user').datagrid('getSelected');

	if (selectedrow) {
		row = selectedrow;
		// ������غ���
		func.call(this, selectedrow);
	} else {

		$.messager.alert('��ʾ��', '����ѡ�б���е�ĳһ��.');
	}
}
/**********************************************************************************
*��������: doAdd()
*����˵��: ��������������
*����˵��: ��Ҫѡ����������һ������
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
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
}
/**********************************************************************************
*��������: doEdit()
*����˵��: �����˱༭����
*����˵��: ��Ҫѡ����������һ������
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
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
}
/**********************************************************************************
*��������: doDelete()
*����˵��: ������ɾ������
*����˵��: ��Ҫѡ����������һ������
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
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
}
/**********************************************************************************
*��������: setState(proc_node)
*����˵��: ���õ�ǰҳ�����״̬   ��Ҫ�������̽ڵ����ж�
*����˵��: ���뵱ǰ���̽ڵ� 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function setState(proc_node) {
	
	if(proc_node == state1.string0){
		$(".first_hidden").css("display","none");
		//$("#tr_").css("display","none");
		$("#REG_STATION").combo('disable');
		$("#REG_TYPE").combo('disable');
		
		
		$("#HOUSE_ATTR").combo('enable');
		$("#GET_MODE").combo('enable');
	}
	if(proc_node == state1.string1){
		//$("#REG_STATION").combo('disable');
		$(":input").attr("disabled", "disabled");
		$("#REG_TYPE").combo('disable');
		$("#HOUSE_ATTR").combo('enable');
		$("#GET_MODE").combo('enable');
		$("#LOCATION_REG_UNIT").combo('enable');
		
		$("#REG_STATION").combo('disable');
		$("#REG_VALUE").removeAttr("disabled"); 
		
		//$("#FLAT_POSITION").combo('disable');
		$("#EXCURSUS").removeAttr("disabled"); 	
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		
		
	}
	if (!(proc_node == state1.string0)&&!(proc_node == state1.string1)) {
		$("#REG_STATION").combo('disable');
		$("#HOUSE_ATTR").combo('disable');
		$("#GET_MODE").combo('disable');
		$("#FLAT_POSITION").combo('disable');
		$("#REG_TYPE").combo('disable');
		$("#LOCATION_REG_UNIT").combo('disable');
		
		$(":input").attr("disabled", "disabled");
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		

	}

}
/**********************************************************************************
*��������: ҳ��У�鷽��
*����˵��: ��֤ҳ���ϵķǿ�  �����ݸ�ʽ   
*����˵��: v_flag ��ֵ�����Ǳ������
*�� �� ֵ: obj  result(trueͨ��  false��ͨ��) message(��Ϣ)  page_name(��ǰҳ������)
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function submit(){
	_init_form_data = $("#main_form").serializeJson(); 
//	var validateResult = validate();
//	if(!validateResult.result){
//		return false;
//	}
 	if(proc_node == state1.string0){
		return saveRegInfo();		
	}
	if(proc_node == state1.string1){
//		if(!saveExcursus()){
//			return false;
//		}	
		return saveRegInfo();
	}
	return true;
}

// �Ǽ���Ϣ������²���
/**********************************************************************************
*��������: saveRegInfo
*����˵��: �Ǽ���Ϣ������²���
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function saveRegInfo() {
	var result = true;
	//alert("saveRegInfo...");
	//var get_mode = $("#GET_MODE").combo('getValue');
	var reg_value = $("#REG_VALUE").numberbox('getValue');
	var reg_station = $("#REG_STATION").combo('getValue');
	//var house_attr = $("#HOUSE_ATTR").combo('getValue');
	var location_reg_unit = $("#LOCATION_REG_UNIT").combo('getValue');
	
	 var EXCURSUS = $("#EXCURSUS").val();
	  $.ajax({
	   		dataType:'json',
	   		url:ctx+"/houseownership/shiftreg!saveLandShiftRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"reg_value":reg_value,"reg_station":reg_station,"excursus":EXCURSUS,"location_reg_unit":location_reg_unit},
	   		success:function(data){
	   			//alert(JSON.stringify(data));
			 	if(data){
			 		top.$.messager.alert('����ɹ���ʾ',data.result,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',"����ʧ��",'error');
				}
	   		},error:function(data){
	   			top.$.messager.alert('����ʧ����ʾ',"����ʧ��!"+JSON.stringify(data),'error');
	   			result = false;
	   		}
	   	});  
	  
	  return result;
}
/**********************************************************************************
*��������: saveExcursus()
*����˵��: ���淿�ز�֤���ǵ���֤����
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function  saveExcursus(){
	 var EXCURSUS = $("#EXCURSUS").val();
		var result = true;
		 
		//var result = validate(); 
		
			
			 $.ajax({
			   		dataType:'json',
			   		url:ctx+"/common/certificate!saveExcursus.action?time="+new Date()+"&proc_id="+proc_id,
			   		contentType:"application/x-www-form-urlencoded; charset=GBK",
			   		//�������л�����
			   		data:{"excursus":EXCURSUS},
			   		success:function(data){
					 	if(data){
					 		top.$.messager.alert('����ɹ���ʾ',"����ɹ�",'info',function(){
							});	
					 		
					 	}else {
							top.$.messager.alert('����ʧ����ʾ',"����ʧ��",'error');
						}
			   		},error:function(data){
			   			result = false;
			   		}
			   	});  
		
		
		     return result;
};
/**********************************************************************************
*��������: getPreRegMess()
*����˵��: ��ȡ������ǰ�ô��ڴ��ݵĵǼ���Ϣ
*����˵��: 
*�� �� ֵ: �޷���ֵ  ֱ�ӻ����ǰҳ��
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function getPreRegMess(){
	$.ajax({
		    dataType: 'json',
			url:ctx+"/houseownership/shiftreg!getLandShiftRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
			success:function(data){
			 	if(data){
			 		//data = data.RegInfo;
			 		//alert(JSON.stringify(data));
			 		$("#REG_CODE").val(data.REG_CODE);
			 		$("#REG_STATION").combodict("setValue",data.REG_STATION);
			 		$("#BUS_DETAIL").val(data.PROC_NAME);
			 		$("#REG_TYPE").combodict("setValue",data.REG_TYPE);
			 		
			 		$("#EXCURSUS").text(data.EXCURSUS);
			 		//$("#HOUSE_ATTR").val(data.HOUSE_KIND);
			 		$("#HOUSE_ATTR").combodict("setValue",data.HOUSE_ATTR);
			 		if(data.GET_MODE){
			 			//alert($("#GET_MODE").combo("getValue"));
			 			$("#GET_MODE").combodict("setValue",data.GET_MODE);
			 		}
			 		if(data.GET_PRICE){
			 			$("#REG_VALUE").numberbox('setValue', data.GET_PRICE);
			 		}
			 		if(data.LOCATION_REG_UNIT){
			 			$("#LOCATION_REG_UNIT").combodict("setValue",data.LOCATION_REG_UNIT);
			 		}
			 		if(data.naturalInfo){
			 			var n_data = data.naturalInfo;
			 			//alert(n_data.BUILDING_NAME)
			 			//alert(JSON.stringify(n_data));
			 			$('#table_house').datagrid('load',n_data);
			 		}
			 	}
			 	_init_form_data = $("#main_form").serializeJson(); 		//���»�ȡ��ǰ��ҳ���ʼ��ֵ
			}
		});
}
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
			result:true,
			message:'',
			page_name:'�����'
	}
	//��ʾ��Ϣ 
	var message;
	if(proc_node==state1.string1){
		message = "���ز�֤���ǣ�";
		var vEXCURSUS = new tt.Field(message,"EXCURSUS");  
	    //�ǿռ���
	    tt.vf.req.add(vEXCURSUS);
		
	    var valResult = tt.validate();
		if(!valResult.result){
			 result.message=valResult.message;
			 result.result=false;
			 return result;
		} 
	}
	
	//��������֤
	if(proc_node==state1.string0){
		
		var location_reg_unit =  $("#LOCATION_REG_UNIT").combo('getValue');
		if($.trim(location_reg_unit).length==0){
			message = '��ѡ�񷿵ز���������';
			result.message=message;
			 result.result=false;
			 return result;

		}
		
//		var house_attr = $("#HOUSE_ATTR").combo('getValue');
//		if($.trim(house_attr).length==0){
//			message = '��ѡ�񹺷����ʣ�';
//			result.message=message;
//			 result.result=false;
//			 return result;
//
//		}
		
//		var get_mode = $("#GET_MODE").combo('getValue');
//		
//		if($.trim(get_mode).length==0){
//			message = '��ѡ��ת�Ʒ�ʽ��';
//			result.message=message;
//			 result.result=false;
//			 return result;
//
//		}
		var reg_value = $("#REG_VALUE").numberbox('getValue');
		if($.trim(reg_value).length==0){
			message = '������ǼǼۿ';
//			top.$.messager.alert('��ʾ',message, 'info',
//					function() {
//						
//					});	
			result.message=message;
			 result.result=false;
			 return result;
	
		}
		
		
		//��֤���÷�
		var tmpData = $('#table_user').datagrid("getRows");
		if(tmpData.length==0){
			message = '��¼�����÷���';
//			top.$.messager.alert('��ʾ',message, 'info',
//					function() {
//						
//					});	
			result.message=message;
			 result.result=false;
			 return result;
		}

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
*��������:getApp_type_dict
*����˵��:��ȡ�����������ֵ���ֵ  ���޸�ʱ ������ѡ��
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�app_type_dict_data��ֵ ���޷���ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/

function getApp_type_dict_data(){
	if(!app_type_dict_data){
		$.ajax({
			url:ctx+'/common/dict!getNewDictByCode.action?code=043',  
			dataType : 'json',
			type : 'post',
			data : {"proc_id":proc_id},
			async:false,
			success : function(data) {
				app_type_dict_data = data;
			}
		});
	}
}
/**********************************************************************************
*��������:getApp_cer_type_dict_data
*����˵��:��ȡ������֤�������ֵ���ֵ  ���޸�ʱ ������ѡ��
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�app_cer_type_dict_data��ֵ ���޷���ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function getApp_cer_type_dict_data(){
	if(!app_cer_type_dict_data){
		$.ajax({
			url:ctx+'/common/dict!getNewDictByCode.action?code=002',  
			dataType : 'json',
			type : 'post',
			data : {"proc_id":proc_id},
			async:false,
			success : function(data) {
				app_cer_type_dict_data = data;
			}
		});
	}
}

/**********************************************************************************
*��������: ����ǰһ��ʹ��Ȩ��Ч�Ǽǲ���Ϣ���������
*����˵��: ����ǰһ��ʹ��Ȩ��Ч�Ǽǲ���Ϣ���������
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function saveBKUserightShipToBusUserightShip(){
	$.ajax({
		url:ctx+"/houseownership/correction!saveBKUserightShipToBusUserightShip.action",
		dataType : 'json',
		type : 'post',
		data : {"proc_id":proc_id},
		async:false,
		success : function(data) {
			
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
	if(proc_node == state1.string0 || proc_node == state1.string1){
		_cur_form_data = $("#main_form").serializeJson(); 
		
		var r = equal(_init_form_data,_cur_form_data);
		//�������ȷ���  ҳ�����ݼ��޸�  ����true
		if(!r){
		  return true;
		}
	}
	return false;
}

