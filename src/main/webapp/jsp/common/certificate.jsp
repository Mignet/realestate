<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK" 
import="com.szhome.security.ext.UserInfo"%>
	<%
  UserInfo user=(UserInfo)session.getAttribute("userInfo");
  String userName=user.getUserName();
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>��֤</title>
        <%@ include file="/base/taglibs.jsp"%>
    	<%@ include file="/base/prefix.jsp"%>
    	<script type="text/javascript">
		var ctx = '${ctx}';
		var user = '<%=userName%>';	
		</script>
    	<script type="text/javascript" src="${ctx}/js/common/certificate.js"></script>
         <style type="text/css">
    	body,html{margin:0px; font-family:Arial; font-size:12px; color:#333333;height:500px;overflow:auto;}
		.tip{color:#3CF;}
		.title {text-align: right;}	
		.bg1 {background: none repeat  0 0 #E0ECFF;}	
		.bg2 { background: none repeat  0 0 #F4F4F4;}
    </style>        
</head>
<body >
<form id="main_form">
  <div data-options="region:'center'">  
    <div class="page_con" style="width:800px">
    
    	<table id="table_natural"></table>
        <table id="table_holders" ></table>
       <form id="form_parcInfo"> 
       <!-- �Ǽ���Ϣ�� -->
       <!-- 
       <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">�Ǽ���Ϣ</label>
		</div>  
		<div class="datagrid-wrap panel-body">
		</br>
		
        <table id="tab_reg_info" style="width: 100%;">
				<tr>
					<td class="title bg1" style="width:140px;">�ǼǱ�ţ�</td>
					<td class="td_1"><input value="" id="REG_CODE" name="REG_CODE" style="width:140px;" class="plui-validatebox input"/></td>
					<td class="title bg1" style="width:140px;">�Ǽ����ͣ�</td>
					<td class="td_2"><input value=""  id="REG_TYPE_NAME" name="REG_TYPE_NAME" style="width:140px;" class="plui-validatebox input" /></td>
				</tr>
				<tr>
					<td class="title bg2" style="width:140px;">����״̬��</td>
					<td class="td_1"><input  value="" id="REG_STATE" name="REG_STATE" style="width:140px;" class="plui-validatebox input"/>
					</td>
					<td class="title bg2" style="width:140px;">�ɷ�״̬��</td>
					<td class="td_2"><input value="δ�ɷ�" id="jfzt" name="JFZT" style="width:140px;" class="plui-validatebox input"/></td>				
				</tr>
			</table>
		
			</div>
			</br>
        -->
       <!-- �ڵ���Ϣ�� -->
       
       <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">������Ϣ</label>
		</div>  
		<div class="datagrid-wrap panel-body">
		</br>
		
        <table id="tab_land" style="width: 100%;">
				<tr>
					<td class="title bg1" style="width:100px;">�ڵغţ�</td>
					<td class="td_1"><input value="" id="PARCEL_CODE" name="PARCEL_CODE" style="width:140px;" class="plui-validatebox input"/></td>
					<td class="title bg1" style="width:100px;">�ڵ����(m<sup>2</sup>)��</td>
					<td class="td_2"><input value=""  id="PARCEL_AREA" name="PARCEL_AREA" style="width:140px;" class="plui-validatebox input" /></td>
				<td class="title bg1" style="width:100px;">������;��</td>
					<td class="td_1"><input class="plui-combodict"  code="015" value="" id="REAL_USAGE" name="REAL_USAGE" style="width:140px;" data-options="hasDownArrow:false"/></td>
				
				
				</tr>
				<tr>
					<td class="title bg2" style="width:100px;">��������</td>
					<td class="td_1"><input class="plui-combodict"  code="065"  value="" id="LOCATION_REG_UNIT" name="LOCATION_REG_UNIT" style="width:146px;" data-options="hasDownArrow:false"/>
					</td>
					<td class="title bg2" style="width:100px;">����λ�ã�</td>
					<td class="td_2"><input value="" id="LAND_ADDRESS" name="LAND_ADDRESS" style="width:140px;" class="plui-validatebox input"/></td>				
					<td class="title bg2" style="width:100px;">ʹ�����ޣ�</td>
					<td class="td_1"><input class="plui-combodict"  code="006" value="" id="USE_PER" name="USE_PER" style="width:140px;" data-options="hasDownArrow:false"/></td>
                     
				</tr>
			</table>
			</div>
			</br>
	   <!-- ������Ϣ�� -->
	  
       <div style="background-color: rgb(224, 236, 255);line-height:18px;" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">������Ϣ</label>
		</div>  
		 <div class="datagrid-wrap panel-body">
		</br>
        <table id="tab_house" style="width: 100%;">
				<tr>
					<td class="title bg1" style="width:100px;">���ز����ƣ�</td>
					<td class="td_1"><input value="" id="PRO_NAME" name="PRO_NAME" style="width:140px;" class="plui-validatebox input"/></td>
					<td class="title bg1" style="width:100px;">�������(m<sup>2</sup>)��</td>
					<td class="td_2"><input value=""  id="BUILD_AREA" name="BUILD_AREA" style="width:140px;" class="plui-validatebox input" /></td>
					<td class="title bg1" style="width:100px;">���ڽ������(m<sup>2</sup>)��</td>
					<td class="td_1"><input value="" id="TAONEI_AREA" name="TAONEI_AREA" style="width:140px;" class="plui-validatebox input"/></td>
				</tr>
				<tr>
					<td class="title bg2" style="width:100px;">��;��</td>
					<td class="td_1"><input class="plui-combodict"  code="062"  value="" id="FLATLET_USAGE" name="FLATLET_USAGE" style="width:146px;" data-options="hasDownArrow:false" />
					</td>
					<td class="title bg2" style="width:100px;">�������ڣ�</td>
					<td class="td_2"><input value="" id="COMPLEION_DATE" name="COMPLEION_DATE" style="width:140px;" class="plui-validatebox input"/></td>				
					<td class="title bg2" style="width:100px;">�ǼǼ�(Ԫ)��</td>
					<td class="td_1"><input value="" id="REG_VALUE" name="REG_VALUE" style="width:140px;" class="plui-validatebox input"/></td>
				</tr>
			</table>
			</div>
       </br>
        <!--��֤��Ϣ -->
       <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">��֤����Ϣ</label>
		</div> 
		<div class="datagrid-wrap panel-body">
		</br>
       <table id="tab_certificate" style="width: 100%;">
				<tr>
					<!-- 
					<td class="title bg1" style="width:100px;">֤����ţ�</td>
					<td class="td_2"><input value="" id="CERTIFICATE_CODE" name="CERTIFICATE_CODE" style="width:1100px;" class="plui-validatebox input"/></td>
					 -->
					<td class="title bg1" style="width:100px;">���ز�֤���ͣ�</td>
					<td class="td_1">
					<input class="plui-combodict"  code="038" id="CERTIFICATE_TYPE" name="CERTIFICATE_TYPE" style="width:140px;" class="plui-validatebox "/>
					</td>
					<td class="title bg1" style="width:100px;">��֤�ˣ�</td>
					<td class="td_1"><input  value="" id="PRINTER" name="PRINTER" style="width:140px;" class="plui-validatebox input"/>
					</td>
					
					<td class="title bg2" style="width:100px;">��֤���ڣ�</td>
					<td class="td_1"><input value="" id="PRINT_DATE" name="PRINT_DATE" style="width:140px;" class="plui-validatebox input"/></td>				               
				</tr>
				<tr>
				 <td colspan="6" scope="col" >
				 <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		          &nbsp;<label style="font-size: 12px;font-weight: bold;">����Ȩ��ժҪ������</label>
		        </div> 
				 
				 </td>
				 </tr>
				 <tr>
              <td colspan="6" scope="col" >
					<textarea  id="EXCURSUS"
							name="EXCURSUS" style="height:100px;width:100%;font-size:14px" disabled="disabled" wrap="virtual"></textarea>
	         </td>
            </tr>
			</table> 
			</div>
			</br>
             <div style="text-align:center">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" >��֤</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" >ȡ��</a>
	        </div>
       
       </form>        
    </div>     
 </div>  
</form>
</body>
</html>
