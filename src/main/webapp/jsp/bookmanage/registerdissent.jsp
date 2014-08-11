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
<script type="text/javascript" src="${ctx}/js/bookmanage/registerdissent.js"></script>
</head>
<body>
	    <!-- ����Ǽ� ��Ϣ��-->
		<div id='dissentInfo' style="display:none;">
			<form id="dissentInfoForm">
				<hr>
				 <div id="divDissentInfo" style="height:400px;overflow-y:auto"> 
				<table id="table_easement">
				    <!-- ��ѺȨ�������ֿ�ʼ -->
					<tr>
						<td width="150px">
						  <c:choose>
						   <c:when test="${reg_unit_type eq HOUSECODE }">
						     <label for="HOUSE_CODE">���ݱ��</label>
						   </c:when>
						   <c:when test="${reg_unit_type eq PARCELCODE }">
						     <label for="PARCEL_CODE">�ڵر��</label>
						   </c:when>
						   <c:otherwise>
						     <label for="ADV_HOUSE_CODE">¥����</label>
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
						     <label for="BULIDING_CODE">¥����</label>
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
						<td><input class="plui-validatebox" readonly name="REG_CODE" id="REG_CODE" type="text" />
						</td>
						<td width="150"><label for="BUS_NAME">�Ǽ�����</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							     name="BUS_NAME" id="BUS_NAME"/>
                        </td>
					</tr>
					<tr>
						<td><label for="DISS_APP">������</label></td>
						<td ><input class="plui-validatebox" readonly type="text"
							id="DISS_APP" name="DISS_APP" /></td>
						<td><label for="DISS_APP_ID">���֤����</label></td>
						<td ><input class="plui-validatebox" readonly type="text"
							id="DISS_APP_ID" name="DISS_APP_ID" /></td>
					</tr>
					<tr>
						<td><label for="DISS_ITEM">��������</label></td>
						<td colspan=3><input class="plui-validatebox" readonly name="DISS_ITEM" id="DISS_ITEM" type="text" style="width:98%"/></td>
					</tr>
					<tr>
						<td><label for="REG_DATE_N">�Ǽ�ʱ��</label></td>
						<td><input class="plui-validatebox" readonly type="text" name="REG_DATE_N" id="REG_DATE_N" /></td>
						<td><label for="RECORDER">������/�Ǳ���</label></td>
						<td><input class="plui-validatebox" readonly type="text" name="RECORDER" id="RECORDER" /></td>
					</tr>
					<!-- ��ѺȨ�������ֽ��� -->
					<tr>
						<td colspan="4">
							<hr width="600">
						</td>
					</tr>
					<tr>
						<td><label for="CAN_DISS_CODE">����ע���ǼǱ��</label></td>
						<td colspan=3><input class="plui-validatebox" readonly name="CAN_DISS_CODE" id="CAN_DISS_CODE" type="text" style="width:98%" /></td>
					</tr>
<!-- 					<tr>
						<td><label for="CAN_DISS_APP">����ע��������</label></td>
						<td colspan=3><input class="plui-validatebox" readonly name="CAN_DISS_APP" id="CAN_DISS_APP" type="text" style="width:98%" /></td>
					</tr>
 -->				     <tr>
						<td><label for="CAN_DISS_REG_DATE_N">�Ǽ�ʱ��</label></td>
						<td><input class="plui-validatebox" readonly type="text" name="CAN_DISS_REG_DATE_N" id="CAN_DISS_REG_DATE_N"/></td>
						<td><label for="CAN_DISS_REC">������/�Ǳ���</label></td>
						<td><input class="plui-validatebox" readonly type="text" name="CAN_DISS_REC" id="CAN_DISS_REC"/></td>
					</tr>
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
				<table id="table_dissentInfo" style="BORDER-COLLAPSE: collapse;"
					borderColor=#000000 align=center border=1 class="table_center">
					<tr>
						<td>���</td>
						<td>�Ǽ�����</td>
						<td>������</td>
						<td>�Ǽ�����</td>
					</tr>
				</table>
			</form>
		</div>
</body>

</html>
