/**
 *合同信息
 * 
 */
var proc_id = '1000016552';
$(function(){
	
	$.ajax({
		url:ctx+'/common/certificate!getContractInfo.action?proc_id='+proc_id,
		type:'post',
		dataType:'json',
		success:function(data){
			$('#table_contract').form('load',data);
			
			$('#BUILDING_NAME').text(data.BUILDING_NAME);
			$('#PARCEL_CODE').text(data.PARCEL_CODE);
		}
	});
	//$('#table_contract').form('load',ctx+'/common/certificate!getContractInfo.action?proc_id='+proc_id);
});

