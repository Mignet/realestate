/*********************************************************************************
*��  ��  ��  ��: registerusehouse.js
*��  ��  ��  ��: �Ǽǲ�Ԥ��
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: sam
*��  ��  ��  �ڣ� 2014-03-17
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/    

/**********************************************************************************
*��������: registerGetUseHouseData()
*����˵��: 
*����˵����userdata
*�� �� ֵ: 
*��������: sam
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/      
function registerGetUseHouseData(userdata){
	$.ajax({
		url: ctx+'/bookmanage/book-manage!bookInfoView.action?time='+new Date(),
		dataType: 'json',
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"bok_is_type":2,"reg_unit_type":userdata.REG_UNIT_TYPE,"book_code":userdata.BOOK_CODE},
		success: function(data){											         
			setOwnershipInfo(data);											
		},
		error:function(){
			
		}
	});
}

/**********************************************************************************
*��������: registerGetHolderData()
*����˵��: 
*��    ����userdata
*�� �� ֵ: 
*��������: sam
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/  
function registerGetHolderData(userdata){
	$.ajax({
		url: ctx+'/bookmanage/book-manage!getBookHolderLstByParam.action?time='+new Date(),
		dataType: 'json',
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"reg_unit_type":userdata.REG_UNIT_TYPE,"book_code":userdata.BOOK_CODE,"reg_code":userdata.REG_CODE},
		success: function(data){									
			setHoldersInfo(data);											//�������Ȩ��Ϣ
		}
	});
} 

/**********************************************************************************
*��������: setHoldersInfo()
*����˵��: ����Ȩ������Ϣ
*����˵����holder
*�� �� ֵ:
*��������: sam
*��������: 2014-03-28
*�޸���ʷ: 
***********************************************************************************/   
function setHoldersInfo(holder)
{         
	   if(!holder) return;
	   var data = holder;
	   if(holder.length < 1){
			return;
		}
		$("#table_holder").empty();
		var count;
		$("#table_holder").append("<tr><td>���</td><td>Ȩ����</td><td>֤������</td><td>���֤����</td><td>ͨѶ��ַ</td><td>�������</td><td>���ز�֤��</td></tr>");
		  //alert(JSON.stringify(data[0]));
		  for(var i = 0;i < data.length; i++){
			  count= i+1;
			  $("#table_holder").append("<tr><td>"+count+"</td>"+
					  "<td><label>"+data[i].HOL_NAME+"</label></td>"+
					  "<td><label>"+data[i].HOL_CER_TYPE+"</label></td>"+
					  "<td><label>"+data[i].HOL_CER_NO+"</label></td>"+
					  "<td><label>"+data[i].HOL_ADDRESS+"</label></td>"+
					  "<td><label>&nbsp;</label></td>"+
					  "<td><label>"+data[i].CER_NO+"</label></td>"+
					  "</tr>"	  
			  );
		  }  
 }


/**********************************************************************************
 *��������: setHistoryOwnershipInfo()
 *����˵��: ��������Ȩ��ʷ��Ϣ
 *��    ����data
 *�� �� ֵ: 
 *��������: sam
 *��������: 2014-03-25
 *�޸���ʷ: 
***********************************************************************************/ 
function setHistoryOwnershipInfo(data){
		  var regDate;
		  var count;
		  if(data)
		  for(var i=0;i<data.length;i++){
			  regDate = data[i].REG_DATE;
			  if(!regDate){
				  regDate="";
			  }
			  count= i+1;
			  $("#table_hisOwnInfo").append("<tr style='cursor:hand;'><td>"+count+"<input type='hidden' value='"+i+"'/></td><td><label>"+data[i].BUS_NAME+
					  "</label></td><td><label>"+data[i].R_COLL_NAMES+"</label></td><td><label>"+regDate+"</label></td></tr>");
		  }
		  //��Ϣ���óɹ���  ���軯�ñ���¼�  
		  initHistoryOwnershipTable(data);
		  if(data)
		  getselectedownershiprowdata(data[count-1]);
	}
