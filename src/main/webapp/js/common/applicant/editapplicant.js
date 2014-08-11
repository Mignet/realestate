var app_pic;//用来存申请人头像ID
var age_pic;//用来存代理人头像
var personType;//用来区分是申请人  还是代理人
function init(user){
	//alert(JSON.stringify(user));
	//alert(JSON.stringify(user));
	//alert(user.app_pic);
	if(user.APP_PIC){
		setImage(user.APP_PIC,"app");	//设置头像
	}
	if(user.AGE_PIC){
		setImage(user.AGE_PIC,"age");	//设置头像
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
			title: '申请人',
			name: 'app_name',
			id: 'sqr',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
		},{
			tag: 'input',
			title: '申请人类型',
			name: 'app_type',
			id: 'sqrlx',
			type: 'combodict',
			options: {
				classCode: '043',
			}
		},
		{
			tag: 'input',
			title: '证件类型',
			name: 'app_cer_type',
			id: 'zjlx',
			type: 'combodict',
			options: {
				classCode: '002',
				
			}
		},{
			tag: 'input',
			title: '法定代表人',
			name: 'legal_name',
			id: 'fddbr'
		     
		},{
			tag: 'input',
			title: '证件号码',
			name: 'app_cer_no',
			id: 'zjbh',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
		},{
			tag: 'input',
			title: '份额',
			name: 'app_port',
			id: 'fe',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
			},{
			tag: 'input',
			title: '地址',
			name: 'app_address',
			id: 'dz',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
			},{
			tag: 'input',
			title: '联系电话',
			name: 'app_tel',
			id: 'lxdh'
		} ,{
			tag: 'input',
			title: '代理人',
			name: 'agent_name',
			id: 'dlr'
		    
		},
		{
			tag: 'input',
			title: '代理人证件类型',
			name: 'agent_cer_type',
			id: 'dlr',
			type: 'combodict',
			options: {
				classCode: '002',
				
			}
		    
		},
		
		{
			tag: 'input',
			title: '代理人证件号码',
			name: 'agent_cer',
			id: 'dlrsfzh'
		    
		},{
			tag: 'input',
			title: '代理人联系电话',
			name: 'agent_tel',
			id: 'dlrdh'
		    
		}
		,{
			tag: 'input',
			title: '申请人关系',
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
				top.$.messager.alert('更新用户提示',data.tipMessage,'info',function(){
					args.userDataGrid.datagrid('reload');
					closeInTopWindow('edit_user_win');
					
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
//点击读ic卡按钮，获取身份证号信息
function setid(){
	ClearIDCard(); //读前清理读卡器缓存
	if(!readIDCard()){
		top.$.messager.alert('读卡提示','识别卡失败','info',function(){});
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
	
	  ClearIDCard(); //读后清理读卡器缓存
	//CVR_IDCard.DoStopRead; //停止读卡
}

function setageid(){
	ClearIDCard(); //读前清理读卡器缓存
	if(!readIDCard()){
		top.$.messager.alert('读卡提示','识别卡失败','info',function(){});
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
	  ClearIDCard(); //读后清理读卡器缓存
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


