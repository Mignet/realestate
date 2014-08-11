/**********************************************************************************
*定义流程启动属性:        refund_start_proc  退文流程启动属性参数初始化
*                        respite_start_proc  暂缓流程启动属性参数初始化
*                        delay_start_proc  延期流程启动属性参数初始化
*                        suspend_start_proc 挂起流程启动属性参数初始化
*                        rejection_start_proc 驳回流程启动属性参数初始化
*功能说明: 根据流程编译器上流程创建后的一些基本属性如流程定义ID，定义节点Id,声明流程启动的一些静态参数
*作者: Sam
*创建日期: 2014年5月12日 
*修改历史: 
***********************************************************************************/
var refund_start_proc = {
		wiId : "",
		procId : "",
		procdefId : "1302",
		activdefId : "start2",
		activName : "退文"
};
var respite_start_proc = {
		wiId : "",
		procId : "",
		procdefId : "1299",
		activdefId : "start2",
		activName : "暂缓"
};
var delay_start_proc = {
		wiId : "",
		procId : "",
		procdefId : "1301",
		activdefId : "start2",
		activName : "延期"
};
var suspend_start_proc = {
		wiId : "",
		procId : "",
		procdefId : "1300",
		activdefId : "start2",
		activName : "挂起"
};
var rejection_start_proc = {
		wiId : "",
		procId : "",
		procdefId : "1298",
		activdefId : "start2",
		activName : "驳回"
};
var spec_proc_add = {
	rejection:'驳回决定书',
	respite:'暂缓决定书',
	suspend:'挂起申请书',
	delay:'延期申请书',
	refund:'退文申请书'
};

/**********************************************************************************
*属性定义: jds_sqb_tw 用于区分判断哪一个流程正在执行 同时动态改变选项卡内容
*          wiId 工作项Id
*          proc 用于接收init()方法传过来的参数 
*          procId 流程定义Id
*          _procName 流程名
*          init_add 判断当前状态是新创建流程还是流程已经创建
*          selectedProc 用于接收选择的流程
*          Iframe 定义内嵌iframe
*功能说明: 定义一些全局变量
*作者: Sam
*创建日期: 2014年5月1日 
*修改历史: 
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
*函数名称: getChinaTip
*功能说明: 通过传过来的参数得到对应的中文信息，同时初始化对应流程的form信息
*参    数：val
*函数作者: Sam
*创建日期: 2014年5月1日 
*修改历史: 
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
*函数名称: init
*功能说明: 用于主窗体对应弹出子窗体传值，单向的
*参    数：items
*函数作者: Sam
*创建日期: 2014年5月1日 
*修改历史: 
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
		 $('#jds_sqb_tw').attr('title','受理退回登记文'); 
		 init_add = 'true';
		 jds_sqb_tw = enumdata.BACKLANGUAGESQS;
	 }else if(jds_sqb_tw == enumdata.BACKLANGUAGESQS){
		 if(proc.activName == '受理'){
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
		 $('#djcontent').attr('title','退文内容');
	 }else{
		 $('#djcontent').attr('title','登记内容');
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
	 if(proc.activName == '受理'){
		 $('#refund_options').css('display','none');
		 $("#save").linkbutton('disable');
	 }
	 initProcBtn();
}
/**********************************************************************************
*函数名称: loadWorkIframe
*功能说明: 加载内嵌窗体
*函数作者: Sam
*创建日期: 2014年5月1日 
*修改历史: 
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
*函数名称: getIframeDom
*功能说明: 得到内嵌窗体dom对象，通过内嵌iframe定义id名
*参    数：iframeId
*函数作者: Sam
*创建日期: 2014年5月1日 
*修改历史: 
***********************************************************************************/
function getIframeDom(iframeId) {    
    return document.getElementById(iframeId).contentWindow || window.frames[iframeId];    
}    
/**********************************************************************************
*函数名称: initTab
*功能说明: 初始化选项卡，定义选项卡被选中时触发的事件
*函数作者: Sam
*创建日期: 2014年5月1日 
*修改历史: 
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
	    	}else if(title == '退文内容' || title == '登记内容'){
	    		loadWorkIframe();
	    	}
	    }
	}); 
}
/**********************************************************************************
*函数名称: initProcBtn
*功能说明: 获取当前流程环节的提交按扭
*函数作者: joyon
*创建日期: 2014年4月21日 
*修改历史: 
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
*函数名称: valideteSelection
*功能说明: 验证选择器，根据url参数值jds_sqb_tw判断当前处于哪个流程，同时调用对应流程的验证函数
*函数作者: Sam
*创建日期: 2014年5月1日 
*修改历史: 
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
*函数名称: initProcessStartProperties
*功能说明: 初始化流程启动属性，根据url参数值jds_sqb_tw判断当前处于哪个流程，同时将上面定义
*函数作者: Sam
*创建日期: 2014年5月1日 
*修改历史: 
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
*函数名称: getparticiPants
*功能说明: 验证是否新流程创建，即提交流程进入下一个节点
*参    数：btn,data
*函数作者: Sam修改
*创建日期: 2014年5月1日 
*修改历史: 
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
	//验证不通过
	if(!vaResult.result){
		top.$.messager.alert('提示',vaResult.message,'info',null);
		return;
	}
	var procdefId=proc.procdefId;			
	var activdefId=data.To;			 
	var resultdata=data;
	var wiId=proc.wiId;
	//alert(data.DisplayName);
	
	if(proc.wiName=="归档"){			
	 	var end={
	 		url:ctx+"/workflow/workflow!WorkItemFinishedEndWithFacade.action",
	 		type:'post',
	 		async:false,
	 		data:{"wiId":wiId,"time":new Date()},
	 		dataType:"json",
	 		success:function(data){
	 			//alert(data);
	 			if(data.sign=="success"){	
	 			top.$.messager.alert('提示', '归档成功！', 'info',
				function() {	
			 				
	 				});			
	 			}
	 			else if(data.sign=="failed"){
	 				top.$.messager.alert('提示', '归档失败！', 'info',
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
*函数名称: createAndStartProcessInstance
*功能说明: 创建并启动新的流程
*函数作者: Sam
*创建日期: 2014年5月1日 
*修改历史: 
***********************************************************************************/
function createAndStartProcessInstance(){
	var procdefId = proc.procdefId;	
	var procName = proc.activName+"-"+_procName;
	var regCode = $('#regCode').val();
	var busId = $('#bus_id').val();
	var specifyId = getSpecifyId();
	if(empty(specifyId)){
		top.$.messager.alert('提示',"该流程为保存，请保存后再进行提交申请，谢谢！",'warn',function(){});
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
					top.$.messager.alert('提示',data.tipMessage,'info',function(){
						openerWindow.openerWindow.location.reload();
						closeInTopWindow('add_app','destroy');
						closeInTopWindow('open_app','destroy');
						closeInTopWindow('wind_djby2','destroy');
					});
				}else{
					top.$.messager.alert('错误', data.errorMessage+'['+data.tipMessage+']。', 'error', function(){});
				}
			}
	};
	$.ajax(startproc);
}

