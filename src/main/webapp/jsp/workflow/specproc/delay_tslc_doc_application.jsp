<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" import="com.szhome.security.ext.UserInfo" %>
<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>�����в�����Ȩ�Ǽ�ϵͳ--�������������</title>
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
					 <td align="right"><a id="showhisdelayrecords" class="plui-linkbutton" iconCls="icon-detail">��ʷ���ڼ�¼</a></td>
				   </tr>
				  </thead>
			     <caption style="font-size: 28px;font-weight: bolder;">�������������</caption>
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
					    <tr><td style="width:130px">���ڱ��</td><td style="width:240px"><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="delay_no" name="sdelayi.delay_no" size="20"/></td><td style="width:130px">�Ǽ�������</td><td style="width:240px"><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="reg_app" name="sdelayi.reg_app" size="20"/></td></tr>
						 <tr><td>�ǼǱ��</td><td><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="reg_code" name="reg_code" size="20"/></td><td>ҵ������</td><td><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="bus_des" name="sdelayi.bus_des" size="20"/></td></tr>
						  <tr><td>�������</td><td><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="delay_app_part" name="sdelayi.delay_app_part" size="20"/></td><td>����������</td><td><input type="text" class="plui-validatebox blankbox" data-options="required:true" id="delay_app" name="sdelayi.delay_app" size="20"/></td></tr>
						  <tr>
						   <td align="center" colspan=4 style="padding-left: 22px;padding-right: 22px;">
						      <table style="width:95%;height:100%;" border=0>
								<tr>
								  <td colspan=2 align="left">1���ܰ���ʱ�䣺<input type="text" style="text-align:right" class="plui-validatebox underline" data-options="required:true,validType:'number'" id="total_limit" name="sdelayi.total_limit" size="1"/><input style="width: 50px;margin-bottom: 3px;vertical-align: middle;" class="underline" value="��������"/></td>
								</tr>
								<tr>
								  <td colspan=2 align="left">2������ʣ��������<input type="text" style="text-align:right;" class="plui-validatebox underline" data-options="required:true,validType:'number'"  id="remainder_days" name="sdelayi.remainder_days" size="1"/><input style="width: 50px;margin-bottom: 3px;vertical-align: middle;margin-top: 2px;" class="underline" value="��������"/></td>
								</tr>
								<tr>
								  <td colspan=2 align="left">3����ȥ�Ƿ��������ڼ�������<input type="text" class="plui-validatebox underline" data-options="required:true" id="isappanddays" name="sdelayi.isappanddays" size="15"/></td>
								</tr>
								<tr>
								  <td colspan=2 align="left">4������������<input type="text" style="text-align:right" class="plui-validatebox underline" data-options="required:true,validType:'number'" id="app_days" name="sdelayi.app_days" size="1"/><input style="width: 50px;margin-bottom: 3px;vertical-align: middle;" class="underline" value="��������"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��׼������<input style="text-align:right" class="plui-validatebox underline" data-options="required:true,validType:'number'" type="text" id="approval_days" name="sdelayi.approval_days" size="1"/><input style="width: 50px;margin-bottom: 3px;vertical-align: middle;" class="underline" value="��������"/></td>
								</tr>
								<tr>
								  <td colspan=2 align="left">5���������ͣ�
									   <select style="width:100px" class="plui-validatebox greenbg" data-options="required:true"  id="delay_type" name="sdelayi.delay_type">
	                                    <option value="069001">��������</option>
	                                    <option value="069002">��������</option>
	                                    <option value="069003">��������</option>
	                                   </select>
                                   </td>
								</tr>
								<tr>
								  <td colspan=2 align="left">6���Ƿ�Ϊ����ģ�<input style="width:15px;height:15px" id="monitor01" name="sdelayi.monitor" type="radio" value="01" valign="middle"/>��&nbsp;&nbsp;&nbsp;&nbsp;<input style="width:15px;height:15px" type="radio" id="monitor02" value="02" name="sdelayi.monitor" valign="middle"/>��</td>
								</tr>
								<tr>
								  <td colspan=2 align="left">7������ԭ��<input type="text" class="underline" size="10"/></td>
								</tr>
								<tr>
								 <td align="left"><textarea style="overflow-y:hidden;" id="delay_reason" class="plui-validatebox" data-options="required:true"  name="sdelayi.delay_reason" cols="82" rows="6"></textarea></td><td valign="middle">
								     <input type="button" name="Submit"  id="fscyy" value="������" onclick="selectCyy()"/>
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
								  <td align="left">���������������Ա����</td>
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
								  <td align="left">�����������׼��Ա����</td>
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
								  <td align="left">��׼�����������Ա����</td>
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

