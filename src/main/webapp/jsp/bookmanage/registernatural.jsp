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
<script type="text/javascript" src="${ctx }/js/bookmanage/registernatural.js"></script>
</head>
<body>
	<!-- ��Ȼ��Ϣ�� -->
	<div id="naturalInfo" style="display: none;">
			<hr>
			<form id="naturalInfoForm">
			  <div id="divNaturalInfo" style="height:500px;overflow-y:auto">  
				<table id="table_natural">
					<tr>
						<td width="120"></td>
						<td width="45"></td>
						<td width="120"></td>
						<td width="160"></td>
						<td></td>
					</tr>
					<tr>
						<td><label for="PARCEL_CODE">�ڵغ�</label></td>
						<td colspan=4><input class="plui-validatebox" readonly type="text"
							name="PARCEL_CODE" id="PARCEL_CODE"  style="width: 98%;"/></td>
					</tr>
					<!-- ����Ҫ��������ʾ�Ĵ���  BEGIN-->
						<tr class="classtr">
							<td><label for="HOUSE_CODE">���ݱ��</label></td>
							<td  colspan=4><input class="plui-validatebox" readonly type="text"
								name="HOUSE_CODE" id="HOUSE_CODE" style="width: 98%;" /></td>
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
							     <label for="BUILDING_ADDR">¥������</label>
							   </c:otherwise>
							   </c:choose>
                             </td>
							<td  colspan=4>
							   <c:choose>
								<c:when test="${reg_unit_type eq HOUSECODE }">
							     <input class="plui-validatebox" readonly type="text"
								  name="HOUSE_LOCATION" id="HOUSE_LOCATION" style="width: 98%;" />
							    </c:when>
							   <c:when test="${reg_unit_type eq PARCELCODE }">
							    <input class="plui-validatebox" readonly type="text"
								  name="LAND_ADDRESS" id="LAND_ADDRESS" style="width: 98%;" />
							   </c:when>
							   <c:otherwise></c:otherwise>
							   </c:choose>
							</td>
						</tr>
						<tr class="classtr">
							<td colspan="5">
								<hr style="width:600px">
							</td>
						</tr>
						<tr class="classtr">
							<td><label for="LAYER_COUNT">�����ܲ���</label></td>
							<td colspan=2><input class="plui-validatebox" readonly type="text"
								name="LAYER_COUNT" id="LAYER_COUNT" /></td>
							<td><label for="FLOOR_NO">�������ڲ�</label>
							<td><input class="plui-validatebox" readonly type="text"
								name="FLOOR_NO" id="FLOOR_NO" />
						   </td>
						</tr>
						<tr class="classtr">
							<td><label for="BUILD_AREA">�������</label></td>
							<td colspan=2><input class="plui-validatebox" readonly type="text"
								name="BUILD_AREA" id="BUILD_AREA" /></td>
							<td colspan=2>&nbsp;</td>
							<!-- <td><label for="zybfjzmj">ר�в��ֽ������</label></td>
							<td><input class="plui-validatebox" readonly type="text"
								name="ZYBFJZMJ" id="zybfjzmj" /></td> -->
						</tr>
						<tr class="classtr">
							<td><label for="INNER_AREA">���ڽ������</label></td>
							<td colspan=2><input class="plui-validatebox" readonly type="text"
								name="INNER_AREA" id="INNER_AREA" /></td>
							<td><label for="SHARE_AREA">��̯�������</label></td>
							<td><input class="plui-validatebox" readonly type="text"
								name="SHARE_AREA" id="SHARE_AREA" /></td>
						</tr>
						<tr class="classtr">
							<td><label for="PLAN_USAGE">�滮��;</label></td>
							<td colspan=2><input class="plui-validatebox" readonly type="text"
								name="PLAN_USAGE" id="PLAN_USAGE" /></td>
							<td><label for="HOUSE_STRUT_N">���ݽṹ</label></td>
							<td><input class="plui-validatebox" readonly type="text"
								name="HOUSE_STRUT_N" id="HOUSE_STRUT_N" /></td>
						</tr>
					<!-- ����Ҫ��������ʾ�Ĵ��� END -->
					<tr>
						<td colspan="5">
							<hr>
						</td>
					</tr>
					<tr>
						<td><label for="PARCEL_CODE">�غ�</label></td>
						<td colspan=2><input class="plui-validatebox" readonly type="text" name="PARCEL_CODE"
							id="PARCEL_CODE1" /></td>
						<td><label for="LAND_ATTRIBUTE_N">��������</label>
						<td><input class="plui-validatebox" readonly type="text"
							name="LAND_ATTRIBUTE_N" id="LAND_ATTRIBUTE_N" /></td>
					</tr>
					<tr>
						<td><label for="CER_NO">����֤��</label></td>
						<td colspan="4"><input class="plui-validatebox" readonly type="text"
							name="CER_NO" id="CER_NO" style="width: 98%;" /></td>
					</tr>
					<tr>
						<td colspan="2"><label for="USERIGHT_TYPE">��������ʹ��Ȩȡ�÷�ʽ</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text"
							name="USERIGHT_TYPE" id="USERIGHT_TYPE" style="width: 98%;" /></td>
					</tr>
				<!-- 	<tr>
						<td colspan="2"><label for="jttdsylx">��������ʹ��Ȩ����</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text"
							name="JTTDSYLX" id="jttdsylx" style="width: 98%;" /></td>
					</tr> -->
					<tr>
						<td><label for="USE_PER">����ʹ������</label></td>
						<td colspan="4"><input class="plui-validatebox" readonly type="text"
							name="USE_PER" id="USE_PER" style="width: 98%;" /></td>
					</tr>
					<tr>
						<td colspan="5">
							<hr/>
						</td>
					</tr>
				</table>
	   			<table >
	   				<tr>
					    <td>
						   <label for="fj" style="float:left">����</label>  
						</td>
				    </tr>
				    <tr>
						<td>
							<textArea name="EXCURSUS" readonly id="EXCURSUS" style="width:600px;height:98px;" ></textArea> 	
						</td>
				    </tr>
	   			</table>
	   			</div>
			</form>
	</div>
	<!-- ��Ȼ��Ϣ��  end-->
</body>

</html>
