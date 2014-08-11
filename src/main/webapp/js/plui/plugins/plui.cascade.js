//����ѡ�����
(function($){
	var levelCH = ['һ','��','��','��','��','��','��','��','��'];
	/**
	*��������ѡ�����
	*/
	function create(target){
		var state = $.data(target, 'cascade');
		var panelOpts = {width:state.options.panelWidth||150,height:state.options.panelHeight||250};
		//����ͨ������
		state.panels = [];
		var panelIndex = state.options.level;
		var titles = state.options.titles||[];
		//�б��
		var url = state.options.dataUrl;
		var treeList =  addTreeList(target,panelOpts,url);
		treeList.attr("panelIndex",0);
		treeList.appendTo($(target));
		treeList.panel($.extend({},panelOpts,{
			title:titles[0]?titles[0]:'��'+levelCH[0]+'���б�'
		}));
		//��ӳ��˵�һ��֮��Ĳ˵���
		for(var i = 1 ; i<panelIndex;i++){
			treeList = addTreeList(target,panelOpts);
			treeList.attr("panelIndex",i);
			treeList.appendTo($(target));
			treeList.panel($.extend({},panelOpts,{
				title:titles[i]?titles[i]:('��'+levelCH[i]+'���б�')
			}));
		}
		//��ѡ���
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
			title:'��ѡ��',
			tools: [{
					iconCls:'icon-save',
					handler:function(){
						var nodes = selectedTree.tree('getRoots');
						state.options.onSubmit?state.options.onSubmit(nodes):'';
					}
				},{  
				iconCls:'icon-remove', 
				title:'�Ƴ�����ѡ��', 
				handler:function(){
					if(!confirm('ȷ��ɾ������ѡ�')){
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
			title:'����ѡ��',
			width:panelOpts.width*(panelIndex+1)+32,
			height:panelOpts.height + 40
		}));
		$(target).find('.panel').css({'float':'left'});
	}
	
	/**
	*�������ѡ����
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
	*���������б�
	*/
	function loadSubList(target,panel,url){
		var state = $.data(target, 'cascade');
		panel.html("���ݼ����С���");
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
	*���ѡ����
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
	*ɾ��ѡ����
	*/
	function deleteSelect(target,node){
		var state = $.data(target, 'cascade');
		var nodes = state.selecteds;
		state.selectedTree.tree('remove',node.target);
	}
	
	//��������ѡ��
	function close(target){
		$(target).window('');
	}
	
	//������ѡ��
	function open(target){
		$(target).window('');
	}
	
	/**
	*����ѡ��������캯��
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
	*����ѡ�������������
	*/
	$.fn.cascade.methods = {};
	
	$.fn.cascade.defaults ={
		width:400,
		height:300
	};
})(jQuery);