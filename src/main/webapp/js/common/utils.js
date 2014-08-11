var esys = esys || {};

/**
* Global setting
**/
jQuery(document).ready(function(){
	
	//全局屏蔽右键
	jQuery(document).bind("contextmenu",function(e){   
	    return false;   
	});
	
	jQuery('body').keydown(function(e){
		//只读时阻止backspace键
		if(e.keyCode == 8 && (e.target.readOnly == undefined || e.target.readOnly == true)){
			e.preventDefault();
		}
		//处理刷新
		if(e.keyCode == 116 && top.F5){
			top.F5();
		}
	});
	
	//防止ajax请求缓存
	jQuery.ajaxSetup({
		cache : false
	});
});

function alert(msg,fn){
	jQuery.messager.alert("提示",msg,"info",fn);
}

function confirm(msg,callback,args,context){
	function _execFunc(func){
		if(jQuery.isArray(args)) {
			func.apply(context,args);
		}else if(args) {
			func.call(context,args);
		}else{
			func.call(context);
		}
	}
	context = context || callback;
	jQuery.messager.confirm("提示",msg,function(value){
			if(!callback)
				return false;
			if(!value && callback.onCancel){
				_execFunc(callback.onCancel);
				return false;
			}
			if(value){
				_execFunc(callback.onOK ? callback.onOK : callback);
			}
	  });
};

/**
 * 有Objet DOM元素时调用
 */
function confirmEx(msg,callback,args,context){
	jQuery("iframe[xtype=mask]").remove();
	confirm(msg,callback,args,context);
	jQuery('<iframe src="about:blank" xtype=mask style="position:absolute; visibility:inherit; width:110%;height:100%;top:0px; left:0px;z-index:-1; filter:progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0);"></iframe>').appendTo(".messager-window");
}

//ajax error通用函数
function fnError(XMLHttpRequest, textStatus, errorThrown){
	alert("网络连接异常，请检查网络");
}
/**
 * 重新封装jQuery.messager.confirm
 * 防止activexObject遮罩div
 * @param {} title
 * @param {} msg
 * @param {} callback
 */
esys.confirm = function(title,msg,callback){
	jQuery("iframe[xtype=mask]").remove();
	jQuery.messager.confirm(title,msg,callback);
	jQuery('<iframe src="about:blank" xtype=mask style="position:absolute; visibility:inherit; width:110%;height:100%;top:0px; left:0px;z-index:-1; filter:progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0);"></iframe>').appendTo(".messager-window");
};
/**
 * 有Objet DOM元素时调用
 */
function alertEx(msg,fn){
	jQuery("iframe[xtype=mask]").remove();
	jQuery.messager.alert("提示",msg,"info",fn);
	jQuery('<iframe src="about:blank" xtype=mask style="position:absolute; visibility:inherit; width:110%;height:100%;top:0px; left:0px;z-index:-1; filter:progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0);"></iframe>').appendTo(".messager-window");
}

function prompt(title,message,callback,context){
	context = context || callback;
	var title = title || "参数输入";
	var message = message || '输入参数值:';
	jQuery.messager.prompt(title,message,function(val){
			if(arguments.length == 0)
				return false;
			if(!val || !callback){
				return false;
			}
			callback.call(context,val);
		});
}

function destroyTab(tabID){
	function _destoryChildren(children){
		for(var i = 0;i<children.length;i++){
			if(children[i].frames && children[i].frames.length > 0)
				_destoryChildren(children[i].frames);
			children[i].location = 'about:blank';
		}
	}
	
	if(!frames[tabID])	return;
	try{
		if(frames[tabID].frames && frames[tabID].frames.length > 0){
			_destoryChildren(frames[tabID].frames);
		}
		frames[tabID].location = 'about:blank';
	}catch(e){}
}

/**
 * 删除本地文件
 */
function deleteLocalFiles(path){
	//fso用来处理文件和文件夹
	var fso = new ActiveXObject("Scripting.FileSystemObject");
	//如果指定的文件夹存在则返回 True ；否则返回 False
	fso.DeleteFile(path);
}

esys.request = function(action,data,callback,method,async,showErr){
	method = method || 'post';
	jQuery.ajax({
		url : action,
		dataType:'json',
		cache:false,
		type:method,
		data:data,
		async : async !== false,
		success : function(result){
			if(callback)
				callback(result);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown){
			if(showErr !== false)
				alert("网络连接异常，请检查网络");
		}
    });
};

