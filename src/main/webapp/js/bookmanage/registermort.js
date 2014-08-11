/*********************************************************************************
*��  ��  ��  ��: registermort.js
*��  ��  ��  ��: ��ѺȨ�Ǽǲ�Ԥ��js
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: panda
*��  ��  ��  �ڣ� 2014-02-28
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/

   //�򿪵ĸ�����

/**********************************************************************************
*��������: registerGetMortData
*����˵��: 
*����˵��: ��
*�� �� ֵ: ��
*��������: sam
*��������: 2014-03-17
*�޸���ʷ: 
***********************************************************************************/
function registerGetMortData(userdata){
	   $.ajax({
			url: ctx+'/bookmanage/book-manage!bookInfoView.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"bok_is_type":3,"reg_unit_type":userdata.REG_UNIT_TYPE,"book_code":userdata.BOOK_CODE},
			success: function(data){
			   setMortInfo(data);
			},
			error:function(){
				 
			}
		});
}


/**********************************************************************************
 *��������: setHistoryMortInfo()
 *����˵��: ���õ�ѺȨ��ʷ��Ϣ
 *��    ����data
 *�� �� ֵ: 
 *��������: sam
 *��������: 2014-03-25
 *�޸���ʷ: 
***********************************************************************************/ 
function setHistoryMortInfo(data){
		  var regDate;
		  var count;
		  if(data)
		  for(var i=0;i<data.length;i++){
			  regDate = data[i].ALIAS_REG_DATE;
			  if(!regDate){
				  regDate="";
			  }
			  count= i+1;
			  $("#table_mortInfo").append("<tr style='cursor:hand;'><td>"+count+"<input type='hidden' value='"+i+"'/></td><td><label>"+data[i].BUS_NAME+
					  "</label></td><td><label>"+data[i].MORTGAGER+"</label></td><td><label>"+regDate+"</label></td></tr>");
		  }
		  //��Ϣ���óɹ���  ���軯�ñ���¼�  
		  initHistoryMortTable(data);
		  if(data)
		  getselectedmortrowdata(data[count-1]);
	}
/**********************************************************************************
 *��������: initHistoryMortTable()
 *����˵��: ��ʼ����ѺȨTable
 *��    ����data
 *�� �� ֵ: 
 *��������: sam
 *��������: 2014-03-25
 *�޸���ʷ: 
***********************************************************************************/ 
function initHistoryMortTable(data){
	      var rowdata = data;
		  var trs = document.getElementById('table_mortInfo').getElementsByTagName('tr');  
		  for( var i=0; i<trs.length; i++ ){  
			   trs[i].onmousedown = function(){  
				   trMortOnmouseDown(trs,rowdata,this);  
			   }  
		  }  
	} 
/**********************************************************************************
 *��������: trMortOnmouseDown()
 *����˵��: ����¼�������
 *��    ����trs:table��tr���󼯺�,data:��õ����������Ϣ,obj����ǰѡ��tr�Ķ���
 *�� �� ֵ: 
 *��������: sam
 *��������: 2014-03-25
 *�޸���ʷ: 
***********************************************************************************/ 
function trMortOnmouseDown(trs,data,obj){
	   if(obj){
		   var idx = obj.getElementsByTagName('input')[0].value;		//�����е�input�л�ȡ����е�������
		   if(data)
		    getselectedmortrowdata(data[idx]);
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
 *��������: getselectedmortrowdata()
 *����˵��: ��õ�ѡ���е�����
 *��    ����data
 *�� �� ֵ: 
 *��������: sam
 *��������: 2014-03-25
 *�޸���ʷ: 
***********************************************************************************/ 
function getselectedmortrowdata(data){
	   if(data){
		   //var reg_unit_type = $.getUrlParam('reg_unit_type');
		   $('#mortInfoForm').form('load',data);
		   if(reg_unit_type == enumdata.house){
			   $("#HOUSE_CODE").val(data.HOUSE_CODE);
			   $("#HOUSE_LOCATION").val(data.HOUSE_LOCATION);
		   }else if(reg_unit_type == enumdata.parcel){
			   $("#PARCEL_CODE").val(data.PARCEL_CODE);
			   $("#LAND_ADDRESS").val(data.LAND_ADDRESS);
		   }
		   $("#BUS_NAME").val(data.BUS_NAME);
		   $("#MORTGAGER").val(data.MORTGAGER);
		   $("#MORTGAGEE").val(data.MORTGAGEE);
		   $("#BORROWER").val(data.BORROWER);
		   $("#MORT_TYPE_NAME").val(data.MORT_TYPE_NAME);
		   $("#ASSURE_AMOUNT").val(data.ASSURE_AMOUNT);
		   $("#ASSUER_RANGE").val(data.ASSUER_RANGE);
		   $("#DEBT_DIS_LIMIT").val(data.DEBT_DIS_LIMIT);
		   $("#CER_NO").val(data.CER_NO);
		   $("#MORT_SEQ").val(data.MORT_SEQ);
		   $("#EXCURSUS").val(data.EXCURSUS);
		   $("#MAX_AMOUNT").val(data.MAX_AMOUNT);
		   $("#SURE_AMOUNT").val(data.SURE_AMOUNT);
		   $("#SURE_REG_DATE_N").val(data.SURE_REG_DATE_N);
		   $("#RECORDER").val(data.RECORDER);
		   $("#SURE_RECORDER").val(data.SURE_RECORDER);
		   $("#SURE_REG_CODE").val(data.SURE_REG_CODE);
		   $("#ALIAS_REG_DATE").val(data.ALIAS_REG_DATE);
		   $("#CAN_RECORDER").val(data.CAN_RECORDER);
		   $("#CAN_REG_DATE_N").val(data.CAN_REG_DATE_N);
		   
	   }
}

//�������Ȩ����Ϣ
/**********************************************************************************
*��������: setMortInfo()
*����˵��: ���õ�Ѻ��Ϣ
*�� �� ֵ: 
*��������: sam
*��������: 2014-03-25
*�޸���ʷ: 
***********************************************************************************/ 
function setMortInfo(mortInfo){
	setHistoryMortInfo(mortInfo);
}
 
  
   
