/*********************************************************************************
*��  ��  ��  ��: inspection-index.js
*��  ��  ��  ��: ������� �����ҳ��js
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: joyon
*��  ��  ��  �ڣ� 2014��4��15�� 
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/

var selectedProc;						//�������ҳ��ѡ��İ���
var iframeid1;
//�洢����������
var iframeData1;
var work_open_count=0;					//�������ƴ򿪹�����ҳ�����
var work_open_examine=0;				//���Tabѡ��򿪴���
var wiId;
var proc;
var proc_id;
var proc_node;							//���̽ڵ�
var select_qua_proc_node;				//��������¼��ѡ�������   ��ѡ��ʱ��ֵ
var state = {
		string1 : "���",
		string2 : "����������",
		string3 : "�а��˴���",
	};
/**********************************************************************************
*��������: 
*����˵��: ҳ���ʼ��
*��������: joyon
*��������: 2014��4��16�� 15:10:49
*�޸���ʷ: 
***********************************************************************************/
//window.onload = function(){
function init(data){
	//window.resizeTo(1300,600);
	//alert(window.outerwidth);
	proc = data;
	proc_node = proc.activName;
	
	//��ȡ����  �ύ��Ť
	initProcBtn();
	//alert(JSON.stringify(data));
	//top.$.messager.alert('��ʾ',JSON.stringify(data),'info',null);
	//window.proc = proc;
	//��ȡ�����ҵ�����������
	selectedProc = getCheckcedbusdata(data.procId);
	//top.$.messager.alert('��ʾ',JSON.stringify(selectedProc),'info',null);
//	selectedProc.procId = selectedProc.PROC_ID;
//	selectedProc.procdefId = selectedProc.PROCDEF_ID;
//	selectedProc.activdefId = selectedProc.ACTIVDEF_ID;
//	selectedProc.activName = selectedProc.NAME;
//	selectedProc.wiId = selectedProc.WI_ID;
	$('#note').text(proc.activName);
	
	//��ȡ��ǰ����  ����Ľڵ�
	
	//��ʼTabѡ�
	initTab();
	
	
	//��ʼ���������ټ�¼�� ---�ĵ��ڵ�ѡ��ʱ��ʼ��
	//initQualityrecord(); 
	
	setStateIndex();
	//examineInit();
	examineInit();
	//��ȡ��ǰ�Ƿ��ǲ����
	getsIs_error_state();
	
	getCurprocnode();
	
	
	//��ʼ������֪ͨ��dialog
	initCorrectnoticeDialog();
}
function setStateIndex(){
//    if(proc_node == state1.string1){
//	       $("#fsyj").attr("disabled","disabled");
//	       $("#shyj1").attr("disabled","disabled");
//    };
//    if(proc_node == state1.string2){
//  	  $("#csyj").attr("disabled","disabled");
//	       $("#shyj1").attr("disabled","disabled");
//    };
//    if(proc_node == state1.string3){
//  	  $("#csyj").attr("disabled","disabled");
//  	  $("#fsyj").attr("disabled","disabled");
//	      };
    if(proc_node != state.string1){
    	//alert($("#table_quality_record input").size());
    	$("#table_quality_record input").attr("disabled","disabled");
    }
}

