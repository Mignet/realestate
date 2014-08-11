<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <title>��־���-�����в������Ǽ�ϵͳ</title>
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
   <h2 class="top_title">��־���-�����в������Ǽ�ϵͳ</h2>
   <div class="demo">  
   		<div id="con">
  			<ul>
    			<li> 
                <h4><a href="#">���</a></h4>
        		<p>���쵼�����ĸ����硿����һ��Ա����Ϊ���ְλ�������㣻�������Ա����Ϊ��������������㣻��������Ա����Ϊ��������������㣬���Ǹж���������ǵ����ء������͸����������ģ�Ա����Ϊ���Ϊ�ˡ��������緶����ֵ�۶�ӵ���㡣��ת��</p>
    			</li>
    			<li>
                <h4><a href="#">�ȴ�׻�</a></h4>
        		<p>���ʡ�"�ҵ�С�����"��Ӣ����ô˵������My Little Good Buddies, ���MLGB��</p>
    			</li>
                <li> 
                <h4><a href="#">�¹��WEB</a></h4>
        		<p>���ѹ��ģ�Ī���ڵ�������һ���ر���ˣ�ȴ������Զ��������һ�𣬻�ٻ��磬�㲻�ò�������[ת]</p>
    			</li>
    			<li>
                <h4><a href="#">½��</a></h4>
        		<p>�������ϣ�û��һ���ӽ������°�����ˣ���û��һ���Ӳ�����˵�ѵ����ˡ���ȷʵ����һ���������㣬���뿪������ˡ�Ҫ��ס��ֻҪ���ˣ��ͻ���и�����衢��ȱ�㡣ǧ�����˱��Ų�����ʵ�ֵ������������ж�ã��Ϳ���������ж�͡��ڴ���һЩ����ϲ�Ż��һ�㡣����½�����ϵ۱��ӣ��������</p>
    			</li>
    			
  			</ul>
		</div>
   </div>
</div>
</body>
</html>
