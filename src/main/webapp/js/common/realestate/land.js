var row;
//初始化加载.	
proc=this.parent.proc;
var proc_id = proc.procId;
//var proc_id = '${procId}';
//var proc_id = 1000016990;
$(function(){
	//初始化宗地信息表
	$(":input").attr("disabled", "disabled");
	$.ajax({
		    dataType: 'json',
			url:"../certificate!getLandById.action?time="+new Date()+"&proc_id="+proc_id,
			//data:{"proc_id":proc_id,"time":new Date()},
			success:function(data){
			 	if(data){
			 		
			 		$('#tab_land').form("load",data);
			 	}
			}
		});
	
	
});//初始化结束
/**********************************************************************************
*函数名称: 页面校验方法
*功能说明: 验证页面上的非空  及数据格式   
*参数说明: 
*返 回 值: obj  result(true通过  false不通过) message(消息)  page_name(当前页面名字)
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function validate(){
	//返回结果对象
	var result ={
			result:true,
			message:'',
			page_name:'宗地信息表'
	}
	return result;
}
function submit(){
	return true;
}
