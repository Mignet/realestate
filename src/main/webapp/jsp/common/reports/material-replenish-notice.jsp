
<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
 	<%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
	<title>�Ӽ����ϱ�</title>
<!-- �����ӡ�ؼ� 
      <OBJECT  ID="jatoolsPrinter" CLASSID="CLSID:B43D3361-D075-4BE2-87FE-057188254255" 
         codebase="jatoolsPrinter.cab#version=5,7,0,0"></OBJECT>  
-->    
       <script type="text/javascript">
       var result ={
   			result:true,
   			message:'',
   			page_name:'��������֪ͨ��'
   		}
		     
				
		      //var proc_id='1000016565';      //����
		  /*     window.onload = (function () { 
		    	  var url="../print!printMatRecCre.action?proc_id="+proc_id;
		    	  //alert(url);
		    	  document.getElementById('printed').src=url;
		    	}); */
		      function validate(){
			    	 return result;
			  }
        </script>
</head>

<body bgcolor="white">
<!--  
<div class="btnDiv">
	<input type="button" value="��ӡԤ��..." onClick="doPrint('��ӡԤ��...')"/>
	<input type="button" value="��ӡ" onClick="doPrint('��ӡ...')"/>
	<input type="button" value="��ӡ" onClick="doPrint('��ӡ')"/>
</div>
-->
<script type="text/javascript">
	var proc=this.parent.proc;
	var proc_id = proc.procId;
        document.write('<iframe src="../print!printCorrectionNotice.action?proc_id='+proc_id+'" id="printed"   name="printed"   width="100%"    height="580px" ></iframe>');
</script>
</body>
</html>

