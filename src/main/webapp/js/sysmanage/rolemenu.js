/*********************************************************************************
*��  ��  ��  ��: rolemenu.js
*��  ��  ��  ��: ��ɫ�˵���Ȩ
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Mignet
*��  ��  ��  �ڣ� 2014-03-01
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
var saveoptions;
/**********************************************************************************
*��������: JQuery DOM Ready(Shortcut)
*����˵��: ҳ���ʼ��
*��������: Mignet
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
$(function(){
	var roleDataGrid = $('#table_roles').datagrid({
		title:'��ɫ��Ϣ',
		//fit:true,
		//���������Դ
		url:'role-manage!getAllRoles.action',
		//���ÿ�п���Զ���Ӧ����ܿ��
		height:300,
		//fitColumns: true,
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
		 remoteSort: false,
		 idField:'roleid',  
         pagination:true,  
         rownumbers:true,  
         sortName:'createdatestr',
         sortOrder:'desc',
		//����������
		columns:[[
			{field:'roleid', title:'��ɫID', width:140, sortable:true},
			{field:'rolename', title:'��ɫ����', width:100, sortable:true},
			//{field:'parentid', title:'������ɫ', width:100, sortable:true},
			{field:'operatorcode', title:'������', width:100, sortable:true},
			{field:'createdatestr', title:'����ʱ��', width:120, sortable:true},
			{field:'attribute', title:'��ɫ����', width:100,formatter:Status, sortable:true},
			{field:'begintimestr', title:'��Ч����', width:120, sortable:true},
			{field:'endtimestr', title:'��ֹ����', width:120, sortable:true},
			{field:'keepflag', title:'�̶���ɫ', width:100,formatter:Keep, sortable:true},
			{field:'effectflag', title:'��Ч��־', width:100,formatter:YesOrNo,sortable:true},
			{field:'remark', title:'��ע', width:100, sortable:false}
		]],
		toolbar:[{
					id : 'role_add',
					text : '���',
					iconCls : 'icon-edit_add',
					disabled : false,
					handler : doRoleAdd
				},{
					id : 'role_del',
					text : 'ɾ��',
					iconCls : 'icon-edit_remove',
					disabled : false,
					handler : doRoleDel
				}
		],
	    onClickRow : function() {
	    	doRoleEditAndTreeSet();
	    },onLoadSuccess : function(data) {
	    	if(data){
	    	    $('#table_roles').datagrid('selectRow',0);
	    	    doRoleEditAndTreeSet();
	    	}
 		}
	});
	
	$('#role_simple_form').form({
		url:"role-manage!updateandAddRole.action?time="+new Date(),
		success:function(data){
			data = $.parseJSON(data);
			if (data.success) {
				top.$.messager.alert('��ʾ',data.tipMessage,'info',function(){
					$('#table_roles').datagrid('reload');
				});	
			}
		}
	});
	function Status(value){
		return value == '01' ? '����' : '��ʱ';
	}
	function Keep(value){
		return value == '01' ? '�̶�' : '�ɱ�';
	}
	function YesOrNo(value){
		return value == '01' ? '��Ч' : '��Ч';
	}
	//�˵���
    $('#tt').tree({    
    	url:'role-manage!getAllMenus.action'
    });   
});//��ʼ������

/**********************************************************************************
*��������: collapseAll
*����˵��: ��ȫ������
*����˵��: ��
*�� �� ֵ: ��
*��������: Mignet
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function collapseAll(){  
    $('#tt').tree('collapseAll');  
}
/**********************************************************************************
*��������: expandAll
*����˵��: ��ȫ��չ��
*����˵��: ��
*�� �� ֵ: ��
*��������: Mignet
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function expandAll(){  
    $('#tt').tree('expandAll');  
}
/**********************************************************************************
*��������: saveRM
*����˵��: ������Ȩ��ϵ
*����˵��: ��
*�� �� ֵ: �ɹ�orʧ��
*��������: Mignet
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function saveRM(){  
	var selected = $('#table_roles').datagrid('getSelected');  
    if (selected){  
	    var nodes = $('#tt').tree('getChecked');  
	    var s = '';  
	    for(var i=0; i<nodes.length; i++){  
	        if (s != '') s += ',';  
	        s += nodes[i].id;  
	    }  
		$.ajax({
			url : 'role-manage!saveRM.action',
			dataType : 'json',
			type : 'post',
			data : {
				"roleid":selected.roleid,
				"menuids":s
			},
			success : function(data) {
				if(data.success){
			 		top.$.messager.alert('��ʾ',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('��ʾ',data.errorMessage,'error');
				}
			}
		});
    }else{
    	alert("����ѡ����Ҫ��Ȩ�Ľ�ɫ��");
    }
} 
/**********************************************************************************
*��������: doRoleAdd
*����˵��: ��ɫ���
*����˵��: ��
*�� �� ֵ: �ɹ�orʧ��
*��������: Sam
*��������: 2014-08-05
*�޸���ʷ: 
***********************************************************************************/
function doRoleAdd(){
	 $('#roleid').text('**********');
	 $('#role_id').val('');
	 $('#role_simple_form')[0].reset();
	 saveoptions = 'add';
	 $('#createdatestr').datetimebox('setValue',getCurTime());
	 $('#operatorcode').val('999999999');
	 $('#keepflag').combobox('setValue','00');
	 $('#effectflag').combobox('setValue','01');
	 $('#attribute').combobox('setValue','00');
	 
};

