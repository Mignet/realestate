<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <title>日志监控-深圳市不动产登记系统</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/push.css">
<style type="text/css">
ul,li{ list-style-type:none;}
#con{ width:760px; height:400px; margin:30px auto 10px auto; position:relative; border:1px #d3d3d3 solid; background-color:#fff; overflow:hidden;}
#con ul{ position:absolute; margin:10px; top:0; left:0; padding:0;}
#con ul li{ width:100%;  border-bottom:1px #ccc dotted; padding:20px 0; overflow:hidden; }
#con ul li a.face{ float:left; width:50px; height:50px; margin-top:2px; padding:2px;}
#con ul li h4{height:22px; line-height:22px; margin-left:60px}
#con ul li p{ margin-left:60px;line-height:22px; }
</style>
<script type="text/javascript" src="${ctx}/js/sysmanage/push.js"></script>
<script language="javascript">
$(function(){
    var scrtime;
    $("#con").hover(function(){
         clearInterval(scrtime);
    },function(){
        scrtime = setInterval(function(){
            	var ul = $("#con ul");
                var liHeight = ul.find("li:last").height();
                ul.animate({marginTop : liHeight+40 +"px"},1000,function(){
                	ul.find("li:last").prependTo(ul)
                	ul.find("li:first").hide();
                	ul.css({marginTop:0});
                	ul.find("li:first").fadeIn(1000);
                });        
        },3000);
     }).trigger("mouseleave");
	$(".face img").corner("5px");
});
</script>
</head>

<body>
<div id="main">
   <h2 class="top_title">日志监控-深圳市不动产登记系统</h2>
   <div class="demo">  
   		<div id="con">
  			<ul>
    			<li> 
                <h4><a href="#">李开复</a></h4>
        		<p>【领导力的四个境界】境界一：员工因为你的职位而服从你；境界二：员工因为你的能力而服从你；境界三：员工因为你的培养而服从你，他们感恩于你对他们的尊重、培养和付出；境界四：员工因为你的为人、魅力、风范、价值观而拥戴你。（转）</p>
    			</li>
    			<li>
                <h4><a href="#">谷大白话</a></h4>
        		<p>「问」"我的小伙伴们"用英文怎么说？「答」My Little Good Buddies, 简称MLGB。</p>
    			</li>
                <li> 
                <h4><a href="#">月光光WEB</a></h4>
        		<p>最难过的，莫过于当你遇上一个特别的人，却明白永远不可能在一起，或迟或早，你不得不放弃。[转]</p>
    			</li>
    			<li>
                <h4><a href="#">陆琪</a></h4>
        		<p>在这世上，没有一辈子接你上下班的男人，更没有一辈子不对你说谎的男人。但确实会有一辈子陪着你，不离开你的男人。要记住，只要是人，就会松懈、懒惰、有缺点。千万别对人抱着不可能实现的期望。男人有多好，就看你的期望有多低。期待少一些，惊喜才会多一点。——陆琪（上帝保佑，大家晚安）</p>
    			</li>
    			
  			</ul>
		</div>
   </div>
</div>
</body>
</html>
