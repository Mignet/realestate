/*********************************************************************************
*��  ��  ��  ��: project-main.js
*��  ��  ��  ��: ��������Ŀ����
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Sam
*��  ��  ��  �ڣ� 2014-08-07
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
/**********************************************************************************
*��������: JQuery DOM Ready(Shortcut)
*����˵��: ҳ���ʼ��
*��������: Sam
*��������: 2014-08-07
*�޸���ʷ: 
***********************************************************************************/
//var row;
//��ʼ������.		
$(function(){
	$('#searchpanel').searchpanel({
		//�˲����ǲ�ѯ������css��ʽ����
		containerStyle: {
			margin: '5px auto 5px auto',
			width: $('body').width()-2
		},
		//�˲���Ϊcssѡ������ѡ���ʼ��δ��չ��ѯ����
		searchbox: '#simpleform',
		/******************* ���в���Ϊpanel������� *******************/
		fit:true,
		doSize: true,
		title: '��ѯ��',
		border: true,
		collapsible: true,
		/******************* ���в���Ϊtableform������� *******************/
		errorCode: 700,
		//��Ԫ�ر���������ͨ����Ϊ���У�
		colnum: 3,
		//���ؼ������п�
		titleWidth: 100,
		//���ؼ�������п�
		cellWidth: 200,
		//���ؼ�input��select��combo��������
		inputWidth:	150,
		//textarea�ؼ�������
		textareaWidth: 500,
		//textarea�ؼ�������
		textareaHeight: 90,
		//�Ƿ�����fieldset��ǩ
		fieldset:false	,
		//��Ԫ�ز������顣������ÿ�����󹹳�һ����Ԫ��
		inputs: [{
			tag: 'input',
			title: '��Ŀ���',
			name: 'project_no',
			id: 'project_no'
		},{
			tag: 'input',
			title: '��Ŀ����',
			name: 'project_name',
			id: 'project_name'
		},{
			tag: 'input',
			title: '����',
			name: 'location',
			id: 'location'
		},{
			tag: 'input',
			name:'queryCondition',
			hidden:true,
			id : 'queryCondition',
			attributes: {
				value: 'project_no like ? and project_name like ? and location = ?'
			}
		}],
		url: 'project!getProjectList.action',
		dataType: 'json',
		success: function(data){
			projectDataGrid.datagrid('loadData',data);							
		},
		onExpand : function() {
			var northpanel = $(document.body).layout('panel', 'north');
			northpanel.panel('resize', {height : 200});
			$(document.body).layout('resize');
		},
		onCollapse : function() {
			var northpanel = $(document.body).layout('panel', 'north');
			northpanel.panel('resize', {height : 40});
			$(document.body).layout('resize');
		}
	});
	
	var projectDataGrid = $('#table_project').datagrid({
		fit:true,
		//���������Դ
		url:'../project!getProjectList.action',
		//���ÿ�п���Զ���Ӧ����ܿ��
		fitColumns: true,
		//ȥ���߿�
		border : false,
		//�Ƿ��з�ҳ��
		pagination:true,
		//ÿҳ����
		pageSize:20,
		emptyMsg : '���޼�¼��',
		//�Ƿ�����������һ����ʾ�кŵ���
		rownumbers:true,
		//����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		//idField: 'project_id',
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
			{field:'prj_id', title:'������Ŀ��¼��ˮ�� ', width:70, sortable:true},
			{field:'project_no', title:'��Ŀ���� ', width:70, sortable:true},
			{field:'project_name', title:'��Ŀ���� ', width:70, sortable:true},
			{field:'conscertificatenu', title:'���������֤�� ', width:70, sortable:true},
			{field:'contract_no', title:'����ʹ�ú�ͬ  ', width:70, sortable:true},
			{field:'project_dis', title:'��Ŀ���� ', width:70, sortable:true},
			{field:'building_num', title:'���������� ', width:70, sortable:true},
			{field:'location', title:'��ַ ', width:70, sortable:true},
			{field:'glebe_area', title:'�õ�������滮�� ', width:70, sortable:true},
			{field:'floor_area', title:'�ܽ���������滮�� ', width:70, sortable:true},
			{field:'build_bestrow_per', title:'�����︲���ʣ��滮�� ', width:70, sortable:true},
			{field:'build_unit', title:'���赥λ ', width:70, sortable:true},
		]],
		//��ͷ����ӹ�������
		toolbar : [{
			id : 'project_add',
			text:'����',
			iconCls:'icon-add',
			handler:doAdd			
		},'-',{
			id : 'project_edit',
			text:'�༭',
			iconCls:'icon-pencil',
			disabled : true,
			handler:doEdit
		}
		,'-',{
			id : 'project_delete',
			text:'ɾ��',
			iconCls:'icon-remove',
			disabled : true,
			handler : doDelete
		}/*,'->',{
			xtype : 'searchbox',
			prompt : '������Ŀ��š���Ŀ���ơ��������',
			width : 200,
			searcher : function(value) {
				projectDataGrid.datagrid('load', {
					searchStr : value
				});
			}
		}*/],
		onClickRow : function() {
			//�����ʱ����༭������ɾ������ť
			$('#project_edit').linkbutton('enable');
			$('#project_delete').linkbutton('enable');
		},
		onLoadSuccess : function() {
			//������Ͻ��á��༭������ɾ������ť
			$('#project_edit').linkbutton('disable');
			$('#project_delete').linkbutton('disable');
		}
	});
	
	//ѡ������ĳһ�е����ݡ�
	function getSelected(func){
		var selectedrow = $('#table_project').datagrid('getSelected');
		
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
			id: 'add_project_win',
			//����iframe��src
			src: ctx+'/jsp/basedata/project-add.jsp',
			//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy: true,
			//���ڱ���
			title: '����������Ŀ',
			//���ڿ�
			width: 800,
			//���ڸ�
			height: 420,
			modal: true,
			//������iframe��window�����onLoad�ص���������
			onLoad:	function(){
				//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
				//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
				this.openerWindow = window;
				//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
				this.args = {
					projectDataGrid: projectDataGrid
				};
				this.init();
			}
		});
	};

	//�༭
	function doEdit() {
		var row = projectDataGrid.datagrid('getSelected');
		
		openInTopWindow({
			//����Ԫ�ص�id
			id: 'edit_project_win',
			//����iframe��src
			src: ctx+'/jsp/basedata/project-editMain.jsp',
			//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy: true,
			//���ڱ���
			title: '�༭���������Ϣ' + row.project_name,
			//���ڿ�
			width: 800,
			//���ڸ�
			height: 420,
			modal: true,
			//������iframe��window�����onLoad�ص���������
			onLoad:	function(){
				//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
				//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
				this.openerWindow = window;
				//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
				this.args = {
					project : row,
					projectDataGrid : projectDataGrid
				};
				this.init();
			}
		});
	};
	
	//ɾ��
	function doDelete(){
		var row = projectDataGrid.datagrid('getSelected');
		top.$.messager.confirm('ȷ��', 'ȷ��Ҫɾ��������Ŀ[' + row.project_name + ']��', function(result){
			if (result) {
				$.ajax({
					url : 'project!deleteProject.action',
					type : 'post',
					data : {
						'prj_id' : row.prj_id
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							top.$.messager.alert('��ʾ', '������Ŀɾ���ɹ���', 'info', function() {
								projectDataGrid.datagrid('reload');
							});
						} else {
							top.$.messager.alert('��ʾ', '������Ŀɾ������', 'error');
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
		url: 'project!getProjectList.action',
		success: function(data){
			projectDataGrid.datagrid('loadData',data);
		}
	});
	
});

function submit1(){
	$('#simpleform').submit();
};
