<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <title>�����в�����Ȩ�Ǽ�ϵͳ</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <script type="text/javascript">
     var ctx = '${ctx}';</script>
    <script type="text/javascript" src="${ctx}/js/workflow/matterportal.js">
    </script>
    <link href="${ctx}/js/portal/axure_rp_page.css" type="text/css"
	rel="stylesheet">
<link href="${ctx}/js/portal/axurerp_pagespecificstyles.css" type="text/css" rel="stylesheet">
<style>
html,body {height:100%; width:100%; margin:0; padding:0;overflow:auto;}
span{color:#154a75;font-family:΢���ź�;font-size:10pt;}
a{color:#154a75;font-family:΢���ź�;font-size:10pt;}
a:link{color:#154a75;font-weight:400;text-decoration:none}
a:hover{color:#66f;font-weight:400;text-decoration:underline}
ul{margin:0 0 0 0px; padding:0;list-style:none;}
li{margin:0; padding:0;}
</style>
    
    <style type="text/css">
        .td {
        border-left:1px solid;
        border-top:1px solid;
        border-bottom:1px solid;
        border-left-color:black;
        border-right-color:black;
        border-top-color:black;
        border-bottom-color:black;
        width:33%;
}
.buttn{
  color: #444;
  background-image: url('images/linkbutton_bg.png');
  background-repeat: no-repeat;
  -moz-border-radius: 5px 5px 5px 5px;
  -webkit-border-radius: 5px 5px 5px 5px;
  border-radius: 5px 5px 5px 5px;
  }

.div
{
	position:absolute;
	font-family: ����;
	 font-size: 12px; 
	 font-weight: bold; 
	 font-style: normal; 
	 text-decoration: none; 
	 color: #333333;
	 text-align: center;
}
.ul{
	margin:10px 0px 0px 0px; 
	padding:0; 
	list-style: none;
	float:left;
	li{float:left; margin-left:5px;}
	a{display:block; float:left; padding:5px; line-height:2.1em; text-decoration:none; color:#FFFFFF;}
	a:hover{color:#000000;}

}
.ttf{
font-family: ����; font-size: 14px; font-weight: bold; font-style: normal; text-decoration: none; color: #333333;
}
    </style>
</head>
<body width="100%">
	<div width="100%" height="300px" id="matter">
	<div style="display:block;"><div class="ttf">��������</div>
	<table >
		<tr><td class="ttf">ҵ�������ţ�</td>
		<td><input value="" id="djbh" name="djbh" style="width: 150px;" class="plui-validatebox input reg"/></td>
		<td><input type="button" value="����"/></td></tr>
	</table>
	<table>
		<tr><td class="ttf">����ҵ�����</td>
		<td><a href="#" onclick="openTab('��������Ȩ��ʼ�Ǽ�')">��������Ȩ��ʼ�Ǽ�</a>&nbsp&nbsp&nbsp</td>
		<td><a href="#" onclick="openTab('������Ʒ��ת�ƵǼ�')">������Ʒ��ת�ƵǼ�</a>&nbsp&nbsp&nbsp</td>
		<td><a href="#" onclick="openTab('���ز���¥��Ѻ�Ǽ�')">���ز���¥��Ѻ�Ǽ�</a>&nbsp&nbsp&nbsp</td></tr>
	</table>
	</div>
	<br/>
	<div class="ttf">����</div>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	<div id="u19_container" class="u19_container">
				<div id="u20" class="u20">
					<div id="u20_rtf">
						<p style="text-align: center;">
							<span
								style="font-family: ����; font-size: 12px; font-weight: bold; font-style: normal; text-decoration: none; color: #333333;">ʹ��Ȩ/����Ȩ</span>
						</p>
					</div>
				</div>
			</div>
	<table width="100%" >
		<tr border="0" style=" border:1px solid #000000;" cellpadding="0" cellspacing="1"> 
		<td colspan="2"   class="td"><!-- <div class="u4" >ʹ��Ȩ/����Ȩ</div> -->
		
			<img id="u19" src="${ctx}/js/portal/transparent.gif" class="u19">
		 <div>
		<table width="100%">
		<tr id="house_all">
		<td><ul id="house_all1"></ul></td>
		<td><ul id="house_all2"></ul></td>
		</tr>
		</table></div></td>
		<td id="house_hexiang" colspan="2" class="td"><!-- <div>����Ȩ</div> -->
		
		<div id="u21_container" class="u21_container">
				<div id="u22" class="u22">
					<div id="u22_rtf">
						<p style="text-align: center;">
							<span
								style="font-family: ����; font-size: 12px; font-weight: bold; font-style: normal; text-decoration: none; color: #333333;">����Ȩ</span>
						</p>
					</div>
				</div>
			</div>
		<div>
		<table width="100%">
		<tr>
		<td><ul id="house_hexiang1"></ul></td>
		<td><ul id="house_hexiang2"></ul></td>
		</tr>
		</table></div></td>
		<td id="house_other"  colspan="2" class="td"><!-- <div>����</div> -->
		<div id="u23_container" class="u23_container">
				<div id="u24" class="u24">
					<div id="u24_rtf">
						<p style="text-align: center;">
							<span
								style="font-family: ����; font-size: 12px; font-weight: bold; font-style: normal; text-decoration: none; color: #333333;">����</span>
						</p>
					</div>
				</div>
			</div>
		<div>
		<table  width="100%">
		<tr>
		<td><ul id="house_other1"></ul></td>
		<td><ul id="house_other2"></ul></td>
		</tr>
		</table></div></td>
		</tr>
	</table> 
	<div id="land" class="ttf">����</div>
	<table width="100%" >
		<tr id="land" border="0" style=" border:1px solid #000000;" cellpadding="0" cellspacing="1"> 
		<td id="land_all" align=center colspan="2" class="td"><!-- <div >ʹ��Ȩ/����Ȩ</div> -->
		<table  width="100%">
		<tr id="land_all">
		<td><ul id="land_all1"></ul></td>
		<td><ul id="land_all2"></ul></td>
		</tr>
		</table></div></td>
		<td id="land_hexiang" colspan="2" class="td"><!-- <div>����Ȩ</div> -->
		<div>
		<table width="100%">
		<tr>
		<td><ul id="land_hexiang1"></ul></td>
		<td><ul id="land_hexiang2"></ul></td>
		</tr>
		</table></div></td>
		<td id="land_other"colspan="2" class="td"><!-- <div>����</div> -->
		<table width="100%">
		<tr>
		<td><ul id="land_other1"></ul></td>
		<td><ul id="land_other2"></ul></td>
		</tr>
		</table></div></td>
		</tr>
		</td>
	</table>
	<div class="ttf">����</div>
	<table width="100%" >
		<tr border="0" style=" border:1px solid #000000;" cellpadding="0" cellspacing="1"> 
		<td  colspan="2"   class="td"><!-- <div >ʹ��Ȩ/����Ȩ</div> -->
		 <div>
		<table  width="100%">
		<tr>
		<td><ul id="seas_all1"></ul></td>
		<td><ul id="seas_all2"></ul></td>
		</tr>
		</table></div></td>
		<td colspan="2" class="td"><!-- <div >����Ȩ</div> -->
		 <div>
		<table  width="100%">
		<tr>
		<td><ul id="seas_hexiang1"></ul></td>
		<td><ul id="seas_hexiang2"></ul></td>
		</tr>
		</table></div></td>
		<td colspan="2" class="td"><!-- <div >����</div> -->
		 <div>
		<table  width="100%">
		<tr>
		<td><ul id="seas_other1"></ul></td>
		<td><ul id="seas_other2"></ul></td>
		</tr>
		</table></div></td>
		</tr>
	</table>
   <div id="holt" class="ttf">�ֵ�</div>
   <table width="100%" >
		<tr border="0" style=" border:1px solid #000000;" cellpadding="0" cellspacing="1"> 
		<td  colspan="2"   class="td"><!-- <div >ʹ��Ȩ/����Ȩ</div> -->
		 <div>
		<table  width="100%">
		<tr>
		<td><ul id="holt_all1"></ul></td>
		<td><ul id="holt_all2"></ul></td>
		</tr>
		</table></div></td>
		<td colspan="2" class="td"><!-- <div >����Ȩ</div> -->
		 <div>
		<table  width="100%">
		<tr>
		<td><ul id="holt_hexiang1"></ul></td>
		<td><ul id="holt_hexiang2"></ul></td>
		</tr>
		</table></div></td>
		<td colspan="2" class="td"><!-- <div >����</div> -->
		 <div>
		<table  width="100%">
		<tr>
		<td><ul id="holt_other1"></ul></td>
		<td><ul id="holt_other2"></ul></td>
		</tr>
		</table></div></td>
		</tr>
	</table>
   <div id="lawn" class="ttf">�ݵ�</div>
   <table width="100%" >
		<tr border="0" style=" border:1px solid #000000;" cellpadding="0" cellspacing="1"> 
		<td  colspan="2" class="td"><!-- <div >ʹ��Ȩ/����Ȩ</div> -->
		 <div>
		<table  width="100%">
		<tr>
		<td><ul id="lawn_all1"></ul></td>
		<td><ul id="lawn_all2"></ul></td>
		</tr>
		</table></div></td>
		<td colspan="2" class="td"><!-- <div >����Ȩ</div> -->
		 <div>
		<table  width="100%">
		<tr>
		<td><ul id="lawn_hexiang1"></ul></td>
		<td><ul id="lawn_hexiang2"></ul></td>
		</tr>
		</table></div></td>
		<td colspan="2" class="td"><!-- <div >����</div> -->
		 <div>
		<table  width="100%">
		<tr>
		<td><ul id="lawn_other1"></ul></td>
		<td><ul id="lawn_other2"></ul></td>
		</tr>
		</table></div></td>
		</tr>
	</table>
	</div>
	</body>
</html>