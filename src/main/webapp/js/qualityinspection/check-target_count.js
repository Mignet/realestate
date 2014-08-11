/*********************************************************************************
*��  ��  ��  ��: check-target_count.js
*��  ��  ��  ��: ���������
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: joyon
*��  ��  ��  �ڣ� 2014��4��24�� 17:26:59
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/

/**********************************************************************************
*��������: 
*����˵��: ҳ���ʼ��
*��������: joyon
*��������: 2014��4��24�� 17:27:05
*�޸���ʷ: 
***********************************************************************************/
window.onload = function(){
	
	
	var date = new Date();
	var month = date.getMonth()+1;
	if(month<10){
		month="0"+month;
	}
	$("#month").val(date.getFullYear()+"-"+month);
	$("#title").text("�ճ�����Ŀ�����������("+month+"�·�)");
//	$("#cur_date").text(getFormatedDate(new Date()));
	initStatisticsData();
}
/**********************************************************************************
*��������: initStatisticsData
*����˵��: ��ȡͳ����Ϣ
*��������: joyon
*��������: 2014��4��24�� 17:27:05
*�޸���ʷ: 
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
	$("#title").text("�ճ�����Ŀ�����������("+arr[1]+"�·�)");
	
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
			var append_str = '<tr><td >����</td><td >�°�����</td><td >�¹�����</td><td >������*0.03</td><td >������*0.03/������</td><td >Ŀ���վ������</td><td >Ŀ���³����</td></tr>';
			
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
*��������: getCurmonthFirstday
*����˵��: ��ȡ��ǰ�µ�һ��
*��������: joyon
*��������: 2014��4��24�� 17:27:05
*�޸���ʷ: 
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
*��������: getCurmonthLastday
*����˵��: ��ȡ��ǰ�����һ��
*��������: joyon
*��������: 2014��4��24�� 17:27:05
*�޸���ʷ: 
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
*��������: getFormatedDate
*����˵��: ��ȡת��Ϊxxxx��xx��xx��  ��ʽ���ַ���
*��������: joyon
*��������: 2014��4��24�� 17:27:05
*�޸���ʷ: 
***********************************************************************************/
function getFormatedDate(myDate){
	//var myDate = new Date();
    var year = myDate.getFullYear();
    var month = myDate.getMonth()+1;
    if (month<10){
        month = "0"+month;
    }
    myDate = new Date(year,month,0);
    var lastDay = year+"��"+month+"��"+myDate.getDate()+"��";
    return lastDay;
}
/**********************************************************************************
*��������: doPrint
*����˵��: ��ӡid�µ�div
*��������: joyon
*��������: 2014��4��24�� 17:27:05
*�޸���ʷ: 
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
*��������: getLastDay
*����˵��: ��ȡĳ���ڵ������һ��
*������dt  ���ڶ���   
*��������: joyon
*��������: 2014��4��24�� 17:27:05
*�޸���ʷ: 
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
    //alert(cdt.getFullYear()+"��"+tmonth+"����ĩ����:"+cdt.getDate()+"��"); 
		return cdt.getFullYear()+"-"+tmonth+"-"+cdt.getDate();
}  
