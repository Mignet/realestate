//��ʼ������.	
var proc;
proc=this.parent.proc;

var serialNumber ={num1:"�ǼǱ��",num2:"�鵵��",num3:"���ز�֤��"};

var activName = "����";//proc.activName;
var proc_node = "����";//proc.activName;
//var activName = "����";
var proc_id = 1000025915;//proc.procId;
//var proc_id = 1000016427;
//var proc_id = 1;
var _init_form_data;							//��ʼ��ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var _cur_form_data;								//��֤ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var house_attr_dict_data;						//��������

var state1 = {
		string0: "����",
		string1 : "����",
		string2 : "����",
		string3 : "�շ�",
		string4 : "��׼",
		string5 : "�������",
		string6 : "�������",
		string7: "������",	
		string9 : "��֤",
		string10 : "����",
		string11: "�鵵",
		string12: "����",
	};
$(function() {
	house_attr_dict_data = dicts.getDict_data_by_code('021');
	//alert($.toJSON(house_attr_dict_data));
	getPreRegMess();
	getBusownership();
	
    houseDataGrid = $('#table_house').datagrid({
		title:'���ز���Ϣ',
		height:240,
		width:785,
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
		     			
		     		}
//		     		,  {
//		     			title : '��Ŀ����',
//		     			field : 'PRO_NAME',
//		     			width:100
//		     			
//		     		}
		     		, {
		     			hidden: true,
		     			field : 'CODE',
		     			
		     		},{
		     			field:"house_attr",
		     			width:70,
		     			title:"��������",editor:{
		     				type:'combobox',
    						options:{
    							 valueField:'value',  
    							 textField:'name', 
    							 data:house_attr_dict_data,
    							 required:true 
    						}
    					}
		     		},{
		     			field:"reg_value",
		     			title:"�ǼǼۿ�",editor:'text'
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
		    		onClickRow:function(rowIndex){
		    			houseDataGrid.datagrid('beginEdit', rowIndex);
		    		},
		     		onLoadSuccess : function() {
		     			$('.editcls').linkbutton({text:'�鿴'});
		     			
		     			//Ϊfalseʱ��ʾ����Ǽǻ�ȡ�����Ϣ
		     			//_init_form_data = $("#attach").serializeJson(); 
		     		}
	});
	
	
	
		//������������Ϣ��
	var userDataGrid = $('#table_user').datagrid({
		//fit : true,
		title:'Ȩ������Ϣ',
		height:240,
		width:785,
		// ���������Դ
		url :ctx+"/houseownership/initialreg!getAppMessage.action?time="+new Date()+"&proc_id="+proc_id,
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
			field : 'app_type',formatter : dicts.format.app_type_format
			
		}, {
			title : '֤������',
			field : 'app_cer_type',formatter : dicts.format.app_cer_type_format
			
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
			field : 'agent_cer_type',
				formatter : function(value) {
					if(value == '001'){
						
						return '���֤';
					};
					if(value == '002'){
						
						return '����֤';
					}
			
				}
			
		},
		
		
		{
			title : '������֤������',
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
			if(activName == state1.string0){
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
	//����Ȩ��״̬
	setState(activName);
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
	;

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

	// ɾ��
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
	;

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
	;

	$('#simpleform').form({
		dataType : 'json',
		url : 'appDelegate/getUserList.run',
		success : function(data) {
			userDataGrid.datagrid('loadData', data);
		}
	});
	test();

	function test() {
		var panel = $('.plui-layout').layout('panel', 'north');
		panel.panel({
			height : 143
		});
		$('.plui-layout').layout('resize');
	}
	;

});


function setState(activName) {

	if(activName == state1.string5){
		$("#djd").combo('disable');
		$(".reg").attr("disabled", "disabled");
		
		$("#fdczfj").removeAttr("disabled"); 	
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
	
		
	};
	if (!(activName == state1.string0)&&!(activName == state1.string5)) {
		$("#djd").combo('disable');
		$("#qdfs").combo('disable');
		$("#qsrq").combo('disable');
		$("#zzrq").combo('disable');
		$("#fwxz").combo('disable');
		$("#yt").combo('disable');
		$(":input").attr("disabled", "disabled");
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		

	}
	;
//	if (activName == state1.string4) {
//		$('#user_edit').linkbutton('enable');
//		$('#user_delete').linkbutton('enable');
//
//		$('#user_add').linkbutton('enable');
//
//	}
	if(activName != state1.string0){
		
		$(".initreg").css({display:"block"});
		$(".remark").css({display:"block"});
		//$("#pric").css({display:"block"});
	}
   if(activName == state1.string0){
		
		//$(".initreg").css({display:"block"});
		//$(".remark").css({display:"block"});
		$(".tt").css({display:"none"});
	}

}

// ��ȡ��ַ������
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
//�ж�ִ�е���saveDjxx()����saveFdccfj(activName)
function submit(){
//	var result = validate();
//	if(!result.result){
//		return false;
//	}
	if(activName == state1.string0){
		return saveDjxx();		
	};
	if(activName == state1.string5){
		//saveFdccfj();	
		return saveOwnership();
	};
	
	
}

// �Ǽ���Ϣ������²���
function saveDjxx() {
	var result =true;
	var djbh = $("#djbh").val();
	var djlx = $('input[name="djlx"]').val();
	var djd = $('input[name="djd"]').val();
	var ywms = $("#ywms").val();
	var xmmc = $("#xmmc").val();
	
	
	$("#djbh1").val(djbh);
	$("#djlx1").val(djlx);
	$("#djd1").val(djd);
	$("#ywms1").val(ywms);
	$("#xmmc1").val(xmmc);
	  $.ajax({
	   		dataType:'json',
	   		url:ctx+"/houseownership/initialreg!saveRegMessage.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"oivo.reg_code":djbh,"oivo.reg_type":djlx,"oivo.reg_station":djd,"oivo.proc_name":ywms},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
				}
	   		},error:function(data){
	   			result = false;
	   		}
	   	});  
	return result;
}

//���淿�ز�֤���ǵ���֤����
function  saveFdccfj(){
	
		//$("#fdczfj1").val(fdczfj);
		
	 $.ajax({
	   		dataType:'json',
	   		url:ctx+"/houseownership/initialreg!saveCerRemark.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"oivo.excursus":fdczfj},
	   		success:function(data){
			 	if(data){
			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
				}
	   		}
	   	});  
	     
};
//��ȡ������ǰ�ô��ڴ��ݵĵǼ���Ϣ
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
			 		/*$("#djbh").val(data.reg_code);
			 		$("#djd").combodict('setValue',data.reg_station);
			 		$("#ywms").val(data.proc_name);			 					 			
			 	    //$("#xmmc").val(data.pro_name);	
			 		//$("#djlx").val(data.reg_type);
			 		$("#djlx").combodict('setValue',data.reg_type);
			 		$("#fdczfj").text(data.excursus);*/
			 		
			 	 
			 	}
				}
		});
	
	
	
}
//���淿������Ȩ��صǼ���Ϣ
function saveOwnership(){
	var result = true;
	 $.ajax({
	   		dataType:'json',
	   		url:ctx+"/houseownership/initialreg!saveOwnership.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:$("#add_app_form").serialize(),
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
				}
	   		},error:function(data){
	   			result = false;
	   		}
	   	});  
	return result;
}

