
function init(user,bus_type_id){   
	$("#cyyid1").val(user.LANGUAGE_ID);
	$("#mc1").val(user.LANGUAGE_NAME);
	$("#nr1").val(user.LANGUAGE_CONTENT);
	$("#bus_type_id").val(bus_type_id);
	$("#mblx1").val(user.TEMP_TYPE);
	
	
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
		inputs: [
		{
			tag: 'input',
			title: '����',
			name: 'LANGUAGE_NAME',
			id: 'mc',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
		},{
			tag: 'input',
			title: 'ģ������',
			name: 'TEMP_TYPE',
			id: 'mblx',
			type: 'combodict',
			options: {
				code:'053'
			}
		},
		
		
		{
			tag: 'textarea',
			title: '����',
			name: 'LANGUAGE_CONTENT',
			id: 'nr',
			type: 'validatebox',
			options: {
				//code: 'cllx',
				//url:'combo_tree_data.json'
				//required: true,
				//missingMessage: '������'
			}
		}
		],
		url:ctx+"/common/configur!updateComLanguage.action?time="+new Date(),
		dataType : 'json',
		success: function(data){
			if (data.success) {
				top.$.messager.alert('���³�������ʾ',data.tipMessage,'info',function(){
				  
					args.userDataGrid.datagrid('reload');
					 closeInTopWindow('edit_user_win');
				});				
			} else {
				top.$.messager.alert('���³��������',data.errorMessage,'error');
			}
		}
		
		
		
	});
	form.form("load",user);
	
	//�ӷ���˻�ȡ�û���Ϣ
	/**
	$.ajax({
		type: "POST",
		url:  '../appDelegate/getAppByID.run?djbh='+args.user.djbh,
		dataType:"json",
		success: function(user){
			//�����������
			user.djbh=args.user.djbh;
			user.password = null;
			//�����
			form.form("load",user);
		}
	});

	*/
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
//����
function submit(){
	
	
	$("#mc1").val($("#mc").val());
	$("#nr1").val($("#nr").val());
	$("#edit_user_form").submit();
	
	
	
}
