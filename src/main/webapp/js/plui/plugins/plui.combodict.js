/**
 * combodict - plui
 * 
 * ×ÖµäÑ¡ÔñÁÐ±í
 * 
 * Dependencies:
 *   combobox
 */
(function($){
	
	function create(target) {
		var opts = $.data(target, 'combodict').options;
		$(target).combobox($.extend({}, opts, {
			url : $.pluiplugin.config.dictUrl + '?classType=' + encodeURI(encodeURI(opts.classType)) + '&classCode=' + encodeURI(encodeURI(opts.classCode)),
			panelHeight : opts.panelHeight || $.fn.combo.defaults.panelHeight,
			onShowPanel : function() {
				if (opts.onShowPanel) {
					opts.onShowPanel.call(target, arguments);
				}
				resize(target);
			}
		}));
	};
	
	function resize(target) {
		var opts = $.data(target, 'combodict').options;
		if (!opts.panelHeight) {
			var panel = $(target).combobox('panel');
			var items = panel.find('div.combobox-item');
			if (items.length != 0) {
				var lastitem = items.last();
				if (items.length > 10) {
					lastitem = $(items[9]);
				}
				panel.panel({
					height : lastitem.position().top + panel.scrollTop() + lastitem.outerHeight() + 2
				});
			}
		}
	};
	
	$.fn.combodict = function(options, param) {
		if (typeof options == 'string') {
			var method = $.fn.combodict.methods[options];
			if (method) {
				return method(this, param);
			} else {
				return this.combobox(options, param);
			}
		}
		
		options = options || {};
		
		return this.each(function() {
			var state = $.data(this, 'combodict');
			if (state) {
				$.extend(state.options, options);
			} else {
				$.data(this, 'combodict', {
					options : $.extend({}, $.fn.combodict.defaults, $.fn.combodict.parseOptions(this), options)
				});
			}
			create(this);
		});
	};
	
	$.fn.combodict.methods = {
		
	};
	
	$.fn.combodict.parseOptions = function (target) {
		return $.extend({}, $.fn.combo.parseOptions(target), $.parser.parseOptions(target, ['classType', 'classCode']));
	};
	
	$.fn.combodict.defaults = $.extend({}, $.fn.combo.defaults, {
		editable : false,
		textField : 'name',
		valueField : 'value',
		panelHeight : null,
		onSelect: function(record){},
		onUnselect: function(record){}
	});
	
	$.parser.plugins.push('combodict');
	
})(jQuery);