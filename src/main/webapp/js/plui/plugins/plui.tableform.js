/**
 * comborole - plui
 * 
 * Dependencies:
 *   datagrid
 *   tree
 *   layout
 */
(function($){
	
	$.parser.plugins.push('tableform');
	
	/**
	 * create the tableform component.
	 */
	function create(target){
		var opts = $.data(target, 'tableform').options;
		var tableform = $.data(target, 'tableform').tableform;
		
		var outer = $(target);		
		outer.addClass('tableform-f');
		
		//以下判断是为了判断组件是否已经生成过。如果生成过，就不需要向目标标签中添加子元素标签了。
		//否则，需要向目标标签中添加代表table的标签
		if (!tableform){
			tableform = $('<div class="formdiv"></div>').appendTo(outer);			
		}						
		
		if (!opts.fieldset)
			genTable(tableform, target, opts.inputs, opts);
		else 
			for (var i=0; i<opts.sets.length;i++){
				var field = $('<fieldset><legend>' + opts.sets[i].legend + '</legend></fieldset>').appendTo(tableform);	
				genTable(field, target, opts.sets[i].inputs, opts);
			}		
		
		$(target).form(opts);
		
		$.data(target, 'tableform').tableform = tableform;
		
		function genTable(field, target2, formControlOptions, opts2){
			var table = $('<table></table>').appendTo(field);
			table.addClass('formtable');
			var numInRow = 0;
			var odd = true;
			var row;
			for (var i=0;i<formControlOptions.length;i++){
				var option = formControlOptions[i];
				if (!option.hidden && option.tag!='textarea'){
					if (numInRow==0){
						row = $('<tr></tr>').appendTo(table);						
						row.addClass('row');
						if (odd)
							row.addClass('odd');
						else
							row.addClass('even');				
						odd = !odd;
					}
					var requiredMark ="";
					if (option.options && option.options.required)
						requiredMark = "<font color='#FF0000'>*</font>";
					var title = $('<td>' + requiredMark + option.title + ': </td>').appendTo(row);
					title.addClass('title');
					title.css('width',opts2.titleWidth+"px");
					title.css('font-size',opts2.fontTitle);
					var td = $('<td></td>').appendTo(row);
					td.addClass('text');
					td.css('width',opts2.cellWidth);
					td.css('font-size',opts2.fontInput);					
					var content = $('<' + option.tag+ setAttributes(option) + '"/>').appendTo(td);
					content.css('width',opts2.inputWidth);
					if (option.id)
						content.attr("id",option.id);
					if (option.inner)
						$(option.inner).appendTo(content);
					if (option.type)
						content[option.type](option.options);
					if (option.disabled)
						if (option.type && option.type != 'validatebox')
							content[option.type]('disable');													
						else
							content.attr('disabled','disabled');											
					if (numInRow<opts2.colnum-1)
						numInRow++;						
					else
						numInRow=0;					
				} else if (option.hidden)
					$('<input type="hidden" '+ setAttributes(option) +'"></input>').prependTo(target2);
				else {
					row = $('<tr></tr>').appendTo(table);
					row.addClass('rowarea');
						if (odd)
							row.addClass('odd');
						else
							row.addClass('even');				
						odd = !odd;
					var requiredMark ="";
					if (option.options && option.options.required)
						requiredMark = "<font color='#FF0000'>*</font>";
					var titlearea = $('<td>' + requiredMark + option.title + ': </td>').appendTo(row);
					titlearea.addClass('title');
					var tdarea = $('<td colspan="'+ (opts2.colnum*2-1) +'"></td>').appendTo(row);
					tdarea.addClass('textarea');
					var textarea = $('<textarea'+ setAttributes(option) +'"></textarea>').appendTo(tdarea);
					textarea.css('width',opts2.textareaWidth);
					textarea.css('height',opts2.textareaHeight);	
					if (option.id)
						textarea.attr("id",option.id);
					numInRow=0;
				}
			}
			
			function setAttributes(option2){
				var attrs = option2.attributes;
				if (!attrs)
					attrs = {};
				$.extend(attrs, {
					name: option2.name
				});
				var str = '';
				for (var attr in attrs)
					str += ' ' + attr + '="' + attrs[attr]+'"';
				return str;	
			}			
		}
		
	}
	
	$.fn.tableform = function(options, param){
		if (typeof options == 'string'){
			var method = $.fn.tableform.methods[options];			
			if (method)
				//当method存在时，调用tableform的方法
				return method(this, param);			
			else 				
				//否则调用父组件form的方法
				return $(this).form(options, param);			
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'tableform');
			if (state){
				$.extend(state.options, options);
			} else {
				var state = $.data(this, 'tableform', {
					options: $.extend(true, {}, $.fn.tableform.defaults, $.fn.tableform.parseOptions(this), options)
				});
			}
			create(this);
		});
	};
	
	$.fn.tableform.methods = {
		options: function(jq){
			$.data(jq[0], 'tableform').options;
		},
		disable: function(jq, name){
			var options = $.data(jq[0], 'tableform').options;
			var table = $.data(jq[0], 'tableform').table;
			var type;
			var inputs = [];
			if (options.fieldset)
				for (var i in options.sets)
					$.merge(inputs, options.sets[i].inputs);
			else
				inputs = options.inputs;
			for (var j in inputs)
				if (name == inputs[j].name){
					type = inputs[j].type;
					break;
				}
			if(type)
				$('[name="'+ name +'"]',table)[type]('disable');
			else
				$('[name="'+ name +'"]',table).attr('disabled','disabled');			
			options.disabled = true;
		},
		enable: function(jq, name){
			var options = $.data(jq[0], 'tableform').options;
			var table = $.data(jq[0], 'tableform').table;
			var type;
			var inputs = [];
			if (options.fieldset)
				for (var i in options.sets)
					$.merge(inputs, options.sets[i].inputs);
			else
				inputs = options.inputs;
			for (var j in inputs)
				if (name == inputs[j].name){
					type = inputs[j].type;
					break;
				}
			if(type)
				$('[name="'+ name +'"]',table)[type]('enable');
			else
				$('[name="'+ name +'"]',table).removeAttr('disabled');			
			options.disabled = false;
		}
	};
	
	$.fn.tableform.parseOptions = function(target){
		return $.parser.parseOptions(target);
	};
	
	$.fn.tableform.defaults = $.extend(true,{},$.fn.form.defaults, {
		titleWidth: 120,
		cellWidth: 300,
		inputWidth:	200,
		textareaWidth: 600,
		textareaHeight: 90,
		colnum:2,
		fontTitle:12,
		fontInput:12,
		fieldset:false	
	});
})(jQuery);