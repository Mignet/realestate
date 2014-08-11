/**
 * 登记簿预览js
 */
/**********************************************************************************
*函数名称: registerGetPreadviceData
*功能说明: 
*参数说明: 无
*返 回 值: 无
*函数作者: sam
*创建日期: 2014-03-17
*修改历史: 
***********************************************************************************/
 function registerGetPreadviceData(userdata){
	    var preadviceInfo;				//预告登记信息
		$.ajax({
			url: ctx+'/bookmanage/book-manage!bookInfoView.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"bok_is_type":5,"reg_unit_type":userdata.REG_UNIT_TYPE,"book_code":userdata.BOOK_CODE},
			success: function(data){
				setpreadvcieInfo(data);											
			},
			error:function(){
			}
		});
   }
 /**********************************************************************************
  *函数名称: setHistoryPreadviceInfo()
  *功能说明: 设置预告登记历史信息
  *参    数：data
  *返 回 值: 
  *函数作者: sam
  *创建日期: 2014-03-25
  *修改历史: 
 ***********************************************************************************/ 
 function setHistoryPreadviceInfo(data){
 		  var regDate;
 		  var count;
 		  if(data)
 		  for(var i=0;i<data.length;i++){
 			  regDate = data[i].REG_DATE_N;
 			  if(!regDate){
 				  regDate="";
 			  }
 			  count= i+1;
 			  $("#table_preadviceInfo").append("<tr style='cursor:hand;'><td>"+count+"<input type='hidden' value='"+i+"'/></td><td><label>"+data[i].BUS_NAME+
 					  "</label></td><td><label>"+data[i].ADV_HOLDER+"</label></td><td><label>"+regDate+"</label></td></tr>");
 		  }
 		  //信息设置成功后  初妈化该表的事件  
 		 initHistoryPreadviceTable(data);
 		  if(data)
 		 getselectedPreadvicerowdata(data[count-1]);
 	}
 /**********************************************************************************
  *函数名称: initHistoryPreadviceTable()
  *功能说明: 初始化预告登记Table
  *参    数：data
  *返 回 值: 
  *函数作者: sam
  *创建日期: 2014-03-25
  *修改历史: 
 ***********************************************************************************/ 
 function initHistoryPreadviceTable(data){
 	      var rowdata = data;
 		  var trs = document.getElementById('table_preadviceInfo').getElementsByTagName('tr');  
 		  for( var i=0; i<trs.length; i++ ){  
 			   trs[i].onmousedown = function(){  
 				  trPreadviceOnmouseDown(trs,rowdata,this);  
 			   }  
 		  }  
 	} 
 /**********************************************************************************
  *函数名称: trPreadviceOnmouseDown()
  *功能说明: 点击事件处理函数
  *参    数：trs:table中tr对象集合,data:获得点击行数据信息,obj：当前选择tr的对象
  *返 回 值: 
  *函数作者: sam
  *创建日期: 2014-03-25
  *修改历史: 
 ***********************************************************************************/ 
 function trPreadviceOnmouseDown(trs,data,obj){
 	   if(obj){
 		   
 		   var idx = obj.getElementsByTagName('input')[0].value;		//从行中的input中获取点击行的索引号
 	       /*var right_rel_id = obj.getElementsByTagName('input')[0].value;		//从行中的input中获取权利人集合ID
 	 	   var reg_unit_type = obj.getElementsByTagName('input')[1].value;	//从行中的input中获取登记单元类型
 	 	   var parcel_code = obj.getElementsByTagName('input')[2].value;		//从行中的input中获取登记单元编号
 */	 	   //var cdata = {"RIGHT_REL_ID":right_rel_id,"REG_UNIT_TYPE":reg_unit_type,"PARCEL_CODE":parcel_code};
 		  if(data)
 		   getselectedPreadvicerowdata(data[idx]);
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
  *函数名称: getselectedPreadvicerowdata()
  *功能说明: 获得到选择行的数据
  *参    数：data
  *返 回 值: 
  *函数作者: sam
  *创建日期: 2014-03-25
  *修改历史: 
 ***********************************************************************************/ 
 function getselectedPreadvicerowdata(data){
	 if(data){
 	   $('#preadviceInfoForm').form('load',data);
 	   $("#HOUSE_CODE").val(data.ADV_HOUSE_CODE);
 	   $("#HOUSE_LOCATION").val(data.HOUSE_LOCATION);
 	   $("#BUS_NAME").val(data.BUS_NAME);
 	   $("#REG_CODE").val(data.REG_CODE);
 	   $("#ADV_HOLDER").val(data.ADV_HOLDER);
 	   $("#ADV_HOLDER_ID").val(data.ADV_HOLDER_ID);
 	   $("#ADV_VOL").val(data.ADV_VOL);
 	   $("#ADV_VOL_ID").val(data.ADV_VOL_ID);
 	   $("#CER_NO").val(data.CER_NO);
 	   $("#RECORDER").val(data.RECORDER);
 	   $("#REG_DATE_N").val(data.REG_DATE_N);
 	   $("#CAN_ADV_NO").val(data.CAN_ADV_NO);
 	   $("#CAN_ADV_DATE_N").val(data.CAN_ADV_DATE_N);
 	   $("#CAN_ADV_REC").val(data.CAN_ADV_REC);
 	   $("#EXCURSUS").val(data.EXCURSUS);
	 }
 }
 /**********************************************************************************
 *函数名称: setpreadvcieInfo
 *功能说明: 
 *参数说明: preadviceInfo
 *返 回 值: 无
 *函数作者: sam
 *创建日期: 2014-03-17
 *修改历史: 
 ***********************************************************************************/
   function setpreadvcieInfo(preadviceInfo)
   {
	   setHistoryPreadviceInfo(preadviceInfo);
	  // $('#dissentInfoForm').form('load',attachInfo);
	  // $('#attachlimit').val("从 "+attachInfo.START_DATE+" 到 "+attachInfo.END_DATE);
   }   
