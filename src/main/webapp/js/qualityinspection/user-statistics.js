/*********************************************************************************
*文  件  名  称: statistics.js
*功  能  描  述: 督检统计
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: joyon
*创  建  日  期： 2014年4月24日 17:26:59
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/

/**********************************************************************************
*函数名称: 
*功能说明: 页面初始化
*函数作者: joyon
*创建日期: 2014年4月24日 17:27:05
*修改历史: 
***********************************************************************************/
window.onload = function(){
	$("#user").combouser();
	initStatisticsData();
	var firstDay = getCurmonthFirstday();
	var lastDay = getCurmonthLastday();

	//限定只能选择一个月的数据
	$('#start_date').datebox({
		editable:false,
		onSelect: function(date){
			var tmonth = (date.getMonth()+1);
			if(tmonth<10)
				tmonth="0"+tmonth;
			var tfirstDay = date.getFullYear()+"-"+tmonth+"-01";
			var tLastday = getLastDay(date);
			$("#start_date").datebox('setValue',tfirstDay);
			$("#end_date").datebox('setValue',tLastday);
		
		}
	});
	$('#end_date').datebox({
		editable:false,
		onSelect: function(date){
			var tmonth = (date.getMonth()+1);
			if(tmonth<10)
				tmonth="0"+tmonth;
			var tfirstDay = date.getFullYear()+"-"+tmonth+"-01";
			var tLastday = getLastDay(date);
			$("#start_date").datebox('setValue',tfirstDay);
			$("#end_date").datebox('setValue',tLastday);
		
		}
	});
	
	$("#start_date").datebox('setValue',firstDay);
	$("#end_date").datebox('setValue',lastDay);
	
	$("#title").text("办文质量检查情况统计表("+firstDay+"——"+lastDay+")");
	$("#cur_date").text(getFormatedDate(new Date()));
}
/**********************************************************************************
*函数名称: initStatisticsData
*功能说明: 获取统计信息
*函数作者: joyon
*创建日期: 2014年4月24日 17:27:05
*修改历史: 
***********************************************************************************/
function initStatisticsData(){
		$.ajax({
			url:ctx+"/qualityinspection/qualityinspection!getDaydetailStatistics.action",
			type:"get",
			dataType:"json",
			success:function(data){
				//alert($.toJSON(data));
				//清空表数据
				if(data.failed){
					top.$.messager.alert('提示',"该时间段无抽检业任务！",'info',null);
					return;
				}
				var append_str = "";
//				for(var i=0;i<data.length;i++){
//					if(i==0){
//						append_str="<tr><td colspan='2'>"+data[i].DEPT_NAME+"</td><td>"+data[i].TOTAL+"</td><td>"+data[i].ERROR_NUM+"</td>";
//					}else if(i==data.length){
//						if(data[i].DEPT_NAME ==data[i-1].DEPT_NAME ){
//							append_str += "<td>"+data[i].TOTAL+"</td><td>"+data[i].ERROR_NUM+"</td></tr>";
//						}else{
//							append_str += "</tr><tr><td colspan='2'>"+data[i].DEPT_NAME+"</td><td>"+data[i].TOTAL+"</td><td>"+data[i].ERROR_NUM+"</td></tr>";
//						}
//					}else if(i>0){
//						if(data[i].DEPT_NAME ==data[i-1].DEPT_NAME ){
//							append_str += "<td>"+data[i].TOTAL+"</td><td>"+data[i].ERROR_NUM+"</td>";
//						}else{
//							append_str += "</tr><tr><td colspan='2'>"+data[i].DEPT_NAME+"</td><td>"+data[i].TOTAL+"</td><td>"+data[i].ERROR_NUM+"</td>";
//						}
//					}
//					
//				}
				for(var i=0;i<data.length;i++){
					append_str+="<tr><td width='80px' colspan='2'>"+data[i].DAY+"</td><td>"+data[i].WINDOW_ALL_TOTAL+"</td><td>"+data[i].WINDOW_ALL_ERROR+"</td><td>"+data[i].ONW_INIT_TOTAL+"</td><td>"+data[i].ONW_INIT_NUM+
					"</td><td>"+data[i].ONW_SEC_TOTAL+"</td><td>"+data[i].ONW_SEC_NUM+"</td><td>"+data[i].ONW_THR_TOTAL+"</td><td>"+data[i].ONW_THR_NUM+
					"</td><td>"+data[i].PRE_MORTGAGE_TOTAL+"</td><td>"+data[i].PRE_MORTGAGE_NUM+"</td><td>"+data[i].MORTGAGE_TOTAL+"</td><td>"+data[i].MORTGAGE_NUM+
					"</td><td>"+data[i].CHANGE_OT_TOTAL+"</td><td>"+data[i].CHANGE_OT_NUM+"</td><td>"+data[i].ANJU_TOTAL+"</td><td>"+data[i].ANJU_TNUM+
					"</td><td>"+data[i].PRE_SALE_TOTAL+"</td><td>"+data[i].PRE_SALE_NUM+"</td><td>"+data[i].EST_COM_TOTAL+"</td><td>"+data[i].EST_COM_NUM+
					"</td><td>"+data[i].TWO_REG_TOTAL+"</td><td>"+data[i].TWO_REG_NUM+"</td><td>"+data[i].OTER_TOTAL+"</td><td>"+data[i].OTHER_NUM+
					"</td><td>"+data[i].TARGET_NUM+"</td><td>"+data[i].ACTUAL_NUM+"</td><td>"+data[i].ACTUAL_ERROR_NUM+"</td></tr>";
				}
				$("#table_business").append(append_str);
			}
		}
		);

}

