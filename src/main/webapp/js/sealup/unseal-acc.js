//��ʼ������.	
var proc=this.parent.proc;
//var serialNumber ={num1:"�ǼǱ��",num2:"�鵵��",num3:"���ز�֤��"};
//
var proc_node = proc.activName;
var proc_id = proc.procId;
var reg_code;       //�ǼǱ��
var _init_form_data;							//��ʼ��ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
var _cur_form_data;								//��֤ʱ����   �����жϵ�ǰҳ�������Ƿ��޸�
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
var attachDataGrid;
var houseDataGrid;
$(function() {
	houseDataGrid = $('#table_house').datagrid({
		title:'���ز���Ϣ',
		height:240,
		url:ctx+"/mortgage/morsetup!getHouseInfo.action?time="+new Date()+"&proc_id="+proc_id,
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
		     		onClickRow : function(rowIndex, field, value) {
		     			if(field=="button"){
		    				$('#table_house').datagrid('selectRow',rowIndex);
		    				dowatch(this);
		    			}
		     		},
		     		onLoadSuccess : function() {
		     			$('.editcls').linkbutton({text:'�鿴'});
		     		}
	});
	

	 getPreRegMess();
	 //getAttach();
	 getUnAttach();
	//����Ȩ��״̬
	setState(proc_node);
	_init_form_data = $("#attach").serializeJson(); 
	document.getElementById("submit").onclick = function() {
		submit();
    };
	
	
});


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

//��ȡ���,�����ֺ������Ϣ
function setAttachInfo(url,title)
{
	attachDataGrid = $('#table_attach').datagrid({
		title:title,
		height:240,
		url:url,//ctx+"/sealup/sealup!getAttachByRegcode.action?time="+new Date()+"&reg_code="+result,
		// ���ÿ�п���Զ���Ӧ����ܿ��
		autoRowHeight : true,
		fitColumns : true,
		// ȥ���߿�
		border : true,
		checkbox:'CK',
		idField: 'REG_CODE',
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
		singleSelect : false,
		
		columns : [ [
		     		// ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������

		     		 {field:'ck',checkbox:true},
		     		{
		     			title : '������',
		     			field : 'DIS_OFF',
		     			width:80
		     			
		     		}, {
		     			title : '����ĺ�',
		     			field : 'DIS_NO',
		     			width:100
		     			
		     		},
		     		{
		     			title : '��������',
		     			field : 'LAW_DOC',
		     				width:100
		     		}, 
		     		{
		     			title : '�������',
		     			field : 'LIM_HOLDER',
		     			width:50
		     			
		     		}, 
		     		{
		     			title : '�ʹ���',
		     			field : 'SERVICE_NAME',
		     			width:50
		     			
		     		},  {
		     			title : '��ϵ��ʽ',
		     			field : 'DIS_PER_TEL',
		     			width:100
		     			
		     		},
		     		{
		     			title : '����',		     		
		     			field:'button',
		     			formatter:function(value,rec){
		     				 var btn = '<a class="watch" onclick="" href="javascript:void(0)">�鿴</a>';  
		                     return btn;
		     				;}
		     		}
		     		] ],
		     		// ��ͷ����ӹ�������
		     		onClickRow : function(rowIndex, field, value) {
		     			if(field=="button"){
		    				$('#table_attach').datagrid('selectRow',rowIndex);
		    				dowatchAttach(this);
		    			}
		     		},
		     		onLoadSuccess : function() {
		     			$('.watch').linkbutton({text:'�鿴'});
		     			//Ϊfalseʱ��ʾ����Ǽǻ�ȡ�����Ϣ
		     			_init_form_data = $("#attach").serializeJson(); 
		     		}
	});
}

