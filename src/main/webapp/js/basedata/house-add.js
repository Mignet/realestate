function init() {
	var form = $('#add_user_form').tableform({
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
			title: '���ݱ��',
			name: 'ho_id',
			id: 'ho_id',
			}
			,{
			tag: 'input',
			title: '���ݱ��루��꣩',
			name: 'house_code_sz',
			id: 'house_code_sz',
			}
			,{
			tag: 'input',
			title: '���ݱ��루���꣩',
			name: 'house_code_gb',
			id: 'house_code_gb',
			}
			,{
			tag: 'input',
			title: '���ݱ��루��ʱ��',
			name: 'house_code_tmp',
			id: 'house_code_tmp',
			}
			,{
			tag: 'input',
			title: '�ڵ�֧��',
			name: 'parcel_no_subno',
			id: 'parcel_no_subno',
			}
			,{
			tag: 'input',
			title: '����',
			name: 'unit_no',
			id: 'unit_no',
			}
			,{
			tag: 'input',
			title: '��Ȼ����',
			name: 'house_no_num',
			id: 'house_no_num',
			}
			,{
			tag: 'input',
			title: '����',
			name: 'seat_no',
			id: 'seat_no',
			}
			,{
			tag: 'input',
			title: '����',
			name: 'house_style',
			id: 'house_style',
			}
			,{
			tag: 'input',
			title: '����¥��',
			name: 'floor_no',
			id: 'floor_no',
			}
			,{
			tag: 'input',
			title: '��Ȼ�㣨ʵ�ʲ㣩',
			name: 'layer_no',
			id: 'layer_no',
			}
			,{
			tag: 'input',
			title: '��Ȼ�㣨���֣�',
			name: 'layer_no_num',
			id: 'layer_no_num',
			}
			,{
			tag: 'input',
			title: '��ռ����',
			name: 'layer_used',
			id: 'layer_used',
			}
			,{
			tag: 'input',
			title: '��������',
			name: 'house_attr',
			id: 'house_attr',
			}
			,{
			tag: 'input',
			title: '��������',
			name: 'house_type',
			id: 'house_type',
			}
			,{
			tag: 'input',
			title: '��;',
			name: 'house_usage',
			id: 'house_usage',
			}
			,{
			tag: 'input',
			title: '�ṹ',
			name: 'house_structure',
			id: 'house_structure',
			}
			,{
			tag: 'input',
			title: 'ʹ������',
			name: 'lu_term',
			id: 'lu_term',
			}
			,{
			tag: 'input',
			title: '��ʼ����',
			name: 'start_date',
			id: 'start_date',
			}
			,{
			tag: 'input',
			title: '��ֹ����',
			name: 'end_date',
			id: 'end_date',
			}
			,{
			tag: 'input',
			title: '�������',
			name: 'built_in_area',
			id: 'built_in_area',
			}
			,{
			tag: 'input',
			title: '���ڷ������',
			name: 'house_in_area',
			id: 'house_in_area',
			},{
			tag: 'input',
			title: 'ʹ�����',
			name: 'floor_area',
			id: 'floor_area',
			}
			,{
			tag: 'input',
			title: '����̯���õ����',
			name: 'lu_area',
			id: 'lu_area',
			}
			,{
			tag: 'input',
			title: '��̯�������',
			name: 'shared_com_area',
			id: 'shared_com_area',
			}
			,{
			tag: 'input',
			title: '��̯�������',
			name: 'shared_base_area',
			id: 'shared_base_area',
			}
			,{
			tag: 'input',
			title: '�������',
			name: 'other_area',
			id: 'other_area',
			}
			,{
			tag: 'input',
			title: '¼��Ա��Ա��',
			name: 'inputer_no',
			id: 'inputer_no',
			}
			,{
			tag: 'input',
			title: '¼��Ա',
			name: 'inputer',
			id: 'inputer',
			}
			,{
			tag: 'input',
			title: '¼������',
			name: 'input_date',
			id: 'input_date',
			}
			,{
			tag: 'input',
			title: '�޸��˹�Ա��',
			name: 'modifier_no',
			id: 'modifier_no',
			}
			,{
			tag: 'input',
			title: '�޸���',
			name: 'modifier',
			id: 'modifier',
			}
			,{
			tag: 'input',
			title: '�޸�����',
			name: 'modify_date',
			id: 'modify_date',
			}
			,{
			tag: 'input',
			title: '¥��.¥����',
			name: 'rise_id',
			id: 'rise_id',
			}
			,{
			tag: 'input',
			title: '��¼״̬',
			name: 'data_status',
			id: 'data_status',
			}
			,{
			tag: 'input',
			title: '������Դ',
			name: 'data_from',
			id: 'data_from',
			}
			,{
			tag: 'input',
			title: '���ݺ�׼��',
			name: 'approver_no',
			id: 'approver_no',
			}
			,{
			tag: 'input',
			title: '���ݺ�׼����',
			name: 'appr_date',
			id: 'appr_date',
			}
			,{
			tag: 'input',
			title: '���ݺ�׼���',
			name: 'appr_opinion',
			id: 'appr_opinion',
			}
			,{
			tag: 'input',
			title: '���ݺ�׼���',
			name: 'appr_result',
			id: 'appr_result',
			}
			,{
			tag: 'input',
			title: '����ͼid',
			name: 'hxt_id',
			id: 'hxt_id',
			}
			,{
			tag: 'input',
			title: '��淿��ID',
			name: 'fcch_flatlet_id',
			id: 'fcch_flatlet_id',
			}],
		url: 'house!saveHouse.action',
		dataType: 'json',
		success: function(data){
			if (data.success) {
				top.$.messager.alert('����������ʾ',data.tipMessage,'info',function(){
					args.userDataGrid.datagrid('reload');
					closeInTopWindow('add_user_win');
				});				
			} else {
				top.$.messager.alert('�������ݴ���',data.errorMessage,'error');
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
