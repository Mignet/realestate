/*********************************************************************************
*文  件  名  称:  registerView.js
*功  能  描  述:  登记簿预览
*Copyright (c):  深圳道行天下软件技术有限公司
*创    建    人:  sam
*创  建  日  期： 2014-03-17
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/ 
//打开的父窗体
	var openerWindow;
   //登簿人  登记预览时需要
   //登记编号      登记预览时需要
   
   var selectNode;
   //var proc=this.parent.proc;
   //流程
   var proc;
/*   var PARCEL_CODE = '009003';
   var HOUSE_CODE = '009001';*/
   //window.onload=init;
   //扩展JQuery一个getUrlParam得到url参数值
   $(function($){
	   $.getUrlParam = function(name)
	   {
		   var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		   var r = window.location.search.substr(1).match(reg);
		   if (r!=null) 
			   return unescape(r[2]); 
		      return null;
	   }
	 });
   /**********************************************************************************
   *函数名称: init()
   *功能说明: 初始化方法  加载左侧树 加载登记簿预览用的数据
   *返 回 值: 
   *函数作者: sam
   *创建日期: 2014-03-01
   *修改历史: 
   ***********************************************************************************/  
   var jsonurl; 
   function init(proc){
	   //alert("openerWindow："+JSON.stringify(openerWindow.proc));
	   //proc=openerWindow.proc;
	   //proc= openerWinssssdow.proc;
	   this.proc = proc;
	   //$.getUrlParam('reg_unit_type');
	   var i=1,n=1,m=1,j=1,l=1,k=1,y=1,o=1;
	   jsonurl = ctx+"/json/registerbook_dataII.json";
	   if(reg_unit_type == enumdata.parcel){
		   jsonurl = ctx+"/json/registerbook_dataI.json";
	   }
	   //$.getUrlParam('realestate_type');
//	   alert(reg_unit_type);
//	   alert(realestate_type);
	   //改代码可以控制登记簿内显示项
	  /* if(realestate_type){
		   if(realestate_type == enumdata.ATTACHBI){
			   jsonurl = ctx+"/json/realestateinfo_data"+enumdata.ATTACHBI+".json";
		   }else if(realestate_type == enumdata.PREADVICEBI){
			   jsonurl = ctx+"/json/realestateinfo_data"+enumdata.PREADVICEBI+".json";
		   }else if(realestate_type == enumdata.DISSENTBI){
			   jsonurl = ctx+"/json/realestateinfo_data"+enumdata.DISSENTBI+".json";
		   }else if(realestate_type == enumdata.MORTGAGEBI){
			   jsonurl = ctx+"/json/realestateinfo_data"+enumdata.MORTGAGEBI+".json";
		   }else if(realestate_type == enumdata.OWNERSHIPBI){
			   jsonurl = ctx+"/json/realestateinfo_data"+enumdata.OWNERSHIPBI+".json";
		   }else if(realestate_type == enumdata.EASEMENTBI){
			   jsonurl = ctx+"/json/realestateinfo_data"+enumdata.EASEMENTBI+".json"; 
		   }else if(realestate_type == enumdata.USERIGHTBI){
			   jsonurl = ctx+"/json/realestateinfo_data"+enumdata.USERIGHTBI+".json"; 
		   }
	   }*/
       //proc_id = 1;
	   $('body').layout({
			fit:true
		});		
		$('#registerbook_tree').tree({  
			url:jsonurl,
			onClick: function(node){
				//alert(node.text);  // alert node text property when clicked
				selectNode=node;
				var selectedDiv;
				if(selectNode.text=='自然信息'){
					if(i==1){
						registerGetNaturalData(proc);
						i++}
					selectedDiv=$("#naturalInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='抵押权信息'){
					if(n==1){
						registerGetMortData(proc);
						n++}
					selectedDiv=$("#mortInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='所有权信息'){
					if(m==1){
						registerGetUseHouseData(proc);
						m++}
					selectedDiv=$("#ownershipInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='地役权信息'){
					if(j==1){
						registerGetEasementData(proc);
						j++}
					selectedDiv=$("#easementInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='预告登记信息'){
					if(l==1){
						registerGetPreadviceData(proc);
						l++}
					selectedDiv=$("#preadviceInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='异议登记信息'){
					if(k==1){
						registerGetDissentData(proc);
						k++}
					selectedDiv=$("#dissentInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='查封登记信息'){
				     if(y==1){
							registerGetAttachData(proc);	
					   y++}
					selectedDiv=$("#attachInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='使用权信息'){
					if(o==1){
						registerGetUserLandData(proc);
						o++}
					selectedDiv=$("#userInfo").css('display','block').siblings('div').css('display','none');
				}
			},
			onLoadSuccess:function(node,data){
				var defaultNode;
				for(var i=0;i<data.length;i++){
					var lsdata = data[i];
					if(realestate_type){
						if(realestate_type == enumdata.ATTACHBI){
							if(lsdata.text=='查封登记信息'){
								defaultNode=lsdata;
								y++;
								break;
							}
						}else if(realestate_type == enumdata.PREADVICEBI){
							if(lsdata.text=='预告登记信息'){
								defaultNode=lsdata;
								l++;
								break;
							}
						}else if(realestate_type == enumdata.DISSENTBI){
							if(lsdata.text=='异议登记信息'){
								defaultNode=lsdata;
								k++;
								break;
							}
						}else if(realestate_type == enumdata.MORTGAGEBI){
							if(lsdata.text=='抵押权信息'){
								defaultNode=lsdata;
								n++;
								break;
							}
						}else if(realestate_type == enumdata.OWNERSHIPBI){
							if(lsdata.text=='所有权信息'){
								defaultNode=lsdata;
								m++;
								break;
							}
						}else if(realestate_type == enumdata.EASEMENTBI){
							if(lsdata.text=='地役权信息'){
								defaultNode=lsdata;
								j++;
								break;
							}
						}else if(realestate_type == enumdata.USERIGHTBI){
							if(lsdata.text=='使用权信息'){
								defaultNode=lsdata;
								o++;
								break;
							}
						}
					}else
						if(lsdata.text=='自然信息'){
							defaultNode=lsdata;
							i++;
							break;
						}
				}
				var node = $('#registerbook_tree').tree('find', defaultNode.id);
				$('#registerbook_tree').tree('select', node.target);
				 if(realestate_type){
					   if(realestate_type == enumdata.ATTACHBI){
						   registerGetAttachData(proc);	
						   $('#attachInfo').css('display','block');
					   }else if(realestate_type == enumdata.PREADVICEBI){
						   registerGetPreadviceData(proc);
						   $('#preadviceInfo').css('display','block');
					   }else if(realestate_type == enumdata.DISSENTBI){
						   registerGetDissentData(proc);
						   $('#dissentInfo').css('display','block');
					   }else if(realestate_type == enumdata.MORTGAGEBI){
						   registerGetMortData(proc);
						   $('#mortInfo').css('display','block');
					   }else if(realestate_type == enumdata.OWNERSHIPBI){
						   registerGetUseHouseData(proc);
						   $('#ownershipInfo').css('display','block');
					   }else if(realestate_type == enumdata.EASEMENTBI){
						   registerGetEasementData(proc);
						   $('#easementInfo').css('display','block');
					   }else if(realestate_type == enumdata.USERIGHTBI){
						   registerGetUserLandData(proc);
						   $('#userInfo').css('display','block');
					   }
				   }else{
					   registerGetNaturalData(proc);
					   $('#naturalInfo').css('display','block');
				   }
			}
		});
   };
   // init method end
