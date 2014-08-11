function init(selectedNode){
	$.ajax({
		url : ctx+"/common/certificate!getBusType.action?time="+new Date(),
		type : 'post',
		dataType : 'json',
		success : function(data) {
			//alert(data[i].bustype);
			for(var i=0;i<data.length;i++){
			$("#selectbustype").append("<option  value='"+data[i].bustype+"'>"+data[i].busname+"</option>")
					
			}		
			
			var t = document.getElementById("selectbustype"); 
			 var selectValue = selectedNode.iconCls;
			  //alert(t1);
			  for(i=0;i<t.length;i++){//给select赋值
				  if(selectValue==t.options[i].value){
					  t.options[i].selected=true;
				  }
				
			  }
		}
	});
}



//保存
function submit(){	
	var bustype= $("#selectbustype").val();
	var type=$("#selecttype").val();
	var name= $("#name").val();
	var url=$("#url").val();
	$.ajax({
			url : ctx+"/common/certificate!saveForm.action?time="+new Date(),
			type : 'post',
			data : {"formname":name,"formurl":url,"bus_typeid":bustype,"formtype":type},
			dataType : 'json',
			success : function(data) {
				//alert(data.success);
				if(data.success==true)
				{
					top.$.messager.alert('提示', '保存成功！', 'info',
					function() {	
						args.userDataGrid.datagrid('reload');
						closeInTopWindow('add_user_win');
					});	
				}
				
			}
	});
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

