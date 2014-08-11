/**
 * form - plui
 * 使用授权及技术支持请联系: server@netgis.cn
 */
(function($){
	/**
	 * submit the form
	 */
	function ajaxSubmit(target, options){
		options = options || {};
		
		var param = {};
		if (options.onSubmit){
			if (options.onSubmit.call(target, param) == false) {
				return;
			}
		}
		
		var form = $(target);
		if (options.url){
			form.attr('action', options.url);
		}
		var frameId = 'plui_frame_' + (new Date().getTime());
		var frame = $('<iframe id='+frameId+' name='+frameId+'></iframe>')
				.attr('src', window.ActiveXObject ? 'javascript:false' : 'about:blank')
				.css({
					position:'absolute',
					top:-1000,
					left:-1000
				});
		var t = form.attr('target'), a = form.attr('action');
		form.attr('target', frameId);
		
		var paramFields = $();
		try {
			frame.appendTo('body');
			frame.bind('load', cb);
			for(var n in param){
				var f = $('<input type="hidden" name="' + n + '">').val(param[n]).appendTo(form);
				paramFields = paramFields.add(f);
			}
			form[0].submit();
		} finally {
			form.attr('action', a);
			t ? form.attr('target', t) : form.removeAttr('target');
			paramFields.remove();
		}
		
		var checkCount = 10;
		function cb(){
			frame.unbind();
			var body = $('#'+frameId).contents().find('body');
			var data = body.html();
			if (data == ''){
				if (--checkCount){
					setTimeout(cb, 100);
					return;
				}
				return;
			}
			var ta = body.find('>textarea');
			if (ta.length){
				data = ta.val();
			} else {
				var pre = body.find('>pre');
				if (pre.length){
					data = pre.html();
				}
			}			
			/*if (options.success){
				options.success(data);
			}*/
			var flag = true;
			try{
				eval('var tmpdata='+data);												
			} catch(e) {		
				options.failure(options.errorCode, data);							
				flag = false;
			}
			if (flag)
				if (options.dataType == 'json') 
					options.success(tmpdata);
				else 
					options.success(data);											
			setTimeout(function(){
				frame.unbind();
				frame.remove();
			}, 100);
		}
	}
	
	/**
	 * load form data
	 * if data is a URL string type load from remote site, 
	 * otherwise load from local data object. 
	 */
	function load(target, data){
		if (!$.data(target, 'form')){
			$.data(target, 'form', {
				options: $.extend({}, $.fn.form.defaults)
			});
		}
		var opts = $.data(target, 'form').options;
		
		if (typeof data == 'string'){
			var param = {};
			if (opts.onBeforeLoad.call(target, param) == false) return;
			
			$.ajax({
				url: data,
				data: param,
				dataType: 'json',
				success: function(data){
					_load(data);
				},
				error: function(){
					opts.onLoadError.apply(target, arguments);
				}
			});
		} else {
			_load(data);
		}
		
		function _load(data){
			var form = $(target);
			for(var name in data){
				var val = data[name];
				var rr = _checkField(name, val);
				if (!rr.length){
					var f = form.find('input[numberboxName="'+name+'"]');
					if (f.length){
						f.numberbox('setValue', val);	// set numberbox value
					} else {
						$('input[name="'+name+'"]', form).val(val);
						$('textarea[name="'+name+'"]', form).val(val);
						$('select[name="'+name+'"]', form).val(val);
					}
				}
				_loadCombo(name, val);
			}
			opts.onLoadSuccess.call(target, data);
			validate(target);
		}
		
		/**
		 * check the checkbox and radio fields
		 */
		function _checkField(name, val){
			var form = $(target);
			var rr = $('input[name="'+name+'"][type=radio], input[name="'+name+'"][type=checkbox]', form);
			$.fn.prop ? rr.prop('checked',false) : rr.attr('checked',false);
			rr.each(function(){
				var f = $(this);
				if (f.val() == String(val)){
					$.fn.prop ? f.prop('checked',true) : f.attr('checked',true);
				}
			});
			return rr;
		}
		
		function _loadCombo(name, val){
			var form = $(target);
			var cc = ['combobox','combotree','combogrid','datetimebox','datebox','combo'];
			var c = form.find('[comboName="' + name + '"]');
			if (c.length){
				for(var i=0; i<cc.length; i++){
					var type = cc[i];
					if (c.hasClass(type+'-f')){
						/**
						 * chenlia-2014/03/12	解决多选组件需要接收数组而开发人员传入的是字符串的问题
						 */
						if (c[type]('options').multiple){
							if (typeof val == 'object'){
								//setValues(target, opts.value);
								c[type]('setValues', val);
							} else {
								//setValues(target, opts.value.split(opts.separator));
								c[type]('setValues', val.split(c[type]('options').separator));
							}
							//c[type]('setValues', val);
						} else {
							c[type]('setValue', val);
						}
						return;
					}
				}
			}
		}
	}
	
	/**
	 * clear the form fields
	 */
	function clear(target, remainDisabled){
		$('input,select,textarea', target).each(function(){
			var t = this.type, tag = this.tagName.toLowerCase();
			//判断是否disabled
			if (this.disabled && remainDisabled)
				return;
			//判断是否是combo组件中的隐藏域
			if (t == 'hidden' && $(this).attr('class')=='combo-value')
				return; 
			if (t == 'text' || t == 'hidden' || t == 'password' || tag == 'textarea'){
				this.value = '';
			} else if (t == 'file'){
				var file = $(this);
				file.after(file.clone().val(''));
				file.remove();
			} else if (t == 'checkbox' || t == 'radio'){
				this.checked = false;
			} else if (tag == 'select'){
				this.selectedIndex = -1;
			}
			
		});
		/*if ($.fn.combo) $('.combo-f', target).each(function(){
			if ($(this).combo('options').disabled && remainDisabled)
				return;
			$(this).combo('clear');
		});
		if ($.fn.combobox) $('.combobox-f', target).each(function(){
			if ($(this).combobox('options').disabled && remainDisabled)
				return;
			$(this).combobox('clear');
		});
		if ($.fn.combotree) $('.combotree-f', target).each(function(){
			if ($(this).combotree('options').disabled && remainDisabled)
				return;
			$(this).combotree('clear');
		});
		if ($.fn.combogrid) $('.combogrid-f', target).each(function(){
			if ($(this).combotree('options').disabled && remainDisabled)
				return;
			$(this).combotree('clear');
		});*/
		
		var comboPlugins = ['combo','combobox','combotree','combogrid','combodept','comborole','combodict','combotreelist','combouser'];
	
		for (var i=0; i<comboPlugins.length; i++ ){
			var comboPluginName = comboPlugins[i];
			if ($.fn[comboPluginName])
				$('.'+comboPluginName+'-f', target).each(function(){
					if ($(this)[comboPluginName]('options').disabled && remainDisabled)
						return;
					$(this)[comboPluginName]('clear');
				});
		}		
	}
		
	function reset(target){
		target.reset();
		var t = $(target);
		if ($.fn.combo){t.find('.combo-f').combo('reset');}
		if ($.fn.combobox){t.find('.combobox-f').combobox('reset');}
		if ($.fn.combotree){t.find('.combotree-f').combotree('reset');}
		if ($.fn.combogrid){t.find('.combogrid-f').combogrid('reset');}
		if ($.fn.spinner){t.find('.spinner-f').spinner('reset');}
		if ($.fn.timespinner){t.find('.timespinner-f').timespinner('reset');}
		if ($.fn.numberbox){t.find('.numberbox-f').numberbox('reset');}
		if ($.fn.numberspinner){t.find('.numberspinner-f').numberspinner('reset');}
		validate(target);
	}
	
	/**
	 * set the form to make it can submit with ajax.
	 */
	function setForm(target){
		var options = $.data(target, 'form').options;
		var form = $(target);
		form.unbind('.form').bind('submit.form', function(){
			setTimeout(function(){
				ajaxSubmit(target, options);
			}, 0);
			return false;
		});
	}
	
//	function validate(target){
//		if ($.fn.validatebox){
//			var box = $('.validatebox-text', target);
//			if (box.length){
//				box.validatebox('validate');
////				box.trigger('focus');
////				box.trigger('blur');
//				var invalidbox = $('.validatebox-invalid:first', target).focus();
//				return invalidbox.length == 0;
//			}
//		}
//		return true;
//	}
	function validate(target){
		if ($.fn.validatebox){
			var t = $(target);
			t.find('.validatebox-text:not(:disabled)').validatebox('validate');
			var invalidbox = t.find('.validatebox-invalid');
			invalidbox.filter(':not(:disabled):first').focus();
			return invalidbox.length == 0;
		}
		return true;
	}
	
	$.fn.form = function(options, param){
		if (typeof options == 'string'){
			return $.fn.form.methods[options](this, param);
		}
		
		options = options || {};
		return this.each(function(){
			if (!$.data(this, 'form')){
				$.data(this, 'form', {
					options: $.extend({}, $.fn.form.defaults, options)
				});
			}
			setForm(this);
		});
	};
	
	$.fn.form.methods = {
		submit: function(jq, options){
			return jq.each(function(){
				ajaxSubmit(this, $.extend({}, $.fn.form.defaults, options||{}));
			});
		},
		load: function(jq, data){
			return jq.each(function(){
				load(this, data);
			});
		},
		clear: function(jq, remainDisabled){
			return jq.each(function(){
				clear(this, remainDisabled);
			});
		},
		reset: function(jq){
			return jq.each(function(){
				reset(this);
			});
		},
		validate: function(jq){
			return validate(jq[0]);
		}
	};
	
	$.fn.form.defaults = {
		url: null,
		dataType : null,
		onSubmit: function(param){return $(this).form('validate');},
		success: function(data){},
		onBeforeLoad: function(param){},
		onLoadSuccess: function(data){},
		onLoadError: function(){},
		errorCode: 700,
		failure: function(param, data){
			$.errorRespond(param, data);
		}
	};
})(jQuery);
