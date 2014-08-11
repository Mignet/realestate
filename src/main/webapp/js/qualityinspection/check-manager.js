/*********************************************************************************
*��  ��  ��  ��: check-manager.js
*��  ��  ��  ��: ������
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: joyon
*��  ��  ��  �ڣ� 2014��4��15�� 
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
var sampleDataGrid;								//����datagrid
/**********************************************************************************
*��������: 
*����˵��: ҳ���ʼ��
*��������: joyon
*��������: 2014��4��15�� 11:46:54
*�޸���ʷ: 
***********************************************************************************/
window.onload = function(){
	//loadTargetInfo();
	
	$("#person_id").combouser();//combodeptuser();
	$("#dept").combodept({
		onSelect:function(data){
			//alert($.toJSON(data));
			//$("#PERSON_NAME").combodeptuser("setDeptId",data.id);
		}
	});
	
	sampleDataGrid = $('#table_business').datagrid({
		//��ҳ����
		loadFilter:pagerFilter,//�÷�����enum-data.js��
		height:320,
		//���������Դ
		url:ctx+'/qualityinspection/qualityinspection!getUncheckSample.action?search_str={"type":"check"}',
		//���ÿ�п���Զ���Ӧ����ܿ��
		fitColumns: true,
		//ȥ���߿�
		border : true,
		//�Ƿ��з�ҳ��
		pagination:true,
		//ÿҳ����
		//pageSize:10,
		//�Ƿ�����������һ����ʾ�кŵ���
		rownumbers:true,
		//����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		//idField: 'user_id',
		//�������Ƿ�����ʾ��ͬ����ɫ					
		striped:true,
		//ֻ����ѡһ��
		singleSelect:true,
		//�Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		//checkOnSelect:true,
		//�Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		//selectOnCheck:true,
		//����������
		columns:[[
			//ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
			
			{field:'BUS_ID',hidden:true},
			{field:'CHE_ID',hidden:true},
			{field:'ACTIVDEF_ID',hidden:true},
			{field:'URL_SPECIFY',hidden:true},
			{field:'PROCDEF_ID',hidden:true},
			{field:'PROC_ID',hidden:true},
			{field:'WI_ID',hidden:true},
			{field:'REG_CODE', title:'ҵ����',align :'center', width:60},
			{field:'procName', title:'ҵ������', align :'left',width:120,formatter:function(value){
				return "���� ���("+value+")";
			}},
			{field:'activeName',	title:'��ǰ����',align :'center', width:30},
			{field:'busprop',	title:'ҵ������',align :'center', width:20},
			{field:'duration',	title:'������',align :'center', width:30,formatter:function(value){
				return value/60/24;
			}},
			{field:'nodelimit',	title:'����ʱ��',align :'center', width:30},
			{field:'REMAIN_DAYS',	title:'����ʣ��',align :'center', width:30},
			{field:'CREATE_DATE',	title:'����ʱ��',align :'center', width:30},
			{field:'procstate',	title:'����״̬',align :'center', width:30},
			{field:'CHE_DATE',	title:'�ѳ��',align :'center', width:30,formatter:function(value){
				if($.trim(value).length==0){
					return "��";
				}else{
					return "��";
				}
			}},
			{field:'check',title:'����',formatter:function(value,rec){return '<span><input  type="button" value="����" onclick=""/></span>';}}
		]],
		onClickRow : function() {
			//�����ʱ����༭������ɾ������ť
			//alert("onClickRow  event");
			//btn_test_flow()
			$('#user_edit').linkbutton('enable');
			$('#user_delete').linkbutton('enable');
		},
		onClickCell:function(rowIndex, field, value){
			if(field=="check"){
				sampleDataGrid.datagrid('selectRow',rowIndex);
				checkSample(this);
			}
		},
		onLoadSuccess : function(data) {
			//alert(JSON.stringify(data));
			//������Ͻ��á��༭������ɾ������ť
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
		}
	});
}

