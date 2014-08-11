<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--�ɹ�ҳ��-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" rev="stylesheet" href="css/defaultcss.css" type="text/css" media="all" title="001" />
<link rel="alternate stylesheet" type="text/css" href="css/happycss.css" title="002" />
<script type="text/javascript" src="${ctx}/js/common/notepadskin/ymPrompt.js"></script>

<script  type="text/javascript">       
  var printRtn = "${printRtn.showButtonType}";
  var ctx = "${ctx}";
  var checkIsCallFlag = "${checkIsCallFlag}";
  var isClickFlag = false;
  
  function printvo(type,isprint){
	var url = ctx+"/print/print.action?isprint=" + isprint + "&printType=" + type;
	$("printFram").src=url;
	if(!isprint){
		$("printFram").style.display="";
	}
  }  

  //���ô�ӡ��ť
  function setPrintButton(){
	var printTypeAndNameMap = null;
	var str = "";
	var buttonNum = 2;
	var buttonName = "";
	var buttonClassName = "button_2";
	if($('printTypeAndNameMap').value != ""){
		printTypeAndNameMap = JSON.decode($('printTypeAndNameMap').value);
		$('buttonTd').innerHTML = "��ӡ��";
		for(var printType in printTypeAndNameMap){
			buttonName = printTypeAndNameMap[printType].replace('���ֵ���ҵ��' , '').replace('���ֵ���' , '')
			.replace('���ҵ��' , '').replace('���' , '').replace('�豸' , '').replace('�ֽ�' , '').replace('Ӫҵ��' , '')
			.replace('����' , '');
			if(printType.test(/\D/)){
				buttonName = "Ѻ��Ʊ";
			}
			if(buttonName.length > 4){
				buttonClassName = "button_long";
			}else{
				buttonClassName = "button_2";
			}
			
			
			str = "&nbsp;<input type='button' class='" + buttonClassName + "' value = '" 
			+ buttonName + "' onclick='printvo(\"" + printType + "\" , true)'/>"
			$('buttonTd').innerHTML += str;
			if(buttonNum % 6 == 0){
				$('buttonTd').innerHTML += "</br>";
			}
			buttonNum += 1;
		}
	}
  }
  
  //ɨ�赯��ҳ��
  function scanOpetate(){
		var url = ctx + "/resourcemanage/scan/scan.action?usercode=" + $('usercode').value + "&databaseurl=" + $('databaseurl').value	
		+ "&operatorcode=" + $('operatorcode').value + "&servicecode=" + $('servicecode').value 
		+ "&orderid=" + $('orderid').value + "&orausercode=" + $('orausercode').value + "&orapassword=" + $('orapassword').value ;
		showModalDialogs(url , 780 , 500);
  }
  
  window.addEvent("domready" , function(){
	//���÷��ؼ��͹رռ�
	if(printRtn == "1"){
		$('closeButton').style.display = 'none';
	}else if(printRtn == "2"){
		$('returnButton').style.display = 'none';
	}else if(printRtn == "0"){
		
	}else{
		$('closeButton').style.display = 'none';
		$('returnButton').style.display = 'none';
	}
	if(checkIsCallFlag != "true"){//�������Ĳ���ӡ�κε���
		setPrintButton();
	}
	
  });
  function _gurl(url){
	  isClickFlag = true;
	  try{
		parent.window.location.href=ctx+url;
	  }catch(e){
		  window.location.href=ctx+url;
		 }
	}
	
	
 function closeAndReturnWindowFun(buttonId){
 	isClickFlag = true;
 	if(buttonId == "returnButton"){
 		window.open('${printRtn.returnPath }','_self');
 	}else {
 		mycloseWindow($('servicecode').value);
 	}
 }
 
 function onUnloadFun(){
 	if(isClickFlag == false){
 		closeWindow($('servicecode').value);
 	}
 
 }

 function mycloseWindow(servicecode){
		 try{
		  try{
				//�¶˳����ǵ���ҳ����Ϊ�������ڣ��ر�ʱ���ø�ҳ���subWindowSuccessClose����Ϊ�ı丸ҳ����������
				
				if($chk(opener)){
					if($chk(opener.subWindowSuccessClose)){
						opener.subWindowSuccessClose();
					}
				}  
				if($chk(parent.subWindowSuccessClose)){
					parent.subWindowSuccessClose();
				} 
			}catch(e){ }
			if(parent.ymPrompt!=null) {		
				 try{
					 parent.showFrame();	
				 }catch(e){ }
				parent.ymPrompt.close();			 
			} 				 
			this.window.opener = null;   
			window.parent.close();
			//window.close();
			try{
				if(servicecode == '00001009'){
					parent.window.close();	
				}
			 	
			}catch(e){ }
		  }catch(e){
			  this.window.opener = null; 
			  window.close();
		  }
	}

</script>  
	<title>�����ɹ�</title>
	
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" /><style type="text/css">
</style></head>
<body class="index_du" onUnload="onUnloadFun();">
<input id="printTypeAndNameMap" type="hidden" value='${printTypeAndNameMap}' />
<input id="databaseurl" type="hidden" value='${databaseurl}'/>
<input id="usercode" type="hidden" value='${usercode}' />
<input id="operatorcode" type="hidden" value='${operatorcode}' />
<input id="servicecode" type="hidden" value='${servicecode}' />
<input id="orderid" type="hidden" value='${orderid}' />
<input id="orausercode" type="hidden" value='${orausercode}' />
<input id="orapassword" type="hidden" value='${orapassword}' />
<table width="577" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
	  <td height="246" valign="middle" background="${ctx}/images/common/succ.jpg" style="background-repeat:no-repeat">&nbsp;</td>
	</tr>
	<tr>
	  <td valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">        
        <tr>
          <td height="25" colspan="2" align="center" valign="top">
          	<font  size="2">���� </font>${printRtn.title }  -  <font color="red">${printRtn.message }</font>
          	<a href="#"  onclick="scanOpetate();">�� ɨ��</a>
          </td>
        </tr>  
         <tr>
          <td height="25" colspan="2" >&nbsp;</td>
        </tr>              
        <tr>
        <td id="buttonTd" align="right" nowrap="nowrap" >
          </td>
          <td align="right"> 
             <input type="button" id="closeButton" class="button_2" value="�� ��"
              onclick="closeAndReturnWindowFun();"/>
             <input type="button" id="returnButton" class="button_2" value="�� ��" 
             onclick="closeAndReturnWindowFun(this.id);"/>
          </td>
        </tr>
          <tr>
          <td colspan="2" >&nbsp;</td>
        </tr>     
        <tr>
        <td colspan="2" align="center"><font color="green">������޷���ȷ��ӡ����<a href="${ctx}/base/print.exe">�������</a>��ӡ�ؼ�����װ��</font></td>
        </tr>
      </table></td>
  </tr>
</table>
<iframe id="printFram"  src="#" style="display:none" width="100%" height="200"></iframe>
</body>
</html>
