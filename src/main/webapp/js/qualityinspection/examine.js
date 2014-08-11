//var proc;
//proc=this.parent.proc;
//alert(proc);
//var proc_id ;//= proc.procId;
var activName ;//= proc.activName;
var procdefId ;//= proc.procdefId;
var serialNumber ={num1:"登记编号",num2:"归档号",num3:"房地产证号"};
var _init_form_data;							//初始化时数据   用于判断当前页面数据是否己修改
var _cur_form_data;								//验证时数据   用于判断当前页面数据是否己修改
var option_type;								//当前操作环节
var examine_hidden_element;
var state1 = {
		string1 : "检查",
		string2 : "负责人审批",
		string3 : "承办人处理",
		string4 : "一级审批",
		string5 : "二级审批",
		string6 : "初审",
		string7 : "复审",
		string8 : "审核",
		string9 : "核准"
	};
var title1 = {
		string1 : '初审意见（初审人员）',
		string2 : '复审意见（核准人员）',
		string3 : '审核意见（科室领导）',
		string4 : '核准意见（中心领导）'
}

function examineInit(){
	//alert(" proc.procId"+ proc.procId);
	proc_id = proc.procId;
	activName = proc.activName;
	//activName = state1.string4;//测试
	procdefId = proc.procdefId;
	option_type = proc.activName;
	//option_type = activName;//测试
	//setTimeout(alert(proc),3000);
	
	focus();
    setState(activName);	
    getExamine();	
	    //获取焦点事件
		//$('textarea').focus(function(){
			//focus();
		//});
		//失去焦点事件
		//$('textarea').blur(function(){
			//blur();
		//});   
}
//$(function(){
//			alert(proc);
//	        setState(activName);	
//	        getExamine();	
//      	    //获取焦点事件
//       		$('textarea').focus(function(){
//       			focus();
//       		});
//       		//失去焦点事件
//       		$('textarea').blur(function(){
//       			blur();
//       		});   
//       });
//       
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
	saveExamine();
 };
