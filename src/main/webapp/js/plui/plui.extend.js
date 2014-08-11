//����������
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
		tip.onEnter=options.onEnter;//��������ڵĻس��¼�
		tip.defaultFN=options.callback;//��ȡֵ���Զ���ص�����
		tip.viewAttr=options.view;//�����������ʾ������
		tip.url=options.url;//�����URL
		tip.data=options.data;//�����̨�Ĳ���JSON��ʽ �磺{id:1,name:'aaa',pageSize:10}
		tip.left=options.left;//��ʾ���left��ʽ
		tip.top=options.top;//��ʾ���top��ʽ
		tip.width=options.width;//��ʾ��Ŀ��
		tip.selectIndex=null;//��ǰѡ��Ľ������
		tip.selectE=null;
		tip.isOne=false;//�Ƿ��ǵ�һ�ε���
		tip.isOpen=false;//�Ƿ��Ѿ���
		tip.isClick=false;//�Ƿ�������ʾ��
		tip.oldKey="";//��һ�εĲ�ѯ�ؼ���
		tip.keyupTimer=null;//������ʱ
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
					if(keycode==40){//��
						if(tip.selectE==null){
							tip.selectE=elems[0];
						}else{
							tip.selectE.className="out";
							tip.selectE=tip.selectE.nextSibling||elems[0];
						}
						
					}else{//��
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
//�����ʾ��
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
			var left=_left,top=_top+_height;//Ĭ�������Ԫ�ص����·���ʾ
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

/*========����Ե������ϴ���ʼ=========*/


/*=======����Ե����ϴ�����======*/

$(document).ready(function() {
	$('.plui-ajaxTip,.plui-mousetip').each(function(index, elem) {
        if(elem.className.indexOf('plui-ajaxTip')>-1)ajaxTip(elem);
		else if(elem.className.indexOf('plui-cascade')>-1)$(elem).click(CascadePop);
		else mouseTip(elem);
    });
});

//��չdatagrid��editors������combogridѡ��
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
 * ������չeditor:slider
 * slider[0-100]
 * v��������width h��������height
 */
$(function(){
	$.extend($.fn.datagrid.defaults.editors, {
	    slider: {//����
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
*��������: �������볤�ȷ���
*����˵��: ���Ʊ��ؼ�����  
*����˵��: opt(��ǰ�ؼ�  ��this)  length(���Ƶĳ���)
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-10
*�޸���ʷ: 
***********************************************************************************/
function limitLength(opt,limitLength){
	var value = opt.value;
    if(value.length>limitLength){
    	opt.value=opt.value.substr(0, limitLength);
    }
}

/**********************************************************************************
*��������: ����ȽϷ���
*����˵��: �Ƚ��������� ����  ����
*����˵��: Ҫ�ȽϵĶ���
*�� �� ֵ: true or false
*��������: Joyon
*��������: 2014-03-10
*�޸���ʷ: 
***********************************************************************************/
function equal(objA, objB)

{
    if (typeof arguments[0] != typeof arguments[1])
        return false;

    //����
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
                allElementsEqual = arguments.callee(arguments[0][i], arguments[1][i]);            //�ݹ��ж϶����Ƿ����                
        }
        return allElementsEqual;
    }
    
    //����
    if (arguments[0] instanceof Object && arguments[1] instanceof Object)
    {
        var result = true;
        var attributeLengthA = 0, attributeLengthB = 0;
        for (var o in arguments[0])
        {
            //�ж����������ͬ�������Ƿ���ͬ�����ֻ��ַ�����
            if (typeof arguments[0][o] == 'number' || typeof arguments[0][o] == 'string')
                result = eval("arguments[0]['" + o + "'] == arguments[1]['" + o + "']");
            	if(!result){
            		return false;
            	}
            else {
                //������������Ҳ�Ƕ�����ݹ��ж����������ͬ������
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
        
        //������������������Ŀ���ȣ�����������Ҳ����
        if (attributeLengthA != attributeLengthB)
            result = false;
        return result;
    }
    return arguments[0] == arguments[1];
}

/**********************************************************************************
*��������: ֻ����������
*����˵��: ֻ���������ֺ�С���� ʹ�÷��� �������뷨 style="ime-mode:disabled" onkeypress="return isNum(event)" 
*����˵��: �¼�
*�� �� ֵ: ���� ��С����
*��������: Joyon
*��������: 2014-03-10
*�޸���ʷ: 
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
*��������: ֻ����������
*����˵��: ֻ���������ֺ�С���� ʹ�÷���  onkeyup = "check_num(this)"
*����˵��: �¼�
*�� �� ֵ: ���� ��С����
*��������: Joyon
*��������: 2014-03-10
*�޸���ʷ: 
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
*��������: ����ǰ��תΪjson����  ��Ҫ����jquery
*����˵��: ��õ�ǰ��object
*����˵��: 
*�� �� ֵ: �����л�����
*��������: Joyon
*��������: 2014-03-10
*�޸���ʷ: 
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