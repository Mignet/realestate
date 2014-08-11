/*********************************************************************************
*文  件  名  称:dispatch.js
*功  能  描  述: 发文功能
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: panda
*创  建  日  期： 2014-02-28
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
var proc=this.parent.proc;
var proc_node = proc.activName;//获取流程名称
var proc_id = proc.procId;//获取流程节点id
//主键值数组，记录复选框选中的行（包括翻页）主键值
window.seq_id_set = [];
/**********************************************************************************
*函数名称: 
*功能说明: 页面初始化
*函数作者: panda
*创建日期: 2014-02-28
*修改历史: 
***********************************************************************************/
$(function(){
	
	
	//创建登记信息表
	var userDataGrid = $('#table_djxx').datagrid({
		title:'登记信息',
		//fit:true,
		height:300,
		//表格数据来源
		url:ctx+"/mortgage/morsetup!getRegunitMess.action?time="+new Date()+"&proc_id="+proc_id,
		//表格每列宽度自动适应表格总宽度
		fitColumns: true,
		//去掉边框
		border:true,
		//是否有翻页栏
		pagination:true,
		//每页行数
		pageSize:20,
		//是否在最左增加一列显示行号的列
		rownumbers:true,
		//主键值所在行。在使用复选框时必须设置此项。
		idField: 'CODE',
		//表格的行是否交替显示不同背景色
		striped:true,
		//只允许单选一行
		singleSelect:false,
		//是否在点选表中一行时同时选中复选框
		checkOnSelect:false,
		//是否在选中复选框时同时点选表中一行
		selectOnCheck:true,
		//列属性设置
		columns:[[
			//每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
			{field:'ck',checkbox:true},
			{
     			title : '宗地号',
     			field : 'PARCEL_CODE',
     			width:80
     			
     		}, {
     			title : '宗地坐落',
     			field : 'LAND_ADDRESS',
     			width:100
     			
     		},
     		
     		
     		{
     			title : '建筑物名称',
     			field : 'BUILDING_NAME',
     				width:100
     		}, 
     		{
     			title : '栋号',
     			field : 'BUILD_NO',
     			width:50
     			
     		}, 
     		{
     			title : '房号',
     			field : 'ROOMNAME',
     			width:50
     			
     		},  {
     			title : '项目名称',
     			field : 'PRO_NAME',
     			width:100
     			
     		}, {
     			hidden: true,
     			field : 'TYPE',
     			width:100
     			
     		}, {
     			hidden: true,
     			field : ' ',
     			width:100
     			
     		},
			
		]],
 		onLoadSuccess : function() {
 			
 			//初始化领证人信息
 			getDis();
 		}
		
		
		
	});//加载表格结束
	
	
	//判断领证日期是否存在，若不存在,设置初始值
	
	if($.trim($("#rec_date").val()).length == 0){
	   //$("#rec_date").val(getTime()); 
	  
	}
	
			
}); //初始化结束
/**********************************************************************************
*函数名称: submit
*功能说明: 保存发证信息
*参数说明: 无
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function submit() {
	var result = true;
	var rows = $('#table_djxx').datagrid('getSelections');
	//循环给提交删除参数赋值(音乐风格编码)
	
	//alert(JSON.stringify(rows));
	rows = $.toJSON(rows);
	//alert(rows.length);
    if(rows.length <= 2){
		top.$.messager.alert('正确提示', '请选择需要发证的登记单元', 'info',
				function() {
					
					
				});
		return false;
		
	}
    //获取界面信息 
	var rec_person = $("#rec_person").val();
	var rec_type = $("#rec_type").combodict('getValue');
	var rec_cer_type = $("#rec_cer_type").combodict('getValue');
	var rec_cer_no = $("#rec_cer_no").val();
	var rec_date = $("#rec_date").val();
	//验证非空信息
	if($.trim(rec_person).length==0 || $.trim(rec_cer_no).length==0){
		top.$.messager.alert('提示', '必填项不能为空，请输入！', 'info',
				function() {					
		});	
		return false;
	} 
	if($.trim(rec_person).length!=0 && $.trim(rec_cer_no).length!=0){
		
		//设置领证人日期
		$("#rec_date").val(getTime());
		rec_date = $("#rec_date").val();
		//设置领证人、证件编号为不能操作
		$("#rec_cer_no").attr("disabled", "disabled");
		$("#rec_person").attr("disabled", "disabled");
		$('#rec_type').combo('disable');
		$("#rec_cer_type").combo('disable');
		
		
	}
	$.ajax({
		dataType : 'json',
		url:ctx+"/mortgage/morsetup!saveDispathcInfo.action",
		contentType : "application/x-www-form-urlencoded; charset=GBK",
		//data:{"regUnitList":rows,"proc_id":proc_id},
		data:{"regUnitList":rows,"proc_id":proc_id,"cer.rec_person":rec_person,"cer.rec_cer_no":rec_cer_no,"cer.rec_type":rec_type,"cer.rec_cer_type":rec_cer_type,"cer.rec_date":rec_date,"time":new Date()},
		success : function(data) {
			if (data.success) {
				top.$.messager.alert('正确提示', data.tipMessage, 'info',
						function() {
							
							
						});
			} else {
				top.$.messager.alert('错误提示', data.errorMessage, 'error');
			}
		},error:function(){
			result = false;
		}
		
	});
	
	return result;
};
/**********************************************************************************
*函数名称: validate
*功能说明: 抵押登记信息格式校验
*参数说明: 
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function validate(v_flag){
	//返回结果对象
	var result ={
			result:false,
			message:'',
			page_name:'发证表'
	}
	//提示消息 
	var message;
	var rows = $('#table_djxx').datagrid('getSelections');
    if(rows.length==0){
//		top.$.messager.alert('正确提示', '请选择需要发证的登记单元', 'info',
//				function() {
//					
//					
//				});
    	result.message="请选择需要发证的登记单元";
		return result;
		
	}
    //获取界面信息 
	var rec_person = $("#rec_person").val();
	var rec_type = $("#rec_type").combodict('getValue');
	var rec_cer_type = $("#rec_cer_type").combodict('getValue');
	var rec_cer_no = $("#rec_cer_no").val();
	var rec_date = $("#rec_date").val();
	//验证非空信息
	if($.trim(rec_person).length==0 || $.trim(rec_cer_no).length==0){
//		top.$.messager.alert('提示', '必填项不能为空，请输入！', 'info',
//				function() {					
//		});	
		result.message="必填项不能为空，请输入！";
		return result;
	} 
	
	result.result=true;
	return result;
	
}
/**********************************************************************************
*函数名称: cancelSave
*功能说明: 取消发证
*参数说明: 无
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function cancelSave() {
	var rows = $('#table_djxx').datagrid('getSelections');
	if(rows==null){
		
		top.$.messager.alert('正确提示', '请选择取消发证的登记单元', 'info',
				function() {
					
					
				});
		return ;
		
	}
	//设置取消时的默认值
	$("#rec_person").val("");
	$("#rec_cer_no").val("");
	$("#rec_date").val(getTime());	
	//获取界面信息
	var rec_person = $("#rec_person").val();
	var rec_type = $("#rec_type").val();
	var rec_cer_type = $("#rec_cer_type").val();
	var rec_cer_no = $("#rec_cer_no").val();
	var rec_date = $("#rec_date").val();
	//激活领证人 领证人证件编号
	$("#rec_person").removeAttr("disabled"); 
	$("#rec_cer_no").removeAttr("disabled"); 
	$('#rec_type').combo('enable');
	$("#rec_cer_type").combo('enable'); 

	var obj = {
			dataType : 'json',
			data : $("#userForm").serialize(),
			url:ctx+"/common/certificate!saveDispatch.action",
			data:{"proc_id":proc_id,"recperson":rec_person,"reccerno":rec_cer_no,"rectype":rec_type,"reccertype":rec_cer_type,"time":new Date()},
			contentType : "application/json; charset=GBK",
			success : function(data) {
				if (data.success) {
					top.$.messager.alert('正确提示',"取消成功", 'info',
							function() {
								
								
							});
				} else {	 
									
					top.$.messager.alert('错误提示', data.errorMessage, 'error');
				}
			}
		};


	$.ajax(obj);
};
/**********************************************************************************
*函数名称: getTime
*功能说明: 获取系统当前时间
*参数说明: 无
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getTime(){
	  var myDate = new Date();
	  var year = myDate.getFullYear();
	  var month = myDate.getMonth()+1;
	  var date = myDate.getDate(); 

var mytime=myDate.toLocaleTimeString();     //获取当前时间
	  
	var time = year+"-"+month+"-"+date+" "+mytime;
	return time;
	
	}
/**********************************************************************************
*函数名称: getTime
*功能说明: 初始化房地产证信息
*参数说明: 无
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/

function getDis(){
	//alert("getDis...");
	//根据流程实例id，查询
	$.ajax({
	    dataType: 'json',
		url:ctx+"/common/certificate!getCertificateByProcId.action?proc_id="+proc_id+"&time="+new Date(),
		success:function(data){
			//alert(data);
			//alert($.toJSON(data));
		 	if(data){
		 		//data = data[0];
		 		 if(data.rec_person != null){	
		 			$('#table_djxx').datagrid('checkAll');
		 			var datagridLength = $('#table_djxx').datagrid('getRows').length;
		 			alert($.toJSON(datagridLength) );
		 			for(var i=0;i<datagridLength;i++){
		 				$('#table_djxx').datagrid('checkRow',i);
		 			}
		 			
		 			     if(data.rec_date){
	   			 		var rec_date=data.rec_date;//.substr(0,data.rec_date.length-2);
	   			 		//alert(rec_date instanceof Date);
	   			 		//alert(rec_date.Format("yyyy-MM-dd"));
	   			 	    $("#rec_date").val(rec_date);
		 		          } 
	   		   			//设置领证人、证件编号为不能操作
		   				$("#rec_cer_no").attr("disabled", "disabled");
		   				$("#rec_person").attr("disabled", "disabled");
		   				$('#rec_type').combo('disable');
		   				$("#rec_cer_type").combo('disable');
	   			 		};
	   			 	if(data.rec_type){
	   			 		
	   			 	$("#rec_type").combodict('setValue',data.rec_type);	
	   			 	}
	   			 	
	   			    $("#rec_person").val(data.rec_person);
	   			    if(data.rec_cer_type){
	   			    	$("#rec_cer_type").combodict('setValue',data.rec_cer_type);
	   			    }
	   			    
	   			    $("#rec_cer_no").val(data.rec_cer_no);
		 	}else{
		 		
		 		$("#rec_date").val(getTime()); 	 	
		 	}
		}
	});
}

//点击读ic卡按钮，获取身份证号信息
function setid(){
	ClearIDCard(); //读前清理读卡器缓存
	if(!readIDCard()){
		top.$.messager.alert('读卡提示','识别卡失败','info',function(){});
		return
	}
	 var pName=CVR_IDCard.NameL; //var pNameL=CVR_IDCard.NameL;
	 var pSex=CVR_IDCard.SexL;   //var pSexL=CVR_IDCard.SexL;
	 var pNation=CVR_IDCard.NationL;  //var pNationL=CVR_IDCard.NationL;
	 var pBorn=CVR_IDCard.BornL;      //var pBornL=CVR_IDCard.BornL;
	 var pAddress=CVR_IDCard.Address;
	 var pCardNo=CVR_IDCard.CardNo;
	 var pPolice=CVR_IDCard.Police;
	 var pActivity=CVR_IDCard.Activity;
	 var pNewAddr=CVR_IDCard.NewAddr;  
	 var pActivityLFrom=CVR_IDCard.ActivityLFrom; 
	 var pActivityLTo=CVR_IDCard.ActivityLTo; 
	 var pPhotoBuffer=CVR_IDCard.GetPhotoBuffer; 
	  
	  $("#rec_cer_no").val(pCardNo);
	  $("#rec_person").val(pName);
	
	  ClearIDCard(); //读后清理读卡器缓存
	//CVR_IDCard.DoStopRead; //停止读卡
}

function readIDCard(){
	var strReadResult=CVR_IDCard.ReadCard;
	if(strReadResult == "0"){
		return true;
	}
	return false;
}
function ClearIDCard() {
	   CVR_IDCard.Name="";
	   CVR_IDCard.NameL="";
	   CVR_IDCard.Sex="";   
	   //CVR_IDCard.SexL="";   
	   CVR_IDCard.Nation="";
	   //CVR_IDCard.NationL="";
	   CVR_IDCard.Born="";
	   //CVR_IDCard.BornL="";
	   CVR_IDCard.Address="";
	   CVR_IDCard.CardNo="";
	   CVR_IDCard.Police="";
	   CVR_IDCard.Activity="";
	   CVR_IDCard.NewAddr="";
	  
	   return true;
}


		
