	
//var row;
var wiId;
var proc;
var proc_id;
//�ǲ���
var dbr;

//�ǼǱ��
var regcode;

var lcslId;

var formid;

/*
var reg_unit_type ={};
reg_unit_type.PARCEL ='009003';						//�Ǽǵ�Ԫ��� �ڵ�
reg_unit_type.BUILDING ='009002';					//�Ǽǵ�Ԫ���  ¥��
reg_unit_type.HOUSE ='009001';						//�Ǽǵ�Ԫ��� ����
*/
var bus_type_id;
//�洢����������
var iframeData;
//�洢��ǰҳ��Iframe��id
var iframeid;

function init(user){
	proc=user;
	proc_id = proc.procId;
	//proc=proc.workItem;
	//JSON.stringify(proc);
	//alert(JSON.stringify(proc));
	
	$('#note').text(proc.activName);
	
	getBusTypeParentId();
	
	
	wiId=proc.wiId;//GetQueryString("wiId");	
	$('#bustree').tree({
		url:ctx+"/common/work-window!getOfficeByNodeid.action?procdefId="+proc.procdefId+"&nodeid="+proc.activdefId+"&time="+new Date(),
		//data:{"procdefId":proc.procdefId,"nodeid":proc.activdefId,"time":new Date()},
		onSelect : function(data) {
			
		},onBeforeSelect:function(node){
			var flag=false;
			var result = true;
			var data = $('#bustree').tree('getSelected');
			//alert(JSON.stringify(data));
			if(data){
			  if(data.attributes.url){	
				if(data.id){
					
					var Iframe;
					iframeid=data.id+"s";
					Iframe=document.getElementById(iframeid);
					//�жϵ�ǰҳ�������Ƿ��Ѿ��޸�  ����޸���ʾ �Ƿ񱣴浱ǰҳ������
					if(Iframe.contentWindow.pageDataIsChange)
					{
						var tmpResult=Iframe.contentWindow.pageDataIsChange();
						//ҳ���޸�   ִ����ʾ�Ƿ񱣴����ݺ��뿪
						if(tmpResult)
						{
							var r = window.confirm("ҳ�����ݼ��޸ģ��Ƿ񱣴���뿪��");
//							alert(r)
							if(r ){
								//save();
								var result;
								var Iframe=document.getElementById(iframeid);
								if(Iframe.contentWindow.validate)
								{
									//�����֤ʧ��   ��ʾ  ������ת����һҳ��
									result=Iframe.contentWindow.validate(1);
									if(!result.result)
									{
										top.$.messager.alert('��ʾ',result.message,'info',function(){
										});
										return false;
									}
									else
									{
										Iframe.contentWindow.submit();
									}
									
								}
							}else{
								//return true;
							}
						}
						
					}
					
				}
			  }
			}
			openChildPage(node);			//����һ��ҳ��
			return result;
		},
		onLoadSuccess : function(node,data) {
			emptyIframe();
			//�����سɹ���򿪵�һ�����ڵ�
			//alert(JSON.stringify(data))
			iframeData=data;
			for(var i=0;i<data.length;i++)
			{
				if(data[i].children)
				{
					for(var j=0;j<data[i].children.length;j++)
					{
						var url = data[i].children[j].attributes.url+"?wiId="+wiId+"&procId="+proc.procId+"&activName="+proc.activName+"&time="+new Date();
						$('#iframe').append("<div style='display:none;'  id='"+data[i].children[j].id+"' name='"+data[i].children[j].id+"' ><iframe  src='"+url+"' id='"+data[i].children[j].id+"s' name='"+data[i].children[j].id+"s' height='800' width='900'  allowtransparency='true' scrolling='yes' frameborder='0'></iframe></div>")
					}
				}
			}
			if(data[0].children)
			{
				var firstNode=data[0].children[0];
				var node = $('#bustree').tree('find', firstNode.id);
				$('#bustree').tree('select', node.target);
			}
			
		}
	});
	

	
	
}

