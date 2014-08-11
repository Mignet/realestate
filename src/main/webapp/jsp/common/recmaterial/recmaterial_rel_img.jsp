<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>材料关联扫描件信息</title>

	<%@ include file="/base/taglibs.jsp"%>
	<%@ include file="/base/prefix.jsp"%>
	<script type="text/javascript" src="${ctx}/js/common/recmaterial/recmaterial_rel_img.js"></script>	
	<script type="text/javascript"> 	
	 	var ctx = '${ctx}';	 
	</script>
	
	<style type="text/css">
		.foot{
			width:100%;
			bottom:0px;
			position:absolute;
			text-align:center;
		}
		#div_img_detail{
			visibility:hidden;
			width:100%;
			height:100%;
			position:absolute;
			left:0px;
			top:0px;
			z-index:101
		}
		
		.divImg {
		width:90px;height:90px;display:block;margin-top:0px;margin-left:0px;border: 1;border-style: solid;
		}

	</style>
</head>
<body>
	
	
	<div id='divImg'>
	
	</div>
	<div class='foot'>
		<input id='submit' type="button" value="确定"/>
	</div>
	<div id='div_img_detail' style="">
		<img title='点击返回关联页面' id="img_detail" style="width:100%;height:100%">
	</div>
	
</body>
</html>
