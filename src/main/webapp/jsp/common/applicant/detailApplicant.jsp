<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%session.setAttribute("response", response);%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>编辑用户</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
        
 <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    
     <style>
  	.col1, .col2 {
		float:left;
		position:relative;
		overflow:hidden;
	}
	.col1{
		width:80%;
		left:0%;
	}
	.col2{
		width:20%;
		left:0%;
	}
	.clear{
		clear:both;
	}
	.age_div{
	}
  </style>
    
   <script type="text/javascript">
  var ctx = '${ctx}';
  </script>   

<script type="text/javascript" src="${ctx}/js/common/applicant/detailApplicant.js"></script>
    </head>
    <body>
    	<div class="plui-panel" data-options="fit:true,border:false">
    		<div style="width:688px;height:310px;">
	    		<div class="col1">
			    	<form id="detail_app_form" method="post">    	
			        	       	<!-- <input type="hidden" id="qlrId" name="holder_id"/>  
			        	       	<input type="hidden" id="qlrName" name="hol_name"/>  
			        	       	<input type="hidden" id="qlrType" name="hol_type"/>  
			        	       	<input type="hidden" id="zjzl" name="cer_type"/>  
			        	       	<input type="hidden" id="qlrzjNo" name="hol_cer_no"/>  
			        	       	<input type="hidden" id="qlrzjNo2" name="hol_cer_no2"/> 
			        	       	<input type="hidden" id="zjNo" name="cer_no"/> 
			        	       	<input type="hidden" id="txAddr" name="address"/>  
			        	       	<input type="hidden" id="depType" name="depart_type"/>  
			        	       	<input type="hidden" id="qlrKind" name="hol_rel"/>  
			        	       	<input type="hidden" id="position" name="position"/>   
			        	       	<input type="hidden" id="qlrjhrId" name="guardian_id"/>   
			        	       	<input type="hidden" id="djdyId" name="reg_unit_id"/>   
			        	       	<input type="hidden" id="djdyType" name="reg_unit_type"/> 
			        	       	
			        	       	<input type="hidden" name='app.app_pic' id='app_pic' />
								<input type="hidden" name='app.age_pic' id='age_pic' /> -->
			        </form>
			    </div>
		    	<div class="col2">
		    		<!-- 申请人头像 -->
		    		<div class="app_div">
		    			<div>
		    				<img id="img_app_pic"  style="width:130px;height:130px;"  src="${ctx}/images/noimg.png" />
		    			</div>
		    			<div>
		    				<!-- <input id='btn_up_pic' type="button"  onclick="" value="上传头像" />
		    				<input id='btn_app_ic' type='button' onclick="setid()" value="读IC卡" /> -->
		    			</div>
		            	
		    		</div>
		    		
		    		<!-- 代理人头像 -->
		    		<div class="age_div">
		    			<div>
			    			<img id="img_age_pic" style="width:130px;height:130px;" src="${ctx}/images/noimg.png" />
		    			</div>
		    			<div >
			            	<!-- <input id='btn_up_pic' type="button"  onclick="" value="上传头像" />
			    			<input id='btn_age_ic' type="button"  onclick="setageid()" value="读IC卡" /> -->
		    			</div>
		    		</div>
		    		
		        	
		            
		    	</div>
	    	</div>
	    	<div class="clear"></div>
	        <div style="text-align:center">
                <!-- <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="submit();">保存</a> -->
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('detail_app_win');">关闭</a>
		    </div>
	    </div>
    </body>
</html>

