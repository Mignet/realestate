/*********************************************************************************
*��  ��  ��  ��: presaleacc.js
*��  ��  ��  ��: Ԥ�۱��������
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Joyon
*��  ��  ��  �ڣ� 2014-03-01
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/

//��ʼ������.	
var proc=this.parent.proc;
//var serialNumber ={num1:"�ǼǱ��",num2:"�鵵��",num3:"���ز�֤��"};
//
var proc_node ="����";// proc.activName;
var proc_id = proc.procId;

var tmpPreSaler;
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
var transferorDataGrid;
var houseDataGrid;
$(function() {
	
	 
	
	houseDataGrid = $('#table_house').datagrid({
		title:'������Ϣ',
		height:240,
		//url:ctx+"/mortgage/morsetup!getHouseInfo.action?time="+new Date()+"&proc_id="+proc_id,
		
		url:ctx+"/backup/presale!getPreSaleInfo.action?time="+new Date()+"&proc_id="+proc_id,
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
		     			title : '��Ŀ����',
		     			field : 'LAND_ADDRESS',
		     			width:100
		     			
		     		},
		     		
		     		
		     		{
		     			title : '����',
		     			field : 'BUILDING_NAME',
		     				width:100
		     		}, 
		     		{
		     			title : '����',
		     			field : 'BUILD_NO',
		     			width:80
		     			
		     		}, 
		     		{
		     			title : 'Ԥ�����֤��',
		     			field : 'cerno',
		     			width:100
		     			
		     		},  {
		     			title : 'Ԥ�ۺ�ͬ��',
		     			field : 'con',
		     			width:100
		     			
		     		},
		     		 {
		     			title : 'Ԥ����',
		     			field : 'presale',
		     			width:50
		     			
		     		}, {
		     			title : 'Ԥ����',
		     			field : 'prebuy',
		     			width:50
		     			
		     		},{
		     			title : '�Ƿ񼺱���',
		     			field : 'rec_status',
		     		},
		     		{
		     			title : '����',		     		
		     			field:'button',
		     			formatter:function(value,rec){return '<span><input  type="button" value="�鿴" onclick=""/></span>';}
		     		}
		     		

		     		] ],
		     		// ��ͷ����ӹ�������
		     		onClickRow : function() {
		     		},
		     		onLoadSuccess : function(data) {
		     			
		     		},
		     		onClickCell:function(rowIndex, field, value){
		    			//���Ԥ����ʱ��Ԥ��������ҳ
		    			if(field=="prebuy"){
		    				//alert();
		    				//$('#table_user').datagrid('selectRow',rowIndex);
		    				//btn_bl(this);
		    				var obj={	
		    						id:"open_prebyer",
		    						src:ctx+"/jsp/backup/pre-buyer.jsp",
		    						//���ڿ�
		    						width: 800,
		    						//���ڸ�
		    						title:'Ԥ������Ϣ',
		    						height: 566,
		    						modal: true,	
		    						destroy:true,
		    						onLoad:	function(){
		    							//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
		    							//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
		    							//this.openerWindow = window;
		    							//this.document.getElementById('name').value='����';
		    							//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
//		    							this.args = {
//		    								user: selectedrow,
//		    								userDataGrid: userDataGrid
//		    							};			
		    							this.init(proc);
		    						}
		    					};	
		    				
		    				openInTopWindow(obj);
		    			}
		    			
		    			if(field=="button"){
		    				$('#table_house').datagrid('selectRow',rowIndex);
		    				dowatch(this);
		    			}
		    			
		    		}
	});
	
	/*
	transferorDataGrid = $('#table_transferor').datagrid({
		title:'��Ѻ��',
		height:200,
		// ���������Դ
		url :ctx+"/mortgage/morsetup!getHolder.action?time="+new Date()+"&proc_id="+proc_id,
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
		     			title : 'Ԥ�۷�',
		     			field : 'HOL_NAME'
		     			
		     		}, {
		     			title : 'Ԥ�۷�����',
		     			field : 'HOL_TYPE'
		     			
		     		}, {
		     			title : '֤������',
		     			field : 'HOL_CER_TYPE'
		     			
		     		}, {
		     			title : '֤�����',
		     			field : 'HOL_CER_NO'
		     			
		     		}, {
		     			title : '�ݶ�',
		     			field : 'PORTION'
		     			
		     		}, {
		     			title : '��ַ',
		     			field : 'HOL_ADDRESS'
		     			
		     		}, {
		     			title : '����������',
		     			field : 'LEGAL_NAME'
		     		
		     		},  {
		     			title : '������',
		     			field : 'AGENT_NAME'
		     			
		     		}, 
		     		{
		     			title : '������֤������',
		     			field : 'AGENT_CER_TYPE'
		     			
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
		     		onClickRow : function() {
		     		},
		     		onLoadSuccess : function() {
		     			
		     			
		     		}
	});
	*/
	
	//����Ԥ�۷���Ϣ��
	 userDataGrid = $('#table_user').datagrid({
		//fit : true,
		title:'Ԥ�۷���Ϣ',
		height:200,
		// ���������Դ
		url :ctx+"/backup/presale!getPreSaler.action?time="+new Date()+"&proc_id="+proc_id,
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
			title : 'Ԥ�۷�',
			field : 'app_name',
			width:80
			
		},{
			title : '����������',
			field : 'legal_name',
			width:100
		
		}, 
		{
			title : '֤������',
			field : 'app_cer_type',
			width:100
			
		}, {
			title : '֤�����',
			field : 'app_cer_no',
			width:100
			
		},    {
			title : '������',
			field : 'agent_name',
			width:50
			
		}, 
		{
			title : '������֤������',
			field : 'agent_cer_type',
			width:100
			
		},	{
			title : '������֤������',
			field : 'agent_cer',
			width:100
			
		}, {
			title : '��ϵ�绰',
			field : 'agent_tel',
			width:100
			
		}

		] ],
		// ��ͷ����ӹ�������
		toolbar : [ {
			id : 'user_add',
			text : '����',
			iconCls : 'icon-add',
			handler : doAdd
		}, '-', {
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

	// ����
	function doAdd() {
		openInTopWindow({
			// ����Ԫ�ص�id
			id : 'add_user_win',
			// ����iframe��src
			src : ctx+'/jsp/common/applicant/addapplicant.jsp?time='+new Date(),
			// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy : true,
			// ���ڱ���
			title : '����Ԥ�۷�',
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
				this.init(proc_id,'063014');
			}
		});
	}

	// �༭
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
			title : '�༭Ԥ�۷�',
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

	// ɾ��
	function doDelete() {
		var row = userDataGrid.datagrid('getSelected');
		top.$.messager.confirm('ȷ��', 'ȷ��Ҫɾ��Ԥ�۷�����Ϊ[' + row.app_name + ']��', function(
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
	

	// ��������ʵ����ѯ
	function searchProcint() {
		var fields = $("#procinstSearchform").serializeArray();
		var o = {};
		jQuery.each(fields, function(i, field) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];

			}
		});
		// console.debug(o);
		$('#dg_procinst').datagrid('load', o);

	}
	

	function test() {
		var panel = $('.plui-layout').layout('panel', 'north');
		panel.panel({
			height : 143
		});
		$('.plui-layout').layout('resize');
	}
	




function setState(proc_node) {
	
	if(proc_node == state1.string0){
		$("#REG_STATION").combo('disable');
	} 
	if(proc_node == state1.string1){
		$("#REG_STATION").combo('disable');
		//$(":input").attr("disabled", "disabled");
		$(".edit_table").attr("disabled", "disabled");	
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		
	}
	if (!(proc_node == state1.string0)&&!(proc_node == state1.string1)) {
		$("#REG_STATION").combo('disable');
		$(":input").attr("disabled", "disabled");
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		
		
		

	}

}
//�ж�ִ�е���saveDjxx()����saveFdccfj(proc_node)
function submit(){
	if(proc_node == state1.string0){
		saveRegInfo();		
	}	
	
}

// �Ǽ���Ϣ������²���
function saveRegInfo() {
	var reg_station = $("#REG_STATION").combo('getValue');
	var reg_code = $("#REG_CODE").val();
	var reg_type = $("#REG_TYPE").combo('getValue');
	var proc_name = $("#BUS_DETAIL").val();
	var location_reg_unit = $("#LOCATION_REG_UNIT").combo('getValue');
	if($.trim(reg_station).length==0){
		top.$.messager.alert('��ʾ', '��ѡ��Ǽǵ㣡', 'info',
				function() {
					
				});	
		return;

	}
	
	  $.ajax({
	   		dataType:'json',
	   		url:ctx+"/mortgage/morsetup!saveRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"oivo.reg_station":reg_station,"oivo.reg_code":reg_code,"oivo.reg_type":reg_type,"oivo.proc_name":proc_name,"oivo.location_reg_unit":location_reg_unit},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		top.$.messager.alert('����ɹ���ʾ',"����ɹ�",'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',"����ʧ��",'error');
				}
	   		}
	   	});  
	

}
//��ȡ������ǰ�ô��ڴ��ݵĵǼ���Ϣ
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
			 		if(data.LOCATION_REG_UNIT){
			 			$("#LOCATION_REG_UNIT").combodict("setValue",data.LOCATION_REG_UNIT);
			 		}else{
			 			
			 		}
			 	}
				}
		});
	
}



