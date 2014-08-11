/*********************************************************************************
*文  件  名  称: recmaterial_rel_img.js
*功  能  描  述: 扫描件关联材料
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Joyon
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
var proc_id;			//流程实例ID
var receival_id;		//材料ID

//父窗口传过来的参数
var obj = window.dialogArguments;

window.onload = function(){
	//alert("您传递的参数为：" + JSON.stringify(obj));
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
				$('#divImg').append("<span  style='width:200px;height190px;padding:5px;'><img title='点击查看大图'  style='width:200px;height:160px;border: 1;border-style: solid;border-color:#aaa;' src="+ctx+data[i].DIRECTORY+" onclick=img_click('"+ctx+data[i].DIRECTORY+"')><input name='cbx' id='"+scanner_id+"' onclick='cbxClick(this)' type='checkbox'  style='margin-right:20px; z-index:100'/></span>");
					//alert(ctx+data[i].DIRECTORY);
				//如果当前扫描件的关联材料ID等于当前材料的ID  则选中
				if(tempData.RECEIVAL_ID == receival_id){
					var scanner = document.getElementById(scanner_id);
					$(scanner).attr("checked","chedked");
					//如果当前扫描件有了关联ID  并且不是当前材料ID  则禁用该复选框  避免误操作
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
