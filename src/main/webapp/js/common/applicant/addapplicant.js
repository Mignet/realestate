var app_pic;//������������ͷ��ID
var age_pic;//�����������ͷ��
var personType;//����������������  ���Ǵ�����

function init(proc_id,hol_rel) {
	if(!hol_rel){
		hol_rel = '063013';
	}
	//$("#proc_id").val(proc_id);
	
	var form = $('#add_app_form').tableform({
		errorCode: 700,
		//��Ԫ�ر���������ͨ����Ϊ���У�
		colnum: 2,
		//���ؼ������п�
		titleWidth: 120,
		//���ؼ�������п�
		cellWidth: 150,
		//���ؼ�input��select��combo��������
		inputWidth:	150,
		//textarea�ؼ�������
		textareaWidth: 500,
		//textarea�ؼ�������
		textareaHeight: 90,
		//�Ƿ�����fieldset��ǩ
		fieldset:false	,
		//��Ԫ�ز������顣������ÿ�����󹹳�һ����Ԫ��
		inputs: [{
			tag: 'input',
			title: '������',
			name: 'app.app_name',
			id: 'sqr',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
		},{
			tag: 'input',
			title: '����������',
			name: 'app.app_type',
			id: 'sqrlx',
			type: 'combodict',
			options: {
				classCode: '043',
				required: true,
				missingMessage: '������'
			}
		    
		},
		{
			tag: 'input',
			title: '֤������',
			name: 'app.app_cer_type',
			id: 'zjlx',
			type: 'combodict',
			options: {
				classCode: '002',
				required: true,
				missingMessage: '������'
				
			}
		},{
			tag: 'input',
			title: '����������',
			name: 'app.legal_name',
			id: 'fddbr'
		     
		},{
			tag: 'input',
			title: '֤������',
			name: 'app.app_cer_no',
			id: 'zjbh',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������',
				//validType:'name'
			}
		},{
			tag: 'input',
			title: '�ݶ�',
			name: 'app.app_port',
			id: 'fe',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
			},{
			tag: 'input',
			title: '��ַ',
			name: 'app.app_address',
			id: 'dz',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
			},{
			tag: 'input',
			title: '��ϵ�绰',
			name: 'app.app_tel',
			type: 'validatebox',
			id: 'lxdh',
			options: {
				required: true,
				missingMessage: '������',
				validType:'mobile'
			}
		} ,{
			tag: 'input',
			title: '������',
			name: 'app.agent_name',
			id: 'dlr',
			type: 'validatebox',
			options: {
				//required: true,
				//missingMessage: '������',
				validType:'name'
				//validType:'length'
			}
		    
		},
		{
			tag: 'input',
			title: '������֤������',
			name: 'app.agent_cer_type',
			id: 'dlrzjlx',
			type: 'combodict',
			options: {
				classCode: '002',
				
			}
		    
		},		
		{
			tag: 'input',
			title: '������֤������',
			name: 'app.agent_cer',
			id: 'dlrsfzh'
		    
		},{
			tag: 'input',
			title: '��������ϵ�绰',
			name: 'app.agent_tel',
			id: 'dlrdh'
		    
		},{
			tag: 'input',
			title: '�����˹�ϵ',
			name: 'app.hol_rel',
			id: 'gx',
			type: 'combodict',
			options: {
				classCode: '063',
				
			}
		    
		}
		
		
		
		],
		url: ctx+'/houseownership/initialreg!saveApplicant.action?time='+new Date()+"&proc_id="+proc_id,
		dataType: 'json',
		success: function(data){
			if (data.success) {
				top.$.messager.alert('�����û���ʾ',data.tipMessage,'info',function(){
					
				});				
				args.userDataGrid.datagrid('reload');
				closeInTopWindow('add_user_win');
			} else {
				top.$.messager.alert('�����û�����',data.errorMessage,'error');
			}
			
		}
	});
	$("#sqrlx").combodict('setValue','043001');
	$("#zjlx").combodict('setValue','002001');
	
	   $("#fddbr").focus(function(){
		  // alert();
		   check();
		});
		//ʧȥ�����¼�
		
		$("#fddbr").blur(function(){
			check();
		}); 
		
	//����Ȩ���˹�ϵ   Ĭ��ֵ 
	if(hol_rel){
		$("#gx").combodict('setValue',hol_rel);
	}
	
	
};
//��鷨���������Ƿ���¼��
function  check(){
	var fddbr = $('#fddbr').val();
	if($.trim(fddbr).length==0){
		
		$("#sqrlx").combodict('setValue','043001');
	}else{
		
		
		$("#sqrlx").combodict('setValue','043002');
		
	}
	
	
}
//�����ic����ť����ȡ���֤����Ϣ
function setid(){
	ClearIDCard(); //��ǰ�������������
	if(!readIDCard()){
		top.$.messager.alert('������ʾ','ʶ��ʧ��','info',function(){});
		return
	}
	 var pName=CVR_IDCard.NameL; //var pNameL=CVR_IDCard.NameL;
	 var pSex=CVR_IDCard.SexL;   //var pSexL=CVR_IDCard.SexL;
	 var pNation=CVR_IDCard.NationL;  //var pNationL=CVR_IDCard.NationL;
	 var pBorn=CVR_IDCard.BornL;      //var pBornL=CVR_IDCard.BornL;
	 var pAddress=CVR_IDCard.Address;
	 var pCardNo=CVR_IDCard.CardNo;
	 var pPolice=CVR_IDCard.Police;
	 var pActivity=CVR_IDCard.Activity;
	 var pNewAddr=CVR_IDCard.NewAddr;  
	 var pActivityLFrom=CVR_IDCard.ActivityLFrom; 
	 var pActivityLTo=CVR_IDCard.ActivityLTo; 
	 var pPhotoBuffer=CVR_IDCard.GetPhotoBuffer; 
	  
	  $("#zjbh").val(pCardNo);
	  $("#sqr").val(pName);
	  $("#dz").val(pAddress);
	  $('#img_app_pic').attr("src","data:image/gif;base64,"+pPhotoBuffer);
	
	  ClearIDCard(); //�����������������
	//CVR_IDCard.DoStopRead; //ֹͣ����
}


