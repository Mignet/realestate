/*********************************************************************************
*��  ��  ��  ��: project-add.js
*��  ��  ��  ��: ������Ŀ���
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Sam
*��  ��  ��  �ڣ� 2014-08-07
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
/**********************************************************************************
*��������: init()
*����˵��: ҳ���ʼ������
*��������: Sam
*��������: 2014-08-07
*�޸���ʷ: 
***********************************************************************************/
function init() {
	var form = $('#add_project_form').tableform({
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
			title: '������Ŀ��¼��ˮ��',
			name: 'prj_id',
			id: 'prj_id',
			}
			,{
			tag: 'input',
			title: '��Ŀ���� ',
			name: 'project_no',
			id: 'project_no',
			}
			,{
			tag: 'input',
			title: '��Ŀ���� ',
			name: 'project_name',
			id: 'project_name',
			}
			,{
			tag: 'input',
			title: '���������֤�� ',
			name: 'conscertificatenu',
			id: 'conscertificatenu',
			}
			,{
			tag: 'input',
			title: '����ʹ�ú�ͬ ',
			name: 'contract_no',
			id: 'contract_no',
			}
			,{
			tag: 'input',
			title: '��Ŀ����',
			name: 'project_dis',
			id: 'project_dis',
			}
			,{
			tag: 'input',
			title: '���������� ',
			name: 'building_num',
			id: 'building_num',
			}
			,{
			tag: 'input',
			title: '��ַ',
			name: 'location',
			id: 'location',
			}
			,{
			tag: 'input',
			title: '�õ�������滮��',
			name: 'glebe_area',
			id: 'glebe_area',
			}
			,{
			tag: 'input',
			title: '�ܽ���������滮�� ',
			name: 'floor_area',
			id: 'floor_area',
			}
			,{
			tag: 'input',
			title: '�����︲���ʣ��滮�� ',
			name: 'build_bestrow_per',
			id: 'build_bestrow_per',
			}
			,{
			tag: 'input',
			title: '���赥λ ',
			name: 'build_unit',
			id: 'build_unit',
			}],
		url: 'project!saveProject.action',
		dataType: 'json',
		success: function(data){
			if (data.success) {
				top.$.messager.alert('����������Ŀ��ʾ',data.tipMessage,'info',function(){
					args.projectDataGrid.datagrid('reload');
					closeInTopWindow('add_project_win');
				});				
			} else {
				top.$.messager.alert('����������Ŀ����',data.errorMessage,'error');
			}
		}
	});
};

/*$(function(){
	$.extend($.fn.validatebox.defaults.rules , {
		confirmPassword: {
	        validator: function(value,param){
	            return value == $(param[0]).val();
	        },
	        message: '�����������벻ƥ�䡣'
	    }
	});
});*/