/**********************************************************************************
*��������: ҳ��У�鷽��
*����˵��: ��֤ҳ���ϵķǿ�  �����ݸ�ʽ   
*����˵��: v_flag ��ֵ�����Ǳ������
*�� �� ֵ: obj  result(trueͨ��  false��ͨ��) message(��Ϣ)  page_name(��ǰҳ������)
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function validate(v_flag){
	//���ؽ������
	var result ={
			result:true,
			message:'',
			page_name:'�����'
	}
	var location_reg_unit =  $("#LOCATION_REG_UNIT").combo('getValue');
	if($.trim(location_reg_unit).length==0){
		message = '��ѡ�񷿵ز���������';
		result.message=message;
		 result.result=false;
		 return result;

	}
	//��ʾ��Ϣ 
	var message;
	
	return result;
}


/**********************************************************************************
*��������: dowatch
*����˵��: �鿴�Ǽǵ�Ԫ��ϸ��Ϣ
*����˵��: ��
*�� �� ֵ: ��
*��������: xuzz
*��������: 2014-03-27
*�޸���ʷ: 
***********************************************************************************/
function dowatch(button){
		var row = $('#table_house').datagrid('getSelected');
		var obj={};
		 	obj.WHERE_CODE=row.CODE;
			obj.REG_UNIT_TYPE=row.TYPE;
		//alert(JSON.stringify(row));
		openInTopWindow({
			// ����Ԫ�ص�id
			id : 'add_user_win',
			// ����iframe��src
			src : ctx+'/bookmanage/book-manage!home.action?reg_unit_type='+row.TYPE+'&time='+new Date(),
			// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy : true,
			// ���ڱ���
			title : '���ز���Ϣ',
			// ���ڿ�
			width : 950,
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
				this.init(obj);
			}
		});
}




