/**********************************************************************************
*����������������:        refund_start_proc  ���������������Բ�����ʼ��
*                        respite_start_proc  �ݻ������������Բ�����ʼ��
*                        delay_start_proc  ���������������Բ�����ʼ��
*                        suspend_start_proc ���������������Բ�����ʼ��
*                        rejection_start_proc ���������������Բ�����ʼ��
*����˵��: �������̱����������̴������һЩ�������������̶���ID������ڵ�Id,��������������һЩ��̬����
*����: Sam
*��������: 2014��5��12�� 
*�޸���ʷ: 
***********************************************************************************/
var refund_start_proc = {
		wiId : "",
		procId : "",
		procdefId : "1302",
		activdefId : "start2",
		activName : "����"
};
var respite_start_proc = {
		wiId : "",
		procId : "",
		procdefId : "1299",
		activdefId : "start2",
		activName : "�ݻ�"
};
var delay_start_proc = {
		wiId : "",
		procId : "",
		procdefId : "1301",
		activdefId : "start2",
		activName : "����"
};
var suspend_start_proc = {
		wiId : "",
		procId : "",
		procdefId : "1300",
		activdefId : "start2",
		activName : "����"
};
var rejection_start_proc = {
		wiId : "",
		procId : "",
		procdefId : "1298",
		activdefId : "start2",
		activName : "����"
};
var spec_proc_add = {
	rejection:'���ؾ�����',
	respite:'�ݻ�������',
	suspend:'����������',
	delay:'����������',
	refund:'����������'
};

/**********************************************************************************
*���Զ���: jds_sqb_tw ���������ж���һ����������ִ�� ͬʱ��̬�ı�ѡ�����
*          wiId ������Id
*          proc ���ڽ���init()�����������Ĳ��� 
*          procId ���̶���Id
*          _procName ������
*          init_add �жϵ�ǰ״̬���´������̻��������Ѿ�����
*          selectedProc ���ڽ���ѡ�������
*          Iframe ������Ƕiframe
*����˵��: ����һЩȫ�ֱ���
*����: Sam
*��������: 2014��5��1�� 
*�޸���ʷ: 
***********************************************************************************/
var jds_sqb_tw;
var add_jds_sqb_tw;
var wiId;
var proc;
var procId;
var _procName;
var init_add;
var selectedProc;
var Iframe;

