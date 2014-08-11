/*********************************************************************************
*文  件  名  称:  registerattach.js
*功  能  描  述:  登记簿预览
*Copyright (c):  深圳道行天下软件技术有限公司
*创    建    人:  sam
*创  建  日  期： 2014-03-17
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/ 
/**********************************************************************************
*函数名称: registerGetAttachData
*功能说明: 
*参数说明: userdata
*返 回 值: 无
*函数作者: sam
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
 function registerGetAttachData(userdata){
		$.ajax({
			url: ctx+'/bookmanage/book-manage!bookInfoView.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"bok_is_type":7,"reg_unit_type":userdata.REG_UNIT_TYPE,"book_code":userdata.BOOK_CODE},
			success: function(data){
				setattachInfo(data);											//添加查封信息
			}
		});
   }
 
 /**********************************************************************************
  *函数名称: setHistoryAttachInfo()
  *功能说明: 设置查封历史信息
  *参    数：data
  *返 回 值: 
  *函数作者: sam
  *创建日期: 2014-03-25
  *修改历史: 
 ***********************************************************************************/ 
 function setHistoryAttachInfo(data){
 		  var regDate;
 		  var count;
 		  for(var i=0;i<data.length;i++){
 			  regDate = data[i].REG_DATE_N;
 			  if(!regDate){
 				  regDate="";
 			  }
 			  count= i+1;
 			  $("#table_attachInfo").append("<tr style='cursor:hand;'><td>"+count+"<input type='hidden' value='"+i+"'/></td><td><label>"+data[i].BUS_NAME+
 					  "</label></td><td><label>"+data[i].DIS_OFF+"</label></td><td><label>"+regDate+"</label></td></tr>");
 		  }
 		  //信息设置成功后  初妈化该表的事件  
 		  initHistoryAttachTable(data);
 		  if(data)
 		  getselectedattachrowdata(data[count-1]);
 	}
 /**********************************************************************************
  *函数名称: initHistoryAttachTable()
  *功能说明: 初始化查封Table
  *参    数：data
  *返 回 值: 
  *函数作者: sam
  *创建日期: 2014-03-25
  *修改历史: 
 ***********************************************************************************/ 
 function initHistoryAttachTable(data){
 	      var rowdata = data;
 		  var trs = document.getElementById('table_attachInfo').getElementsByTagName('tr');  
 		  for( var i=0; i<trs.length; i++ ){  
 			   trs[i].onmousedown = function(){  
 				  trAttachOnmouseDown(trs,rowdata,this);  
 			   }  
 		  }  
 	} 
 /**********************************************************************************
  *函数名称: trAttachOnmouseDown()
  *功能说明: 点击事件处理函数
  *参    数：trs:table中tr对象集合,data:获得点击行数据信息,obj：当前选择tr的对象
  *返 回 值: 
  *函数作者: sam
  *创建日期: 2014-03-25
  *修改历史: 
 ***********************************************************************************/ 
 function trAttachOnmouseDown(trs,data,obj){
 	   if(obj){
 		   var idx = obj.getElementsByTagName('input')[0].value;		//从行中的input中获取点击行的索引号
 		  if(data)
 		   getselectedattachrowdata(data[idx]);
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
  *函数名称: getselectedattachrowdata()
  *功能说明: 获得到选择行的数据
  *参    数：data
  *返 回 值: 
  *函数作者: sam
  *创建日期: 2014-03-25
  *修改历史: 
 ***********************************************************************************/ 
 function getselectedattachrowdata(data){
	   //var reg_unit_type = $.getUrlParam('reg_unit_type');
	   $('#attachInfoForm').form('load',data);
	   if(reg_unit_type == enumdata.house){
		   $("#HOUSE_CODE").val(data.HOUSE_CODE);
		   $("#HOUSE_LOCATION").val(data.HOUSE_LOCATION);
	   }else if(reg_unit_type == enumdata.parcel){
		   $("#PARCEL_CODE").val(data.PARCEL_CODE);
		   $("#LAND_ADDRESS").val(data.LAND_ADDRESS);
	   }
	   $("#BUS_NAME").val(data.BUS_NAME);
	   $("#DIS_OFF").val(data.DIS_OFF);
	   $("#LAW_DOC").val(data.LAW_DOC);
	   $("#DIS_NO").val(data.DIS_NO);
	   $("#DIS_DATE").val(data.ALIAS_DIS_DATE);
	   $("#ATTACH_LIMIT").val(data.ATTACH_LIMIT);
	   $("#REG_DATE_N").val(data.REG_DATE_N);
	   $("#RECORDER").val(data.RECORDER);
	   $("#REM_REG_CODE").val(data.REM_REG_CODE);
	   $("#REM_LAW_DOC").val(data.REM_LAW_DOC);
	   $("#REM_DIS_NO").val(data.REM_DIS_NO);
	   $("#JDIS_DATE").val(data.JDIS_DATE);
	   $("#JALIAS_REG_DATE").val(data.JALIAS_REG_DATE);
	   $("#JRECORDER").val(data.JRECORDER);
	   $("#EXCURSUS").val(data.EXCURSUS);
 }

 
 
 /**********************************************************************************
 *函数名称: setattachInfo()
 *功能说明: 设置查封信息
 *返 回 值: 
 *函数作者: sam
 *创建日期: 2014-03-25
 *修改历史: 
 ***********************************************************************************/ 
 function setattachInfo(attachInfo){
	 setHistoryAttachInfo(attachInfo);
 }
   

