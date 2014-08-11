/**
 * 登记簿预览js
 */

   //打开的父窗体
   var selectNode;
   var proc=this.parent.proc;
   //流程实例ID
   var proc_id;
   var cur_reg_unit_code;					//当前查看的登记单元的编号
   var old_reg_unit_code;					//上次登记单元编号    用来判断是否需要从后台取数据
   function init(proc){
	   
	   $(":input").attr("readonly", "readonly");
	  proc_id = proc.procId;
	  //alert(JSON.stringify(proc));
	   $('body').layout({
			fit:true
		});		
		
		$('#registerbook_tree').tree({  
			url:ctx+"/json/attach_data.json",
			onClick: function(node){
				//cur_reg_unit_code = node.attributes.code;							//点击一行时时给当前选择的reg_unit_code赋值
				//如果当前登记单元编号不等于上次登记单元编号    表示  切换成另一登记单元     更新当前页面数据      ----其它则直接用当前数据
				if(old_reg_unit_code!=cur_reg_unit_code){
					//registerPreview("");
				}
				selectNode=node;
				var selectedDiv;
				if(selectNode.text=='自然信息'){
					//alert(selectNode.text);
					selectedDiv=$("#naturalInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='查封信息'){
					selectedDiv=$("#attachInfo").css('display','block').siblings('div').css('display','none');
					//getMortMess();
				}
			},
			onLoadSuccess:function(node,data){
				var defaultNode;
				//cur_reg_unit_code = data.attributes.code;				//初始化成功时给当前选择的reg_unit_code赋值
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

		registerPreview("");		
		
   };// init方法结束

   function registerPreview(cur_reg_unit_code){
	   var naturalInfo;					//自然信息数据
	   var attachInfo;				//查封信息数据
	   var applicants;					//申请人信息
	   var historyOwnershipInfo;		//历史信息
		$.ajax({
			url: ctx+'/common/register!getregisterInfo.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"proc_id":proc_id,"reg_unit_code":cur_reg_unit_code},
			success: function(data){
				
				//alert(JSON.stringify(data));
				naturalInfo = data.naturalInfo;
				
				
				setNaturalInfo(naturalInfo);
				attachInfo = data.userInfo;											//查封信息
				
				setattachInfo(attachInfo);											//添加查封信息
				if(data.userInfoHis)
				{
					ownershipInfoHis=data.userInfoHis;           //所有权信息
					setcanInfoHis(ownershipInfoHis);
				}
				
			},
			error:function(){
				 //  $('#HOUSE_ATTR_DICT').combo('destroy');
				   //$('#GET_MODE_DICT').combo('destroy');
			}
		});
		old_reg_unit_code = cur_reg_unit_code;					//获取数据后把当前的登记单元编号 赋值给上次历史信息
   }
   
   /**********************************************************************************
    *函数名称: setcanInfoHis()
    *功能说明: 填充解封登记信息
    *返 回 值: 
    *函数作者: Joyon
    *创建日期: 2014-03-01
    *修改历史: 
    ***********************************************************************************/   
    function setcanInfoHis(userInfoHis){
       $('#unattachInfoForm').form('load',userInfoHis);
       $("#REG_CODE1").val(userInfoHis.REG_CODE);
    }
   
   
   function setattachInfo(attachInfo)
   {
	   $('#attachInfoForm').form('load',attachInfo);
	   $('#attachlimit').val("从 "+attachInfo.START_DATE+" 到 "+attachInfo.END_DATE);
	   var historyOwnershipInfo = attachInfo.historyOwnershipInfo;
	   //setHolder(attachInfo.holder);
	   setHistoryOwnershipInfo(historyOwnershipInfo);
   }
   
   //填充自然信息
   function setNaturalInfo(naturalInfo){
	   //alert(JSON.stringify(naturalInfo));
	   $('#naturalInfoForm').form('load',naturalInfo);
	   $("#BUILD_AREA").val($("#BUILD_AREA").val());
	   
	   $("#TAONEI_AREA").val($("#TAONEI_AREA").val());
	   
	   $("#FT_COMMON_AREA").val($("#FT_COMMON_AREA").val());
	   $("#USE_PER").val($("#USE_PER").val()+" 年 "+naturalInfo.PA_START_DATE+" 到  "+naturalInfo.PA_END_DATE);
	   
   }

   
   /**********************************************************************************
	*函数名称: setHistoryOwnershipInfo
	*功能说明: 设置所有权历史信息
	*参数说明: 无
	*返 回 值: 无
	*函数作者: xuzz
	*创建日期: 2014-04-08
	*修改历史: 
	***********************************************************************************/
function setHistoryOwnershipInfo(data){
	  var dis_off;
	  var state;
	  var regDate;
	  var count;
	  for(var i=0;i<data.length;i++){
		  /*tempHolder =data[i].holder;
		  for(var j=0;j < tempHolder.length; j++){
			  if(j==0){
				  holder = tempHolder[j].HOL_NAME;
			  }else{
				  holder = holder+"、"+tempHolder[j].HOL_NAME;
			  }
		  }*/
		  regDate = data[i].regDate;
		  if(!regDate){
			  regDate="";
		  }
		  
		  count= i+1;
		  //$('#REG_CODE').val(data[i].regId); 
		  $("#table_attachInfo").append("<tr><td>"+count+"<input type='hidden' value='"+data[i].regId+"'/></td><td><label>"+data[i].busName+
				  "</label></td><td><label>"+data[i].dis_off+"</label></td><td><label>"+regDate+"</label></td><td><label>"+data[i].state+"</label></td></tr>");
	  }
	  
	  //信息设置成功后  初妈化该表的事件  
	  initHistoryOwnershipTable();
}
/**********************************************************************************
*函数名称: initHistoryOwnershipTable
*功能说明: 初始化所有权历史表   加上点击事件
*参数说明: 无
*返 回 值: 无
*函数作者: xuzz
*创建日期: 2014-04-08
*修改历史: 
***********************************************************************************/
function initHistoryOwnershipTable(){
 var trs = document.getElementById('table_attachInfo').getElementsByTagName('tr');  

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
 	  reloadMortInfoByRegId(regId);								//重新加载所有权相关信息    --自然信息未重新加载
    }  
    else{  
       trs[o].style.backgroundColor = '';  
    }  
   }  
 } 
 /**********************************************************************************
  *函数名称: reloadMortInfoByRegId()
  *功能说明: 重新加载抵押权信息  通过登记编号 从行中的input中获取登记编号
  *返 回 值: 
  *参   数:regId 登记编号
  *函数作者: Joyon
  *创建日期: 2014-03-01
  *修改历史: 
  ***********************************************************************************/  
  function reloadMortInfoByRegId(regId){
  	 $.ajax({
  			url: ctx+'/common/register!getMorPre.action?time='+new Date(),
  		dataType: 'json',
  		contentType:"application/x-www-form-urlencoded; charset=GBK",
  		data:{"reg_code":regId,"reg_unit_code":cur_reg_unit_code},
  		success: function(data){
  			//alert(JSON.stringify(data));
  			$('#mortInfoForm').form('load',data);							//重新加载所有权信息
  			holder = data.holder;
  			setHolder(holder,data.rightCertificateNo);					//设置申请人信息  传进房地产证号
  			}
  	 });
  }
   
