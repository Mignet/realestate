<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" import="com.szhome.security.ext.UserInfo" %>
<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>�����в�����Ȩ�Ǽ�ϵͳ--���صǼǾ�����</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <script type="text/javascript" src="${ctx}/js/workflow/specproc/reject_tslc_reg_decision.js"></script> 
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
			      <tr><td align="center">
			         <form id="rejectionForm" class="searchrow" method="post">
					     <input type="hidden" id="bus_id" name="srejectioni.bus_id"/>
					     <input type="hidden" id="rej_id" name="srejectioni.rej_id"/>
					     <input type="hidden" id="rej_no" name="srejectioni.rej_no"/>
					     <input type="hidden" id="app_date" name="srejectioni.app_date"/>
					     <input type="hidden" id="rej_status" name="srejectioni.rej_status"/>
					     <input type="hidden" id="regCode"/>
					     <input type="hidden" id="lastbusId"/>
					     <input type="hidden" id="law" name="srejectioni.law"/>
				      <table style="width:80%;height:100%;" border=0>
				         <caption style="font-size: 28px;font-weight: bolder;">���صǼǾ�����</caption>
				         <thead>
				           <tr>
				             <td align="right">������겵<span id='rej_code'></span>��</td>
				           </tr>
				         </thead>
				         <tbody>
				           <tr>
				             <td align="left"><input class="plui-validatebox greenbg" style="text-align:right" data-options="required:true" type="text" id="app" name="srejectioni.app" size="20" missingMessage="��������Ϊ������������磺���� ...."/>��</td>
				           </tr>
				           <tr>
				             <td style="padding-left: 32px;padding-top:8px;"><input class="plui-validatebox greenbg" data-options="required:true" type="text" missingMessage="��������Ϊ������������磺2008��08��08��...." id="app_date_str" name="app_date_str" size="11" />���յ�
				             <input class="plui-validatebox greenbg" data-options="required:true" missingMessage="��������Ϊ������������磺ĳĳ . ���ز���ʼ�����������..." type="text" id="bus_des" name="srejectioni.bus_des" size="27" /> ���룬�ǼǱ��Ϊ��<input class="plui-validatebox greenbg" data-options="required:true" type="text" id="reg_code" name="reg_code" size="20" />��
				             </td>
				           </tr>
				           <tr>
				              <td>���˲飬�����벻����<input class="plui-validatebox greenbg" data-options="required:true" type="text" id="law01" size="82" /></td>
				           </tr>
				           <tr>
				              <td><input class="greenbg" type="text" id="law02" size="92" /> �涨���������ظ����롣</td>
				           </tr>
				            <tr>
				              <td style="padding-left: 32px;padding-top:8px;text-align:left">�����������£�</td>
				           </tr>
				           <tr>
				              <td><textarea style="overflow-y:hidden;" class="plui-validatebox greenbg" data-options="required:true" cols="90" rows="4" id="rej_reason" name="srejectioni.rej_reason"></textarea></td>
				           </tr>
				         </tbody>
				         <tfoot>
				           <tr>
				              <td style="padding-left: 32px;padding-top:8px;text-align:left">�㣨��λ�����Ա������������Խӵ���֪֮ͨ����ʮ��������Ǽǻ������븴�顣</td>
				           </tr>
				         </tfoot>
			      </table>
			      </form>
			      <br/>
			    </td></tr>
			    <tr id="rejection_options">
			      <td style="height:700px;vertical-align: top;">
			         <jsp:include page="suggestion.jsp" flush="false"></jsp:include>
			      </td>
			    </tr>
			  <!--   <tr><td align="center">
			     <br/>
			      <table style="width:80%;height:100%;" border=0>
			        <tr>
			          <td align="left">���������������Ա����</td>
			        </tr>
			        <tr>
			         <td><textarea style="overflow-y:hidden;" cols="90" rows="4" name="rjremark"></textarea></td>
			        </tr>
			      </table>
				   <br/>
			    </td></tr>
			    <tr><td align="center">
			     <br/>
			      <table style="width:80%;height:100%;" border=0>
			        <tr>
			          <td align="left">�����������׼��Ա����</td>
			        </tr>
			        <tr>
			         <td><textarea style="overflow-y:hidden;" cols="90" rows="4" name="rjremark"></textarea></td>
			        </tr>
			      </table>
			    <br/>
			    </td></tr>
				 <tr><td align="center">
				  <br/>
			      <table style="width:80%;height:100%;" border=0>
			        <tr>
			          <td align="left">��׼�����������Ա����</td>
			        </tr>
			        <tr>
			         <td><textarea style="overflow-y:hidden;" cols="90" rows="4" name="rjremark"></textarea></td>
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

