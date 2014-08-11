(function($){

	//添加validatebox组件的验证规则。验证数字和区间内的整数
	$.extend($.fn.validatebox.defaults.rules, {
		number: {
			validator: function(value, param){
				try{
					eval('var a = ' + value);
					if (typeof a != 'number')
						return false;
					if (!param)
						return true;
					var result = true;
					if (param.length>0)
						result = (a>=param[0]);
					if (param.length>1)
						result = (result && a<param[1]);
					return result;					
				}catch(ex){
					return false;
				}
			},
			message: '该输入项必须是数字'
		},
		integer: {
			validator: function(value, param){
				try{
					eval('var a = ' + value);
					if (typeof a != 'number')
						return false;
					if (a != parseInt(a))
						return false;
					if (!param)
						return true;
					var result = true;
					if (param.length>0)
						result = (a>=param[0]);
					if (param.length>1)
						result = (result && a<param[1]);
					return result;
				}catch(ex){
					return false;
				}
			},
			message: '该输入项必须是非负整数'
		},
		maxCNLength: {   
			validator: function(value, param){
				return value.replace(/[^\x00-\xFF]/g, 'xx').length <= param[0];
			},
			message: '最多输入{0}个字符.'
		}
	});
	
})(jQuery);