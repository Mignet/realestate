<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>���غ�ͬ��Ϣ</title>
<%@ include file="/base/taglibs.jsp"%>
  <%@ include file="/base/prefix.jsp"%>
  <script type="text/javascript" src="${ctx}/js/common/landcontract.js"></script>
	<script type="text/javascript"> 	
  	var ctx = '${ctx}';	 
</script>
</head>
<body>
	<table id='contract' width="900" border="0" bgcolor="#999999">
	<tr>
    <td colspan="4" align="center">
      <label style='font-weight:bold;'>��ͬ����</label>
    </td>
  </tr>
  </table>
  <table id='contractinfo' width="900" border="0" bgcolor="#999999">
  <tr>
    <td width="115px" align="right">�õ���Ŀ��ţ�</td>
    <td width="150px"> <input type="text" name="LP_LU_PROJ_NO" /></td>
	  <td align="right" width="115px">��ͬ��ţ�</td>
    <td><input type="text" name="CONT_SEQ" /></td>
    <td align="right" width="115px">��ͬ�ֺţ�</td>
    <td><input type="text" name="CONTRACT_FLAG" /></td>
  </tr>
  <tr>
    <td align="right">��ͬ��ţ�</td>
    <td><input type="text" name="CONTRACT_NO" />
    <td align="right">�ڵغţ�</td>
    <td><input type="text" name="PARCEL_NO" />
    <td align="right">���÷�ʽ��</td>
    <td>
    <input class="plui-combodict"  code="069" value="" id="CONTRACT_TYPE" name="CONTRACT_TYPE"/>
    <!-- <select id="CONTRACT_TYPE" name="CONTRACT_TYPE" style="width:150px;"> 
					<option  value="069001">Э��</option>
					<option  value="069002">�б�</option>
					<option  value="069003">����</option>
					<option  value="069004">��ʱ</option>
					<option  value="069006">Э��ؼ�</option>
					<option  value="069005">��ؼ�</option>
					<option  value="069007">�г��ۼ���Ʒ��</option>
					<option  value="069008">��������</option>
					<option  value="069009">��������</option>
					</select> --></td>
  </tr>
  <tr>
    <td align="right">�ļ���ţ�</td>
    <td><input type="text" name="DOC_DOC_NO" />
    <td align="right">���߱�ţ�</td>
    <td><input type="text" name="RL_REDLINE_NO" />
    <td align="right">�õ������</td>
    <td><input type="text" name="LU_AREA" /></td>
  </tr>
    <tr>
    <td align="right">������;��</td>
    <td>
    <input class="plui-combodict"  code="015" value="" id="LU_FUNCTION" name="LU_FUNCTION"/>
    <!-- <select id="LU_FUNCTION" style="width:150px;"> 
					<option  value="015001">סլ�õ�</option>
					<option  value="015002">��ҵ�õ�</option>
					<option  value="015003">�ִ��õ�</option>
					<option  value="015004">��ҵ����ҵ�õ�</option>
					<option  value="015005">����ҵ�õ�</option>
					<option  value="015006">���ڱ���ҵ�õ�</option>
					<option  value="015007">����������ʩ�õ�</option>
					<option  value="015008">�̴��õ�</option>
					<option  value="015009">���������õ�</option>
					<option  value="015010">��/��/���õ�</option>
					</select> --></td>
    <td align="right">�õص�λ��</td>
    <td><input type="text" name="textfield2" />
    <td align="right">����λ�ã�</td>
    <td><input type="text" name="LU_LOCATION" /></td>
  </tr>
  <tr>
    <td align="right">�������ʣ�</td>
    <td><input type="text" name="textfield2" />
    <td align="right">�׷�������룺</td>
    <td><input type="text" name="CLIENT_REPRES_NO" />
    <td align="right">�׷�����������</td>
    <td><input type="text" name="CLIENT_REPRES_NAME" /></td>
  </tr>
   <tr>
    <td align="right">ʹ�����ޣ�</td>
    <td><input type="text" name="LU_TERM" />
    <td align="right">��ʼ���ڣ�</td>
    <td><input type="text" name="START_DATE" />
    <td align="right">��ֹ���ڣ�</td>
    <td><input type="text" name="END_DATE" /></td>
  </tr>
  <tr>
    <td align="right">�׷����ƣ�</td>
    <td><input type="text" name="CLIENT_NAME" />
    <td align="right">ǩ���ص㣺</td>
    <td><input type="text" name="SIGN_PLACE" />
    <td align="right">ǩ�����ڣ�</td>
    <td><input type="text" name="SIGN_DATE" /></td>
  </tr>
   <tr>
    <td align="right">��ͬ����</td>
    <td><input type="text" name="CONT_MARGIN" />
    <td align="right">������֣�</td>
    <td>
    <input class="plui-combodict"  code="057" value="" id="MARGIN_CURR_TYPE" name="MARGIN_CURR_TYPE"/>
    <!-- <select id="MARGIN_CURR_TYPE" style="width:150px;"> 
					<option  value="057001">HKD</option>
					<option  value="057001">RMB</option>
					<option  value="057001">USD</option>
					</select> --></td>
    <td align="right">�������ޣ�</td>
    <td><input type="text" name="COMPLETE_DATE" /></td>
  </tr>
  <tr>
    <td align="right">������ý�</td>
    <td><input type="text" name="LAND_GRANT_COST" />
    <td align="right">���ý���֣�</td>
    <td>
    <input class="plui-combodict"  code="057" value="" id="GRANT_CURRENCY" name="GRANT_CURRENCY"/>
    <!-- <select id="GRANT_CURRENCY" style="width:150px;"> 
					<option  value="057001">HKD</option>
					<option  value="057001">RMB</option>
					<option  value="057001">USD</option>
					</select> --></td>
    <td align="right">���⿪���ѣ�</td>
    <td><input type="text" name="LAND_DEVEL_COST" /></td>
    
  </tr>
  <tr>
    <td align="right">�����ѱ��֣�</td>
    <td>
    <input class="plui-combodict"  code="057" value="" id="DEVEL_CURRENCY" name="DEVEL_CURRENCY"/>
    <!-- <select id="DEVEL_CURRENCY" style="width:150px;"> 
					<option  value="057001">HKD</option>
					<option  value="057001">RMB</option>
					<option  value="057001">USD</option>
					</select> --></td>
    <td align="right">�������׷ѣ�</td>
    <td><input type="text" name="LAND_ATTACH_COST" />
    <td align="right">���׷ѱ��֣�</td>
    <td>
    <input class="plui-combodict"  code="057" value="" id="ATTACH_CURRENCY" name="ATTACH_CURRENCY"/>
   <!--  <select id="ATTACH_CURRENCY" style="width:150px;"> 
					<option  value="057001">HKD</option>
					<option  value="057001">RMB</option>
					<option  value="057001">USD</option>
					</select> --></td>
  </tr>
   <tr>
    <td align="right">�����������룺</td>
    <td><input type="text" name="OTHER_INCOME" />
    <td align="right">����������֣�</td>
    <td>
    <input class="plui-combodict"  code="057" value="" id="OTHER_CURRENCY" name="OTHER_CURRENCY"/>
    <!-- <select id="OTHER_CURRENCY" style="width:150px;"> 
					<option  value="057001">HKD</option>
					<option  value="057001">RMB</option>
					<option  value="057001">USD</option>
					</select> --></td>
  </tr>
     <tr>
    <td align="right">��ע��</td>
    <td colspan="5">
	<textarea name="REMARK" rows="5" style="width:650px;"></textarea>
	</td>
  </tr>
</table>
</body>
</html>
