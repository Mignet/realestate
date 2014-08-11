/*********************************************************************************
*��  ��  ��  ��:  registereasement.js
*��  ��  ��  ��:  �Ǽǲ�Ԥ��
*Copyright (c):  ���ڵ�����������������޹�˾
*��    ��    ��:  sam
*��  ��  ��  �ڣ� 2014-03-17
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/ 
/**********************************************************************************
*��������: registerGetEasementData
*����˵��: 
*����˵��: ��
*�� �� ֵ: ��
*��������: sam
*��������: 2014-03-17
*�޸���ʷ: 
***********************************************************************************/
 function registerGetEasementData(userdata){
	    var easementInfo;				//����Ȩ��Ϣ
		$.ajax({
			url: ctx+'/bookmanage/book-manage!bookInfoView.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"bok_is_type":4,"reg_unit_type":userdata.REG_UNIT_TYPE,"book_code":userdata.BOOK_CODE},
			success: function(data){
				seteasementInfo(data);											//��Ӳ����Ϣ
			},
			error:function(){
			}
		});
   }
 /**********************************************************************************
  *��������: setHistoryEasementInfo()
  *����˵��: ���õ���Ȩ��ʷ��Ϣ
  *��    ����data
  *�� �� ֵ: 
  *��������: sam
  *��������: 2014-03-25
  *�޸���ʷ: 
 ***********************************************************************************/ 
 function setHistoryEasementInfo(data){
 		  var regDate;
 		  var count;
 		  if(data)
 		  for(var i=0;i<data.length;i++){
 			  regDate = data[i].REG_DATE_N;
 			  if(!regDate){
 				  regDate="";
 			  }
 			  count= i+1;
 			  $("#table_easementInfo").append("<tr style='cursor:hand;'><td>"+count+"<input type='hidden' value='"+i+"'/></td><td><label>"+data[i].BUS_NAME+
 					  "</label></td><td><label>"+data[i].EASE_HOLDER+"</label></td><td><label>"+regDate+"</label></td></tr>");
 		  }
 		  //��Ϣ���óɹ���  ���軯�ñ���¼�  
 		  initHistoryEasementTable(data);
 		  if(data)
 		 getselectedEasementrowdata(data[count-1]);
 	}
 
 /**********************************************************************************
  *��������: initHistoryEasementTable()
  *����˵��: ��ʼ������ȨTable
  *��    ����data
  *�� �� ֵ: 
  *��������: sam
  *��������: 2014-03-25
  *�޸���ʷ: 
 ***********************************************************************************/ 
 function initHistoryEasementTable(data){
 	      var rowdata = data;
 		  var trs = document.getElementById('table_easementInfo').getElementsByTagName('tr');  
 		  for( var i=0; i<trs.length; i++ ){  
 			   trs[i].onmousedown = function(){  
 				  trEasementOnmouseDown(trs,rowdata,this);  
 			   }  
 		  }  
 	} 
 /**********************************************************************************
  *��������: trEasementOnmouseDown()
  *����˵��: ����¼�������
  *��    ����trs:table��tr���󼯺�,data:��õ����������Ϣ,obj����ǰѡ��tr�Ķ���
  *�� �� ֵ: 
  *��������: sam
  *��������: 2014-03-25
  *�޸���ʷ: 
 ***********************************************************************************/ 
 function trEasementOnmouseDown(trs,data,obj){
 	   if(obj){
 		   var idx = obj.getElementsByTagName('input')[0].value;		//�����е�input�л�ȡ����е�������
 		  if(data)
 		  getselectedEasementrowdata(data[idx]);
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
  *��������: getselectedEasementrowdata()
  *����˵��: ��õ�ѡ���е�����
  *��    ����data
  *�� �� ֵ: 
  *��������: sam
  *��������: 2014-03-25
  *�޸���ʷ: 
 ***********************************************************************************/ 
 function getselectedEasementrowdata(data){
	   if(data){
		   //var reg_unit_type = $.getUrlParam('reg_unit_type');
		   $('#easementInfoForm').form('load',data);
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
		   $("#EASE_HOLDER").val(data.EASE_HOLDER);
		   $("#EXCURSUS").val(data.EXCURSUS);
	   }
 }
 /**********************************************************************************
  *��������: seteasementInfo()
  *����˵��: ���õ���Ȩ�Ǽ���Ϣ
  *�� �� ֵ: 
  *��������: sam
  *��������: 2014-03-25
  *�޸���ʷ: 
  ***********************************************************************************/
    function seteasementInfo(easementInfo){
    	setHistoryEasementInfo(easementInfo);
    }   
