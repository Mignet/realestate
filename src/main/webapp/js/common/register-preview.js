/*********************************************************************************
*文  件  名  称: register-preview.js
*功  能  描  述: 登记簿预览
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Joyon
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
//打开的父窗体
var openerWindow;
//登簿人  登记预览时需要
//登记编号      登记预览时需要
   
var selectNode;
//var proc=this.parent.proc;
var proc;
//流程实例ID
var proc_id;
/**********************************************************************************
*函数名称: init()
*功能说明: 初始化方法  加载左侧树 加载登记簿预览用的数据
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/     
function init(proc){
	this.proc = proc;					//父窗口传过来的流程属性
	proc_id = proc.procId;				//流程实例ID赋值
	
	$('body').layout({
		fit:true
	});		
	//加载登记簿预览树
	$('#registerbook_tree').tree({  
		url:ctx+"/json/registerbook_data.json",
		onClick: function(node){
			selectNode=node;
				var selectedDiv;
				if(selectNode.text=='自然信息'){
					selectedDiv=$("#naturalInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='所有权信息'){
					selectedDiv=$("#ownershipInfo").css('display','block').siblings('div').css('display','none');
				}
			},
			onLoadSuccess:function(node,data){
				var defaultNode;
				for(var i=0;i<data.length;i++){
					if(data[i].text=='自然信息');{
					defaultNode=data[i];
					break;
				}
			}
			var node = $('#registerbook_tree').tree('find', defaultNode.id);
			$('#registerbook_tree').tree('select', node.target);
			
			$('#naturalInfo').css('display','block');
		}
	});
	registerPreview();				//登记簿预览主方法
	$(":input").attr("readonly","readonly");		//设置所有input组件为不可编辑状态
};
// init method end
/**********************************************************************************
*函数名称: registerPreview()
*功能说明: 登记簿预览主方法  调用后台方法  给页面设值 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/      
function registerPreview(){
   var naturalInfo;					//自然信息数据
   var ownershipInfo;				//所有权信息数据
   var applicants;					//申请人信息 getregisterInfo
   var ownershipInfoHis;			//获取要注销的所有权信息
   var historyOwnershipInfo;		//所有权历史信息 registerPreview
	$.ajax({
		url: ctx+'/common/register!getregisterInfo.action?time='+new Date(),
		dataType: 'json',
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"proc_id":proc_id},
		success: function(data){
			JSON.stringify(data.userInfoHis);
			//JSON.stringify(data.userInfoHis);
			naturalInfo = data.naturalInfo;												//自然信息
			setNaturalInfo(naturalInfo);												//设置自然信息
			ownershipInfo = data.userInfo;											//所有权信息
			setOwnershipInfo(ownershipInfo);											//填充所有权信息
			if(data.userInfoHis)
			{
				ownershipInfoHis=data.userInfoHis;           //所有权信息
				setcanInfoHis(ownershipInfoHis);
			}
		},
		error:function(){
			
			}
		});
}
/**********************************************************************************
*函数名称: setcanInfoHis()
*功能说明: 填充房地产证注销信息
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/   
function setcanInfoHis(userInfoHis){
   $("#can_regcode").val(userInfoHis.REG_CODE);
   $("#can_reason").val(userInfoHis.REASON);   
   $("#can_regDate").val(userInfoHis.REG_DATE);
   $("#can_recorder").val(userInfoHis.RECORDER);
}


/**********************************************************************************
*函数名称: setNaturalInfo()
*功能说明: 填充自然信息
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/   
function setNaturalInfo(naturalInfo){
   $('#naturalInfoForm').form('load',naturalInfo);
   $('#ownershipInfoForm').form('load',naturalInfo);
   
   $("#BUILD_AREA").val($("#BUILD_AREA").val()+" ㎡");
   
   $("#TAONEI_AREA").val($("#TAONEI_AREA").val()+" ㎡");
   
   $("#FT_COMMON_AREA").val($("#FT_COMMON_AREA").val()+" ㎡");
   
   //$("#AT_FLOOR").val(naturalInfo.ROOMNAME+"("+naturalInfo.AT_FLOOR+")");
   
   $("#USE_PER").val($("#USE_PER").val()+" 年 "+naturalInfo.PA_START_DATE+" 到  "+naturalInfo.PA_END_DATE);
	   
}
/**********************************************************************************
*函数名称: setHoldersInfo()
*功能说明: 设置权利人信息
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/   
function setHoldersInfo(holder,rightCertificateNo){
	var data = holder;
	if(holder.length<1){
		return;
	}
	$("#table_holder").empty();
	$("#table_holder").append("<tr><td>序号</td><td>所有权人</td><td>身份证号</td><td>户籍所在地</td><td>共有情况</td><td>房地产证号</td></tr>");
	var cerNo=" ";
	if(rightCertificateNo){
		if(rightCertificateNo.length>0){
			cerNo =rightCertificateNo[0].CER_NO;
		}
	}
	$("#CER_NO").val(cerNo);
	var holder;
	var regDate;
	var count;
	
	for(var i=0;i<data.length;i++){
		  count= i+1;
		  $("#table_holder").append("<tr><td>"+count+"</td>"+
			  "<td><label>"+data[i].HOL_NAME+"</label></td>"+
			  "<td><label>"+data[i].HOL_CER_NO+"</label></td>"+
			  "<td><label>"+""+"</label></td>"+
			  "<td><label>"+data[i].PORTION+"</label></td>"+
			  "<td><label>"+cerNo+"</label></td>"+
			  "</tr>"
		  );
	 }
}
/**********************************************************************************
*函数名称: setOwnershipInfo()
*功能说明: 填充所有权信息
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/   
function setOwnershipInfo(data){
	//alert(JSON.stringify(data));
	   $('#ownershipInfoForm').form('load',data);
   holder = data.holder;
	setHoldersInfo(holder,data.rightCertificateNo);					//设置申请人信息  传进房地产证号
	
	historyOwnershipInfo = data.historyOwnershipInfo;				//历史所有权信息
	
	setHistoryOwnershipInfo(historyOwnershipInfo);					//设置历史所有权信息表
}
/**********************************************************************************
*函数名称: setHistoryOwnershipInfo()
*功能说明: 设置所有权历史信息  把regId放进行input 设置为hidden
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/   
function setHistoryOwnershipInfo(data){
	
	
	  var holder;
	  var regDate;
	  var count;
	  for(var i=0;i<data.length;i++){
		  tempHolder =data[i].holder;
		  for(var j=0;j < tempHolder.length; j++){
			  if(j==0){
				  holder = tempHolder[j].HOL_NAME;
			  }else{
				  holder = holder+"、"+tempHolder[j].HOL_NAME; 
		  }
	  }
	  regDate = data[i].regDate;
	  if(!regDate){
		  regDate="";
	  }
	  count= i+1;
	  $("#table_hisOwnInfo").append("<tr><td>"+count+"<input type='hidden' value='"+data[i].regId+"'/></td><td><label>"+data[i].busName+
			  "</label></td><td><label>"+holder+"</label></td><td><label>"+regDate+"</label></td></tr>");
  }
  //信息设置成功后  初妈化该表的事件  
  initHistoryOwnershipTable();
}
   
/**********************************************************************************
*函数名称: initHistoryOwnershipTable()
*功能说明: 初始化所有权历史表   加上行点击事件
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
function initHistoryOwnershipTable(){
	  var trs = document.getElementById('table_hisOwnInfo').getElementsByTagName('tr');  
	  for( var i=0; i<trs.length; i++ ){  
		   trs[i].onmousedown = function(){  
			   trOnmouseDown(trs,this);  
		   }  
	  }  
} 
/**********************************************************************************
*函数名称: trOnmouseDown()
*功能说明: 所有权历史表 行点击事件 点击行后  改变背景颜色  重新加载所有权数据
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
function trOnmouseDown(trs,obj){  
  for( var o=0; o<trs.length; o++ ){  
   if( trs[o] == obj ){  
    trs[o].style.backgroundColor = '#DFEBF2';  
	  var regId = trs[o].getElementsByTagName('input')[0].value;		//从行中的input中获取登记编号
	  reloadOwnershipInfoByRegId(regId);								//重新加载所有权相关信息    --自然信息未重新加载
   }  
   else{  
      trs[o].style.backgroundColor = '';  
   }  
  }  
} 
/**********************************************************************************
*函数名称: reloadOwnershipInfoByRegId()
*功能说明: 重新加载所有权信息  通过登记编号 从行中的input中获取登记编号
*返 回 值: 
*参   数:regId 登记编号
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
function reloadOwnershipInfoByRegId(regId){
	$("#REG_CODE").val(regId);
	 $.ajax({
			url: ctx+'/common/register!getOwnershipInfo.action?time='+new Date(),
		dataType: 'json',
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"reg_code":regId},
		success: function(data){
			//alert(JSON.stringify(data));
			$('#ownershipInfoForm').form('load',ownershipInfo);							//重新加载所有权信息
			holder = data.holder;
			setHoldersInfo(holder,data.rightCertificateNo);					//设置申请人信息  传进房地产证号
			}
	 });
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
   
   
