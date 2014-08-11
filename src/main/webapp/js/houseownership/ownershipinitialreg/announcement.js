var proc;
proc=this.parent.proc;

var proc_id = proc.procId;
//var proc_id = 1000016427;
var activName = proc.activName;
//var activName = "公告";
var procdefId = proc.procdefId;
//流程节点集合
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
		string12: "拟定公告",
		string13: "公告"
	};
//初始化开始
$(function(){
	setState(activName);
	getAnnounce();  
	if(activName == state1.string13){
		
		$("#ggry").val(user);
		
	}
	
   
      	
        //获取焦点事件
   		$('#ggnr').focus(function(){
   		
   			focus();
   		});
   		$('#ggcsyj').focus(function(){
   			focus();
   		});
   		$('textarea#ggfsyj').focus(function(){
   			focus();
   		});
   		$('textarea#ggshyj').focus(function(){
   			focus();
   		});
   		$('textarea#gghzyj').focus(function(){
   			focus();
   		});
   		
   		//失去焦点事件 		
   		$('textarea#ggnr').blur(function(){
   			blur();
   		});
   		$('textarea#ggcsyj').blur(function(){
   			blur();
   		});
   		$('textarea#ggfsyj').blur(function(){
   			blur();
   		});
   		$('textarea#ggshyj').blur(function(){
   			blur();
   		});
   		$('textarea#gghzyj').blur(function(){
   			blur();
   		});
   		
   		
 }); //初始化结束  
//获取地址栏参数
function GetQueryString(name)
{
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r!=null) return unescape(r[2]);
	return null;
}

//意见保存更新操作
function submit(){
	 if(activName == state1.string12){
			
		   saveAnnounce();
	   };
	   if(activName == state1.string13){
			//alert("保存公告信息");
		   saveAnnouncePub();
	   };
	   if(activName == state1.string5){
		
		   saveExamFirst();
	   };
	   if(activName == state1.string6){
		   saveExamSecond();
	   };
	   if(activName == state1.string7){
		   saveExamThird();
	   };
	   if(activName == state1.string8){
		   saveExamForth();
	   };
    
	   return true;
};
 //根据流程节点设置控件权限
function setState(activName){
	    // alert(activName);
	      $(":input").attr("disabled", "disabled");   
	       if(activName == state1.string13){
	    	   $(".test").removeAttr("disabled");
	    	   $(".combo-text").removeAttr("disabled");
	    	   $(".spinner-text").removeAttr("disabled");
	    	   
	    	  
	    	   
	       }
	      if(activName == state1.string5){
	    	   
	    	  $("#ggcsyj").removeAttr("disabled");
	    	  $("#ggcscyy").removeAttr("disabled");
	    	  $("#ggsj").combo("disable");
	    	  $("#fbsj").combo("disable");
	    	  $("#cbwdzrq").combo("disable");
	    	
	      };
	      if(activName == state1.string6){
	    	   
	    	  $("#ggfsyj").removeAttr("disabled");
	    	  $("#ggfscyy").removeAttr("disabled");
	    	  $("#ggsj").combo("disable");
	    	  $("#fbsj").combo("disable");
	    	  $("#cbwdzrq").combo("disable");
	      };
	      if(activName == state1.string7){
		       
	    	  
	    	  $("#ggshyj").removeAttr("disabled");
	    	  $("#ggshcyy").removeAttr("disabled");
	    	  $("#ggsj").combo("disable");
	    	  $("#fbsj").combo("disable");
	    	  $("#cbwdzrq").combo("disable");
		      };
	      if(activName == state1.string8){
	       
	    	   
	    	  $("#gghzyj").removeAttr("disabled");
	    	  $("#gghzcyy").removeAttr("disabled");
	    	  $("#ggsj").combo("disable");
	    	  $("#fbsj").combo("disable");
	    	  $("#cbwdzrq").combo("disable");
	      };
	      if(activName == state1.string12){
	    	 
	    	  $("#ggnr").removeAttr("disabled");
	    	  $("#ggmb").removeAttr("disabled");
	    	  $("#ggsj").combo("disable");
	    	  $("#fbsj").combo("disable");
	    	  $("#cbwdzrq").combo("disable");
	      };
	      if(!(activName == state1.string5 || activName == state1.string6 ||activName == state1.string7 ||activName == state1.string8 || activName == state1.string12 ||activName == state1.string13)){
	    	  $(":input").attr("disabled", "disabled"); 
	    	  $("#ggsj").combo("disable");
	    	  $("#fbsj").combo("disable");
	    	  $("#cbwdzrq").combo("disable");
	    	  
	    	  
	      }
	  
	  };
