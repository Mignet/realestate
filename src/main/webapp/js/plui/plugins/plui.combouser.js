/**
 * combouser - plui
 * 
 * Dependencies:
 *   combo
 *   datagrid
 *   tree
 */
(function($){
	
	$.parser.plugins.push('combouser');
	
	function create(target) {
		var opts = $.data(target, 'combouser').options;
		var tree = $.data(target, 'combouser').tree;
		var datagrid = $.data(target, 'combouser').datagrid;
		var boxlist = $.data(target, 'combouser').boxlist;
		
		$(target).addClass('combouser-f');
		$(target).combo(opts);
		var panel = $(target).combo('panel');
		
		//set param
		opts.param = opts.param||{};
		$.extend(opts.param, opts.bureauCode?{bureauCode:opts.bureauCode}:{}, opts.rootDept?{rootDept:opts.rootDept}:{});
		
		//加大多选高度
		if (opts.multiple) {
			panel.panel({ height : opts.panelHeight + 36 });
		}
		
		if (!tree) {
			
			var outerLayout = $('<div></div>').appendTo(panel);
			$('<div data-options="region:\'west\',border:false" style="width:250px;" />').appendTo(outerLayout);
			$('<div data-options="region:\'center\',border:false" />').appendTo(outerLayout);
			//初始化面板
			outerLayout.layout({ fit : true });
			
			tree = $('<ul/>').appendTo(outerLayout.layout('panel', 'west'));
			$.data(target, 'combouser').tree = tree;
			
			tree.css('overflow', 'hidden');
//			tree.css('text-overflow', 'ellipsis');
			
			datagrid = $('<table />').appendTo(outerLayout.layout('panel', 'center'));
			$.data(target, 'combouser').datagrid = datagrid;
			
			if (opts.multiple) {

				//添加多选面板
				outerLayout.layout('add', {
					region : 'south',
					height : 36,
					border : false
				});
				
				var southlayout = $('<div />').appendTo(outerLayout.layout('panel', 'south'));
				$('<div data-options="region:\'center\',border:false"></div>').appendTo(southlayout);
				$('<div data-options="region:\'east\',border:false" style="width:140px"></div>').appendTo(southlayout);
				
				southlayout.layout({ fit : true });
				
				boxlist = $('<div></div>').appendTo(southlayout.layout('panel', 'center'));
				$.data(target, 'combouser').boxlist = boxlist;
				boxlist.boxlist({
					onBeforeClose : function(box) {
						datagrid.datagrid('unSelectRecord', box.attr('vid'));
					}
				});
				
				var buttondiv = $('<div class="dialog-button" />').appendTo(southlayout.layout('panel', 'east'));
				
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
						if($(target).attr('onChoose')) {
							(function(){
								var user = datagrid.datagrid('getSelections'), parti = partis = users = user;
								eval($(target).attr('onChoose'));
							}).apply(target, []);
						}
					}
				}]);
			}
		}
		
		datagrid.datagrid({
			url : $.pluiplugin.config.deptUsersUrl,
			dataType : 'jsonp',
			idField : opts.idField,
			textField : opts.textField,
			border : false,
			fit : true,
			queryParams : { groupId : '-1', searchStr : opts.searchStr || {} },
			//fitColumns: true,
			singleSelect : !opts.multiple,
			pagination : true,
			pageList : [12],
			columns : [[
				{field:'username',title:'名称',width:100},
				{field:'dutyname',title:'职务',width:140},
				{field:'groupname',title:'部门',width:140, hidden : true}
			]],
			onClickRow : function(rowIndex, rowData) {
				if (opts.multiple) {
					var options = datagrid.datagrid('options');
					var tr = options.finder.getTr(this, rowIndex);
					if (tr.hasClass('datagrid-row-selected')) {
						//选中操作
						boxlist.boxlist('setValues', {
							ids : rowData.userid,
							values : rowData.username
						});
					} else {
						//取消选中操作
						boxlist.boxlist('deleteValues', {
							ids : rowData.userid
						});
					}
				} else {
					retrieveValues(target);
					$(target).combo('hidePanel');
					if($(target).attr('onChoose')) {
						(function(){
							var user = datagrid.datagrid('getSelected'), parti = partis = users = user;
							eval($(target).attr('onChoose'));
						}).apply(target, []);
					}
				}
			},
			toolbar : [{
				xtype : 'searchbox',
				prompt : '请输入姓名、部门',
				width : 150,
				//menu : $('<div><div name="all">所有人员</div><div name="sub">当前部门</div></div>'),
				searcher : function(value) {
					//输入查询
					datagrid.datagrid('hideColumn', 'dutyname');
					datagrid.datagrid('showColumn', 'groupname');
					datagrid.datagrid('load', {
						searchStr : encodeURI(value)
					});
				}
			}]
		});
		datagrid.datagrid('getPager').pagination({
			showPageList : false,
			showRefresh : false,
			displayMsg : ''
		});
		
		tree.tree({
			url : $.pluiplugin.config.deptTreeUrl,
			dataType : 'jsonp',
			param : opts.param,
			lazy : true,
			onLoadSuccess: function(node, data){
				tree.tree('expand', tree.tree('getRoot').target);
			},
			onClick : function (node) {
				datagrid.datagrid('hideColumn', 'groupname');
				datagrid.datagrid('showColumn', 'dutyname');
				datagrid.datagrid('load', {
					groupId : node.id,
					searchStr : opts.searchStr || {}
				});
			}
		});
	};
	
	function retrieveValues(target) {
		var opts = $.data(target, 'combouser').options;
		var datagrid = $.data(target, 'combouser').datagrid;
		
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
		var opts = $.data(target, 'combouser').options;
		if (opts.multiple) {
			var datagrid = $.data(target, 'combouser').datagrid;
			var boxlist = $.data(target, 'combouser').boxlist;
			
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
	
	$.fn.combouser = function(options, param) {
		if(typeof options == 'string') {
			var method = $.fn.combouser.methods[options];
			if (method) {
				return method(this, param);
			}else {
				return this.combo(options, param);
			}
		}
		
		options = options || {};
		return this.each(function() {
			var state = $.data(this, 'combouser');
			if (state) {
				$.extend(state.options, options);
			} else {
				$.data(this, 'combouser', {
					options : $.extend({}, $.fn.combouser.defaults, $.fn.combouser.parseOptions(this), options)
				});
			}
			create(this);
			initData(this);
		});
	};
	
	$.fn.combouser.methods = {};
	
	$.fn.combouser.parseOptions = function(target) {
		return $.extend({}, $.fn.combo.parseOptions(target), $.parser.parseOptions(target, ['bureauCode','rootDept','searchStr']));
	};
	
	$.fn.combouser.defaults = $.extend({}, $.fn.combo.defaults, $.fn.datagrid.defaults, {
		editable : false,
		idField : 'userid',
		textField : 'username',
		panelWidth : 500,
		panelHeight : 385
	});
})(jQuery);