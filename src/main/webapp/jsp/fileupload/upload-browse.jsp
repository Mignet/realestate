<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ͼƬ���</title>
<script type="text/javascript">
var total = 1;
</script>
</head>
<body>
<table align="center" width="50%">
	<tr>
		<td><s:fielderror cssStyle="color:red" /></td>
	</tr>
</table>
<s:form action="upload.action" theme="simple" enctype="multipart/form-data" method="post">
	<table align="center" width="50%" border="1">
		<tr>
			<td>��ѡ��Ҫ�ϴ����ļ�</td>
			<td id="more"><s:file name="upload"
				onchange="clearTooltip(this);checkExt(this);"></s:file> <span></span>
			<input type="button" value="�������" onclick="addMore();"/></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><s:submit value="ȷ��"></s:submit> <s:reset
				value="����"></s:reset></td>
			<td></td>
		</tr>
	</table>
</s:form>
</body>
</html>
