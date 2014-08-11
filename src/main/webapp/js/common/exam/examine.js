var proc;
proc=this.parent.proc;

var proc_id = proc.procId;
//var proc_id = 3;


var activName = proc.activName;
var procdefId = proc.procdefId;
var serialNumber ={num1:"登记编号",num2:"归档号",num3:"房地产证号"};
var opintion;                         //用来锁定常用语返回值设定的控件ID
var _init_form_data;							//初始化时数据   用于判断当前页面数据是否己修改
var _cur_form_data;								//验证时数据   用于判断当前页面数据是否己修改
//var activName = "初审";
var state1 = {
		string0: "受理",
		string1 : "初审",
		string2 : "复审",
		string3 : "收费",
		string4 : "核准",
		string5 : "初步审查",
		string6 : "初步审核",
		string7: "初步审定",		
		string9 : "缮证",
		string10 : "发文",
		string11: "归档",
		string12: "公告",
	};
$(function(){
	
	        setState(activName);	
      	    //获取焦点事件
	        initial();
	        getExamine();
	        //获取公告信息
	        getAnnounce();
	      //失去焦点事件
       		$('textarea').blur(function(){
       			blur();
       		});   
       		//_init_form_data = $("#main_form").serializeJson(); 
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
	if(activName == state1.string12)
	{
		saveAnnounce();
		return true;
	}
	else
	{
		return saveExamine();
	}
	
 };
//保存公告及相关信息
 function saveAnnounce(){
	 	var yj = $("#ggnr").val();
		//var sj = $("#ggndsj").val();
		//var r = $("#ggndr").val();
		//alert($("ggndr").val());\
		var result;
		var url;
		if(activName == state1.string12)
	   	{
			result=$("#main_form").serialize();
			url=ctx+"/common/exam!saveAnnouncePub.action?time="+new Date()+"&proc_id="+proc_id;
	   	}
	   	else
	   	{
	   		result={"notice_content":yj};
	   		url=ctx+"/common/exam!saveAnnounce.action?time="+new Date()+"&proc_id="+proc_id;
	   	}
		if($.trim(yj).length== 0){
			
			 top.$.messager.confirm('提示','公告内容不能为空，请录入公告内容或选择公告模版！',function(){});
			
		}else{
 		$.ajax({
 	   		dataType:'json',
 	   		url:url,
 	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
 	   		//表单的序列化操作
 	   		data:result,
 	   		success:function(data){
 			 	if(data){
 			 		//alert(data);
 			 		getAnnounce();
 			 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
 					});	
 			 		
 			 	}else {
 					top.$.messager.alert('保存失败提示',data.errorMessage,'error');
 				}
 			 	_init_form_data = $("#main_form").serializeJson(); 
 	   		}
 	   	});
		}
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
		 		if(activName == state1.string5)
		 		{
		 			saveAnnounce();
		 		}
		 		else
		 		{
		 			top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
					});
		 		}
		 		//alert(data);
		 			
		 		
		 	}else {
				top.$.messager.alert('保存失败提示',data.errorMessage,'error');
			}
   		},error:function(data){
   			result = false;
   		}
   	});
	return result;
}

