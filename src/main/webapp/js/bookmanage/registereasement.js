/*********************************************************************************
*文  件  名  称:  registereasement.js
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
 function registerGetEasementData(userdata){
	    var easementInfo;				//地役权信息
		$.ajax({
			url: ctx+'/bookmanage/book-manage!bookInfoView.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"bok_is_type":4,"reg_unit_type":userdata.REG_UNIT_TYPE,"book_code":userdata.BOOK_CODE},
			success: function(data){
				seteasementInfo(data);											//添加查封信息
			},
			error:function(){
			}
		});
   }
 /**********************************************************************************
  *函数名称: setHistoryEasementInfo()
  *功能说明: 设置地役权历史信息
  *参    数：data
  *返 回 值: 
  *函数作者: sam
  *创建日期: 2014-03-25
  *修改历史: 
 ***********************************************************************************/ 
 function setHistoryEasementInfo(data){
 		  var regDate;
 		  var count;
 		  if(data)
 		  for(var i=0;i<data.length;i++){
 			  regDate = data[i].REG_DATE_N;
 			  if(!regDate){
 				  regDate="";
 			  }
 			  count= i+1;
 			  $("#table_easementInfo").append("<tr style='cursor:hand;'><td>"+count+"<input type='hidden' value='"+i+"'/></td><td><label>"+data[i].BUS_NAME+
 					  "</label></td><td><label>"+data[i].EASE_HOLDER+"</label></td><td><label>"+regDate+"</label></td></tr>");
 		  }
 		  //信息设置成功后  初妈化该表的事件  
 		  initHistoryEasementTable(data);
 		  if(data)
 		 getselectedEasementrowdata(data[count-1]);
 	}
 
 /**********************************************************************************
  *函数名称: initHistoryEasementTable()
  *功能说明: 初始化地役权Table
  *参    数：data
  *返 回 值: 
  *函数作者: sam
  *创建日期: 2014-03-25
  *修改历史: 
 ***********************************************************************************/ 
 function initHistoryEasementTable(data){
 	      var rowdata = data;
 		  var trs = document.getElementById('table_easementInfo').getElementsByTagName('tr');  
 		  for( var i=0; i<trs.length; i++ ){  
 			   trs[i].onmousedown = function(){  
 				  trEasementOnmouseDown(trs,rowdata,this);  
 			   }  
 		  }  
 	} 
 /**********************************************************************************
  *函数名称: trEasementOnmouseDown()
  *功能说明: 点击事件处理函数
  *参    数：trs:table中tr对象集合,data:获得点击行数据信息,obj：当前选择tr的对象
  *返 回 值: 
  *函数作者: sam
  *创建日期: 2014-03-25
  *修改历史: 
 ***********************************************************************************/ 
 function trEasementOnmouseDown(trs,data,obj){
 	   if(obj){
 		   var idx = obj.getElementsByTagName('input')[0].value;		//从行中的input中获取点击行的索引号
 		  if(data)
 		  getselectedEasementrowdata(data[idx]);
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
  *函数名称: getselectedEasementrowdata()
  *功能说明: 获得到选择行的数据
  *参    数：data
  *返 回 值: 
  *函数作者: sam
  *创建日期: 2014-03-25
  *修改历史: 
 ***********************************************************************************/ 
 function getselectedEasementrowdata(data){
	   if(data){
		   //var reg_unit_type = $.getUrlParam('reg_unit_type');
		   $('#easementInfoForm').form('load',data);
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
		   $("#EASE_HOLDER").val(data.EASE_HOLDER);
		   $("#EXCURSUS").val(data.EXCURSUS);
	   }
 }
 /**********************************************************************************
  *函数名称: seteasementInfo()
  *功能说明: 设置地役权登记信息
  *返 回 值: 
  *函数作者: sam
  *创建日期: 2014-03-25
  *修改历史: 
  ***********************************************************************************/
    function seteasementInfo(easementInfo){
    	setHistoryEasementInfo(easementInfo);
    }   
