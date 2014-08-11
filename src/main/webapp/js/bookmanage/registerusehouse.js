/*********************************************************************************
*文  件  名  称: registerusehouse.js
*功  能  描  述: 登记簿预览
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: sam
*创  建  日  期： 2014-03-17
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/    

/**********************************************************************************
*函数名称: registerGetUseHouseData()
*功能说明: 
*参数说明：userdata
*返 回 值: 
*函数作者: sam
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/      
function registerGetUseHouseData(userdata){
	$.ajax({
		url: ctx+'/bookmanage/book-manage!bookInfoView.action?time='+new Date(),
		dataType: 'json',
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"bok_is_type":2,"reg_unit_type":userdata.REG_UNIT_TYPE,"book_code":userdata.BOOK_CODE},
		success: function(data){											         
			setOwnershipInfo(data);											
		},
		error:function(){
			
		}
	});
}

/**********************************************************************************
*函数名称: registerGetHolderData()
*功能说明: 
*参    数：userdata
*返 回 值: 
*函数作者: sam
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
function registerGetHolderData(userdata){
	$.ajax({
		url: ctx+'/bookmanage/book-manage!getBookHolderLstByParam.action?time='+new Date(),
		dataType: 'json',
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"reg_unit_type":userdata.REG_UNIT_TYPE,"book_code":userdata.BOOK_CODE,"reg_code":userdata.REG_CODE},
		success: function(data){									
			setHoldersInfo(data);											//填充所有权信息
		}
	});
} 

/**********************************************************************************
*函数名称: setHoldersInfo()
*功能说明: 设置权利人信息
*参数说明：holder
*返 回 值:
*函数作者: sam
*创建日期: 2014-03-28
*修改历史: 
***********************************************************************************/   
function setHoldersInfo(holder)
{         
	   if(!holder) return;
	   var data = holder;
	   if(holder.length < 1){
			return;
		}
		$("#table_holder").empty();
		var count;
		$("#table_holder").append("<tr><td>序号</td><td>权利人</td><td>证件种类</td><td>身份证明号</td><td>通讯地址</td><td>共有情况</td><td>房地产证号</td></tr>");
		  //alert(JSON.stringify(data[0]));
		  for(var i = 0;i < data.length; i++){
			  count= i+1;
			  $("#table_holder").append("<tr><td>"+count+"</td>"+
					  "<td><label>"+data[i].HOL_NAME+"</label></td>"+
					  "<td><label>"+data[i].HOL_CER_TYPE+"</label></td>"+
					  "<td><label>"+data[i].HOL_CER_NO+"</label></td>"+
					  "<td><label>"+data[i].HOL_ADDRESS+"</label></td>"+
					  "<td><label>&nbsp;</label></td>"+
					  "<td><label>"+data[i].CER_NO+"</label></td>"+
					  "</tr>"	  
			  );
		  }  
 }


/**********************************************************************************
 *函数名称: setHistoryOwnershipInfo()
 *功能说明: 设置所有权历史信息
 *参    数：data
 *返 回 值: 
 *函数作者: sam
 *创建日期: 2014-03-25
 *修改历史: 
***********************************************************************************/ 
function setHistoryOwnershipInfo(data){
		  var regDate;
		  var count;
		  if(data)
		  for(var i=0;i<data.length;i++){
			  regDate = data[i].REG_DATE;
			  if(!regDate){
				  regDate="";
			  }
			  count= i+1;
			  $("#table_hisOwnInfo").append("<tr style='cursor:hand;'><td>"+count+"<input type='hidden' value='"+i+"'/></td><td><label>"+data[i].BUS_NAME+
					  "</label></td><td><label>"+data[i].R_COLL_NAMES+"</label></td><td><label>"+regDate+"</label></td></tr>");
		  }
		  //信息设置成功后  初妈化该表的事件  
		  initHistoryOwnershipTable(data);
		  if(data)
		  getselectedownershiprowdata(data[count-1]);
	}
/**********************************************************************************
 *函数名称: initHistoryOwnershipTable()
 *功能说明: 初始化所有权Table
 *参    数：data
 *返 回 值: 
 *函数作者: sam
 *创建日期: 2014-03-25
 *修改历史: 
***********************************************************************************/ 
function initHistoryOwnershipTable(data){
	      var rowdata = data;
		  var trs = document.getElementById('table_hisOwnInfo').getElementsByTagName('tr');  
		  for( var i=0; i<trs.length; i++ ){  
			   trs[i].onmousedown = function(){  
				   trUseHouseOnmouseDown(trs,rowdata,this);  
			   }  
		  }  
	} 
/**********************************************************************************
 *函数名称: trUseHouseOnmouseDown()
 *功能说明: 点击事件处理函数
 *参    数：trs:table中tr对象集合,data:获得点击行数据信息,obj：当前选择tr的对象
 *返 回 值: 
 *函数作者: sam
 *创建日期: 2014-03-25
 *修改历史: 
***********************************************************************************/ 
function trUseHouseOnmouseDown(trs,data,obj){
	   if(obj){
		   
		   var idx = obj.getElementsByTagName('input')[0].value;		//从行中的input中获取点击行的索引号
	       /*var right_rel_id = obj.getElementsByTagName('input')[0].value;		//从行中的input中获取权利人集合ID
	 	   var reg_unit_type = obj.getElementsByTagName('input')[1].value;	//从行中的input中获取登记单元类型
	 	   var parcel_code = obj.getElementsByTagName('input')[2].value;		//从行中的input中获取登记单元编号
*/	 	   //var cdata = {"RIGHT_REL_ID":right_rel_id,"REG_UNIT_TYPE":reg_unit_type,"PARCEL_CODE":parcel_code};
		   if(data)
		   getselectedownershiprowdata(data[idx]);
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
 *函数名称: getselectedownershiprowdata()
 *功能说明: 获得到选择行的数据
 *参    数：data
 *返 回 值: 
 *函数作者: sam
 *创建日期: 2014-03-25
 *修改历史: 
***********************************************************************************/ 
function getselectedownershiprowdata(data){
	if(data){
	   $('#ownershipInfoForm').form('load',data);
	   $("#HOUSE_CODE").val(data.HOUSE_CODE);
	   $("#HOUSE_LOCATION").val(data.HOUSE_LOCATION);
	   $("#BUS_NAME").val(data.BUS_NAME);
	   $("#HOUSE_KIND_N").val(data.HOUSE_KIND_N);
	   $("#GET_MODE").val(data.GET_MODE);
	   $("#RECORDER").val(data.RECORDER);
	   $("#REG_DATE").val(data.REG_DATE);
	   $("#EXCURSUS").val(data.EXCURSUS);
	   registerGetHolderData(data);
	}
}
/**********************************************************************************
*函数名称: setOwnershipInfo()
*功能说明: 填充所有权信息
*返 回 值: 
*函数作者: sam
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/   
function setOwnershipInfo(data){
	/*   $('#ownershipInfoForm').form('load',data);
   holder = data.holder;
	setHoldersInfo(holder,data.rightCertificateNo);					//设置申请人信息  传进房地产证号
	
	historyOwnershipInfo = data.historyOwnershipInfo;				//历史所有权信息
*/	
	setHistoryOwnershipInfo(data);								//设置历史所有权信息表
}

/**********************************************************************************
*函数名称: validate()
*功能说明: 通用校验方法  在这里不用校验  直接返回true
*返 回 值: 
*参   数:
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
function validate(){
	return true;
}
   
   
