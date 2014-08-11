/**
 * comborole - plui
 * 
 * Dependencies:
 *   combo
 *   tree
 *   layout
 */
(function($){
	
	$.parser.plugins.push('combotreelist');
	
	/**
	 * create the combodept component.
	 */
	function create(target){
		var opts = $.data(target, 'combotreelist').options;
		
		$(target).addClass('combotreelist-f');
		$(target).combo($.extend({},opts, {
			onShowPanel : function() {
				//_showPanel(target);
			}
		}));
		var panel = $(target).combo('panel');	
					
		var treelist = $('<div></div>').appendTo(panel);
		treelist.treelist($.extend(true, {}, opts, {
			datagrid: {
				onClickRow: function (index, row){
					//执行用户自定义回调函数
					opts.datagrid.onClickRow(index, row);
					//执行必有的回调函数，点选一行后数据填入表单
					$(target).combo('setValue',(row[opts.datagrid.idField]));
					$(target).combo('setText',(row[opts.datagrid.textField]));
					$(target).combo('hidePanel');
				}
			}			
		}));
		
		$.data(target, 'combotreelist',$.extend(true,{},$.data(treelist,'treelist'),{options: opts}));		
	}
	
	$.fn.combotreelist = function(options, param){
		if (typeof options == 'string'){			
			var method = $.fn.combotreelist.methods[options];
			if (method)
				return method(this, param);
			else
				return this.combo(options, param);			
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'combotreelist');
			if (state){
				$.extend(state.options, options);
			} else {
				$.data(this, 'combotreelist', {
					options: $.extend(true, {}, $.fn.combotreelist.defaults, $.fn.combotreelist.parseOptions(this), options)
				});
			}
			create(this);
		});
	};
	
	$.fn.combotreelist.methods = {
		layout: function (jq){
			return $.data(jq[0], 'combotreelist').layout;
		},
		tree: function(jq){
			return $.data(jq[0], 'combotreelist').tree;			
		},
		datagrid: function(jq){
			return $.data(jq[0], 'combotreelist').datagrid;			
		},
		options: function(jq){
			return $.data(jq[0],'combotreelist').options;
		}		
	};
	
	$.fn.combotreelist.parseOptions = function(target){
		return $.parser.parseOptions(target);
	};
	
	$.fn.combotreelist.defaults = $.extend({},$.fn.treelist.defaults,$.fn.combo.defaults);
	
})(jQuery);