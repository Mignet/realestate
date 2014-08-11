function init(productId) {
		$.ajax({
			url:ctx+"/common/configur!getCount.action?time="+new Date(),
			data:{"menu.parent_id":productId},
			dataType:"json",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			success:function(data){
				if(data.count){
					//alert(JSON.stringify(data));
					var count = parseInt(data.count)+1;
					$("#menu_order").val(count);
				}else{
					$("#menu_order").val('1001');
				}
			}
		});
}





$(function() {
	//init form
	$("#create_date").datetimebox('setValue',getTime());
	$("#creator").val(user);	
	$('#product_add_form').form({
		url : ctx+"/common/configur!saveMenu.action?time="+new Date(),
		success : function(data) {
			//parentWindow
			var data = $.parseJSON(data);
			if (data.success) {
				//top.$.messager.alert('提示', data.tipMessage, 'info', function(){});
				var parentId = $('#parent_id').combotree('getValue');
			//	openerWindow.$('#productList').treegrid('reload');
				
					//closeInTopWindow('add_product_win');
					
					
				top.$.messager.alert('提示', data.tipMessage, 'info', function(){
							
					if (parentId == '-1') {
						openerWindow.$('#productList').treegrid('reload');
						closeInTopWindow('add_product_win');
					} else {
						var node = openerWindow.$('#productList').treegrid('find', parentId);
						if (node) {
							var children = openerWindow.$('#productList').treegrid('getChildren', parentId);
							if (node.state == 'open') {
								if (children == '') {
									if (node.parent_id == '-1') {
										openerWindow.$('#productList').treegrid('reload');
										closeInTopWindow('add_product_win');
									} else {
										openerWindow.$('#productList').treegrid('reload', node.parent_id);
										closeInTopWindow('add_product_win');
									}
								} else {
									openerWindow.$('#productList').treegrid('reload', parentId);
									closeInTopWindow('add_product_win');
								}
							} else {
								openerWindow.$('#productList').treegrid('reload', parentId);
								closeInTopWindow('add_product_win');
							}
						}
					}
				});
			} else {
				top.$.messager.alert('错误', data.errorMessage, 'error', function(){});
			}
		}
	});
});

//保存操作
function submit(){
	$('#product_add_form').submit();
}

//获取系统当前时间
function getTime(){
	  var myDate = new Date();
	  var year = myDate.getFullYear();
	  var month = myDate.getMonth()+1;
	  var date = myDate.getDate(); 
     var mytime=myDate.toLocaleTimeString();     //获取当前时间	  
	var time = year+"-"+month+"-"+date+" "+mytime;
	return time;
	
	}