//获取系统当前时间
	  function getTime(){
	  var myDate = new Date();
	  var year = myDate.getFullYear();
	  var month = myDate.getMonth()+1;
	  var date = myDate.getDate(); 
      var mytime=myDate.toLocaleTimeString(); //获取当前时间	  
	  var time = year+"-"+month+"-"+date+" "+mytime;
	  return time;
 };

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
//选择公告模版
function selectGgmb(){
	 //alert("选择常用语");
	// window.open(ctx+"/houseownership/ownershipinitialreg/announceselect.action","newindow","width=600,height=400,toolbar=no,scrollbars=no");
	 openInTopWindow({
			// 窗口元素的id
			id : 'anmodel',
			// 窗口iframe的src
			src : ctx+'/jsp/houseownership/ownershipinitialreg/announce-select.jsp?time='+new Date(),
			// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy : true,
			// 窗口标题
			title : '选择公告模版',
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
	 
} 

//当意见框获取焦点时，保存当前用户和当前时间
function focus1(){
	if(activName == state1.string5){
		
		//  获取当前时间
	         $("#ggndsj").val(getTime());
	         //获取当前审批人
	        $("#ggndr").val(user); 
	        //  获取当前时间
	        
 };
	
}
function focus(){
if(activName == state1.string12){
		
		//  获取当前时间
	         $("#ggndsj").val(getTime());
	         //获取当前审批人
	        $("#ggndr").val(user); 
	        //  获取当前时间
	        
 };
	
	 if(activName == state1.string5){
			
			//  获取当前时间
		         $("#ggcssj").val(getTime());
		         //获取当前审批人
		        $("#ggcsr").val(user); 
		    
	 };
	 if(activName == state1.string6){
			
			//  获取当前时间
		         $("#ggfssj").val(getTime());
		         //获取当前审批人
		        $("#ggfsr").val(user); 
	 };
	 if(activName == state1.string7){
			
			//  获取当前时间
		         $("#ggshsj").val(getTime());
		         //获取当前审批人
		        $("#ggshr").val(user); 
	 };
	 if(activName == state1.string8){
			//  获取当前时间
		         $("#gghzsj").val(getTime());
		         //获取当前审批人
		        $("#gghzr").val(user); 
	 };
};




//当意见框获取焦点时，保存当前用户和当前时间
function blur1(){
	if(activName == state1.string5){
		
		//  获取当前时间
	         $("#ggndsj").val(getTime());
	         //获取当前审批人
	        $("#ggndr").val(user); 
	        //  获取当前时间
	        
 };
	
}

function blur(){
	if(activName == state1.string12){
		
		//  获取当前时间
	         $("#ggndsj").val(getTime());
	         //获取当前审批人
	        $("#ggndr").val(user); 
	        //  获取当前时间
	        
 };
	
	 if(activName == state1.string5){
			
			//  获取当前时间
		         $("#ggcssj").val(getTime());
		         //获取当前审批人
		        $("#ggcsr").val(user); 
		        //  获取当前时间
		        
	 };
	 if(activName == state1.string6){
			
			//  获取当前时间
		         $("#ggfssj").val(getTime());
		         //获取当前审批人
		        $("#ggfsr").val(user); 
	 };
	 if(activName == state1.string7){
			
			//  获取当前时间
		         $("#ggshsj").val(getTime());
		         //获取当前审批人
		        $("#ggshr").val(user); 
	 };
	 if(activName == state1.string8){
			//  获取当前时间
		         $("#gghzsj").val(getTime());
		         //获取当前审批人
		        $("#gghzr").val(user); 
	 };
	
};
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
						 $("#ggndsj").val(ggndsj.substr(0,ggndsj.length-2));
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
		   			 	   $('#ggsj').datetimebox('setValue',ggsj.substr(0,ggsj.length-2));
		   			 		 
		   			 	 }
		   			 	 
		   			 	 $("#fbdw").val(data.annouce.notice_pub_off);
		   			 	
		   			 	 
		   			 	 var fbsj = data.annouce.notice_pub_time;
		   			 	 if(fbsj != null){
		   			 		//$("#fbsj").val(fbsj.substr(0,fbsj.length-2));
		   			 	$('#fbsj').datetimebox('setValue',fbsj.substr(0,fbsj.length-2));
		   			// $('#fbsj').datetimebox('setValue', '6/1/2012 12:30:56');	
		   			 		 
		   			 	 }
		   			 	 		   		 
		   			 	 $("#cbwmc").val(data.annouce.pub_name_date);
		   			 	 var cbsj = data.annouce.pub_date;
		   			 	 if(cbsj != null){
		   			 		 
		   			 		//$("#cbwdzrq").val(cbsj.substr(0,cbsj.length-2)); 
		   			 		$('#cbwdzrq').datetimebox('setValue',cbsj.substr(0,cbsj.length-2));
		   			 	 }
		   			 	 
		   			 	 
					 };
					 if(data.examf){
                         var cssj = data.examf.opinion_time;
                         if(cssj != null){
                        	 $("#ggcssj").val(cssj.substr(0,cssj.length-2));  
                         }
						 
						 $("#ggcsyj").val(data.examf.opinion_content);
		   			 	 $("#ggcsr").val(data.examf.checker_no);
						 
					 };
					 if(data.exams){
						 
						 var fssj =data.exams.opinion_time;
						 if(fssj != null){
							 $("#ggfssj").val(fssj.substr(0,fssj.length-2));  
						 }
						 $("#ggfsyj").val(data.exams.opinion_content);
		   			 	$("#ggfsr").val(data.exams.checker_no);
		   			
						 
					 };
					 if(data.examt){
						 var shsj = data.examt.opinion_time;
						 if(shsj != null){
							 $("#ggshsj").val(shsj.substr(0,shsj.length-2));  
							 
						 }
						 
						 $("#ggshr").val(data.examt.checker_no);
		   			 		$("#ggshyj").val(data.examt.opinion_content);
					 };
					 if(data.examfo){
						 var hzsj = data.examfo.opinion_time;
						 if(hzsj != null){
							 $("#gghzsj").val(hzsj.substr(0,hzsj.length-2)); 
							 
						 }
						 
						
						 $("#gghzr").val(data.examfo.checker_no);
		   			 	 $("#gghzyj").val(data.examfo.opinion_content);
					 }
					                				
				}
			}
});


}
//保存公告及相关信息
function saveAnnounce(){
	var yj = $("#ggnr").val()
	var sj = $("#ggndsj").val()
	var r = $("#ggndr").val()
	if($.trim(yj).length== 0){
		
		 top.$.messager.confirm('提示','公告内容不能为空，请录入公告内容或选择公告模版！',function(){});
		
	}else{
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveAnnounce.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"an.notice_pro_person":r,"an.notice_content":yj,"an.notice_pro_time":sj},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getAnnounce();
			 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',data.errorMessage,'error');
				}
	   		}
	   	});
	}
}


