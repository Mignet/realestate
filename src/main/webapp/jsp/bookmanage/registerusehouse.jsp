<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>登记簿预览</title>
<%@ include file="/base/taglibs.jsp"%>
<c:if test="1==2">
    <%@ include file="/base/prefix.jsp" %>
</c:if>
<script type="text/javascript" src="${ctx }/js/bookmanage/registerusehouse.js"></script>
</head>
<body>
    	<!-- 自然信息层 -->
    	<!-- <div id="naturalInfo" style="display:none;">
    	
    		<hr>
    		<form id="naturalInfoForm" >
    			<table id="tableNaturalInfo" >
    					<tr>
				<td><label for="PARCEL_CODE">宗地号</label>  
    							
				</td>
				<td><input class="plui-validatebox" readonly type="text" name="PARCEL_CODE" id="PARCEL_CODE"  /> </td>
			</tr>
			<tr>
				<td><label for="ADV_HOUSE_CODE">房屋编号</label>  
    							 
				</td>
				<td><input class="plui-validatebox" readonly type="text" name="ADV_HOUSE_CODE" id="ADV_HOUSE_CODE"  /></td>
			</tr>
			<tr>
				<td><label for="HOUSE_LOCATION">房屋坐落</label>  
      					
      				</td>
				<td><input class="plui-validatebox" readonly type="text" name="HOUSE_LOCATION" id="HOUSE_LOCATION" /> </td>
			</tr>
			<tr>
				<td colspan="4">
					<hr>
				</td>
			</tr>
			<tr>
				<td>
					<label for="LAYER_COUNT">房屋总层数</label>  
      				</td>
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="LAYER_COUNT" id="LAYER_COUNT" /> 
      				</td>
				<td>
					<label for="AT_FLOOR">房屋所在层</label>  
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="AT_FLOOR" id="AT_FLOOR" /></td>
      				</td>
			</tr>
			<tr>
				<td>
					<label for="BUILD_AREA">建筑面积</label>  
      				</td>
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="BUILD_AREA" id="BUILD_AREA" /> 
      				</td>
				<td width='150'>
					<label for="zybfjzmj">专有部分建筑面积</label>  
      				</td>
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="ZYBFJZMJ" id="zybfjzmj" />
      				</td>
			</tr>
			<tr>
				<td>
					<label for="TAONEI_AREA">套内建筑面积</label>  
      				</td>
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="TAONEI_AREA" id="TAONEI_AREA" /> 
      				</td>
				<td>
					<label for="FT_COMMON_AREA">分摊共有面积</label>  
      				</td>
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="FT_COMMON_AREA" id="FT_COMMON_AREA" />
      				</td>
			</tr>
			<tr>
				<td>
					<label for="PLAN_USAGE">规则用途</label>  
      				</td>
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="PLAN_USAGE" id="PLAN_USAGE" /> 
      				</td>
				<td>
					<label for="HOUSE_STRUT">房屋结构</label>  
      				</td>
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="HOUSE_STRUT" id="HOUSE_STRUT" />
      				</td>
			</tr>
			<tr>
				<td colspan="4">
					<hr>
				</td>
			</tr>
			<tr>
				<td>
					<label for="CER_NO">房地产证号</label>  
      				</td>
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="CER_NO" id="CER_NO" /> 
      				</td>
				<td>
					<label for="LAND_ATTRIBUTE">土地性质</label>  
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="LAND_ATTRIBUTE" id="LAND_ATTRIBUTE" /></td>
      				</td>
			</tr>
			<tr>
				<td>
					<label for="tdqdfs">国有土地使用权取得方式</label>  
      				</td>
				</td>
				<td  colspan="3">
      					<input class="plui-validatebox" readonly type="text" name="TDQDFS" id="tdqdfs" style="width:450px;"/>
      				</td>
			</tr>
			<tr>
				<td >
					<label for="jttdsylx">集体土地使用权类型</label>  
				</td>
				<td  colspan="3">
      					<input class="plui-validatebox" readonly type="text" name="JTTDSYLX" id="jttdsylx" style="width:450px;"/>
      				</td>
			</tr>
			<tr>
				<td>
					<label for="USE_PER">土地使用年限</label>  
				</td>
				<td  colspan="3">
      					<input class="plui-validatebox" readonly type="text" name="USE_PER" id="USE_PER" style="width:485px;"/>
      				</td>
			</tr>
			<tr>
				<td colspan="4">
					<hr>
				</td>
			</tr>
			
    			</table> -->
    	<!--  自然信息中不用
    			<table >
    				<tr>
				<td>
					<label for="fj" style="float:left">附记</label>  
      					
					</td>
					
				</tr>
				<tr>
					<td>
						<textArea   name="FJ" id="fj" style="width:600px;height:100px;" >
						</textArea> 	
					</td>
				</tr>
    			</table>
    			-->
    		<!-- </form>
    	</div> -->
    	<!-- 自然信息层  end-->
    	<!-- 所有权信息层 -->
     <div id='ownershipInfo' style="display: none;">
		<form id="ownershipInfoForm">
			<hr>
			<div id="divOwnershipInfo" style="height:400px;overflow-y:auto">
			<table id="table_ownership">
				<tr>
					<td width='120'>
					     <c:choose>
					       <c:when test="${reg_unit_type eq HOUSECODE }">
						     <label for="HOUSE_CODE">房屋编号</label>
						   </c:when>
						   <c:otherwise>
						     <label for="ADV_HOUSE_CODE">楼宇编号</label>
						   </c:otherwise>
						   </c:choose>
                    </td>
					<td colspan=3>
					  <c:choose>
					       <c:when test="${reg_unit_type eq HOUSECODE }">
						     <input class="plui-validatebox" readonly type="text"
						        name="HOUSE_CODE" id="HOUSE_CODE" style="width:98%"/>
						   </c:when>
						   <c:otherwise>
						     <label for="ADV_HOUSE_CODE">楼宇编号</label>
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
						   <c:otherwise>
						     <label for="ADV_HOUSE_CODE">楼宇坐落</label>
						   </c:otherwise>
						   </c:choose>
                      </td>
					<td colspan=3>
					  <c:choose>
					       <c:when test="${reg_unit_type eq HOUSECODE }">
						     <input class="plui-validatebox" readonly type="text"
						        name="HOUSE_LOCATION" id="HOUSE_LOCATION" style="width:98%" />
						   </c:when>
						   <c:otherwise>
						     <label for="ADV_HOUSE_CODE">楼宇坐落</label>
						   </c:otherwise>
						   </c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<hr width="600">
					</td>
				</tr>
				<tr>
					<td><label for="REG_CODE">登记编号</label></td>
					<td><input class="plui-validatebox" readonly type="text"
						name="REG_CODE" id="REG_CODE" /></td>
					<td width='120'><label for="BUS_NAME">登记种类</label></td>
					<td><input class="plui-validatebox" readonly type="text"
						name="BUS_NAME" id="BUS_NAME" /></td>
				</tr>
				<tr>
					<td><label for="GET_MODE">房屋取得方式</label></td>
					<td><input class="plui-validatebox" readonly type="text"
						name="GET_MODE" id="GET_MODE" /></td>
					<td><label for="HOUSE_KIND_N">房屋性质</label></td>
					<td><input class="plui-validatebox" readonly type="text"
						name="HOUSE_KIND_N" id="HOUSE_KIND_N" /></td>
				</tr>
				<!-- <tr>
					<td><label for="HOUSE_CLASS">产别</label></td>
					<td colspan=3><input class="plui-validatebox" readonly type="text"
						name="HOUSE_CLASS" id="HOUSE_CLASS" /></td>
				</tr> -->
				<tr>
					<td><label for="regDate">登记时间</label></td>
					<td><input class="plui-validatebox" readonly type="text" name="REG_DATE"
						id="REG_DATE" /></td>
					<td><label for="recorder">终审人/登簿人</label></td>
					<td colspan=3><input class="plui-validatebox" readonly type="text"
						name="recorder" id="recorder"  style="width:98%"/></td>
				</tr>
			</table>
			<hr width="600">
			<table id="table_holder" style="BORDER-COLLAPSE: collapse"
				borderColor=#000000 cellPadding=1 align=center border=1
				class="table_center">	
			</table>
			<br/>
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
			<table id="table_hisOwnInfo" style="BORDER-COLLAPSE: collapse;"
				borderColor=#000000 align=center border=1 class="table_center">
				<tr>
					<td>序号</td>
					<td>登记类型</td>
					<td>产权人</td>
					<td>登记日期</td>
				</tr>
			</table>
		</form>
		<div id="divSyqr"></div>
		<div id="divDjzl"></div>
	</div>
	<div id="divDjbyl" style="display: none;"></div>
</body>
</html>
