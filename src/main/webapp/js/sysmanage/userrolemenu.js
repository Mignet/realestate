var userroleLst;
$.fn.datebox.defaults.formatter = function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	if(y < 100){
		y = 1900 + y;
	}
	if(m < 10){
		m = "0" + m;
	}
	if(d < 10){
		d = "0" + d;
	}
	return y+'-'+m+'-'+d;
}
$(function(){
	userroleLst = $('#userroleLst').datagrid({
		//��ҳ����
		loadFilter:pagerFilter,//�÷�����enum-data.js��
	    title:'�û���Ϣ',
		//���������ַ
		url : ctx+'/sysmanage/user-role-manage!searchUserListByOptions.action?time=' + new Date(),
		fit:true,
		//��ʼҳ
		//pageNumber:1,
		//ҳ�뼯��
		//pageList:[8,16,24,32,40],
		//ÿҳ����
		pageSize:8,
		//���ÿ�п���Զ���Ӧ����ܿ��
		fitColumns: true,
		//ȥ���߿�
		border : false,
		//�Ƿ��з�ҳ��
		pagination:true,
		//�Ƿ�����������һ����ʾ�кŵ���
		rownumbers:true,
		//����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		idField: 'user_id',
		//�������Ƿ�����ʾ��ͬ����ɫ					
		striped:true,
		//ֻ����ѡһ��
		singleSelect:true,
		//�Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		//checkOnSelect:true,
		//�Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		//selectOnCheck:true,
		//����������
		height:390,
		sortName:'CREATEDATE',
		sortOrder:'desc',
		columns :[[ 
			{ title : '�û�ID', field : 'USER_ID', width : 80 },
			{ title : '�û���', field : 'USER_NAME', width : 80 },
			{ title : '������', field : 'CREATOR', width : 80 },
			{ title : '����ʱ��', field : 'CREATEDATE', width : 100 ,sortable:true},
			{ title : '��ɫ', field : 'ROLENAME', width : 100 },
			{ hidden:'true',field : 'ROLEID', width : 100 }
		]],
		toolbar : [{
			id : 'userrole_edit',
			text : '�༭',
			disabled : true,
			iconCls : 'icon-edit',
			handler : editUserRole
		}],
		onClickRow : function() {
			//������ʱ����༭��
			$('#userrole_edit').linkbutton('enable');
		}
	});
	
/*	$('#simpleform').form({
		url: ctx + '/sysmanage/user-role-manage!searchUserListByOptions.action?time=' + new Date(),
		success:function(data){
			data = $.parseJSON(data);
			if (data.success) {
				top.$.messager.alert('��ʾ',data.tipMessage,'info',function(){
					$('#userroleLst').datagrid('reload');
				});	
			}
		}
 	});*/
	/**
	 * �༭�û�
	 */
	function editUserRole() {
		//��ȡѡ����
		var selectedNode = $('#userroleLst').datagrid('getSelected');
		//�򿪶��㴰��
		openInTopWindow({
			id : 'edit_userrole_win',
			src : ctx+'/jsp/sysmanage/edituserrole.jsp',
			destroy : true,
			title : '�༭�û�:' + selectedNode.USER_NAME,
			width : 280,
			height : 400,
			modal: true,
			onLoad: function(){
				//���Ӵ�����ض���ֵ
				this.openerWindow = window;
				var _this = this;
				//���ػ�ȡ��������
				_this.$('#userrole_edit_form').form('load', selectedNode);
				this.init(selectedNode);
			}
		}); 
	};
});

function submit1(){
	    $('#userroleLst').datagrid('loadData',{total:0,rows:[]});
	    $('#userroleLst').datagrid('load',$('#simpleform').serializeJson());
}
function clear1(){
    $('#simpleform')[0].reset();
}
