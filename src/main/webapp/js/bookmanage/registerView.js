/*********************************************************************************
*��  ��  ��  ��:  registerView.js
*��  ��  ��  ��:  �Ǽǲ�Ԥ��
*Copyright (c):  ���ڵ�����������������޹�˾
*��    ��    ��:  sam
*��  ��  ��  �ڣ� 2014-03-17
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/ 
//�򿪵ĸ�����
	var openerWindow;
   //�ǲ���  �Ǽ�Ԥ��ʱ��Ҫ
   //�ǼǱ��      �Ǽ�Ԥ��ʱ��Ҫ
   
   var selectNode;
   //var proc=this.parent.proc;
   //����
   var proc;
/*   var PARCEL_CODE = '009003';
   var HOUSE_CODE = '009001';*/
   //window.onload=init;
   //��չJQueryһ��getUrlParam�õ�url����ֵ
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
   *��������: init()
   *����˵��: ��ʼ������  ��������� ���صǼǲ�Ԥ���õ�����
   *�� �� ֵ: 
   *��������: sam
   *��������: 2014-03-01
   *�޸���ʷ: 
   ***********************************************************************************/  
   var jsonurl; 
   function init(proc){
	   //alert("openerWindow��"+JSON.stringify(openerWindow.proc));
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
	   //�Ĵ�����Կ��ƵǼǲ�����ʾ��
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
				if(selectNode.text=='��Ȼ��Ϣ'){
					if(i==1){
						registerGetNaturalData(proc);
						i++}
					selectedDiv=$("#naturalInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='��ѺȨ��Ϣ'){
					if(n==1){
						registerGetMortData(proc);
						n++}
					selectedDiv=$("#mortInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='����Ȩ��Ϣ'){
					if(m==1){
						registerGetUseHouseData(proc);
						m++}
					selectedDiv=$("#ownershipInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='����Ȩ��Ϣ'){
					if(j==1){
						registerGetEasementData(proc);
						j++}
					selectedDiv=$("#easementInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='Ԥ��Ǽ���Ϣ'){
					if(l==1){
						registerGetPreadviceData(proc);
						l++}
					selectedDiv=$("#preadviceInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='����Ǽ���Ϣ'){
					if(k==1){
						registerGetDissentData(proc);
						k++}
					selectedDiv=$("#dissentInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='���Ǽ���Ϣ'){
				     if(y==1){
							registerGetAttachData(proc);	
					   y++}
					selectedDiv=$("#attachInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='ʹ��Ȩ��Ϣ'){
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
							if(lsdata.text=='���Ǽ���Ϣ'){
								defaultNode=lsdata;
								y++;
								break;
							}
						}else if(realestate_type == enumdata.PREADVICEBI){
							if(lsdata.text=='Ԥ��Ǽ���Ϣ'){
								defaultNode=lsdata;
								l++;
								break;
							}
						}else if(realestate_type == enumdata.DISSENTBI){
							if(lsdata.text=='����Ǽ���Ϣ'){
								defaultNode=lsdata;
								k++;
								break;
							}
						}else if(realestate_type == enumdata.MORTGAGEBI){
							if(lsdata.text=='��ѺȨ��Ϣ'){
								defaultNode=lsdata;
								n++;
								break;
							}
						}else if(realestate_type == enumdata.OWNERSHIPBI){
							if(lsdata.text=='����Ȩ��Ϣ'){
								defaultNode=lsdata;
								m++;
								break;
							}
						}else if(realestate_type == enumdata.EASEMENTBI){
							if(lsdata.text=='����Ȩ��Ϣ'){
								defaultNode=lsdata;
								j++;
								break;
							}
						}else if(realestate_type == enumdata.USERIGHTBI){
							if(lsdata.text=='ʹ��Ȩ��Ϣ'){
								defaultNode=lsdata;
								o++;
								break;
							}
						}
					}else
						if(lsdata.text=='��Ȼ��Ϣ'){
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
