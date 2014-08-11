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
<title>登记簿预览</title>
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
            <!-- 自然信息层 -->
        	<div id="naturalInfo" style="display:none;">
        	
        		<hr>
        		<form id="naturalInfoForm" >
        			<table id="tableNaturalInfo" >
							<tr>
								<td width="110"><label for="PARCEL_CODE">宗地编号</label>  
        							 
								</td>
								<td><input class="plui-validatebox" type="text" name="PARCEL_CODE" id="PARCEL_CODE"  /></td>
							</tr>
							<tr>
								<td><label for="LAND_ADDRESS">宗地坐落</label>  
		        					
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
									<label for="PARCEL_AREA">宗地面积</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="PARCEL_AREA" id="PARCEL_AREA" /> 
		        				</td>
								<td width="80">
									<label for="SINGLE_AREA">独用面积</label>  
		        				<td>
		        					<input class="plui-validatebox" type="text" name="SINGLE_AREA" id="SINGLE_AREA" /></td>
		        				</td>
							</tr>
							<tr>
								<td>
									<label for="GLEBE_AREA">分摊面积</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="GLEBE_AREA" id="GLEBE_AREA" /> 
		        				</td>
								<td>
									<label for="BUILD_PLOT_RATIO">建筑容积率</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="BUILD_PLOT_RATIO" id="BUILD_PLOT_RATIO" />
		        				</td>
							</tr>
							<tr>
								<td>
									<label for="BUILDING_DENSITY">建筑密度</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="BUILDING_DENSITY" id="BUILDING_DENSITY" /> 
		        				</td>
								<td>
									<label for="BUILDING_HEIGHT_LIMIT">建筑高度</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name=BUILDING_HEIGHT_LIMIT id="BUILDING_HEIGHT_LIMIT" />
		        				</td>
							</tr>
							<tr>
								<td>
									<label for="CONSTRUCTIOIN_AREA">建筑物占地面积</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="CONSTRUCTIOIN_AREA" id="CONSTRUCTIOIN_AREA" /> 
		        				</td>
								<td>
									<label for="CONSTRUCTIOIN_TYPE">建筑物类型</label>  
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
									<label for="DEDARE_OWNERSHIP">申报建筑物权属</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="DEDARE_OWNERSHIP" id="DEDARE_OWNERSHIP" /> 
		        				</td>
								<td>
									<label for="ATTRIBUTE">权属性质</label>  
		        				<td>
		        					<input class="plui-validatebox" type="text" name="ATTRIBUTE" id="ATTRIBUTE" /></td>
		        				</td>
							</tr>
							<tr>
								<td>
									<label for="USE_RIGHT_TYPE">使用权类型</label>  
		        				</td>
								</td>
								<td  colspan="3">
		        					<input class="plui-validatebox" type="text" name="USE_RIGHT_TYPE" id="USE_RIGHT_TYPE" style="width:450px;"/>
		        				</td>
							</tr>
							<!-- <tr>
								<td >
									<label for="GET_PRICE">取得价格</label>  
								</td>
								<td  colspan="3">
		        					<input class="plui-validatebox" type="text" name="GET_PRICE" id="GET_PRICE" style="width:450px;"/>
		        				</td>
							</tr> -->
							<tr>
								<td>
									<label for="USE_PER">土地使用年限</label>  
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
        	<!-- 自然信息层  end-->
        	
        	<!--使用权信息层 -->
        	<div id='userInfo' style="display:none;">
	        		<div id="divUserInfo"></div>
						<form id="userInfoForm" >
						<hr >
						<table id="table_land">
							<tr>
								<td>
									<label for="PARCEL_CODE">宗地号</label>  
								</td>
								<td>
									<input class="plui-validatebox" type="text" name="PARCEL_CODE" id="PARCEL_CODE"  /> 
								</td>
							</tr>
							<tr>
								<td>
									<label for="LAND_ADDRESS">宗地坐落</label>  
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
									<label for="BUS_NAME">登记类型</label>  
								</td>
								<td>
									<input class="plui-validatebox" type="text" name="BUS_NAME" id="BUS_NAME" /> 
								</td>
								<!-- <td>
									<label for="GET_MODE">取得方式</label>  
        						</td>
        						<td>
									<input class="plui-validatebox" type="text" name="GET_MODE" id="GET_MODE" /> 
								</td> -->
							</tr>
							
							<tr>
								<!-- <td>
									<label for="USERIGHT_PROP">使用权性质</label>  
								</td>
								<td>
									<input class="plui-validatebox" type="text" name="USERIGHT_PROP" id="USERIGHT_PROP"  />   
								</td> -->
								<td width="110">
									<label for="recorder">终审人/登簿人</label>  
        							
        						</td>
        						<td width="80">
        							<input class="plui-validatebox" type="text" name="recorder" id="recorder"  /> 
        						</td>
								<td>
									<label for="REG_DATE">登记时间</label>  
        						</td>
        						<td>
									<input class="plui-validatebox" type="text" name="REG_DATE" id="regDate"  /> 
								</td>
							</tr>
							<!-- <tr>
								<td>
									<label for="recorder">终审人/登簿人</label>  
        							
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
									<label for="can_regcode">注销登记编号</label>  
        						</td>
        						<td>
									<input class="plui-validatebox" type="text" name="can_regcode" id="can_regcode"  /> 
								</td>
								<td width="130">
									<label for="can_reason">注销原因</label>  
        							
        						</td>
        						<td>
        							<input class="plui-validatebox" type="text" name="can_reason" id="can_reason"  /> 
        						</td>
							</tr>
							<tr>
								<td width='130'>
									<label for="can_regDate">登记时间</label>  
        						</td>
        						<td>
									<input class="plui-validatebox" type="text" name="can_regDate" id="can_regDate"  /> 
								</td>
								<td width="130">
									<label for="can_recorder">终审人/登簿人</label>  
        							
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
									<label for="EXCURSUS" style="float:left">附记</label>  
		        					
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
							<tr><td>序号</td><td>所有权人</td><td>身份证号</td><td>户籍所在地</td><td>共有情况</td><td>房地产证号</td></tr>						
							</table>
							<hr >
							<table id="table_hisuseInfo" style="BORDER-COLLAPSE: collapse;" borderColor=#000000    align=center border=1 class="table_center">
								<tr>
									<td>序号</td>
									<td>登记类型</td>
									<td>产权人</td>
									<td>登记日期</td>
									
								</tr>
								
							</table>
						</form>
        	</div>
		
			
        </div>

		
</body>
</html>
