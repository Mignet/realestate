<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>����������ɫ</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
        
        <jsp:include page="/plui2/head.jsp" />
        
<script type="text/javascript">
		
	//var row;
	//��ʼ������.		
	function __init(){
		
		$('#table_rel_role').datagrid({
			fit:true,
			border:false,
			//���������Դ
			url:'../userRoleRelDelegate/getRoleListByUserID.run?user_id='+args.user.user_id,
			//���ÿ�п���Զ���Ӧ����ܿ��
			fitColumns: true,
			//�Ƿ��з�ҳ��
			pagination:false,
			//ÿҳ����
			pageSize:20,
			//�Ƿ�����������һ����ʾ�кŵ���
			rownumbers:true,
			//����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
			idField: 'user_id',
			//�������Ƿ�����ʾ��ͬ����ɫ					
			striped:true,
			//ֻ����ѡһ��
			singleSelect:true,
			//����������
			columns:[[
				//ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
				
				{field:'uur_id', title:'����ID', hidden:true},
				{field:'role_id', title:'��ɫID', width:100},
				{field:'role_name', title:'��ɫ����', width:200},	
				{field:'role_code', title:'��ɫ����', width:200},
				{field:'product_name', title:'������Ʒ', width:200},
				{field:'remark', title:'������ע', width:200},
				{field:'creator', title:'������', width:80},
				{field:'create_date', title:'����ʱ��', width:100}						
				
			]],
			//��ͷ����ӹ�������
			toolbar:[{
				text:'������ɫ����',
				iconCls:'icon-add',
				handler:doAdd
			},'-',{
				text:'ɾ����ɫ����',
				iconCls:'icon-remove',
				handler:function(){
					
					getSelected(doDelete);
				}
			}]
		});
		
	};

	
	//ѡ������ĳһ�е����ݡ�
	function getSelected(func){
		var selectedrow = $('#table_rel_role').datagrid('getSelected');
		
		if (selectedrow){
			row = selectedrow;
			//������غ���
			func.call(this,selectedrow);
		}
		else{
			
			$.messager.alert('��ʾ��','����ѡ�б���е�ĳһ��.');
		}
	}
	
	//����
	function doAdd(row){
		var options={
				id:'plat_addRelRole', //idһ��Ҫ��Ψһ
				src:'../user/relRoleAdd.jsp',
				destroy:true,//��ر�ʱ�Ƿ�ע���ô����ͷ�����
				title:'����������ɫ������',
				width:800,   
				height:600, 
				modal:true,
				onBeforeClose:function(){
					//ˢ��
					$('#table_rel_role').datagrid("reload");
				},
				onLoad:function(){
					
					//�����this�Ѿ�ָ���˶�ӦIFRAME��window
					
					//���ݲ�������������
					this.user_id =args.user.user_id;
				}
			};
			openInTopWindow(options);
	}

	//ɾ��
	function doDelete(row){
		top.$.messager.confirm('ȷ��', 'ȷ��Ҫɾ��������ɫ[' + row.role_name + ']��', function(result){
			if (result) {
				$.ajax({
				   type: "POST",
				   url:  '../userRoleRelDelegate/deleteUserRoleRelFromUser.run?urr_ids='+row.urr_id,
				   dataType:"json",
				   success: function(data){
				    	//�����
				    	$('#table_rel_role').datagrid("reload");
				   }
				});
			}
		});
	}
	
	

</script>
</head>
<body>

<table id="table_rel_role"  />

</body>