//获取公告信息
function getAnnounce(){
	$.ajax({
		    dataType: 'json',
			url:ctx+"/common/exam!getAnnounceByid.action?proc_id="+proc_id+"&time="+new Date(),
			success:function(data){
				if(data){
					 if(data.annouce){
						 var ggndsj = data.annouce.notice_pro_time;
						 if(ggndsj != null){
						 $("#ggndsj").val(ggndsj.substr(0,20));
						 }
						 $("#ggndr").val(data.annouce.notice_pro_person);
		   			 	 $("#ggnr").val(data.annouce.notice_content);
		   			 	 $("#ggbh").val(data.annouce.notice_code);
		   			 	 if(data.annouce.notice_person){
		   			 		 
		   			 		$("#ggry").val(data.annouce.notice_person); 
		   			 		 
		   			 	 }else{
		   			 		//$("#ggry").val(user); 
		   			 	 }
		   			 	 $("#ggqx").val(data.annouce.notice_limit);
		   			 	 var ggsj = data.annouce.noticie_date;
		   			 	 if(ggsj != null){
		   			 		//$("#ggsj").val(ggsj.substr(0,ggsj.length-2)); 
		   			 	   $('#ggsj').datetimebox('setValue',ggsj.substr(0,10));
		   			 	 }
		   			 	 $("#fbdw").val(data.annouce.notice_pub_off);
		   			 	 var fbsj = data.annouce.notice_pub_time;
		   			 	 if(fbsj != null){
		   			 	$('#fbsj').datetimebox('setValue',fbsj.substr(0,10));
		   			 	 }
		   			 	 $("#cbwmc").val(data.annouce.pub_name_date);
		   			 	 var cbsj = data.annouce.pub_date;
		   			 	 if(cbsj != null){
		   			 		$('#cbwdzrq').datetimebox('setValue',cbsj.substr(0,10));
		   			 	 }
					 };
				}
			}
});
}


 
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
   			 			//初步审查
   			 			if(data[i].opinion_type==state1.string5)
   			 			{
                          	$("#cbscsj").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-3));  
      			 		    $("#cbsc").val(data[i].opinion_content);
      			 		    $("#cbscr").val(data[i].checker_no);
   			 			}
   			 			//初步审核
   			 			if(data[i].opinion_type==state1.string6)
   			 			{
	   			 			$("#cbshsj").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-3));  
	  			 		    $("#cbsh").val(data[i].opinion_content);
	  			 		    $("#cbshr").val(data[i].checker_no);
   			 			}
   			 			//初步审定
   			 			if(data[i].opinion_type==state1.string7)
   			 			{
	   			 			$("#cbsdsj").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-3));  
	  			 		    $("#cbsd").val(data[i].opinion_content);
	  			 		    $("#cbsdr").val(data[i].checker_no);
   			 			}
   			 			//初审
   			 			if(data[i].opinion_type==state1.string1)
   			 			{
	   			 			$("#cssj").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-3));  
	  			 		    $("#cs").val(data[i].opinion_content);
	  			 		    $("#csr").val(data[i].checker_no);
   			 			}
   			 			//复审
   			 			if(data[i].opinion_type==state1.string2)
   			 			{
	   			 			$("#fssj").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-3));  
	  			 		    $("#fs").val(data[i].opinion_content);
	  			 		    $("#fsr").val(data[i].checker_no);
   			 			}
   			 			//核准
   			 			if(data[i].opinion_type==state1.string4)
   			 			{
	   			 			$("#hzsj").val(data[i].opinion_timestr.substr(0,data[i].opinion_timestr.length-3));  
	  			 		    $("#hz").val(data[i].opinion_content);
	  			 		    $("#hzr").val(data[i].checker_no);
   			 			}
   			 		}
   			 	}
   			 _init_form_data = $("#main_form").serializeJson(); 
   				 
   			}
   		});
	
	
}


//根据流程节点设置控件权限
 function setState(activName){
      $(":input").attr("disabled", "disabled");
 	      if(activName == state1.string5)
 	      {
 	    	 opintion="cbsc";
 	    	 $("#cbsc").removeAttr("disabled","disabled");
 	    	 $("#cbsccyy").removeAttr("disabled","disabled");
 	    	 $("#ggnr").removeAttr("disabled", "disabled");
	    	 $("#ggmb").removeAttr("disabled", "disabled");
 	      };
 	      if(activName == state1.string6)
 	      {
 	    	 opintion="cbsh";
 	    	 $("#cbsh").removeAttr("disabled","disabled");
 	    	 $("#cbshcyy").removeAttr("disabled","disabled");
 	      };
 	      if(activName == state1.string7)
 	      {
 	    	 opintion="cbsd";
 	    	 $("#cbsd").removeAttr("disabled","disabled");
 	    	 $("#cbsdcyy").removeAttr("disabled","disabled");
 		  };
 	      if(activName == state1.string12)
 	      {
 	    	 $("#ggbh").removeAttr("disabled", "disabled");
 	    	 $("#ggry").removeAttr("disabled", "disabled");
 	    	 $("#ggqx").removeAttr("disabled", "disabled");
 	    	 $("#ggsj").removeAttr("disabled", "disabled");
 	    	 $("#fbdw").removeAttr("disabled", "disabled");
 	    	 $("#fbsj").removeAttr("disabled", "disabled");
 	    	 $("#cbwdzrq").removeAttr("disabled", "disabled");
 	    	 $("#cbwmc").removeAttr("disabled", "disabled");
 	      };
 	      if(activName == state1.string1)
 	      {
 	    	 opintion="cs";
 	    	 $("#cs").removeAttr("disabled","disabled");
 	    	 $("#cscyy").removeAttr("disabled","disabled");
 	      };
 	      if(activName == state1.string2)
 	      {
 	    	 opintion="fs";
 	    	 $("#fs").removeAttr("disabled","disabled");
 	    	 $("#fscyy").removeAttr("disabled","disabled");
 	      };
 	      if(activName == state1.string4)
 	      {
 	    	 opintion="hz";
 	    	  $("#hz").removeAttr("disabled","disabled");
 	    	 $("#hzcyy").removeAttr("disabled","disabled");
	      };
 	      /*if(!(activName == state1.string1 || activName == state1.string2 ||activName == state1.string3 ||activName == state1.string7||activName == state1.string5||activName == state1.string6)){
 	    	  $(":input").attr("disabled", "disabled");  
 	      }*/
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
				this.init(activName,procdefId,opintion);
			}
		});
	 
	 
		 }; 

