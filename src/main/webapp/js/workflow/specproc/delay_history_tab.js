var delay_his_tab;
function init(proc){
	delay_his_tab = $('#delay_his_tab').datagrid({
		title:'������ʷ��¼',
		//���������ַ
		url : ctx+'/workflow/workflow!loadDelayLst.action?sdelayi.bus_id='+proc.bus_id+'&sdelayi.delay_status='+proc.status+'&time='+new Date(),
		fit : true,
		//���ÿ�п���Զ���Ӧ����ܿ��
		fitColumns: true,
		//ȥ���߿�
		border : false,
		//�Ƿ��з�ҳ��
		pagination:true,
		//pageNumber:1,
		pageList:[3,6,9,12],
		//ÿҳ����
		pageSize:6,
		//�Ƿ�����������һ����ʾ�кŵ���
		rownumbers:true,
		//pagePosition:'bottom',
		//����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		idField: 'delay_id',
		//�������Ƿ�����ʾ��ͬ����ɫ					
		striped:true,
		//ֻ����ѡһ��
		singleSelect:false,
		//�Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		/*checkOnSelect:true,
		//�Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		selectOnCheck:true,*/
		columns :[[ 
			{ title : '���ڱ��',     field : 'delay_no'   ,width:100 },
			{ title : '�������',   field : 'delay_app_part' ,width:80 },
			{ title : 'ҵ������',   field : 'bus_des'  ,width:100 },
			{ title : '����������',     field : 'delay_app' ,width:100 },
			{ hidden:'true',field : 'delay_id'},
			{ hidden:'true',field : 'bus_id'}
		]],
		onDblClickRow:function(rowIndex, rowData){
			    $('#delay_his_tab').datagrid('selectRow',rowIndex);
			    alert(JSON.stringify(rowData));
		}
	 });
}
