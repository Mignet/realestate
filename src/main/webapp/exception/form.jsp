<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Ĭ���쳣</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
        
       <%@ include file="/base/prefix.jsp"%>
                
        <script>
			$(function(){
				$('#form').tableform({
					url: '../../errorDelegate/throwSpecifiedException.run',
					dataType:'json',
					errorCode: 700,
					success: function(data){},
					//��Ԫ�ر���������ͨ����Ϊ���У�
					colnum: 1,
					//���ؼ������п�
					titleWidth: 120,
					//���ؼ�������п�
					cellWidth: 300,
					//���ؼ�input��select��combo��������
					inputWidth:	200,
					//textarea�ؼ�������
					textareaWidth: 600,
					//textarea�ؼ�������
					textareaHeight: 90,
					//�Ƿ�����fieldset��ǩ
					fieldset:false	,
					//��Ԫ�ز������顣������ÿ�����󹹳�һ����Ԫ��
					inputs: [{
						tag: 'input',
						title: '��������',
						name: 'dept_name',
						id: 'dept_name',
						type: 'validatebox',
						options: {
							required: true,
							missingMessage: '��Ϊ������0'
						}
					},{
						//���ؼ���ǩ����������input��select��textarea�ȡ�
						tag: 'select',
						//�ؼ������������
						title: 'ѡ���׳��쳣',
						//�ؼ��ֶ���
						name: 'index',
						//�ؼ�id����ѡ
						id: 'index',
						//�ؼ����ͣ�������plui���ؼ�comboϵ�С���û�õ�combo���ʱ�������ô�ֵ��
						type: 'combobox',
						//combo�ؼ����ɲ�������û�õ�combo���ʱ�������ô�ֵ
						options: {
							width:202,
							height: 24,
							required: true,
							missingMessage: '��Ϊ������1',
							valueField: 'id',
							textField: 'name',
							data: [{
								id: 0,
								name: '�쳣0'
							},{
								id: 1,
								name: '�쳣1'
							},{
								id: 2,
								name: '�쳣2'
							},{
								id: 3,
								name: '�쳣3'
							},{
								id: -1,
								name: 'Ĭ���쳣'
							}]
						}
					}]
				});
			});
        </script>
    </head>
    <body>    	
		<form id="form"></form>
    </body>
</html>

