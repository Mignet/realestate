(function($){

	$.ajaxSetup({
		
	});	
	
	$.errorRespond = function(statusCode, data){
		for (var i=0;i<$.errorRespond.types.length;i++)
			if ($.errorRespond.types[i].code == statusCode){
				$.errorRespond.types[i].method(data);
				return;
			}
		$.errorRespond.types[0].method(data);	
	};
	
	$.errorRespond.types = [{
		code: 500,
		method: function(data){
			redirectPage(window,'/exception/exception.jsp');
		}
	},{
		code: 901,
		method: function(){
			top.$.messager.confirm('确定','你的连接已丢失，是否跳转到登录页面？',function(r){
				if (r)
					redirectPage(top,'/exception/session_lost.jsp');
			});
		}
	},{
		code: 700,
		method: function(data){
			var error_code = "PLAT-99999";
			var error_msg = "后台出错，请联系管理员！";
			var codeIndex = data.indexOf("errorCode:");
			var tmpdata;
			if (codeIndex >-1){
				tmpdata = data.substring(codeIndex+10);
				error_code = tmpdata.substring(0, tmpdata.indexOf(";"));
			}
			var msgIndex = data.indexOf("errorMsg:");
			if (msgIndex >-1){
				tmpdata = data.substring(msgIndex+9);
				error_msg = tmpdata.substring(0, tmpdata.indexOf(";"));
			}
			error_code = "异常代码："+error_code;
			error_msg = "异常信息："+ error_msg;			
			var error_tip = error_code +"<p>" + error_msg;
			top.$.messager.alert('异常提示：',error_tip,'error');
		}
	}];
	
	/** 
	 * 统一定制ajax请求的异常处理机制，需要后台提供ajax请求的各类状态码。如：
	 *     901: session丢失 （可定制）
	 *     500: 后台抛出异常（此状态码为spring异常处理机制包装设置，不可修改）
	 */
	$(document).ajaxError(function(event,XHR){
		var error_code = XHR.getResponseHeader("error_code");
		var error_msg = XHR.getResponseHeader("error_msg");
		error_code = "异常代码："+error_code;
		error_msg = "异常信息："+ error_msg;
		
		//var error_tip = error_code +";\r\n" + error_msg;
		//alert(error_tip);
		
		var error_tip = error_code +"<p>" + error_msg;
		top.$.messager.alert('异常提示：',error_tip,'error');
		//alert(XHR.responseText);
		//$.errorRespond(XHR.status);
	});	
})(jQuery);

/*
 * 在js中实现重定向。此方法将指定窗口win重定向为指定的URL。
 * 注意此URL为对根目录的相对位置。必须以'/'开头。
 */
function redirectPage(win,url){
	win.location.pathname = $.getRootPath() + url;
}
