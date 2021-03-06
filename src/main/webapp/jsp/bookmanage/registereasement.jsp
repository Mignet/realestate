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
<script type="text/javascript" src="${ctx }/js/bookmanage/registereasement.js"></script>
</head>

<body>
	    <!-- 地役权信息层 -->
		<div id='easementInfo' style="display:none;">
			<form id="easementInfoForm">
				<hr>
			<div id="divEasementInfo" style="height:400px;overflow-y:auto">
				<table id="table_easement">
				    <!-- 抵押权设立部分开始 -->
					<tr>
						<td width='150'>
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
						    <input class="plui-validatebox" readonly type="text"
							name="HOUSE_CODE" id="HOUSE_CODE"  style="width:98%"/>
						   </c:when>
						   <c:when test="${reg_unit_type eq PARCELCODE }">
						     <input class="plui-validatebox" readonly type="text"
							        name="PARCEL_CODE" id="PARCEL_CODE"  style="width:98%"/>
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
						     <input class="plui-validatebox" readonly type="text"
							   name="HOUSE_LOCATION" id="HOUSE_LOCATION"  style="width:98%"/>
						   </c:when>
						   <c:when test="${reg_unit_type eq PARCELCODE }">
						     <input class="plui-validatebox" readonly type="text"
							   name="LAND_ADDRESS" id="LAND_ADDRESS"  style="width:98%"/>
						   </c:when>
						   <c:otherwise>
						     <label for="BUILD_LOCATION">楼宇坐落</label>
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
						<td><label for="REG_CODE">登记编号</label></td>
						<td><input class="plui-validatebox" readonly type="text" name="REG_CODE" id="REG_CODE" /></td>
						<td width='150'><label for="BUS_NAME">登记种类</label></td>
						<td>
						<input class="plui-validatebox" readonly type="text" id="BUS_NAME" name="BUS_NAME" />
							<!-- <input type="hidden" class="plui-combodict" code='reg_type'  />   -->
                        </td>
					</tr>
					<tr>
						<td><label for="EASE_HOLDER">地役权人</label></td>
						<td colspan=3><input class="plui-validatebox" readonly type="text"
							id="EASE_HOLDER" name="EASE_HOLDER" style="width:98%"/></td>
					</tr>
					<tr>
						<td><label for="EASE_SET_ITEM">地役权设立情况</label></td>
						<td colspan=3><input class="plui-validatebox" readonly type="text" id="EASE_SET_ITEM" name="EASE_SET_ITEM" style="width:98%"/></td>
					</tr>
					<tr>
						<td><label for="EASEMENT_LIMIT">地役权利用期限</label></td>
						<td colspan=3>
						    <input class="plui-validatebox" readonly type="text" id="EASEMENT_LIMIT" name="EASEMENT_LIMIT" style="width:98%" />
						</td>
					</tr>
					<tr>
						<td><label for="CER_NO">他项权证号</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text" id="CER_NO" name="CER_NO"  style="width:98%"/>
						</td>
					</tr>
					<tr>
						<td><label for="REG_DATE_N">登记时间</label></td>
						<td><input class="plui-validatebox" readonly type="text" id="REG_DATE_N" name="REG_DATE_N"/></td>
						<td><label for="RECORDER">终审人/登薄人</label></td>
						<td><input class="plui-validatebox" readonly type="text" id="RECORDER" name="RECORDER"/></td>
					</tr>
					<!-- 抵押权设立部分结束 -->
					<tr>
						<td colspan="4">
							<hr width="600">
						</td>
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
				<hr>
				<table id="table_easementInfo" style="BORDER-COLLAPSE: collapse;"
					borderColor=#000000 align=center border=1 class="table_center">
					<tr>
						<td>序号</td>
						<td>登记类型</td>
						<td>地役权人</td>
						<td>登记日期</td>
					</tr>
				</table>
			</form>
		</div>
</body>

</html>
