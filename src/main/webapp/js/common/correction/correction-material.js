/*********************************************************************************
*��  ��  ��  ��: correction-material.js
*��  ��  ��  ��: ��������js
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Joyon
*��  ��  ��  �ڣ� 2014-03-01
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
//var proc=this.parent.proc;
var proc={};										//��Ҫ��������ҵ�������
var cur_proc = this.parent.proc;				//��ǰ��������
var proc_id = cur_proc.procId;					
var procNode = cur_proc.activName;

window.onload = init;
/**********************************************************************************
*��������: init
*����˵��: ���ݳ�ʼ��
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014��5��27�� 18:27:12
*�޸���ʷ: 
***********************************************************************************/
function init(){
	proc = getLastProcdata();
	
	//��iframeǶ��Ӽ����ϱ�
	var appendStr = '<iframe id="rec_iframe" src="'+ctx+'/jsp/common/recmaterial/recmaterial.jsp" allowtransparency="true" width="100%" height="100%" frameborder="0"></iframe>';
	
	$("#div_iframe").html(appendStr);
}
/**********************************************************************************
*��������: getLastProcdata
*����˵��: ��ȡ��Ҫ  ����ҵ��  ����������  �ڲ���ʱ  �Ӽ�������Ҫ�õ����proc����  
*����˵��: 
*�� �� ֵ: proc
*��������: Joyon
*��������: 2014��5��27�� 18:27:12
*�޸���ʷ: 
***********************************************************************************/
function getLastProcdata(){
	alert();
	var lastProc = {};
	lastProc.procId="1000017642";
	lastProc.activName="����";
	//lastProc.proc_node="����";
	 $.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/recmaterial!getLastProcid.action?time="+new Date(),
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//�������л�����
	   		data:{"proc_id":proc_id},
	   		async:false,
	   		success:function(data){
	   			if(data){
	   				lastProc.procId = data.proc_id; 
	   			}
	   		},error:function(data){
	   			
	   		}
	   	});
	return lastProc;
	 
}

function submit(){
	return true;
}
/**********************************************************************************
*��������: pageDataIsChange
*����˵��: �жϵ�ǰҳ�������Ƿ��Ѿ��޸�
*����˵��: 
*�� �� ֵ: �޸ķ���true   δ�޸ķ���false
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function pageDataIsChange(){
	if(proc_node == state1.string0){
		var tmpChangeData = $('#table_rec').datagrid("getChanges");
		if(tmpChangeData.length>0){
			return true;
		}
	}
	//�����ȷ���  ҳ������δ�޸�  ����false
	return false;
}


