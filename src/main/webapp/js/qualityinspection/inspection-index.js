/*********************************************************************************
*文  件  名  称: inspection-index.js
*功  能  描  述: 质量抽检 检查主页面js
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: joyon
*创  建  日  期： 2014年4月15日 
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/

var selectedProc;						//质量抽检页面选择的办文
var iframeid1;
//存储整个树数据
var iframeData1;
var work_open_count=0;					//用来控制打开工作流页面次数
var work_open_examine=0;				//检查Tab选项打开次数
var wiId;
var proc;
var proc_id;
var proc_node;							//流程节点
var select_qua_proc_node;				//质量检查记录表选择的流程   在选择时赋值
var state = {
		string1 : "检查",
		string2 : "负责人审批",
		string3 : "承办人处理",
	};
/**********************************************************************************
*函数名称: 
*功能说明: 页面初始化
*函数作者: joyon
*创建日期: 2014年4月16日 15:10:49
*修改历史: 
***********************************************************************************/
//window.onload = function(){
function init(data){
	//window.resizeTo(1300,600);
	//alert(window.outerwidth);
	proc = data;
	proc_node = proc.activName;
	
	//获取流程  提交按扭
	initProcBtn();
	//alert(JSON.stringify(data));
	//top.$.messager.alert('提示',JSON.stringify(data),'info',null);
	//window.proc = proc;
	//获取被检查业务的流程数据
	selectedProc = getCheckcedbusdata(data.procId);
	//top.$.messager.alert('提示',JSON.stringify(selectedProc),'info',null);
//	selectedProc.procId = selectedProc.PROC_ID;
//	selectedProc.procdefId = selectedProc.PROCDEF_ID;
//	selectedProc.activdefId = selectedProc.ACTIVDEF_ID;
//	selectedProc.activName = selectedProc.NAME;
//	selectedProc.wiId = selectedProc.WI_ID;
	$('#note').text(proc.activName);
	
	//获取当前流程  办过的节点
	
	//初始Tab选项卡
	initTab();
	
	
	//初始化质量跟踪记录表 ---改到节点选择时初始化
	//initQualityrecord(); 
	
	setStateIndex();
	//examineInit();
	examineInit();
	//获取当前是否是差错文
	getsIs_error_state();
	
	getCurprocnode();
	
	
	//初始化整改通知书dialog
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
*函数名称: initTab
*功能说明: 初始化当前的tab选项卡
*函数作者: joyon
*创建日期: 2014年4月21日 
*修改历史: 
***********************************************************************************/
function initTab(){
	$('#tt').tabs({  
	    border:false,  
	    onSelect:function(title){  
	    	if(title=='办文抽检'){
	    		if(work_open_count==0){
	    			var Iframe=document.getElementById("work-iframe");
		    		if(Iframe.contentWindow.init){
		    			//alert(Iframe.contentWindow.init);
		    			Iframe.contentWindow.init(selectedProc);
		    		}
	    		}
	    		work_open_count++;
	    		
	    	}else if(title=="检查意见"){
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
*函数名称: getCurprocnode
*功能说明: 获取当前流程 办过的节点
*函数作者: joyon
*创建日期: 2014年4月21日 
*修改历史: 
***********************************************************************************/
function getCurprocnode(){
	var openiframe_count = 0;					//用来判断iframe打开多少次
	//环节选择  下拉框
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
	
	//质量检查记录表  环节选择  下拉框
	$('#qua_proc_node').combobox({  
	    url:ctx+"/qualityinspection/qualityinspection!getHistoryProcActivity.action?proc_id="+selectedProc.procId,  
	    valueField:'activdefId',  
	    textField:'name',
	    onSelect: function(rec){  
	    	//alert(JSON.stringify(rec));
	    	select_qua_proc_node = rec;					//给当前流程节点赋值
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
	    	//每次选择节点时初始化一次
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
*函数名称: initProcBtn
*功能说明: 获取当前流程环节的提交按扭
*函数作者: joyon
*创建日期: 2014年4月21日 
*修改历史: 
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
		data:{"proc_id":proc_id},
		async:false,
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			resultData = data;	
		}
	});
	return resultData;
}

//提交
function getparticiPants(btn,data){
	var vaResult = validate();
	//验证不通过
	if(!vaResult.result){
		top.$.messager.alert('提示',vaResult.message,'info',null);
		return;
	}
	
	var procdefId=proc.procdefId;			
	var activdefId=data.To;			 
	var resultdata=data;
	var wiId=proc.wiId;
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
	 	openerWindow.location.reload();
	 	closeInTopWindow('open_app','destroy');
	 }else{
		 
	}
	
	summit(activdefId,procdefId,resultdata,data.DisplayName);
	//alert(data.DisplayName);
	
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
						title:"选择接收人",
						id:"particiPants",
						//窗口宽
						width: 300,
						//窗口高
						height: 300,
						modal: true,	
						src:ctx+"/jsp/common/partici-pants.jsp",
						destroy:true,
						onLoad:	function(){
							//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
							//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
							this.openerWinssssdow = window;
							//this.document.getElementById('name').value='名称';
							//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
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
*函数名称: initQualityrecord
*功能说明: 初始化质量
*函数作者: joyon
*创建日期: 2014年4月15日 11:46:54
*修改历史: 
***********************************************************************************/
function initQualityrecord(){
	var appendStr = "";					//用来临时appendStr
	$("#table_quality_record").empty();
	appendStr="<tr><td>序号</td><td>跟踪质量指标项</td><td></td><td></td></tr>";
	$("#table_quality_record").append(appendStr);
	
	//从配置表获取质量指标项
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
	//把质量跟踪项  添加到表格中
	if(qualityrecordData){
		for(var i=0;i<qualityrecordData.length;i++){
			appendStr="<tr><td>"+(parseInt(i)+1)+"</td><td>"+qualityrecordData[i].qua_index+"</td><td><input type='checkbox' id='"+qualityrecordData[i].qua_tar_id+"' onclick='cbxClick(this)'/></td><td></td></tr>";
			$("#table_quality_record").append(appendStr);
		}
	}
	
	//数据回填
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
*函数名称: cbxClick
*功能说明: 复选框点击时
*函数作者: joyon
*创建日期: 2014年4月15日 11:46:54
*修改历史: 
***********************************************************************************/
function cbxClick(cbx){
	//alert(cbx.id);
	//勾选时
	if(cbx.checked){
		relQualityrecord(cbx.id,"checked");
	//取消勾选时
	}else{
		relQualityrecord(cbx.id,"unchecked");
	}
}
/**********************************************************************************
*函数名称: relQualityrecord
*功能说明: 关联当前质量指标项到质量跟踪表
*qua_tar_id  指标项id   type--checked/unchecked是否勾选  勾选则做关联  unchecked取消关联
*函数作者: joyon
*创建日期: 2014年4月15日 11:46:54
*修改历史: 
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
*函数名称: getReledQualitydata
*功能说明: 获取本次业务检查流程 质量跟踪表中已经关联的质量指标项 
*函数作者: joyon
*创建日期: 2014年4月15日 11:46:54
*修改历史: 
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
*函数名称: cbx_error_click
*功能说明: 差错文点击事件  
*函数作者: joyon
*创建日期: 2014年4月15日 11:46:54
*修改历史: 
***********************************************************************************/
function cbx_error_click(cbx){
	var isChecked = "unchecked";
	//勾选时
	if(cbx.checked){
		isChecked = "checked";
	//取消勾选时
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
*函数名称: getsIs_error_state
*功能说明: 获取当前登记文是否是差错文 
*函数作者: joyon
*创建日期: 2014年5月5日 19:04:45
*修改历史: 
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
*函数名称: initCorrectnoticeDialog
*功能说明: 初始化整改通知书对话框
*函数作者: joyon
*创建日期: 2014年5月20日 19:00:40
*修改历史: 
***********************************************************************************/
function initCorrectnoticeDialog(){
	getCorrectnoticeContent();
	$('#div_correct_notice').dialog({  
	    title: '拟定整改通知书',  
	    width: 600,  
	    height: 480,  
	    closed: true,  
	    cache: false,  
	    modal: true  
	});
}
/**********************************************************************************
*函数名称: saveCorrectnoticeContent
*功能说明: 保存整改通知书内容到数据库
*函数作者: joyon
*创建日期: 2014年5月20日 19:01:07
*修改历史: 
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
				top.$.messager.alert('提示',data.message,'info',null);
			}else{
				top.$.messager.alert('提示',data.message,'error',null);
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
*函数名称: getCorrectnoticeContent
*功能说明: 初始化时获取整改通知书内容回填
*函数作者: joyon
*创建日期: 2014年5月20日 19:01:07
*修改历史: 
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
*函数名称: viewCorrectionNotice
*功能说明: 查看整改通知书
*函数作者: joyon
*创建日期: 2014年5月20日 19:01:07
*修改历史: 
***********************************************************************************/
function viewCorrectionNotice(){
	openInTopWindow({
		//窗口元素的id
		id: 'view_win',
		//窗口iframe的src
		src: ctx+'/jsp/common/reports/correction-notice.jsp',
		//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
		destroy: true,
		//窗口标题
		title: '新增用户',
		//窗口宽
		width: 700,
		//窗口高
		height: 550,
		modal: true,
		//窗口中iframe的window对象的onLoad回调函数设置
		onLoad:	function(){
			//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
			//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
			//this.openerWindow = window;
			//this.proc_id=proc_id;
			this.init(proc_id);
			//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
			
		}
	});
}
