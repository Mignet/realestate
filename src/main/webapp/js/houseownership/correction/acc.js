/*********************************************************************************
*��  ��  ��  ��: acc.js
*��  ��  ��  ��: �����Ǽ������
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Joyon
*��  ��  ��  �ڣ� 2014-03-01
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
var proc;
proc=this.parent.proc;
var proc_node = proc.activName;

var proc_id = proc.procId;
//var proc_id = '1000016570';//1000016366;
//var proc_node = "����";
var changeLastEditIndex;	//���������޸����ݵ����һ��

var appLastEditIndex		//���������һ��

var old_data;										//���ݿ��еı��ǰ����    �ӷ���  �ڵ�   ¥��  �Ǽǲ�������Ȩ  ��ʹ��Ȩ��   Ȩ������ȡ��   

var change_data_list; 								//���ֵ����ȡ������Ҫ�����������

var change_dict_class_code = '060';					//��������������ֵ����(��ȡ����ֵ����  ��չ  ֱ�����ֵ�������Ӿ���)

var change_data_item = {};							//���������

var user_load_count	=0;								//�û�datagrid  ���ش���     �����ж���ʷȨ�����Ƿ�ı��    ����ı��   ���ڱ����¼�м�¼�µ�ǰ�ı������

var hisHolderData;									//��ʷȨ����(δ�ı����Ȩ����)

var changedHolderData;								//�ı�����Ȩ����

var cur_cbx_id;										//��ǰѡ�еĸ�ѡ��id  �����ж��Ƿ�ʹ���ֵ���

var is_combo_dict ='text';							//��¼ѡ�б����   editor�Ƿ����ֵ���    ���ֵ���     �����ֵ���object    ����ʹ��Ĭ�� 'text'

var house_attr_dict_data;							//���������ֵ�������
var flatlet_usage_dict_data;						//������;�ֵ�������
var use_per_dict_data;								//ʹ�������ֵ�������
var real_usage_dict_data;							//������;����̫�� ���� 


change_data_item.PRODUCT_NAME ='060001';			//���ز�����
change_data_item.HOUSE_LOCATION='060002';			//��������
change_data_item.BUILD_AREA='060003';				//�������
change_data_item.TAONEI_AREA='060004';				//�������
change_data_item.FLATLET_USAGE='060005';			//������;
change_data_item.HOUSE_ATTR='060006';				//��������
change_data_item.REG_VALUE='060007';				//�ǼǼ۸�
change_data_item.HOL_NAME='060016';					//Ȩ��������
change_data_item.HOL_CER_NO='060017';				//���֤����
change_data_item.PARCEL_CODE='060008';				//�ڵغ�
change_data_item.PARCEL_AREA='060009';				//�ڵ����
change_data_item.REAL_USAGE='060010';				//������;	
change_data_item.LOCATION_AREA='060011';			//������
change_data_item.LAND_ADDRESS='060012';				//����λ��
change_data_item.USE_PER='060013';					//ʹ������
change_data_item.PAR_REG_VALUE='060014';			//���ؼۿ�
change_data_item.ADD_PARCEL_PRICE='060015';			//���ؼۿ�

var reg_unit_type ={};
reg_unit_type.PARCEL ='009003';						//�Ǽǵ�Ԫ��� �ڵ�
reg_unit_type.BUILDING ='009002';					//�Ǽǵ�Ԫ���  ¥��
reg_unit_type.HOUSE ='009001';						//�Ǽǵ�Ԫ��� ����

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
var userDataGrid;
var transferorDataGrid;
var houseDataGrid;
var _init_form_data;							//��ʼ��ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var _cur_form_data;								//��֤ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�


$(function() {
	 
	//��ȡ��Ҫ����������� 
	getChangeOldInfo();
	
		
		//�жϵǼ��Ƿ��ݻ� �ڵ�  �ֱ���ò�ͬ�ĵǼǲ���Ϣ  ����ȡ���Ե��ֵ�������
		if(old_data.reg_unit_type == reg_unit_type.HOUSE){
			if(proc_node == state1.string0){
				saveBKOwnerShipToBusOwnerShip();
			}
			//��ȡ���������ֵ���list
			gethouse_attr_dict_data();
			//��ȡ������;�ֵ���
			getflatlet_usage_dict_data();
		}else if(old_data.reg_unit_type == reg_unit_type.PARCEL){
			if(proc_node == state1.string0){
				saveBKUserightShipToBusUserightShip();
			}
			//��ȡ����ʹ�������ֵ���
			getuse_per_dict_data() ;
			//��ȡ������;�ֵ���
			getreal_usage_dict_data();
		}
		
	

	
	//��ȡ�������  ������    ���checbox  ��ѡ��
	getchange_data_list();
	
	/**ע�͵������˺ͷ��ز�֤��Ϣ  2014��7��21�� 10:06:40 **/
	houseDataGrid = $('#table_house').datagrid({
		title:'���ز���Ϣ',
		height:120,
		url:ctx+"/houseownership/shiftreg!getHouseInfo.action?time="+new Date()+"&proc_id="+proc_id,
		// ���ÿ�п���Զ���Ӧ����ܿ��
		autoRowHeight : true,
		fitColumns : true,
		// ȥ���߿�
		border : true,
		striped : true,
		// �Ƿ��з�ҳ��
		pagination : false,
		// pagePosition:'top',
		// ÿҳ����
		//pageSize : 10,
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
	//������������Ϣ��
	 userDataGrid = $('#table_user').datagrid({
		//fit : true,
		title:'������',
		height:200,
		// ���������Դ
		url :ctx+"/houseownership/correction!getAppMessage.action?time="+new Date()+"&proc_id="+proc_id,
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
		toolbar : [ 
//		            {
//			id : 'user_add',
//			text : '����',
//			iconCls : 'icon-add',
//			handler : doAdd
//		}, '-', 
		{
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
			
			//alert(JSON.stringify(data));
			//������Ͻ��á��༭������ɾ������ť
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
			
			//ֻ�����������вŶ�Ȩ���˱�����м�¼  �������ڲ�����Ȩ���˱��
			if(proc_node=='����'){
				//alert(user_load_count);
				//��һ�μ���ʱ   �ѵ�ǰȨ���˱������ʷȨ����
				if(user_load_count == 0){
					//��ȡ�����и�������������
					var dbChangeRecordInfo = getChangeRecordInfo();
					//���û������˵��δ���й�Ȩ���˺�Ȩ����֤�����ݸı�
					if(dbChangeRecordInfo.total==0){
						hisHolderData = data.rows;
					}else{		//�й�Ȩ���˺�Ȩ����֤�����ݸı�   ѭ��  ���������е���ʷ������ ��ֵ����ʷȨ����
						
						tmpCompareHolderData =$('#table_user').datagrid("getRows");// data.rows;	
						
						//--���Ը���  ֱ�Ӹ���ԭʼ����  ��ֹ��������  ���¸ı�hisHolderData��   data.rows���ű�
						hisHolderData = $.extend(true,[],tmpCompareHolderData);
						//hisHolderData[0].app_tel='6666666666';
						dbChangeRecordInfo = dbChangeRecordInfo.rows;
						for(var i = 0 ;i<dbChangeRecordInfo.length;i++){
							//ֻѭ���Ƚ� ������ΪȨ�������Ƽ�Ȩ����֤����ŵ�������     -----���ж��Ƿ�ı���Ȩ����֤��
							if(dbChangeRecordInfo[i].CHANGE_CODE==change_data_item.HOL_CER_NO ){
								for(var j=0;j<hisHolderData.length;j++){
									if(dbChangeRecordInfo[i].OLD_DATA==hisHolderData[j].app_cer_no || dbChangeRecordInfo[i].NEW_DATA==hisHolderData[j].app_cer_no){
										hisHolderData[j].app_cer_no = dbChangeRecordInfo[i].OLD_DATA;
									}
								}
							//----�ٱȽ��Ƿ�ı���Ȩ��������     ���������޸ĸ�������
							}else if( dbChangeRecordInfo[i].CHANGE_CODE==change_data_item.HOL_NAME){
								for(var j=0;j<hisHolderData.length;j++){
									if(dbChangeRecordInfo[i].OLD_DATA==hisHolderData[j].app_name || dbChangeRecordInfo[i].NEW_DATA==hisHolderData[j].app_name){
										hisHolderData[j].app_name = dbChangeRecordInfo[i].OLD_DATA;
									}
								}
							}
							
						}
					}
					
				//��������±��浽�ı���Ȩ������	
				}else{
					
					changedHolderData = data.rows;
					
					//alert(JSON.stringify(changedHolderData));
					
					//alert(hisHolderData[0].app_name+changedHolderData[0].app_name);
					
					//�ж�Ȩ����(���ơ����֤��)�Ƿ��Ѿ��ı� ����ı����ڱ��������¼���в���һ������
					checkHolderIsChanged(hisHolderData,changedHolderData);
				}
				user_load_count++;
			}
		}

	});
	/**
	ע�͵������˺ͷ��ز�֤��Ϣ  2014��7��21�� 10:06:40
	**/
	
	$('#div_change_detail').datagrid({
		title:'��������',
		height:300,
		url:ctx+"/houseownership/correction!getChangeRecord.action?time="+new Date()+"&proc_id="+proc_id,
		// ���ÿ�п���Զ���Ӧ����ܿ��
		autoRowHeight : true,
		fitColumns : true,
		// ȥ���߿�
		border : true,
		striped : true,
		// �Ƿ��з�ҳ��
		pagination : false,
		// pagePosition:'top',
		// ÿҳ����
		//pageSize : 10,
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
		     			title : '����������',
		     			field : 'CHANGE_CODE',
		     			width:200,formatter : function(value) {
		     				//alert(JSON.stringify(change_data_list));
		     				for(var i =0;i<change_data_list.length;i++){
		     					if(change_data_list[i].value == value){
		     						return  change_data_list[i].name;
		     					}
		     				}
		    				
		    			}
		     			
		     		}, {
		     			title : '����ǰ',
		     			field : 'OLD_DATA',
		     			width:200,formatter : old_new_data_format
		     			
		     		}, {
		     			title : '������',
		     			field : 'NEW_DATA',
		     			width:200,editor:is_combo_dict,formatter : old_new_data_format
		     			
		     		}

		     		] ],
		     		toolbar : [{
		    			id : 'change_save',
		    			text : '����',
		    			iconCls : 'icon-ok',
		    			disabled : true,
		    			handler : saveChangeInfo
		    		}],
		     		// ��ͷ����ӹ�������
		     		onClickRow : changeRowClick,
		     		onLoadSuccess : function(data) {
		     			
		     			//���ݼ��سɹ�ʱ  checkbox ���ݻ���
		     			//alert(JSON.stringify(data.rows));
		     			var rows = data.rows;
		     			for(var i = 0;i<rows.length;i++){
		     				for(var j=0;j<change_data_list.length;j++){
		     					if(rows[i].CHANGE_CODE == change_data_list[j].value){
		     						$("#"+change_data_list[j].value).attr("checked","checked");
		     					}
		     					
		     				}
		     				
		     				//����з�������   ����·�������value��ֵ
//		     				if(rows[i].CHANGE_CODE==change_data_item.HOUSE_ATTR){
//		     					new_house_attr.value=rows[i].NEW_DATA;
//		     				}
		     			}
		     			
		     		}
	});
	 
	 getPreRegMess();
	//����Ȩ��״̬
	setState(proc_node);
	
