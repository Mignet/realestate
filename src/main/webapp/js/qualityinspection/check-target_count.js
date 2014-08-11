/*********************************************************************************
*文  件  名  称: check-target_count.js
*功  能  描  述: 抽检量计算
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
	
	
	var date = new Date();
	var month = date.getMonth()+1;
	if(month<10){
		month="0"+month;
	}
	$("#month").val(date.getFullYear()+"-"+month);
	$("#title").text("日常督查目标抽检量计算表("+month+"月份)");
//	$("#cur_date").text(getFormatedDate(new Date()));
	initStatisticsData();
}
/**********************************************************************************
*函数名称: initStatisticsData
*功能说明: 获取统计信息
*函数作者: joyon
*创建日期: 2014年4月24日 17:27:05
*修改历史: 
***********************************************************************************/
function initStatisticsData(){
		var monthStr = $("#month").val();
		$.ajax({
			url:ctx+"/qualityinspection/qualityinspection!calculateTarget.action",
			type:"get",
			dataType:"json",
			data:{"monthStr":monthStr,"date":new Date()},
			success:function(data){
				//alert($.toJSON(data));
				//data = data.rows;
				var append_str = "";
				for(var i=0;i<data.length;i++){
					append_str+="<tr><td>"+data[i].DEPT_NAME+"</td><td>"+data[i].DEPT_COUNT+"</td><td>"+data[i].WORKDAY+"</td><td>"+data[i].PER_COUNT+"" +
							"</td><td>"+data[i].PER_COUNT_WORKDAY+"</td><td>"+data[i].DEPT_TARGET_COUNT_WORKDAY+"</td><td>"+data[i].DEPT_TARGET_COUNT+"</td></tr>";
				
				}
				$("#table_business").append(append_str);
			}
		}
		);

}

function queryBydate(){
	//var start_date = $("#start_date").datebox('getValue');
	//var end_date = $("#end_date").datebox('getValue');
	
	var month = $("#month").val();
	if($.trim(month).length==0){
		//alert();
		return;
	}
	
	var arr=month.split('-');
	$("#title").text("日常督查目标抽检量计算表("+arr[1]+"月份)");
	
	//alert(start_date+end_date);
	//var user = $("#user").combodept("getValue");
	//alert(dept);
	//return;
	$.ajax({
		url:ctx+"/qualityinspection/qualityinspection!calculateTarget.action",
		type:"get",
		dataType:"json",
		data:{"monthStr":month,"date":new Date()},
		success:function(data){
			//data = data.rows;
			$("#table_business").empty();
			var append_str = '<tr><td >科室</td><td >月办文量</td><td >月工作日</td><td >月收文*0.03</td><td >月收文*0.03/工作日</td><td >目标日均抽检量</td><td >目标月抽检量</td></tr>';
			
			for(var i=0;i<data.length;i++){
				append_str+="<tr><td>"+data[i].DEPT_NAME+"</td><td>"+data[i].DEPT_COUNT+"</td><td>"+data[i].WORKDAY+"</td><td>"+data[i].PER_COUNT+"" +
						"</td><td>"+data[i].PER_COUNT_WORKDAY+"</td><td>"+data[i].DEPT_TARGET_COUNT_WORKDAY+"</td><td>"+data[i].DEPT_TARGET_COUNT+"</td></tr>";
			
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
    myDate = new Date(year,month,0);
    var lastDay = year+"年"+month+"月"+myDate.getDate()+"日";
    return lastDay;
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