//��ȡ��������Ȩ�Ǽ���Ϣ
function getBusownership(){
	$.ajax({
	    dataType: 'json',
		url:ctx+"/houseownership/initialreg!getBusownership.action?time="+new Date()+"&proc_id="+proc_id,
		success:function(data){
		 	if(data){
		 		//alert(JSON.stringify(data));
		 		$("#fdczh").val(data.cer_no);
		 		$("#djjk").val(data.reg_value);
		 		$("#qdfs").combodict('setValue',data.get_mode);
		 		$("#synx").val(data.lu_term);
		 		
		 		if(data.start_date){
		 			var qsrq = data.start_date;
		 			
		 			$("#qsrq").datebox('setValue',qsrq.substr(0,10));
		 		}
		 		
		 		if(data.end_date){
		 			var zzrq = data.end_date;
		 			$("#zzrq").datebox('setValue',zzrq.substr(0,10));	
		 			
		 		}
		 		$("#fwxz").combodict('setValue',data.house_attr);
		 		$("#yt").combodict('setValue',data.house_usage);
		 		
		 		
		 	 
		 	}
		 	 //_init_form_data = $("#add_app_form").serializeJson();  	
		}
	});
}
/**********************************************************************************
*��������: ҳ��У�鷽��
*����˵��: ��֤ҳ���ϵķǿ�  �����ݸ�ʽ   
*����˵��: v_flag 1������ �ύ����ֵ �������ֱ�����ύ
*�� �� ֵ: obj  result(trueͨ��  false��ͨ��) message(��Ϣ)  page_name(��ǰҳ������)
*��������: Joyon
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
	var message;
	
	if(activName == state1.string0){
		var rowlen  = $('#table_user').datagrid('getRows').length;
		if(rowlen == 0){
			message= '��¼�������ˣ�';
			 result.message=message;
			 return result;
		}
		
		var djbh = $("#djbh").val();
		var djlx = $('input[name="djlx"]').val();
		var djd = $('input[name="djd"]').val();
		var ywms = $("#ywms").val();
		var xmmc = $("#xmmc").val();
		if($.trim(djlx).length==0){
			message= '��ѡ��Ǽ����ͣ�';
			 result.message=message;
			 return result;

		}
		if($.trim(djd).length==0){
			message= '��ѡ��Ǽǵ㣡';
			 result.message=message;
			 return result;
		}
		if($.trim(ywms).length==0){
			message= '������ҵ��������';
			 result.message=message;
			 return result;
		}
		/*if($.trim(xmmc).length==0){
			message= '��������Ŀ���ƣ�';
			 result.message=message;
			 return result;
		}*/
		
		//����Ǳ���  �������л�һ�� ���ݳ�ʼ������
		if(v_flag){
			_init_form_data = "";//$("#main_form").serializeJson(); 
		}
		//�ж��������Ƿ��޸�  ������޸�  ����ʾ�Ƿ񱣴�δ��������
		_cur_form_data = "";//$("#main_form").serializeJson(); 
		
		var r = equal(_init_form_data,_cur_form_data);
		if(!r){
			var flag= 0 ;//����ȷ�� �Ƿ��û��Ѿ������������  δ���  ���������������     ����false
			message = '���ݼ��޸ģ����ȱ�����ύ��';
			 if(flag){
				 
			 }else{
				 result.message=message;
				 result.result=false; 
			 }
			 return result;
		}
	}else if(activName == state1.string5){
		// alert($("#add_app_form").serialize());
		var djjk = $("#djjk").val();
		var qdfs = $('input[name="get_mode"]').val();
		var synx = $("#synx").val();
		var qsrq = $('input[name="start_date"]').val();
		var zzrq = $('input[name="end_date"]').val();
		var fwxz = $('input[name="house_attr"]').val();
		var yt = $('input[name="house_usage"]').val();
		 var fdczfj = $("#fdczfj").val();
//		if($.trim(djjk).length==0){
//			
//			message= '��¼��ǼǼۿ�!';
//			 result.message=message;
//			 result.result=false;
//			 return result;
//	
//		}
		if($.trim(qdfs).length==0){
			message= '��ѡ��ȡ�÷�ʽ��';
			 result.message=message;
			 result.result=false;
			 return result;
			
		}
//		if($.trim(synx).length==0){
//			message= '��¼��ʹ�����ޣ�';
//			 result.message=message;
//			 result.result=false;
//			 return result;
//		}
//		if($.trim(qsrq).length==0){
//			message= '��ѡ����ʼ���ڣ�';
//			 result.message=message;
//			 result.result=false;
//			 return result;
//			
//		}
//		if($.trim(zzrq).length==0){
//			
//			message= '��ѡ����ֹ���ڣ�';
//			 result.message=message;
//			 result.result=false;
//			 return result;
//			
//		}
//		if($.trim(fwxz).length==0){
//			message= '��ѡ�������ʣ�';
//			 result.message=message;
//			 result.result=false;
//			 return result;
//		}
//		if($.trim(yt).length==0){
//			message= '��ѡ����;��';
//			 result.message=message;
//			 result.result=false;
//			 return result;
//		}
		
		
		 if($.trim(fdczfj).length==0){
				message= '�����뷿�ز�֤���ǣ�';
				 result.message=message;
				 result.result=false;
				 return result;
		}
		 
		//�ύ�Ž����ж�  ����ʱ��ʼ���ȶԱ���
		if(!v_flag){
				//�ж��������Ƿ��޸�  ������޸�  ����ʾ�Ƿ񱣴�δ��������
			_cur_form_data = $("#main_form").serializeJson(); 
			
			var r = equal(_init_form_data,_cur_form_data);
			if(!r){
				var flag= 0 ;//����ȷ�� �Ƿ��û��Ѿ������������  δ���  ���������������     ����false
				message = '���ݼ��޸ģ����ȱ�����ύ��';
				 if(flag){
					 
				 }else{
					 result.message=message;
					 result.result=false; 
				 }
				 return result;
			}
		}else{
			_init_form_data = "";//$("#main_form").serializeJson(); 
		}
	
	}
	result.result=true;
	return result;
}

