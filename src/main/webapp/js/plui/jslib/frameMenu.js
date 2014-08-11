(function($){
	
	function createMenu(target){
		var opts = $.data(target, 'frameMenu').options;			
		if (opts.data)
			createMenuFromData(target, opts.data);
		else
			$.getJSON(opts.url,function(data){				
				createMenuFromData(target, data);									
			});				
	}
	
	function createMenuFromData(target, data){
		var opts = $.data(target, 'frameMenu').options;	
		$(target).addClass('frameMenu');
		$(target).css('width',opts.width);
		var ul = $('<ul></ul>').appendTo(target);
		for(var i=0;i<data.length;i++){
			var li = $('<li></li>').appendTo(ul);							
			var menudiv = $('<div class="menutitle"></div>').appendTo(li);
			if (data[i].id)
				menudiv.attr('id',data[i].id);
			if(i==0)
				menudiv.addClass('firstMenu');
			var lm = $('<div class="lm"></div>').appendTo(li);
			var lmt = $('<div class="lmt"></div>').appendTo(lm);
			var lmc = $('<div class="lmc"></div>').appendTo(lm);
			var lmb = $('<div class="lmb"></div>').appendTo(lm);
			var subul = $('<ul class="two"></ul>').appendTo(li);
			subul.css('width',opts.width-8);			
			if (data[i].iconCls)
				$('<img src="../images/index/m_'+data[i].iconCls+'.png"/>').appendTo(menudiv);
			$('<span>'+data[i].text+'</span>').appendTo(menudiv);
			if (data[i].children)
				for(var j=0;j<data[i].children.length;j++){
					var subli = $('<li class="twoli"></li>').appendTo(subul);
					var menuse = $('<div class="menuse">').appendTo(subli);
					if(data[i].children[j].children && data[i].children[j].children.length>0){						
						menuse.addClass('menuse2');
						var subsubul = $('<ul class="three"></ul>').appendTo(subli);
						$('<span style="float:left;">'+data[i].children[j].text+'</span>').appendTo(menuse);
						if(data[i].children[j].count && data[i].children[j].count>0)
							$('<span class="count">('+data[i].children[j].count+')</span>').appendTo(menuse);
						$('<div class="sign"></div>').appendTo(menuse);
						for(var k=0;k<data[i].children[j].children.length;k++){
							var node = data[i].children[j].children[k];
							var subsubli = $('<li class="threeli"></li>').appendTo(subsubul);
							var amenu = $('<a href="#">'+ node.text +'</a>').appendTo(subsubli);
							amenu.bind('click',node,function(event){
								$.fn.frameMenu.openMenus[event.data.open_type || opts.open_type](event.data.text, event.data.url, event.data.param || opts.param);
							});
						}											
					}else{
						var node = data[i].children[j];						
						var amenu = $('<a href="#">'+ node.text +'</a>').appendTo(menuse);						
						amenu.bind('click',node,function(event){
							$.fn.frameMenu.openMenus[event.data.open_type || opts.open_type](event.data.text, event.data.url, event.data.param || opts.param);
						});
						if(data[i].children[j].count && data[i].children[j].count>0)
							$('<span class="count">('+data[i].children[j].count+')</span>').appendTo(menuse);
					}	
					var menusp = $('<div class="menusp"></div>').appendTo($('<li class="splitli">').appendTo(subul));
					menusp.css('width',opts.width-20);	
				}
		}		
		
		bindMenuItemEvent(target, opts.menu2Top);
		openMenu('.firstMenu',target, opts.menu2Top);
		if (opts.onLoadSuccess.call(target) == false) return;
	}
	
	function hasthree(data){
		if (data.children)
			for(var z=0;z<data.children.length;z++){
				if(data.children[z].children && data.children[z].children.length > 0){
					return true;
				}
			}
		return false;
	}
	
	function bindMenuItemEvent(target,menu2Top){
		$(target).find('.menutitle').each(function(index,value){
			$(value).bind('click',function(){
				openMenu(value,target,menu2Top);
			 });
		});
		
		$(target).find('.menuse2').each(function(index,value){
			$(value).bind('click',function(){
				openSecond(value,target);
			 });
		});
		/*
		$(target).find('.two a').each(function(index,value){
			$(value).bind('click',function(){
				setColor(value);
			 });
		});
		*/
	}
	
	function setColor(o){
		$('.two').find('a').each(function(index,value){
			$(value).css('color', '#333333');
		});
		$(o).css('color', '#0099cc');
	}
	
	function openMenu(o, target, menu2Top){
		var panel = $(target);
		var pt = $(o).parent();
		
		if(pt.find('.two').css("display")=="block"){
			panel.find('.lm').hide();
			panel.find('.two').hide();
		}else{
			panel.find('.lm').hide();
			panel.find('.two').hide();
			pt.find('.two').show();
			pt.find('.lm').show();
			
			var h=panel.height() - panel.find(".two").length * panel.find(".menutitle").height();
			
			if(pt.find(".two").height()<=h){
				if((pt.find(".two").height()-90) >= 0){
					pt.find(".lmc").height(pt.find(".two").height()-82);					
				}else{
					pt.find(".lmc").height(0);
					//pt.find(".lm").height(97);
				}
			}else{
				pt.find(".lmc").height(h-97);
				pt.find(".two").height(h-15);
			}
			
			pt.find(".two").css("top",$(o).offset().top+60-110);
			pt.find(".two").css("left",$(o).offset().left+3);
		}
	}
	
	function openSecond(o, target){
		var panel = $(target);
		var _h=0;
		var _pt = $(o).parents(".two");
		
		if($(o).next().css("display")=="none"){
			$(o).css("background", "url(../../images/index/bg3.jpg) repeat-x 1px 1px");
			$(o).find(".sign").css("background","url(../../images/index/open.jpg) no-repeat");
			
			$(o).next().css("display","block");
			$(o).parent().height($(o).parent().find(".three li").length * $(o).parent().find(".three li").height() + $(o).height());
			
			_pt.find(".twoli").each(function(index,value){
				_h+=$(value).height();
			});
			if ($.browser.msie){
				_pt.height(_h + _pt.find(".twoli").length*1 + 20);
			}else{
				_pt.height(_h + _pt.find(".twoli").length*1);
			}		
		}else{
			$(o).css("background", "");
			$(o).find(".sign").css("background","url(../../images/index/close.jpg) no-repeat");
			
			$(o).next().css("display","none");
			$(o).parent().height(24);
			_pt.find(".twoli").each(function(index,value){
				_h+=$(value).height();
			});
			if ($.browser.msie){
				_pt.height(_h + _pt.find(".twoli").length * 1 + 20);
			}else{
				_pt.height(_h + _pt.find(".twoli").length * 1);
			}
		}
		
		var h=panel.height() - panel.find(".two").length * panel.find(".menutitle").height();
		
		if(_pt.height()<=h){
			if((_pt.height()-90) >= 0){
				_pt.parent().find(".lmc").height(_pt.height()-85);
			}else{
				_pt.parent().find(".lmc").height(0);
				//_pt.parent().find(".lm").height(97);
			}
		}else{
			_pt.parent().find(".lmc").height(h-97);
			_pt.height(h-16);
		}
	}		
	
	$.fn.frameMenu = function(options, param){
		if (typeof options == 'string'){
			return $.fn.frameMenu.methods[options](this, param);
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'frameMenu');
			if (state){
				$.extend(state.options, options);
			} else {
				state = $.data(this, 'frameMenu', {
					options: $.extend({}, $.fn.frameMenu.defaults, $.fn.frameMenu.parseOptions(this), options)
				});
				createMenu(this);
			}
		});
	};
	
	$.fn.frameMenu.methods = {
		options: function(jq){
			return $.data(jq[0], 'frameMenu').options;
		}
	};
	
	$.fn.frameMenu.parseOptions = function(target){
		return $.extend({}, $.parser.parseOptions(target, ['width','url','param','open_type']));
	};
	
	$.fn.frameMenu.defaults = {
		width:200,
		url:null,
		open_type:'tab',
		param:null,
		onLoadSuccess:function(){}
	};
	
	$.fn.frameMenu.openMenus = {
		window: function(title, url, param){
			window.open(url,title);
		},
		//param为css选择器，选择一个目的地tab组件。根据标题title和链接url显示一个选项卡到b被选中的tab组件。如果同标题的选项卡存在，则选择此选项卡。否则新增。
		tab: function(title, url, param){
			if ($(param).tabs('exists',title)){
				$(param).tabs('select',title);
			} else {
				$(param).tabs('add',{
					title: title,
					type: 'frame',
					href: url,
					height: '100%',
					closable:true
				});
			}
		},
		frame: function(title, url, param){
			$(param).attr('src',url);
		}
	};
	
	$.parser.plugins.push("frameMenu"); 
})(jQuery);

