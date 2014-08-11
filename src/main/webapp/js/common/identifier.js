 var selectNode;
 
 var selectedLi;			//选中的li
 
 var selectedChar;			//选中的常用字   修改时取值
 
 var code_id;				//编号配置ID
 
 var ruleTree ;				//tree
 
 var editNodeText;			//编辑节点的文本  用来是否改变  如果未改变 则不更新到数据库
$(function(){
	$('body').layout({
		fit:true
	});		
	
	
	
	
	initTree();
	
	
	
	initTabs();
	
	$("#btnCharAdd").click(function(){
		
//		var option = $('#identifier_tree').tree("getRoot");
//		var data = $('#identifier_tree').tree("getData",option.target);
//		alert($.toJSON(data));
//		//alert($.toJSON(option));
//		
//		return;
		var char = $.trim($("#iptChar").val());
		if(char.length==0){
			alert("常用字不能为空！");
			return ;
		}
		var showChar = "常用字["+char+"]";		//显示字符
		sortAppend('char',showChar,char);
//	    var node = t.tree('getSelected');  
//	    t.tree('append', {  
//	        data: [{  
//	        	id:"char",
//	            text: '常用字['+char+']'  
//	        }]  
//	    });  
		 
		 initLiClickEvent();
	});
	
	//修改事伯
	$("#btnCharModify").click(function(){
		$(this).toggle();
		
		
		//alert(selectedChar);
		//1、常用字文本和先中的常用字文本是否未改变  如果 改变 则修改     否则  不做任何操作
		var iptCharValue = $("#iptChar").val();
		
		var selectInput = selectedChar.children("input")[0];   ///这时候已经转换为dom对象   要注意使用 
		//selectInput = selectInput[0];
		
		var selectInputValue = selectInput.value;
		
		
		
		//值 相同  不做任何修改
		if(selectInputValue == iptCharValue){
			return ;
		}
		
		
		//修改常用字
		var tmpshow = "常用字["+iptCharValue+"]";
		
		
		$(selectInput).attr("show",tmpshow);
		selectInput.value=iptCharValue;
		
		var selectLabel = selectedChar.children("label")[0];
		//alert($.toJSON(selectLabel));
		selectLabel.innerHTML=tmpshow;
		
		
		
		
		
	});
	
	
	$("#btnDelSortli").click(function(){
		$(selectedLi).remove();
	});
	
	$("#input_squence").numberspinner({  
	    min: 1,  
	    max: 10,  
	    editable: false  ,
	    onSpinUp:function(){
	    	//alert($("#input_squence").numberspinner("getValue"));
	    	appendSquence($("#input_squence").numberspinner("getValue"));
	    },onSpinDown:function(){
	    	appendSquence($("#input_squence").numberspinner("getValue"));
	    }
	});
	
//	$("#btnEditRule").click(function(){
//		if(!selectNode){
//			alert("请选择文号类型！");
//			return;
//		}
//		$.ajax({
//			url:"../common/identifier!getIdentifierIdByName.action"+"?time="+new Date(),
//			type:"post",
//			contentType:"application/x-www-form-urlencoded; charset=GBK",
//			dataType:"json",
//			data:{"name":selectNode.text},
//			success:function(data){
//				
//				//alert(data);
//				identifier_id=data.identifier_id;
//				
//				openInTopWindow({
//					//窗口元素的id
//					id: 'edit_user_win',
//					//窗口iframe的src
//					src: ctx+'/jsp/common/identifier-edit.jsp?date='+new Date(),
//					//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
//					destroy: true,
//					//窗口标题
//					title: '编辑编号信息--' + selectNode.text,
//					//窗口宽
//					width: 800,
//					//窗口高
//					height: 600,
//					modal: true,
//					//窗口中iframe的window对象的onLoad回调函数设置
//					onLoad:	function(){
//						//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
//						//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
//						this.openerWindow = window;
//						this.identifier_id=identifier_id;
//						//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
//						this.args = {
//							node : selectNode
//							
//						};
//						this.init(selectNode);
//					}
//				});
//			  }
//			});
//			
//			
//		});
	
	
	initLiClickEvent();
		
}); 

