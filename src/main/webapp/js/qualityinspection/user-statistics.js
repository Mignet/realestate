/*********************************************************************************
*��  ��  ��  ��: statistics.js
*��  ��  ��  ��: ����ͳ��
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
	$("#user").combouser();
	initStatisticsData();
	var firstDay = getCurmonthFirstday();
	var lastDay = getCurmonthLastday();

	//�޶�ֻ��ѡ��һ���µ�����
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
	
	$("#title").text("��������������ͳ�Ʊ�("+firstDay+"����"+lastDay+")");
	$("#cur_date").text(getFormatedDate(new Date()));
}
/**********************************************************************************
*��������: initStatisticsData
*����˵��: ��ȡͳ����Ϣ
*��������: joyon
*��������: 2014��4��24�� 17:27:05
*�޸���ʷ: 
***********************************************************************************/
function initStatisticsData(){
		$.ajax({
			url:ctx+"/qualityinspection/qualityinspection!getDaydetailStatistics.action",
			type:"get",
			dataType:"json",
			success:function(data){
				//alert($.toJSON(data));
				//��ձ�����
				if(data.failed){
					top.$.messager.alert('��ʾ',"��ʱ����޳��ҵ����",'info',null);
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
	$("#title").text("��������������ͳ�Ʊ�("+start_date+"����"+end_date+")");
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
				top.$.messager.alert('��ʾ',"��ʱ���û�г��ҵ����",'info',null);
				return;
			}
			
			$("#table_business").empty();
			var append_str = '<tr><td colspan="2" rowspan="2">����/����</td><td colspan="2">��������</td><td colspan="2">��ʼ�Ǽ�</td><td colspan="2">����ת��</td><td colspan="2">����ת��</td><td colspan="2">Ԥ�۵�Ѻ</td><td colspan="2">���۷���Ѻ</td><td colspan="2">���������</td><td colspan="2">���ӷ���֤</td><td colspan="2">Ԥ�ۺ�ͬ����</td><td colspan="2">��Ȩ�ۺϰ���</td><td colspan="2">����Ǽ�</td><td colspan="2">�����Ǽ�</td><td colspan="3">�ϼ�</td>'+
			'</tr><tr>'+
			'<td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td >�����</td><td>Ŀ������</td><td>ʵ�ʳ����</td><td>�����</td>'+
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
    //myDate = new Date(year,month,0);
    var cur_date_format_str = year+"��"+month+"��"+myDate.getDate()+"��";
    return cur_date_format_str;
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
