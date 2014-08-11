//jQuery essential,jQuery will not be recognized when jQuery.noConflict used
jQuery.extend(jQuery.fn.datagrid.defaults.editors, {   
    label: {   
        init: function(container, options){  
            var input = jQuery('<span>').appendTo(container);   
            return input;   
        },   
        getValue: function(target){   
            return jQuery(target).html();   
        },   
        setValue: function(target, value){   
            jQuery(target).html(value);   
        },   
        resize: function(target, width){   
            var input = jQuery(target);   
            if (jQuery.boxModel == true){   
                input.width(width - (input.outerWidth() - input.width()));   
            } else {   
                input.width(width);   
            }   
        }   
    }
});

jQuery.extend(jQuery.fn.validatebox.defaults.rules, {  
    regexp: {  
        validator: function(value, param){  
        	var result = true;
        	try{
        		result =  new RegExp(param[0],"ig").test(value);
        		if(param[1])
        			this.message = param[1];
        	}catch(e){}
        	return result;
        },  
        message: '您的输入不符合格式要求！'  
    },
    fn:{
    	validator: function(value, param){  
    		var result  = true;
            try{
            	eval('var fn = '+param[0]);
            	result =  fn(value);
            	if(param[1])
        			this.message = param[1];
            }catch(e){}
            return result;
        },  
        message: '您的输入不符合格式要求！'
    },
    remote:{
    	validator:function(value,param){
    		var data={};
    		data[param[1]]=value;
    		try{
    			if(param[2]){
    				this.message = param[2];
    			}
    			var response = jQuery.ajax({url:param[0],dataType:"json",data:data,async:false,cache:false,type:"post"}).responseText;
    			return response == "true";
    		}catch(e){
    			
    		}
    		return true;
    	},
    	message:"您的输入不符合格式要求！"}
});     

(function($){
	$.fn.treegrid.methods.removeNode = function(jq,id){
		var removeNode = null;
		try{
			$(jq[0]).treegrid("remove",id);
			var p = $(jq[0]).treegrid("getParent",id);
			var opts = $(jq[0]).treegrid("options");
			var idField = opts.idField;
			var cc = p ? p.children : $(jq[0]).treegrid("getData");
			for (var i = 0; i < cc.length; i++) {
				if (cc[i][opts.idField] == id) {
					removeNode = cc.splice(i, 1);
					break;
				}
			}
		}catch(e){}
		return removeNode;
	}
})(jQuery);

function createDialog(title, content, buttons){
	var win = jQuery('<div class="messager-body"></div>').appendTo('body');
	win.append(content);
	if (buttons){
		var tb = jQuery('<div class="messager-button"></div>').appendTo(win);
		for(var label in buttons){
			jQuery('<a></a>').attr('href', 'javascript:void(0)').text(label)
						.css('margin-left', 10)
						.bind('click', eval(buttons[label]))
						.appendTo(tb).linkbutton();
		}
	}
	win.window({
		title: title,
		width: 300,
		height: 'auto',
		modal: true,
		collapsible: false,
		minimizable: false,
		maximizable: false,
		resizable: false,
		onClose: function(){
			setTimeout(function(){
				win.window('destroy');
			}, 100);
		}
	});
	return win;
}


jQuery.extend(true,jQuery.messager,{
	prompt:function(_1f8, msg, fn) {
		var _1f9 = "<div class=\"messager-icon messager-question\"></div>" + "<div>" + msg + "</div>" + "<br/>"
				+ "<input class=\"messager-input\" type=\"text\"/>" + "<div style=\"clear:both;\"/>";
		var _1fa = {};
		_1fa[$.messager.defaults.ok] = function() {
			win.window("close");
			if (fn) {
				fn($(".messager-input", win).val());
				return false;
			}
		};
		_1fa[$.messager.defaults.cancel] = function() {
			win.window("close");
			if (fn) {
				fn();
				return false;
			}
		};
		var win = createDialog(_1f8, _1f9, _1fa);
		// 增加回车的处理.DYB 2012-10-26
		win.children("input.messager-input").focus().keydown(function(event) {
			if (event.keyCode == 13) {
				win.window("close");
				if (fn) {
					fn($(".messager-input", win).val());
					return false;
				}
			}
		});
	},
	confirm : function(_1f5, msg, fn) {
		function _1ec(_1ed, _1ee, _1ef) {
			var win = jQuery("<div class=\"messager-body\"></div>").appendTo("body");
			win.append(_1ee);
			if (_1ef) {
				var tb = jQuery("<div class=\"messager-button\"></div>")
						.appendTo(win);
				for (var _1f0 in _1ef) {
					jQuery("<a></a>").attr("href", "javascript:void(0)").text(_1f0)
							.css("margin-left", 10).bind("click",
									eval(_1ef[_1f0])).appendTo(tb).linkbutton();
				}
			}
			win.window({
						title : _1ed,
						noheader : (_1ed ? false : true),
						width : 300,
						height : "auto",
						modal : true,
						collapsible : false,
						minimizable : false,
						maximizable : false,
						resizable : false,
						onClose : function() {
							setTimeout(function() {
										win.window("destroy");
									}, 100);
						}
					});
			win.window("window").addClass("messager-window");
			win.children("div.messager-button").children("a:first").focus();
			return win;
		};
		
		var _1f6 = "<div class=\"messager-icon messager-question\"></div>"
				+ "<div>"
				+ msg
				+ "</div>"
				+ "<div style=\"clear:both;\"/>";
		var _1f7 = {};
		_1f7[jQuery.messager.defaults.ok] = function() {
			win.window("options").onClose = function(){}
			win.window("close");
			if (fn) {
				fn(true);
				return false;
			}
		};
		_1f7[jQuery.messager.defaults.cancel] = function() {
			win.window("options").onClose = function(){}
			win.window("close");
			if (fn) {
				fn(false);
				return false;
			}
		};
		var win = _1ec(_1f5, _1f6, _1f7);
		win.window({onClose:function(){
			if(fn){
				fn(false);
				return false;
			}
		}})
	}
});

