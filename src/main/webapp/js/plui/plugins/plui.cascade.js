//联动选择组件
(function($){
	var levelCH = ['一','二','三','四','五','六','七','八','九'];
	/**
	*创建联动选择组件
	*/
	function create(target){
		var state = $.data(target, 'cascade');
		var panelOpts = {width:state.options.panelWidth||150,height:state.options.panelHeight||250};
		//将普通面板添加
		state.panels = [];
		var panelIndex = state.options.level;
		var titles = state.options.titles||[];
		//列表框
		var url = state.options.dataUrl;
		var treeList =  addTreeList(target,panelOpts,url);
		treeList.attr("panelIndex",0);
		treeList.appendTo($(target));
		treeList.panel($.extend({},panelOpts,{
			title:titles[0]?titles[0]:'第'+levelCH[0]+'级列表'
		}));
		//添加除了第一个之外的菜单框
		for(var i = 1 ; i<panelIndex;i++){
			treeList = addTreeList(target,panelOpts);
			treeList.attr("panelIndex",i);
			treeList.appendTo($(target));
			treeList.panel($.extend({},panelOpts,{
				title:titles[i]?titles[i]:('第'+levelCH[i]+'级列表')
			}));
		}
		//已选择框
		var selectedList = $('<div>');
		selectedList.css({width:panelOpts.width,heigth:panelOpts.height,float:'left'});
		selectedList.appendTo($(target));
		var selectedTree = $('<div>');
		state.selectedTree = selectedTree;
		selectedTree.tree({
			onDblClick:function(node){
				deleteSelect(target,node);
			}
		});
		selectedTree.appendTo(selectedList);
		selectedList.panel($.extend({},panelOpts,{
			title:'已选项',
			tools: [{
					iconCls:'icon-save',
					handler:function(){
						var nodes = selectedTree.tree('getRoots');
						state.options.onSubmit?state.options.onSubmit(nodes):'';
					}
				},{  
				iconCls:'icon-remove', 
				title:'移除所有选项', 
				handler:function(){
					if(!confirm('确定删除所有选项？')){
						return;
					}
					var nodes = state.selectedTree.tree('getRoots');
					if(nodes){
						for(var i=0;i<nodes.length;i++){
							selectedTree.tree('remove',nodes[i].target);
						}
					}
				}  
			}]  
		}));
		var cas = $(target).window($.extend({},state.options,{
			title:'联动选择',
			width:panelOpts.width*(panelIndex+1)+32,
			height:panelOpts.height + 40
		}));
		$(target).find('.panel').css({'float':'left'});
	}
	
	/**
	*用于添加选择项
	*/
	function addTreeList(target,panelOpts,url){
		var treeList = $('<div>');
		var state = $.data(target, 'cascade');
		state.panels.push(treeList);
		treeList.css({width:panelOpts.width,heigth:panelOpts.height,float:'left'});
		if(!url || url == '')return treeList
		loadSubList(target,treeList,url);
		return treeList;
	}
	
	/**
	*加载数据列表
	*/
	function loadSubList(target,panel,url){
		var state = $.data(target, 'cascade');
		panel.html("数据加载中……");
		$.getJSON(url,function(d){
			var tree = $('<ul>');
			for(var i in d){
				if(!d[i].attributes || !d[i].attributes.url || d[i].attributes.url == ""){
					d[i].iconCls= 'icon-field';
				}else{
					d[i].iconCls= 'icon-folder';
				}
			}
			panel.html('');
			tree.appendTo(panel);
			tree.tree({
				data:d,
				onClick:function(node){
					if(node.attributes && node.attributes.url){
						var index = $(panel).attr('panelIndex');
						if(state.panels.length ){
							
						}
						loadSubList(target,state.panels[parseInt(index)+1],node.attributes.url);
					}
				},
				onDblClick:function(node){
					addSelect(target,node);
				}
			});
		});
	}
	
	/**
	*添加选择项
	*/
	function addSelect(target,node){
		var state = $.data(target, 'cascade');
		var nodes = state.selectedTree.tree('getRoots');
		var flag = true;
		if(node.attributes && (node.attributes.url || nodes.attributes.url=='')){
			return;
		}
		if(nodes){
			for(var i=0;i<nodes.length;i++){
				if(nodes[i].id == node.id){
					flag = false;
				}
			}
		}
		if(flag){
			state.selectedTree.tree('append',{parent:null,data:[node]});
		}
	}
	
	/**
	*删除选择项
	*/
	function deleteSelect(target,node){
		var state = $.data(target, 'cascade');
		var nodes = state.selecteds;
		state.selectedTree.tree('remove',node.target);
	}
	
	//隐藏联动选择
	function close(target){
		$(target).window('');
	}
	
	//打开联动选择
	function open(target){
		$(target).window('');
	}
	
	/**
	*联动选择组件构造函数
	*/
	$.fn.cascade = function(options, param){
		if (typeof options == 'string'){
			var method = $.fn.cascade.methods[options];
			if (method){
				return method(this, param);
			} else {
				return this.panel(options, param);
			}
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'cascade');
			if (state){
				$.extend(state.options, options);
			} else {
				state = $.data(this, 'cascade', {
					options: $.extend({}, $.fn.cascade.defaults, $.fn.cascade.parseOptions(this), options)
				});
				if (!state.options.inline){
					document.body.appendChild(this);
				}
			}
			create(this);
		});
	}
	
	$.fn.cascade.parseOptions = function(target){
		return $.extend({}, $.fn.panel.parseOptions(target),$.fn.window.parseOptions(target), $.parser.parseOptions(target, [
			{}
		]));
	};
	/**
	*联动选择组件方法定义
	*/
	$.fn.cascade.methods = {};
	
	$.fn.cascade.defaults ={
		width:400,
		height:300
	};
})(jQuery);