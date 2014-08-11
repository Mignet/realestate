//联想输入类
var AjaxTip={
	createTip:function(){
		var o=document.createElement("div");
		o.id="tipCon";
		o.className="ajaxTip_con";
		o.onmouseover=function(){
			AjaxTip.isClick=true;
		}
		o.onmouseout=function(){
			AjaxTip.isClick=false;
		}
		document.body.appendChild(o);
	},
	openTip:function(){
		var tipCon=$E("tipCon");
		tipCon.style.left=(this.left==void(0)?$(this.element).offset().left:this.left)+"px";
		tipCon.style.top=(this.top==void(0)?$(this.element).offset().top+$(this.element).outerHeight():this.top)+"px";
		tipCon.style.width=(this.width==void(0)?$(this.element).outerWidth()-2:this.width)+"px";
		tipCon.style.display="block";
		this.isOpen=true;
	},
	setFocus:function(){
		var tip=AjaxTip;
		if(!$E("tipCon")){
			tip.createTip();
		}
		eval('var options={'+this.getAttribute('data-options')+'}');
		tip.element=this;
		tip.onEnter=options.onEnter;//在输入框内的回车事件
		tip.defaultFN=options.callback;//获取值后自定义回调函数
		tip.viewAttr=options.view;//定义输入框显示的属性
		tip.url=options.url;//请求的URL
		tip.data=options.data;//传入后台的参数JSON格式 如：{id:1,name:'aaa',pageSize:10}
		tip.left=options.left;//提示框的left样式
		tip.top=options.top;//提示框的top样式
		tip.width=options.width;//提示框的宽度
		tip.selectIndex=null;//当前选择的结果索引
		tip.selectE=null;
		tip.isOne=false;//是否是第一次调用
		tip.isOpen=false;//是否已经打开
		tip.isClick=false;//是否点击了提示框
		tip.oldKey="";//上一次的查询关键字
		tip.keyupTimer=null;//请求延时
		if(tip.element.value=="")return;
		$.ajax({
			  type: 'POST',
			  url: tip.url,
			  data: tip.data,
			  dataType: 'json',
			  success: function(data){
				  tip.view(data);
			  }
		});
		if(!tip.isOpen)tip.openTip();
	},
	setKeyDown:function(event){
		var tip=AjaxTip;
		try{
			var e=event||window.event;
			var keycode=e.keyCode?e.keyCode:e.which;
			//alert(keycode);
			
			if(typeof tip.onEnter=='function' && keycode==13){
				tip.onEnter(e);
			}
			if(keycode==38||keycode==40){
				var elems=$E("tipCon").getElementsByTagName('a');
				if(elems.length>0){
					var startText="";
					var x,y,id,typeId=null;
					x=y=id="";
					if(tip.selectIndex!=null&& tip.isOne==false){
						tip.selectE=elems[tip.selectIndex];
						tip.isOne=true;
					}
					if(keycode==40){//下
						if(tip.selectE==null){
							tip.selectE=elems[0];
						}else{
							tip.selectE.className="out";
							tip.selectE=tip.selectE.nextSibling||elems[0];
						}
						
					}else{//上
						if(tip.selectE==null){
						}else{
							tip.selectE.className="out";
							tip.selectE=tip.selectE.previousSibling||elems[elems.length-1];
						}
					}
					tip.selectE.className="over";
					eval('var data_='+tip.selectE.getAttribute('data'));
					tip.sendVal(data_);
				}
			}
		}catch(e){}
	},
	setKeyUp:function(event){
		var tip=AjaxTip;
		var e=event||window.event;
		var keycode=e.keyCode?e.keyCode:e.which;
		if(!tip.isOpen)tip.openTip();
		if((keycode>=48&&keycode<=111)||(keycode>186&&keycode<222)||keycode==8||keycode==32||keycode==46){
			if(tip.element.value=="" ||tip.element.value==tip.oldKey){
				if(tip.element.value==""){
					$E("tipCon").innerHTML="";
				}
				return;		
			}
			$E("tipCon").innerHTML="";
			tip.oldKey=tip.element.value;
			if(tip.keyupTimer){
					clearTimeout(tip.keyupTimer);
					tip.keyupTimer=null;
			}
			this.keyupTimer=setTimeout(function(){
				$.ajax({
						type: 'POST',
						url: tip.url,
						data: tip.data,
						dataType: 'json',
						success: function(data){
							AjaxTip.view(data);
						}
				});
			},300);
		}
	},
	setBlur:function(){
		var tip=AjaxTip;
		if(!$E("tipCon")){
			tip.createTip();
		}
		if(tip.isClick){
			setTimeout(function(){
				$E("tipCon").style.display="none";
				tip.isOpen=false;					
			},200);		
		}else{
			$E("tipCon").style.display="none";
			tip.isOpen=false;	
		}
	},
	view:function(list){
		if(!list)return;
		$E("tipCon").innerHTML="";
		var length=list.length;
		var html=[];
		var count=0;
		for(var i=0;i<length;i++){
			var className="";
			var o=list[i];
			if(o.name==this.element.value && count==0){
				this.sendVal(o);
				this.selectIndex=i;
				className="over";
				count++;
			}
			var elem=document.createElement('a');
			elem.setAttribute('href','javascript:;');
			var data=[];
			for(var te in o){
				data.push(te+':\''+o[te]+'\'');
			}
			elem.innerHTML=o[this.viewAttr];
			elem.setAttribute('data','{'+data.join(',')+'}');
			if(className)elem.className=className;
			elem.onclick=function(event){eval('var data_='+this.getAttribute('data'));AjaxTip.sendVal(data_);};
			$E("tipCon").appendChild(elem);
		}
		this.selectE=null;
		this.isOne=false;
	},
	sendVal:function(o){
		if(!o){
			var data=this.getAttribute('data');
			if(data){
				eval('o='+data);
			}	
		}
		this.element.value=o[this.viewAttr].replace(/<[^>].*?>/g,"");;
		if(typeof this.defaultFN=='function')this.defaultFN(o);
		return false;
	}
}
//鼠标提示类
var MouseTip={
	create:function(){
		var html='<div id="mouseTipBox" class="mosetip_jt_tl" style="width:200px;height:100px;">'+
			'<div class="tline"><div class="lbg"></div><div class="rbg"></div><div class="cbg"></div></div>'+
			'<div class="main__" style="height:90px;">'+
				'<div class="lbg"></div><div class="bgr"></div>'+
				'<div class="content"></div>'+
			'</div>'+
			'<div class="bline"><div class="lbg"></div><div class="rbg"></div><div class="cbg"></div></div>'+
			'<div class="jt"></div>'+
		'</div>';
		$(html).appendTo('body');
	},
	show:function(o,event){
		if($E('mouseTipBox')){
			var data=$(o).attr('data-options');
			if(data){
				eval('data={'+data+'}');
			}else{
				data={width:200,height:100,content:''};
			}
			var width=data.width||200;
			var height=data.height||100;
			var _left=$(o).offset().left;
			var _top=$(o).offset().top+10;
			var _width=$(o).width();
			var _height=$(o).height();
			var left=_left,top=_top+_height;//默认情况在元素的左下方提示
			var box=$('#mouseTipBox');
			var _w=$(window).width();
			var _h=$(window).height();
			box.css('width',width).css('height',height);
			box.find('.main__').css('height',height-10);
			//jt_bl jt_br  jt_tl jt_tr
			var xcss='',ycss='';
			if(left+width>_w){
				box.css('left','auto').css('right',_w-(_left+_width));
				xcss='r'
			}else{
				box.css('left',left).css('right','auto');
				xcss='l';
			}
			if(top+height>_h){
				box.css('top','auto').css('bottom',_h-(_top-_height-5));
				ycss='b';
			}else{
				box.css('top',top).css('bottom','auto');
				ycss='t';
			}
			$E('mouseTipBox').className='mosetip_jt_'+ycss+xcss;
			box.show();
			box.find('.main').css('height',data.height-10||190);
			box.find('.content').html(data.content);
		}else{
			this.create();
			this.show(o,event);
		}
	}
};
function ajaxTip(o){
	if(typeof o=='string')o=$E(o);
	$(o).attr('autoComplete','off').focus(AjaxTip.setFocus).keydown(AjaxTip.setKeyDown).keyup(AjaxTip.setKeyUp).blur(AjaxTip.setBlur);
}
function mouseTip(o){
	if(typeof o=='string')o=$E(o);
	$(o).hover(function(event){
		MouseTip.show(this,event);
	},function(event){
		$('#mouseTipBox').hide();
	});
}

