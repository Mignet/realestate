/**
 * combosearch - plui
 * 
 * Dependencies:
 *   combo
 *   datagrid
 */
(function($) {
	
	function create(target) {
		var opts = $.data(target, 'combosearch').options;
		$(target).addClass('combosearch-f');
		$(target).combo(opts);
		
		var datagrid = $.data(target, 'combosearch').datagrid;
		var boxlist = $.data(target, 'combosearch').boxlist;
		
		var queryParams = {};
		$.data(target, 'combosearch').queryParams = queryParams;
		
		var panel = $(target).combo('panel');
		var viewpanel = panel;
		
		if (!datagrid) {
			if (opts.multiple) {
				opts.panelHeight += 36;
				$(target).combo({ panelHeight : opts.panelHeight });
				var layout = $('<div />').appendTo(panel);
				$('<div data-options="region:\'center\',border:false"></div>').appendTo(layout);
				$('<div data-options="region:\'south\',border:false" style="height:36px"></div>').appendTo(layout);
				layout.layout({ fit : true });
				viewpanel = layout.layout('panel', 'center');
				
				var southpanel = layout.layout('panel', 'south');
				
				var southlayout = $('<div />').appendTo(southpanel);
				$('<div data-options="region:\'center\',border:false"></div>').appendTo(southlayout);
				$('<div data-options="region:\'east\',border:false" style="width:140px"></div>').appendTo(southlayout);
				southlayout.layout({ fit : true });
				
				boxlist = $('<div></div>').appendTo(southlayout.layout('panel', 'center'));
				$.data(target, 'combosearch').boxlist = boxlist;
				boxlist.boxlist({
					onBeforeClose : function(box) {
						datagrid.datagrid('unSelectRecord', box.attr('vid'));
					}
				});
				
				var buttondiv = $('<div class="dialog-button"></div>').appendTo(southlayout.layout('panel', 'east'));
				
				function _buildButton(buttons) {
					for(var i=0; i<buttons.length; i++){
						var p = buttons[i];
						var button = $('<a href="javascript:void(0)"></a>').appendTo(buttondiv);
						if (p.handler) button[0].onclick = p.handler;
						button.linkbutton(p);
					}
				};
				
				_buildButton([{
						text : '清除', handler : function() {
							//清楚datagrid所有选中
							datagrid.datagrid('clearSelections');
							//清楚bixlist所有数据
							boxlist.boxlist('clear');
						}
					},{ text : '确定', handler : function() {
						$(target).combo('hidePanel');
						retrieveValues(target);
					}
				}]);
			}
			
			datagrid = $('<table />').appendTo(viewpanel);
			$.data(target, 'combosearch').datagrid = datagrid;
		}
		
		datagrid.datagrid({
			url : opts.url,
			method : opts.method,
			idField : opts.idField,
			textField : opts.textField,
			border : false,
			fit : true,
			fitColumns: true,
			queryParams : queryParams,
			singleSelect : !opts.multiple,
			pagination : true,
			columns : opts.columns,
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
					$(target).combo('hidePanel');
					retrieveValues(target);
				}
			},
			toolbar : [{
				xtype : 'searchbox',
				prompt : opts.prompt,
				searcher : function(value) {
					queryParams[opts.searchField] = value;
					datagrid.datagrid('load', queryParams);
				}
			}]
		});
	};
	
	function retrieveValues(target) {
		var opts = $.data(target, 'combosearch').options;
		var datagrid = $.data(target, 'combosearch').datagrid;
		
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
	
	/**
	 * 设置组件初始值
	 */
	function initData(target) {
		var opts = $.data(target, 'combosearch').options;
		if (opts.multiple) {
			var datagrid = $.data(target, 'combosearch').datagrid;
			var boxlist = $.data(target, 'combosearch').boxlist;
			
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
				boxlist.boxlist('setValues', {
					ids : values,
					values : texts?texts:values
				});
			}
		}
	};
	
	$.fn.combosearch = function(options, param) {
		if (typeof options == 'string') {
			var method = $.fn.combosearch.methods[options];
			if (method) {
				return method(this, param);
			} else {
				return this.combo(options, param);
			}
		}
		
		options = options || {};
		
		return this.each(function() {
			var state = $.data(this, 'combosearch');
			if (state) {
				$.extend(state.options, options);
			} else {
				$.data(this, 'combosearch', {
					options : $.extend({}, $.fn.combosearch.defaults, $.fn.combosearch.parseOptions(this), options)
				});
			}
			create(this);
			initData(this);
		});
	};
	
	$.fn.combosearch.methods = {
	};
	
	$.fn.combosearch.parseOptions = function(target) {
		return $.extend({}, $.fn.combo.parseOptions(target), $.parser.parseOptions(target));
	};
	
	$.fn.combosearch.defaults = $.extend({}, $.fn.combo.defaults, {
		url : null,
		method : 'post',
		editable : false,
		idField : null,
		textField : null,
		columns : null,
		panelWidth : 500,
		panelHeight : 335,
		prompt : '全文检索',
		searchField : 'searchField'
	});
	
	$.parser.plugins.push('combosearch');
	
})(jQuery);