function doDetail(node){
	$("#name").val(node.text);
}



/**********************************************************************************
*函数名称: append
*功能说明: 编号树新增    保存到数据库
*函数作者: joyon
*创建日期: 2014年6月26日 14:40:39
*修改历史: 
***********************************************************************************/
function append(){  
    var t = $('#identifier_tree');  
    var parentNode = t.tree('getSelected');  
   // alert(node.id);
    var parentId = parentNode.id;
    var ruleName = 'new item';
    
    
    $.ajax({
		url:ctx+"/common/identifier!createRule.action",
		//url:ctx+"/houseownership/shiftreg!getRegInfo.action,
		data:{"ruleName":ruleName,"parentId":parentId,"time":new Date()},
		dataType:"json",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			
			//alert($.toJSON(data));
			if(data.code_cfig_id){
				//暂时未完成    树刷新 不了    需要 刷新
				//var rootNode = ruleTree.tree('find', "new_item");
				
				//ruleTree.tree("reload");
				
				 t.tree('append', {  
				        parent: (parentNode?parentNode.target:null),  
				        data: [{  
				        	id:data.code_cfig_id,
				            text: 'new item'  
				        }]  
				    }); 
				
				
				var node = ruleTree.tree('find', data.code_cfig_id);
				
				
				//更新新增树的ID  为系统自动生成的ID
//				if (node){
//					ruleTree.tree('update', {
//						target: node.target,
//						id: data.cfig_code_id
//					});
//				}
				//选中新增的节点
				ruleTree.tree('select', node.target);
				
				//var selected = ruleTree.tree('getSelected');
				
				//alert(selected.id+selected.text);
			}
		}
	});
}  


/**********************************************************************************
*函数名称: remove
*功能说明: 编号树删除    删除数据库  并删除序列   未完成
*函数作者: joyon
*创建日期: 2014年6月26日 14:40:39
*修改历史: 
***********************************************************************************/
function remove(){  
    var node = $('#identifier_tree').tree('getSelected');  
   
	top.$.messager.confirm("删除编号","是否删除编号["+node.text+"]",function(r){
		if(r){
			$.ajax({
				url:ctx+"/common/identifier!removeRule.action",
				//url:ctx+"/houseownership/shiftreg!getRegInfo.action,
				data:{"code_id":code_id,"time":new Date()},
				dataType:"json",
				type:"post",
				contentType:"application/x-www-form-urlencoded; charset=GBK",
				success:function(data){
					if(data.sign=="success"){
						$('#identifier_tree').tree('remove', node.target);  
					}
					alert(data.msg);
				}
			});
		}
		
	});
    
    
    
}  

/**********************************************************************************
*函数名称: collapse
*功能说明: 编号树折叠
*函数作者: joyon
*创建日期: 2014年6月26日 14:40:39
*修改历史: 
***********************************************************************************/
function collapse(){  
    var node = $('#identifier_tree').tree('getSelected');  
    $('#identifier_tree').tree('collapse',node.target);  
}  

/**********************************************************************************
*函数名称: expand
*功能说明: 编号树展开
*函数作者: joyon
*创建日期: 2014年6月26日 14:40:39
*修改历史: 
***********************************************************************************/
function expand(){  
    var node = $('#identifier_tree').tree('getSelected');  
    $('#identifier_tree').tree('expand',node.target);  
}  

