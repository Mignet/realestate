var proc;
proc=this.parent.proc;

var proc_id = proc.procId;
var activName = proc.activName;
var procdefId = proc.procdefId;
var serialNumber ={num1:"登记编号",num2:"归档号",num3:"房地产证号"};
var _init_form_data;							//初始化时数据   用于判断当前页面数据是否己修改
var _cur_form_data;								//验证时数据   用于判断当前页面数据是否己修改
var opinion;							//意见
var opiniontime;							//意见录入时间
var opinionpeople;						//意见人
var opiniontype;						//意见类型

var state1 = {
		string0: "受理",
		string1 : "初审1",
		string2 : "复审1",
		string3 : "审核1",
		string4 : "初审2",
		string5 : "审核2",
		string6 : "核准",
		string7: "归档",
	};
$(function(){
	        setState(activName);	
	        getExamine();	
	        initial();
       		//失去焦点事件
       		$('textarea').blur(function(){
       			blur();
       		});   
       });
       
//获取地址栏参数
function GetQueryString(name)
	{
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r!=null) return unescape(r[2]);
		return null;
};
       
//意见保存更新操作
function submit(){  
	return saveExamine();
 };
//根据流程节点设置控件权限
function setState(activName){
	      if(activName == state1.string1){
		        $("#fsyj").attr("disabled","disabled");
		        $("#csyj2").attr("disabled","disabled");
		        $("#shyj1").attr("disabled","disabled");
		        $("#shyj2").attr("disabled","disabled");
		        $("#hzyj").attr("disabled","disabled");
		        $("#fscyy").attr("disabled","disabled");
		        $("#cscyy2").attr("disabled","disabled");
		        $("#shcyy2").attr("disabled","disabled");
		        $("#shcyy1").attr("disabled","disabled");
		        $("#hzcyy").attr("disabled","disabled");
	      };
	      if(activName == state1.string2){
	    	  $("#csyj1").attr("disabled","disabled");
		       $("#csyj2").attr("disabled","disabled");
		       $("#shyj1").attr("disabled","disabled");
		       $("#shyj2").attr("disabled","disabled");
		       $("#hzyj").attr("disabled","disabled");
		       $("#cscyy1").attr("disabled","disabled");
		       $("#cscyy2").attr("disabled","disabled");
		       $("#shcyy2").attr("disabled","disabled");
		       $("#shcyy1").attr("disabled","disabled");
		       $("#hzcyy").attr("disabled","disabled");
	      };
	      if(activName == state1.string3){
	    	  $("#csyj1").attr("disabled","disabled");
	    	  $("#fsyj").attr("disabled","disabled");
		       $("#csyj2").attr("disabled","disabled");
		       $("#shyj2").attr("disabled","disabled");
		       $("#hzyj").attr("disabled","disabled");
		       $("#fscyy").attr("disabled","disabled");
		       $("#cscyy1").attr("disabled","disabled");
		       $("#cscyy2").attr("disabled","disabled");
		       $("#shcyy2").attr("disabled","disabled");
		       $("#hzcyy").attr("disabled","disabled");
		      };
	      if(activName == state1.string4){
	    	  $("#csyj1").attr("disabled","disabled");
	    	  $("#shyj1").attr("disabled","disabled");
	    	  $("#fsyj").attr("disabled","disabled");
		       $("#shyj2").attr("disabled","disabled");
		       $("#hzyj").attr("disabled","disabled");
		       $("#shcyy1").attr("disabled","disabled");
		       $("#fscyy").attr("disabled","disabled");
		       $("#cscyy1").attr("disabled","disabled");
		       $("#shcyy2").attr("disabled","disabled");
		       $("#hzcyy").attr("disabled","disabled");
	      };
	      if(activName == state1.string5){
	    	   $("#csyj1").attr("disabled","disabled");
	    	   $("#shyj1").attr("disabled","disabled");
	    	   $("#csyj2").attr("disabled","disabled");
	    	   $("#fsyj").attr("disabled","disabled");
		       $("#hzyj").attr("disabled","disabled");
		       $("#cscyy2").attr("disabled","disabled");
		       $("#shcyy1").attr("disabled","disabled");
		       $("#fscyy").attr("disabled","disabled");
		       $("#cscyy1").attr("disabled","disabled");
		       $("#hzcyy").attr("disabled","disabled");
	      };
	      if(activName == state1.string6){
	    	  $("#csyj1").attr("disabled","disabled");
	    	  $("#shyj1").attr("disabled","disabled");
	    	  $("#csyj2").attr("disabled","disabled");
	    	  $("#fsyj").attr("disabled","disabled");
	    	  $("#shyj2").attr("disabled","disabled");
		       $("#cscyy2").attr("disabled","disabled");
		       $("#shcyy1").attr("disabled","disabled");
		       $("#fscyy").attr("disabled","disabled");
		       $("#cscyy1").attr("disabled","disabled");
		       $("#shcyy2").attr("disabled","disabled");
	      };
	      if(!(activName == state1.string1 || activName == state1.string2 ||activName == state1.string3 ||activName == state1.string4||activName == state1.string5||activName == state1.string6)){
	    	  $(":input").attr("disabled", "disabled");  
	      }
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
//选择常用语
 function selectCyy(){ 
	 openInTopWindow({
			// 窗口元素的id
			id : 'comlan',
			// 窗口iframe的src
			src : ctx+'/jsp/common/comlangu/lanselect.jsp?time='+new Date(),
			// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy : true,
			// 窗口标题
			title : '选择意见模版',
			// 窗口宽
			width : 600,
			// 窗口高
			height : 400,
			modal : true,
			// 窗口中iframe的window对象的onLoad回调函数设置
			onLoad : function() {
				// 此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
				// 因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
				this.openerWindow = window;
				// 将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
				this.args = {
					//userDataGrid : userDataGrid
				};
				this.init(activName,procdefId);
			}
		});
		 }; 
		
//获取子页面的值
function  getNewLinkValue(value){
	
	if(activName == state1.string1){
		$("#csyj1").text(value);
		//  获取当前时间
	    $("#cssj1").val(getTime());
	     //获取当前审批人
	    $("#csr1").val(user); 
	 };
	 if(activName == state1.string2){
		 $("#fsyj").text(value);
		 //  获取当前时间
		 $("#fssj").val(getTime());
		 //获取当前审批人
		 $("#fsr").val(user); 
	 };
	 if(activName == state1.string3){
		 $("#shyj1").text(value);
		 //  获取当前时间
		 $("#shsj1").val(getTime());
		 //获取当前审批人
		 $("#shr1").val(user); 
	 };
	 if(activName == state1.string4){
		 $("#csyj2").text(value);
		 //  获取当前时间
		 $("#cssj2").val(getTime());
		 //获取当前审批人
		 $("#csr2").val(user); 
	 };
	 if(activName == state1.string5){
		 $("#shyj2").text(value);
		 //  获取当前时间
		 $("#shsj2").val(getTime());
		 //获取当前审批人
		 $("#shr2").val(user); 
	 };
	 if(activName == state1.string6){
		 $("#hzyj").text(value);
		 //  获取当前时间
		 $("#hzsj").val(getTime());
		 //获取当前审批人
		 $("#hzr").val(user); 
	 };
};

//初始化当前登录人以及日期
function initial(){
	
	if(activName == state1.string1){
		//  获取当前时间
	    $("#cssj1").val(getTime());
	     //获取当前审批人
	    $("#csr1").val(user); 
	 };
	 if(activName == state1.string2){
		 //  获取当前时间
		 $("#fssj").val(getTime());
		 //获取当前审批人
		 $("#fsr").val(user); 
	 };
	 if(activName == state1.string3){
		 //  获取当前时间
		 $("#shsj1").val(getTime());
		 //获取当前审批人
		 $("#shr1").val(user); 
	 };
	 if(activName == state1.string4){
		 //  获取当前时间
		 $("#cssj2").val(getTime());
		 //获取当前审批人
		 $("#csr2").val(user); 
	 };
	 if(activName == state1.string5){
		 //  获取当前时间
		 $("#shsj2").val(getTime());
		 //获取当前审批人
		 $("#shr2").val(user); 
	 };
	 if(activName == state1.string6){
		 //  获取当前时间
		 $("#hzsj").val(getTime());
		 //获取当前审批人
		 $("#hzr").val(user); 
	 };
	
};




//当意见框获取焦点时，保存当前用户和当前时间

function blur(){
	
	  if(activName == state1.string1){
		    
			//  获取当前时间
		        $("#cssj").val(getTime());
		        //获取当前审批人
		        $("#csr").val(user); 
	 };
	 if(activName == state1.string2){
			//  获取当前时间
		         $("#fssj").val(getTime());
		         //获取当前审批人
		        $("#fsr").val(user); 
	 };
	 if(activName == state1.string3){
	
			//  获取当前时间
		         $("#shsj").val(getTime());
		         //获取当前审批人
		        $("#shr").val(user); 
	 };
	 if(activName == state1.string4){
			
			//  获取当前时间
		         $("#hzsj").val(getTime());
		         //获取当前审批人
		        $("#hzr").val(user); 
	 };
	
};

/**********************************************************************************
 *函数名称: getExamine
 *功能说明: 获取审批表信息
 *参数说明: 
 *返 回 值:
 *函数作者: xuzz
 *创建日期: 2014-04-22
 *修改历史: 
***********************************************************************************/
function getExamine(){
	
	  //初始化审批表信息   
  	$.ajax({
   		    dataType: 'json',
   			url:ctx+"/common/exam!getExamineById.action?proc_id="+proc_id+"&time="+new Date(),
   			success:function(data){
   			 	if(data){
   			 		for(var i=0;i<data.length;i++)
   			 		{
   			 			//初审1
   			 			if(data[i].opinion_type==state1.string1)
   			 			{
                          	$("#cssj1").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-2));  
      			 		    $("#csyj1").val(data[i].opinion_content);
      			 		    $("#csr1").val(data[i].checker_no);
   			 			}
   			 			//复审1
   			 			if(data[i].opinion_type==state1.string2)
   			 			{
	   			 			$("#fssj").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-2));  
	  			 		    $("#fsyj").val(data[i].opinion_content);
	  			 		    $("#fsr").val(data[i].checker_no);
   			 			}
   			 			//审核1
   			 			if(data[i].opinion_type==state1.string3)
   			 			{
	   			 			$("#shsj1").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-2));  
	  			 		    $("#shyj1").val(data[i].opinion_content);
	  			 		    $("#shr1").val(data[i].checker_no);
   			 			}
   			 			//初审2
   			 			if(data[i].opinion_type==state1.string4)
   			 			{
	   			 			$("#cssj2").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-2));  
	  			 		    $("#csyj2").val(data[i].opinion_content);
	  			 		    $("#csr2").val(data[i].checker_no);
   			 			}
   			 			//审核2
   			 			if(data[i].opinion_type==state1.string5)
   			 			{
	   			 			$("#shsj2").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-2));  
	  			 		    $("#shyj2").val(data[i].opinion_content);
	  			 		    $("#shr2").val(data[i].checker_no);
   			 			}
   			 			//核准
   			 			if(data[i].opinion_type==state1.string6)
   			 			{
	   			 			$("#hzsj").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-2));  
	  			 		    $("#hzyj").val(data[i].opinion_content);
	  			 		    $("#hzr").val(data[i].checker_no);
   			 			}
   			 		}
   			 	}
   			 _init_form_data = $("#main_form").serializeJson(); 
   				 
   			}
   		});
	
	
}


