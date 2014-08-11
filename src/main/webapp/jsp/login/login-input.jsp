<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户登陆</title>
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/js/login/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/login/login.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/login/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/login/esys.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/login/jquery.autocomplete.css">
<script type="text/javascript">
	var showTitleOverImage = '1';
	var admin = '';
	var errorLoginMsg = "${errorLoginMsg}";
	var loginflag="${loginflag}";

</script>
    </head>
   <body style="overflow:hidden; height:100%;">
		
	<table align="center" width="100%" height="100%" class="logonForm" border=1>
		<tr>
			<td valign="middle" align="center" id="">
				<!-- 深圳市不动产权登记系统 -->
				<div id="SystemTitle" style="display: block;">深圳市不动产登记系统</div>
				<form id="loginForm" action="login!userLogin.action" method="post" onsubmit="return check();">
				<table style="text-align: center; font-size: 10pt;width: 375px;height: 211px;position: relative;top:80px;" id="logonInfo">
					<tbody><tr height="50">
						<td colspan="3">&nbsp;</td>
					</tr>
						<tr>
							<td width="80" style="color: white;" align="right">用户名：</td>
							<td style="width: 150px; height: 25px;" align="left">
								<input type="text" name="usercode" id="usercode" style="ime-mode:disabled;width: 150px;height: 22px;line-height: 22px;" autocomplete="off" class="ac_input" maxlength="20">
								<input type="hidden" id="uid">
							</td>
							<td rowspan="2" valign="middle" style="text-align: center;" id="btnLogin" class="login-btn-large">
								<span style="padding:0 0 0 5px;">登　录</span>
							</td>
						</tr>
						<tr>
							<td width="80" style="color: white;" align="right">密　码：</td>
							<td align="left">
								<input  type="password" name="password" id="password" style="width: 150px;height: 22px;line-height: 22px;">
							</td>
						</tr>
						
					
					<tr height="100">
						<td colspan="3">&nbsp;</td>
					</tr>
				</tbody></table>
				</form>
			</td>
		</tr>
	</table>

	<script  charset="GBK" type="text/javascript" src="${ctx}/js/login/jquery.autocomplete.min.js"></script>

	<script type="text/javascript" src="${ctx}/js/login/login-input.js"></script>
	<script type="text/javascript" src="${ctx}/js/login/util.js"></script>
</body>
</html>

