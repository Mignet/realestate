<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK" import="com.szhome.security.ext.UserInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <title>�����в�����Ȩ�Ǽ�ϵͳ</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <script type="text/javascript" src="${ctx}/js/sys/rolemenu.js"></script>
</head>
<body>  
<div>�������ҵ��ı���Ϣ��������pdf�ļ�Ԥ��</div>
<iframe src="../base!exportToPDF.action" width="900px" height="400px"></iframe>
</body>
</html>
