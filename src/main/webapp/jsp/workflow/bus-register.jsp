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
span{color:#154a75;font-family:΢���ź�;font-size:8pt;}
a{color:#154a75;font-family:΢���ź�;font-size:8pt;}
a:link{color:#154a75;font-weight:400;text-decoration:none}
a:hover{color:#66f;font-weight:400;text-decoration:underline}
ul{margin:0 0 0 22px; padding:0;list-style:none;}
li{margin:0; padding:0;}
</style>
        
</head>
<body>
	<!-- ��ʼ��ѯ�������ڿ�������ѯ���ĳ�ʼ״̬ ע�������������ʽsearchrow��
        3. ��
    -->	   
     <!-- �Ա���ÿ���ؼ�Ԫ�أ������������Ҳ���PLUIcombo�����������Ӧ������ʽ"combo"��
        	 �Ա���ÿ���ؼ�Ԫ�أ������combo�������Ӧ��������"width="80""��
        
    <form id="simpleform" class="searchrow" method="post">    	
    	<input type="hidden" name="queryCondition" value="user_name like ?"/>
       
        &nbsp;������<input type="text" name="user_name" class="combo" />
        <a class="plui-linkbutton" href="javascript:;" iconCls='icon-search' onclick="submit1();">��ѯ</a>
    </form> --> 
    <!-- ��������ѯ��ʹ�õı�ǩ -->        
    <div id="searchpanel"></div>
   <!--  
    <div class="item">
	<div class="left"><span>����</span></div>
	<div class="middle">
	<div class="mid"></div>
	</div>
	<div class="content">
		<ul>
			<li><a href="#">����ʹ��Ȩ��ʼ�Ǽ�</a></li>
			<li><a href="#">����ʹ��Ȩ����ת��</a></li>
			<li><a href="#">����ʹ��Ȩ����ת��</a></li>

		</ul>
	</div>
</div>
    <div class="item">
	<div class="left"><span>����</span></div>
	<div class="middle">
	<div class="mid"></div>
	</div>
	<div class="content">
		<ul>
			<li><a href="#">��������Ȩ��ʼ�Ǽ�</a></li>
			<li><a href="#">��������Ȩ����Ǽ�</a></li>
			<li><a href="#">��������Ȩ����ת�ƵǼ�</a></li>
			<li><a href="#">��������Ȩ����ת�ƵǼ�</a></li>
			<li><a href="#">��������Ȩע���Ǽ�</a></li>
			
		</ul>
	</div>
</div>
    <div class="item">
	<div class="left"><span>���Ϲ�����</span></div>
	<div class="middle">
	<div class="mid"></div>
	</div>
	<div class="content">
		<ul>
			<li><a href="#">���Ϲ������ʼ�Ǽ� </a></li>
			<li><a href="#">���Ϲ�����ת�ƵǼ�</a></li>
			<li><a href="#">���Ϲ�����Ԥ��Ǽ�</a></li>
			<li><a href="#">���Ϲ��������Ǽ�</a></li>
			<li><a href="#">���Ϲ������Ѻ�Ǽ� </a></li>
			<li><a href="#">���Ϲ�����ע���Ǽ�</a></li>
			<li><a href="#">���Ϲ���������Ǽ�</a></li>
			<li><a href="#">���Ϲ���������Ǽ�</a></li>
			<li><a href="#">���Ϲ�����֤����ʧ�����Ǽ�</a></li>
		</ul>
	</div>
</div>
    <div class="item">
	<div class="left"><span>�ݵ�</span></div>
	<div class="middle">
	<div class="mid"></div>
	</div>
	<div class="content">
		<ul>
			<li><a href="#">�ݵس�ʼ�Ǽ� </a></li>
			<li><a href="#">�ݵ�ת�ƵǼ�</a></li>
			<li><a href="#">�ݵ�Ԥ��Ǽ�</a></li>
			<li><a href="#">�ݵر���Ǽ�</a></li>
			<li><a href="#">�ݵص�Ѻ�Ǽ� </a></li>
			<li><a href="#">�ݵ�ע���Ǽ�</a></li>
			<li><a href="#">�ݵ�����Ǽ�</a></li>
			<li><a href="#">�ݵظ����Ǽ�</a></li>
			<li><a href="#">�ݵ�֤����ʧ�����Ǽ�</a></li>
		</ul>
	</div>
</div>
    <div class="item">
	<div class="left"><span>�ֵ�</span></div>
	<div class="middle">
	<div class="mid"></div>
	</div>
	<div class="content">
		<ul>
			<li><a href="#">�ֵس�ʼ�Ǽ� </a></li>
			<li><a href="#">�ֵ�ת�ƵǼ�</a></li>
			<li><a href="#">�ֵ�Ԥ��Ǽ�</a></li>
			<li><a href="#">�ֵر���Ǽ�</a></li>
			<li><a href="#">�ֵص�Ѻ�Ǽ� </a></li>
			<li><a href="#">�ֵ�ע���Ǽ�</a></li>
			<li><a href="#">�ֵ�����Ǽ�</a></li>
			<li><a href="#">�ֵظ����Ǽ�</a></li>
			<li><a href="#">�ֵ�֤����ʧ�����Ǽ�</a></li>
		</ul>
	</div>
</div>
    <div class="item">
	<div class="left"><span>����</span></div>
	<div class="middle">
	<div class="mid"></div>
	</div>
	<div class="content">
		<ul>
			<li><a href="#">һ���ѺȨ�����Ǽ�</a></li>
			<li><a href="#">һ���ѺȨת�ƵǼ�</a></li>
			<li><a href="#">���Ǽ�</a></li>
			<li><a href="#">���Ǽ�</a></li>
			<li><a href="#">�ڲ���ʾ�Ա�ע</a></li>
			<li><a href="#">ע���ڲ���ʾ�Ա�ע</a></li>
			<li><a href="#">˾���ö�����</a></li>
			<li><a href="#">ע���ö�����</a></li>
		</ul>
	</div>
</div> 
     -->
 
	<table id="table_user"  />
	 <div id="tb" style="float:right;">
                <a href="#" id='user_edit' class="plui-linkbutton" iconcls="icon-pencil" plain="true" onclick="javascript:doEdit();">����</a> 
      </div>
</body>
</html>
