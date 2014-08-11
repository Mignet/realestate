var app_pic;//������������ͷ��ID
var age_pic;//�����������ͷ��
var personType;//����������������  ���Ǵ�����
//0:�����ı���, 1:��������ѡ���
var holArray = [['060016','qlrName1',0],['060027','qlrType1',1]
                ,['060030','zjzl1',1],['060017','zjNo1',0]
                ,['060019','txAddr1',0],['060018','position1',0]
				,['060022','frName1',0],['060029','qlrKind1',1]];
function init(user,array){
	    $("#qlrId").val(user.HOLDER_ID);
	    $("#qlrName").val(user.HOL_NAME);
	    $("#qlrType").val(user.HOL_TYPE);
	    $("#zjzl").val(user.CER_TYPE);
	    $("#zjNo").val(user.CER_NO);
	    $("#txAddr").val(user.ADDRESS);
	    $("#depType").val(user.DEPART_TYPE);
	    $("#qlrKind").val(user.HOL_REL);
	    $("#position").val(user.POSITION);
	    $("#djdyId").val(user.REG_UNIT_ID);
	    $("#djdyType").val(user.REG_UNIT_TYPE);
	   
	var form = $('#edit_holder_form').tableform({
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
			title: 'Ȩ����',
			name: 'HOL_NAME',
			id: 'qlrName1',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
		},{
			tag: 'input',
			title: 'Ȩ��������',
			name: 'HOL_TYPE',
			id: 'qlrType1',
			type: 'combodict',
			options: {
				code: '043',
			}
		},
		{
			tag: 'input',
			title: '֤������',
			name: 'CER_TYPE',
			id: 'zjzl1',
			type: 'combodict',
			options: {
				code: '002',
				
			}
		},{
			tag: 'input',
			title: '֤������',
			name: 'CER_NO',
			id: 'zjNo1'
		     
		},{
			tag: 'input',
			title: 'ͨѶ��ַ',
			name: 'ADDRESS',
			id: 'txAddr1',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
		},{
			tag: 'input',
			title: '�ݶ�',
			name: 'POSITION',
			id: 'position1',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
			},{
			tag: 'input',
			title: '����',
			name: 'LEGAL_NAME',
			id: 'frName1',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '������'
			}
			},{
				tag: 'input',
				title: 'Ȩ��������',
				name: 'HOL_REL',
				id: 'qlrKind1',
				type: 'combodict',
				options: {
					code: '063',
					
				}
			    
			}],
		url: ctx+'/houseownership/initialreg!updateApplicant.action?time='+new Date(),
		dataType : 'json',
		success: function(data){
			if (data.success) {
				top.$.messager.alert('�����û���ʾ',data.tipMessage,'info',function(){
					args.userDataGrid.datagrid('reload');
					closeInTopWindow('edit_holder_win');
					
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
	
  for(var i = 0;i < holArray.length; i++){
	  var temp = holArray[i][0];
	  var ishas = 0;
	  for(var j = 0;j < array.length; j++){
		  if(array[j] == temp){
			  ishas = 1;
			  break;
		  }
	  }
	  if(holArray[i][2] == 0){
         if(ishas == 1){
		     $("#"+holArray[i][1]).prop('readonly',false);
	     }else{
	    	 $("#"+holArray[i][1]).prop('readonly',true);
	     }
	  }else if(holArray[i][2] == 1){
		  if(ishas == 0){
			 $("#"+holArray[i][1]).combo("disable", true);
		  }
	  }
  }
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
	    $("#qlrName").val($("#qlrName1").val());
	    $("#qlrType").val($('input[name="HOL_TYPE"]').val());
	    $("#zjzl").val($('input[name="CER_TYPE"]').val());
	    $("#zjNo").val($("#zjNo1").val());
	    $("#txAddr").val($("#txAddr1").val());
	    $("#depType").val($("#depType1").val());
	    $("#qlrKind").val($("#qlrKind1").val());
	    $("#position").val($("#position1").val());
	    $("#edit_holder_form").submit();
}


