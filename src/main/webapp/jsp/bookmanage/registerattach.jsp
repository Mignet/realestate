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
<script type="text/javascript" src="${ctx }/js/bookmanage/registerattach.js"></script>
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
		<!-- 查封信息层 -->
		<div id='attachInfo' style="display:none;">
			<form id="attachInfoForm">
			   <hr/>
			   <div id="divAttachInfo" style="height:400px;overflow-y:auto">
				<table id="table_attach">
					<tr>
						<td width="150">
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
						<td colspan=3>
						 <c:choose>
			   			   <c:when test="${reg_unit_type eq HOUSECODE }">
						     <input class="plui-validatebox" readonly readonly type="text" name="HOUSE_CODE" id="HOUSE_CODE"  style="width:98%"/>
						   </c:when>
						   <c:when test="${reg_unit_type eq PARCELCODE }">
						       <input class="plui-validatebox" readonly type="text" name="PARCEL_CODE" id="PARCEL_CODE"  style="width:98%"/>
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
						<td colspan=3>
						 <c:choose>
						   <c:when test="${reg_unit_type eq HOUSECODE }">
						     <input class="plui-validatebox" readonly type="text" name="HOUSE_LOCATION" id="HOUSE_LOCATION"  style="width:98%"/>
						   </c:when>
						   <c:when test="${reg_unit_type eq PARCELCODE }">
						      <input class="plui-validatebox" readonly type="text" name="LAND_ADDRESS" id="LAND_ADDRESS"  style="width:98%"/>
						   </c:when>
						   <c:otherwise>
						     <label for="BUILD_LOCATION">楼宇坐落</label>
						   </c:otherwise>
						   </c:choose>
						</td>
					</tr>
					<tr>
					   <td colspan=4>
					     <hr width="600px">
					   </td>
					</tr>
					<tr>
						<td ><label for="label3">登记编号</label></td>
						<td><input class="plui-validatebox" readonly type="text" id="REG_CODE" name="REG_CODE" /></td>
						<td width="150"><label for="label4">查封类型</label></td>
						<td ><input class="plui-validatebox" readonly type="text" name="BUS_NAME" id="BUS_NAME" /></td>
					</tr>
					<tr>
						<td><label for="label5">查封机关</label></td>
						<td colspan=3><input class="plui-validatebox" readonly type="text" name="DIS_OFF" id="DIS_OFF"  style="width:98%"/></td>
					</tr>
					<tr>
					<td><label for="label6">查封文件</label></td>
						<td colspan=3><input class="plui-validatebox" readonly type="text" name="LAW_DOC" id="LAW_DOC"  style="width:98%"/></td>
					</tr>
					<tr>
						<td><label for="label7">查封文号</label></td>
						<td colspan=3><input class="plui-validatebox" readonly type="text" id="DIS_NO" name="DIS_NO"  style="width:98%"/></td>
					</tr>
					<tr>
						<td><label for="label8">查封时间</label></td>
						<td colspan=3><input class="plui-validatebox" readonly type="text" name="DIS_DATE" id="DIS_DATE"  style="width:98%"/></td>
					</tr>
					<tr>
						<td><label for="label9">查封期限</label></td>
						<td colspan=3><input class="plui-validatebox" readonly type="text" id="ATTACH_LIMIT" name="ATTACH_LIMIT"  style="width:98%" /></td>
					</tr>
					<tr>
					    <td><label for="label10">登记时间</label></td>
						<td><input class="plui-validatebox" readonly type="text" name="REG_DATE_N" id="REG_DATE_N" /></td>
						<td><label for="label11">终审人/登簿人</label></td>
						<td><input class="plui-validatebox" readonly type="text" name="RECORDER" id="RECORDER" /></td>
					</tr>
                    <tr>
	                    <td colspan="4">
						  <hr width="600px">
						</td>
					</tr>
					<tr>
						<td><label for="label12">查封注销登记编号</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text"name="REM_REG_CODE" id="REM_REG_CODE"   style="width:98%"/></td>
					</tr>
					<tr>
						<td><label for="label13">解除查封文件</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text" id="REM_LAW_DOC" name="REM_LAW_DOC"  style="width:98%"/>
						</td>
					</tr>
					<tr>
						<td><label for="label14">解除查封文号</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text" name="REM_DIS_NO" id="REM_DIS_NO"  style="width:98%" /></td>
					</tr>
					<tr>
					    <td><label for="label15">解除查封时间</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text"name="JDIS_DATE" id="JDIS_DATE"  style="width:98%"/></td>
				   <tr/>
				   <tr>
				        <td><label for="label15">登记时间</label></td>
						<td><input class="plui-validatebox" readonly type="text"name="JALIAS_REG_DATE" id="JALIAS_REG_DATE" /></td>
						<td><label for="label16">终审人/登簿人</label></td>
						<td><input class="plui-validatebox" readonly type="text"name="JRECORDER" id="JRECORDER" /></td>
					</tr>
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
				<hr />
				<table id="table_attachInfo" style="BORDER-COLLAPSE: collapse;"
						borderColor=#000000 align=center border=1 class="table_center">
						<tr>
							<td align="center">序号</td>
							<td align="center">登记种类</td>
							<td align="center">查封机关</td>
							<td align="center">登记日期</td>
						</tr>
			   </table>
			</form>
		</div>
</body>
</html>
