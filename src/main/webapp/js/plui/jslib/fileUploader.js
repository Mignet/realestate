(function($){
	
	$.parser.plugins.push('fileuploader');	
	
	function initFileList(options, datagrid, targetId){	
		datagrid.datagrid($.extend({},{
			//���ߴ��Ƿ�����Ӧ������
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
			//���ÿ�п���Զ���Ӧ����ܿ��
			fitColumns: true,
			//�Ƿ�����������һ����ʾ�кŵ���
			rownumbers:false,
			//��¼����ֵ�������ֶΡ���ѡ�
			idField: 'uploadId',
			//�Ƿ񶯻�չ��/�۵����ڵ�
			animate:false,
			//�������Ƿ�����ʾ��ͬ����ɫ					
			striped:true,
			//ֻ����ѡһ��
			singleSelect:false,
			checkOnSelect:false,
			selectOnCheck:false,
			//����������
			columns:[[
				//ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
				{field:'uploadId', hidden: true },
				{field:'ck', checkbox: true, formatter: function(value, row){
					if (row.uploading)
						return '';
					else
						return 'disabled';
				}},
				{field:'fileName', title:'�ļ���', width:300},
				{field:'fileSize', title:'��С', width:150, formatter: getNiceFileSize},
				{field:'ope', title:'����/�ϴ�����', width:200, formatter: function(value, row, index){
					if (row.uploading)
						return '<span class="progress_bar_span" style="width:'+ options.progressBarLength +'px;" id="'+ row.uploadId +'_progress"><label style="margin-left:5px;"></label></span>' + 
						'<div class="upload_speed">0%</div>';
					else {
						download = '<a href="'+$.fn.fileuploader.servlets.down+'&uploadId='+row.uploadId
						+'&busiTable='+options.busiTable+'&busiId='+options.busiId
						+'" class="down_btn" title="�����ļ�"></a>';							
						delet = '<a href="#" onClick="$(\'#'+targetId+'\').fileuploader(\'del\',\''+row.uploadId+'\');'
						+'" class="del_btn" title="ɾ���ļ�"></a>';
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
		
		//�����ж���Ϊ���ж�����Ƿ��Ѿ����ɹ���������ɹ����Ͳ���Ҫ��Ŀ���ǩ�������Ԫ�ر�ǩ�ˡ�
		//������Ҫ��Ŀ���ǩ����Ӵ���layout�����岼�֣���content���ұ��ļ����ݣ���filelist������ļ��б��ı�ǩ						
		
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
			progressData:'all',// 'percentage''speed''all'//��������ʾ�ļ��ϴ����ȵķ�ʽ��all-�ϴ��ٶ�+�ٷֱȣ�percentage-�ٷֱȣ�speed-�ϴ��ٶ�
			auto:opts.auto||false,//�ļ�ѡ����ɺ��Ƿ��Զ��ϴ�
			height: 20,//�ϴ���ť�ĸߺͿ�
			width: 78,
			queueSizeLimit:opts.queueSizeLimit||999,//�ļ��ϴ�����
			buttonImage: $.contextPath + '/plui2/images/upload/selbtn.gif',
			swf: $.contextPath + '/plui2/extend/uploadify.swf',//[��������]swf��·��
			uploader: $.fn.fileuploader.servlets.uploader,//[��������]�ϴ��ļ�������url
			cancelImg: $.contextPath + '/plui2/images/upload/uploadify-cancel.png',//[��������]ȡ��ͼƬ��·��
			buttonText: 'ѡ��ͼƬ',//�ϴ���ť������
			fileTypeExts: opts.fileType||'*',//�����ϴ����ļ����ͣ����Ƶ����ļ�ѡ�������ѡ����ļ�
			fileTypeDesc:'�ϴ��ı�����'+opts.fileType||'*',//�����ϴ����ļ����͵��������ڵ������ļ�ѡ��������ʾ
			fileSizeLimit: opts.limit||'0',//�ϴ��ļ��Ĵ�С���� KB����MB
			removeCompleted: true, //�ϴ��ɹ�����ļ����Ƿ��ڶ������Զ�ɾ��
			//�ϴ����ȷ������ʱ����
			onUploadProgress : function(file,fileBytesLoaded,fileTotalBytes,queueBytesLoaded,swfuploadifyQueueUploadSize) {
				var progressbox=$(panel.panel('body')).find("#"+file.id+"_progress").parent();
				var sche = progressbox.find('label');
				var speed = fileBytesLoaded / fileTotalBytes;
				progressbox.find(".upload_speed").html(Math.ceil(speed * 100) + "%");
				sche.css("width",parseInt(opts.progressBarLength * speed));//91Ϊ�ܳ���				
			},
			onDialogClose:function(fq){//���ļ�ѡ��Ի���ر�ʱ����
				
			},
			onQueueComplete:function(stats){//�������е������ļ�ȫ������ϴ�ʱ����
				opts.onQueueComplete(stats);
				refreshList(target);
			},
			onUploadSuccess : function(file,data,response){//ÿһ���ļ��ϴ��ɹ��󱣴��ļ���ַ		
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
			onSelect:function(file){//��ÿ���ļ���������к󴥷�
				datagrid.datagrid("appendRow",{
					uploadId: file.id,
					fileName: file.name,
					fileSize: file.size,
					uploading: true
				});
				datagrid.datagrid("checkRow", datagrid.datagrid("getRowIndex",file.id));
				opts.onSelect(file);
			},
			onUploadStart:function(file){//�����Զ�������ύ��̨
				var autopath = "/";
				if (opts.superPath)
					autopath += opts.superPath+'/';
				autopath += opts.busiTable+'/'+opts.busiId+'/';
				if (opts.subPath)
					autopath += opts.subPath+'/';
				uploadify.uploadify("settings", "formData",{
					relativePath:opts.relativePath||autopath,//  �ļ���������·����������������·��Ĭ��Ϊ"/{superPath}/{busiTable}/{busiId}/{subPath}"
					superPath:opts.superPath,
					subPath:opts.subPath,
					busiTable:opts.busiTable,//ҵ���
					busiId:opts.busiId,//ҵ��ID
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
				//������subû��ֵʱ������linkbutton�ķ���
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
			throw new Exception("�������󣡲���ֻ��Ϊ����id���ַ������߸���id���ַ������飡");
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
			title: "�ļ�����"
		},
		datagrid: {
			border: false
		},
		progressBarLength: 91,
		
		onQueueComplete:function(stats){//�������е������ļ�ȫ������ϴ�ʱ����
			
		},
		onUploadSuccess : function(file){//ÿһ���ļ��ϴ��ɹ��󴥷�
			
		},
		onSelect:function(file){//��ÿ���ļ���������к󴥷�
			
		},
		onBeforeUpload:function(file){//
			  
		}, 
		onBeforeDelete:function(id){//
			  
		},
		onDeleteSuccess:function(id){
			
		}
	};
	
	$.fn.fileuploader.servlets = {
		uploader:$.contextPath+ '/commons/plui/upload.file?method=upload',//[��������]�ϴ��ļ�������url
		del:$.contextPath+'/commons/plui/upload.file?method=remove',
		sort:$.contextPath+'/commons/plui/upload.file?method=adjust',
		get:$.contextPath+'/commons/plui/upload.file?method=uploadResult',
		down:$.contextPath+'/commons/plui/upload.file?method=download',
	};
	
})(jQuery);

