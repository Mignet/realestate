//var row;
//��ʼ������.	
var bus_type_id;	
var selectedNode;//ѡ�е����˵�ҵ������
$(function(){

   	var userDataGrid = $('#table_user').datagrid({
		fit:true,
		//���������Դ
		url:ctx+'/common/configur!getComLan.action?time='+new Date(),
		//���ÿ�п���Զ���Ӧ����ܿ��
		autoRowHeight:false,
		fitColumns: true,
		//ȥ���߿�
		border : false,
		//�Ƿ��з�ҳ��
		pagination:true,
		//pagePosition:'top',
		//ÿҳ����
		pageSize:20,
		//�Ƿ�����������һ����ʾ�кŵ���
		rownumbers:true,
		//����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		idField: 'LANGUAGE_NAME',
		//�������Ƿ�����ʾ��ͬ����ɫ					
		striped:true,
		//ֻ����ѡһ��
		singleSelect:true,
		//�Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		//checkOnSelect:true,
		//�Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		//selectOnCheck:true,
		//����������
		queryParams : {
			//djlx_id : '-1'
		},
		columns:[[
			//ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
			
			 //{field:'ck',checkbox:true},
			 { title : '��ʶ', field : 'LANGUAGE_ID', width : 100 },
			{ title : '����', field : 'LANGUAGE_NAME', width : 100 },
			{ title : '����', field : 'LANGUAGE_CONTENT', width : 200 },
			{ title : 'ģ������', field : 'TEMP_TYPE', width : 200,formatter : function(value) {
				return value == '053002' ? '����ģ��' : '���ģ��';
			}}
			
			
			
			
		]],
		//��ͷ����ӹ�������
		toolbar : [{
			id : 'user_add',
			text:'����',
			iconCls:'icon-add',
			handler:doAdd			
		},'-',{
			id : 'user_edit',
			text:'�༭',
			iconCls:'icon-pencil',
			disabled : true,
			handler:doEdit
		}
		,'-',{
			id : 'user_delete',
			text:'ɾ��',
			iconCls:'icon-remove',
			disabled : true,
			handler : doDelete
		},'->',{
			xtype : 'searchbox',
			prompt : '�Ӽ���������',
			width : 200,
			searcher : function(value) {
				userDataGrid.datagrid('load', {
					searchStr : value
				});
			}
		}],
		onClickRow : function() {
			//�����ʱ����༭������ɾ������ť
			$('#user_edit').linkbutton('enable');
			$('#user_delete').linkbutton('enable');
		},
		onLoadSuccess : function() {
			//������Ͻ��á��༭������ɾ������ť
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
		}
		
		
		
		
	});
	//��ʼ����Ʒ��
	var productTree = $('#productTree').tree({
		url : ctx+'/common/certificate!getBusTypeToTree.action?time='+new Date(),
		onLoadSuccess : function() {
			//$('#productTree').tree('select', $('#productTree').tree('find', '-1').target);
		},
		onSelect : function(node) {
		   
		    bus_type_id = node.id;
		    selectedNode = node;
			$('#table_user').datagrid('load', {
				
				bus_type_id : node.id
			
			});
			
		}
//		onContextMenu:function(e,node){
//			//alert("���ԡ���������������");
//			e.preventDefault();
//			// ѡ��ڵ�
//			$('#productTree').tree('select', node.target);
//
//			$('#mm').menu('show', {
//				left: e.pageX,
//				top: e.pageY
//			});
//
//			
//		}
	});
	//ѡ������ĳһ�е����ݡ�
	function getSelected(func){
		var selectedrow = $('#table_user').datagrid('getSelected');
		
		if (selectedrow){
			row = selectedrow;
			//������غ���
			func.call(this,selectedrow);
		}
		else{
			
			$.messager.alert('��ʾ��','����ѡ�б���е�ĳһ��.');
		}
	};

	//����
	function doAdd() {		
		//alert("#djlx_id").val();
		if(selectedNode == null){
			alert("��ѡ��ҵ������");
			return;
		}
		openInTopWindow({
			//����Ԫ�ص�id
			id: 'add_user_win',
			//����iframe��src
			src: ctx+'/jsp/common/comlangu/addlan.jsp?',
			//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy: true,
			//���ڱ���
			title: '����������',
			//���ڿ�
			width: 700,
			//���ڸ�
			height: 300,
			modal: true,
			//������iframe��window�����onLoad�ص���������
			onLoad:	function(){
				//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
				//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
				this.openerWindow = window;
				//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
				this.args = {
					userDataGrid: userDataGrid
				};
				this.init(bus_type_id);
			}
		});
	};

	//�༭
	function doEdit() {
		var row = userDataGrid.datagrid('getSelected');
		
		openInTopWindow({
			//����Ԫ�ص�id
			id: 'edit_user_win',
			//����iframe��src
			src: ctx+'/jsp/common/comlangu/editlan.jsp',
			//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy: true,
			//���ڱ���
			title: '�༭������',
			//���ڿ�
			width: 700,
			//���ڸ�
			height: 300,
			modal: true,
			//������iframe��window�����onLoad�ص���������
			onLoad:	function(){
				//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
				//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
				this.openerWindow = window;
				//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
				this.args = {
					user : row,
					userDataGrid : userDataGrid
				};
				this.init(row,bus_type_id);
			}
		});
	};
	
	//ɾ��
	function doDelete(){
		var row = userDataGrid.datagrid('getSelected');
		top.$.messager.confirm('ȷ��', 'ȷ��Ҫɾ��������Ϊ[' + row.LANGUAGE_NAME + ']��', function(result){
			if (result) {
				$.ajax({
					url : ctx+"/common/configur!delComLanguage.action?time="+new Date(),
					type : 'post',
					data : {
						language_id : row.LANGUAGE_ID
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							top.$.messager.alert('��ʾ', '������ɾ���ɹ���', 'info', function() {
								userDataGrid.datagrid('reload');
							});
						} else {
							top.$.messager.alert('��ʾ', '����ɾ������', 'error');
						}
					}
				});
			}
		});
	};

	//˫�������ĳһ�еĴ������¼�
	function rowDblclick(rowIndex,row){
		var i = 0;
		var props = [];
		
		for(var p in row){
			props[i++]= p+' = '+row[p];
			
		}
		alert(props.join(',\n'));
		//info(row);
	};

	//��������ʵ����ѯ
	function searchProcint(){
		//$('#searchform').form()
		//$('#dg_proctask').datagrid('load',{});

		var fields = $("#procinstSearchform").serializeArray();
		var o = {};
		jQuery.each( fields, function(i, field){
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
				
			}
		});
		//console.debug(o);
		$('#dg_procinst').datagrid('load',o);
		//console.info("form : "+ o.procName );
		
	};	
	
	$('#simpleform').form({
		dataType: 'json',
		url: 'appDelegate/getUserList.run',
		success: function(data){
			userDataGrid.datagrid('loadData',data);
		}
	});
	
});

function submit1(){
	$('#simpleform').submit();
}
