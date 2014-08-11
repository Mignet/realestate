/**
 * plui.plugin.js
 * plui 插件
 */

(function($){
	
	$.pluiplugin = $.pluiplugin || {
		//server : 'http://www.szpl.gov/platform'
		//server : 'http://192.168.102.20:5001/platform'
		//server : 'http://localhost:8080/webtest'
		server : '/dxtx_re'
	};
	
	$.pluiplugin.config = {
//		deptTreeUrl : $.pluiplugin.server + '/plui2/orgPluginDelegate/getDeptTreeJson.run',
//		deptUsersUrl : $.pluiplugin.server + '/plui2/orgPluginDelegate/getDeptUsersJson.run',
//		searchUsersUrl : $.pluiplugin.server + '/plui2/orgPluginDelegate/searchUsersJson.run',
//		getUserTreeJson : $.pluiplugin.server + '/plui2/orgPluginDelegate/getUserTreeJson.run',

			deptTreeUrl : $.pluiplugin.server + '/sysmanage/org-manage!getDeptTreeJson.action',
			deptUsersUrl : $.pluiplugin.server + '/sysmanage/org-manage!getDeptUsersJson.action',
			searchUsersUrl : $.pluiplugin.server + '/sysmanage/org-manage!searchUsersJson.action',
			dictUrl : $.pluiplugin.server + '/common/dict!getNewDictByCode.action',
			roleByProductUrl: $.pluiplugin.server + '/plui/roleDelegate/getRoleByProductId.run',
			productTreeUrl: $.pluiplugin.server + '/plui/productDelegate/getProductTreeJson.run'
//		deptTreeUrl : $.pluiplugin.server + '/plui2/organization/getDeptTreeJson.go',
//		deptUsersUrl : $.pluiplugin.server + '/plui2/organization/getDeptUsersJson.go',
//		searchUsersUrl : $.pluiplugin.server + '/plui2/organization/searchUsersJson.go',
//		getUserTreeJson : $.pluiplugin.server + '/plui2/organization/getUserTreeJson.go',
//		dictUrl : $.pluiplugin.server + '/plui2/organization/getDictByCode.go'
		
		
//		dictUrl : 'http://localhost:8080/PlatTutor/plui2/dictDelegate/getDictByCode.run'
		//roleByProductUrl : 'http://localhost:8080/PlatTutor/plui2/roleDelegate/getRoleByProductId.run',
		//productTreeUrl : 'http://localhost:8080/PlatTutor/plui2/productDelegate/getProductTreeJson.run'
	};
	
})(jQuery);

/**
 * combodept - plui
 * 
 * Dependencies:
 *   combo
 *   tree
 */
