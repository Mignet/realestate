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
<script type="text/javascript" src="${ctx }/js/bookmanage/registerpreadvice.js"></script>
</head>
<body>
	    <!-- Ԥ��Ǽ���Ϣ�� -->
		<div id='preadviceInfo' style="display:none;text-align:left">
			<form id="preadviceInfoForm">
				<hr>
				 <div id="divPreadviceInfo" style="height:400px;overflow-y:auto">
				<table id="table_preadvice">
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
						<td><label for="reg_code">�ǼǱ��</label></td>
						<td><input class="plui-validatebox" readonly type="text" name="REG_CODE" id="REG_CODE"/>
						</td>
						<td><label for="procdef_id">�Ǽ�����</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							 name="BUS_NAME" id="BUS_NAME" />
                        </td>
					</tr>

					<tr>
						<td><label for="ADV_HOLDER">Ȩ����</label></td>
						<td colspan=3><input class="plui-validatebox" readonly type="text"
							name="ADV_HOLDER" id="ADV_HOLDER"  style="width:98%"/></td>
					</tr>
					<tr>
						<td><label for="ADV_HOLDER_ID">Ȩ�������֤����</label></td>
						<td colspan=3><input class="plui-validatebox" readonly type="text" name="ADV_HOLDER_ID" id="ADV_HOLDER_ID" style="width:98%" /></td>
					</tr>
					<tr>
						<td><label
							for="ADV_VOL">������</label></td>
						<td colspan=3>
						   <input class="plui-validatebox" readonly type="text" name="ADV_VOL" id="ADV_VOL"   style="width:98%" />
						</td>
					</tr>
					<tr>
						<td><label
							for="ADV_VOL_ID">���������֤����</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly name="ADV_VOL_ID" id="ADV_VOL_ID" type="text"  style="width:98%" />
						</td>
					</tr>
					<tr>
						<td><label for="CER_NO">Ȩ��֤����</label></td>
						<td colspan=3><input class="plui-validatebox" readonly type="text"
						   name="CER_NO" id="CER_NO" style="width:98%" /></td>
					</tr>
					<tr>
						<td><label for="REG_DATE_N">�Ǽ�ʱ��</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							 name="REG_DATE_N" id="REG_DATE_N"  /></td>
						<td width="150"><label for="RECORDER">������/�Ǳ���</label></td>
						<td><input class="plui-validatebox" readonly type="text" name="RECORDER" id="RECORDER"
							  /></td>
					</tr>
					<!-- ��ѺȨ�������ֽ��� -->
					<tr>
						<td colspan="4">
							<hr width="600">
						</td>
					</tr>
					<tr>
						<td><label for="CAN_ADV_NO">Ԥ��ע���ǼǱ��</label></td>
						<td colspan=3><input class="plui-validatebox" readonly type="text"
							name="CAN_ADV_NO" id="CAN_ADV_NO" style="width: 330px" /></td>
					</tr>
				     <tr>
						<td><label for="CAN_ADV_DATE_N">�Ǽ�ʱ��</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							 name="CAN_ADV_DATE_N" id="CAN_ADV_DATE_N" style="width: 150px" /></td>
						<td><label for="CAN_ADV_REC">������/�Ǳ���</label></td>
						<td><input class="plui-validatebox" readonly type="text" 
							name="CAN_ADV_REC" id="CAN_ADV_REC" style="width: 150px" /></td>
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
				<table id="table_preadviceInfo" style="BORDER-COLLAPSE: collapse;"
				       borderColor=#000000 align=center border=1 class="table_center">
					<tr>
						<td>���</td>
						<td>�Ǽ�����</td>
						<td>Ȩ����</td>
						<td>�Ǽ�����</td>
					</tr>
				</table>
			</form>
		
		</div>
</body>

</html>
