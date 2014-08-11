var app_pic;//用来存申请人头像ID
var age_pic;//用来存代理人头像
var personType;//用来区分是申请人  还是代理人
//0:代表文本框, 1:代表下拉选项框
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
		//表单元素表格的列数（通常设为两列）
		colnum: 2,
		//表单控件标题列宽
		titleWidth: 120,
		//表单控件输入框列宽
		cellWidth: 150,
		//表单控件input、select、combo等输入框宽
		inputWidth:	150,
		//textarea控件输入框宽
		textareaWidth: 500,
		//textarea控件输入框高
		textareaHeight: 90,
		//是否设置fieldset标签
		fieldset:false	,
		//表单元素参数数组。数组中每个对象构成一个表单元素
		inputs: [{
			tag: 'input',
			title: '权利人',
			name: 'HOL_NAME',
			id: 'qlrName1',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
		},{
			tag: 'input',
			title: '权利人类型',
			name: 'HOL_TYPE',
			id: 'qlrType1',
			type: 'combodict',
			options: {
				code: '043',
			}
		},
		{
			tag: 'input',
			title: '证件类型',
			name: 'CER_TYPE',
			id: 'zjzl1',
			type: 'combodict',
			options: {
				code: '002',
				
			}
		},{
			tag: 'input',
			title: '证件号码',
			name: 'CER_NO',
			id: 'zjNo1'
		     
		},{
			tag: 'input',
			title: '通讯地址',
			name: 'ADDRESS',
			id: 'txAddr1',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
		},{
			tag: 'input',
			title: '份额',
			name: 'POSITION',
			id: 'position1',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
			},{
			tag: 'input',
			title: '法人',
			name: 'LEGAL_NAME',
			id: 'frName1',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
			},{
				tag: 'input',
				title: '权利人种类',
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
				top.$.messager.alert('更新用户提示',data.tipMessage,'info',function(){
					args.userDataGrid.datagrid('reload');
					closeInTopWindow('edit_holder_win');
					
				});				
			} else {
				top.$.messager.alert('更新用户错误',data.errorMessage,'error');
			}
		}
		
		
		
	});
	form.form("load",user);
	
	//从服务端获取用户信息
	/**
	$.ajax({
		type: "POST",
		url:  '../appDelegate/getAppByID.run?djbh='+args.user.djbh,
		dataType:"json",
		success: function(user){
			//对象添加属性
			user.djbh=args.user.djbh;
			user.password = null;
			//载入表单
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
	        message: '两次密码输入不匹配。'
	    }
	});
});


/**
 * 打开拍照窗口
 */
function upload_pic(personType){
	window.open(ctx+'/jsp/upload/avatar.jsp?personType='+personType+'&uid='+Math.round(Math.random() * 900) + 100 +'&uploadtype=head','图片上传','height=253,width=450,status=no,toolbar=no,menubar=no,location=no,scrollbars=no,resizable=no');
}


/**
 * 设置图片为最新上传的图片--方法由拍照完后点确定按扭调用
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