function dowatchAttach()
{
	var row = attachDataGrid.datagrid('getSelected');
	openInTopWindow({
		// ����Ԫ�ص�id
		id : 'edit_user_win',
		// ����iframe��src
		src : ctx+'/jsp/personalOffice/attach/attachInfo.jsp',
		// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
		destroy : true,
		// ���ڱ���
		title : '�鿴��Ϣ',
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
}

	/**********************************************************************************
	*��������: setState
	*����˵��: ����״̬
	*����˵��: 
	*�� �� ֵ: ��
	*��������: xuzz
	*��������: 2014-04-08
	*�޸���ʷ: 
	***********************************************************************************/
function setState(proc_node) {

	
	$(".attach_tab").attr("disabled", "disabled");	
	if(proc_node == state1.string1){
		//$("#REG_STATION").combo('disable');
		//$(":input").attr("disabled", "disabled");
		$(".edit_table").attr("disabled", "disabled");	
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');

		$('#user_add').linkbutton('disable');
		
	}
	if (!(proc_node == state1.string0)&&!(proc_node == state1.string1)) {
		//$(".attach_tab").attr("disabled", "disabled");	
		$("#REG_STATION").combo('disable');
		$(":input").attr("disabled", "disabled");
		$('#user_edit').linkbutton('disable');
		$('#user_delete').linkbutton('disable');
		
		$('#user_add').linkbutton('disable');
		//$("#mort_type").combo('disable');
		$(".com").combo('disable');
	}
	
	if (proc_node == state1.string4) {
		$('#user_edit').linkbutton('enable');
		$('#user_delete').linkbutton('enable');
		$(".edit_table").attr("disabled", "disabled");
		$('#user_add').linkbutton('enable');

	}
	_init_form_data = $("#attach").serializeJson(); 
}
/**********************************************************************************
*��������: submit
*����˵��: �������
*����˵��: 
*�� �� ֵ: ��
*��������: xuzz
*��������: 2014-04-08
*�޸���ʷ: 
***********************************************************************************/
function submit(){
		saveRegInfo();			
		//saveAttach();
}
/**********************************************************************************
*��������: saveRegInfo
*����˵��: �Ǽ���Ϣ������²���
*����˵��: 
*�� �� ֵ: ��
*��������: xuzz
*��������: 2014-04-08
*�޸���ʷ: 
***********************************************************************************/
function saveRegInfo() {
	var reg_station = $("#REG_STATION").combo('getValue');
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
	   		data:$("#add_app_form").serialize(),
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
*�� �� ֵ: ��
*��������: xuzz
*��������: 2014-04-08
*�޸���ʷ: 
***********************************************************************************/
function getPreRegMess(){
	$.ajax({
		    dataType: 'json',
			url:ctx+"/sealup/sealup!getRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
			success:function(data){
			 	if(data){
			 		//alert(JSON.stringify(data));
			 		$("#REG_CODE").val(data.REG_CODE);
			 		$("#REG_STATION").combodict("setValue",data.REG_STATION);
			 		$("#BUS_DETAIL").val(data.PROC_NAME);
			 		$("#REG_TYPE").combodict("setValue",data.REG_TYPE);
			 		reg_code = data.REG_CODE;
			 		var url=ctx+"/sealup/sealup!getUnAttachByRegcode.action?time="+new Date()+"&reg_code="+reg_code+"&attachType="+enumdata.unattach;;
			 		setAttachInfo(url,"�����Ϣ");
			 	}
				}
		});
	
}

/**********************************************************************************
*��������: getUnAttach
*����˵��: ��ȡ���Ǽ���Ϣ
*����˵��: 
*�� �� ֵ: ��
*��������: xuzz
*��������: 2014-04-08
*�޸���ʷ: 
***********************************************************************************/
function getUnAttach(){
	$.ajax({
	    dataType: 'json',
		url:ctx+"/sealup/sealup!getAttachById.action?time="+new Date()+"&proc_id="+proc_id,
		success:function(data){
		 	if(data){
		 		$("#redis_off").val(data.dis_off);
		 		$("#redis_no").val(data.dis_no);
		 		$("#relaw_doc").val(data.law_doc);
		 		$("#reservice_name").val(data.service_name);
		 		if(data.dis_type)
		 		{
		 			$("#redis_type").combodict('setValue',data.dis_type);
		 			$("#redis_type").combodict({value:data.dis_type,onChange: selectChange});
		 		}
		 		$("#redis_per_tel").numberbox('setValue',data.dis_per_tel);
		 		$("#reservice_name").val(data.service_name);
		 		$("#reworkid").val(data.workid);
		 		if(data.dis_date)
		 		{
		 			$("#redis_date").datebox('setValue',data.dis_date.substr(0,data.dis_date.length-11));	
		 		}
		 		if(data.service_date){
		 			$("#reservice_date").datebox('setValue',data.service_date.substr(0,data.service_date.length-11));	
		 		}
		 	}
		 	else
		 	{
		 		$("#redis_type").combodict('setValue',enumdata.unattach);
	 			$("#redis_type").combodict({value:enumdata.unattach,onChange: selectChange});
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
	var title;
	//�ֺ���ת���
	if($("#redis_type").combodict("getValue")==enumdata.unchattach)
	{
		url=ctx+"/sealup/sealup!getUnAttachByRegcode.action?time="+new Date()+"&reg_code="+reg_code+"&attachType="+enumdata.unchattach;
		title="�ֺ�����Ϣ";
	}
	//���
	else if($("#redis_type").combodict("getValue")==enumdata.unattach)
	{
		url=ctx+"/sealup/sealup!getUnAttachByRegcode.action?time="+new Date()+"&reg_code="+reg_code+"&attachType="+enumdata.unattach;
		title="�����Ϣ";
	}
	//����ֺ���
	else if($("#redis_type").combodict("getValue")==enumdata.unreattach)
	{
		url=ctx+"/sealup/sealup!getUnAttachByRegcode.action?time="+new Date()+"&reg_code="+reg_code+"&attachType="+enumdata.unreattach;
		title="�ֺ�����Ϣ";
	}
	else
	{
		setDataIsNull();
	}
	
	setAttachInfo(url,title);
}

/**********************************************************************************
*��������: setDataIsNull
*����˵��: ���ý������Ϊ��
*����˵��: 
*�� �� ֵ: ��
*��������: xuzz
*��������: 2014-04-08
*�޸���ʷ: 
***********************************************************************************/
function setDataIsNull()
{
	$("#redis_off").val("");
		$("#redis_no").val("");
		$("#relaw_doc").val("");
		$("#reservice_name").val("");
		$("#redis_per_tel").numberbox('setValue',"");
		$("#reworkid").val("");
		$("#redis_date").datebox('setValue',"");	
		$("#reservice_date").datebox('setValue',"");	
}

/**********************************************************************************
*��������: saveAttach
*����˵��: ������Ǽ���Ϣ
*����˵��: 
*�� �� ֵ: ��
*��������: xuzz
*��������: 2014-04-08
*�޸���ʷ: 
***********************************************************************************/
function saveAttach(){
	 $.ajax({
	   		dataType:'json',
	   		url:ctx+"/sealup/sealup!saveUnAttach.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:$("#add_app_form").serialize(),
	   		success:function(data){
			 	if(data){
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
		var dis_off=$("#redis_off").val();
	 	var dis_no=$("#redis_no").val();
	 	var law_doc=$("#relaw_doc").val();
	 	var lim_holder=$("#relim_holder").val();
	 	var service_name=$("#reservice_name").val();
	 	var dis_per_tel=$("#redis_per_tel").numberbox('getValue');
	 	var workid=$("#reworkid").val();
	 	var service_date=$("#reservice_date").datebox('getValue');	
		if($.trim(dis_off).length==0){
			message = '����������أ�';
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









