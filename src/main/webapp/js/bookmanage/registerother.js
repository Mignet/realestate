/*********************************************************************************
*��  ��  ��  ��:  registerother.js
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
 function registerGetOtherData(){
	    var otherInfo;				//������Ϣ
		$.ajax({
			url: ctx+'/bookmanage/book-manage!getregisterInfo.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"proc_id":proc_id},
			success: function(data){
				//alert(JSON.stringify(data));
				otherInfo = data.userInfo;											//�����Ϣ
				setotherInfo(otherInfo);											//��Ӳ����Ϣ
			},
			error:function(){
				 //  $('#HOUSE_ATTR_DICT').combo('destroy');
				   //$('#GET_MODE_DICT').combo('destroy');
			}
		});
   }
   function setotherInfo(otherInfo)
   {
	   if(!otherInfo){
		   registerGetOtherData();
	   }
	  // $('#dissentInfoForm').form('load',attachInfo);
	  // $('#attachlimit').val("�� "+attachInfo.START_DATE+" �� "+attachInfo.END_DATE);
   }   
