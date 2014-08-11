<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>土地信息</title>
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
       <!-- 宗地信息表 -->
       
       <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">土地信息</label>
		</div>  
		<div class="datagrid-wrap panel-body">
		</br>
		
        <table id="tab_land" style="width: 100%;">
				<tr>
					<td class="title bg1" style="width:80px;">宗地号：</td>
					<td class="td_1"><input value="" id="zdh" name="parcel_code" style="width:140px;" class="plui-validatebox input"/></td>
					<td class="title bg1" style="width:80px;">宗地坐落：</td>
					<td class="td_1"><input value=""  id="zdzl" name="land_address" style="width:140px;" class="plui-validatebox input" /></td>
				<td class="title bg1" style="width:100px;">房地产证号：</td>
					<td class="td_1"><input value="" id="fdczh" name="cer_no" style="width:140px;" class="plui-validatebox input"/></td>
				
				
				</tr>
				<tr>
					<td class="title bg2" style="width:80px;">实际用途：</td>
					<td class="td_1"><input  value="" id="sjyt" name="real_usage" style="width:140px;" class="plui-validatebox input"/>
					</td>
					<td class="title bg2" style="width:80px;">用地面积(m<sup>2</sup>)：</td>
					<td class="td_2"><input value="" id="syqmj" name="use_area" style="width:140px;" class="plui-validatebox input"/></td>				
					<td class="title bg2" style="width:80px;">建筑总面积(m<sup>2</sup>)：</td>
					<td class="td_1"><input value="" id="jzzmj" name="build_total_area" style="width:140px;" class="plui-validatebox input"/></td>    
				</tr>
				<tr>
					<td class="title bg1" style="width:80px;">规划用途：</td>
					<td class="td_1"><input  value="" id="ghyt" name="plan_usage" style="width:140px;" class="plui-validatebox input"/>
					</td>
					<td class="title bg1" style="width:80px;">土地性质：</td>
					<td class="td_2"><input value="" id="tdxz" name="land_attribute" style="width:140px;" class="plui-validatebox input"/></td>				
					<td class="title bg1" style="width:80px;">土地等级：</td>
					<td class="td_1"><input value="" id="tddj" name="land_grade" style="width:140px;" class="plui-validatebox input"/></td>    
				</tr>
				<tr>
					<td class="title bg2" style="width:80px;">使用年限：</td>
					<td class="td_1"><input  value="" id="synx" name="use_per" style="width:140px;" class="plui-validatebox input"/>
					</td>
					<td class="title bg2" style="width:100px;">使用权类型：</td>
					<td class="td_2"><input value="" id="syqlx" name="use_right_type" style="width:140px;" class="plui-validatebox input"/></td>				
					<td class="title bg2" style="width:80px;">起始日期：</td>
					<td class="td_1"><input value="" id="qsrq" name="start_date" style="width:140px;" class="plui-validatebox input"/></td>    
				</tr>
				<tr>
					<td class="title bg1" style="width:80px;">用地项目名称：</td>
					<td class="td_1"><input  value="" id="ydxmc" name="XMMC" style="width:140px;" class="plui-validatebox input"/>
					</td>
					<td class="title bg1" style="width:80px;">登记价款(元)：</td>
					<td class="td_2"><input value="" id="djjk" name="reg_pri" style="width:140px;" class="plui-validatebox input"/></td>				
					<td class="title bg1" style="width:80px;">终止日期：</td>
					<td class="td_1"><input value="" id="zzrq" name="end_date" style="width:140px;" class="plui-validatebox input"/></td>    
				</tr>
				<tr>
				<td class="title bg1" style="width:100px;">用地单位名称：</td>
					<td class="td_1" colspan="5"><input  value="" id="yddwmc" name="YDDWMC" style="width:666px;" class="plui-validatebox input"/>
					</td>
				
				</tr>
		</table>
		</br>
			</div>
			</br>
       </form>        
      
    <!-- tab项 -->
    <div id="tt" class="plui-tabs" style="width:500px;height:200px;">  
    <div title="宗地图 " style="padding:20px;display:none;" data-options="selected:true" >  
           
    </div>  
    <div title="宗地位置图 " data-options="closable:false" style="overflow:auto;padding:20px;display:none;">  
         
    </div>  
    <div title="红线图 " data-options="closable:false" style="overflow:auto;padding:20px;display:none;">  
         
    </div>
    </div>
    
</div>      
     
</div>  
</body>
</html>