(function($){
	
	$.parser.plugins.push('combodept');
	
	/**
	 * scroll panel to display the specified item
	 */
	function scrollTo(target, value){
		var panel = $(target).combo('panel');
		var tree = $.data(target, 'combodept').tree;
		var node = tree.tree('find', value);
		var item = $(node.target);
		if (item.position().top <= 0){
			var h = panel.scrollTop() + item.position().top;
			panel.scrollTop(h);
		} else if (item.position().top + item.outerHeight() > panel.height()){
			var h = panel.scrollTop() + item.position().top + item.outerHeight() - panel.height();
			panel.scrollTop(h);
		}
	}
	
	/**
	 * show panel
	 */
	function _showPanel(target) {
		var opts = $.data(target, 'combodept').options;
		var tree = $.data(target, 'combodept').tree;
		if(!opts.multiple) {
			var value = $(target).combodept('getValue');
			if(value) {
				var node = tree.tree('find', value);
				tree.tree('select', node.target);
				tree.tree('expandTo', node.target);
				scrollTo(target, value);
			}
		}
	};
	
	/**
	 * create the combodept component.
	 */
	function create(target){
		var opts = $.data(target, 'combodept').options;
		var tree = $.data(target, 'combodept').tree;
		var boxlist = $.data(target, 'combodept').boxlist;
		
		$(target).addClass('combodept-f');
		$(target).combo($.extend({},opts, {
			onShowPanel : function() {
				//_showPanel(target);
			}
		}));
		var panel = $(target).combo('panel');
		var viewpanel = panel;
		
		//set param
		opts.param = opts.param||{};
		$.extend(opts.param, opts.bureauCode?{bureauCode:opts.bureauCode}:{}, opts.rootDept?{rootDept:opts.rootDept}:{});

		if (!tree) {
			if(opts.multiple) {
				
				var layout = $('<div></div>').appendTo(panel);
				$('<div data-options="region:\'north\',border:false" style="height:' + (opts.panelHeight - 90) + 'px"></div>').appendTo(layout);
				$('<div data-options="region:\'center\',border:false"></div>').appendTo(layout);
				$('<div data-options="region:\'south\',border:false" style="height:36px"></div>').appendTo(layout);
				
				layout.layout({ fit : true });
				
				viewpanel = layout.layout('panel', 'north');
				
				boxlist = $('<div></div>').appendTo(layout.layout('panel', 'center'));
				$.data(target, 'combodept').boxlist = boxlist;
				boxlist.boxlist({
					onBeforeClose : function(box) {
						tree.tree('uncheck', tree.tree('find', box.attr('vid')).target);
					}
				});
				
				var buttondiv = $('<div class="dialog-button"></div>').appendTo(layout.layout('panel', 'south'));
				
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
							var checkeds = tree.tree('getChecked');
							$.each(checkeds, function() {
								tree.tree('uncheck', this.target);
							});
						}
					},{ text : '确定', handler : function() {
						retrieveValues(target);
						$(target).combo('hidePanel');
						if($(target).attr('onChoose')) {
							(function(){
								var values = tree.tree('getChecked');
								var dept = [], depts, parti, partis;
								$(values).each(function() {
									dept.push({ deptId : this.id, deptName : this.text });
								});
								parti = partis = depts = dept;
								eval($(target).attr('onChoose'));
							}).apply(target, []);
						}
					}
				}]);
			} 
			var viewlayout = $('<div></div>').appendTo(viewpanel);
			$('<div data-options="region:\'north\',border:false" style="height:25px;"></div>').appendTo(viewlayout);
			$('<div data-options="region:\'center\',border:false"></div>').appendTo(viewlayout);
			viewlayout.layout({fit:true});
			
			var searcher = $('<input></input>').appendTo(viewlayout.layout('panel', 'north'));
			searcher.css('width', opts.panelWidth - 2);
			searcher.searchbox({
				prompt : '搜索部门',
				searcher : function(value, name) {
					tree.tree('query', value);
				}
			});
			tree = $('<ul></ul>').appendTo(viewlayout.layout('panel', 'center'));
			$.data(target, 'combodept').tree = tree;
		}
		
		tree.tree($.extend({}, opts, {
			url : $.pluiplugin.config.deptTreeUrl,
			dataType : 'jsonp',
			param : opts.param,
			lazy : true,
			checkbox: opts.multiple,
			onLoadSuccess: function(node, data){
				var values = $(target).combodept('getValues');
				if (opts.multiple){
					var nodes = tree.tree('getChecked');
					for(var i=0; i<nodes.length; i++){
						var id = nodes[i].id;
						(function(){
							for(var i=0; i<values.length; i++){
								if (id == values[i]) return;
							}
							values.push(id);
						})();
					}
				}
				$(target).combodept('setValues', values);
				//tree.tree('collapseAll');
				tree.tree('expand', tree.tree('getRoot').target);
				opts.onLoadSuccess.call(this, node, data);
			},
			onClick: function(node){
				if (!opts.multiple) {
					//如果为单选，将点击节点值和文字设置为combo值和文字
					retrieveValues(target);
					$(target).combo('hidePanel');
					//opts.onClick.call(this, node);
					if($(target).attr('onChoose')) {
						(function(){
							var dept = { deptId : node.id, deptName : node.text }, parti = partis = depts = dept;
							eval($(target).attr('onChoose'));
						}).apply(target, []);
					}
				} else {
					//如果为多选
					var ck = $(node.target).find('.tree-checkbox');
					if (ck.length){
						if (ck.hasClass('tree-checkbox1')){
							//如果已经选择，取消该节点选中
							tree.tree('uncheck', node.target);
						} else {
							//如果未选中，选中该节点
							tree.tree('check', node.target);
						}
					}
				}
			},
			onCheck: function(node, checked){
				console.log(node);
				//retrieveValues(target);
				opts.onCheck.call(this, node, checked);
				if (checked) {
					boxlist.boxlist('setValues', {
						ids : node.id,
						values : node.text
					});
				} else {
					boxlist.boxlist('deleteValues', {
						ids : node.id
					});
				}
			}
		}));
		
	}
	
	/**
	 * retrieve values from tree panel.
	 */
	function retrieveValues(target){
		var opts = $.data(target, 'combodept').options;
		var tree = $.data(target, 'combodept').tree;
		var vv = [], ss = [];
		if (opts.multiple){
			var nodes = tree.tree('getChecked');
			for(var i=0; i<nodes.length; i++){
				vv.push(nodes[i].id);
				ss.push(nodes[i].text);
			}
		} else {
			var node = tree.tree('getSelected');
			if (node){
				vv.push(node.id);
				ss.push(node.text);
			}
		}
		$(target).combo('setValues', vv).combo('setTexts', ss);
	};
	
	function setValues(target, values){
		var opts = $.data(target, 'combodept').options;
		var tree = $.data(target, 'combodept').tree;
		var boxlist = $.data(target, 'combodept').boxlist;
		tree.find('span.tree-checkbox').addClass('tree-checkbox0').removeClass('tree-checkbox1 tree-checkbox2');
		var vv = [], ss = [];
		for(var i=0; i<values.length; i++){
			var v = values[i];
			var s = v;
			
			var node = tree.tree('find', v);
			if (node){
				s = node.text;
				$(node.target).find('span.tree-checkbox').removeClass('tree-checkbox0 tree-checkbox2').addClass('tree-checkbox1');
				tree.tree('select', node.target);
			}
			vv.push(v);
			ss.push(s);
		}
		if (opts.multiple) {
			var boxlist = $.data(target, 'combodept').boxlist;
			boxlist.find('div.textboxlist>ul').empty();
			boxlist.boxlist('setValues', {
				ids : vv,
				values : ss
			});
		}
		$(target).combo('setValues', vv).combo('setTexts', ss);
	};
	
	//初始化数据值
	function initData(target) {
		
	};
	
	$.fn.combodept = function(options, param){
		if (typeof options == 'string'){
			var method = $.fn.combodept.methods[options];
			if (method){
				return method(this, param);
			} else {
				return this.combo(options, param);
			}
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'combodept');
			if (state){
				$.extend(state.options, options);
			} else {
				$.data(this, 'combodept', {
					options: $.extend({}, $.fn.combodept.defaults, $.fn.combodept.parseOptions(this), options)
				});
			}
			create(this);
			//initData(this);
		});
	};
	
	$.fn.combodept.methods = {
		setBureauCode : function(jq, bureauCode) {
			jq.each(function() {
				var opts = $.data(this, 'combodept').options;
				if (bureauCode && bureauCode != '') {
					$.extend(opts.param, {bureauCode:bureauCode});
				} else {
					opts.param.bureauCode = null;
				}
				$(this).combodept('setValues', []).combodept('setText', '');
				$.data(this, 'combodept').tree.tree();
			});
		},
		setValues: function(jq, values){
			return jq.each(function(){
				setValues(this, values);
			});
		},
		setValue: function(jq, value){
			return jq.each(function(){
				setValues(this, [value]);
			});
		}
	};
	
	$.fn.combodept.parseOptions = function(target){
		return $.extend({}, $.fn.combo.parseOptions(target), $.fn.tree.parseOptions(target), $.parser.parseOptions(target, ['bureauCode','rootDept']));
	};
	
	$.fn.combodept.defaults = $.extend({}, $.fn.combo.defaults, $.fn.tree.defaults, {
		editable : false,
		cascadeCheck : false,
		panelHeight : 400,
		panelWidth : 350
	});
})(jQuery);

