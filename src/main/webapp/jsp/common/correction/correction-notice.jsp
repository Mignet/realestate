<%@ page language="java" import="java.util.*" pageEncoding="GBK"
	contentType="text/html;charset=GBK"
	import="com.szhome.security.ext.UserInfo"%>
<%
	UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
	String userName = userInfo.getUserName();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�Ǽ���Ϣ</title>
<meta http-equiv="content-type" content="text/html;charset=GBK">
	<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">
				<meta http-equiv="keywords" content="">

					<%@ include file="/base/taglibs.jsp"%>
					<%@ include file="/base/prefix.jsp"%>
					<style type="text/css">
body,html {
	margin: 0px;
	font-family: Arial;
	font-size: 12px;
	color: #333333;
}

.tip {
	color: #3CF;
}

.title {
	text-align: right;
}

.bg1 {
	background: none repeat scroll 0 0 #E0ECFF;
}

.bg2 {
	background: none repeat scroll 0 0 #F4F4F4;
}

.panel-body {
	background: none repeat scroll 0 0 #F8FAFF;
}
</style>
					<link rel="stylesheet" type="text/css"
						href="../../plui/demo/demo.css">
						<link rel="stylesheet" type="text/css"
							href="../../plui/themes/gray/plui.css">
							<script type="text/javascript">
								var user = '<%=userName%>';
								var ctx = '${ctx}';
							</script>
							<script type="text/javascript"
								src="${ctx}/js/common/correction/correction-notice.js"></script>
</head>
<body class="body_set">
	<div class="plui-layout" style="width: 100%; height: 100%;">
		<div data-options="region:'center',border:true">
			<div class="page_con">
				<div style="background-color: rgb(224, 236, 255); line-height: 18px"
					class="panel-header">
					&nbsp;<label style="font-size: 12px; font-weight: bold;">�Ǽ���Ϣ</label>
				</div>
				<table>
					<caption>
						<h2>�����з��ز�Ȩ�Ǽ����ķ��ز��Ǽǲ�������֪ͨ��</h2>
					</caption>
					<tr>
						<td><label id="rec_date" name="rec_date">2014��5��27�� </label>
							,�յ� <label id="apps" name="apps">����</label> ��� <label
							id="bus_ype" name="bus_type">���ݳ�ʼ�Ǽ�</label> ���롣���˲飬���ύ����: <input
							type="radio" name="rec_state" value="1" onclick="radioClick(this)"/><label>����ȫ</label></td>
					</tr>
					<tr>
						<td><input type="radio" name="rec_state" value="2" onclick="radioClick(this)"/><label>�����Ϲ涨</label>
							<input type="radio" name="rec_state" value="3" onclick="radioClick(this)"/><label>����ȫ�Ҳ����Ϲ涨</label>��
							�벹����������Ǽǲ���</td>
					</tr>
				</table>

				<table id="table_rec">

				</table>

				<p>�����յ���֪ͨ��֮���������ڰ�������Ҫ��������Ǽǲ����������ģ�����δ��������Ǽǲ��� �ģ���Ϊ�Զ��������롣</p>



			</div>
		</div>
	</div>
</body>
</html>
