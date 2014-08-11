/**
 * combosearchuser - plui
 * 
 * Dependencies:
 *   combo
 *   datagrid
 */
(function($) {
	
	$.parser.plugins.push('combosearchuser');
	
	function create(target) {
		var opts = $.data(target, 'combosearchuser').options;
		$(target).addClass('combosearchuser-f');
		$(target).combo(opts);
		
		var datagrid = $.data(target, 'combosearchuser').datagrid;
		var boxlist = $.data(target, 'combosearchuser').boxlist;
		
		var queryParams = {};
		$.data(target, 'combosearchuser').queryParams = queryParams;
		if(opts.deptId || opts.defdeptId) {
			queryParams.groupId = opts.deptId || opts.defdeptId;
		}
		
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
				$.data(target, 'combosearchuser').boxlist = boxlist;
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
						if($(target).attr('onChoose')) {
							//处理onChoose事件
							(function(){
								var values = datagrid.datagrid('getSelections');
								var parti, partis=[], users, user;
								$(values).each(function() {
									partis.push({
										userId : this.userid,
										userName : this.username,
										deptId : this.groupid,
										deptName : this.groupname
									});
								});
								parti = users = user = partis;
								eval($(target).attr('onChoose'));
							}).apply(target, []);
						}
					}
				}]);
			}
			
			datagrid = $('<table />').appendTo(viewpanel);
			$.data(target, 'combosearchuser').datagrid = datagrid;
		}
		
		datagrid.datagrid({
			url : $.pluiplugin.config.searchUsersUrl,
			//跨域 - 只能通过
			dataType : 'jsonp',
			//jsonp只能使用get请求
			method : 'get',
			idField : opts.idField,
			textField : opts.textField,
			border : false,
			fit : true,
			fitColumns: true,
			queryParams : queryParams,
			singleSelect : !opts.multiple,
			pagination : true,
			columns : [[
				{field:'username',title:'姓名',width:100},
				{field:'groupname',title:'机构名',width:150},
				{field:'dutyname',title:'职务',width:150}
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
					$(target).combo('hidePanel');
					retrieveValues(target);
					if($(target).attr('onChoose')) {
						//处理onChoose事件
						(function(){
							var selected = datagrid.datagrid('getSelected');
							var parti = partis = users = user = {
								userId : selected.userid,
								userName : selected.username,
								deptId : selected.groupid,
								deptName : selected.groupname
							};
							eval($(target).attr('onChoose'));
						}).apply(target, []);
					}
				}
			},
			toolbar : [{
				xtype : 'searchbox',
				prompt : '请输入姓名、部门、职务查询',
				width : 200,
				searcher : function(value) {
					if (value == '' || /^(\s)+$/.test(value)) {
						value = '';
						if (opts.defdeptId) {
							queryParams.groupId = opts.defdeptId;
						}
					} else if (queryParams.groupId && !opts.deptId) {
						delete queryParams.groupId;
					}
					datagrid.datagrid('load', $.extend(queryParams, {
						searchStr : encodeURI(value)
					}));
				}
			}]
		});
	};
	
	function retrieveValues(target) {
		var opts = $.data(target, 'combosearchuser').options;
		var datagrid = $.data(target, 'combosearchuser').datagrid;
		
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
		var opts = $.data(target, 'combosearchuser').options;
		if (opts.multiple) {
			var datagrid = $.data(target, 'combosearchuser').datagrid;
			var boxlist = $.data(target, 'combosearchuser').boxlist;
			
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
	
	$.fn.combosearchuser = function(options, param) {
		if (typeof options == 'string') {
			var method = $.fn.combosearchuser.methods[options];
			if (method) {
				return method(this, param);
			} else {
				return this.combo(options, param);
			}
		}
		
		options = options || {};
		
		return this.each(function() {
			var state = $.data(this, 'combosearchuser');
			if (state) {
				$.extend(state.options, options);
			} else {
				$.data(this, 'combosearchuser', {
					options : $.extend({}, $.fn.combosearchuser.defaults, $.fn.combosearchuser.parseOptions(this), options)
				});
			}
			create(this);
			initData(this);
		});
	};
	
	$.fn.combosearchuser.methods = {
		setDeptId : function(jq, deptId) {
			return jq.each(function() {
				var queryParams = $.data(this, 'combosearchuser').queryParams;
				$.extend(queryParams, {
					groupId : deptId,
					searchStr : ''
				});
				var datagrid = $.data(this, 'combosearchuser').datagrid;
				datagrid.datagrid('load', queryParams);
			});
		}
	};
	
	$.fn.combosearchuser.parseOptions = function(target) {
		return $.extend({}, $.fn.combo.parseOptions(target), $.parser.parseOptions(target, ['deptId', 'defdeptId']));
	};
	
	$.fn.combosearchuser.defaults = $.extend({}, $.fn.combo.defaults, {
		editable : false,
		idField : 'userid',
		textField : 'username',
		panelWidth : 500,
		panelHeight : 335
	});
	
})(jQuery);