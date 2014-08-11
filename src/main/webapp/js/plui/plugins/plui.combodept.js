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