//根据流程节点设置控件权限
function setState(activName){
	      if(activName == state1.string1){
		       inputDisable_Style("#fsyj");
		       inputDisable_Style("#shyj1");
		       inputDisable_Style("#yjspyj");
		       inputDisable_Style("#ejspyj");
	      }
	      else if(activName == state1.string2){
	    	  inputDisable_Style("#csyj");
		      inputDisable_Style("#shyj1");
		      inputDisable_Style("#yjspyj");
		      inputDisable_Style("#ejspyj");
	      }
	      else if(activName == state1.string3){
	    	  inputDisable_Style("#csyj");
	    	  inputDisable_Style("#fsyj");
	    	  inputDisable_Style("#yjspyj");
		      inputDisable_Style("#ejspyj");
		  }
	      else if(activName == state1.string4){
	    	  inputDisable_Style("#csyj");
	    	  inputDisable_Style("#fsyj");
	    	  inputDisable_Style("#shyj1");
		      inputDisable_Style("#ejspyj");
		  }
	      else if(activName == state1.string5){
	    	  inputDisable_Style("#csyj");
	    	  inputDisable_Style("#fsyj");
	    	  inputDisable_Style("#shyj1");
	    	  inputDisable_Style("#yjspyj");
		  }
	      else if(activName == state1.string6){
		       inputDisable_Style("#fsyj");
		       inputDisable_Style("#shyj1");
		       inputDisable_Style("#hzyj");
		       inputDisable_Style("#yjspyj");
		       inputDisable_Style("#ejspyj");
	      }
	      else if(activName == state1.string7){
	    	  inputDisable_Style("#csyj");
		      inputDisable_Style("#shyj1");
		      inputDisable_Style("#hzyj");
		      inputDisable_Style("#yjspyj");
		      inputDisable_Style("#ejspyj");
	      }
	      else if(activName == state1.string8){
	    	  inputDisable_Style("#csyj");
	    	  inputDisable_Style("#fsyj");
	    	  inputDisable_Style("#hzyj");
	    	  inputDisable_Style("#yjspyj");
		      inputDisable_Style("#ejspyj");
		  }
	      else if(activName == state1.string9){
	    	  inputDisable_Style("#csyj");
	    	  inputDisable_Style("#fsyj");
	    	  inputDisable_Style("#shyj1");
	    	  inputDisable_Style("#yjspyj");
	    	  inputDisable_Style("#ejspyj");
	      }
	      else{
	    	 // inputDisable_Style(":input").attr("disabled", "disabled");  
	    	  inputDisable_Style("#csyj");
	    	  inputDisable_Style("#fsyj");
		      inputDisable_Style("#shyj1");
		      inputDisable_Style("#hzyj");
		      inputDisable_Style("#yjspyj");
		      inputDisable_Style("#ejspyj");
	       }
	      /*(!(activName == state1.string1 
	    		  || activName == state1.string2 
	    		  || activName == state1.string3 
	    		  || activName == state1.string4 
	    		  || activName == state1.string5 
	    		  || activName == state1.string6 
	    		  || activName == state1.string7 
	    		  || activName == state1.string8))*/
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
				//获取子页面的值
				  $("#csyj").text(value);
				//  获取当前时间
			         $("#cssj").val(getTime());
			         //获取当前审批人
			        $("#csr").val(user); 
		 }
	   else if(activName == state1.string2){
				//获取子页面的值
				  $("#fsyj").text(value);
				//  获取当前时间
			         $("#fssj").val(getTime());
			         //获取当前审批人
			        $("#fsr").val(user); 
		 }
	   else if(activName == state1.string3){
				//获取子页面的值
				  $("#shyj").text(value);
				//  获取当前时间
			         $("#shsj").val(getTime());
			         //获取当前审批人
			        $("#shr").val(user); 
		 }
	   else if(activName == state1.string4){
				//获取子页面的值
				  $("#yjspyj").text(value);
				//  获取当前时间
			         $("#yjspsj").val(getTime());
			         //获取当前审批人
			        $("#yjspr").val(user); 
		 }
	   else if(activName == state1.string5){
				//获取子页面的值
				  $("#ejspyj").text(value);
				//  获取当前时间
			         $("#ejspsj").val(getTime());
			         //获取当前审批人
			        $("#ejspr").val(user); 
	   }
	   else if(activName == state1.string6){
			//获取子页面的值
			  $("#csyj").text(value);
			//  获取当前时间
		         $("#cssj").val(getTime());
		         //获取当前审批人
		        $("#csr").val(user); 
	 }
     else if(activName == state1.string7){
			//获取子页面的值
			  $("#fsyj").text(value);
			//  获取当前时间
		         $("#fssj").val(getTime());
		         //获取当前审批人
		        $("#fsr").val(user); 
	 }
     else if(activName == state1.string8){
			//获取子页面的值
			  $("#shyj1").text(value);
			//  获取当前时间
		         $("#shsj1").val(getTime());
		         //获取当前审批人
		        $("#shr1").val(user); 
	 }
     else if(activName == state1.string9){
    	 //获取子页面的值
    	 $("#hzyj").text(value);
    	 //  获取当前时间
    	 $("#hzsj").val(getTime());
    	 //获取当前审批人
    	 $("#hzr").val(user); 
     }
	         
};

//当意见框获取焦点时，保存当前用户和当前时间
function focus(){
	
	  if(activName == state1.string1){
		    
			//  获取当前时间
		         $("#cssj").val(getTime());
		         //获取当前审批人
		        $("#csr").val(user); 
		        _init_form_data = $("#csyj").val();
	 }
	 else if(activName == state1.string2){
			//  获取当前时间
		         $("#fssj").val(getTime());
		         //获取当前审批人
		        $("#fsr").val(user); 
	 }
	 else if(activName == state1.string3){
	
			//  获取当前时间
		         $("#shsj1").val(getTime());
		         //获取当前审批人
		        $("#shr1").val(user); 
	 }
	 else if(activName == state1.string4){
			
			//  获取当前时间
		         $("#yjspsj").val(getTime());
		         //获取当前审批人
		        $("#yjspr").val(user); 
	 }
	 else if(activName == state1.string5){
			
			//  获取当前时间
		         $("#ejspsj").val(getTime());
		         //获取当前审批人
		        $("#ejspr").val(user); 
	 }
	 else if(activName == state1.string6){
		    
			//  获取当前时间
		         $("#cssj").val(getTime());
		         //获取当前审批人
		        $("#csr").val(user); 
		        _init_form_data = $("#csyj").val();
	 }
	 else if(activName == state1.string7){
			//  获取当前时间
		         $("#fssj").val(getTime());
		         //获取当前审批人
		        $("#fsr").val(user); 
	 }
	 else if(activName == state1.string8){
	
			//  获取当前时间
		         $("#shsj1").val(getTime());
		         //获取当前审批人
		        $("#shr1").val(user); 
	 }
	 else if(activName == state1.string9){
		 
		 //  获取当前时间
		 $("#hzsj").val(getTime());
		 //获取当前审批人
		 $("#hzr").val(user); 
	 }
	
};




