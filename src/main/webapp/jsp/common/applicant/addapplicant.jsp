<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%session.setAttribute("response", response); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新增用户</title>
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
<script type="text/javascript" src="${ctx}/js/common/applicant/addapplicant.js"></script>
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
    <body>
    	<div class="plui-panel" data-options="fit:true,border:false">
    		<div style="width:688px;height:310px;">
	    		<div class="col1">
			    	<form id="add_app_form" method="post" action=""> 
						<input type="hidden" name='app.app_pic' id='app_pic' />
						<input type="hidden" name='app.age_pic' id='age_pic' />
			    	</form>
		    	</div>
		    	<div class="col2">
		    		<!-- 申请人头像 -->
		    		<div class="app_div">
		    			<div>
		    				<img id="img_app_pic"  style="width:130px;height:130px;"  src="${ctx}/images/noimg.png" />
		    			</div>
		    			<div>
		    				<input id='btn_app_ic' type='button' onclick="setid()" value="读IC卡" />
		    				<input id='btn_up_pic' type="button"  onclick="upload_pic('app')" value="拍照" />
		    			</div>
		            	
		    		</div>
		    		
		    		<!-- 代理人头像 -->
		    		<div class="age_div">
		    			<div>
			    			<img id="img_age_pic" style="width:130px;height:130px;" src="${ctx}/images/noimg.png" />
		    			</div>
		    			<div >
			    			<input id='btn_age_ic' type="button"  onclick="setageid()" value="读IC卡" />
			            	<input id='btn_up_pic' type="button"  onclick="upload_pic('age')" value="拍照" />
		    			</div>
		    		</div>
		    		
		        	
		            
		    	</div>
	    	</div>
	    	<div class="clear"></div>
	        <div style="text-align:center">
	            <a id="submit" class="plui-linkbutton" iconCls="icon-save" onclick="submit();">保存</a>
	            <a id="cancel" class="plui-linkbutton" iconCls="icon-undo" onclick="closeInTopWindow('add_user_win');">取消</a>
	        </div>
		</div>
    </body>
</html>

