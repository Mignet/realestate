/**
 * plui
 * plui���ڴ򿪵ȶ������
 */

/**
 * �򿪴���
 * @deprecated �Ƽ�ʹ��openInTopWindow
 */
function openWindow_dep(title, url, width, height, args, opts) {
	opts = opts || {};
	args = args || {};
	(function($) {
		var win = $('<div />').appendTo($(top.document.body));
		win.window($.extend({
			title : title,
			href : url,
			type : 'frame',
			args : $.extend({}, args, {
				opener : win,
				window : window
			}),
			width : width,
			height : height,
			minimizable : false,
	    	maximizable : false,
	    	collapsible : false,
	    	modal : true
		}, opts, {
			onClose : function() {
				if (opts.onClose) {
					opts.onClose.call(win, arguments);
				}
				win.window('destroy');
			}
		}));
	})(top.$);
};

/**
 * ���㴰���д�Window
 * @param options
 */
function openInTopWindow(options) {
    $(top.document.body).append('<div id="' + options.id + '" style=" overflow:hidden;"><iframe scrolling="auto" id="' + options.id + 'Frame" name="' + options.id + 'Frame" frameborder="0"  src="' + options.src + '" style="width:100%;height:100%;"></iframe></div>');
    iframeLoaded(top.document.getElementById(options.id + 'Frame'), options.onLoad);
    if (options.destroy) {
        options.onClose = function () {
            top.$(this).window('destroy');
        };
    }
    top.$('#' + options.id).window($.extend({
    	minimizable : false,
    	maximizable : false,
    	collapsible : false,
    	modal : true
    }, options, {
    	
    }));
    top.$('#' + options.id).window('open');
    //if(navigator.userAgent.indexOf("Firefox")>0)topWindow.document.getElementById(options.id+'Frame').contentWindow.location=options.src;
    //else window.eval('topWindow.'+options.id+'Frame.location=\''+options.src+'\';');
};
function closeInTopWindow(winID,type){
	if(!type)type='close';
	//if(typeof topWindow=='undefined')getTopWindow();//��ȡ����һ��PAGE��WINDOW����
	top.$('#' + winID).window(type);
};

//iframe������֮��ص��� 
function iframeLoaded(iframeEl, callback) {      
    //����ͬ������򿪵�onload�¼�
	
	$(iframeEl).on('load', function() {
		var fwin = iframeEl.contentWindow;
		fwin['windowFrom']=window; 
        window[iframeEl.id]=fwin; 
           
        if (callback && typeof (callback) == "function") { 
            callback.call(fwin); 
            iFrameHeight(iframeEl.id); 
        }
	});
	
	/*
    if (iframeEl.attachEvent) { 
        iframeEl.attachEvent("onload", function () { 
            //������ǰ���ںͱ��򿪴��ڵĹ����� 
            window.eval('var fwin=top.' + iframeEl.id); 
            fwin['windowFrom']=window; 
            window[iframeEl.id]=fwin; 
               
            if (callback && typeof (callback) == "function") { 
                //fwin ��iframe�е�window���� 
                //window.eval('var fwin=topWindow.' + iframeEl.id); 
                callback.call(fwin); 
                iFrameHeight(iframeEl.id); 
            } 
        }); 
    } else { 
        iframeEl.onload = function () { 
            //������ǰ���ںͱ��򿪴��ڵĹ����� 
            window.eval('var fwin=top.' + iframeEl.id); 
            fwin['windowFrom']=window; 
            window[iframeEl.id]=fwin; 
               
            if (callback && typeof (callback) == "function") { 
                //window.eval('var fwin=topWindow.' + iframeEl.id); 
                callback.call(fwin); 
                iFrameHeight(iframeEl.id); 
            } 
        }
    }
    */
};

function iFrameHeight(id) {
    var ifm = top.document.getElementById(id);
    var subWeb = document.frames ? top.document.frames[id].document : ifm.contentDocument;
    if (ifm != null && subWeb != null) {
        ifm.height = subWeb.body.scrollHeight;
		id=id.substring(0,id.length-5);
        if (subWeb.body.scrollHeight > top.document.getElementById(id).offsetHeight) {
            top.document.getElementById(id).style.overflowY = 'auto';
        }
    }
};

function getTopWindow(doc) {
    if (!doc) doc = window;
    if (doc != doc.parent) {
        getTopWindow(doc.parent);
    } else {
        window.topWindow = doc;
    }
};
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
						$(target).window('close');
					},
					title:'����'
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

var path=getScriptPath(/plui\.core\.js(\W|$)/i);
loadJs(path+'plui.extend.js');
