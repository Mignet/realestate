var app_pic;//������������ͷ��ID
var age_pic;//�����������ͷ��
var personType;//����������������  ���Ǵ�����
function init(user){
 /*   $("#qlrId").val(user.HOLDER_ID);
    $("#qlrName").val(user.HOL_NAME);
    $("#qlrType").val(user.HOL_TYPE);
    $("#zjzl").val(user.CER_TYPE);
    $("#zjNo").val(user.CER_NO);
    $("#txAddr").val(user.ADDRESS);
    $("#depType").val(user.DEPART_TYPE);
    $("#qlrKind").val(user.HOL_REL);
    $("#position").val(user.POSITION);
    $("#djdyId").val(user.REG_UNIT_ID);
    $("#djdyType").val(user.REG_UNIT_TYPE);*/
   
	var form = $('#detail_app_form').tableform({
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
					closeInTopWindow('detail_app_win');
					
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



