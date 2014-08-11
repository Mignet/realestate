<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�Ǽǲ�Ԥ��</title>
<%@ include file="/base/taglibs.jsp"%>
<c:if test="1==2">
    <%@ include file="/base/prefix.jsp" %>
</c:if>
<script type="text/javascript" src="${ctx }/js/bookmanage/registereasement.js"></script>
</head>

<body>
	    <!-- ����Ȩ��Ϣ�� -->
		<div id='easementInfo' style="display:none;">
			<form id="easementInfoForm">
				<hr>
			<div id="divEasementInfo" style="height:400px;overflow-y:auto">
				<table id="table_easement">
				    <!-- ��ѺȨ�������ֿ�ʼ -->
					<tr>
						<td width='150'>
						   <c:choose>
						    <c:when test="${reg_unit_type eq HOUSECODE }">
						     <label for="HOUSE_CODE">���ݱ��</label>
						   </c:when>
						   <c:when test="${reg_unit_type eq PARCELCODE }">
						     <label for="PARCEL_CODE">�ڵر��</label>
						   </c:when>
						   <c:otherwise>
						     <label for="BUILDING_CODE">¥����</label>
						   </c:otherwise>
						   </c:choose>
						 </td>
						<td colspan=3>
						<c:choose>
						    <c:when test="${reg_unit_type eq HOUSECODE }">
						    <input class="plui-validatebox" readonly type="text"
							name="HOUSE_CODE" id="HOUSE_CODE"  style="width:98%"/>
						   </c:when>
						   <c:when test="${reg_unit_type eq PARCELCODE }">
						     <input class="plui-validatebox" readonly type="text"
							        name="PARCEL_CODE" id="PARCEL_CODE"  style="width:98%"/>
						   </c:when>
						   <c:otherwise>
						     <label for="BUILDING_CODE">¥����</label>
						   </c:otherwise>
						</c:choose>
						</td>
					</tr>
					<tr>
						<td>
						<c:choose>
						   <c:when test="${reg_unit_type eq HOUSECODE }">
						     <label for="HOUSE_LOCATION">��������</label>
						   </c:when>
						   <c:when test="${reg_unit_type eq PARCELCODE }">
						     <label for="LAND_ADDRESS">�ڵ�����</label>
						   </c:when>
						   <c:otherwise>
						     <label for="BUILD_LOCATION">¥������</label>
						   </c:otherwise>
						   </c:choose>
                        </td>
						<td colspan=3>
						<c:choose>
						   <c:when test="${reg_unit_type eq HOUSECODE }">
						     <input class="plui-validatebox" readonly type="text"
							   name="HOUSE_LOCATION" id="HOUSE_LOCATION"  style="width:98%"/>
						   </c:when>
						   <c:when test="${reg_unit_type eq PARCELCODE }">
						     <input class="plui-validatebox" readonly type="text"
							   name="LAND_ADDRESS" id="LAND_ADDRESS"  style="width:98%"/>
						   </c:when>
						   <c:otherwise>
						     <label for="BUILD_LOCATION">¥������</label>
						   </c:otherwise>	
						   </c:choose>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<hr width="600px">
						</td>
					</tr>
					<tr>
						<td><label for="REG_CODE">�ǼǱ��</label></td>
						<td><input class="plui-validatebox" readonly type="text" name="REG_CODE" id="REG_CODE" /></td>
						<td width='150'><label for="BUS_NAME">�Ǽ�����</label></td>
						<td>
						<input class="plui-validatebox" readonly type="text" id="BUS_NAME" name="BUS_NAME" />
							<!-- <input type="hidden" class="plui-combodict" code='reg_type'  />   -->
                        </td>
					</tr>
					<tr>
						<td><label for="EASE_HOLDER">����Ȩ��</label></td>
						<td colspan=3><input class="plui-validatebox" readonly type="text"
							id="EASE_HOLDER" name="EASE_HOLDER" style="width:98%"/></td>
					</tr>
					<tr>
						<td><label for="EASE_SET_ITEM">����Ȩ�������</label></td>
						<td colspan=3><input class="plui-validatebox" readonly type="text" id="EASE_SET_ITEM" name="EASE_SET_ITEM" style="width:98%"/></td>
					</tr>
					<tr>
						<td><label for="EASEMENT_LIMIT">����Ȩ��������</label></td>
						<td colspan=3>
						    <input class="plui-validatebox" readonly type="text" id="EASEMENT_LIMIT" name="EASEMENT_LIMIT" style="width:98%" />
						</td>
					</tr>
					<tr>
						<td><label for="CER_NO">����Ȩ֤��</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text" id="CER_NO" name="CER_NO"  style="width:98%"/>
						</td>
					</tr>
					<tr>
						<td><label for="REG_DATE_N">�Ǽ�ʱ��</label></td>
						<td><input class="plui-validatebox" readonly type="text" id="REG_DATE_N" name="REG_DATE_N"/></td>
						<td><label for="RECORDER">������/�Ǳ���</label></td>
						<td><input class="plui-validatebox" readonly type="text" id="RECORDER" name="RECORDER"/></td>
					</tr>
					<!-- ��ѺȨ�������ֽ��� -->
					<tr>
						<td colspan="4">
							<hr width="600">
						</td>
					</tr>
				</table>
				<table>
				<tr>
					<td><label for="EXCURSUS" style="float: left">����</label></td>
				</tr>
				<tr>
					<td><textArea name="EXCURSUS" readonly id="EXCURSUS"
							style="width: 600px; height: 100px;">
										</textArea></td>
				</tr>
			</table>
			   </div>
				<hr>
				<table id="table_easementInfo" style="BORDER-COLLAPSE: collapse;"
					borderColor=#000000 align=center border=1 class="table_center">
					<tr>
						<td>���</td>
						<td>�Ǽ�����</td>
						<td>����Ȩ��</td>
						<td>�Ǽ�����</td>
					</tr>
				</table>
			</form>
		</div>
</body>

</html>
