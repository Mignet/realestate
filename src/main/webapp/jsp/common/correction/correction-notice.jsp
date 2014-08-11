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
<title>登记信息</title>
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
					&nbsp;<label style="font-size: 12px; font-weight: bold;">登记信息</label>
				</div>
				<table>
					<caption>
						<h2>深圳市房地产权登记中心房地产登记补正材料通知书</h2>
					</caption>
					<tr>
						<td><label id="rec_date" name="rec_date">2014年5月27日 </label>
							,收到 <label id="apps" name="apps">李四</label> 提出 <label
							id="bus_ype" name="bus_type">房屋初始登记</label> 申请。经核查，所提交材料: <input
							type="radio" name="rec_state" value="1" onclick="radioClick(this)"/><label>不齐全</label></td>
					</tr>
					<tr>
						<td><input type="radio" name="rec_state" value="2" onclick="radioClick(this)"/><label>不符合规定</label>
							<input type="radio" name="rec_state" value="3" onclick="radioClick(this)"/><label>不齐全且不符合规定</label>。
							请补正如下申请登记材料</td>
					</tr>
				</table>

				<table id="table_rec">

				</table>

				<p>请在收到本通知书之日起五日内按照上述要求补正申请登记材料送我中心，逾期未补正申请登记材料 的，视为自动放弃申请。</p>



			</div>
		</div>
	</div>
</body>
</html>
