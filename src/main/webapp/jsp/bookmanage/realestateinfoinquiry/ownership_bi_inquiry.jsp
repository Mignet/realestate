<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" import="com.szhome.security.ext.UserInfo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>�����в�����Ȩ�Ǽ�ϵͳ</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <script type="text/javascript" src="${ctx}/js/bookmanage/realestateinfoinquiry/ownership_bi_inquiry.js"></script>
    <script type="text/javascript">
	  var ctx = '${ctx}';
    </script>
	<style type="text/css">
	   th{
	    text-align:right;
	   }
	   #mytable th{
	     width:120px;
	   }
	   #mytable td{
	      width:230px;
	   }
	</style>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">   
</head>
<body class="plui-layout">
    <div data-options="region:'center'" border=false>
		<div class="plui-layout" fit=true>
			<div data-options="region:'north',split:true,border:false" style="height:auto;">
				    <form id="simpleform" class="searchrow" method="post">
						 <table id="mytable">
						  <!--   <tr><td colspan=2><input type="hidden" name="queryCondition" value="user_name like ?"/></td></tr>
							�Ա���ÿ���ؼ�Ԫ�أ������������Ҳ���PLUIcombo�����������Ӧ������ʽ"combo"��
								 �Ա���ÿ���ؼ�Ԫ�أ������combo�������Ӧ��������"width="80""��
							 -->
							<tr>
								<th>�ǼǱ�ţ�</th>
								<td>
								  <input type="text" name="sovo.regcode" />
								</td>
								<th>�ڵغţ�</th>
								<td><input type="text" name="sovo.parcelcode" /></td>
							</tr>
							<tr>
							    <th>���ţ�</th>
								<td >
								    <input type="text" name="sovo.houseno" />
								 </td>
								 <th>�������䣺</th>
								<td><input type="text" name="sovo.houselocation" /></td>
							</tr>
							<tr>
								<th>��Ŀ��</th>
								<td >
									<input type="text" name="sovo.proname" /> 
								</td>
								<th>ת�Ʒ�ʽ��</th>
								<td>
								    <input type="text" name="sovo.getmode"/>
								</td>
							</tr>
							<tr>
								<th>¥�������ţ�</th>
								<td >
									<input type="text" name="sovo.building" /> 
								</td>
								<th>Ȩ�������֤���룺</th>
								<td>
								    <input type="text" name="sovo.idno"/>
								</td>
							</tr>
							<tr>
							    <td colspan=3>&nbsp;</td>
								<td>
								 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
									<a class="plui-linkbutton" href="javascript:;" iconCls='icon-search' onclick="submit1();">��ѯ</a>
								</td>
							</tr>
						</table>
						</form>
                              <br/>
			</div>
			<div data-options="region:'center',border:false">
				<table id="ownershipBILst"></table>
			</div>
			<div id="printdiv" style="text-align:right;">
				<span style="margin-left:7px;margin-right:7px;"><a class="plui-linkbutton" href="javascript:;" iconCls='icon-print' onclick="print();">�� ӡ</a></span>
			</div>
		</div>
	</div>
</body>
</html>
