/*********************************************************************************
*文  件  名  称: examine.js
*功  能  描  述: 审批表js
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Joyon
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
var proc;
proc=this.parent.proc;

var proc_id = proc.procId;


var activName = proc.activName;

var serialNumber ={num1:"登记编号",num2:"归档号",num3:"房地产证号"};
var _init_form_data;							//初始化时数据   用于判断当前页面数据是否己修改
var _cur_form_data;								//验证时数据   用于判断当前页面数据是否己修改
var state1 = {
		string0: "受理",
		string1 : "初审",
		string2 : "复审",
		string3 : "审核",
		string4 : "核准",
		string5 : "初步审定",
		string6 : "公告审定",
		string7: "公告结果初审",
		string8 : "公告结果复审",		
		string9 : "缮证",
		string10 : "发证",
		string11: "归档",
		String12:"拟定公告"
	};
/**********************************************************************************
*函数名称: 
*功能说明: js加载时运行  
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
$(function(){
	
	        setState(activName);	
       		//wiId=GetQueryString("wiId");
	        getExam();	
      	    //获取焦点事件
       		$('textarea').focus(function(){
       			focus();
       		});
       		//失去焦点事件
       		$('textarea').blur(function(){
       			blur();
       		});   
       });
/**********************************************************************************
*函数名称: submit()
*功能说明: 意见保存更新操作置控件权限
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function submit(){  
	
//	var result = validate();
//	if(!result.result){
//		return false;
//	}
	
		if(activName == state1.string1){
			return saveExamFirst();
	  
	   };
	   if(activName == state1.string2){
		   return saveExamSecond();
		  
	   };
	   if(activName == state1.string3){
		 // return  saveExamThird();
		 
	   };
	   if(activName == state1.string4){
		  return saveExamForth();
	   };


 };
/**********************************************************************************
*函数名称: setState()
*功能说明: 根据流程节点设置控件权限
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function setState(activName){
	       
	      if(activName == state1.string1){
	       
	       $("#fsyj").attr("disabled","disabled");
	       $("#shyj").attr("disabled","disabled");
	       $("#hzyj").attr("disabled","disabled");
	       $("#fscyy").attr("disabled","disabled");
	       $("#shcyy").attr("disabled","disabled");
	       $("#hzcyy").attr("disabled","disabled");
	       
	      //$("#csfjsc").removeAttr("disabled");
	      };
	      if(activName == state1.string2){
	    	  $("#shyj").attr("disabled","disabled");
	    	  $("#shcyy").attr("disabled","disabled");
	       $("#csyj").attr("disabled","disabled");
	       $("#hzyj").attr("disabled","disabled");
	       $("#cscyy").attr("disabled","disabled");
	       $("#hzcyy").attr("disabled","disabled");
	       
	       //$("#fsfjsc").removeAttr("disabled");
	      };
	      if(activName == state1.string3){
	    	  $("#fsyj").attr("disabled","disabled");
	    	  $("#fscyy").attr("disabled","disabled");
		       $("#csyj").attr("disabled","disabled");
		       $("#hzyj").attr("disabled","disabled");
		       $("#cscyy").attr("disabled","disabled");
		       $("#hzcyy").attr("disabled","disabled");
		      };
	      if(activName == state1.string4){
	       
	       $("#csyj").attr("disabled","disabled");
	       $("#shyj").attr("disabled","disabled");
	       $("#fsyj").attr("disabled","disabled");
	       $("#cscyy").attr("disabled","disabled");
	       $("#shcyy").attr("disabled","disabled");
	       $("#fscyy").attr("disabled","disabled");
	       
	       //$("#hjfjsc").removeAttr("disabled");
	      };
	      if(!(activName == state1.string1 || activName == state1.string2 ||activName == state1.string3 ||activName == state1.string4)){
	    	  $(":input").attr("disabled", "disabled");  
	      }
}
/**********************************************************************************
*函数名称: getTime()
*功能说明: 获取系统当前时间语
*参数说明: 
*返 回 值: 
*函数作者: Joyon
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
*函数名称: selectCyy()
*功能说明: 选择常用语
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function selectCyy(){
 openInTopWindow({
		// 窗口元素的id
		id : 'comlan',
		// 窗口iframe的src
		src : ctx+'/jsp/common/comlangu/lanselect.jsp?time='+new Date(),
		// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
		destroy : true,
		// 窗口标题
		title : '选择常用语',
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
			this.init(activName);
		}
	});
 
 
}; 
		
/**********************************************************************************
*函数名称: getNewLinkValue()
*功能说明: 获取子页面的值和当前时间
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function  getNewLinkValue(value){
	   if(activName == state1.string1){
				//获取子页面的值
				  $("#csyj").text(value);
				//  获取当前时间
			         $("#cssj").val(getTime());
			         //获取当前审批人
			        $("#csr").val(user); 
		 };
		 if(activName == state1.string2){
				//获取子页面的值
				  $("#fsyj").text(value);
				//  获取当前时间
			         $("#fssj").val(getTime());
			         //获取当前审批人
			        $("#fsr").val(user); 
		 };
		 if(activName == state1.string3){
				//获取子页面的值
				  $("#shyj").text(value);
				//  获取当前时间
			         $("#shsj").val(getTime());
			         //获取当前审批人
			        $("#shr").val(user); 
		 };
		 if(activName == state1.string4){
				//获取子页面的值
				  $("#hzyj").text(value);
				//  获取当前时间
			         $("#hzsj").val(getTime());
			         //获取当前审批人
			        $("#hzr").val(user); 
		 };
	         
};

/**********************************************************************************
*函数名称: focus()
*功能说明: 当意见框获取焦点时，保存当前用户和当前时间
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function focus(){
	
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
*函数名称: blur()
*功能说明: 当意见框获取焦点时，保存当前用户和当前时间
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
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
*函数名称: getExam()
*功能说明: 获取审批表信息意见
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getExam(){
	
	  //初始化审批表信息   
  	$.ajax({
   		    dataType: 'json',
   			url:ctx+"/common/exam!getExamByid.action?proc_id="+proc_id+"&time="+new Date(),
   			success:function(data){
   			 	if(data){
   			 		
   			 		
   			 			
   			 			if(data.examf){
   			 			var cssj = data.examf.opinion_time;
                        if(cssj != null){
                       	 $("#cssj").val(cssj.substr(0,cssj.length-2));  
                        }
   			 			
   			 		    $("#csyj").val(data.examf.opinion_content);
   			 		    $("#csr").val(data.examf.checker_no);
   			 				
   			 			}
   			 		if(data.exams){
   			 			
   			 		var fssj =data.exams.opinion_time;
					 if(fssj != null){
						 $("#fssj").val(fssj.substr(0,fssj.length-2));  
					 }
   			 		    $("#fsyj").val(data.exams.opinion_content);
   			 		    $("#fsr").val(data.exams.checker_no);
   			 				
   			 			}
   			 	   if(data.examt){
   			 		 var shsj = data.examt.opinion_time;
					 if(shsj != null){
						 $("#shsj").val(shsj.substr(0,shsj.length-2));  
						 
					 }
			 		    $("#shyj").val(data.examt.opinion_content);
			 		    $("#shr").val(data.examt.checker_no);
			 				
			 			}
   			       if(data.examfo){
   			    	var hzsj = data.examfo.opinion_time;
					 if(hzsj != null){
						 $("#hzsj").val(hzsj.substr(0,hzsj.length-2)); 
						 
					 }
		 		    $("#hzyj").val(data.examfo.opinion_content);
		 		    $("#hzr").val(data.examfo.checker_no);
		 				
		 			}
   			 	}
   				
   			 _init_form_data = $("#main_form").serializeJson(); 
   			}
   		});
	
	
}

/**********************************************************************************
*函数名称: saveExamFirst()
*功能说明: 保存初审意见
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function saveExamFirst(){
	var result = true;
	var yj = $("#csyj").val()
	var sj = $("#cssj").val()
	var r = $("#csr").val()
	
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaFirst.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"exf.checker_no":r,"exf.opinion_content":yj,"exf.opinion_time":sj,"exf.opinion_type":"初审"},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getExam();
			 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',data.errorMessage,'error');
				}
	   		},error:function(data){
	   			result = false;
	   			top.$.messager.alert('保存失败提示',"保存失败"+data,'error');
	   		}
	   	});
	
	
	return result;
}

/**********************************************************************************
*函数名称: saveExamSecond()
*功能说明: 保存复审意见
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function saveExamSecond(){
	var result = true;
	var yj = $("#fsyj").val()
	var sj = $("#fssj").val()
	var r = $("#fsr").val()
	
	
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaSecond.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"exs.checker_no":r,"exs.opinion_content":yj,"exs.opinion_time":sj,"exs.opinion_type":"复审"},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getExam();
			 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',data.errorMessage,'error');
				}
	   		},error:function(data){
	   			result = false;
	   			top.$.messager.alert('保存失败提示',"保存失败"+data,'error');
	   		}
	   	});
	return result;
}
/**********************************************************************************
*函数名称: saveExamThird()
*功能说明: 保存审核意见
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function saveExamThird(){
	var yj = $("#shyj").val()
	var sj = $("#shsj").val()
	var r = $("#shr").val()
	if($.trim(yj).length== 0){
		
		 top.$.messager.confirm('提示','审核意见不能为空，请录入审核意见或选择常用语！',function(){});
		
	}else{
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaThird.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"ext.checker_no":r,"ext.opinion_content":yj,"ext.opinion_time":sj,"ext.opinion_type":"审核"},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getExam();
			 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',data.errorMessage,'error');
				}
	   		}
	   	});
	};
}

/**********************************************************************************
*函数名称: saveExamForth()
*功能说明: 保存核准意见
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function saveExamForth(){
	var result = true;
	var yj = $("#hzyj").val()
	var sj = $("#hzsj").val()
	var r = $("#hzr").val()
	
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaForth.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"exfo.checker_no":r,"exfo.opinion_content":yj,"exfo.opinion_time":sj,"exfo.opinion_type":"核准"},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getExam();
			 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',data.errorMessage,'error');
				}
	   		},error:function(data){
	   			result = false;
	   			top.$.messager.alert('保存失败提示',"保存失败"+data,'error');
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
		var yj = $("#csyj").val();
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
	}if(activName == state1.string4){
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
*函数名称: uploadFile
*功能说明: 上传附件  弹出上传页面
*参数说明: 
*返 回 值: 己修改返回true   未修改返回false
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function uploadFile(proc_node){
	proc.openNode = proc_node;		//用来标识点的是哪个环节的附件
	 openInTopWindow({
			// 窗口元素的id
			id : 'upload_window_id',
			// 窗口iframe的src
			src : ctx+'/jsp/fileupload/uploadFile.jsp',
			// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy : true,
			// 窗口标题
			title : proc_node+'附件',
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
				this.init(proc);
			}
		});
	//window.open(ctx+'/fileupload/uploadFile.action', proc_node+'附件上传', 'height=480, width=600, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=true,location=n o, status=no');
}

/**********************************************************************************
*函数名称: getUploadFile
*功能说明: 获取与该环节关联的附件
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getUploadFile(proc_node){
	$.ajax({
   		dataType:'json',
   		url:ctx+"/common/filerel!getFileRelView.action?time="+new Date(),
   		contentType:"application/x-www-form-urlencoded; charset=GBK",
   		//表单的序列化操作
   		data:{proc_id:proc.procId,proc_node:proc_node},
   		success:function(data){
   			//alert($.toJSON(data));
		 	if(data.idList){
		 		//location.href= ctx+'/commons/fileupload/upload.do?method=imgView&uploadId='+data.uploadId+'&type=flash&idList='+data.idList+'&index=1#p=1';
		 		
		 		 openInTopWindow({
		 			// 窗口元素的id
		 			id : 'upload_pre_window',
		 			// 窗口iframe的src
		 			src : ctx+'/commons/fileupload/upload.do?method=imgView&uploadId='+data.uploadId+'&type=flash&idList='+data.idList+'&index=1#p=1',
		 			// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
		 			destroy : true,
		 			// 窗口标题
		 			title : proc_node+'附件查看',
		 			// 窗口宽
		 			width : 1050,
		 			// 窗口高
		 			height : 600,
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
		 			}
		 		});
		 		
		 	}else {
				top.$.messager.alert('提示',data.errorMessage,'info');
			}
   		},error:function(data){
   			result = false;
   			top.$.messager.alert('保存失败提示',"保存失败"+data,'error');
   		}
   		
   	
   	});
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
	//if(proc_node == state1.string0 || proc_node == state1.string1){
		_cur_form_data = $("#main_form").serializeJson(); 
		
		var r = equal(_init_form_data,_cur_form_data);
		//如果不相等返回  页面数据己修改  返回true
		if(!r){
		  return true;
		}
	//}
	return false;
}

	  
