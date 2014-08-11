/*********************************************************************************
*��  ��  ��  ��:  registerdissent.js
*��  ��  ��  ��:  �Ǽǲ�Ԥ��
*Copyright (c):  ���ڵ�����������������޹�˾
*��    ��    ��:  sam
*��  ��  ��  �ڣ� 2014-03-17
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/ 

/**********************************************************************************
*��������: registerGetDissentData
*����˵��: 
*����˵��: userdata
*�� �� ֵ: ��
*��������: sam
*��������: 2014-03-27
*�޸���ʷ: 
***********************************************************************************/
 function registerGetDissentData(userdata){
		$.ajax({
			url: ctx+'/bookmanage/book-manage!bookInfoView.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"bok_is_type":6,"reg_unit_type":userdata.REG_UNIT_TYPE,"book_code":userdata.BOOK_CODE},
			success: function(data){
				setdissentInfo(data);											//��Ӳ����Ϣ
			}
		});
   }
 
 /**********************************************************************************
  *��������: setHistoryDissentInfo()
  *����˵��: ���ò����ʷ��Ϣ
  *��    ����data
  *�� �� ֵ: 
  *��������: sam
  *��������: 2014-03-25
  *�޸���ʷ: 
 ***********************************************************************************/ 
 function setHistoryDissentInfo(data){
 		  var regDate;
 		  var count;
 		  if(data)
 		  for(var i=0;i<data.length;i++){
 			  regDate = data[i].REG_DATE_N;
 			  if(!regDate){
 				  regDate="";
 			  }
 			  count= i+1;
 			  $("#table_dissentInfo").append("<tr style='cursor:hand;'><td>"+count+"<input type='hidden' value='"+i+"'/></td><td><label>"+data[i].BUS_NAME+
 					  "</label></td><td><label>"+data[i].DISS_APP+"</label></td><td><label>"+regDate+"</label></td></tr>");
 		  }
 		  //��Ϣ���óɹ���  ���軯�ñ���¼�  
 		  initHistoryDissentTable(data);
 		  if(data)
 		  getselectedDissentrowdata(data[count-1]);
 	}
 /**********************************************************************************
  *��������: initHistoryDissentTable()
  *����˵��: ��ʼ�����Table
  *��    ����data
  *�� �� ֵ: 
  *��������: sam
  *��������: 2014-03-25
  *�޸���ʷ: 
 ***********************************************************************************/ 
 function initHistoryDissentTable(data){
 	      var rowdata = data;
 		  var trs = document.getElementById('table_dissentInfo').getElementsByTagName('tr');  
 		  for( var i=0; i<trs.length; i++ ){  
 			   trs[i].onmousedown = function(){  
 				  trDissentOnmouseDown(trs,rowdata,this);  
 			   }  
 		  }  
 	} 
 /**********************************************************************************
  *��������: trDissentOnmouseDown()
  *����˵��: ����¼�������
  *��    ����trs:table��tr���󼯺�,data:��õ����������Ϣ,obj����ǰѡ��tr�Ķ���
  *�� �� ֵ: 
  *��������: sam
  *��������: 2014-03-25
  *�޸���ʷ: 
 ***********************************************************************************/ 
 function trDissentOnmouseDown(trs,data,obj){
 	   if(obj){
 		   var idx = obj.getElementsByTagName('input')[0].value;		//�����е�input�л�ȡ����е�������
 		  if(data)
 		   getselectedDissentrowdata(data[idx]);
 		  for( var o=0; o<trs.length; o++ ){  
 		    if( trs[o] == obj ){  
 		     /*console.info("trs[o]:"+trs[o]);
 		     console.info("obj:"+obj);*/
 		      trs[o].style.backgroundColor = '#DFEBF2';  
 		    }  
 		    else{  
 		       trs[o].style.backgroundColor = '';  
 		    }  
 		  } 
 	  }
 	} 
 /**********************************************************************************
  *��������: getselectedDissentrowdata()
  *����˵��: ��õ�ѡ���е�����
  *��    ����data
  *�� �� ֵ: 
  *��������: sam
  *��������: 2014-03-25
  *�޸���ʷ: 
 ***********************************************************************************/ 
 function getselectedDissentrowdata(data){
	   if(data){
		  //var reg_unit_type = $.getUrlParam('reg_unit_type');
		   $('#dissentInfoForm').form('load',data);
		   if(reg_unit_type == enumdata.house){
			   $("#HOUSE_CODE").val(data.HOUSE_CODE);
			   $("#HOUSE_LOCATION").val(data.HOUSE_LOCATION);
		   }else if(reg_unit_type == enumdata.parcel){
			   $("#PARCEL_CODE").val(data.PARCEL_CODE);
			   $("#LAND_ADDRESS").val(data.LAND_ADDRESS);
		   }
		   $("#BUS_NAME").val(data.BUS_NAME);
		   $("#REG_DATE_N").val(data.REG_DATE_N);
		   $("#RECORDER").val(data.RECORDER);
		   $("#CAN_REG_CODE").val(data.CAN_REG_CODE);
		   $("#DISS_APP").val(data.DISS_APP);
		   $("#DISS_ITEM").val(data.DISS_ITEM);
		   $("#CAN_DISS_APP").val(data.CAN_DISS_APP);
		   $("#CAN_DISS_REG_DATE_N").val(data.CAN_DISS_REG_DATE_N);
		   $("#CAN_DISS_REC").val(data.CAN_DISS_REC);
		   $("#EXCURSUS").val(data.EXCURSUS+"===="+data.CAN_EXCURSUS);
	   }
 }

 
 
 /**********************************************************************************
 *��������: setdissentInfo()
 *����˵��: ��������Ǽ���Ϣ
 *�� �� ֵ: 
 *��������: sam
 *��������: 2014-03-25
 *�޸���ʷ: 
 ***********************************************************************************/
   function setdissentInfo(dissentInfo){
	   setHistoryDissentInfo(dissentInfo);
   }   