esys.get = function(action,data,callback,async,showErr){
	this.request(action,data,callback,"get",async,showErr);
}

esys.getURLParameter = function(name,uri) {
	return decodeURI(
		(RegExp(name + '=' + '(.+?)(&|$)').exec(uri || location.search)||[,null])[1]
	);
};

var SCREEN_W_FIX = 20,SCREEN_H_FIX = 40;

esys._dialogDimension = function(width,height){
	var left = (window.screen.availWidth-width)/2;
	var top = (window.screen.availHeight-height)/2;
	//screen.availWidth,screen.availHeight修正
	if(width >= screen.availWidth){
		width = screen.availWidth - (jQuery.browser.msie ? SCREEN_W_FIX : 0);
	}
	if(height >= screen.availHeight){
		height = screen.availHeight - (jQuery.browser.msie ? SCREEN_H_FIX : 0);
	}
	top = top <= SCREEN_H_FIX/2 ? 0 : top;
	left = left <= SCREEN_W_FIX/2 ? 0 : left;
	return {"left":left, "top":top, "width":width, "height":height};
};

esys.openFullScreenWin = function(url,name){
	var width = screen.availWidth - (jQuery.browser.msie ? SCREEN_W_FIX : 0);
	var height = screen.availHeight - (jQuery.browser.msie ? SCREEN_H_FIX : 0);
	var top = 0,left = 0;
	var winName = name || new Date().getTime();
	var params = "height="+height+",width="+width+",top="+top+",left="+left+",toolbar=no,menubar=no,scrollbars=no, resizable=yes,location=no, status=no";
	var reParams = /\?.+/;
	var win = null;
	try{
		win = window.open("",winName,params);
		if(win.location.href.replace(reParams,"").indexOf(url.replace(reParams,"")) === -1){
			win = window.open(url,winName,params);
		}
	}catch(e){
		win = window.open(url,winName,params);
	}
	win.focus();
	return win;
};

esys.openWin = function(url,width,height,name,resizable){
	var dimension = esys._dialogDimension(width,height);
	var left = dimension.left, top = dimension.top;
	width = dimension.width;
	height = dimension.height;
	var options = "height="+height+",width="+width+",top="+top+",left="+left+",toolbar=no,menubar=no,scrollbars=no, resizable="+(resizable === false?"no":"yes")+",location=no, status=no";
	try{
		window.open(url,(name|| new Date().getTime().toString()).replace(/\s+/gi,''),
					options).focus();
	}catch(e){
		alert("打开窗口错误:" + e.message + "<br/>"
			+ options);
	}
};

esys.openModalDialog = function(url,width,height,args){
	var dimension = esys._dialogDimension(width,height);
	var left = dimension.left, top = dimension.top;
	width = dimension.width;
	height = dimension.height;
	return window.showModalDialog(url,args,
					"dialogWidth:"+width+"px;dialogHeight:"+height+"px;dialogTop:"+top+";dialogLeft:"+left+";scroll:no;status:no");
};

//屏蔽backspace键
esys.disableBackspace = function(){
	jQuery(document).keydown(function(e) {
		var $target = jQuery(event.target);
		var element = e.target.nodeName.toLowerCase();
		if (element != 'input' && element != 'textarea') {
			if (e.keyCode === 8) {
				return false;
			}
		} else {
			var readonly = $target.attr("readonly");
			var is_readonly = readonly != 'undefined' && readonly == 'readonly';
			var disabled_or_readonly = $target.is(':disabled') || is_readonly;
			if (disabled_or_readonly) return false;
		}
		return true;
	});
};

/**
 * @param s:"a=v1,b=v2,c=v3"
 * @return {a:v1,b:v2,c:v3}
   @return {} when exception
 */
esys.parseParam = function(s){
	var result = {};
	if(!s)
		return result;
	//var arr = s.split(",");
	var arr = s.split(/,(?=\w+)/g);//支持a=v1,b=,,c=v3
	try{
		jQuery.each(arr,function(i,item){
			if(!item)
				return;
			try{
				var ss = item.split("=");
				if(ss && ss[1]){
					result[$.trim(ss[0])] = jQuery.trim(ss[1]);
				}
			}catch(e){}
		});
	}catch(e){
		result = {};
	}
	return result;
};

/**
 * 动态加载脚本
 * @param url 相对当前页面路径的地址
 * @param callback 加载完成后需要执行的回调函数
 */
