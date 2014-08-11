<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>登记簿预览</title>
  <%@ include file="/base/taglibs.jsp"%>
  <%@ include file="/base/prefix.jsp" %>
<style>
	div.selected{
		display:block;
	}
	table{
		margin:auto;
		width:600px;
	}
	table input{
		float:left;
		readOnly:expression(this.readOnly=true);
		BORDER-TOP-STYLE: none; 
		BORDER-RIGHT-STYLE: none;
		BORDER-LEFT-STYLE: none; 
		
	}
	table label{
		float:left;
	}
	.table_center input{
		text-align:center;
		float:none;
	}
	.table_center label{
		float:none;
	}
</style>
<script type="text/javascript">
	//<![CDATA[
	   var ctx='${ctx}';
	   var reg_unit_type = '${reg_unit_type}';
	   var realestate_type = '${realestate_type}';
	//]]
</script>
<script type="text/javascript" src="${ctx}/js/bookmanage/registerView.js"></script>
</head>
<body>
    <!-- 左边菜单栏 -->
	<div id="cleft" data-options="region:'west'"
		style="width: 193px; height: 600px">
		<ul id="registerbook_tree"></ul>
	</div>
	<div data-options="region:'center'" style="text-align: center">
			<!-- 自然信息层 -->
	    <jsp:include page="registernatural.jsp" flush="false"></jsp:include>
		<c:choose> 
			<c:when test="${reg_unit_type eq PARCELCODE}"> 
				<!-- 使用权信息层-->
				  <jsp:include page="registeruseland.jsp" flush="false"></jsp:include>
			</c:when>
			<c:otherwise>
				<!-- 所有权信息层-->
				 <jsp:include page="registerusehouse.jsp" flush="false"></jsp:include>
			</c:otherwise>
		</c:choose>
			<!-- 抵押权信息层-->
			<jsp:include page="registermort.jsp" flush="false"></jsp:include>
			<!-- 地役权信息层-->
			<jsp:include page="registereasement.jsp" flush="false"></jsp:include>
			<!-- 查封登记信息层-->
			<jsp:include page="registerattach.jsp" flush="false"></jsp:include>
			<!-- 异议登记信息层-->
			<jsp:include page="registerdissent.jsp" flush="false"></jsp:include>
			<!-- 预告登记信息层-->
			<c:if test="${reg_unit_type eq HOUSECODE}">
			  <jsp:include page="registerpreadvice.jsp" flush="false"></jsp:include>
			</c:if>
	</div>
</body>
</html>
