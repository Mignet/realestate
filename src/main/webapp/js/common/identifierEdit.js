
//接收从 identifier.jsp传过来的 选中节点的数据
var nodeData;

var bhObject;

//接收从后台返回的规则数据
var ruleData;

//当前编号 的编号  id  在这是惟一 的
var identifier_id;

//当前编号的详细信息  惟一 的
var identifier;

//当前操作的rule_id;
var rule_id;

//当前选择的li  主要用来上下移动
var selectedLi;

//当前选择的编号种类
var selectedIdentifier;

window.onload=function(){
		init();
}

//父窗口调用的本地初妈化函数  并传进来选择的树节点的名称
function init(){
	//nodeData=node;
	//alert("identifierEdit init method...");
	//$('#editmain').tabs();
	//var test=JSON.stringify(node);
	//alert(test);
	//$("#name").val(node.text);
	//$("#ruleDetail").form();
	$("#mainLayoutDiv").layout({
		fit:true
	});
	
	$("#syMain").layout({
		fit:true
	});	
	
	$("#syLayout1").layout({
		fit:true
	});	
	
	$('#identifier_tree').tree({  
		url:ctx+"/json/identifierMenuTree.json",
		//url:ctx+"/leftmenu/tree!getTreeJson.action",
		onClick: function(node){
			//alert(node.text);  // alert node text property when clicked
			selectedIdentifier=node;
			
			//获取索引相关信息
			getSyList();
			
			alert(JSON.stringify(node));
			//doDetail(node);
			
		}
	});
	
	//生成编号规则选项卡
	$('#tt').tabs({  
	    border:false,  
	    onSelect:function(title){  
	       	// alert(title+' is selected'); 
	       // $(this).css('display','block').siblings('div').css('display','none');
	    }  
	});  
	//getIdentifierIdByName(nodeData.text);
	
	//获取索引排序信息
	getRuleSortList();
	
	
	
	
	$("#btnSyAdd").click(function(){
		if(rule_id){
			rule_id=null;
			$("#rule").val("");
			return;
		}
		var tempRule=$.trim($("#rule").val());
		//规则为空时
		if(tempRule.length==0){
			alert("规则不能为空");
			return;
		}
		alert("createRule...");
		
		createSyByRule();
	});
	
	$("#btnSyDelete").click(function(){
		if(rule_id==null){
			alert("请选择索引");
			return;
		}
		deleteSyByRuleId();
	});
	
	$("#btnSumbit").click(function(){
		doRuleSave();
		
	});
	
	$("#cbYear").click(function(){
		checkYear();
	});
	
	$("#bh_longth").numberspinner({  
	    min: 4,  
	    max: 10,  
	    editable: false  
	});
	
	//修改编号长度时调用方法
	$("#bh_longth").change(function(){
		//numberspinner('setValue',4);
		var bh_longth=7;
		modifyBhLongth(bh_longth);
	});
	//获取是否使用年份
	//getCheckYear();
	//获取编号序列的长度
	//getBhLongth();
	
	
}

//获取左边顺序调整的List
function getRuleSortList(){
	$.ajax({
		type:"POST",
		url:"../common/identifier!getRuleSortList.action"+"?time="+new Date(),
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"name":nodeData.text},
		dataType:"json",
		
		success :function(data){
			data=data.rows;
			//alert("method:getSyList  data:"+JSON.stringify(data)); 
			//ata=JSON.parse(data);
			//alert(data.length+data[0].rule_name);
			
			//获取规则信息成功后  则加到ul里   并把rule_id  和rule_name添加进属性
			$("#ulSort").empty();
			for(var i=0;i<data.length;i++){
				
				$("#ulSort").append("<ol id='"+data[i].SORT+"' value='"+data[i].RULE_TYPE+"'>"+data[i].RULE_TYPE+"</ol>");
				
			}
			
		
			
			//左边顺序调整ul的点击事件
			$('.ulSy ul ol').click(function(){
				rule_id='';
				$(this).addClass('selected').siblings('ol').removeClass('selected');
				//alert($(this).text());
				//alert($(this).attr("id"));
				//rule_id=$(this).attr("id");
				//getRuleDetail(rule_id);
				selectedLi=$(this);
			});
		}
		
	});
}

