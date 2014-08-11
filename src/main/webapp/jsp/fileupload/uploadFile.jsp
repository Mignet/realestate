<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
      <head>
        <title>附件上传</title>
        <style type="text/css">
        	#uploadify{ position:absolute;left:12px; top:37px;}
        </style>
         <%@ include file="/base/taglibs.jsp"%>
		 <%@ include file="/base/prefix.jsp"%>
        <script type="text/javascript">
        
        var ctx = '${ctx}';
        var proc;
        var pluiUpload={};
        
        window.cfg ={
        		viewType:1,
        		system:"深圳市不动产登记系统",
        		module:"module",
        		busiType:"初始登记",
        		busiTable:"bus_opinion",
        		busiId:"1222",
        		userName:"test",
        		//userId:"test",
        		viewAble:false,
        		auto:false,
        		queueSizeLimit:999,
        		fileType:"*",
        		limit:"0"
        };
        
        //初始化
        function init(initData){
        	proc = initData;
        	
        
        	
        	//初始化上传组件配置
        	cfg.busiId=proc.openNode;
        	cfg.busiType = proc.procdefName;
        	cfg.module = proc.procdefName;
        	cfg.busiTable =proc.procId;
        	
        	//初始化上传组件
        	initUpload();
        	
        	setState();
        }
        
        //初始化上传组件
        function initUpload(){
        	pluiUpload={
    				init:function(){
    					this.viewType=cfg.viewType||1;
    					this.params={//要往后台传的参数
    						relativePath:cfg.relativePath||('/'+cfg.system+'/'+cfg.module+'/'+cfg.busiTable+'/'+cfg.busiId+'/'),//  文件保存的相对路径，如果不设置相对路径默认为"/{system}/{module}/{busiTable}/{busiId}/"
    						system:cfg.system,//系统名称
    						module:cfg.module,//系统模块
    						busiType:cfg.busiType,//业务类型
    						busiTable:cfg.busiTable,//业务表
    						busiId:cfg.busiId,//业务ID
    						userName:cfg.userName,//上传用户
    						userId:cfg.userId,//上传用户ID
    						viewAble:cfg.viewAble
    					},
    					this.servlets={	
    							uploader:ctx+ "/commons/fileupload/upload.do?method=upload",//[必须设置]上传文件触发的url
    							del:ctx+'/commons/fileupload/upload.do?method=remove',
    							sort:ctx+'/commons/fileupload/upload.do?method=adjust',
    							get:ctx+'/commons/fileupload/upload.do?method=uploadResult',
    							view:ctx+'/commons/fileupload/upload.do?method=view',
    							down:ctx+'/commons/fileupload/upload.do?method=download',
    							imgView:ctx+'/commons/fileupload/upload.do?method=imgView',
    							imgSrc:ctx+'/commons/fileupload/upload.do?method=getImg'
    						},
    					this.initFileList(this.params);
    					if(cfg.auto){
    						$('#uploadBtn').hide();
    					}
    					$("#uploadify").uploadify({
    						relativePath:pluiUpload.params.relativePath,
    						progressData:'all',// 'percentage''speed''all'//队列中显示文件上传进度的方式：all-上传速度+百分比，percentage-百分比，speed-上传速度
    						auto:cfg.auto||false,//文件选择完成后，是否自动上传
    						height: 20,//上传按钮的高和宽
    						width: 78,
    						queueSizeLimit:cfg.queueSizeLimit||999,//文件上传个数
    						buttonImage: ctx+'/js/plui/extend/uploader/images/selbtn.gif',
    						swf: ctx+'/js/plui/extend/uploader/uploadify.swf',//[必须设置]swf的路径
    						uploader: this.servlets.uploader,//[必须设置]上传文件触发的url
    						cancelImg: ctx+'/js/plui/extend/uploader/images/uploadify-cancel.png',//[必须设置]取消图片的路径
    						buttonText: '选择图片',//上传按钮的文字
    						fileTypeExts: cfg.fileType||'*',//允许上传的文件类型，限制弹出文件选择框里能选择的文件
    						fileTypeDesc:'上传的必须是'+cfg.fileType||'*',//允许上传的文件类型的描述，在弹出的文件选择框里会显示
    						fileSizeLimit: cfg.limit||'0',//上传文件的大小限制 KB或者MB
    						removeCompleted: true, //上传成功后的文件，是否在队列中自动删除
    						//上传进度发生变更时触发
    						onUploadProgress : function(file,fileBytesLoaded,fileTotalBytes,queueBytesLoaded,swfuploadifyQueueUploadSize) {
    							var ul=$("#"+file.id+"_0").parent().parent();
    							var sche = ul.find(".n4 label");
    							var speed = fileBytesLoaded / fileTotalBytes;
    							ul.find(".upload_speed").html(Math.ceil(speed * 100) + "%");
    							if(pluiUpload.viewType==1){
    								sche.css("width",parseInt(91 * speed));//91为总长度
    							}else{
    								sche.css("width",parseInt(82 * speed));
    							}
    						},
    						onDialogClose:function(fq){//当文件选择对话框关闭时触发
    							
    						},
    						onQueueComplete:function(stats){//当队列中的所有文件全部完成上传时触发
    							if(pluiUpload.viewType==1)initDragShot('uploadFileList');
    						},
    						onUploadSuccess : function(file,data,response){//每一个文件上传成功后保存文件地址
    							//alert( 'id: ' + file.id　+ ' - 索引: ' + file.index　+ ' - 文件名: ' + file.name + ' - 文件大小: ' + file.size　+ ' - 类型: ' + file.type + ' - 创建日期: ' + file.creationdate + ' - 修改日期: ' + file.modificationdate + ' - 文件状态: ' + file.filestatus + ' - 服务器端消息: ' + data + ' - 是否上传成功: ' + response);
    							window.eval('var o='+data);
    							var ul=$("#"+file.id+"_0").parent().parent();
    							//是否可以下载
    							var download = '';
    							//是否可以预览
    							var view = '';
    							//是否可以删除
    							var delet = '';
    							download = '<a href="'+pluiUpload.servlets.down+'&uploadId='+o.uploadId+'" class="down_btn" title="下载文件"></a> ';
    							if(file.type.toLowerCase()=='.jpg' || file.type.toLowerCase()=='.png' || file.type.toLowerCase()=='.jpeg' ){
    								view = '<a href="javascript:;" onclick="imgFileView('+null+',\''+pluiUpload.servlets.imgView+'&uploadId='+o.uploadId+'&type=flash\',this,\'uploadFileList\');" class="btw_btn imgFile" title="文件预览"></a>';
    							}else{
    								view = '<a href="javascript:;" onclick="window.open(\''+pluiUpload.servlets.view+'&uploadId='+o.uploadId+'&type=pdf\');'+'" class="btw_btn" title="文件预览"></a>';
    							}
    							delet = '<a href="javascript:;" onclick="delPageUpload(\''+o.uploadId+'\',this,\''+pluiUpload.servlets.del+'\',\''+this.settings.id+'\')" class="del_btn" title="删除文件"></a>';
    							
    							ul.find(".n4").html(download + view+delet);
    							//ul.find(".n4").html('<a href="'+pluiUpload.servlets.down+'&uploadId='+json.uploadId+'" class="down_btn" title="下载文件"></a><a href="javascript:;" onclick="window.open(\''+pluiUpload.servlets.view+'&uploadId='+json.uploadId+'&type=flash\')" class="btw_btn" title="flash预览"></a> <a href="javascript:;" onclick="pluiUpload.del(\''+json.uploadId+'\',this)" class="del_btn" title="删除文件"></a>');
    							ul.find(".upload_speed").empty();
    							ul.attr('uploadId',o.uploadId);
    							if(pluiUpload.viewType==1){
    								$("#"+file.id+"_0").attr('checked',false).attr('disabled',true);
    							}else{
    								var htm='';
    								if(file.type.toLowerCase()=='.gif' || file.type.toLowerCase()=='.png' || file.type.toLowerCase()=='.jpg' ||file.type.toLowerCase()=='.tif' ){
    									htm='<img src="<%=request.getContextPath() %>'+pluiUpload.params.relativePath +file.name+'" />';
    								}else{
    									htm='<span class="'+file.type.substring(1)+'"></span>';
    								}
    								$(ul[0].getElementsByTagName('li')[2]).remove();
    								ul.find(".img_con").html(htm);
    							}
    							if(cfg.callbackfun){
    								cfg.callbackfun(o);
    							}
    							
    							
    							//上传成功后保存到文件关联表
    							data = eval('(' + data + ')');
    							saveFileRel(data.uploadId);
    						},
    						onSelect:function(file){//当每个文件添加至队列后触发
    							$("#uploadify-queue").hide();
    							var obj = pluiUpload.getListHtml(file);
    							if(pluiUpload.viewType==1){
    								$(obj).appendTo($("#uploadFileList"));
    								initDragShot('uploadFileList');
    							}
    							else $(obj).appendTo($("#iocCon"));
    						},
    						onUploadStart:function(file){//设置自定义参数提交后台
    							$("#uploadify").uploadify("settings", "formData",pluiUpload.params);  
    						}  
    					});
    				},
    				closeDig:function(){
    					var files=[];
    					var uls=null;
    					if(pluiUpload.viewType==1)uls=$E('uploadFileList').getElementsByTagName('ul');
    					else uls=$E('iocCon').getElementsByTagName('ul');
    					if(pluiUpload.viewType==1)$('#uploadFileList').empty();
    					else $('#iocCon').empty();
    					if(window.opener){window.opener.openr=null;window.close();}
    					else{
    						var ifr=parent.document.getElementById('splui_uploader');
    						ifr.setAttribute('src','about:blank');
    						ifr.style.display='none';
    					}
    				},
    				del:function(uploadId,o){
    					//alert(uploadId);
    					delFileRel(uploadId);
    					
    					$.getJSON(pluiUpload.servlets.del+'&uploadId='+uploadId+'&t='+Math.random(),function(result){
    						if(result.success){
    							//删除本地数据库中关联表数据
    							
    							
    							$(o).parent().parent().remove();
    						}else{
    							alert('删除失败：'+result.msg);
    						}
    						//var id=o.parentNode.parentNode.getElementsByTagName('input')[0].id;
    						//id=id.substring(0,id.length-2);
    						//$("#" + id).remove();
    						//window["uploadify_uploadify"].cancelUpload(id);
    						//delete window["uploadify_uploadify"].queueData.files[id];
    					})
    					
    				},
    				showMsg:function(obj){
    					var str= "";
    					for(i in obj){
    						if(typeof obj[i] != 'function'){
    							str += i + ":" +obj[i] + "\r\n"; 
    						}
    					}
    					alert(str);
    				},
    				upload:function(){//点击上传
    					var arr = new Array();
    					var chks=document.getElementsByName('upload_check_id');
    					for(var i=0;i<chks.length;i++){
    						if(chks[i].checked){
    							var file =chks[i].id;
    							file = file.substring(0,file.length - 2);
    							arr.push(file);
    						}
    					}
    					if(arr.length == 0){
    						alert('请选择要上传的文件!');
    					}else{
    						var str = "$('#uploadify').uploadify('upload','"+arr.join("','")+"')";
    						eval(str);
    					}
    				},
    				getListHtml:function(file){//获取添加一个文件时应该添加的列表数据  
    					var html='';
    					var size=getNiceFileSize(file.size);
    					if(this.viewType==1){
    						html='<ul fid="'+file.id+'_0" fname="'+file.name+'" ftype="'+file.type.substring(1)+'" fsize="'+size+'">'+
    							  '<li class="n1"><input type="checkbox" checked="true" name="upload_check_id" id= "'+file.id+'_0"></li>'+
    							  '<li class="n2 elli">'+file.name+'</li>'+
    							  '<li class="n3">'+size+'</li>'+
    							  '<li class="n4">'+
    								  '<span><label></label></span>'+
    							  '</li>'+
    							  '<li class="upload_speed">0%</li>'+
    						  '</ul>';	
    					}else{
    						html='<ul fid="'+file.id+'_0" fname="'+file.name+'" ftype="'+file.type.substring(1)+'" fsize="'+size+'">'+
                                '<li class="img_con" title="'+size+'&#13;'+file.name+'"><span class="'+file.type.substring(1)+'"></span><label>等待上传...</label><input type="checkbox" checked="true" name="upload_check_id" id= "'+file.id+'_0"></li>'+
                                '<li class="n4">'+
                                    '<span><label></label></span>'+
                                '</li>'+
                                '<li><label class="kb">'+size+'</label><label class="upload_speed">0%</label></li>'+
                            '</ul>';
    					}
    					return $(html);
    				},
    				checkAll:function(box){//全选的函数
    					var chks=document.getElementsByName('upload_check_id');
    					for(var i=0;i<chks.length;i++){
    						if(!chks[i].disabled)chks[i].checked= box.checked;
    					}
    				},
    				changeView:function(type){
    					if(type==1){//列表显示
    						if($E('iocCon').style.display=='none')return;
    						$E('reo_upload_type1').checked=true;
    						var uls=$E('iocCon').getElementsByTagName('ul');
    						var html='';
    						for(var i=0;i<uls.length;i++){
    							var ul=uls[i];
    							var fid=ul.getAttribute('fid')||'',
    								fname=ul.getAttribute('fname')||'',
    								fsize=ul.getAttribute('fsize')||'',
    								ftype=ul.getAttribute('ftype').toLowerCase()||'',
    								uploadId=ul.getAttribute('uploadId')||'';
    							if(uploadId){//已上传
    								//是否可以下载
    								var download = '';
    								//是否可以预览
    								var view = '';
    								//是否可以删除
    								var delet = '';
    								download = '<a href="'+pluiUpload.servlets.down+'&uploadId='+uploadId+'" class="down_btn" title="下载文件"></a> ';
    								if(ftype=='jpg' || ftype=='png' || ftype=='jpeg'){
    									view = '<a href="javascript:;" onclick="imgFileView('+null+',\''+pluiUpload.servlets.imgView+'&uploadId='+uploadId+'&type=flash\',this,\'listCon\');" class="btw_btn imgFile" title="文件预览"></a>';
    								}else{
    									view = '<a href="javascript:;" onclick="window.open(\''+pluiUpload.servlets.view+'&uploadId='+uploadId+'&type=flash\');'+'" class="btw_btn" title="文件预览"></a>';
    								}
    								delet = '<a href="javascript:;" onclick="pluiUpload.del(\''+uploadId+'\',this)" class="del_btn" title="删除文件"></a>';
    								
    								html+='<ul uploadId="'+uploadId+'" fid="'+fid+'" fname="'+fname+'" ftype="'+ftype+'" fsize="'+fsize+'">'+
    								  '<li class="n1"><input type="checkbox" disabled="true" name="upload_check_id"></li>'+
    								  '<li class="n2 elli" title="'+fname+'">'+fname+'</li>'+
    								  '<li class="n3">'+fsize+'</li>'+
    								  '<li class="n4">'+download+view+delet+'</li>'+
    								  '<li class="upload_speed"></li>'+
    							  '</ul>';
    							}else{//未上传
    								var selected=ul.getElementsByTagName('input')[0].checked;
    								html+='<ul fid="'+fid+'" fname="'+fname+'" ftype="'+ftype+'" fsize="'+fsize+'">'+
    								  '<li class="n1"><input type="checkbox" '+(selected?'checked="true"':'')+' name="upload_check_id" id= "'+fid+'"></li>'+
    								  '<li class="n2 elli">'+fname+'</li>'+
    								  '<li class="n3">'+fsize+'</li>'+
    								  '<li class="n4">'+
    									  '<span><label></label></span>'+
    								  '</li>'+
    								  '<li class="upload_speed">0%</li>'+
    							  '</ul>';
    							}
    						}
    						$('#iocCon').hide().empty();
    						$('#uploadFileList').empty().html(html);
    						initDragShot('uploadFileList');
    						$('#listCon').show();
    					}else{
    						if($E('listCon').style.display=='none')return;
    						$E('reo_upload_type2').checked=true;
    						var uls=$E('uploadFileList').getElementsByTagName('ul');
    						var html='';
    						for(var i=0;i<uls.length;i++){
    							var ul=uls[i];
    							var fid=ul.getAttribute('fid')||'',
    									fname=ul.getAttribute('fname')||'',
    									fsize=ul.getAttribute('fsize')||'',
    									ftype=ul.getAttribute('ftype').toLowerCase()||'',
    									uploadId=ul.getAttribute('uploadId')||'';
    							if(uploadId){//已上传
    								//是否可以下载
    								var download = '';
    								//是否可以预览
    								var view = '';
    								//是否可以删除
    								var delet = '';
    								download = '<a href="'+pluiUpload.servlets.down+'&uploadId='+uploadId+'" class="down_btn" title="下载文件"></a> ';
    								if(ftype=='jpg' || ftype=='png' || ftype=='jpeg'){
    									view = '<a href="javascript:;" onclick="imgFileView('+null+',\''+pluiUpload.servlets.imgView+'&uploadId='+uploadId+'&type=flash\',this,\'iocCon\');" class="btw_btn imgFile" title="文件预览"></a>';
    								}else{
    									view = '<a href="javascript:;" onclick="window.open(\''+pluiUpload.servlets.view+'&uploadId='+uploadId+'&type=flash\');'+'" class="btw_btn" title="文件预览"></a>';
    								}
    								delet = '<a href="javascript:;" onclick="pluiUpload.del(\''+uploadId+'\',this)" class="del_btn" title="删除文件"></a>';
    								html+='<ul uploadId="'+uploadId+'" fid="'+fid+'" fname="'+fname+'" ftype="'+ftype+'" fsize="'+fsize+'">'+
    									'<li class="img_con" title="'+fsize+' &#13;'+fname+'">';
    									if(ftype=='gif' || ftype=='png' || ftype=='jpg' ||ftype=='tif'){
    										html+='<img src="<%=request.getContextPath() %>'+pluiUpload.servlets.imgSrc+'&uploadId='+uploadId+'"/>';
    									}else{
    										html+='<span class="'+ftype+'"></span>';
    									}
    									html+='</li><li class="n4">'+download+view+delet+
    									'</li>'+
    								'</ul>';
    							}else{//未上传
    								var selected=ul.getElementsByTagName('input')[0].checked;
    								html+='<ul path="'+path+'" fid="'+fid+'" fname="'+fname+'" ftype="'+ftype+'" fsize="'+fsize+'">'+
    									'<li class="img_con"><span class="'+ftype+'"></span><label>等待上传...</label><input type="checkbox" '+(selected?'checked="true"':'')+' name="upload_check_id" id= "'+fid+'"></li>'+
    									'<li class="n4">'+
    										'<span><label></label></span>'+
    									'</li>'+
    									'<li><label class="kb">'+fsize+'</label><label class="upload_speed">0%</label></li>'+
    								'</ul>';
    							}
    						}
    						$('#iocCon').empty().html(html).show();
    						$('#listCon').hide();
    						$('#uploadFileList').empty();
    					}
    					pluiUpload.viewType=type;
    				},
    				initFileList:function(params){
    					$.ajax({url:pluiUpload.servlets.get,dataType:'json',type:'POST',data:params,success:function(list){
    						if(!list){return;}
    						var html='';
    						if(pluiUpload.viewType==1){//列表
    							for(var i=0;i<list.length;i++){
    								var o=list[i];
    								var path=o.relativePath+o.name;
    								var size=getNiceFileSize(o.fileSize);
    								var fileType = o.fileType;
    								//是否可以下载
    								var download = '';
    								//是否可以预览
    								var view = '';
    								//是否可以删除
    								var delet = '';
    								download = '<a href="'+pluiUpload.servlets.down+'&uploadId='+o.uploadId+'" class="down_btn" title="下载文件"></a> ';
    								if(fileType=='jpg' || fileType=='png' || fileType=='jpeg'){
    									view = '<a href="javascript:;" onclick="imgFileView('+null+',\''+pluiUpload.servlets.imgView+'&uploadId='+o.uploadId+'&type=flash\',this,\'uploadFileList\');" class="btw_btn imgFile" title="文件预览"></a>';
    								}else{
    									view = '<a href="javascript:;" onclick="window.open(\''+pluiUpload.servlets.view+'&uploadId='+o.uploadId+'&type=flash\');'+'" class="btw_btn" title="文件预览"></a>';
    								}
    								delet = '<a href="javascript:;" onclick="pluiUpload.del(\''+o.uploadId+'\',this)" class="del_btn" title="删除文件"></a>';
    								html+='<ul uploadId="'+o.uploadId+'" fname="'+o.name+'" ftype="'+o.fileType+'" fsize="'+size+'">'+
    									  '<li class="n1"><input type="checkbox" disabled="true" name="upload_check_id"></li>'+
    									  '<li class="n2 elli" title="'+o.name+'">'+o.name+'</li>'+
    									  '<li class="n3">'+size+'</li>'+
    									  '<li class="n4">'+download+view+delet+'</li>'+
    									  '<li class="upload_speed"></li>'+
    								  '</ul>';
    							}
    							$('#uploadFileList').empty().html(html);
    							initDragShot('uploadFileList');
    						}else{
    							for(var i=0;i<list.length;i++){
    								var o=list[i];
    								var path=o.relativePath+o.name;
    								var size=getNiceFileSize(o.fileSize);
    								html+='<ul uploadId="'+o.uploadId+'" fname="'+o.name+'" ftype="'+o.fileType+'" fsize="'+size+'">'+
    								'<li class="img_con" title="'+size+' &#13;'+o.name+'">';
    								if(o.fileType.toLowerCase()=='gif' || o.fileType.toLowerCase()=='png' || o.fileType.toLowerCase()=='jpg' ||o.fileType.toLowerCase()=='tif'){
    									html+='<img src="<%=request.getContextPath() %>'+path+'"/>';
    								}else{
    									html+='<span class="'+o.fileType+'"></span>';
    								}
    								//是否可以下载
    								var download = '';
    								//是否可以预览
    								var view = '';
    								//是否可以删除
    								var delet = '';
    								download = '<a href="'+pluiUpload.servlets.down+'&uploadId='+o.uploadId+'" class="down_btn" title="下载文件"></a> ';
    								if(o.fileType.toLowerCase()=='jpg' || o.fileType.toLowerCase()=='png' || o.fileType.toLowerCase()=='jpeg'||o.fileType.toLowerCase()=='tif'){
    									view = '<a href="javascript:;" onclick="imgFileView('+null+',\''+pluiUpload.servlets.imgView+'&uploadId='+o.uploadId+'&type=flash\',this,\'uploadFileList\');" class="btw_btn imgFile" title="文件预览"></a>';
    								}else{
    									view = '<a href="javascript:;" onclick="window.open(\''+pluiUpload.servlets.view+'&uploadId='+o.uploadId+'&type=flash\');'+'" class="btw_btn" title="文件预览"></a>';
    								}
    								delet = '<a href="javascript:;" onclick="pluiUpload.del(\''+o.uploadId+'\',this)" class="del_btn" title="删除文件"></a>';
    								html+='</li><li class="n4">'+
    									download+view+delet
    								'</li>'+
    							'</ul>';
    							}
    							$('#iocCon').empty().html(html);
    						}
    					}});
    				}
    			};
        	
        	
        	
        	
        	//定义完成后  初始化
        	
        	if(window.opener){
				$('#tip').show();
				$('#closeBtn').hide();
			}else{
				$('#tip').hide();
			}
			//window.par=window.opener||parent;
			//window.par.setConfig();
			if(!window.cfg)window.cfg={};
			pluiUpload.init();
			if(pluiUpload.viewType==1){
				$E('reo_upload_type1').checked=true;
				$('#iocCon').hide();
				$('#listCon').show();
			}else{
				$E('reo_upload_type2').checked=true;
				$('#listCon').hide();
				$('#iocCon').show();
			}
        	
        }
			
			
			$(document).ready(function() {
				
			});
			
			//保存文件关联表  --在上传成功时调用 
			function saveFileRel(uploadId){
				$.ajax({
					url : ctx+"/common/filerel!saveFileRel.action?time="+new Date(),
					type : 'post',
					data : {
						uploadId:uploadId,proc_id:proc.procId,proc_node:proc.activName
					},
					dataType : 'json',
					success : function(data) {
						
					}
				});
			}
			
			//删除文件关联表 --在删除成功时调用
			function delFileRel(uploadId){
				$.ajax({
					url : ctx+"/common/filerel!delFileRel.action?time="+new Date(),
					type : 'post',
					data : {
						uploadId:uploadId,proc_id:proc.procId,proc_node:proc.activName
					},
					dataType : 'json',
					success : function(data) {
						
					}
				});
			}
			
			//封装一层删除   因为要在本地库中删除上传文件
			function delPageUpload(uploadId,o,servlet,upid){
				//删除本地库
				delFileRel(uploadId);
				//调用组件的删除方法
				deletePageUpload(uploadId,o,servlet,upid);
			}
			
			function setState(){
				//如果查看附件环节不是当前环节   则禁用上传按扭
	        	if(proc.openNode!=proc.activName){
	        		//$('#uploadBtn').attr("disabled","disabled");
	        		//$('#uploadify').attr("disabled","disabled");
	        		
	        		$('#uploadBtn').css("display","none");
	        		$('#uploadify').css("display","none");
	        		$('#SWFUpload_0').css("display","none");
	        		
	        		
	        	}
			}
			
        </script>
      <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    </head>
    <body scroll="no" onUnload="if(window.opener)window.opener.openr=null;">
    	<div id="file_upload_layer" class="upload_layer">
            <div class="con bg"></div>
            <div class="con">
                <div class="title">文件管理　　
                
                <!-- 
                <label id="tip" style="color:red; font-weight:normal;">警告：保存数据只能点击“确定” 按钮才能回传数据！</label>
                 -->
                </div>
                <!-- 
                <a href="javascript:;" onClick="pluiUpload.closeDig()" class="close" id="closeBtn"></a>
                 -->
                 <div id="uploadify" class="uploadify-button"></div>
               
                <div class="radio_con"  style="display:none;"> 
                    <input  type="radio" name="reo_upload" value="1" id="reo_upload_type1" checked="true" onclick="pluiUpload.changeView(1)"/>
                    <label for="reo_upload_type1">列表显示</label> 
                 </div> 
                     <!--
                    <input type="radio" name="reo_upload" id="reo_upload_type2" value="2" onclick="pluiUpload.changeView(2)"/>
                    <label for="reo_upload_type2">缩略图显示</label> 
                 -->
               
                <div id="uploadifySortBtn" class="savesort_btn" onclick="saveSortValue(null,this);" style=" position:absolute; top:37px; right:70px;"></div>
                <div id="uploadBtn" class="upload_btn" onclick="pluiUpload.upload();"></div>
                <div class="content">
                    <div id="listCon" class="list_con">
                        <div class="list_head">
                        　　　文件名　　　　　　　　　　　　　 进度
                        </div>
                        <div id="uploadFileList" class="list"></div>
                        <input type="checkbox" class="chk_all" id="upload_chk_all" onclick="pluiUpload.checkAll(this);"/>
                    </div>
                    <div id="iocCon" class="ioc_con" style="display:none;"></div>
                </div>
                <div class="ok_btn" onClick="pluiUpload.closeDig();" style="display:none;"></div>
            </div>
        </div>
    </body>
</html>

