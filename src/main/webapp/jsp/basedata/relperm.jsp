<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<jsp:include page="/plui2/head.jsp" />
<script type="text/javascript">
/**
 * ��ʼ������
 * ���ڼ�����ɺ��ɸ������Զ�����
 */
function __init() {
	var roleDeptTable = $('#roleDeptTable').datagrid({
		url : 'userPermDelegate/searchPerms.run',
		fit : true,
		fitColumns : true,
		rownumbers:true,
		border : false,
		queryParams : {
			user_id : args.user.user_id
		},
		singleSelect : true,
		pagination : true,
		pageSize : 18,
		pageList : [18,50,100],
		columns : [[
			{ title:'Ȩ������',field:'perm_name',width:150 },
			{ title:'Ȩ�޴���',field:'perm_code',width:250 }
		]],
		toolbar : ['->', {
			xtype : 'searchbox',
			prompt : '����Ȩ�����ƻ����',
			searcher : function(value) {
				roleDeptTable.datagrid('load', {
					user_id : args.user.user_id,
					searchStr : value
				});
			}
		}]
	});
};
</script>
<title>��������</title>
</head>
<body>
	<table id="roleDeptTable"></table>
</body>
</html>