/**
 * combodeptuser - plui
 * 
 * Dependencies:
 *   combo
 *   datagrid
 *   tree
 */
(function($){
	
	$.parser.plugins.push('combodeptuser');
	
	function create(target) {
		var opts = $.data(target, 'combodeptuser').options;
		var depttree = $.data(target, 'combodeptuser').depttree;
		var usertree = $.data(target, 'combodeptuser').usertree;
		var selecttree = $.data(target, 'combodeptuser').selecttree;
		var checkall = $.data(target, 'combodeptuser').checkall;
		
		if (opts.selectType == 'dept') {
			$.extend(opts, { panelWidth : 420 });
		}
		
		$(target).addClass('combodeptuser-f');
		$(target).combo(opts);
		var panel = $(target).combo('panel');

		if (opts.view) {
			var combo = $.data(target, 'combo').combo;
			combo.find('.combo-text').hide();
			combo.attr('style', '');
		}
		
		if (opts.iconCls) {
			combo.find('.combo-arrow').addClass(opts.iconCls);
		}
		
		//将param放到使用处初始化可以防止param对象数据被其他组件污染
		opts.param = opts.param||{};
		$.extend(opts.param, opts.bureauCode?{bureauCode:opts.bureauCode}:{});
		
		if (!depttree) {
			
			//设置面板文字不能被选择
			panel.on('selectstart', function() { return false; });
			
			var outerLayout = $('<div></div>').appendTo(panel);
			$('<div data-options="region:\'west\',border:false,split:true" style="width:230px;"/>').appendTo(outerLayout);
			$('<div data-options="region:\'center\',border:false,split:true" />').appendTo(outerLayout);
//			$('<div data-options="region:\'east\',border:false" style="width:170px;"  />').appendTo(outerLayout);
			//初始化面板
			outerLayout.layout({ fit : true });
			
			if (opts.selectType == 'dept') {
				// todo...
			} else {
				outerLayout.layout('add', { region : 'east', width : 190, split:true, border : false });
			}
			
			depttree = $('<ul/>').appendTo(outerLayout.layout('panel', 'west'));
			outerLayout.layout('panel', 'west').addClass('panel-body-rightborder');
			$.data(target, 'combodeptuser').depttree = depttree;
			depttree.css('overflow', 'hidden');

			if (opts.selectType != 'dept') {
				var centerpanel = outerLayout.layout('panel', 'center');
				centerpanel.addClass('panel-body-leftborder panel-body-rightborder');
				var centerLayout = $('<div></div>').appendTo(centerpanel);
				$('<div data-options="region:\'north\',border:false" style="height:29px;"></div>').appendTo(centerLayout);
				$('<div data-options="region:\'center\',border:false"></div>').appendTo(centerLayout);
				centerLayout.layout({ fit : true });
				//搜索面板
				var searchpanel = centerLayout.layout('panel', 'north');
				searchpanel.addClass('dialog-toolbar');
				
				var searcher = $('<input></input>').appendTo(searchpanel);
				searcher.searchbox({
					prompt : '搜索人员',
					width : 130,
					searcher : function(value, name) {
						if (jQuery.trim(value) === '') { return ; }
						usertree.tree({
							url : $.pluiplugin.config.getUserTreeJson,// + '?searchStr=' + encodeURI(),
							param : { searchStr : encodeURI(value) },
							dataType : 'jsonp'
						});
					}
				});
				//用户面板
				var userpanel = centerLayout.layout('panel', 'center');
				usertree = $('<ul />').appendTo(userpanel);
				$.data(target, 'combodeptuser').usertree = usertree;
				usertree.css('overflow', 'hidden');
				
				checkall = $('<span title="全选"></span>').appendTo(centerLayout.layout('panel', 'center')).attr('style', 'position:absolute;display:block;top:0px;left:0px;cursor:pointer;');
				checkall.addClass('tree-checkbox tree-checkbox0');
				$.data(target, 'combodeptuser').checkall = checkall;
				checkall.on('click', function() {
					var tt = $(this);
					if (tt.hasClass('tree-checkbox0') || tt.hasClass('tree-checkbox2')) {
						//如果复选框为没选中或半选中状态 - 将其设为选中状态
						tt.removeClass('tree-checkbox0 tree-checkbox2').addClass('tree-checkbox1');
						
						//处理复选框选中事件 - 选中所有节点
						var nodes = usertree.tree('getChecked', 'unchecked');
						//批量构建新增选中数据
						var data = [];
						for (var i = 0; i < nodes.length; i ++) {
							//添加选中数据
							data.push({
								id : nodes[i].id,
								text : nodes[i].text,
								value : nodes[i].username,
								tip : nodes[i].groupname,
								//checked : true,
								iconCls : 'icon-user'
							});
						}
						//将新增数据添加至选中树
						selecttree.tree('append', {
							data : data
						});
						usertree.find('span.tree-checkbox0').removeClass('tree-checkbox0').addClass('tree-checkbox1');
						/*
						$.each(nodes, function() {
							//usertree.tree('check', this.target);
							selecttree.tree('append', {
								data : [{
									id : this.id,
									text : this.text,
									value : this.username,
									//checked : true,
									iconCls : 'icon-user'
								}]
							});
							$(this.target).find('span.tree-checkbox').removeClass('tree-checkbox0').addClass('tree-checkbox1');
						});
						*/
					} else {
						//如果复选框为选中状态 - 将其设为非选中状态
						tt.removeClass('tree-checkbox1').addClass('tree-checkbox0');
						
						//处理复选框取消选中事件
						var nodes = usertree.tree('getChecked');
						$.each(nodes, function() {
							//usertree.tree('uncheck', this.target);
							selecttree.tree('remove', selecttree.tree('find', this.id).target);
							$(this.target).find('span.tree-checkbox').removeClass('tree-checkbox1').addClass('tree-checkbox0');
						});
					}
				});
			}
			
			var selectpanel;
			if (opts.selectType == 'dept') {
				selectpanel = outerLayout.layout('panel', 'center');
			} else {
				selectpanel = outerLayout.layout('panel', 'east');
			}
			selectpanel.addClass('panel-body-leftborder');
			var selectLayout = $('<div></div>').appendTo(selectpanel);
			$('<div data-options="region:\'center\',border:false"></div>').appendTo(selectLayout);
			$('<div data-options="region:\'south\',border:false" style="height:36px;"></div>').appendTo(selectLayout);
			selectLayout.layout({ fit : true });
			
			selecttree = $('<ul />').appendTo(selectLayout.layout('panel', 'center'));
			selecttree.css('margin-left', '10px');
			selecttree.css('overflow', 'hidden');
			$.data(target, 'combodeptuser').selecttree = selecttree;

			//$('<a class="layout-button-up" href="javascript:void(0)"></a>').appendTo(selectLayout.layout('panel', 'center')).attr('style', 'position:absolute;display:block;top:50px;left:5px;width:16px;height:16px;');
			$('<a href="javascript:void(0)"></a>').appendTo(selectLayout.layout('panel', 'center')).attr('style', 'position:absolute;display:block;top:50px;left:0px;').linkbutton({plain:true,iconCls:'icon-order-top'}).bind('click', function() {
				var sctnode = selecttree.tree('getSelected');
				if (sctnode) {
					if ($(sctnode.target).closest('li').prev().length == 1) {
						$(sctnode.target).closest('li').insertBefore($(sctnode.target).closest('ul').find('li:first'));
					}
				}
			});
			$('<a href="javascript:void(0)"></a>').appendTo(selectLayout.layout('panel', 'center')).attr('style', 'position:absolute;display:block;top:80px;left:0px;').linkbutton({plain:true,iconCls:'icon-order-up'}).bind('click', function() {
				var sctnode = selecttree.tree('getSelected');
				if (sctnode) {
					$(sctnode.target).closest('li').insertBefore($(sctnode.target).closest('li').prev());
				}
			});
			$('<a href="javascript:void(0)"></a>').appendTo(selectLayout.layout('panel', 'center')).attr('style', 'position:absolute;display:block;top:110px;left:0px;').linkbutton({plain:true,iconCls:'icon-order-down'}).bind('click', function() {
				var sctnode = selecttree.tree('getSelected');
				if (sctnode) {
					$(sctnode.target).closest('li').insertAfter($(sctnode.target).closest('li').next());
				}
			});
			$('<a href="javascript:void(0)"></a>').appendTo(selectLayout.layout('panel', 'center')).attr('style', 'position:absolute;display:block;top:140px;left:0px;').linkbutton({plain:true,iconCls:'icon-order-bottom'}).bind('click', function() {
				var sctnode = selecttree.tree('getSelected');
				if (sctnode) {
					if ($(sctnode.target).closest('li').next().length == 1) {
						$(sctnode.target).closest('li').insertAfter($(sctnode.target).closest('ul').find('li:last'));
					}
				}
			});
			
			var buttondiv = $('<div class="dialog-button"></div>').appendTo(selectLayout.layout('panel', 'south'));
			
			function _buildButton(buttons) {
				for(var i=0; i<buttons.length; i++){
					var p = buttons[i];
					var button = $('<a href="javascript:void(0)"></a>').appendTo(buttondiv);
					if (p.handler) button[0].onclick = p.handler;
					button.linkbutton(p);
				}
			};
			
			_buildButton([{text : '确定', handler : function() {
						retrieveValues(target);
						$(target).combo('hidePanel');
					}
				},{
					text : '清除', handler : function() {
						//取消选中的部门
						/*
						var deptsecnodes = depttree.tree('getChecked');
						if (deptsecnodes) {
							$.each(deptsecnodes, function() {
								depttree.tree('uncheck', this.target);
							});
						}
						*/
						depttree.find('span.tree-checkbox1').each(function() {
							$(this).removeClass('tree-checkbox1').addClass('tree-checkbox0');
						});
						//取消选中的人员
						/*
						var usersecnodes = usertree.tree('getChecked');
						if (usersecnodes) {
							$.each(usersecnodes, function() {
								usertree.tree('uncheck', this.target);
							});
						}
						*/
						if (opts.selectType != 'dept') {
							usertree.find('span.tree-checkbox1').each(function() {
								$(this).removeClass('tree-checkbox1').addClass('tree-checkbox0');
							});
							//将全选框设为未选中
							checkall.removeClass('tree-checkbox1 tree-checkbox2').addClass('tree-checkbox0');
						}
						//清空选择区
						selecttree.empty();
					}
				}]);
			
			//部门tree
			depttree.tree({
				url : $.pluiplugin.config.deptTreeUrl,
				param : opts.param,
				dataType : 'jsonp',
				lazy : true,
				checkbox : opts.selectType != 'user',	//如果选择类型为'user'，部门节点不出现checkbox
				cascadeCheck : false,
				onLoadSuccess: function(node, data){
					depttree.tree('expand', depttree.tree('getRoot').target);
					
					if (opts.selectType != 'dept') {
						//不为选择部门时，默认选中defaultDept所指定的部门
						if (opts.defaultDept) {
							var deptnode = depttree.tree('find', opts.defaultDept);
							if (deptnode) {
								depttree.tree('select', deptnode.target);
							}
						}
					}
					
					//设置初始部门后，选中初始部门
					if (opts.value) {
						var values = opts.value.split(opts.separator);
						for (var i = 0; i < values.length; i ++) {
							if (values[i].substr(0, 1) == 'g') {
								depttree.tree('check', depttree.tree('find', values[i].substr(2)).target);
							}
						}
					}
				},
				onBeforeSelect : function(node) {
					//选中同一个部门时不重新加载人员数据
					if (depttree.tree('getSelected') && node.id == depttree.tree('getSelected').id) { return false; }
					//只选择部门时，不获取人员相关数据
					if (opts.selectType != 'dept') {
						usertree.tree({
							url : $.pluiplugin.config.getUserTreeJson,// + '?groupId=' + node.id,
							param : { groupId : node.id },
							dataType : 'jsonp'
						});
					}
				},
				onClick : function(node) {
					//如果是选择部门 - 点击部门节点时直接选中该节点
					if (opts.selectType == 'dept') {
						var ck = $(node.target).find('.tree-checkbox');
						if (ck.length){
							if (ck.hasClass('tree-checkbox1')){
								//如果已经选择，取消该节点选中
								depttree.tree('uncheck', node.target);
							} else {
								//如果未选中，选中该节点
								depttree.tree('check', node.target);
							}
						}
					}
				},
				onCheck : function(node, checked) {
					if (opts.selectType == 'deptuser') {
						//混选时部门ID前用'g_'标识
						if (checked) {
							if (selecttree.tree('find', 'g_' + node.id)) { return ; }	//如果已经存在-不继续添加
							selecttree.tree('append', {
								data : [{
									id : 'g_' + node.id,
									text : node.text,
									value : node.text,
									//checked : true,
									iconCls : 'icon-users'
								}]
							});
						} else {
							selecttree.tree('remove', selecttree.tree('find', 'g_' + node.id).target);
						}
					} else if (opts.selectType == 'dept') {
						//只选部门时，直接使用部门ID
						if (checked) {
							if (selecttree.tree('find', node.id)) { return ; }	//如果已经存在-不继续添加
							selecttree.tree('append', {
								data : [{
									id : node.id,
									text : node.text,
									value : node.text,
									//checked : true,
									iconCls : 'icon-users'
								}]
							});
						} else {
							selecttree.tree('remove', selecttree.tree('find', node.id).target);
						}
					}
				}
			});
			
			//人员树 -- 只选部门时不加载人员面板
			if (opts.selectType != 'dept') {
				usertree.tree({
					checkbox : true,
					cascadeCheck : false,
					onLoadSuccess : function(node, data) {
						//将全选选择框设为未选中状态
						checkall.removeClass('tree-checkbox1 tree-checkbox2').addClass('tree-checkbox0');
						
						var sctnodes = selecttree.tree('getRoots');
						$.each(sctnodes, function() {
							//选中已经选择过了的人员节点
							if (this.id.substr(0, 1) != 'g') {
								var usernode = usertree.tree('find', this.id);
								if (usernode) {
									//usertree.tree('check', usernode.target);
									$(usernode.target).find('span.tree-checkbox').removeClass('tree-checkbox0').addClass('tree-checkbox1');
								}
							}
						});
						
						//设置全选选择框
						if (usertree.tree('getRoots').length == usertree.tree('getChecked').length) {
							checkall.removeClass('tree-checkbox0').addClass('tree-checkbox1');
						} else if (usertree.tree('getChecked').length > 0) {
							checkall.removeClass('tree-checkbox0').addClass('tree-checkbox2');
						}
					},
					onCheck : function(node, checked) {
						if (checked) {
							if (selecttree.tree('find', node.id)) { return ; }	//如果已经存在-不继续添加
							selecttree.tree('append', {
								data : [{
									id : node.id,
									text : node.text,
									value : node.username,
									tip : node.groupname,
									//checked : true,
									iconCls : 'icon-user'
								}]
							});
						} else {
							if (!selecttree.tree('find', node.id)) { return ; }	//如果不已经存在-不继续添加
							selecttree.tree('remove', selecttree.tree('find', node.id).target);
						}
						
						//设置全选选择框
						checkall.removeClass('tree-checkbox0 tree-checkbox1 tree-checkbox2');
						if (usertree.tree('getRoots').length == usertree.tree('getChecked').length) {
							checkall.addClass('tree-checkbox1');
						} else if (usertree.tree('getChecked').length > 0) {
							checkall.addClass('tree-checkbox2');
						} else if (usertree.tree('getChecked').length == 0) {
							checkall.addClass('tree-checkbox0');
						}
					},
					onClick : function(node) {
						var ck = $(node.target).find('.tree-checkbox');
						if (ck.length){
							if (ck.hasClass('tree-checkbox1')){
								//如果已经选择，取消该节点选中
								usertree.tree('uncheck', node.target);
							} else {
								//如果未选中，选中该节点
								usertree.tree('check', node.target);
							}
						}
					}
				});
			}
			
			//选中树
			selecttree.tree({
				//checkbox : true,
				onDblClick : function(node) {
					removeSelectNode(target, node);
				}
			});
			
//			if (opts.value && opts.text) {
//				selecttree.tree
//			}
		}
	};
	
	function retrieveValues(target) {
		var opts = $.data(target, 'combodeptuser').options;
		var selecttree = $.data(target, 'combodeptuser').selecttree;
		var sctnodes = selecttree.tree('getRoots');
		
		var vv = [],ss = [];
		for(var i=0; i<sctnodes.length; i++){
			vv.push(sctnodes[i].id);
			ss.push(sctnodes[i].value);
		}
		$(target).combo('setValues', vv);
		$(target).combo('setTexts', ss);
		
		if (opts.view) {
			$(opts.view).val(ss.join(opts.seperator));
		}
		
		//出发onChoose事件
		if($(target).attr('onChoose')) {
			(function(){
				var dept = [], depts, user, users;
				$(sctnodes).each(function() {
					dept.push({ id : this.id, name : this.value});
				});
				user = users = depts = dept;
				eval($(target).attr('onChoose'));
			}).apply(target, []);
		}
	};
	
	function initData(target) {
		var opts = $.data(target, 'combodeptuser').options;
		var selecttree = $.data(target, 'combodeptuser').selecttree;
		var depttree = $.data(target, 'combodeptuser').depttree;
		
		if (opts.value) {
			var values, texts, data = [];
			values = opts.value.split(opts.separator);
			if (opts.text) {
				texts = opts.text.split(opts.separator);
			}

			for (var i = 0; i < values.length; i ++) {
				if (values[i].substr(0, 1) == 'g') {
					//add dept
					data.push({
						id : values[i],
						text : opts.text?texts[i]:'',
						value : opts.text?texts[i]:'',
						iconCls : 'icon-users'
					});
				} else {
					data.push({
						id : values[i],
						text : opts.text?texts[i]:'',
						value : opts.text?texts[i]:'',
						iconCls : 'icon-user'
					});
				}
			}
			
			selecttree.tree('append', {
				data : data
			});
			
			/*
				if (this.id.substr(0, 1) != 'g') {
				id : node.id,
				text : node.text,
				value : node.username,
				//checked : true,
				iconCls : 'icon-user'
			 */
			
			
		}
	};
	
	$.fn.combodeptuser = function(options, param) {
		if(typeof options == 'string') {
			var method = $.fn.combodeptuser.methods[options];
			if (method) {
				return method(this, param);
			}else {
				return this.combo(options, param);
			}
		}
		
		options = options || {};
		return this.each(function() {
			var state = $.data(this, 'combodeptuser');
			if (state) {
				$.extend(state.options, options);
			} else {
				$.data(this, 'combodeptuser', {
					options : $.extend({}, $.fn.combodeptuser.defaults, $.fn.combodeptuser.parseOptions(this), options)
				});
			}
			create(this);
			initData(this);
		});
	};
	
	function removeSelectNode(target, node) {
		var opts = $.data(target, 'combodeptuser').options;
		var depttree = $.data(target, 'combodeptuser').depttree;
		var usertree = $.data(target, 'combodeptuser').usertree;
		var selecttree = $.data(target, 'combodeptuser').selecttree;
		
		if (typeof node == 'string') {
			node = selecttree.tree('find', node);
		}
		
		if (!node) { return ; }
		
		if (opts.selectType == 'deptuser') {//部门人员混选
			if (node.id.substr(0, 1) == 'g') {
				//取消部门
				depttree.tree('uncheck', depttree.tree('find', node.id.substr(2)).target);	//设置部门未选中时会自动删除当前节点
			} else {
				//取消人员
				var sctuser = usertree.tree('find', node.id);
				if (sctuser) {
					usertree.tree('uncheck', sctuser.target);	//设置人员未选中时会自动删除当前节点
				} else {
					selecttree.tree('remove', node.target);	//如果人员当前树没显示，手动删除当前节点
				}
			}
		} else if (opts.selectType == 'dept') {//选择部门
			depttree.tree('uncheck', depttree.tree('find', node.id).target);	//设置部门未选中时会自动删除当前节点
		} else if (opts.selectType == 'user') {//选择人员
			//取消人员
			var sctuser = usertree.tree('find', node.id);
			if (sctuser) {
				usertree.tree('uncheck', sctuser.target);	//设置人员未选中时会自动删除当前节点
			} else {
				selecttree.tree('remove', node.target);	//如果人员当前树没显示，手动删除当前节点
			}
		}
	};
	
	function getTexts(target){
		var opts = $.data(target, 'combodeptuser').options;
		var combo = $.data(target, 'combo').combo;
		return combo.find('input.combo-text').val().split(opts.separator);
	};
	
	$.fn.combodeptuser.methods = {
		/*
		 * 设置选中部门
		 */
		selectDept : function(jq, deptId) {
			//寻找并选中找到的部门节点
			var depttree = $.data(jq[0], 'combodeptuser').depttree;
			var deptnode = depttree.tree('find', deptId);
			if (deptnode) {
				depttree.tree('select', deptnode.target);
			}
		},
		removeSelect : function(jq, nodeId) {
			removeSelectNode(jq[0], nodeId);
			retrieveValues(jq[0]);
		},
		getTexts: function(jq){
			return getTexts(jq[0]);
		}
	};
	
	$.fn.combodeptuser.parseOptions = function(target) {
		return $.extend({}, $.fn.combo.parseOptions(target), $.parser.parseOptions(target, ['selectType','view','bureauCode','defaultDept','iconCls']));
	};
	
	$.fn.combodeptuser.defaults = $.extend({}, $.fn.combo.defaults, {
		editable : false,
		idField : 'userid',
		textField : 'username',
		panelWidth : 600,
		panelHeight : 350,
		multiple : true,
		selectType : 'deptuser',
		bureauCode : null,
		defaultDept : null,
		iconCls : null,
		view : null
	});
})(jQuery);

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

