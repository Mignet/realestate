/*********************************************************************************
*��  ��  ��  ��: pre-buyer.js
*��  ��  ��  ��: Ԥ��������Ϣ.js
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Joyon
*��  ��  ��  �ڣ� 2014-03-01
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
//��ʼ������.	
var proc;//=this.parent.proc;
//var serialNumber ={num1:"�ǼǱ��",num2:"�鵵��",num3:"���ز�֤��"};
//
var proc_node ="";// proc.activName;
var proc_id ="";// proc.procId;
var userDataGrid;
var form;

//window.onload = function(){
//	init();
//};
function init(cur_proc){
	proc = cur_proc;
	proc_id = proc.procId;
	initForm();
	initDatagrid();
};
/**********************************************************************************
*��������: initForm
*����˵��: ��ʼ�����
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014��5��12�� 19:21:25
*�޸���ʷ: 
***********************************************************************************/
function initForm(){
	 form = $('#edit_user_form').tableform({
		errorCode: 700,
		//��Ԫ�ر���������ͨ����Ϊ���У�
		colnum: 2,
		//���ؼ������п�
		titleWidth: 120,
		//���ؼ�������п�
		cellWidth: 250,
		//���ؼ�input��select��combo��������
		inputWidth:	200,
		//textarea�ؼ�������
		textareaWidth: 500,
		//textarea�ؼ�������
		textareaHeight: 90,
		//�Ƿ�����fieldset��ǩ
		fieldset:false	,
		//��Ԫ�ز������顣������ÿ�����󹹳�һ����Ԫ��
		inputs: [{
			tag: 'input',
			title: 'Ԥ����',
			name: 'app_name',
			id: 'sqr',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
		},{
			tag: 'input',
			title: 'Ԥ��������',
			name: 'app_type',
			id: 'sqrlx',
			type: 'combodict',
			options: {
				code: '043',
			}
		},
		{
			tag: 'input',
			title: '֤������',
			name: 'app_cer_type',
			id: 'zjlx',
			type: 'combodict',
			options: {
				code: '002',
				
			}
		},{
			tag: 'input',
			title: '����������',
			name: 'legal_name',
			id: 'fddbr'
		     
		},{
			tag: 'input',
			title: '֤������',
			name: 'app_cer_no',
			id: 'zjbh',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
		},{
			tag: 'input',
			title: '�ݶ�',
			name: 'app_port',
			id: 'fe',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
			},{
			tag: 'input',
			title: '��ַ',
			name: 'app_address',
			id: 'dz',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
			},{
			tag: 'input',
			title: '��ϵ�绰',
			name: 'app_tel',
			id: 'lxdh'
		} ,{
			tag: 'input',
			title: '������',
			name: 'agent_name',
			id: 'dlr'
		    
		},
		{
			tag: 'input',
			title: '������֤������',
			name: 'agent_cer_type',
			id: 'dlr',
			type: 'combodict',
			options: {
				code: '002',
				
			}
		    
		},
		
		{
			tag: 'input',
			title: '������֤������',
			name: 'agent_cer',
			id: 'dlrsfzh'
		    
		},{
			tag: 'input',
			title: '��������ϵ�绰',
			name: 'agent_tel',
			id: 'dlrdh'
		    
		}
		,{
			tag: 'input',
			title: 'Ԥ���˹�ϵ',
			name: 'app.hol_rel',
			id: 'gx',
			type: 'combodict',
			options: {
				code: '063',
				
			}
		    
		}],
		url: ctx+'/houseownership/ownershipinitialreg/initialreg!updateApplicant.action?time='+new Date(),
		dataType : 'json',
		success: function(data){
			if (data.success) {
				top.$.messager.alert('�����û���ʾ',data.tipMessage,'info',function(){
					args.userDataGrid.datagrid('reload');
					closeInTopWindow('edit_user_win');
					
				});				
			} else {
				top.$.messager.alert('�����û�����',data.errorMessage,'error');
			}
		}
		
	});
}
/**********************************************************************************
*��������: initDatagrid
*����˵��: ��ʼ��datagrid
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014��5��12�� 19:21:21
*�޸���ʷ: 
***********************************************************************************/
function initDatagrid(){
	//����Ԥ������Ϣ��
	 userDataGrid = $('#datagrid_user').datagrid({
		//fit : true,
		title:'Ԥ�����б�',
		height:200,
		// ���������Դ
		url :ctx+"/backup/presale!getPreBuyerInfo.action?time="+new Date()+"&proc_id="+proc_id,
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
			title : 'Ԥ����',
			field : 'app_name',
			width:80
			
		},{
			title : '����������',
			field : 'legal_name',
			width:100
		
		}, 
		{
			title : '֤������',
			field : 'app_cer_type',
			width:100,formatter : dicts.format.app_cer_type_format
			
		}, {
			title : '֤�����',
			field : 'app_cer_no',
			width:100
			
		},    {
			title : '������',
			field : 'agent_name',
			width:50
			
		}, 
		{
			title : '������֤������',
			field : 'agent_cer_type',
			width:100,formatter : dicts.format.app_cer_type_format
			
		},	{
			title : '������֤������',
			field : 'agent_cer',
			width:100
			
		}, {
			title : '��ϵ�绰',
			field : 'agent_tel',
			width:100
			
		}

		] ],
		// ��ͷ����ӹ�������
		toolbar : [ {
			id : 'user_edit',
			text : '�༭',
			iconCls : 'icon-pencil',
			disabled : true,
			handler : doEdit
		}],
		onClickRow : function() {
			//�����ʱ����༭������ɾ������ť
			var user = userDataGrid.datagrid("getSelected");
			form.form("load",user);	
			$('#user_edit').linkbutton('enable');
		},
		onLoadSuccess : function(data) {
			data = data.rows;
			if(data){
				userDataGrid.datagrid("selectRow",0);
				form.form("load",data[0]);	
			}
			//������Ͻ��á��༭������ɾ������ť
			//$('#user_edit').linkbutton('disable');
			//$('#user_delete').linkbutton('disable');
			
			
		}

	});
}

//�༭
function doEdit() {
	var row = userDataGrid.datagrid('getSelected');

	openInTopWindow({
		// ����Ԫ�ص�id
		id : 'edit_user_win',
		// ����iframe��src
		src : ctx+'/common/applicant/editapplicant.jsp',
		// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
		destroy : true,
		// ���ڱ���
		title : '�༭Ԥ�۷�',
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
$(function(){
	$.extend($.fn.validatebox.defaults.rules , {
		confirmPassword: {
	        validator: function(value,param){
	            return value == $(param[0]).val();
	        },
	        message: '�����������벻ƥ�䡣'
	    }
	});
});



