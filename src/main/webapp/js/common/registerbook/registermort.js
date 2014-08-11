/*********************************************************************************
*��  ��  ��  ��: registermort.js
*��  ��  ��  ��: ��ѺȨ�Ǽǲ�Ԥ��js
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: panda
*��  ��  ��  �ڣ� 2014-02-28
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/

   //�򿪵ĸ�����
   var selectNode;
  // var proc=this.parent.proc;
   //����ʵ��ID
   var proc_id;
   var cur_reg_unit_code;					//��ǰ�鿴�ĵǼǵ�Ԫ�ı��
   var old_reg_unit_code;					//�ϴεǼǵ�Ԫ���    �����ж��Ƿ���Ҫ�Ӻ�̨ȡ����
/**********************************************************************************
	*��������: init
	*����˵��: ҳ���ʼ��
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
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
				cur_reg_unit_code = node.attributes.code;							//���һ��ʱʱ����ǰѡ���reg_unit_code��ֵ
				//�����ǰ�Ǽǵ�Ԫ��Ų������ϴεǼǵ�Ԫ���    ��ʾ  �л�����һ�Ǽǵ�Ԫ     ���µ�ǰҳ������      ----������ֱ���õ�ǰ����
				if(old_reg_unit_code!=cur_reg_unit_code){
					registerPreview(cur_reg_unit_code);
				}
				
				selectNode=node;
				//alert(JSON.stringify(selectNode));
				var selectedDiv;
				if(selectNode.text=='��ѺȨ��Ϣ'){
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
				cur_reg_unit_code = data.attributes.code;				//��ʼ���ɹ�ʱ����ǰѡ���reg_unit_code��ֵ
				
//				for(var i=0;i<data.length;i++){
//					if(data[i].text=='��Ȼ��Ϣ');{
//						cur_reg_unit_code = data[i].attributes.code;				//��ʼ���ɹ�ʱ����ǰѡ���reg_unit_code��ֵ
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

			
		
   };// init��������
	/**********************************************************************************
	*��������: registerPreview
	*����˵��: 
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
	***********************************************************************************/
   function registerPreview(cur_reg_unit_code){
	   var naturalInfo;					//��Ȼ��Ϣ����
	   var ownershipInfo;				//����Ȩ��Ϣ����
	   var applicants;					//��������Ϣ
	   var historyOwnershipInfo;		//����Ȩ��ʷ��Ϣ
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
		old_reg_unit_code = cur_reg_unit_code;					//��ȡ���ݺ�ѵ�ǰ�ĵǼǵ�Ԫ��� ��ֵ���ϴ���ʷ��Ϣ
   }
	/**********************************************************************************
	*��������: setNaturalInfo
	*����˵��: �����Ȼ��Ϣ
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
	***********************************************************************************/
   function setNaturalInfo(naturalInfo){
	   //alert(JSON.stringify(naturalInfo));
	   $('#naturalInfoForm').form('load',naturalInfo);
	   $("#BUILD_AREA").val($("#BUILD_AREA").val());
	   
	   $("#TAONEI_AREA").val($("#TAONEI_AREA").val());
	   
	   $("#FT_COMMON_AREA").val($("#FT_COMMON_AREA").val());
	   $("#USE_PER").val($("#USE_PER").val()+" �� "+naturalInfo.PA_START_DATE+" ��  "+naturalInfo.PA_END_DATE);
	   
   }
   /**********************************************************************************
	*��������: setMortInfo
	*����˵��: ����ѺȨ������Ϣ
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
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
	*��������: setHolder
	*����˵��: ���Ȩ������Ϣ
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
	***********************************************************************************/
  function setHolder(holder){
	  if(!holder){
		  return;
	  }
	  var holder_rel = {
			  MORTGAGER :"063003",					//��Ѻ��
			  MORTGAGEE :"063004",					//��ѺȨ��
			  MORTGAGE_TRANSFEROREE:"063006"		//��ѺȨ���÷�
	  };
	  var mortgagerNameStr="";						//��Ѻ�������ַ���
	  var mortgageeNameStr="";						//��ѺȨ�������ַ���
	  
	  for(var i =0;i<holder.length;i++){
		  //����ǵ�Ѻ�� �����Ѻ�˸�ֵ
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
	*��������: setHolder
	*����˵��: ���Ȩ������Ϣ
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
	***********************************************************************************/
  function setHistoryInfo(historyInfo){
	  $("#table_mortInfo").empty();
	  $("#table_mortInfo").append("<tr><td>���</td><td>�Ǽ�����</td><td>ծ����</td><td>�Ǽ�����</td></tr>");
	  
	  var data = historyInfo;
	  var holder;
	  var borrower;									//ծȨ��
	  var regDate;
	  var count;
	  for(var i=0;i<data.length;i++){
//		  tempHolder =data[i].holder;
//		  for(var j=0;j < tempHolder.length; j++){
//			  if(j==0){
//				  holder = tempHolder[j].HOL_NAME;
//			  }else{
//				  holder = holder+"��"+tempHolder[j].HOL_NAME; 
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
	*��������: getMortMess
	*����˵��: ��ȡ��Ѻ������Ϣ
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
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
   *��������: initHistoryOwnershipTable()
   *����˵��: ��ʼ������Ȩ��ʷ��   �����е���¼�
   *�� �� ֵ: 
   *��������: Joyon
   *��������: 2014-03-01
   *�޸���ʷ: 
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
	   $('#mortInfoForm').form('reset');	
	  // $("#REG_CODE").val(regId);
	   
   	 $.ajax({
   			url: ctx+'/common/register!getMorPre.action?time='+new Date(),
   		dataType: 'json',
   		contentType:"application/x-www-form-urlencoded; charset=GBK",
   		data:{"reg_code":regId,"reg_unit_code":cur_reg_unit_code},
   		success: function(data){
   			ownershipInfo = data.userInfo;	
   			$('#mortInfoForm').form('load',ownershipInfo);							//���¼�������Ȩ��Ϣ
   			
   			var holder = ownershipInfo.holder;
   			setHolder(holder,data.rightCertificateNo);					//������������Ϣ  �������ز�֤��
   			if($("#sure_amount").val()=='0')
   				$("#sure_amount").val("");
   		}
   	 });
   }
 
  
   
