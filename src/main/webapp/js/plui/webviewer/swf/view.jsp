<%@ page language="java"  pageEncoding="GBK"%>
<!-- 多页单次加载 在页面加载完时，阅读器大小随之调整，只调整一次-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">	
    <head> 
        <title>查阅FLASH</title>         
	    
	    <jsp:include page="../../head.jsp" />
	    
	    <style type="text/css" media="screen"> 
			html, body	{ height:100%; }
			body { margin:0; padding:0; overflow:auto; }   
			#flashContent { display:none; }
        </style>
		<script type="text/javascript" src="js/flexpaper_flash.js"></script>
		<script type="text/javascript">
			var __fileInfo = $.evalJSON(decodeURI(decodeURI('<%= request.getParameter("fileInfo") %>')));
			var urls = [];
			urls.push($.filereader.contextPath, '/plui2/webviewer/', $.filereader.actionURL);
			urls.push('?', 'method=swfView&', jQuery.param(__fileInfo));
			var swfurl = urls.join('');
		</script>	
    </head> 
    <body>
	        <div id="viewerPlaceHolder" width="100%" height="100%"></div>
	        <script type="text/javascript"> 
						var fp = new FlexPaperViewer('js/FlexPaperViewer',
						 'viewerPlaceHolder', 
						 { 
							config : {
								SwfFile : encodeURIComponent(swfurl),
								Scale : 1.3, 
								ZoomTransition : 'easeOut',
								ZoomTime : 0.5,
								ZoomInterval : 0.2,
								FitPageOnLoad : false,
								FitWidthOnLoad : false,						 
								FullScreenAsMaxWindow : false,
								ProgressiveLoading : true,
								MinZoomSize : 0.2,
								MaxZoomSize : 5,
								SearchMatchAll : false,
								InitViewMode : 'Portrait',
								PrintPaperAsBitmap : false,
								PrintEnabled : true,
								ReadOnly : true,
								PrintToolVisible : true,
								ViewModeToolsVisible : true,
								ZoomToolsVisible : true,
								NavToolsVisible : true,
								CursorToolsVisible : true,
								SearchToolsVisible : true,
		  						localeChain: 'zh_CN'
						 	}
						});
	        </script>
   </body> 
</html> 
<script type="text/javascript" language="JavaScript">
	//Powered By smvv @hi.baidu.com/smvv21
	function flashChecker()
	{
			var hasFlash=0;         //是否安装了flash
			var flashVersion=0; 		//flash版本
			var isIE=/*@cc_on!@*/0;      //是否IE浏览器
			try{			
					if(isIE)
					{
						var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash'); 
						if(swf) {
							hasFlash=1;
							VSwf=swf.GetVariable("$version");
							flashVersion=parseInt(VSwf.split(" ")[1].split(",")[0]); 
						}
					}else{
					if (navigator.plugins && navigator.plugins.length > 0)
					{
							var swf=navigator.plugins["Shockwave Flash"];
					    if (swf)
					     {
									hasFlash=1;
					        var words = swf.description.split(" ");
					        for (var i = 0; i < words.length; ++i)
									{
					            if (isNaN(parseInt(words[i]))) continue;
					            flashVersion = parseInt(words[i]);
									}
					    }
					}
				}			
			}catch(e){
								 
			}
		return {f:hasFlash,v:flashVersion};
	}
	
	
	var fls=flashChecker();
	var s="";
	if(fls.f)
	{
		
	}
	else{ 
		//document.getElementById("flexviewer").style.display = "none";
		document.getElementById("divAlternateContent").style.display = "";
  }
</script>