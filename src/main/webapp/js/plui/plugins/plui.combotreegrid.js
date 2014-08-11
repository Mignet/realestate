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
				
				//��Ӷ�ѡ���
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
					text : '���',
					handler : function() {
						//���datagrid����ѡ��
						datagrid.datagrid('clearSelections');
						//���bixlist��������
						boxlist.boxlist('clear');
					}
				},{
					text : 'ȷ��',
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
						//ѡ�в���
						boxlist.boxlist('setValues', {
							ids : rowData[opts.idField],
							values : rowData[opts.textField]
						});
					} else {
						//ȡ��ѡ�в���
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
	
	//��ʼ������
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
			//���岢����������tree��grid���ݣ�δ����ᵼ�����ݼ̳�ʱ������ݶ�ʧ
			var tree, grid;
			tree = $.extend({}, options.tree);
			grid = $.extend({}, options.grid);
			//�Ƴ�tree��grid����
			options.tree = {};
			options.grid = {};
			
			var state = $.data(this, 'combotreegrid');
			if (state) {
				$.extend(state.options, options);
				$.extend(state.options.tree, tree);	//�̳�tree��������
				$.extend(state.options.grid, grid);	//�̳�grid��������
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
			url : null,	//��ȡ�����ݵ�URL
			lazy : false,	//�첽��Ⱦ
			width : 200	//��ռ�õĿ��
		},
		grid : {
			url : null,	//grid��ȡ���ݵ�URL
			paramName : null,	//grid�ڵ㷢������̨��nameֵ�����ڵ��ֶ�����������ȡ���ڵ�idֵ
			defaultParamValue : '-1',	//gridĬ�ϼ������ݸ��ڵ㣬������ڵ�ǰĬ�ϼ��ص�����
			columns : null	//grid�ڵ���ʾ������
		},
		idField : null,	//ѡ��gridʱ���ø�combo����ĺ�ֵ̨�ֶ�
		textField : null,	//ѡ��gridʱ���ø�combo�������ʾֵ�ֶ�
		panelHeight : 400,	//����߶�
		panelWidth : 400	//������
	});
	
	$.parser.plugins.push('combotreegrid');
})(jQuery);