/**
 * �����մ���
 */
function upload_pic(personType){
	window.open(ctx+'/jsp/upload/avatar.jsp?personType='+personType+'&uid='+Math.round(Math.random() * 900) + 100 +'&uploadtype=head','ͼƬ�ϴ�','height=253,width=450,status=no,toolbar=no,menubar=no,location=no,scrollbars=no,resizable=no');
}


/**
 * ����ͼƬΪ�����ϴ���ͼƬ--��������������ȷ����Ť����
 * @param imageId
 */
function setImage(imageId,paraPersonType){

	personType = paraPersonType;
	//alert(personType);
	if(personType=="app"){
		app_pic = imageId;
		$('#img_app_pic').attr("src",ctx+"/common/upload!getImage.action?imageId="+imageId);
	}else if (personType=="age"){
		age_pic = imageId;
		$('#img_age_pic').attr("src",ctx+"/common/upload!getImage.action?imageId="+imageId);
	}
	
}

function setageid(){
	ClearIDCard(); //��ǰ�������������
	if(!readIDCard()){
		top.$.messager.alert('������ʾ','ʶ��ʧ��','info',function(){});
		return
	}

	 var pName=CVR_IDCard.NameL; //var pNameL=CVR_IDCard.NameL;
	 var pSex=CVR_IDCard.SexL;   //var pSexL=CVR_IDCard.SexL;
	 var pNation=CVR_IDCard.NationL;  //var pNationL=CVR_IDCard.NationL;
	 var pBorn=CVR_IDCard.BornL;      //var pBornL=CVR_IDCard.BornL;
	 var pAddress=CVR_IDCard.Address;
	 var pCardNo=CVR_IDCard.CardNo;
	 var pPolice=CVR_IDCard.Police;
	 var pActivity=CVR_IDCard.Activity;
	 var pNewAddr=CVR_IDCard.NewAddr;  
	 var pActivityLFrom=CVR_IDCard.ActivityLFrom; 
	 var pActivityLTo=CVR_IDCard.ActivityLTo; 
	 var pPhotoBuffer=CVR_IDCard.GetPhotoBuffer; 
	 
	$("#dlr").val(pName);
	$("#dlrsfzh").val(pCardNo);
	$("#dlrzjlx").combodict('setValue','002001');
	$('#img_age_pic').attr("src","data:image/gif;base64,"+pPhotoBuffer);
	  ClearIDCard(); //�����������������
}
function submit(){
	if(app_pic){
		$("#app_pic").val(app_pic);
	}
	if(age_pic){
		$("#age_pic").val(age_pic);
	}
	
	$('#add_app_form').submit();
}
function readIDCard(){
	var strReadResult=CVR_IDCard.ReadCard;
	if(strReadResult == "0"){
		return true;
	}
	return false;
}
function ClearIDCard() {
	   CVR_IDCard.Name="";
	   CVR_IDCard.NameL="";
	   CVR_IDCard.Sex="";   
	   //CVR_IDCard.SexL="";   
	   CVR_IDCard.Nation="";
	   //CVR_IDCard.NationL="";
	   CVR_IDCard.Born="";
	   //CVR_IDCard.BornL="";
	   CVR_IDCard.Address="";
	   CVR_IDCard.CardNo="";
	   CVR_IDCard.Police="";
	   CVR_IDCard.Activity="";
	   CVR_IDCard.NewAddr="";
	  
	   return true;
}
