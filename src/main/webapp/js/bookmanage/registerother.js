/*********************************************************************************
*文  件  名  称:  registerother.js
*功  能  描  述:  登记簿预览
*Copyright (c):  深圳道行天下软件技术有限公司
*创    建    人:  sam
*创  建  日  期： 2014-03-17
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/ 
/**********************************************************************************
*函数名称: registerGetEasementData
*功能说明: 
*参数说明: 无
*返 回 值: 无
*函数作者: sam
*创建日期: 2014-03-17
*修改历史: 
***********************************************************************************/
 function registerGetOtherData(){
	    var otherInfo;				//其他信息
		$.ajax({
			url: ctx+'/bookmanage/book-manage!getregisterInfo.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"proc_id":proc_id},
			success: function(data){
				//alert(JSON.stringify(data));
				otherInfo = data.userInfo;											//查封信息
				setotherInfo(otherInfo);											//添加查封信息
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
	  // $('#attachlimit').val("从 "+attachInfo.START_DATE+" 到 "+attachInfo.END_DATE);
   }   
