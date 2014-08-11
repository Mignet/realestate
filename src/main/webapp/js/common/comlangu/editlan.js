
function init(user,bus_type_id){   
	$("#cyyid1").val(user.LANGUAGE_ID);
	$("#mc1").val(user.LANGUAGE_NAME);
	$("#nr1").val(user.LANGUAGE_CONTENT);
	$("#bus_type_id").val(bus_type_id);
	$("#mblx1").val(user.TEMP_TYPE);
	
	
	var form = $('#edit_user_form').tableform({
		errorCode: 700,
		//表单元素表格的列数（通常设为两列）
		colnum: 2,
		//表单控件标题列宽
		titleWidth: 120,
		//表单控件输入框列宽
		cellWidth: 250,
		//表单控件input、select、combo等输入框宽
		inputWidth:	200,
		//textarea控件输入框宽
		textareaWidth: 500,
		//textarea控件输入框高
		textareaHeight: 90,
		//是否设置fieldset标签
		fieldset:false	,
		//表单元素参数数组。数组中每个对象构成一个表单元素
		inputs: [
		{
			tag: 'input',
			title: '名称',
			name: 'LANGUAGE_NAME',
			id: 'mc',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
		},{
			tag: 'input',
			title: '模版类型',
			name: 'TEMP_TYPE',
			id: 'mblx',
			type: 'combodict',
			options: {
				code:'053'
			}
		},
		
		
		{
			tag: 'textarea',
			title: '内容',
			name: 'LANGUAGE_CONTENT',
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
		url:ctx+"/common/configur!updateComLanguage.action?time="+new Date(),
		dataType : 'json',
		success: function(data){
			if (data.success) {
				top.$.messager.alert('更新常用语提示',data.tipMessage,'info',function(){
				  
					args.userDataGrid.datagrid('reload');
					 closeInTopWindow('edit_user_win');
				});				
			} else {
				top.$.messager.alert('更新常用语错误',data.errorMessage,'error');
			}
		}
		
		
		
	});
	form.form("load",user);
	
	//从服务端获取用户信息
	/**
	$.ajax({
		type: "POST",
		url:  '../appDelegate/getAppByID.run?djbh='+args.user.djbh,
		dataType:"json",
		success: function(user){
			//对象添加属性
			user.djbh=args.user.djbh;
			user.password = null;
			//载入表单
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
	        message: '两次密码输入不匹配。'
	    }
	});
});
//保存
function submit(){
	
	
	$("#mc1").val($("#mc").val());
	$("#nr1").val($("#nr").val());
	$("#edit_user_form").submit();
	
	
	
}
