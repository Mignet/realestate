/*********************************************************************************
*文  件  名  称: project-add.js
*功  能  描  述: 建设项目添加
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Sam
*创  建  日  期： 2014-08-07
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
/**********************************************************************************
*函数名称: init()
*功能说明: 页面初始化函数
*函数作者: Sam
*创建日期: 2014-08-07
*修改历史: 
***********************************************************************************/
function init() {
	var form = $('#add_project_form').tableform({
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
			title: '建设项目记录流水号',
			name: 'prj_id',
			id: 'prj_id',
			}
			,{
			tag: 'input',
			title: '项目编码 ',
			name: 'project_no',
			id: 'project_no',
			}
			,{
			tag: 'input',
			title: '项目名称 ',
			name: 'project_name',
			id: 'project_name',
			}
			,{
			tag: 'input',
			title: '建筑物许可证号 ',
			name: 'conscertificatenu',
			id: 'conscertificatenu',
			}
			,{
			tag: 'input',
			title: '土地使用合同 ',
			name: 'contract_no',
			id: 'contract_no',
			}
			,{
			tag: 'input',
			title: '项目描述',
			name: 'project_dis',
			id: 'project_dis',
			}
			,{
			tag: 'input',
			title: '建筑物数量 ',
			name: 'building_num',
			id: 'building_num',
			}
			,{
			tag: 'input',
			title: '地址',
			name: 'location',
			id: 'location',
			}
			,{
			tag: 'input',
			title: '用地面积（规划）',
			name: 'glebe_area',
			id: 'glebe_area',
			}
			,{
			tag: 'input',
			title: '总建筑面积（规划） ',
			name: 'floor_area',
			id: 'floor_area',
			}
			,{
			tag: 'input',
			title: '建筑物覆盖率（规划） ',
			name: 'build_bestrow_per',
			id: 'build_bestrow_per',
			}
			,{
			tag: 'input',
			title: '建设单位 ',
			name: 'build_unit',
			id: 'build_unit',
			}],
		url: 'project!saveProject.action',
		dataType: 'json',
		success: function(data){
			if (data.success) {
				top.$.messager.alert('新增建设项目提示',data.tipMessage,'info',function(){
					args.projectDataGrid.datagrid('reload');
					closeInTopWindow('add_project_win');
				});				
			} else {
				top.$.messager.alert('新增建设项目错误',data.errorMessage,'error');
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
	        message: '两次密码输入不匹配。'
	    }
	});
});*/
