/*********************************************************************************
*��  ��  ��  ��: project-edit.js
*��  ��  ��  ��: ��������Ŀ�༭
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Sam
*��  ��  ��  �ڣ� 2014-08-07
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
/**********************************************************************************
*��������: __init()
*����˵��: ҳ���ʼ������
*��������: Sam
*��������: 2014-08-07
*�޸���ʷ: 
***********************************************************************************/
function __init(){

	var form = $('#edit_project_form').tableform({
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
					title: '�����︲����(�滮)',
					name: 'build_bestrow_per',
					id: 'build_bestrow_per',
					}
					,{
					tag: 'input',
					title: '���赥λ ',
					name: 'build_unit',
					id: 'build_unit',
					}],
		url: 'project!updateProject.action',
		dataType : 'json',
		success: function(data){
			if (data.success) {
				top.$.messager.alert('���½�����ʾ',data.tipMessage,'info',function(){
					args.projectDataGrid.datagrid('reload');
				});				
			} else {
				top.$.messager.alert('���½�������',data.errorMessage,'error');
			}
		}
	});
	//�ӷ���˻�ȡ������Ϣ
	$.ajax({
		type: "POST",
		url:  'project!getProjectByID.action?prj_id='+args.project.prj_id,
		dataType:"json",
		success: function(project){
			//�����������
			//building.dept_name=args.building.dept_name;
			//building.password = null;
			//�����
			form.form("load",project);
		}
	});

	/*$('#building_password').focusout(function() {
		if ($(this).val() != '') {
			//���벻Ϊ��ʱ - ����ȷ������
			$('#confirm_password').validatebox({
				required : true,
				validType : "confirmPassword['#building_password']"
			});
		} else {
			//����Ϊ��ʱ - ��У��ȷ������
			$('#confirm_password').validatebox({
				required : false,
				validType : null
			});
			$('#confirm_password').val('');
		}
	});*/
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