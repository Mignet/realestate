/*********************************************************************************
*��  ��  ��  ��: mortcanacc.js
*��  ��  ��  ��: ע����ѺȨ�Ǽ������js
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: panda
*��  ��  ��  �ڣ� 2014-02-28
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
var proc=this.parent.proc;
var proc_node = proc.activName;
var proc_id = proc.procId;
//var proc_id = 4;//1000016366;
//var proc_node = "����";
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
	//����ע���Ǽ�����
	var zxrq = $("#CAN_MORT_DATE").val();
	if( $.trim(zxrq).length==0){
		$("#CAN_MORT_DATE").val(getTime());
	}
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
		checkbox:'ck',
		// �Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		 //checkOnSelect:false,
		// �Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		 selectOnCheck:false,
		columns : [ [
		     		// ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������

		     		 {field:'ck',checkbox:true},
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
		     				
		     				//return '<span><input class="search"  onclick=""/></span>';
		     				 var btn = '<a class="editcls" onclick="" href="javascript:void(0)">�鿴</a>';  
		                     return btn;		
		     			}
		     		}
		     		] ],
		     		// ��ͷ����ӹ�������
		     		onClickRow : function() {
		     		},
		     		onClickCell:function(rowIndex, field, value){
		     			if(field=="button"){
		    				$('#table_house').datagrid('selectRow',rowIndex);
		    				dowatch(this);
		    			}
		    		},onCheck:function(rowIndex,data){
		    			//alert(JSON.stringify(data));
		    		},
		     		onLoadSuccess : function(data) {
		     			 $('.editcls').linkbutton({text:'�鿴'});
		     		}
	});
    	mortgager = $('#table_transferor').datagrid({
		title:'��Ѻ��',
		height:200,
		// ���������Դ
		url :ctx+"/mortgage/morsetup!getMortCancelMortgager.action?time="+new Date()+"&proc_id="+proc_id+"&apptype=063003",
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
		     			formatter :dicts.format.app_type_format
		     			
		     		}, {
		     			title : '֤������',
		     			field : 'APP_CER_TYPE',
		     			formatter : dicts.format.app_cer_type_format
		     			
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
		     			formatter : dicts.format.app_cer_type_format
		     			
		     		},
		     		
		     		
		     		{
		     			title : '������֤������',
		     			field : 'AGENT_CER'
		     			
		     		}, {
		     			title : '��������ϵ�绰',
		     			field : 'AGENT_TEL '
		     			
		     		}

		     		] ],
		     	// ��ͷ����ӹ�������
		    		toolbar : [ {
		    			id : 'mortgager_edit',
		    			text : '�༭',
		    			iconCls : 'icon-pencil',
		    			disabled : true,
		    			handler : mortgagerEdit
		    		}, '-', {
		    			id : 'mortgager_delete',
		    			text : 'ɾ��',
		    			iconCls : 'icon-remove',
		    			disabled : true,
		    			handler : mortgagerDel
		    		}],
		     		// ��ͷ����ӹ�������
		     		onClickRow : function() {
		     			$('#mortgager_edit').linkbutton('enable');
	     				$('#mortgager_delete').linkbutton('enable');
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
	
	
	
	
	

	//������������Ϣ��
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
		// ��ͷ����ӹ�������
		toolbar : [ {
			id : 'user_edit',
			text : '�༭',
			iconCls : 'icon-pencil',
			disabled : true,
			handler : mortgagerEdit
		}, '-', {
			id : 'user_delete',
			text : 'ɾ��',
			iconCls : 'icon-remove',
			disabled : true,
			handler : mortgagerDel
		}],
		onClickRow : function() {
			//�����ʱ����༭������ɾ������ť
			
			$('#user_edit').linkbutton('enable');
			$('#user_delete').linkbutton('enable');
		
		},
		onLoadSuccess : function() {
			//������Ͻ��á��༭������ɾ������ť
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
			
			
		}

	});
	
	 getPreRegMess();
	

	
	
});
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
					userDataGrid : userDataGrid
				};
				this.init(proc_id);
			}
		});
	}
	/**********************************************************************************
	*��������: doEdit
	*����˵��: �༭��ѺȨ����Ϣ
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
	***********************************************************************************/
	function doEdit() {
		var row = userDataGrid.datagrid('getSelected');

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
	*��������: doDelete
	*����˵��: ɾ����ѺȨ����Ϣ
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
	***********************************************************************************/
	function doDelete() {
		var row = userDataGrid.datagrid('getSelected');
		top.$.messager.confirm('ȷ��', 'ȷ��Ҫɾ������������Ϊ[' + row.app_name + ']��', function(
				result) {
			if (result) {
				$.ajax({
					url : ctx+"/houseownership/initialreg!deleteApplicant.action?time="+new Date(),
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
			}
		});
	}

	/**********************************************************************************
	*��������: submit
	*����˵��: ���ύ����
	*����˵��: ��
	*�� �� ֵ: ��
	*��������: panda
	*��������: 2014-03-01
	*�޸���ʷ: 
	***********************************************************************************/
function submit(){
	if(proc_node == state1.string0){
		saveRegInfo();		
	}	
}
/**********************************************************************************
*��������: saveRegInfo
*����˵��:  �Ǽ���Ϣ������²���
*����˵��: ��
*�� �� ֵ: ��
*��������: panda
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function saveRegInfo() {
	//��֤��Ѻ����Ϣ����ѺȨ����Ϣ��ע��ԭ���Ƿ�Ϊ��
	  if(!checkMortgagee()){
		  return;
	  }
	  var CAN_MORT_DATE = $("#CAN_MORT_DATE").val();
	  var CAN_MORT_REASON = $("#CAN_MORT_REASON").val();
	  $.ajax({
	   		dataType:'json',
	   		url:ctx+"/mortgage/morsetup!saveMortMess.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"mort.can_mort_date":CAN_MORT_DATE,"mort.can_mort_reason":CAN_MORT_REASON},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		top.$.messager.alert('����ɹ���ʾ',"����ɹ�",'info',function(){
			 			getPreRegMess();
					});	
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',"����ʧ��",'error');
				}
	   		}
	   	});  
	

}


/**********************************************************************************
*��������: getPreRegMess
*����˵��: ��ȡ������ǰ�ô��ڴ��ݵĵǼ���Ϣ
*����˵��: ��
*�� �� ֵ: ��
*��������: panda
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function getPreRegMess(){
	$.ajax({
		    dataType: 'json',
			url:ctx+"/mortgage/morsetup!getMortcanMess.action?time="+new Date()+"&proc_id="+proc_id,
			success:function(data){
			 	if(data){
			 		//alert(JSON.stringify(data));
			 		$("#REG_CODE").val(data.REG_CODE);
			 		$("#REG_STATION").combodict("setValue",data.REG_STATION);
			 		$("#BUS_DETAIL").val(data.PROC_NAME);
			 		$("#REG_TYPE").combodict("setValue",data.REG_TYPE);
			 		if(data.CAN_MORT_REASON){
			 		$("#CAN_MORT_REASON").val(data.CAN_MORT_REASON);	
			 			
			 		}
			 		if(data.CAN_MORT_DATE){
			 			
			 			var qsrq = data.CAN_MORT_DATE;
			 			$("#CAN_MORT_DATE").val(qsrq);//qsrq.substr(0,qsrq.length-11));	
			 			
			 		}
			 	}
				}
		});
	
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
	var time = year+"-"+month+"-"+date+" "+mytime;
	return time;
	
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
	var CAN_MORT_REASON = $("#CAN_MORT_REASON").val();
	if($.trim(CAN_MORT_REASON).length==0){
		top.$.messager.alert('��ʾ', '��¼��ע��ԭ��', 'info',
				function(){
					
				});	
		return false;

	}
	return true;
}
/**********************************************************************************
*��������: validate
*����˵��: ע����Ѻ�Ǽ���Ϣ��ʽУ��
*����˵��: 
*�� �� ֵ: ��
*��������: panda
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function validate(){
	//У���Ѻ�����˺͵�ѺȨ���Ƿ�Ϊ��
	if(!checkMortgagee()){
		return;
	};
	//ע��ԭ��ǿ�У��
	var can_reason = new tt.Field("ע��ԭ��","CAN_MORT_REASON"); 
	tt.vf.req.add(can_reason);
	
	return tt.validate();
	
}