/**********************************************************************************
*��������: openChildPage
*����˵��: ���ӽڵ�칫ҳ��
*����˵��: �ӽڵ�����node
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function openChildPage(data){
	var Iframe;
	iframeid=data.id+"s";
	Iframe=document.getElementById(iframeid);
	if(data.iconCls!="")
	{
		for(var i=0;i<iframeData.length;i++)
		{
			if(iframeData[i].children)
			{
				for(var j=0;j<iframeData[i].children.length;j++)
				{
					$('#'+iframeData[i].children[j].id).css("display","none");
				}
			}
		}
		if(data.id){
			$('#'+data.id).css("display","block");
			iframeid=data.id+"s";
			$('#'+data.id+"s").attr("src",data.attributes.url);
		//window.open(data.attributes.url+"?wiId="+wiId+"&procId="+proc.procId+"&activName="+proc.activName+"&time="+new Date(),'in');
		} 
	}
}
		//�ύ
		function getparticiPants(btn,data){
			//�ں�׼����  �ж��Ƿ��Ѿ��ǲ�  ���δ�ǲ�   ��ʾ���ȵǲ����ύ     --����ǲ����������
			if(proc.activName=="��׼"){
				//�����ǰ��ŤΪ  �����ˡ����ж��Ƿ��Ѿ��ǲ�  ����Ѿ��ǲ� ���������
				if(btn.innerHTML.indexOf("����")!="-1"){
					if(enumdata.PRE_SALE_RECORD == bus_type_id || enumdata.TIP_RECORD == bus_type_id || enumdata.JUDICIAL_TRANSFER == bus_type_id){
						//Ԥ�۱���ҵ��  ���޸ı���״̬ ���ܻ��� 
						if(enumdata.PRE_SALE_RECORD == bus_type_id){//if(enumdata.PRESALE_BACKUP == getBusTypeId()){
							if(isRecorded()){
								top.$.messager.alert('��ʾ', "�Ѿ��޸ı���״̬  ���ܽ��д˲���!", 'info',null);
								return;
							}
						}
					}else{
						if(isRegisterSave()){
							top.$.messager.alert('��ʾ',"�Ѿ��ǲ������ܽ��д˲�����",'info',function(){
							});
							return ;
						}
					}
				}
				//����ύ��Ťʱ   �ж��Ƿ��Ѿ��ǲ�  ���δ�ǲ� �������ύ
				if(btn.innerHTML.indexOf("�ύ")!="-1"){
					if(enumdata.PRE_SALE_RECORD == bus_type_id || enumdata.TIP_RECORD == bus_type_id || enumdata.JUDICIAL_TRANSFER == bus_type_id){
						//Ԥ�۱���ҵ��  δ�޸ı���״̬ �����ύ
						if(enumdata.PRE_SALE_RECORD == bus_type_id){//if(enumdata.PRESALE_BACKUP == getBusTypeId()){
							if(!isRecorded()){
								top.$.messager.alert('��ʾ', "�����޸ı���״̬  ���ٽ����ύ!", 'info',null);
								return;
							}
						}
					}else{
						if(!isRegisterSave()){
							top.$.messager.alert('��ʾ', "���ȵǲ����ٽ����ύ!", 'info',
									function() {
									});
							return ;
						}
					}
				}
			}
			var id;
			var result;
			var Iframe;
			var procdefId=proc.procdefId;			
			var activdefId=data.To;			 
			var resultdata=data;
			var wiId=proc.wiId;
			Iframe=document.getElementById(iframeid);
			if(iframeid&&Iframe.contentWindow.validate)
			{
				//Iframe=document.getElementById(iframeid);
				result= Iframe.contentWindow.validate();
				if(result.result)
				{
					for(var i=0;i<iframeData.length;i++)
					{
						if(iframeData[i].children)
						{
							for(var j=0;j<iframeData[i].children.length;j++)
							{
								id=iframeData[i].children[j].id+"s";
								var allframe=document.getElementById(id);
								result=allframe.contentWindow.validate();
								if(!result.result)
								{
									break;
								}
							}
						}
						if(!result.result)
						{
							break;
						}
					}
					if(result.result)
					{
						
						//Iframe.contentWindow.submit();
					
					if(proc.wiName=="�鵵"){			
						 	var end={
						 		url:ctx+"/workflow/workflow!WorkItemFinishedEndWithFacade.action",
						 		type:'post',
						 		async:false,
						 		data:{"wiId":wiId,"time":new Date()},
						 		dataType:"json",
						 		success:function(data){
						 			//alert(data);
						 			if(data.sign=="success"){	
						 			top.$.messager.alert('��ʾ', '�鵵�ɹ���', 'info',
									function() {	
								 				
						 				});			
						 			}
						 			else if(data.sign=="failed"){
						 				top.$.messager.alert('��ʾ', '�鵵ʧ�ܣ�', 'info',
									function() {	
								 				
						 				});	
						 				return;
						 			}
						 		}
						 	};$.ajax(end);
						 	openerWindow.location.reload();
						 	closeInTopWindow('open_app','destroy');
						 }
						 else{
							summit(activdefId,procdefId,resultdata);
									}
					}
					else
					{
						//if(id!=iframeid)
						//{
							top.$.messager.alert('��ʾ',result.page_name+"��"+result.message,'info',function(){
							});	
						//}
					}
				}
				else
				{
					top.$.messager.alert('��ʾ',result.message,'info',function(){
					});	
				}
			}
			else
			{
				 $.messager.confirm('����ȷ��',"��ǰû�б����û����֤���Ƿ��ύ��",function(r){  
					    if (r){
					    	summit(activdefId,procdefId,resultdata);
					    	
					    }else{
					    	 
					    }
				 });

			}
		}
		//����particiPants.jspҳ��
	function summit(activdefId,procdefId,result){
		
		
		var sub={
				url:ctx+"/workflow/workflow!getParticipantsOfWorkItem.action",
				type:'post',
				data:{"activdefId":activdefId,"procdefId":procdefId,"time":new Date()},
				success:function(data){		
					//alert(JSON.stringify(data));	
					if (data) {
						var obj={};
						obj=$.extend(obj,result);
						obj=$.extend(obj,proc);
						obj.data=data;
						//alert(JSON.stringify(obj));
						openInTopWindow({	
							title:"ѡ�������",
							id:"particiPants",
							//���ڿ�
							width: 300,
							//���ڸ�
							height: 300,
							modal: true,	
							src:ctx+"/jsp/common/partici-pants.jsp",
							destroy:true,
							onLoad:	function(){
								//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
								//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
								this.openerWinssssdow = window;
								//this.document.getElementById('name').value='����';
								//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
								this.args = {
									particpants: obj
								};			
								this.init(obj);
							}
						});									
					}else {							 							
							 
					}			
				}										
			};
			$.ajax(sub);
	}


	//����iframeҳ�����������
	function save()
	{	var result;
		var Iframe=document.getElementById(iframeid);
		if(Iframe.contentWindow.validate)
		{
			result=Iframe.contentWindow.validate(1);
			if(!result.result)
			{
				top.$.messager.alert('��ʾ',result.message,'info',function(){
				});
			}
			else
			{
				Iframe.contentWindow.submit();
			}
			
		}
		
	}



	//��ȡ��ַ������
	function GetQueryString(name)
	{
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r!=null) return unescape(r[2]); return null;
	}
	
	
	//�ǲ�
	function registerSave () {		
		
		
		var proc_id=proc.procId;
		//alert(lcslId);
		var result="failed";
		
		$.ajax({
			url:ctx+"/common/register!registerSave.action",
			data:{"proc_id":proc_id,"time":new Date()},
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			dataType:"json",
			async:false,
			success:function(data){
				//alert(data.result);
				//result=data.result;
				if(data.result=="success"){
					$("#btn_reg_save").linkbutton('disable');
					top.$.messager.alert('��ʾ',"�ǲ��ɹ���",'info',null);
					//return data.result;
				}else if(data.result=="exists"){
					top.$.messager.alert('��ʾ',"�Ѿ��ǲ����������²���",'info',null);
				}
				//return data.result;
			}
		});
		//return result;
	}
	
	//�Ǽǲ�Ԥ������
	function registerPreview () {	
		
		
		var objWindow = {
				//����Ԫ�ص�id
				id: 'wind_djbyl',
				//����iframe��src
				src: ctx+'/jsp/common/register-preview.jsp',
				//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
				destroy: true,
				//���ڱ���
				title: '�Ǽǲ�Ԥ��',
				//���ڿ�
				width: 850,
				//���ڸ�
				height: 680,
				modal: true,
				//������iframe��window�����onLoad�ص���������
				onLoad:	function(){
					proc.regcode=regcode;
					//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
					//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
					//this.openerWindow = window;
					
					//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
					this.args = {
						///userDataGrid: userDataGrid
					};
					this.init(proc);
				}
			};
		var bus_type=proc.procdefName;
		//����Ȩ
		if(enumdata.ALL == bus_type_id){
			objWindow.src = ctx+'/jsp/common/register-preview.jsp';
		}
		//ʹ��Ȩ
		else if(enumdata.USE == bus_type_id){
			objWindow.src = ctx+'/jsp/common/registerbook/registeruse.jsp';
		}
		//��Ѻ
		else if(enumdata.MORT == bus_type_id){
			objWindow.src = ctx+'/jsp/common/registerbook/registermort.jsp';
		}
		//���Ǽ�
		else if(enumdata.ATTACH == bus_type_id){
			objWindow.src = ctx+'/jsp/common/registerbook/registerattach.jsp';
		}
		//����Ǽ�
		else if(enumdata.DEMURRER == bus_type_id){
			objWindow.src = ctx+'/jsp/common/registerbook/registerdemurrer.jsp';
		}
		//���ز�֤ע���Ǽ�
		else if(enumdata.REALESTATE_CAN == bus_type_id){
			$.ajax({
				url:ctx+'/common/register!getReg_relationship.action',
				dataType : 'json',
				type : 'post',
				data : {"proc_id":proc_id,"time":new Date()},
				async:false,
				success : function(data) {
					if(enumdata.house == data.reg_unit_type){
						objWindow.src = ctx+'/jsp/common/register-preview.jsp';
					}else if(enumdata.parcel == data.reg_unit_type){
						objWindow.src = ctx+'/jsp/common/registerbook/registeruse.jsp';
					}
				}
			});
			//objWindow.src = ctx+'/common/register-preview.action';
		}else if(enumdata.CORRECTION == bus_type_id){
			$.ajax({
				url:ctx+'/common/register!getReg_relationship.action',
				dataType : 'json',
				type : 'post',
				data : {"proc_id":proc_id,"time":new Date()},
				async:false,
				success : function(data) {
					
					if(enumdata.parcel == data.reg_unit_type){
						objWindow.src = ctx+'/jsp/common/registerbook/registeruse.jsp';
					}else if(enumdata.house == data.reg_unit_type || enumdata.build == data.reg_unit_type){
						objWindow.src = ctx+'/jsp/common/register-preview.jsp';
					}
				}
			});
			
		}
		else if(enumdata.REISSUE == bus_type_id){
			$.ajax({
				url:ctx+'/common/register!getReg_relationship.action',
				dataType : 'json',
				type : 'post',
				data : {"proc_id":proc_id,"time":new Date()},
				async:false,
				success : function(data) {
					
					if(enumdata.parcel == data.reg_unit_type){
						objWindow.src = ctx+'/jsp/common/registerbook/registeruse.jsp';
					}else if(enumdata.house == data.reg_unit_type || enumdata.build == data.reg_unit_type){
						objWindow.src = ctx+'/jsp/common/register-preview.jsp';
					}
				}
			});
			
		}
		openInTopWindow(objWindow);
	};
	
	
		
	function isWin(obj){ 
		return /Window|global/.test({}.toString.call(obj))||obj==obj.document&&obj.document!=obj; 
	} 
	
	//�жϵ�ǰҵ���Ƿ����ڱ���     �ڲ���ʾ�Ա�ע  ˾���ö�����   �������������ʾ�Ǽǲ�Ԥ��  �͵ǲ�
	function getBusTypeParentId(){
		
		$.ajax({
			url:ctx+"/common/certificate!getBusTypeParentId.action",
			data:{"proc_id":proc.procId,"time":new Date()},
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			dataType:"json",
			async:false,
			success:function(data){
				//alert(data.bus_type_id);
				bus_type_id = data.bus_type_id;
				
				if(enumdata.PRE_SALE_RECORD == bus_type_id || enumdata.TIP_RECORD == bus_type_id || enumdata.JUDICIAL_TRANSFER == bus_type_id){
					if(proc.wiName=="��׼"){
						//Ԥ�۱���ҵ��  ��ʾ�޸ı���״̬ ��Ť
						if(enumdata.PRE_SALE_RECORD == bus_type_id){//if(enumdata.PRESALE_BACKUP == getBusTypeId()){
							$("#btn_presale_status").css("visibility","visible");
							if(!isRecorded()){
								$("#btn_presale_status").linkbutton('enable');
							}
						}
					}
				}else{
					if(proc.wiName=="��׼"){
						
						
						$("#btn_regPrev").css("visibility","visible");
						$("#btn_reg_save").css("visibility","visible");
						//����Ѿ��ǲ�����õǲ���Ť
						if(isRegisterSave()){
							$("#btn_reg_save").linkbutton('disable');
						}
						
					}
					if(enumdata.MORT_CAN_REG == getBusTypeId() || enumdata.MAX_MORT_CAN_REG == getBusTypeId()){
						if(proc.wiName=="����"){
							
							$("#btn_regPrev").css("visibility","visible");
							$("#btn_reg_save").css("visibility","visible");
							//����Ѿ��ǲ�����õǲ���Ť
							if(isRegisterSave()){
								$("#btn_reg_save").linkbutton('disable');
							}
							
						}
					}
				}
			}
			
		});
		
	}
	
	/**********************************************************************************
	*��������: getBusTypeId
	*����˵��: ��ȡ��ǰҵ������ID 
	*����˵��: 
	*�� �� ֵ: busTypeId ҵ������ID 
	*��������: Joyon
	*��������: 2014-03-01
	*�޸���ʷ: 
	***********************************************************************************/	
