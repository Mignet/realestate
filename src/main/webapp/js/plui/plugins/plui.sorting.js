/**
 * comborole - plui
 * 
 * Dependencies:
 *   datagrid
 *   tree
 *   layout
 */
(function($){
	
	$.parser.plugins.push('sorting');
	
	/**
	 * create the combodept component.
	 */
	function create(target){
		window.preRow = null;
		var opts = $.data(target, 'sorting').options;			
		var datagrid = $.data(target, 'sorting').datagrid;
		var win = $.data(target, 'sorting').win;
		
		$(target).addClass('sorting-f');
		
		var outer = $(target);
		
		if (!win)
			outer.window(opts);		
		
		//以下判断是为了判断组件是否已经生成过。如果生成过，就不需要向目标标签中添加子元素标签了。
		//否则，需要向目标标签中添加代表layout（整体布局）、datagrid（右边列表）、tree（左边树）的标签						
				
		if (!datagrid)		
			datagrid = $('<table></table>').appendTo(outer);		
	
		//生成数据表格
		datagrid.datagrid($.extend({}, opts.datagrid, {
			fit:true,
			//表格每列宽度自动适应表格总宽度
			fitColumns: true,
			idField: opts.idField,
			singleSelect: false,
			rownumbers: true,
			pagination:false,
			striped:true,
			onClickRow: function(index, row){
				var preIndex = $.data(datagrid, 'preIndex');
				if (typeof preIndex == 'number'){					
					datagrid.datagrid('unselectAll');
					datagrid.datagrid('moveRow', {index: preIndex, dest: index+1});														
					$.data(datagrid, 'preIndex', null);
					$('#moveup').linkbutton('disable');
					$('#movedown').linkbutton('disable');
				} else{
					$.data(datagrid, 'preIndex', index);
					$('#moveup').linkbutton('enable');
					$('#movedown').linkbutton('enable');
				}
			},			
			toolbar: [{
				id: 'moveup',
				iconCls: 'icon-accordion_up',
				text: '上移',
				handler: function(){
					var index = $.data(datagrid, 'preIndex');
					if (index>0){
						datagrid.datagrid('moveRow', {index: index, dest: index-1});
						datagrid.datagrid('selectRow',index-1);
						$.data(datagrid, 'preIndex', index-1);
					}
				}					
			},'-',{
				id: 'movedown',
				iconCls: 'icon-accordion_down',
				text: '下移',
				handler: function(){
					var index = $.data(datagrid, 'preIndex');
					if (index<datagrid.datagrid('getRows').length-1){
						datagrid.datagrid('moveRow', {index: index, dest: index+2});
						datagrid.datagrid('selectRow',index+1);
						$.data(datagrid, 'preIndex', index+1);
					}
				}					
			},'-',{
				iconCls: 'icon-save',
				text: '保存',
				handler: function(){
					var rows = datagrid.datagrid('getRows');
					var idSet = [];
					var orderSet =[];
					for (var i=0; i<rows.length; i++){
						idSet.push(rows[i][opts.idField]);
						orderSet.push(i);
					}
					var queryParam = {};
					queryParam[opts.idsField]=idSet.toString();
					queryParam[opts.turnsField]=orderSet.toString();
					$.ajax({
						url: opts.sortUrl,
						data: queryParam,
						dataType: 'json',
						success: function(){
							outer.window('close'); 
							opts.success();					
						}
					});
				}
			},'-',{
				text: '重置',
				iconCls: 'icon-reload',
				handler: function(){
					datagrid.datagrid('reload');
				}
			},'->',{
				iconCls: 'icon-question',
				handler: function(){
					top.$.messager.alert('使用提示','1. 请点选一个需要排序的选项<br>2. 请通过“上移”、“下移”按钮移动选项进行排序。或者<br>3. 请再点选另一项。之前点选的一项会排在此项后面');
				}
			}]
		}));	
		
		$('#moveup').linkbutton('disable');
		$('#movedown').linkbutton('disable');
				
		$.data(target, 'sorting').datagrid = datagrid;	
	}
	
	$.fn.sorting = function(options, param){
		if (typeof options == 'string'){
			var method = $.fn.sorting.methods[options];			
			if (method)
				//当参数sub没赋值时，调用sorting的方法
				return method(this, param);			
			else 				
				return this.window(options, param);			
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'sorting');
			if (state){
				$.extend(state.options, options);				
			} else {
				var state = $.data(this, 'sorting', {
					options: $.extend(true, {}, $.fn.sorting.defaults, $.fn.sorting.parseOptions(this), options)
				});
			}
			create(this);
		});
	};
	
	$.fn.sorting.methods = {
		datagrid: function(jq){
			return $.data(jq[0], 'sorting').datagrid;			
		},
		options: function(jq){
			$.data(jq[0], 'sorting').options;
		}
	};
	
	$.fn.sorting.parseOptions = function(target){
		return $.parser.parseOptions(target);
	};
	
	$.fn.sorting.defaults = $.extend(true,{},{
		datagrid: $.extend({}, $.fn.datagrid.defaults, {
			onClickRow: function(){}
		})
	}, {
		resizable: false,
		maximizable: false,
		collapsible: false,
		onClose: function(){}
	});
})(jQuery);

function createSorting(options){
	var sorting = top.$('<div></div>').appendTo(top.$('body'));
	sorting.attr("id",options.id);
	var onClose = options.onClose || function (){};	
	sorting.sorting($.extend({}, options, {
		onClose : function () {
			onClose();
            top.$(this).window('destroy');
        }
	}));	
}