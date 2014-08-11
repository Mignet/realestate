var suspend_his_tab;
function init(proc){
	suspend_his_tab = $('#suspend_his_tab').datagrid({
		title:'������ʷ��Ϣ',
		//���������ַ
		url : ctx+'/workflow/workflow!loadSuspendLst.action?ssuspendi.bus_id='+proc.bus_id+'&ssuspendi.sus_status='+proc.status+'&time='+new Date(),
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
		idField: 'sus_id',
		//�������Ƿ�����ʾ��ͬ����ɫ					
		striped:true,
		//ֻ����ѡһ��
		singleSelect:false,
		//�Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		/*checkOnSelect:true,
		//�Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		selectOnCheck:true,*/
		columns :[[ 
			{ title : '������',     field : 'sus_no'   ,width:100 },
			{ title : '�������',   field : 'app_off' ,width:80 },
			{ title : 'ҵ������',   field : 'bus_des'  ,width:100 },
			{ title : '����������',     field : 'sus_app' ,width:100 },
			{ hidden:'true',field : 'sus_id'},
			{ hidden:'true',field : 'bus_id'}
		]],
		onDblClickRow:function(rowIndex, rowData){
			    $('#suspend_his_tab').datagrid('selectRow',rowIndex);
			    alert(JSON.stringify(rowData));
		}
	 });
}
