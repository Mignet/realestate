<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ļ��ϴ�</title>
<script type="text/javascript">
var total = 1;
function addMore()
{
	if(total>10){
		alert("һ��ֻ���ϴ�10��");return;
	}
	total++;
	 var td = document.getElementById("more");
	 var br = document.createElement("br");
	 var input = document.createElement("input");
	 var button = document.createElement("input");
	 var span=document.createElement("span");
	 input.type = "file";
	 input.name = "upload";
	 button.type = "button";
	 button.value = "ɾ��";
	 
	 button.onclick = function()
	 {
	  td.removeChild(br);
	  td.removeChild(input);
	  td.removeChild(button);
	  td.removeChild(span);
	 }
	 input.onchange=function(){
	   clearTooltip(this);
	   checkExt(this);
	 }
	 td.appendChild(br);
	 td.appendChild(input);
	 td.appendChild(span);
	 td.appendChild(button);
}
function clearTooltip(){
	
}
function checkExt(o){
	
}
//������ʾ
function downfile(){
	new Request.JSON( {
		url : "upload!downFile.action?url="+$('url').value,
		onSuccess : function(responseText, txt) {
        if(responseText.code=='ok'){
        	var img = document.createElement('image');
        	img.src = '${ctx}/upload/'+ responseText.src;
        	document.body.appendChild(img);
        }else{
            alert('�����쳣!');
        }
	},
	onFailure:function() {
		alert('�����쳣!');
	} 
	}).get();
}
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
URL:<input id="url" type="text" size="50" value="201301/20130130/intrs1.jpg"/><input type="button" value="���ز���" onclick="downfile();"/>
</body>
</html>
