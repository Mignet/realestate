$(function(){
	
    if (window != top) 
              top.location.href = location.href; 
	
	$("#btnLogin").click(function(){
		login();
	});
	
	$("#password").keydown(function(event){
	    var curKey = event.which; 
	  
	    if(curKey==13){  
	    	//alert(curKey);
	      //setTimeout(login(),200);
	      $("#btnLogin").click(); //chrome ff����Ч  ie��Ч
	      $("#btnLogin").focus(); 	//ie����Ч  chrome ff����Ч
	    }
	   });

	$('#usercode').focus();
	$('#usercode').select();
	
	
	if(loginflag!=null && loginflag=="1"){
		top.$.messager.alert('��½������ʾ��',"��û�е�¼���߳�ʱ��û�в���<br>�����µ�¼",'error');
		window.open("login!input.action?loginflag=0", "_top");
	}
	
	if(errorLoginMsg != null && errorLoginMsg != ""){	
		//alert({message:errorLoginMsg,title:'��½������ʾ',height:170,width:280,showMask:false,handler:handler});
		top.$.messager.alert('��½������ʾ��',errorLoginMsg,'error');
	}
});


/**
 * �����ַ����ĳ��ȣ�������2������
 * @param str : ��Ҫ��鳤�ȵ��ַ�
 * @param nbsp : �Ƿ�ȥ���ո���飬true��ȥ����false����ȥ����
 * @return �ַ����ĳ���
 */
function checkLength(str, nbsp){
	if(str.length <= 0) return 0;
	if('string' !== typeof str) return false;
	if(nbsp == undefined || nbsp == null) nbsp = 0;
	if(nbsp != 0) {
		return parseInt(str.replace(/\s/g, '').replace(/[^\x00-\xff]/g,"**").length);
	}
	return parseInt(str.replace(/[^\x00-\xff]/g,"**").length);
}
function login(){
	if(check()){
		//$("#loginForm").submit();
		//�ύ��ͬʱ������ҳ��
		$("#usercode").attr("disabled","disabled");
		$("#password").attr("disabled","disabled");
		$("#btnLogin").attr("disabled","disabled");
		var win = $.messager.progress({
			title:'Please waiting',
			msg:'����������У������...'
		});
		//ʹ��ajaxУ��
		$.post("login!userLogin.action",
		{usercode:$("#usercode").val(),password:$("#password").val()},
		function(data,status){
			var o = $.parseJSON(data);
			if(o.errorLoginMsg){
				$.messager.progress('close');
				top.$.messager.alert('�쳣��ʾ��',o.errorLoginMsg,'error');
				$("#usercode").removeAttr("disabled");
				$("#password").removeAttr("disabled");
				$("#btnLogin").removeAttr("disabled");
			}else{
				window.location.assign('./login!home.action?u='+o.resulturl);
				//toggleFullScreen();
				//esys.openFullScreenWin('./login!home.action?u='+o.resulturl,"�����в������Ǽ�ϵͳ");
				//windowclose();
			}
		 });
	}
}
function toggleFullScreen() { 
	
	var doc = window.document; 
	var docEl = doc.documentElement; 
	 
	var requestFullScreen = docEl.requestFullscreen || docEl.mozRequestFullScreen || docEl.webkitRequestFullScreen; 
	var cancelFullScreen = doc.exitFullscreen || doc.mozCancelFullScreen || doc.webkitExitFullscreen; 
	 
	if(!doc.fullscreenElement && !doc.mozFullScreenElement && !doc.webkitFullscreenElement) { 
	  requestFullScreen.call(docEl); 
	} 
	else { 
	  cancelFullScreen.call(doc); 
	} 
}
function windowclose() {
    var browserName = navigator.appName;
    if (browserName=="Netscape") {
        window.open('', '_self', '');
        window.close();
    }
    else {
        if (browserName == "Microsoft Internet Explorer"){
            window.opener = "whocares";
            window.opener = null;
            window.open('', '_top');
            window.close();
        }
    }
}
function handler() {	
	if(errorLoginMsg.test("����")) {		  		
		$('#password').focus();
		$('#password').select();	
	}
	if(errorLoginMsg.test("����")) { 		
		$('#usercode').focus();
		$('#usercode').select();
	}
}

function check() {
	if($.trim($('#usercode').val()).length<=0) {
		alert("�������û���! ");
		return false;
	}
	if(checkLength($('#usercode').val(),true)>20){
		alert("�û������ܳ���20λ! ");
		return false;
	}
	if($.trim($('#password').val()).length<=0) {
		alert("�������û�����! ");
		return false;
	}
	/*
	try{
		if(!$chk($('validatecode')..val())) {
			alert({message:"��������֤��! ",title:'��½������ʾ',height:170,width:280,showMask:false,handler:pswFocus});
			return false;
		}
	}catch(e){}*/
	
	return true;
}


function changevalidateimage(){
	$('#validateimage').src = "login!createValidateImage.action?r=" + Math.random();
}