/**********************************************************************************
*��������: initTab
*����˵��: ��ʼ����ǰ��tabѡ�
*��������: joyon
*��������: 2014��4��21�� 
*�޸���ʷ: 
***********************************************************************************/
function initTab(){
	$('#tt').tabs({  
	    border:false,  
	    onSelect:function(title){  
	    	if(title=='���ĳ��'){
	    		if(work_open_count==0){
	    			var Iframe=document.getElementById("work-iframe");
		    		if(Iframe.contentWindow.init){
		    			//alert(Iframe.contentWindow.init);
		    			Iframe.contentWindow.init(selectedProc);
		    		}
	    		}
	    		work_open_count++;
	    		
	    	}else if(title=="������"){
	    		if(work_open_examine==0){
	    			//examineInit();
	    		}
	    		$("#save").linkbutton('enable');
	    		work_open_examine++;
	    	}
	    }  
	}); 
}
/**********************************************************************************
*��������: getCurprocnode
*����˵��: ��ȡ��ǰ���� ����Ľڵ�
*��������: joyon
*��������: 2014��4��21�� 
*�޸���ʷ: 
***********************************************************************************/
function getCurprocnode(){
	var openiframe_count = 0;					//�����ж�iframe�򿪶��ٴ�
	//����ѡ��  ������
	$('#proc_node').combobox({  
	    url:ctx+"/qualityinspection/qualityinspection!getHistoryProcActivity.action?proc_id="+selectedProc.procId,  
	    valueField:'activdefId',  
	    textField:'name',
	    onSelect: function(rec){  
	    	//alert(JSON.stringify(rec));
	    	selectedProc.activdefId = rec.activdefId;
	    	selectedProc.activName = rec.name;
	    	var Iframe=document.getElementById("work-iframe");
			if(Iframe.contentWindow.init){
				//alert("onselect...");
				//Iframe.contentWindow.getElementById("");
				
				Iframe.contentWindow.emptyIframe();
				Iframe.contentWindow.init(selectedProc);
//				if(openiframe_count==0){
//					alert("first...");
//					Iframe.contentWindow.emptyIframe();
//					Iframe.contentWindow.init(selectedProc);
//				}
//				openiframe_count++;
				
			}
	    },
	    onLoadSuccess:function(data){
	    	if(data.length>0){
	    		$('#proc_node').combobox("select",data[0].activdefId);
	    	}
	    	
	    }
	});  
	
	//��������¼��  ����ѡ��  ������
	$('#qua_proc_node').combobox({  
	    url:ctx+"/qualityinspection/qualityinspection!getHistoryProcActivity.action?proc_id="+selectedProc.procId,  
	    valueField:'activdefId',  
	    textField:'name',
	    onSelect: function(rec){  
	    	//alert(JSON.stringify(rec));
	    	select_qua_proc_node = rec;					//����ǰ���̽ڵ㸳ֵ
//	    	selectedProc.activdefId = rec.activdefId;
//	    	selectedProc.activName = rec.name;
//	    	var Iframe=document.getElementById("work-iframe");
//			if(Iframe.contentWindow.init){
//				//alert(Iframe.contentWindow.init);
//				//Iframe.contentWindow.getElementById("");
//				Iframe.contentWindow.emptyIframe();
//				Iframe.contentWindow.init(selectedProc);
//				
//			}
	    	//ÿ��ѡ��ڵ�ʱ��ʼ��һ��
	    	initQualityrecord();
	    },
	    onLoadSuccess:function(data){
	    	if(data.length>0){
	    		$('#qua_proc_node').combobox("select",data[0].activdefId);
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
	$.ajax({
		url:ctx+"/common/work-window!getFormTreeurl.action",
		type:"get",
		dataType:"json",
		data:{"wiId":wiId,"procId":proc.procId,"procdefId":proc.procdefId,"activdefId":proc.activdefId,"activName":proc.activName,"time":new Date()},
		success:function(data){
			//alert(JSON.stringify(data));
			
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
		data:{"proc_id":proc_id},
		async:false,
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			resultData = data;	
		}
	});
	return resultData;
}

//�ύ
function getparticiPants(btn,data){
	var vaResult = validate();
	//��֤��ͨ��
	if(!vaResult.result){
		top.$.messager.alert('��ʾ',vaResult.message,'info',null);
		return;
	}
	
	var procdefId=proc.procdefId;			
	var activdefId=data.To;			 
	var resultdata=data;
	var wiId=proc.wiId;
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
	 	openerWindow.location.reload();
	 	closeInTopWindow('open_app','destroy');
	 }else{
		 
	}
	
	summit(activdefId,procdefId,resultdata,data.DisplayName);
	//alert(data.DisplayName);
	
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
			//dataType:'json',
			data:{"activdefId":activdefId,"procdefId":procdefId,"procId":proc.procId,"btn_name":btnName,"time":new Date()},
			success:function(data){		
				//alert(JSON.stringify(data));	
				if (data) {
					var obj={};
					obj=$.extend(obj,result);
					obj=$.extend(obj,proc);
					obj.data=data;
					//alert(JSON.stringify(obj.data));
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
//							this.args = {
//								particpants: obj
//							};			
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
*��������: initQualityrecord
*����˵��: ��ʼ������
*��������: joyon
*��������: 2014��4��15�� 11:46:54
*�޸���ʷ: 
***********************************************************************************/
function initQualityrecord(){
	var appendStr = "";					//������ʱappendStr
	$("#table_quality_record").empty();
	appendStr="<tr><td>���</td><td>��������ָ����</td><td></td><td></td></tr>";
	$("#table_quality_record").append(appendStr);
	
	//�����ñ��ȡ����ָ����
	var qualityrecordData;
	$.ajax({
		url:ctx+'/qualityinspection/qualityinspection!getChkQualitytargetinfo.action',
		type:"post",
		dataType:"json",
		data:{"proc_id":proc_id},
		async:false,
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			qualityrecordData = data.rows;	
		}
	});
	//alert(JSON.stringify(qualityrecordData));
	//������������  ��ӵ������
	if(qualityrecordData){
		for(var i=0;i<qualityrecordData.length;i++){
			appendStr="<tr><td>"+(parseInt(i)+1)+"</td><td>"+qualityrecordData[i].qua_index+"</td><td><input type='checkbox' id='"+qualityrecordData[i].qua_tar_id+"' onclick='cbxClick(this)'/></td><td></td></tr>";
			$("#table_quality_record").append(appendStr);
		}
	}
	
	//���ݻ���
	var reledData = getReledQualitydata();
	//alert(JSON.stringify(reledData));
	if(reledData){
		if(reledData.length>0){
			for(var i=0;i<reledData.length;i++){
				$("#"+reledData[i].qua_no).attr("checked","checked");
			}
		}
	}
}
/**********************************************************************************
*��������: cbxClick
*����˵��: ��ѡ����ʱ
*��������: joyon
*��������: 2014��4��15�� 11:46:54
*�޸���ʷ: 
***********************************************************************************/
function cbxClick(cbx){
	//alert(cbx.id);
	//��ѡʱ
	if(cbx.checked){
		relQualityrecord(cbx.id,"checked");
	//ȡ����ѡʱ
	}else{
		relQualityrecord(cbx.id,"unchecked");
	}
}
/**********************************************************************************
*��������: relQualityrecord
*����˵��: ������ǰ����ָ����������ٱ�
*qua_tar_id  ָ����id   type--checked/unchecked�Ƿ�ѡ  ��ѡ��������  uncheckedȡ������
*��������: joyon
*��������: 2014��4��15�� 11:46:54
*�޸���ʷ: 
***********************************************************************************/
function relQualityrecord(qua_tar_id, type){
    $.ajax({
		url:ctx+'/qualityinspection/qualityinspection!relQualitytarget.action',
		type:"post",
		dataType:"json",
		data:{"qua_tar_id":qua_tar_id,"type":type,"proc_id":proc.procId,"activdefId":select_qua_proc_node.activdefId,"activdefName":select_qua_proc_node.name},
		async:false,
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
		}
	});
}
/**********************************************************************************
*��������: getReledQualitydata
*����˵��: ��ȡ����ҵ�������� �������ٱ����Ѿ�����������ָ���� 
*��������: joyon
*��������: 2014��4��15�� 11:46:54
*�޸���ʷ: 
***********************************************************************************/
function getReledQualitydata(){
	var resultData;
	//getReledQualitydata
	$.ajax({
		url:ctx+'/qualityinspection/qualityinspection!getReledQualitydata.action',
		type:"post",
		dataType:"json",
		data:{"proc_id":proc.procId,"activdefId":select_qua_proc_node.activdefId,"activdefName":select_qua_proc_node.name},
		async:false,
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			//alert(JSON.stringify(data));
			resultData = data;
			//alert(JSON.stringify(resultData));
		},error:function(data){
			alert(JSON.stringify(data));
		}
	});
	//alert(JSON.stringify(resultData));
	return resultData;
}
/**********************************************************************************
*��������: cbx_error_click
*����˵��: ����ĵ���¼�  
*��������: joyon
*��������: 2014��4��15�� 11:46:54
*�޸���ʷ: 
***********************************************************************************/
function cbx_error_click(cbx){
	var isChecked = "unchecked";
	//��ѡʱ
	if(cbx.checked){
		isChecked = "checked";
	//ȡ����ѡʱ
	}
	
	$.ajax({
		url:ctx+'/qualityinspection/qualityinspection!changeIserrorstate.action',
		type:"post",
		dataType:"json",
		data:{"proc_id":proc.procId,"type":isChecked},
		async:false,
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			//alert(JSON.stringify(data));
			resultData = data;
			//alert(JSON.stringify(resultData));
		},error:function(data){
			alert(JSON.stringify(data));
		}
	});
}
/**********************************************************************************
*��������: getsIs_error_state
*����˵��: ��ȡ��ǰ�Ǽ����Ƿ��ǲ���� 
*��������: joyon
*��������: 2014��5��5�� 19:04:45
*�޸���ʷ: 
***********************************************************************************/
function getsIs_error_state(){
	$.ajax({
		url:ctx+'/qualityinspection/qualityinspection!getIserrorstate.action',
		type:"post",
		dataType:"json",
		data:{"proc_id":proc.procId},
		async:false,
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			//alert(JSON.stringify(data));
			if(data.is_error=='1'){
				document.getElementById("is_error").checked = true;
			}
		},error:function(data){
			//alert(JSON.stringify(data));
		}
	});
}
/**********************************************************************************
*��������: initCorrectnoticeDialog
*����˵��: ��ʼ������֪ͨ��Ի���
*��������: joyon
*��������: 2014��5��20�� 19:00:40
*�޸���ʷ: 
***********************************************************************************/
function initCorrectnoticeDialog(){
	getCorrectnoticeContent();
	$('#div_correct_notice').dialog({  
	    title: '�ⶨ����֪ͨ��',  
	    width: 600,  
	    height: 480,  
	    closed: true,  
	    cache: false,  
	    modal: true  
	});
}
/**********************************************************************************
*��������: saveCorrectnoticeContent
*����˵��: ��������֪ͨ�����ݵ����ݿ�
*��������: joyon
*��������: 2014��5��20�� 19:01:07
*�޸���ʷ: 
***********************************************************************************/
function saveCorrectnoticeContent(){
	var correct_content = $("#correct_content").text();
	$.ajax({
		url:ctx+'/qualityinspection/qualityinspection!saveCorrectnoticeContent.action',
		type:"post",
		dataType:"json",
		data:{"proc_id":proc.procId,"correct_content":correct_content},
		async:false,
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			//alert(JSON.stringify(data));
			if(data.result=="success"){
				top.$.messager.alert('��ʾ',data.message,'info',null);
			}else{
				top.$.messager.alert('��ʾ',data.message,'error',null);
			}
//			if(data.is_error=='1'){
//				document.getElementById("is_error").checked = true;
//			}
		},error:function(data){
			//alert(JSON.stringify(data));
		}
	});
}

