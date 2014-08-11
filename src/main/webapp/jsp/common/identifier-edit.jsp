<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK" import="com.szhome.security.ext.UserInfo"%>
<%
  UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
  String userName=userInfo.getUserName();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>�༭����</title>
        
        <%@ include file="/base/taglibs.jsp"%>
		<%@ include file="/base/prefix.jsp" %>
        

        <style type="text/css" >
			.nav {
				width:160px;	
			}
			.nav ul{
				list-style:none;
			}
			.nav ul li{
				padding:4px 0px;
			}
			.nav ul li:hover{
				background-color:#EEE;
			}
			.nav ul li.selected{
				background-color:#D3E0F8;
			}
			h3{
				text-align:center;
			}
			
			.ulSy {
				width:160px;	
			}
			
			.ulSy ul{
				list-style:none;
			}
			.ulSy ul ol{
				padding:4px 0px;
			}
			.ulSy ul ol:hover{
				background-color:#EEE;
			}
			.ulSy ul ol.selected{
				background-color:#D3E0F8;
			}
			
		</style>
		 <script>	
        	var ctx ='${ctx}';
        </script>
		<script type="text/javascript" src="${ctx}/js/common/identifier-edit.js"></script>
       
    </head>
    <body style="width:974px;">

      <div id="mainLayoutDiv" style="width:974px;height:574px;">
      	 <div  data-options="region:'west'" style="width:184px" title="����">
      	 	<div id="identifier_tree"></div>
      	 </div>
      	 <div  data-options="region:'east'" style="width:184px">
      	 
      	 	<!-- ����˳�򲼾�  Start -->
      	 	<div id="syLayout1" style="width:200px">
      	 		
      	 		<div  data-options="region:'north'" style="width:184px;height:30px;padding:10px;background-color: rgb(224, 236, 255);">
      	 			˳�����
      	 		</div>
      	 		
      	 		<div class='ulSy' data-options="region:'center'" style="width:124px">
      	 			<ul id="ulSort">
						
					</ul>  
      	 		</div>
      	 		
      	 		<div  data-options="region:'east'"  style="width:60px;background-color: rgb(224, 236, 255);">
      	 			<input id="btnMoveUp" type="button" value="����" onclick="UpLi()" />
      	 			<input id="btnMoveDown" type="button" value="����" onclick="DownLi()" />
      	 		</div>
      	 	</div>
      	 	<!-- ����˳�򲼾�  end -->
      	 	
			
		  </div>
		  <div data-options="region:'center'" style="text-align:center;height:408px;">
		  
		  		<div id="tt"  style="height:508px;">  
		  		
		  			<!-- �������div start-->
				    <div title="����" style="padding:20px;display:block;">  
				    	
				    	<!-- ����������div start-->
				    	<div id="syMain" >
					    		
								    <!-- �����б� div start-->
								    <div data-options="region:'west',width:'200px'," class="nav" >
								    <h3>�����б�</h3>
								        <ul id="ulSy">
								        
								        </ul>
								    </div>
							    
							    	<!-- �����б� div end-->
							  
							    
							    <!-- syRight Start -->
							    <div id="syRight"  data-options="region:'center'">
							    	<div id="ruleDetail" style="text-align:center">
						  				<label id="testLabel"></label><br/>
							  		
								  		
								  		<label>����ֵ:</label><input id="rule" /><br/>
							  			<label for="cbxAdministrativeArea">�Ƿ�ʹ��������</label><input type="checkbox" id="cbxAdministrativeArea" /><input class="plui-combodict" value="������" code="adm_area" id="adm_area" name="adm_area" style="width:100px;" class="plui-validatebox input"/><br/>
							  			
							  			<input id="btnSyAdd" type="button" value="�½�"/>
							  			<input id="btnSyDelete" type="button" value="ɾ��"/>
					  			
			  						</div> 
			  						
					   		 	</div>
					   		 	<!-- syRight end -->
					    </div>
					    <!-- ����������div end-->
					    
					    
				        
				    </div>
				    <!-- �������div end-->
				      
				    <div title="���"  style="overflow:auto;padding:20px;display:block;text-align:center">  
				        	<label for="cbYear">�Ƿ�ʹ�����</label><input type="checkbox" id="cbYear"  onclick="userYear()"/>
				    </div>  
				    <div title="���"  style="padding:20px;display:block;text-align:center">  
				       		���� <input id="bh_longth" required="required" style="width:80px;" />  <br/>
				       		<input type="button" id="btn_bh_longth" value="����"/>
				    </div>  
				    <div title="������"  style="padding:20px;display:block;text-align:center">  
				    	������<input id="input_cyz"></input><br/>
				        <input id="add" type="button" value="�½�"/>
						<input id="delete" type="button" value="ɾ��"/>
				    </div> 
				</div> 
		  		
		  </div>
		  <!-- div center���ֽ��� -->
		  
		  <div data-options="region:'south'" style="text-align:center">
		  	<input type="button" id="btnSumbit" value="�������"/>
		  </div>
      </div>
     
      
    </body>
</html>

