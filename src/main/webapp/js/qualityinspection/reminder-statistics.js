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
var tmp_datagrid_data;							//��ʱdatagriddata  ����������л�divʱcss�����ݲ���Ⱦ����

/**********************************************************************************
*��������: 
*����˵��: ҳ���ʼ��
*��������: joyon
*��������: 2014��4��15�� 11:46:54
*�޸���ʷ: 
***********************************************************************************/
window.onload = function(){
	$("#person_id").combouser();//combodeptuser();
	$("#dept").combodept({
		onSelect:function(data){
			//alert($.toJSON(data));
			//$("#PERSON_NAME").combodeptuser("setDeptId",data.id);
		}
	});
	//��ʼ�����
	initDatagrid();
	
	initReport();

}
/**********************************************************************************
*��������: initReport(){
*����˵��: ��ʼ��ͳ��ͼ
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�Ŀ�긳ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function initReport(){
	$("#div_report").empty();
	//��ʼ��ͳ��ͼ
	 var url=ctx+"/common/print!printUrage.action?time="+new Date();
	  //alert(url);
	 // document.frames["printed"].location=url;
	 //var str='<object id="pdf1"  classid="clsid:CA8A9780-280D-11CF-A24D-444553540000"    width="100%"    height="560"  border="0"> <param   name="_Version"   value="65539"> <param   name="_ExtentX"   value="20108"> <param   name="_ExtentY"   value="10866"> <param   name="_StockProps" value="0"> <param    name="SRC"  value="'+url+'"      ></object> ';
	// var str = '<iframe  id="printed"  name="printed"  width="100%" height="580" src="'+url+'"></iframe>';
	 var str = '<image src="'+url+'" width="100%" height="600" />';
	 document.getElementById('div_report').innerHTML=str;
}

/**********************************************************************************
*��������: initDatagrid(){
*����˵��: ��ʼ�����
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�Ŀ�긳ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function initDatagrid(){
	sampleDataGrid = $('#table_business').datagrid({
		//��ҳ����
		loadFilter:pagerFilter,//�÷�����enum-data.js��
		//fit:true,
		height:320,
		//���������Դ
		url:ctx+'/qualityinspection/qualityinspection!getUrgeStatistics.action',
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
			{field:'BUS_TYPE_NAME', title:'ҵ������', align :'left',width:120},
			{field:'BUS_TYPE_NAME1', title:'������', align :'left',width:120},
			{field:'BUS_TYPE_NAME2', title:'����', align :'left',width:120},
			{field:'ACC_COUNT', title:'��������', align :'left',width:120},
			{field:'APP_COUNT', title:'��׼����', align :'left',width:120},
			{field:'ARCH_COUNT', title:'�鵵����', align :'left',width:120},
			{field:'RE_COUNT', title:'������', align :'left',width:120},
			{field:'TO_COUNT', title:'��ʱ��', align :'left',width:120},
			//{field:'ck',checkbox:true},
//			{field:'bus_id',hidden:true},
//			{field:'reg_code', title:'ҵ����',align :'center', width:60, sortable:true},
//			{field:'procName', title:'ҵ������', align :'left',width:120},
//			{field:'activName',	title:'��ǰ����',align :'center', width:30},
//			{field:'busprop',	title:'ҵ������',align :'center', width:20},
//			{field:'duration',	title:'������',align :'center', width:30,formatter:function(value){
//				return value/60/24;
//			}},
//			{field:'node_duration',	title:'����ʱ��',align :'center', width:30},
//			{field:'node_remain',	title:'����ʣ��',align :'center', width:30},
//			{field:'node_start_date',	title:'����ʱ��',align :'center', width:30},
//			{field:'procstate',	title:'����״̬',align :'center', width:30},
//			{field:'user_name',	title:'�а���',align :'center', width:30},
//			{field:'button',title:'����',align :'center',formatter:function(value,rec){return '<span><input  type="button" name="btn_detail" value="����" onclick="btn_click(this);"/><input name="btn_reminder"  type="button" value="����" onclick="btn_click(this);"/></span>';}}
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
			tmp_datagrid_data = data;
			//alert(JSON.stringify(data));
			//������Ͻ��á��༭������ɾ������ť
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
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
	//top.$.messager.alert('��ʾ',$.toJSON(form_data),'info',null);
	//top.$.messager.alert("��ʾ",$.toJSON(form_data),null);
	//return ;
	//alert();
	$.ajax({
		url:ctx+"/qualityinspection/qualityinspection!getUrgeStatistics.action",
		type:"post",
		dataType:"json",
		data:{"search_str":$.toJSON(form_data)},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
//			alert($.toJSON(data));
			//top.$.messager.alert("��ʾ",$.toJSON(data));
			
			
			if(data){
				tmp_datagrid_data = data;
				sampleDataGrid.datagrid("loadData",data);
				sampleDataGrid.datagrid("loaded");
			}
			
			initReport();
//			if(data.result=="success"){
//				//location.href="./qualityinspection.action";
//			}else{
//				top.$.messager.alert("��ʾ","��̨�����������ϵ����Ա��");
//			}
			
		}
	});
	
	sampleDataGrid.datagrid("loaded");
}
/**********************************************************************************
*��������: btn_click(){
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

function radioClick(radio){
	var show_type = radio.value;
//	alert(show_type);
	//ͳ�Ʊ�ѡ���¼�
	if(show_type == 'table'){
		//alert(show_type);
		$("#div_table").css("display","block");
		$("#div_report").css("display","none");
		sampleDataGrid.datagrid("loadData",tmp_datagrid_data);
	}else{
		//alert(show_type+"ddd");
		$("#div_table").css("display","none");
		$("#div_report").css("display","block");
	}

	//alert(radio.value);
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
