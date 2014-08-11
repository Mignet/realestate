<%@ page language="java" import="java.util.*" pageEncoding="GBK"
	contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>登记信息</title>
<meta http-equiv="content-type" content="text/html;charset=GBK">
	<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">
				<meta http-equiv="keywords" content="">

		 <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
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
<script type="text/javascript">
var ctx = '${ctx}';
</script>

<script type="text/javascript" src="${ctx}/js/mortgage/max-mort-shift-acc.js"></script>
</head>
<body class="body_set">
<div  class="plui-layout" style="width:100%;height:100%;">  
	<div data-options="region:'center',border:true">	 
         <div class="page_con" >        
		<form id="add_app_form" class="searchrow" method="post">		
          <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		 &nbsp;<label style="font-size: 12px;font-weight: bold;">登记信息</label>
		</div> 
		
		<div class="datagrid-wrap panel-body">
		</br>
			<table class="edit_table" style="width: 100%;">
				<tr>
					<td class="title bg1" style="width:200px;">登记编号：</td>
					<td class="td_1"><input value="" id="REG_CODE" name="REG_CODE" style="width:200px;" class="plui-validatebox input" disabled="disabled"/></td>
					<td class="title bg1" style="width:200px;">登记类型：</td>
					<td class="td_1"><input class="plui-combodict" value="" code="061" id="REG_TYPE" name="REG_TYPE" style="width:200px;" class="plui-validatebox input" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td class="title bg2" style="width:200px;">登记点：</td>
					<td class="td_1"><input class="plui-combodict" value="" code="054" id="REG_STATION" name="REG_STATION" style="width:200px;" class="plui-validatebox input" disabled="disabled" />
					</td>
					<td class="title bg2" style="width:200px;">业务描述：</td>
					<td class="td_2"><input value="" id="BUS_DETAIL" name="BUS_DETAIL" style="width:200px;" class="plui-validatebox input" disabled="disabled"/></td>
				</tr>
				<br/>
				
			</table>
			<!-- 抵押登记信息 -->
			<div style="display:none"  class="mortreg">
			<table class="mort_tab" style="width: 100%;" >
			    <tr>
					<td class="title bg1" style="width:200px;">抵押类型：</td>
					<td class="td_1"><input class="plui-combodict" code="007"  id="mort_type" name="mort.mort_type" style="width:200px;" class="plui-validatebox input com" data-options="required:true"/></td>
                     <td class="title bg1" style="width:200px;">贷款用途：</td>
					<td class="td_1"><input  id="loan_usage" name="mort.loan_usage" style="width:200px;" class="plui-validatebox input" data-options="required:true,validType:'chinese'" maxlength="20"/></td>
				</tr>
				<tr>
					<td class="title bg2" style="width:200px;">抵押合同号：</td>
					<td class="td_1"><input  id="mort_con_no" name="mort.mort_con_no" style="width:200px;" class="plui-validatebox input" data-options="required:true,validType:'unnormal'" maxlength="20"/></td>
					<td class="title bg2" style="width:200px;">房地产原值：</td>
					<td class="td_2"><input id="rel_orig_value" name="mort.rel_orig_value" style="width:200px;" class="plui-numberbox  input" data-options="required:true,min:0,precision:2"   maxlength="15"/>
					</td>
				</tr>
				<tr>
					<td class="title bg1" style="width:200px;">抵押担保的主债权数额：</td>
					<td class="td_1"><input class="plui-numberbox  input"   id="mort_assure_right" name="mort.mort_assure_right" style="width:200px;" data-options="required:true,min:0,precision:2"    maxlength="15"/>
					</td>
					<td class="title bg1" style="width:200px;">评估价：</td>
					<td class="td_2"><input  id="assess_price" name="mort.assess_price" style="width:200px;" class="plui-numberbox  input" data-options="required:true,min:0,precision:2"    maxlength="15"/></td>
				</tr>
				<tr>
					<td class="title bg2" style="width:200px;">议定价：</td>
					<td class="td_1"><input  id="agreed_price" name="mort.agreed_price" style="width:200px;" class="plui-numberbox  input" data-options="required:true,min:0,precision:2"    maxlength="15"/></td>
                  	
                  	<td class="title bg2" style="width:200px;">抵押顺位：</td>
					<td class="td_1"><input  id="mort_seq" name="mort.mort_seq" style="width:200px;" class="plui-validatebox input" data-options="required:true" maxlength="15"/></td>
                   
                  	
					
				</tr>
				
				<tr>
					
					<td class="title bg1" style="width:200px;">债权起始日期：</td>
					<td class="td_2"><input id="creditor_start_date" name="mort.creditor_start_date"  style="width:200px;" class="plui-datebox input com" data-options="required:true"/></td>
				    <td class="title bg1" style="width:200px;">债权终止日期：</td>
					<td class="td_1"><input  id="creditor_end_date" name="mort.creditor_end_date" style="width:200px;" class="plui-datebox input com" data-options="required:true"/></td>
				</tr>
				<tr>
				     <td class="title bg2" style="width:200px;">债务人：</td>
					<td class="td_1"><input class="plui-validatebox"   id="borrower" name="mort.borrower" style="width:200px;" data-options="required:true" maxlength="10"/>
					</td>
					<td class="title bg2" style="width:200px;">抵押份额：</td>
					<td class="td_1"><input  id="mort_port" name="mort.mort_port" style="width:200px;" class="plui-validatebox input" data-options="required:true"  maxlength="4"/></td>
                   
                   
				</tr>
				<tr>
				     <td class="title bg1" style="width:200px;">担保范围：</td>
					<td class="td_1"><input class="plui-validatebox"   id="assuer_range" name="mort.assuer_range" style="width:200px;" data-options="required:true" maxlength="15"/>
					</td>
					
               
				    <td class="title bg2" style="width:200px;">最高债权确定事实：</td>
					<td class="td_1"><input class="plui-validatebox"   id="max_amount" name="mort.max_amount" style="width:200px;" data-options="required:true" maxlength="20"/>
					</td>
				</tr>
				<tr>
                  	<td class="title bg2" style="width:200px;">确定担保的债权数额：</td>
					<td class="td_1"><input  id="sure_amount" name="mort.sure_amount" style="width:200px;" class="plui-numberbox input" data-options="required:true" maxlength="15"/></td>
				</tr>
				<!-- 
				<tr>
                  	<td class="title bg2" style="width:200px;">确定担保的债权数额：</td>
					<td class="td_1"><input  id="sure_amount" name="mort.sure_amount" style="width:200px;" class="plui-numberbox input" data-options="required:true" maxlength="15"/></td>
                    
                  
                  
                    <td class="title bg2" style="width:200px;">抵押登记日期：</td>
					<td class="td_1"><input  id="mort_reg_date" name="mort.mort_reg_date" style="width:200px;" class="plui-validatebox input " data-options="required:true" readonly="readonly"/></td>
				
				</tr>
				 -->
				
			</table>
			</br>
			</div>
			</div>
			
			<table id="table_house"></table>
			
			<table id="table_transferor"></table>
			
            <table id="table_user"></table>
            <div style="display:none">
            <div style="background-color: rgb(224, 236, 255);line-height:18px" class="panel-header">
		          &nbsp;<label style="font-size: 12px;font-weight: bold;">房地产证附记</label>
		        </div> 
		        <div class="datagrid-wrap panel-body">
		      <table id="tab_fdcz" style="width: 100%;">
		         <tr>
              <td colspan="4" scope="col" >
					<textarea value="" id="EXCURSUS"  onKeyDown="limitLength(this,400)" onKeyUp="limitLength(this,400)" onPaste="limitLength(this,400)"
							name="EXCURSUS" style="height:80px;width:100%;font-size:14px" disabled="disabled"></textarea>
	             </td>
               </tr>   
		     </table>
		     
		     </div>
		     </div>
		     <div style="text-align:center;display:none">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" >保存</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('add_user_win');">取消</a>
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="validate();">校验</a>
	        </div>
               
		</form>
		</div>
	</div>
</div>
</body>
</html>