/**********************************************************************************
*��������:getApp_type_dict
*����˵��:��ȡ�����������ֵ���ֵ  ���޸�ʱ ������ѡ��
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�app_type_dict_data��ֵ ���޷���ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/

function getApp_type_dict_data(){
	if(!app_type_dict_data){
		$.ajax({
			url:ctx+'/common/dict!getNewDictByCode.action?code=043',  
			dataType : 'json',
			type : 'post',
			data : {"proc_id":proc_id},
			async:false,
			success : function(data) {
				app_type_dict_data = data;
			}
		});
	}
}
/**********************************************************************************
*��������:getApp_cer_type_dict_data
*����˵��:��ȡ������֤�������ֵ���ֵ  ���޸�ʱ ������ѡ��
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�app_cer_type_dict_data��ֵ ���޷���ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function getApp_cer_type_dict_data(){
	if(!app_cer_type_dict_data){
		$.ajax({
			url:ctx+'/common/dict!getNewDictByCode.action?code=002',  
			dataType : 'json',
			type : 'post',
			data : {"proc_id":proc_id},
			async:false,
			success : function(data) {
				app_cer_type_dict_data = data;
			}
		});
	}
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
		_cur_form_data = "";//$("#main_form").serializeJson(); 
		
		var r = equal(_init_form_data,_cur_form_data);
		//�������ȷ���  ҳ�����ݼ��޸�  ����true
		if(!r){
		  return true;
		}
	}
	return false;
}




