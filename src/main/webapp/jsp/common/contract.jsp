<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>合同信息</title>
<%@ include file="/base/taglibs.jsp"%>
  <%@ include file="/base/prefix.jsp"%>
  <script type="text/javascript" src="${ctx}/js/common/contract.js"></script>
	<script type="text/javascript"> 	
  	var ctx = '${ctx}';	 
</script>
</head>
<body>
	<table id='table_contract' width="781" border="0" bgcolor="#999999">
  <tr>
    <td colspan="2" align="left"><label>宗地号：</label><label name='PARCEL_CODE' id='PARCEL_CODE'></label>&nbsp;</td>
    <td width="209" align="right"><label>楼名及栋号：
    </label><label name='BUILDING_NAME' id='BUILDING_NAME'></label>&nbsp; </td>
    <td width="221">&nbsp;</td>
  </tr>
  <tr>
    <td height="16" colspan="4" align="center"><form id="form3" name="form3" method="post" action="">
      <label>合同</label>
    </form>    </td>
  </tr>
  <tr>
    <td height="15" align="right">&nbsp;</td>
    <td align="right"><div >
	
	<label>来源 </label><label>退购</label>&nbsp;&nbsp;
	
    </div></td>
    <td align="right">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="99" height="27" align="right"><form id="form1" name="form1" method="post" action="">
      <label>合同号：</label>    
    </form>    </td>
    <td width="234"><label></label>
	  <input type="text" name="ID" />
      <label>
      <input type="radio" name="radiobutton" value="radiobutton" />
      </label>
      <input type="radio" name="radiobutton" value="radiobutton" /></td>
    <td align="right">是否需要公证：</td>
    <td><input type="text" name="IS_NOTA" /></td>
  </tr>
  <tr>
    <td align="right">&nbsp;</td>
    <td><input type="text" name="textfield2" />
      <label>
      <input type="radio" name="radiobutton" value="radiobutton" />
      </label>
      <input type="radio" name="radiobutton" value="radiobutton" /></td>
    <td align="right">币种：</td>
    <td><input type="text" name="CURRENCY" /></td>
  </tr>
  <tr>
    <td align="right">&nbsp;</td>
    <td><input type="text" name="textfield22" />
      <label>
      <input type="radio" name="radiobutton" value="radiobutton" />
      </label>
      <input type="radio" name="radiobutton" value="radiobutton" /></td>
    <td align="right">汇率：</td>
    <td><input type="text" name="EXCHANGE_RATE" /></td>
  </tr>
  <tr>
    <td align="right"><label>合同类型：   
    </label><label> </label></td>
    <td><label>
      <input type="text" name="CON_TYPE" />
    </label>
      <label></label></td>
    <td align="right">首期价款：</td>
    <td><input type="text" name="DOWN_PAYMENT" /></td>
  </tr>
  <tr>
    <td height="26" align="right"><label>合同年份：
    </label><label></label></td>
    <td><label>
      <input type="text" name="CON_DATE" />
    </label>
      <label></label></td>
    <td align="right">付款方式： </td>
    <td><input type="text" name="PAYMENT_METHOD" /></td>
  </tr>
  <tr>
    <td align="right"><label>合同区域：
    </label><label></label></td>
    <td><label>
      <input type="text" name="CON_AREA" />
    </label>
      <label></label></td>
    <td align="right">按揭年数：</td>
    <td><input type="text" name="MORTGAGE_YEARS" /></td>
  </tr>
  <tr>
    <td align="right"><label>预售证号：
    </label><label></label></td>
    <td><label>
      <input type="text" name="PRE_SALE_NO" />
    </label>
      <label></label></td>
    <td align="right">入伙日期：</td>
    <td><input type="text" name="OCCUPATION_DATE" /></td>
  </tr>
  <tr>
    <td align="right"><label>合同面积：
    </label><label></label></td>
    <td><label>
      <input type="text" name="CON_AREAS" />
    </label>
      <label></label></td>
    <td align="right">是否新合同：</td>
    <td><input type="text" name="IS_NEW_CONTRACT" /></td>
  </tr>
  <tr>
    <td align="right"><label>合同单价：
        </label>
    <label></label></td>
    <td><label>
      <input type="text" name="CON_UNIT_PRICE" />
    </label></td>
    <td align="right">是否需要补充合同：</td>
    <td><input type="text" name="IS_SUP_CONTRACT" /></td>
  </tr>
  <tr>
    <td align="right"><label>合同价款：
    </label><label></label></td>
    <td><label>
      <input type="text" name="CON_PRIC" />
    </label>
      <label></label></td>
    <td align="right">是否监证：</td>
    <td><input type="text" name="IS_CER" /></td>
  </tr>
  <tr>
    <td align="right"><label>合同价格中文：
        </label>
    <label></label></td>
    <td><label>
      <input type="text" name="CON_PRICE_CN" />
    </label></td>
    <td align="right">享受购房入户资格：</td>
    <td><input type="text" name="HOME_BUYERS_QUA" /></td>
  </tr>
  <tr>
    <td align="right"><label>转移方式：
        </label>
    <label></label></td>
    <td><label>
      <input type="text" name="SHIFT_MODE" />
    </label></td>
    <td align="right">购房入户证明书号：</td>
    <td><input type="text" name="HOME_BUYERS_CER_NO" /></td>
  </tr>
  <tr>
    <td align="right"><label>购房性质：
    </label><label></label></td>
    <td><label>
      <input type="text" name="HOUSE_BUY_PRO" />
    </label>
      <label></label></td>
    <td align="right">购房入户证明日期：</td>
    <td><input type="text" name="HOME_BUYERS_CER_DATE" /></td>
  </tr>
  <tr>
    <td align="right"><label>合同签定日期：
    </label><label></label></td>
    <td><label>
      <input type="text" name="CON_SIGN_DATE" />
    </label>
      <label></label></td>
    <td align="right">购房入户个数：</td>
    <td><input type="text" name="HOME_BUYERS_NUMBER" /></td>
  </tr>
  <tr>
    <td align="right"><label>是否楼花按揭：    </label>
      <label></label></td>
    <td><input type="text" name="IS_MORTGAGE" /></td>
    <td align="right">内外销：</td>
    <td><input type="text" name="WITHIN_EXPORT" /></td>
  </tr>
  <tr>
    <td colspan="4" align="right">&nbsp;</td>
  </tr>
  <tr>
    <td align="right">注销经办人：</td>
    <td><input type="text" name="CANCELLATION_MANAGERS" /></td>
    <td align="right">注销日期：</td>
    <td><input type="text" name="CANCELLATION_DATE" /></td>
  </tr>
  <tr>
    <td align="right">注销原因</td>
    <td colspan="3">
     
      <textarea name="CANCELLATION_REASON" style="width:599px"></textarea>
       </td>
  </tr>
  <tr>
    <td align="right">&nbsp;</td>
    <td>&nbsp;</td>
    <td align="right">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>
