function init() {
	var form = $('#add_user_form').tableform({
		errorCode: 700,
		//表单元素表格的列数（通常设为两列）
		colnum: 2,
		//表单控件标题列宽
		titleWidth: 120,
		//表单控件输入框列宽
		cellWidth: 150,
		//表单控件input、select、combo等输入框宽
		inputWidth:	150,
		//textarea控件输入框宽
		textareaWidth: 500,
		//textarea控件输入框高
		textareaHeight: 90,
		//是否设置fieldset标签
		fieldset:false	,
		//表单元素参数数组。数组中每个对象构成一个表单元素
		inputs: [{
			tag: 'input',
			title: '房屋编号',
			name: 'ho_id',
			id: 'ho_id',
			}
			,{
			tag: 'input',
			title: '房屋编码（深标）',
			name: 'house_code_sz',
			id: 'house_code_sz',
			}
			,{
			tag: 'input',
			title: '房屋编码（国标）',
			name: 'house_code_gb',
			id: 'house_code_gb',
			}
			,{
			tag: 'input',
			title: '房屋编码（临时）',
			name: 'house_code_tmp',
			id: 'house_code_tmp',
			}
			,{
			tag: 'input',
			title: '宗地支号',
			name: 'parcel_no_subno',
			id: 'parcel_no_subno',
			}
			,{
			tag: 'input',
			title: '房号',
			name: 'unit_no',
			id: 'unit_no',
			}
			,{
			tag: 'input',
			title: '自然房号',
			name: 'house_no_num',
			id: 'house_no_num',
			}
			,{
			tag: 'input',
			title: '座号',
			name: 'seat_no',
			id: 'seat_no',
			}
			,{
			tag: 'input',
			title: '户型',
			name: 'house_style',
			id: 'house_style',
			}
			,{
			tag: 'input',
			title: '所在楼层',
			name: 'floor_no',
			id: 'floor_no',
			}
			,{
			tag: 'input',
			title: '自然层（实际层）',
			name: 'layer_no',
			id: 'layer_no',
			}
			,{
			tag: 'input',
			title: '自然层（数字）',
			name: 'layer_no_num',
			id: 'layer_no_num',
			}
			,{
			tag: 'input',
			title: '所占层数',
			name: 'layer_used',
			id: 'layer_used',
			}
			,{
			tag: 'input',
			title: '房屋性质',
			name: 'house_attr',
			id: 'house_attr',
			}
			,{
			tag: 'input',
			title: '房屋类型',
			name: 'house_type',
			id: 'house_type',
			}
			,{
			tag: 'input',
			title: '用途',
			name: 'house_usage',
			id: 'house_usage',
			}
			,{
			tag: 'input',
			title: '结构',
			name: 'house_structure',
			id: 'house_structure',
			}
			,{
			tag: 'input',
			title: '使用年限',
			name: 'lu_term',
			id: 'lu_term',
			}
			,{
			tag: 'input',
			title: '起始日期',
			name: 'start_date',
			id: 'start_date',
			}
			,{
			tag: 'input',
			title: '终止日期',
			name: 'end_date',
			id: 'end_date',
			}
			,{
			tag: 'input',
			title: '房屋面积',
			name: 'built_in_area',
			id: 'built_in_area',
			}
			,{
			tag: 'input',
			title: '套内房屋面积',
			name: 'house_in_area',
			id: 'house_in_area',
			},{
			tag: 'input',
			title: '使用面积',
			name: 'floor_area',
			id: 'floor_area',
			}
			,{
			tag: 'input',
			title: '（分摊）用地面积',
			name: 'lu_area',
			id: 'lu_area',
			}
			,{
			tag: 'input',
			title: '分摊公用面积',
			name: 'shared_com_area',
			id: 'shared_com_area',
			}
			,{
			tag: 'input',
			title: '分摊基底面积',
			name: 'shared_base_area',
			id: 'shared_base_area',
			}
			,{
			tag: 'input',
			title: '其它面积',
			name: 'other_area',
			id: 'other_area',
			}
			,{
			tag: 'input',
			title: '录入员雇员号',
			name: 'inputer_no',
			id: 'inputer_no',
			}
			,{
			tag: 'input',
			title: '录入员',
			name: 'inputer',
			id: 'inputer',
			}
			,{
			tag: 'input',
			title: '录入日期',
			name: 'input_date',
			id: 'input_date',
			}
			,{
			tag: 'input',
			title: '修改人雇员号',
			name: 'modifier_no',
			id: 'modifier_no',
			}
			,{
			tag: 'input',
			title: '修改人',
			name: 'modifier',
			id: 'modifier',
			}
			,{
			tag: 'input',
			title: '修改日期',
			name: 'modify_date',
			id: 'modify_date',
			}
			,{
			tag: 'input',
			title: '楼宇.楼宇编号',
			name: 'rise_id',
			id: 'rise_id',
			}
			,{
			tag: 'input',
			title: '记录状态',
			name: 'data_status',
			id: 'data_status',
			}
			,{
			tag: 'input',
			title: '数据来源',
			name: 'data_from',
			id: 'data_from',
			}
			,{
			tag: 'input',
			title: '数据核准人',
			name: 'approver_no',
			id: 'approver_no',
			}
			,{
			tag: 'input',
			title: '数据核准日期',
			name: 'appr_date',
			id: 'appr_date',
			}
			,{
			tag: 'input',
			title: '数据核准意见',
			name: 'appr_opinion',
			id: 'appr_opinion',
			}
			,{
			tag: 'input',
			title: '数据核准结果',
			name: 'appr_result',
			id: 'appr_result',
			}
			,{
			tag: 'input',
			title: '户型图id',
			name: 'hxt_id',
			id: 'hxt_id',
			}
			,{
			tag: 'input',
			title: '测绘房屋ID',
			name: 'fcch_flatlet_id',
			id: 'fcch_flatlet_id',
			}],
		url: 'house!saveHouse.action',
		dataType: 'json',
		success: function(data){
			if (data.success) {
				top.$.messager.alert('新增房屋提示',data.tipMessage,'info',function(){
					args.userDataGrid.datagrid('reload');
					closeInTopWindow('add_user_win');
				});				
			} else {
				top.$.messager.alert('新增房屋错误',data.errorMessage,'error');
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
	        message: '两次密码输入不匹配。'
	    }
	});
});
