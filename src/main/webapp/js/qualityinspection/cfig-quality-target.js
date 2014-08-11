/*********************************************************************************
*��  ��  ��  ��: cfig-quality-target.js
*��  ��  ��  ��: ��������ָ����Ϣ����jsѡ������js
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: joyon
*��  ��  ��  �ڣ� 2014��4��15�� 
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
var cfig_datagrid;							//��datagrid
var lastEditIndex=-1;						//�ɱ༭����
/**********************************************************************************
*��������: 
*����˵��: ҳ���ʼ��
*��������: joyon
*��������: 2014��4��15�� 11:46:54
*�޸���ʷ: 
***********************************************************************************/
window.onload = function(){
	initDatagrid();
}

function initDatagrid(){
	cfig_datagrid = $("#datagrid-div").datagrid({
		title : '��������ָ����Ϣ',
		fit : true,
		border : false,
		singleSelect : true,
		fitColumns : true,
		//�Ƿ�����������һ����ʾ�кŵ���
		rownumbers:true,
		url:ctx+'/qualityinspection/qualityinspection!getChkQualitytargetinfo.action',
		pagination : true,
		columns : [[
			{ title : 'ָ����', field : 'qua_index', width : 100,editor:'text' },
			{  field : 'qua_tar_id', hidden:true}
		]],
		toolbar : [{
			id : 'add',
			text : '����',
			iconCls : 'icon-add',
			handler : add
		},'-',{
			id : 'delete',
			text : 'ɾ��',
			iconCls : 'icon-remove',
			disabled : true,
			handler : function() {
				//ɾ���ֵ�����
				var selectedRow = cfig_datagrid.datagrid('getSelected');
				top.$.messager.confirm('ȷ��', '����������һ��ɾ��<br/>ȷ��Ҫɾ��[' + selectedRow.qua_index + ']��', function(result){
					if (result) {
						$.ajax({
							url:ctx+'/qualityinspection/qualityinspection!deleteChkQualitytarget.action',
							dataType : 'json',
							data : {
								qua_tar_id : selectedRow.qua_tar_id
							},
							type : 'post',
							success : function(data) {
								if (data.result=="success") {
									top.$.messager.alert('��ʾ', '����ɾ���ɹ���', 'info', function(){
										cfig_datagrid.datagrid('reload');
									});
								}
							}
						});
					}
				});
			}
		},'-',{
			id : 'save',
			text : '����',
			iconCls : 'icon-save',
			handler : save
		},'->',{
			//������� - ==> plui-searchbox
			xtype : 'searchbox',
			prompt : '����������ָ��',
			searcher : function(value, name) {
				var queryParams = {
					searchStr : value
				};
				cfig_datagrid.datagrid('load', queryParams);
			}
		}],
		onLoadSuccess : function(data) {
				//alert(JSON.stringify(data));
		},
		onClickRow : function(rowIndex, rowData) {
			//����ǰ��༭��   
			cfig_datagrid.datagrid('endEdit', lastEditIndex);
			
			lastEditIndex = rowIndex;
			$('#update').linkbutton('enable');
			$('#delete').linkbutton('enable');
			//�ѵ�ǰ����Ϊ�ɱ༭״̬
			cfig_datagrid.datagrid('beginEdit', lastEditIndex);
		}
	});
}
/**********************************************************************************
*��������: add
*����˵��: ������������Ŀ����Ϣ
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�Ŀ�긳ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function add() {
	//var dict = cfig_datagrid.datagrid('getSelected');
	cfig_datagrid.datagrid('appendRow',{  
		index:''  
    });
	//������������Ϊ�ɱ༩״̬
	var rowlen  = cfig_datagrid.datagrid('getRows').length;
	cfig_datagrid.datagrid('endEdit', lastEditIndex);
	cfig_datagrid.datagrid('beginEdit', rowlen - 1);
	lastEditIndex = rowlen - 1;
};
/**********************************************************************************
*��������: save
*����˵��: ������������Ŀ����Ϣ
*����˵��: 
*�� �� ֵ: ֱ�Ӹ�Ŀ�긳ֵ
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function save(){
	cfig_datagrid.datagrid('endEdit', lastEditIndex);
	cfig_datagrid.datagrid("unselectAll");
	lastEditIndex = -1;
	var inserted = cfig_datagrid.datagrid('getChanges', 'inserted');
	var deleted = cfig_datagrid.datagrid('getChanges', 'deleted');
	var updated = cfig_datagrid.datagrid('getChanges', 'updated');
	//�����̨��������
	$.ajax({
		url:ctx+'/qualityinspection/qualityinspection!applyChkQualitytargetEdit.action',
		dataType : 'json',
		type : 'post',
		data : {
			//������ƴװ��JSON�ַ������ݵ���̨
			datas :$.toJSON({
				inserted : inserted,
				deleted : deleted,
				updated : updated
			})
		},
		success : function(data) {
			if (data.success) {
				top.$.messager.alert('��ʾ', '���ݱ���ɹ���', 'info', function(){
					//�������Ϊ���ɱ༩
					//����ʱ���á��༭��ť��
					$('#dictitem_edit').linkbutton('enable');
					//����ʱ���¼��ر������
					cfig_datagrid.datagrid('reload');
				});
			}else{
				top.$.messager.alert('��ʾ', '���ݱ���ʧ�ܣ�', 'info', function(){});
			}
		}
	});
}
