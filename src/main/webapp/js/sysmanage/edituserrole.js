
$(function() {
	//init form
	$('#userrole_edit_form').form({
		url :  ctx+"/sysmanage/user-role-manage!updateUserRole.action",
		success : function(data) {
			//parentWindow
			var data = $.parseJSON(data);
			if (data.success) {
				top.$.messager.alert('��ʾ', data.tipMessage, 'info', function(){
					//�رմ���
					openerWindow.$('#userroleLst').datagrid('reload');
					closeInTopWindow('edit_userrole_win');
				});
			} else {
				top.$.messager.alert('����', data.errorMessage, 'error', function(){});
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
	
	//��ֵ��ֵ���û�form��
	$('#userid').val(singleselected.USER_ID);
	$('#username').val(singleselected.USER_NAME);
	$('#creator').val(singleselected.CREATOR);
	$('#createdate').val(singleselected.CREATEDATE);
	$('#roleid').val(singleselected.ROLEID);
}
function submit(){
	$('#userrole_edit_form').submit();
}

//��ȡϵͳ��ǰʱ��
function getTime(){
	  var myDate = new Date();
	  var year = myDate.getFullYear();
	  var month = myDate.getMonth()+1;
	  var date = myDate.getDate(); 
      var mytime=myDate.toLocaleTimeString();     //��ȡ��ǰʱ��	  
	  var time = year+"-"+month+"-"+date+" "+mytime;
	  return time;
	}
