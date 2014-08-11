<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<%@ include file="/base/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--错误页面-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" rev="stylesheet" href="css/defaultcss.css" type="text/css" media="all" title="001" />
<link rel="alternate stylesheet" type="text/css" href="css/happycss.css" title="002" />
<script type="text/javascript" src="${ctx}/js/common/notepadskin/ymPrompt.js"></script>
<script  type="text/javascript">     
	function mycloseWindow(){
		  try{
			if(parent.ymPrompt!=null) {		
				parent.showFrame();	
				parent.ymPrompt.close();			 
			}else{  				 
				this.window.opener = null;   
				window.close();
			}      
		  }catch(e){
			  this.window.opener = null; 
			  window.parent.close();
			  //window.close();
		  }
	} 
  function Wa_SetImgAutoSize() 
  { 
   var img=document.all.img1;//获取图片
   var WidthRate=document.body.clientWidth/screen.availWidth;//设置高宽比
   var HeightRate=document.body.clientHeight/screen.availHeight;//设置宽高比
   if(img.readyState!="complete")return false;//确保图片完全加载 
   if(WidthRate>HeightRate){
	   img.width=img.offsetWidth*HeightRate;
	   img.height=img.width*img.offsetHeight/img.offsetWidth;
   }else{	   
	   img.height=img.offsetHeight*WidthRate;
	   img.width=img.height*img.offsetWidth/img.offsetHeight;
   }  
  }    
</script>  
  
<%@ include file="/base/prefix.jsp"%>
<title>操作失败</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" /><style type="text/css">
<!--
body {
	margin-top: 90px;
	margin-left: 0px;
}
-->
</style></head>
<body class="index_du" onload="Wa_SetImgAutoSize();">
<table  border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
	  <td  valign="middle" align="center"><img src="${ctx}/images/common/err.jpg" id="img1" /></td>
	</tr>
	<tr><td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="25" ><font  size="2">　¤ </font> ${operateRtn.title} -  <font color="red">${operateRtn.message }</td>
      </tr>  
      <tr>
        <td height="25" >&nbsp;</td>
      </tr>
      <tr>
        <s:if test="operateRtn.showButtonType==1">
          <s:if test='operateRtn.returnPath == &quot;/&quot;'>
            <td height="25" align="center"><a href="#" onclick="javascript:history.back();"> 
              <input name="back" type="button" class="button_common" value="返 回" /></a></td>
          </s:if>
          <s:else>
            <td align="center"><input name="back" type="button" class="button_common" value="返 回" onclick="window.open('${operateRtn.returnPath }','_self')"/></td>
          </s:else>
        </s:if>
        <s:elseif test="operateRtn.showButtonType==2">
          <td align="center"><input name="back" type="button" class="button_common" value="关  闭" onclick="javascript:mycloseWindow();"/></td>
        </s:elseif>
        <s:elseif test="operateRtn.showButtonType==0">
          <td align="center"><s:if test='operateRtn.returnPath == &quot;/&quot;'> <a href="#" onclick="javascript:history.back();"> <input name="back" type="button" class="button_common" value="返 回" /></a> </s:if>
              <s:else><input name="back" type="button" class="button_common" value="返 回" onclick="window.open('${operateRtn.returnPath }','_self')"/></s:else>
              &nbsp;&nbsp;
           <input name="back" type="button" class="button_common" value="关  闭" onclick="javascript:mycloseWindow();"/></td>
        </s:elseif>
      </tr>
    </table>
    </td>
    </tr>
	</table>
<blockquote>&nbsp;	</blockquote>
</body>
</html>
