<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK" import="com.szhome.security.ext.UserInfo"%>
<%
	UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
	String userName = userInfo.getUserName();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�Ǽǲ�Ԥ��</title>
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp"%>


<style>
div.selected {
	display: block;
}

table {
	margin: auto;
	width: 600px;
}

table input {
	float: left;
	readOnly: expression(this.readOnly = true);
	BORDER-TOP-STYLE: none;
	BORDER-RIGHT-STYLE: none;
	BORDER-LEFT-STYLE: none;
}



.table_center input {
	text-align: left;
	float: none;
}

.table_center label {
	float: none;
}
</style>

<script type="text/javascript">
	//         
	var ctx = '${ctx}';
	//]]
</script>
<script type="text/javascript"
	src="${ctx}/js/common/registerbook/registerdemurrer.js"></script>
</head>
<body>

	<div id="cleft" data-options="region:'west'"
		style="width: 203px; height: 600px">
		<ul id="registerbook_tree"></ul>

	</div>
	<div data-options="region:'center'" style="text-align: left">

		<!-- ��Ȼ��Ϣ�� -->
		<div id="naturalInfo" style="display: none;">

			<hr>
			<form id="naturalInfoForm">
				<table id="tableNaturalInfo">
					<tr>
						<td ><label for="PARCEL_CODE">�ڵغ�</label></td>
						<td><input class="plui-validatebox" type="text"
							name="PARCEL_CODE" id="PARCEL_CODE" /></td>
					</tr>
					<tr>
						<td><label for="ADV_HOUSE_CODE">���ݱ��</label></td>
						<td><input class="plui-validatebox" type="text"
							name="ADV_HOUSE_CODE" id="ADV_HOUSE_CODE" /></td>
					</tr>
					<tr>
						<td><label for="HOUSE_LOCATION">��������</label></td>
						<td><input class="plui-validatebox" type="text"
							name="HOUSE_LOCATION" id="HOUSE_LOCATION" /></td>
					</tr>
					<tr>
						<td colspan="4">
							<hr>
						</td>
					</tr>
					<tr>
						<td><label for="LAYER_COUNT">�����ܲ���</label></td>
						<td><input class="plui-validatebox" type="text"
							name="LAYER_COUNT" id="LAYER_COUNT" /></td>
						<td><label for="AT_FLOOR">�������ڲ�</label>
						<td><input class="plui-validatebox" type="text"
							name="AT_FLOOR" id="AT_FLOOR" /></td>
						</td>
					</tr>
					<tr>
						<td><label for="BUILD_AREA">�������(m<sup>2</sup>)
						</label></td>
						<td><input class="plui-validatebox" type="text"
							name="BUILD_AREA" id="BUILD_AREA" /></td>
						<td width='150'><label for="zybfjzmj">ר�в��ֽ������(m<sup>2</sup>)
						</label></td>
						<td><input class="plui-validatebox" type="text"
							name="ZYBFJZMJ" id="zybfjzmj" /></td>
					</tr>
					<tr>
						<td><label for="TAONEI_AREA">���ڽ������(m<sup>2</sup>)
						</label></td>
						<td><input class="plui-validatebox" type="text"
							name="TAONEI_AREA" id="TAONEI_AREA" /></td>
						<td><label for="FT_COMMON_AREA">��̯�������(m<sup>2</sup>)
						</label></td>
						<td><input class="plui-validatebox" type="text"
							name="FT_COMMON_AREA" id="FT_COMMON_AREA" /></td>
					</tr>
					<tr>
						<td><label for="PLAN_USAGE">������;</label></td>
						<td><input class="plui-validatebox" type="text"
							name="PLAN_USAGE" id="PLAN_USAGE" /></td>
						<td><label for="HOUSE_STRUT">���ݽṹ</label></td>
						<td><input class="plui-validatebox" type="text"
							name="HOUSE_STRUT" id="HOUSE_STRUT" /></td>
					</tr>
					<tr>
						<td colspan="4">
							<hr>
						</td>
					</tr>
					<tr>
						<td><label for="CER_NO">���ز�֤��</label></td>
						<td><input class="plui-validatebox" type="text" name="CER_NO"
							id="CER_NO" /></td>
						<td><label for="LAND_ATTRIBUTE">��������</label>
						<td><input class="plui-validatebox" type="text"
							name="LAND_ATTRIBUTE" id="LAND_ATTRIBUTE" /></td>
						</td>
					</tr>
					<tr>
						<td><label for="tdqdfs">��������ʹ��Ȩȡ�÷�ʽ</label></td>
						</td>
						<td colspan="3"><input class="plui-validatebox" type="text"
							name="TDQDFS" id="tdqdfs" style="width: 450px;" /></td>
					</tr>
					<tr>
						<td><label for="jttdsylx">��������ʹ��Ȩ����</label></td>
						<td colspan="3"><input class="plui-validatebox" type="text"
							name="JTTDSYLX" id="jttdsylx" style="width: 450px;" /></td>
					</tr>
					<tr>
						<td><label for="USE_PER">����ʹ������</label></td>
						<td colspan="3"><input class="plui-validatebox" type="text"
							name="USE_PER" id="USE_PER" style="width: 485px;" /></td>
					</tr>
					<tr>
						<td colspan="4">
							<hr>
						</td>
					</tr>

				</table>
			</form>
		</div>
		<!-- ��Ȼ��Ϣ��  end-->

		<!-- ������Ϣ�� -->
		<div id='demurrerInfo' style="display:none;text-align:left">
			<form id="demurrerInfoForm">
				<!-- <table id="table_demurrer">
					<tr>
						<td>���ݱ��</td>
						<td><input class="plui-validatebox" type="text" name="ADV_HOUSE_CODE" id="ADV_HOUSE_CODE" /></td>
						<td>��������</td>
						<td><input class="plui-validatebox" type="text" name="HOUSE_LOCATION" id="HOUSE_LOCATION" /></td>
					</tr>
					</table>
					<hr width="600px">-->
					<table> 
					<tr>
						<td >�ǼǱ��</td>
						<td><input class="plui-validatebox" type="text" id="REG_CODE" name="REG_CODE" /></td>
						<td >�Ǽ�����</td>
						<td ><input class="plui-validatebox" type="text" id="BUS_NAME" name="BUS_NAME" />
                        </td>
					</tr>
					
					<tr>
						<td><label for="HOL_NAME">������</label></td>
						<td ><input class="plui-validatebox" type="text"
							id="HOL_NAME" name="HOL_NAME"  /></td>
							<td><label for="HOL_CER_NO">���֤����</label></td>
						<td ><input class="plui-validatebox" type="text"
							id="HOL_CER_NO" name="HOL_CER_NO" /></td>
					</tr>
					<tr>
						<td><label for="DISS_ITEM">��������</label></td>
						<td colspan=3><input class="plui-validatebox" name="DISS_ITEM" id="DISS_ITEM" type="text" style="width:98%"/></td>
					</tr>
					<tr>
						<td><label for="REG_DATE">�Ǽ�ʱ��</label></td>
						<td><input class="plui-validatebox" type="text" name="REG_DATE" id="REG_DATE" /></td>
						<td><label for="RECORDER">������/�Ǳ���</label></td>
						<td><input class="plui-validatebox" type="text" name="RECORDER" id="RECORDER" /></td>
					</tr>
					<tr>
						<td colspan="4">
							<hr width="600">
						</td>
					</tr>
					</table>
					<table>
					<tr>
						<td><label for="CAN_REG_CODE">ע��������</label></td>
						<td ><input class="plui-validatebox" name="CAN_REG_CODE" id="CAN_REG_CODE" type="text"/></td>
					</tr>
				     <tr>
						<td><label for="CAN_REG_DATE">�Ǽ�ʱ��</label></td>
						<td><input class="plui-validatebox" type="text" name="CAN_REG_DATE" id="CAN_REG_DATE"/></td>
						<td><label for="CAN_RECORDER">������/�Ǳ���</label></td>
						<td><input class="plui-validatebox" type="text" name="CAN_RECORDER" id="CAN_RECORDER"/></td>
					</tr>
				    <tr>
						<td colspan="4">
							<hr width="600">
						</td>
					</tr>
				</table>
				 <table id="table_demurrerInfo" style="BORDER-COLLAPSE: collapse;"
					borderColor=#000000 align=center border=1 class="table_center">
					<tr>
						<td align="center">���</td>
						<td align="center">�Ǽ�����</td>
						<td align="center">������</td>
						<td align="center">�Ǽ�����</td>
					</tr>
				</table> 
			</form>

		</div>
	</div>
</body>
</html>