function queryBydate(){
	var start_date = $("#start_date").datebox('getValue');
	var end_date = $("#end_date").datebox('getValue');
	$("#title").text("办文质量检查情况统计表("+start_date+"——"+end_date+")");
	//alert(start_date+end_date);
	var user = $("#user").combodept("getValue");
	//alert(dept);
	//return;
	$.ajax({
		url:ctx+"/qualityinspection/qualityinspection!getDaydetailStatistics.action",
		type:"get",
		dataType:"json",
		data:{"start_date":start_date,"end_date":end_date,"user_id":user},
		success:function(data){
			//alert($.toJSON(data));
			if(data.failed){
				top.$.messager.alert('提示',"该时间段没有抽检业任务！",'info',null);
				return;
			}
			
			$("#table_business").empty();
			var append_str = '<tr><td colspan="2" rowspan="2">日期/类型</td><td colspan="2">窗口收文</td><td colspan="2">初始登记</td><td colspan="2">二级转移</td><td colspan="2">三级转移</td><td colspan="2">预售抵押</td><td colspan="2">现售房抵押</td><td colspan="2">变更及其它</td><td colspan="2">安居房换证</td><td colspan="2">预售合同备案</td><td colspan="2">产权综合办文</td><td colspan="2">两规登记</td><td colspan="2">其它登记</td><td colspan="3">合计</td>'+
			'</tr><tr>'+
			'<td >抽检量</td><td >差错量</td><td >抽检量</td><td >差错量</td><td >抽检量</td><td >差错量</td><td >抽检量</td><td >差错量</td><td >抽检量</td><td >差错量</td><td >抽检量</td><td >差错量</td><td >抽检量</td><td >差错量</td><td >抽检量</td><td >差错量</td><td >抽检量</td><td >差错量</td><td >抽检量</td><td >差错量</td><td >抽检量</td><td >差错量</td><td >抽检量</td><td >差错量</td><td>目标抽检量</td><td>实际抽检量</td><td>差错量</td>'+
  			'</tr>';
//			for(var i=0;i<data.length;i++){
//				if(i==0){
//					append_str="<tr><td colspan='2'>"+data[i].DEPT_NAME+"</td><td>"+data[i].TOTAL+"</td><td>"+data[i].ERROR_NUM+"</td>";
//				}else if(i==data.length){
//					if(data[i].DEPT_NAME ==data[i-1].DEPT_NAME ){
//						append_str += "<td>"+data[i].TOTAL+"</td><td>"+data[i].ERROR_NUM+"</td></tr>";
//					}else{
//						append_str += "</tr><tr><td colspan='2'>"+data[i].DEPT_NAME+"</td><td>"+data[i].TOTAL+"</td><td>"+data[i].ERROR_NUM+"</td></tr>";
//					}
//				}else if(i>0){
//					if(data[i].DEPT_NAME ==data[i-1].DEPT_NAME ){
//						append_str += "<td>"+data[i].TOTAL+"</td><td>"+data[i].ERROR_NUM+"</td>";
//					}else{
//						append_str += "</tr><tr><td colspan='2'>"+data[i].DEPT_NAME+"</td><td>"+data[i].TOTAL+"</td><td>"+data[i].ERROR_NUM+"</td>";
//					}
//				}
//				
//			}
			for(var i=0;i<data.length;i++){
				append_str+="<tr><td width='80px' colspan='2'>"+data[i].DAY+"</td><td>"+data[i].WINDOW_ALL_TOTAL+"</td><td>"+data[i].WINDOW_ALL_ERROR+"</td><td>"+data[i].ONW_INIT_TOTAL+"</td><td>"+data[i].ONW_INIT_NUM+
				"</td><td>"+data[i].ONW_SEC_TOTAL+"</td><td>"+data[i].ONW_SEC_NUM+"</td><td>"+data[i].ONW_THR_TOTAL+"</td><td>"+data[i].ONW_THR_NUM+
				"</td><td>"+data[i].PRE_MORTGAGE_TOTAL+"</td><td>"+data[i].PRE_MORTGAGE_NUM+"</td><td>"+data[i].MORTGAGE_TOTAL+"</td><td>"+data[i].MORTGAGE_NUM+
				"</td><td>"+data[i].CHANGE_OT_TOTAL+"</td><td>"+data[i].CHANGE_OT_NUM+"</td><td>"+data[i].ANJU_TOTAL+"</td><td>"+data[i].ANJU_TNUM+
				"</td><td>"+data[i].PRE_SALE_TOTAL+"</td><td>"+data[i].PRE_SALE_NUM+"</td><td>"+data[i].EST_COM_TOTAL+"</td><td>"+data[i].EST_COM_NUM+
				"</td><td>"+data[i].TWO_REG_TOTAL+"</td><td>"+data[i].TWO_REG_NUM+"</td><td>"+data[i].OTER_TOTAL+"</td><td>"+data[i].OTHER_NUM+
				"</td><td>"+data[i].TARGET_NUM+"</td><td>"+data[i].ACTUAL_NUM+"</td><td>"+data[i].ACTUAL_ERROR_NUM+"</td></tr>";
			}
			$("#table_business").append(append_str);
		}
	}
	);
}

