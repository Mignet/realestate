<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--成功页面-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" rev="stylesheet" href="css/defaultcss.css" type="text/css" media="all" title="001" />
<link rel="alternate stylesheet" type="text/css" href="css/happycss.css" title="002" />
<script type="text/javascript" src="${ctx}/js/common/notepadskin/ymPrompt.js"></script>

<script  type="text/javascript">   
function mycloseWindow(servicecode){
	 try{
	  try{
			//下端程序是当该页面作为弹出窗口，关闭时调用父页面的subWindowSuccessClose方法为改变父页面的相关内容
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
  var returnPath = '${operateRtn.returnPath }';
  
  //返回按钮事件
  function goBackFun(){
  	window.open(returnPath ,'_self');
  }
  
  
  function Wa_SetImgAutoSize() 
  { 
   var img=document.all.img1;//获取图片
   var WidthRate=document.getWidth()/screen.availWidth;//设置高宽比
   var HeightRate=document.getHeight()/screen.availHeight;//设置宽高比
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
  

	<title>操作成功</title>
	
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
          <td height="25" align="center" valign="top"><font  size="2">　¤ </font>${operateRtn.title }  -  <font color="red">${operateRtn.message }</font></td>
        </tr>  
         <tr>
          <td height="25" >&nbsp;</td>
        </tr>              
        <tr>
          <s:if test="operateRtn.showButtonType==1">
            <s:if test='operateRtn.returnPath == &quot;/&quot;'>
              <td align="center"><a href="#" onclick="javascript:history.back();"> <input name="back" type="button" class="button_common" value="返 回" /></a></td>
            </s:if>
            <s:else>
              <td width="12%" align="center"> <input name="back" type="button" class="button_common" value="返 回" onclick="window.open('${operateRtn.returnPath }','_self')"/></td>
            </s:else>
          </s:if>
          <s:elseif test="operateRtn.showButtonType==2">
            <td width="7%" align="center"><input name="back" type="button" class="button_common" value="关 闭" onclick="javascript:mycloseWindow('${servicecode}');"/></td>
          </s:elseif>
          <s:else>
            <td width="33%" align="center"><s:if test='operateRtn.returnPath == &quot;/&quot;'> <a href="#" onclick="javascript:history.back();"> <input name="back" type="button" class="button_common" value="返 回" /></a> </s:if>
                <s:else> <input name="back" type="button" class="button_common" value="返  回" onclick="goBackFun();"/></s:else>
             &nbsp;&nbsp;
              <input name="back" type="button" class="button_common" value="关  闭" onclick="mycloseWindow('${servicecode}');"/></td>
          </s:else>
        </tr>
      </table></td>
  </tr>
</table>
</body>
</html>
