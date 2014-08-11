
$(function() {
	//init form
	$('#userrole_edit_form').form({
		url :  ctx+"/sysmanage/user-role-manage!updateUserRole.action",
		success : function(data) {
			//parentWindow
			var data = $.parseJSON(data);
			if (data.success) {
				top.$.messager.alert('提示', data.tipMessage, 'info', function(){
					//关闭窗口
					openerWindow.$('#userroleLst').datagrid('reload');
					closeInTopWindow('edit_userrole_win');
				});
			} else {
				top.$.messager.alert('错误', data.errorMessage, 'error', function(){});
			}
		}
	});
	
	
});
function init(singleselected){
	if(singleselected.CREATOR == "")
       $('#trcreator').hide();
	if(singleselected.CREATEDATE == "")
		$('#trcreatedate').hide();
	$('#roleid').combotree({   
       url: ctx + "/sysmanage/role-manage!getAllRoles2GrowTreeByUserId.action?userid="+singleselected.USER_ID    
    });
	
	//将值赋值到用户form里
	$('#userid').val(singleselected.USER_ID);
	$('#username').val(singleselected.USER_NAME);
	$('#creator').val(singleselected.CREATOR);
	$('#createdate').val(singleselected.CREATEDATE);
	$('#roleid').val(singleselected.ROLEID);
}
function submit(){
	$('#userrole_edit_form').submit();
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
