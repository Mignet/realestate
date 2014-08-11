<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title></title>
       	<%@ include file="/base/taglibs.jsp"%>
    	<%@ include file="/base/prefix.jsp"%>
    	<script type="text/javascript">
var ctx = '${ctx}';
</script>
        <script type="text/javascript" src="${ctx}/js/workflow/busRegister.js"></script>
        <style>
html,body {height:100%; width:100%; margin:0; padding:0;overflow:auto;}
.item { height:150px; width:50%;line-height:25px; margin:10;float:left;}
.left { height:25px; width:142px; background-image:url(${ctx}/images/div-left.png);text-align:center;float:left;}
.middle { margin:0 22px 0 142px;}
.middle .mid { height:25px; width:100%; background-image:url(${ctx}/images/div-right.png); float:left;}
.more { height:25px; width:22px; background-image:url(${ctx}/images/div-right.png); float:left;}
.content {height:100px; width:100%; background:#F5F5F5; float:left;}
span{color:#154a75;font-family:微软雅黑;font-size:8pt;}
a{color:#154a75;font-family:微软雅黑;font-size:8pt;}
a:link{color:#154a75;font-weight:400;text-decoration:none}
a:hover{color:#66f;font-weight:400;text-decoration:underline}
ul{margin:0 0 0 22px; padding:0;list-style:none;}
li{margin:0; padding:0;}
</style>
        
</head>
<body>
	<!-- 初始查询表单，用于可伸缩查询栏的初始状态 注意这里采用了样式searchrow。
        3. 。
    -->	   
     <!-- 对表单的每个控件元素，如果是输入框，且并非PLUIcombo类型组件，则应采用样式"combo"。
        	 对表单的每个控件元素，如果是combo组件，则应设置属性"width="80""。
        
    <form id="simpleform" class="searchrow" method="post">    	
    	<input type="hidden" name="queryCondition" value="user_name like ?"/>
       
        &nbsp;姓名：<input type="text" name="user_name" class="combo" />
        <a class="plui-linkbutton" href="javascript:;" iconCls='icon-search' onclick="submit1();">查询</a>
    </form> --> 
    <!-- 可伸缩查询栏使用的标签 -->        
    <div id="searchpanel"></div>
   <!--  
    <div class="item">
	<div class="left"><span>土地</span></div>
	<div class="middle">
	<div class="mid"></div>
	</div>
	<div class="content">
		<ul>
			<li><a href="#">土地使用权初始登记</a></li>
			<li><a href="#">土地使用权二级转移</a></li>
			<li><a href="#">土地使用权三级转移</a></li>

		</ul>
	</div>
</div>
    <div class="item">
	<div class="left"><span>房屋</span></div>
	<div class="middle">
	<div class="mid"></div>
	</div>
	<div class="content">
		<ul>
			<li><a href="#">房屋所有权初始登记</a></li>
			<li><a href="#">房屋所有权变更登记</a></li>
			<li><a href="#">房屋所有权二级转移登记</a></li>
			<li><a href="#">房屋所有权三级转移登记</a></li>
			<li><a href="#">房屋所有权注销登记</a></li>
			
		</ul>
	</div>
</div>
    <div class="item">
	<div class="left"><span>海上构筑物</span></div>
	<div class="middle">
	<div class="mid"></div>
	</div>
	<div class="content">
		<ul>
			<li><a href="#">海上构筑物初始登记 </a></li>
			<li><a href="#">海上构筑物转移登记</a></li>
			<li><a href="#">海上构筑物预告登记</a></li>
			<li><a href="#">海上构筑物变更登记</a></li>
			<li><a href="#">海上构筑物抵押登记 </a></li>
			<li><a href="#">海上构筑物注销登记</a></li>
			<li><a href="#">海上构筑物异议登记</a></li>
			<li><a href="#">海上构筑物更正登记</a></li>
			<li><a href="#">海上构筑物证书灭失补发登记</a></li>
		</ul>
	</div>
</div>
    <div class="item">
	<div class="left"><span>草地</span></div>
	<div class="middle">
	<div class="mid"></div>
	</div>
	<div class="content">
		<ul>
			<li><a href="#">草地初始登记 </a></li>
			<li><a href="#">草地转移登记</a></li>
			<li><a href="#">草地预告登记</a></li>
			<li><a href="#">草地变更登记</a></li>
			<li><a href="#">草地抵押登记 </a></li>
			<li><a href="#">草地注销登记</a></li>
			<li><a href="#">草地异议登记</a></li>
			<li><a href="#">草地更正登记</a></li>
			<li><a href="#">草地证书灭失补发登记</a></li>
		</ul>
	</div>
</div>
    <div class="item">
	<div class="left"><span>林地</span></div>
	<div class="middle">
	<div class="mid"></div>
	</div>
	<div class="content">
		<ul>
			<li><a href="#">林地初始登记 </a></li>
			<li><a href="#">林地转移登记</a></li>
			<li><a href="#">林地预告登记</a></li>
			<li><a href="#">林地变更登记</a></li>
			<li><a href="#">林地抵押登记 </a></li>
			<li><a href="#">林地注销登记</a></li>
			<li><a href="#">林地异议登记</a></li>
			<li><a href="#">林地更正登记</a></li>
			<li><a href="#">林地证书灭失补发登记</a></li>
		</ul>
	</div>
</div>
    <div class="item">
	<div class="left"><span>公共</span></div>
	<div class="middle">
	<div class="mid"></div>
	</div>
	<div class="content">
		<ul>
			<li><a href="#">一般抵押权设立登记</a></li>
			<li><a href="#">一般抵押权转移登记</a></li>
			<li><a href="#">查封登记</a></li>
			<li><a href="#">解封登记</a></li>
			<li><a href="#">内部提示性备注</a></li>
			<li><a href="#">注销内部提示性备注</a></li>
			<li><a href="#">司法裁定过户</a></li>
			<li><a href="#">注销裁定过户</a></li>
		</ul>
	</div>
</div> 
     -->
 
	<table id="table_user"  />
	 <div id="tb" style="float:right;">
                <a href="#" id='user_edit' class="plui-linkbutton" iconcls="icon-pencil" plain="true" onclick="javascript:doEdit();">受理</a> 
      </div>
</body>
</html>
