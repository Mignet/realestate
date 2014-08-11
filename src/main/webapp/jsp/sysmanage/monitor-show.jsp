<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <title>API列表-深圳市不动产登记系统</title>
    <%@ include file="/base/taglibs.jsp"%>
    <%@ include file="/base/prefix.jsp"%>
    <script type="text/javascript">
    	var ctx = '${ctx}';
    	function showDetails(sid,sname,info){
    		$.ajax({
		   		dataType:'json',
		   		url:ctx+"/sysmanage/monitor!getAllMethodsBySID.action?time="+getDateStr(),
		   		type : 'post',
		   		//表单的序列化操作
		   		data:{"sid":sid},
		   		success:function(data){
				 	if(data){
				 		$('#details').empty();
				 		$('#details').append('<div class="live" style="width:100%">'+
				 				'<h4>'+sname+'</h4>'+
				 				'<h4 class="code"> '+info+' </h4>'+
				 			'</div>');
				 		$('#details').append('<tr>'+
				 				'<th align="left" width="20%"><h5>方法名</h5></th>'+
				 				'<th align="left" width="25%"><h5>参数</h5></th>'+
				 				'<th align="left" width="25%"><h5>返回类型</h5></th>'+
				 				'<th align="left" width="50%"><h5>描述</h5></th>'+
				 				'</tr>');
				 		for(var i=0;i<data.length;i++){
					 		$('#details').append("<tr>"+
					 		"<td><span>"+data[i].mname+"</span></td>"+
					 		"<td><span>"+data[i].parameters+"</span></td>"+
					 		"<td><span>"+data[i].returntype+"</span></td>"+
					 		"<td><span>"+data[i].info+"</span></td>"+
					 		"</tr>");
				 		}
				 		/* top.$.messager.alert('成功提示',"获取成功",'info',function(){
						});	 */
				 	}else {
						top.$.messager.alert('失败提示',"获取失败",'error');
					}
		   		},error:function(data){
		   			top.$.messager.alert('失败提示',"获取失败",'error');
		   		}
		   	});  
    	}
    	function editAPI(){
    		window.open(ctx+'/sysmanage/monitor!edit.action');
    	}
    </script>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/live-preview.css">
</style>
</head>
<body>
	<div style="padding: 10px 100px 10px 100px;">
		<h4>深圳市不动产登记系统<a href="#" onclick="editAPI()">[编辑]</a></h4>
		<h4 class="code"> API 1.0 列表 </h4>
		<hr>
	</div>
	<div style="padding: 10px 100px 10px 100px;">
		<div class="live">
		<table style="border: 1px solid #DBDBDB;">
			<tr>
				<th align="left" width="20%"><h5>接口类别</h5></th>
				<th align="left" width="25%"><h5>接口名</h5></th>
				<th align="left" width="50%"><h5>描述</h5></th>
			</tr>
			<c:forEach items="${serviceList}" var="sp">
			<tr>
				<td><span>登记业务</span></td>
				<td><a onclick="showDetails('${sp.sid}','${sp.sname}','${sp.info}')">${sp.sname}</a></td>
				<td><span>${sp.info}</span></td>
			</tr>
			</c:forEach>
		</table>
		</div>
		<div class="live">
		<table id="details"  style="border: 1px solid #DBDBDB;">
		</table>
		</div>
	</div>
</body>
</html>
