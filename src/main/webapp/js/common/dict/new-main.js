var dictDataGrid;
var itemDataGrid;
$(function(){
	//字典项是否可编缉
	var itemCanEdit = false, lastEditIndex = -1;
	
	/**
	 * 初始字典类型表格
	 */
	 dictDataGrid = $('#dictDataGrid').datagrid({
		//分页功能
		loadFilter:pagerFilter,//该方法在enum-data.js中
		height:200,
		title : '字典分类列表',
		fit : true,
		border : false,
		//是否在最左增加一列显示行号的列
		rownumbers:true,
		singleSelect : true,
		fitColumns : true,
		url :ctx+'/common/dict!searchDictType.action',
		pagination : true,
		queryParams : {
			product_id : '-1'
		},
		columns : [[
			{ title : '字典名称', field : 'dic_type_value', width : 100 },
			{ title : '字典代码', field : 'dic_type_code', width : 100 },
			{ title : '备注', field : 'dic_type_des', width : 200 }
		]],
		toolbar : [{
			id : 'dictclass_add',
			text : '新增',
			iconCls : 'icon-add',
			handler : addDictClass
		},'-',{
			id : 'dictclass_update',
			text : '修改',
			iconCls : 'icon-edit',
			disabled : true,
			handler : updateDictClass
		},'-',{
			id : 'dictclass_delete',
			text : '删除',
			iconCls : 'icon-remove',
			disabled : true,
			handler : function() {
				//删除字典类型
				var selectedRow = dictDataGrid.datagrid('getSelected');
				top.$.messager.confirm('确认', '分类和字典项将一并删除<br/>确定要删除[' + selectedRow.dic_type_value + ']？', function(result){
					if (result) {
						$.ajax({
							url : ctx+'/common/dict!deleteType.action',
							dataType : 'json',
							data : {
								dic_type_id : selectedRow.dic_type_id
							},
							type : 'post',
							success : function(data) {
								if (data.result) {
									top.$.messager.alert('提示', '数据删除成功！', 'info', function(){
										dictDataGrid.datagrid('loadData', { total: 0, rows: [] });
										itemDataGrid.datagrid('loadData', { total: 0, rows: [] });
										dictDataGrid.datagrid('reload');
										itemDataGrid.datagrid('reload');
									});
								}
							}
						});
					}
				});
			}
		},'->',{
			//组件类型 - ==> plui-searchbox
			xtype : 'searchbox',
			prompt : '输入字典名称或代码',
			searcher : function(value, name) {
				var queryParams = {
					searchStr : value
				};
				if (name == 'sub') {
					$.extend(queryParams, {
						product_id : $('#productTree').tree('getSelected').id
					});
				}
				dictDataGrid.datagrid('load', queryParams);
			},
			menu : $('<div><div name="all">所有数据</div><div name="sub">节点数据</div></div>').appendTo($(document.body))
		}],
		onLoadSuccess : function() {
			$('#dictclass_update').linkbutton('disable');
			$('#dictclass_delete').linkbutton('disable');
			$('#dictitem_edit').linkbutton('disable');
			itemDataGrid.datagrid('load', {
				dic_type_id : '-1'
			});
		},
		onClickRow : function(rowIndex, rowData) {
			//.datagrid
			itemDataGrid.datagrid('loadData', { total: 0, rows: [] });
			$('#dictclass_update').linkbutton('enable');
			$('#dictclass_delete').linkbutton('enable');
			$('#dictitem_edit').linkbutton('enable');
			itemDataGrid.datagrid('load', {
				dic_type_id : rowData.dic_type_id
			});
			itemDataGrid.datagrid('reload');
		}
	});
	
	/**
	 * 初始产品树
	 */
	$('#productTree').tree({
		onSelect : function(node) {
			dictDataGrid.datagrid('load', {
				product_id : node.id
			});
		},
		onLoadSuccess : function() {
			$('#productTree').tree('select', $('#productTree').tree('find', '-1').target);
		}
	});
	
	/**
	 * 添加字典类型
	 */
	function addDictClass() {
		//打开一个顶层窗口
		openInTopWindow({
			id : 'add_dictclass_win',
			src : ctx+'/jsp/common/dict/dict-type-add.jsp',
			destroy : true,
			title : '新增字典类型',
			width : 600,
			height : 300,
			modal: true,
			onLoad: function(){
				//给子窗口相关对象赋值
				this.openerWindow = window;
				//窗口表单赋值
				//var selectedNode = $('#productTree').tree('getSelected');
				/*
				this.$('#dictclass_add_form').form('load', {
					product_id : selectedNode.id
				});
				*/
			}
		});
	};
	
	/**
	 * 修改字典类型
	 */
	function updateDictClass() {
		//获取选中行
		var selectedRow = dictDataGrid.datagrid('getSelected');
		//打开一个顶层窗口
		openInTopWindow({
			id : 'edit_dictclass_win',
			src : ctx+'/jsp/common/dict/dict-type-edit.jsp',
			destroy : true,
			title : '编辑字典类型：' + selectedRow.dic_type_value,
			width : 600,
			height : 300,
			modal: true,
			onLoad: function(){
				//给子窗口相关对象赋值
				this.openerWindow = window;
				//窗口表单赋值
				var selectedNode = $('#productTree').tree('getSelected');
				this.$('#dictclass_edit_form').form('load', selectedRow);
			}
		});
	};
	
	/**
	 * 菜单项列表
	 */
	 itemDataGrid = $('#itemDataGrid').datagrid({
		loadFilter:pagerFilter,//该方法在enum-data.js中
		title : '字典项列表',
		fit : true,
		border : false,
		//是否有翻页栏
		pagination:true,
		//每页行数
		pageSize:10,
		//是否在最左增加一列显示行号的列
		rownumbers:true,
		singleSelect : true,
		url : ctx+'/common/dict!getItemsByTypeId.action',
		queryParams : {
			dic_type_id : '-1'
		},
		columns : [[
			{ title:'名称', field:'dic_item_value', width:200, editor:'text' },
			{ title:'值', field:'dic_item_code', width:150, editor:'text' },
			{ title:'字典名称', field:'dic_type_value', width:150 },
			{ title:'字典代码', field:'dic_type_code', width:150 },
			{ title:'是否有效', field:'b_deleteflag', width:60, formatter : function(value) {
				return value == '1' ? '有效' : '无效';
			}, editor:{type:'checkbox',options:{on:'1',off:'0'}}}
			
		]],
		rowStyler: function(index,row){
			if (row.valid_flag == '2'){
				return 'text-decoration:line-through;font-style:italic;';
			}
		},
		toolbar : [
		          {
			id : 'dictitem_add',
			text : '新增',
			iconCls : 'icon-add',
			disabled : true,
			handler:function(){  
				//addDictItem();
				
				var rowlen = itemDataGrid.datagrid('getRows');
				 var dict = dictDataGrid.datagrid('getSelected');
				//新增一行
				 
				
					if(!rowlen){
						//打开一个顶层窗口
						openInTopWindow({
							id : 'add_dictitem_win',
							src : ctx+'/jsp/common/dict/dict-item-add.jsp',
							destroy : true,
							title : '新增字典项',
							width : 600,
							height : 300,
							modal: true,
							onLoad: function(){
								//给子窗口相关对象赋值
								this.openerWindow = window;
								
								//窗口表单赋值
								//var selectedNode = $('#productTree').tree('getSelected');
								
								this.$('#dictitem_add_form').form('load', {
									dic_type_id : dict.dic_type_id
								});
								
							}
						});
					}else{
						 //结束编辑状态
						 var rows = $('#itemDataGrid').datagrid('getRows');
						 for ( var i = 0; i < rows.length; i++) {
				        	 $('#itemDataGrid').datagrid('endEdit', i);
						 }
						 
						 
						 $('#itemDataGrid').datagrid('appendRow',{  
							 dic_item_value:'',dic_item_code:'',dic_type_code:dict.dic_type_code,dic_type_value:dict.dic_type_value,dic_type_id:dict.dic_type_id,b_deleteflag:'1',turn:''
			               });  
					}
					
				
                
                 lastEditIndex = $('#itemDataGrid').datagrid('getRows').length-1;  
                 $('#itemDataGrid').datagrid('selectRow', lastEditIndex);  
                 $('#itemDataGrid').datagrid('beginEdit', lastEditIndex);  
                 
			} 
		},{
			id : 'dictitem_del',
			text : '删除',
			iconCls : 'icon-remove',
			disabled : true,
			handler : delDictItem
		},{
			id : 'dictitem_save',
			text : '保存',
			iconCls : 'icon-ok',
			disabled : true,
			handler : saveDictItem
		}],
		onLoadSuccess : itemGridLoadSuccess,
		onClickRow : itemRowClick
	});
	
	/**
	 * 编辑字典项
	 */
	function editDictItem() {
		//禁用“编辑”和“启用、禁用”按钮
		$('#dictitem_edit').linkbutton('disable');
		$('#dictitem_statuschange').linkbutton('disable');
		
		//开启“新增”、“删除”、“保存”、“撤销”按钮
		$('#dictitem_add').linkbutton('enable');
		//$('#dictitem_del').linkbutton('enable');
		$('#dictitem_save').linkbutton('enable');
		//$('#dictitem_cancel').linkbutton('enable');
		
		//开启表格编辑模式
		itemCanEdit = true;
		
		//将所有数据设为编辑状态
		
	};
	
	/**
	 * 新增字典项
	 * 在itemDataGrid最后添加一条可编辑行
	 */
	function addDictItem() {
		//var rowlen = itemDataGrid.datagrid('getRows').length;
		
		//新增一行
		
		/*
		if(!rowlen){
			itemDataGrid.datagrid("insertRow",{index:0,row:{
				name:'',value:'',class_id:dict.class_id,class_name:dict.class_name,class_code:dict.class_code,valid_flag:'1',turn:'' 
			} });
		}
		*/
		var dict = dictDataGrid.datagrid('getSelected');
		itemDataGrid.datagrid('appendRow',{  
			name:'',value:'',class_id:dict.class_id,class_name:dict.class_name,class_code:dict.class_code,valid_flag:'1',turn:''  
        });
		//将新增的行置为可编缉状态
		var rowlen  = itemDataGrid.datagrid('getRows').length;
		itemDataGrid.datagrid('endEdit', lastEditIndex);
		itemDataGrid.datagrid('beginEdit', rowlen - 1);
		lastEditIndex = rowlen - 1;
	};
	
	/**
	 * 删除字典项
	 */
	function delDictItem() {
        var row = itemDataGrid.datagrid('getSelected');
		if (row) {
            var index = itemDataGrid.datagrid('getRowIndex', row);
            itemDataGrid.datagrid('deleteRow', index);
        }
		lastEditIndex = -1;
	};
	
	/**
	 * 保存字典数据
	 * 将itemDataGrid表格中增、删、改的数据应用到后台
	 */
	function saveDictItem() {
		itemDataGrid.datagrid('endEdit', lastEditIndex);
		var rows = $('#itemDataGrid').datagrid('getRows');
        for ( var i = 0; i < rows.length; i++) {
        	 $('#itemDataGrid').datagrid('endEdit', i);
        }
		lastEditIndex = -1;
		var inserted = itemDataGrid.datagrid('getChanges', 'inserted');
		var deleted = itemDataGrid.datagrid('getChanges', 'deleted');
		var updated = itemDataGrid.datagrid('getChanges', 'updated');
		//请求后台保存数据
		$.ajax({
			url :  ctx+'/common/dict!applyEditItem.action',
			dataType : 'json',
			type : 'post',
			data : {
				//将数据拼装成JSON字符串传递到后台
				datas :$.toJSON({
					inserted : inserted,
					deleted : deleted,
					updated : updated
				})
			},
			success : function(data) {
				if (data.success) {
					top.$.messager.alert('提示', '数据保存成功！', 'info', function(){
						//将表格置为不可编缉
						itemCanEdit = false;
						//撤销时启用“编辑按钮”
						$('#dictitem_edit').linkbutton('enable');
						//撤销时重新加载表格数据
						itemDataGrid.datagrid('reload');
					});
				}else{
					top.$.messager.alert('提示', '数据保存失败！', 'info', function(){});
				}
			}
		});
	};
	
	/**
	 * 修改字典状态
	 */
	function changeState() {
		var selectedRow = itemDataGrid.datagrid('getSelected');
		var valid_flag = selectedRow.valid_flag;
		valid_flag = valid_flag == '1'?'2':'1';
		$.ajax({
			url : 'dictDelegate/updateItem.run',
			dataType : 'json',
			type : 'post',
			data : {
				item_id : selectedRow.item_id,
				valid_flag : valid_flag
			},
			success : function(data) {
				if (data.success) {
					top.$.messager.alert('提示', '数据保存成功！', 'info', function(){
						itemDataGrid.datagrid('reload');
					});
				}
			}
		});
	}
	
	/**
	 * itemDataGrid加载完后触发
	 */
	function itemGridLoadSuccess(data) {
		$('#dictitem_add').linkbutton('enable');
		$('#dictitem_save').linkbutton('enable');
		//alert(JSON.stringify(data));
		//加载数据时禁用“新增”、“删除”、“保存”、“撤销”、“启用”等按钮
		//$('#dictitem_add').linkbutton('disable');
		$('#dictitem_del').linkbutton('disable');
		//$('#dictitem_save').linkbutton('disable');
		//$('#dictitem_cancel').linkbutton('disable');
		//$('#dictitem_statuschange').linkbutton('disable');
	};
	
	function reloadData(){
		dictDataGrid.datagrid('reload');
	}
	
	/**
	 * 字典项行点击触发
	 */
	function itemRowClick(rowIndex, rowData) {
		$('#dictitem_del').linkbutton('enable');
		itemCanEdit = true;
		//如果当前表格为可编缉状态时，将选中行设为可编缉
		if (itemCanEdit) {
			if (lastEditIndex != rowIndex) {
				itemDataGrid.datagrid('endEdit', lastEditIndex);
				itemDataGrid.datagrid('beginEdit', rowIndex);
				lastEditIndex = rowIndex;
			}
		} else {
			//点击数据时启用“启用”按钮
			$('#dictitem_statuschange').linkbutton('enable');
			
			//根据数据状态修改启用按钮文字及图标
			if (rowData.valid_flag == '1') {
				$('#dictitem_statuschange').linkbutton({
					text : '禁用',
					iconCls : 'icon-none'
				});
			}else {
				$('#dictitem_statuschange').linkbutton({
					text : '启用',
					iconCls : 'icon-ok'
				});
			}
		}
	};
});
