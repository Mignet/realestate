var row;
//��ʼ������.	
proc=this.parent.proc;
var proc_id = proc.procId;
//var proc_id = '${procId}';
//var proc_id = 1000016990;
$(function(){
	//��ʼ���ڵ���Ϣ��
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
	
	
});//��ʼ������
/**********************************************************************************
*��������: ҳ��У�鷽��
*����˵��: ��֤ҳ���ϵķǿ�  �����ݸ�ʽ   
*����˵��: 
*�� �� ֵ: obj  result(trueͨ��  false��ͨ��) message(��Ϣ)  page_name(��ǰҳ������)
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function validate(){
	//���ؽ������
	var result ={
			result:true,
			message:'',
			page_name:'�ڵ���Ϣ��'
	}
	return result;
}
function submit(){
	return true;
}
