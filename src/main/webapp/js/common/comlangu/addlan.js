function init(bus_type_id) {
   
	$("#bustype").val(bus_type_id);
	var form = $('#add_app_form').tableform({
		errorCode: 700,
		//��Ԫ�ر���������ͨ����Ϊ���У�
		colnum: 2,
		//���ؼ������п�
		titleWidth: 120,
		//���ؼ�������п�
		cellWidth: 150,
		//���ؼ�input��select��combo��������
		inputWidth:	150,
		//textarea�ؼ�������
		textareaWidth: 500,
		//textarea�ؼ�������
		textareaHeight: 90,
		//�Ƿ�����fieldset��ǩ
		fieldset:false	,
		//��Ԫ�ز������顣������ÿ�����󹹳�һ����Ԫ��
		inputs: [{
			tag: 'input',
			title: '����',
			name: 'co.language_name',
			id: 'mc',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
		},{
			tag: 'input',
			title: 'ģ������',
			name: 'co.temp_type',
			id: 'mblx',
			type: 'combodict',
			options: {
				code:'053'
			}
		},
		
		
		{
			tag: 'textarea',
			title: '����',
			name: 'co.language_content',
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
		url: ctx+"/common/configur!saveComLanguage.action?time="+new Date(),
		dataType: 'json',
		success: function(data){
			if (data.success) {
				top.$.messager.alert('������������ʾ',data.tipMessage,'info',function(){
					args.userDataGrid.datagrid('reload');
					closeInTopWindow('add_user_win');
				});				
			} else {
				top.$.messager.alert('�������������',data.errorMessage,'error');
			}
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

//����
function submit(){
	
	
	$('#add_app_form').submit();
}


