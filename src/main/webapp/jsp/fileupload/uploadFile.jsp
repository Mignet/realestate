<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
      <head>
        <title>�����ϴ�</title>
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
        		system:"�����в������Ǽ�ϵͳ",
        		module:"module",
        		busiType:"��ʼ�Ǽ�",
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
        
        //��ʼ��
        function init(initData){
        	proc = initData;
        	
        
        	
        	//��ʼ���ϴ��������
        	cfg.busiId=proc.openNode;
        	cfg.busiType = proc.procdefName;
        	cfg.module = proc.procdefName;
        	cfg.busiTable =proc.procId;
        	
        	//��ʼ���ϴ����
        	initUpload();
        	
        	setState();
        }
        
        //��ʼ���ϴ����
        function initUpload(){
        	pluiUpload={
    				init:function(){
    					this.viewType=cfg.viewType||1;
    					this.params={//Ҫ����̨���Ĳ���
    						relativePath:cfg.relativePath||('/'+cfg.system+'/'+cfg.module+'/'+cfg.busiTable+'/'+cfg.busiId+'/'),//  �ļ���������·����������������·��Ĭ��Ϊ"/{system}/{module}/{busiTable}/{busiId}/"
    						system:cfg.system,//ϵͳ����
    						module:cfg.module,//ϵͳģ��
    						busiType:cfg.busiType,//ҵ������
    						busiTable:cfg.busiTable,//ҵ���
    						busiId:cfg.busiId,//ҵ��ID
    						userName:cfg.userName,//�ϴ��û�
    						userId:cfg.userId,//�ϴ��û�ID
    						viewAble:cfg.viewAble
    					},
    					this.servlets={	
    							uploader:ctx+ "/commons/fileupload/upload.do?method=upload",//[��������]�ϴ��ļ�������url
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
    						progressData:'all',// 'percentage''speed''all'//��������ʾ�ļ��ϴ����ȵķ�ʽ��all-�ϴ��ٶ�+�ٷֱȣ�percentage-�ٷֱȣ�speed-�ϴ��ٶ�
    						auto:cfg.auto||false,//�ļ�ѡ����ɺ��Ƿ��Զ��ϴ�
    						height: 20,//�ϴ���ť�ĸߺͿ�
    						width: 78,
    						queueSizeLimit:cfg.queueSizeLimit||999,//�ļ��ϴ�����
    						buttonImage: ctx+'/js/plui/extend/uploader/images/selbtn.gif',
    						swf: ctx+'/js/plui/extend/uploader/uploadify.swf',//[��������]swf��·��
    						uploader: this.servlets.uploader,//[��������]�ϴ��ļ�������url
    						cancelImg: ctx+'/js/plui/extend/uploader/images/uploadify-cancel.png',//[��������]ȡ��ͼƬ��·��
    						buttonText: 'ѡ��ͼƬ',//�ϴ���ť������
    						fileTypeExts: cfg.fileType||'*',//�����ϴ����ļ����ͣ����Ƶ����ļ�ѡ�������ѡ����ļ�
    						fileTypeDesc:'�ϴ��ı�����'+cfg.fileType||'*',//�����ϴ����ļ����͵��������ڵ������ļ�ѡ��������ʾ
    						fileSizeLimit: cfg.limit||'0',//�ϴ��ļ��Ĵ�С���� KB����MB
    						removeCompleted: true, //�ϴ��ɹ�����ļ����Ƿ��ڶ������Զ�ɾ��
    						//�ϴ����ȷ������ʱ����
    						onUploadProgress : function(file,fileBytesLoaded,fileTotalBytes,queueBytesLoaded,swfuploadifyQueueUploadSize) {
    							var ul=$("#"+file.id+"_0").parent().parent();
    							var sche = ul.find(".n4 label");
    							var speed = fileBytesLoaded / fileTotalBytes;
    							ul.find(".upload_speed").html(Math.ceil(speed * 100) + "%");
    							if(pluiUpload.viewType==1){
    								sche.css("width",parseInt(91 * speed));//91Ϊ�ܳ���
    							}else{
    								sche.css("width",parseInt(82 * speed));
    							}
    						},
    						onDialogClose:function(fq){//���ļ�ѡ��Ի���ر�ʱ����
    							
    						},
    						onQueueComplete:function(stats){//�������е������ļ�ȫ������ϴ�ʱ����
    							if(pluiUpload.viewType==1)initDragShot('uploadFileList');
    						},
    						onUploadSuccess : function(file,data,response){//ÿһ���ļ��ϴ��ɹ��󱣴��ļ���ַ
    							//alert( 'id: ' + file.id��+ ' - ����: ' + file.index��+ ' - �ļ���: ' + file.name + ' - �ļ���С: ' + file.size��+ ' - ����: ' + file.type + ' - ��������: ' + file.creationdate + ' - �޸�����: ' + file.modificationdate + ' - �ļ�״̬: ' + file.filestatus + ' - ����������Ϣ: ' + data + ' - �Ƿ��ϴ��ɹ�: ' + response);
    							window.eval('var o='+data);
    							var ul=$("#"+file.id+"_0").parent().parent();
    							//�Ƿ��������
    							var download = '';
    							//�Ƿ����Ԥ��
    							var view = '';
    							//�Ƿ����ɾ��
    							var delet = '';
    							download = '<a href="'+pluiUpload.servlets.down+'&uploadId='+o.uploadId+'" class="down_btn" title="�����ļ�"></a> ';
    							if(file.type.toLowerCase()=='.jpg' || file.type.toLowerCase()=='.png' || file.type.toLowerCase()=='.jpeg' ){
    								view = '<a href="javascript:;" onclick="imgFileView('+null+',\''+pluiUpload.servlets.imgView+'&uploadId='+o.uploadId+'&type=flash\',this,\'uploadFileList\');" class="btw_btn imgFile" title="�ļ�Ԥ��"></a>';
    							}else{
    								view = '<a href="javascript:;" onclick="window.open(\''+pluiUpload.servlets.view+'&uploadId='+o.uploadId+'&type=pdf\');'+'" class="btw_btn" title="�ļ�Ԥ��"></a>';
    							}
    							delet = '<a href="javascript:;" onclick="delPageUpload(\''+o.uploadId+'\',this,\''+pluiUpload.servlets.del+'\',\''+this.settings.id+'\')" class="del_btn" title="ɾ���ļ�"></a>';
    							
    							ul.find(".n4").html(download + view+delet);
    							//ul.find(".n4").html('<a href="'+pluiUpload.servlets.down+'&uploadId='+json.uploadId+'" class="down_btn" title="�����ļ�"></a><a href="javascript:;" onclick="window.open(\''+pluiUpload.servlets.view+'&uploadId='+json.uploadId+'&type=flash\')" class="btw_btn" title="flashԤ��"></a> <a href="javascript:;" onclick="pluiUpload.del(\''+json.uploadId+'\',this)" class="del_btn" title="ɾ���ļ�"></a>');
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
    							
    							
    							//�ϴ��ɹ��󱣴浽�ļ�������
    							data = eval('(' + data + ')');
    							saveFileRel(data.uploadId);
    						},
    						onSelect:function(file){//��ÿ���ļ���������к󴥷�
    							$("#uploadify-queue").hide();
    							var obj = pluiUpload.getListHtml(file);
    							if(pluiUpload.viewType==1){
    								$(obj).appendTo($("#uploadFileList"));
    								initDragShot('uploadFileList');
    							}
    							else $(obj).appendTo($("#iocCon"));
    						},
    						onUploadStart:function(file){//�����Զ�������ύ��̨
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
    							//ɾ���������ݿ��й���������
    							
    							
    							$(o).parent().parent().remove();
    						}else{
    							alert('ɾ��ʧ�ܣ�'+result.msg);
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
    				upload:function(){//����ϴ�
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
    						alert('��ѡ��Ҫ�ϴ����ļ�!');
    					}else{
    						var str = "$('#uploadify').uploadify('upload','"+arr.join("','")+"')";
    						eval(str);
    					}
    				},
    				getListHtml:function(file){//��ȡ���һ���ļ�ʱӦ����ӵ��б�����  
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
                                '<li class="img_con" title="'+size+'&#13;'+file.name+'"><span class="'+file.type.substring(1)+'"></span><label>�ȴ��ϴ�...</label><input type="checkbox" checked="true" name="upload_check_id" id= "'+file.id+'_0"></li>'+
                                '<li class="n4">'+
                                    '<span><label></label></span>'+
                                '</li>'+
                                '<li><label class="kb">'+size+'</label><label class="upload_speed">0%</label></li>'+
                            '</ul>';
    					}
    					return $(html);
    				},
    				checkAll:function(box){//ȫѡ�ĺ���
    					var chks=document.getElementsByName('upload_check_id');
    					for(var i=0;i<chks.length;i++){
    						if(!chks[i].disabled)chks[i].checked= box.checked;
    					}
    				},
    				changeView:function(type){
    					if(type==1){//�б���ʾ
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
    							if(uploadId){//���ϴ�
    								//�Ƿ��������
    								var download = '';
    								//�Ƿ����Ԥ��
    								var view = '';
    								//�Ƿ����ɾ��
    								var delet = '';
    								download = '<a href="'+pluiUpload.servlets.down+'&uploadId='+uploadId+'" class="down_btn" title="�����ļ�"></a> ';
    								if(ftype=='jpg' || ftype=='png' || ftype=='jpeg'){
    									view = '<a href="javascript:;" onclick="imgFileView('+null+',\''+pluiUpload.servlets.imgView+'&uploadId='+uploadId+'&type=flash\',this,\'listCon\');" class="btw_btn imgFile" title="�ļ�Ԥ��"></a>';
    								}else{
    									view = '<a href="javascript:;" onclick="window.open(\''+pluiUpload.servlets.view+'&uploadId='+uploadId+'&type=flash\');'+'" class="btw_btn" title="�ļ�Ԥ��"></a>';
    								}
    								delet = '<a href="javascript:;" onclick="pluiUpload.del(\''+uploadId+'\',this)" class="del_btn" title="ɾ���ļ�"></a>';
    								
    								html+='<ul uploadId="'+uploadId+'" fid="'+fid+'" fname="'+fname+'" ftype="'+ftype+'" fsize="'+fsize+'">'+
    								  '<li class="n1"><input type="checkbox" disabled="true" name="upload_check_id"></li>'+
    								  '<li class="n2 elli" title="'+fname+'">'+fname+'</li>'+
    								  '<li class="n3">'+fsize+'</li>'+
    								  '<li class="n4">'+download+view+delet+'</li>'+
    								  '<li class="upload_speed"></li>'+
    							  '</ul>';
    							}else{//δ�ϴ�
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
    							if(uploadId){//���ϴ�
    								//�Ƿ��������
    								var download = '';
    								//�Ƿ����Ԥ��
    								var view = '';
    								//�Ƿ����ɾ��
    								var delet = '';
    								download = '<a href="'+pluiUpload.servlets.down+'&uploadId='+uploadId+'" class="down_btn" title="�����ļ�"></a> ';
    								if(ftype=='jpg' || ftype=='png' || ftype=='jpeg'){
    									view = '<a href="javascript:;" onclick="imgFileView('+null+',\''+pluiUpload.servlets.imgView+'&uploadId='+uploadId+'&type=flash\',this,\'iocCon\');" class="btw_btn imgFile" title="�ļ�Ԥ��"></a>';
    								}else{
    									view = '<a href="javascript:;" onclick="window.open(\''+pluiUpload.servlets.view+'&uploadId='+uploadId+'&type=flash\');'+'" class="btw_btn" title="�ļ�Ԥ��"></a>';
    								}
    								delet = '<a href="javascript:;" onclick="pluiUpload.del(\''+uploadId+'\',this)" class="del_btn" title="ɾ���ļ�"></a>';
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
    							}else{//δ�ϴ�
    								var selected=ul.getElementsByTagName('input')[0].checked;
    								html+='<ul path="'+path+'" fid="'+fid+'" fname="'+fname+'" ftype="'+ftype+'" fsize="'+fsize+'">'+
    									'<li class="img_con"><span class="'+ftype+'"></span><label>�ȴ��ϴ�...</label><input type="checkbox" '+(selected?'checked="true"':'')+' name="upload_check_id" id= "'+fid+'"></li>'+
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
    						if(pluiUpload.viewType==1){//�б�
    							for(var i=0;i<list.length;i++){
    								var o=list[i];
    								var path=o.relativePath+o.name;
    								var size=getNiceFileSize(o.fileSize);
    								var fileType = o.fileType;
    								//�Ƿ��������
    								var download = '';
    								//�Ƿ����Ԥ��
    								var view = '';
    								//�Ƿ����ɾ��
    								var delet = '';
    								download = '<a href="'+pluiUpload.servlets.down+'&uploadId='+o.uploadId+'" class="down_btn" title="�����ļ�"></a> ';
    								if(fileType=='jpg' || fileType=='png' || fileType=='jpeg'){
    									view = '<a href="javascript:;" onclick="imgFileView('+null+',\''+pluiUpload.servlets.imgView+'&uploadId='+o.uploadId+'&type=flash\',this,\'uploadFileList\');" class="btw_btn imgFile" title="�ļ�Ԥ��"></a>';
    								}else{
    									view = '<a href="javascript:;" onclick="window.open(\''+pluiUpload.servlets.view+'&uploadId='+o.uploadId+'&type=flash\');'+'" class="btw_btn" title="�ļ�Ԥ��"></a>';
    								}
    								delet = '<a href="javascript:;" onclick="pluiUpload.del(\''+o.uploadId+'\',this)" class="del_btn" title="ɾ���ļ�"></a>';
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
    								//�Ƿ��������
    								var download = '';
    								//�Ƿ����Ԥ��
    								var view = '';
    								//�Ƿ����ɾ��
    								var delet = '';
    								download = '<a href="'+pluiUpload.servlets.down+'&uploadId='+o.uploadId+'" class="down_btn" title="�����ļ�"></a> ';
    								if(o.fileType.toLowerCase()=='jpg' || o.fileType.toLowerCase()=='png' || o.fileType.toLowerCase()=='jpeg'||o.fileType.toLowerCase()=='tif'){
    									view = '<a href="javascript:;" onclick="imgFileView('+null+',\''+pluiUpload.servlets.imgView+'&uploadId='+o.uploadId+'&type=flash\',this,\'uploadFileList\');" class="btw_btn imgFile" title="�ļ�Ԥ��"></a>';
    								}else{
    									view = '<a href="javascript:;" onclick="window.open(\''+pluiUpload.servlets.view+'&uploadId='+o.uploadId+'&type=flash\');'+'" class="btw_btn" title="�ļ�Ԥ��"></a>';
    								}
    								delet = '<a href="javascript:;" onclick="pluiUpload.del(\''+o.uploadId+'\',this)" class="del_btn" title="ɾ���ļ�"></a>';
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
        	
        	
        	
        	
        	//������ɺ�  ��ʼ��
        	
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
			
			//�����ļ�������  --���ϴ��ɹ�ʱ���� 
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
			
			//ɾ���ļ������� --��ɾ���ɹ�ʱ����
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
			
			//��װһ��ɾ��   ��ΪҪ�ڱ��ؿ���ɾ���ϴ��ļ�
			function delPageUpload(uploadId,o,servlet,upid){
				//ɾ�����ؿ�
				delFileRel(uploadId);
				//���������ɾ������
				deletePageUpload(uploadId,o,servlet,upid);
			}
			
			function setState(){
				//����鿴�������ڲ��ǵ�ǰ����   ������ϴ���Ť
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
                <div class="title">�ļ�������
                
                <!-- 
                <label id="tip" style="color:red; font-weight:normal;">���棺��������ֻ�ܵ����ȷ���� ��ť���ܻش����ݣ�</label>
                 -->
                </div>
                <!-- 
                <a href="javascript:;" onClick="pluiUpload.closeDig()" class="close" id="closeBtn"></a>
                 -->
                 <div id="uploadify" class="uploadify-button"></div>
               
                <div class="radio_con"  style="display:none;"> 
                    <input  type="radio" name="reo_upload" value="1" id="reo_upload_type1" checked="true" onclick="pluiUpload.changeView(1)"/>
                    <label for="reo_upload_type1">�б���ʾ</label> 
                 </div> 
                     <!--
                    <input type="radio" name="reo_upload" id="reo_upload_type2" value="2" onclick="pluiUpload.changeView(2)"/>
                    <label for="reo_upload_type2">����ͼ��ʾ</label> 
                 -->
               
                <div id="uploadifySortBtn" class="savesort_btn" onclick="saveSortValue(null,this);" style=" position:absolute; top:37px; right:70px;"></div>
                <div id="uploadBtn" class="upload_btn" onclick="pluiUpload.upload();"></div>
                <div class="content">
                    <div id="listCon" class="list_con">
                        <div class="list_head">
                        �������ļ����������������������������� ����
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

