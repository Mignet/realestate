/*********************************************************************************
*��  ��  ��  ��: mort-change-acc.js
*��  ��  ��  ��: һ���ѺȨ����Ǽ������js
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: panda
*��  ��  ��  �ڣ� 2014��4��9�� 10:33:28
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
//��ʼ������.	
var proc=this.parent.proc;
var proc_node = proc.activName;
var proc_id = proc.procId;
//var proc_id = 4;//1000016366;
//var proc_node = "����";
//��ǰ��Ԫ�ص�ֵ
var _cur_form_data;
//��ʼ��ʱ��Ԫ�ص�ֵ
var _init_form_data;
var state1 = {
		string0: "����",
		string1 : "����",
		string2 : "����",
		string3 : "���",
		string4 : "��׼",
		string5 : "������",
		string6 : "������",
		string7: "����������",
		string8 : "����������",		
		string9 : "��֤",
		string10 : "��֤",
		string11: "�鵵",
		string12: "����",
		string13:"�ⶨ����"
	};
var userDataGrid;
var mortgager;
var houseDataGrid;
/**********************************************************************************
*��������: 
*����˵��: ҳ���ʼ��
*��������: panda
*��������: 2014-02-28
*�޸���ʷ: 
***********************************************************************************/
$(function() {
	houseDataGrid = $('#table_house').datagrid({
		title:'���ز���Ϣ',
		height:240,
		url:ctx+"/mortgage/morsetup!getRegunitMess.action?time="+new Date()+"&proc_id="+proc_id,
		// ���ÿ�п���Զ���Ӧ����ܿ��
		autoRowHeight : true,
		fitColumns : true,
		// ȥ���߿�
		border : true,
		striped : true,
		// �Ƿ��з�ҳ��
		pagination : true,
		// pagePosition:'top',
		// ÿҳ����
		pageSize : 10,
		// �Ƿ�����������һ����ʾ�кŵ���
		rownumbers : true,
		// ����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		//idField : 'jjclmc',
		// �������Ƿ�����ʾ��ͬ����ɫ
		striped : true,
		// ֻ����ѡһ��
		singleSelect : true,
		
		columns : [ [
		     		// ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������

		     		// {field:'ck',checkbox:true},
		     		
		     		{
		     			title : '�ڵغ�',
		     			field : 'PARCEL_CODE',
		     			width:80
		     			
		     		}, {
		     			title : '�ڵ�����',
		     			field : 'LAND_ADDRESS',
		     			width:100
		     			
		     		},
		     		
		     		
		     		{
		     			title : '����������',
		     			field : 'BUILDING_NAME',
		     				width:100
		     		}, 
		     		{
		     			title : '����',
		     			field : 'BUILD_NO',
		     			width:50
		     			
		     		}, 
		     		{
		     			title : '����',
		     			field : 'ROOMNAME',
		     			width:50
		     			
		     		},  {
		     			title : '��Ŀ����',
		     			field : 'PRO_NAME',
		     			width:100
		     			
		     		}, {
		     			hidden: true,
		     			field : 'TYPE',
		     			width:100
		     			
		     		}, {
		     			hidden: true,
		     			field : 'CODE',
		     			width:100
		     			
		     		},
		     		{
		     			title : '����',		     		
		     			field:'button',
		     			formatter:function(value,rec){
		     				 var btn = '<a class="editcls" onclick="" href="javascript:void(0)">�鿴</a>';  
		                     return btn;
		     			
		     			}
		     		}
		     		

		     		] ],
		     		// ��ͷ����ӹ�������
		     		onClickCell:function(rowIndex, field, value){
		     			if(field=="button"){
		    				$('#table_house').datagrid('selectRow',rowIndex);
		    				dowatch(this);
		    			}
		    		},
		     		onLoadSuccess : function() {
		     			$('.editcls').linkbutton({text:'�鿴'});
		     			
		     		}
	});
	
	
	mortgager = $('#table_transferor').datagrid({
		title:'��Ѻ��',
		height:200,
		// ���������Դ
		url :ctx+"/mortgage/morsetup!getChangeMortgager.action?time="+new Date()+"&proc_id="+proc_id+"&apptype=063003",
		// ���ÿ�п���Զ���Ӧ����ܿ��
		autoRowHeight : true,
		fitColumns : false,
		// ȥ���߿�
		border : true,
		striped : true,
		// �Ƿ��з�ҳ��
		pagination : true,
		// pagePosition:'top',
		// ÿҳ����
		pageSize : 10,
		// �Ƿ�����������һ����ʾ�кŵ���
		rownumbers : true,
		// ����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		//idField : 'jjclmc',
		// �������Ƿ�����ʾ��ͬ����ɫ
		striped : true,
		// ֻ����ѡһ��
		singleSelect : true,
		
		columns : [ [
		     		// ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������

		     		// {field:'ck',checkbox:true},
		     		{
		            	hidden:  true,
		               	field : 'HOL_REL'
			
		                   },
		                   {
				            hidden:  true,
				            field : 'REG_UNIT_CODE'
					
				           },
		                   {
		           			hidden:  true,
		           			field : 'APPLICANT_ID'
		           			
		           		}, 
		      		{
		     			title : '������',
		     			field : 'APP_NAME'
		     			
		     		}, {
		     			title : '����������',
		     			field : 'APP_TYPE',
		     			formatter : function(value) {
		    				if(value == '043001'){
		    					
		    					return '����';
		    				};
		    				if(value == '043002'){
		    					
		    					return '��λ';
		    				}
		    		
		    			}
		     			
		     		}, {
		     			title : '֤������',
		     			field : 'APP_CER_TYPE',
		     			formatter : function(value) {
		    				if(value == '002001'){
		    					
		    					return '���֤';
		    				};
		    				if(value == '002002'){
		    					
		    					return '����֤';
		    				}
		    		
		    			}
		     			
		     		}, {
		     			title : '֤�����',
		     			field : 'APP_CER_NO'
		     			
		     		}, {
		     			title : '�ݶ�',
		     			field : 'APP_PORT'
		     			
		     		}, {
		     			title : '��ַ',
		     			field : 'APP_ADDRESS'
		     			
		     		}, {
		     			title : '����������',
		     			field : 'LEGAL_NAME'
		     		
		     		},  {
		     			title : '������',
		     			field : 'AGENT_NAME'
		     			
		     		}, 
		     		{
		     			title : '������֤������',
		     			field : 'AGENT_CER_TYPE',
		     			formatter : function(value) {
		    				if(value == '002001'){
		    					
		    					return '���֤';
		    				};
		    				if(value == '002002'){
		    					
		    					return '����֤';
		    				}
		    		
		    			}
		     			
		     		},
		     		
		     		
		     		{
		     			title : '������֤������',
		     			field : 'AGENT_CER'
		     			
		     		}, {
		     			title : '��������ϵ�绰',
		     			field : 'AGENT_TEL '
		     			
		     		}

		     		] ],
		     		toolbar : [ {
		    			id : 'holder_edit',
		    			text : '�༭',
		    			iconCls : 'icon-pencil',
		    			disabled : true,
		    			handler : mortgagerEdit
		    		}, '-', {
		    			id : 'holder_delete',
		    			text : 'ɾ��',
		    			iconCls : 'icon-remove',
		    			disabled : true,
		    			handler : mortgagerDel
		    		}],
		     		// ��ͷ����ӹ�������
		     		onClickRow : function() {
		     			if(proc_node == state1.string0){
		     				$('#holder_edit').linkbutton('enable');
		     				$('#holder_delete').linkbutton('enable');
		     				}
		     			
		     		},
		     		onLoadSuccess : function() {
		     			
		     			
		     		}
	});
	/**********************************************************************************
	*��������: mortgagerEdit
	*����˵��: �༭��Ѻ��
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
	***********************************************************************************/
	function mortgagerEdit() {
		var row = mortgager.datagrid('getSelected');
    // alert(JSON.stringify(row));
		openInTopWindow({
			// ����Ԫ�ص�id
			id : 'edit_user_win',
			// ����iframe��src
			src : ctx+'/jsp/common/applicant/editapplicant.jsp',
			// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy : true,
			// ���ڱ���
			title : '�༭������',
			// ���ڿ�
			width : 700,
			// ���ڸ�
			height : 400,
			modal : true,
			// ������iframe��window�����onLoad�ص���������
			onLoad : function() {
				// �˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
				// ��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
				this.openerWindow = window;
				// ����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
				this.args = {
					user : row,
					userDataGrid : mortgager
				};
				this.init(row);
			}
		});
	};
	/**********************************************************************************
	*��������: mortgagerDel
	*����˵��: ɾ����Ѻ��
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
	***********************************************************************************/
	
	function mortgagerDel() {
		var row = mortgager.datagrid('getSelected');
		top.$.messager.confirm('ȷ��', 'ȷ��Ҫɾ������������Ϊ[' + row.APP_NAME + ']��', function(
				result) {
			if (result) {
				$.ajax({
					url : ctx+"/houseownership/initialreg!deleteApplicant.action?time="+new Date(),
					type : 'post',
					data : {
						applicant_id : row.APPLICANT_ID
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							top.$.messager.alert('��ʾ', data.tipMessage, 'info',
									function() {
								        //alert("ɾ��֮��ˢ��");
								     mortgager.datagrid('reload');
									});
						} else {
							top.$.messager.alert('��ʾ', data.errorMessage, 'error');
						}
					}
				});
			}
		});
	}
	//������ѺȨ����Ϣ��
	 userDataGrid = $('#table_user').datagrid({
		//fit : true,
		title:'��ѺȨ��',
		height:200,
		// ���������Դ
		url :ctx+"/mortgage/morsetup!getChangeMortgagee.action?time="+new Date()+"&proc_id="+proc_id+"&apptype=063004",
		// ���ÿ�п���Զ���Ӧ����ܿ��
		autoRowHeight : true,
		fitColumns : false,
		// ȥ���߿�
		border : true,
		striped : true,
		// �Ƿ��з�ҳ��
		pagination : true,
		// pagePosition:'top',
		// ÿҳ����
		pageSize : 10,
		// �Ƿ�����������һ����ʾ�кŵ���
		rownumbers : true,
		// ����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		//idField : 'jjclmc',
		// �������Ƿ�����ʾ��ͬ����ɫ
		striped : true,
		// ֻ����ѡһ��
		singleSelect : true,
		// �Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		// checkOnSelect:true,
		// �Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		// selectOnCheck:true,
		// ����������
		columns : [ [
		// ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������

		// {field:'ck',checkbox:true},
		{
			hidden:  true,
			field : 'HOL_REL'
			
		}, 
		{
			hidden:  true,
			field : 'APPLICANT_ID'
			
		}, 
		{
			title : '������',
			field : 'APP_NAME'
			
		}, {
			title : '����������',
			field : 'APP_TYPE',
			formatter : function(value) {
				if(value == '043001'){
					
					return '����';
				};
				if(value == '043002'){
					
					return '��λ';
				}
		
			}
			
		}, {
			title : '֤������',
			field : 'APP_CER_TYPE',
			formatter : function(value) {
				if(value == '002001'){
					
					return '���֤';
				};
				if(value == '002002'){
					
					return '����֤';
				}
		
			}
			
		}, {
			title : '֤�����',
			field : 'APP_CER_NO'
			
		}, {
			title : '�ݶ�',
			field : 'APP_PORT'
			
		}, {
			title : '��ַ',
			field : 'APP_ADDRESS'
			
		}, {
			title : '��ϵ�绰',
			field : 'APP_TEL'
			
		}, {
			title : '����������',
			field : 'LEGAL_NAME'
		
		},  {
			title : '������',
			field : 'AGENT_NAME'
			
		}, 
		{
			title : '������֤������',
			field : 'AGENT_CER_TYPE',
			formatter : function(value) {
				if(value == '002001'){
					
					return '���֤';
				};
				if(value == '002002'){
					
					return '����֤';
				}
		
			}
			
		},
		
		
		{
			title : '������֤������',
			field : 'AGENT_CER'
			
		}, {
			title : '��������ϵ�绰',
			field : 'AGENT_TEL'
			
		}

		] ],
		// ��ͷ����ӹ�������
		toolbar : [ 
//		            {
//			id : 'user_add',
//			text : '����',
//			iconCls : 'icon-add',
//			handler : doAdd
//		}, '-',
			{
			id : 'user_edit',
			text : '�༭',
			iconCls : 'icon-pencil',
			disabled : true,
			handler : doEdit
		}, '-', {
			id : 'user_delete',
			text : 'ɾ��',
			iconCls : 'icon-remove',
			disabled : true,
			handler : doDelete
		}],
		onClickRow : function() {
			//�����ʱ����༭������ɾ������ť
			if(proc_node == state1.string0){
			$('#user_edit').linkbutton('enable');
			$('#user_delete').linkbutton('enable');
			}
		},
		onLoadSuccess : function() {
			//������Ͻ��á��༭������ɾ������ť
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
			
			
		}

	});
	
	 
	 
	 getPreRegMess();
	 getMortMess();

	//����Ȩ��״̬
	setState(proc_node);
	
	document.getElementById("submit").onclick = function() {
		submit();
    };
	
	
});
	
	// ѡ������ĳһ�е����ݡ�
	function getSelected(func) {
		var selectedrow = $('#table_user').datagrid('getSelected');

		if (selectedrow) {
			row = selectedrow;
			// ������غ���
			func.call(this, selectedrow);
		} else {

			$.messager.alert('��ʾ��', '����ѡ�б���е�ĳһ��.');
		}
	}
	/**********************************************************************************
	*��������: dowatch
	*����˵��: �鿴�Ǽǵ�Ԫ��ϸ��Ϣ
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
	***********************************************************************************/
		function dowatch(button){
			var row = $('#table_house').datagrid('getSelected');
			//alert(JSON.stringify(row));
			openInTopWindow({
				// ����Ԫ�ص�id
				id : 'add_user_win',
				// ����iframe��src
				//src : ctx+'/common/applicant/addapplicant.action?time='+new Date(),
				// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
				destroy : true,
				// ���ڱ���
				title : '���ز���Ϣ',
				// ���ڿ�
				width : 800,
				// ���ڸ�
				height : 600,
				modal : true,
				// ������iframe��window�����onLoad�ص���������
				onLoad : function() {
					// �˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
					// ��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
					this.openerWindow = window;
					// ����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
					this.args = {
						userDataGrid : userDataGrid,
						regunit:row
					};
					this.init(proc_id);
				}
			});
		}
	/**********************************************************************************
	*��������: doAdd
	*����˵��: ������ѺȨ��
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
	***********************************************************************************/
	function doAdd() {
		var hol_rel = "063004";							//Ĭ��Ȩ���˹�ϵΪ��ѺȨ��
		openInTopWindow({
			// ����Ԫ�ص�id
			id : 'add_user_win',
			// ����iframe��src
			src : ctx+'/jsp/common/applicant/addapplicant.jsp?time='+new Date(),
			// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy : true,
			// ���ڱ���
			title : '����������',
			// ���ڿ�
			width : 700,
			// ���ڸ�
			height : 400,
			modal : true,
			// ������iframe��window�����onLoad�ص���������
			onLoad : function() {
				// �˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
				// ��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
				this.openerWindow = window;
				// ����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
				this.args = {
					userDataGrid : userDataGrid
				};
				this.init(proc_id,hol_rel);
			}
		});
	}

	/**********************************************************************************
	*��������: doAdd
	*����˵��: �༭��ѺȨ��
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
	***********************************************************************************/
	function doEdit() {
		var row = userDataGrid.datagrid('getSelected');
    // alert(JSON.stringify(row));
		openInTopWindow({
			// ����Ԫ�ص�id
			id : 'edit_user_win',
			// ����iframe��src
			src : ctx+'/jsp/common/applicant/editapplicant.jsp',
			// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy : true,
			// ���ڱ���
			title : '�༭������',
			// ���ڿ�
			width : 700,
			// ���ڸ�
			height : 400,
			modal : true,
			// ������iframe��window�����onLoad�ص���������
			onLoad : function() {
				// �˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
				// ��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
				this.openerWindow = window;
				// ����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
				this.args = {
					user : row,
					userDataGrid : userDataGrid
				};
				this.init(row);
			}
		});
	};

	/**********************************************************************************
	*��������: doAdd
	*����˵��: ɾ����ѺȨ��
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
	***********************************************************************************/
	function doDelete() {
		var row = userDataGrid.datagrid('getSelected');
		top.$.messager.confirm('ȷ��', 'ȷ��Ҫɾ������������Ϊ[' + row.APP_NAME + ']��', function(
				result) {
			if (result) {
				$.ajax({
					url : ctx+"/houseownership/initialreg!deleteApplicant.action?time="+new Date(),
					type : 'post',
					data : {
						applicant_id : row.APPLICANT_ID
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
			}
		});
	}

	// ˫�������ĳһ�еĴ������¼�
	function rowDblclick(rowIndex, row) {
		var i = 0;
		var props = [];

		for ( var p in row) {
			props[i++] = p + ' = ' + row[p];

		}
		alert(props.join(',\n'));
		// info(row);
	}
	
	/**********************************************************************************
	*��������: setState
	*����˵��: �������̽ڵ㣬����ҳ��ؼ���Ȩ��
	*����˵��: proc_node�����̽ڵ�����
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
	***********************************************************************************/
function setState(proc_node) {
	
	
	if(proc_node == state1.string1){
		$("#REG_STATION").combo('disable');
		$(".edit_table").attr("disabled", "disabled");	
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');
		$('#user_add').linkbutton('disable');
		
		if($('#mort_reg_date').val()==null){
		$('#mort_reg_date').val(getTime());
		}
		
	}
	if (!(proc_node == state1.string0)&&!(proc_node == state1.string1)) {
		$("#REG_STATION").combo('disable');
		$(":input").attr("disabled", "disabled");
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		$("#mort_type").combo('disable');
		$(".com").combo('disable');
	}
	
	if (proc_node == state1.string4) {
		$('#user_edit').linkbutton('enable');
		$('#user_delete').linkbutton('enable');

		//$('#user_add').linkbutton('enable');

	}
    if(proc_node != state1.string0){
		
		$(".mortreg").css({display:"block"});
		//$(".remark").css({display:"block"});
	}
    
}
/**********************************************************************************
*��������: submit
*����˵��: ���ύ����
*����˵��: 
*�� �� ֵ: ��
*��������: panda
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function submit(){	
    if(proc_node == state1.string0){
		
    	return checkMortgagee();
		
	}
	if(proc_node == state1.string1){
		
		return saveMortMess();
		
	}
	return true;
}



/**********************************************************************************
*��������: getPreRegMess
*����˵��: ��ȡ�Ǽǻ�����Ϣ������Ǽǵ㡢ҵ���������Ǽ����͡��ǼǱ�ŵ�
*����˵��: 
*�� �� ֵ: ��
*��������: panda
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function getPreRegMess(){
	$.ajax({
		    dataType: 'json',
			url:ctx+"/mortgage/morsetup!getRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
			success:function(data){
			 	if(data){
			 		//alert(JSON.stringify(data));
			 		$("#REG_CODE").val(data.REG_CODE);
			 		$("#REG_STATION").combodict("setValue",data.REG_STATION);
			 		$("#BUS_DETAIL").val(data.PROC_NAME);
			 		$("#REG_TYPE").combodict("setValue",data.REG_TYPE);
			 		//$("#EXCURSUS").text(data.EXCURSUS);
			 		//$("#HOUSE_ATTR").val(data.HOUSE_KIND);
			 		if(data.MORT_MODE){
			 			//alert($("#GET_MODE").combo("getValue"));
			 			//$("#MORT_MODE").combodict("setValue",data.MORT_MODE);
			 			
			 		}
			 		
			 	 
			 	}
				}
		});
	
}
/**********************************************************************************
*��������: getMortMess
*����˵��: ��ȡ��Ѻ�Ǽ���Ϣ
*����˵��: 
*�� �� ֵ: ��Ѻ�Ǽ���Ϣjson����
*��������: panda
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function getMortMess(){
	$.ajax({
	    dataType: 'json',
		url:ctx+"/mortgage/morsetup!getChangeMortgage.action?time="+new Date()+"&proc_id="+proc_id,
		success:function(data){
		 	if(data){
		 		 $("#mort_type").combodict('setValue',data.mort_type);
		 		$("#loan_usage").val(data.loan_usage);
		 		 $("#mort_con_no").val(data.mort_con_no);
		 		//$("#rel_orig_value").val(data.rel_orig_value);
		 		//$("#mort_assure_right").val(data.mort_assure_right);
		 		 //$("#assess_price").val(data.assess_price);
		 		//$("#agreed_price").val(data.agreed_price);
		 		$("#borrower").val(data.borrower);
		 		$("#mort_port").val(data.mort_port);
		 		$("#assuer_range").val(data.assuer_range);
		 		$("#mort_seq").val(data.mort_seq);
		 		$("#max_amount").val(data.max_amount);
		 		 if(data.rel_orig_value){
			 			$("#rel_orig_value").numberbox('setValue',data.rel_orig_value);
			 		}
		 		
                if(data.mort_assure_right){
		 			
		 			
		 			$("#mort_assure_right").numberbox('setValue',data.mort_assure_right);
		 		}
		 		
		 		if(data.assess_price){
		 			
		 			
		 			$("#assess_price").numberbox('setValue',data.assess_price);
		 		}
		 		
                 if(data.agreed_price){
		 			
		 			$("#agreed_price").numberbox('setValue',data.agreed_price);
		 			
		 		}
		 		if(data.sure_amount){
		 			
		 			$("#sure_amount").numberbox('setValue',data.sure_amount);
		 			
		 		}
		 		
		 		if(data.mort_reg_date){
		 			var djrq = data.mort_reg_date;
		 			//$("#mort_reg_date").datebox('setValue',djrq.substr(0,djrq.length-11));
		 			$("#mort_reg_date").val(djrq.substr(0,djrq.length-11));
		 			
		 		}
		 		if(data.creditor_start_date){
		 			var qsrq = data.creditor_start_date;
		 			$("#creditor_start_date").datebox('setValue',qsrq.substr(0,qsrq.length-11));	
		 			
		 		}
		 		if(data.creditor_end_date){
		 			var zzrq = data.creditor_end_date;
		 			$("#creditor_end_date").datebox('setValue',zzrq.substr(0,zzrq.length-11));	
		 			
		 		}
	
		 		
		 	}
		 	//��ʼ������ֵ
		 	_init_form_data = $("#add_app_form").serializeJson();
		 
		}
	});
	
	
	
	
}
/**********************************************************************************
*��������: saveMortMess
*����˵��: �����Ѻ�Ǽ���Ϣ
*����˵��: 
*�� �� ֵ: ��
*��������: panda
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function saveMortMess(){
	var result = true;
	//����У��
//	if(!checkMortMess()){
//		
//		return;
//	}
	 $.ajax({
	   		dataType:'json',
	   		url:ctx+"/mortgage/morsetup!saveMortMess.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:$("#add_app_form").serialize(),
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		
			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
			 			getMortMess();
					});	
			 		
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
				}
	   		},error:function(){
	   			result = false;
	   		}
	   	});  
	 
	 return result;

}
/**********************************************************************************
*��������: checkMortMess
*����˵��: ����ѺȨ���Ƿ��Ѵ�������
*����˵��: 
*�� �� ֵ: ��
*��������: panda
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function checkMortMess(){
		var result ={
				result:false,
				message:""
		};
	 	var dylx = $("#mort_type").combodict('getValue');
		
		if($.trim(dylx).length==0){
			result.message = '��¼���Ѻ���ͣ�';
			return result;

		}
		var dkyt = $("#loan_usage").val();
		if($.trim(dkyt).length==0){
			result.message = '��¼�������;��';
			return result;

		}
		var dyhth = $("#mort_con_no").val();
		if($.trim(dyhth).length==0){
			result.message = '��¼���Ѻ��ͬ�ţ�';
			return result;

		}
		var fdcyz = $("#rel_orig_value").val();
		if($.trim(fdcyz).length==0){
			result.message = '��¼�뷿�ز�ԭֵ��';
			return result;

		}
		var zzq = $("#mort_assure_right").val();
		
		if($.trim(zzq).length==0){
			result.message = '��¼����ծȨ���';
			return result;

		}
		var pgj = $("#assess_price").val();
		if($.trim(pgj).length==0){
			result.message = '��¼�������ۣ�';
			return result;

		}
		var ydj = $("#agreed_price").val();
		if($.trim(ydj).length==0){
			result.message = '��¼���鶨�ۣ�';
			return result;

		}
//		var djrq = $("#mort_reg_date").datebox('getValue');
//		if($.trim(djrq).length==0){
//			result.message = '��¼��Ǽ����ڣ�';
//			return result;
//
//		}
//		
		var sure_amount =  	$("#sure_amount").numberbox('getValue');
		if($.trim(sure_amount).length==0){
			result.message =  '��¼��ȷ��������ծȨ���';
			return result;

		}
		
		var qsrq =   $("#creditor_start_date").datebox('getValue');
		if($.trim(qsrq).length==0){
			result.message =  '��¼���Ѻ��ʼ���ڣ�';
			return result;

		}
		var zzrq =   $("#creditor_end_date").datebox('getValue');
		if($.trim(zzrq).length==0){
			result.message =  '��¼���Ѻ��ֹ���ڣ�';
			return result;

		}
		var jkr = $("#borrower").val();
		if($.trim(jkr).length==0){
			result.message =  '��¼�����ˣ�';
			return result;

		}
		var dyfe = $("#mort_port").val();
		if($.trim(dyfe).length==0){
			result.message =  '��¼���Ѻ�ݶ';
			return result;

		}
		
		var max_amount = $("#max_amount").val();
		if($.trim(max_amount).length==0){
			result.message =  '��¼�����ծȨȷ����ʵ��';
			return result;

		}
	result.result=true;
	return result;
	
	
}
/**********************************************************************************
*��������: checkMortgagee
*����˵��: ����ѺȨ���Ƿ��Ѵ�������
*����˵��: 
*�� �� ֵ: ��
*��������: panda
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function checkMortgagee(){
	
	var rowlen  = userDataGrid.datagrid('getRows').length;
	 var  mortrowlen = mortgager.datagrid('getRows').length;
	if(rowlen == 0){
		top.$.messager.alert('��ʾ', '��ѺȨ����ϢΪ�գ�', 'info',
				function() {
		        
				});	
		return false;		
		
	}
	if(mortrowlen == 0){
		top.$.messager.alert('��ʾ', '��Ѻ����ϢΪ�գ�', 'info',
				function() {
					
				});	
		return false;
		
		
	}
	return true;
	
}
/**********************************************************************************
*��������: validate
*����˵��: ��Ѻ�Ǽ���Ϣ��ʽУ��
*����˵��: 
*�� �� ֵ: ��
*��������: panda
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function validate(v_flag){
	//���ؽ������
	var result ={
			result:false,
			message:'',
			page_name:'�����'
	}
	//��ʾ��Ϣ 
	var message;
	//У���Ѻ�����˺͵�ѺȨ���Ƿ�Ϊ��
//    if(proc_node == state1.string0){
//    	var rowlen  = userDataGrid.datagrid('getRows').length;
//   	 	var  mortrowlen = mortgager.datagrid('getRows').length;
//   		if(rowlen == 0){
//		   		message ='��ѺȨ����ϢΪ�գ�';
//		   		result.message = message;
//				return result;
//	   	}
//	   	if(mortrowlen == 0){
//	   		message = '��Ѻ����ϢΪ�գ�';
//	   		result.message = message;
//			return result;
//	   		
//	   		
//	   	}
//	}else 
	if(proc_node == state1.string1){
		//У���Ѻ��Ϣ�Ƿ�Ϊ��
		var tmp_result= checkMortMess();
			
	     if(!tmp_result.result){
	    	 message =tmp_result.message;
	     	 result.message = message;
	 		 return result;
		}
	}
	
     //�㱣�水Ť  ��ʼ������
     if(v_flag){
			_init_form_data = $("#add_app_form").serializeJson(); 
		}
	//���л���ǰ����ֵ
	_cur_form_data = $("#add_app_form").serializeJson();
	//alert(JSON.stringify(_init_form_data)+" "+JSON.stringify(_cur_form_data));
	var r = equal(_init_form_data,_cur_form_data);
	if(!r){
		message ="��Ԫ�����޸ģ��뱣����ύ��";
     	result.message = message;
 		return result;
	}	
	
	result.result=true;
	return result;

/**
	//��Ѻ����
	var mort_type = new tt.Field("��Ѻ����","mort.mort_type"); 
	//������;	
	var loan_usage = new tt.Field("������;","mort.loan_usage"); 
	//��Ѻ��ͬ��
	var mort_con_no = new tt.Field("��Ѻ��ͬ��","mort.mort_con_no"); 
	//���ز�ԭֵ
	var rel_orig_value = new tt.Field("���ز�ԭֵ","mort.rel_orig_value"); 
	//��Ѻ��������ծȨ����
	var mort_assure_right = new tt.Field("��Ѻ��������ծȨ����","mort.mort_assure_right"); 
	//������
	var assess_price = new tt.Field("������","mort.assess_price"); 
	//�鶨��
	var agreed_price = new tt.Field("�鶨��","mort.agreed_price");
	//��Ѻ�Ǽ�����
	var mort_reg_date = new tt.Field("��Ѻ�Ǽ�����","mort.mort_reg_date"); 
	//ծȨ��ʼ����
	var creditor_start_date = new tt.Field("ծȨ��ʼ����","mort.creditor_start_date"); 
	//ծȨ��ֹ����
	var creditor_end_date = new tt.Field("ծȨ��ֹ����","mort.creditor_end_date"); 
	//�����
	var borrower = new tt.Field("�����","mort.borrower"); 
	//��Ѻ�ݶ�
	var mort_port = new tt.Field("��Ѻ�ݶ�","mort.mort_port"); 
	//������Χ
	var assuer_range = new tt.Field("������Χ","mort.assuer_range"); 
	//��Ѻ˳λ
	var mort_seq = new tt.Field("��Ѻ˳λ","mort.mort_seq"); 
	//���ծȨȷ����ʵ
	var max_amount = new tt.Field("���ծȨȷ����ʵ","mort.max_amount"); 
	//ȷ��������ծȨ����
	var sure_amount = new tt.Field("ȷ��������ծȨ����","mort.sure_amount"); 
	//�ǿ���֤
	tt.vf.req.add(mort_type,loan_usage,mort_con_no,rel_orig_value,mort_assure_right,assess_price,agreed_price,mort_reg_date,creditor_start_date,creditor_end_date,borrower,
			mort_port,assuer_range,mort_seq,max_amount,sure_amount);
	//ծȨ��ʼ���ں���ֹ���ڱȽ� 
	new tt.CV().add(creditor_start_date).set('v', "<=", creditor_end_date); 
	
	return tt.validate(); 
*/	
}
/**********************************************************************************
*��������: getTime
*����˵��: ��ȡ��ǰϵͳʱ��
*����˵��: ��
*�� �� ֵ: ��
*��������: panda
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function getTime(){
	  var myDate = new Date();
	  var year = myDate.getFullYear();
	  var month = myDate.getMonth()+1;
	  var date = myDate.getDate(); 
     var mytime=myDate.toLocaleTimeString();     //��ȡ��ǰʱ��	  
	var time = year+"-"+month+"-"+date;
	return time;
	
}

/**********************************************************************************
*��������: pageDataIsChange
*����˵��: �жϵ�ǰҳ�������Ƿ��Ѿ��޸�
*����˵��: 
*�� �� ֵ: ���޸ķ���true   δ�޸ķ���false
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function pageDataIsChange(){
	if(proc_node == state1.string0 || proc_node == state1.string1){
		_cur_form_data = $("#add_app_form").serializeJson(); 
		
		var r = equal(_init_form_data,_cur_form_data);
		//�������ȷ���  ҳ�����ݼ��޸�  ����true
		if(!r){
		  return true;
		}
	}
	return false;
}






