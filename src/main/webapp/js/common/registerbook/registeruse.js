/**
 * 登记簿预览js
 */

    //打开的父窗体
	var openerWindow;
   //登簿人  登记预览时需要
   //登记编号      登记预览时需要
   
   var selectNode;
   var proc=this.parent.proc;
   //流程
   var proc;
   //流程实例ID
   var proc_id;
   //流程实例ID  
   
   //window.onload=init;
   
   function init(proc){
	   //alert("openerWindow："+JSON.stringify(openerWindow.proc));
	   //proc=openerWindow.proc;
	   //proc= openerWinssssdow.proc;
//	   this.proc = proc;
	   proc_id = proc.procId;
	   //proc_id = 1000016479;
	  
	   $('body').layout({
			fit:true
		});		
		
		$('#registerbook_tree').tree({  
			url:ctx+"/json/use_registerbook_data.json",
			onClick: function(node){
				//alert(node.text);  // alert node text property when clicked
				selectNode=node;
				var selectedDiv;
				if(selectNode.text=='自然信息'){
					//alert(selectNode.text);
					selectedDiv=$("#naturalInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='使用权信息'){
					selectedDiv=$("#userInfo").css('display','block').siblings('div').css('display','none');
					
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
		registerPreview();
			
		
   };
   // init method end
   
   function registerPreview(){
	   //alert(proc_id);
	   var naturalInfo;					//自然信息数据
	   var userInfo;				//所有权信息数据
	   var applicants;					//申请人信息
	   var historyOwnershipInfo;		//所有权历史信息
	   var ownershipInfoHis;			//获取要注销的所有权信息
		$.ajax({
			url: ctx+'/common/register!getregisterInfo.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"proc_id":proc_id},
			success: function(data){
				//alert(JSON.stringify(data.userInfo));
				naturalInfo = data.naturalInfo; //自然信息
				setNaturalInfo(naturalInfo);  //填充自然信息 
				userInfo = data.userInfo;											//所有权信息
				setuserInfo(userInfo);											//填充所有权信息
				if(data.userInfoHis)
				{
					ownershipInfoHis=data.userInfoHis;           //所有权信息
					setcanInfoHis(ownershipInfoHis);
				}
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
      $("#can_recorder").val(userInfoHis.REGCORDER);
   }
   
   //填充自然信息
   function setNaturalInfo(naturalInfo){
	   //alert(JSON.stringify(naturalInfo));
	   $('#naturalInfoForm').form('load',naturalInfo);
	   $("#BUILD_AREA").val($("#BUILD_AREA").val()+" ㎡");
	   
	   $("#TAONEI_AREA").val($("#TAONEI_AREA").val()+" ㎡");
	   
	   $("#FT_COMMON_AREA").val($("#FT_COMMON_AREA").val()+" ㎡");
	   $("#USE_PER").val($("#USE_PER").val()+" 年 "+naturalInfo.PA_START_DATE+" 到  "+naturalInfo.PA_END_DATE);
	   
   }
 	//填充申请人的信息
   function setHoldersInfo(holder,rightCertificateNo)
   {
	   var data = holder;
	   if(holder.length<1){
			return;
		}
		$("#table_holder").empty();
		var cerNo="";
		if(rightCertificateNo){
			if(rightCertificateNo.length>0){
				cerNo =rightCertificateNo[0].CER_NO;
			}	
		}
		var holder;
		var regDate;
		var count;
		  $("#table_holder").append("<tr><td>序号</td><td>权利人</td><td>证件种类</td><td>身份证明号</td><td>单位性质</td><td>通讯地址</td><td>房地产证号</td></tr>");
		  //alert(JSON.stringify(data[0]));
		  for(var i=0;i<data.length;i++){
			  count= i+1;
			  $("#table_holder").append("<tr><td>"+count+"</td>"+
					  "<td><label>"+data[i].HOL_NAME+"</label></td>"+
					  "<td><label>"+data[i].HOL_CER_TYPE+"</label></td>"+
					  "<td><label>"+data[i].HOL_CER_NO+"</label></td>"+
					  "<td><label>"+data[i].DEPART_TYPE+"</label></td>"+
					  "<td><label>"+data[i].HOL_ADDRESS+"</label></td>"+
					  "<td><label>"+cerNo+"</label></td>"+
					  "</tr>"	  
			  );
		  }  
   }
   
   //填充所有权人信息
   function setuserInfo(data){
	  // alert(JSON.stringify(data));
	   $('#userInfoForm').form('load',data);
	   holder = data.holder;
		setHoldersInfo(holder,data.rightCertificateNo);					//设置申请人信息  传进房地产证号
		
		historyOwnershipInfo = data.historyOwnershipInfo;
		
		setHistoryOwnershipInfo(historyOwnershipInfo);								//设置历史所有权信息表
   }
   
   
   
   //设置所有权历史信息
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
		  $("#table_hisuseInfo").append("<tr><td>"+count+"<input type='hidden' value='"+data[i].regId+"'/></td><td><label>"+data[i].busName+
				  "</label></td><td><label>"+holder+"</label></td><td><label>"+regDate+"</label></td></tr>");
	  }
	  
	  //信息设置成功后  初妈化该表的事件  
	  initHistoryOwnershipTable();
  }
   
 //初始化所有权历史表   加上点击事件
 function initHistoryOwnershipTable(){
	 var trs = document.getElementById('table_hisuseInfo').getElementsByTagName('tr');  

	  for( var i=0; i<trs.length; i++ ){  
		   trs[i].onmousedown = function(){  
			   trOnmouseDown(trs,this);  
		   }  
	  }  
 } 
 
 //
 function trOnmouseDown(trs,obj){  
  for( var o=0; o<trs.length; o++ ){  
   if( trs[o] == obj ){  
    trs[o].style.backgroundColor = '#DFEBF2';  
    
	  var regId = trs[o].getElementsByTagName('input')[0].value;		//从行中的input中获取登记编号
	  
	  reloadOwnershipInfoByRegId(regId);
   }  
   else{  
    trs[o].style.backgroundColor = '';  
   }  
  }  
 } 
 
 
 //重新加载所有权信息  通过登记编号
 function reloadOwnershipInfoByRegId(regId){
	 $.ajax({
			url: ctx+'/common/register!getOwnershipInfo.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"reg_code":regId},
			success: function(data){				
				$('#userInfoForm').form('load',data);							//重新加载所有权信息				
				holder = data.holder;
				setHoldersInfo(holder,data.rightCertificateNo);					//设置申请人信息  传进房地产证号
					
				
									
			}
	 });
 }
   
   
