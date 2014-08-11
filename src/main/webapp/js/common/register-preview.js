/*********************************************************************************
*��  ��  ��  ��: register-preview.js
*��  ��  ��  ��: �Ǽǲ�Ԥ��
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Joyon
*��  ��  ��  �ڣ� 2014-03-01
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
//�򿪵ĸ�����
var openerWindow;
//�ǲ���  �Ǽ�Ԥ��ʱ��Ҫ
//�ǼǱ��      �Ǽ�Ԥ��ʱ��Ҫ
   
var selectNode;
//var proc=this.parent.proc;
var proc;
//����ʵ��ID
var proc_id;
/**********************************************************************************
*��������: init()
*����˵��: ��ʼ������  ��������� ���صǼǲ�Ԥ���õ�����
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/     
function init(proc){
	this.proc = proc;					//�����ڴ���������������
	proc_id = proc.procId;				//����ʵ��ID��ֵ
	
	$('body').layout({
		fit:true
	});		
	//���صǼǲ�Ԥ����
	$('#registerbook_tree').tree({  
		url:ctx+"/json/registerbook_data.json",
		onClick: function(node){
			selectNode=node;
				var selectedDiv;
				if(selectNode.text=='��Ȼ��Ϣ'){
					selectedDiv=$("#naturalInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='����Ȩ��Ϣ'){
					selectedDiv=$("#ownershipInfo").css('display','block').siblings('div').css('display','none');
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
	registerPreview();				//�Ǽǲ�Ԥ��������
	$(":input").attr("readonly","readonly");		//��������input���Ϊ���ɱ༭״̬
};
// init method end
/**********************************************************************************
*��������: registerPreview()
*����˵��: �Ǽǲ�Ԥ��������  ���ú�̨����  ��ҳ����ֵ 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/      
function registerPreview(){
   var naturalInfo;					//��Ȼ��Ϣ����
   var ownershipInfo;				//����Ȩ��Ϣ����
   var applicants;					//��������Ϣ getregisterInfo
   var ownershipInfoHis;			//��ȡҪע��������Ȩ��Ϣ
   var historyOwnershipInfo;		//����Ȩ��ʷ��Ϣ registerPreview
	$.ajax({
		url: ctx+'/common/register!getregisterInfo.action?time='+new Date(),
		dataType: 'json',
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"proc_id":proc_id},
		success: function(data){
			JSON.stringify(data.userInfoHis);
			//JSON.stringify(data.userInfoHis);
			naturalInfo = data.naturalInfo;												//��Ȼ��Ϣ
			setNaturalInfo(naturalInfo);												//������Ȼ��Ϣ
			ownershipInfo = data.userInfo;											//����Ȩ��Ϣ
			setOwnershipInfo(ownershipInfo);											//�������Ȩ��Ϣ
			if(data.userInfoHis)
			{
				ownershipInfoHis=data.userInfoHis;           //����Ȩ��Ϣ
				setcanInfoHis(ownershipInfoHis);
			}
		},
		error:function(){
			
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
   $("#can_recorder").val(userInfoHis.RECORDER);
}


/**********************************************************************************
*��������: setNaturalInfo()
*����˵��: �����Ȼ��Ϣ
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/   
function setNaturalInfo(naturalInfo){
   $('#naturalInfoForm').form('load',naturalInfo);
   $('#ownershipInfoForm').form('load',naturalInfo);
   
   $("#BUILD_AREA").val($("#BUILD_AREA").val()+" �O");
   
   $("#TAONEI_AREA").val($("#TAONEI_AREA").val()+" �O");
   
   $("#FT_COMMON_AREA").val($("#FT_COMMON_AREA").val()+" �O");
   
   //$("#AT_FLOOR").val(naturalInfo.ROOMNAME+"("+naturalInfo.AT_FLOOR+")");
   
   $("#USE_PER").val($("#USE_PER").val()+" �� "+naturalInfo.PA_START_DATE+" ��  "+naturalInfo.PA_END_DATE);
	   
}
/**********************************************************************************
*��������: setHoldersInfo()
*����˵��: ����Ȩ������Ϣ
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/   
function setHoldersInfo(holder,rightCertificateNo){
	var data = holder;
	if(holder.length<1){
		return;
	}
	$("#table_holder").empty();
	$("#table_holder").append("<tr><td>���</td><td>����Ȩ��</td><td>���֤��</td><td>�������ڵ�</td><td>�������</td><td>���ز�֤��</td></tr>");
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
*��������: setOwnershipInfo()
*����˵��: �������Ȩ��Ϣ
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/   
function setOwnershipInfo(data){
	//alert(JSON.stringify(data));
	   $('#ownershipInfoForm').form('load',data);
   holder = data.holder;
	setHoldersInfo(holder,data.rightCertificateNo);					//������������Ϣ  �������ز�֤��
	
	historyOwnershipInfo = data.historyOwnershipInfo;				//��ʷ����Ȩ��Ϣ
	
	setHistoryOwnershipInfo(historyOwnershipInfo);					//������ʷ����Ȩ��Ϣ��
}
/**********************************************************************************
*��������: setHistoryOwnershipInfo()
*����˵��: ��������Ȩ��ʷ��Ϣ  ��regId�Ž���input ����Ϊhidden
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
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
				  holder = holder+"��"+tempHolder[j].HOL_NAME; 
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
  //��Ϣ���óɹ���  ���軯�ñ���¼�  
  initHistoryOwnershipTable();
}
   
/**********************************************************************************
*��������: initHistoryOwnershipTable()
*����˵��: ��ʼ������Ȩ��ʷ��   �����е���¼�
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
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
*��������: trOnmouseDown()
*����˵��: ����Ȩ��ʷ�� �е���¼� ����к�  �ı䱳����ɫ  ���¼�������Ȩ����
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/  
function trOnmouseDown(trs,obj){  
  for( var o=0; o<trs.length; o++ ){  
   if( trs[o] == obj ){  
    trs[o].style.backgroundColor = '#DFEBF2';  
	  var regId = trs[o].getElementsByTagName('input')[0].value;		//�����е�input�л�ȡ�ǼǱ��
	  reloadOwnershipInfoByRegId(regId);								//���¼�������Ȩ�����Ϣ    --��Ȼ��Ϣδ���¼���
   }  
   else{  
      trs[o].style.backgroundColor = '';  
   }  
  }  
} 
/**********************************************************************************
*��������: reloadOwnershipInfoByRegId()
*����˵��: ���¼�������Ȩ��Ϣ  ͨ���ǼǱ�� �����е�input�л�ȡ�ǼǱ��
*�� �� ֵ: 
*��   ��:regId �ǼǱ��
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
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
			$('#ownershipInfoForm').form('load',ownershipInfo);							//���¼�������Ȩ��Ϣ
			holder = data.holder;
			setHoldersInfo(holder,data.rightCertificateNo);					//������������Ϣ  �������ز�֤��
			}
	 });
}
/**********************************************************************************
*��������: validate()
*����˵��: ͨ��У�鷽��  �����ﲻ��У��  ֱ�ӷ���true
*�� �� ֵ: 
*��   ��:
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/  
function validate(){
	return true;
}
   
   
