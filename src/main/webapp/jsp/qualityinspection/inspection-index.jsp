<%@ page language="java" import="java.util.*" pageEncoding="GBK"
	contentType="text/html;charset=GBK"%>
<%@page import="java.util.ArrayList" import="com.szhome.security.ext.UserInfo"%>
<%
  UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
	String userName=userInfo.getUserName();
  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�������칫ҳ��</title>
       	<%@ include file="/base/taglibs.jsp"%>
    	<%@ include file="/base/prefix.jsp"%>
    	 <script type="text/javascript">
    	var ctx = '${ctx}';
    	var user = '<%=userName%>';
    	</script>
    	<script type="text/javascript" src="${ctx}/js/qualityinspection/inspection-index.js"></script>
    	<script type="text/javascript" src="${ctx}/js/qualityinspection/examine.js"></script>
<style type="text/css">
body,html {
	overflow:auto;
	margin: 0px;
	font-family: Arial;
	font-size: 12px;
	color: #333333;
	padding: 5px !important;
}

.tip {
	color: #3CF;
}

</style>
</head>
<body class="plui-layout">
	<!-- ��ʼ��ѯ�������ڿ�������ѯ���ĳ�ʼ״̬ ע�������������ʽsearchrow��
        3. ��
    -->
	<!-- �Ա���ÿ���ؼ�Ԫ�أ������������Ҳ���PLUIcombo�����������Ӧ������ʽ"combo"��
        	 �Ա���ÿ���ؼ�Ԫ�أ������combo�������Ӧ��������"width="80""��-->
      
       <div data-options="region:'north'"  border=false> 	
       		
       		 <div id="operation"
				style="width: 1100px; height: 30px; background-color: #eeeeee;text-align:right;padding-right:50px;" >
				
				<label style="float:left;margin:4px 0px 4px 4px;">��ǰ����Ϊ��</label>
				<label id="note" style="float:left;margin:4px 0px 0px 0px;"> </label>
				
				  <a id="save" class="plui-linkbutton" iconCls="icon-save" onclick="saveExamine()" disabled="disabled">����</a>
			</div>
       		
       		
			<div id="tt"  style="width:1150px;height:560px;">  
			    <div title="���ĳ��" style="padding:20px;display:block;">  
			    	<div id="div_proc_node" style="width: 1200px; height: 30px; background-color: #eeeeee;" >
			    		<label>���ڵ㣺</label><input id="proc_node" name="proc_node" />  
			    	</div>
						<iframe src="${ctx}/jsp/qualityinspection/work-window.jsp"  id='work-iframe' name='work-iframe' height='598' width='1200'  allowtransparency='true' scrolling='no' frameborder='no'></iframe>
			    </div>  
			    <div title="������"  style="overflow:auto;padding:20px;display:block;">  
			        	<%@ include file="examine.jsp"%> 
			    </div>  
			    <div title="��������¼��"  style="padding:20px;display:block;">  
			   		<div id="div_proc_node" style="width: 1200px; height: 30px; background-color: #eeeeee;" >
			   			 <label>���ڵ㣺</label><input id="qua_proc_node" name="qua_proc_node" />  
			   			 <input type="checkbox" id="is_error" style="margin-left:400px;" onclick="cbx_error_click(this);"/><label for="is_error">�����</label> 
			   			 <a id="btn_opendialog" class="plui-linkbutton" iconCls="icon-pencil" onclick="$('#div_correct_notice').dialog('open');">�ⶨ����֪ͨ��</a>
			   			
			   		
			   		 </div>
			        <table id='table_quality_record' style="width:600px;text-align:center;BORDER-COLLAPSE: collapse;" borderColor=#000000  cellPadding=1  align=center border=1>
			        	
			        </table>
			        
			        <div id="div_correct_notice" >  
						    <table id="tab_reg_info" style="width:600px;margin-top:30px;margin-left:20px;">
						    	<caption><h1>����֪ͨ������</h1></caption>
								<tr>
									<td colspan="3" scope="col"><textarea value="" id="correct_content"
											onKeyDown="limitLength(this,400)"
											onKeyUp="limitLength(this,400)"
											onPaste="limitLength(this,400)" name="oivo.excursus"
											style="height: 100px; width: 480px; font-size: 14px"
											></textarea></td>
								</tr>
								<tr>
									<td class="td_1" colspan="3">	
										<a id="btn_submit" class="plui-linkbutton" iconCls="icon-save" onclick="saveCorrectnoticeContent()" >����</a>
										<!-- 
										 <a id="btn_submit" class="plui-linkbutton" iconCls="icon-search" onclick="viewCorrectionNotice()" >�鿴����֪ͨ��</a>
										 -->
									</td>
							
								</tr>
							</table>
					</div>  
			    </div>  
			</div>  
	
        </div>	 
    	 
	<div data-options="region:'west',split:true,title:'����Ҫ��',border:false"
		style="width: 200px;">
		<ul id="bustree1"  />
	</div>
	

</body>
</html>
