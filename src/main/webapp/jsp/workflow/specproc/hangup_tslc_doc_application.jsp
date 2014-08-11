<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" import="com.szhome.security.ext.UserInfo" %>
<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>深圳市不动产权登记系统--公文挂起申请表</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <script type="text/javascript" src="${ctx}/js/workflow/specproc/hangup_tslc_doc_application.js"></script>
    <script type="text/javascript">
	  var ctx = '${ctx}';
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">   
    <style type="text/css">
      input,textarea{
         background-color: #cde6c7;
		 border-top:1px solid #afb4db;
		 border-left:1px solid #afb4db;
		 border-bottom:2px solid #cde6c7;
		 border-right:2px solid #cde6c7;
      }
	  table{
         font-size: 12px;
      }
    </style>   
</head>
<body class="plui-layout"> --%>
<div class="plui-layout" fit=true>
    <div data-options="region:'center'" border=false style="overflow-x: hidden;">
		<table style="width:100%;height:100%;border:1px solid #ccc;">
           <tr>
             <td align="center">
			    <table style="width:90%;" border=0>
			     <caption style="font-size: 28px;font-weight: bolder;">公文挂起申请表</caption>
				 <thead>
				   <tr>
					 <td align="right"><a id="showhanguprecords" class="plui-linkbutton" iconCls="icon-detail" onclick="">查看挂起记录</a></td>
				   </tr>
				  </thead>
				  <tbody>
                  <tr>
				   <td align="center">
				   <form id="suspendForm" class="searchrow" method="post">
				     <input type="hidden" id="bus_id" name="ssuspendi.bus_id"/>
				     <input type="hidden" id="sus_id" name="ssuspendi.sus_id"/>
				     <input type="hidden" id="sus_status" name="ssuspendi.sus_status"/>
				     <input type="hidden" id="regCode"/>
				     <input type="hidden" id="lastbusId"/>
				     <table style="width:100%;text-align:center;border-spacing: 0px;border:1px solid #ccc;" border=1 >
					    <tr><td style="width:180px;">挂起编号</td><td style="width:300px;"><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="sus_no" name="ssuspendi.sus_no" size="20"/></td><td style="width:180px;">登记申请人</td><td style="width:300px;"><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="reg_app" name="ssuspendi.reg_app" size="20"/></td></tr>
						 <tr><td>登记编号</td><td><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="reg_code" name="reg_code" size="20"/></td><td>业务描述</td><td><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="bus_des" name="ssuspendi.bus_des" size="20"/></td></tr>
						  <tr><td>申请科室</td><td><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="app_off" name="ssuspendi.app_off" size="20"/></td><td>挂起申请人</td><td><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="sus_app" name="ssuspendi.sus_app" size="20"/></td></tr>
						  <tr>
						   <td colspan=4 align="center">
						      <table style="width:90%;height:100%;" border=0>
								<tr>
								  <td colspan=2 align="left">申请原因：<input type="text" class="underline" size="10"/></td>
								</tr>
								<tr>
								 <td align="left"><textarea style="overflow-y:hidden;" class="plui-validatebox" data-options="required:true" cols="85" rows="6" id="sus_reason" name="ssuspendi.sus_reason"></textarea></td><td valign="middle">
								     <input type="button" name="Submit"  id="fscyy" value="常用语" onclick="selectCyy()"/>
								 </td>
								</tr>
							  </table>
							  <br/>
						   </td>
						  </tr>
						   <tr id="suspend_options">
						      <td colspan=4 style="height:560px;vertical-align: top;">
						         <jsp:include page="suggestion.jsp" flush="false"></jsp:include>
						      </td>
						    </tr>
							<!-- <tr><td colspan=4 align="center">
							  <table style="width:90%;height:100%;" border=0>
								<tr>
								  <td align="left">初审意见（初审人员）：</td>
								</tr>
								<tr>
								 <td align="left"><textarea style="overflow-y:hidden;" cols="90" rows="4" name="dmremark"></textarea></td>
								</tr>
							  </table>
							   <br/>
							</td></tr>
							<tr><td colspan=4 align="center">
							  <table style="width:90%;height:100%;" border=0>
								<tr>
								  <td align="left">复审意见（核准人员）：</td>
								</tr>
								<tr>
								 <td align="left"><textarea style="overflow-y:hidden;" cols="90" rows="4" name="dmremark"></textarea></td>
								</tr>
							  </table>
							  <br/>
							</td></tr>
							 <tr><td colspan=4 align="center">
							  <table style="width:90%;height:100%;" border=0>
								<tr>
								  <td align="left">核准意见（科室人员）：</td>
								</tr>
								<tr>
								 <td align="left"><textarea style="overflow-y:hidden;" cols="90" rows="4" name="dmremark"></textarea></td>
								</tr>
							  </table>
							  <br/>
							</td></tr> -->
					 </table>
					</form>
				   </td>
				  </tr>
				</tbody>
			</table>
			</td>
		   </tr>
		</table>
		<br/>
	     <br/>
	</div>
	</div>
<!-- </body>
</html> -->

