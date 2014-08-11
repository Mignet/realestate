<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>土地合同信息</title>
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
      <label style='font-weight:bold;'>合同属性</label>
    </td>
  </tr>
  </table>
  <table id='contractinfo' width="900" border="0" bgcolor="#999999">
  <tr>
    <td width="115px" align="right">用地项目编号：</td>
    <td width="150px"> <input type="text" name="LP_LU_PROJ_NO" /></td>
	  <td align="right" width="115px">合同编号：</td>
    <td><input type="text" name="CONT_SEQ" /></td>
    <td align="right" width="115px">合同字号：</td>
    <td><input type="text" name="CONTRACT_FLAG" /></td>
  </tr>
  <tr>
    <td align="right">合同序号：</td>
    <td><input type="text" name="CONTRACT_NO" />
    <td align="right">宗地号：</td>
    <td><input type="text" name="PARCEL_NO" />
    <td align="right">出让方式：</td>
    <td>
    <input class="plui-combodict"  code="069" value="" id="CONTRACT_TYPE" name="CONTRACT_TYPE"/>
    <!-- <select id="CONTRACT_TYPE" name="CONTRACT_TYPE" style="width:150px;"> 
					<option  value="069001">协议</option>
					<option  value="069002">招标</option>
					<option  value="069003">拍卖</option>
					<option  value="069004">临时</option>
					<option  value="069006">协议地价</option>
					<option  value="069005">免地价</option>
					<option  value="069007">市场价及商品房</option>
					<option  value="069008">财政挂账</option>
					<option  value="069009">行政划拨</option>
					</select> --></td>
  </tr>
  <tr>
    <td align="right">文件编号：</td>
    <td><input type="text" name="DOC_DOC_NO" />
    <td align="right">红线编号：</td>
    <td><input type="text" name="RL_REDLINE_NO" />
    <td align="right">用地面积：</td>
    <td><input type="text" name="LU_AREA" /></td>
  </tr>
    <tr>
    <td align="right">土地用途：</td>
    <td>
    <input class="plui-combodict"  code="015" value="" id="LU_FUNCTION" name="LU_FUNCTION"/>
    <!-- <select id="LU_FUNCTION" style="width:150px;"> 
					<option  value="015001">住宅用地</option>
					<option  value="015002">工业用地</option>
					<option  value="015003">仓储用地</option>
					<option  value="015004">商业服务业用地</option>
					<option  value="015005">旅游业用地</option>
					<option  value="015006">金融保险业用地</option>
					<option  value="015007">市政公用设施用地</option>
					<option  value="015008">绿带用地</option>
					<option  value="015009">公共建筑用地</option>
					<option  value="015010">文/体/娱用地</option>
					</select> --></td>
    <td align="right">用地单位：</td>
    <td><input type="text" name="textfield2" />
    <td align="right">土地位置：</td>
    <td><input type="text" name="LU_LOCATION" /></td>
  </tr>
  <tr>
    <td align="right">土地性质：</td>
    <td><input type="text" name="textfield2" />
    <td align="right">甲方代表代码：</td>
    <td><input type="text" name="CLIENT_REPRES_NO" />
    <td align="right">甲方代表姓名：</td>
    <td><input type="text" name="CLIENT_REPRES_NAME" /></td>
  </tr>
   <tr>
    <td align="right">使用年限：</td>
    <td><input type="text" name="LU_TERM" />
    <td align="right">起始日期：</td>
    <td><input type="text" name="START_DATE" />
    <td align="right">终止日期：</td>
    <td><input type="text" name="END_DATE" /></td>
  </tr>
  <tr>
    <td align="right">甲方名称：</td>
    <td><input type="text" name="CLIENT_NAME" />
    <td align="right">签定地点：</td>
    <td><input type="text" name="SIGN_PLACE" />
    <td align="right">签定日期：</td>
    <td><input type="text" name="SIGN_DATE" /></td>
  </tr>
   <tr>
    <td align="right">合同定金：</td>
    <td><input type="text" name="CONT_MARGIN" />
    <td align="right">定金币种：</td>
    <td>
    <input class="plui-combodict"  code="057" value="" id="MARGIN_CURR_TYPE" name="MARGIN_CURR_TYPE"/>
    <!-- <select id="MARGIN_CURR_TYPE" style="width:150px;"> 
					<option  value="057001">HKD</option>
					<option  value="057001">RMB</option>
					<option  value="057001">USD</option>
					</select> --></td>
    <td align="right">竣工期限：</td>
    <td><input type="text" name="COMPLETE_DATE" /></td>
  </tr>
  <tr>
    <td align="right">减免出让金：</td>
    <td><input type="text" name="LAND_GRANT_COST" />
    <td align="right">出让金币种：</td>
    <td>
    <input class="plui-combodict"  code="057" value="" id="GRANT_CURRENCY" name="GRANT_CURRENCY"/>
    <!-- <select id="GRANT_CURRENCY" style="width:150px;"> 
					<option  value="057001">HKD</option>
					<option  value="057001">RMB</option>
					<option  value="057001">USD</option>
					</select> --></td>
    <td align="right">减免开发费：</td>
    <td><input type="text" name="LAND_DEVEL_COST" /></td>
    
  </tr>
  <tr>
    <td align="right">开发费币种：</td>
    <td>
    <input class="plui-combodict"  code="057" value="" id="DEVEL_CURRENCY" name="DEVEL_CURRENCY"/>
    <!-- <select id="DEVEL_CURRENCY" style="width:150px;"> 
					<option  value="057001">HKD</option>
					<option  value="057001">RMB</option>
					<option  value="057001">USD</option>
					</select> --></td>
    <td align="right">减免配套费：</td>
    <td><input type="text" name="LAND_ATTACH_COST" />
    <td align="right">配套费币种：</td>
    <td>
    <input class="plui-combodict"  code="057" value="" id="ATTACH_CURRENCY" name="ATTACH_CURRENCY"/>
   <!--  <select id="ATTACH_CURRENCY" style="width:150px;"> 
					<option  value="057001">HKD</option>
					<option  value="057001">RMB</option>
					<option  value="057001">USD</option>
					</select> --></td>
  </tr>
   <tr>
    <td align="right">减免其他收入：</td>
    <td><input type="text" name="OTHER_INCOME" />
    <td align="right">其它收入币种：</td>
    <td>
    <input class="plui-combodict"  code="057" value="" id="OTHER_CURRENCY" name="OTHER_CURRENCY"/>
    <!-- <select id="OTHER_CURRENCY" style="width:150px;"> 
					<option  value="057001">HKD</option>
					<option  value="057001">RMB</option>
					<option  value="057001">USD</option>
					</select> --></td>
  </tr>
     <tr>
    <td align="right">备注：</td>
    <td colspan="5">
	<textarea name="REMARK" rows="5" style="width:650px;"></textarea>
	</td>
  </tr>
</table>
</body>
</html>