jQuery.extend(jQuery.fn.datagrid.methods, {  
    addToolbarItem: function(jq, items){  
        return jq.each(function(){  
            var toolbar = jQuery(this).parent().prev("div.datagrid-toolbar");
            for(var i = 0;i<items.length;i++){
                var item = items[i];
                if(item === "-"){
                    toolbar.append('<div class="datagrid-btn-separator"></div>');
                }else{
                    var btn=jQuery("<a href=\"javascript:void(0)\"></a>");
                    btn[0].onclick=eval(item.handler||function(){});
                    btn.css("float","left").appendTo(toolbar).linkbutton(jQuery.extend({},item,{plain:true}));
                }
            }
            toolbar = null;
        });  
    },
    removeToolbarItem: function(jq, param){  
        return jq.each(function(){  
            var btns = jQuery(this).parent().prev("div.datagrid-toolbar").find("a");
            var cbtn = null;
            if(typeof param == "number"){
                cbtn = btns.eq(param);
            }else if(typeof param == "string"){
                var text = null;
                btns.each(function(){
                    text = jQuery(this).data().linkbutton.options.text;
                    if(text == param){
                        cbtn = jQuery(this);
                        text = null;
                        return;
                    }
                });
            } 
            if(cbtn){
                var prev = cbtn.prev()[0];
                var next = cbtn.next()[0];
                if(prev && next && prev.nodeName == "DIV" && prev.nodeName == next.nodeName){
                    jQuery(prev).remove();
                }else if(next && next.nodeName == "DIV"){
                    jQuery(next).remove();
                }else if(prev && prev.nodeName == "DIV"){
                    jQuery(prev).remove();
                }
                cbtn.remove();    
                cbtn= null;
            }                        
        });  
    },
    disabledToolbarItem: function(jq, param){ 
        return jq.each(function(){
          var btns = jQuery(this).parent().prev("div.datagrid-toolbar").find("a");
            var cbtn = null;
            if(typeof param == "number"){
                cbtn = btns.eq(param);
            }else if(typeof param == "string"){
                var text = null;
                btns.each(function(){
                    text = jQuery(this).data().linkbutton.options.text;
                    if(text == param){
                        cbtn = jQuery(this);
                        text = null;
                        return;
                    }
                });
            } 
            if(cbtn){
                var prev = cbtn.prev()[0];
                var next = cbtn.next()[0];
                if(prev && next && prev.nodeName == "DIV" && prev.nodeName == next.nodeName){
                    jQuery(prev).attr('disabled','disabled');
                }else if(next && next.nodeName == "DIV"){
                    jQuery(next).attr('disabled','disabled');
                }else if(prev && prev.nodeName == "DIV"){
                    jQuery(prev).attr('disabled','disabled');
                }
                cbtn.linkbutton({'disabled':true});
                cbtn= null;
            }                        
        });  
    },
    enabledToolbarItem: function(jq, param){  
        return jq.each(function(){  
            var btns = jQuery(this).parent().prev("div.datagrid-toolbar").find("a");
            var cbtn = null;
            if(typeof param == "number"){
                cbtn = btns.eq(param);
            }else if(typeof param == "string"){
                var text = null;
                btns.each(function(){
                    text = jQuery(this).data().linkbutton.options.text;
                    if(text == param){
                        cbtn = jQuery(this);
                        text = null;
                        return;
                    }
                });
            } 
            if(cbtn){
                var prev = cbtn.prev()[0];
                var next = cbtn.next()[0];
                if(prev && next && prev.nodeName == "DIV" && prev.nodeName == next.nodeName){
                    jQuery(prev).removeAttr('disabled');
                }else if(next && next.nodeName == "DIV"){
                    jQuery(next).removeAttr('disabled');
                }else if(prev && prev.nodeName == "DIV"){
                    jQuery(prev).removeAttr('disabled');
                }
                cbtn.linkbutton({'disabled':false});
                cbtn= null;
            }                        
        });  
    }  
});
