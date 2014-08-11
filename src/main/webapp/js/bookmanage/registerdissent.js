/*********************************************************************************
*文  件  名  称:  registerdissent.js
*功  能  描  述:  登记簿预览
*Copyright (c):  深圳道行天下软件技术有限公司
*创    建    人:  sam
*创  建  日  期： 2014-03-17
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/ 

/**********************************************************************************
*函数名称: registerGetDissentData
*功能说明: 
*参数说明: userdata
*返 回 值: 无
*函数作者: sam
*创建日期: 2014-03-27
*修改历史: 
***********************************************************************************/
 function registerGetDissentData(userdata){
		$.ajax({
			url: ctx+'/bookmanage/book-manage!bookInfoView.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"bok_is_type":6,"reg_unit_type":userdata.REG_UNIT_TYPE,"book_code":userdata.BOOK_CODE},
			success: function(data){
				setdissentInfo(data);											//添加查封信息
			}
		});
   }
 
 /**********************************************************************************
  *函数名称: setHistoryDissentInfo()
  *功能说明: 设置查封历史信息
  *参    数：data
  *返 回 值: 
  *函数作者: sam
  *创建日期: 2014-03-25
  *修改历史: 
 ***********************************************************************************/ 
 function setHistoryDissentInfo(data){
 		  var regDate;
 		  var count;
 		  if(data)
 		  for(var i=0;i<data.length;i++){
 			  regDate = data[i].REG_DATE_N;
 			  if(!regDate){
 				  regDate="";
 			  }
 			  count= i+1;
 			  $("#table_dissentInfo").append("<tr style='cursor:hand;'><td>"+count+"<input type='hidden' value='"+i+"'/></td><td><label>"+data[i].BUS_NAME+
 					  "</label></td><td><label>"+data[i].DISS_APP+"</label></td><td><label>"+regDate+"</label></td></tr>");
 		  }
 		  //信息设置成功后  初妈化该表的事件  
 		  initHistoryDissentTable(data);
 		  if(data)
 		  getselectedDissentrowdata(data[count-1]);
 	}
 /**********************************************************************************
  *函数名称: initHistoryDissentTable()
  *功能说明: 初始化查封Table
  *参    数：data
  *返 回 值: 
  *函数作者: sam
  *创建日期: 2014-03-25
  *修改历史: 
 ***********************************************************************************/ 
 function initHistoryDissentTable(data){
 	      var rowdata = data;
 		  var trs = document.getElementById('table_dissentInfo').getElementsByTagName('tr');  
 		  for( var i=0; i<trs.length; i++ ){  
 			   trs[i].onmousedown = function(){  
 				  trDissentOnmouseDown(trs,rowdata,this);  
 			   }  
 		  }  
 	} 
 /**********************************************************************************
  *函数名称: trDissentOnmouseDown()
  *功能说明: 点击事件处理函数
  *参    数：trs:table中tr对象集合,data:获得点击行数据信息,obj：当前选择tr的对象
  *返 回 值: 
  *函数作者: sam
  *创建日期: 2014-03-25
  *修改历史: 
 ***********************************************************************************/ 
 function trDissentOnmouseDown(trs,data,obj){
 	   if(obj){
 		   var idx = obj.getElementsByTagName('input')[0].value;		//从行中的input中获取点击行的索引号
 		  if(data)
 		   getselectedDissentrowdata(data[idx]);
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
  *函数名称: getselectedDissentrowdata()
  *功能说明: 获得到选择行的数据
  *参    数：data
  *返 回 值: 
  *函数作者: sam
  *创建日期: 2014-03-25
  *修改历史: 
 ***********************************************************************************/ 
 function getselectedDissentrowdata(data){
	   if(data){
		  //var reg_unit_type = $.getUrlParam('reg_unit_type');
		   $('#dissentInfoForm').form('load',data);
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
		   $("#CAN_REG_CODE").val(data.CAN_REG_CODE);
		   $("#DISS_APP").val(data.DISS_APP);
		   $("#DISS_ITEM").val(data.DISS_ITEM);
		   $("#CAN_DISS_APP").val(data.CAN_DISS_APP);
		   $("#CAN_DISS_REG_DATE_N").val(data.CAN_DISS_REG_DATE_N);
		   $("#CAN_DISS_REC").val(data.CAN_DISS_REC);
		   $("#EXCURSUS").val(data.EXCURSUS+"===="+data.CAN_EXCURSUS);
	   }
 }

 
 
 /**********************************************************************************
 *函数名称: setdissentInfo()
 *功能说明: 设置异议登记信息
 *返 回 值: 
 *函数作者: sam
 *创建日期: 2014-03-25
 *修改历史: 
 ***********************************************************************************/
   function setdissentInfo(dissentInfo){
	   setHistoryDissentInfo(dissentInfo);
   }   
