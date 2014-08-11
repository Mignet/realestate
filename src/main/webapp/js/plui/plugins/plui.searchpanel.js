/**
 * comborole - plui
 * 
 * Dependencies:
 *   datagrid
 *   tree
 *   layout
 */
(function($){
	
	$.parser.plugins.push('searchpanel');
	
	/**
	 * create the combodept component.
	 */
	function create(target){
		var opts = $.data(target, 'searchpanel').options;
		var tableform = $.data(target, 'searchpanel').tableform;
		var panel = $.data(target, 'searchpanel').panel;
		
		$(target).addClass('searchpanel-f');
		
		var outer = $(target);
		
		if (opts.containerStyle)
			outer.css(opts.containerStyle);
		
		if (!tableform){
			var searchbox = $(opts.searchbox).appendTo(outer);
			var wrapper = $('<div></div>').appendTo(outer);
			wrapper.width(outer.width()-2);
			wrapper.hide();			
			var button = $('<a></a>').appendTo(searchbox);
			button.linkbutton({
				iconCls: 'icon-accordion_down',
				plain: true
			});
			$(button).click(function(){
				searchbox.hide();
				wrapper.show();
				opts.onExpand.call(target);
			});	
			panel = $('<div></div>').appendTo(wrapper);
			tableform = $('<form method="post"></form>').appendTo(panel);
			var buttondiv = $('<div></div>').appendTo(panel);
			buttondiv.css('textAlign','center');
			var button2 = $('<a></a>').appendTo(buttondiv);
			button2.linkbutton({
				iconCls: 'icon-search',
				text: '查询'
			});	
			button2.click(function(){
				tableform.submit();
			});	
			var button3 = $('<a></a>').appendTo(buttondiv);
			button3.linkbutton({
				iconCls: 'icon-reload',
				text: '重置'
			});	
			button3.click(function(){
				tableform.tableform('clear');
			});	
			var button4 = $('<a></a>').appendTo(buttondiv);
			button4.linkbutton({
				iconCls: 'icon-accordion_up',
				plain: true
			});	
			button4.click(function(){
				wrapper.hide();
				searchbox.show();
				opts.onCollapse.call(target);
			});					
		}			
		
		tableform.tableform(opts);
		
		panel.panel(opts);										
		
		$.data(target, 'searchpanel', {
			tableform: tableform,
			panel: panel,			
			options: opts
		}); 

	}
	
	$.fn.searchpanel = function(options, param){
		if (typeof options == 'string'){
			var method = $.fn.searchpanel.methods[options];			
			var tableformMethod = $.fn.tableform.methods[options];				
			if (method)
				//当参数sub没赋值时，调用searchpanel的方法
				return method(this, param);			
			else if (tableformMethod)				
				return this.tableform(options, param);	
			else
				return this.panel(options, param);		
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'searchpanel');
			if (state){
				$.extend(state.options, options);
			} else {
				var state = $.data(this, 'searchpanel', {
					options: $.extend(true, {}, $.fn.searchpanel.defaults, $.fn.searchpanel.parseOptions(this), options)
				});
			}
			create(this);
		});
	};
	
	$.fn.searchpanel.methods = {
		form: function(jq){
			return $.data(jq[0], 'searchpanel').form;			
		},
		panel: function(jq){
			return $.data(jq[0], 'searchpanel').panel;			
		},
		options: function(jq){
			return $.data(jq[0], 'searchpanel').options;
		}
	};
	
	$.fn.searchpanel.parseOptions = function(target){
		return $.extend(true, {}, $.parser.parseOptions(target), $.fn.panel.parseOptions(target), $.fn.tableform.parseOptions(target));
	};
	
	$.fn.searchpanel.defaults = $.extend(true,{}, $.fn.panel.defaults, $.fn.tableform.defaults, {
		searchbox: null ,
		onExpand : function() {},
		onCollapse : function() {}
	});
	
})(jQuery);
