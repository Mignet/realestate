<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>税费申报表</title>
<style>
  input{
  	width:100%;
  }
  table{
  border:0;
  }
  td{
  border:0;
  }
  div{
  	border-width:1px;
  	border-color:#CCCCCC;
  	 border-style:solid;
  }
  body,html{margin:0px; font-family:Arial; font-size:12px; color:#333333;}
  		.tip{color:#3CF;}
  		.title {text-align: right;}	
  		.bg1 {background: none repeat scroll 0 0 #E0ECFF;}	
  		.bg2 { background: none repeat scroll 0 0 #F4F4F4;}
</style>
 <%@ include file="/base/taglibs.jsp"%>
  <%@ include file="/base/prefix.jsp" %>
  <script type="text/javascript">
	//<![CDATA[
	   var ctx='${ctx}';
	//]]
</script>
<script type="text/javascript" src="${ctx}/js/common/tax_return.js"></script>
</head>

<body>
	<div style="margin:auto;width:1019px;" id='divMain'>
		<div>
			
		</div>
		
		<div><strong>登记信息
			</strong>
		  <table width="1014" border="1">
  <tr>
    <td width="76" class="title bg1">登记编号</td>
    <td width="85">
      <input type="text" name="REG_CODE" id="REG_CODE" />
        </td>
    <td width="150" class="title bg1">原房地产证号</td>
    <td width="85">
      <input type="text" name="ORIG_CER_NO" id='ORIG_CER_NO' />
       </td>
    <td width="153" class="title bg1 ">（产权）登记类型</td>
    <td width="104">
      <input type="text" name="REG_TYPE" id="REG_TYPE" />
        </td>
    <td width="132" class="title bg1">（地税）登记类型</td>
    <td width="177">
      <input type="text" name="REG_TYPE" id="REG_TYPE" />
        </td>
  </tr>
  <tr>
    <td class="title bg1">登记方式</td>
    <td>
      <input type="text" name="textfield5" />
       </td>
    <td class="title bg1">（产权）原转移方式</td>
    <td>
      <input type="text" name="textfield6" />
        </td>
    <td class="title bg1">（产权）转移方式</td>
    <td colspan="3">
      <input type="text" name="textfield7" />
        </td>
    </tr>
  <tr>
    <td class="title bg1">登记状态</td>
    <td><form id="form8" name="form8" method="post" action="">
      <input type="text" name="textfield8" />
    </form>    </td>
    <td class="title bg1">（地税）原转移方式</td>
    <td><form id="form9" name="form9" method="post" action="">
      <input type="text" name="textfield9" />
    </form>    </td>
    <td class="title bg1">（地税）转移方式</td>
    <td colspan="3"><form id="form10" name="form10" method="post" action="">
      <input type="text" name="textfield10" />
    </form>    </td>
    </tr>
  <tr>
    <td class="title bg1">受理日期</td>
    <td><form id="form11" name="form11" method="post" action="">
      <input type="text" name="textfield11" />
    </form>    </td>
    <td class="title bg1">购房性质</td>
    <td><form id="form12" name="form12" method="post" action="">
      <input type="text" name="textfield12" />
    </form>    </td>
    <td class="title bg1">合同或文书日期</td>
    <td colspan="3"><form id="form13" name="form13" method="post" action="">
      <input type="text" name="textfield13" />
    </form>    </td>
    </tr>
  <tr>
    <td class="title bg1">个税征收方式</td>
    <td>
      <input type="text" name="INTAX_CHARGE_TYPE" id='INTAX_CHARGE_TYPE' />
        </td>
    <td class="title bg1">土增税征收方式</td>
    <td>
      <input type="text" name="LAND_INCARMENT_TAX_CHARGE_TYPE" id='LAND_INCARMENT_TAX_CHARGE_TYPE' />
        </td>
    <td class="title bg1">合同编号</td>
    <td colspan="3">
      <input type="text" name="CON_NO" id='CON_NO' />
        </td>
    </tr>
</table>
	  </div>
		
		<div><strong>土地或房屋信息
			</strong>
		  <table width="1016" border="1">
  <tr bgcolor="#999999" >
    <td width="79" height="19">宗地号</td>
    <td width="153">楼名称及栋号</td>
    <td width="54">房号</td>
    <td width="128">旧房屋用途</td>
    <td width="104">房屋用途</td>
    <td width="128">旧房屋性质</td>
    <td width="324">房屋性质</td>
  </tr>
  <tr>
    <td> <input type="text" name="PARCEL_NO" id='PARCEL_NO' /></td>
    <td><input type="text" name="BUILDING_NO" id='BUILDING_NO' /></td>
    <td><input type="text" name="UNIT_NO" id='UNIT_NO' /></td>
    <td><input type="text" name="FLATLET_USAGE" id='FLATLET_USAGE' /></td>
    <td><input type="text" name="FLATLET_USAGE" id='FLATLET_USAGE' /></td>
    <td><input type="text" name="HOUSE_KIND" id='HOUSE_KIND' /></td>
    <td><input type="text" name="HOUSE_KIND" id='HOUSE_KIND' /></td>
  </tr>
</table>
<table width="1016" border="1">
  <tr>
    <td width="74" class="title bg1">行政区域</td>
    <td width="86">
      <input type="text" name="textfield17" />
        </td>
    <td width="121" class="title bg1">土地或建筑面积</td>
    <td width="86">
      <input type="text" name="BUILD_AREA" id='BUILD_AREA' />
        </td>
    <td width="71" class="title bg1">套内面积</td>
    <td width="88">
      <input type="text" name="INSIDE_SPACE" id='INSIDE_SPACE'/>
        </td>
    <td width="74" class="title bg1">超出面积</td>
    <td width="86">
      <input type="text" name="textfield20" />
      </td>
    <td width="144" class="title bg1">容积率是否大于1</td>
    <td width="122">
    <!-- 
      <input type="text" name="RJLSFDY1" id='RJLSFDY1' />
     -->
     <select name="RJLSFDY1" id='RJLSFDY1'>
      	<option value="是">是</option>
      	<option value="否">否</option>
      </select>
       </td>
  </tr>
  <tr>
    <td class="title bg1">房屋编号</td>
    <td>
      <input type="text" name="ADV_HOUSE_CODE" id='ADV_HOUSE_CODE' />
    
    </td>
    <td class="title bg1">交易价(人民币)</td>
    <td>
      <input type="text" name="textfield44" />
    
    </td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td class="title bg1">土地位置</td>
    <td colspan="3">
      <input type="text" name="LAND_LOCATION" id='LAND_LOCATION' />
        </td>
    <td class="title bg1">街道</td>
    <td>
      <input type="text" name="LOCATION" id='LOCATION' />
       </td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
		</div>
		
	  <div><strong>转让人信息
		</strong>
	    <table width="1015" border="1">
  <tr bgcolor="#333333">
    <td width="113" bgcolor="#999999">纳税人名称</td>
    <td width="101" bgcolor="#999999">旧纳税人类型</td>
    <td width="113" bgcolor="#999999">纳税人类型</td>
    <td width="85" bgcolor="#999999">旧证件类型</td>
    <td width="113" bgcolor="#999999">证件类型</td>
    <td width="113" bgcolor="#999999">证件号码</td>
    <td width="331" bgcolor="#999999">转让份额</td>
    </tr>
  <tr bgcolor="#CCCCCC">
    <td>
      <input type="text" name="textfield25" />
       </td>
    <td>&nbsp;</td>
    <td>
      <input type="text" name="textfield26" />
        </td>
    <td>&nbsp;</td>
    <td>
      <input type="text" name="textfield27" />
        </td>
    <td>
      <input type="text" name="textfield28" />
        </td>
    <td>
      <input type="text" name="PORTION" />
        </td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    </tr>
</table>
<table width="1016" border="1">
  <tr>
    <td width="132" class="title bg1">是否家庭唯一住房</td>
    <td width="79">
    <!--  
      <input type="text" name="UNIQUE_HOUSE" id='UNIQUE_HOUSE' />
     -->
      <select name="UNIQUE_HOUSE" id='UNIQUE_HOUSE'>
      	<option value="是">是</option>
      	<option value="否">否</option>
      </select>
        </td>
    <td width="105" class="title bg1">原购房时间</td>
    <td width="79">
      <input type="text" name="ORIG_BUY_DATE" id='ORIG_BUY_DATE' />
        </td>
    <td width="168" class="title bg1">该份额对应原购置价</td>
    <td width="105"><form id="form32" name="form32" method="post" action="">
      <input type="text" name="textfield31" />
    </form>    </td>
    <td width="139" class="title bg1">联系电话</td>
    <td width="157">
      <input type="text" name="TRANSFEROR_PHONENUM" id='TRANSFEROR_PHONENUM' />
        </td>
  </tr>
</table>

		
		</div>
		
		<div><strong>受让人信息
		</strong>
		  <table width="1017" border="1">
  <tr bgcolor="#999999">
    <td width="100">纳税人名称</td>
    <td width="120">旧纳税人类型</td>
    <td width="100">纳税人类型</td>
    <td width="100">旧证件类型</td>
    <td width="81">证件类型</td>
    <td width="81">证件号码</td>
    <td width="81">受让份额</td>
    <td width="302">所占份额</td>
  </tr>
  <tr>
    <td>
      <input type="text" name="TRANSFEREE_NAME" ID='TRANSFEREE_NAME' />
       </td>
    <td>
      <input type="text" name="OLD_PAYER_TYPE" ID='OLD_PAYER_TYPE' />
        </td>
    <td>
      <input type="text" name="PAYER_TYPE" ID='PAYER_TYPE' />
        </td>
    <td>
      <input type="text" name="OLD_CER_TYPE" ID='OLD_CER_TYPE' />
        </td>
    <td>
      <input type="text" name="CER_TYPE" id='CER_TYPE' />
        </td>
    <td>
      <input type="text" name="CER_NO" id='CER_NO' />
        </td>
    <td>
      <input type="text" name="TRANSFEREE_SHARE" id='TRANSFEREE_SHARE' />
       </td>
    <td>
      <input type="text" name="TRANSFEREE_SHARE" id='TRANSFEREE_SHARE' />
        </td>
  </tr>
</table>
<table width="1018" border="1">
  <tr>
    <td width="115" class="title bg1">是否家庭唯一住房</td>
    <td width="80">
    <!--  
      <input type="text" name="UNIQUE_HOUSE" id='UNIQUE_HOUSE' />
     -->
      <select name="UNIQUE_HOUSE" id='UNIQUE_HOUSE'>
      	<option value="是">是</option>
      	<option value="否">否</option>
      </select>
        </td>
    <td width="75" class="title bg1">联系电话</td>
    <td width="412">
      <input type="text" name="TRANSFEREE_PHONENUM" id='TRANSFEREE_PHONENUM' />
        </td>
    <td width="302">
	      <input type="button" id="reSendTax" name="submit-re" value="重发地税" style="width:140px;"/>
	      <input type="button" id="sendTax" name="Submit" value="发送地税"  style="width:140px;"/>
     </td>
  </tr>
</table>
		</div>
	</div>
</body>
</html>
