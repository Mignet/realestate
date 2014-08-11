/*********************************************************************************
*文  件  名  称: registermort.js
*功  能  描  述: 抵押权登记簿预览js
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: panda
*创  建  日  期： 2014-02-28
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/

   //打开的父窗体

/**********************************************************************************
*函数名称: registerGetMortData
*功能说明: 
*参数说明: 无
*返 回 值: 无
*函数作者: sam
*创建日期: 2014-03-17
*修改历史: 
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
 *函数名称: setHistoryMortInfo()
 *功能说明: 设置抵押权历史信息
 *参    数：data
 *返 回 值: 
 *函数作者: sam
 *创建日期: 2014-03-25
 *修改历史: 
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
		  //信息设置成功后  初妈化该表的事件  
		  initHistoryMortTable(data);
		  if(data)
		  getselectedmortrowdata(data[count-1]);
	}
/**********************************************************************************
 *函数名称: initHistoryMortTable()
 *功能说明: 初始化抵押权Table
 *参    数：data
 *返 回 值: 
 *函数作者: sam
 *创建日期: 2014-03-25
 *修改历史: 
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
 *函数名称: trMortOnmouseDown()
 *功能说明: 点击事件处理函数
 *参    数：trs:table中tr对象集合,data:获得点击行数据信息,obj：当前选择tr的对象
 *返 回 值: 
 *函数作者: sam
 *创建日期: 2014-03-25
 *修改历史: 
***********************************************************************************/ 
function trMortOnmouseDown(trs,data,obj){
	   if(obj){
		   var idx = obj.getElementsByTagName('input')[0].value;		//从行中的input中获取点击行的索引号
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
 *函数名称: getselectedmortrowdata()
 *功能说明: 获得到选择行的数据
 *参    数：data
 *返 回 值: 
 *函数作者: sam
 *创建日期: 2014-03-25
 *修改历史: 
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

//填充所有权人信息
/**********************************************************************************
*函数名称: setMortInfo()
*功能说明: 设置抵押信息
*返 回 值: 
*函数作者: sam
*创建日期: 2014-03-25
*修改历史: 
***********************************************************************************/ 
function setMortInfo(mortInfo){
	setHistoryMortInfo(mortInfo);
}
 
  
   
