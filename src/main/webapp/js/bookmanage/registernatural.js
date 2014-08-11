/*********************************************************************************
*��  ��  ��  ��:  registernatural.js
*��  ��  ��  ��:  �Ǽǲ�Ԥ��
*Copyright (c):  ���ڵ�����������������޹�˾
*��    ��    ��:  sam
*��  ��  ��  �ڣ� 2014-03-17
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
 $(function(){
	   var reg_unit_type = $.getUrlParam('reg_unit_type');
	   if(reg_unit_type == enumdata.house){
		    $('.classtr').css('display','block')
	   }else{
		   $('.classtr').css('display','none')
	   }
 });
//�����Ȼ��Ϣ
/**********************************************************************************
*��������: setNaturalInfo
*����˵��: �����Ȼ��Ϣ
*����˵��: ��
*�� �� ֵ: ��
*��������: sam
*��������: 2014-03-17
*�޸���ʷ: 
***********************************************************************************/
function setNaturalInfo(naturalInfo){
   //alert(JSON.stringify(naturalInfo));
   //var reg_unit_type = $.getUrlParam('reg_unit_type');
   if(naturalInfo){
	   $('#naturalInfoForm').form('load',naturalInfo);
	   $("#PARCEL_CODE").val(naturalInfo.PARCEL_CODE);
	   $("#LAND_ADDRESS").val(naturalInfo.LAND_ADDRESS);
	   $("#PARCEL_CODE1").val(naturalInfo.PARCEL_CODE);
	   $("#LAND_ATTRIBUTE_N").val(naturalInfo.LAND_ATTRIBUTE_N);
	   $("#CER_NO").val(naturalInfo.CER_NO);
	   $("#USERIGHT_TYPE").val(naturalInfo.USERIGHT_TYPE);
	   $("#USE_PER").val(naturalInfo.USE_PER);
	   $("#EXCURSUS").val(naturalInfo.EXCURSUS);
	   if(reg_unit_type == enumdata.house){
		   $("#HOUSE_LOCATION").val(naturalInfo.HOUSE_LOCATION);
		   $("#HOUSE_CODE").val(naturalInfo.HOUSE_CODE);
		   $("#LAYER_COUNT").val(naturalInfo.LAYER_COUNT);
		   $("#PLAN_USAGE").val(naturalInfo.PLAN_USAGE);
		   $("#AT_FLOOR").val(naturalInfo.AT_FLOOR);
		   $("#HOUSE_STRUT_N").val(naturalInfo.HOUSE_STRUT_N);
		   $("#BUILD_AREA").val(naturalInfo.BUILD_AREA+" �O");
		   $("#INNER_AREA").val(naturalInfo.INNER_AREA+" �O");
		   $("#SHARE_AREA").val(naturalInfo.SHARE_AREA+" �O");
		   $("#EXCURSUS").val(naturalInfo.FW_EXCURSUS);
	   }
   }
}

/**********************************************************************************
*��������: registerGetNaturalData
*����˵��: 
*����˵��: ��
*�� �� ֵ: ��
*��������: sam
*��������: 2014-03-17
*�޸���ʷ: 
***********************************************************************************/
function registerGetNaturalData(userdata){
	    var naturalInfo;					//��Ȼ��Ϣ����
		$.ajax({
			url: ctx+'/bookmanage/book-manage!bookInfoView.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"bok_is_type":1,"reg_unit_type":userdata.REG_UNIT_TYPE,"book_code":userdata.BOOK_CODE},
			success: function(data){
				setNaturalInfo(data);							
			}
		});
}
