/*********************************************************************************
*��  ��  ��  ��: registeruseland.js
*��  ��  ��  ��: �Ǽǲ�Ԥ��
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: sam
*��  ��  ��  �ڣ� 2014-03-17
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/ 

/**********************************************************************************
*��������: registerGetUserLandData()
*����˵��: 
*�� �� ֵ: 
*��������: sam
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/    
function registerGetUserLandData(userdata){
		$.ajax({
			url: ctx+'/bookmanage/book-manage!bookInfoView.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"bok_is_type":2,"reg_unit_type":userdata.REG_UNIT_TYPE,"book_code":userdata.BOOK_CODE},
			success: function(data){									
				setuserInfo(data);											//�������Ȩ��Ϣ
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
	   if(!holder)return;
	   var data = holder;
	   if(holder.length < 1){
			return;
		}
		$("#table_holder").empty();
		var count;
		$("#table_holder").append("<tr><td>���</td><td>Ȩ����</td><td>֤������</td><td>���֤����</td><td>��λ����</td><td>ͨѶ��ַ</td><td>�������</td><td>���ز�֤��</td></tr>");
		  //alert(JSON.stringify(data[0]));
		  for(var i = 0;i < data.length; i++){
			  count= i+1;
			  $("#table_holder").append("<tr><td>"+count+"</td>"+
					  "<td><label>"+data[i].HOL_NAME+"</label></td>"+
					  "<td><label>"+data[i].HOL_CER_TYPE+"</label></td>"+
					  "<td><label>"+data[i].HOL_CER_NO+"</label></td>"+
					  "<td><label>"+data[i].DEP_T_NAME+"</label></td>"+
					  "<td><label>"+data[i].HOL_ADDRESS+"</label></td>"+
					  "<td><label>&nbsp;</label></td>"+
					  "<td><label>"+data[i].CER_NO+"</label></td>"+
					  "</tr>"	  
			  );
		  }  
   }
   
  /* //���ʹ��Ȩ��Ϣ
   function setUserrightInfo(userright)
   {
	   var data = userright;
	   if(userright.length < 1){
		   return;
	   }
	   $("#table_hisuserInfo").empty();	
	   var count;
	   $("#table_hisuserInfo").append("<tr><td>���</td><td>�Ǽ�����</td><td>��Ȩ��</td><td>�Ǽ�ʱ��</td></tr>");
	   //alert(JSON.stringify(data[0]));
	   for(var i=0;i<data.length;i++){
		   count= i+1;
		   var cdata = {"right_rel_id":data[i].RIGHT_REL_ID,"reg_unit_type":data[i].REG_UNIT_TYPE,"parcel_code":data[i].PARCEL_CODE};
		   $("#table_hisuserInfo").append("<tr onmouseover='style.backgroundColor=\"gray\"' onmouseout='style.backgroundColor=\"white\"'  style='cursor:hand;'><td>"+count+"</td>"+
				   "<td><label>"+data[i].BUS_NAME+"</label></td>"+
				   "<td><label>"+data[i].R_COLL_NAMES+"</label></td>"+
				   "<td><label>"+data[i].REG_DATE+"</label></td>"+
				   "</tr>"	  
		   );
	   }
	   getcurrowdata(data[count-1]);
   }*/
   
   /**********************************************************************************
    *��������: setHistoryUserrightInfo()
    *����˵��: ����ʹ��Ȩ��ʷ��Ϣ
    *��    ����data
    *�� �� ֵ: 
    *��������: sam
    *��������: 2014-03-25
    *�޸���ʷ: 
 ***********************************************************************************/ 
   function setHistoryUserrightInfo(data){
		  var regDate;
		  var count;
		  if(data)
		  for(var i=0;i<data.length;i++){
			  regDate = data[i].REG_DATE;
			  if(!regDate){
				  regDate="";
			  }
			  count= i+1;
			  $("#table_hisuserInfo").append("<tr style='cursor:hand;'><td>"+count+"<input type='hidden' value='"+i+"'/></td><td><label>"+data[i].BUS_NAME+
					  "</label></td><td><label>"+data[i].R_COLL_NAMES+"</label></td><td><label>"+regDate+"</label></td></tr>");
		  }
		  //��Ϣ���óɹ���  ���軯�ñ���¼�  
		  initHistoryUserrightTable(data);
		  if(data)
		  getselecteduserightrowdata(data[count-1]);
	}
   /**********************************************************************************
    *��������: initHistoryUserrightTable()
    *����˵��: ��ʼ��ʹ��ȨTable
    *��    ����data
    *�� �� ֵ: 
    *��������: sam
    *��������: 2014-03-25
    *�޸���ʷ: 
 ***********************************************************************************/ 
   function initHistoryUserrightTable(data){
	      var rowdata = data;
		  var trs = document.getElementById('table_hisuserInfo').getElementsByTagName('tr');  
		  for( var i=0; i<trs.length; i++ ){  
			   trs[i].onmousedown = function(){  
				   trUselandOnmouseDown(trs,rowdata,this);  
			   }  
		  }  
	} 
   /**********************************************************************************
    *��������: trUselandOnmouseDown()
    *����˵��: ����¼�������
    *��    ����trs:table��tr���󼯺�,data:��õ����������Ϣ,obj����ǰѡ��tr�Ķ���
    *�� �� ֵ: 
    *��������: sam
    *��������: 2014-03-25
    *�޸���ʷ: 
 ***********************************************************************************/ 
   function trUselandOnmouseDown(trs,data,obj){
	   if(obj){
		   var idx = obj.getElementsByTagName('input')[0].value;		//�����е�input�л�ȡ����е�������
	       /*var right_rel_id = obj.getElementsByTagName('input')[0].value;		//�����е�input�л�ȡȨ���˼���ID
	 	   var reg_unit_type = obj.getElementsByTagName('input')[1].value;	//�����е�input�л�ȡ�Ǽǵ�Ԫ����
	 	   var parcel_code = obj.getElementsByTagName('input')[2].value;		//�����е�input�л�ȡ�Ǽǵ�Ԫ���
*/	 	   //var cdata = {"RIGHT_REL_ID":right_rel_id,"REG_UNIT_TYPE":reg_unit_type,"PARCEL_CODE":parcel_code};
		   if(data)
		   getselecteduserightrowdata(data[idx]);
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
    *��������: getselecteduserightrowdata()
    *����˵��: ��õ�ѡ���е�����
    *��    ����data
    *�� �� ֵ: 
    *��������: sam
    *��������: 2014-03-25
    *�޸���ʷ: 
 ***********************************************************************************/ 
   function getselecteduserightrowdata(data){
	   if(data){
		   $('#userInfoForm').form('load',data);
		   $("#PARCEL_CODE").val(data.PARCEL_CODE);
		   $("#LAND_ADDRESS").val(data.LAND_ADDRESS);
		   $("#BUS_NAME").val(data.BUS_NAME);
		   $("#RECORDER").val(data.RECORDER);
		   $("#REG_DATE").val(data.REG_DATE);
		   $("#EXCURSUS").val(data.EXCURSUS);
		   registerGetHolderData(data);
	   }
   }
   
   //�������Ȩ����Ϣ
   /**********************************************************************************
   *��������: setuserInfo()
   *����˵��: 
   *�� �� ֵ: 
   *��������: sam
   *��������: 2014-03-01
   *�޸���ʷ: 
   ***********************************************************************************/ 
   function setuserInfo(data){
	//$('#userInfoForm').form('load',data);
    // var holder = data.holder;
    // var userright = data.userright;
	//setHoldersInfo(holder);					//������������Ϣ  
	//setUserrightInfo(data);                     //����ʹ��Ȩ��Ϣ
	//historyOwnershipInfo = data.historyOwnershipInfo;
	
	 setHistoryUserrightInfo(data);								//������ʷ����Ȩ��Ϣ��
   }
