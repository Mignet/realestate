<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK" import="com.szhome.security.ext.UserInfo"%>
<%
  UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
	String userName=userInfo.getUserName();
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�Ǽǲ�Ԥ��</title>
  <%@ include file="/base/taglibs.jsp"%>
  <%@ include file="/base/prefix.jsp" %>


<style>
	div.selected{
		display:block;
	}
	
	table{
		margin:auto;
		width:600px;
	}
	table input{
		
		float:left;
		readOnly:expression(this.readOnly=true);
		BORDER-TOP-STYLE: none; 
		BORDER-RIGHT-STYLE: none;
		BORDER-LEFT-STYLE: none; 
		
	}
	table label{
		
		float:right;
	}
	
	.table_center input{
		text-align:center;
		float:none;
	}
	.table_center label{
		
		float:none;
	}
</style>

<script type="text/javascript">
	//<![CDATA[
	   var ctx='${ctx}';
	//]]
</script>
 <script type="text/javascript" src="${ctx}/js/common/registerbook/registeruse.js"></script>
  
 
</head>
<body>
	<div id="cleft" data-options="region:'west'" style="width:203px;height:600px">
        	<ul id="registerbook_tree"></ul>
        </div>
        <div data-options="region:'center'" style="text-align:center">
            <!-- ��Ȼ��Ϣ�� -->
        	<div id="naturalInfo" style="display:none;">
        	
        		<hr>
        		<form id="naturalInfoForm" >
        			<table id="tableNaturalInfo" >
							<tr>
								<td width="110"><label for="PARCEL_CODE">�ڵر��</label>  
        							 
								</td>
								<td><input class="plui-validatebox" type="text" name="PARCEL_CODE" id="PARCEL_CODE"  /></td>
							</tr>
							<tr>
								<td><label for="LAND_ADDRESS">�ڵ�����</label>  
		        					
		        				</td>
								<td ><input class="plui-validatebox" type="text" name="LAND_ADDRESS" id="LAND_ADDRESS" /> </td>
							</tr>
							<tr>
								<td colspan="4">
									<hr>
								</td>
							</tr>
							<tr>
								<td>
									<label for="PARCEL_AREA">�ڵ����</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="PARCEL_AREA" id="PARCEL_AREA" /> 
		        				</td>
								<td width="80">
									<label for="SINGLE_AREA">�������</label>  
		        				<td>
		        					<input class="plui-validatebox" type="text" name="SINGLE_AREA" id="SINGLE_AREA" /></td>
		        				</td>
							</tr>
							<tr>
								<td>
									<label for="GLEBE_AREA">��̯���</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="GLEBE_AREA" id="GLEBE_AREA" /> 
		        				</td>
								<td>
									<label for="BUILD_PLOT_RATIO">�����ݻ���</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="BUILD_PLOT_RATIO" id="BUILD_PLOT_RATIO" />
		        				</td>
							</tr>
							<tr>
								<td>
									<label for="BUILDING_DENSITY">�����ܶ�</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="BUILDING_DENSITY" id="BUILDING_DENSITY" /> 
		        				</td>
								<td>
									<label for="BUILDING_HEIGHT_LIMIT">�����߶�</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name=BUILDING_HEIGHT_LIMIT id="BUILDING_HEIGHT_LIMIT" />
		        				</td>
							</tr>
							<tr>
								<td>
									<label for="CONSTRUCTIOIN_AREA">������ռ�����</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="CONSTRUCTIOIN_AREA" id="CONSTRUCTIOIN_AREA" /> 
		        				</td>
								<td>
									<label for="CONSTRUCTIOIN_TYPE">����������</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="CONSTRUCTIOIN_TYPE" id="CONSTRUCTIOIN_TYPE" />
		        				</td>
							</tr>
							<tr>
								<td colspan="4">
									<hr>
								</td>
							</tr>
							<tr>
								<td>
									<label for="DEDARE_OWNERSHIP">�걨������Ȩ��</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="DEDARE_OWNERSHIP" id="DEDARE_OWNERSHIP" /> 
		        				</td>
								<td>
									<label for="ATTRIBUTE">Ȩ������</label>  
		        				<td>
		        					<input class="plui-validatebox" type="text" name="ATTRIBUTE" id="ATTRIBUTE" /></td>
		        				</td>
							</tr>
							<tr>
								<td>
									<label for="USE_RIGHT_TYPE">ʹ��Ȩ����</label>  
		        				</td>
								</td>
								<td  colspan="3">
		        					<input class="plui-validatebox" type="text" name="USE_RIGHT_TYPE" id="USE_RIGHT_TYPE" style="width:450px;"/>
		        				</td>
							</tr>
							<!-- <tr>
								<td >
									<label for="GET_PRICE">ȡ�ü۸�</label>  
								</td>
								<td  colspan="3">
		        					<input class="plui-validatebox" type="text" name="GET_PRICE" id="GET_PRICE" style="width:450px;"/>
		        				</td>
							</tr> -->
							<tr>
								<td>
									<label for="USE_PER">����ʹ������</label>  
								</td>
								<td  colspan="3">
		        					<input class="plui-validatebox" type="text" name=USE_PER_VALUE id="USE_PER" style="width:485px;"/>
		        				</td>
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
        	
        	<!--ʹ��Ȩ��Ϣ�� -->
        	<div id='userInfo' style="display:none;">
	        		<div id="divUserInfo"></div>
						<form id="userInfoForm" >
						<hr >
						<table id="table_land">
							<tr>
								<td>
									<label for="PARCEL_CODE">�ڵغ�</label>  
								</td>
								<td>
									<input class="plui-validatebox" type="text" name="PARCEL_CODE" id="PARCEL_CODE"  /> 
								</td>
							</tr>
							<tr>
								<td>
									<label for="LAND_ADDRESS">�ڵ�����</label>  
		        				</td>
								<td>
									<input class="plui-validatebox" type="text" name="LAND_ADDRESS" id="LAND_ADDRESS" /> 
								</td>
							</tr>
							<tr>
							<td colspan="4">
							<hr width="600">
							</td>
							</tr>
							
							<tr>
								<td>
									<label for="BUS_NAME">�Ǽ�����</label>  
								</td>
								<td>
									<input class="plui-validatebox" type="text" name="BUS_NAME" id="BUS_NAME" /> 
								</td>
								<!-- <td>
									<label for="GET_MODE">ȡ�÷�ʽ</label>  
        						</td>
        						<td>
									<input class="plui-validatebox" type="text" name="GET_MODE" id="GET_MODE" /> 
								</td> -->
							</tr>
							
							<tr>
								<!-- <td>
									<label for="USERIGHT_PROP">ʹ��Ȩ����</label>  
								</td>
								<td>
									<input class="plui-validatebox" type="text" name="USERIGHT_PROP" id="USERIGHT_PROP"  />   
								</td> -->
								<td width="110">
									<label for="recorder">������/�ǲ���</label>  
        							
        						</td>
        						<td width="80">
        							<input class="plui-validatebox" type="text" name="recorder" id="recorder"  /> 
        						</td>
								<td>
									<label for="REG_DATE">�Ǽ�ʱ��</label>  
        						</td>
        						<td>
									<input class="plui-validatebox" type="text" name="REG_DATE" id="regDate"  /> 
								</td>
							</tr>
							<!-- <tr>
								<td>
									<label for="recorder">������/�ǲ���</label>  
        							
        						</td>
        						<td>
        							<input class="plui-validatebox" type="text" name="recorder" id="recorder"  /> 
        						</td>
							</tr> -->
							<tr>
							<td colspan="4">
							<hr width="600">
							</td>
							</tr>
							<br/>
							
							<tr>
								<td width='130'>
									<label for="can_regcode">ע���ǼǱ��</label>  
        						</td>
        						<td>
									<input class="plui-validatebox" type="text" name="can_regcode" id="can_regcode"  /> 
								</td>
								<td width="130">
									<label for="can_reason">ע��ԭ��</label>  
        							
        						</td>
        						<td>
        							<input class="plui-validatebox" type="text" name="can_reason" id="can_reason"  /> 
        						</td>
							</tr>
							<tr>
								<td width='130'>
									<label for="can_regDate">�Ǽ�ʱ��</label>  
        						</td>
        						<td>
									<input class="plui-validatebox" type="text" name="can_regDate" id="can_regDate"  /> 
								</td>
								<td width="130">
									<label for="can_recorder">������/�ǲ���</label>  
        							
        						</td>
        						<td>
        							<input class="plui-validatebox" type="text" name="can_recorder" id="can_recorder"  /> 
        						</td>
							</tr>
						</table>
        					<hr width="600">
							<table>
								<tr>
									<td>
									<label for="EXCURSUS" style="float:left">����</label>  
		        					
									</td>
									
								</tr>
								<tr>
									<td>
										<textArea   name="EXCURSUS" id="EXCURSUS" style="width:600px;height:100px;" >
										</textArea> 	
									</td>
								</tr>							
        					</table>
							<hr >
							<table id="table_holder" style="BORDER-COLLAPSE: collapse" borderColor=#000000  cellPadding=1  align=center border=1 class="table_center">
							<tr><td>���</td><td>����Ȩ��</td><td>���֤��</td><td>�������ڵ�</td><td>�������</td><td>���ز�֤��</td></tr>						
							</table>
							<hr >
							<table id="table_hisuseInfo" style="BORDER-COLLAPSE: collapse;" borderColor=#000000    align=center border=1 class="table_center">
								<tr>
									<td>���</td>
									<td>�Ǽ�����</td>
									<td>��Ȩ��</td>
									<td>�Ǽ�����</td>
									
								</tr>
								
							</table>
						</form>
        	</div>
		
			
        </div>

		
</body>
</html>
