/**
 * �Ǽǲ�Ԥ��js
 */
/**********************************************************************************
*��������: registerGetPreadviceData
*����˵��: 
*����˵��: ��
*�� �� ֵ: ��
*��������: sam
*��������: 2014-03-17
*�޸���ʷ: 
***********************************************************************************/
 function registerGetPreadviceData(userdata){
	    var preadviceInfo;				//Ԥ��Ǽ���Ϣ
		$.ajax({
			url: ctx+'/bookmanage/book-manage!bookInfoView.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"bok_is_type":5,"reg_unit_type":userdata.REG_UNIT_TYPE,"book_code":userdata.BOOK_CODE},
			success: function(data){
				setpreadvcieInfo(data);											
			},
			error:function(){
			}
		});
   }
 /**********************************************************************************
  *��������: setHistoryPreadviceInfo()
  *����˵��: ����Ԥ��Ǽ���ʷ��Ϣ
  *��    ����data
  *�� �� ֵ: 
  *��������: sam
  *��������: 2014-03-25
  *�޸���ʷ: 
 ***********************************************************************************/ 
 function setHistoryPreadviceInfo(data){
 		  var regDate;
 		  var count;
 		  if(data)
 		  for(var i=0;i<data.length;i++){
 			  regDate = data[i].REG_DATE_N;
 			  if(!regDate){
 				  regDate="";
 			  }
 			  count= i+1;
 			  $("#table_preadviceInfo").append("<tr style='cursor:hand;'><td>"+count+"<input type='hidden' value='"+i+"'/></td><td><label>"+data[i].BUS_NAME+
 					  "</label></td><td><label>"+data[i].ADV_HOLDER+"</label></td><td><label>"+regDate+"</label></td></tr>");
 		  }
 		  //��Ϣ���óɹ���  ���軯�ñ���¼�  
 		 initHistoryPreadviceTable(data);
 		  if(data)
 		 getselectedPreadvicerowdata(data[count-1]);
 	}
 /**********************************************************************************
  *��������: initHistoryPreadviceTable()
  *����˵��: ��ʼ��Ԥ��Ǽ�Table
  *��    ����data
  *�� �� ֵ: 
  *��������: sam
  *��������: 2014-03-25
  *�޸���ʷ: 
 ***********************************************************************************/ 
 function initHistoryPreadviceTable(data){
 	      var rowdata = data;
 		  var trs = document.getElementById('table_preadviceInfo').getElementsByTagName('tr');  
 		  for( var i=0; i<trs.length; i++ ){  
 			   trs[i].onmousedown = function(){  
 				  trPreadviceOnmouseDown(trs,rowdata,this);  
 			   }  
 		  }  
 	} 
 /**********************************************************************************
  *��������: trPreadviceOnmouseDown()
  *����˵��: ����¼�������
  *��    ����trs:table��tr���󼯺�,data:��õ����������Ϣ,obj����ǰѡ��tr�Ķ���
  *�� �� ֵ: 
  *��������: sam
  *��������: 2014-03-25
  *�޸���ʷ: 
 ***********************************************************************************/ 
 function trPreadviceOnmouseDown(trs,data,obj){
 	   if(obj){
 		   
 		   var idx = obj.getElementsByTagName('input')[0].value;		//�����е�input�л�ȡ����е�������
 	       /*var right_rel_id = obj.getElementsByTagName('input')[0].value;		//�����е�input�л�ȡȨ���˼���ID
 	 	   var reg_unit_type = obj.getElementsByTagName('input')[1].value;	//�����е�input�л�ȡ�Ǽǵ�Ԫ����
 	 	   var parcel_code = obj.getElementsByTagName('input')[2].value;		//�����е�input�л�ȡ�Ǽǵ�Ԫ���
 */	 	   //var cdata = {"RIGHT_REL_ID":right_rel_id,"REG_UNIT_TYPE":reg_unit_type,"PARCEL_CODE":parcel_code};
 		  if(data)
 		   getselectedPreadvicerowdata(data[idx]);
 		  for( var o=0; o<trs.length; o++ ){  
 		    if( trs[o] == obj ){  
 		    /* console.info("trs[o]:"+trs[o]);
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
  *��������: getselectedPreadvicerowdata()
  *����˵��: ��õ�ѡ���е�����
  *��    ����data
  *�� �� ֵ: 
  *��������: sam
  *��������: 2014-03-25
  *�޸���ʷ: 
 ***********************************************************************************/ 
 function getselectedPreadvicerowdata(data){
	 if(data){
 	   $('#preadviceInfoForm').form('load',data);
 	   $("#HOUSE_CODE").val(data.ADV_HOUSE_CODE);
 	   $("#HOUSE_LOCATION").val(data.HOUSE_LOCATION);
 	   $("#BUS_NAME").val(data.BUS_NAME);
 	   $("#REG_CODE").val(data.REG_CODE);
 	   $("#ADV_HOLDER").val(data.ADV_HOLDER);
 	   $("#ADV_HOLDER_ID").val(data.ADV_HOLDER_ID);
 	   $("#ADV_VOL").val(data.ADV_VOL);
 	   $("#ADV_VOL_ID").val(data.ADV_VOL_ID);
 	   $("#CER_NO").val(data.CER_NO);
 	   $("#RECORDER").val(data.RECORDER);
 	   $("#REG_DATE_N").val(data.REG_DATE_N);
 	   $("#CAN_ADV_NO").val(data.CAN_ADV_NO);
 	   $("#CAN_ADV_DATE_N").val(data.CAN_ADV_DATE_N);
 	   $("#CAN_ADV_REC").val(data.CAN_ADV_REC);
 	   $("#EXCURSUS").val(data.EXCURSUS);
	 }
 }
 /**********************************************************************************
 *��������: setpreadvcieInfo
 *����˵��: 
 *����˵��: preadviceInfo
 *�� �� ֵ: ��
 *��������: sam
 *��������: 2014-03-17
 *�޸���ʷ: 
 ***********************************************************************************/
   function setpreadvcieInfo(preadviceInfo)
   {
	   setHistoryPreadviceInfo(preadviceInfo);
	  // $('#dissentInfoForm').form('load',attachInfo);
	  // $('#attachlimit').val("�� "+attachInfo.START_DATE+" �� "+attachInfo.END_DATE);
   }   
