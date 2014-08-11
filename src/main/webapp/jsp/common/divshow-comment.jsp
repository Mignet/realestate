<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/base/taglibs.jsp"%>
<script language="javascript">
//div现在的x坐标
var DivShowCommentFun = (
			function (){
				var current_x = null;
				//div现在的y坐标
				var current_y = null;
				//div水平方向拖动距离
				var vOfferLeft = null;
				//div竖直方向拖动距离
				var vOfferTop = null;
				//目标div对象
				var targetElment = null;

				//div鼠标按下事件
				function divMouseDown(element){
					if(event.srcElement.tagName == "INPUT" || event.srcElement.tagName == "TEXTAREA"){
						return;
					}
					
					try{
						if(event.srcElement.tagName == "DIV" && event.srcElement.id == "inserProductDiv"){
							return;
						}
					}catch(e){}
					
					if (event.type=='mousedown' && element.style.display != 'none') {
						current_x = event.clientX + document.body.scrollLeft;
						current_y = event.clientY + document.body.scrollTop;
						element.style.cursor = "move";
						document.onmousemove = documentMouseOver;
					}
				}
				//div鼠标移动事件
				function divMouseMove(element){
					if(event.srcElement.tagName == "INPUT" || event.srcElement.tagName == "TEXTAREA"){
						return;
					}
					if (event.type=='mousemove' && current_x != null && current_y != null && element.style.display != 'none' ) {
						targetElment = element;
						vOfferLeft = event.clientX + document.body.scrollLeft - current_x;
						vOfferTop = event.clientY + document.body.scrollTop - current_y;
						current_x = event.clientX + document.body.scrollLeft;
						current_y = event.clientY + document.body.scrollTop;
						try{
							element.style.left = (element.style.left.replace("px" , "").toInt() + vOfferLeft) + "px" ;
							element.style.top = (element.style.top.replace("px" , "").toInt() + vOfferTop) + "px" ;
						}catch(e){
							element.style.left =( "0".toInt() + vOfferLeft) + "px" ;
							element.style.top = ("0".toInt() + vOfferTop) + "px" ;
						}
						
					}
				}
				//div鼠标松开事件
				function divMouseUp(element){
					if (event.type=='mouseup' && element.style.display != 'none') {
						element.style.cursor = "default";
						current_x = null;
						current_y = null;
						document.onmousemove = function(){};
					}
				}
				//鼠标在界面上移动事件
				function documentMouseOver (){
					if (event.type=='mousemove' && current_x != null && current_y != null && targetElment != null) {
						if(event.clientX + document.body.scrollLeft != current_x 
								&& event.clientY + document.body.scrollTop != current_y){
							vOfferLeft = event.clientX + document.body.scrollLeft - current_x;
							vOfferTop = event.clientY + document.body.scrollTop - current_y;
							current_x = event.clientX + document.body.scrollLeft;
							current_y = event.clientY + document.body.scrollTop;
							targetElment.style.left = (targetElment.style.left.replace("px" , "").toInt() + vOfferLeft) + "px" ;
							targetElment.style.top = (targetElment.style.top.replace("px" , "").toInt() + vOfferTop) + "px" ;
						}
					}
				}

				//显示或隐藏目标客户
				function showOrHiddenTargetDiv(divId , vleft , vtop , isHiddenIv , isNotSupperIE6){
					var preFixStr = divId.replace(/Div$/,"");
					if(isHiddenIv != true){
						$(divId).setStyle("left" , vleft);
						$(divId).setStyle("top" , vtop);
						$(divId).setStyle("display" , "");
						if(isNotSupperIE6 != true){
							$( preFixStr +'Frame').style.height = $(preFixStr + 'Table').offsetHeight;
							$( preFixStr +'Frame').style.width = $(preFixStr + 'Table').offsetWidth;
						}
						
					}else{
						$(divId).setStyle("display" , "none");
					}
				}
				//{'title':'','url':'','height':'','width':''}
				function showdiv(options){
					$('divtitle').innerHTML = options['title'];
					$('innerpageshow').src = options['url'];
					$('checkUnpassDiv').setStyle("display" , "");
					$('checkUnpassDiv').setStyle("height" , options['height']);
					$('checkUnpassDiv').setStyle("width" , options['width']);
					var vleft = event.clientX - 350;
					var vtop = event.clientY - 80;
					$('checkUnpassDiv').setStyle("left" , "0");
					$('checkUnpassDiv').setStyle("top" , "0");
				}
				function closediv(){
					$('checkUnpassDiv').setStyle("display" , "none");
				}
				//刷新页面
				function reFreshTargetDiv(divId){
					var preFixStr = divId.replace(/Div$/,"");
					$( preFixStr +'Frame').style.height = $(preFixStr + 'Table').offsetHeight;

				}
				return {'divMouseDown':divMouseDown,'divMouseMove':divMouseMove,
					'divMouseUp':divMouseUp,'showdiv':showdiv,'closediv':closediv}
			}
		)();

</script>



<div id="checkUnpassDiv" onmousedown="DivShowCommentFun.divMouseDown(this);"
	onmousemove="DivShowCommentFun.divMouseMove(this);" ; onmouseup="DivShowCommentFun.divMouseUp(this);"
	style="height: auto; overflow: auto; overflow-x: hidden; margin-left: 0px; display: none; width: 800px; z-index: 1; background-color: #ffffff; position: absolute">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="3px" class="ym-tl"></td>
		<td width="100%" class="ym-tc">
		<table width="100%" height="25" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td class="ym-header-text" id="divtitle"></td>
				<td width="2%" style="cursor: hand" id="divClose"><img
					src="${ctx}/images/blue/sign/close.gif" width="16" height="16"
					onClick="DivShowCommentFun.closediv();" /></td>
			</tr>
		</table>
		</td>
		<td width="3px" class="ym-tr"></td>
	</tr>
	<tr class="font10">
		<td width="3px" class="ym-ml"></td>
		<td class="ym-mc">
		<table width="100%" height="25" border="0" cellpadding="0"
			cellspacing="0" class="font10" id="checkUnpassTable">
			<iframe id="innerpageshow" scrolling="no" src=""  width="100%" height="142px" frameborder="0"></iframe>
		</table>
		</td>
		<td width="3px" class="ym-mr"></td>
	</tr>
	<tr>
		<td height="3px" class="ym-bl"></td>
		<td height="3px" class="ym-bc"></td>
		<td height="3px" class="ym-br"></td>
	</tr>
</table>
</div>




