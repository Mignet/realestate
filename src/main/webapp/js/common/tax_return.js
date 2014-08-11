//var proc=this.parent.proc;
//   //Á÷³ÌÊµÀýID
//var proc_id = proc.procId;

var proc_id = '1000016786';

$(function(){
	$.ajax({
		url:ctx+'/common/certificate!getDeclarationFormInfo.action',
		data:{"proc_id":proc_id},
		dataType:"json",
		type:"post",
		success:function(data){
			//alert(JSON.stringify(data));
			$('#divMain').form("load",data);
		}
		
	});
	
	$('#sendTax').click(function(){
		sendTax();
	});
	
});

function sendTax(){
	//alert("sencTax...");
	$.ajax({
		url:ctx+'/common/certificate!sendLandTax.action',
		data:{"proc_id":proc_id},
		dataType:"json",
		type:"post",
		success:function(data){
			alert(JSON.stringify(data));
			//$('#divMain').form("load",data);
		}
		
	});
	
}
