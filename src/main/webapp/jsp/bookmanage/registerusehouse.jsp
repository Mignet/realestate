<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�Ǽǲ�Ԥ��</title>
<%@ include file="/base/taglibs.jsp"%>
<c:if test="1==2">
    <%@ include file="/base/prefix.jsp" %>
</c:if>
<script type="text/javascript" src="${ctx }/js/bookmanage/registerusehouse.js"></script>
</head>
<body>
    	<!-- ��Ȼ��Ϣ�� -->
    	<!-- <div id="naturalInfo" style="display:none;">
    	
    		<hr>
    		<form id="naturalInfoForm" >
    			<table id="tableNaturalInfo" >
    					<tr>
				<td><label for="PARCEL_CODE">�ڵغ�</label>  
    							
				</td>
				<td><input class="plui-validatebox" readonly type="text" name="PARCEL_CODE" id="PARCEL_CODE"  /> </td>
			</tr>
			<tr>
				<td><label for="ADV_HOUSE_CODE">���ݱ��</label>  
    							 
				</td>
				<td><input class="plui-validatebox" readonly type="text" name="ADV_HOUSE_CODE" id="ADV_HOUSE_CODE"  /></td>
			</tr>
			<tr>
				<td><label for="HOUSE_LOCATION">��������</label>  
      					
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
					<label for="LAYER_COUNT">�����ܲ���</label>  
      				</td>
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="LAYER_COUNT" id="LAYER_COUNT" /> 
      				</td>
				<td>
					<label for="AT_FLOOR">�������ڲ�</label>  
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="AT_FLOOR" id="AT_FLOOR" /></td>
      				</td>
			</tr>
			<tr>
				<td>
					<label for="BUILD_AREA">�������</label>  
      				</td>
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="BUILD_AREA" id="BUILD_AREA" /> 
      				</td>
				<td width='150'>
					<label for="zybfjzmj">ר�в��ֽ������</label>  
      				</td>
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="ZYBFJZMJ" id="zybfjzmj" />
      				</td>
			</tr>
			<tr>
				<td>
					<label for="TAONEI_AREA">���ڽ������</label>  
      				</td>
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="TAONEI_AREA" id="TAONEI_AREA" /> 
      				</td>
				<td>
					<label for="FT_COMMON_AREA">��̯�������</label>  
      				</td>
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="FT_COMMON_AREA" id="FT_COMMON_AREA" />
      				</td>
			</tr>
			<tr>
				<td>
					<label for="PLAN_USAGE">������;</label>  
      				</td>
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="PLAN_USAGE" id="PLAN_USAGE" /> 
      				</td>
				<td>
					<label for="HOUSE_STRUT">���ݽṹ</label>  
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
					<label for="CER_NO">���ز�֤��</label>  
      				</td>
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="CER_NO" id="CER_NO" /> 
      				</td>
				<td>
					<label for="LAND_ATTRIBUTE">��������</label>  
      				<td>
      					<input class="plui-validatebox" readonly type="text" name="LAND_ATTRIBUTE" id="LAND_ATTRIBUTE" /></td>
      				</td>
			</tr>
			<tr>
				<td>
					<label for="tdqdfs">��������ʹ��Ȩȡ�÷�ʽ</label>  
      				</td>
				</td>
				<td  colspan="3">
      					<input class="plui-validatebox" readonly type="text" name="TDQDFS" id="tdqdfs" style="width:450px;"/>
      				</td>
			</tr>
			<tr>
				<td >
					<label for="jttdsylx">��������ʹ��Ȩ����</label>  
				</td>
				<td  colspan="3">
      					<input class="plui-validatebox" readonly type="text" name="JTTDSYLX" id="jttdsylx" style="width:450px;"/>
      				</td>
			</tr>
			<tr>
				<td>
					<label for="USE_PER">����ʹ������</label>  
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
    	<!--  ��Ȼ��Ϣ�в���
    			<table >
    				<tr>
				<td>
					<label for="fj" style="float:left">����</label>  
      					
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
    	<!-- ��Ȼ��Ϣ��  end-->
    	<!-- ����Ȩ��Ϣ�� -->
     <div id='ownershipInfo' style="display: none;">
		<form id="ownershipInfoForm">
			<hr>
			<div id="divOwnershipInfo" style="height:400px;overflow-y:auto">
			<table id="table_ownership">
				<tr>
					<td width='120'>
					     <c:choose>
					       <c:when test="${reg_unit_type eq HOUSECODE }">
						     <label for="HOUSE_CODE">���ݱ��</label>
						   </c:when>
						   <c:otherwise>
						     <label for="ADV_HOUSE_CODE">¥����</label>
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
						     <label for="ADV_HOUSE_CODE">¥����</label>
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
						   <c:otherwise>
						     <label for="ADV_HOUSE_CODE">¥������</label>
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
						     <label for="ADV_HOUSE_CODE">¥������</label>
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
					<td><label for="REG_CODE">�ǼǱ��</label></td>
					<td><input class="plui-validatebox" readonly type="text"
						name="REG_CODE" id="REG_CODE" /></td>
					<td width='120'><label for="BUS_NAME">�Ǽ�����</label></td>
					<td><input class="plui-validatebox" readonly type="text"
						name="BUS_NAME" id="BUS_NAME" /></td>
				</tr>
				<tr>
					<td><label for="GET_MODE">����ȡ�÷�ʽ</label></td>
					<td><input class="plui-validatebox" readonly type="text"
						name="GET_MODE" id="GET_MODE" /></td>
					<td><label for="HOUSE_KIND_N">��������</label></td>
					<td><input class="plui-validatebox" readonly type="text"
						name="HOUSE_KIND_N" id="HOUSE_KIND_N" /></td>
				</tr>
				<!-- <tr>
					<td><label for="HOUSE_CLASS">����</label></td>
					<td colspan=3><input class="plui-validatebox" readonly type="text"
						name="HOUSE_CLASS" id="HOUSE_CLASS" /></td>
				</tr> -->
				<tr>
					<td><label for="regDate">�Ǽ�ʱ��</label></td>
					<td><input class="plui-validatebox" readonly type="text" name="REG_DATE"
						id="REG_DATE" /></td>
					<td><label for="recorder">������/�ǲ���</label></td>
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
			<table id="table_hisOwnInfo" style="BORDER-COLLAPSE: collapse;"
				borderColor=#000000 align=center border=1 class="table_center">
				<tr>
					<td>���</td>
					<td>�Ǽ�����</td>
					<td>��Ȩ��</td>
					<td>�Ǽ�����</td>
				</tr>
			</table>
		</form>
		<div id="divSyqr"></div>
		<div id="divDjzl"></div>
	</div>
	<div id="divDjbyl" style="display: none;"></div>
</body>
</html>
