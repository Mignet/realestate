var obj;
var data;
var openerWinssssdow;
function init(particpants){
	$("#divParticpants").empty();
	obj=particpants;
	data=eval(obj.data);	
	for(var i=0;i<data.length;i++){
		$("#divParticpants").append("<input type='radio' class='"+data[i].partiName+"' id='"+data[i].partiId+
				"' value='"+data[i].partiType+"' name='radio' /> <label for='"+data[i].partiId+"'>"+data[i].partiName+" </label> </br>");		
	}
	
}

//提交按钮
function submit(){
	if(obj.activName=="核准"){
//		$.messager.confirm('登簿提示','登簿之后将无法进行回退，是否登簿？',function(r){  
//		    if (r){ 
		    		//var tt=registerSave();//openerWinssssdow.doDb();
		     		//if(tt=="success"){
		     			//alert(tt);
		     			selectsubmit();		     			
		     		/*}
		     		else
		     		{
		     			//alert(tt);
		     			top.$.messager.alert('提示', '提交失败！', 'info',
						function() {								
						//关闭上一页面
						closeInTopWindow('open_app','destroy');
						//关闭当前页面
						closeInTopWindow('particiPants','destroy');
						openerWinssssdow.openerWindow.location.reload();
						});	
		     		}*/
		     		
		   // }
		  //});
  	}
	else
	{
	selectsubmit();
	}
	
	
	
}


//提交操作
function selectsubmit(){

var particpants=new Array();
	var j=0;
	//alert(JSON.stringify(data));
	for(var i=0;i<data.length;i++){		
		var partiId=document.getElementById(data[i].partiId);
		if($(partiId).attr("checked")=="checked"){
			particpants[j]={"partiId":data[i].partiId,"partiType":data[i].partiType,"partiName":data[i].partiName};
			j++;
		}		
	}
	var state;
	state=obj.state;
	//alert(state);
	//alert(state);
	//alert(JSON.parse(obj.data));
	if(particpants.length>0)
	{
		particpants=JSON.stringify(particpants);
		//alert(JSON.stringify(particpants));
		var objs={
			url:ctx+"/workflow/workflow!WorkItemNextParamWithFacade.action",
			type:"post",
			dataType:"json",
			async:false,
			data:{'particpants':particpants,'wiId':obj.wiId,'procId':obj.procId,'state':state,'code':j},
			//dataType:"",
			success:function(data){	
			//alert(data);
			//closeInTopWindow("particiPants");
				if(data.sign=="success"){	
				    top.$.messager.alert('提示', '提交成功！', 'info',
					function() {						
					//关闭上一页面
					closeInTopWindow('open_app','destroy');
					//关闭当前页面
					closeInTopWindow('particiPants','destroy');
					});	
				}
				else if(data.sign=="failed"){
				top.$.messager.alert('提示', '流程已经提交！', 'info',
					function() {									
					//关闭上一页面
					closeInTopWindow('open_app','destroy');
					//关闭当前页面
					closeInTopWindow('particiPants','destroy');
					});	
				}
			}
		};$.ajax(objs);
		openerWinssssdow.openerWindow.location.reload();
	}
	else
	{
		top.$.messager.alert('提示', '请选择提交人！', 'info',
				function() {						
				//关闭当前页面
				//closeInTopWindow('particiPants','destroy');
				});	
	}
}

//登记簿预览功能
function registerSave () {		
	var proc = openerWinssssdow.proc;
	lcslId = proc.procId;
	djbh = openerWinssssdow.djbh;
	//alert(lcslId);
	var result="failed";
	
	$.ajax({
		url:ctx+"/common/certificate!registerSave.action",
		data:{"lcslbid":lcslId,"djbh":djbh,"procdefId":proc.procdefId,"time":new Date()},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		dataType:"json",
		async:false,
		success:function(data){
			result=data.result;
		}
	});
	return result;
}

