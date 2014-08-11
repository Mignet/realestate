/*********************************************************************************
*文  件  名  称: correction-material.js
*功  能  描  述: 补正材料js
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Joyon
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
//var proc=this.parent.proc;
var proc={};										//需要补正材料业务的流程
var cur_proc = this.parent.proc;				//当前补正流程
var proc_id = cur_proc.procId;					
var procNode = cur_proc.activName;

window.onload = init;
/**********************************************************************************
*函数名称: init
*功能说明: 数据初始化
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014年5月27日 18:27:12
*修改历史: 
***********************************************************************************/
function init(){
	proc = getLastProcdata();
	
	//用iframe嵌入接件材料表
	var appendStr = '<iframe id="rec_iframe" src="'+ctx+'/jsp/common/recmaterial/recmaterial.jsp" allowtransparency="true" width="100%" height="100%" frameborder="0"></iframe>';
	
	$("#div_iframe").html(appendStr);
}
/**********************************************************************************
*函数名称: getLastProcdata
*功能说明: 获取需要  补正业务  的流程数据  在补正时  接件材料需要拿到这个proc参数  
*参数说明: 
*返 回 值: proc
*函数作者: Joyon
*创建日期: 2014年5月27日 18:27:12
*修改历史: 
***********************************************************************************/
function getLastProcdata(){
	alert();
	var lastProc = {};
	lastProc.procId="1000017642";
	lastProc.activName="受理";
	//lastProc.proc_node="受理";
	 $.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/recmaterial!getLastProcid.action?time="+new Date(),
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"proc_id":proc_id},
	   		async:false,
	   		success:function(data){
	   			if(data){
	   				lastProc.procId = data.proc_id; 
	   			}
	   		},error:function(data){
	   			
	   		}
	   	});
	return lastProc;
	 
}

function submit(){
	return true;
}
/**********************************************************************************
*函数名称: pageDataIsChange
*功能说明: 判断当前页面数据是否已经修改
*参数说明: 
*返 回 值: 修改返回true   未修改返回false
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function pageDataIsChange(){
	if(proc_node == state1.string0){
		var tmpChangeData = $('#table_rec').datagrid("getChanges");
		if(tmpChangeData.length>0){
			return true;
		}
	}
	//如果相等返回  页面数据未修改  返回false
	return false;
}


