<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>��������</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
        
        <jsp:include page="/plui2/head.jsp" />
        
<script>
/**  */
function init(user_id){
	$('#form').tableform({
		errorCode: 700,
		//��Ԫ�ر���������ͨ����Ϊ���У�
		colnum: 1,
		//���ؼ������п�
		titleWidth: 120,
		//���ؼ�������п�
		cellWidth: 400,
		//���ؼ�input��select��combo��������
		inputWidth:	300,
		//textarea�ؼ�������
		textareaWidth: 300,
		//textarea�ؼ�������
		textareaHeight: 90,
		//�Ƿ�����fieldset��ǩ
		fieldset:false	,
		//��Ԫ�ز������顣������ÿ�����󹹳�һ����Ԫ��
		inputs: [{
			tag: 'select',
			title: '��������',
			name: 'depts',
			id: 'depts',
			type: 'combodept',
			options: {
				required: true,
				missingMessage: '������',
				multiple: true,
				valueMergeField : 'deptIds',
				textMergeField : 'deptNames'
			}
		},{
			//���ؼ���ǩ����������input��select��textarea�ȡ�
			tag: 'select',
			//�ؼ������������
			title: '��������',
			//�ؼ��ֶ���
			name: 'type',
			//�ؼ�id����ѡ
			id: 'type',
			//�ؼ����ͣ�������plui���ؼ�comboϵ�С���û�õ�combo���ʱ�������ô�ֵ��
			type: 'combodict',
			//combo�ؼ����ɲ�������û�õ�combo���ʱ�������ô�ֵ
			options: {
				classCode: 'platt_dept_type',
				required: true,
				missingMessage: '��Ϊ������1'
			}
		},{
			tag: 'input',
			title: '����ְ��',
			name: 'duty',
			id: 'duty'
		},{
			tag: 'select',
			title: '�Ƿ��쵼',
			name: 'is_leader',
			id: 'is_leader',
			type: 'combodict',
			options: {
				classCode: 'yes_or_no'
			}
		},{
			tag: 'select',
			title: '��ʾ��ͨѶ¼',
			name: 'is_addresslist',
			id: 'is_addresslist',
			type: 'combodict',
			options: {
				classCode: 'yes_or_no'
			}
		},{
			tag: 'input',
			title: '����',
			name: 'turn',
			id: 'turn',
			type: 'numberbox'
		},{
			tag: 'textarea',
			title: '������ע',
			name: 'remark',
			id: 'remark'
		}],
		url: '../deptUserRelDelegate/saveDeptUserRelFromUser.run?user_id='+user_id,
		dataType: 'json',
		onSubmit: function(){
			var isValid = $(this).form('validate');
			
			return isValid;	// ����false��ֹͣform�ύ 
		},
		success:function(data){
			if (data.success) {
				top.$.messager.alert('�������Ź�����ʾ','�������Ź����ɹ���','info',function(){
					closeInTopWindow('palt_relDeptAdd');
				});
			} else {
				top.$.messager.alert('�������Ź�������',data.errorMessage,'error');
			}		
		}
	});
	/** ��ʼ����
	var initdata = {'is_addresslist':'1','is_leader':'0'};
	$('#form').form("load",initdata);
	*/
};
</script>
    </head>
    <body>
    	<div class="plui-panel" data-options="fit:true,border:false">
	    	<form id="form" method="post"> 
	        </form>
	        <div style="text-align:center;">
               <a id="saveBtn" onclick="$('#form').submit();" class="plui-linkbutton" data-options="iconCls:'icon-save'">����</a>
               <a id="resetBtn" onclick="$('#form').form('clear');" class="plui-linkbutton" data-options="iconCls:'icon-reload'">����</a>
               <a id="closeBtn" onclick="closeInTopWindow('palt_relDeptAdd');" class="plui-linkbutton" data-options="iconCls:'icon-undo'">�ر�</a>
	        </div>
        </div>
    </body>
</html>