/**********************************************************************************
*函数名称: initTree
*功能说明: 初始化当前的树结构
*函数作者: joyon
*创建日期: 2014年6月26日 14:40:33
*修改历史: 
***********************************************************************************/
function initTree(){
	ruleTree = $('#identifier_tree').tree({  
		url:ctx+"/identifier!getIdentifierTree.action",
	    animate: true, 
	    onClick:function(node){
	    	if(node.id)
	    	code_id = node.id;
	    	//alert(code_id);
	    	
	    	emptyUlTree();		//清空规则树
	    	
	    	$("#btnCharModify").css("display","none");
	    	
	    	//$('#form_rules').form('reset');
	    	//$('#form_rules')[0].reset();
	    	resetRules();
	    	
	    	getRule();			//获取当前编号下的规则
	    	//alert(node.id);
	    },
	    onBeforeEdit:function(node){
	    	editNodeText = $.trim(node.text);
	    },
	    onAfterEdit:function(node){
	    	//alert(node.id+node.text);
	    	//如果编辑前和编辑后没改变 则不做更新系统
	    	if(editNodeText==$.trim(node.text)){
	    		return;
	    	}
	    	$.ajax({
	    		url:ctx+"/common/identifier!editRule.action",
	    		//url:ctx+"/houseownership/shiftreg!getRegInfo.action,
	    		data:{"code_id":node.id,"ruleName":node.text,"time":new Date()},
	    		dataType:"json",
	    		type:"post",
	    		contentType:"application/x-www-form-urlencoded; charset=GBK",
	    		success:function(data){
	    			
	    		}
	    	});
	    	
	    },
	    onCancelEdit:function(node){
	    	alert(node.id);
	    },
	    onDblClick:function(node){$(this).tree('beginEdit',node.target)}, 
	    onContextMenu: function(e,node){  
	        e.preventDefault();  
	        $(this).tree('select',node.target);  
	        $('#mm').menu('show',{  
	            left: e.pageX,  
	            top: e.pageY  
	        });  
	    }  
	});
	
}

/**********************************************************************************
*函数名称: initTabs
*功能说明: 初始化当前的tab选项卡
*函数作者: joyon
*创建日期: 2014年6月26日 14:40:39
*修改历史: 
***********************************************************************************/
function initTabs(){
	$('#div_tabs').tabs({  
	    border:false,  
	    onSelect:function(title){  
	    	if(title=='办文抽检'){
	    		if(work_open_count==0){
	    			var Iframe=document.getElementById("work-iframe");
		    		if(Iframe.contentWindow.init){
		    			//alert(Iframe.contentWindow.init);
		    			Iframe.contentWindow.init(selectedProc);
		    		}
	    		}
	    		work_open_count++;
	    		
	    	}else if(title=="检查意见"){
	    		if(work_open_examine==0){
	    			//examineInit();
	    		}
	    		$("#save").linkbutton('enable');
	    		work_open_examine++;
	    	}
	    }  
	}); 
}


/**********************************************************************************
*函数名称: initLiClickEvent
*功能说明: 初始化li点击事件
*函数作者: joyon
*创建日期: 2014年6月26日 14:40:39
*修改历史: 
***********************************************************************************/
function initLiClickEvent(){
	$('.nav ul li').click(function(){
		$(this).addClass('selected').siblings('li').removeClass('selected');
		selectedLi=$(this);
		
		//如果是常用字 则的开常用字选项卡   并把值填进去   显示修改按扭
		if(this.id=="lichar"){
			selectedChar = $(this);
			
			var ruleValue =selectedLi.children("input")[0].value;
			$("#iptChar").val(ruleValue);
			//选中常用字选项卡
			$('#div_tabs').tabs('select', "常用字");
			
			//显示按扭
			$("#btnCharModify").css("display","inline-block");
		}else if(this.id=="lihousehold"){
			$('#div_tabs').tabs('select', "户籍");
		}else if(this.id=="liarea"){
			$('#div_tabs').tabs('select', "区域");
		}else if(this.id=="liyear"){
			$('#div_tabs').tabs('select', "年份");
		}else if(this.id=="lisquence"){
			$('#div_tabs').tabs('select', "流水号");
		}
		
		//alert(this.id+ruleValue);
	});
	
}

/**********************************************************************************
*函数名称: upLi
*功能说明: li上移
*函数作者: joyon
*创建日期: 2014年6月26日 14:40:39
*修改历史: 
***********************************************************************************/
function upLi()
{
	 var onthis = $(selectedLi);
	 var getup = $(selectedLi).prev();
	 
	 if(getup){
		 
		 $(getup).before(onthis);
		 
	 }else{
		 alert("己是最顶端");
	 }
	 
}

