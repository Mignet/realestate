
<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>
����֪ͨ��
</title>
<!-- <link rel="stylesheet" type="text/css" href="stylesheet.css" title="Style"> -->
<!-- �����ӡ�ؼ� 
      <OBJECT  ID="jatoolsPrinter" CLASSID="CLSID:B43D3361-D075-4BE2-87FE-057188254255" 
         codebase="jatoolsPrinter.cab#version=5,7,0,0"></OBJECT>  
-->         
        <script type="text/javascript">
		     /*  //var proc=this.parent.proc;
		      var proc_id = 1000025915;//proc.procId;
		     //var proc_id=1;
		     //��object�ؼ�ֻ��IE��Ч������ȡ������ʹ��iframe
		      window.onload = (function () { 
		    	  //var url="../print!printAcceptanceNotice.action?proc_id=1217&time="+new Date();
		    	  
		    	  //alert(url);
		    	 // document.frames["printed"].location=url;
		    	 //var str='<object id="pdf1"  classid="clsid:CA8A9780-280D-11CF-A24D-444553540000"    width="100%"    height="580"  border="0"> <param   name="_Version"   value="65539"> <param   name="_ExtentX"   value="20108"> <param   name="_ExtentY"   value="10866"> <param name="_StockProps" value="0"> <param name="SRC"  value="'+url+'"></object> ';
		    	// document.getElementById('page1').innerHTML=str;
		    	// alert(document.getElementById('src').value);
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
		      function print(){
		    	  pdf1.Print();
		      } */
        </script>
      
</head>

<body bgcolor="white">
<!-- 
<input type="button" value="��ӡԤ��..." onClick="doPrint('��ӡԤ��...')"/>
<input type="button" value="��ӡ" onClick="doPrint('��ӡ...')"/>
-->
<!-- <input type="button" value="��ӡ" onClick="return print()"/> -->
 
<div id='page1'  style='width:100%;height:580px;'>
  <iframe scrolling="yes" frameborder="0"  src="../print!printAcceptanceNotice.action?proc_id=1000026187" style="width:800px;height:800px;"></iframe>
<!-- <iframe  id="printed"  name="printed"  width="100%" height="580"></iframe> -->
</div>
</body>
</html>

