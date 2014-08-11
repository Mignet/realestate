/*********************************************************************************
*��  ��  ��  ��: recmaterial_rel_img.js
*��  ��  ��  ��: ɨ�����������
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Joyon
*��  ��  ��  �ڣ� 2014-03-01
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
var proc_id;			//����ʵ��ID
var receival_id;		//����ID

//�����ڴ������Ĳ���
var obj = window.dialogArguments;

window.onload = function(){
	//alert("�����ݵĲ���Ϊ��" + JSON.stringify(obj));
	init(obj);
	
	//window.returnValue
}

function init(data){
	//alert('procid:'+data.proc_id);
	//proc_id = data.proc_id;
	proc_id = '1000017797';
	receival_id = data.RECEIVAL_ID;
	//alert(receival_id);
	$.ajax({
		type:"post",
		data:{"proc_id":proc_id},
		dataType:"json",
		url:ctx+'/common/recmaterial!getScannerInfo.action',
		success:function(data){
			//alert(JSON.stringify(data));
			//data = data.rows;
			
			for(var i=0;i<data.length;i++){
				var tempData = data[i];
				var scanner_id = tempData.SCANNER_ID;
				$('#divImg').append("<span  style='width:200px;height190px;padding:5px;'><img title='����鿴��ͼ'  style='width:200px;height:160px;border: 1;border-style: solid;border-color:#aaa;' src="+ctx+data[i].DIRECTORY+" onclick=img_click('"+ctx+data[i].DIRECTORY+"')><input name='cbx' id='"+scanner_id+"' onclick='cbxClick(this)' type='checkbox'  style='margin-right:20px; z-index:100'/></span>");
					//alert(ctx+data[i].DIRECTORY);
				//�����ǰɨ����Ĺ�������ID���ڵ�ǰ���ϵ�ID  ��ѡ��
				if(tempData.RECEIVAL_ID == receival_id){
					var scanner = document.getElementById(scanner_id);
					$(scanner).attr("checked","chedked");
					//�����ǰɨ������˹���ID  ���Ҳ��ǵ�ǰ����ID  ����øø�ѡ��  ���������
				}else if(tempData.RECEIVAL_ID){
					var scanner = document.getElementById(scanner_id);
					$(scanner).attr("disabled","disabled");
					$(scanner).attr("checked","chedked");
				}
			}
			
			/*
			$("img").click(function(){
				//alert('img click,,,');
				$(this).css("position:absolute;left:0px;top:0px;index:101");
				$(this).css("height",$(this).css("height")==="160px"?"600px":"160px");
				$(this).css("width",$(this).css("width")==="200px"?"800px":"200px");
				//onclick=img_click('"+ctx+data[i].DIRECTORY+"')
			});
			*/
		}
		
	});
	
	
	$('#submit').click(function(){
		submit();
	});
	
	$('#div_img_detail').click(function(){
		if($('#div_img_detail').css("visibility") == "visible"){
			$('#div_img_detail').css("visibility","hidden");
			$('#divImg').css("visibility","visible");
		}
	});
}


function img_click(data){
	
	//alert(data);
	$('#img_detail').attr("src",data);
	$('#divImg').css("visibility","hidden");
	$('#div_img_detail').css("visibility","visible");
	

}

function submit(){
	this.window.close();
}

function cbxClick(cbx){
	var obj={
			url:ctx+'/common/recmaterial!scannerRelImg.action',
			dataType:"json",
			type:"post",
			data:{"receival_id":receival_id,"scanner_id":cbx.id},
			success:function(data){
				
			}
	}
	if($(cbx).attr("checked")){
		
	}else{
		obj.data.receival_id="";
	}
	
	$.ajax(obj);
	
}
