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
<script type="text/javascript" src="${ctx }/js/bookmanage/registermort.js"></script>
</head>
<body>
		<!-- ��Ȼ��Ϣ�� -->
		<!-- <div id="naturalInfo" style="display: none;">

			<hr>
			<form id="naturalInfoForm">
				<table id="tableNaturalInfo">
					<tr>
						<td ><label for="PARCEL_CODE">�ڵغ�</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="PARCEL_CODE" id="PARCEL_CODE" /></td>
					</tr>
					<tr>
						<td><label for="ADV_HOUSE_CODE">���ݱ��</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="ADV_HOUSE_CODE" id="ADV_HOUSE_CODE" /></td>
					</tr>
					<tr>
						<td><label for="HOUSE_LOCATION">��������</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="HOUSE_LOCATION" id="HOUSE_LOCATION" /></td>
					</tr>
					<tr>
						<td colspan="4">
							<hr>
						</td>
					</tr>
					<tr>
						<td><label for="LAYER_COUNT">�����ܲ���</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="LAYER_COUNT" id="LAYER_COUNT" /></td>
						<td><label for="AT_FLOOR">�������ڲ�</label>
						<td><input class="plui-validatebox" readonly type="text"
							name="AT_FLOOR" id="AT_FLOOR" /></td>
						</td>
					</tr>
					<tr>
						<td><label for="BUILD_AREA">�������(m<sup>2</sup>)
						</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="BUILD_AREA" id="BUILD_AREA" /></td>
						<td width='150'><label for="zybfjzmj">ר�в��ֽ������(m<sup>2</sup>)
						</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="ZYBFJZMJ" id="zybfjzmj" /></td>
					</tr>
					<tr>
						<td><label for="TAONEI_AREA">���ڽ������(m<sup>2</sup>)
						</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="TAONEI_AREA" id="TAONEI_AREA" /></td>
						<td><label for="FT_COMMON_AREA">��̯�������(m<sup>2</sup>)
						</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="FT_COMMON_AREA" id="FT_COMMON_AREA" /></td>
					</tr>
					<tr>
						<td><label for="PLAN_USAGE">������;</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="PLAN_USAGE" id="PLAN_USAGE" /></td>
						<td><label for="HOUSE_STRUT">���ݽṹ</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="HOUSE_STRUT" id="HOUSE_STRUT" /></td>
					</tr>
					<tr>
						<td colspan="4">
							<hr>
						</td>
					</tr>
					<tr>
						<td><label for="CER_NO">���ز�֤��</label></td>
						<td><input class="plui-validatebox" readonly type="text" name="CER_NO"
							id="CER_NO" /></td>
						<td><label for="LAND_ATTRIBUTE">��������</label>
						<td><input class="plui-validatebox" readonly type="text"
							name="LAND_ATTRIBUTE" id="LAND_ATTRIBUTE" /></td>
						</td>
					</tr>
					<tr>
						<td><label for="tdqdfs">��������ʹ��Ȩȡ�÷�ʽ</label></td>
						</td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text"
							name="TDQDFS" id="tdqdfs" style="width: 450px;" /></td>
					</tr>
					<tr>
						<td><label for="jttdsylx">��������ʹ��Ȩ����</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text"
							name="JTTDSYLX" id="jttdsylx" style="width: 450px;" /></td>
					</tr>
					<tr>
						<td><label for="USE_PER">����ʹ������</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text"
							name="USE_PER" id="USE_PER" style="width: 485px;" /></td>
					</tr>
					<tr>
						<td colspan="4">
							<hr>
						</td>
					</tr>

				</table>
			</form>
		</div> -->
		<!-- ��Ȼ��Ϣ��  end-->
		<!-- ��ѺȨ��Ϣ�� -->
		<div id="mortInfo" style="display:none;">
			<form id="mortInfoForm">
				<hr>
				 <div id="divMortInfo" style="height:400px;overflow-y:auto">
				<table id="table_mort" >
				    <tr>
						<td width="180px">&nbsp;</td>
						<td width="20px">&nbsp;</td>
						<td width="140px">&nbsp;</td>
						<td width="110px">&nbsp;</td>
						<td width="150px">&nbsp;</td>
					</tr>
				    <!-- ��ѺȨ�������_ʼ -->
					<tr>
						<td >
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
						<td colspan=4>
						   <c:choose>
								<c:when test="${reg_unit_type eq HOUSECODE }">
							     <input class="plui-validatebox" readonly type="text"
								   name="HOUSE_CODE" id="HOUSE_CODE" style="width:98%"/>
							   </c:when>
							   <c:when test="${reg_unit_type eq PARCELCODE }">
							     <input class="plui-validatebox" readonly type="text"
								  name="PARCEL_CODE" id="PARCEL_CODE" style="width:98%"/>
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
						<td colspan=4>
						    <c:choose>
								<c:when test="${reg_unit_type eq HOUSECODE }">
							     <input class="plui-validatebox" readonly type="text"
								   name="HOUSE_LOCATION" id="HOUSE_LOCATION" style="width:98%"/>
							   </c:when>
							   <c:when test="${reg_unit_type eq PARCELCODE}">
							     <input class="plui-validatebox" readonly type="text"
								   name="LAND_ADDRESS" id="LAND_ADDRESS" style="width:98%"/>
							   </c:when>
							   <c:otherwise>
							     <label for="BUILD_LOCATION">¥������</label>
							   </c:otherwise>
						   </c:choose>
						</td>
					</tr>
					<tr>
						<td colspan="5">
							<hr width="600px">
						</td>
					</tr>
					<tr>
						<td><label for="REG_CODE">�ǼǱ��</label></td>
						<td colspan=2><input class="plui-validatebox" readonly type="text" id="REG_CODE" name="REG_CODE" />
						</td>
						<td><label for="BUS_NAME">�Ǽ�����</label></td>
						<td ><input class="plui-validatebox" readonly type="text" name="BUS_NAME" id="BUS_NAME"/>
							
                        </td>
					</tr>
					<tr>
						<td><label for="MORTGAGEE">��ѺȨ��</label></td>
						<td colspan=2><input class="plui-validatebox" readonly type="text" name="MORTGAGEE" id="MORTGAGEE" /></td>
						<td ><label for="MORTGAGER">��Ѻ��</label></td>
						<td ><input class="plui-validatebox" readonly type="text" name="MORTGAGER"
							id="MORTGAGER" /></td>
					</tr>
					<tr>
						<td><label for="BORROWER">�����</label></td>
						<td  colspan=2><input class="plui-validatebox" readonly type="text" id="BORROWER" name="BORROWER" /></td>
					    <td><label for="MORT_TYPE_NAME">��Ѻ����</label></td>
						<td><input class="plui-validatebox" readonly type="text" name="MORT_TYPE_NAME" id="MORT_TYPE_NAME"/></td>
					</tr>
					<tr>
						<td colspan="2"><label
							for="ASSURE_AMOUNT">��������ծȨ������ծȨ���</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text"
							id="ASSURE_AMOUNT" name="ASSURE_AMOUNT"  style="width:98%" /></td>
					</tr>
					<tr>
						<td ><label for="ASSUER_RANGE">������Χ</label></td>
						<td colspan="4"><input class="plui-validatebox" readonly type="text"
							name="ASSUER_RANGE" id="ASSUER_RANGE"  style="width:98%"/></td>
					</tr>
					<tr>
						<td colspan="2"><label
							for="DEBT_DIS_LIMIT">ծ���������ޣ�ծȨȷ���ڼ䣩</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text"
							id="DEBT_DIS_LIMIT" name="DEBT_DIS_LIMIT"  style="width:98%" />
						</td>
					</tr>
					<tr>
						<td colspan=2><label for="CER_NO">���ز�֤��/Ԥ�ۺ�ͬ��</label></td>
						<td><input class="plui-validatebox" readonly type="text" id="CER_NO" name="CER_NO"/></td>
						<td><label for="MORT_SEQ">��Ѻ˳λ</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="MORT_SEQ" id="MORT_SEQ" /></td>
					</tr>
					<tr>
					    <td><label for="ALIAS_REG_DATE">�Ǽ�ʱ��</label></td>
						<td colspan=2><input class="plui-validatebox" readonly type="text"
							name="ALIAS_REG_DATE" id="ALIAS_REG_DATE" /></td>
						<td><label for="RECORDER">������/�ǲ���</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="RECORDER" id="RECORDER" /></td>
					</tr>
					<!-- ��ѺȨ�������ֽ��� -->
					<tr>
						<td colspan="5">
							<hr width="600">
						</td>
					</tr>
					<!-- ��߶��ѺȨȷ���Ǽǲ��� -->
					<tr>
						<td ><label for="SURE_REG_CODE">�ǼǱ��</label></td>
						<td colspan=4><input class="plui-validatebox" readonly type="text"
							name="SURE_REG_CODE" id="SURE_REG_CODE"  style="width:98%"/></td>
						<!-- <td><label for="sure_reg_date">�Ǽ�ʱ��</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="sure_reg_date" id="sure_reg_date" /></td> -->
					</tr>
					<tr>
					    <td colspan=2><label for="MAX_AMOUNT">���ծȨȷ����ʵ�ͽ��</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="MAX_AMOUNT" id="MAX_AMOUNT"  style="width:98%"/></td>
						<td><label for="SURE_AMOUNT">ȷ��������ծȨ����</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="SURE_AMOUNT" id="SURE_AMOUNT" /></td>
					</tr>
					<tr>
					    <td><label for="SURE_REG_DATE_N">�Ǽ�ʱ��</label></td>
						<td colspan=2><input class="plui-validatebox" readonly type="text"
							name="SURE_REG_DATE_N" id="SURE_REG_DATE_N" /></td>
						<td ><label for="SURE_RECORDER">������/�ǲ���</label></td>
						<td ><input class="plui-validatebox" readonly type="text"
							name="SURE_RECORDER" id="SURE_RECORDER" /></td>
					</tr>
					<tr>
						<!-- ��߶��Ѻȷ���Ǽǽ��� -->
						<td colspan="5">
							<hr width="600">
						</td>
					</tr>
					<!-- ��Ѻע����Ϣ���� -->
					<tr>
						<td colspan=2><label for="MORT_CAN_CODE">ע����Ѻ�ǼǱ��</label></td>
						<td colspan=3><input class="plui-validatebox" readonly type="text"
							name="MORT_CAN_CODE" id="MORT_CAN_CODE"  style="width:98%"/></td>
					</tr>
					<tr>
					    <td><label for="CAN_REG_DATE">�Ǽ�ʱ��</label></td>
						<td colspan=2><input class="plui-validatebox" readonly type="text"
							name="CAN_REG_DATE_N" id="CAN_REG_DATE_N" /></td>
						<td ><label for="CAN_RECORDER">������/�ǲ���</label></td>
						<td ><input class="plui-validatebox" readonly type="text"
							name="CAN_RECORDER" id="CAN_RECORDER" /></td>
					</tr>
					<!-- ��Ѻע����Ϣ���� -->
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
				<table id="table_mortInfo" style="BORDER-COLLAPSE: collapse;"
					borderColor=#000000 align=center border=1 class="table_center">
					<tr>
						<td>���</td>
						<td>��Ѻ����</td>
						<td>��Ѻ��</td>
						<td>�Ǽ�����</td>
					</tr>
				</table>
			</form>
	   </div>
</body>
</html>
