/**
 * comborole - plui
 * 
 * Dependencies:
 *   datagrid
 *   tree
 *   layout
 */
(function($){
	
	$.parser.plugins.push('treelist');
	
	/**
	 * create the combodept component.
	 */
	function create(target){
		var opts = $.data(target, 'treelist').options;
		var tree = $.data(target, 'treelist').tree;
		var datagrid = $.data(target, 'treelist').datagrid;
		var layout = $.data(target, 'treelist').layout;
		
		$(target).addClass('treelist-f');
		
		var outerLayout = $(target);
		
		//以下判断是为了判断组件是否已经生成过。如果生成过，就不需要向目标标签中添加子元素标签了。
		//否则，需要向目标标签中添加代表layout（整体布局）、datagrid（右边列表）、tree（左边树）的标签
		if (!layout){
			var width = opts.leftWidth || 500;
			$('<div data-options="region:\'west\'" style="width:'+ width +'px;" />').appendTo(outerLayout);
			$('<div data-options="region:\'center\'"/>').appendTo(outerLayout);					
		}						
		//生成布局
		outerLayout.layout($.extend({},opts.layout,{}));
		
		if (!tree)
			tree = $('<ul/>').appendTo(outerLayout.layout('panel', 'west'));
		if (!datagrid)		
			datagrid = $('<table></table>').appendTo(outerLayout.layout('panel', 'center'));		
		
		var params = opts.datagrid.queryParams || {};		
		//如果queryParams中没有设置idField的对应主键值，设置datagrid的初始后台请求参数（queryParams），将值为-10000的树节点主键值传递进去，从而得到空数据表格 
		if (!opts.tree.idField in params)
			params[opts.tree.idField] = -10000;		
		//生成数据表格
		datagrid.datagrid($.extend({},opts.datagrid,{
			queryParams: params
		}));	
		
		tree.tree($.extend({}, opts.tree,{
			onClick:function(node){
				var params = opts.datagrid.queryParams || {};
				//点击树节点时即时刷新datagrid的查询内容。从树节点的id作为查询参数。
				params[opts.tree.idField] = node.id;	
				//点击树节点回调函数在此时响应
				opts.tree.onClick(node);
				//刷新数据表格
				$(datagrid).datagrid({
					queryParams: params
				});
			}
		}));
		
		$.data(target, 'treelist').layout = layout;
		$.data(target, 'treelist').datagrid = datagrid;
		$.data(target, 'treelist').tree = tree;
		
	}
	
	$.fn.treelist = function(options, param){
		if (typeof options == 'string'){
			var method = $.fn.treelist.methods[options];			
			if (method)
				//当参数sub没赋值时，调用treelist的方法
				return method(this, param);			
			else 				
				throw new Error("The method \""+ method +"\" does not exist!");			
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'treelist');
			if (state){
				$.extend(state.options, options);
			} else {
				var state = $.data(this, 'treelist', {
					options: $.extend(true, {}, $.fn.treelist.defaults, $.fn.treelist.parseOptions(this), options)
				});
			}
			create(this);
		});
	};
	
	$.fn.treelist.methods = {
		layout: function (jq){
			return $.data(jq[0], 'treelist').layout;
		},
		tree: function(jq){
			return $.data(jq[0], 'treelist').tree;			
		},
		datagrid: function(jq){
			return $.data(jq[0], 'treelist').datagrid;			
		},
		options: function(jq){
			$.data(jq[0], 'treelist').options;
		}
	};
	
	$.fn.treelist.parseOptions = function(target){
		return $.parser.parseOptions(target);
	};
	
	$.fn.treelist.defaults = $.extend(true,{},{
		layout: $.fn.layout.defaults,
		datagrid: $.fn.datagrid.defaults, 
		tree: $.fn.tree.defaults
	}, {
		
	});
})(jQuery);