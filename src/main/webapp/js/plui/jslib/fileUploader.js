(function($){
	
	$.parser.plugins.push('fileuploader');	
	
	function initFileList(options, datagrid, targetId){	
		datagrid.datagrid($.extend({},{
			//表格尺寸是否自适应父容器
			fit:false,
			queryParams: {
				superPath : options.superPath,
                subPath : options.subPath,
                busiTable : options.busiTable,
                busiId : options.busiId,
                userName : options.userName,
                userId : options.userId,
                element : options.element
			},
			url: $.fn.fileuploader.servlets.get,
			//表格每列宽度自动适应表格总宽度
			fitColumns: true,
			//是否在最左增加一列显示行号的列
			rownumbers:false,
			//记录主键值所在列字段。必选项。
			idField: 'uploadId',
			//是否动画展开/折叠树节点
			animate:false,
			//表格的行是否交替显示不同背景色					
			striped:true,
			//只允许单选一行
			singleSelect:false,
			checkOnSelect:false,
			selectOnCheck:false,
			//列属性设置
			columns:[[
				//每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
				{field:'uploadId', hidden: true },
				{field:'ck', checkbox: true, formatter: function(value, row){
					if (row.uploading)
						return '';
					else
						return 'disabled';
				}},
				{field:'fileName', title:'文件名', width:300},
				{field:'fileSize', title:'大小', width:150, formatter: getNiceFileSize},
				{field:'ope', title:'操作/上传进度', width:200, formatter: function(value, row, index){
					if (row.uploading)
						return '<span class="progress_bar_span" style="width:'+ options.progressBarLength +'px;" id="'+ row.uploadId +'_progress"><label style="margin-left:5px;"></label></span>' + 
						'<div class="upload_speed">0%</div>';
					else {
						download = '<a href="'+$.fn.fileuploader.servlets.down+'&uploadId='+row.uploadId
						+'&busiTable='+options.busiTable+'&busiId='+options.busiId
						+'" class="down_btn" title="下载文件"></a>';							
						delet = '<a href="#" onClick="$(\'#'+targetId+'\').fileuploader(\'del\',\''+row.uploadId+'\');'
						+'" class="del_btn" title="删除文件"></a>';
						return download + delet;
					}
				}}
			]]
		},options.datagrid));
	}
	
	/**
	 * create the combodept component.
	 */
	function create(target){
		var opts = $.data(target, 'fileuploader').options;
		var button = $.data(target, 'fileuploader').button;
		var panel = $.data(target, 'fileuploader').panel;
		var datagrid = $.data(target, 'fileuploader').datagrid;
		var uploadify = $.data(target, 'fileuploader').uploadify;
		
		$(target).addClass('fileuploader-f');
		
		var outer = $(target);	
		
		//以下判断是为了判断组件是否已经生成过。如果生成过，就不需要向目标标签中添加子元素标签了。
		//否则，需要向目标标签中添加代表layout（整体布局）、content（右边文件内容）、filelist（左边文件列表）的标签						
		
		button = outer.linkbutton(opts);
		
		if (!panel){
			panel = $('<div class="upload_list_panel"><div class="toolbar"></div></div>').appendTo('body');			
		}		
                
		panel.panel($.extend(true, {},opts.panel,{
			content: '',
			style: {
				position: 'absolute',
				"z-index": 9999,
				left: opts.left|| (outer.offset().left + opts.leftOffset||0),
				top: opts.top||(outer.offset().top+outer.get(0).offsetHeight + (opts.topOffset||0))	
			}
		}));		
		
		var toolbar = $(panel.panel('body')).find("div.toolbar");
		var uploadbtn = toolbar.find(".upload_btn");
        if (!uploadify){
        	uploadify = $('<div id="uploadify_'+ Math.floor(1000000000000 * Math.random()) +'" class="uploadify-button"></div>').appendTo(toolbar);        
        	uploadbtn = $('<div id="uploadBtn" class="upload_btn" onClick="$(\'#' + target.id + '\').fileuploader(\'upload\');"></div>').appendTo(toolbar);
        	$('<div style="clear:both;height:0px;"></div>').appendTo(toolbar);
		}
		if (!datagrid)
			datagrid = $('<table class="filelist"></table>').appendTo(panel.panel('body'));
		
		initFileList(opts, datagrid, target.id);
		
		button.toggle(function(){
			datagrid.datagrid('reload');
			panel.panel('open');
		},function(){
			panel.panel('close');
		});	
		
		panel.panel('close');				
		
        uploadify.uploadify({        	
			progressData:'all',// 'percentage''speed''all'//队列中显示文件上传进度的方式：all-上传速度+百分比，percentage-百分比，speed-上传速度
			auto:opts.auto||false,//文件选择完成后，是否自动上传
			height: 20,//上传按钮的高和宽
			width: 78,
			queueSizeLimit:opts.queueSizeLimit||999,//文件上传个数
			buttonImage: $.contextPath + '/plui2/images/upload/selbtn.gif',
			swf: $.contextPath + '/plui2/extend/uploadify.swf',//[必须设置]swf的路径
			uploader: $.fn.fileuploader.servlets.uploader,//[必须设置]上传文件触发的url
			cancelImg: $.contextPath + '/plui2/images/upload/uploadify-cancel.png',//[必须设置]取消图片的路径
			buttonText: '选择图片',//上传按钮的文字
			fileTypeExts: opts.fileType||'*',//允许上传的文件类型，限制弹出文件选择框里能选择的文件
			fileTypeDesc:'上传的必须是'+opts.fileType||'*',//允许上传的文件类型的描述，在弹出的文件选择框里会显示
			fileSizeLimit: opts.limit||'0',//上传文件的大小限制 KB或者MB
			removeCompleted: true, //上传成功后的文件，是否在队列中自动删除
			//上传进度发生变更时触发
			onUploadProgress : function(file,fileBytesLoaded,fileTotalBytes,queueBytesLoaded,swfuploadifyQueueUploadSize) {
				var progressbox=$(panel.panel('body')).find("#"+file.id+"_progress").parent();
				var sche = progressbox.find('label');
				var speed = fileBytesLoaded / fileTotalBytes;
				progressbox.find(".upload_speed").html(Math.ceil(speed * 100) + "%");
				sche.css("width",parseInt(opts.progressBarLength * speed));//91为总长度				
			},
			onDialogClose:function(fq){//当文件选择对话框关闭时触发
				
			},
			onQueueComplete:function(stats){//当队列中的所有文件全部完成上传时触发
				opts.onQueueComplete(stats);
				refreshList(target);
			},
			onUploadSuccess : function(file,data,response){//每一个文件上传成功后保存文件地址		
				eval("o="+data);
				datagrid.datagrid("updateRow",{
					index: datagrid.datagrid("getRowIndex", file.id),					
					row: {
						uploadId: o.uploadId,
						fileName: file.name,
						fileSize: file.size,
						uploading: false
					}
				});				
				opts.onUploadSuccess(o);
			},
			onSelect:function(file){//当每个文件添加至队列后触发
				datagrid.datagrid("appendRow",{
					uploadId: file.id,
					fileName: file.name,
					fileSize: file.size,
					uploading: true
				});
				datagrid.datagrid("checkRow", datagrid.datagrid("getRowIndex",file.id));
				opts.onSelect(file);
			},
			onUploadStart:function(file){//设置自定义参数提交后台
				var autopath = "/";
				if (opts.superPath)
					autopath += opts.superPath+'/';
				autopath += opts.busiTable+'/'+opts.busiId+'/';
				if (opts.subPath)
					autopath += opts.subPath+'/';
				uploadify.uploadify("settings", "formData",{
					relativePath:opts.relativePath||autopath,//  文件保存的相对路径，如果不设置相对路径默认为"/{superPath}/{busiTable}/{busiId}/{subPath}"
					superPath:opts.superPath,
					subPath:opts.subPath,
					busiTable:opts.busiTable,//业务表
					busiId:opts.busiId,//业务ID
					element: opts.element,
					viewAble:opts.viewAlbe
				});  
				opts.onBeforeUpload(file);
			}  
		});       
        
		$.data(target, 'fileuploader').button = button;		
		$.data(target, 'fileuploader').panel = panel;	
		$.data(target, 'fileuploader').datagrid = datagrid;	
		$.data(target, 'fileuploader').uploadify = uploadify;	
	}
	
	$.fn.fileuploader = function(options, param){
		if (typeof options == 'string'){
			var method = $.fn.fileuploader.methods[options];			
			if (method)
				//当参数sub没赋值时，调用linkbutton的方法
				return method(this, param);			
			else 				
				return this.linkbutton(options, param);			
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'fileuploader');
			if (state){
				$.extend(state.options, options);				
			} else {
				state = $.data(this, 'fileuploader', {
					options: $.extend(true, {}, $.fn.fileuploader.defaults, $.fn.fileuploader.parseOptions(this), options)
				});
			}
			create(this);
		});

	};
	
	function download(target, params){
		var url = $.fn.fileuploader.servlets.down+'&uploadId='+id;
		var tmpForm = $('<form />').appendTo($('body'));
		tmpForm.attr({method : 'post', action : url, target : '_self'});
		for (var name in params){
			var hidInput = $('<input type="hidden" />').appendTo(tmpForm);
			hidInput.attr({
				name : name, 
				value : params[name]
			});
		}
		tmpForm.trigger('submit');
		$('body').remove(tmpForm);
	}
	
	function upload(target, param){
		var uploadify = $.data(target, 'fileuploader').uploadify;
		var datagrid = $.data(target, 'fileuploader').datagrid;
		var ids = [];
		if (!param){
			var rows = datagrid.datagrid("getChecked");
			for (var j=0;j<rows.length;j++)
				ids[j] = rows[j].uploadId;			
		} else if (typeof param == "string") 
			ids = [param];
		else if (param instanceof Array) 
			ids = param;
		else
			throw new Exception("参数错误！参数只能为单个id的字符串或者复数id的字符串数组！");
		eval('uploadify.uploadify("upload","'+ids.join('","')+'");');
	}
	
	function del(target, params){
		var opts = $.data(target, 'fileuploader').options;
		var param;
		var id;
		if (typeof params == "string"){
			param = {
				uploadId: params,
				busiTable: opts.busiTable,
				busiId: opts.busiId
			};
			id = params;
		}
		else {
			param = params;
			id = param.id;
		}
		opts.onBeforeDelete(id);
		$.ajax({
			url: $.fn.fileuploader.servlets.del,
			dataType: 'json',
			data: param,
			success: function(result){
				refreshList(target);
			}
		});
		opts.onDeleteSuccess(id);
	}
	
	function refreshList(target){
		var datagrid = $.data(target, 'fileuploader').datagrid;
		datagrid.datagrid('reload');
	}
	
	function getNiceFileSize(bitnum){
		var _K = 1024;
		var _M = _K * 1024;
	    if (bitnum < _M) {
	        if (bitnum < _K) {
	            return bitnum + 'B';
	        }
	        else {
	            return Math.ceil(bitnum / _K) + 'K';
	        }
	        
	    }
	    else {
	        return Math.ceil(100 * bitnum / _M) / 100 + 'M';
	    }
	}
	
	$.fn.fileuploader.methods = {
		panel: function(jq){
			return $.data(jq[0], 'fileuploader').panel;			
		},
		options: function(jq){
			return $.data(jq[0], 'fileuploader').options;
		},
		del: function(jq, param){
			del(jq[0], param);
		},
		download: function(jq, param){
			download(jq[0], param);	
		},
		upload: function(jq, param){
			upload(jq[0], param);
		}
	};
	
	$.fn.fileuploader.parseOptions = function(target){
		return $.parser.parseOptions(target);
	};
	
	$.fn.fileuploader.defaults = {
		panel: {
			border: true,
			title: "文件管理"
		},
		datagrid: {
			border: false
		},
		progressBarLength: 91,
		
		onQueueComplete:function(stats){//当队列中的所有文件全部完成上传时触发
			
		},
		onUploadSuccess : function(file){//每一个文件上传成功后触发
			
		},
		onSelect:function(file){//当每个文件添加至队列后触发
			
		},
		onBeforeUpload:function(file){//
			  
		}, 
		onBeforeDelete:function(id){//
			  
		},
		onDeleteSuccess:function(id){
			
		}
	};
	
	$.fn.fileuploader.servlets = {
		uploader:$.contextPath+ '/commons/plui/upload.file?method=upload',//[必须设置]上传文件触发的url
		del:$.contextPath+'/commons/plui/upload.file?method=remove',
		sort:$.contextPath+'/commons/plui/upload.file?method=adjust',
		get:$.contextPath+'/commons/plui/upload.file?method=uploadResult',
		down:$.contextPath+'/commons/plui/upload.file?method=download',
	};
	
})(jQuery);