/**********************************************************************************
*��������: loadTargetInfo
*����˵��: �����³��Ŀ����Ϣ
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�Ŀ�긳ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function loadTargetInfo(){
	$.ajax({
		url:ctx+"/qualityinspection/qualityinspection!getTargetInfo.action",
		type:"post",
		dataType:"json",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			if(data){
				$("#month_target").val(data.month_target);
				$("#inspectioned").val(data.inspectioned);
			}
		}
	});
	
}
/**********************************************************************************
*��������: checkSample
*����˵��: ���ѡ�е�ҵ��
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function checkSample(){
	//alert("����Ť�¼�");
	var selectedrow = sampleDataGrid.datagrid('getSelections');//�ҽ���jsonarray��
	selectedrow[0].type="checked";//��ʶ����
	//selectedrow[0].procId = selectedrow[0].PROC_ID;
	var selectedRowJson = $.toJSON(selectedrow);
	
	//alert(selectedRowJson);
	//return ;
	//alert(selectedrow.wiId);
	//var selectedrow = $('#table_user').datagrid('getSelections');
	//alert(JSON.stringify(selectedrow)+'-----');
	//�򿪴���  obj����
	var obj={	
			id:"open_app",
			src:ctx+"/jsp/qualityinspection/inspection-index.jsp",
			//���ڿ�
			width: 1150,
			//���ڸ�
			title:'�������ҳ��',
			height: 598,
			modal: true,	
			destroy:true,
			onLoad:	function(){
				//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
				//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
				this.openerWindow = window;
				//this.document.getElementById('name').value='����';
				//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
//				this.args = {
//					user: selectedrow
//					//userDataGrid: userDataGrid
//				};			
				this.init(selectedrow);
			}
		};	
//	selectedrow = {"activId":145813,"activName":"���","activdefId":"manual3","activdefName":"���","dayType":"workingDay","moduleId":1065,"moduleName":"��������","name":"���","procId":1000018824,"procName":"���ĳ��(��߶��ѺȨע���Ǽ�(��ҵܰ԰--20140314000000000018��))","procdefId":1290,"procdefName":"��������","startDate":"2014-04-23 17:37:25","state":"1","type":"push","urlSpecify":"/qualityinspection/inspection-index.action","urlType":"default","wiId":83139};
//	openInTopWindow(obj);
	//�������̳ɹ����ٴ򿪴���
	$.ajax({
		url:ctx+"/qualityinspection/qualityinspection!startInspection.action",
		type:"post",
		dataType:"json",
		data:{"selectedrow":selectedRowJson},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		async:false,
		success:function(data){
			
			//�������̳ɹ���  �ѵ�ǰ�����ɹ���ļ������ ������ҳ��
			selectedrow = data.workItem;
			//alert($.toJSON(selectedrow));
			//�򿪼�����̴���
			openInTopWindow(obj);	
			
			sampleDataGrid.datagrid("reload");
		}
	});
}

/**********************************************************************************
*��������: querySamle(){
*����˵��: ������ɸѡ����
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�Ŀ�긳ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function querySamle(){
	sampleDataGrid.datagrid("loading");
//	var form_data = $("#m_search_form").serialize();
//	form_data = decodeURIComponent(form_data,true);  
	var form_data = $("#m_search_form").serializeObject();
	form_data.type="check";
	//top.$.messager.alert('��ʾ',$.toJSON(form_data),'info',null);
	//top.$.messager.alert("��ʾ",$.toJSON(form_data),null);
	//return ;
	$.ajax({
		url:ctx+"/qualityinspection/qualityinspection!getUncheckSample.action",
		type:"post",
		dataType:"json",
		data:{"search_str":$.toJSON(form_data)},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			//alert($.toJSON(data));
			if(data){
				sampleDataGrid.datagrid("loadData",data);
				sampleDataGrid.datagrid("loaded");
			}
//			if(data.result=="success"){
//				//location.href="./qualityinspection.action";
//			}else{
//				top.$.messager.alert("��ʾ","��̨�����������ϵ����Ա��");
//			}
		}
	});
}

(function($){  
    $.fn.extend({  
        serializeObject:function(){  
            if(this.length>1){  
                return false;  
            }  
            var arr=this.serializeArray();  
            var obj=new Object;  
            $.each(arr,function(k,v){  
                obj[v.name]=v.value;  
            });  
            return obj;  
        }  
    });  
})(jQuery);  
