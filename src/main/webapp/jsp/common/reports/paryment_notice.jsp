
<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>
�ɿ�֪ͨ��
</title>
<link rel="stylesheet" type="text/css" href="stylesheet.css" title="Style">
<!-- �����ӡ�ؼ� 
      <OBJECT  ID="jatoolsPrinter" CLASSID="CLSID:B43D3361-D075-4BE2-87FE-057188254255" 
         codebase="jatoolsPrinter.cab#version=5,7,0,0"></OBJECT>  
-->         
        <script type="text/javascript">
        
		     // var proc=this.parent.proc;
		      //var proc_id = proc.procId;
		     var proc_id='1000016565';
		     
		      window.onload = (function () { 
		    	  var url="../print!printPaymentNotice.action?proc_id="+proc_id;
		    	  //alert(url);
		    	  document.frames["printed"].location=url;
		    	  //document.getElementById('pdf').src=url;
		    	  //var box = document.getElementById('pdf');
		    	  //str = '<embed  width="620" height="500"  src="'+url+'"></embed>';
		    	 // box.innerHTML = str;
		    	});
		      //ҳ����֤���� 
		      var result ={
		    			result:true,
		    			message:'',
		    			page_name:'����֪ͨ��'
		    	}
		      function validate(){
			    	 return result;
			  }
        </script>
      
</head>

<body bgcolor="white">

<!--  
<input type="button" value="��ӡԤ��..." onClick="doPrint('��ӡԤ��...')"/>
<input type="button" value="��ӡ" onClick="doPrint('��ӡ...')"/>
<input type="button" value="��ӡ" onClick="doPrint('��ӡ')"/>
-->
<div id='page1'  style='width:100%;height:300px;'>

<iframe  id="printed"  name="printed"  width="80%" height="580">
	
</iframe>
</div>
</body>
</html>