//	document.getElementById("submit").onclick = function() {
//		submit();
//    };
});
	
	// ѡ������ĳһ�е����ݡ�
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
	}

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
	}



function setState(proc_node) {
	
	if(proc_node == state1.string0){
		$("#REG_STATION").combo('disable');
		$("#REG_TYPE").combo('disable');
		$('#app_save').linkbutton('enable');
		//$('#change_div').css("visibility","hidden");
		$('#change_div').css("display","none");
		$('#tr_excursus').css("display","none");
		
	}
	if(proc_node == state1.string1){
		$("#LOCATION_REG_UNIT").combo('disable');
		$("#REG_STATION").combo('disable');
		$("#REG_TYPE").combo('disable');
		$("#select_develop").attr('disabled','disabled');
		$('#change_save').linkbutton('enable');
		//$(":input").attr("disabled", "disabled");
		
		$("#EXCURSUS").removeAttr("disabled"); 	
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		
	}
	if (!(proc_node == state1.string0)&&!(proc_node == state1.string1)) {
		$("#LOCATION_REG_UNIT").combo('disable');
		$("#REG_STATION").combo('disable');
		$("#REG_TYPE").combo('disable');
		$(":input").attr("disabled", "disabled");
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		

	}
	
}
/**********************************************************************************
*��������: ���水Ť ��Ҫ���淿�ز�֤����
*����˵��: �ڳ��󻷽ڱ��淿�ز�֤����
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function submit(){
	_init_form_data = $("#main_form").serializeJson(); 
//	var result = validate();
//	if(!result.result){
//		return false;
//	}
	if(proc_node == state1.string0){
		return saveRegInfo();		
	}
	if(proc_node == state1.string1){
		//alert();
		return saveExcursus();		
	}
}
/**********************************************************************************
*��������: saveRegInfo
*����˵��: ����Ǽ���Ϣ
*����˵��: 
*�� �� ֵ: true/false
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function saveRegInfo(){
	var  result = true;
	var location_reg_unit = $("#LOCATION_REG_UNIT").combo("getValue");
	 $.ajax({
	   		dataType:'json',
	   		url:ctx+"/houseownership/correction!saveRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"location_reg_unit":location_reg_unit},
	   		success:function(data){
			 	if(data.result=="success"){
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
}

/**********************************************************************************
*��������: saveExcursus
*����˵��: �ڳ��󻷽ڱ��淿�ز�֤����
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function  saveExcursus(){
	 var EXCURSUS = $("#EXCURSUS").val();
	var result = true; 
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
		   			top.$.messager.alert('����ʧ����ʾ',"����ʧ��",'error');
		   		}
		   	});  
	return result;
};
/**********************************************************************************
*��������: getPreRegMess
*����˵��: ��ȡ������ǰ�ô��ڴ��ݵĵǼ���Ϣ
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function getPreRegMess(){
	$.ajax({
		    dataType: 'json',
			url:ctx+"/houseownership/correction!getRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
			success:function(data){
			 	if(data){
			 		//alert(JSON.stringify(data));
			 		$("#REG_CODE").val(data.REG_CODE);
			 		$("#REG_STATION").combodict("setValue",data.REG_STATION);
			 		$("#BUS_DETAIL").val(data.PROC_NAME);
			 		$("#REG_TYPE").combodict("setValue",data.REG_TYPE);
			 		$("#EXCURSUS").text(data.EXCURSUS);
			 		$("#HOUSE_ATTR").val(data.HOUSE_KIND);
			 		if(data.GET_MODE){
			 			//alert($("#GET_MODE").combo("getValue"));
			 			$("#GET_MODE").combodict("setValue",data.GET_MODE);
			 		}
			 		if(data.REG_VALUE){
			 			$("#REG_VALUE").val(data.REG_VALUE);
			 		}
			 		if(data.naturalInfo){
			 			var n_data = data.naturalInfo;
			 			//alert(n_data.BUILDING_NAME)
			 			//alert(JSON.stringify(n_data));
			 			$('#table_house').datagrid('load',n_data);
			 		}if(data.LOCATION_REG_UNIT){
			 			$("#LOCATION_REG_UNIT").combodict("setValue",data.LOCATION_REG_UNIT);
			 		}
			 	}
			 	_init_form_data =  $("#main_form").serializeJson();
				}
		});
}
/**********************************************************************************
*��������: ������ɸѡ�����¼�
*����˵��: ��ѡ��ѡ���¼�  ѡ��ʱ  �����һ��   δѡ��ʱ   ɾ����һ��
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function cbxClick(cbx){
	var cbx_id;
	var change_name;
	var old_value;
		cbx_id = cbx.id;
		change_name = $('#lbl_'+cbx_id).text();
		
		
		//alert("old_data.PARCEL_CODE:"+old_data.PARCEL_CODE);
		
		//���ʱ��old_value��ֵ
		if(cbx_id == change_data_item.PRODUCT_NAME){			//��Ŀ����
			old_value= old_data.naturaInfo.PRO_NAME;
		}else if(cbx_id == change_data_item.TAONEI_AREA){		//�������
			old_value= old_data.naturaInfo.TAONEI_AREA;
		}else if(cbx_id == change_data_item.BUILD_AREA){		//�������
			old_value= old_data.naturaInfo.BUILD_AREA;
		}else if(cbx_id == change_data_item.FLATLET_USAGE){		//������;
			old_value= old_data.naturaInfo.FLATLET_USAGE;
		}else if(cbx_id == change_data_item.REG_VALUE){			//���ݼ۸�
			old_value= old_data.regBookInfo.REG_VALUE;
		}else if(cbx_id == change_data_item.HOUSE_LOCATION){	//��������
			old_value= old_data.naturaInfo.HOUSE_LOCATION;
		}else if(cbx_id == change_data_item.HOUSE_ATTR){		//��������
			old_value= old_data.regBookInfo.HOUSE_ATTR;
		}else if(cbx_id == change_data_item.PARCEL_CODE){		//�ڵغ�
			old_value= old_data.naturaInfo.PARCEL_CODE;
		}else if(cbx_id == change_data_item.PARCEL_AREA){		//�ڵ����
			old_value= old_data.naturaInfo.PARCEL_AREA;
		}else if(cbx_id == change_data_item.REAL_USAGE){		//������;	
			old_value= old_data.naturaInfo.REAL_USAGE;
		}else if(cbx_id == change_data_item.LAND_ADDRESS){		//����λ��
			old_value= old_data.naturaInfo.LAND_ADDRESS;
		}else if(cbx_id == change_data_item.USE_PER){			//ʹ������
			old_value= old_data.naturaInfo.USE_PER;
		}else if(cbx_id == change_data_item.PAR_REG_VALUE){		//���ؼۿ�
			old_value= old_data.regBookInfo.REG_VALUE;
		}
		//����һ�����ؼۿ�
		//else if(cbx_id == change_data_item.LAND_ADDRESS){			//������
//			old_value= old_data.LAND_ADDRESS;
//		}
		
//		change_data_item.TAONEI_AREA='060015';				//���ؼۿ�
		if(cbx.checked){
			cur_cbx_id = cbx_id;
			 $('#div_change_detail').datagrid('appendRow',{  
				 CHANGE_CODE:cbx_id,OLD_DATA:old_value,NEW_DATA:''
            });
			 

             changeLastEditIndex = $('#div_change_detail').datagrid('getRows').length-1; 
             //�༭��ʱ�������  ��ȡ��������  ���
        	 var rows = $('#div_change_detail').datagrid('getRows');
             for ( var i = 0; i < rows.length; i++) {
             	 $('#div_change_detail').datagrid('endEdit', i);
             }
             
             $('#div_change_detail').datagrid('selectRow', changeLastEditIndex);  
             $('#div_change_detail').datagrid('beginEdit', changeLastEditIndex);  
             
             //�����ֵ���   ����cbx_id�ж�
             setEditorCombobox(cbx_id,changeLastEditIndex);
            
		}else{
			cur_cbx_id = "";
			top.$.messager.confirm('ȷ��', '�Ƿ�ȡ�� '+change_name+' ���', function(result){
				if (result) {
					var rows =  $('#div_change_detail').datagrid('getRows');
					for(var i = 0;i<rows.length;i++){
						
						if(cbx_id == rows[i].CHANGE_CODE){
							//alert(rows[i].change_name);
							$('#div_change_detail').datagrid('deleteRow',i);
						}
					}
				}else{
					$('#'+cbx_id).attr("checked","chedked");
				}
			});
		}
}

//�е���¼�
function changeRowClick(rowIndex, rowData){
	//�����ʱ����༭״̬    ֻ�ڳ��󻷽ڼ���
	if(proc_node!= state1.string1){
		return;
	}
	cur_cbx_id = rowData.CHANGE_CODE;
	//�����Ȩ���˻�Ȩ����֤��   ���ṩ�༭
	if(cur_cbx_id == change_data_item.HOL_CER_NO || cur_cbx_id == change_data_item.HOL_NAME){
		return;
	}
	
	if (changeLastEditIndex != rowIndex) {
		 $('#div_change_detail').datagrid('endEdit', changeLastEditIndex);
		 $('#div_change_detail').datagrid('beginEdit', rowIndex);
		
	}
	changeLastEditIndex = rowIndex;
	
	setEditorCombobox(cur_cbx_id,rowIndex);
	
}

//��ñ��ǰ������
function getChangeOldInfo(){
	//alert('getChangeOldInfo..');
	$.ajax({
		url:ctx+"/houseownership/correction!getChangeOldInfo.action",
		//url:ctx+"/houseownership/shiftreg!getRegInfo.action,
		data:{"proc_id":proc_id,"time":new Date()},
		dataType:"json",
		type:"post",
		async:false,
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			old_data = data;
		}
	});
}




/**
 * saveAppInfo
 */
