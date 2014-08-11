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
			title: '��Ŀ����',
			name: 'project_name',
			id: 'project_name'
		},{
			tag: 'input',
			title: '¥��������',
			name: 'bldg_name_no',
			id: 'bldg_name_no'
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
				value: 'project_name like ? and bldg_name_no like ? and location like ?'
			}
		}],
		url: 'building!getBuildingList.action',
		dataType: 'json',
		success: function(data){
			buildingDataGrid.datagrid('loadData',data);							
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
	
	var buildingDataGrid = $('#table_building').datagrid({
		fit:true,
		//���������Դ
		url:'../building!getBuildingList.action',
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
		//idField: 'building_id',
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
			{field:'bldg_id', title:'�������¼��ˮ��', width:140, sortable:true},
			{field:'bldg_code_sz', title:'���������[���]', width:130, sortable:true},
			{field:'bldg_code_gb', title:'���������[����]', width:130, sortable:true},
			{field:'bldg_code_tmp', title:'���������[��ʱ]', width:130, sortable:true},
			{field:'par_id', title:'�ڵ�.�ؿ��� ', width:100, sortable:true},
			{field:'project_name', title:'��Ŀ���� ', width:120, sortable:true},
			{field:'bldg_name_no', title:'¥���Ƽ����� ', width:120, sortable:true},
			{field:'division', title:'���������� ', width:100, sortable:true},
			{field:'rise_no', title:'���� ', width:60, sortable:true},
			/* {field:'road', title:'��ַ��·�� ', width:70, sortable:true},
			{field:'street', title:'���ڽֵ����֣� ', width:70, sortable:true},
			{field:'house_num', title:'��ַ�����ƺţ� ', width:70, sortable:true},
			{field:'location', title:'���� ', width:70, sortable:true},
			{field:'par_no_subno', title:'�ڵغż�֧�� ', width:70, sortable:true},
			{field:'bldg_attr', title:'�������� ', width:70, sortable:true},
			{field:'bldg_type', title:'�������� ', width:70, sortable:true},
			{field:'bldg_usage', title:'������;���滮�� ', width:70, sortable:true},
			{field:'bldg_usage_now', title:'������;����״�� ', width:70, sortable:true},
			{field:'bldg_structure', title:'���ݽṹ ', width:70, sortable:true},
			{field:'bldg_floors', title:'���ݲ��� ', width:70, sortable:true},
			{field:'const_startdate', title:'�������� ', width:70, sortable:true},
			{field:'const_enddate', title:'�������� ', width:70, sortable:true},
			{field:'lu_term', title:'ʹ������ ', width:70, sortable:true},
			{field:'start_date', title:'��ʼ���� ', width:70, sortable:true},
			{field:'end_date', title:'��ֹ���� ', width:70, sortable:true},
			{field:'base_area', title:'������� ', width:70, sortable:true},
			{field:'built_up_area', title:'������� ', width:70, sortable:true},
			{field:'ther_area', title:'������� ', width:70, sortable:true},
			{field:'shared_lu_area', title:'��̯�õ���� ', width:70, sortable:true},
			{field:'b_jkc_count', title:'�ܿղ��� ', width:70, sortable:true},
			{field:'b_zhc_count', title:'ת������ ', width:70, sortable:true},
			{field:'b_sbc_count', title:'�豸���� ', width:70, sortable:true},
			{field:'b_bnc_count', title:'���Ѳ��� ', width:70, sortable:true},
			{field:'b_up_build_area', title:'����������� ', width:70, sortable:true},
			{field:'b_ather_build_area', title:'���������� ', width:70, sortable:true},
			{field:'b_down_build_area', title:'��������� ', width:70, sortable:true},
			{field:'b_build_pos_x', title:'������X���� ', width:70, sortable:true},
			{field:'b_build_pos_y', title:'������Y���� ', width:70, sortable:true},
			{field:'b_build_height', title:'�����߶� ', width:70, sortable:true},
			{field:'b_skirt_count', title:'ȹ¥���� ', width:70, sortable:true},
			{field:'b_tower_count', title:'��¥���� ', width:70, sortable:true},
			{field:'b_common_area', title:'���ý�������ϼ� ', width:70, sortable:true},
			{field:'b_apportion_common_area', title:'Ӧ��̯���ý������ ', width:70, sortable:true},
			{field:'b_noapportion_common_area', title:'��Ӧ��̯���ý������ ', width:70, sortable:true},
			{field:'b_up_build_floor', title:'�����ܲ��� ', width:70, sortable:true},
			{field:'b_down_build_floor', title:'�����ܲ��� ', width:70, sortable:true},
			{field:'ather_build_floor', title:'������Ҳ��� ', width:70, sortable:true},
			{field:'unit_suits', title:'�������� ', width:70, sortable:true},
			{field:'rptdate', title:'����ʱ�� ', width:70, sortable:true},
			{field:'rptid', title:'����֤�� ', width:70, sortable:true},
			{field:'inputer_no', title:'������id ', width:70, sortable:true},
			{field:'inputer', title:'���������� ', width:70, sortable:true},
			{field:'input_date', title:'�������� ', width:70, sortable:true},
			{field:'modifier_no', title:'����޸��˹�Ա�� ', width:70, sortable:true},
			{field:'modifier', title:'����޸��� ', width:70, sortable:true},
			{field:'modify_date', title:'����޸����� ', width:70, sortable:true},*/
			{field:'bldg_status', title:'¥�̱�״̬ ', width:80, sortable:true},
			{field:'data_state', title:'��¼״̬ ', width:70, sortable:true},
			{field:'data_from', title:'������Դ ', width:70, sortable:true}, 
			{field:'yxt_id', title:'Ӱ��ͼID ', width:70, sortable:true},
			{field:'fcch_bldg_id', title:'��潨����ID ', width:70, sortable:true}
		]],
		//��ͷ����ӹ�������
		toolbar : [{
			id : 'building_add',
			text:'����',
			iconCls:'icon-add',
			handler:doAdd			
		},'-',{
			id : 'building_edit',
			text:'�༭',
			iconCls:'icon-pencil',
			disabled : true,
			handler:doEdit
		}
		,'-',{
			id : 'building_delete',
			text:'ɾ��',
			iconCls:'icon-remove',
			disabled : true,
			handler : doDelete
		}/*,'->',{
			xtype : 'searchbox',
			prompt : '������Ŀ���ơ�¥�������š��������',
			width : 200,
			searcher : function(value) {
				buildingDataGrid.datagrid('load', {
					searchStr : value
				});
			}
		}*/],
		onClickRow : function() {
			//�����ʱ����༭������ɾ������ť
			$('#building_edit').linkbutton('enable');
			$('#building_delete').linkbutton('enable');
		},
		onLoadSuccess : function() {
			//������Ͻ��á��༭������ɾ������ť
			$('#building_edit').linkbutton('disable');
			$('#building_delete').linkbutton('disable');
		}
	});
	
	//ѡ������ĳһ�е����ݡ�
	function getSelected(func){
		var selectedrow = $('#table_building').datagrid('getSelected');
		
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
			id: 'add_building_win',
			//����iframe��src
			src: ctx+'/jsp/basedata/building-addMain.jsp',
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
					buildingDataGrid: buildingDataGrid
				};
				this.init();
			}
		});
	};

	//�༭
	function doEdit() {
		var row = buildingDataGrid.datagrid('getSelected');
		
		openInTopWindow({
			//����Ԫ�ص�id
			id: 'edit_building_win',
			//����iframe��src
			src: ctx+'/jsp/basedata/building-editMain.jsp',
			//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy: true,
			//���ڱ���
			title: '�༭���������Ϣ' + row.bldg_name_no,
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
					building : row,
					buildingDataGrid : buildingDataGrid
				};
				this.init();
			}
		});
	};
	
	//ɾ��
	function doDelete(){
		var row = buildingDataGrid.datagrid('getSelected');
		top.$.messager.confirm('ȷ��', 'ȷ��Ҫɾ������[' + row.building_name + ']��', function(result){
			if (result) {
				$.ajax({
					url : 'building!deleteBuilding.action',
					type : 'post',
					data : {
						building_id : row.building_id
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							top.$.messager.alert('��ʾ', '����ɾ���ɹ���', 'info', function() {
								buildingDataGrid.datagrid('reload');
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
		url: 'building!getBuildingList.action',
		success: function(data){
			buildingDataGrid.datagrid('loadData',data);
		}
	});
	
});

function submit1(){
	$('#simpleform').submit();
};
