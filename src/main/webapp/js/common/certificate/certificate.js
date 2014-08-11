function init(user){
	var form = $('#edit_user_form').tableform({
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
			title: '������',
			name: 'APP_NAME',
			id: 'sqr',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
		},{
			tag: 'input',
			title: '����������',
			name: 'APP_TYPE',
			id: 'sqrlx',
			type: 'combodict',
			options: {
				code: '043',
			}
		},
		{
			tag: 'input',
			title: '֤������',
			name: 'APP_CER_TYPE',
			id: 'zjlx',
			type: 'combodict',
			options: {
				code: '002',
				
			}
		},{
			tag: 'input',
			title: '����������',
			name: 'LEGAL_NAME',
			id: 'fddbr'
		     
		},{
			tag: 'input',
			title: '֤������',
			name: 'APP_CER_NO',
			id: 'zjbh',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
		},{
			tag: 'input',
			title: '�ݶ�',
			name: 'APP_PORT',
			id: 'fe',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
			},{
			tag: 'input',
			title: '��ַ',
			name: 'APP_ADDRESS',
			id: 'dz',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
			},{
			tag: 'input',
			title: '��ϵ�绰',
			name: 'APP_TEL',
			id: 'lxdh'
		} ,{
			tag: 'input',
			title: '������',
			name: 'AGENT_NAME',
			id: 'dlr'
		    
		},
		{
			tag: 'input',
			title: '������֤������',
			name: 'AGENT_CER_TYPE',
			id: 'dlr',
			type: 'combodict',
			options: {
				code: '002',
				
			}
		    
		},
		
		{
			tag: 'input',
			title: '������֤������',
			name: 'AGENT_CER',
			id: 'dlrsfzh'
		    
		},{
			tag: 'input',
			title: '��������ϵ�绰',
			name: 'AGENT_TEL',
			id: 'dlrdh'
		    
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
	form.form("load",user);
	
	//�ӷ���˻�ȡ�û���Ϣ
};





