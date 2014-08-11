/**
 * comborole - plui
 * 
 * Dependencies:
 *   combo
 *   tree
 *   layout
 */
(function($){
	
	$.parser.plugins.push('comborole');
	
	function create(target){
		var opts = $.data(target, 'comborole').options;
		
		$(target).addClass('comborole-f');
		var combotreelist = $(target).combotreelist(opts);
		
		$.data(target, 'comborole', $.data(combotreelist,'combotreelist'));
	}
	
	$.fn.comborole = function(options, param){
		if (typeof options == 'string'){			
			var method = $.fn.comborole.methods[options];
			if (method)
				return method(this, param);
			else
				return this.combotreelist(options, param);			
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'comborole');
			if (state){
				$.extend(state.options, options);
			} else {
				$.data(this, 'comborole', {
					options: $.extend(true, {}, $.fn.comborole.defaults, $.fn.comborole.parseOptions(this), options)
				});
			}
			create(this);
		});
	};
	
	$.fn.comborole.methods = {
	};
	
	$.fn.comborole.parseOptions = function(target){
		return $.parser.parseOptions(target);
	};
	
	$.fn.comborole.defaults = $.extend(true,{},$.fn.treelist.defaults,$.fn.combo.defaults,{
		layout:{
			fit: true,
			west:{
				title: '��Ʒ��'
			},
			center:{
				title: '���ݱ�'
			}
		},
		tree:{
			idField: 'product_id',						
			url : $.pluiplugin.config.productTreeUrl
		},
		datagrid:{
			idField: 'role_id',
			textField: 'role_name',
			fit : true,
			singleSelect : true,
			fitColumns : true,
			url : $.pluiplugin.config.roleByProductUrl,
			pagination : true,
			queryParams : {
				product_id : '-1'
			},
			columns : [[
				{ title : '��ɫ����', field : 'role_name', width : 100 },
				{ title : '��ɫ����', field : 'role_code', width : 100 },
				{ title : '������Ʒ', field : 'product_name', width : 100 },
				{ title : '��ע', field : 'remark', width : 300 }
			]]
		},
		leftWidth: 300,
		width:200,
		panelWidth: 800,
		panelHeight: 500		
	});
	
})(jQuery);