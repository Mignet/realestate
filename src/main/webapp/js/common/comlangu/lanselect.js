
var activNames;
var opintions;
//��ʼ��
function init(activName,procdefId,opintion) {
	opintions=opintion;
	activNames = activName;
	var cyyDataGrid = $('#table_cyy').datagrid({
		fit:true,
		//���������Դ
		url:ctx+'/common/configur!getComLanById.action?procdefId='+procdefId+'&temptype=053001&time='+new Date(),
		//���ÿ�п���Զ���Ӧ����ܿ��
		autoRowHeight:false,
		fitColumns: true,
		//ȥ���߿�
		border : true,
		//�Ƿ��з�ҳ��
		pagination:true,
		pagePosition:'top',
		//ÿҳ����
		pageSize:20,
		//�Ƿ�����������һ����ʾ�кŵ���
		rownumbers:true,
		//����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		idField: 'LANGUAGE_ID',
		//�������Ƿ�����ʾ��ͬ����ɫ					
		striped:true,
		nowarp: true,
		//ֻ����ѡһ��
		singleSelect:true,
		//�Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		//checkOnSelect:true,
		//�Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		//selectOnCheck:true,
		//����������
		queryParams : {
			//djlx_id : '2'
		},
		columns:[[
			//ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
			{ title : '����', field : 'LANGUAGE_NAME', width : 100 },
			{ title : '����', field : 'LANGUAGE_CONTENT', width : 400 }
			
		]]
		});

	
	
};//��ʼ������
//����ѡ�г�����

function  submit1(){
	
	var row = $('#table_cyy').datagrid('getSelected');
	if(row == null){
		
		top.$.messager.alert('��ʾ', '��ѡ��һ�������', 'info',
				function() {					
				});	
		return;
	}
	openerWindow.$("#"+opintions).text(row.LANGUAGE_CONTENT);
	openerWindow.focus();
	closeInTopWindow('comlan');

  }
