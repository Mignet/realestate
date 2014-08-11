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

/**********************************************************************************
*��������: 
*����˵��: ҳ���ʼ��
*��������: joyon
*��������: 2014��4��15�� 11:46:54
*�޸���ʷ: 
***********************************************************************************/
window.onload = function(){
	//�л���springMVC����г���  ���ݳ�����
//	$("#person_id").combouser();//combodeptuser();
//	$("#dept").combodept({
//		onSelect:function(data){
//			//alert($.toJSON(data));
//			//$("#PERSON_NAME").combodeptuser("setDeptId",data.id);
//		}
//	});
	
	sampleDataGrid = $('#table_business').datagrid({
			//��ҳ����
			loadFilter:pagerFilter,//�÷�����enum-data.js��
			//fit:true,
			height:240,
			//���������Դ
			//url:ctx+'/qualityinspection/qualityinspection!getCurMonthBusiness.action',
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
			//singleSelect:true,
			//�Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
			//checkOnSelect:true,
			//�Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
			//selectOnCheck:true,
			//����������
			columns:[[
				//ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
				
				{field:'ck',checkbox:true},
				{field:'bus_id',hidden:true},
				{field:'reg_code', title:'ҵ����',align :'center', width:60, sortable:true},
				{field:'procName', title:'ҵ������', align :'left',width:120},
				{field:'activeName',	title:'��ǰ����',align :'center', width:30},
				{field:'busprop',	title:'ҵ������',align :'center', width:20},
				{field:'duration',	title:'������',align :'center', width:30,formatter:function(value){
					return value/60/24;
				}},
				{field:'nodelimit',	title:'����ʱ��',align :'center', width:30},
				{field:'REMAIN_DAYS',	title:'����ʣ��',align :'center', width:30},
				{field:'CREATE_DATE',	title:'����ʱ��',align :'center', width:30},
				{field:'procstate',	title:'����״̬',align :'center', width:30},
				{field:'inspectioned',	title:'�ѳ��',align :'center', width:30}
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
					$('#table_user').datagrid('selectRow',rowIndex);
					btn_bl(this);
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
		location.href="./qualityinspection.jsp";
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
	$.ajax({
		url:ctx+"/qualityinspection/qualityinspection!querySamele.action",
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
