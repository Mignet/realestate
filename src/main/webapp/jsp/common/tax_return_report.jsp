<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>税费申报表</title>
<style type="text/css">
<!--
.STYLE1 {
	font-size: 24px;
	font-weight: bold;
}
.STYLE2 {font-size: 24}
input{width:80px; border-left: 0; border-right: 0; border-top: 0; border-bottom: 1px solid #000000;}
-->
</style>
</head>

<body>
	<div style="text-align:center;margin:auto;width:973px">
		<div class="STYLE1" style="text-align:center;margin:auto;width:973px" ><label>深圳市地方税务局房地产交易税费申报表</label></div>
		<div style="width:973px"><label></label>
		  <span class="STYLE2" style="float:left"><label>国土申报编号:</label>
		  
		  <label>2000783423</label>
          </span>&nbsp; 
		  <label>登记类型：</label><label>三级转移</label>
			&nbsp; <label>填报日期：</label><label>2014年2月17日</label>
			&nbsp; 
			<span style="float:right;"><label>单位：</label><label>人民币 元 平方米</label></span>
		</div>
		<div>
		<input type="hidden" id='DEC_ID' name='DEC_ID'/>
		<table width="973"  style="BORDER-COLLAPSE: collapse;" borderColor=#000000    align=center border=1>
  <tr>
    <td colspan="10" align="center">房地产品交易信息</td>
    </tr>
  <tr>
    <td width="97">宗地号</td>
    <td width="93"><form id="form1" name="form1" method="post" action="">
      <input type="text" name="PARCEL_NO" id='PARCEL_NO' />
    </form>    </td>
    <td width="90">所在区域</td>
    <td width="85"><input type="text" name="textfield" /></td>
    <td width="78">所在街道</td>
    <td width="120"><input type="text" name="textfield" /></td>
    <td width="83">土地坐落</td>
    <td colspan="3"><input type="text" name="LAND_LOCATION" id='LAND_LOCATION' /></td>
    </tr>
  <tr>
    <td>房地产证号码</td>
    <td><input type="text" name="textfield" /></td>
    <td>本次交易合同号或法律文书号码</td>
    <td><input type="text" name="textfield" /></td>
    <td>本次权属转移方式</td>
    <td colspan="2"><input type="text" name="textfield" /></td>
    <td width="86">房地产获得方式</td>
    <td colspan="2"><input type="text" name="textfield" /></td>
    </tr>
  <tr>
    <td>房屋ID</td>
    <td><input type="text" name="textfield" /></td>
    <td>楼及栋号</td>
    <td><input type="text" name="textfield" /></td>
    <td>房号</td>
    <td><input type="text" name="textfield" /></td>
    <td>建筑面积（土地面积）</td>
    <td><input type="text" name="textfield" /></td>
    <td width="91">套内面积</td>
    <td width="86"><input type="text" name="textfield" /></td>
  </tr>
  <tr>
    <td>房屋用途</td>
    <td><input type="text" name="textfield" /></td>
    <td>房屋性质</td>
    <td><input type="text" name="textfield" /></td>
    <td>公有住房超出规定面积金额</td>
    <td><input type="text" name="textfield2" />
      <input type="text" name="textfield" /></td>
    <td>容积率是否大于1.0</td>
    <td><input type="text" name="textfield" /></td>
    <td>是否普通住房</td>
    <td><input type="text" name="textfield" /></td>
  </tr>
  <tr>
    <td>合同签订时间或法律文书生效时间</td>
    <td><input type="text" name="textfield" /></td>
    <td>过户申请受理时间</td>
    <td><input type="text" name="textfield" /></td>
    <td>行政区域住房总价标准</td>
    <td><input type="text" name="textfield" /></td>
    <td>成效评估总价</td>
    <td><input type="text" name="textfield" /></td>
    <td>原登记价格（或补交地价）</td>
    <td><input type="text" name="textfield" /></td>
  </tr>
  <tr>
    <td>成效合同总价</td>
    <td><input type="text" name="textfield" /></td>
    <td>成交单价</td>
    <td><input type="text" name="textfield" /></td>
    <td>成效计税复核价格</td>
    <td><input type="text" name="textfield" /></td>
    <td>是否核定计税价格</td>
    <td><input type="text" name="textfield" /></td>
    <td>计税价格</td>
    <td><input type="text" name="textfield" /></td>
  </tr>
</table>

<table style="BORDER-COLLAPSE: collapse;" borderColor=#000000    align=center  width="973px" border="1">
  <tr>
    <td colspan="12" align="center"><strong>交易双方纳税人信息</strong></td>
    </tr>
  <tr>
    <td width="78">交易方类型</td>
    <td width="77">纳税人名称</td>
    <td width="89">纳税人类别</td>
    <td width="67">证件类型</td>
    <td width="66">证件号码</td>
    <td width="73">是否家庭唯一住房</td>
    <td width="115">是否购买满五年</td>
    <td width="74">原购房时间</td>
    <td width="30">份额</td>
    <td width="73">该份额对应购置价</td>
    <td width="73">个人所得税征收方式</td>
    <td width="82">土地增值税征收方式</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>

		</div>
	</div>
</body>
</html>
