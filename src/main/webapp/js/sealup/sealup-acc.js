//��ʼ������.	
var proc=this.parent.proc;
//var serialNumber ={num1:"�ǼǱ��",num2:"�鵵��",num3:"���ز�֤��"};
//
var proc_node = proc.activName;
var proc_id = proc.procId;
var reg_code;
//var proc_id = 4;//1000016366;
//var proc_node = "����";

var _init_form_data;							//��ʼ��ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var _cur_form_data;								//��֤ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
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
/**********************************************************************************
*��������: 
*����˵��: ҳ���ʼ��
*����˵��: 
*�� �� ֵ: 
*��������: xuzz
*��������: 2014-03-19
*�޸���ʷ: 
***********************************************************************************/

$(function() {
	 getPreRegMess();
	 getMortMess();
	//
	//����Ȩ��״̬
	setState(proc_node);
	document.getElementById("submit").onclick = function() {
		submit();
    };
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
});
	


	/**********************************************************************************
	*��������: doAdd
	*����˵��: ����
	*����˵��: 
	*�� �� ֵ: 
	*��������: xuzz
	*��������: 2014-03-19
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
	}

	/**********************************************************************************
	*��������: doEdit
	*����˵��: �༭
	*����˵��: 
	*�� �� ֵ: 
	*��������: xuzz
	*��������: 2014-03-19
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
	*����˵��: ɾ��
	*����˵��: 
	*�� �� ֵ: 
	*��������: xuzz
	*��������: 2014-03-19
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
	*��������: setState
	*����˵��: ����ҳ��״̬
	*����˵��: 
	*�� �� ֵ: 
	*��������: xuzz
	*��������: 2014-03-19
	*�޸���ʷ: 
	***********************************************************************************/
function setState(proc_node) {
	
	//alert();
	if(proc_node == state1.string1){
		$(".edit_table").attr("disabled", "disabled");	
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');
		//$(".attach_tab").attr("disabled", "disabled");
		$('#user_add').linkbutton('disable');
		
	}
	if (!((proc_node == state1.string0)&&(proc_node == state1.string1))) {
		// $(".attach_tab").attr("disabled", "disabled");	
		$("#REG_STATION").combo('disable');
		//$(":input").attr("disabled", "disabled");
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');
		
		$('#user_add').linkbutton('disable');
		//$("#mort_type").combo('disable');
		//$(".com").combo('disable');
	}
	
	if (proc_node == state1.string4) {
		$('#user_edit').linkbutton('enable');
		$('#user_delete').linkbutton('enable');
		//$(".attach_tab").attr("disabled", "disabled");	
		$('#user_add').linkbutton('enable');

	}
	$('#dis_type').combo('enable');
}
/**********************************************************************************
*��������: submit
*����˵��: ������Ϣ
*����˵��: 
*�� �� ֵ: 
*��������: xuzz
*��������: 2014-03-19
*�޸���ʷ: 
***********************************************************************************/
function submit(){
		//saveRegInfo();		
		saveMortMess();
}

