/*********************************************************************************
*文  件  名  称: inspection-index.js
*功  能  描  述: 质量抽检 检查主页面js
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: joyon
*创  建  日  期： 2014年4月15日 
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/

var selectedProc;						//质量抽检页面选择的办文
var iframeid1;
//存储整个树数据
var iframeData1;

/**********************************************************************************
*函数名称: 
*功能说明: 页面初始化
*函数作者: joyon
*创建日期: 2014年4月16日 15:10:49
*修改历史: 
***********************************************************************************/
//window.onload = function(){
function init(data){
	selectedProc = data;
	selectedProc.procId = selectedProc.PROC_ID;
	selectedProc.procdefId = selectedProc.PROCDEF_ID;
	selectedProc.activdefId = selectedProc.ACTIVDEF_ID;
	selectedProc.activName = selectedProc.NAME;
	selectedProc.wiId = selectedProc.WI_ID;
	//alert(JSON.stringify(selectedProc));
	$('#bustree1').tree({
		url:ctx+"/json/qualityinspection.json",
		//data:{"procdefId":proc.procdefId,"nodeid":proc.activdefId,"time":new Date()},
		onSelect : function(data) {
//			var tmpurl=selectedProc.URL_SPECIFY;
//			$("#main_iframe").attr("src",tmpurl);
//			//document.all.iframename.document.location.reload();
//			//document.frames('main_iframe').location.reload();
//			//var displayFrame = document.frames("displayFrame");
//			//displayFrame.init(selectedProc);
//			//window.frames["main_iframe"].init(selectedProc);
//			var tmpFrameStr = "<iframe src='"+tmpurl+"' id='main_iframe' name='main_iframe'  height='800' width='900'  allowtransparency='true' scrolling='yes' frameborder='0'></iframe>"
//			$("#div_iframe").html(tmpFrameStr);
//			//document.getElementById('main_iframe').contentWindow.init);
//				//var Iframe=$("#main_iframe");
//				//Iframe.contentWindow.init(selectedProc);
			
			if(data)
			{
				for(var i=0;i<iframeData1.length;i++)
				{
							
							if(data.id == iframeData1[i].id){
								$('#'+data.id).css("display","block");
								iframeid1=data.id+"s";
								if(data.attributes){
									$('#'+data.id+"s").attr("src",ctx+data.attributes.url);
								}else{
									$('#'+data.id+"s").attr("src",selectedProc.URL_SPECIFY);
								}
								//window.open(data.attributes.url+"?wiId="+wiId+"&procId="+proc.procId+"&activName="+proc.activName+"&time="+new Date(),'in');
							}else{
								$('#'+iframeData1[i].id).css("display","none");
							}
				}
			}
			
			
			test();
		},onBeforeSelect:function(node){
		},
		onLoadSuccess : function(node,data) {
			iframeData1 = data;
			for(var i=0;i<data.length;i++){
				var url;
				if(data[i].attributes){
					url = ctx+data[i].attributes.url+"?time="+new Date();
					
				}else{
					url = selectedProc.URL_SPECIFY+"?time="+new Date();
				}
				$('#div_iframe').append("<div id='"+data[i].id+"' name='"+data[i].id+"' ><iframe src='"+url+"' id='"+data[i].id+"s' name='"+data[i].id+"s' height='800' width='900'  allowtransparency='true' scrolling='yes' frameborder='0'></iframe></div>")
			}
			
			if(data)
			{
				var firstNode=data[0];
				var node = $('#bustree1').tree('find', firstNode.id);
				$('#bustree1').tree('select', node.target);
			}
			
		}
	});
}

function test(){
	var Iframe=document.getElementById(iframeid1);
	
	if(Iframe.contentWindow.init){
		//alert(Iframe.contentWindow.init);
		Iframe.contentWindow.init(selectedProc);
	}
}
