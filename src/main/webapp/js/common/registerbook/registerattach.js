/**
 * �Ǽǲ�Ԥ��js
 */

   //�򿪵ĸ�����
   var selectNode;
   var proc=this.parent.proc;
   //����ʵ��ID
   var proc_id;
   var cur_reg_unit_code;					//��ǰ�鿴�ĵǼǵ�Ԫ�ı��
   var old_reg_unit_code;					//�ϴεǼǵ�Ԫ���    �����ж��Ƿ���Ҫ�Ӻ�̨ȡ����
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
				//cur_reg_unit_code = node.attributes.code;							//���һ��ʱʱ����ǰѡ���reg_unit_code��ֵ
				//�����ǰ�Ǽǵ�Ԫ��Ų������ϴεǼǵ�Ԫ���    ��ʾ  �л�����һ�Ǽǵ�Ԫ     ���µ�ǰҳ������      ----������ֱ���õ�ǰ����
				if(old_reg_unit_code!=cur_reg_unit_code){
					//registerPreview("");
				}
				selectNode=node;
				var selectedDiv;
				if(selectNode.text=='��Ȼ��Ϣ'){
					//alert(selectNode.text);
					selectedDiv=$("#naturalInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='�����Ϣ'){
					selectedDiv=$("#attachInfo").css('display','block').siblings('div').css('display','none');
					//getMortMess();
				}
			},
			onLoadSuccess:function(node,data){
				var defaultNode;
				//cur_reg_unit_code = data.attributes.code;				//��ʼ���ɹ�ʱ����ǰѡ���reg_unit_code��ֵ
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

		registerPreview("");		
		
   };// init��������

   function registerPreview(cur_reg_unit_code){
	   var naturalInfo;					//��Ȼ��Ϣ����
	   var attachInfo;				//�����Ϣ����
	   var applicants;					//��������Ϣ
	   var historyOwnershipInfo;		//��ʷ��Ϣ
		$.ajax({
			url: ctx+'/common/register!getregisterInfo.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"proc_id":proc_id,"reg_unit_code":cur_reg_unit_code},
			success: function(data){
				
				//alert(JSON.stringify(data));
				naturalInfo = data.naturalInfo;
				
				
				setNaturalInfo(naturalInfo);
				attachInfo = data.userInfo;											//�����Ϣ
				
				setattachInfo(attachInfo);											//��Ӳ����Ϣ
				if(data.userInfoHis)
				{
					ownershipInfoHis=data.userInfoHis;           //����Ȩ��Ϣ
					setcanInfoHis(ownershipInfoHis);
				}
				
			},
			error:function(){
				 //  $('#HOUSE_ATTR_DICT').combo('destroy');
				   //$('#GET_MODE_DICT').combo('destroy');
			}
		});
		old_reg_unit_code = cur_reg_unit_code;					//��ȡ���ݺ�ѵ�ǰ�ĵǼǵ�Ԫ��� ��ֵ���ϴ���ʷ��Ϣ
   }
   
   /**********************************************************************************
    *��������: setcanInfoHis()
    *����˵��: �����Ǽ���Ϣ
    *�� �� ֵ: 
    *��������: Joyon
    *��������: 2014-03-01
    *�޸���ʷ: 
    ***********************************************************************************/   
    function setcanInfoHis(userInfoHis){
       $('#unattachInfoForm').form('load',userInfoHis);
       $("#REG_CODE1").val(userInfoHis.REG_CODE);
    }
   
   
   function setattachInfo(attachInfo)
   {
	   $('#attachInfoForm').form('load',attachInfo);
	   $('#attachlimit').val("�� "+attachInfo.START_DATE+" �� "+attachInfo.END_DATE);
	   var historyOwnershipInfo = attachInfo.historyOwnershipInfo;
	   //setHolder(attachInfo.holder);
	   setHistoryOwnershipInfo(historyOwnershipInfo);
   }
   
   //�����Ȼ��Ϣ
   function setNaturalInfo(naturalInfo){
	   //alert(JSON.stringify(naturalInfo));
	   $('#naturalInfoForm').form('load',naturalInfo);
	   $("#BUILD_AREA").val($("#BUILD_AREA").val());
	   
	   $("#TAONEI_AREA").val($("#TAONEI_AREA").val());
	   
	   $("#FT_COMMON_AREA").val($("#FT_COMMON_AREA").val());
	   $("#USE_PER").val($("#USE_PER").val()+" �� "+naturalInfo.PA_START_DATE+" ��  "+naturalInfo.PA_END_DATE);
	   
   }

   
   /**********************************************************************************
	*��������: setHistoryOwnershipInfo
	*����˵��: ��������Ȩ��ʷ��Ϣ
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: xuzz
	*��������: 2014-04-08
	*�޸���ʷ: 
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
				  holder = holder+"��"+tempHolder[j].HOL_NAME;
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
	  
	  //��Ϣ���óɹ���  ���軯�ñ���¼�  
	  initHistoryOwnershipTable();
}
/**********************************************************************************
*��������: initHistoryOwnershipTable
*����˵��: ��ʼ������Ȩ��ʷ��   ���ϵ���¼�
*����˵��: ��
*�� �� ֵ: ��
*��������: xuzz
*��������: 2014-04-08
*�޸���ʷ: 
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
 	  reloadMortInfoByRegId(regId);								//���¼�������Ȩ�����Ϣ    --��Ȼ��Ϣδ���¼���
    }  
    else{  
       trs[o].style.backgroundColor = '';  
    }  
   }  
 } 
 /**********************************************************************************
  *��������: reloadMortInfoByRegId()
  *����˵��: ���¼��ص�ѺȨ��Ϣ  ͨ���ǼǱ�� �����е�input�л�ȡ�ǼǱ��
  *�� �� ֵ: 
  *��   ��:regId �ǼǱ��
  *��������: Joyon
  *��������: 2014-03-01
  *�޸���ʷ: 
  ***********************************************************************************/  
  function reloadMortInfoByRegId(regId){
  	 $.ajax({
  			url: ctx+'/common/register!getMorPre.action?time='+new Date(),
  		dataType: 'json',
  		contentType:"application/x-www-form-urlencoded; charset=GBK",
  		data:{"reg_code":regId,"reg_unit_code":cur_reg_unit_code},
  		success: function(data){
  			//alert(JSON.stringify(data));
  			$('#mortInfoForm').form('load',data);							//���¼�������Ȩ��Ϣ
  			holder = data.holder;
  			setHolder(holder,data.rightCertificateNo);					//������������Ϣ  �������ز�֤��
  			}
  	 });
  }
   
