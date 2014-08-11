/*********************************************************************************
*��  ��  ��  ��: selectsample.js
*��  ��  ��  ��: ѡ������js
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: joyon
*��  ��  ��  �ڣ� 2014��4��15�� 
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/

var sampleDataGrid;								//����datagrid
var datagrid_btn_name;							//��Ť��  �������ֵ���������黹�Ƕ���

/**********************************************************************************
*��������: 
*����˵��: ҳ���ʼ��
*��������: joyon
*��������: 2014��4��15�� 11:46:54
*�޸���ʷ: 
***********************************************************************************/
window.onload = function(){
	$("#person_id").combouser();  
//	$("#dept").combodept({
//		onSelect:function(data){
//			//alert($.toJSON(data));
//			//$("#PERSON_NAME").combodeptuser("setDeptId",data.id);
//		}
//	});
	
	//�Ի���
	 $('#div_dialog').dialog({  
		 title: '��������',  
	     width: 333,  
	     height: 300,  
	     cache: false,  
	     modal: true,  
	     closed:true,
         buttons:[{  
             text:'ȷ��',  
             iconCls:'icon-ok',  
             handler:reminderSurebtnClick
         },{  
             text:'ȡ��',  
             handler:function(){  
                 $('#div_dialog').dialog('close');  
             }  
         }]  
     }); 
  
	
	sampleDataGrid = $('#table_business').datagrid({
			//loadFilter:pagerFilter,
			//fit:true,
			height:320,
			//���������Դ
			url:ctx+'/qualityinspection/qualityinspection!getRemindersBusiness.action',
			//���ÿ�п���Զ���Ӧ����ܿ��
			fitColumns: true,
			//ȥ���߿�
			border : true,
			//�Ƿ��з�ҳ��
			pagination:true,
			//ÿҳ����
			pageSize:10,
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
				
				//{field:'ck',checkbox:true},
				{field:'BUS_ID',hidden:true},
				{field:'REG_CODE', title:'ҵ����',align :'center', width:60, sortable:true},
				{field:'PROC_NAME', title:'ҵ������', align :'left',width:120},
				{field:'activName',	title:'��ǰ����',align :'center', width:30},
				{field:'busprop',	title:'ҵ������',align :'center', width:20},
				{field:'PI_DURATION',	title:'������',align :'center', width:30,formatter:function(value){
					return value/60/24;
				}},
				{field:'node_duration',	title:'����ʱ��',align :'center', width:30},
				{field:'node_remain',	title:'����ʣ��',align :'center', width:30},
				{field:'node_start_date',	title:'����ʱ��',align :'center', width:60},
				{field:'procstate',	title:'����״̬',align :'center', width:30},
				{field:'user_name',	title:'�а���',align :'center', width:30},
				{field:'button',title:'����', width:80,align :'center',formatter:function(value,rec){return '<span><input  type="button" name="btn_detail" value="����" onclick="btn_click(this);"/><input name="btn_reminder"  type="button" value="����" onclick="btn_click(this);"/></span>';}}
			]],
			onClickRow : function() {
				//�����ʱ����༭������ɾ������ť
				//alert("onClickRow  event");
				//btn_test_flow()
				$('#user_edit').linkbutton('enable');
				$('#user_delete').linkbutton('enable');
			},
			onClickCell:function(rowIndex, field, value){
				//alert("onclickcell event"+rowIndex+field+value);
				if(field=="button"){
					sampleDataGrid.datagrid('selectRow',rowIndex);
					//btn_click(this);
					clickCell();
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
*��������: saveSelectsamle
*����˵��: ����ѡ���������Ϣ
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�Ŀ�긳ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function saveSelectsamle(){
	var checkedSample = sampleDataGrid.datagrid("getChecked");
	if(checkedSample.length==0){
		location.href="./qualityinspection.action";
		return;
	}
	
	checkedSample = $.toJSON(checkedSample);
	$.ajax({
		url:ctx+"/qualityinspection/qualityinspection!saveChecksample.action",
		type:"post",
		dataType:"json",
		data:{"sample":checkedSample},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			if(data.result=="success"){
				location.href="./qualityinspection.action";
			}else{
				top.$.messager.alert("��ʾ","��̨�����������ϵ����Ա��");
			}
		}
	});
	//alert(JSON.stringify(checkedSample));
}
/**********************************************************************************
*��������: querySamle
*����˵��: ������ɸѡ����
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�Ŀ�긳ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function querySamle(){
	var form_data = $("#m_search_form").serializeObject();
	//ɸѡ ����  
	var data ={"search_str":$.toJSON(form_data)};
	//���������¼���datagrid
	sampleDataGrid.datagrid("load",data);
}
/**********************************************************************************
*��������: btn_click
*����˵��: ��������Ť����ֵ
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�Ŀ�긳ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function btn_click(btn){
	datagrid_btn_name = btn.name;
	//alert(btn.name);
}
/**********************************************************************************
*��������: clickCell
*����˵��: ��Ԫ��������  �ж���������������������̲鿴����  ���������Ƕ���  ��򿪶������
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�Ŀ�긳ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function clickCell(){
	//alert(datagrid_btn_name);
	if(datagrid_btn_name=='btn_detail'){
		openWorkflow();
	}else if(datagrid_btn_name =='btn_reminder'){
		openReminder();
	}
}
/**********************************************************************************
*��������: openWorkflow
*����˵��: ������ҳ��鿴��������
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�Ŀ�긳ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function openWorkflow(){
	var selectedrow = sampleDataGrid.datagrid('getSelected');
	
	//alert(selectedrow.wiId);
	//var selectedrow = $('#table_user').datagrid('getSelections');
	//alert(JSON.stringify(selectedrow)+'-----');
	//return;
	//�򿪴���  obj����
	var obj={	
		id:"open_app",
		//���ڿ�
		width: 988,
		//���ڸ�
		title:'�칫ҳ��',
		height: 598,
		modal: true,	
		destroy:true,
		onLoad:	function(){
			//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
			//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
			this.openerWindow = window;
			//this.document.getElementById('name').value='����';
			//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
			this.args = {
				user: selectedrow,
			};			
			this.init(selectedrow);
		}
	};				
	
	//s
	var url = selectedrow.url;
	//alert(data);
	if(!url){
		alert("�õ�ַ�����ڣ�"+url);							
	}
	else if(url=='null'||url==null||url==''||url==undefined )
	{
		alert("��ַΪnull");
	}else if(url.indexOf("qualityinspection/inspection-index")!=-1){
//		if(url.indexOf(ctx)==-1){
//			url = ctx+url;
//		}
		if(url.indexOf(ctx)==-1){
			url = ctx+url;
		}
		obj.width=1230;
		//obj.src=url;
		
		
		/*window.open(obj);*/
		//openInTopWindow(obj);	
		
		//return null;
	}
	else
	{													
		//window.showModalDialog(data+'?wiId='+selectedrow.wiId,'NEW','dialogHeight: 600px; dialogWidth: 1200px;');
		//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
		//obj.src=url;
		/*window.open(obj);*/
				
	}
	url = url.replace(ctx,ctx+"/jsp");
	url = url.replace(".action",".jsp");
	obj.src=url;
	//alert(obj.src);
	openInTopWindow(obj);	
	//this.init(selectedrow);
}
/**********************************************************************************
*��������: openReminder
*����˵��: �򿪶������  ���ж������
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�Ŀ�긳ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function openReminder(){
	
	$('#div_dialog').dialog('open');  
}
/**********************************************************************************
*��������: reminderSurebtnClick
*����˵��: �򿪶������ ȷ����Ť ����¼�
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�Ŀ�긳ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function reminderSurebtnClick(){
	var selectedrow = sampleDataGrid.datagrid('getSelected');
	var bus_id = selectedrow.bus_id;
	var user_id = selectedrow.user_id;
	var message_type = $('input:radio[name="type"]:checked').val();
	var is_re = $('input:radio[name="is_re"]:checked').val();
	var message = $("#txt_message").text(); 
	message = "�ǼǱ�ţ�"+selectedrow.reg_code+" "+message;
	$.ajax({
		url:ctx+"/qualityinspection/qualityinspection!reminde.action",
		type:"post",
		dataType:"json",
		data:{"bus_id":bus_id,"user_id":user_id,"message_type":message_type,"message":message,"is_re":is_re},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			//alert($.toJSON(data));
			if(data.result){
				$('#div_dialog').dialog('close');  
				top.$.messager.alert("��ʾ","����ɹ���");
				
			}
		}
	});
	//alert(message_type+is_re+message);
}
/**********************************************************************************
*��������: reset
*����˵��: form��������
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function reset(){
	//$('#tab_reg_info').form('reset');
	$('#tab_reg_info')[0].reset()
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
