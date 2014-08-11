function init(bus_type_id) {
   
	$("#bustype").val(bus_type_id);
	var form = $('#add_app_form').tableform({
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
			title: '名称',
			name: 'co.language_name',
			id: 'mc',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
		},{
			tag: 'input',
			title: '模版类型',
			name: 'co.temp_type',
			id: 'mblx',
			type: 'combodict',
			options: {
				code:'053'
			}
		},
		
		
		{
			tag: 'textarea',
			title: '内容',
			name: 'co.language_content',
			id: 'nr',
			type: 'validatebox',
			options: {
				//code: 'cllx',
				//url:'combo_tree_data.json'
				//required: true,
				//missingMessage: '必填项'
			}
		}
				
		],
		url: ctx+"/common/configur!saveComLanguage.action?time="+new Date(),
		dataType: 'json',
		success: function(data){
			if (data.success) {
				top.$.messager.alert('新增常用语提示',data.tipMessage,'info',function(){
					args.userDataGrid.datagrid('reload');
					closeInTopWindow('add_user_win');
				});				
			} else {
				top.$.messager.alert('新增常用语错误',data.errorMessage,'error');
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

//保存
function submit(){
	
	
	$('#add_app_form').submit();
}


