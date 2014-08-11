<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>������������</title>
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
		
		$('#table_rel_dept').datagrid({
			fit:true,
			border:false,
			//���������Դ
			url:'../deptUserRelDelegate/getDeptListByUserID.run?user_id='+args.user.user_id,
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
				
				{field:'dur_id', title:'����ID', hidden:false},
				{field:'dept_id', title:'����ID', width:100},
				{field:'dept_name', title:'��������', width:200},	
				{field:'dtype', title:'��������', width:100},
				{field:'duty', title:'���ڲ���ְ��', width:100},
				{field:'creator', title:'������', width:80},
				{field:'create_date', title:'����ʱ��', width:100}						
				
			]],
			//��ͷ����ӹ�������
			toolbar:[{
				text:'�������Ź���',
				iconCls:'icon-add',
				handler:doAdd
			},'-',{
				text:'ɾ�����Ź���',
				iconCls:'icon-remove',
				handler:function(){
					getSelected(doDelete);
				}
			}]
		});
		
	};

	
	//ѡ������ĳһ�е����ݡ�
	function getSelected(func){
		var selectedrow = $('#table_rel_dept').datagrid('getSelected');
		
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
				id:'palt_relDeptAdd', //idһ��Ҫ��Ψһ
				src:'../user/relDeptAdd.jsp',
				destroy:true,//��ر�ʱ�Ƿ�ע���ô����ͷ�����
				title:'���������벿�Ź�����',
				width:500,   
				height:500, 
				modal:true,
				onBeforeClose:function(){
					//ˢ��
					$('#table_rel_dept').datagrid("reload");
				},
				onLoad:function(){
					
					//�����this�Ѿ�ָ���˶�ӦIFRAME��window
					//this.$('#procinstform').form('load',row);
					//�������ڴ��ݲ���
					/**
					this.user_id=window.parent.row_user.user_id;
					this.init();
					*/
					//���·�ʽ�����
					this.init(args.user.user_id);
				}
			};
			openInTopWindow(options);
	}
	

	//ɾ��
	function doDelete(row){
		top.$.messager.confirm('ȷ��', 'ȷ��Ҫɾ����������[' + row.dept_name + ']��', function(result){
			if (result) {
				$.ajax({
					type: "POST",
					url:  '../deptUserRelDelegate/deleteDeptUserRelFromUser.run?dur_ids='+row.dur_id,
					dataType:"json",
					success: function(user){
						//�����
						$('#table_rel_dept').datagrid("reload");
					}
				});
			}
		});
 		
	}
	
	

</script>
</head>
<body>

<table id="table_rel_dept"  />

</body>