//保存初步审定意见
function saveExamFirst(){
	var yj = $("#ggcsyj").val()
	var sj = $("#ggcssj").val()
	var r = $("#ggcsr").val()
	if($.trim(yj).length== 0){
		
		 top.$.messager.confirm('提示','初步审定意见不能为空，请录入意见或选择常用语！',function(){});
		
	}else{
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaFirst.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"exf.checker_no":r,"exf.opinion_content":yj,"exf.opinion_time":sj,"exf.opinion_type":"初步审定"},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getAnnounce();
			 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',data.errorMessage,'error');
				}
	   		}
	   	});
	}
}

//保存公告审定意见
function saveExamSecond(){
	var yj = $("#ggfsyj").val()
	var sj = $("#ggfssj").val()
	var r = $("#ggfsr").val()
	if($.trim(yj).length== 0){
		
		 top.$.messager.confirm('提示','公告审定意见不能为空，请录入意见或选择常用语！',function(){});
		
	}else{
	
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaSecond.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"exs.checker_no":r,"exs.opinion_content":yj,"exs.opinion_time":sj,"exs.opinion_type":"公告审定"},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getAnnounce();
			 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',data.errorMessage,'error');
				}
	   		}
	   	});
	};
}

//保存公告结果初审意见
function saveExamThird(){
	var yj = $("#ggshyj").val()
	var sj = $("#ggshsj").val()
	var r = $("#ggshr").val()
	if($.trim(yj).length== 0){
		
		 top.$.messager.confirm('提示','公告初审意见不能为空，请录入审核意见或选择常用语！',function(){});
		
	}else{
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaThird.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"ext.checker_no":r,"ext.opinion_content":yj,"ext.opinion_time":sj,"ext.opinion_type":"公告结果初审"},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		getAnnounce();
			 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',data.errorMessage,'error');
				}
	   		}
	   	});
	};
}
//保存公告结果复审意见
function saveExamForth(){
	
	var yj = $("#gghzyj").val()
	var sj = $("#gghzsj").val()
	var r = $("#gghzr").val()
	if($.trim(yj).length== 0){
		
		 top.$.messager.confirm('提示','公告结果复审意见不能为空，请录入意见或选择常用语！',function(){});
		
	}else{
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveExaForth.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"exfo.checker_no":r,"exfo.opinion_content":yj,"exfo.opinion_time":sj,"exfo.opinion_type":"公告结果复审"},
	   		success:function(data){
			 	if(data){
			 	
			 		//alert(data);
			 		getAnnounce();
			 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',data.errorMessage,'error');
				}
	   		}
	   	});
	}
}
	   			 		
//保存公告发布信息
function saveAnnouncePub(){
	   
	    
		$.ajax({
	   		dataType:'json',
	   		url:ctx+"/common/exam!saveAnnouncePub.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		//data:{"exfo.checker_no":r,"exfo.opinion_content":yj,"exfo.opinion_time":sj,"exfo.opinion_type":"公告结果复审"},
	   		
	   		data:$("#add_gg_form").serialize(),
	   		success:function(data){
			 	if(data){
			 		
			 		getAnnounce();
			 		top.$.messager.alert('保存成功提示',data.tipMessage,'info',function(){
					});	
			 		
			 	}else {
					top.$.messager.alert('保存失败提示',data.errorMessage,'error');
				}
	   		}
	   	});
	
}
//生成常用语面板

	  
/**********************************************************************************
*函数名称: 页面校验方法
*功能说明: 验证页面上的非空  及数据格式   
*参数说明: flag 1代表保存 提交不传值 用来区分保存和提交
*返 回 值: obj  result(true通过  false不通过) message(消息)  page_name(当前页面名字)
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function validate(v_flag){
	//返回结果对象
	var result ={
			result:false,
			message:'',
			page_name:'公告表'
	}
	var message;
	
	result.result = true;
	return result;
}

	  
	  
