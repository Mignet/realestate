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
	src="${ctx}/js/common/registerbook/registermort.js">
	
	
	</script> 
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
						<td><input class="plui-validatebox" type="text" name="PA_CER_NO"
							id="PA_CER_NO" /></td>
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
							name="USE_PER_VALUE" id="USE_PER" style="width: 485px;" /></td>
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

		<!-- ��ѺȨ��Ϣ�� -->
		<div id='mortInfo' style="display:none;text-align:left">
			<form id="mortInfoForm">
				<hr>
				<table id="table_fw">
					<!-- 
					<tr>
						<td width="120px"><label for="ADV_HOUSE_CODE">���ݱ��</label></td>
						<td><input class="plui-validatebox" type="text"
							name="ADV_HOUSE_CODE" id="ADV_HOUSE_CODE" /></td>
						<td><label for="HOUSE_LOCATION">��������</label></td>
						<td><input class="plui-validatebox" type="text"
							name="HOUSE_LOCATION" id="HOUSE_LOCATION" /></td>
					</tr>
					 -->
					<tr>
						<td colspan="4">
							<hr width="600px">
						</td>
					</tr>

					<tr>
						<td><label for="reg_code">�ǼǱ��</label></td>
						<td><input class="plui-validatebox" type="text" id="mo_reg_code" name="mo_reg_code" />

						</td>
						<td><label for="procdef_id">�Ǽ�����</label></td>
						<td><input class="plui-validatebox" type="text" id="procdef_id" name="BUS_NAME"/>
                        </td>
					</tr>

					<tr>
						<td><label for="mortgager">��Ѻ��</label></td>
						<td><input class="plui-validatebox" type="text"
							name ="mortgager" id="mortgager" /></td>
						<td><label for="mortgagee">��ѺȨ��</label></td>
						<td><input class="plui-validatebox" type="text"
							name="mortgagee" id="mortgagee" /></td>
					</tr>
					<tr>
						<td><label for="borrower">ծ����</label></td>
						<td><input class="plui-validatebox" type="text" id="borrower"
							name="borrower" /></td>
							<td><label for="mort_type">��Ѻ����</label></td>
						<td><input class="plui-validatebox" type="text" id="mort_type" name="MORT_TYPE" />
							</td>
					</tr>
					<tr>
						<td  colspan="2"><label
							for="assure_amount">��������ծȨ������ծȨ���</label></td>
						<td colspan="2"><input class="plui-validatebox" type="text"
							id="assure_amount" name="assure_amount" style="width: 250px" /></td>
					</tr>
					
					<tr>
						<td colspan="2"><label
							for="debt_dis_limit">ծ���������ޣ�ծȨȷ���ڼ䣩</label></td>
						<td colspan="2"><input class="plui-validatebox" type="text"
							id="debt_dis_limit" name="debt_dis_limit" style="width: 250px" />

						</td>
					</tr>
					<tr>
						<td><label for="assuer_range">������Χ</label></td>
						<td colspan="3"><input class="plui-validatebox" type="text"
							name="assuer_range" id="assuer_range" style="width: 430px" /></td>
					</tr>
					<tr>
						<td><label for="cer_no">���ز�֤��</label></td>
						<td><input class="plui-validatebox" type="text" id="cer_no" name="cer_no"/>

						</td>
						<td><label for="mort_seq">��Ѻ˳λ</label></td>
						<td><input class="plui-validatebox" type="text"
							name="mort_seq" id="mort_seq" /></td>
					</tr>
					<tr>
						<td><label for="cer_no">Ԥ�ۺ�ͬ��</label></td>
						<td><input class="plui-validatebox" type="text" id="contact_no" name="contact_no"/>
					
						<td><label for="reg_date">�Ǽ�ʱ��</label></td>
						<td><input class="plui-validatebox" type="text"
							name="reg_date" id="reg_date" /></td>
						
					</tr>
					<tr>
						<td><label for="recorder">������/�ǲ���</label></td>
						<td><input class="plui-validatebox" type="text"
							name="recorder" id="recorder" /></td>
					</tr>
					<!-- ��ѺȨ�������ֽ��� -->
					<tr>
						<td colspan="4">
							<hr width="600">
						</td>
					</tr>
					<!-- ��߶��ѺȨȷ���Ǽǲ��� -->
					
					<tr>
					    <td width="153px"><label for="max_amount">���ծȨȷ����ʵ</label></td>
						<td><input class="plui-validatebox" type="text"
							name="max_amount" id="max_amount" /></td>
						<td width="153"><label for="SURE_AMOUNT">ȷ��������ծȨ����</label></td>
						<td><input class="plui-validatebox" type="text"
							name="sure_amount" id="sure_amount" /></td>
						
					</tr>
					<tr>
						<td><label for="sure_reg_code">�ǼǱ��</label></td>
						<td><input class="plui-validatebox" type="text"
							name="sure_reg_code" id="sure_reg_code" /></td>
						<td><label for="sure_reg_date">�Ǽ�ʱ��</label></td>
						<td><input class="plui-validatebox" type="text"
							name="sure_reg_date" id="sure_reg_date" /></td>
						
					</tr>
					<tr>
						<td><label for="sure_recorder">������/�ǲ���</label></td>
						<td><input class="plui-validatebox" type="text"
							name="sure_recorder" id="sure_recorder" /></td>
					</tr>

					<tr>
						<!-- ��߶��Ѻȷ���Ǽǽ��� -->
						<td colspan="4">
							<hr width="600">
						</td>
					</tr>
					<!-- ��Ѻע����Ϣ���� -->
					<tr>
						<td><label for="can_mort_no">ע����Ѻ�ǼǱ��</label></td>
						<td><input class="plui-validatebox" type="text"
							name="can_mort_no" id="can_mort_no" /></td>
						<td><label for="can_reg_date">�Ǽ�ʱ��</label></td>
						<td><input class="plui-validatebox" type="text"
							name="can_reg_date" id="can_reg_date" /></td>

					</tr>
					<tr>
						<td><label for="can_recorder">������/�ǲ���</label></td>
						<td><input class="plui-validatebox" type="text"
							name="can_recorder" id="can_recorder" /></td>
					</tr>
					<!-- ��Ѻע����Ϣ���� -->
				</table>
				<hr>

				<table id="table_mortInfo" style="BORDER-COLLAPSE: collapse;"
					borderColor=#000000 align=center border=1 class="table_center">
					

				</table>




			</form>

		</div>




	</div>


</body>
</html>
