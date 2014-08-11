$(function() {
	//alert();	
	//init form
	$('#product_edit_form').form({
		url :  ctx+"/common/configur!updateMenu.action?time="+new Date(),
		success : function(data) {
			//parentWindow
			var data = $.parseJSON(data);
			if(data.success){
				var parentId = $('#parent_id').combotree('getValue');
				top.$.messager.alert('提示', data.tipMessage, 'info', function(){
					
					if (parentId == '-1') {
						openerWindow.$('#productList').treegrid('reload');
						closeInTopWindow('edit_product_win');
					} else {
						var node = openerWindow.$('#productList').treegrid('find', parentId);
						if (node) {
							var children = openerWindow.$('#productList').treegrid('getChildren', parentId);
							if (node.state == 'open') {
								if (children == '') {
									if (node.parent_id == '-1') {
										openerWindow.$('#productList').treegrid('reload');
										closeInTopWindow('edit_product_win');
									} else {
										openerWindow.$('#productList').treegrid('reload', node.parent_id);
										closeInTopWindow('edit_product_win');
									}
								} else {
									openerWindow.$('#productList').treegrid('reload', parentId);
									closeInTopWindow('edit_product_win');
								}
							} else {
								openerWindow.$('#productList').treegrid('reload', parentId);
								closeInTopWindow('edit_product_win');
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


function submit(){
	
	$("#cjsj").val(getTime());
	//alert(user);
	
	$("#cjr").val(user);
	
	
	$("#mc1").val($("#mc").val());
	
	$("#ss1").val($("#parent_id").combodict('getValue'));
	$("#url1").val($("#url").val());	
	$("#cjr1").val($("#cjr").val());
	$("#cjsj1").val($("#cjsj").val());
	$("#id1").val($("#id").val());
	$("#menu_order1").val($("#menu_order").val());
	
	$('#product_edit_form').submit();
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