function saveAppInfo(){
	 $('#table_transferor').datagrid('endEdit', appLastEditIndex);
	 changeLastEditIndex = -1;
	var inserted =  $('#table_transferor').datagrid('getChanges', 'inserted');
	var deleted =  $('#table_transferor').datagrid('getChanges', 'deleted');
	var updated =  $('#table_transferor').datagrid('getChanges', 'updated');
	//�����̨��������
	$.ajax({
		//url :  ctx+'/common/dict!applyEdit.action',
		dataType : 'json',
		type : 'post',
		data : {
			//������ƴװ��JSON�ַ������ݵ���̨
			datas :$.toJSON({
				inserted : inserted,
				deleted : deleted,
				updated : updated
			})
		},
		success : function(data) {
			if (data.success) {
				top.$.messager.alert('��ʾ', '���ݱ���ɹ���', 'info', function(){
					//�������Ϊ���ɱ༩
					itemCanEdit = false;
					//����ʱ���á��༭��ť��
					$('#dictitem_edit').linkbutton('enable');
					//����ʱ���¼��ر������
					itemDataGrid.datagrid('reload');
				});
			}else{
				top.$.messager.alert('��ʾ', '���ݱ���ʧ�ܣ�', 'info', function(){});
			}
		}
	});
}

/**
 * ������ĺ������
 * ��div_change_detail���������ɾ���ĵ�����Ӧ�õ���̨
 */