/**********************************************************************************
 *函数名称: getOpinion
 *功能说明: 获取意见
 *参数说明: 
 *返 回 值:
 *函数作者: xuzz
 *创建日期: 2014-04-21
 *修改历史: 
 ***********************************************************************************/
  function getOpinion()
  {
	  if(activName == state1.string1){
	   	    opinion = $("#csyj1").val();
	   	    opiniontime = $("#cssj1").val(getTime());
	   	    opinionpeople = $("#csr1").val();
	   	    opiniontype = state1.string1;
    };
    if(activName == state1.string2){
	       opinion = $("#fsyj").val();
	   	   opiniontime = $("#fssj").val(getTime());
	   	   opinionpeople = $("#fsr").val();
	   	   opiniontype = state1.string2;
    };
    if(activName == state1.string3){
	       opinion = $("#shyj1").val();
	   	   opiniontime = $("#shsj1").val(getTime());
	   	   opinionpeople = $("#shr1").val();
	   	   opiniontype = state1.string3;
	      };
    if(activName == state1.string4){
	       opinion = $("#csyj2").val();
	   	   opiniontime = $("#cssj2").val(getTime());
	   	   opinionpeople = $("#csr2").val();
	   	   opiniontype = state1.string4;
    };
    if(activName == state1.string5){
	       opinion = $("#shyj2").val();
	   	   opiniontime = $("#shsj2").val(getTime());
	   	   opinionpeople = $("#shr2").val();
	   	   opiniontype = state1.string5;
    };
    if(activName == state1.string6){
	       opinion = $("#hezyj").val();
	   	   opiniontime = $("#hzsj").val(getTime());
	   	   opinionpeople = $("#hzr").val();
	   	   opiniontype = state1.string6;
    };
  }


  /**********************************************************************************
  *函数名称: saveExamine
  *功能说明: 保存意见
  *参数说明: 
  *返 回 值:
  *函数作者: xuzz
  *创建日期: 2014-04-21
  *修改历史: 
  ***********************************************************************************/
