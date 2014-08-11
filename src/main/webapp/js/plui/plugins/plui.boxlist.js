(function($){
	
	function createboxlist(target){
		var opts = $.data(target, 'boxlist').options;
		var html = '';
		//alert(opts.ids)
		if(opts.ids == null){
			html += '<div class="textboxlist"><ul></ul></div>';
		}else{
			var ids = opts.ids.split(',');
			var values = opts.values.split(',');
			
			html += '<div class="textboxlist"><ul>';
			if(ids.length){
				for(var i=0;i<ids.length;i++){
					if(values[i]!= ''){
						if(opts.attr == 'line'){
							html += '<li>';
						}else{
							html += '<li class="li-column">';
						}
						html += '<div class="boxlist" vid='+ids[i]+'>';
						html += values[i]+'<span class="textboxlist-close"></span>';
						html += '</div>';
						html += '</li>';
					}
				}
			}
			html += '</ul></div>';
		}
		
		$(target).html(html);
		
		bindBoxItemEvent(target);
	}
	
	function bindBoxItemEvent(target){
		var opts = $.data(target, 'boxlist').options;
		$(target).find('.boxlist').each(function(index,value){
			$(value).unbind().hover(function(){
				$(value).addClass('boxlist-item-hover');
			}, function(){
				$(value).removeClass('boxlist-item-hover');
			}).bind('click',function(){
				$(target).find('.boxlist').each(function(i,v){
					$(v).removeClass('boxlist-item-selected');
				});
				$(value).addClass('boxlist-item-selected');
			});
		});
		
		$(target).find('.textboxlist-close').each(function(index,value){
			$(value).unbind().hover(function(){
				$(value).addClass('textboxlist-close-hover');
			}, function(){
				$(value).removeClass('textboxlist-close-hover');
			}).bind('click',function(){
				if (opts.onBeforeClose.call(target,$(value).parent()) == false) return;
				$(value).parents('li').remove();
			});
		});
	}
	
	function setValues(target, box){
		var opts = $.data(target, 'boxlist').options;
		var ids = box.ids.toString().split(',');
		var values = box.values.toString().split(',');
		
		var html = '';
		if(ids.length<1)return ;
		for(var i=0;i<ids.length;i++){
			if(opts.attr == 'line'){
				html += '<li>';
			}else{
				html += '<li class="li-column">';
			}
			html += '<div class="boxlist" vid='+ids[i]+'>';
			html += values[i]+'<span class="textboxlist-close"></span>';
			html += '</div>';
			html += '</li>';
		}
		$(target).find('ul').append(html);
		bindBoxItemEvent(target);
	}
	
	function getValues(target){
		var values = [];
		$(target).find('.boxlist').each(function(index,value){
			values.push($(value).attr('vid')+':'+$(value).text());
		});
		return values;
	}
	
	function deleteValues(target, box){
		var opts = $.data(target, 'boxlist').options;
		var ids = box.ids.toString().split(',');
		
		$(target).find('.boxlist').each(function(index,value){
			for(var i=0;i<ids.length;i++){
				if(ids[i] == $(value).attr('vid')){
					$(value).parent().remove();
				}
			}
		});
	};
	
	/**
	 * 清楚所有项
	 * chenlia-2013/09/11
	 */
	function clear(target) {
		$(target).find('.boxlist:parent').remove();
	};
	
	$.fn.boxlist = function(options, param){
		if (typeof options == 'string'){
			return $.fn.boxlist.methods[options](this, param);
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'boxlist');
			if (state){
				$.extend(state.options, options);
			} else {
				state = $.data(this, 'boxlist', {
					options: $.extend({}, $.fn.boxlist.defaults, $.fn.boxlist.parseOptions(this), options)
				});
				createboxlist(this);
			}
		});
	};
	
	$.fn.boxlist.methods = {
		options: function(jq){
			return $.data(jq[0], 'boxlist').options;
		},
		setValues: function(jq,box){
			return jq.each(function(){
				setValues(this, box);
			});
		},
		getValues: function(jq){
			return getValues(jq[0]);
		},
		deleteValues: function(jq, box){
			return jq.each(function(){
				deleteValues(this, box);
			});
		},
		clear: function(jq) {
			return jq.each(function(){
				clear(this);
			});
		}
	};
	
	$.fn.boxlist.parseOptions = function(target){
		return $.extend({}, $.parser.parseOptions(target, ['id','attr','ids','values']));
	};
	
	$.fn.boxlist.defaults = {
		id:null,
		attr:'line',
		ids:null,
		values:null,
		onBeforeClose:function(box){}
	};
	
	$.parser.plugins.push("boxlist");
})(jQuery);