function saveChangeInfo() {
	
	 $('#div_change_detail').datagrid('endEdit', changeLastEditIndex);
	 changeLastEditIndex = -1;
	 
	 //�༭��ʱ�������  ��ȡ��������  ���
	 var rows = $('#div_change_detail').datagrid('getRows');
     for ( var i = 0; i < rows.length; i++) {
     	 $('#itemDataGrid').datagrid('endEdit', i);
     }
	 
	var inserted =  $('#div_change_detail').datagrid('getChanges', 'inserted');
	var deleted =  $('#div_change_detail').datagrid('getChanges', 'deleted');
	var updated =  $('#div_change_detail').datagrid('getChanges', 'updated');
	//��֤ �������������Ƿ�ǿ���д  ֻ��֤�����͸��µ�
	if(inserted){
		for(var i=0;i<inserted.length;i++){
			if(inserted[i].NEW_DATA.length==0){
				top.$.messager.alert('��ʾ', '���������ݲ���Ϊ�գ�', 'info',
						function() {
							
						});	
				return false;
			}
		}
	}
	if(updated){
		for(var i=0;i<updated.length;i++){
			if(updated[i].NEW_DATA.length==0){
				top.$.messager.alert('��ʾ', '���������ݲ���Ϊ�գ�', 'info',
						function() {
							
						});	
				return false;
			}
		}
	}
	//�����̨��������
	$.ajax({
		url :  ctx+"/houseownership/correction!applyEditChangeRecord.action",
		dataType : 'json',
		type : 'post',
		data : {
			//������ƴװ��JSON�ַ������ݵ���̨
			datas :$.toJSON({
				inserted : inserted,
				deleted : deleted,
				updated : updated
			}),proc_id:proc_id
		},
		success : function(data) {
			if (data.success) {
				top.$.messager.alert('��ʾ', '���ݱ���ɹ���', 'info', function(){
					//�������Ϊ���ɱ༩
					itemCanEdit = false;
					//����ʱ���á��༭��ť��
					$('#dictitem_edit').linkbutton('enable');
					//����ʱ���¼��ر������
					 $('#div_change_detail').datagrid('reload');
				});
			}else{
				top.$.messager.alert('��ʾ', '���ݱ���ʧ�ܣ�', 'info', function(){});
			}
		}
	});
};