/*========结果以弹出框上传开始=========*/


/*=======结果以弹出上传结束======*/

$(document).ready(function() {
	$('.plui-ajaxTip,.plui-mousetip').each(function(index, elem) {
        if(elem.className.indexOf('plui-ajaxTip')>-1)ajaxTip(elem);
		else if(elem.className.indexOf('plui-cascade')>-1)$(elem).click(CascadePop);
		else mouseTip(elem);
    });
});

//扩展datagrid的editors，增加combogrid选项
/*
$(function(){
	$.extend($.fn.datagrid.defaults.editors, {
		combogrid:{
			init: function(container, options){
				var combo = $('<input type="text">').appendTo(container);
				combo.combogrid(options || {});
				return combo;
			},
			destroy: function(target){
				$(target).combogrid('destroy');
			},
			getValue: function(target){
				return $(target).combogrid('getValue');
			},
			setValue: function(target, value){
				$(target).combogrid('setValue', value);
			},
			resize: function(target, width){
				$(target).combogrid('resize', width)
			}
		}
	});
});*/
/**
 * 功能扩展editor:slider
 * slider[0-100]
 * v方向设置width h方向设置height
 */
$(function(){
	$.extend($.fn.datagrid.defaults.editors, {
	    slider: {//名称
	        init: function(container, options){
	            var input = $('<input class="plui-slider">').appendTo(container);
	            input.slider(options);
	            return input;
			},
			getValue: function(target){
	//			return ($(target).slider('getValue')*10/1000).toFixed(1);
	//			return ($(target).slider('getValue')/100).toFixed(1)*100;
				return $(target).slider('getValue');
			},
			setValue: function(target, value){
	//			$(target).slider('setValue', value*100);
				$(target).slider('setValue', value);
			},
			resize: function(target, width){
	//			$(target)._outerWidth(width+40);
				$(target).slider('resize', {'width':(width-10)});
			}
	    }
	});
});
/**********************************************************************************
*函数名称: 限制输入长度方法
*功能说明: 限制表单控件长度  
*参数说明: opt(当前控件  传this)  length(限制的长度)
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-10
*修改历史: 
***********************************************************************************/
function limitLength(opt,limitLength){
	var value = opt.value;
    if(value.length>limitLength){
    	opt.value=opt.value.substr(0, limitLength);
    }
}