esys.loadJs = function(url, callback) {
	var done = false;
	var script = document.createElement('script');
	script.type = 'text/javascript';
	script.language = 'javascript';
	script.src = url;
	script.onload = script.onreadystatechange = function() {
		if (!done && (!script.readyState || script.readyState == 'loaded' || script.readyState == 'complete')) {
			done = true;
			script.onload = script.onreadystatechange = null;
			if (callback) {
				callback.call(script);
			}
		}
	};
	document.getElementsByTagName("head")[0].appendChild(script);
};

/**
 * 动态加载css
 * @param url 相对当前路径的地址
 * @param callback 加载完成后需要执行的回调函数
 */
esys.loadCss = function(url, callback) {
	var link = document.createElement('link');
	link.rel = 'stylesheet';
	link.type = 'text/css';
	link.media = 'screen';
	link.href = url;
	document.getElementsByTagName('head')[0].appendChild(link);
	if (callback) {
		callback.call(link);
	}
};

esys.browserIsIE = function(){
	if(jQuery.browser.msie){
		return true;
	}else{
		return false;
	}	
};

esys.showMask = function(selector, show, options){
	options = options || {};
	if(show){
		jQuery(selector).mask({
	        maskMsg: options.msg || '数据加载中...',
	        opacity: options.opacity || 1
	    });
	}else{
		jQuery(selector).mask("hide");
	}
};

