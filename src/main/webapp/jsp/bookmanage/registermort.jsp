<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>登记簿预览</title>
<%@ include file="/base/taglibs.jsp"%>
<c:if test="1==2">
    <%@ include file="/base/prefix.jsp" %>
</c:if>
<script type="text/javascript" src="${ctx }/js/bookmanage/registermort.js"></script>
</head>
<body>
		<!-- 自然信息层 -->
		<!-- <div id="naturalInfo" style="display: none;">

			<hr>
			<form id="naturalInfoForm">
				<table id="tableNaturalInfo">
					<tr>
						<td ><label for="PARCEL_CODE">宗地号</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="PARCEL_CODE" id="PARCEL_CODE" /></td>
					</tr>
					<tr>
						<td><label for="ADV_HOUSE_CODE">房屋编号</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="ADV_HOUSE_CODE" id="ADV_HOUSE_CODE" /></td>
					</tr>
					<tr>
						<td><label for="HOUSE_LOCATION">房屋坐落</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="HOUSE_LOCATION" id="HOUSE_LOCATION" /></td>
					</tr>
					<tr>
						<td colspan="4">
							<hr>
						</td>
					</tr>
					<tr>
						<td><label for="LAYER_COUNT">房屋总层数</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="LAYER_COUNT" id="LAYER_COUNT" /></td>
						<td><label for="AT_FLOOR">房屋所在层</label>
						<td><input class="plui-validatebox" readonly type="text"
							name="AT_FLOOR" id="AT_FLOOR" /></td>
						</td>
					</tr>
					<tr>
						<td><label for="BUILD_AREA">建筑面积(m<sup>2</sup>)
						</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="BUILD_AREA" id="BUILD_AREA" /></td>
						<td width='150'><label for="zybfjzmj">专有部分建筑面积(m<sup>2</sup>)
						</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="ZYBFJZMJ" id="zybfjzmj" /></td>
					</tr>
					<tr>
						<td><label for="TAONEI_AREA">套内建筑面积(m<sup>2</sup>)
						</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="TAONEI_AREA" id="TAONEI_AREA" /></td>
						<td><label for="FT_COMMON_AREA">分摊共有面积(m<sup>2</sup>)
						</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="FT_COMMON_AREA" id="FT_COMMON_AREA" /></td>
					</tr>
					<tr>
						<td><label for="PLAN_USAGE">规则用途</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="PLAN_USAGE" id="PLAN_USAGE" /></td>
						<td><label for="HOUSE_STRUT">房屋结构</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="HOUSE_STRUT" id="HOUSE_STRUT" /></td>
					</tr>
					<tr>
						<td colspan="4">
							<hr>
						</td>
					</tr>
					<tr>
						<td><label for="CER_NO">房地产证号</label></td>
						<td><input class="plui-validatebox" readonly type="text" name="CER_NO"
							id="CER_NO" /></td>
						<td><label for="LAND_ATTRIBUTE">土地性质</label>
						<td><input class="plui-validatebox" readonly type="text"
							name="LAND_ATTRIBUTE" id="LAND_ATTRIBUTE" /></td>
						</td>
					</tr>
					<tr>
						<td><label for="tdqdfs">国有土地使用权取得方式</label></td>
						</td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text"
							name="TDQDFS" id="tdqdfs" style="width: 450px;" /></td>
					</tr>
					<tr>
						<td><label for="jttdsylx">集体土地使用权类型</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text"
							name="JTTDSYLX" id="jttdsylx" style="width: 450px;" /></td>
					</tr>
					<tr>
						<td><label for="USE_PER">土地使用年限</label></td>
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
		<!-- 自然信息层  end-->
		<!-- 抵押权信息层 -->
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
				    <!-- 抵押权设立部開始 -->
					<tr>
						<td >
						   <c:choose>
							   <c:when test="${reg_unit_type eq HOUSECODE }">
							     <label for="HOUSE_CODE">房屋编号</label>
							   </c:when>
							   <c:when test="${reg_unit_type eq PARCELCODE }">
							     <label for="PARCEL_CODE">宗地编号</label>
							   </c:when>
							   <c:otherwise>
							    <label for="BUILDING_CODE">楼宇编号</label>
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
							     <label for="BUILDING_CODE">楼宇编号</label>
							   </c:otherwise>
						   </c:choose>
						</td>
					</tr>
					<tr>
						<td>
						 <c:choose>
							   <c:when test="${reg_unit_type eq HOUSECODE }">
							     <label for="HOUSE_LOCATION">房屋坐落</label>
							   </c:when>
							   <c:when test="${reg_unit_type eq PARCELCODE }">
							     <label for="LAND_ADDRESS">宗地坐落</label>
							   </c:when>
							   <c:otherwise>
							     <label for="BUILD_LOCATION">楼宇坐落</label>
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
							     <label for="BUILD_LOCATION">楼宇坐落</label>
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
						<td><label for="REG_CODE">登记编号</label></td>
						<td colspan=2><input class="plui-validatebox" readonly type="text" id="REG_CODE" name="REG_CODE" />
						</td>
						<td><label for="BUS_NAME">登记种类</label></td>
						<td ><input class="plui-validatebox" readonly type="text" name="BUS_NAME" id="BUS_NAME"/>
							
                        </td>
					</tr>
					<tr>
						<td><label for="MORTGAGEE">抵押权人</label></td>
						<td colspan=2><input class="plui-validatebox" readonly type="text" name="MORTGAGEE" id="MORTGAGEE" /></td>
						<td ><label for="MORTGAGER">抵押人</label></td>
						<td ><input class="plui-validatebox" readonly type="text" name="MORTGAGER"
							id="MORTGAGER" /></td>
					</tr>
					<tr>
						<td><label for="BORROWER">借款人</label></td>
						<td  colspan=2><input class="plui-validatebox" readonly type="text" id="BORROWER" name="BORROWER" /></td>
					    <td><label for="MORT_TYPE_NAME">抵押类型</label></td>
						<td><input class="plui-validatebox" readonly type="text" name="MORT_TYPE_NAME" id="MORT_TYPE_NAME"/></td>
					</tr>
					<tr>
						<td colspan="2"><label
							for="ASSURE_AMOUNT">被担保主债权数额（最高债权数额）</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text"
							id="ASSURE_AMOUNT" name="ASSURE_AMOUNT"  style="width:98%" /></td>
					</tr>
					<tr>
						<td ><label for="ASSUER_RANGE">担保范围</label></td>
						<td colspan="4"><input class="plui-validatebox" readonly type="text"
							name="ASSUER_RANGE" id="ASSUER_RANGE"  style="width:98%"/></td>
					</tr>
					<tr>
						<td colspan="2"><label
							for="DEBT_DIS_LIMIT">债务履行期限（债权确定期间）</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text"
							id="DEBT_DIS_LIMIT" name="DEBT_DIS_LIMIT"  style="width:98%" />
						</td>
					</tr>
					<tr>
						<td colspan=2><label for="CER_NO">房地产证号/预售合同号</label></td>
						<td><input class="plui-validatebox" readonly type="text" id="CER_NO" name="CER_NO"/></td>
						<td><label for="MORT_SEQ">抵押顺位</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="MORT_SEQ" id="MORT_SEQ" /></td>
					</tr>
					<tr>
					    <td><label for="ALIAS_REG_DATE">登记时间</label></td>
						<td colspan=2><input class="plui-validatebox" readonly type="text"
							name="ALIAS_REG_DATE" id="ALIAS_REG_DATE" /></td>
						<td><label for="RECORDER">终审人/登簿人</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="RECORDER" id="RECORDER" /></td>
					</tr>
					<!-- 抵押权设立部分结束 -->
					<tr>
						<td colspan="5">
							<hr width="600">
						</td>
					</tr>
					<!-- 最高额抵押权确定登记部分 -->
					<tr>
						<td ><label for="SURE_REG_CODE">登记编号</label></td>
						<td colspan=4><input class="plui-validatebox" readonly type="text"
							name="SURE_REG_CODE" id="SURE_REG_CODE"  style="width:98%"/></td>
						<!-- <td><label for="sure_reg_date">登记时间</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="sure_reg_date" id="sure_reg_date" /></td> -->
					</tr>
					<tr>
					    <td colspan=2><label for="MAX_AMOUNT">最高债权确定事实和金额</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="MAX_AMOUNT" id="MAX_AMOUNT"  style="width:98%"/></td>
						<td><label for="SURE_AMOUNT">确定担保的债权数额</label></td>
						<td><input class="plui-validatebox" readonly type="text"
							name="SURE_AMOUNT" id="SURE_AMOUNT" /></td>
					</tr>
					<tr>
					    <td><label for="SURE_REG_DATE_N">登记时间</label></td>
						<td colspan=2><input class="plui-validatebox" readonly type="text"
							name="SURE_REG_DATE_N" id="SURE_REG_DATE_N" /></td>
						<td ><label for="SURE_RECORDER">终审人/登簿人</label></td>
						<td ><input class="plui-validatebox" readonly type="text"
							name="SURE_RECORDER" id="SURE_RECORDER" /></td>
					</tr>
					<tr>
						<!-- 最高额抵押确定登记结束 -->
						<td colspan="5">
							<hr width="600">
						</td>
					</tr>
					<!-- 抵押注销信息部分 -->
					<tr>
						<td colspan=2><label for="MORT_CAN_CODE">注销抵押登记编号</label></td>
						<td colspan=3><input class="plui-validatebox" readonly type="text"
							name="MORT_CAN_CODE" id="MORT_CAN_CODE"  style="width:98%"/></td>
					</tr>
					<tr>
					    <td><label for="CAN_REG_DATE">登记时间</label></td>
						<td colspan=2><input class="plui-validatebox" readonly type="text"
							name="CAN_REG_DATE_N" id="CAN_REG_DATE_N" /></td>
						<td ><label for="CAN_RECORDER">终审人/登簿人</label></td>
						<td ><input class="plui-validatebox" readonly type="text"
							name="CAN_RECORDER" id="CAN_RECORDER" /></td>
					</tr>
					<!-- 抵押注销信息结束 -->
				</table>
				<table>
					<tr>
						<td><label for="EXCURSUS" style="float: left">附记</label></td>

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
						<td>序号</td>
						<td>抵押类型</td>
						<td>抵押人</td>
						<td>登记日期</td>
					</tr>
				</table>
			</form>
	   </div>
</body>
</html>
