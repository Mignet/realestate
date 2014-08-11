/*********************************************************************************
*��  ��  ��  ��:  registerattach.js
*��  ��  ��  ��:  �Ǽǲ�Ԥ��
*Copyright (c):  ���ڵ�����������������޹�˾
*��    ��    ��:  sam
*��  ��  ��  �ڣ� 2014-03-17
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/ 
/**********************************************************************************
*��������: registerGetAttachData
*����˵��: 
*����˵��: userdata
*�� �� ֵ: ��
*��������: sam
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
 function registerGetAttachData(userdata){
		$.ajax({
			url: ctx+'/bookmanage/book-manage!bookInfoView.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"bok_is_type":7,"reg_unit_type":userdata.REG_UNIT_TYPE,"book_code":userdata.BOOK_CODE},
			success: function(data){
				setattachInfo(data);											//��Ӳ����Ϣ
			}
		});
   }
 
 /**********************************************************************************
  *��������: setHistoryAttachInfo()
  *����˵��: ���ò����ʷ��Ϣ
  *��    ����data
  *�� �� ֵ: 
  *��������: sam
  *��������: 2014-03-25
  *�޸���ʷ: 
 ***********************************************************************************/ 
 function setHistoryAttachInfo(data){
 		  var regDate;
 		  var count;
 		  for(var i=0;i<data.length;i++){
 			  regDate = data[i].REG_DATE_N;
 			  if(!regDate){
 				  regDate="";
 			  }
 			  count= i+1;
 			  $("#table_attachInfo").append("<tr style='cursor:hand;'><td>"+count+"<input type='hidden' value='"+i+"'/></td><td><label>"+data[i].BUS_NAME+
 					  "</label></td><td><label>"+data[i].DIS_OFF+"</label></td><td><label>"+regDate+"</label></td></tr>");
 		  }
 		  //��Ϣ���óɹ���  ���軯�ñ���¼�  
 		  initHistoryAttachTable(data);
 		  if(data)
 		  getselectedattachrowdata(data[count-1]);
 	}
 /**********************************************************************************
  *��������: initHistoryAttachTable()
  *����˵��: ��ʼ�����Table
  *��    ����data
  *�� �� ֵ: 
  *��������: sam
  *��������: 2014-03-25
  *�޸���ʷ: 
 ***********************************************************************************/ 
 function initHistoryAttachTable(data){
 	      var rowdata = data;
 		  var trs = document.getElementById('table_attachInfo').getElementsByTagName('tr');  
 		  for( var i=0; i<trs.length; i++ ){  
 			   trs[i].onmousedown = function(){  
 				  trAttachOnmouseDown(trs,rowdata,this);  
 			   }  
 		  }  
 	} 
 /**********************************************************************************
  *��������: trAttachOnmouseDown()
  *����˵��: ����¼�������
  *��    ����trs:table��tr���󼯺�,data:��õ����������Ϣ,obj����ǰѡ��tr�Ķ���
  *�� �� ֵ: 
  *��������: sam
  *��������: 2014-03-25
  *�޸���ʷ: 
 ***********************************************************************************/ 
 function trAttachOnmouseDown(trs,data,obj){
 	   if(obj){
 		   var idx = obj.getElementsByTagName('input')[0].value;		//�����е�input�л�ȡ����е�������
 		  if(data)
 		   getselectedattachrowdata(data[idx]);
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
  *��������: getselectedattachrowdata()
  *����˵��: ��õ�ѡ���е�����
  *��    ����data
  *�� �� ֵ: 
  *��������: sam
  *��������: 2014-03-25
  *�޸���ʷ: 
 ***********************************************************************************/ 
 function getselectedattachrowdata(data){
	   //var reg_unit_type = $.getUrlParam('reg_unit_type');
	   $('#attachInfoForm').form('load',data);
	   if(reg_unit_type == enumdata.house){
		   $("#HOUSE_CODE").val(data.HOUSE_CODE);
		   $("#HOUSE_LOCATION").val(data.HOUSE_LOCATION);
	   }else if(reg_unit_type == enumdata.parcel){
		   $("#PARCEL_CODE").val(data.PARCEL_CODE);
		   $("#LAND_ADDRESS").val(data.LAND_ADDRESS);
	   }
	   $("#BUS_NAME").val(data.BUS_NAME);
	   $("#DIS_OFF").val(data.DIS_OFF);
	   $("#LAW_DOC").val(data.LAW_DOC);
	   $("#DIS_NO").val(data.DIS_NO);
	   $("#DIS_DATE").val(data.ALIAS_DIS_DATE);
	   $("#ATTACH_LIMIT").val(data.ATTACH_LIMIT);
	   $("#REG_DATE_N").val(data.REG_DATE_N);
	   $("#RECORDER").val(data.RECORDER);
	   $("#REM_REG_CODE").val(data.REM_REG_CODE);
	   $("#REM_LAW_DOC").val(data.REM_LAW_DOC);
	   $("#REM_DIS_NO").val(data.REM_DIS_NO);
	   $("#JDIS_DATE").val(data.JDIS_DATE);
	   $("#JALIAS_REG_DATE").val(data.JALIAS_REG_DATE);
	   $("#JRECORDER").val(data.JRECORDER);
	   $("#EXCURSUS").val(data.EXCURSUS);
 }

 
 
 /**********************************************************************************
 *��������: setattachInfo()
 *����˵��: ���ò����Ϣ
 *�� �� ֵ: 
 *��������: sam
 *��������: 2014-03-25
 *�޸���ʷ: 
 ***********************************************************************************/ 
 function setattachInfo(attachInfo){
	 setHistoryAttachInfo(attachInfo);
 }
   