(function($){
	$.fn.serializeObject = function() {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
			if (o[this.name]) {
				if (!o[this.name].push) {
					o[this.name] = [o[this.name]];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	};
	
	$.postJSON = function(url, data, callback, async) {
		async = async !== false;
	    return jQuery.ajax({
	        'type': 'POST',
	        'url': url,
	        'async' : async,
	        'contentType': 'application/json',
	        'data': JSON.stringify(data),
	        'dataType': 'json',
	        'success': callback
	    });
	};
})(jQuery);

(function($){
	$.fn.supportSelectAll = function(options){
	   $.fn.options = {onAfterSelection:function(){}};
	   $.extend($.fn.options,options||{});
	   var c = this;
	   var sl = selection = function(){
	   		var oriX = $(c).offset().left;
	   		var oriY = $(c).offset().top;
			return{
				mouseD:false,
				startD:false,
				origin:{x:oriX,y:oriY},
				start:{x:oriX,y:oriY},
				end:{x:oriX,y:oriY},
				el:{html:'<div id="se" class="se-item" type="selection" style="' +
							'background:#ffc;' +
							//透明度absolute定位时不起作用，暂时屏蔽
							//'filter:alpha(opacity=10);-moz-opacity:0.1;opacity:0.1;' +
							'position:absolute;' +
							'left:' + oriX + 'px;' +
							'top:' + oriY + 'px;' +
							'width:0px;height:0px;border:1px dotted red;"></div>"'}
			};
	   }();
	   $(this).mousedown(function(e){
			  sl.mouseD = true;
			  sl.start = {x:e.pageX+$(c).parent().scrollLeft()-sl.origin.x,y:e.pageY+$(c).parent().scrollTop()-sl.origin.y};
	   }).mousemove(function(e){
			 if(!sl.mouseD)
				return true;
			 document.selection.clear();
			 sl.startD = true;
			 sl.end = {x:e.pageX+$(c).parent().scrollLeft()-sl.origin.x,y:e.pageY+$(c).parent().scrollTop()-sl.origin.y};
			 $(sl.el.html).appendTo($(this));
			 var left = sl.start.x<=sl.end.x?sl.start.x:sl.end.x;
			 var top = sl.start.y<=sl.end.y?sl.start.y:sl.end.y;
			 $('#se[type="selection"]',this).css({left:left,top:top});
			 $('#se[type="selection"]',this).css({width:Math.abs(sl.end.x-sl.start.x),height:Math.abs(sl.end.y-sl.start.y)});
	   }).mouseup(function(e){
	   		sl.mouseD = false;
	   	    if(!sl.startD){
	   	    	if($('#se[type="selection"]',this)){
	   	    		$('#se[type="selection"]',this).remove();
	   	    	}
	   	    	return true;
	   	    }
	   	    sl.startD = false;
			sl.end = {x:e.pageX+$(c).parent().scrollLeft()-sl.origin.x,y:e.pageY+$(c).parent().scrollTop()-sl.origin.y};
			$('#se[type="selection"]',this).remove();
			
			//计算左上点和右下点
			var x1 = Math.min(sl.start.x,sl.end.x);
			var x2 = Math.max(sl.start.x,sl.end.x);
			var y1 = Math.min(sl.start.y,sl.end.y);
			var y2 = Math.max(sl.start.y,sl.end.y);
			$.fn.options.onAfterSelection.call($(this),{minX:x1,maxX:x2,minY:y1,maxY:y2});
	   });
	};
})(jQuery);

//pop window based on jQuery
(function($){
	function _doClick(options,context){
		$.fn.popupWindow.defaultSettings = {
			height:500, // sets the height in pixels of the window.
			width:500, // sets the width in pixels of the window.
			name:null, // name of window set from the name attribute of the element that invokes the click
			url:null, // url used for the popup
			fn:null
		};
		context = context||{name:"popwin",href:"about:blank"};
		$.popupWindow.defaultSettings = $.fn.popupWindow.defaultSettings;
		var settings = $.extend({}, $.fn.popupWindow.defaultSettings, options || {});
		if(settings.fn){
			settings.fn.call(context);
		}
		settings.name = settings.name || context.name;
		settings.url =  settings.url || context.href;
		esys.openWin(settings.url,settings.width,settings.height,settings.name);	
		return false;
	}
	
	$.fn.popupWindow = function(options){
		return this.each(function() {
			$(this).click(function() {
				_doClick(options,this);
			});

		});	
	};
	
	$.popupWindow = function(options){
		_doClick(options);	
	};
})(jQuery);

(function($){
	$.fn.extend({
		showTip:function(msg){
			msg = $(this).attr("msg")||msg;
			var box=$(this);
			var tip=$("<div class=\"validatebox-tip\">"+"<span class=\"validatebox-tip-content\">"+"</span>"+"<span class=\"validatebox-tip-pointer\">"+"</span>"+"</div>").appendTo("body");
			tip.find(".validatebox-tip-content").html(msg);
			tip.css({display:"block",left:box.offset().left+box.outerWidth(),top:box.offset().top});
			$(this)[0].tip = tip;
			$(this).addClass("validatebox-invalid");
		},
		closeTip:function(){
			$(this).removeClass("validatebox-invalid");
			if(!$(this)[0] || !$(this)[0].tip)
				return;
			$(this)[0].tip.remove();
		}
	});
	
	var TipTemplate = 
		"<div id='{0}' title='双击立即关闭' class='loading-tip' style='display:none;overflow:hidden;'>" +
			"<b class='b1'></b><b class='b2'></b><b class='b3'></b><b class='b4'></b>" +
				"<div id='tipContent' class='content'>" +
				"<iframe  src='javascript:void(0);' style='position:absolute; visibility:inherit; width:100%;height:100%;top:0px; left:0px;z-index:-1; filter:progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0);'></iframe>"+
				"<div class='tip-icon'></div><div class='tip-text'></div></div>" +
			"<b class='b4'></b><b class='b3'></b><b class='b2'></b><b class='b1'></b>" +
		"</div>";
	//iframe for NTKO OFFICE activeX

	var TipUtils = {
		//type: loading, error, success
		//pos: object includes left,top,right,bottom info,e.g.{left:10,top:10}
		_showTip: function(type, msg, autoClosed, timeDuration, pos, tipPane) {
			autoClosed = autoClosed || false;
			timeDuration = timeDuration || 3;
			var context = this[0] ? $(this[0]) : $("body");
			tipPane = tipPane || "loading-tip";
			var tipDiv = $("#" + tipPane,context);
			if(!tipDiv[0]) {
				context.append(TipTemplate.replace(/\{0\}/gi,tipPane));
				tipDiv = $("#" + tipPane,context);
			}
			$("#tipContent",tipDiv).removeClass("loading").removeClass("success").removeClass("error");
			$("#tipContent",tipDiv).addClass(type).show();
			$(".tip-text",tipDiv).html(msg);
			if(pos)
				tipDiv.css(pos);
			else
				tipDiv.css({left:'5px',bottom:'10px'});
			tipDiv.show();
			if(this._timer) {
				window.clearTimeout(this._timer);
			}
			if(autoClosed) {
				this._timer = setTimeout(function(){
					TipUtils.closeTip(tipPane);
				}, timeDuration * 1000);
			}else{
				tipDiv.dblclick(function(){
					TipUtils.closeTip(tipPane);
				});
			}
		},
		
		hasShow : function(tipPane){
			var context = this[0] ? $(this[0]) : $("body");
			tipPane = "#" + (tipPane || "loading-tip");
			if($(tipPane,context)[0]
					&& $(tipPane,context).css("display")=="block"){
				return true;
			}else{
				return false;
			}
		},

		showError: function(msg,pos) {
			this._showTip("error", msg, false, null,pos);
		},

		showSuccess: function(msg,pos,autoClose) {
			autoClose = arguments.length == 2 ? true : autoClose;
			this._showTip("success", msg, autoClose, 15, pos);
		},

		showLoading: function(msg,pos) {
			this._showTip("loading", msg, false, null,pos);
		},
		
		showInfo:function(msg,pos,cls,tipID){
			this._showTip(cls || "info", msg, false, null, pos, tipID);
		},

		closeTip: function(tipPane) {
			tipPane = "#" + (tipPane || "loading-tip");
			var tipDiv = !this[0] ? $(tipPane) : $(tipPane,this[0]);
			if(tipDiv[0]) {
				tipDiv.hide();
			}
		}
	};
	$.fn.extend(TipUtils);
	$.extend(true,$.messager,TipUtils);
})(jQuery);

/**
 * disable and enable method
 * dependencies:jQuery1.7.2
 */
(function($){
	$.fn.extend({disable : function(){
		return this.each(function(){
			var self = $(this);
			self.attr("disabled", true);
			var events = self.data("events");
			if(!events || $.isEmptyObject(events)){
				return self;
			}
			var list = new Array();
			for(var i in events){
				$.each(events[i],function(j){
					list.push(i + "_" + j);
					self.data("_E" + i + "_" + j,events[i][j].handler);
				});
				self.unbind(i);
			}
			self.attr("_E",list.join(","));//_E:"click_0,..."
		});
	}, enable : function(){
		return this.each(function(){
			var self = $(this);
			var list = self.attr("_E");
			if(!list)
				return self;
			var events = list.split(",");
			$.each(events,function(i){
				//events[i] click_0,click_1,.....
				self.bind(events[i].replace(/_\d+$/,""),self.data("_E" + events[i]));
				self.removeData("_E" + events[i]);
			});
			self.removeAttr("disabled").removeAttr("_E");
		});
	}});
})(jQuery);
/**
 * Array methods extend
 */
Array.prototype.remove = function(i){
	return this.splice(i,1)[0];
};
Array.prototype.removeAll = function(){
	var o = this;
	while(this.length>0){
		o.pop();
	}
};
Array.prototype.clone = function(){
	var o = this;
	var result = [];
	for(var i = 0;i<o.length;i++){
		result.push(o[i]);
	}
	return result;
};
Array.prototype.contain = function(obj,key){
	return this.indexOf(obj,key)!=-1;
};
Array.prototype.get = function(target,multi){
	if(!target)
		return null;
	var arr = this;
	var result = new Array;
	var value = typeof(target) == "string"?target:target.value;
	var key = (typeof(target) == "object" && target.key)?target.key:null;
	for(var i = 0;i<arr.length;i++){
		var v = key == null?arr[i]:arr[i][key];
		if(v == value){
			if(!multi){
				return arr[i];
			}else{
				result.push(arr[i]);
			}
		}
	}
	return multi ? result : null;
};
Array.prototype.indexOf = function(obj,key){
	var arr = this;
	for(var i = 0;i<arr.length;i++){
		if(!key){
			if(arr[i] == obj)
			   return i;
		}else{
		    if(arr[i][key] == obj[key])
			   return i;
		}
	}
	return -1;
};
Array.prototype.swap = function(src,dest){
	var arr = this;
	var tmp = arr[dest];
	arr[dest] = arr[src];
	arr[src] = tmp;
};

//增加数组的去重方法
Array.prototype.unique = function(){
	var n = {},r=[]; //n为hash表，r为临时数组
	for(var i = 0; i < this.length; i++) //遍历当前数组
	{
		if (!n[this[i]]) //如果hash表中没有当前项
		{
			n[this[i]] = true; //存入hash表
			r.push(this[i]); //把当前数组的当前项push到临时数组里面
		}
	}
	return r;
};
// 增加查找数组中相同元素，仅测试用于字符与数字
Array.prototype.getSameData = function(){
	var n = {},r=[],m=[]; //n为hash表，r为临时数组,m为临时数组，存放相同的元素
	for(var i = 0; i < this.length; i++) //遍历当前数组
	{
		if (!n[this[i]]) //如果hash表中没有当前项
		{
			n[this[i]] = true; //存入hash表
			r.push(this[i]); //把当前数组的当前项push到临时数组里面
		}else{
			m.push(this[i]);
		}
	}
	return m;
};


