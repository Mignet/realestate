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
			  alert("您输入的页码大于总页数，请重新输入页码！");
			  return false;  
		  }	
		  if(parseInt($('pageNo').value)<1) 
		  {			  
			  alert("您输入的页码小于1，请重新输入页码！");
			  return false;  
		  }		  
		  form1.submit();	     
		  return true;
	  }
	  else
	  {
		  alert("请输入数字");
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
	 if(kc ==13){//回车键
	    if(v==null ||v==''){
	        return false;
	    }else{
	    	if(parseInt($('pageNo').value)==$('pageNo').value){
	    		if(parseInt($('pageNo').value)>parseInt(totalPage)){
	    			alert("您输入的页码大于总页数，请重新输入页码！");
	    			return false;  
	    		}	
	    		if(parseInt($('pageNo').value)<1){			  
	    			alert("您输入的页码小于1，请重新输入页码！");
	    			return false;  
	    		}	
	    		form1.pageNo.value=	 $('pageNo').value; 
	    		form1.submit();	     
	    		return true;
	    	}else{
	    		$('pageNo').set('value',''); 
	    	    alert("页码请输入数字！");
	    	    $('pageNo').focus();
	    	    return false; 
	        }
	      }
 	}else if(kc<48||kc>57){//输入非回车，非0-9  
   	 	$('pageNo').set('value',''); 
        alert("页码请输入数字！");
    	$('pageNo').focus();
    	return false; 
    }  
}  
</script>
<table width="99%">
	<tr>
		<td>
		<p align="right">当前为第${pg.pageNo}页，
		共有${pg.totalPage}页，${pg.totalSize}条记录，每页${pg.pageSize}条记录，<a href="#"
			onclick="GoPage(1)"> 第一页</a> <s:if test="%{pg.pageNo<=1}">前一页</s:if>
		<s:if test="%{pg.pageNo>1}">
			<a href="#" onclick="GoPage(${pg.pageNo-1})"> 前一页 </a>
		</s:if> <s:if test="%{pg.pageNo>=pg.totalPage}">下一页</s:if> <s:if
			test="%{pg.pageNo < pg.totalPage}">
			<a href="#" onclick="GoPage(${pg.pageNo+1})">下一页</a>
		</s:if> <a href="#" onclick="GoPage(${pg.totalPage})">最后页 </a> <input
			type="hidden" name="tp" value="${pg.totalPage}"> 跳转到<input id="pageNo"
			type="text" size="2" name="pageNo" value="${pg.pageNo}"
			class="input_kind" style="width:30px" onKeyPress="checkkey(this.value);"
			onblur="value=value=='+.'||value=='-.'?value+0:value"></p>
		</td>
	</tr>
</table>