//通过文号名称取得该文号下面的规则   先清空ul   再把数据并添加到ul下面
function getSyList(){
	
	//清空ul下的所有li
	
	//通过文号名称取得该文号下面的规则  并添加到ul下面
	$.ajax({
		type:"POST",
		url:"../common/identifier!getRuleByRuleNameAndIdentifierId.action"+"?time="+new Date(),
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"name":nodeData.text,"rule_name":"索引"},
		dataType:"json",
		
		success :function(data){
			data=data.rows;
			//alert("method:getSyList  data:"+JSON.stringify(data)); 
			//alert("method:getSyList  data:"+data);
			//data=JSON.parse(data);
			//alert(data.length+data[0].rule_name);
			
			//获取规则信息成功后  则加到ul里   并把rule_id  和rule_name添加进属性
			for(var i=0;i<data.length;i++){
				$("#ulSy").append("<li id='"+data[i].RULE_ID+"'>"+data[i].RULE+"</li>");
				
			}
			
			//li点击事件   点击后给当前点击li 添加selected class 并移动同级li的 selected class
			$('.nav ul li').click(function(){
				rule_id='';
				$(this).addClass('selected').siblings('li').removeClass('selected');
				//alert($(this).text());
				//alert($(this).attr("id"));
				rule_id=$(this).attr("id");
				getRuleDetail(rule_id);
			});
			
			
		}
		
	});
}

//获取编号详细信息  通过编号名称
function getIdentifierByName(){
	
	
	
	
	
}

//获取编号ID 通过编号名称
function getIdentifierIdByName(name){
	
	$.ajax({
		url:"idenitifierDelegate/getIdentifierIdByName.run",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		dataType:"json",
		data:{"name":name},
		success:function(data){
			identifier_id=data.id;
			//alert("getIdentifierIdByName:"+identifier_id);
		}
		
	});
}
//创建索引规则  首先通过编号名在编号表获取编号 ID  然后通过编号id在编号规则表里创建规则  然后在编号规则排序表中插入排序相关信息         
function createSyByRule(){
	
	
	
	var ruleType="索引";
	var name=nodeData.text;
	
	
	
			
			
			
			var rule=$("#rule").val();
			//alert(identifier+identifier.id);
			alert("createSyByRule，，，  name:"+name+" rule:"+rule);
			$.ajax({
				url:"../common/identifier!createRuleByIdentifierId.action"+"?time="+new Date(),
				type:"post",
				dataType:"json",
				contentType:"application/x-www-form-urlencoded; charset=GBK",
				data:{"rule":rule,"identifier_id":identifier_id,"rule_type":ruleType},
				success:function(data){
					
							alert("createSy success function:"+JSON.stringify(data));
							$(".nav ul li").remove();
							
							//获取索引信息
							getSyList();
							
							//获取索引排序信息
							getRuleSortList();
						
				}
				
			});
	
	

	
	
	
	
}


//删除规则通过  rule_id
function deleteSyByRuleId(){
	var ruleType="索引";
	alert("deleteSyByRuleId....");
	$.ajax({
		url:"../common/identifier!deleteRuleByRuleId.action"+"?time="+new Date(),
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"rule_id":rule_id,"identifier_id":identifier_id,"rule_type":ruleType},
		success:function(data){
			$(".nav ul li").remove();
			getSyList();
		}
		
	});
}

//新建常用字  
function createCyz(){
	
}

function deleteCyz(){
	
}

function doRuleSave(){
	
	//通过选择的编号名称 获取编号信息
	var name=nodeData.text;
	alert("getIdentifierByName  name:"+name);
	
			$.ajax({
				url:"../common/identifier!save.action"+"?time="+new Date(),
				type:"post",
				contentType:"application/x-www-form-urlencoded; charset=GBK",
				data:{"identifier_id":identifier_id},
				success:function(data){
					alert('Ok');
			
					
				}
			});
	
	
}


//获取规则详细信息  传入参数  rule_id
function getRuleDetail(rule_id){
	$.ajax({
		url:"../common/identifier!getRuleById.action"+"?time="+new Date(),
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		dataType:"json",
		data:{"rule_id":rule_id},
		success:function(data){
			$("#testLabel").text(JSON.stringify(data));
			
			//alert(JSON.stringify(data));
			$("#rule_name").val(data.RULE_NAME);
			$("#rule").val(data.RULE);
			//alert("getRuleDetail success");
		}
	});
	
}

