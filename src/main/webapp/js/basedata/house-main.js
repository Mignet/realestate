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
			title: '���ݱ��',
			name: 'ho_id',
			id: 'ho_id'
		},{
			tag: 'input',
			title: '��Ȼ����',
			name: 'house_no_num',
			id: 'house_no_num'
		},{
			tag: 'input',
			title: '����',
			name: 'seat_no',
			id: 'seat_no'
		},{
			tag: 'input',
			name:'queryCondition',
			hidden:true,
			id : 'queryCondition',
			attributes: {
				value: 'ho_id like ? and house_no_num like ?  and seat_no like ?'
			}
		}],
		url: 'house!getHouseList.action',
		dataType: 'json',
		success: function(data){
			houseDataGrid.datagrid('loadData',data);							
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
	
	var houseDataGrid = $('#table_house').datagrid({
		fit:true,
		//���������Դ
		url:'../house!getHouseList.action',
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
		//idField: 'house_id',
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
			{field:'ho_id', title:'���ݱ��', width:70, sortable:true},
			{field:'house_code_sz', title:'���ݱ��루�������չ��', width:70, sortable:true},
			{field:'house_code_gb', title:'���ݱ��루���꣩', width:70, sortable:true},
			{field:'house_code_tmp', title:'���ݱ��루��ʱ���룩', width:70, sortable:true},
			{field:'parcel_no_subno', title:'�ڵ�֧��', width:70, sortable:true},
			{field:'unit_no', title:'����', width:70, sortable:true},
			{field:'house_no_num', title:'��Ȼ����', width:70, sortable:true},
			{field:'seat_no', title:'����', width:70, sortable:true},
			{field:'house_style', title:'����', width:70, sortable:true},
			{field:'floor_no', title:'����¥��', width:70, sortable:true},
			/*{field:'layer_no', title:'��Ȼ�㣨ʵ�ʲ㣩', width:70, sortable:true},
			{field:'layer_no_num', title:'��Ȼ�㣨���֣�', width:70, sortable:true},
			{field:'layer_used', title:'��ռ����', width:70, sortable:true},
			{field:'house_attr', title:'��������', width:70, sortable:true},
			{field:'house_type', title:'��������', width:70, sortable:true},
			{field:'house_usage', title:'��;', width:70, sortable:true},
			{field:'house_structure', title:'�ṹ', width:70, sortable:true},
			{field:'lu_term', title:'ʹ������', width:70, sortable:true},
			{field:'start_date', title:'��ʼ����', width:70, sortable:true},
			{field:'end_date', title:'��ֹ����', width:70, sortable:true},
			{field:'built_in_area', title:'�������', width:70, sortable:true},
			{field:'house_in_area', title:'���ڷ������', width:70, sortable:true},
			{field:'house_comm_area', title:'', width:70, sortable:true},
			{field:'floor_area', title:'ʹ�����', width:70, sortable:true},
			{field:'lu_area', title:'����̯���õ����', width:70, sortable:true},
			{field:'shared_com_area', title:'��̯�������', width:70, sortable:true},
			{field:'shared_base_area', title:'��̯�������', width:70, sortable:true},
			{field:'other_area', title:'�������', width:70, sortable:true},
			{field:'inputer_no', title:'¼��Ա��Ա��', width:70, sortable:true},
			{field:'inputer', title:'¼��Ա', width:70, sortable:true},
			{field:'input_date', title:'¼������', width:70, sortable:true},
			{field:'modifier_no', title:'�޸��˹�Ա��', width:70, sortable:true},
			{field:'modifier', title:'�޸���', width:70, sortable:true},
			{field:'modify_date', title:'�޸�����', width:70, sortable:true},
			{field:'rise_id', title:'¥��.¥����', width:70, sortable:true},
			{field:'data_status', title:'��¼״̬', width:70, sortable:true},
			{field:'data_from', title:'������Դ', width:70, sortable:true},
			{field:'approver_no', title:'���ݺ�׼��', width:70, sortable:true},
			{field:'appr_date', title:'���ݺ�׼����', width:70, sortable:true},
			{field:'appr_opinion', title:'���ݺ�׼���', width:70, sortable:true},
			{field:'appr_result', title:'���ݺ�׼���', width:70, sortable:true},
			{field:'hxt_id', title:'����ͼid', width:70, sortable:true},*/
			{field:'fcch_flatlet_id', title:'��淿��ID', width:70, sortable:true}
		]],
		//��ͷ����ӹ�������
		toolbar : [{
			id : 'house_add',
			text:'����',
			iconCls:'icon-add',
			handler:doAdd			
		},'-',{
			id : 'house_edit',
			text:'�༭',
			iconCls:'icon-pencil',
			disabled : true,
			handler:doEdit
		}
		,'-',{
			id : 'house_delete',
			text:'ɾ��',
			iconCls:'icon-remove',
			disabled : true,
			handler : doDelete
		}/*,'->',{
			xtype : 'searchbox',
			prompt : '���뷿�ݱ�š���Ȼ���š�����',
			width : 200,
			searcher : function(value) {
				houseDataGrid.datagrid('load', {
					searchStr : value
				});
			}
		}*/],
		onClickRow : function() {
			//�����ʱ����༭������ɾ������ť
			$('#house_edit').linkbutton('enable');
			$('#house_delete').linkbutton('enable');
		},
		onLoadSuccess : function() {
			//������Ͻ��á��༭������ɾ������ť
			$('#house_edit').linkbutton('disable');
			$('#house_delete').linkbutton('disable');
		}
	});
	
	//ѡ������ĳһ�е����ݡ�
	function getSelected(func){
		var selectedrow = $('#table_house').datagrid('getSelected');
		
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
			id: 'add_house_win',
			//����iframe��src
			src: ctx+'/jsp/basedata/house-add.jsp',
			//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy: true,
			//���ڱ���
			title: '��������',
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
					houseDataGrid: houseDataGrid
				};
				this.init();
			}
		});
	};

	//�༭
	function doEdit() {
		var row = houseDataGrid.datagrid('getSelected');
		
		openInTopWindow({
			//����Ԫ�ص�id
			id: 'edit_house_win',
			//����iframe��src
			src: ctx+'/jsp/basedata/house-editMain.jsp',
			//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy: true,
			//���ڱ���
			title: '�༭���������Ϣ',
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
					house : row,
					houseDataGrid : houseDataGrid
				};
				this.init();
			}
		});
	};
	
	//ɾ��
	function doDelete(){
		var row = houseDataGrid.datagrid('getSelected');
		top.$.messager.confirm('ȷ��', 'ȷ��Ҫɾ���÷��ݣ�', function(result){
			if (result) {
				$.ajax({
					url : 'house!deleteHouse.action',
					type : 'post',
					data : {
						house_id : row.ho_id
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							top.$.messager.alert('��ʾ', '����ɾ���ɹ���', 'info', function() {
								houseDataGrid.datagrid('reload');
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
		url: 'house!getHouseList.action',
		success: function(data){
			houseDataGrid.datagrid('loadData',data);
		}
	});
	
});

function submit1(){
	$('#simpleform').submit();
};
