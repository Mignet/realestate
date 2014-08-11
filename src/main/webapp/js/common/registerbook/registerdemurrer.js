/**
 * 登记簿预览js
 */

   //打开的父窗体
   var selectNode;
   var proc=this.parent.proc;
   //流程实例ID
   var proc_id;
   
   /**********************************************************************************
	*函数名称: init
	*功能说明: 初始化页面
	*参数说明: 无
	*返 回 值: 无
	*函数作者: xuzz
	*创建日期: 2014-04-03
	*修改历史: 
	***********************************************************************************/
   function init(proc){
	   
	   $(":input").attr("readonly", "readonly");
	  proc_id = proc.procId;
	  //alert(JSON.stringify(proc));
	   $('body').layout({
			fit:true
		});		
		
		$('#registerbook_tree').tree({  
			url:ctx+"/json/demurrer_data.json",
			onClick: function(node){
				
				selectNode=node;
				var selectedDiv;
				if(selectNode.text=='自然信息'){
					//alert(selectNode.text);
					selectedDiv=$("#naturalInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='异议信息'){
					selectedDiv=$("#demurrerInfo").css('display','block').siblings('div').css('display','none');
					//getMortMess();
				}
			},
			onLoadSuccess:function(node,data){
				var defaultNode;
				
				for(var i=0;i<data.length;i++){
					if(data[i].text=='自然信息'){
						defaultNode=data[i];
						break;
					}
				}
				
				var node = $('#registerbook_tree').tree('find', defaultNode.id);
				$('#registerbook_tree').tree('select', node.target);
				
				$('#naturalInfo').css('display','block');
			}
		});

		registerPreview();		
		
   };// init方法结束
   /**********************************************************************************
	*函数名称: registerPreview
	*功能说明: 获取登记簿预览信息
	*参数说明: 无
	*返 回 值: 无
	*函数作者: xuzz
	*创建日期: 2014-04-03
	*修改历史: 
	***********************************************************************************/
   function registerPreview(){
	   var naturalInfo;					//自然信息数据
	   var demurrerInfo;				//异议信息数据
	   var applicants;					//申请人信息
	   var historyOwnershipInfo;		//历史信息
		$.ajax({
			url: ctx+'/common/register!getregisterInfo.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"proc_id":proc_id},
			success: function(data){
				
				alert(JSON.stringify(data));
				naturalInfo = data.naturalInfo;
				
				
				setNaturalInfo(naturalInfo);
				demurrerInfo = data.userInfo;											//异议信息
				setdemurrerInfo(demurrerInfo);											//添加异议信息
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
   }
   
   /**********************************************************************************
   *函数名称: setcanInfoHis()
   *功能说明: 填充注销异议信息
   *返 回 值: 
   *函数作者: Joyon
   *创建日期: 2014-03-01
   *修改历史: 
   ***********************************************************************************/   
   function setcanInfoHis(userInfoHis){
      $("#CAN_REG_CODE").val(userInfoHis.REG_CODE);
      //$("#CAN_DISS_DATE").val(userInfoHis.DISS_DATE);   
      $("#CAN_REG_DATE").val(userInfoHis.REG_DATE);
      $("#CAN_RECORDER").val(userInfoHis.RECORDER);
   }
   
   /**********************************************************************************
	*函数名称: setcandemurrerInfo
	*功能说明: 注销异议信息数据展示
	*参数说明: 无
	*返 回 值: 无
	*函数作者: xuzz
	*创建日期: 2014-04-03
	*修改历史: 
	***********************************************************************************/
  function setcandemurrerInfo(demurrerInfo)
  {
	   $('#demurrerInfoForm').form('load',demurrerInfo);
	   $('#demurrerlimit').val("从 "+demurrerInfo.START_DATE+" 到 "+demurrerInfo.END_DATE);
  }
   /**********************************************************************************
	*函数名称: setdemurrerInfo
	*功能说明: 异议信息数据展示
	*参数说明: 无
	*返 回 值: 无
	*函数作者: xuzz
	*创建日期: 2014-04-03
	*修改历史: 
	***********************************************************************************/
   function setdemurrerInfo(demurrerInfo)
   {
	   $('#demurrerInfoForm').form('load',demurrerInfo);
	   
	   var historyOwnershipInfo = demurrerInfo.historyOwnershipInfo;
	   setHolder(demurrerInfo.holder);
	   setHistoryOwnershipInfo(historyOwnershipInfo);
   }
   
   /**********************************************************************************
	*函数名称: setHolder
	*功能说明: 填充权利人信息
	*参数说明: 无
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/
 function setHolder(holder){
	  var applyname="";						//申请人
	  var applynamenum="";						//申请人身份证明号
	  
	  for(var i =0;i<holder.length;i++){
			  if(applyname.length==0){
				  applyname+=holder[i].HOL_NAME;
				  applynamenum+=holder[i].HOL_CER_NO;
			  }else{
				  applyname+=","+holder[i].HOL_NAME;
				  applynamenum+=","+holder[i].HOL_CER_NO;
			  }
	  }
	  $('#HOL_NAME').val(applyname);
	  $('#HOL_CER_NO').val(applynamenum);
 }
  
 /**********************************************************************************
	*函数名称: setHistoryOwnershipInfo
	*功能说明: 设置所有权历史信息
	*参数说明: 无
	*返 回 值: 无
	*函数作者: xuzz
	*创建日期: 2014-04-03
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
		  //$('#REG_CODE').val(data[i].regId); 
		  $("#table_demurrerInfo").append("<tr><td>"+count+"<input type='hidden' value='"+data[i].regId+"'/></td><td><label>"+data[i].busName+
				  "</label></td><td><label>"+holder+"</label></td><td><label>"+regDate+"</label></td></tr>");
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
	*创建日期: 2014-04-03
	*修改历史: 
	***********************************************************************************/
 function initHistoryOwnershipTable(){
	 var trs = document.getElementById('table_demurrerInfo').getElementsByTagName('tr');  

	  for( var i=0; i<trs.length; i++ ){  
		   trs[i].onmousedown = function(){  
			   trOnmouseDown(trs,this);  
		   }  
	  }  
 } 
   /**********************************************************************************
	*函数名称: setNaturalInfo
	*功能说明: 填充自然信息
	*参数说明: 无
	*返 回 值: 无
	*函数作者: xuzz
	*创建日期: 2014-04-03
	*修改历史: 
	***********************************************************************************/
   function setNaturalInfo(naturalInfo){
	   //alert(JSON.stringify(naturalInfo));
	   $('#naturalInfoForm').form('load',naturalInfo);
	   $("#BUILD_AREA").val($("#BUILD_AREA").val());
	   
	   $("#TAONEI_AREA").val($("#TAONEI_AREA").val());
	   
	   $("#FT_COMMON_AREA").val($("#FT_COMMON_AREA").val());
	   $("#USE_PER").val($("#USE_PER").val()+" 年 "+naturalInfo.PA_START_DATE+" 到  "+naturalInfo.PA_END_DATE);
	   
   }
 
  
   
