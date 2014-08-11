<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" import="com.szhome.security.ext.UserInfo" %>
<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>深圳市不动产权登记系统--退文申请表</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <script type="text/javascript" src="${ctx}/js/workflow/specproc/backlanguage_tslc_application.js"></script>
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
               <table style="width:90%;border-spacing: 0px;border:1px solid #ccc;" border=1>
			    <tr><td>
			     <form id="refundForm" class="searchrow" method="post">
			     <input type="hidden" id="bus_id" name="srefundi.bus_id"/>
			     <input type="hidden" id="refund_id" name="srefundi.refund_id"/>
			     <input type="hidden" id="refund_no" name="srefundi.refund_no"/>
			     <input type="hidden" id="refund_status" name="srefundi.refund_status"/>
			     <input type="hidden" id="regCode"/>
			     <input type="hidden" id="lastbusId"/>
			      <table style="width:100%;height:100%;" border=0>
			         <caption style="font-size: 28px;font-weight: bolder;">退文申请表</caption>
			         <tbody>
							<tr style="padding-bottom: 15px;">
								<td style="width: 20%; text-align: right;">登记编号：</td>
								<td style="width: 25%; text-align: left;"><input id="reg_code" 
									name="reg_code" readonly class="greenbg" type="text" size="20" /></td>
								<td style="width: 25%;text-align: right;">解除登记文办文事项：</td>
								<td style="width: 30%;text-align: left;"><input id="reg_item"
									name="srefundi.reg_item" class="plui-validatebox greenbg" data-options="required:true" type="text" size="18" /></td>
							</tr>
							<tr>
								<td style="text-align: right;">已通知登记申请人：</td>
								<td style="text-align: left;"><input id="reg_app"
									name="srefundi.reg_app" class="plui-validatebox greenbg" data-options="required:true" type="text" size="20"/></td>
								<td style="text-align: right;">通知日期：</td>
								<td style="text-align: left;"><input id="notice_date"
									name="srefundi.notice_date" class="plui-datebox greenbg" required="required" type="text" size="18" /></td>
							</tr>
							<tr>
								<td colspan=4
									style="padding-top: 18px; padding-left: 59px; text-align: left;">退文理由：</td>
							</tr>
							<tr>
								<td colspan=4
									style="padding-bottom: 22px; padding-left: 59px; text-align: left;">
									<textarea id="refund_reason" name="srefundi.refund_reason" class="plui-validatebox" data-options="required:true"  style="overflow-y: hidden;" cols="68" rows="6"
										name="rjremark"></textarea>
								</td>
							</tr>
							<tr>
								<td style="text-align: right;">退文申请人姓名：</td>
								<td style="text-align: left;"><input id="refund_app"
									name="srefundi.refund_app" class="plui-validatebox greenbg" data-options="required:true" type="text" size="20" onchange="setrfApp(this.value)"/></td>
								<td style="text-align: right;">退文申请人身份证号：</td>
								<td style="text-align: left;">
									<table style="width:100%;" border=0>
									  <tr>
									    <td><input id="refund_app_idno" name="srefundi.refund_app_idno" class="plui-validatebox greenbg" data-options="required:true" type="text" size="18" /></td>
									    <td><button style="width: 60px; height: 22px; font-size: 9;">读IC卡</button></td>
									  </tr>
									</table>
								</td>
							</tr>
							<tr>
							  <td colspan=4>
							     <br/>
							     <table style="width:100%;text-align: center;">
						            <tr>
						               <td style="height: 30px;"><span style="font-size:20px;font-weight: bolder;" id="refund_caption">深圳市房地产权交易中心</span></td>
						            </tr>
						            <tr>
						               <td style="height: 30px;"><span style="font-size:20px;font-weight: bolder;">退文通知书</span></td>
						            </tr>
						            <tr>
						               <td style="text-align: left;padding-left: 30px;font-size:18px;"><span id="rd_app">&nbsp;</span>：</td>
						            </tr>
						            <tr>
						               <td align="center">
						                    <textarea name="srefundi.notice_note" id="notice_note" cols="80" rows="9"></textarea>
						                    <div id="refund_prt" style="display:none;width:90%;" border=true>
						                          <span id="refund_notice" style="width:100%;font-size: 18px;text-align: left;line-height: 27px;">
						                             
						                          </span>
						                    </div>
						               </td>
						            </tr>
						            <tr>
						               <td style="height:25px;text-align: right;padding-right: 120px;"><span id="refund_caption_floor" style="font-size:18px;">深圳市房地产权交易中心</span></td>
						            </tr>
						            <tr>
						               <td style="height:25px;text-align: right;padding-right: 120px;"><span id="refund_date" style="font-size:18px;">二Ｏ一四年五月二十日</span></td>
						            </tr>
						          </table>
							  </td>
							</tr>
						</tbody>
			      </table>
			      </form>
			      <br/>
			    </td></tr>
			     <tr id="refund_options">
						      <td style="height:560px;vertical-align: top;">
						         <jsp:include page="suggestion.jsp" flush="false"></jsp:include>
						      </td>
						    </tr>
			   <!--  <tr><td align="center">
			      <table style="width:80%;height:100%;" border=0>
			        <tr>
			          <td align="left">初审意见（初审人员）：</td>
			        </tr>
			        <tr>
			         <td align="left"><textarea style="overflow-y:hidden;" cols="82" rows="4" name="rjremark"></textarea></td>
			        </tr>
			      </table>
				   <br/>
			    </td></tr>
			    <tr><td align="center">
			      <table style="width:80%;height:100%;" border=0>
			        <tr>
			          <td align="left">复审意见（核准人员）：</td>
			        </tr>
			        <tr>
			         <td align="left"><textarea style="overflow-y:hidden;" cols="82" rows="4" name="rjremark"></textarea></td>
			        </tr>
			      </table>
			    <br/>
			    </td></tr>
				 <tr><td align="center">
			      <table style="width:80%;height:100%;" border=0>
			        <tr>
			          <td align="left">核准意见（科室人员）：</td>
			        </tr>
			        <tr>
			         <td align="left"><textarea style="overflow-y:hidden;" cols="82" rows="4" name="rjremark"></textarea></td>
			        </tr>
			      </table>
				  <br/>
			    </td></tr> -->
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


