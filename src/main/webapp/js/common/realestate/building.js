//var row;
//��ʼ������.	
proc=this.parent.proc;
var proc_id = proc.procId;
//var proc_id = 1000016479;
$(function(){
	//���������ı���
	$(":input").attr("disabled", "disabled");
	//��ʼ����������Ϣ��
	
	$.ajax({
	    dataType: 'json',
		url:"../certificate!getBuildById.action?time="+new Date()+"&proc_id="+proc_id,
		//data:{"proc_id":proc_id,"time":new Date()},
		success:function(data){
			data=data.rows[0];
			//alert(JSON.stringify(data));
		 	if(data){
		 		$('#tab_build').form("load",data);
		 	}
		}
	});
	
	//�����ֶ����ܱ�
	var userDataGrid = $('#table_fdhzb').datagrid({
		title:'�ֶ����ܱ�',
		//fit:true,
		//���������Դ
		height:150,
		url:'../certificate!getBuildById.action?proc_id='+proc_id+"&time="+new Date(),
		//���ÿ�п���Զ���Ӧ����ܿ��
		fitColumns: true,
		//ȥ���߿�
		border : true,
		//�Ƿ��з�ҳ��
		pagination:true,
		//ÿҳ����
		pageSize:10,
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
			{field:'building_name', title:'����������', width:70, sortable:true},
			{field:'build_no', title:'����', width:20, sortable:true},	
			{field:'layer_count', title:'�ܲ���', width:20},						
			{field:'FWYT',	title:'��;', width:30},
			{field:'floor_area',	title:'�ܽ������', width:50},
			{field:'build_ld_area',	title:'�������', width:50},
			{field:'JDFTXS',title:'���׷�̯ϵ��', width:60},
			{field:'KGRQ',	title:'��������', width:50},
			{field:'compleion_date',	title:'��������', width:50},
			{field:'JK',	title:'�ۿ�', width:50},
			{field:'BZ',	title:'��ע', width:50}	
		]],
		onLoadSuccess:function(data){
			//alert("success");
		},
		onLoadError:function(){
			//alert("load data error");
		}
	});
	
	
	//�����ֻ����ܱ�
	var userDataGrid1 = $('#table_fhhzb').datagrid({
		title:'�ֻ����ܱ�',
		//fit:true,
		//���������Դ
		url:'../certificate!getHouseById.action?proc_id='+proc_id+"&time="+new Date(),
		//���ÿ�п���Զ���Ӧ����ܿ��
		height:150,
		fitColumns: true,
		//ȥ���߿�
		border : true,
		//�Ƿ��з�ҳ��
		pagination:true,
		//ÿҳ����
		pageSize:10,
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
			{field:'build_no', title:'����', width:30, sortable:true},
			{field:'roomname', title:'����', width:30, sortable:true},
			{field:'kfsmc', title:'����������', width:100, sortable:true },
			{field:'parcel_code', title:' �ڵر��', width:50, },
			{field:'build_area', title:'�������', width:50, },
			{field:'fentan_area', title:'��̯�������', width:80, },
			{field:'taonei_area', title:'���ڽ������', width:80, },
			{field:'ft_glebe_area', title:'��̯���õ����', width:80, },
			{field:'FTJDMJ', title:'��̯�������', width:80, },
			{field:'ft_glebe_area', title:'�õ����', width:50, },
			{field:'flatlet_usage', title:'��;', width:50, },
			{field:'JK', title:'�ۿ�', width:50, },
			{field:'BZ', title:'��ע', width:50, }			
		]]
	});
 
});//��ʼ������

/**********************************************************************************
*��������: ҳ��У�鷽��
*����˵��: ��֤ҳ���ϵķǿ�  �����ݸ�ʽ   
*����˵��: 
*�� �� ֵ: obj  result(trueͨ��  false��ͨ��) message(��Ϣ)  page_name(��ǰҳ������)
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function validate(){
	//���ؽ������
	var result ={
			result:true,
			message:'',
			page_name:'�ڵ���Ϣ��'
	}
	return result;
}
function submit(){
	return true;
}