/**********************************************************************************
*��������: getChinaTip
*����˵��: ͨ���������Ĳ����õ���Ӧ��������Ϣ��ͬʱ��ʼ����Ӧ���̵�form��Ϣ
*��    ����val
*��������: Sam
*��������: 2014��5��1�� 
*�޸���ʷ: 
***********************************************************************************/
function getChinaTip(val){
	  var keyval;
	  if(val == 'REJECTJDS'){
		  keyval = enumdata.REJECTJDS ;
		  initEditRejectionForm(procId);
	  }else if(val == 'DEFERMENTJDS'){
		  keyval = enumdata.DEFERMENTJDS ;
		  initEditRespiteForm(procId);
	  }else if(val == 'DELAYSQS'){
		  keyval = enumdata.DELAYSQS ;
		  initEditDelayForm(procId);
	  }else if(val == 'HANGUPSQS'){
		  keyval = enumdata.HANGUPSQS ;
		  initEditSuspendForm(procId);
	  }else if(val == 'BACKLANGUAGESQS'){
		  initEditRefundForm(procId);
		  keyval = enumdata.BACKLANGUAGESQS ;
	  }
	  return decodeURI(keyval);
}
/**********************************************************************************
*��������: init
*����˵��: �����������Ӧ�����Ӵ��崫ֵ�������
*��    ����items
*��������: Sam
*��������: 2014��5��1�� 
*�޸���ʷ: 
***********************************************************************************/
function init(items){
	 var params = arguments;
	 proc = items;
	 _procName = items.procName;
	 procId = items.procId;
	 //
     if(!empty(params[1])){
    	 if(params[1] == 'true'){
    	   init_add = params[1];
    	 }
     }
	 jds_sqb_tw = getChinaTip($.getUrlParam('jds_sqs_tw'));
	 if(empty(jds_sqb_tw)){
		 $('#jds_sqb_tw').attr('title','�����˻صǼ���'); 
		 init_add = 'true';
		 jds_sqb_tw = enumdata.BACKLANGUAGESQS;
	 }else if(jds_sqb_tw == enumdata.BACKLANGUAGESQS){
		 if(proc.activName == '����'){
		  add_jds_sqb_tw = spec_proc_add.refund;
		  $('#jds_sqb_tw').attr('title',add_jds_sqb_tw); 
		 }else
		  $('#jds_sqb_tw').attr('title',jds_sqb_tw);
	 }else if(jds_sqb_tw == enumdata.REJECTJDS){
		 if(!empty(init_add) && init_add == 'true'){
			 add_jds_sqb_tw = spec_proc_add.rejection;
			 $('#jds_sqb_tw').attr('title',add_jds_sqb_tw); 
		 }else
			  $('#jds_sqb_tw').attr('title',jds_sqb_tw);
	 }else if(jds_sqb_tw == enumdata.DEFERMENTJDS){
		 if(!empty(init_add) && init_add == 'true'){
			 add_jds_sqb_tw = spec_proc_add.respite;
			 $('#jds_sqb_tw').attr('title',add_jds_sqb_tw); 
		 }else
			  $('#jds_sqb_tw').attr('title',jds_sqb_tw);
	 }else if(jds_sqb_tw == enumdata.DELAYSQS){
		 if(!empty(init_add) && init_add == 'true'){
			 add_jds_sqb_tw = spec_proc_add.delay;
			 $('#jds_sqb_tw').attr('title',add_jds_sqb_tw); 
		 }else
			  $('#jds_sqb_tw').attr('title',jds_sqb_tw);
	 }else if(jds_sqb_tw == enumdata.HANGUPSQS){
		 if(!empty(init_add) && init_add == 'true'){
			 add_jds_sqb_tw = spec_proc_add.suspend;
			 $('#jds_sqb_tw').attr('title',add_jds_sqb_tw); 
		 }else
			  $('#jds_sqb_tw').attr('title',jds_sqb_tw);
	 }
	 
	 if(jds_sqb_tw == enumdata.BACKLANGUAGESQS){
		 $('#djcontent').attr('title','��������');
	 }else{
		 $('#djcontent').attr('title','�Ǽ�����');
	 }
	 if(!empty(proc.activName))
	    $('#note').text(proc.activName);
	 //
	 initTab();
	 //
	 if(!empty(init_add) && init_add == 'true'){
		 var selected = add_jds_sqb_tw;
		 $("#save").linkbutton('enable');
		  var ctab = $('#tt').tabs('tabs'), opts;
	        for (var i = 0; i < ctab.length; i++) {
	            opts = ctab[i].panel('options');
	            if (opts.title != selected) {
	                ctab[i].hide();
	                opts.tab.hide();
	            }
	        }
	        if(selected == spec_proc_add.refund){
	        	 $('#refund_options').css('display','none');
	    	}else if(selected == spec_proc_add.rejection){
	    		 $('#rejection_options').css('display','none');
	    	}else if(selected == spec_proc_add.delay){
	    		 $('#delay_options').css('display','none');
	    	}else if(selected == spec_proc_add.suspend){
	    		 $('#suspend_options').css('display','none');
	    	}else if(selected == spec_proc_add.respite){
	    		 $('#respite_options').css('display','none');
	    	}
	 }else{
		 $("#save").linkbutton('enable');
		 examineInit();
		 examineHiddenElement();
/*		 if(!(jds_sqb_tw == enumdata.BACKLANGUAGESQS))*/
		 selectedProc = getCheckcedbusdata(items.procId);
		 // setTimeout(loadWorkIframe,100);
		 //var Iframe=document.getElementById("work-iframe");
	 }
	 if(proc.activName == '����'){
		 $('#refund_options').css('display','none');
		 $("#save").linkbutton('disable');
	 }
	 initProcBtn();
}
/**********************************************************************************
*��������: loadWorkIframe
*����˵��: ������Ƕ����
*��������: Sam
*��������: 2014��5��1�� 
*�޸���ʷ: 
***********************************************************************************/
function loadWorkIframe(){
	    Iframe = getIframeDom('work-iframe');
		if(Iframe.init){
			//alert("onselect...");
			//Iframe.contentWindow.getElementById("");
			Iframe.init(selectedProc);
//				if(openiframe_count==0){
//					alert("first...");
//					Iframe.contentWindow.emptyIframe();
//					Iframe.contentWindow.init(selectedProc);
//				}
//				openiframe_count++;
		}
}
/**********************************************************************************
*��������: getIframeDom
*����˵��: �õ���Ƕ����dom����ͨ����Ƕiframe����id��
*��    ����iframeId
*��������: Sam
*��������: 2014��5��1�� 
*�޸���ʷ: 
***********************************************************************************/
function getIframeDom(iframeId) {    
    return document.getElementById(iframeId).contentWindow || window.frames[iframeId];    
}    
/**********************************************************************************
*��������: initTab
*����˵��: ��ʼ��ѡ�������ѡ���ѡ��ʱ�������¼�
*��������: Sam
*��������: 2014��5��1�� 
*�޸���ʷ: 
***********************************************************************************/
function initTab(){
	var currenttitle;
	if(!empty(init_add) && init_add == 'true'){
		currenttitle = add_jds_sqb_tw;
	}else{
		currenttitle = jds_sqb_tw;
	}
	$('#tt').tabs({  
	    border:false,  
	    onSelect:function(title){  
	    	if(title== currenttitle){
	            if(!empty(init_add) && init_add == 'true'){
	            	 $('#notice_date').datebox('setValue',getCurrentDate());
	    		}
	    	}else if(title == currenttitle){
		    		if(!empty(init_add) && init_add == 'true'){
		    		    $('#rej_code').text("*************");
		    		    $('#app_date_str').val(getCurrentDate('China'));
		    		    $('#app_date').val(getCurrentDate());
		    		}
	    	}else if(title == currenttitle){
	    		    if(!empty(init_add) && init_add == 'true'){
		    		    $('#res_code').text("*************");
		    		    $('#app_date_str').val(getCurrentDate('China'));
		    		    $('#app_date').val(getCurrentDate());
		    		}
	    	}else if(title == currenttitle){
	    		    if(!empty(init_add) && init_add == 'true'){
		    		    $('#showhisdelayrecords').linkbutton('disable');
		    		    $('#delay_no').val("*************");
		    		}
	    	}else if(title == currenttitle){
	    		if(!empty(init_add) && init_add == 'true'){
	    		    $('#showhanguprecords').linkbutton('disable');
	    		    $('#sus_no').val("*************");
	    		}
	    	}else if(title == '��������' || title == '�Ǽ�����'){
	    		loadWorkIframe();
	    	}
	    }
	}); 
}
/**********************************************************************************
*��������: initProcBtn
*����˵��: ��ȡ��ǰ���̻��ڵ��ύ��Ť
*��������: joyon
*��������: 2014��4��21�� 
*�޸���ʷ: 
***********************************************************************************/
function initProcBtn(){
	if(!empty(init_add) && init_add == 'true'){
		initProcessStartProperties();
	}
	$.ajax({
		url:ctx+"/common/work-window!getFormTreeurl.action",
		type:"post",
		dataType:"json",
		data:{"wiId":wiId,"procId":proc.procId,"procdefId":proc.procdefId,"activdefId":proc.activdefId,"activName":proc.activName,"time":new Date()},
		success:function(data){
			for(var i=0;i<data[0].rows.length;i++){
						(function(){
							var tempData=data[0].rows[i];
							$("#operation").append("<a id='"+data[0].rows[i].To+i+"' >"+data[0].rows[i].DisplayName+"</a>");							
							var btnId=document.getElementById(data[0].rows[i].To+i);
							$(btnId).linkbutton({  
								  iconCls: 'icon-search'  
							}); 
							$(btnId).click(function(){
								getparticiPants(this,tempData);
							});
						})()	
			}
		}
	});
}
/**********************************************************************************
*��������: valideteSelection
*����˵��: ��֤ѡ����������url����ֵjds_sqb_tw�жϵ�ǰ�����ĸ����̣�ͬʱ���ö�Ӧ���̵���֤����
*��������: Sam
*��������: 2014��5��1�� 
*�޸���ʷ: 
***********************************************************************************/
function valideteSelection(){
	var result;
	if(!jds_sqb_tw){
		jds_sqb_tw = getChinaTip($.getUrlParam('jds_sqs_tw'));
	}
	if(jds_sqb_tw == enumdata.BACKLANGUAGESQS){
		result = validateRefundForm();
	}else if(jds_sqb_tw == enumdata.REJECTJDS){
		result = validateRejectionForm();
	}else if(jds_sqb_tw == enumdata.DELAYSQS){
		result = validateDelayForm();
	}else if(jds_sqb_tw == enumdata.HANGUPSQS){
		result = validateSuspendForm();
	}else if(jds_sqb_tw == enumdata.DEFERMENTJDS){
		result = validateRespiteForm();
	}
	return result;
}
/**********************************************************************************
*��������: initProcessStartProperties
*����˵��: ��ʼ�������������ԣ�����url����ֵjds_sqb_tw�жϵ�ǰ�����ĸ����̣�ͬʱ�����涨��
*��������: Sam
*��������: 2014��5��1�� 
*�޸���ʷ: 
***********************************************************************************/
function initProcessStartProperties(){
	if(!jds_sqb_tw){
		jds_sqb_tw = getChinaTip($.getUrlParam('jds_sqs_tw'));
	}
	if(jds_sqb_tw == enumdata.BACKLANGUAGESQS){
		   wiId	= refund_start_proc.wiId;
	       proc = refund_start_proc;
	}else if(jds_sqb_tw == enumdata.REJECTJDS){
		   wiId	= rejection_start_proc.wiId;
	       proc = rejection_start_proc;
	}else if(jds_sqb_tw == enumdata.DELAYSQS){
		   wiId	= delay_start_proc.wiId;
	       proc = delay_start_proc;
	}else if(jds_sqb_tw == enumdata.HANGUPSQS){
		   wiId	= suspend_start_proc.wiId;
	       proc = suspend_start_proc;
	}else if(jds_sqb_tw == enumdata.DEFERMENTJDS){
		   wiId	= respite_start_proc.wiId;
	       proc = respite_start_proc;
	}
}
/**********************************************************************************
*��������: getparticiPants
*����˵��: ��֤�Ƿ������̴��������ύ���̽�����һ���ڵ�
*��    ����btn,data
*��������: Sam�޸�
*��������: 2014��5��1�� 
*�޸���ʷ: 
***********************************************************************************/
function getparticiPants(btn,data){
	var vaResult; 
	if(empty(init_add)){
		vaResult = validate();
	}else{
		vaResult = valideteSelection();
		if(vaResult.result && init_add == 'true'){
			createAndStartProcessInstance();
			return;
		}
	}
	//��֤��ͨ��
	if(!vaResult.result){
		top.$.messager.alert('��ʾ',vaResult.message,'info',null);
		return;
	}
	var procdefId=proc.procdefId;			
	var activdefId=data.To;			 
	var resultdata=data;
	var wiId=proc.wiId;
	//alert(data.DisplayName);
	
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
	 	};
	 	$.ajax(end);
	 	closeInTopWindow('open_app','destroy');
	 	openerWindow.location.reload();
	 }else{
		 summit(activdefId,procdefId,resultdata,data.DisplayName);
	 }
}
/**********************************************************************************
*��������: createAndStartProcessInstance
*����˵��: �����������µ�����
*��������: Sam
*��������: 2014��5��1�� 
*�޸���ʷ: 
***********************************************************************************/
function createAndStartProcessInstance(){
	var procdefId = proc.procdefId;	
	var procName = proc.activName+"-"+_procName;
	var regCode = $('#regCode').val();
	var busId = $('#bus_id').val();
	var specifyId = getSpecifyId();
	if(empty(specifyId)){
		top.$.messager.alert('��ʾ',"������Ϊ���棬�뱣����ٽ����ύ���룬лл��",'warn',function(){});
		return;
	}
	var startproc = {
			url: ctx + "/workflow/workflow!doApplyProcess.action?time="+new Date(),
			type:'post',
			data:{"regCode":regCode,"specifyId":specifyId,"busId":busId,"procdefId":procdefId,"procName":procName,"time":new Date()},
			success:function(data){
				//submitUpdateSP();
				var data = $.parseJSON(data);
				if (data.success) {
					top.$.messager.alert('��ʾ',data.tipMessage,'info',function(){
						openerWindow.openerWindow.location.reload();
						closeInTopWindow('add_app','destroy');
						closeInTopWindow('open_app','destroy');
						closeInTopWindow('wind_djby2','destroy');
					});
				}else{
					top.$.messager.alert('����', data.errorMessage+'['+data.tipMessage+']��', 'error', function(){});
				}
			}
	};
	$.ajax(startproc);
}

