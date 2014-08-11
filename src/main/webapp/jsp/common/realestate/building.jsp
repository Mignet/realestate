<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>房屋信息</title>
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
  <script type="text/javascript" src="${ctx}/js/common/realestate/building.js"></script>	
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
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">建筑物信息</label>
		</div>  
		  <div class="datagrid-wrap panel-body">
		</br>
		
        <table id="tab_build" style="width: 100%;">
				<tr>
					<td class="title bg1" style="width:160px;">楼名及栋号：</td>
					<td class="td_1"><input value="" id="jzwmc" name="build_no" style="width:190px;" class="plui-validatebox input"/></td>
					<td class="title bg1" style="width:160px;">建筑物编号：</td>
					<td class="td_1"><input value=""  id="jzwbm" name="building_code" style="width:190px;" class="plui-validatebox input" /></td>		
				</tr>
				<tr>
					<td class="title bg2" style="width:160px;">房屋类型：</td>
					<td class="td_2"><input value="" id="fwlx" name="FWLX" style="width:190px;" class="plui-validatebox input"/></td>
					<td class="title bg2" style="width:160px;">房屋结构：</td>
					<td class="td_2"><input value=""  id="fwjg" name="FWJG" style="width:190px;" class="plui-validatebox input" /></td>		
				</tr>
				<tr>
					<td class="title bg1" style="width:160px;">总层数：</td>
					<td class="td_1"><input value="" id="zts" name="layer_count" style="width:190px;" class="plui-validatebox input"/></td>
					<td class="title bg1" style="width:160px;">基底面积(m<sup>2</sup>)：</td>
					<td class="td_1"><input value=""  id="jdmj" name="build_ld_area" style="width:190px;" class="plui-validatebox input" /></td>		
				</tr>
				<tr>
					<td class="title bg2" style="width:160px;">总建筑面积(测绘)(m<sup>2</sup>)：</td>
					<td class="td_2"><input value="" id="zjzmj" name="floor_area" style="width:190px;" class="plui-validatebox input"/></td>
					<td class="title bg2" style="width:160px;">使用年限(年)：</td>
					<td class="td_2"><input value=""  id="synx" name="SYNX" style="width:190px;" class="plui-validatebox input" /></td>		
				</tr>
				<tr>
					<td class="title bg1" style="width:160px;">公用建筑面积(m<sup>2</sup>)：</td>
					<td class="td_1"><input value="" id="gyjzmj" name="common_area" style="width:190px;" class="plui-validatebox input"/></td>
					<td class="title bg1" style="width:160px;">应分摊公用建筑面积(m<sup>2</sup>)：</td>
					<td class="td_1"><input value=""  id="yftjzmj" name="apportion_common_area" style="width:190px;" class="plui-validatebox input" /></td>		
				</tr>
				<tr>
					<td class="title bg2" style="width:160px;">不应分摊公用建筑面积(m<sup>2</sup>)：</td>
					<td class="td_2"><input value="" id="byftjzmj" name="noapportion_common_area" style="width:190px;" class="plui-validatebox input"/></td>
					<td class="title bg2" style="width:160px;">建筑容积率：</td>
					<td class="td_2"><input value=""  id="jzrjl" name="build_plot_ratio" style="width:190px;" class="plui-validatebox input" /></td>		
				</tr>
				<tr>
					<td class="title bg1" style="width:160px;">房屋用途：</td>
					<td class="td_1"><input value="" id="fwyt" name="FWYT" style="width:190px;" class="plui-validatebox input"/></td>
						
				</tr>
			</table>
			</div>	
			<!-- 分栋汇总表 -->	
           <table id="table_fdhzb" ></table>
           <!-- 分户汇总表 -->
           <table id="table_fhhzb" ></table>     
       </form>   
           
    </div>     
</div>  
</body>
</html>
