(function($){
	
	$.parser.plugins.push('filereader');
	
	/**
	 * create the combodept component.
	 */
	function create(target){
		var opts = $.data(target, 'filereader').options;	
		var layout = $.data(target, 'filereader').layout;
		var filelist = $.data(target, 'filereader').filelist;
		var content = $.data(target, 'filereader').content;
		
		$(target).addClass('filereader-f');
		
		var outer = $(target);
		
		//以下判断是为了判断组件是否已经生成过。如果生成过，就不需要向目标标签中添加子元素标签了。
		//否则，需要向目标标签中添加代表layout（整体布局）、content（右边文件内容）、filelist（左边文件列表）的标签						
		
		if (!layout){
			if (opts.header)
				$('<div id="top" data-options="region:\'north\',href:\''+ opts.headerUrl +'\',split:false" border=no style="height:'+ opts.headerHeight +'px;overflow:hidden;"></div>').appendTo(outer);
			$('<div id="leftfilelist" data-options="region:\'west\',split:true" title="文件列表" style="width:' + opts.listWidth + 'px;"></div>').appendTo(outer);
			$('<div data-options="region:\'center\',split:true"><div id="fcontent_tab"></div></div>').appendTo(outer);
			if (opts.footer)
				$('<div data-options="region:\'south\',split:false,href:\'south.jsp\'" border=no style="height:15px;"></div>').appendTo(outer);

			layout = outer.layout({
				fit:true
			});	
			$.data(target, 'filereader').layout = layout;
		}
		
		if (!filelist){
			var left = $($.fn.filereader.listDefaults[opts.listType].tag).appendTo("#leftfilelist");
			//filelist = left[opts.listType]($.extend(true, {}, $.fn.filereader.listDefaults[opts.listType].options, opts.listOptions));
			filelist = left[opts.listType]($.fn.filereader.listDefaults[opts.listType].getOptions(opts));
			$.data(target, 'filereader').filelist = filelist;
		}
		
		if (!content){
			content = $('#fcontent_tab')[opts.viewType]($.extend(true, {}, $.fn.filereader.viewDefaults[opts.viewType].options, opts.viewOptions));
			$.data(target, 'filereader').content = content;
		}	
		
		filelist[opts.listType]({
			onSelect : function(){
				$.fn.filereader.viewDefaults[opts.viewType].onSelect(arguments,content,opts);
			}/*,
			onClick: function(){
				$.fn.filereader.viewDefaults[opts.viewType].onClick(arguments,content,opts);
			},
			onClickRow: function(){
				$.fn.filereader.viewDefaults[opts.viewType].onClick(arguments,content,opts);
			}*/
		});

		
		//默认指定打开某个文件
		if (opts.defaultFile) {
			$.fn.filereader.listDefaults[opts.listType].selectFile(opts.defaultFile, filelist);
		} else {
			//只有一个文件时，选择该文件
			if (opts.listOptions.data.length == 1) {
				if (!opts.listOptions.data[0].children) {
					$.fn.filereader.listDefaults[opts.listType].selectFile(opts.listOptions.data[0].id, filelist);
				}
			}
		}
	};
	
	/**
	 * 合并文件信息,并将数据编码为URL字符串（两次encodeURL）解决中文传输乱码问题
	 * 将文件全局信息合并到文件数据下（设置了全局权限时，需要进行合并）
	 */
	function mergeFileInfoToURLString(node, options) {
		var permission = node.permission || '';
		permission += options.permission;
		node.permission = permission;
		delete node.text;
		return encodeURI(encodeURI($.toJSON(node)));
	};
	
	$.fn.filereader = function(options, param){
		if (typeof options == 'string'){
			var method = $.fn.filereader.methods[options];			
			if (method)
				//当参数sub没赋值时，调用sorting的方法
				return method(this, param);			
			else 				
				return this.layout(options, param);			
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'filereader');
			if (state){
				$.extend(state.options, options);				
			} else {
				state = $.data(this, 'filereader', {
					options: $.extend(true, {}, $.fn.filereader.defaults, $.fn.filereader.parseOptions(this), options)
				});
			}
			create(this);
		});

	};
	
	$.fn.filereader.methods = {
		layout: function(jq){
			return $.data(jq[0], 'filereader').layout;			
		},
		filelist: function(jq){
			return $.data(jq[0], 'filereader').filelist;			
		},
		content: function(jq){
			return $.data(jq[0], 'filereader').content;			
		},
		options: function(jq){
			return $.data(jq[0], 'filereader').options;
		}
	};
	
	$.fn.filereader.parseOptions = function(target){
		return $.parser.parseOptions(target);
	};
	
	$.fn.filereader.defaults = {
		layout: $.fn.layout.defaults,
		header: true,
		footer: true,
		viewType: 'tabs',
		viewOptions: {},
		listType: 'tree',
		listOptions: {},
		headerUrl: 'north.jsp',
		headerHeight: 56,
		listWidth : 200,
		permission : ''	//权限，C复制、P打印
	};
	/*
	function FileDef(url, mime){
		this.url = url;
		this.mime = mime;
	}
	
	FileDef.prototype.url =  "/plui2/webviewer/inline/view.jsp";
	FileDef.prototype.mime =  "application/octet-stream";
	*/
	$.fn.filereader.fileTypes = { 
		doc : "/plui2/webviewer/office/view.jsp", 
		docx : "/plui2/webviewer/office/view.jsp", 
		xls : "/plui2/webviewer/office/view.jsp", 
		xlsx : "/plui2/webviewer/office/view.jsp", 
		ppt : "/plui2/webviewer/office/view.jsp", 
		pptx : "/plui2/webviewer/office/view.jsp", 
		pdf : "/plui2/webviewer/pdf/view.jsp", 
		swf : "/plui2/webviewer/swf/view.jsp", 
		tif : "/plui2/webviewer/tif/view.jsp",
		ceb : "/plui2/webviewer/inline/view.jsp",
		txt : "/plui2/webviewer/inline/view.jsp",
		jpg : "/plui2/webviewer/inline/view.jsp"
	};
	
	function processTreeData(data, opts) {
		$.each(data, function(idx, itm) {
			
			if (itm.children) {
				processTreeData(itm.children, opts);
				if (itm.fileName) {
					itm.text = itm.fileName;
				}
			} else {
				//设置图标
				itm.iconCls = 'icon-' + itm.fileExt.toLowerCase();
				if ((opts.permission && opts.permission.indexOf('D') != -1) || (itm.permission && itm.permission.indexOf('D') != -1)) {
					var url = $.filereader.contextPath + '/' + $.filereader.actionURL + '?method=download&' + jQuery.param($.extend({}, itm, {fileName:encodeURI(encodeURI(itm.fileName)), param:encodeURI(encodeURI(itm.param))}));
					itm.text = itm.fileName + '  <a href="'+url+'" onclick="event.cancelBubble = true;" title="下载文件" class="down_btn" />';
				}else {
					itm.text = itm.fileName;
				}
			}
		});
	};
	
	$.fn.filereader.listDefaults = {
		tree: { 
			tag: '<ul></ul>',
			options: $.extend({},$.fn.tree.defaults, {		
			}), 
			getOptions : function(opts) {
				processTreeData(opts.listOptions.data, opts);
				return $.extend(true, {}, $.fn.filereader.listDefaults[opts.listType].options, opts.listOptions);
			},
			selectFile : function(id, filelist) {
				filelist.tree('select', filelist.tree('find', id).target);
			}
		},
		datagrid: { 
			tag: '<div></div>',
			options: $.extend({},$.fn.datagrid.defaults, {
				border : false
			}), 
			getOptions : function(opts) {
				opts.listOptions.columns[0].push({
					title:'', field:'id', width:20,
					formatter : function(value, row, index) {
						if ((opts.permission && opts.permission.indexOf('D') != -1) || (row.permission && row.permission.indexOf('D') != -1)) {
							var url = $.filereader.contextPath + '/' + $.filereader.actionURL + '?method=download&' + jQuery.param($.extend({}, row, {fileName:encodeURI(encodeURI(row.fileName)), param:encodeURI(encodeURI(row.param))}));
							return '<a href="'+url+'" onclick="event.cancelBubble = true;" title="下载文件" class="down_btn" />';
						}
						return '';
					}
				});
				return $.extend(true, {}, $.fn.filereader.listDefaults[opts.listType].options, opts.listOptions);
			},
			selectFile : function(id, filelist) {
				filelist.datagrid('selectRecord', id);
			}
		},
		treegrid: { 
			tag: '<div></div>',
			options: $.extend({},$.fn.treegrid.defaults, {
				border : false
			}), 
			getOptions : function(opts) {
				$.each(opts.listOptions.data, function(idx, itm) {
					//设置图标
					itm.iconCls = 'icon-' + itm.fileExt.toLowerCase();
				});
				opts.listOptions.columns[0].push({
					title:'', field:'id', width:20,
					formatter : function(value, row, index) {
						if ((opts.permission && opts.permission.indexOf('D') != -1) || (row.permission && row.permission.indexOf('D') != -1)) {
							var url = $.filereader.contextPath + '/' + $.filereader.actionURL + '?method=download&' + jQuery.param($.extend({}, row, {fileName:encodeURI(encodeURI(row.fileName)), param:encodeURI(encodeURI(row.param))}));
							return '<a href="'+url+'" onclick="event.cancelBubble = true;" title="下载文件" class="down_btn" />';
						}
						return '';
					}
				});
				return $.extend(true, {}, $.fn.filereader.listDefaults[opts.listType].options, opts.listOptions);
			},
			selectFile : function(id, filelist) {
				filelist.treegrid('select', id);
			}
		}
	};
	
	$.fn.filereader.viewDefaults = {
		tabs: {
			onSelect : function(args, view, options) {
				var node = args.length > 1 ? node = args[1] : args[0];
				
				if (node.children) { return; };
				
				if (view.tabs('exists', node.fileName)) {
					view.tabs('select', node.fileName);
				} else {
					view.tabs('add', {
						title : node.fileName,
						type : 'frame',
						href : $.filereader.contextPath + $.fn.filereader.fileTypes[node.fileExt] + '?fileInfo=' + mergeFileInfoToURLString(node, options),
						closable:true
					});
				}
			},
			options: $.extend({}, $.fn.tabs.defaults, {
				fit : true,
				border : false
			})
		},
		panel: {
			onSelect : function(args, view, options){
				var node = args.length > 1 ? node = args[1] : args[0];
				
				if (node.children) { return; };

				view.panel({
					href: $.filereader.contextPath + $.fn.filereader.fileTypes[node.fileExt] + '?fileInfo=' + mergeFileInfoToURLString(node, options)
				});
			},
			options: $.extend({}, $.fn.panel.defaults, {
				fit : true,
				border : false,
				type: 'frame'
			})
		}
	};
})(jQuery);

function openReader(options, target) {

	var data = options.listOptions.data;
	
	var sTarget = target;
	
	var tmpForm = $('<form />').appendTo($('body'));
	if (!sTarget) {
		var dNow = new Date();
		sTarget = 'readerWin' + dNow.getFullYear() + '_' + dNow.getMonth() + '_' + dNow.getDate() + '_' + dNow.getHours() + '_' + dNow.getMinutes() + '_' + dNow.getSeconds();
	}
	tmpForm.attr({method : 'post', action : $.filereader.contextPath + '/plui2/webviewer/container/index.jsp', target : sTarget});
	var hidInput = $('<input type="hidden" />').appendTo(tmpForm);
	hidInput.attr({name : 'options', value : encodeURI($.toJSON((options)))});
	if (!target) {
		tmpForm.on('submit', function(){
			window.open('about:blank', sTarget);
		});
	}
	tmpForm.trigger('submit');
	$('body').remove(tmpForm);

}