/**********************************************************************************
*��������: summit
*����˵��: ����particiPants.jspҳ��
*��������: joyon
*��������: 2014��4��15�� 11:46:54
*�޸���ʷ: 
***********************************************************************************/
function summit(activdefId,procdefId,result,btnName){
	var sub={
			url:ctx+"/workflow/workflow!getParticipantsOfWorkItem.action",
			type:'post',
			data:{"activdefId":activdefId,"procdefId":procdefId,"procId":proc.procId,"btn_name":btnName,"time":new Date()},
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
						src: ctx + "/jsp/common/partici-pants.jsp",
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

/**********************************************************************************
*��������: getCheckcedbusdata
*����˵��: ��ȡ��ǰ������̵�  �����������������
*��������: joyon
*��������: 2014��4��15�� 11:46:54
*�޸���ʷ: 
***********************************************************************************/
function getCheckcedbusdata(proc_id){
	var resultData;
	$.ajax({
		url:ctx+"/qualityinspection/qualityinspection!getCheckcedbusdata.action",
		type:"post",
		dataType:"json",
		data:{"proc_id":proc_id,"state":'1'},
		async:false,
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			resultData = data;	
		}
	});
	return resultData;
}

/*function submitUpdateSP(){
	if(!jds_sqb_tw){
		jds_sqb_tw = getChinaTip($.getUrlParam('jds_sqs_tw'));
	}
	if(jds_sqb_tw == enumdata.BACKLANGUAGESQS){
		    $('#refund_status').val(enumdata.PROCONTHEMARCH);
			$('#refundForm').submit();
	}else if(jds_sqb_tw == enumdata.REJECTJDS){
			$('#rej_status').val(enumdata.PROCONTHEMARCH);
	        $('#rejectionForm').submit();
	}else if(jds_sqb_tw == enumdata.DELAYSQS){
		    $('#delay_status').val(enumdata.PROCONTHEMARCH);
		    $('#delayForm').submit();
	}else if(jds_sqb_tw == enumdata.HANGUPSQS){
		    $('#sus_status').val(enumdata.PROCONTHEMARCH); 
		    $('#suspendForm').submit();
	}else if(jds_sqb_tw == enumdata.DEFERMENTJDS){
		    $('#res_status').val(enumdata.PROCONTHEMARCH);  
		    $('#respiteForm').submit();
	}
				
}*/
/**********************************************************************************
*��������: getSpecifyId
*����˵��: �������id��ţ�����url����ֵjds_sqb_tw�жϵ�ǰ�����ĸ����̣�ͬʱ�����̶�Ӧ��id��Ż�ȡ��
*��������: Sam
*��������: 2014��5��1�� 
*�޸���ʷ: 
***********************************************************************************/
function getSpecifyId(){
      var id;
    if(!jds_sqb_tw){
  		jds_sqb_tw = getChinaTip($.getUrlParam('jds_sqs_tw'));
  	}
  	if(jds_sqb_tw == enumdata.BACKLANGUAGESQS){
  		   id = $('#refund_id').val();
  	}else if(jds_sqb_tw == enumdata.REJECTJDS){
  		   id = $('#rej_id').val();
  	}else if(jds_sqb_tw == enumdata.DELAYSQS){
  		   id = $('#delay_id').val();
  	}else if(jds_sqb_tw == enumdata.HANGUPSQS){
  		   id = $('#sus_id').val();
  	}else if(jds_sqb_tw == enumdata.DEFERMENTJDS){
  		   id = $('#res_id').val();
  	}
  	return id;;
}
/**********************************************************************************
*��������: submitsp
*����˵��: �����´��� form���ύ��Ҳ�����url����ֵjds_sqb_tw�жϵ�ǰ�����ĸ����̣������ύ�ĸ�����
*��������: Sam
*��������: 2014��5��1�� 
*�޸���ʷ: 
***********************************************************************************/
function submitsp(){
	if(!jds_sqb_tw){
		jds_sqb_tw = getChinaTip($.getUrlParam('jds_sqs_tw'));
	}
	if(jds_sqb_tw == enumdata.BACKLANGUAGESQS){
		if(!empty(init_add) && init_add == 'true'){
			if(empty($('#refund_id').val()))
				   $('#refundForm').submit();
			else
				top.$.messager.alert('��ʾ','�����Ѿ��������豣�棡','info',function(){});
		}else{
			saveExamine();
		}
	}else if(jds_sqb_tw == enumdata.REJECTJDS){
		if(!empty(init_add) && init_add == 'true'){
			if(empty($('#rej_id').val())){
				setLaw();
				if(!validateDate())
					return;
				$('#rejectionForm').submit();
			}else
				top.$.messager.alert('��ʾ','�����Ѿ��������豣�棡','info',function(){});
		}else{
			saveExamine();
		}
	}else if(jds_sqb_tw == enumdata.DELAYSQS){
		if(!empty(init_add) && init_add == 'true'){
			if(empty($('#delay_id').val()))
				   $('#delayForm').submit();
			else
				top.$.messager.alert('��ʾ','�����Ѿ��������豣�棡','info',function(){});
		}else{
			saveExamine();
		}
	}else if(jds_sqb_tw == enumdata.HANGUPSQS){
		if(!empty(init_add) && init_add == 'true'){
			if(empty($('#sus_id').val()))
				   $('#suspendForm').submit();
			else
				top.$.messager.alert('��ʾ','�����Ѿ��������豣�棡','info',function(){});
		}else{
			saveExamine();
		}
	}else if(jds_sqb_tw == enumdata.DEFERMENTJDS){
		if(!empty(init_add) && init_add == 'true'){
			if(empty($('#res_id').val())){
				if(!validateDate())
					return;
				 $('#respiteForm').submit();
			 }else
				 top.$.messager.alert('��ʾ','�����Ѿ��������豣�棡','info',function(){});
		}else{
			saveExamine();
		}
	}
}
/**********************************************************************************
*��������: validateDate
*����˵��: ������֤��������Ҫ������ں͹��������������ڸ�ʽ
*��������: Sam
*��������: 2014��5��1�� 
*�޸���ʷ: 
***********************************************************************************/
function validateDate(){
	var app_date_str = $('#app_date_str').val();
	var pattern = /(\d{4})��(\d{2})��(\d{2})��/;
	var newdateformat;
	var date;
    try{
    	newdateformat = app_date_str.replace(pattern,'$1-$2-$3');
    	date = new Date(Date.parse(newdateformat.replace(/-/g,'/')));
    	$('#app_date').val(newdateformat);
    	return true;
    }catch(exception){
    	top.$.messager.alert('����', '��������ʽ������', 'error', function(){});
    	return false;
    }
}
/*********************************************************
*��������: getPreProcId
*����˵��: �õ�ǰ����Id
*��������: Sam
*��������: 2014��5��1�� 
*�޸���ʷ: 
 **********************************************/
function getPreProcId(){
	var reg_code = $('#reg_code').val();
	if(empty(reg_code)){
		return;
	}
	$.ajax({
		url:ctx+"/workflow/workflow!getPreProcIdByPreRegCodeFromBusMain.action",
 		type:'post',
 		dataType:"json",
 		data:{"regCode":reg_code,"time":new Date()},
 		success:function(data){
 			if(data)
 			 selectedProc = getCheckcedbusdata(data.PROC_ID);
 		}
	});
}
