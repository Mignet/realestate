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
<script type="text/javascript" src="${ctx}/js/common/register-preview.js"></script>
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
								<td><label for="PARCEL_CODE">宗地号</label>  
        							
								</td>
								<td><input class="plui-validatebox" type="text" name="PARCEL_CODE" id="PARCEL_CODE"  /> </td>
								<td><label for="HOUSE_LOCATION">房屋坐落</label>  
		        					
		        				</td>
								<td><input class="plui-validatebox" type="text" name="HOUSE_LOCATION" id="HOUSE_LOCATION" /> </td>
							</tr>
							<!-- 
							<tr>
								<td><label for="ADV_HOUSE_CODE">房屋编号</label>  
        							 
								</td>
								<td><input class="plui-validatebox" type="text" name="ADV_HOUSE_CODE" id="ADV_HOUSE_CODE"  /></td>
							</tr>
							 -->
							<tr>
								<td colspan="4">
									<hr>
								</td>
							</tr>
							<tr>
								<td>
									<label for="LAYER_COUNT">项目名称</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="PRO_NAME" id="PRO_NAME" /> 
		        				</td>
								<td >
									<label for="AT_FLOOR">栋号</label>  
		        				<td>
		        					<input class="plui-validatebox" type="text" name="BUILD_NO" id="BUILD_NO" /></td>
		        				</td>
							</tr>
							<tr>
								<td >
									<label for="AT_FLOOR">层次</label>  
		        				<td>
		        					<input class="plui-validatebox" type="text" name="AT_FLOOR" id="AT_FLOOR" /></td>
		        				</td>
								<td >
									<label for="AT_FLOOR">房号</label>  
		        				<td>
		        					<input class="plui-validatebox" type="text" name="ROOMNAME" id="ROOMNAME" /></td>
		        				</td>
							</tr>
							<tr>
								<td>
									<label for="LAYER_COUNT">房屋总层数</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="LAYER_COUNT" id="LAYER_COUNT" /> 
		        				</td>
								<td>
									<label for="BUILD_AREA">建筑面积</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="BUILD_AREA" id="BUILD_AREA" /> 
		        				</td>
							</tr>
							<tr>
								<td >
									<label for="zybfjzmj">专有部分建筑面积</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="ZYBFJZMJ" id="zybfjzmj" />
		        				</td>
								<td>
									<label for="TAONEI_AREA">套内建筑面积</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="TAONEI_AREA" id="TAONEI_AREA" /> 
		        				</td>
							</tr>
							<tr>
								<td>
									<label for="FT_COMMON_AREA">分摊共有面积</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="FT_COMMON_AREA" id="FT_COMMON_AREA" />
		        				</td>
								<td>
									<label for="PLAN_USAGE">规划用途</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="PLAN_USAGE" id="PLAN_USAGE" /> 
		        				</td>
							</tr>
							<tr>
								<td>
									<label for="HOUSE_STRUT">房屋结构</label>  
		        				</td>
		        				<td>
		        					<input class="plui-validatebox" type="text" name="HOUSE_STRUT" id="HOUSE_STRUT" />
		        				</td>
								<td>
									<label for="HOUSE_ATTR">竣工时间</label>  
								</td>
								<td>
									<input class="plui-validatebox"  type="text" name="BUILD_COMPLEION_DATE"  id="BUILD_COMPLEION_DATE"  />   
								</td>
							</tr>
							<tr>
								<td>
									<label for="HOUSE_ATTR">房屋性质</label>  
								</td>
								<td>
									<input class="plui-validatebox"  type="text" name="HOUSE_ATTR_NAME"  id="HOUSE_ATTR"  />   
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
		        					<input class="plui-validatebox" type="text" name="PA_CER_NO" id="PA_CER_NO" /> 
		        				</td>
		        				<td>
									<label for="LAND_ATTRIBUTE">土地面积</label>  
		        				<td>
		        					<input class="plui-validatebox" type="text" name="PARCEL_AREA" id="PARCEL_AREA" /></td>
		        				</td>
		        				
		        			</tr>
		        			<tr>
		        				<td>
									<label for="LAND_ATTRIBUTE">土地用途</label>  
		        				<td>
		        					<input class="plui-validatebox" type="text" name="PA_REAL_USAGE_NAME" id="REAL_USAGE" /></td>
		        				</td>
								<td>
									<label for="LAND_ATTRIBUTE">土地性质</label>  
		        				<td>
		        					<input class="plui-validatebox" type="text" name="LAND_ATTRIBUTE" id="LAND_ATTRIBUTE" /></td>
		        				</td>
							</tr>
							<tr>
								<td width="130">
									<label for="tdqdfs">国有土地使用权取得方式(集体土地使用权类型)</label>  
		        				</td>
								</td>
								<td  >
		        					<input class="plui-validatebox" type="text" name="USERRIGHT_TYPE" id="USERRIGHT_TYPE"/>
		        				</td>
								<td>
									<label for="LAND_ATTRIBUTE">取得时间</label>  
		        				<td>
		        					<input class="plui-validatebox" type="text" name="PA_START_DATE" id="PA_START_DATE" /></td>
		        				</td>
							</tr>
							<!-- 
							<tr>
								<td >
									<label for="jttdsylx">集体土地使用权类型</label>  
								</td>
								<td  colspan="3">
		        					<input class="plui-validatebox" type="text" name="JTTDSYLX" id="jttdsylx"  style="width:100%"/>
		        				</td>
							</tr>
							 -->
							<tr>
								<td>
									<label for="USE_PER">土地使用年限</label>  
								</td>
								<td  colspan="3">
		        					<input class="plui-validatebox" type="text" name="USE_PER_VALUE" id="USE_PER"  style="width:100%"/>
		        				</td>
							</tr>
							<tr>
								<td colspan="4">
									<hr>
								</td>
							</tr>
							
        			</table>
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
        		</form>
        	</div>
        	<!-- 自然信息层  end-->
        	
        	<!-- 所有权信息层 -->
        	<div id='ownershipInfo' style="display:none;">
        		
	        		<div id="divOwnershipInfo"></div>
						<form id="ownershipInfoForm" >
						<hr >
						<table id="table_fw">
							
							<tr>
								<td>
									<label for="ADV_HOUSE_CODE">房屋编号</label>  
								</td>
								<td>
									<input class="plui-validatebox" type="text" name="ADV_HOUSE_CODE" id="ADV_HOUSE_CODE"  /> 
								</td>
							</tr>
							<tr>
								<td>
									<label for="HOUSE_LOCATION">房屋坐落</label>  
		        				</td>
								<td>
									<input class="plui-validatebox" type="text" name="HOUSE_LOCATION" id="HOUSE_LOCATION" /> 
								</td>
							</tr>
							<tr>
							<td colspan="4">
							<hr width="600">
							</td>
							</tr>
							
							<tr>
								<td>
									<label for="REG_CODE">登记编号</label>  
								</td>
								<td>
									<input class="plui-validatebox" type="text" name="REG_CODE" id="REG_CODE" /> 
								</td>
								<td>
									<label for="BUS_NAME">登记类型</label>  
								</td>
								<td>
									<input class="plui-validatebox" type="text" name="BUS_NAME" id="BUS_NAME" /> 
								</td>
								
							</tr>
							
							<tr>
								<td>
									<label for="GET_MODE">取得方式</label>  
        						</td>
        						<td>
        							<input class="plui-validatebox"  type="text" name="GET_MODE_NAME"  id="GET_MODE"  />
        							<!-- 
									<input class="plui-validatebox " type="hidden" code='house_get_mode' type="text" name="GET_MODE" id="GET_MODE_DICT"  />
									 --> 
								</td>
								<td>
									<label for="REG_VALUE">登记价格</label>  
								</td>
								<td>
									<input class="plui-validatebox" type="text" name="REG_VALUE" id="REG_VALUE" /> 
								</td>
								<!-- 
								<td>
									<label for="HOUSE_ATTR">房屋性质</label>  
								</td>
								<td>
									<input class="plui-validatebox"  type="text" name="HOUSE_ATTR_NAME"  id="HOUSE_ATTR"  />   
								</td>
								 -->
								
							</tr>
							<tr>
								<td width='130'>
									<label for="REG_DATE">登记时间</label>  
        						</td>
        						<td>
									<input class="plui-validatebox" type="text" name="REG_DATE" id="REG_DATE"  /> 
								</td>
								<td width="130">
									<label for="RECORDER">终审人/登簿人</label>  
        							
        						</td>
        						<td>
        							<input class="plui-validatebox" type="text" name="RECORDER" id="RECORDER"  /> 
        						</td>
							</tr>
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
						<table id="can_info">
							</table>
							
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
							<table id="table_holder" style="BORDER-COLLAPSE: collapse" borderColor=#000000  cellPadding=1  align=center border=1 class="table_center">
								<tr><td>序号</td><td>所有权人</td><td>身份证号</td><td>户籍所在地</td><td>共有情况</td><td>房地产证号</td></tr>
							</table>
							<hr >
							<table id="table_hisOwnInfo" style="BORDER-COLLAPSE: collapse;" borderColor=#000000    align=center border=1 class="table_center">
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
        	
        	<div id="divDjbyl" style="display:none;">
        	</div>
		
			
        </div>

		
</body>
</html>
