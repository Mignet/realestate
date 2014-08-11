function __init(){

	var form = $('#edit_building_form').tableform({
		errorCode: 700,
		//表单元素表格的列数（通常设为两列）
		colnum: 2,
		//表单控件标题列宽
		titleWidth: 140,
		//表单控件输入框列宽
		cellWidth: 250,
		//表单控件input、select、combo等输入框宽
		inputWidth:	180,
		//textarea控件输入框宽
		textareaWidth: 500,
		//textarea控件输入框高
		textareaHeight: 90,
		//是否设置fieldset标签
		fieldset:false	,
		//表单元素参数数组。数组中每个对象构成一个表单元素
		inputs: [{
			tag: 'input',
			title: '建筑物编码[深标]',
			name: 'bldg_code_sz',
			id: 'bldg_code_sz',
			type: 'validatebox'
			}
			,{
			tag: 'input',
			title: '建筑物编码[国标] ',
			name: 'bldg_code_gb',
			id: 'bldg_code_gb',
			type: 'validatebox'
			}
			,{
			tag: 'input',
			title: '建筑物临时编码 ',
			name: 'bldg_code_tmp',
			id: 'bldg_code_tmp',
			type: 'validatebox'
			}
			,{
			tag: 'input',
			title: '宗地.地块编号 ',
			name: 'par_id',
			id: 'par_id',
			}
			,{
			tag: 'input',
			title: '项目名称 ',
			name: 'project_name',
			id: 'project_name',
			}
			,{
			tag: 'input',
			title: '栋号 ',
			name: 'rise_no',
			id: 'rise_no',
			}
			,{
			tag: 'input',
			title: '楼名称及栋号 ',
			name: 'bldg_name_no',
			id: 'bldg_name_no',
			}
			,{
			tag: 'input',
			title: '所在行政区 ',
			name: 'division',
			id: 'division',
			}
			,{
			tag: 'input',
			title: '地址[路]',
			name: 'road',
			id: 'road',
			}
			,{
			tag: 'input',
			title: '所在街道[街] ',
			name: 'street',
			id: 'street',
			}
			,{
			tag: 'input',
			title: '地址[门牌号] ',
			name: 'house_num',
			id: 'house_num',
			}
			,{
			tag: 'input',
			title: '坐落 ',
			name: 'location',
			id: 'location',
			}
			,{
			tag: 'input',
			title: '宗地号及支号 ',
			name: 'par_no_subno',
			id: 'par_no_subno',
			}
			,{
			tag: 'input',
			title: '房屋性质 ',
			name: 'bldg_attr',
			id: 'bldg_attr',
			}
			,{
			tag: 'input',
			title: '房屋类型 ',
			name: 'bldg_type',
			id: 'bldg_type',
			}
			,{
			tag: 'input',
			title: '房屋用途[规划]',
			name: 'bldg_usage',
			id: 'bldg_usage',
			}
			,{
			tag: 'input',
			title: '房屋用途[现状] ',
			name: 'bldg_usage_now',
			id: 'bldg_usage_now',
			}
			,{
			tag: 'input',
			title: '房屋结构 ',
			name: 'bldg_structure',
			id: 'bldg_structure',
			}
			,{
			tag: 'input',
			title: '房屋层数 ',
			name: 'bldg_floors',
			id: 'bldg_floors',
			}
			,{
			tag: 'input',
			title: '开工日期 ',
			name: 'const_startdate',
			id: 'const_startdate',
			type: 'datebox'
			}
			,{
			tag: 'input',
			title: '竣工日期 ',
			name: 'const_enddate',
			id: 'const_enddate',
			type: 'datebox'
			}
			,{
			tag: 'input',
			title: '使用年限 ',
			name: 'lu_term',
			id: 'lu_term',
			}
			,{
			tag: 'input',
			title: '起始日期 ',
			name: 'start_date',
			id: 'start_date',
			type: 'datebox'
			}
			,{
			tag: 'input',
			title: '终止日期 ',
			name: 'end_date',
			id: 'end_date',
			type: 'datebox'
			}
			,{
			tag: 'input',
			title: '基底面积 ',
			name: 'base_area',
			id: 'base_area',
			}
			,{
			tag: 'input',
			title: '建筑面积 ',
			name: 'built_up_area',
			id: 'built_up_area',
			}
			,{
			tag: 'input',
			title: '其他面积 ',
			name: 'ther_area',
			id: 'ther_area',
			}
			,{
			tag: 'input',
			title: '分摊用地面积 ',
			name: 'shared_lu_area',
			id: 'shared_lu_area',
			}
			,{
			tag: 'input',
			title: '架空层数 ',
			name: 'b_jkc_count',
			id: 'b_jkc_count',
			}
			,{
			tag: 'input',
			title: '转换层数 ',
			name: 'b_zhc_count',
			id: 'b_zhc_count',
			}
			,{
			tag: 'input',
			title: '设备层数 ',
			name: 'b_sbc_count',
			id: 'b_sbc_count',
			}
			,{
			tag: 'input',
			title: '避难层数 ',
			name: 'b_bnc_count',
			id: 'b_bnc_count',
			}
			,{
			tag: 'input',
			title: '地面以上面积 ',
			name: 'b_up_build_area',
			id: 'b_up_build_area',
			}
			,{
			tag: 'input',
			title: '半地下室面积 ',
			name: 'b_ather_build_area',
			id: 'b_ather_build_area',
			}
			,{
			tag: 'input',
			title: '地下室面积',
			name: 'b_down_build_area',
			id: 'b_down_build_area',
			}
			,{
			tag: 'input',
			title: '建筑物X坐标 ',
			name: 'b_build_pos_x',
			id: 'b_build_pos_x',
			}
			,{
			tag: 'input',
			title: '建筑物Y坐标 ',
			name: 'b_build_pos_y',
			id: 'b_build_pos_y',
			}
			,{
			tag: 'input',
			title: '建筑高度 ',
			name: 'b_build_height',
			id: 'b_build_height',
			}
			,{
			tag: 'input',
			title: '裙楼层数 ',
			name: 'b_skirt_count',
			id: 'b_skirt_count',
			}
			,{
			tag: 'input',
			title: '塔楼层数 ',
			name: 'b_tower_count',
			id: 'b_tower_count',
			}
			,{
			tag: 'input',
			title: '公用建筑面积合计 ',
			name: 'b_common_area',
			id: 'b_common_area',
			}
			,{
			tag: 'input',
			title: '应分摊建筑面积 ',
			name: 'b_apportion_common_area',
			id: 'b_apportion_common_area',
			}
			,{
			tag: 'input',
			title: '不应分摊建筑面积 ',
			name: 'b_noapportion_common_area',
			id: 'b_noapportion_common_area',
			}
			,{
			tag: 'input',
			title: '地上总层数 ',
			name: 'b_up_build_floor',
			id: 'b_up_build_floor',
			}
			,{
			tag: 'input',
			title: '地下总层数 ',
			name: 'b_down_build_floor',
			id: 'b_down_build_floor',
			}
			,{
			tag: 'input',
			title: '半地下室层数 ',
			name: 'ather_build_floor',
			id: 'ather_build_floor',
			}
			,{
			tag: 'input',
			title: '房屋套数 ',
			name: 'unit_suits',
			id: 'unit_suits',
			}
			,{
			tag: 'input',
			title: '报建时间 ',
			name: 'rptdate',
			id: 'rptdate',
			type: 'datebox'
			}
			,{
			tag: 'input',
			title: '报建证号 ',
			name: 'rptid',
			id: 'rptid',
			}
			,{
			tag: 'input',
			title: '创建人id ',
			name: 'inputer_no',
			id: 'inputer_no',
			}
			,{
			tag: 'input',
			title: '创建人姓名 ',
			name: 'inputer',
			id: 'inputer',
			}
			,{
			tag: 'input',
			title: '创建日期 ',
			name: 'input_date',
			id: 'input_date',
			type: 'datebox'
			}
			,{
			tag: 'input',
			title: '最后修改人雇员号 ',
			name: 'modifier_no',
			id: 'modifier_no',
			}
			,{
			tag: 'input',
			title: '最后修改人 ',
			name: 'modifier',
			id: 'modifier',
			}
			,{
			tag: 'input',
			title: '最后修改日期 ',
			name: 'modify_date',
			id: 'modify_date',
			type: 'datebox'
			}
			,{
			tag: 'input',
			title: '楼盘表状态 ',
			name: 'bldg_status',
			id: 'bldg_status',
			}
			,{
			tag: 'input',
			title: '记录状态 ',
			name: 'data_state',
			id: 'data_state',
			}
			,{
			tag: 'input',
			title: '数据来源 ',
			name: 'data_from',
			id: 'data_from',
			}
			,{
			tag: 'input',
			title: '影像图ID ',
			name: 'yxt_id',
			id: 'yxt_id',
			}
			,{
			tag: 'input',
			title: '测绘建筑物ID ',
			name: 'fcch_bldg_id',
			id: 'fcch_bldg_id',
			}],
		url: 'building!updateBuilding.action',
		dataType : 'json',
		success: function(data){
			if (data.success) {
				top.$.messager.alert('更新建筑提示',data.tipMessage,'info',function(){
					args.buildingDataGrid.datagrid('reload');
				});				
			} else {
				top.$.messager.alert('更新建筑错误',data.errorMessage,'error');
			}
		}
	});
	//从服务端获取建筑信息
	$.ajax({
		type: "POST",
		url:  'building!getBuildingByID.action?bldg_id='+args.building.bldg_id,
		dataType:"json",
		success: function(building){
			//对象添加属性
			//building.dept_name=args.building.dept_name;
			//building.password = null;
			//载入表单
			form.form("load",building);
		}
	});

	$('#building_password').focusout(function() {
		if ($(this).val() != '') {
			//密码不为空时 - 检验确认密码
			$('#confirm_password').validatebox({
				required : true,
				validType : "confirmPassword['#building_password']"
			});
		} else {
			//密码为空时 - 不校验确认密码
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
	        message: '两次密码输入不匹配。'
	    }
	});
});