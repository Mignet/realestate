<%@ page language="java" import="java.util.*" pageEncoding="GBK"
	contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
       	<%@ include file="/base/taglibs.jsp"%>
    	<%@ include file="/base/prefix.jsp"%>
		<title>特殊流程窗口</title>
    	<script type="text/javascript">
    	   var ctx = '${ctx}';
    	   var user = '${user}';
    	</script>
    	<c:choose> 
		<c:when test="${jds_sqs_tw eq 'REJECTJDS'}"> 
    	    <script type="text/javascript" src="${ctx}/js/workflow/specproc/reject_tslc_reg_decision.js"></script> 
		</c:when> 
		<c:when test="${jds_sqs_tw eq 'DEFERMENTJDS'}"> 
    	    <script type="text/javascript" src="${ctx}/js/workflow/specproc/deferment_tslc_reg_decision.js"></script>
		</c:when> 
		<c:when test="${jds_sqs_tw eq 'DELAYSQS'}"> 
    	    <script type="text/javascript" src="${ctx}/js/workflow/specproc/delay_tslc_doc_application.js"></script>
		</c:when> 
		<c:when test="${jds_sqs_tw eq 'HANGUPSQS'}"> 
    	    <script type="text/javascript" src="${ctx}/js/workflow/specproc/hangup_tslc_doc_application.js"></script>
		</c:when> 
		<c:when test="${jds_sqs_tw eq 'BACKLANGUAGESQS'}"> 
    	    <script type="text/javascript" src="${ctx}/js/workflow/specproc/backlanguage_tslc_application.js"></script>
		</c:when> 
		</c:choose>
		<script type="text/javascript" src="${ctx}/js/workflow/spec-work-window.js"></script>
    	<script type="text/javascript" src="${ctx}/js/workflow/specproc/suggestion.js"></script>
    	<%-- <script type="text/javascript" src="${ctx}/js/qualityinspection/examine.js"></script> --%>
		<style type="text/css">
			body,html {
				margin: 0px;
				font-family: Arial;
				font-size: 12px;
				color: #333333;
				padding: 5px !important;
			}
			 .underline{
		           background-color: #ffffff;       
				   border-color:#000000;    
				   border-style:solid;    
				   border-top-width:0px;    
				   border-right-width:0px;    
				   border-bottom-width:1px;    
				   border-left-width:0px;
				   margin-left: 0px;
				   margin-right: 0px;   
	        }
			.blankbox{
		           background-color: #ffffff;       
				   border-color:#000000;    
				   border-style:solid;    
				   border-top-width:0px;    
				   border-right-width:0px;    
				   border-bottom-width:0px;    
				   border-left-width:0px;   
	        }
			input[class='greenbg'],textarea{
		         background-color: #cde6c7;
				 border-top:1px solid #afb4db;
				 border-left:1px solid #afb4db;
				 border-bottom:2px solid #cde6c7;
				 border-right:2px solid #cde6c7;
		     }
		    .greenbg{
		         background-color: #cde6c7;
				 border-top:1px solid #afb4db;
				 border-left:1px solid #afb4db;
				 border-bottom:2px solid #cde6c7;
				 border-right:2px solid #cde6c7;
		    }
		    table{
	              font-size: 12px;
	        }
			.raised {background:transparent;width:100%;margin-bottom:2px}
			.raised h1,.raised p {margin:0 1px;}
			.raised h1 {font-size:18px;color:#000;float:left;}
			.raised p {padding-bottom:1px;}
			.raised .b1,.raised .b2,.raised .b3,.raised .b4,.raised .b1b,.raised .b2b,.raised .b3b,.raised .b4b {display:block;overflow:hidden;font-size:1px;}
			.raised .b1,.raised .b2,.raised .b3,.raised .b1b,.raised .b2b,.raised .b3b {height:1px;}
			.raised .b2 {background:rgb(224, 236, 255);border-left:1px solid rgb(224, 236, 255);border-right:1px solid rgb(224, 236, 255);}
			.raised .b3 {background:rgb(224, 236, 255);border-left:1px solid rgb(224, 236, 255);border-right:1px solid rgb(224, 236, 255);}
			.raised .b4 {background:rgb(224, 236, 255);border-left:1px solid rgb(224, 236, 255);border-right:1px solid rgb(224, 236, 255);}
			.raised .b4b {background:#000;border-left:1px solid #eee;border-right:1px solid #999;}
			.raised .b3b {background:#ccc;border-left:1px solid #ddd;border-right:1px solid #999;}
			.raised .b2b {background:#ccc;border-left:1px solid #aaa;border-right:1px solid #999;}
			.raised .b1 {margin:0 5px;background:#fff;}
			.raised .b2, .raised .b2b {margin:0 3px;border-width:0 2px;}
			.raised .b3, .raised .b3b {margin:0 2px;}
			.raised .b4, .raised .b4b {height:2px; margin:0 1px;}
			.raised .b1b {margin:0 5px; background:rgb(224, 236, 255);}
			.raised .boxcontent {display:block;background:rgb(224, 236, 255);border-left:1px solid rgb(224, 236, 255);;border-right:1px solid rgb(224, 236, 255);text-align:left;padding-left:8px;padding-bottom: 2px;}
		</style>
</head>
<body class="plui-layout" >
   <div data-options="region:'center'" border=false>
      <div class="plui-layout" fit=true>
	       <div data-options="region:'north'" border=false style="overflow: hidden;" > 			
				<div id="operation_head" class="raised" >
					<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
					<div class="boxcontent">
					 <div id="operation" style="width:100%;text-align:right;padding-right:50px;">
					  <h1><label id="note">初审</label></h1>
                      <a id="save" class="plui-linkbutton" iconCls="icon-save" onclick="submitsp()" disabled="disabled">保存</a>
					 </div>
					</div>
					<b class="b4b"></b><b class="b3b"></b><b class="b2b"></b><b class="b1b"></b>
				</div>
	        </div>	 
			<div data-options="region:'center'" border=false style="overflow-x: hidden;">	
				 <div id="tt"  fit=true>  
					    <div id="jds_sqb_tw" title="驳回决定书" style="padding:1px;display:block;text-align:center;">
					       <c:choose>
					       	<c:when test="${jds_sqs_tw eq 'REJECTJDS'}"> 
					             <jsp:include page="./specproc/reject_tslc_reg_decision.jsp" flush="false"></jsp:include>
							</c:when> 
							<c:when test="${jds_sqs_tw eq 'DEFERMENTJDS'}"> 
					             <jsp:include page="./specproc/deferment_tslc_reg_decision.jsp" flush="false"></jsp:include>
							</c:when> 
							<c:when test="${jds_sqs_tw eq 'DELAYSQS'}"> 
					             <jsp:include page="./specproc/delay_tslc_doc_application.jsp" flush="false"></jsp:include>
							</c:when> 
							<c:when test="${jds_sqs_tw eq 'HANGUPSQS'}"> 
					             <jsp:include page="./specproc/hangup_tslc_doc_application.jsp" flush="false"></jsp:include>
							</c:when> 
							<c:when test="${jds_sqs_tw eq 'BACKLANGUAGESQS'}"> 
					             <jsp:include page="./specproc/backlanguage_tslc_application.jsp" flush="false"></jsp:include>
							</c:when> 
					       </c:choose>
					    </div>  
					   <%--  <div id="djview" title="核审意见"  style="overflow-x:hidden;padding:2px;display:block;">  
					        	 <jsp:include page="./specproc/suggestion.jsp" flush="false"></jsp:include>
					        	 <jsp:include page="../qualityinspection/examine.jsp" flush="false"></jsp:include>
					    </div>   --%>
					    <div id="djcontent" title="登记内容"  style="overflow-x:hidden;padding:2px;display:block;">  
					        	<iframe src="${ctx}/jsp/qualityinspection/work-window.jsp"  id='work-iframe' name='work-iframe' style="width:100%;height:100%" allowtransparency='true' scrolling='no' frameborder='no'></iframe> 
					    </div>  
			     </div>  
			</div>
		</div>
	</div>
</body>
</html>
