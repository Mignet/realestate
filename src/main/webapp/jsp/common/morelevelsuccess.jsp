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
  function closeWindow(){
	    //�������ҳ��ĸ�ҳ���жԱ������ݴ���Ļ���ִ�д���
	    try{
	    	if($chk(parent.reservationDataFunction)){
		  		parent.reservationDataFunction($('reservationData').value);
		  	}
	    }catch(e){

	    }
	    try{
		  	opener.reservationDataFunction($('reservationData').value);
	    }catch(e){

	    }
	  	try{
	  		if(parent.ymPrompt!=null) {		
				parent.ymPrompt.close();			 
			} 				 
  			this.window.opener = null;   
  			window.close();
			
	  	}catch(e){
	  		window.close();
	  	}
  }   
  function Wa_SetImgAutoSize() 
  { 
   var img=document.all.img1;//��ȡͼƬ
   var WidthRate=document.getWidth()/screen.availWidth;//���ø߿��
   var HeightRate=document.getHeight()/screen.availHeight;//���ÿ�߱�
   if(img.readyState!="complete")return false;//ȷ��ͼƬ��ȫ���� 
   if(WidthRate>HeightRate){
	   img.width=img.offsetWidth*HeightRate;
	   img.height=img.width*img.offsetHeight/img.offsetWidth;
   }else{	   
	   img.height=img.offsetHeight*WidthRate;
	   img.width=img.height*img.offsetWidth/img.offsetHeight;
   }  
  } 
</script>  
  

	<title>�����ɹ�</title>
	
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" /><style type="text/css">
<!--
body {
	margin-top: 90px;
}
-->
</style></head>
<body class="index_du" onload="Wa_SetImgAutoSize();">
<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
	  <td  valign="middle" align="center" ><img src="${ctx}/images/common/succ.jpg" id="img1" /></td>
	</tr>
	<tr>
	  <td valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">        
        <tr>
          <td height="25" align="center" valign="top"><font  size="2">���� </font>�����ɹ�</td>
        </tr>  
         <tr>
          <td height="25" >&nbsp;</td>
        </tr>              
        <tr>
           <td width="7%" align="center">
         	  <input name="back" type="button" class="button_common" value="�� ��" onclick="javascript:closeWindow();"/>
           </td>
        </tr>
      </table></td>
  </tr>
</table>
<input type="hidden" name="reservationData" id="reservationData" value="${reservationData}"/>
</body>
</html>