/**********************************************************************************
 *��������: doRoleDel
 *����˵��: ��ɫɾ��
 *����˵��: ��
 *�� �� ֵ: �ɹ�orʧ��
 *��������: Sam
 *��������: 2014-08-05
 *�޸���ʷ: 
 ***********************************************************************************/
function doRoleDel(){
	var roleid;
	var row = $('#table_roles').datagrid('getSelected');  
	if (row){
		var obj = {
				url:"role-manage!deleteRole.action?time="+new Date(),
				data:{"roleid":row.roleid},
				success:function(data){
					data = $.parseJSON(data);
					if (data.success) {
						top.$.messager.alert('��ʾ',data.tipMessage,'info',function(){
							$('#table_roles').datagrid('reload');
						});	
					}
				}
		};
		top.$.messager.confirm('��ʾ','ȷ��Ҫɾ����һ����',function(r){
			if(r){
				var index = $('#table_roles').datagrid('getRowIndex', row);  
				$('#table_roles').datagrid('deleteRow', index);    
				$.ajax(obj);
			}
		});
	}else{   
		top.$.messager.confirm('��ʾ','��ѡ��Ҫɾ�����У���');             	
	}  
};

/**********************************************************************************
*��������: doRoleEditAndTreeSet
*����˵��: ��ɫ�༭�����˵�����
*����˵��: ��
*�� �� ֵ: �ɹ�orʧ��
*��������: Sam
*��������: 2014-08-05
*�޸���ʷ: 
***********************************************************************************/
function doRoleEditAndTreeSet(){
	var selected = $('#table_roles').datagrid('getSelected');  
    if (selected){  
      //�˵���
        $('#tt').tree({    
        	url:'role-manage!getCheckedMenus.action?roleid='+selected.roleid
        })
    }
    $('#role_simple_form').form('load',selected);
    $('#roleid').text($('#role_id').val());
    saveoptions = 'update';
};
/**********************************************************************************
*��������: submit
*����˵��: ���ύ
*����˵��: ��
*�� �� ֵ: �ɹ�orʧ��
*��������: Sam
*��������: 2014-08-05
*�޸���ʷ: 
***********************************************************************************/
function submit(){
	if(saveoptions){
		if(saveoptions == 'update'){
			top.$.messager.confirm('��ʾ',"��ȷ��Ҫ���¡�"+$('#rolename').val()+"����",function(r){
				if(r){
					$('#role_simple_form').submit();
				}
			});
		}
		else if(saveoptions == 'add'){
			
			top.$.messager.confirm('��ʾ',"��ȷ��Ҫ���ӡ�"+$('#rolename').val()+"����",function(r){
				if(r){
					$('#role_simple_form').submit();
				}
			});
		}
	}
};
