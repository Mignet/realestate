<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>˰���걨��</title>
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
		
		<div><strong>�Ǽ���Ϣ
			</strong>
		  <table width="1014" border="1">
  <tr>
    <td width="76" class="title bg1">�ǼǱ��</td>
    <td width="85">
      <input type="text" name="REG_CODE" id="REG_CODE" />
        </td>
    <td width="150" class="title bg1">ԭ���ز�֤��</td>
    <td width="85">
      <input type="text" name="ORIG_CER_NO" id='ORIG_CER_NO' />
       </td>
    <td width="153" class="title bg1 ">����Ȩ���Ǽ�����</td>
    <td width="104">
      <input type="text" name="REG_TYPE" id="REG_TYPE" />
        </td>
    <td width="132" class="title bg1">����˰���Ǽ�����</td>
    <td width="177">
      <input type="text" name="REG_TYPE" id="REG_TYPE" />
        </td>
  </tr>
  <tr>
    <td class="title bg1">�ǼǷ�ʽ</td>
    <td>
      <input type="text" name="textfield5" />
       </td>
    <td class="title bg1">����Ȩ��ԭת�Ʒ�ʽ</td>
    <td>
      <input type="text" name="textfield6" />
        </td>
    <td class="title bg1">����Ȩ��ת�Ʒ�ʽ</td>
    <td colspan="3">
      <input type="text" name="textfield7" />
        </td>
    </tr>
  <tr>
    <td class="title bg1">�Ǽ�״̬</td>
    <td><form id="form8" name="form8" method="post" action="">
      <input type="text" name="textfield8" />
    </form>    </td>
    <td class="title bg1">����˰��ԭת�Ʒ�ʽ</td>
    <td><form id="form9" name="form9" method="post" action="">
      <input type="text" name="textfield9" />
    </form>    </td>
    <td class="title bg1">����˰��ת�Ʒ�ʽ</td>
    <td colspan="3"><form id="form10" name="form10" method="post" action="">
      <input type="text" name="textfield10" />
    </form>    </td>
    </tr>
  <tr>
    <td class="title bg1">��������</td>
    <td><form id="form11" name="form11" method="post" action="">
      <input type="text" name="textfield11" />
    </form>    </td>
    <td class="title bg1">��������</td>
    <td><form id="form12" name="form12" method="post" action="">
      <input type="text" name="textfield12" />
    </form>    </td>
    <td class="title bg1">��ͬ����������</td>
    <td colspan="3"><form id="form13" name="form13" method="post" action="">
      <input type="text" name="textfield13" />
    </form>    </td>
    </tr>
  <tr>
    <td class="title bg1">��˰���շ�ʽ</td>
    <td>
      <input type="text" name="INTAX_CHARGE_TYPE" id='INTAX_CHARGE_TYPE' />
        </td>
    <td class="title bg1">����˰���շ�ʽ</td>
    <td>
      <input type="text" name="LAND_INCARMENT_TAX_CHARGE_TYPE" id='LAND_INCARMENT_TAX_CHARGE_TYPE' />
        </td>
    <td class="title bg1">��ͬ���</td>
    <td colspan="3">
      <input type="text" name="CON_NO" id='CON_NO' />
        </td>
    </tr>
