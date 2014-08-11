/**
 * �Ǽǲ�Ԥ��js
 */

   //�򿪵ĸ�����
   var selectNode;
   var proc=this.parent.proc;
   //����ʵ��ID
   var proc_id;
   
   /**********************************************************************************
	*��������: init
	*����˵��: ��ʼ��ҳ��
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: xuzz
	*��������: 2014-04-03
	*�޸���ʷ: 
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
				if(selectNode.text=='��Ȼ��Ϣ'){
					//alert(selectNode.text);
					selectedDiv=$("#naturalInfo").css('display','block').siblings('div').css('display','none');
				}else if(selectNode.text=='������Ϣ'){
					selectedDiv=$("#demurrerInfo").css('display','block').siblings('div').css('display','none');
					//getMortMess();
				}
			},
			onLoadSuccess:function(node,data){
				var defaultNode;
				
				for(var i=0;i<data.length;i++){
					if(data[i].text=='��Ȼ��Ϣ'){
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
		
   };// init��������
   /**********************************************************************************
	*��������: registerPreview
	*����˵��: ��ȡ�Ǽǲ�Ԥ����Ϣ
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: xuzz
	*��������: 2014-04-03
	*�޸���ʷ: 
	***********************************************************************************/
   function registerPreview(){
	   var naturalInfo;					//��Ȼ��Ϣ����
	   var demurrerInfo;				//������Ϣ����
	   var applicants;					//��������Ϣ
	   var historyOwnershipInfo;		//��ʷ��Ϣ
		$.ajax({
			url: ctx+'/common/register!getregisterInfo.action?time='+new Date(),
			dataType: 'json',
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			data:{"proc_id":proc_id},
			success: function(data){
				
				alert(JSON.stringify(data));
				naturalInfo = data.naturalInfo;
				
				
				setNaturalInfo(naturalInfo);
				demurrerInfo = data.userInfo;											//������Ϣ
				setdemurrerInfo(demurrerInfo);											//���������Ϣ
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
   }
   
   /**********************************************************************************
   *��������: setcanInfoHis()
   *����˵��: ���ע��������Ϣ
   *�� �� ֵ: 
   *��������: Joyon
   *��������: 2014-03-01
   *�޸���ʷ: 
   ***********************************************************************************/   
   function setcanInfoHis(userInfoHis){
      $("#CAN_REG_CODE").val(userInfoHis.REG_CODE);
      //$("#CAN_DISS_DATE").val(userInfoHis.DISS_DATE);   
      $("#CAN_REG_DATE").val(userInfoHis.REG_DATE);
      $("#CAN_RECORDER").val(userInfoHis.RECORDER);
   }
   
   /**********************************************************************************
	*��������: setcandemurrerInfo
	*����˵��: ע��������Ϣ����չʾ
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: xuzz
	*��������: 2014-04-03
	*�޸���ʷ: 
	***********************************************************************************/
  function setcandemurrerInfo(demurrerInfo)
  {
	   $('#demurrerInfoForm').form('load',demurrerInfo);
	   $('#demurrerlimit').val("�� "+demurrerInfo.START_DATE+" �� "+demurrerInfo.END_DATE);
  }
   /**********************************************************************************
	*��������: setdemurrerInfo
	*����˵��: ������Ϣ����չʾ
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: xuzz
	*��������: 2014-04-03
	*�޸���ʷ: 
	***********************************************************************************/
   function setdemurrerInfo(demurrerInfo)
   {
	   $('#demurrerInfoForm').form('load',demurrerInfo);
	   
	   var historyOwnershipInfo = demurrerInfo.historyOwnershipInfo;
	   setHolder(demurrerInfo.holder);
	   setHistoryOwnershipInfo(historyOwnershipInfo);
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
	  var applyname="";						//������
	  var applynamenum="";						//���������֤����
	  
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
	*��������: setHistoryOwnershipInfo
	*����˵��: ��������Ȩ��ʷ��Ϣ
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: xuzz
	*��������: 2014-04-03
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
		  //$('#REG_CODE').val(data[i].regId); 
		  $("#table_demurrerInfo").append("<tr><td>"+count+"<input type='hidden' value='"+data[i].regId+"'/></td><td><label>"+data[i].busName+
				  "</label></td><td><label>"+holder+"</label></td><td><label>"+regDate+"</label></td></tr>");
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
	*��������: 2014-04-03
	*�޸���ʷ: 
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
	*��������: setNaturalInfo
	*����˵��: �����Ȼ��Ϣ
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: xuzz
	*��������: 2014-04-03
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
 
  
   