function saveExamine(){
	var result = true; 
	getOpinion();
	
	$.ajax({
   		dataType:'json',
   		url:ctx+"/common/exam!saveExaine.action?time="+new Date()+"&proc_id="+proc_id,
   		contentType:"application/x-www-form-urlencoded; charset=GBK",
   		//表单的序列化操作
   		data:{"examine.checker_no":opinionpeople,"examine.opinion_content":opinion,"examine.opinion_type":opiniontype},
   		success:function(data){
		 	if(data){
		 		//alert(data);
		 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
				});	
		 		
		 	}else {
				top.$.messager.alert('保存失败提示',data.errorMessage,'error');
			}
   		},error:function(data){
   			result = false;
   		}
   	});
	return result;
}
/**********************************************************************************
*函数名称: validate()
*功能说明: 公用校验方法  受理时验证 其它环节返回true
*参数说明: 
*返 回 值: obj  result(true通过  false不通过) message(消息)  page_name(当前页面名字)
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function validate(v_flag){
	//返回结果对象
	var result ={
			result:true,
			message:'',
			page_name:'审批表'
	}
	//提示消息 
	var message;
	
	if(activName == state1.string1){
		var yj = $("#csyj1").val();
		if($.trim(yj).length== 0){
			message = '初审意见不能为空，请录入初审意见或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 
			 result.message=message;
			 result.result=false;
			 return result;
		}
		
	}if(activName == state1.string2){
		var yj = $("#fsyj").val()
		if($.trim(yj).length== 0){
			message = '复审意见不能为空，请录入复审意见或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}if(activName == state1.string3){
		var yj = $("#shyj1").val()
		if($.trim(yj).length== 0){
			message = '审核意见不能为空，请录入审核意见或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}if(activName == state1.string4){
		var yj = $("#csyj2").val()
		if($.trim(yj).length== 0){
			message = '初审意见不能为空，请录入审核意见或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}
	if(activName == state1.string5){
		var yj = $("#shyj2").val()
		if($.trim(yj).length== 0){
			message = '审核意见不能为空，请录入审核意见或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}
	if(activName == state1.string6){
		var yj = $("#hzyj").val()
		if($.trim(yj).length== 0){
			message = '核准意见不能为空，请录入核准意见或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}
	
	if(v_flag){
		_init_form_data = $("#main_form").serializeJson(); 
	}
	//判断数据项是否己修改  如果己修改  则提示是否保存未保存数据
	_cur_form_data = $("#main_form").serializeJson(); 
	
	var r = equal(_init_form_data,_cur_form_data);
	//alert(JSON.stringify(_cur_form_data));
	if(!r){
		var flag= 0 ;//用来确认 是否用户已经点击放弃保存  未点击  代表是在外面调用     返回false
		message = '数据己修改！请先保存后提交！';
		 if(flag){
			 
		 }else{
			 result.message=message;
			 result.result=false; 
		 }
		 return result;
	}
	return result;
}


/**********************************************************************************
*函数名称: pageDataIsChange
*功能说明: 判断当前页面数据是否已经修改
*参数说明: 
*返 回 值: 己修改返回true   未修改返回false
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function pageDataIsChange(){
	_cur_form_data = $("#main_form").serializeJson(); 
	
	var r = equal(_init_form_data,_cur_form_data);
	//如果不相等返回  页面数据己修改  返回true
	if(!r){
	  return true;
	}
	return false;
}

	  