/**********************************************************************************
*��������: getCorrectnoticeContent
*����˵��: ��ʼ��ʱ��ȡ����֪ͨ�����ݻ���
*��������: joyon
*��������: 2014��5��20�� 19:01:07
*�޸���ʷ: 
***********************************************************************************/
function getCorrectnoticeContent(){
	var correct_content = $("#correct_content").text();
	$.ajax({
		url:ctx+'/qualityinspection/qualityinspection!getCorrectnoticeContent.action',
		type:"post",
		dataType:"json",
		data:{"proc_id":proc.procId},
		async:false,
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			//alert(JSON.stringify(data));
			if(data.correct_content){
				$("#correct_content").text(data.correct_content)
			}
		},error:function(data){
			//alert(JSON.stringify(data));
		}
	});
}

/**********************************************************************************
*��������: viewCorrectionNotice
*����˵��: �鿴����֪ͨ��
*��������: joyon
*��������: 2014��5��20�� 19:01:07
*�޸���ʷ: 
***********************************************************************************/
function viewCorrectionNotice(){
	openInTopWindow({
		//����Ԫ�ص�id
		id: 'view_win',
		//����iframe��src
		src: ctx+'/jsp/common/reports/correction-notice.jsp',
		//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
		destroy: true,
		//���ڱ���
		title: '�����û�',
		//���ڿ�
		width: 700,
		//���ڸ�
		height: 550,
		modal: true,
		//������iframe��window�����onLoad�ص���������
		onLoad:	function(){
			//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
			//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
			//this.openerWindow = window;
			//this.proc_id=proc_id;
			this.init(proc_id);
			//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
			
		}
	});
}
