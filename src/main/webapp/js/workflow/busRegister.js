		
//var row;
//��ʼ������.		
/*$(function(){	
	var userDataGrid = $('#table_user').datagrid({
		fit:true,
		//���������Դ
		url:'workflow!queryProcessdef.action',
		//���ÿ�п���Զ���Ӧ����ܿ��
		fitColumns: true,
		//ȥ���߿�
		border : false,
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
		remoteSort:false,
		//�Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		//checkOnSelect:true,
		//�Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		//selectOnCheck:true,
		//����������
		columns:[[
			//ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
			{field:'procdefId', title:'ID',align :'center', width:30, sortable:true,hidden:true},
			{field:'name', title:'ҵ������',align :'left', width:80, sortable:true},	
			{field:'createDate',	title:'��������', align :'center',width:50},
			{field:'lastEditDate',	title:'�޸�����',align :'center', width:50},
			
		]],
		//��ͷ����ӹ�������
		toolbar : ['->','-',{
			id : 'user_edit',
			text:'����',
			iconCls:'icon-pencil',
			disabled : true,
			handler:doEdit
		}
		
		],
		onClickRow : function() {
			//�����ʱ����༭������ɾ������ť
			$('#user_edit').linkbutton('enable');
			//$('#user_delete').linkbutton('enable');
		},
		onLoadSuccess : function() {
			//������Ͻ��á��༭������ɾ������ť
			$('#user_edit').linkbutton('disable');
			//$('#user_delete').linkbutton('disable');
		}
	});*/


/*$(function(){
	$.ajax({
		url : ctx+'/common/configur!getBusProcList.action?time='+new Date(),
		type : 'post',
		data : {
			applicant_id : row.applicant_id
		},
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				top.$.messager.alert('��ʾ', data.tipMessage, 'info',
						function() {
					        //alert("ɾ��֮��ˢ��");
							userDataGrid.datagrid('reload');
						});
			} else {
				top.$.messager.alert('��ʾ', data.errorMessage, 'error');
			}
		}
	});
	
});*/

	 var userDataGrid;   
	
	$(function(){
		userDataGrid =$('#table_user').treegrid({
			//���������ַ
			url : ctx+'/common/configur!getBusProcList.action?time='+new Date(),
			//��ʶ�ֶ�
			idField : 'id',
			fit : true,
			border : false,
			//�Ƿ�����������һ����ʾ�кŵ���
			rownumbers:true,
			fitColumns : true,
			pagination:true,
			//pageSize:20,
			treeField : 'name',
			columns :[[ 
				//ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
				{field:'name', title:'ҵ������',align :'left', width:70,},	
				{field:'procdefid', title:'���̶���ID',align :'center', width:30,},
				{field:'create_date',	title:'��������', align :'center',width:30},
				{field:'change_date',	title:'�޸�����',align :'center', width:30},
			]],
			//��ͷ����ӹ�������
			toolbar :'#tb'
				
//				[
//			     {
//				id : 'user_edit',
//				text:'����',
//				iconCls:'icon-pencil',
//				disabled : true,
//				handler:doEdit
//			}
//			]
				,
			onClickRow : function(rowIndex, rowData) {
				//�����ʱ����༭������ɾ������ť
				if(rowIndex.procdefid==null)
				{
					$('#user_edit').linkbutton('disable');
				}
				else
				{
					$('#user_edit').linkbutton('enable');
				}
				//userDataGrid.datagrid('getSelected');
				//$('#user_delete').linkbutton('enable');
			},
			onLoadSuccess : function(rowIndex, rowData) {
				//������Ͻ��á��༭������ɾ������ť
				$('#user_edit').linkbutton('disable');
			//	$('#user_edit').css('float','right');
				//$('#user_edit').css('text-align','right');
				//$('#user_delete').linkbutton('disable');
			}
		});
	
	
	
	//ѡ������ĳһ�е����ݡ�
	function getSelected(func){
		var selectedrow = userDataGrid.datagrid('getSelected');
		
		if (selectedrow){
			row = selectedrow;
			//������غ���
			func.call(this,selectedrow);
		}
		else{
			
			$.messager.alert('��ʾ��','����ѡ�б���е�ĳһ��.');
		}
	};

	

	
	


	//˫�������ĳһ�еĴ������¼�
	function rowDblclick(rowIndex,row){
		var i = 0;
		var props = [];
		
		for(var p in row){
			props[i++]= p+' = '+row[p];
			
		}
		//alert(props.join(',\n'));
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
		//url: 'userDelegate/getUserList.run',
		success: function(data){
			userDataGrid.datagrid('loadData',data);
		}
	});
	
});

	//����
	function doEdit() {
		var row = $('#table_user').datagrid('getSelected');
		var user;
		//alert(row);
		openInTopWindow({
			title:'����ǰ���',
			//����Ԫ�ص�id
			id: 'edit_user_win',
			//����iframe��src
			src: '../jsp/common/preaudit.jsp',
			//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy: true,
			//���ڿ�
			width: 900,
			//���ڸ�
			height: 650,
			modal: true,
			//������iframe��window�����onLoad�ص���������
			onLoad:	function(){
				//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
				//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
				this.openerWindow = window;
				//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
				this.args = {
					user : row,
					userDataGrid : userDataGrid
				};
				//alert(JSON.stringify(row));
				this.init(row);
				//this.document.getElementById('name').value='����';
			}
		});
	};
	
function submit1(){
	$('#simpleform').submit();
}
