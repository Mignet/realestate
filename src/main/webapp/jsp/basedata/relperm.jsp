<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<jsp:include page="/plui2/head.jsp" />
<script type="text/javascript">
/**
 * 初始化方法
 * 窗口加载完成后由父窗口自动调用
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
			{ title:'权限名称',field:'perm_name',width:150 },
			{ title:'权限代码',field:'perm_code',width:250 }
		]],
		toolbar : ['->', {
			xtype : 'searchbox',
			prompt : '输入权限名称或代码',
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
<title>关联部门</title>
</head>
<body>
	<table id="roleDeptTable"></table>
</body>
</html>