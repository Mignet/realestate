function __init(){

	var form = $('#edit_building_form').tableform({
		errorCode: 700,
		//��Ԫ�ر���������ͨ����Ϊ���У�
		colnum: 2,
		//���ؼ������п�
		titleWidth: 140,
		//���ؼ�������п�
		cellWidth: 250,
		//���ؼ�input��select��combo��������
		inputWidth:	180,
		//textarea�ؼ�������
		textareaWidth: 500,
		//textarea�ؼ�������
		textareaHeight: 90,
		//�Ƿ�����fieldset��ǩ
		fieldset:false	,
		//��Ԫ�ز������顣������ÿ�����󹹳�һ����Ԫ��
		inputs: [{
			tag: 'input',
			title: '���������[���]',
			name: 'bldg_code_sz',
			id: 'bldg_code_sz',
			type: 'validatebox'
			}
			,{
			tag: 'input',
			title: '���������[����] ',
			name: 'bldg_code_gb',
			id: 'bldg_code_gb',
			type: 'validatebox'
			}
			,{
			tag: 'input',
			title: '��������ʱ���� ',
			name: 'bldg_code_tmp',
			id: 'bldg_code_tmp',
			type: 'validatebox'
			}
			,{
			tag: 'input',
			title: '�ڵ�.�ؿ��� ',
			name: 'par_id',
			id: 'par_id',
			}
			,{
			tag: 'input',
			title: '��Ŀ���� ',
			name: 'project_name',
			id: 'project_name',
			}
			,{
			tag: 'input',
			title: '���� ',
			name: 'rise_no',
			id: 'rise_no',
			}
			,{
			tag: 'input',
			title: '¥���Ƽ����� ',
			name: 'bldg_name_no',
			id: 'bldg_name_no',
			}
			,{
			tag: 'input',
			title: '���������� ',
			name: 'division',
			id: 'division',
			}
			,{
			tag: 'input',
			title: '��ַ[·]',
			name: 'road',
			id: 'road',
			}
			,{
			tag: 'input',
			title: '���ڽֵ�[��] ',
			name: 'street',
			id: 'street',
			}
			,{
			tag: 'input',
			title: '��ַ[���ƺ�] ',
			name: 'house_num',
			id: 'house_num',
			}
			,{
			tag: 'input',
			title: '���� ',
			name: 'location',
			id: 'location',
			}
			,{
			tag: 'input',
			title: '�ڵغż�֧�� ',
			name: 'par_no_subno',
			id: 'par_no_subno',
			}
			,{
			tag: 'input',
			title: '�������� ',
			name: 'bldg_attr',
			id: 'bldg_attr',
			}
			,{
			tag: 'input',
			title: '�������� ',
			name: 'bldg_type',
			id: 'bldg_type',
			}
			,{
			tag: 'input',
			title: '������;[�滮]',
			name: 'bldg_usage',
			id: 'bldg_usage',
			}
			,{
			tag: 'input',
			title: '������;[��״] ',
			name: 'bldg_usage_now',
			id: 'bldg_usage_now',
			}
			,{
			tag: 'input',
			title: '���ݽṹ ',
			name: 'bldg_structure',
			id: 'bldg_structure',
			}
			,{
			tag: 'input',
			title: '���ݲ��� ',
			name: 'bldg_floors',
			id: 'bldg_floors',
			}
			,{
			tag: 'input',
			title: '�������� ',
			name: 'const_startdate',
			id: 'const_startdate',
			type: 'datebox'
			}
			,{
			tag: 'input',
			title: '�������� ',
			name: 'const_enddate',
			id: 'const_enddate',
			type: 'datebox'
			}
			,{
			tag: 'input',
			title: 'ʹ������ ',
			name: 'lu_term',
			id: 'lu_term',
			}
			,{
			tag: 'input',
			title: '��ʼ���� ',
			name: 'start_date',
			id: 'start_date',
			type: 'datebox'
			}
			,{
			tag: 'input',
			title: '��ֹ���� ',
			name: 'end_date',
			id: 'end_date',
			type: 'datebox'
			}
			,{
			tag: 'input',
			title: '������� ',
			name: 'base_area',
			id: 'base_area',
			}
			,{
			tag: 'input',
			title: '������� ',
			name: 'built_up_area',
			id: 'built_up_area',
			}
			,{
			tag: 'input',
			title: '������� ',
			name: 'ther_area',
			id: 'ther_area',
			}
			,{
			tag: 'input',
			title: '��̯�õ���� ',
			name: 'shared_lu_area',
			id: 'shared_lu_area',
			}
			,{
			tag: 'input',
			title: '�ܿղ��� ',
			name: 'b_jkc_count',
			id: 'b_jkc_count',
			}
			,{
			tag: 'input',
			title: 'ת������ ',
			name: 'b_zhc_count',
			id: 'b_zhc_count',
			}
			,{
			tag: 'input',
			title: '�豸���� ',
			name: 'b_sbc_count',
			id: 'b_sbc_count',
			}
			,{
			tag: 'input',
			title: '���Ѳ��� ',
			name: 'b_bnc_count',
			id: 'b_bnc_count',
			}
			,{
			tag: 'input',
			title: '����������� ',
			name: 'b_up_build_area',
			id: 'b_up_build_area',
			}
			,{
			tag: 'input',
			title: '���������� ',
			name: 'b_ather_build_area',
			id: 'b_ather_build_area',
			}
			,{
			tag: 'input',
			title: '���������',
			name: 'b_down_build_area',
			id: 'b_down_build_area',
			}
			,{
			tag: 'input',
			title: '������X���� ',
			name: 'b_build_pos_x',
			id: 'b_build_pos_x',
			}
			,{
			tag: 'input',
			title: '������Y���� ',
			name: 'b_build_pos_y',
			id: 'b_build_pos_y',
			}
			,{
			tag: 'input',
			title: '�����߶� ',
			name: 'b_build_height',
			id: 'b_build_height',
			}
			,{
			tag: 'input',
			title: 'ȹ¥���� ',
			name: 'b_skirt_count',
			id: 'b_skirt_count',
			}
			,{
			tag: 'input',
			title: '��¥���� ',
			name: 'b_tower_count',
			id: 'b_tower_count',
			}
			,{
			tag: 'input',
			title: '���ý�������ϼ� ',
			name: 'b_common_area',
			id: 'b_common_area',
			}
			,{
			tag: 'input',
			title: 'Ӧ��̯������� ',
			name: 'b_apportion_common_area',
			id: 'b_apportion_common_area',
			}
			,{
			tag: 'input',
			title: '��Ӧ��̯������� ',
			name: 'b_noapportion_common_area',
			id: 'b_noapportion_common_area',
			}
			,{
			tag: 'input',
			title: '�����ܲ��� ',
			name: 'b_up_build_floor',
			id: 'b_up_build_floor',
			}
			,{
			tag: 'input',
			title: '�����ܲ��� ',
			name: 'b_down_build_floor',
			id: 'b_down_build_floor',
			}
			,{
			tag: 'input',
			title: '������Ҳ��� ',
			name: 'ather_build_floor',
			id: 'ather_build_floor',
			}
			,{
			tag: 'input',
			title: '�������� ',
			name: 'unit_suits',
			id: 'unit_suits',
			}
			,{
			tag: 'input',
			title: '����ʱ�� ',
			name: 'rptdate',
			id: 'rptdate',
			type: 'datebox'
			}
			,{
			tag: 'input',
			title: '����֤�� ',
			name: 'rptid',
			id: 'rptid',
			}
			,{
			tag: 'input',
			title: '������id ',
			name: 'inputer_no',
			id: 'inputer_no',
			}
			,{
			tag: 'input',
			title: '���������� ',
			name: 'inputer',
			id: 'inputer',
			}
			,{
			tag: 'input',
			title: '�������� ',
			name: 'input_date',
			id: 'input_date',
			type: 'datebox'
			}
			,{
			tag: 'input',
			title: '����޸��˹�Ա�� ',
			name: 'modifier_no',
			id: 'modifier_no',
			}
			,{
			tag: 'input',
			title: '����޸��� ',
			name: 'modifier',
			id: 'modifier',
			}
			,{
			tag: 'input',
			title: '����޸����� ',
			name: 'modify_date',
			id: 'modify_date',
			type: 'datebox'
			}
			,{
			tag: 'input',
			title: '¥�̱�״̬ ',
			name: 'bldg_status',
			id: 'bldg_status',
			}
			,{
			tag: 'input',
			title: '��¼״̬ ',
			name: 'data_state',
			id: 'data_state',
			}
			,{
			tag: 'input',
			title: '������Դ ',
			name: 'data_from',
			id: 'data_from',
			}
			,{
			tag: 'input',
			title: 'Ӱ��ͼID ',
			name: 'yxt_id',
			id: 'yxt_id',
			}
			,{
			tag: 'input',
			title: '��潨����ID ',
			name: 'fcch_bldg_id',
			id: 'fcch_bldg_id',
			}],
		url: 'building!updateBuilding.action',
		dataType : 'json',
		success: function(data){
			if (data.success) {
				top.$.messager.alert('���½�����ʾ',data.tipMessage,'info',function(){
					args.buildingDataGrid.datagrid('reload');
				});				
			} else {
				top.$.messager.alert('���½�������',data.errorMessage,'error');
			}
		}
	});
	//�ӷ���˻�ȡ������Ϣ
	$.ajax({
		type: "POST",
		url:  'building!getBuildingByID.action?bldg_id='+args.building.bldg_id,
		dataType:"json",
		success: function(building){
			//�����������
			//building.dept_name=args.building.dept_name;
			//building.password = null;
			//�����
			form.form("load",building);
		}
	});

	$('#building_password').focusout(function() {
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