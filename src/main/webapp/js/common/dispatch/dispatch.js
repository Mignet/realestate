//var procId =1000016365 ;
//var procId= GetQueryString("procId");
var proc;
proc=this.parent.proc;
var procId = proc.procId;

var _init_form_data;							//初始化时数据   用于判断当前页面数据是否己修改
var _cur_form_data;								//验证时数据   用于判断当前页面数据是否己修改

$(function(){
	
	//初始化领证人信息
	getDis();
	
	//创建登记信息表
	/*var userDataGrid = $('#table_djxx').datagrid({
		title:'登记信息',
		//fit:true,
		height:200,
		//表格数据来源
		url:ctx+"/common/certificate!getRegsMessage.action?procId="+procId+"&time="+new Date(),
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
		//idField: 'user_id',
		//表格的行是否交替显示不同背景色
		striped:true,
		//只允许单选一行
		singleSelect:true,
		//是否在点选表中一行时同时选中复选框
		//checkOnSelect:true,
		//是否在选中复选框时同时点选表中一行
		//selectOnCheck:true,
		//列属性设置
		columns:[[
			//每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
			{field:'BUS_ID', title:'业务编号', width:70, sortable:true},
			{field:'DJLX', title:'业务类型', width:80, sortable:true},	
			{field:'SQR', title:'权利人', width:30, sortable:true },
			{field:'ZJLX', title:'证件类型', width:50},
			{field:'ZJBH', title:'证件编号', width:100},
			{field:'FDCMC', title:'房地产名称', width:100},						
			{field:'BLZT',	title:'办理状态', width:50},
			{field:'JFZT',	title:'缴费状态', width:50}
			
		]]
	});*/
	
	
	
			
}); //初始化结束
//保存领证人信息
//意见保存更新操作


/*     	<form id="userForm" name="userForm" method="post">
<input type="hidden" id="rec_type" name="c.rec_type"/> 
<input type="hidden" id="rec_person" name="c.rec_person"/> 
<input type="hidden" id="rec_cer_type" name="c.rec_cer_type"/> 
<input type="hidden" id="rec_cer_no" name="c.rec_cer_no"/> 
<input type="hidden" id="rec_date" name="c.rec_date"/> */

function submit(){
	return true;
}


function submit1() {
	_init_form_data = $("#main_form").serializeJson(); 
	var v_result = validate(1);
	if(!v_result.result){
		top.$.messager.alert('提示',v_result.message, 'info',
				function() {
					
				});	
		return ;
	}
    //获取界面信息 
	var rec_person = $("#rec_person").val();
	var rec_type = $("#rec_type").val();
	var rec_cer_type = $("#rec_cer_type").val();
	var rec_cer_no = $("#rec_cer_no").val();
	var rec_date = $("#rec_date").val();
	
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
	/*$("#rec_person1").val(rec_person);
	$("#rec_type1").val(rec_type);
	$("#rec_cer_type1").val(rec_cer_type);
	$("#rec_cer_no1").val(rec_cer_no);
	$("#rec_date1").val(rec_date);
	$("#procId").val(procId);*/
	var obj = {
		dataType : 'json',
		data : $("#userForm").serialize(),
		url:ctx+"/common/certificate!saveDispatch.action",
		data:{"procid":procId,"recperson":rec_person,"reccerno":rec_cer_no,"rectype":rec_type,"reccertype":rec_cer_type,"time":new Date()},
		contentType : "application/json; charset=GBK",
		success : function(data) {
			if (data.success) {
				top.$.messager.alert('正确提示', data.tipMessage, 'info',
						function() {
							
							
						});
			} else {
				top.$.messager.alert('错误提示', data.errorMessage, 'error');
			}
		}
	};


	$.ajax(obj);
};
//取消保存更新操作
function cancelSave() {
	
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
/*	//向隐藏域传值
	$("#rec_person1").val(rec_person);
	$("#rec_type1").val(rec_type);
	$("#rec_cer_type1").val(rec_cer_type);
	$("#rec_cer_no1").val(rec_cer_no);
	$("#rec_date1").val(rec_date);
	$("#procId").val(procId);*/

	var obj = {
			dataType : 'json',
			data : $("#userForm").serialize(),
			url:ctx+"/common/certificate!saveDispatch.action",
			data:{"procid":procId,"recperson":rec_person,"reccerno":rec_cer_no,"rectype":rec_type,"reccertype":rec_cer_type,"time":new Date()},
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

//获取地址栏参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
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
//初始化房地产证信息
function getDis(){
	//根据流程实例id，查询
	$.ajax({
	    dataType: 'json',
		url:ctx+"/common/certificate!getCertificateByid.action?proc_id="+procId+"&time="+new Date(),
		success:function(data){
		 	if(data){
		 		 if(data.rec_person != null){		 			
	   			 		var rec_date=data.rec_date.substr(0,data.rec_date.length-2);
	   			 	    $("#rec_date").val(rec_date);
	   		   			//设置领证人、证件编号为不能操作
		   				$("#rec_cer_no").attr("disabled", "disabled");
		   				$("#rec_person").attr("disabled", "disabled");
		   				$('#rec_type').combo('disable');
		   				$("#rec_cer_type").combo('disable');
	   			 		};
	   			 	$("#rec_type").val(data.rec_type);
	   			    $("#rec_person").val(data.rec_person);
	   			    $("#rec_cer_type").val(data.rec_cer_type);
	   			    $("#rec_cer_no").val(data.rec_cer_no);
		 	}
		 	
		 	_init_form_data = $("#main_form").serializeJson(); 
		}
	});
}



//初始化登记信息


/**********************************************************************************
*函数名称: 页面校验方法
*功能说明: 验证页面上的非空  及数据格式   
*参数说明: v_flag 有值代表是保存操作
*返 回 值: obj  result(true通过  false不通过) message(消息)  page_name(当前页面名字)
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function validate(v_flag){
	 var result ={
	   			result:true,
	   			message:'',
	   			page_name:'受理通知书'
	   		}
	//验证非空信息
		var rec_person = $("#rec_person").val();
		var rec_cer_no = $("#rec_cer_no").val();
		if($.trim(rec_person).length==0 || $.trim(rec_cer_no).length==0){
			result.message='必填项不能为空，请输入！';
			 result.result=false; 
			 return result; 
		} 
		
		//有值  代表当前的是保存操作  重新给变量赋值
		if(v_flag){
			_init_form_data = $("#main_form").serializeJson(); 
		}
		
		//判断数据项是否己修改  如果己修改  则提示是否保存未保存数据
		_cur_form_data = $("#main_form").serializeJson(); 
		
		var r = equal(_init_form_data,_cur_form_data);
		if(!r){
			var flag= 0 ;//用来确认 是否用户已经点击放弃保存  未点击  代表是在外面调用     返回false
			message = '数据己修改！是否放弃保存？';
			 if(flag){
				 
			 }else{
				 result.message=message;
				 result.result=false; 
			 }
			 return result;
		}
	
	return result; 
	
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

		
