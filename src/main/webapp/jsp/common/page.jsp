<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/base/taglibs.jsp"%>
<script language="javascript">
var totalPage = '${pg.totalPage}';
var totalSize = '${pg.totalSize}';
var pageSize ='${pg.pageSize}';
function GoPage(pageno)
{
	form1.pageNo.value=pageno;
	form1.submit();
}

function EnterTo(){	
	
	if(window.event.keyCode==13)
	{   alert();
	  if(parseInt(form1.pageNo.value)==form1.pageNo.value)
	  {

		  if(parseInt($('pageNo').value)>parseInt(totalPage)) 
		  {
			  alert("�������ҳ�������ҳ��������������ҳ�룡");
			  return false;  
		  }	
		  if(parseInt($('pageNo').value)<1) 
		  {			  
			  alert("�������ҳ��С��1������������ҳ�룡");
			  return false;  
		  }		  
		  form1.submit();	     
		  return true;
	  }
	  else
	  {
		  alert("����������");
		  $('pageNo').focus();
		  return false;
	  }	     
	}
	else
	{
		return true;
	}   
}
function checkkey(v){
	 var kc=event.keyCode;   
	 if(kc ==13){//�س���
	    if(v==null ||v==''){
	        return false;
	    }else{
	    	if(parseInt($('pageNo').value)==$('pageNo').value){
	    		if(parseInt($('pageNo').value)>parseInt(totalPage)){
	    			alert("�������ҳ�������ҳ��������������ҳ�룡");
	    			return false;  
	    		}	
	    		if(parseInt($('pageNo').value)<1){			  
	    			alert("�������ҳ��С��1������������ҳ�룡");
	    			return false;  
	    		}	
	    		form1.pageNo.value=	 $('pageNo').value; 
	    		form1.submit();	     
	    		return true;
	    	}else{
	    		$('pageNo').set('value',''); 
	    	    alert("ҳ�����������֣�");
	    	    $('pageNo').focus();
	    	    return false; 
	        }
	      }
 	}else if(kc<48||kc>57){//����ǻس�����0-9  
   	 	$('pageNo').set('value',''); 
        alert("ҳ�����������֣�");
    	$('pageNo').focus();
    	return false; 
    }  
}  
</script>
<table width="99%">
	<tr>
		<td>
		<p align="right">��ǰΪ��${pg.pageNo}ҳ��
		����${pg.totalPage}ҳ��${pg.totalSize}����¼��ÿҳ${pg.pageSize}����¼��<a href="#"
			onclick="GoPage(1)"> ��һҳ</a> <s:if test="%{pg.pageNo<=1}">ǰһҳ</s:if>
		<s:if test="%{pg.pageNo>1}">
			<a href="#" onclick="GoPage(${pg.pageNo-1})"> ǰһҳ </a>
		</s:if> <s:if test="%{pg.pageNo>=pg.totalPage}">��һҳ</s:if> <s:if
			test="%{pg.pageNo < pg.totalPage}">
			<a href="#" onclick="GoPage(${pg.pageNo+1})">��һҳ</a>
		</s:if> <a href="#" onclick="GoPage(${pg.totalPage})">���ҳ </a> <input
			type="hidden" name="tp" value="${pg.totalPage}"> ��ת��<input id="pageNo"
			type="text" size="2" name="pageNo" value="${pg.pageNo}"
			class="input_kind" style="width:30px" onKeyPress="checkkey(this.value);"
			onblur="value=value=='+.'||value=='-.'?value+0:value"></p>
		</td>
	</tr>
</table>