/**********************************************************************************
*函数名称: getCurmonthFirstday
*功能说明: 获取当前月第一天
*函数作者: joyon
*创建日期: 2014年4月24日 17:27:05
*修改历史: 
***********************************************************************************/
function getCurmonthFirstday(){
	var myDate = new Date();
    var year = myDate.getFullYear();
    var month = myDate.getMonth()+1;
    if (month<10){
        month = "0"+month;
    }
    var firstDay = year+"-"+month+"-"+"01";
    return firstDay;
}
/**********************************************************************************
*函数名称: getCurmonthLastday
*功能说明: 获取当前月最后一天
*函数作者: joyon
*创建日期: 2014年4月24日 17:27:05
*修改历史: 
***********************************************************************************/
function getCurmonthLastday(){
	var myDate = new Date();
    var year = myDate.getFullYear();
    var month = myDate.getMonth()+1;
    if (month<10){
        month = "0"+month;
    }
    myDate = new Date(year,month,0);
    var lastDay = year+"-"+month+"-"+myDate.getDate();
    return lastDay;
}
/**********************************************************************************
*函数名称: getFormatedDate
*功能说明: 获取转换为xxxx年xx月xx日  格式的字符串
*函数作者: joyon
*创建日期: 2014年4月24日 17:27:05
*修改历史: 
***********************************************************************************/
function getFormatedDate(myDate){
	//var myDate = new Date();
    var year = myDate.getFullYear();
    var month = myDate.getMonth()+1;
    if (month<10){
        month = "0"+month;
    }
    //myDate = new Date(year,month,0);
    var cur_date_format_str = year+"年"+month+"月"+myDate.getDate()+"日";
    return cur_date_format_str;
}
/**********************************************************************************
*函数名称: doPrint
*功能说明: 打印id下的div
*函数作者: joyon
*创建日期: 2014年4月24日 17:27:05
*修改历史: 
***********************************************************************************/
function doPrint(id_s){
	var cc = document.getElementById(id_s).innerHTML;  
	  var isIe=0;  
	  if(navigator.userAgent.indexOf('MSIE')>0){  
	    isIe = 1;  
	  }  
	  var frame = document.getElementById('dsh_myframe');  
	  if (!frame) {  
	    if (isIe) {  
	      frame = document.createElement('<iframe id = "dsh_myframe"></iframe>');  
	    } else {  
	      frame = document.createElement('iframe');  
	      frame.id ='dsh_myframe';  
	      frame.setAttribute('style','width: 0pt; height: 0pt;')  
	    }  
	  }  
	  if (isIe) {  
	    frame.src = 'javascript:;';  
	    frame.style.cssText= 'width: 0pt; height: 0pt;';  
	  }  
	  document.body.appendChild(frame);  
	  if (isIe) {  
	    doc = frame.contentWindow.document;  
	  } else {  
	    doc = frame.contentDocument;  
	  }  
	  doc.write(cc);  
	  doc.close();  
	  frame.contentWindow.focus();  
	  if(isIe){  
	    setTimeout(function(){  
	      frame.contentWindow.print();  
	    },2);  
	  }else{  
	    frame.contentWindow.print();  
	  }  
}
/**********************************************************************************
*函数名称: getLastDay
*功能说明: 获取某日期当月最后一天
*参数：dt  日期对象   
*函数作者: joyon
*创建日期: 2014年4月24日 17:27:05
*修改历史: 
***********************************************************************************/
function getLastDay(dt){  
    //var dt = new Date(); 
	//dt.setYear(year);
	//dt.setMonth(month);
	//alert(dt.getMonth());
    dt.setDate(1);  
    dt.setMonth(dt.getMonth()+1);  
    cdt = new Date(dt.getTime()-1000*60*60*24); 
	var tmonth = (Number(cdt.getMonth()))+1;
	if(tmonth<10){
		tmonth="0"+tmonth;
	}
    //alert(cdt.getFullYear()+"年"+tmonth+"月月末日期:"+cdt.getDate()+"日"); 
		return cdt.getFullYear()+"-"+tmonth+"-"+cdt.getDate();
}  
