
	//����ֵ���飬��¼��ѡ��ѡ�е��У�������ҳ������ֵ
	window.seq_id_set = [];
var nodeid;

var selectedNode;//ѡ�е����˵�ҵ������
$(function(){
   	var userDataGrid = $('#table_user').datagrid({
   		//��ҳ����
		loadFilter:pagerFilter,//�÷�����enum-data.js��
		fit:true,
		//���������Դctx+"/common/certificate!getProcName.action",
		url : ctx+"/common/certificate!getFormById.action?time="+new Date(),
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
		idField: 'OFFICE_ID',
		//�������Ƿ�����ʾ��ͬ����ɫ					
		striped:true,
		checkbox:'CK',
		//ֻ����ѡһ��
		singleSelect:true,
		//�Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		checkOnSelect:false,
		//�Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		selectOnCheck:false,
		//����������
		queryParams : {
			//ywlxid : '-1'
		},
		columns:[[
			//ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������			
			{ field:'CK',checkbox:true},
			{ title : 'ID', field : 'OFFICE_ID', width : 15 },
			{ title : 'ҵ������ID', field : 'BUS_TYPE_ID', width : 50 },
			{ title : '���', field : 'OFFICE_TYPE', width : 50 },
			{ title : '����', field : 'OFFICE_NAME', width : 50 },
			{ title : 'URL', field : 'OFFICE_URL', width : 300 }
		]],
		//��ѡ�и�ѡ��ļ�¼����ֵ��������seq_id_set
		onCheck: function(index,row){
			if(nodeid!=null&&nodeid!="")
			{
				$.ajax({
					url : ctx+"/common/certificate!insertNode.action?time="+new Date(),
					type : 'post',
					data : {"nodeid":nodeid,"bus_typeid":row.BUS_TYPE_ID,"officeid":row.OFFICE_ID},
					dataType : 'json',
					success : function(data) {
					
					}			
				});
			}
			//seq_id_set[seq_id_set.length] = row.OFFICE_ID;
		},
		//��ȡ��ѡ�и�ѡ��ļ�¼����ֵ������seq_id_setɾ��
		onUncheck: function(index,row){
			if(nodeid!=null&&nodeid!="")
			{
				$.ajax({
					url : ctx+"/common/certificate!deleteNode.action?time="+new Date(),
					type : 'post',
					data : {"nodeid":nodeid,"bus_typeid":row.BUS_TYPE_ID,"officeid":row.OFFICE_ID},
					dataType : 'json',
					success : function(data) {
					
					}			
				});
			}
			//seq_id_set.splice(seq_id_set.indexOf(row.OFFICE_ID),1);
		},
		//��ȫѡʱ����������ֵ��������seq_id_set
		onCheckAll: function(rows){
			for (var i=0;i<rows.length;i++){
				if(nodeid!=null&&nodeid!="")
				{
					$.ajax({
						url : ctx+"/common/certificate!insertNode.action?time="+new Date(),
						type : 'post',
						data : {"nodeid":nodeid,"bus_typeid":rows[i].BUS_TYPE_ID,"officeid":rows[i].OFFICE_ID},
						dataType : 'json',
						success : function(data) {
						
						}			
					});
				}
			}
		},
		//��ȡ��ȫѡʱ����������ֵ������seq_id_set��ɾ��
		onUncheckAll: function(rows){
			for (var i=0;i<rows.length;i++){
				for (var i=0;i<rows.length;i++){
					if(nodeid!=null&&nodeid!="")
					{
						$.ajax({
							url : ctx+"/common/certificate!insertNode.action?time="+new Date(),
							type : 'post',
							data : {"nodeid":nodeid,"bus_typeid":rows[i].BUS_TYPE_ID,"officeid":rows[i].OFFICE_ID},
							dataType : 'json',
							success : function(data) {
							
							}			
						});
					}
				}
			}
		},
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
		}],
		onClickRow : function() {
			//�����ʱ����༭������ɾ������ť
			$('#user_edit').linkbutton('enable');
			$('#user_delete').linkbutton('enable');
		},
		onLoadSuccess : function(data) {
			var target = userDataGrid[0];
			var state = $.data(target, 'datagrid');
			var opts = state.options;
			var rows = state.data.rows;
			var dc = state.dc;
			var hck = dc.header1.add(dc.header2).find('input[type=checkbox]');
			var bck = opts.finder.getTr(target, '', 'allbody').find('div.datagrid-cell-check input[type=checkbox]');
			hck.add(bck)._propAttr('checked', false);
			state.checkedRows = [];
			state.selectedRows = [];
			bck.each(function(index,o){
				if(o.value){
					o.checked = true;
				}
			});
			//������Ͻ��á��༭������ɾ������ť
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
		}
	});
	//��ʼ����Ʒ��
	var productTree = $('#getProcName').tree({
		url : ctx+"/common/certificate!getProcName.action",
		onLoadSuccess : function() {
			//$('#getProcName').tree('select', $('#getProcName').tree('find', '-1').target);
		},
		onSelect : function(node) {
			$('#table_user').datagrid('load',{'nodeid':node.id,'bus_typeid':node.iconCls});
			nodeid=node.id;
			selectedNode = node;
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
		if(selectedNode == null){
			alert("��ѡ��ҵ������");
			return;
		}
		//alert("#djlx_id").val();
		openInTopWindow({
			//����Ԫ�ص�id
			id: 'add_user_win',
			//����iframe��src
			src:ctx+'/jsp/common/officeconfig/add-office-config.jsp',
			//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy: true,
			//���ڱ���
			title: '������������',
			//���ڿ�
			width: 700,
			//���ڸ�
			height: 400,
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
				this.init(selectedNode);
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
			src: ctx+'/jsp/common/officeconfig/edit-office-config.jsp',
			//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy: true,
			//���ڱ���
			title: '�༭����������Ϣ',
			//���ڿ�
			width: 700,
			//���ڸ�
			height: 400,
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
				this.init(row);
			}
		});
	};
	
	//ɾ��
	function doDelete(){
		var row = userDataGrid.datagrid('getSelected');
		top.$.messager.confirm('ȷ��', 'ȷ��Ҫɾ��������Ϊ[' + row.OFFICE_NAME + ']��', function(result){
			if (result) {
				$.ajax({
					url : ctx+"/common/certificate!deleteForm.action?time="+new Date(),
					type : 'post',
					data : {"officeid":row.OFFICE_ID},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							top.$.messager.alert('��ʾ', 'ɾ���ɹ���', 'info', function() {
								userDataGrid.datagrid('reload');
							});
						} else {
							top.$.messager.alert('��ʾ', 'ɾ������', 'error');
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
