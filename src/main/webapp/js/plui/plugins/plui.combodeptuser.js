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
		$.extend(opts.param, opts.rootDept?{rootDept:opts.rootDept}:{});
		
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
		return $.extend({}, $.fn.combo.parseOptions(target), $.parser.parseOptions(target, ['selectType','view','bureauCode','defaultDept','iconCls', 'rootDept']));
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
		rootDept : null,
		defaultDept : null,
		iconCls : null,
		view : null
	});
})(jQuery);