/**********************************************************************************
*��������: saveRegInfo
*����˵��: �Ǽ���Ϣ������²���
*����˵��: 
*�� �� ֵ: 
*��������: xuzz
*��������: 2014-03-19
*�޸���ʷ: 
***********************************************************************************/
function saveRegInfo() {
	var reg_station = $("#REG_STATION").combo('getValue');
	var reg_code = $("#REG_CODE").val();
	var reg_type = $("#REG_TYPE").combo('getValue');
	var proc_name = $("#BUS_DETAIL").val();
	if($.trim(reg_station).length==0){
		top.$.messager.alert('��ʾ', '��ѡ��Ǽǵ㣡', 'info',
				function() {
					
				});	
		return;

	}
	  $.ajax({
	   		dataType:'json',
	   		url:ctx+"/sealup/sealup!saveRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"oivo.reg_station":reg_station,"oivo.reg_code":reg_code,"oivo.reg_type":reg_type,"oivo.proc_name":proc_name},
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
/**********************************************************************************
*��������: getPreRegMess
*����˵��: ��ȡ������ǰ�ô��ڴ��ݵĵǼ���Ϣ
*����˵��: 
*�� �� ֵ: 
*��������: xuzz
*��������: 2014-03-19
*�޸���ʷ: 
***********************************************************************************/
function getPreRegMess(){
	$.ajax({
		    dataType: 'json',
		    // /sealup/sealup!getRegInfo.action?time=
			url:ctx+"/landuseright/landinitialreg!getRegMessage.action?time="+new Date()+"&proc_id="+proc_id,
		    //url:ctx+"/mortgage/morsetup!getRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			success:function(data){
			 	if(data){
			 		//alert(JSON.stringify(data));
			 		$("#REG_CODE").val(data.RegInfo.REG_CODE);
			 		reg_code=data.RegInfo.REG_CODE;
			 		$("#REG_STATION").combodict("setValue",data.RegInfo.REG_STATION);
			 		$("#BUS_DETAIL").val(data.RegInfo.PROC_NAME);
			 		$("#REG_TYPE").combodict("setValue",data.RegInfo.REG_TYPE);
			 	}
				}
		});
	
}


/**********************************************************************************
*��������: selectChange
*����˵��: ����������¼�
*����˵��: 
*�� �� ֵ: 
*��������: xuzz
*��������: 2014-04-09
*�޸���ʷ: 
***********************************************************************************/
function selectChange()
{
	var url;
	//�ֺ���ת���
	if($("#dis_type").combodict("getValue")==enumdata.chattach)
	{
		url=ctx+"/sealup/sealup!getAttachByRegcode.action?time="+new Date()+"&reg_code="+reg_code+"&attachType="+enumdata.chattach;
	}
	//����
	else if($("#dis_type").combodict("getValue")==enumdata.reattach)
	{
		url=ctx+"/sealup/sealup!getAttachByRegcode.action?time="+new Date()+"&reg_code="+reg_code+"&attachType="+enumdata.reattach;
	}
	/*//�ֺ���ת���
	else if($("#dis_type").combodict("getValue")==enumdata.chattach)
	{
		url=ctx+"/sealup/sealup!getAttachByRegcode.action?time="+new Date()+"&reg_code="+reg_code+"&attachType="+enumdata.reattach;
	}*/
	else
	{
		setDataIsNull();
	}
	
	getAttach(url);
}
/**********************************************************************************
*��������: setDataIsNull
*����˵��: ���ò������Ϊ��
*����˵��: 
*�� �� ֵ: ��
*��������: xuzz
*��������: 2014-04-08
*�޸���ʷ: 
***********************************************************************************/
function setDataIsNull()
{
	$("#dis_off").val("");
		$("#dis_no").val("");
		$("#law_doc").val("");
		$("#lim_holder").val("");
		$("#service_name").val("");
		$("#dis_per_tel").numberbox('setValue',"");
		$("#service_name").val("");
		$("#dis_range").val("");
		//$("#dis_type").combodict('setValue',data.DIS_TYPE);
		$("#pre_con_no").val("");
		$("#workid").val("");
		$("#remark").val("");
		$("#dis_date").datebox('setValue',"");	
		$("#service_date").datebox('setValue',"");	
		$("#start_date").datebox('setValue',"");	
		$("#end_date").datebox('setValue',"");	
}


/**********************************************************************************
*��������: getAttach
*����˵��: ��ȡ���Ǽ���Ϣ�����ֺ�����Ϣ
*����˵��: 
*�� �� ֵ: ��
*��������: xuzz
*��������: 2014-04-08
*�޸���ʷ: 
***********************************************************************************/
function getAttach(url){
	$.ajax({
	    dataType: 'json',
		url:url,
		success:function(data){
		 	if(data){
		 		$("#dis_off").val(data.DIS_OFF);
		 		$("#dis_no").val(data.DIS_NO);
		 		$("#law_doc").val(data.LAW_DOC);
		 		$("#lim_holder").val(data.LIM_HOLDER);
		 		$("#service_name").val(data.SERVICE_NAME);
		 		$("#dis_per_tel").numberbox('setValue',data.DIS_PER_TEL);
		 		$("#service_name").val(data.SERVICE_NAME);
		 		$("#dis_range").val(data.DIS_RANGE);
		 		//$("#dis_type").combodict('setValue',data.DIS_TYPE);
		 		$("#pre_con_no").val(data.PRE_CON_NO);
		 		$("#workid").val(data.WORKID);
		 		$("#remark").val(data.REMARK);
		 		if(data.DIS_DATE)
		 		{
		 			$("#dis_date").datebox('setValue',data.DIS_DATE.substr(0,data.DIS_DATE.length));	
		 		}
		 		if(data.SERVICE_DATE){
		 			$("#service_date").datebox('setValue',data.SERVICE_DATE.substr(0,data.SERVICE_DATE.length));	
		 		}
		 		if(data.START_DATE){
		 			$("#start_date").datebox('setValue',data.START_DATE.substr(0,data.START_DATE.length));	
		 		}
		 		if(data.END_DATE){
		 			$("#end_date").datebox('setValue',data.END_DATE.substr(0,data.END_DATE.length));	
		 		}
		 	}
			}
	});
}

/**********************************************************************************
*��������: getMortMess
*����˵��: ��ȡ���Ǽ���Ϣ
*����˵��: 
*�� �� ֵ: 
*��������: xuzz
*��������: 2014-03-19
*�޸���ʷ: 
***********************************************************************************/
function getMortMess(){
	$.ajax({
	    dataType: 'json',
		url:ctx+"/sealup/sealup!getAttachById.action?time="+new Date()+"&proc_id="+proc_id,
		success:function(data){
			//alert(JSON.stringify(data));
		 	if(data){
		 		$("#dis_off").val(data.dis_off);
		 		$("#dis_no").val(data.dis_no);
		 		$("#law_doc").val(data.law_doc);
		 		$("#lim_holder").val(data.lim_holder);
		 		$("#service_name").val(data.service_name);
		 		 //$("#dis_per_tel").numberbox(data.dis_per_tel);
		 		 if(data.dis_per_tel){
		 			$("#dis_per_tel").numberbox('setValue',data.dis_per_tel);
		 		}
		 		$("#service_name").val(data.service_name);
		 		$("#workid").val(data.workid);
		 		$("#remark").val(data.remark);
		 		$("#dis_range").val(data.dis_range);
		 		if(data.dis_type)
		 		{
		 			$("#dis_type").combodict('setValue',data.dis_type);
		 			$("#dis_type").combodict({value:data.dis_type,onChange: selectChange});
		 		}
		 		$("#pre_con_no").val(data.pre_con_no);
		 		$("#dis_limit").val(data.dis_limit);
		 		
		 		if(data.dis_date){
		 			$("#dis_date").datebox('setValue',data.dis_date.substr(0,data.dis_date.length-11));	
		 		}
		 		if(data.service_date){
		 			//var djrq = data.mort_reg_date;
		 			$("#service_date").datebox('setValue',data.service_date.substr(0,data.service_date.length-11));	
		 			
		 		}
		 		if(data.start_date){
		 			//var qsrq = data.creditor_start_date;
		 			$("#start_date").datebox('setValue',data.start_date.substr(0,data.start_date.length-11));	
		 			
		 		}
		 		if(data.end_date){
		 			//var zzrq = data.creditor_end_date;
		 			$("#end_date").datebox('setValue',data.end_date.substr(0,data.end_date.length-11));	
		 			
		 		}
		 	}
		 	else
		 	{
		 		$("#dis_type").combodict('setValue',enumdata.attach);
	 			$("#dis_type").combodict({value:enumdata.attach,onChange: selectChange});
		 	}
			}
	});
}
/**********************************************************************************
*��������: saveMortMess
*����˵��: ������Ǽ���Ϣ
*����˵��: 
*�� �� ֵ: 
*��������: xuzz
*��������: 2014-03-19
*�޸���ʷ: 
***********************************************************************************/
function saveMortMess(){
	 $.ajax({
	   		dataType:'json',
	   		url:ctx+"/sealup/sealup!saveAttach.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:$("#attach").serialize(),
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		//getMortMess();
			 		top.$.messager.alert('����ɹ���ʾ',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('����ʧ����ʾ',data.errorMessage,'error');
				}
	   		}
	   	});  
	
	
}
/**********************************************************************************
*��������: ҳ��У�鷽��
*����˵��: ��֤ҳ���ϵķǿ�  �����ݸ�ʽ   
*����˵��: 
*�� �� ֵ: 
*��������: xuzz
*��������: 2014-03-19
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
	
	//��������֤
	if(proc_node==state1.string0){
		var land_use=$("#land_use").val();
		var dis_off=$("#dis_off").val();
	 	var dis_no=$("#dis_no").val();
	 	var law_doc=$("#law_doc").val();
	 	var lim_holder=$("#lim_holder").val();
	 	var service_name=$("#service_name").val();
	 	var dis_limit=$("#dis_limit").val();
	 	var dis_per_tel=$("#dis_per_tel").numberbox('getValue');
	 	var workid=$("#workid").val();
	 	var service_date=$("#service_date").datebox('getValue');	
	 	var start_date=$("#start_date").datebox('getValue');	
	 	var end_date=$("#end_date").datebox('getValue');
	 	var dis_date=$("#dis_date").datebox('getValue');
		if($.trim(dis_off).length==0){
			message = '����������أ�';
			result.message=message;
			 result.result=false;
			 return result;
		}
		if($.trim(dis_date).length==0){
			message = '�����������ڣ�';
			result.message=message;
			 result.result=false;
			 return result;
		}
		if($.trim(dis_limit).length==0){
			message = '�����������ޣ�';
			result.message=message;
			 result.result=false;
			 return result;
		}
		if($.trim(dis_no).length==0){
			message = '���������ĺţ�';
			result.message=message;
			 result.result=false;
			 return result;
	
		}
		if($.trim(law_doc).length==0){
			message = '�����뷨�����飡';
			result.message=message;
			 result.result=false;
			 return result;

		}
		if($.trim(lim_holder).length==0){
			message = '�����뱻����ˣ�';
			result.message=message;
			 result.result=false;
			 return result;
		}
		if($.trim(service_name).length==0){
			message = '�������ʹ��ˣ�';
			result.message=message;
			 result.result=false;
			 return result;
		}
		if($.trim(dis_per_tel).length==0){
			message = '��������ϵ��ʽ��';
			result.message=message;
			 result.result=false;
			 return result;
		}
		if($.trim(workid).length==0){
			message = '�����빤��֤�ţ�';
			result.message=message;
			 result.result=false;
			 return result;

		}
		if($.trim(service_date).length==0){
			message = '�������ʹ����ڣ�';
			result.message=message;
			 result.result=false;
			 return result;
		}
		if($.trim(start_date).length==0){
			message = '��������ʼ���ڣ�';
			result.message=message;
			 result.result=false;
			 return result;
		}
		if($.trim(end_date).length==0){
			message = '��������ֹ���ڣ�';
			result.message=message;
			 result.result=false;
			 return result;
		}

	}
	if(v_flag){
		_init_form_data = $("#attach").serializeJson(); 
	}
	
	//�ж��������Ƿ��޸�  ������޸�  ����ʾ�Ƿ񱣴�δ��������
	_cur_form_data = $("#attach").serializeJson(); 
	
	var r = equal(_init_form_data,_cur_form_data);
	if(!r){
		var flag= 0 ;//����ȷ�� �Ƿ��û��Ѿ������������  δ���  ���������������     ����false
		message = '���ݼ��޸ģ��Ƿ�������棿';
		 if(flag){
			 
		 }else{
			 result.message=message;
			 result.result=false; 
		 }
		 return result;
	}
	return result;
}









