//��ʼ������.	
var proc;
proc=this.parent.proc;

var serialNumber ={num1:"�ǼǱ��",num2:"�鵵��",num3:"���ز�֤��"};

var activName = proc.activName;
//var activName = '${activName}';
//var activName = "����";
//var proc_id =  '${procId}';
var proc_id = proc.procId;
//var proc_id = 1000016990;
var proc_node=proc
var _init_form_data;							//��ʼ��ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var _cur_form_data;								//��֤ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var procNode = {
		//����
		accepted: enumdata.ACCEPTED,
		//����
		examine : enumdata.EXAMINE,
		//����
		reexamine : enumdata.REEXAMINE,
		//��׼
		approved : enumdata.APPROVED,
		//����
		bulletin : enumdata.BULLETIN,
		//�������
		initialexamine : enumdata.INITIALEXAMINE,
		//�������
		initialaudit : enumdata.INITIALAUDIT,
		//������
		initialvalidation: enumdata.INITIALVALIDATION,
		//�շ�
		charge : enumdata.CHARGE,		
		//��֤
		certificate : enumdata.CERTIFICATE,
		//����
		posting : enumdata.POSTING,
		//�鵵
		file: enumdata.FILE,

	};

$(document).ready(function() {

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
			     		//ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
			     		{
			     			title : '�ڵغ�',
			     			field : 'PARCEL_CODE',
			     			width:80
			     			
			     		}, {
			     			title : '�Ǽǵ�Ԫ����',
			     			field : 'TYPE',formatter : dicts.format.reg_unit_type_format,
			     			width:100
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
			     			field : 'CODE',
			     			
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
			     			
			     			//Ϊfalseʱ��ʾ����Ǽǻ�ȡ�����Ϣ
			     			_init_form_data = $("#attach").serializeJson(); 
			     		}
		});
	
	//������������Ϣ��
	var userDataGrid = $('#table_user').datagrid({
		//fit : true,
		title:'��������Ϣ',
		height:240,
		// ���������Դ
		url :ctx+"/landuseright/landinitialreg!getAppMessage.action?time="+new Date()+"&proc_id="+proc_id,
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
			title : '������',
			field : 'app_name'
			
		}, {
			title : '����������',
			field : 'app_type'
			
		}, {
			title : '֤������',
			field : 'app_cer_type'
			
		}, {
			title : '֤�����',
			field : 'app_cer_no'
			
		}, {
			title : '�ݶ�',
			field : 'app_port'
			
		}, {
			title : '��ַ',
			field : 'app_address'
			
		}, {
			title : '��ϵ�绰',
			field : 'app_tel'
			
		}, {
			title : '����������',
			field : 'legal_name'
		
		},  {
			title : '������',
			field : 'agent_name'
			
		}, 
		{
			title : '������֤������',
			field : 'agent_cer_type'
			
		},
		
		
		{
			title : '���������֤��',
			field : 'agent_cer'
			
		}, {
			title : '��������ϵ�绰',
			field : 'agent_tel'
			
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
			$('#user_edit').linkbutton('enable');
			$('#user_delete').linkbutton('enable');
		},
		onLoadSuccess : function() {
			//������Ͻ��á��༭������ɾ������ť
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
			getPreRegMess();
			getuseright();
			setState(activName);
			_init_form_data = $("#acceptance").serializeJson();
		}
		
	});
	
	/**********************************************************************************
	*��������: getSelected
	*����˵��: ѡ������ĳһ�е����ݡ�
	*����˵��: 
	*�� �� ֵ: 
	*��������: xuzz
	*��������: 2014-03-17
	*�޸���ʷ: 
	***********************************************************************************/
	function getSelected(func) {
		var selectedrow = $('#table_user').datagrid('getSelected');

		if (selectedrow) {
			row = selectedrow;
			// ������غ���
			func.call(this, selectedrow);
		} else {

			$.messager.alert('��ʾ��', '����ѡ�б���е�ĳһ��.');
		}
	};

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
				height : 700,
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
	
	
	/**********************************************************************************
	*��������: doAdd
	*����˵��: ����������
	*����˵��: 
	*�� �� ֵ: 
	*��������: xuzz
	*��������: 2014-03-17
	*�޸���ʷ: 
	***********************************************************************************/
	function doAdd() {
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
				this.init(proc_id);
			}
		});
	};

	/**********************************************************************************
	*��������: doEdit
	*����˵��: �༭������
	*����˵��: 
	*�� �� ֵ: 
	*��������: xuzz
	*��������: 2014-03-17
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
	*����˵��: ɾ��������
	*����˵��: 
	*�� �� ֵ: 
	*��������: xuzz
	*��������: 2014-03-17
	*�޸���ʷ: 
	***********************************************************************************/
	function doDelete() {
		var row = userDataGrid.datagrid('getSelected');
		top.$.messager.confirm('ȷ��', 'ȷ��Ҫɾ������������Ϊ[' + row.app_name + ']��', function(
				result) {
			if (result) {
				$.ajax({
					url : ctx+"/landuseright/landinitialreg!deleteApplicant.action?time="+new Date(),
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
	};
	_init_form_data = $("#acceptance").serializeJson();

});

/**********************************************************************************
*��������: setState
*����˵��: ����ҳ��״̬
*����˵��: ���̶�������activName
*�� �� ֵ: 
*��������: xuzz
*��������: 2014-03-17
*�޸���ʷ: 
***********************************************************************************/
function setState(activName) {

	if(activName == procNode.initialexamine){
		$("#djd").combo('disable');
		$(".reg").attr("disabled", "disabled");
		
		$("#fdczfj").removeAttr("disabled"); 	
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
	
		
	};
	if (!(activName == procNode.accepted)&&!(activName == procNode.initialexamine)) {
		$("#djd").combo('disable');
		$(":input").attr("disabled", "disabled");
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		

	}
	;
	/*if (activName == procNode.string4) {
		$('#user_edit').linkbutton('enable');
		$('#user_delete').linkbutton('enable');

		$('#user_add').linkbutton('enable');

	}*/
	if(activName != procNode.accepted){
		
		$(".initreg").css({display:"block"});
	}
	_init_form_data = $("#acceptance").serializeJson();
}
/**********************************************************************************
*��������: GetQueryString
*����˵��:��ȡ��ַ������
*����˵��: �ӵ�ַ���������Ĳ���
*�� �� ֵ: 
*��������: xuzz
*��������: 2014-03-17
*�޸���ʷ: 
***********************************************************************************/
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
/**********************************************************************************
*��������: submit
*����˵��: �������
*����˵��: 
*�� �� ֵ: 
*��������: xuzz
*��������: 2014-03-17
*�޸���ʷ: 
***********************************************************************************/
function submit(){
	var data;
	if(activName == procNode.initialexamine){
		saveFdccfj();	
		//saveOwnership();
	};
	return true;
}

/**********************************************************************************
*��������: saveFdccfj
*����˵��: ���淿�ز�֤���ǵ���֤����
*����˵��: 
*�� �� ֵ: 
*��������: xuzz
*��������: 2014-03-17
*�޸���ʷ: 
***********************************************************************************/
function  saveFdccfj(){
	var result;
	 var fdczfj = $("#fdczfj").val();
	 if($.trim(fdczfj).length==0){
			top.$.messager.alert('��ʾ', '�����뷿�ز�֤���ǣ�', 'info',
					function() {
						
					});	
			return;
		}
	 
		$("#fdczfj1").val(fdczfj);
		
	 $.ajax({
	   		dataType:'json',
	   		url:ctx+"/landuseright/landinitialreg!saveCerRemark.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"excursus":fdczfj},
	   		data:$("#acceptance").serialize(),
	   		success:function(data){
	   			 result=data;
	   			
			 	if(data){
			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
					});	
			 		return result;
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
				}
	   		}
	   	});  
	     
}

/**********************************************************************************
*��������: getPreRegMess
*����˵��: ��ȡ������ǰ�ô��ڴ��ݵĵǼ���Ϣ
*����˵��: 
*�� �� ֵ: 
*��������: xuzz
*��������: 2014-03-17
*�޸���ʷ: 
***********************************************************************************/
function getPreRegMess(){
	$.ajax({
		    dataType: 'json',
			url:ctx+"/landuseright/landinitialreg!getRegMessage.action?time="+new Date()+"&proc_id="+proc_id,
			success:function(data){
			 	if(data){
			 		$("#djbh").val(data.RegInfo.REG_CODE);
			 		$("#djd").combodict('setValue',data.RegInfo.REG_STATION);
			 		$("#ywms").val(data.RegInfo.PROC_NAME);			 					 			
			 	   //$("#xmmc").val(data.PRO_NAME);	
			 		$("#djlx").combodict('setValue',data.RegInfo.REG_TYPE);
			 		$("#fdczfj").text(data.excursus);
			 	}
			 	_init_form_data = $("#acceptance").serializeJson();
			}
	
			
		});
}

/**********************************************************************************
*��������: saveOwnership
*����˵��: ��������ʹ��Ȩ��صǼ���Ϣ
*����˵��: 
*�� �� ֵ: 
*��������: xuzz
*��������: 2014-03-17
*�޸���ʷ: 
***********************************************************************************/
function saveOwnership(){
	 //alert($("#acceptance").serialize());
	 $.ajax({
	   		dataType:'json',
	   		url:ctx+"/landuseright/landinitialreg!saveUseright.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:$("#acceptance").serialize(),
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		top.$.messager.alert('����ɹ���ʾ',"����ɹ���",'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',"����ʧ�ܣ�",'error');
				}
	   		}
	   	});  
}