/**********************************************************************************
*函数名称: 对象比较方法
*功能说明: 比较两个对象 数组  。。
*参数说明: 要比较的对象
*返 回 值: true or false
*函数作者: Joyon
*创建日期: 2014-03-10
*修改历史: 
***********************************************************************************/
function equal(objA, objB)

{
    if (typeof arguments[0] != typeof arguments[1])
        return false;

    //数组
    if (arguments[0] instanceof Array)
    {
        if (arguments[0].length != arguments[1].length)
            return false;
        
        var allElementsEqual = true;
        for (var i = 0; i < arguments[0].length; ++i)
        {
            if (typeof arguments[0][i] != typeof arguments[1][i])
                return false;

            if (typeof arguments[0][i] == 'number' && typeof arguments[1][i] == 'number')
                allElementsEqual = (arguments[0][i] == arguments[1][i]);
            else
                allElementsEqual = arguments.callee(arguments[0][i], arguments[1][i]);            //递归判断对象是否相等                
        }
        return allElementsEqual;
    }
    
    //对象
    if (arguments[0] instanceof Object && arguments[1] instanceof Object)
    {
        var result = true;
        var attributeLengthA = 0, attributeLengthB = 0;
        for (var o in arguments[0])
        {
            //判断两个对象的同名属性是否相同（数字或字符串）
            if (typeof arguments[0][o] == 'number' || typeof arguments[0][o] == 'string')
                result = eval("arguments[0]['" + o + "'] == arguments[1]['" + o + "']");
            	if(!result){
            		return false;
            	}
            else {
                //如果对象的属性也是对象，则递归判断两个对象的同名属性
                //if (!arguments.callee(arguments[0][o], arguments[1][o]))
                if (!arguments.callee(eval("arguments[0]['" + o + "']"), eval("arguments[1]['" + o + "']")))
                {
                    result = false;
                    return result;
                }
            }
            ++attributeLengthA;
        }
        
        for (var o in arguments[1]) {
            ++attributeLengthB;
        }
        
        //如果两个对象的属性数目不等，则两个对象也不等
        if (attributeLengthA != attributeLengthB)
            result = false;
        return result;
    }
    return arguments[0] == arguments[1];
}

/**********************************************************************************
*函数名称: 只能输入数字
*功能说明: 只能输入数字和小数点 使用方法 屏蔽输入法 style="ime-mode:disabled" onkeypress="return isNum(event)" 
*参数说明: 事件
*返 回 值: 数字 和小数点
*函数作者: Joyon
*创建日期: 2014-03-10
*修改历史: 
***********************************************************************************/
function isNum(e) {
    var k = window.event ? e.keyCode : e.which;
    if (((k >= 48) && (k <= 57)) || k == 8 || k == 0 || k==46) {
    } else {
        if (window.event) {
            window.event.returnValue = false;
        }
        else {
            e.preventDefault(); //for firefox 
        }
    }
  
} 

/**********************************************************************************
*函数名称: 只能输入数字
*功能说明: 只能输入数字和小数点 使用方法  onkeyup = "check_num(this)"
*参数说明: 事件
*返 回 值: 数字 和小数点
*函数作者: Joyon
*创建日期: 2014-03-10
*修改历史: 
***********************************************************************************/
function check_num(opt){
    //alert(opt.value);
        opt.value.substring(0,1)=='0'?opt.value='0':opt.value=opt.value;
        if(!opt.value.match(/^[\+\-]?\d*?\.?\d*?$/))
            opt.value=opt.t_value;
        else opt.t_value=opt.value;
        if(opt.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))
            opt.o_value=opt.value
}

/**********************************************************************************
*函数名称: 将当前表单转为json对象  需要引用jquery
*功能说明: 获得当前表单object
*参数说明: 
*返 回 值: 表单序列化对象
*函数作者: Joyon
*创建日期: 2014-03-10
*修改历史: 
***********************************************************************************/
(function($){  
    $.fn.serializeJson=function(){  
        var serializeObj={};  
        var array=this.serializeArray();  
        var str=this.serialize();  
        $(array).each(function(){  
            if(serializeObj[this.name]){  
                if($.isArray(serializeObj[this.name])){  
                    serializeObj[this.name].push(this.value);  
                }else{  
                    serializeObj[this.name]=[serializeObj[this.name],this.value];  
                }  
            }else{  
                serializeObj[this.name]=this.value;   
            }  
        });  
        return serializeObj;  
    };  
})(jQuery);  