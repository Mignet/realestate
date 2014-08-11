/**
 * comborole - plui
 * 
 * Dependencies:
 *   datagrid
 *   tree
 *   layout
 */
(function($){
	
	$.parser.plugins.push('selection');
	
	/**
	 * create the combodept component.
	 */
	function create(target){
		var opts = $.data(target, 'selection').options;
		var tree = $.data(target, 'selection').tree;
		var datagrid = $.data(target, 'selection').datagrid;
		var selected = $.data(target, 'selection').selected;
		var layout = $.data(target, 'selection').layout;
		var win = $.data(target, 'selection').win;
		
		$(target).addClass('selection-f');
		
		var outer = $(target);
		
		//以下判断是为了判断组件是否已经生成过。如果生成过，就不需要向目标标签中添加子元素标签了。
		//否则，需要向目标标签中添加代表layout（整体布局）、datagrid（右边列表）、tree（左边树）的标签
		
		if (!win)
			outer.window(opts);		
		
		if (!layout){
			var outerLayout = $('<div></div>').appendTo(outer);
			var width = opts.leftWidth || 300;
			var rightWidth = opts.rightWidth || 300;
			$('<div data-options="region:\'west\'" style="width:'+ width +'px;" />').appendTo(outerLayout);
			$('<div data-options="region:\'center\'"/>').appendTo(outerLayout);
			$('<div data-options="region:\'east\'" style="width:'+ rightWidth +'px;"/>').appendTo(outerLayout);						
			//生成布局
			layout = outerLayout.layout($.extend({},opts.layout,{}));
		}								
		
		if (!tree)
			tree = $('<ul/>').appendTo(layout.layout('panel', 'west'));
		if (!datagrid)		
			datagrid = $('<table></table>').appendTo(layout.layout('panel', 'center'));	
		if (!selected)
			selected = 	$('<table></table>').appendTo(layout.layout('panel', 'east'));
		
		var params = opts.datagrid.queryParams || {};		
		//如果queryParams中没有设置idField的对应主键值，设置datagrid的初始后台请求参数（queryParams），将值为-10000的树节点主键值传递进去，从而得到空数据表格 
		if (!opts.tree.idField in params)
			params[opts.tree.idField] = -10000;		
		
		var gridcols = [];
		
		for (var i=0;i<opts.datagrid.columns.length; i++)
			$.merge(gridcols, opts.datagrid.columns[i]);
		
		var columns = [];
		var column = {};
		
		for (var j=0;j<gridcols.length;j++){
			if (gridcols[j].field == opts.datagrid.idField){
				column = gridcols[j];
				column.hidden = true;
				$.merge(columns,[column]);
			}
			if (gridcols[j].field == opts.datagrid.textField){
				$.merge(columns,[gridcols[j]]);
			}
		}
		
		selected.datagrid($.extend({},opts.selected,{
			fit: true,
			pagination:false,
			data: [],
			columns: [columns],
			toolbar: [{
				text: '确定',
				iconCls: 'icon-save',
				handler: function(){
					opts.submit(selected.datagrid('getRows'));
				}
			},{
				text: '清空',
				iconCls: 'icon-undo',
				handler: function(){
					selected.datagrid('loadData',[]);
				}
			}],
			onClickRow: function(index, row){
				selected.datagrid('deleteRow',index);
			}
		}));
		
		//生成数据表格
		datagrid.datagrid($.extend({},opts.datagrid,{
			queryParams: params,
			onSelect: function(index, row){
				opts.datagrid.onSelect(index, row);
				selected.datagrid('appendRow',row);
				datagrid.datagrid('unselectAll');
			}			
		}));	
		
		tree.tree($.extend({}, opts.tree,{
			onClick:function(node){
				var params = opts.datagrid.queryParams || {};
				//点击树节点时即时刷新datagrid的查询内容。从树节点的id作为查询参数。
				params[opts.tree.idField] = node.id;	
				//点击树节点回调函数在此时响应
				opts.tree.onClick(node);
				//刷新数据表格
				datagrid.datagrid({
					queryParams: params
				});
			}
		}));
		
		$.data(target, 'selection', {
			layout: layout,
			datagrid: datagrid,
			tree: tree,
			selected: selected,
			win: win,
			options: opts
		}); 

	}
	
	$.fn.selection = function(options, param){
		if (typeof options == 'string'){
			var method = $.fn.selection.methods[options];			
			if (method)
				//当参数sub没赋值时，调用selection的方法
				return method(this, param);			
			else 				
				return this.window(options, param);			
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'selection');
			if (state){
				$.extend(state.options, options);
			} else {
				var state = $.data(this, 'selection', {
					options: $.extend(true, {}, $.fn.selection.defaults, $.fn.selection.parseOptions(this), options)
				});
			}
			create(this);
		});
	};
	
	$.fn.selection.methods = {
		layout: function (jq){
			return $.data(jq[0], 'selection').layout;
		},
		tree: function(jq){
			return $.data(jq[0], 'selection').tree;			
		},
		datagrid: function(jq){
			return $.data(jq[0], 'selection').datagrid;			
		},
		selected: function(jq){
			return $.data(jq[0], 'selection').selected;			
		},
		options: function(jq){
			return $.data(jq[0], 'selection').options;
		}
	};
	
	$.fn.selection.parseOptions = function(target){
		return $.parser.parseOptions(target);
	};
	
	$.fn.selection.defaults = $.extend(true,{},{
		layout: $.fn.layout.defaults,
		/*datagrid: $.extend({},$.fn.datagrid.defaults,{
			onSelect: function(){}	
		}), */
		datagrid: $.fn.datagrid.defaults,
		tree: $.fn.tree.defaults,
		selected: $.fn.datagrid.defaults, 
		win: $.fn.window.defaults,
		resizable: false,
		maximizable: false,
		collapsible: false,
		onClose: function(){}
	}, {
		
	});
	
})(jQuery);

function createSelect(options){
	var selection = top.$('<div></div>').appendTo(top.$('body'));
	selection.attr("id",options.id);
	var onClose = options.onClose || function (){};	
	selection.selection($.extend({}, options, {
		onClose : function () {
			onClose();
            top.$(this).window('destroy');
        }
	}));	
}