function getBusTypeId(){
	var busTypeId;
	$.ajax({
		url:ctx+"/common/certificate!getBusTypeId.action",
		data:{"proc_id":proc.procId,"time":new Date()},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		dataType:"json",
		async:false,
		success:function(data){
			busTypeId = data.busTypeId;
		}
	});
	
	//alert(busTypeId);
	return busTypeId;
}
/**********************************************************************************
*��������: isRegisterSave
*����˵��: �ж��Ƿ�ǲ� 
*����˵��: 
*�� �� ֵ: true/false  �Ѿ��ǲ�/δ�ǲ�
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/	
function isRegisterSave(){
	var result = false;
	$.ajax({
		url:ctx+"/common/register!isRegisterSave.action",
		data:{"proc_id":proc.procId,"time":new Date()},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		dataType:"json",
		async:false,
		success:function(data){
			if(data.result=="1"){
				result = true;
			}
		}
		
	});
	
	return result;
}
/**********************************************************************************
*��������: isRecorded
*����˵��: �ж��Ƿ��Ѿ����� 
*����˵��: 
*�� �� ֵ: true/false  �Ѿ��ǲ�/δ�ǲ�
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/	
function isRecorded(){
	var result = false;
	$.ajax({
		url:ctx+"/backup/presale!isRecorded.action",
		data:{"proc_id":proc.procId,"time":new Date()},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		dataType:"json",
		async:false,
		success:function(data){
			if(data.result=="1"){
				result = true;
			}
		}
		
	});
	
	return result;
}

/**********************************************************************************
*��������: modPresaleStatus
*����˵��: �޸�Ԥ�۱���״̬
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/	
function modPresaleStatus(){
	$.ajax({
		url:ctx+"/backup/presale!setPreSaleState.action",
		data:{"proc_id":proc.procId,"time":new Date()},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		dataType:"json",
		async:false,
		success:function(data){
			$("#btn_presale_status").linkbutton('disable');
			top.$.messager.alert('��ʾ',"�޸ĳɹ�",'info',null);
			//ˢ�µ�ǰѡ����ӽڵ�ҳ��
			var selectedNode = $('#bustree').tree('getSelected');
			if(selectedNode){
				openChildPage(selectedNode);
			}
		},error:function(data){
			top.$.messager.alert('��ʾ',"�޸�ʧ�ܣ�ϵͳ�ڲ�����",'error',null);
		}
		
	});
}

/**********************************************************************************
*��������: emptyIframe
*����˵��: ��iframe�ÿ�
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/	
function emptyIframe(){
	$('#iframe').empty();
}

