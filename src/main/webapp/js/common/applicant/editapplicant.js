var app_pic;//������������ͷ��ID
var age_pic;//�����������ͷ��
var personType;//����������������  ���Ǵ�����
function init(user){
	//alert(JSON.stringify(user));
	//alert(JSON.stringify(user));
	//alert(user.app_pic);
	if(user.APP_PIC){
		setImage(user.APP_PIC,"app");	//����ͷ��
	}
	if(user.AGE_PIC){
		setImage(user.AGE_PIC,"age");	//����ͷ��
	}
	
	$("#sqrbid").val(user.APPLICANT_ID);
	
	if(!$("#sqrbid").val()){
		$("#sqrbid").val(user.APPLICANT_ID);
	}
	$("#bus_id").val(user.BUS_ID);
	
	if(!$("#bus_id").val()){
		$("#bus_id").val(user.BUS_ID);
	}
	
	
    $("#sqr").val(user.app_name);
    $("#sqrlx").val(user.app_type);
    $("#zjlx").val(user.app_cer_type);
    $("#fddbr").val(user.legal_name);
    $("#zjbh").val(user.app_cer_no);
    $("#fe").val(user.app_port);
    $("#dz").val(user.app_address);
    $("#lxdh").val(user.app_tel);
    $("#dlr").val(user.agent_name);
    $("#dlrdh").val(user.agent_tel);
    $("#dlrzjlx").val(user.agent_cer_type);
    $("#dlrsfzh").val(user.agent_cer);
    //$("#gx").val(user.hol_rel);
    $("#djdybh").val(user.reg_unit_code);
  //  $("#lcslbid").val(user.LCSLBID);
   
	var form = $('#edit_user_form').tableform({
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
			name: 'app_name',
			id: 'sqr',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
		},{
			tag: 'input',
			title: '����������',
			name: 'app_type',
			id: 'sqrlx',
			type: 'combodict',
			options: {
				classCode: '043',
			}
		},
		{
			tag: 'input',
			title: '֤������',
			name: 'app_cer_type',
			id: 'zjlx',
			type: 'combodict',
			options: {
				classCode: '002',
				
			}
		},{
			tag: 'input',
			title: '����������',
			name: 'legal_name',
			id: 'fddbr'
		     
		},{
			tag: 'input',
			title: '֤������',
			name: 'app_cer_no',
			id: 'zjbh',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
		},{
			tag: 'input',
			title: '�ݶ�',
			name: 'app_port',
			id: 'fe',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
			},{
			tag: 'input',
			title: '��ַ',
			name: 'app_address',
			id: 'dz',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
			},{
			tag: 'input',
			title: '��ϵ�绰',
			name: 'app_tel',
			id: 'lxdh'
		} ,{
			tag: 'input',
			title: '������',
			name: 'agent_name',
			id: 'dlr'
		    
		},
		{
			tag: 'input',
			title: '������֤������',
			name: 'agent_cer_type',
			id: 'dlr',
			type: 'combodict',
			options: {
				classCode: '002',
				
			}
		    
		},
		
		{
			tag: 'input',
			title: '������֤������',
			name: 'agent_cer',
			id: 'dlrsfzh'
		    
		},{
			tag: 'input',
			title: '��������ϵ�绰',
			name: 'agent_tel',
			id: 'dlrdh'
		    
		}
		,{
			tag: 'input',
			title: '�����˹�ϵ',
			name: 'hol_rel',
			id: 'gx',
			type: 'combodict',
			options: {
				classCode: '063',
				
			}
		    
		}],
		url: ctx+'/houseownership/initialreg!updateApplicant.action?time='+new Date(),
		dataType : 'json',
		success: function(data){
			if (data.success) {
				top.$.messager.alert('�����û���ʾ',data.tipMessage,'info',function(){
					args.userDataGrid.datagrid('reload');
					closeInTopWindow('edit_user_win');
					
				});				
			} else {
				top.$.messager.alert('�����û�����',data.errorMessage,'error');
			}
		}
		
		
		
	});
	form.form("load",user);
	
	//�ӷ���˻�ȡ�û���Ϣ
	/**
	$.ajax({
		type: "POST",
		url:  '../appDelegate/getAppByID.run?djbh='+args.user.djbh,
		dataType:"json",
		success: function(user){
			//�����������
			user.djbh=args.user.djbh;
			user.password = null;
			//�����
			form.form("load",user);
		}
	});

	*/
};

$(function(){
	$.extend($.fn.validatebox.defaults.rules , {
		confirmPassword: {
	        validator: function(value,param){
	            return value == $(param[0]).val();
	        },
	        message: '�����������벻ƥ�䡣'
	    }
	});
});


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


function submit(){
	if(app_pic){
		$("#app_pic").val(app_pic);
	}
	if(age_pic){
		$("#age_pic").val(age_pic);
	}
	//alert($("#zjbh").val());
	    $("#sqr1").val($("#sqr").val());
	    $("#sqrlx1").val($('input[name="APP_TYPE"]').val());
	    $("#zjlx1").val($('input[name="APP_CER_TYPE"]').val());
	    $("#fddbr1").val($("#fddbr").val());
	    $("#zjbh1").val($("#zjbh").val());
	    $("#fe1").val($("#fe").val());
	    $("#dz1").val($("#dz").val());
	    $("#lxdh1").val($("#lxdh").val());
	    $("#dlr1").val($("#dlr").val());
	    $("#dlrzjlx1").val($('input[name="APP_CER_TYPE"]').val());
	    $("#dlrdh1").val($("#dlrdh").val());
	    $("#dlrsfzh1").val($("#dlrsfzh").val());
	    $("#gx1").val($("#gx").combo("getValue"));
	   //return ;
	$('#edit_user_form').submit();
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


