	var userDataGrid;	
//var row;
//��ʼ������.		
$(function(){	
	 userDataGrid = $('#table_user').datagrid({
		//��ҳ����
		loadFilter:pagerFilter,//�÷�����enum-data.js��
		//fit:true,
		height:470,
		//���������Դ
		url:'workflow!getfinishworkflowList.action',
		//���ÿ�п���Զ���Ӧ����ܿ��
		fitColumns: true,
		//ȥ���߿�
		border : true,
		
		//�Ƿ��з�ҳ��
		pagination:true,
		//ÿҳ����
		pageSize:20,
		//�Ƿ�����������һ����ʾ�кŵ���
		rownumbers:true,
		//����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		//idField: 'user_id',
		//�������Ƿ�����ʾ��ͬ����ɫ					
		striped:true,
		//ֻ����ѡһ��
		singleSelect:true,
		//�Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		//checkOnSelect:true,
		//�Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		//selectOnCheck:true,
		//����������
		columns:[[
			//ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
			{field:'busno', title:'ҵ����',align :'center', width:60, sortable:true},
			{field:'procName', title:'ҵ������',align :'left', width:120},	
			{field:'totallimit',	title:'������',align :'center', width:30},
			{field:'receive',	title:'�Ƿ�ǩ��',align :'center', width:30},
			{field:'committime',	title:'�ύʱ��',align :'center', width:70},
			{field:'nextnode',	title:'��һ����',align :'center', width:60},
			{field:'person',	title:'��һ���ڽ�����',align :'center', width:70},
			{field:'button',align :'center',formatter:function(value,rec){return '<span><input  type="button" value="�鿴" onclick=""/></span>';}}
		]],
		onClickRow : function() {
			//�����ʱ����༭������ɾ������ť
			//alert("onClickRow  event");
			//btn_test_flow()
			$('#user_edit').linkbutton('enable');
			$('#user_delete').linkbutton('enable');
		},
		onClickCell:function(rowIndex, field, value){
			//alert("onclickcell event"+rowIndex+field+value);
			if(field=="button"){
				$('#table_user').datagrid('selectRow',rowIndex);
				//btn_bl(this);
			}
		},
		onLoadSuccess : function() {
			//������Ͻ��á��༭������ɾ������ť
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
		}
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
		openInTopWindow({
			//����Ԫ�ص�id
			id: 'add_user_win',
			//����iframe��src
			src: '../user/add.jsp',
			//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy: true,
			//���ڱ���
			title: '�����û�',
			//���ڿ�
			width: 700,
			//���ڸ�
			height: 550,
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
				this.init();
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
			src: '../user/editMain.jsp',
			//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy: true,
			//���ڱ���
			title: '�༭�û������Ϣ' + row.user_name,
			//���ڿ�
			width: 800,
			//���ڸ�
			height: 600,
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
				this.init();
			}
		});
	};
	
	//ɾ��
	function doDelete(){
		var row = userDataGrid.datagrid('getSelected');
		top.$.messager.confirm('ȷ��', 'ȷ��Ҫɾ���û�[' + row.user_name + ']��', function(result){
			if (result) {
				$.ajax({
					url : 'workflowDelegate/deleteUser.run',
					type : 'post',
					data : {
						user_id : row.user_id
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							top.$.messager.alert('��ʾ', '�û�ɾ���ɹ���', 'info', function() {
								userDataGrid.datagrid('reload');
							});
						} else {
							top.$.messager.alert('��ʾ', '��ɫɾ������', 'error');
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
		//alert(props.join(',\n'));
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
		url: 'workflowDelegate/test.run',
		success: function(data){
			
			userDataGrid.datagrid('loadData',data);
		}
	});
	
	
	
});

function submit1(){
	$('#simpleform').submit();
}

function btn_bl(button){	
		//alert("����Ť�¼�");
		var selectedrow = $('#table_user').datagrid('getSelected');
		//alert(selectedrow);
		//var selectedrow = $('#table_user').datagrid('getSelections');
		//alert(JSON.stringify(selectedrow)+'-----');
		//�򿪴���  obj����
		var obj={	
			id:"open_app",
			//���ڿ�
			width: 1200,
			//���ڸ�
			title:'�칫ҳ��',
			height: 680,
			modal: true,	
			destroy:true,
			onLoad:	function(){
				//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
				//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
				this.openerWindow = window;
				//this.document.getElementById('name').value='����';
				//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
				this.args = {
					user: selectedrow,
					userDataGrid: userDataGrid
				};			
				this.init(selectedrow);
			}
		};				
		
				//alert(selectedrow[0].wiId+"++++");
				var objs={
					
					url:"workflow!test.action",
					type:'post',
					data:{'wiId':selectedrow.wiId},
					success:function(data){
						if(!data){
							alert("�õ�ַ�����ڣ�"+data);							
						}
						else if(data=='null'||data==null||data==''||data==undefined )
						{
							alert("��ַΪnull");
						}
						else
						{													
							//window.showModalDialog(data+'?wiId='+selectedrow[0].wiId,'NEW','dialogHeight: 600px; dialogWidth: 1200px; edge: Raised; center: Yes; help: No; resizable: no; status: no;');
							//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
							//alert(data);
							obj.src=data+'?wiId='+selectedrow.wiId;
								openInTopWindow(obj);		
						}
						//this.init(selectedrow);
					}
					
					
				};
				
				$.ajax(objs);
			
		
}