/**********************************************************************************
*��������: getuseright
*����˵��: ��ȡ����ʹ��Ȩ�Ǽ���Ϣ  
*����˵��: 
*�� �� ֵ: 
*��������: xuzz
*��������: 2014-03-17
*�޸���ʷ: 
***********************************************************************************/
function getuseright(){
	$.ajax({
	    dataType: 'json',
		url:ctx+"/landuseright/landinitialreg!getUseright.action?time="+new Date()+"&proc_id="+proc_id,
		success:function(data){
		 	if(data){
		 		//alert(data.useright_type);
		 		//$("#get_price").val(data.get_price);
		 		//$("#use_limit").val(data.use_limit);
		 		//$("#use_type").val(data.useright_type);
		 		if(data.use_limit){
		 			
		 			
		 			$("#use_limit").numberbox('setValue',data.use_limit);
		 		}if(data.get_price){
		 			
		 			
		 			$("#get_price").numberbox('setValue',data.get_price);
		 		}
		 		if(data.start_date){
		 			var start_date = data.start_date;
		 			
		 			$("#start_date").datebox('setValue',start_date.substr(0,10));
		 		}
		 		
		 		if(data.end_date){
		 			var end_date = data.end_date;
		 			$("#end_date").datebox('setValue',end_date.substr(0,10));	
		 			
		 		}
		 		$("#land_use").combodict('setValue',data.land_use);
		 		$("#use_type").combodict('setValue',data.useright_type);
		 		
		 		
		 	 
		 	}
		 	_init_form_data = $("#acceptance").serializeJson();
		}
	
	
	});
}
/**********************************************************************************
*��������: ҳ��У�鷽��
*����˵��: ��֤ҳ���ϵķǿ�  �����ݸ�ʽ   
*����˵��: flag 1������ �ύ����ֵ �������ֱ�����ύ
*�� �� ֵ: obj  result(trueͨ��  false��ͨ��) message(��Ϣ)  page_name(��ǰҳ������)
*��������: xuzz
*��������: 2014-03-17
*�޸���ʷ: 
***********************************************************************************/
function validate(v_flag){
	
	//���ؽ������
	var result ={
			result:true,
			message:'',
			page_name:'�����'
	}
	//��ʾ��Ϣ 
	var message;
	if(activName==procNode.initialexamine){
		message = "���ز�֤���ǣ�";
		var vEXCURSUS = new tt.Field(message,"fdczfj");  
	    //�ǿռ���
	    tt.vf.req.add(vEXCURSUS);
		
	    var valResult = tt.validate();
		if(!valResult.result){
			 result.message=valResult.message;
			 result.result=false;
			 return result;
		} 
	}
	
	//��������֤
	if(activName==procNode.initialexamine){
		var land_use=$("#land_use").combo('getValue');
		//var house_attr = $("#HOUSE_ATTR").combo('getValue');
		if($.trim(land_use).length==0){
			message = '��ѡ��������;��';
			result.message=message;
			 result.result=false;
			 return result;
		}
		var get_price=$("#get_price").numberbox('getValue');
		if($.trim(get_price).length==0){
			message = '������ȡ�ü۸�';
			result.message=message;
			 result.result=false;
			 return result;
	
		}
		var use_type = $("#use_type").combo('getValue');
		
		if($.trim(use_type).length==0){
			message = '��ѡ��ʹ��Ȩ���ͣ�';
			result.message=message;
			 result.result=false;
			 return result;

		}
		var use_limit = $("#use_limit").numberbox('getValue');
		if($.trim(use_limit).length==0){
			message = '������ʹ�����ޣ�';
			result.message=message;
			 result.result=false;
			 return result;
		}
		var start_date = $("#start_date").datebox('getValue');
		if($.trim(start_date).length==0){
			message = '��������ʼ���ڣ�';
			result.message=message;
			 result.result=false;
			 return result;
		}
		var end_date = $("#end_date").datebox('getValue');
		if($.trim(end_date).length==0){
			message = '��������ֹ���ڣ�';
			result.message=message;
			 result.result=false;
			 return result;
		}
		

	}
	/*if(v_flag){
		_init_form_data = $("#acceptance").serializeJson(); 
	}*/
	
	//�ж��������Ƿ��޸�  ������޸�  ����ʾ�Ƿ񱣴�δ��������
	//_cur_form_data = $("#acceptance").serializeJson(); 
	
	//var r = equal(_init_form_data,_cur_form_data);
	/*if(!r){
		var flag= 0 ;//����ȷ�� �Ƿ��û��Ѿ������������  δ���  ���������������     ����false
		message = '���ݼ��޸ģ��Ƿ�������棿';
		 if(flag){
			 
		 }else{
			 result.message=message;
			 result.result=false; 
		 }
		 return result;
	}*/
	return result;
}

/**********************************************************************************
*��������: pageDataIsChange
*����˵��: �жϵ�ǰҳ�������Ƿ��Ѿ��޸�
*����˵��: 
*�� �� ֵ: �޸ķ���true   δ�޸ķ���false
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
/*function pageDataIsChange(){
	if(proc_node == procNode.ACCEPTED || proc_node == procNode.string1){
		_cur_form_data = $("#main_form").serializeJson(); 
		
		var r = equal(_init_form_data,_cur_form_data);
		//�����ȷ���  ҳ������δ�޸�  ����false
		if(r){
		  return false;
		}
	}
	return true;
}*/