/**********************************************************************************
 *��������: initHistoryOwnershipTable()
 *����˵��: ��ʼ������ȨTable
 *��    ����data
 *�� �� ֵ: 
 *��������: sam
 *��������: 2014-03-25
 *�޸���ʷ: 
***********************************************************************************/ 
function initHistoryOwnershipTable(data){
	      var rowdata = data;
		  var trs = document.getElementById('table_hisOwnInfo').getElementsByTagName('tr');  
		  for( var i=0; i<trs.length; i++ ){  
			   trs[i].onmousedown = function(){  
				   trUseHouseOnmouseDown(trs,rowdata,this);  
			   }  
		  }  
	} 
/**********************************************************************************
 *��������: trUseHouseOnmouseDown()
 *����˵��: ����¼�������
 *��    ����trs:table��tr���󼯺�,data:��õ����������Ϣ,obj����ǰѡ��tr�Ķ���
 *�� �� ֵ: 
 *��������: sam
 *��������: 2014-03-25
 *�޸���ʷ: 
***********************************************************************************/ 
function trUseHouseOnmouseDown(trs,data,obj){
	   if(obj){
		   
		   var idx = obj.getElementsByTagName('input')[0].value;		//�����е�input�л�ȡ����е�������
	       /*var right_rel_id = obj.getElementsByTagName('input')[0].value;		//�����е�input�л�ȡȨ���˼���ID
	 	   var reg_unit_type = obj.getElementsByTagName('input')[1].value;	//�����е�input�л�ȡ�Ǽǵ�Ԫ����
	 	   var parcel_code = obj.getElementsByTagName('input')[2].value;		//�����е�input�л�ȡ�Ǽǵ�Ԫ���
*/	 	   //var cdata = {"RIGHT_REL_ID":right_rel_id,"REG_UNIT_TYPE":reg_unit_type,"PARCEL_CODE":parcel_code};
		   if(data)
		   getselectedownershiprowdata(data[idx]);
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
 *��������: getselectedownershiprowdata()
 *����˵��: ��õ�ѡ���е�����
 *��    ����data
 *�� �� ֵ: 
 *��������: sam
 *��������: 2014-03-25
 *�޸���ʷ: 
***********************************************************************************/ 
function getselectedownershiprowdata(data){
	if(data){
	   $('#ownershipInfoForm').form('load',data);
	   $("#HOUSE_CODE").val(data.HOUSE_CODE);
	   $("#HOUSE_LOCATION").val(data.HOUSE_LOCATION);
	   $("#BUS_NAME").val(data.BUS_NAME);
	   $("#HOUSE_KIND_N").val(data.HOUSE_KIND_N);
	   $("#GET_MODE").val(data.GET_MODE);
	   $("#RECORDER").val(data.RECORDER);
	   $("#REG_DATE").val(data.REG_DATE);
	   $("#EXCURSUS").val(data.EXCURSUS);
	   registerGetHolderData(data);
	}
}
/**********************************************************************************
*��������: setOwnershipInfo()
*����˵��: �������Ȩ��Ϣ
*�� �� ֵ: 
*��������: sam
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/   
function setOwnershipInfo(data){
	/*   $('#ownershipInfoForm').form('load',data);
   holder = data.holder;
	setHoldersInfo(holder,data.rightCertificateNo);					//������������Ϣ  �������ز�֤��
	
	historyOwnershipInfo = data.historyOwnershipInfo;				//��ʷ����Ȩ��Ϣ
*/	
	setHistoryOwnershipInfo(data);								//������ʷ����Ȩ��Ϣ��
}

/**********************************************************************************
*��������: validate()
*����˵��: ͨ��У�鷽��  �����ﲻ��У��  ֱ�ӷ���true
*�� �� ֵ: 
*��   ��:
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/  
function validate(){
	return true;
}
   
   