//当意见框获取焦点时，保存当前用户和当前时间

function blur(){
	
	  if(activName == state1.string1){
		    
			//  获取当前时间
		        $("#cssj").val(getTime());
		        //获取当前审批人
		        $("#csr").val(user); 
	 }
	  else if(activName == state1.string2){
			//  获取当前时间
		         $("#fssj").val(getTime());
		         //获取当前审批人
		        $("#fsr").val(user); 
	 }
	  else if(activName == state1.string3){
	
			//  获取当前时间
		         $("#shsj").val(getTime());
		         //获取当前审批人
		        $("#shr").val(user); 
	 }
	  else if(activName == state1.string4){
			
			//  获取当前时间
		         $("#yjspsj").val(getTime());
		         //获取当前审批人
		        $("#yjspr").val(user); 
	 }
	  else if(activName == state1.string5){
			
			//  获取当前时间
		         $("#ejspsj").val(getTime());
		         //获取当前审批人
		        $("#ejspr").val(user); 
	 }
	  else if(activName == state1.string6){
		    
			//  获取当前时间
		        $("#cssj").val(getTime());
		        //获取当前审批人
		        $("#csr").val(user); 
	 }
	  else if(activName == state1.string7){
			//  获取当前时间
		         $("#fssj").val(getTime());
		         //获取当前审批人
		        $("#fsr").val(user); 
	 }
	  else if(activName == state1.string8){
	
			//  获取当前时间
		         $("#shsj1").val(getTime());
		         //获取当前审批人
		        $("#shr1").val(user); 
	 }
	  else if(activName == state1.string8){
		  
		  //  获取当前时间
		  $("#hzsj").val(getTime());
		  //获取当前审批人
		  $("#hzr").val(user); 
	  }
};

//获取审批表信息
function getExamine(){
	
	  //初始化审批表信息   
  	$.ajax({
   		    dataType: 'json',
   			url:ctx+"/common/exam!getExamineById.action?proc_id="+proc_id+"&time="+new Date(),
   			success:function(data){
   			 	if(data){
   			 		//alert($.toJSON(data));
   			 		for(var i=0;i<data.length;i++)
   			 		{
	   			 		if(data[i].opinion_type == state1.string1){
	   	   					$("#csyj").val(data[i].opinion_content);
	   	   					$("#cssj").val(data[i].opinion_timestr);
	   	   					$("#csr").val(data[i].checker_no);
	   	   				}else if(data[i].opinion_type == state1.string2){
	   	   					$("#fsyj").val(data[i].opinion_content);
	   	   					 $("#fssj").val(data[i].opinion_timestr);
	   	   					$("#fsr").val(data[i].checker_no);
	   	   				}else if(data[i].opinion_type == state1.string3){
	   	   					$("#shyj1").val(data[i].opinion_content);
	   	   					$("#shsj1").val(data[i].opinion_timestr);
	   	   					$("#shr1").val(data[i].checker_no);
	   	   				}else if(data[i].opinion_type == state1.string4){
	   	   					$("#yjspyj").val(data[i].opinion_content);
	   	   					 $("#yjspsj").val(data[i].opinion_timestr);
	   	   					$("#yjspr").val(data[i].checker_no);
	   	   				}else if(data[i].opinion_type == state1.string5){
	   	   					$("#ejspyj").val(data[i].opinion_content);
	   	   					$("#ejspsj").val(data[i].opinion_timestr);
	   	   					$("#ejspr").val(data[i].checker_no);
	   	   				}else if(data[i].opinion_type == state1.string6){
	   	   					$("#csyj").val(data[i].opinion_content);
	   	   					$("#cssj").val(data[i].opinion_timestr);
	   	   					$("#csr").val(data[i].checker_no);
	   	   				}else if(data[i].opinion_type == state1.string7){
	   	   					$("#fsyj").val(data[i].opinion_content);
	   	   					 $("#fssj").val(data[i].opinion_timestr);
	   	   					$("#fsr").val(data[i].checker_no);
	   	   				}else if(data[i].opinion_type == state1.string8){
	   	   					$("#shyj1").val(data[i].opinion_content);
	   	   					$("#shsj1").val(data[i].opinion_timestr);
	   	   					$("#shr1").val(data[i].checker_no);
	   			 		}else if(data[i].opinion_type == state1.string9){
	   			 			$("#hzyj").val(data[i].opinion_content);
	   			 			$("#hzsj").val(data[i].opinion_timestr);
	   			 			$("#hzr").val(data[i].checker_no);
	   			 		}
   			 		}
   			 		/*	if(data.examf){
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
		 				
		 			}*/
   			 	}
   			 _init_form_data = getexamineInitdata();
   				 
   			}
   		});
	
	
}



  


