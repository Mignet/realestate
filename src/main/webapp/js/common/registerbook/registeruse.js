/**
 * �Ǽǲ�Ԥ��js
 */

    //�򿪵ĸ�����
	var openerWindow;
   //�ǲ���  �Ǽ�Ԥ��ʱ��Ҫ
   //�ǼǱ��      �Ǽ�Ԥ��ʱ��Ҫ
   
   var selectNode;
   var proc=this.parent.proc;
   //����
   var proc;
   //����ʵ��ID
   var proc_id;
   //����ʵ��ID  
   
   //window.onload=init;
   
   function init(proc){
	   //alert("openerWindow��"+JSON.stringify(openerWindow.proc));
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
				if(selectNode.text=='��Ȼ��Ϣ'){
					//alert(selectNode.text);
					selectedDiv=$("#naturalInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='ʹ��Ȩ��Ϣ'){
					selectedDiv=$("#userInfo").css('display','block').siblings('div').css('display','none');
					
				}
			},
			onLoadSuccess:function(node,data){
				
				var defaultNode;
				
				for(var i=0;i<data.length;i++){
					if(data[i].text=='��Ȼ��Ϣ');{
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
	   var naturalInfo;					//��Ȼ��Ϣ����
	   var userInfo;				//����Ȩ��Ϣ����
	   var applicants;					//��������Ϣ
	   var historyOwnershipInfo;		//����Ȩ��ʷ��Ϣ
	   var ownershipInfoHis;			//��ȡҪע��������Ȩ��Ϣ
		$.ajax({
			url: ctx+'/common/register!getregisterInfo.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"proc_id":proc_id},
			success: function(data){
				//alert(JSON.stringify(data.userInfo));
				naturalInfo = data.naturalInfo; //��Ȼ��Ϣ
				setNaturalInfo(naturalInfo);  //�����Ȼ��Ϣ 
				userInfo = data.userInfo;											//����Ȩ��Ϣ
				setuserInfo(userInfo);											//�������Ȩ��Ϣ
				if(data.userInfoHis)
				{
					ownershipInfoHis=data.userInfoHis;           //����Ȩ��Ϣ
					setcanInfoHis(ownershipInfoHis);
				}
			}
			
		});
   } 
   
   /**********************************************************************************
   *��������: setcanInfoHis()
   *����˵��: ��䷿�ز�֤ע����Ϣ
   *�� �� ֵ: 
   *��������: Joyon
   *��������: 2014-03-01
   *�޸���ʷ: 
   ***********************************************************************************/   
   function setcanInfoHis(userInfoHis){
      $("#can_regcode").val(userInfoHis.REG_CODE);
      $("#can_reason").val(userInfoHis.REASON);
      $("#can_regDate").val(userInfoHis.REG_DATE);
      $("#can_recorder").val(userInfoHis.REGCORDER);
   }
   
   //�����Ȼ��Ϣ
   function setNaturalInfo(naturalInfo){
	   //alert(JSON.stringify(naturalInfo));
	   $('#naturalInfoForm').form('load',naturalInfo);
	   $("#BUILD_AREA").val($("#BUILD_AREA").val()+" �O");
	   
	   $("#TAONEI_AREA").val($("#TAONEI_AREA").val()+" �O");
	   
	   $("#FT_COMMON_AREA").val($("#FT_COMMON_AREA").val()+" �O");
	   $("#USE_PER").val($("#USE_PER").val()+" �� "+naturalInfo.PA_START_DATE+" ��  "+naturalInfo.PA_END_DATE);
	   
   }
 	//��������˵���Ϣ
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
		  $("#table_holder").append("<tr><td>���</td><td>Ȩ����</td><td>֤������</td><td>���֤����</td><td>��λ����</td><td>ͨѶ��ַ</td><td>���ز�֤��</td></tr>");
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
   
   //�������Ȩ����Ϣ
   function setuserInfo(data){
	  // alert(JSON.stringify(data));
	   $('#userInfoForm').form('load',data);
	   holder = data.holder;
		setHoldersInfo(holder,data.rightCertificateNo);					//������������Ϣ  �������ز�֤��
		
		historyOwnershipInfo = data.historyOwnershipInfo;
		
		setHistoryOwnershipInfo(historyOwnershipInfo);								//������ʷ����Ȩ��Ϣ��
   }
   
   
   
   //��������Ȩ��ʷ��Ϣ
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
				  holder = holder+"��"+tempHolder[j].HOL_NAME;
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
	  
	  //��Ϣ���óɹ���  ���軯�ñ���¼�  
	  initHistoryOwnershipTable();
  }
   
 //��ʼ������Ȩ��ʷ��   ���ϵ���¼�
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
    
	  var regId = trs[o].getElementsByTagName('input')[0].value;		//�����е�input�л�ȡ�ǼǱ��
	  
	  reloadOwnershipInfoByRegId(regId);
   }  
   else{  
    trs[o].style.backgroundColor = '';  
   }  
  }  
 } 
 
 
 //���¼�������Ȩ��Ϣ  ͨ���ǼǱ��
 function reloadOwnershipInfoByRegId(regId){
	 $.ajax({
			url: ctx+'/common/register!getOwnershipInfo.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"reg_code":regId},
			success: function(data){				
				$('#userInfoForm').form('load',data);							//���¼�������Ȩ��Ϣ				
				holder = data.holder;
				setHoldersInfo(holder,data.rightCertificateNo);					//������������Ϣ  �������ز�֤��
					
				
									
			}
	 });
 }
   
   
