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
<script type="text/javascript" src="${ctx }/js/bookmanage/registernatural.js"></script>
</head>
<body>
	<!-- 自然信息层 -->
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
						<td><label for="PARCEL_CODE">宗地号</label></td>
						<td colspan=4><input class="plui-validatebox" readonly type="text"
							name="PARCEL_CODE" id="PARCEL_CODE"  style="width: 98%;"/></td>
					</tr>
					<!-- 根据要求屏蔽显示的代码  BEGIN-->
						<tr class="classtr">
							<td><label for="HOUSE_CODE">房屋编号</label></td>
							<td  colspan=4><input class="plui-validatebox" readonly type="text"
								name="HOUSE_CODE" id="HOUSE_CODE" style="width: 98%;" /></td>
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
							     <label for="BUILDING_ADDR">楼宇坐落</label>
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
							<td><label for="LAYER_COUNT">房屋总层数</label></td>
							<td colspan=2><input class="plui-validatebox" readonly type="text"
								name="LAYER_COUNT" id="LAYER_COUNT" /></td>
							<td><label for="FLOOR_NO">房屋所在层</label>
							<td><input class="plui-validatebox" readonly type="text"
								name="FLOOR_NO" id="FLOOR_NO" />
						   </td>
						</tr>
						<tr class="classtr">
							<td><label for="BUILD_AREA">建筑面积</label></td>
							<td colspan=2><input class="plui-validatebox" readonly type="text"
								name="BUILD_AREA" id="BUILD_AREA" /></td>
							<td colspan=2>&nbsp;</td>
							<!-- <td><label for="zybfjzmj">专有部分建筑面积</label></td>
							<td><input class="plui-validatebox" readonly type="text"
								name="ZYBFJZMJ" id="zybfjzmj" /></td> -->
						</tr>
						<tr class="classtr">
							<td><label for="INNER_AREA">套内建筑面积</label></td>
							<td colspan=2><input class="plui-validatebox" readonly type="text"
								name="INNER_AREA" id="INNER_AREA" /></td>
							<td><label for="SHARE_AREA">分摊共有面积</label></td>
							<td><input class="plui-validatebox" readonly type="text"
								name="SHARE_AREA" id="SHARE_AREA" /></td>
						</tr>
						<tr class="classtr">
							<td><label for="PLAN_USAGE">规划用途</label></td>
							<td colspan=2><input class="plui-validatebox" readonly type="text"
								name="PLAN_USAGE" id="PLAN_USAGE" /></td>
							<td><label for="HOUSE_STRUT_N">房屋结构</label></td>
							<td><input class="plui-validatebox" readonly type="text"
								name="HOUSE_STRUT_N" id="HOUSE_STRUT_N" /></td>
						</tr>
					<!-- 根据要求屏蔽显示的代码 END -->
					<tr>
						<td colspan="5">
							<hr>
						</td>
					</tr>
					<tr>
						<td><label for="PARCEL_CODE">地号</label></td>
						<td colspan=2><input class="plui-validatebox" readonly type="text" name="PARCEL_CODE"
							id="PARCEL_CODE1" /></td>
						<td><label for="LAND_ATTRIBUTE_N">土地性质</label>
						<td><input class="plui-validatebox" readonly type="text"
							name="LAND_ATTRIBUTE_N" id="LAND_ATTRIBUTE_N" /></td>
					</tr>
					<tr>
						<td><label for="CER_NO">土地证号</label></td>
						<td colspan="4"><input class="plui-validatebox" readonly type="text"
							name="CER_NO" id="CER_NO" style="width: 98%;" /></td>
					</tr>
					<tr>
						<td colspan="2"><label for="USERIGHT_TYPE">国有土地使用权取得方式</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text"
							name="USERIGHT_TYPE" id="USERIGHT_TYPE" style="width: 98%;" /></td>
					</tr>
				<!-- 	<tr>
						<td colspan="2"><label for="jttdsylx">集体土地使用权类型</label></td>
						<td colspan="3"><input class="plui-validatebox" readonly type="text"
							name="JTTDSYLX" id="jttdsylx" style="width: 98%;" /></td>
					</tr> -->
					<tr>
						<td><label for="USE_PER">土地使用年限</label></td>
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
						   <label for="fj" style="float:left">附记</label>  
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
	<!-- 自然信息层  end-->
</body>

</html>
