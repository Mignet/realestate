/**
 * combotreegrid - plui
 * 
 * Dependencies:
 *   combo
 *   datagrid
 *   tree
 */
(function($){
	
	function create(target) {
		var opts = $.data(target, 'combotreegrid').options;
		var tree = $.data(target, 'combotreegrid').tree;
		var datagrid = $.data(target, 'combotreegrid').datagrid;
		var boxlist = $.data(target, 'combotreegrid').boxlist;
		
		$(target).addClass('combotreegrid-f');
		$(target).combo(opts);
		var panel = $(target).combo('panel');
		
		if (!tree) {
			var outerLayout = $('<div></div>').appendTo(panel);
			$('<div data-options="region:\'west\',border:false" style="width:' + opts.tree.width + 'px;" />').appendTo(outerLayout);
			$('<div data-options="region:\'center\',border:false" />').appendTo(outerLayout);
			
			outerLayout.layout({ fit : true });
			
			datagrid = $('<table />').appendTo(outerLayout.layout('panel', 'center'));
			$.data(target, 'combotreegrid').datagrid = datagrid;
			
			tree = $('<ul/>').appendTo(outerLayout.layout('panel', 'west'));
			$.data(target, 'combotreegrid').tree = tree;
			
			if (opts.multiple) {
				
				//添加多选面板
				outerLayout.layout('add', {
					region : 'south',
					height : 36,
					border : false
				});
				
				var bottomLayout = $('<div />').appendTo(outerLayout.layout('panel', 'south'));
				
				$('<div data-options="region:\'center\',border:false" />').appendTo(bottomLayout);
				$('<div data-options="region:\'east\',border:false" style="width:140px" />').appendTo(bottomLayout);
				
				bottomLayout.layout({ fit : true });
				
				boxlist = $('<div></div>').appendTo(bottomLayout.layout('panel', 'center'));
				$.data(target, 'combotreegrid').boxlist = boxlist;
				boxlist.boxlist({
					onBeforeClose : function(box) {
						datagrid.datagrid('unSelectRecord', box.attr('vid'));
					}
				});
				
				var buttondiv = $('<div class="dialog-button" />').appendTo(bottomLayout.layout('panel', 'east'));
				
				function _buildButton(buttons) {
					for(var i=0; i<buttons.length; i++){
						var p = buttons[i];
						var button = $('<a href="javascript:void(0)"></a>').appendTo(buttondiv);
						if (p.handler) button[0].onclick = p.handler;
						button.linkbutton(p);
					}
				};
				
				_buildButton([{
					text : '清除',
					handler : function() {
						//清楚datagrid所有选中
						datagrid.datagrid('clearSelections');
						//清楚bixlist所有数据
						boxlist.boxlist('clear');
					}
				},{
					text : '确定',
					handler : function() {
						retrieveValues(target);
						$(target).combo('hidePanel');
					}
				}]);
			}
			
			var params = {};
			params[opts.grid.paramName] = opts.grid.defaultParamValue;
			
		}
		
		datagrid.datagrid({
			url : opts.grid.url,
			idField : opts.idField,
			textField : opts.textField,
			border : false,
			fit : true,
			queryParams : params,
			fitColumns: true,
			singleSelect : !opts.multiple,
			pagination : true,
			columns : opts.grid.columns,
			onClickRow : function(rowIndex, rowData) {
				if (opts.multiple) {
					var options = datagrid.datagrid('options');
					var tr = options.finder.getTr(this, rowIndex);
					if (tr.hasClass('datagrid-row-selected')) {
						//选中操作
						boxlist.boxlist('setValues', {
							ids : rowData[opts.idField],
							values : rowData[opts.textField]
						});
					} else {
						//取消选中操作
						boxlist.boxlist('deleteValues', {
							ids : rowData[opts.idField]
						});
					}
				} else {
					retrieveValues(target);
					$(target).combo('hidePanel');
				}
			}
		});
		datagrid.datagrid('getPager').pagination({
			showPageList : false,
			showRefresh : false,
			displayMsg : ''
		});
		
		tree.tree({
			url : opts.tree.url,
			lazy : opts.tree.lazy,
			onLoadSuccess: function(node, data){
				tree.tree('expand', tree.tree('getRoot').target);
			},
			onClick : function (node) {
				var params = {};
				params[opts.grid.paramName] = node.id;
				datagrid.datagrid('load', params);
			}
		});
	};
	
	function retrieveValues(target) {
		var opts = $.data(target, 'combotreegrid').options;
		var datagrid = $.data(target, 'combotreegrid').datagrid;
		
		var rows = datagrid.datagrid('getSelections');
		var vv = [],ss = [];
		for(var i=0; i<rows.length; i++){
			vv.push(rows[i][opts.idField]);
			ss.push(rows[i][opts.textField]);
		}
		if (!opts.multiple){
			$(target).combo('setValues', (vv.length ? vv : ['']));
		} else {
			$(target).combo('setValues', vv);
		}
		$(target).combo('setTexts', ss);
	};
	
	//初始化数据
	function initData(target) {
		var opts = $.data(target, 'combotreegrid').options;
		if (opts.multiple) {
			var datagrid = $.data(target, 'combotreegrid').datagrid;
			var boxlist = $.data(target, 'combotreegrid').boxlist;
			
			if (opts.value) {
				var values, texts, data = [];
				values = opts.value.split(opts.separator);
				if (opts.text) {
					texts = opts.text.split(opts.separator);
				}
				var selectedRows = $.data(datagrid[0], 'datagrid').selectedRows;
				var tmp;
				for (var i = 0; i < values.length; i ++) {
					tmp = {};
					tmp[opts.idField] = values[i];
					tmp[opts.textField] = opts.text?texts[i]:values[i];
					selectedRows.push(tmp);
				}
			}
			
			boxlist.boxlist('setValues', {
				ids : values,
				values : texts?texts:values
			});
		}
	};
	
	$.fn.combotreegrid = function(options, param) {
		if(typeof options == 'string') {
			var method = $.fn.combotreegrid.methods[options];
			if (method) {
				return method(this, param);
			}else {
				return this.combo(options, param);
			}
		}
		
		options = options || {};
		return this.each(function() {
			//定义并抽离属性中tree、grid数据，未抽离会导致数据继承时相关数据丢失
			var tree, grid;
			tree = $.extend({}, options.tree);
			grid = $.extend({}, options.grid);
			//移除tree、grid属性
			options.tree = {};
			options.grid = {};
			
			var state = $.data(this, 'combotreegrid');
			if (state) {
				$.extend(state.options, options);
				$.extend(state.options.tree, tree);	//继承tree属性数据
				$.extend(state.options.grid, grid);	//继承grid属性数据
			} else {
				var opts = $.extend({}, $.fn.combotreegrid.defaults, $.fn.combotreegrid.parseOptions(this), options);
				$.extend(opts.tree, $.fn.combotreegrid.defaults.tree, tree);
				$.extend(opts.grid, $.fn.combotreegrid.defaults.grid, grid);
				$.data(this, 'combotreegrid', {
					options : opts
				});
			}
			create(this);
			initData(this);
		});
	};
	
	$.fn.combotreegrid.methods = {};
	
	$.fn.combotreegrid.parseOptions = function(target) {
		return $.extend({}, $.fn.combo.parseOptions(target));
	}
	
	$.fn.combotreegrid.defaults = $.extend({}, $.fn.combo.defaults, $.fn.datagrid.defaults, {
		editable : false,
		tree : {
			url : null,	//获取树数据的URL
			lazy : false,	//异步渲染
			width : 200	//树占用的宽度
		},
		grid : {
			url : null,	//grid获取数据的URL
			paramName : null,	//grid节点发送至后台的name值（父节点字段名），数据取树节点id值
			defaultParamValue : '-1',	//grid默认加载数据父节点，点击树节点前默认加载的数据
			columns : null	//grid节点显示列配置
		},
		idField : null,	//选中grid时设置给combo组件的后台值字段
		textField : null,	//选中grid时设置给combo组件的显示值字段
		panelHeight : 400,	//组件高度
		panelWidth : 400	//组件宽度
	});
	
	$.parser.plugins.push('combotreegrid');
})(jQuery);