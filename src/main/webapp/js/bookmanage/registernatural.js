/*********************************************************************************
*文  件  名  称:  registernatural.js
*功  能  描  述:  登记簿预览
*Copyright (c):  深圳道行天下软件技术有限公司
*创    建    人:  sam
*创  建  日  期： 2014-03-17
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
 $(function(){
	   var reg_unit_type = $.getUrlParam('reg_unit_type');
	   if(reg_unit_type == enumdata.house){
		    $('.classtr').css('display','block')
	   }else{
		   $('.classtr').css('display','none')
	   }
 });
//填充自然信息
/**********************************************************************************
*函数名称: setNaturalInfo
*功能说明: 填充自然信息
*参数说明: 无
*返 回 值: 无
*函数作者: sam
*创建日期: 2014-03-17
*修改历史: 
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
		   $("#BUILD_AREA").val(naturalInfo.BUILD_AREA+" ㎡");
		   $("#INNER_AREA").val(naturalInfo.INNER_AREA+" ㎡");
		   $("#SHARE_AREA").val(naturalInfo.SHARE_AREA+" ㎡");
		   $("#EXCURSUS").val(naturalInfo.FW_EXCURSUS);
	   }
   }
}

/**********************************************************************************
*函数名称: registerGetNaturalData
*功能说明: 
*参数说明: 无
*返 回 值: 无
*函数作者: sam
*创建日期: 2014-03-17
*修改历史: 
***********************************************************************************/
function registerGetNaturalData(userdata){
	    var naturalInfo;					//自然信息数据
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
