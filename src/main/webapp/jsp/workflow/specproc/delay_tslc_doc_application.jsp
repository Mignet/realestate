<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" import="com.szhome.security.ext.UserInfo" %>
<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>深圳市不动产权登记系统--公文延期申请表</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <script type="text/javascript" src="${ctx}/js/workflow/specproc/delay_tslc_doc_application.js"></script>
    <script type="text/javascript">
	  var ctx = '${ctx}';
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">   
  <style type="text/css">
      input,textarea,select{
         background-color: #cde6c7;
		 border-top:1px solid #afb4db;
		 border-left:1px solid #afb4db;
		 border-bottom:2px solid #cde6c7;
		 border-right:2px solid #cde6c7;
      }
	  .underline{
           background-color: #ffffff;       
		   border-color:#000000;    
		   border-style:solid;    
		   border-top-width:0px;    
		   border-right-width:0px;    
		   border-bottom-width:1px;    
		   border-left-width:0px;   
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
               <table style="width:90%;" border=0 >
                <thead>
				   <tr>
					 <td align="right"><a id="showhisdelayrecords" class="plui-linkbutton" iconCls="icon-detail">历史延期记录</a></td>
				   </tr>
				  </thead>
			     <caption style="font-size: 28px;font-weight: bolder;">公文延期申请表</caption>
				 <tbody>
                  <tr>
				   <td align="center">
					 <form id="delayForm" class="searchrow" method="post">
				         <input type="hidden" id="bus_id" name="sdelayi.bus_id"/>
					     <input type="hidden" id="delay_id" name="sdelayi.delay_id"/>
					     <input type="hidden" id="delay_status" name="sdelayi.delay_status"/>
					     <input type="hidden" id="regCode"/>
					     <input type="hidden" id="lastbusId"/>
				     <table style="width:100%;text-align:center;border-spacing: 0px;border:1px solid #ccc;" border=1>
					    <tr><td style="width:130px">延期编号</td><td style="width:240px"><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="delay_no" name="sdelayi.delay_no" size="20"/></td><td style="width:130px">登记申请人</td><td style="width:240px"><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="reg_app" name="sdelayi.reg_app" size="20"/></td></tr>
						 <tr><td>登记编号</td><td><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="reg_code" name="reg_code" size="20"/></td><td>业务描述</td><td><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="bus_des" name="sdelayi.bus_des" size="20"/></td></tr>
						  <tr><td>申请科室</td><td><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="delay_app_part" name="sdelayi.delay_app_part" size="20"/></td><td>延期申请人</td><td><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="delay_app" name="sdelayi.delay_app" size="20"/></td></tr>
						  <tr>
						   <td align="center" colspan=4 style="padding-left: 22px;padding-right: 22px;">
						      <table style="width:95%;height:100%;" border=0>
								<tr>
								  <td colspan=2 align="left">1、总办文时间：<input type="text" style="text-align:right" class="plui-validatebox underline" data-options="required:true,validType:'number'" id="total_limit" name="sdelayi.total_limit" size="1"/><input style="width: 50px;margin-bottom: 3px;vertical-align: middle;" class="underline" value="个工作日"/></td>
								</tr>
								<tr>
								  <td colspan=2 align="left">2、至今剩余天数：<input type="text" style="text-align:right;" class="plui-validatebox underline" data-options="required:true,validType:'number'"  id="remainder_days" name="sdelayi.remainder_days" size="1"/><input style="width: 50px;margin-bottom: 3px;vertical-align: middle;margin-top: 2px;" class="underline" value="个工作日"/></td>
								</tr>
								<tr>
								  <td colspan=2 align="left">3、过去是否申请延期及天数：<input type="text" class="plui-validatebox underline" data-options="required:true" id="isappanddays" name="sdelayi.isappanddays" size="15"/></td>
								</tr>
								<tr>
								  <td colspan=2 align="left">4、申请天数：<input type="text" style="text-align:right" class="plui-validatebox underline" data-options="required:true,validType:'number'" id="app_days" name="sdelayi.app_days" size="1"/><input style="width: 50px;margin-bottom: 3px;vertical-align: middle;" class="underline" value="个工作日"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批准天数：<input style="text-align:right" class="plui-validatebox underline" data-options="required:true,validType:'number'" type="text" id="approval_days" name="sdelayi.approval_days" size="1"/><input style="width: 50px;margin-bottom: 3px;vertical-align: middle;" class="underline" value="个工作日"/></td>
								</tr>
								<tr>
								  <td colspan=2 align="left">5、延期类型：
									   <select style="width:100px" class="plui-validatebox greenbg" data-options="required:true"  id="delay_type" name="sdelayi.delay_type">
	                                    <option value="069001">办文延期</option>
	                                    <option value="069002">处理延期</option>
	                                    <option value="069003">发文延期</option>
	                                   </select>
                                   </td>
								</tr>
								<tr>
								  <td colspan=2 align="left">6、是否为监察文：<input style="width:15px;height:15px" id="monitor01" name="sdelayi.monitor" type="radio" value="01" valign="middle"/>是&nbsp;&nbsp;&nbsp;&nbsp;<input style="width:15px;height:15px" type="radio" id="monitor02" value="02" name="sdelayi.monitor" valign="middle"/>否</td>
								</tr>
								<tr>
								  <td colspan=2 align="left">7、申请原因：<input type="text" class="underline" size="10"/></td>
								</tr>
								<tr>
								 <td align="left"><textarea style="overflow-y:hidden;" id="delay_reason" class="plui-validatebox" data-options="required:true"  name="sdelayi.delay_reason" cols="82" rows="6"></textarea></td><td valign="middle">
								     <input type="button" name="Submit"  id="fscyy" value="常用语" onclick="selectCyy()"/>
								 </td>
								</tr>
							  </table>
							  <br/>
						   </td>
						  </tr>
						  <tr id="delay_options">
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

