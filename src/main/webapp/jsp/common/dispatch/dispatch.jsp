<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <meta http-equiv="x-ua-compatible" content="ie=7" />
	<title>发文</title>
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
<script type="text/javascript" src="${ctx}/js/common/dispatch/dispatch.js"></script>
  <script type="text/javascript">
  var ctx = '${ctx}';
  </script>
</head>
 <OBJECT classid="clsid:10946843-7507-44FE-ACE8-2B3483D179B7"
		codebase="CVR100.cab#version=3,0,3,3"
		id="CVR_IDCard"
	    name="CVR_IDCard"
		width=0
		height=0
		align=center
		hspace=0
		vspace=0>
</OBJECT> 
<body  class="plui-layout">
  <!-- 中部 -->
    <div data-options="region:'center'">
    <div class="page_con"  style="width:800px">
    <table id="table_djxx"></table>
    <!-- 领证人信息表 -->
     <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 <label style="font-size: 12px;font-weight: bold;" >领证人信息</label>
		</div> 
		<div class="datagrid-wrap panel-body"> 
		</br>
        <table  id="tab_lzr" class="tab_lzr" style="width: 100%;">
				<tr>
					<td class="title bg1" style="width:200px;"><b>＊</b>领证人类型:</td>
					<td class="td_1"><select class="plui-combobox" style="width:200px" id="rec_type" name="rec_type">
					<option>申请人</option>
					<option>代理人</option>
					</select>
					
					
					</td>
					<td class="title bg1" style="width:200px;"><b>＊</b>领证人:</td>
					<td class="td_2"><input type="text" value=""  id="rec_person" name="rec_person" style="width:200px;" class="plui-validatebox input" data-options="required:true"/></td>
				
				</tr>
				<tr>
				<td class="title bg2" style="width:200px;"><b>＊</b>领证人证件类型:</td>
					<td class="td_1"><select class="plui-combobox" style="width:200px" id="rec_cer_type" name="rec_cer_type">
					<option>身份证</option>
					<option>军官证</option>
					</select>
					
					
					</td>
					<td class="title bg2" style="width:200px;"><input  type="button" onclick="setid()" value="读IC卡"/><b>＊</b>领证人证件编号:</td>
					<td class="td_1"><input type="text" value="" id="rec_cer_no" name="rec_cer_no" style="width:200px;" class="plui-validatebox input" data-options="required:true"/>
					
					</td>
					
				</tr>
				<tr>
				<td class="title bg1" style="width:200px;"><b>＊</b>领证日期:</td>
					<td class="td_2"><input type="text" value="" id="rec_date" name="rec_date" style="width:200px;" class="plui-validatebox input" disabled="disabled"/></td>
				
				</tr>
			</table>
        
        <br/>
        <br/>
        <br/>
        <div style=" text-align:center;">
           <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="submit1()">发证</a>
            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="cancelSave()">取消发证</a>
        </div>
        </br>
          </div>
        </form>
  
    
    </div>
    
    
    
    
    </div>
</body>
</html>