//保存意见
function saveExamine(){
	var vaResult = validate(1);
	//验证不通过
	if(!vaResult.result){
		top.$.messager.alert('提示',vaResult.message,'info',null);
		return;
	}
	//alert(proc_id);
	//return;
	var yj ;//= $("#csyj").val()
	var sj ;//= $("#cssj").val()
	var r ;//= $("#csr").val()
	
	var result = true; 
	if(option_type == state1.string1){
		yj = $("#csyj").val()
		sj = $("#cssj").val()
		r = $("#csr").val()
	}else if(option_type == state1.string2){
		yj = $("#fsyj").val()
		sj = $("#fssj").val()
		r = $("#fsr").val()
	}else if(option_type == state1.string3){
		yj = $("#shyj1").val()
		sj = $("#shsj1").val()
		r = $("#shr1").val()
	}else if(option_type == state1.string4){
		yj = $("#yjspyj").val()
		sj = $("#yjspsj").val()
		r = $("#yjspr").val()
	}else if(option_type == state1.string5){
		yj = $("#ejspyj").val()
		sj = $("#ejspsj").val()
		r = $("#ejspr").val()
	}else if(option_type == state1.string6){
		yj = $("#csyj").val()
		sj = $("#cssj").val()
		r = $("#csr").val()
	}else if(option_type == state1.string7){
		yj = $("#fsyj").val()
		sj = $("#fssj").val()
		r = $("#fsr").val()
	}else if(option_type == state1.string8){
		yj = $("#shyj1").val()
		sj = $("#shsj1").val()
		r = $("#shr1").val()
	}else if(option_type == state1.string9){
		yj = $("#hzyj").val()
		sj = $("#hzsj").val()
		r = $("#hzr").val()
	}
	
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaine.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"examine.checker_no":r,"examine.opinion_content":yj,"examine.opinion_time":sj,"examine.opinion_type":option_type},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getexamineInitdata();
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
		var yj = $("#csyj").val();
		if($.trim(yj).length== 0){
			message = '检查结果及处理意见 不能为空，请录入检查结果及处理意见 或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 
			 result.message=message;
			 result.result=false;
			 return result;
		}
		
	}else if(activName == state1.string2){
		var yj = $("#fsyj").val()
		if($.trim(yj).length== 0){
			message = '科室负责人审批 不能为空，请录入科室负责人审批或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}else if(activName == state1.string3){
		var yj = $("#shyj1").val()
		if($.trim(yj).length== 0){
			message = ' 处理结果 不能为空，请录入 处理结果 或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}else if(activName == state1.string4){
		var yj = $("#yjspyj").val()
		if($.trim(yj).length== 0){
			message = ' 一级审批意见 不能为空，请录入 一级审批意见 或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}else if(activName == state1.string5){
		var yj = $("#ejspyj").val()
		if($.trim(yj).length== 0){
			message = ' 二级审批意见不能为空，请录入 二级审批意见 或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}else if(activName == state1.string6){
		var yj = $("#csyj").val();
		if($.trim(yj).length== 0){
			message = '初审结果及处理意见 不能为空，请录入初审结果及处理意见 或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 
			 result.message=message;
			 result.result=false;
			 return result;
		}
		
	}else if(activName == state1.string7){
		var yj = $("#fsyj").val()
		if($.trim(yj).length== 0){
			message = '复审结果及处理意见 不能为空，请录入复审结果及处理意见 或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}else if(activName == state1.string8){
		var yj = $("#shyj1").val()
		if($.trim(yj).length== 0){
			message = '审核结果及处理意见 不能为空，请录入审核结果及处理意见 或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}else if(activName == state1.string9){
		var yj = $("#hzyj").val()
		if($.trim(yj).length== 0){
			message = '核准结果及处理意见 不能为空，请录入核准结果及处理意见 或选择常用语！';
//			 top.$.messager.confirm('提示',message,function(){});
			 result.message=message;
			 result.result=false;
			 return result;
		}
	}
	//不是保存操作才判断  页面数据修改
	if(!v_flag){
	//判断数据项是否己修改  如果己修改  则提示是否保存未保存数据
		_cur_form_data = getexamineInitdata(); 
		
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
	}else{
		_init_form_data = getexamineInitdata(); 
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
/**********************************************************************************
*函数名称: getexamineInitdata
*功能说明: 初始化当前意见时   获取初始值   用来离开页面时提醒是否保存
*参数说明: 返回当前环节意见
*返 回 值: 己修改返回true   未修改返回false
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getexamineInitdata(){
	var result;
	  if(activName == state1.string1){
		    
			//  获取当前时间
		         $("#cssj").val(getTime());
		         //获取当前审批人
		        $("#csr").val(user); 
		        result = $("#csyj").val();
	 }
	  else if(activName == state1.string2){
			//  获取当前时间
		         $("#fssj").val(getTime());
		         //获取当前审批人
		        $("#fsr").val(user); 
		        result = $("#fsyj").val();
	 }
	  else if(activName == state1.string3){
	
			//  获取当前时间
		         $("#shsj1").val(getTime());
		         //获取当前审批人
		        $("#shr1").val(user); 
		        result = $("#shyj1").val();
	 }
	  else if(activName == state1.string4){
			//  获取当前时间
			 $("#yjspsj").val(getTime());
			 //获取当前审批人
			$("#yjspr").val(user); 
			result = $("#yjspyj").val();
	 }
	  else if(activName == state1.string5){
	
			//  获取当前时间
		 $("#ejspsj").val(getTime());
		 //获取当前审批人
		$("#ejspr").val(user); 
		result = $("#ejspyj").val();
	}
	  else if(activName == state1.string6){
	    
		//  获取当前时间
	         $("#cssj").val(getTime());
	         //获取当前审批人
	        $("#csr").val(user); 
	        result = $("#csyj").val();
	 }
	  else if(activName == state1.string7){
			//  获取当前时间
		         $("#fssj").val(getTime());
		         //获取当前审批人
		        $("#fsr").val(user); 
		        result = $("#fsyj").val();
	 }
	  else if(activName == state1.string8){
	
			//  获取当前时间
		         $("#shsj1").val(getTime());
		         //获取当前审批人
		        $("#shr1").val(user); 
		        result = $("#shyj1").val();
	 }
	  else if(activName == state1.string9){
		  
		  //  获取当前时间
		  $("#hzsj").val(getTime());
		  //获取当前审批人
		  $("#hzr").val(user); 
		  result = $("#hzyj").val();
	  }
	 return result;
}

function examineHiddenElement(){
	if(!jds_sqb_tw){
		jds_sqb_tw = getChinaTip($.getUrlParam('jds_sqs_tw'));
	}
	$('#handling_suggestion01').text(title1.string1);
	$('#handling_suggestion02').text(title1.string2);
	$('#handling_suggestion03').text(title1.string3);
	if(jds_sqb_tw == enumdata.BACKLANGUAGESQS){
		$('#handling_opinions01').css('display','none');
	}else if(jds_sqb_tw == enumdata.DELAYSQS){
		$('#handling_opinions01').css('display','none');
	}else if(jds_sqb_tw == enumdata.HANGUPSQS){
		$('#handling_opinions01').css('display','none');
	}
	if(jds_sqb_tw == enumdata.REJECTJDS){
		$('#handling_opinions02').css('display','none');
	}else{
		$('#handling_opinions02').css('display','block');
	}
	$('#handling_suggestion04').text(title1.string4);
	$('#handling_opinions03').css('display','none');
	$('#submit').css('display','none');
	$('#cancel').css('display','none');
}
function inputDisable_Style(val){
	$(val).attr("disabled","disabled");
}
	  