//li上移
function UpLi()
{
	 var onthis = $(selectedLi);
	 var getup = $(selectedLi).prev();
	 
	 //如果选择的上一级有值  也就是非最顶值   则进行上移   否则不做任何操作
	 if(getup.attr("value")){
		 
		//把当前的id和上面的id互换
		 var tempId=selectedLi.attr("id");
		 selectedLi.attr("id",selectedLi.prev().attr("id"));
		 selectedLi.prev().attr("id",tempId);
		 alert(selectedLi.attr("value")+selectedLi.attr("id"));
		 
		 
		 $.ajax({
			 url:"../common/identifier!changeRuleSort.action"+"?time="+new Date(),
				type:"post",
				contentType:"application/x-www-form-urlencoded; charset=GBK",
				data:{"selectedType":selectedLi.attr("value"),"selectedSort":selectedLi.attr("id"),"otherType":selectedLi.prev().attr("value"),"otherSort":selectedLi.prev().attr("id"),"identifier_id":identifier_id},
				success:function(data){
					
				}
		 });
		 $(getup).before(onthis);
		 
	 }else{
		 alert("己是最顶端");
	 }
	 
}

//li下移
function DownLi()
{
	 var onthis = selectedLi;
	 var getdown = selectedLi.next();
	 
	 if(getdown.attr("value")){
		//把当前的id和下面的id互换
		 var tempId=selectedLi.attr("id");
		 selectedLi.attr("id",selectedLi.next().attr("id"));
		 selectedLi.next().attr("id",tempId);
		 alert(selectedLi.attr("value")+selectedLi.attr("id"));
		 
		 $.ajax({
			 url:"../common/identifier!changeRuleSort.action"+"?time="+new Date(),
				type:"post",
				contentType:"application/x-www-form-urlencoded; charset=GBK",
				data:{"selectedType":selectedLi.attr("value"),"selectedSort":selectedLi.attr("id"),"otherType":selectedLi.next().attr("value"),"otherSort":selectedLi.next().attr("id"),"identifier_id":identifier_id},
				success:function(data){
					
				}
		 });
		 
		 $(getdown).after(onthis);
	 }else{
		 alert("己是最底端");
	 }
	 
	
}
//li删除
function RemoveLi(){
 	$(selectedLi).remove();
}


//获取编号索引的长度  
function getBhLongth(){
	$.ajax({
		url:"idenitifierDelegate/getSeqNumberLength.run",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"identifier_id":identifier_id},
		dataType:"json",
		success:function(data){
			if(data){
				$("#bh_longth").numberspinner('setValue',data.rule);
				return;
			}
			$("#bh_longth").numberspinner('setValue',4);
			
		}
	});
	
}

//修改编号长度 
function modifyBhLongth(bh_longth){
	
	$.ajax({
		url:"idenitifierDelegate/getSeqNumberLength.run",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"identifier_id":identifier_id,"bh_longth":bh_longth},
		dataType:"json",
		success:function(data){
			
			
		}
	});
}

//获取是否使用年份
function getCheckYear(){
	$.ajax({
		url:"idenitifierDelegate/isUserYear.run",
		type:"post",
		dataType:"json ",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"identifier_id":identifier_id,"random":Math.random()},
		success:function(data){
			// alert(data.rule);
			if(data.rule){
				
				var isCheck=data.rule;
				if(isCheck=="isCheck"){
					$("#cbYear").attr("checked",true);
				}else{
					$("#cbYear").attr("checked",false);
				}
			}
			
		}
	});
}

//修改是否使用年份
function modifyYear(isCheck){
	//alert(identifier_id);
	$.ajax({
		url:"idenitifierDelegate/isUserYear.run",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"isCheck":isCheck,"identifier_id":identifier_id,"random":Math.random()},
		success:function(data){
			data.isCheck;
		}
	});
}
//年龄checkbox事件  isCheck是选择  unCheck是不选择
function checkYear(){
	var isCheck='unCheck';
	//alert("checkYear()");
	var isCheck=$("#cbYear").attr("checked");
	if(isCheck){
		//如果选择就让日期生效
		//alert("checked");
		isCheck='isCheck';
	}else{
		isCheck='unCheck';
	}
	
	modifyYear(isCheck);
}

