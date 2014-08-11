<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>图片浏览</title>
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
			<td>请选择要上传的文件</td>
			<td id="more"><s:file name="upload"
				onchange="clearTooltip(this);checkExt(this);"></s:file> <span></span>
			<input type="button" value="继续添加" onclick="addMore();"/></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><s:submit value="确定"></s:submit> <s:reset
				value="重置"></s:reset></td>
			<td></td>
		</tr>
	</table>
</s:form>
</body>
</html>