</table>
	  </div>
		
		<div><strong>���ػ�����Ϣ
			</strong>
		  <table width="1016" border="1">
  <tr bgcolor="#999999" >
    <td width="79" height="19">�ڵغ�</td>
    <td width="153">¥���Ƽ�����</td>
    <td width="54">����</td>
    <td width="128">�ɷ�����;</td>
    <td width="104">������;</td>
    <td width="128">�ɷ�������</td>
    <td width="324">��������</td>
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
    <td width="74" class="title bg1">��������</td>
    <td width="86">
      <input type="text" name="textfield17" />
        </td>
    <td width="121" class="title bg1">���ػ������</td>
    <td width="86">
      <input type="text" name="BUILD_AREA" id='BUILD_AREA' />
        </td>
    <td width="71" class="title bg1">�������</td>
    <td width="88">
      <input type="text" name="INSIDE_SPACE" id='INSIDE_SPACE'/>
        </td>
    <td width="74" class="title bg1">�������</td>
    <td width="86">
      <input type="text" name="textfield20" />
      </td>
    <td width="144" class="title bg1">�ݻ����Ƿ����1</td>
    <td width="122">
    <!-- 
      <input type="text" name="RJLSFDY1" id='RJLSFDY1' />
     -->
     <select name="RJLSFDY1" id='RJLSFDY1'>
      	<option value="��">��</option>
      	<option value="��">��</option>
      </select>
       </td>
  </tr>
  <tr>
    <td class="title bg1">���ݱ��</td>
    <td>
      <input type="text" name="ADV_HOUSE_CODE" id='ADV_HOUSE_CODE' />
    
    </td>
    <td class="title bg1">���׼�(�����)</td>
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
    <td class="title bg1">����λ��</td>
    <td colspan="3">
      <input type="text" name="LAND_LOCATION" id='LAND_LOCATION' />
        </td>
    <td class="title bg1">�ֵ�</td>
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
		
	  <div><strong>ת������Ϣ
		</strong>
	    <table width="1015" border="1">
  <tr bgcolor="#333333">
    <td width="113" bgcolor="#999999">��˰������</td>
    <td width="101" bgcolor="#999999">����˰������</td>
    <td width="113" bgcolor="#999999">��˰������</td>
    <td width="85" bgcolor="#999999">��֤������</td>
    <td width="113" bgcolor="#999999">֤������</td>
    <td width="113" bgcolor="#999999">֤������</td>
    <td width="331" bgcolor="#999999">ת�÷ݶ�</td>
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
    <td width="132" class="title bg1">�Ƿ��ͥΨһס��</td>
    <td width="79">
    <!--  
      <input type="text" name="UNIQUE_HOUSE" id='UNIQUE_HOUSE' />
     -->
      <select name="UNIQUE_HOUSE" id='UNIQUE_HOUSE'>
      	<option value="��">��</option>
      	<option value="��">��</option>
      </select>
        </td>
    <td width="105" class="title bg1">ԭ����ʱ��</td>
    <td width="79">
      <input type="text" name="ORIG_BUY_DATE" id='ORIG_BUY_DATE' />
        </td>
    <td width="168" class="title bg1">�÷ݶ��Ӧԭ���ü�</td>
    <td width="105"><form id="form32" name="form32" method="post" action="">
      <input type="text" name="textfield31" />
    </form>    </td>
    <td width="139" class="title bg1">��ϵ�绰</td>
    <td width="157">
      <input type="text" name="TRANSFEROR_PHONENUM" id='TRANSFEROR_PHONENUM' />
        </td>
  </tr>
</table>

		
		</div>
		
		<div><strong>��������Ϣ
		</strong>
		  <table width="1017" border="1">
  <tr bgcolor="#999999">
    <td width="100">��˰������</td>
    <td width="120">����˰������</td>
    <td width="100">��˰������</td>
    <td width="100">��֤������</td>
    <td width="81">֤������</td>
    <td width="81">֤������</td>
    <td width="81">���÷ݶ�</td>
    <td width="302">��ռ�ݶ�</td>
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
    <td width="115" class="title bg1">�Ƿ��ͥΨһס��</td>
    <td width="80">
    <!--  
      <input type="text" name="UNIQUE_HOUSE" id='UNIQUE_HOUSE' />
     -->
      <select name="UNIQUE_HOUSE" id='UNIQUE_HOUSE'>
      	<option value="��">��</option>
      	<option value="��">��</option>
      </select>
        </td>
    <td width="75" class="title bg1">��ϵ�绰</td>
    <td width="412">
      <input type="text" name="TRANSFEREE_PHONENUM" id='TRANSFEREE_PHONENUM' />
        </td>
    <td width="302">
	      <input type="button" id="reSendTax" name="submit-re" value="�ط���˰" style="width:140px;"/>
	      <input type="button" id="sendTax" name="Submit" value="���͵�˰"  style="width:140px;"/>
     </td>
  </tr>
</table>
		</div>
	</div>
</body>
</html>
