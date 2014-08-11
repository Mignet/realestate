(function($){

	//���validatebox�������֤������֤���ֺ������ڵ�����
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
			message: '�����������������'
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
			message: '������������ǷǸ�����'
		},
		maxCNLength: {   
			validator: function(value, param){
				return value.replace(/[^\x00-\xFF]/g, 'xx').length <= param[0];
			},
			message: '�������{0}���ַ�.'
		}
	});
	
})(jQuery);