/**********************************************************************************
*��������: ��ȡ���checkbox�������
*����˵��: ������� checkbox  �������     ������Ϊ�ֵ���    ---Ȼ��ɸѡ�����ڷ��ݵı���Ǽǣ�
*����˵��: 
*�� �� ֵ: ֱ���ڽ�������Ӹ��ȿ� 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function getchange_data_list(){
	
	$.ajax({
		url :  ctx+'/common/dict!getDictByCode.action',
		dataType : 'json',
		type : 'post',
		data : {code:change_dict_class_code},
		async:false,
		success : function(data) {
			change_data_list =data;
			//alert(JSON.stringify(data));
			var count = 0;					//������¼���ٸ�������  �Ա��������
			//��̨ȡ�ֵ���ɹ���  ѭ��append��table��     ����
			var dataColumn = 6;				//�ֵ�����
			var str = '';					//table append �ַ���
			var code;						//����������ʱ��������code
			for(var i=0;i<data.length;i++){
				code = data[i].value;
					
				//ɸѡ���ڷ��ز�����Ǽǵ�  ������  ��ʾ
				if(code == change_data_item.PRODUCT_NAME || code == change_data_item.HOUSE_LOCATION || code == change_data_item.BUILD_AREA || code == change_data_item.FLATLET_USAGE || code == change_data_item.TAONEI_AREA || code == change_data_item.HOUSE_ATTR || code == change_data_item.REG_VALUE){
					if(old_data.reg_unit_type == reg_unit_type.PARCEL){
						continue;
					}
					if(count%dataColumn==0){
						str+='<tr><td><input id="'+data[i].value+'"  type="checkbox" onclick="cbxClick(this)"/>'+
						'<label for="'+data[i].value+'" id="lbl_'+data[i].value+'">'+data[i].name+'</label></td>';
					}else if(count%dataColumn==5){
						str+='<td><input id="'+data[i].value+'"  type="checkbox" onclick="cbxClick(this)"/>'+
						'<label for="'+data[i].value+'" id="lbl_'+data[i].value+'">'+data[i].name+'</label></td></tr>';
					}else{
						str+='<td><input id="'+data[i].value+'"  type="checkbox" onclick="cbxClick(this)"/>'+
						'<label for="'+data[i].value+'" id="lbl_'+data[i].value+'">'+data[i].name+'</label></td>';
					}
					
					//����Ҫ��ʾ����Ϣ
				}else if(code == change_data_item.HOL_NAME || code == change_data_item.HOL_CER_NO || code == change_data_item.LOCATION_AREA ){
					continue;
				}else{			//�ڵظ�����Ϣ
					if(old_data.reg_unit_type == reg_unit_type.HOUSE){
						continue;
					}
					if(count%dataColumn==0){
						str+='<tr><td><input id="'+data[i].value+'"  type="checkbox" onclick="cbxClick(this)"/>'+
						'<label for="'+data[i].value+'" id="lbl_'+data[i].value+'">'+data[i].name+'</label></td>';
						
					}else if(count%dataColumn==5){
						str+='<td><input id="'+data[i].value+'"  type="checkbox" onclick="cbxClick(this)"/>'+
						'<label for="'+data[i].value+'" id="lbl_'+data[i].value+'">'+data[i].name+'</label></td></tr>';
					}else{
						str+='<td><input id="'+data[i].value+'"  type="checkbox" onclick="cbxClick(this)"/>'+
						'<label for="'+data[i].value+'" id="lbl_'+data[i].value+'">'+data[i].name+'</label></td>';
					}
				}
				count++;
			}
			$('#table_cbx').append(str);						//����������ӵ�table��
		}
	});
}


/**********************************************************************************
*��������: �ж�Ȩ����(���ơ����֤��)�Ƿ��Ѿ��ı� ����ı����ڱ��������¼���в���һ������  
*����˵��: �ж�Ȩ����(���ơ����֤��)�Ƿ��Ѿ��ı� ����ı����ڱ��������¼���в���һ������  
*����˵��: hisHolderData(��ʷȨ����) changedHolderData(�ı���Ȩ����)
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function checkHolderIsChanged(hisHolderData,changedHolderData){
	for(var i =0;i<hisHolderData.length;i++){
		for(var j = 0;j<changedHolderData.length;j++){
			if(hisHolderData[i].applicant_id == changedHolderData[j].applicant_id){
				//Ȩ�������Ƹı�  �����
				if(hisHolderData[i].app_name != changedHolderData[j].app_name){
					saveChangeRecord(change_data_item.HOL_NAME,hisHolderData[i].app_name,changedHolderData[j].app_name);
				}
				if(hisHolderData[i].app_cer_no != changedHolderData[j].app_cer_no){
					saveChangeRecord(change_data_item.HOL_CER_NO,hisHolderData[i].app_cer_no,changedHolderData[j].app_cer_no);
				}
			}
		}
	}
}

/**********************************************************************************
*��������: ����������������¼��
*����˵��: ����������������¼��  ����ɹ���ˢ��һ�ε�ǰdatagrid
*����˵��: code(�ֵ���code) old_data(����ǰ����) new_data(����������)
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function saveChangeRecord(code,old_data,new_data){
	$.ajax({
		url:ctx+"/houseownership/correction!saveChangeRecord.action",
		//url:ctx+"/houseownership/shiftreg!getRegInfo.action,
		data:{"proc_id":proc_id,"code":code,"old_data":old_data,"new_data":new_data,"time":new Date()},
		dataType:"json",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			if(data.result=="success"){
				$('#div_change_detail').datagrid('reload');
			}
		}
	});
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
function validate(v_flag){
	//���ؽ������
	var result ={
			result:true,
			message:'',
			page_name:'�����'
	}
	//��ʾ��Ϣ 
	var message;
	if(proc_node==state1.string0){
		var location_reg_unit = $("#LOCATION_REG_UNIT").combodict("getValue");
		if($.trim(location_reg_unit).length==0){
			message = '��ѡ�񷿵ز���������';
			result.message=message;
			 result.result=false;
			 return result;

		}
	}
	if(proc_node==state1.string1){
		//�ǿռ���
		message = "���ز�֤���ǣ�";
		var vEXCURSUS = new tt.Field(message,"EXCURSUS")  
	    tt.vf.req.add(vEXCURSUS);
		
		var valResult = tt.validate();
		if(!valResult.result){
			 result.message=valResult.message;
			 result.result=false;
			 return result;
		} 
		//�ύ�Ž����ж�  ����ʱ��ʼ���ȶԱ���
		if(!v_flag){
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
		}else{
			_init_form_data = $("#main_form").serializeJson(); 
		}
		 var tmpData = $('#div_change_detail').datagrid('getRows');
		 if(tmpData.length<0){
			 message = '����������Ϊ�գ����ȱ�����ύ��';
				 result.message=message;
				 result.result=false; 
			 return result; 
		 }
		
		//��������������У��
		 var tmpData = $('#div_change_detail').datagrid('getChanges');
		 if(tmpData.length>0){
			 var flag= 0 ;//����ȷ�� �Ƿ��û��Ѿ������������  δ���  ���������������     ����false
			 message = '����������޸ģ����ȱ�����ύ��';
			 if(flag){
				 
			 }else{
				 result.message=message;
				 result.result=false; 
			 }
			 return result;
		 }
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
	
	if(proc_node == state1.string0 || proc_node == state1.string1){
	
		_cur_form_data = $("#main_form").serializeJson(); 
		
		var r = equal(_init_form_data,_cur_form_data);
		//�������ȷ���  ҳ�����ݼ��޸�  ����true
		if(!r){
		  return true;
		}
		
		//��������������У��
		 var tmpData = $('#div_change_detail').datagrid('getChanges');
		 if(tmpData.length>0){
			return true;
		 }	
	}
	return false;
}

/**********************************************************************************
*��������: ����ǰһ������Ȩ��Ч�Ǽǲ���Ϣ���������
*����˵��:����ǰһ������Ȩ��Ч�Ǽǲ���Ϣ���������
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function saveBKOwnerShipToBusOwnerShip(){
	$.ajax({
		url:ctx+"/houseownership/correction!saveBKOwnerShipToBusOwnerShip.action",
		dataType : 'json',
		type : 'post',
		data : {"proc_id":proc_id},
		async:false,
		success : function(data) {
			
		}
	});
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
*��������:��ȡ���������ֵ�������
*����˵��:��ȡ�����������ֵ���ֵ  ���޸�ʱ ������ѡ��
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�house_attr_dict_data��ֵ ���޷���ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function gethouse_attr_dict_data(){
	if(!house_attr_dict_data){
		$.ajax({
			url:ctx+'/common/dict!getNewDictByCode.action?code=021',  
			dataType : 'json',
			type : 'post',
			data : {"proc_id":proc_id},
			async:false,
			success : function(data) {
				house_attr_dict_data = data;
			}
		});
	}
}
/**********************************************************************************
*��������:getflatlet_usage_dict_data()
*����˵��:��ȡ��������;�ֵ���ֵ  ���޸�ʱ ������ѡ��
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�flatlet_usage_dict_data��ֵ ���޷���ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function getflatlet_usage_dict_data(){
	if(!flatlet_usage_dict_data){
		$.ajax({
			url:ctx+'/common/dict!getNewDictByCode.action?code=062',  
			dataType : 'json',
			type : 'post',
			data : {"proc_id":proc_id},
			async:false,
			success : function(data) {
				flatlet_usage_dict_data = data;
			}
		});
	}
}
/**********************************************************************************
*��������:getreal_usage_dict_data()
*����˵��:��ȡ��������;�ֵ���ֵ  ���޸�ʱ ������ѡ��
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�real_usage_dict_data��ֵ ���޷���ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function getreal_usage_dict_data(){
	if(!flatlet_usage_dict_data){
		$.ajax({
			url:ctx+'/common/dict!getNewDictByCode.action?code=015',  
			dataType : 'json',
			type : 'post',
			data : {"proc_id":proc_id},
			async:false,
			success : function(data) {
				real_usage_dict_data = data;
			}
		});
	}
}
/**********************************************************************************
*��������:getuse_per_dict_data()
*����˵��:��ȡʹ�������ֵ���ֵ  ���޸�ʱ ������ѡ��
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�use_per_dict_data��ֵ ���޷���ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function getuse_per_dict_data(){
	if(!flatlet_usage_dict_data){
		$.ajax({
			url:ctx+'/common/dict!getNewDictByCode.action?code=006',  
			dataType : 'json',
			type : 'post',
			data : {"proc_id":proc_id},
			async:false,
			success : function(data) {
				use_per_dict_data = data;
			}
		});
	}
}
/**********************************************************************************
*��������:old_new_data_format()
*����˵��:���������ֵ����name  
*����˵��: value  ��ǰ�е�ֵ 
*�� �� ֵ: �ֵ����name  ���ߵ�ǰ��value
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function old_new_data_format(value) {
		
		if(house_attr_dict_data){					//���������ֵ���
			var tmpData = house_attr_dict_data;
			for(var i =0;i<tmpData.length;i++){
				if(tmpData[i].value == value){
					return  tmpData[i].name;
				}
			}
		}if(flatlet_usage_dict_data){				//������;�ֵ���
			var tmpData = flatlet_usage_dict_data;
			for(var i =0;i<tmpData.length;i++){
				if(tmpData[i].value == value){
					return  tmpData[i].name;
				}
			}
		}if(use_per_dict_data){						//ʹ�������ֵ���
			var tmpData = use_per_dict_data;
			for(var i =0;i<tmpData.length;i++){
				if(tmpData[i].value == value){
					return  tmpData[i].name;
				}
			}
		}if(real_usage_dict_data){					//������;�ֵ���
			var tmpData = real_usage_dict_data;
			for(var i =0;i<tmpData.length;i++){
				if(tmpData[i].value == value){
					return  tmpData[i].name;
				}
			}
		}
	return value;
}


/**********************************************************************************
*��������: ����   cbx_id--�ֵ�����ȡ��   ��ȡ��ָ����Editor  ����������Ϊcombobox   
*����˵��: ������Ҫ�ֵ���ѡ��ı����  Ϊ�ֵ���
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function setEditorCombobox(cbx_id,row_index){
	//gethouse_attr_dict_data();
	 //�ж�  ��Ҫ���ֵ����ѡ�� ���ȡeditor  ��editor��Ϊ�ֵ���(combodict)
    if(cur_cbx_id == change_data_item.HOUSE_ATTR){				//��������
	   		var house_attr_editor = $('#div_change_detail').datagrid('getEditor', {index:row_index,field:'NEW_DATA'});
	   		var tempCombo =  $(house_attr_editor.target).combodict({  
			    code:'021',
			    width:'100%',
			    onSelect: function(rec){  
			    	house_attr_editor.target.val(rec.value);
		        }
			}); 
	}else if(cur_cbx_id == change_data_item.FLATLET_USAGE){ 	//������;
		var flatlet_usage_editor = $('#div_change_detail').datagrid('getEditor', {index:row_index,field:'NEW_DATA'});
   		var tempCombo =  $(flatlet_usage_editor.target).combodict({  
		    code:'062',
		    width:'100%',
		    onSelect: function(rec){  
		    	flatlet_usage_editor.target.val(rec.value);
	        }
   		});
	}else if(cur_cbx_id == change_data_item.REAL_USAGE){		//������;
		var real_usage_editor = $('#div_change_detail').datagrid('getEditor', {index:row_index,field:'NEW_DATA'});
   		var tempCombo =  $(real_usage_editor.target).combodict({  
		    code:'015',
		    width:'100%',
		    onSelect: function(rec){  
		    	real_usage_editor.target.val(rec.value);
	        }
   		});
	}else if(cur_cbx_id == change_data_item.USE_PER){			//ʹ������
		var use_per_editor = $('#div_change_detail').datagrid('getEditor', {index:row_index,field:'NEW_DATA'});
   		var tempCombo =  $(use_per_editor.target).combodict({  
		    code:'006',
		    width:'100%',
		    onSelect: function(rec){  
		    	use_per_editor.target.val(rec.value);
	        }
   		});
	}
    
    
}

/**********************************************************************************
*��������:getChangeRecordInfo
*����˵��:��ȡ���ݿ��е�ǰҵ������������¼
*����˵��: 
*�� �� ֵ: ���ݿ��и�����������
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function getChangeRecordInfo(){
	var result;
	$.ajax({
		url:ctx+"/houseownership/correction!getChangeRecord.action?time="+new Date(),
		dataType : 'json',
		type : 'post',
		data : {"proc_id":proc_id},
		async:false,
		success : function(data) {
			result = data;
		}
	});
	
	return result;
}