/**
 * combodict - plui
 * 
 * 字典选择列表
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

/**
 * comborole - plui
 * 
 * Dependencies:
 *   combo
 *   tree
 *   layout
 */
(function($){
	
	$.parser.plugins.push('comborole');
	
	function create(target){
		var opts = $.data(target, 'comborole').options;
		
		$(target).addClass('comborole-f');
		var combotreelist = $(target).combotreelist(opts);
		
		$.data(target, 'comborole', $.data(combotreelist,'combotreelist'));
	}
	
	$.fn.comborole = function(options, param){
		if (typeof options == 'string'){			
			var method = $.fn.comborole.methods[options];
			if (method)
				return method(this, param);
			else
				return this.combotreelist(options, param);			
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'comborole');
			if (state){
				$.extend(state.options, options);
			} else {
				$.data(this, 'comborole', {
					options: $.extend(true, {}, $.fn.comborole.defaults, $.fn.comborole.parseOptions(this), options)
				});
			}
			create(this);
		});
	};
	
	$.fn.comborole.methods = {
	};
	
	$.fn.comborole.parseOptions = function(target){
		return $.parser.parseOptions(target);
	};
	
	$.fn.comborole.defaults = $.extend(true,{},$.fn.treelist.defaults,$.fn.combo.defaults,{
		layout:{
			fit: true,
			west:{
				title: '产品树'
			},
			center:{
				title: '数据表'
			}
		},
		tree:{
			idField: 'product_id',						
			url : $.pluiplugin.config.productTreeUrl
		},
		datagrid:{
			idField: 'role_id',
			textField: 'role_name',
			fit : true,
			singleSelect : true,
			fitColumns : true,
			url : $.pluiplugin.config.roleByProductUrl,
			pagination : true,
			queryParams : {
				product_id : '-1'
			},
			columns : [[
				{ title : '角色名称', field : 'role_name', width : 100 },
				{ title : '角色代码', field : 'role_code', width : 100 },
				{ title : '所属产品', field : 'product_name', width : 100 },
				{ title : '备注', field : 'remark', width : 300 }
			]]
		},
		leftWidth: 300,
		width:200,
		panelWidth: 800,
		panelHeight: 500		
	});
	
})(jQuery);