/**********************************************************************************
*函数名称: downLi
*功能说明: li下移
*函数作者: joyon
*创建日期: 2014年6月26日 14:40:39
*修改历史: 
***********************************************************************************/
function downLi()
{
	 var onthis = selectedLi;
	 var getdown = selectedLi.next();
	 
	 if(getdown){
		 
		 $(getdown).after(onthis);
	 }else{
		 alert("己是最底端");
	 }
	 
	
}

/**********************************************************************************
*函数名称: sortAppend
*功能说明: 排序规则添加数据
*函数作者: joyon
*参数说明: id:识别码  text:显示的text  rule:规则
*创建日期: 2014年6月26日 14:40:39
*修改历史: 
***********************************************************************************/
function sortAppend(id,text,rule){
	 var t = $('#ulTree');  
	 if(text){
		 t.append("<li id='li"+id+"'><label name='"+id+"'>"+text+"</label><input type='hidden' name='"+id+"' show='"+text+"'  value='"+rule+"'></input></li>");
	 }
}

/**********************************************************************************
*函数名称: getRule
*功能说明: 获取规则
*函数作者: joyon
*创建日期: 2014年6月26日 14:40:39
*修改历史: 
***********************************************************************************/
function getRule(){
	$.ajax({
		url:ctx+"/common/identifier!getRule.action",
		//url:ctx+"/houseownership/shiftreg!getRegInfo.action,
		data:{"code_id":code_id,"time":new Date()},
		dataType:"json",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			if(data){
				if(data.rule_exper){
					var rule = eval(data.rule_exper);		//把数据库中规则转换为json对象
					
					ruleDataback(rule);			//回填进选项卡规则
					
					for(var i=0;i<rule.length;i++){
						sortAppend(rule[i].id,rule[i].show,rule[i].text);
					}
					
					initLiClickEvent();
				}
				//alert($.toJSON(data));
			}
		}
	});
}

/**********************************************************************************
*函数名称: saveRule
*功能说明: 保存规则
*函数作者: joyon
*参数说明: id:识别码  text:显示的text
*创建日期: 2014年6月26日 14:40:39
*修改历史: 
***********************************************************************************/
function saveRule(){
	var inputs = $("#ulTree input");
	var arr = new Array();
	for(var i = 0;i<inputs.length;i++){
		arr[i] = {};
		//alert(inputs[i].value+inputs[i].name);
		arr[i].id=inputs[i].name;
		arr[i].text=inputs[i].value;
		arr[i].show=inputs[i].show;
	}
	
	$.ajax({
		url:ctx+"/common/identifier!saveRule.action",
		//url:ctx+"/houseownership/shiftreg!getRegInfo.action,
		data:{"code_id":code_id,"rule":$.toJSON(arr),"time":new Date()},
		dataType:"json",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			if(data){
				alert("保存成功！");
			}
		}
	});
	//alert($.toJSON(arr));
}

/**********************************************************************************
*函数名称: userYear
*功能说明: 保存规则
*函数作者: joyon
*创建日期: 2014年6月26日 14:40:39
*修改历史: 
***********************************************************************************/
function userYear(){
	
	//sortAppend();
}

/**********************************************************************************
*函数名称: appendYear
*功能说明: 保存规则
*函数作者: joyon
*参数说明:rule:yy  yyyy 用来区分是年份两位  还是四位
*创建日期: 2014年6月26日 14:40:39
*修改历史: 
***********************************************************************************/
function appendYear(rule){
	var tmpText = "年份2位";
	if(rule=='yyyy'){
		tmpText = "年份4位";
	}else if(rule=='yyyymmdd'){
		tmpText = "年月日yyyymmdd";
	}else if(rule=='yyyy-mm-dd'){
		tmpText = "年月日yyyy-mm-dd";
	}
	
	//如果已经有年份了 则更新   否则添加
	var oldYear = $("#liyear");
	if(oldYear.length>0){
		//alert(oldYear);
		var yearInput = oldYear.children("input")[0];	
		yearInput.value=rule;
		$(yearInput).attr("show",tmpText);
		
		var yearLabel =  oldYear.children("label")[0];
		yearLabel.innerHTML=tmpText;
	}else{
		
		sortAppend("year",tmpText,rule);
		initLiClickEvent();
	}
}

