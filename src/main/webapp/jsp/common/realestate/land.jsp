<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>������Ϣ</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
         <style type="text/css">
    	body,html{margin:0px; font-family:Arial; font-size:12px; color:#333333;}
		.tip{color:#3CF;}
		.title {text-align: right;}	
		.bg1 {background: none repeat scroll 0 0 #E0ECFF;}	
		.bg2 { background: none repeat scroll 0 0 #F4F4F4;}
		.panel-body {
    background: none repeat scroll 0 0 #F8FAFF;
}
    </style>        
 <%@ include file="/base/taglibs.jsp"%>
  <%@ include file="/base/prefix.jsp"%>
  <script type="text/javascript" src="${ctx}/js/common/realestate/land.js"></script>	
    <script type="text/javascript"> 		
  var ctx = '${ctx}';	 
</script>
</head>
<body class="plui-layout">
<div data-options="region:'center'">  
    <div class="page_con" style="width:800px">
       <form id="form_zdxx"> 
       <!-- �ڵ���Ϣ�� -->
       
       <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">������Ϣ</label>
		</div>  
		<div class="datagrid-wrap panel-body">
		</br>
		
        <table id="tab_land" style="width: 100%;">
				<tr>
					<td class="title bg1" style="width:80px;">�ڵغţ�</td>
					<td class="td_1"><input value="" id="zdh" name="parcel_code" style="width:140px;" class="plui-validatebox input"/></td>
					<td class="title bg1" style="width:80px;">�ڵ����䣺</td>
					<td class="td_1"><input value=""  id="zdzl" name="land_address" style="width:140px;" class="plui-validatebox input" /></td>
				<td class="title bg1" style="width:100px;">���ز�֤�ţ�</td>
					<td class="td_1"><input value="" id="fdczh" name="cer_no" style="width:140px;" class="plui-validatebox input"/></td>
				
				
				</tr>
				<tr>
					<td class="title bg2" style="width:80px;">ʵ����;��</td>
					<td class="td_1"><input  value="" id="sjyt" name="real_usage" style="width:140px;" class="plui-validatebox input"/>
					</td>
					<td class="title bg2" style="width:80px;">�õ����(m<sup>2</sup>)��</td>
					<td class="td_2"><input value="" id="syqmj" name="use_area" style="width:140px;" class="plui-validatebox input"/></td>				
					<td class="title bg2" style="width:80px;">���������(m<sup>2</sup>)��</td>
					<td class="td_1"><input value="" id="jzzmj" name="build_total_area" style="width:140px;" class="plui-validatebox input"/></td>    
				</tr>
				<tr>
					<td class="title bg1" style="width:80px;">�滮��;��</td>
					<td class="td_1"><input  value="" id="ghyt" name="plan_usage" style="width:140px;" class="plui-validatebox input"/>
					</td>
					<td class="title bg1" style="width:80px;">�������ʣ�</td>
					<td class="td_2"><input value="" id="tdxz" name="land_attribute" style="width:140px;" class="plui-validatebox input"/></td>				
					<td class="title bg1" style="width:80px;">���صȼ���</td>
					<td class="td_1"><input value="" id="tddj" name="land_grade" style="width:140px;" class="plui-validatebox input"/></td>    
				</tr>
				<tr>
					<td class="title bg2" style="width:80px;">ʹ�����ޣ�</td>
					<td class="td_1"><input  value="" id="synx" name="use_per" style="width:140px;" class="plui-validatebox input"/>
					</td>
					<td class="title bg2" style="width:100px;">ʹ��Ȩ���ͣ�</td>
					<td class="td_2"><input value="" id="syqlx" name="use_right_type" style="width:140px;" class="plui-validatebox input"/></td>				
					<td class="title bg2" style="width:80px;">��ʼ���ڣ�</td>
					<td class="td_1"><input value="" id="qsrq" name="start_date" style="width:140px;" class="plui-validatebox input"/></td>    
				</tr>
				<tr>
					<td class="title bg1" style="width:80px;">�õ���Ŀ���ƣ�</td>
					<td class="td_1"><input  value="" id="ydxmc" name="XMMC" style="width:140px;" class="plui-validatebox input"/>
					</td>
					<td class="title bg1" style="width:80px;">�ǼǼۿ�(Ԫ)��</td>
					<td class="td_2"><input value="" id="djjk" name="reg_pri" style="width:140px;" class="plui-validatebox input"/></td>				
					<td class="title bg1" style="width:80px;">��ֹ���ڣ�</td>
					<td class="td_1"><input value="" id="zzrq" name="end_date" style="width:140px;" class="plui-validatebox input"/></td>    
				</tr>
				<tr>
				<td class="title bg1" style="width:100px;">�õص�λ���ƣ�</td>
					<td class="td_1" colspan="5"><input  value="" id="yddwmc" name="YDDWMC" style="width:666px;" class="plui-validatebox input"/>
					</td>
				
				</tr>
		</table>
		</br>
			</div>
			</br>
       </form>        
      
    <!-- tab�� -->
    <div id="tt" class="plui-tabs" style="width:500px;height:200px;">  
    <div title="�ڵ�ͼ " style="padding:20px;display:none;" data-options="selected:true" >  
           
    </div>  
    <div title="�ڵ�λ��ͼ " data-options="closable:false" style="overflow:auto;padding:20px;display:none;">  
         
    </div>  
    <div title="����ͼ " data-options="closable:false" style="overflow:auto;padding:20px;display:none;">  
         
    </div>
    </div>
    
</div>      
     
</div>  
</body>
</html>
