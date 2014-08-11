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
   var selectNode;
  // var proc=this.parent.proc;
   //流程实例ID
   var proc_id;
   var cur_reg_unit_code;					//当前查看的登记单元的编号
   var old_reg_unit_code;					//上次登记单元编号    用来判断是否需要从后台取数据
/**********************************************************************************
	*函数名称: init
	*功能说明: 页面初始化
	*参数说明: 无
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/   
  
function init(proc){
	   
	   $(":input").attr("readonly", "readonly");
	  proc_id =proc.procId// 1000017793;
	  
	   $('body').layout({
			fit:true
		});		
		
		$('#registerbook_tree').tree({  
			url:ctx+"/common/register!getTreeForMortPre.action?proc_id="+proc_id,
			onClick: function(node){
				cur_reg_unit_code = node.attributes.code;							//点击一行时时给当前选择的reg_unit_code赋值
				//如果当前登记单元编号不等于上次登记单元编号    表示  切换成另一登记单元     更新当前页面数据      ----其它则直接用当前数据
				if(old_reg_unit_code!=cur_reg_unit_code){
					registerPreview(cur_reg_unit_code);
				}
				
				selectNode=node;
				//alert(JSON.stringify(selectNode));
				var selectedDiv;
				if(selectNode.text=='抵押权信息'){
					selectedDiv=$("#mortInfo").css('display','block').siblings('div').css('display','none');
					//getMortMess();
				}else{
					selectedDiv=$("#naturalInfo").css('display','block').siblings('div').css('display','none');
				}
			},
			onLoadSuccess:function(node,data){
				var defaultNode;
				data=data[0];
				defaultNode = data;
				cur_reg_unit_code = data.attributes.code;				//初始化成功时给当前选择的reg_unit_code赋值
				
//				for(var i=0;i<data.length;i++){
//					if(data[i].text=='自然信息');{
//						cur_reg_unit_code = data[i].attributes.code;				//初始化成功时给当前选择的reg_unit_code赋值
//						defaultNode=data[i];
//						break;
//					}
//				}
				
				var node = $('#registerbook_tree').tree('find', defaultNode.id);
				$('#registerbook_tree').tree('select', node.target);
				
				$('#naturalInfo').css('display','block');
				
				registerPreview(cur_reg_unit_code);
				
			}
		});

			
		
   };// init方法结束
	/**********************************************************************************
	*函数名称: registerPreview
	*功能说明: 
	*参数说明: 无
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/
   function registerPreview(cur_reg_unit_code){
	   var naturalInfo;					//自然信息数据
	   var ownershipInfo;				//所有权信息数据
	   var applicants;					//申请人信息
	   var historyOwnershipInfo;		//所有权历史信息
		$.ajax({
			url: ctx+'/common/register!getMorPre.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"proc_id":proc_id,"reg_unit_code":cur_reg_unit_code},
			success: function(data){
				//alert(JSON.stringify(data));
				naturalInfo = data.naturalInfo;
				setNaturalInfo(naturalInfo);	
				
				$("#cer_no").val(data.naturalInfo.CER_NO  );
				
				setMortInfo(data.userInfo);
				
			},
			error:function(){
				 //  $('#HOUSE_ATTR_DICT').combo('destroy');
				   //$('#GET_MODE_DICT').combo('destroy');
			}
			
		});
		old_reg_unit_code = cur_reg_unit_code;					//获取数据后把当前的登记单元编号 赋值给上次历史信息
   }
	/**********************************************************************************
	*函数名称: setNaturalInfo
	*功能说明: 填充自然信息
	*参数说明: 无
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
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
   /**********************************************************************************
	*函数名称: setMortInfo
	*功能说明: 填充抵押权部分信息
	*参数说明: 无
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/
   function setMortInfo(mort){
	   //alert(JSON.stringify(mort));
	   $('#mortInfoForm').form('load',mort);
	   setHolder(mort.holder);
	   
	   setHistoryInfo(mort.hisRegInfo);
	   
	   if($("#sure_amount").val()=='0')
				$("#sure_amount").val("");
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
	  if(!holder){
		  return;
	  }
	  var holder_rel = {
			  MORTGAGER :"063003",					//抵押人
			  MORTGAGEE :"063004",					//抵押权人
			  MORTGAGE_TRANSFEROREE:"063006"		//抵押权受让方
	  };
	  var mortgagerNameStr="";						//抵押人名字字符串
	  var mortgageeNameStr="";						//抵押权人名字字符串
	  
	  for(var i =0;i<holder.length;i++){
		  //如果是抵押人 则给抵押人赋值
		  if(holder[i].HOL_REL == holder_rel.MORTGAGER){
			  if(mortgagerNameStr.length==0){
				  mortgagerNameStr+=holder[i].HOL_NAME;
			  }else{
				  mortgagerNameStr+=","+holder[i].HOL_NAME;
			  }
		  }else if(holder[i].HOL_REL == holder_rel.MORTGAGEE || holder[i].HOL_REL == holder_rel.MORTGAGE_TRANSFEROREE){
			  if(mortgageeNameStr.length==0){
				  mortgageeNameStr+=holder[i].HOL_NAME;
			  }else{
				  mortgageeNameStr+=","+holder[i].HOL_NAME;
			  }
		  }
	  }
	  $('#mortgager').val(mortgagerNameStr);
	  $('#mortgagee').val(mortgageeNameStr);
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
  function setHistoryInfo(historyInfo){
	  $("#table_mortInfo").empty();
	  $("#table_mortInfo").append("<tr><td>序号</td><td>登记类型</td><td>债务人</td><td>登记日期</td></tr>");
	  
	  var data = historyInfo;
	  var holder;
	  var borrower;									//债权人
	  var regDate;
	  var count;
	  for(var i=0;i<data.length;i++){
//		  tempHolder =data[i].holder;
//		  for(var j=0;j < tempHolder.length; j++){
//			  if(j==0){
//				  holder = tempHolder[j].HOL_NAME;
//			  }else{
//				  holder = holder+"、"+tempHolder[j].HOL_NAME; 
//			  }
//		  }
		  borrower = data[i].borrower;
		  regDate = data[i].regDate;
		  if(!regDate){
			  regDate="";
		  }
		  count= i+1;
		  $("#table_mortInfo").append("<tr><td>"+count+"<input type='hidden' value='"+data[i].regId+"'/></td><td><label>"+data[i].busName+
			  "</label></td><td><label>"+borrower+"</label></td><td><label>"+regDate+"</label></td></tr>");
	  }
	 // alert(JSON.stringify(historyInfo));
	  initHistoryOwnershipTable();
  }
   /**********************************************************************************
	*函数名称: getMortMess
	*功能说明: 获取抵押过程信息
	*参数说明: 无
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/
   function getMortMess(){
	   
	   $.ajax({
			//url: ctx+'/common/register!getMortMess.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"proc_id":proc_id},
			success: function(data){
				$('#mortInfoForm').form('load',data);
				 $('#procdef_id').val($('#procdef_id1').combo('getText'));
				   $('#procdef_id1').combo('destroy');				   
				   $('#mort_type').val($('#mort_type1').combo('getText'));			 
				   $('#mort_type1').combo('destroy');
				
				
			},
			error:function(){
				  $('#procdef_id').combo('destroy');
				   $('#mort_type').combo('destroy');
			}
			
		});
	   
	   
	   
	   
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
   	  var trs = document.getElementById('table_mortInfo').getElementsByTagName('tr');  
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
	   $('#mortInfoForm').form('reset');	
	  // $("#REG_CODE").val(regId);
	   
   	 $.ajax({
   			url: ctx+'/common/register!getMorPre.action?time='+new Date(),
   		dataType: 'json',
   		contentType:"application/x-www-form-urlencoded; charset=GBK",
   		data:{"reg_code":regId,"reg_unit_code":cur_reg_unit_code},
   		success: function(data){
   			ownershipInfo = data.userInfo;	
   			$('#mortInfoForm').form('load',ownershipInfo);							//重新加载所有权信息
   			
   			var holder = ownershipInfo.holder;
   			setHolder(holder,data.rightCertificateNo);					//设置申请人信息  传进房地产证号
   			if($("#sure_amount").val()=='0')
   				$("#sure_amount").val("");
   		}
   	 });
   }
 
  
   