/**********************************************************************************
*函数名称: appendSquence
*功能说明: 添加流水号规则
*函数作者: joyon
*参数说明: length:流水号长度
*创建日期: 2014年6月26日 14:40:39
*修改历史: 
***********************************************************************************/
function appendSquence(length){
	var tmpText = "流水号("+length+")位";
	
	//如果已经有流水号了 则是修改流水号    否则新增
	var oldSquence = $("#lisquence");
	if(oldSquence.length>0){
//		oldSquence.remove();
		//修改隐藏input值  
		var squenceInput = oldSquence.children('input')[0];
		squenceInput.value=length;
		$(squenceInput).attr("show",tmpText);
		
		//修改显示label值 
		var squenceLabel = oldSquence.children('label')[0];
		squenceLabel.innerHTML=tmpText;
		
		
	}else{
		sortAppend("squence",tmpText,length);
		initLiClickEvent();
	}
}


/**********************************************************************************
*函数名称: userArea
*功能说明: 添加是否使用区号
*函数作者: joyon
*参数说明: cbx：复选框
*创建日期: 2014年6月26日 14:40:39
*修改历史: 
***********************************************************************************/
function userArea(cbx){
	
	if(cbx.checked){
		
		//如果有了区则不做任何操作
		var oldArea = $("#liarea");
		//alert(oldArea);
		if(oldArea)
			oldArea.remove();
		
		//没有则append  并加上事件
		
		sortAppend("area","区","area");
		initLiClickEvent();
	}else{
		var oldArea = $("#liarea");
		if(oldArea)
			oldArea.remove();
	}
}

/**********************************************************************************
*函数名称: userHousehold
*功能说明: 添加是否使用户籍
*函数作者: joyon
*参数说明: cbx：复选框
*创建日期: 2014年6月26日 14:40:39
*修改历史: 
***********************************************************************************/
function userHousehold(cbx){
	
	if(cbx.checked){
		
		//如果有了区则不做任何操作
		var oldHousehold = $("#lihousehold");
		//alert(oldArea);
		if(oldHousehold)
			oldHousehold.remove();
		
		//没有则append  并加上事件
		
		sortAppend("household","户籍","household");
		initLiClickEvent();
	}else{
		var oldHousehold = $("#lihousehold");
		if(oldHousehold)
			oldHousehold.remove();
	}
}

/**********************************************************************************
*函数名称: emptyUlTree
*功能说明: 清空规则树
*函数作者: joyon
*创建日期: 2014年6月30日 09:32:54
*修改历史: 
***********************************************************************************/
function  emptyUlTree(){
	$("#ulTree").empty();
}

/**********************************************************************************
*函数名称: ruleDataback
*功能说明: 规则数据回填  
*函数作者: joyon
*创建日期: 2014年6月30日 09:58:37
*修改历史: 
***********************************************************************************/
function ruleDataback(data){
	//alert($.toJSON(data));
	//code_id
	for(var i=0;i<data.length;i++){
		if(data[i].id=="household"){
			$("#iptHousehold").attr("checked","checked");
		}else if(data[i].id=="area"){
			$("#iptArea").attr("checked","checked");
		}else if(data[i].id=="year"){
			if(data[i].text=="yy"){
				$("#ra_y2").attr("checked","checked");
			}else if(data[i].text=="yyyy"){
				$("#ra_y4").attr("checked","checked");
			}else if(data[i].text=='yyyymmdd'){
				$("#ra_ymd").attr("checked","checked");
			}else if(data[i].text=='yyyy-mm-dd'){
				$("#ra_ymd1").attr("checked","checked");
			}
		}else if(data[i].id=="squence"){
			$("#input_squence").numberspinner("setValue",data[i].text);
		}else if(data[i].id=="char"){
			
		}
	}
}

function resetRules(){
	$("#iptHousehold").removeAttr("checked","checked");
	
	$("#iptArea").removeAttr("checked","checked");
	
	$("#input_squence").numberspinner("clear");
	
	$("#iptChar").val("");
	
	$("input[name='ra_year']").removeAttr("checked","checked");
}


