 var dictDataGrid;
var itemDataGrid;
$(function(){
	//�ֵ����Ƿ�ɱ༩
	var itemCanEdit = false, lastEditIndex = -1;
	
	/**
	 * ��ʼ�ֵ����ͱ��
	 */
	 dictDataGrid = $('#dictDataGrid').datagrid({
		//��ҳ����
		loadFilter:pagerFilter,//�÷�����enum-data.js��
		title : '�ֵ�����б�',
		fit : true,
		border : false,
		singleSelect : true,
		fitColumns : true,
		url :ctx+'/common/dict!searchDictClass.action',
		pagination : true,
		queryParams : {
			product_id : '-1'
		},
		columns : [[
			{ title : '�ֵ�����', field : 'class_name', width : 100 },
			{ title : '�ֵ����', field : 'class_code', width : 100 },
			{ title : '������Ʒ', field : 'product_name', width : 100 },
			{ title : '��ע', field : 'remark', width : 200 }
		]],
		toolbar : [{
			id : 'dictclass_add',
			text : '����',
			iconCls : 'icon-add',
			handler : addDictClass
		},'-',{
			id : 'dictclass_update',
			text : '�޸�',
			iconCls : 'icon-edit',
			disabled : true,
			handler : updateDictClass
		},'-',{
			id : 'dictclass_delete',
			text : 'ɾ��',
			iconCls : 'icon-remove',
			disabled : true,
			handler : function() {
				//ɾ���ֵ�����
				var selectedRow = dictDataGrid.datagrid('getSelected');
				top.$.messager.confirm('ȷ��', '����������һ��ɾ��<br/>ȷ��Ҫɾ��[' + selectedRow.class_name + ']��', function(result){
					if (result) {
						$.ajax({
							url : ctx+'/common/dict!deleteClass.action',
							dataType : 'json',
							data : {
								class_id : selectedRow.class_id
							},
							type : 'post',
							success : function(data) {
								if (data.result) {
									top.$.messager.alert('��ʾ', '����ɾ���ɹ���', 'info', function(){
										dictDataGrid.datagrid('reload');
									});
								}
							}
						});
					}
				});
			}
		},'->',{
			//������� - ==> plui-searchbox
			xtype : 'searchbox',
			prompt : '�����ֵ����ƻ����',
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
			menu : $('<div><div name="all">��������</div><div name="sub">�ڵ�����</div></div>').appendTo($(document.body))
		}],
		onLoadSuccess : function() {
			$('#dictclass_update').linkbutton('disable');
			$('#dictclass_delete').linkbutton('disable');
			$('#dictitem_edit').linkbutton('disable');
			itemDataGrid.datagrid('load', {
				class_id : '-1'
			});
		},
		onClickRow : function(rowIndex, rowData) {
			//.datagrid
			itemDataGrid.datagrid('loadData', { total: 0, rows: [] });
			$('#dictclass_update').linkbutton('enable');
			$('#dictclass_delete').linkbutton('enable');
			$('#dictitem_edit').linkbutton('enable');
			itemDataGrid.datagrid('load', {
				class_id : rowData.class_id
			});
			itemDataGrid.datagrid('reload');
		}
	});
	
	/**
	 * ��ʼ��Ʒ��
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
	 * ����ֵ�����
	 */
	function addDictClass() {
		//��һ�����㴰��
		openInTopWindow({
			id : 'add_dictclass_win',
			src : ctx+'/jsp/common/dict/dictclassadd.jsp',
			destroy : true,
			title : '�����ֵ�����',
			width : 600,
			height : 300,
			modal: true,
			onLoad: function(){
				//���Ӵ�����ض���ֵ
				this.openerWindow = window;
				//���ڱ���ֵ
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
	 * �޸��ֵ�����
	 */
	function updateDictClass() {
		//��ȡѡ����
		var selectedRow = dictDataGrid.datagrid('getSelected');
		//��һ�����㴰��
		openInTopWindow({
			id : 'edit_dictclass_win',
			src : ctx+'/jsp/common/dict/dictclassedit.jsp',
			destroy : true,
			title : '�༭�ֵ����ͣ�' + selectedRow.class_name,
			width : 600,
			height : 300,
			modal: true,
			onLoad: function(){
				//���Ӵ�����ض���ֵ
				this.openerWindow = window;
				//���ڱ���ֵ
				var selectedNode = $('#productTree').tree('getSelected');
				this.$('#dictclass_edit_form').form('load', selectedRow);
			}
		});
	};
	
	/**
	 * �˵����б�
	 */
	 itemDataGrid = $('#itemDataGrid').datagrid({
		title : '�ֵ����б�',
		fit : true,
		border : false,
		singleSelect : true,
		url : ctx+'/common/dict!getItemsByClassId.action',
		queryParams : {
			class_id : '-1'
		},
		columns : [[
			{ title:'����', field:'name', width:200, editor:'text' },
			{ title:'ֵ', field:'value', width:150, editor:'text' },
			{ title:'�ֵ�����', field:'class_name', width:150 },
			{ title:'�ֵ����', field:'class_code', width:150 },
			{ title:'�Ƿ���Ч', field:'valid_flag', width:60, formatter : function(value) {
				return value == '1' ? '��Ч' : '��Ч';
			}, editor:{type:'checkbox',options:{on:'1',off:'2'}}},
			{ title:'˳��', field:'turn', width:30, editor:'numberbox' }
		]],
		rowStyler: function(index,row){
			if (row.valid_flag == '2'){
				return 'text-decoration:line-through;font-style:italic;';
			}
		},
		toolbar : [{
			id : 'dictitem_edit',
			text : '�༭',
			iconCls : 'icon-edit',
			disabled : true,
			handler : editDictItem
		},'-',{
			id : 'dictitem_add',
			text : '����',
			iconCls : 'icon-add',
			disabled : true,
			handler:function(){  
				//addDictItem();
				
				var rowlen = itemDataGrid.datagrid('getRows');
				 var dict = dictDataGrid.datagrid('getSelected');
				//����һ��
				 
				
					if(!rowlen){
						//��һ�����㴰��
						openInTopWindow({
							id : 'add_dictitem_win',
							src : ctx+'/jsp/common/dict/dictitemadd.jsp',
							destroy : true,
							title : '�����ֵ���',
							width : 600,
							height : 300,
							modal: true,
							onLoad: function(){
								//���Ӵ�����ض���ֵ
								this.openerWindow = window;
								
								//���ڱ���ֵ
								//var selectedNode = $('#productTree').tree('getSelected');
								
								this.$('#dictitem_add_form').form('load', {
									class_id : dict.class_id
								});
								
							}
						});
					}else{
						 $('#itemDataGrid').datagrid('appendRow',{  
			                    name:'',value:'',class_id:dict.class_id,class_name:dict.class_name,class_code:dict.class_code,valid_flag:'1',turn:''
			               });  
					}
					
				
                
                 lastEditIndex = $('#itemDataGrid').datagrid('getRows').length-1;  
                 $('#itemDataGrid').datagrid('selectRow', lastEditIndex);  
                 $('#itemDataGrid').datagrid('beginEdit', lastEditIndex);  
                 
			} 
		},{
			id : 'dictitem_del',
			text : 'ɾ��',
			iconCls : 'icon-remove',
			disabled : true,
			handler : delDictItem
		},{
			id : 'dictitem_save',
			text : '����',
			iconCls : 'icon-ok',
			disabled : true,
			handler : saveDictItem
		},{
			id : 'dictitem_cancel',
			text : '����',
			iconCls : 'icon-undo',
			disabled : true,
			handler : function() {
				//�������Ϊ���ɱ༩
				itemCanEdit = false;
				//����ʱ���á��༭��ť��
				$('#dictitem_edit').linkbutton('enable');
				//����ʱ���¼��ر������
				itemDataGrid.datagrid('reload');
				lastEditIndex = -1;
			}
		},'-',{
			id : 'dictitem_statuschange',
			text : '����',
			iconCls : 'icon-ok',
			disabled : true,
			handler : changeState
		}],
		onLoadSuccess : itemGridLoadSuccess,
		onClickRow : itemRowClick,
		onAfterEdit:function(index,row){     
	        row.editing = false;     
	        $('#itemDataGrid').datagrid('refreshRow', index);     
	    }
	});
	
	/**
	 * �༭�ֵ���
	 */
	function editDictItem() {
		//���á��༭���͡����á����á���ť
		$('#dictitem_edit').linkbutton('disable');
		$('#dictitem_statuschange').linkbutton('disable');
		
		//����������������ɾ�����������桱������������ť
		$('#dictitem_add').linkbutton('enable');
		$('#dictitem_del').linkbutton('enable');
		$('#dictitem_save').linkbutton('enable');
		$('#dictitem_cancel').linkbutton('enable');
		
		//�������༭ģʽ
		itemCanEdit = true;
		
		//������������Ϊ�༭״̬
		
	};
	
	/**
	 * �����ֵ���
	 * ��itemDataGrid������һ���ɱ༭��
	 */
	function addDictItem() {
		//var rowlen = itemDataGrid.datagrid('getRows').length;
		
		//����һ��
		
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
		//������������Ϊ�ɱ༩״̬
		var rowlen  = itemDataGrid.datagrid('getRows').length;
		itemDataGrid.datagrid('endEdit', lastEditIndex);
		itemDataGrid.datagrid('beginEdit', rowlen - 1);
		lastEditIndex = rowlen - 1;
	};
	
	/**
	 * ɾ���ֵ���
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
	 * �����ֵ�����
	 * ��itemDataGrid���������ɾ���ĵ�����Ӧ�õ���̨
	 */
	function saveDictItem() {
		
		itemDataGrid.datagrid('endEdit', lastEditIndex);
		itemDataGrid.datagrid("unselectAll");
		lastEditIndex = -1;
		var inserted = itemDataGrid.datagrid('getChanges', 'inserted');
		var deleted = itemDataGrid.datagrid('getChanges', 'deleted');
		var updated = itemDataGrid.datagrid('getChanges', 'updated');
		//�����̨��������
		$.ajax({
			url :  ctx+'/common/dict!applyEdit.action',
			dataType : 'json',
			type : 'post',
			data : {
				//������ƴװ��JSON�ַ������ݵ���̨
				datas :$.toJSON({
					inserted : inserted,
					deleted : deleted,
					updated : updated
				})
			},
			success : function(data) {
				if (data.success) {
					top.$.messager.alert('��ʾ', '���ݱ���ɹ���', 'info', function(){
						//�������Ϊ���ɱ༩
						itemCanEdit = false;
						//����ʱ���á��༭��ť��
						$('#dictitem_edit').linkbutton('enable');
						//����ʱ���¼��ر������
						itemDataGrid.datagrid('reload');
					});
				}else{
					top.$.messager.alert('��ʾ', '���ݱ���ʧ�ܣ�', 'info', function(){});
				}
			}
		});
	};
	
	/**
	 * �޸��ֵ�״̬
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
					top.$.messager.alert('��ʾ', '���ݱ���ɹ���', 'info', function(){
						itemDataGrid.datagrid('reload');
					});
				}
			}
		});
	}
	
	/**
	 * itemDataGrid������󴥷�
	 */
	function itemGridLoadSuccess() {
		//��������ʱ���á�����������ɾ�����������桱�����������������á��Ȱ�ť
		$('#dictitem_add').linkbutton('disable');
		$('#dictitem_del').linkbutton('disable');
		$('#dictitem_save').linkbutton('disable');
		$('#dictitem_cancel').linkbutton('disable');
		$('#dictitem_statuschange').linkbutton('disable');
	};
	
	function reloadData(){
		dictDataGrid.datagrid('reload');
	}
	
	/**
	 * �ֵ����е������
	 */
	function itemRowClick(rowIndex, rowData) {
		//�����ǰ���Ϊ�ɱ༩״̬ʱ����ѡ������Ϊ�ɱ༩
		if (itemCanEdit) {
			if (lastEditIndex != rowIndex) {
				itemDataGrid.datagrid('endEdit', lastEditIndex);
				itemDataGrid.datagrid('beginEdit', rowIndex);
				lastEditIndex = rowIndex;
			}
		} else {
			//�������ʱ���á����á���ť
			$('#dictitem_statuschange').linkbutton('enable');
			
			//��������״̬�޸����ð�ť���ּ�ͼ��
			if (rowData.valid_flag == '1') {
				$('#dictitem_statuschange').linkbutton({
					text : '����',
					iconCls : 'icon-none'
				});
			}else {
				$('#dictitem_statuschange').linkbutton({
					text : '����',
					iconCls : 'icon-ok'
				});
			}
		}
	};
});
