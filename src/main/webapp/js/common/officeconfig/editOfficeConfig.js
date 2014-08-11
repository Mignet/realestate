
var form;
function init(user){
	//alert(JSON.stringify(user));
	$.ajax({
		url : ctx+"/common/certificate!getBusType.action?time="+new Date(),
		type : 'post',
		dataType : 'json',
		success : function(data) {
			for(var i=0;i<data.length;i++){
			$("#selectbustype").append("<option  value='"+data[i].bustype+"'>"+data[i].busname+"</option>")					
			}	
			
			var t = document.getElementById("selectbustype"); 
			 var selectValue = user.BUS_TYPE_ID;
			  //alert(t1);
			  for(i=0;i<t.length;i++){//给select赋值
				  if(selectValue==t.options[i].value){
					  t.options[i].selected=true;
				  }
				
			  }
		}
	});
	form=user;
	
	//$("#selectbustype").
	//document.getElementById("selectbustype").value=user.BUS_TYPE_ID;
	//$("#selectbustype").val(user.BUS_TYPE_ID);
	//alert(document.getElementById("selectbustype").value);
	//alert(JSON.stringify(user));
	//$("#selectbustype").val(user.BUS_TYPE_ID);
	
	
	 
	 
	  
	
	if(user.OFFICE_TYPE=="表单")
	{
		$("#selecttype").val("0");
	}
	else
	{
		$("#selecttype").val("1");
	}
	$("#name").val(user.OFFICE_NAME);
	$("#url").val(user.OFFICE_URL);
	
};

function submit(){
	var bustype= $("#selectbustype").val();
	
	var type=$("#selecttype").val();
	var name= $("#name").val();
	var url=$("#url").val();
	var officeid=form.OFFICE_ID;
	$.ajax({
			url : ctx+"/common/certificate!updateForm.action?time="+new Date(),
			type : 'post',
			data : {"formname":name,"formurl":url,"bus_typeid":bustype,"formtype":type,"officeid":officeid},
			dataType : 'json',
			success : function(data) {
				//alert(data.success);
				if(data.success==true)
				{
					top.$.messager.alert('提示', '更新成功！', 'info',
					function() {
						args.userDataGrid.datagrid('reload');
						closeInTopWindow('edit_user_win');
					});	
				}
				
			}
	});
}
