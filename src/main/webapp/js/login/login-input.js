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
	      $("#btnLogin").click(); //chrome ff下有效  ie无效
	      $("#btnLogin").focus(); 	//ie下有效  chrome ff下无效
	    }
	   });

	$('#usercode').focus();
	$('#usercode').select();
	
	
	if(loginflag!=null && loginflag=="1"){
		top.$.messager.alert('登陆出错提示：',"你没有登录或者长时间没有操作<br>请重新登录",'error');
		window.open("login!input.action?loginflag=0", "_top");
	}
	
	if(errorLoginMsg != null && errorLoginMsg != ""){	
		//alert({message:errorLoginMsg,title:'登陆出错提示',height:170,width:280,showMask:false,handler:handler});
		top.$.messager.alert('登陆出错提示：',errorLoginMsg,'error');
	}
});


/**
 * 计算字符串的长度，汉字算2个长度
 * @param str : 需要检查长度的字符
 * @param nbsp : 是否去除空格后检查，true：去除；false：不去除；
 * @return 字符串的长度
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
		//提交的同时，锁定页面
		$("#usercode").attr("disabled","disabled");
		$("#password").attr("disabled","disabled");
		$("#btnLogin").attr("disabled","disabled");
		var win = $.messager.progress({
			title:'Please waiting',
			msg:'服务器正在校验数据...'
		});
		//使用ajax校验
		$.post("login!userLogin.action",
		{usercode:$("#usercode").val(),password:$("#password").val()},
		function(data,status){
			var o = $.parseJSON(data);
			if(o.errorLoginMsg){
				$.messager.progress('close');
				top.$.messager.alert('异常提示：',o.errorLoginMsg,'error');
				$("#usercode").removeAttr("disabled");
				$("#password").removeAttr("disabled");
				$("#btnLogin").removeAttr("disabled");
			}else{
				window.location.assign('./login!home.action?u='+o.resulturl);
				//toggleFullScreen();
				//esys.openFullScreenWin('./login!home.action?u='+o.resulturl,"深圳市不动产登记系统");
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
	if(errorLoginMsg.test("密码")) {		  		
		$('#password').focus();
		$('#password').select();	
	}
	if(errorLoginMsg.test("代码")) { 		
		$('#usercode').focus();
		$('#usercode').select();
	}
}

function check() {
	if($.trim($('#usercode').val()).length<=0) {
		alert("请输入用户名! ");
		return false;
	}
	if(checkLength($('#usercode').val(),true)>20){
		alert("用户名不能超过20位! ");
		return false;
	}
	if($.trim($('#password').val()).length<=0) {
		alert("请输入用户密码! ");
		return false;
	}
	/*
	try{
		if(!$chk($('validatecode')..val())) {
			alert({message:"请输入验证码! ",title:'登陆出错提示',height:170,width:280,showMask:false,handler:pswFocus});
			return false;
		}
	}catch(e){}*/
	
	return true;
}


function changevalidateimage(){
	$('#validateimage').src = "login!createValidateImage.action?r=" + Math.random();
}

