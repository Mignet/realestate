var app_pic;//用来存申请人头像ID
var age_pic;//用来存代理人头像
var personType;//用来区分是申请人  还是代理人

function init(proc_id,hol_rel) {
	if(!hol_rel){
		hol_rel = '063013';
	}
	//$("#proc_id").val(proc_id);
	
	var form = $('#add_app_form').tableform({
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
			name: 'app.app_name',
			id: 'sqr',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
		},{
			tag: 'input',
			title: '申请人类型',
			name: 'app.app_type',
			id: 'sqrlx',
			type: 'combodict',
			options: {
				classCode: '043',
				required: true,
				missingMessage: '必填项'
			}
		    
		},
		{
			tag: 'input',
			title: '证件类型',
			name: 'app.app_cer_type',
			id: 'zjlx',
			type: 'combodict',
			options: {
				classCode: '002',
				required: true,
				missingMessage: '必填项'
				
			}
		},{
			tag: 'input',
			title: '法定代表人',
			name: 'app.legal_name',
			id: 'fddbr'
		     
		},{
			tag: 'input',
			title: '证件号码',
			name: 'app.app_cer_no',
			id: 'zjbh',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项',
				//validType:'name'
			}
		},{
			tag: 'input',
			title: '份额',
			name: 'app.app_port',
			id: 'fe',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
			},{
			tag: 'input',
			title: '地址',
			name: 'app.app_address',
			id: 'dz',
			type: 'validatebox',
			options: {
				required: true,
				missingMessage: '必填项'
			}
			},{
			tag: 'input',
			title: '联系电话',
			name: 'app.app_tel',
			type: 'validatebox',
			id: 'lxdh',
			options: {
				required: true,
				missingMessage: '必填项',
				validType:'mobile'
			}
		} ,{
			tag: 'input',
			title: '代理人',
			name: 'app.agent_name',
			id: 'dlr',
			type: 'validatebox',
			options: {
				//required: true,
				//missingMessage: '必填项',
				validType:'name'
				//validType:'length'
			}
		    
		},
		{
			tag: 'input',
			title: '代理人证件类型',
			name: 'app.agent_cer_type',
			id: 'dlrzjlx',
			type: 'combodict',
			options: {
				classCode: '002',
				
			}
		    
		},		
		{
			tag: 'input',
			title: '代理人证件号码',
			name: 'app.agent_cer',
			id: 'dlrsfzh'
		    
		},{
			tag: 'input',
			title: '代理人联系电话',
			name: 'app.agent_tel',
			id: 'dlrdh'
		    
		},{
			tag: 'input',
			title: '申请人关系',
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
				top.$.messager.alert('新增用户提示',data.tipMessage,'info',function(){
					
				});				
				args.userDataGrid.datagrid('reload');
				closeInTopWindow('add_user_win');
			} else {
				top.$.messager.alert('新增用户错误',data.errorMessage,'error');
			}
			
		}
	});
	$("#sqrlx").combodict('setValue','043001');
	$("#zjlx").combodict('setValue','002001');
	
	   $("#fddbr").focus(function(){
		  // alert();
		   check();
		});
		//失去焦点事件
		
		$("#fddbr").blur(function(){
			check();
		}); 
		
	//设置权利人关系   默认值 
	if(hol_rel){
		$("#gx").combodict('setValue',hol_rel);
	}
	
	
};
//检查法定代表人是否已录入
function  check(){
	var fddbr = $('#fddbr').val();
	if($.trim(fddbr).length==0){
		
		$("#sqrlx").combodict('setValue','043001');
	}else{
		
		
		$("#sqrlx").combodict('setValue','043002');
		
	}
	
	
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
