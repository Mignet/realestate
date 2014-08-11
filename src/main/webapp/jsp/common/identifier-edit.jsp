<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK" import="com.szhome.security.ext.UserInfo"%>
<%
  UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
  String userName=userInfo.getUserName();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>编辑规则</title>
        
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
      	 <div  data-options="region:'west'" style="width:184px" title="索引">
      	 	<div id="identifier_tree"></div>
      	 </div>
      	 <div  data-options="region:'east'" style="width:184px">
      	 
      	 	<!-- 索引顺序布局  Start -->
      	 	<div id="syLayout1" style="width:200px">
      	 		
      	 		<div  data-options="region:'north'" style="width:184px;height:30px;padding:10px;background-color: rgb(224, 236, 255);">
      	 			顺序调整
      	 		</div>
      	 		
      	 		<div class='ulSy' data-options="region:'center'" style="width:124px">
      	 			<ul id="ulSort">
						
					</ul>  
      	 		</div>
      	 		
      	 		<div  data-options="region:'east'"  style="width:60px;background-color: rgb(224, 236, 255);">
      	 			<input id="btnMoveUp" type="button" value="上移" onclick="UpLi()" />
      	 			<input id="btnMoveDown" type="button" value="下移" onclick="DownLi()" />
      	 		</div>
      	 	</div>
      	 	<!-- 索引顺序布局  end -->
      	 	
			
		  </div>
		  <div data-options="region:'center'" style="text-align:center;height:408px;">
		  
		  		<div id="tt"  style="height:508px;">  
		  		
		  			<!-- 索引先项卡div start-->
				    <div title="索引" style="padding:20px;display:block;">  
				    	
				    	<!-- 索引分栏主div start-->
				    	<div id="syMain" >
					    		
								    <!-- 索引列表 div start-->
								    <div data-options="region:'west',width:'200px'," class="nav" >
								    <h3>索引列表</h3>
								        <ul id="ulSy">
								        
								        </ul>
								    </div>
							    
							    	<!-- 索引列表 div end-->
							  
							    
							    <!-- syRight Start -->
							    <div id="syRight"  data-options="region:'center'">
							    	<div id="ruleDetail" style="text-align:center">
						  				<label id="testLabel"></label><br/>
							  		
								  		
								  		<label>索引值:</label><input id="rule" /><br/>
							  			<label for="cbxAdministrativeArea">是否使用行政区</label><input type="checkbox" id="cbxAdministrativeArea" /><input class="plui-combodict" value="深圳市" code="adm_area" id="adm_area" name="adm_area" style="width:100px;" class="plui-validatebox input"/><br/>
							  			
							  			<input id="btnSyAdd" type="button" value="新建"/>
							  			<input id="btnSyDelete" type="button" value="删除"/>
					  			
			  						</div> 
			  						
					   		 	</div>
					   		 	<!-- syRight end -->
					    </div>
					    <!-- 索引分栏主div end-->
					    
					    
				        
				    </div>
				    <!-- 索引先项卡div end-->
				      
				    <div title="年份"  style="overflow:auto;padding:20px;display:block;text-align:center">  
				        	<label for="cbYear">是否使用年份</label><input type="checkbox" id="cbYear"  onclick="userYear()"/>
				    </div>  
				    <div title="编号"  style="padding:20px;display:block;text-align:center">  
				       		长度 <input id="bh_longth" required="required" style="width:80px;" />  <br/>
				       		<input type="button" id="btn_bh_longth" value="保存"/>
				    </div>  
				    <div title="常用字"  style="padding:20px;display:block;text-align:center">  
				    	常用字<input id="input_cyz"></input><br/>
				        <input id="add" type="button" value="新建"/>
						<input id="delete" type="button" value="删除"/>
				    </div> 
				</div> 
		  		
		  </div>
		  <!-- div center布局结束 -->
		  
		  <div data-options="region:'south'" style="text-align:center">
		  	<input type="button" id="btnSumbit" value="保存规则"/>
		  </div>
      </div>
     
      
    </body>
</html>

