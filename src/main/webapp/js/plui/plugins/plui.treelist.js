/**
 * comborole - plui
 * 
 * Dependencies:
 *   datagrid
 *   tree
 *   layout
 */
(function($){
	
	$.parser.plugins.push('treelist');
	
	/**
	 * create the combodept component.
	 */
	function create(target){
		var opts = $.data(target, 'treelist').options;
		var tree = $.data(target, 'treelist').tree;
		var datagrid = $.data(target, 'treelist').datagrid;
		var layout = $.data(target, 'treelist').layout;
		
		$(target).addClass('treelist-f');
		
		var outerLayout = $(target);
		
		//�����ж���Ϊ���ж�����Ƿ��Ѿ����ɹ���������ɹ����Ͳ���Ҫ��Ŀ���ǩ�������Ԫ�ر�ǩ�ˡ�
		//������Ҫ��Ŀ���ǩ����Ӵ���layout�����岼�֣���datagrid���ұ��б���tree����������ı�ǩ
		if (!layout){
			var width = opts.leftWidth || 500;
			$('<div data-options="region:\'west\'" style="width:'+ width +'px;" />').appendTo(outerLayout);
			$('<div data-options="region:\'center\'"/>').appendTo(outerLayout);					
		}						
		//���ɲ���
		outerLayout.layout($.extend({},opts.layout,{}));
		
		if (!tree)
			tree = $('<ul/>').appendTo(outerLayout.layout('panel', 'west'));
		if (!datagrid)		
			datagrid = $('<table></table>').appendTo(outerLayout.layout('panel', 'center'));		
		
		var params = opts.datagrid.queryParams || {};		
		//���queryParams��û������idField�Ķ�Ӧ����ֵ������datagrid�ĳ�ʼ��̨���������queryParams������ֵΪ-10000�����ڵ�����ֵ���ݽ�ȥ���Ӷ��õ������ݱ�� 
		if (!opts.tree.idField in params)
			params[opts.tree.idField] = -10000;		
		//�������ݱ��
		datagrid.datagrid($.extend({},opts.datagrid,{
			queryParams: params
		}));	
		
		tree.tree($.extend({}, opts.tree,{
			onClick:function(node){
				var params = opts.datagrid.queryParams || {};
				//������ڵ�ʱ��ʱˢ��datagrid�Ĳ�ѯ���ݡ������ڵ��id��Ϊ��ѯ������
				params[opts.tree.idField] = node.id;	
				//������ڵ�ص������ڴ�ʱ��Ӧ
				opts.tree.onClick(node);
				//ˢ�����ݱ��
				$(datagrid).datagrid({
					queryParams: params
				});
			}
		}));
		
		$.data(target, 'treelist').layout = layout;
		$.data(target, 'treelist').datagrid = datagrid;
		$.data(target, 'treelist').tree = tree;
		
	}
	
	$.fn.treelist = function(options, param){
		if (typeof options == 'string'){
			var method = $.fn.treelist.methods[options];			
			if (method)
				//������subû��ֵʱ������treelist�ķ���
				return method(this, param);			
			else 				
				throw new Error("The method \""+ method +"\" does not exist!");			
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'treelist');
			if (state){
				$.extend(state.options, options);
			} else {
				var state = $.data(this, 'treelist', {
					options: $.extend(true, {}, $.fn.treelist.defaults, $.fn.treelist.parseOptions(this), options)
				});
			}
			create(this);
		});
	};
	
	$.fn.treelist.methods = {
		layout: function (jq){
			return $.data(jq[0], 'treelist').layout;
		},
		tree: function(jq){
			return $.data(jq[0], 'treelist').tree;			
		},
		datagrid: function(jq){
			return $.data(jq[0], 'treelist').datagrid;			
		},
		options: function(jq){
			$.data(jq[0], 'treelist').options;
		}
	};
	
	$.fn.treelist.parseOptions = function(target){
		return $.parser.parseOptions(target);
	};
	
	$.fn.treelist.defaults = $.extend(true,{},{
		layout: $.fn.layout.defaults,
		datagrid: $.fn.datagrid.defaults, 
		tree: $.fn.tree.defaults
	}, {
		
	});
})(jQuery);