/**********************************************************************************
*函数名称: summit
*功能说明: 弹出particiPants.jsp页面
*函数作者: joyon
*创建日期: 2014年4月15日 11:46:54
*修改历史: 
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
						title:"选择接收人",
						id:"particiPants",
						//窗口宽
						width: 300,
						//窗口高
						height: 300,
						modal: true,	
						src: ctx + "/jsp/common/partici-pants.jsp",
						destroy:true,
						onLoad:	function(){
							//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
							//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
							this.openerWinssssdow = window;
							//this.document.getElementById('name').value='名称';
							//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
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
*函数名称: getCheckcedbusdata
*功能说明: 获取当前检查流程的  被检查流程流程数据
*函数作者: joyon
*创建日期: 2014年4月15日 11:46:54
*修改历史: 
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
*函数名称: getSpecifyId
*功能说明: 获得流程id编号，根据url参数值jds_sqb_tw判断当前处于哪个流程，同时将流程对应的id编号获取到
*函数作者: Sam
*创建日期: 2014年5月1日 
*修改历史: 
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
*函数名称: submitsp
*功能说明: 流程新创建 form表单提交，也需根据url参数值jds_sqb_tw判断当前处于哪个流程，具体提交哪个流程
*函数作者: Sam
*创建日期: 2014年5月1日 
*修改历史: 
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
				top.$.messager.alert('提示','数据已经保存无需保存！','info',function(){});
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
				top.$.messager.alert('提示','数据已经保存无需保存！','info',function(){});
		}else{
			saveExamine();
		}
	}else if(jds_sqb_tw == enumdata.DELAYSQS){
		if(!empty(init_add) && init_add == 'true'){
			if(empty($('#delay_id').val()))
				   $('#delayForm').submit();
			else
				top.$.messager.alert('提示','数据已经保存无需保存！','info',function(){});
		}else{
			saveExamine();
		}
	}else if(jds_sqb_tw == enumdata.HANGUPSQS){
		if(!empty(init_add) && init_add == 'true'){
			if(empty($('#sus_id').val()))
				   $('#suspendForm').submit();
			else
				top.$.messager.alert('提示','数据已经保存无需保存！','info',function(){});
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
				 top.$.messager.alert('提示','数据已经保存无需保存！','info',function(){});
		}else{
			saveExamine();
		}
	}
}
/**********************************************************************************
*函数名称: validateDate
*功能说明: 日期验证函数，主要针对延期和挂起流程中文日期格式
*函数作者: Sam
*创建日期: 2014年5月1日 
*修改历史: 
***********************************************************************************/
function validateDate(){
	var app_date_str = $('#app_date_str').val();
	var pattern = /(\d{4})年(\d{2})月(\d{2})日/;
	var newdateformat;
	var date;
    try{
    	newdateformat = app_date_str.replace(pattern,'$1-$2-$3');
    	date = new Date(Date.parse(newdateformat.replace(/-/g,'/')));
    	$('#app_date').val(newdateformat);
    	return true;
    }catch(exception){
    	top.$.messager.alert('错误', '输入错误格式的日期', 'error', function(){});
    	return false;
    }
}
/*********************************************************
*函数名称: getPreProcId
*功能说明: 得到前流程Id
*函数作者: Sam
*创建日期: 2014年5月1日 
*修改历史: 
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
