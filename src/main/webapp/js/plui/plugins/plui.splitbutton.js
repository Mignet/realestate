/**
 * splitbutton - plui
 * 使用授权及技术支持请联系: server@netgis.cn
 * 
 * Dependencies:
 *   linkbutton
 *   menu
 */
(function($){
	
	function init(target){
		var opts = $.data(target, 'splitbutton').options;
		
		var btn = $(target);
		btn.removeClass('s-btn-active s-btn-plain-active').addClass('s-btn');
		btn.linkbutton($.extend({}, opts, {
			text: opts.text + '<span class="s-btn-downarrow">&nbsp;</span>'
		}));
		
		if (opts.menu){
			$(opts.menu).menu({
				onShow: function(){
					btn.addClass((opts.plain==true) ? 's-btn-plain-active' : 's-btn-active');
				},
				onHide: function(){
					btn.removeClass((opts.plain==true) ? 's-btn-plain-active' : 's-btn-active');
				}
			});
		}
		
		setDisabled(target, opts.disabled);
	}
	
	function setDisabled(target, disabled){
		var opts = $.data(target, 'splitbutton').options;
		opts.disabled = disabled;
		
		var btn = $(target);
		var menubtn = btn.find('.s-btn-downarrow');
		if (disabled){
			btn.linkbutton('disable');
			menubtn.unbind('.splitbutton');
		} else {
			btn.linkbutton('enable');
			menubtn.unbind('.splitbutton');
			menubtn.bind('click.splitbutton', function(){
				showMenu();
				return false;
			});
			var timeout = null;
			menubtn.bind('mouseenter.splitbutton', function(){
				timeout = setTimeout(function(){
					showMenu();
				}, opts.duration);
				return false;
			}).bind('mouseleave.splitbutton', function(){
				if (timeout){
					clearTimeout(timeout);
				}
			});
		}
		
		function showMenu(){
			if (!opts.menu) return;
			
//			var left = btn.offset().left;
//			if (left + $(opts.menu)._outerWidth() + 5 > $(window)._outerWidth()){
//				left = $(window)._outerWidth() - $(opts.menu)._outerWidth() - 5;
//			}
			
			$('body>div.menu-top').menu('hide');
			$(opts.menu).menu('show', {alignTo:btn});
//			$(opts.menu).menu('show', {
//				left: left,
//				top: btn.offset().top + btn.outerHeight()
//			});
			btn.blur();
		}
	}
	
	$.fn.splitbutton = function(options, param){
		if (typeof options == 'string'){
			return $.fn.splitbutton.methods[options](this, param);
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'splitbutton');
			if (state){
				$.extend(state.options, options);
			} else {
//				$(this).append('<span class="s-btn-downarrow">&nbsp;</span>');
				$.data(this, 'splitbutton', {
					options: $.extend({}, $.fn.splitbutton.defaults, $.fn.splitbutton.parseOptions(this), options)
				});
				$(this).removeAttr('disabled');
			}
			init(this);
		});
	};
	
	$.fn.splitbutton.methods = {
		options: function(jq){
			return $.data(jq[0], 'splitbutton').options;
		},
		enable: function(jq){
			return jq.each(function(){
				setDisabled(this, false);
			});
		},
		disable: function(jq){
			return jq.each(function(){
				setDisabled(this, true);
			});
		},
		destroy: function(jq){
			return jq.each(function(){
				var opts = $(this).splitbutton('options');
				if (opts.menu){
					$(opts.menu).menu('destroy');
				}
				$(this).remove();
			});
		}
	};
	
	$.fn.splitbutton.parseOptions = function(target){
		var t = $(target);
		return $.extend({}, $.fn.linkbutton.parseOptions(target), 
				$.parser.parseOptions(target, ['menu',{plain:'boolean',duration:'number'}]));
	};
	
	$.fn.splitbutton.defaults = $.extend({}, $.fn.linkbutton.defaults, {
		plain: true,
		menu: null,
		duration: 100
	});
})(jQuery);
