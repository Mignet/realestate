/**
 * validatebox - plui
 * 使用授权及技术支持请联系: server@netgis.cn
 * 功能描述：实现表单验证
 */
(function($){
	
	function init(target){
		$(target).addClass('validatebox-text');
	}
	
	/**
	 * destroy the box, including it's tip object.
	 */
	function destroyBox(target){
		var state = $.data(target, 'validatebox');
		state.validating = false;
		var tip = state.tip;
		if (tip){
			tip.remove();
		}
		$(target).unbind();
		$(target).remove();
	}
	
	function bindEvents(target){
		var box = $(target);
		var state = $.data(target, 'validatebox');
		
		box.unbind('.validatebox').bind('focus.validatebox', function(){
			state.validating = true;
			state.value = undefined;
			(function(){
				if (state.validating){
					if (state.value != box.val()){	// when box value changed, validate it
						state.value = box.val();
						if (state.timer){
							clearTimeout(state.timer);
						}
						state.timer = setTimeout(function(){
							$(target).validatebox('validate');
						}, state.options.delay);
					} else {
						fixTipPosition(target);	// correct the tip position
					}
					setTimeout(arguments.callee, 200);
				}
			})();
		}).bind('blur.validatebox', function(){
			if (state.timer){
				clearTimeout(state.timer);
				state.timer = undefined;
			}
			state.validating = false;
			hideTip(target);
		}).bind('mouseenter.validatebox', function(){
			if (box.hasClass('validatebox-invalid')){
				showTip(target);
			}
		}).bind('mouseleave.validatebox', function(){
			if (!state.validating){
				hideTip(target);
			}
		});
	}
	
	/**
	 * show tip message.
	 */
	function showTip(target){
		var msg = $.data(target, 'validatebox').message;
		var tip = $.data(target, 'validatebox').tip;
		if (!tip){
			tip = $(
				'<div class="validatebox-tip">' +
					'<span class="validatebox-tip-content">' +
					'</span>' +
					'<span class="validatebox-tip-pointer">' +
					'</span>' +
				'</div>'
			).appendTo('body');
			$.data(target, 'validatebox').tip = tip;
		}
		tip.find('.validatebox-tip-content').html(msg);
		fixTipPosition(target);
	}
	
	function fixTipPosition(target){
		var state = $.data(target, 'validatebox');
		if (!state){return}
		var tip = state.tip;
		if (tip){
			var box = $(target);
			var pointer = tip.find('.validatebox-tip-pointer');
			var content = tip.find('.validatebox-tip-content');
			tip.show();
			tip.css('top', box.offset().top - (content._outerHeight() - box._outerHeight()) / 2);
			if (state.options.tipPosition == 'left'){
				if($(target).hasClass('combo-text')){
					tip.css('left', box.offset().left - tip._outerWidth());
				}else{
					tip.css('left', box.offset().left - tip._outerWidth());
				}
				tip.addClass('validatebox-tip-left');
			} else {
				if($(target).hasClass('combo-text')){
					tip.css('left', box.offset().left + box._outerWidth() + 18);
				}else{
					tip.css('left', box.offset().left + box._outerWidth());
				}
				tip.removeClass('validatebox-tip-left');
			}
			pointer.css('top', (content._outerHeight() - pointer._outerHeight()) / 2);
		}
	}
	
	/**
	 * hide tip message.
	 */
	function hideTip(target){
		var tip = $.data(target, 'validatebox').tip;
		if (tip){
			tip.remove();
			$.data(target, 'validatebox').tip = null;
		}
	}
	
	/**
	 * do validate action
	 */
	function validate(target){
		var state = $.data(target, 'validatebox');
		var opts = state.options;
		var tip = state.tip;
		var box = $(target);
		var value = box.val();
		
		function setTipMessage(msg){
			state.message = msg;
		}
		function doValidate(vtype){
			var result = /([a-zA-Z_]+)(.*)/.exec(vtype);
			var rule = opts.rules[result[1]];
			if (rule && value){
				var param = eval(result[2]);
				if (!rule['validator'](value, param)){
					box.addClass('validatebox-invalid');
					
					var message = rule['message'];
					if (param){
						for(var i=0; i<param.length; i++){
							message = message.replace(new RegExp("\\{" + i + "\\}", "g"), param[i]);
						}
					}
					setTipMessage(opts.invalidMessage || message);
					if (state.validating){
						showTip(target);
					}

					return false;
				}
			}
			return true;
		}
		
		
		if (opts.required){
			if (trim(value) == ''){
				box.addClass('validatebox-invalid');
				setTipMessage(opts.missingMessage);
				if (state.validating){
					showTip(target);
				}
				return false;
			}
		}
		if (opts.validType){
			if (typeof opts.validType == 'string'){
				if (!doValidate(opts.validType)){return false;};
			} else {
				for(var i=0; i<opts.validType.length; i++){
					if (!doValidate(opts.validType[i])){return false;}
				}
			}
		}
		
		box.removeClass('validatebox-invalid');
		hideTip(target);
		return true;
	}
	
	$.fn.validatebox = function(options, param){
		if (typeof options == 'string'){
			return $.fn.validatebox.methods[options](this, param);
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'validatebox');
			if (state){
				$.extend(state.options, options);
			} else {
				init(this);
				$.data(this, 'validatebox', {
					options: $.extend({}, $.fn.validatebox.defaults, $.fn.validatebox.parseOptions(this), options)
				});
			}
			
			bindEvents(this);
		});
	};
	
	$.fn.validatebox.methods = {
		destroy: function(jq){
			return jq.each(function(){
				destroyBox(this);
			});
		},
		validate: function(jq){
			return jq.each(function(){
				validate(this);
			});
		},
		isValid: function(jq){
			return validate(jq[0]);
		}
	};
	
	$.fn.validatebox.parseOptions = function(target){
		var t = $(target);
		return $.extend({}, $.parser.parseOptions(target, [
		    'validType','missingMessage','invalidMessage','tipPosition',{delay:'number'}
		]), {
			required: (t.attr('required') ? true : undefined)
		});
	};
	
	$.fn.validatebox.defaults = {
		required: false,
		validType: null,
		delay: 200,	// delay to validate from the last inputting value.
		missingMessage: '该输入项为必输项.',
		invalidMessage: null,
		tipPosition: 'right',	// Possible values: 'left','right'.
		
		rules: {
			email:{
				validator: function(value){
					return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
				},
				message: '请输入有效的电子邮件地址.'
			},
			url: {
				validator: function(value){
					return /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(value);
				},
				message: '请输入有效的URL地址.'
			},
			minLength: {   
				validator: function(value, param){   
					return value.length >= param[0];   
				},   
				message: '至少输入{0}个字符.'  
			},
			maxLength: {   
				validator: function(value, param){   
					return value.length <= param[0];   
				},   
				message: '最多输入{0}个字符.'  
			},
			length: {//判断字符长度
				validator: function(value, param){
					var len = $.trim(value).length;
					return len >= param[0] && len <= param[1]
				},
				message: '输入内容长度必须介于{0}和{1}之间.'
			},
			int: {//判断是否为整数
				validator: function(value,param){//参数1为最小值，参数2为最大值
					var patrn = /^[-,+]{0,1}[0-9]{0,}$/;
					if (!patrn.exec(value)){
						this.message='输入内容必须是整数.';
						return false;
					}
					if(param && param.length==2){
						if(param[0]==null && param[1]!==null && value>param[1]){
							this.message='输入的整数不能大于{1}.';
							return false;
						}else if(param[1]==null && param[0]!==null && value<param[0]){
							this.message='输入的整数不能小于{0}.';
							return false;
						}else if(param[0]!==null && param[1]!==null && !(value>=param[0] && value<=param[1])){
							this.message='只能输入{0}-{1}之间的整数.';
							return false;
						}	
					}
					return true;
				},
				message: '输入内容必须是整数.'
			},
			number: {//判断是否为数字
				validator: function(value,param){//参数1为最小值，参数2为最大值
					var patrn = /^[-,+]{0,1}[0-9]{0,}[.]{0,1}[0-9]{0,}$/;
					if (!patrn.exec(value)){
						this.message='输入内容必须是数字.';
						return false;
					}
					if(param && param.length==2){
						if(param[0]==null && param[1]!==null && value>param[1]){
							this.message='输入的数字不能大于{1}.';
							return false;
						}else if(param[1]==null && param[0]!==null && value<param[0]){
							this.message='输入的数字不能小于{0}.';
							return false;
						}else if(param[0]!==null && param[1]!==null && !(value>=param[0] && value<=param[1])){
							this.message='只能输入{0}-{1}之间的数字.';
							return false;
						}	
					}
					return true;
				},
				message: '输入内容必须是数字.'
			},
			float: {//判断是否为小数
				validator: function(value,param){//参数1是小数位数，参数2为最小值，参数3为最大值
					if (isNaN(value)) {
						this.message='输入内容必须是数字.';
						return false;
					}
					if(param && param.length==3){
						if(param[0]!==null && !this.checkDecimal(value,param[0])){//如果为0则不验证小数位
							this.message='小数位不能超过{0}位.';
							return false;
						}else if(param[1]==null && param[2]!==null && value>param[2]){
							this.message='输入的数字不能大于{2}.';
							return false;
						}else if(param[2]==null && param[1]!==null && value<param[1]){
							this.message='输入的数字不能小于{1}.';
							return false;
						}else if(param[1]!==null && param[2]!==null && !(value>=param[1] && value<=param[2])){
							this.message='只能输入{1}-{2}之间的数字.';
							return false;
						}	
					}
					return true;
				},
				checkDecimal:function(num, dec) {//检查小数位数
					var len = dec * 1 + 1;
					if (num.indexOf('.') > 0) {
						num = num.substr(num.indexOf('.') + 1, num.length - 1);
						if ((num.length) < len) {
							return true;
						} else {
							return false;
						}
					}
					return true;
				},
				message: '输入内容必须是数字.'
			},
			date: {//判断是否为日期格式
				validator: function(value){
					if (!this.isDate(value) && value != "") {
						this.message='日期格式只能是(yyyy-mm-dd)';
						return false;
					}
					return true;
				},
				isDate:function(str) {//判断是否为日期 2010-4-20
					var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
					if (r == null) {
						return false;
					}
					var d = new Date(r[1], r[3] - 1, r[4]);
					if (!(d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4])) {
						return false;
					}
					return true;
				},
				message: '输入内容必须是日期.'
			},
			datetime: {//判断是否为日期加时间格式
				validator: function(value){
					if (!this.isDateTime(value) && value != "") {
						this.message='日期时间格式必须是(yyyy-mm-dd hh:mm:ss).';
						return false;
					}
					return true;
				},
				isDateTime:function(str) {//判断是否为日期加时间2010-4-20 09:19:50
					var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
					var r = str.match(reg);
					if (r == null) return false;
					var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
					return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4] && d.getHours() == r[5] && d.getMinutes() == r[6] && d.getSeconds() == r[7]);
				},
				message: '日期时间格式必须是(yyyy-mm-dd hh:mm:ss).'
			},
			time: {//判断是否为时间格式
				validator: function(value){
					if (!this.isTime(value) && value != "") {
						this.message='时间格式必须是(hh:mm:ss).';
						return false;
					}
					return true;
				},
				isTime:function(str) {//判断是否为时间09:19:50
					var a = str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
					if (a == null) return false;
					if (a[1] > 23 || a[3] > 59 || a[4] > 59) {
						return false
					}
					return true;
				},
				message: '时间格式必须是(hh:mm:ss).'
			},
			equals: {   
				validator: function(value,param){   
					return value == $(param[0]).val();   
				},   
				message: '字段值不一匹配.'  
			},
			tel : {// 验证电话号码，支持手机号验证，多个电话以半角分号隔开
				validator : function(value) { 
					//return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
					var str = value.split(";");
					for (var i = 0; i < str.length; i++) {
						if(str[i].length > 0){
							if (!/^(\d{3}-\d{7})$|^(\d{3}-\d{8})$|^(\d{4}-\d{7})$|^(\d{4}-\d{8})$|^(\d{7})$|^(\d{8})$|^(13|15|18)\d{9}$/.test(str[i])) {
								this.message="座机号格式只能是7位、8位、3位-7位、3位-8位、4位-7位、4位-8位数字组成,手机号由11位数字组成";
								return false;
							}	
						}
					}
					return true;
				}, 
				message : '电话格式不正确.' 
			}, 
			mobile : {// 验证手机号码 
				validator : function(value) { 
					return /^(13|15|18)\d{9}$/i.test(value); 
				}, 
				message : '手机号码格式不正确' 
			}, 
			idcard : {// 验证身份证 
				validator : function(value) { 
					return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value); 
				}, 
				message : '身份证号码格式不正确' 
			}, 
			chinese : {// 验证中文 
				validator : function(value) { 
					return /^[\u0391-\uFFE5]+$/i.test(value); 
				}, 
				message : '只能输入中文' 
			}, 
			english : {// 验证英语 
				validator : function(value) { 
					return /^[A-Za-z]+$/i.test(value); 
				}, 
				message : '只能输入英文' 
			}, 
			fax : {// 验证传真 
				validator : function(value) { 
		//            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value); 
					return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
				}, 
				message : '传真号码不正确' 
			}, 
			zip : {// 验证邮政编码 
				validator : function(value) { 
					return /^[1-9]\d{5}$/i.test(value); 
				}, 
				message : '邮政编码格式不正确' 
			}, 
			ip : {// 验证IP地址 
				validator : function(value) { 
					return /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/.test(value); 
				}, 
				message : 'IP地址格式不正确' 
			}, 
			remote: {
				validator: function(value, param){
					var data = {};
					data[param[1]] = value;
					var response = $.ajax({
						url:param[0],
						dataType:'json',
						data:data,
						async:false,
						cache:false,
						type:'post'
					}).responseText;
					return response == 'true';
				},
				message: '请修正该字段.'
			}
		}
	};
})(jQuery);