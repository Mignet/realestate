<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" import="com.szhome.security.ext.UserInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>�����в�����Ȩ�Ǽ�ϵͳ--���صǼǾ�����</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <script type="text/javascript" src="${ctx}/js/workflow/specproc/business_baseinfo.js"></script>
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
	  table{
        font-size: 12px;
      }
    </style>   
</head>
<body class="plui-layout">
    <div data-options="region:'center'" border=false>
		<div class="plui-layout" fit=true style="text-align:center;">
			<table style="width:80%;text-align:center;" border=0>
				 <tbody>
					<tr style="height:24px"><td style="width:18%;text-align:right;">�ǼǱ�ţ�</td><td style="width:32%;text-align:left;"><input size="18" readonly/></td><td style="width:18%;text-align:right;">��������</td><td style="width:32%;text-align:left;"><input size="18" readonly/></td></tr>
					<tr style="height:24px"><td style="width:18%;text-align:right;">�Ǽ����ͣ�</td><td style="width:32%;text-align:left;"><input size="18" readonly/></td><td style="width:18%;text-align:right;">ҵ��������</td><td style="width:32%;text-align:left;"><input size="18" readonly/></td></tr>
					<tr style="height:24px"><td style="width:18%;text-align:right;">ת�Ʒ�ʽ��</td><td style="width:32%;text-align:left;"><select style="width:140px;"></select></td><td style="width:18%;text-align:right;">�������ʣ�</td><td style="width:32%;text-align:left;"><input size="18"/></td></tr>
				</tbody>
				<tfoot>
				  <tr style="height:120px">
				    <td style="width:18%;text-align:right;">���ز�֤���ǣ�<br/><br/><br/><button style="width:45px;height:20px;">��ʾ��</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td colspan=3 style="width:90%;text-align:left">
					   <textarea style="overflow-y:hidden;" rows="4" cols="60"></textarea>
					</td>
				  </tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>
</html>


