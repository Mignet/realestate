function init(user){
	var form = $('#edit_user_form').tableform({
		errorCode: 700,
		//表单元素表格的列数（通常设为两列）
		colnum: 2,
		//表单控件标题列宽
		titleWidth: 120,
		//表单控件输入框列宽
		cellWidth: 250,
		//表单控件input、select、combo等输入框宽
		inputWidth:	200,
		//textarea控件输入框宽
		textareaWidth: 500,
		//textarea控件输入框高
		textareaHeight: 90,
		//是否设置fieldset标签
		fieldset:false	,
		//表单元素参数数组。数组中每个对象构成一个表单元素
		inputs: [{
			tag: 'input',
			title: '申请人',
			name: 'APP_NAME',
			id: 'sqr',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
		},{
			tag: 'input',
			title: '申请人类型',
			name: 'APP_TYPE',
			id: 'sqrlx',
			type: 'combodict',
			options: {
				code: '043',
			}
		},
		{
			tag: 'input',
			title: '证件类型',
			name: 'APP_CER_TYPE',
			id: 'zjlx',
			type: 'combodict',
			options: {
				code: '002',
				
			}
		},{
			tag: 'input',
			title: '法定代表人',
			name: 'LEGAL_NAME',
			id: 'fddbr'
		     
		},{
			tag: 'input',
			title: '证件号码',
			name: 'APP_CER_NO',
			id: 'zjbh',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
		},{
			tag: 'input',
			title: '份额',
			name: 'APP_PORT',
			id: 'fe',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
			},{
			tag: 'input',
			title: '地址',
			name: 'APP_ADDRESS',
			id: 'dz',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
			},{
			tag: 'input',
			title: '联系电话',
			name: 'APP_TEL',
			id: 'lxdh'
		} ,{
			tag: 'input',
			title: '代理人',
			name: 'AGENT_NAME',
			id: 'dlr'
		    
		},
		{
			tag: 'input',
			title: '代理人证件类型',
			name: 'AGENT_CER_TYPE',
			id: 'dlr',
			type: 'combodict',
			options: {
				code: '002',
				
			}
		    
		},
		
		{
			tag: 'input',
			title: '代理人证件号码',
			name: 'AGENT_CER',
			id: 'dlrsfzh'
		    
		},{
			tag: 'input',
			title: '代理人联系电话',
			name: 'AGENT_TEL',
			id: 'dlrdh'
		    
		}],
		url: ctx+'/houseownership/ownershipinitialreg/initialreg!updateApplicant.action?time='+new Date(),
		dataType : 'json',
		success: function(data){
			if (data.success) {
				top.$.messager.alert('更新用户提示',data.tipMessage,'info',function(){
					args.userDataGrid.datagrid('reload');
					closeInTopWindow('edit_user_win');
					
				});				
			} else {
				top.$.messager.alert('更新用户错误',data.errorMessage,'error');
			}
		}
		
		
		
	});
	form.form("load",user);
	
	//从服务端获取用户信息
};