//初始化当前登录人以及日期
function initial(){
	if(activName == state1.string5){
		//  获取当前时间
	    $("#cbscsj").val(getTime());
	     //获取当前审批人
	    $("#cbscr").val(user); 
	    //  获取当前时间
		 $("#ggndsj").val(getTime());
		 //获取当前审批人
		 $("#ggndr").val(user); 
	 };
	 if(activName == state1.string6){
		 //  获取当前时间
		 $("#cbshsj").val(getTime());
		 //获取当前审批人
		 $("#cbshr").val(user); 
	 };
	 if(activName == state1.string7){
		 //  获取当前时间
		 $("#cbsdsj").val(getTime());
		 //获取当前审批人
		 $("#cbsdr").val(user); 
	 };
	 if(activName == state1.string12){
		     //获取当前公告人
		    $("#ggry").val(user); 
		 };
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
	 if(activName == state1.string4){
		 //  获取当前时间
		 $("#hzsj").val(getTime());
		 //获取当前审批人
		 $("#hzr").val(user); 
	 };
	
};
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
	  if(activName == state1.string5){
	   	    opinion = $("#cbsc").val();
	   	    opiniontime = $("#cbscsj").val(getTime());
	   	    opinionpeople = $("#cbscr").val();
	   	    opiniontype = state1.string5;
    };
    if(activName == state1.string6){
	       opinion = $("#cbsh").val();
	   	   opiniontime = $("#cbshsj").val(getTime());
	   	   opinionpeople = $("#cbshr").val();
	   	   opiniontype = state1.string6;
    };
    if(activName == state1.string7){
	       opinion = $("#cbsd").val();
	   	   opiniontime = $("#cbsdsj").val(getTime());
	   	   opinionpeople = $("#cbsdr").val();
	   	   opiniontype = state1.string7;
	      };
    if(activName == state1.string1){
    	opinion = $("#cs").val();
	   	   opiniontime = $("#cssj").val(getTime());
	   	   opinionpeople = $("#csr").val();
	   	   opiniontype = state1.string1;
    };
    if(activName == state1.string2){
	       opinion = $("#fs").val();
	   	   opiniontime = $("#fssj").val(getTime());
	   	   opinionpeople = $("#fssj").val();
	   	   opiniontype = state1.string2;
    };
    if(activName == state1.string4){
	       opinion = $("#hz").val();
	   	   opiniontime = $("#hzsj").val(getTime());
	   	   opinionpeople = $("#hzr").val();
	   	   opiniontype = state1.string4;
    };
  }



//当意见框获取焦点时，保存当前用户和当前时间

function blur(){
	
	if(activName == state1.string5){
		//  获取当前时间
	    $("#cbscsj").val(getTime());
	    $("#ggndsj").val(getTime());
	     //获取当前审批人
	    $("#cbscr").val(user); 
	    $("#ggndr").val(user);
	 };
	 if(activName == state1.string6){
		 //  获取当前时间
		 $("#cbshsj").val(getTime());
		 //获取当前审批人
		 $("#cbshr").val(user); 
	 };
	 if(activName == state1.string7){
		 //  获取当前时间
		 $("#cbsdsj").val(getTime());
		 //获取当前审批人
		 $("#cbsdr").val(user); 
	 };
	 if(activName == state1.string12){
		 //  获取当前时间
		 //$("#cbsdsj").val(getTime());
		 //获取当前审批人
		 $("#ggry").val(user); 
	 };
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
	 if(activName == state1.string4){
		 //  获取当前时间
		 $("#hzsj").val(getTime());
		 //获取当前审批人
		 $("#hzr").val(user); 
	 };
	
};
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
	if(activName == state1.string6){
		var yj = $("#cbsh").val()
		if($.trim(yj).length== 0){
			message = '初步审核意见不能为空，请录入审核意见或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}
	if(activName == state1.string7){
		var yj = $("#cbsd").val()
		if($.trim(yj).length== 0){
			message = '初步审定意见不能为空，请录入审核意见或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}
	
	if(activName == state1.string1){
		var yj = $("#cs").val();
		if($.trim(yj).length== 0){
			message = '初审意见不能为空，请录入初审意见或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 
			 result.message=message;
			 result.result=false;
			 return result;
		}
		
	}if(activName == state1.string2){
		var yj = $("#fs").val()
		if($.trim(yj).length== 0){
			message = '复审意见不能为空，请录入复审意见或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}if(activName == state1.string5){
		var yj = $("#cbsc").val()
		if($.trim(yj).length== 0){
			message = '初步审查意见不能为空，请录入审核意见或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
		var yj = $("#ggnr").val()
		if($.trim(yj).length== 0){
			message = '拟定公告意见不能为空，请录入审核意见或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}if(activName == state1.string4){
		var yj = $("#hz").val()
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
//		 $.messager.confirm('保存确认',message,function(r){  
//			    if (r){  
//			    	result.message=message;
//			    	flag=1;
//			    }else{
//			    	 result.message=message;
//					 result.result=false;
//					 flag=1;
//			    }
//		 });
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

	  
