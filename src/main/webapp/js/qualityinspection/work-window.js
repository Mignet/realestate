	
//var row;
var wiId;
var proc;
var proc_id;
//登簿人
var dbr;

//登记编号
var regcode;

var lcslId;

var formid;

/*
var reg_unit_type ={};
reg_unit_type.PARCEL ='009003';						//登记单元编号 宗地
reg_unit_type.BUILDING ='009002';					//登记单元编号  楼宇
reg_unit_type.HOUSE ='009001';						//登记单元编号 房屋
*/
var bus_type_id;
//存储整个树数据
var iframeData;
//存储当前页面Iframe的id
var iframeid;

function init(user){
	proc=user;
	proc_id = proc.procId;
	//proc=proc.workItem;
	//JSON.stringify(proc);
	//alert(JSON.stringify(proc));
	
	$('#note').text(proc.activName);
	
	getBusTypeParentId();
	
	
	wiId=proc.wiId;//GetQueryString("wiId");	
	$('#bustree').tree({
		url:ctx+"/common/work-window!getOfficeByNodeid.action?procdefId="+proc.procdefId+"&nodeid="+proc.activdefId+"&time="+new Date(),
		//data:{"procdefId":proc.procdefId,"nodeid":proc.activdefId,"time":new Date()},
		onSelect : function(data) {
			
		},onBeforeSelect:function(node){
			var flag=false;
			var result = true;
			var data = $('#bustree').tree('getSelected');
			//alert(JSON.stringify(data));
			if(data){
			  if(data.attributes.url){	
				if(data.id){
					
					var Iframe;
					iframeid=data.id+"s";
					Iframe=document.getElementById(iframeid);
					//判断当前页面数据是否已经修改  如果修改提示 是否保存当前页面数据
					if(Iframe.contentWindow.pageDataIsChange)
					{
						var tmpResult=Iframe.contentWindow.pageDataIsChange();
						//页面修改   执行提示是否保存数据后离开
						if(tmpResult)
						{
							var r = window.confirm("页面数据己修改！是否保存后离开？");
//							alert(r)
							if(r ){
								//save();
								var result;
								var Iframe=document.getElementById(iframeid);
								if(Iframe.contentWindow.validate)
								{
									//如果验证失败   提示  并不跳转到下一页面
									result=Iframe.contentWindow.validate(1);
									if(!result.result)
									{
										top.$.messager.alert('提示',result.message,'info',function(){
										});
										return false;
									}
									else
									{
										Iframe.contentWindow.submit();
									}
									
								}
							}else{
								//return true;
							}
						}
						
					}
					
				}
			  }
			}
			openChildPage(node);			//打开下一个页面
			return result;
		},
		onLoadSuccess : function(node,data) {
			emptyIframe();
			//树加载成功后打开第一个树节点
			//alert(JSON.stringify(data))
			iframeData=data;
			for(var i=0;i<data.length;i++)
			{
				if(data[i].children)
				{
					for(var j=0;j<data[i].children.length;j++)
					{
						var url = data[i].children[j].attributes.url+"?wiId="+wiId+"&procId="+proc.procId+"&activName="+proc.activName+"&time="+new Date();
						$('#iframe').append("<div style='display:none;'  id='"+data[i].children[j].id+"' name='"+data[i].children[j].id+"' ><iframe  src='"+url+"' id='"+data[i].children[j].id+"s' name='"+data[i].children[j].id+"s' height='800' width='900'  allowtransparency='true' scrolling='yes' frameborder='0'></iframe></div>")
					}
				}
			}
			if(data[0].children)
			{
				var firstNode=data[0].children[0];
				var node = $('#bustree').tree('find', firstNode.id);
				$('#bustree').tree('select', node.target);
			}
			
		}
	});
	

	
	
}

/**********************************************************************************
*函数名称: openChildPage
*功能说明: 打开子节点办公页面
*参数说明: 子节点数据node
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function openChildPage(data){
	var Iframe;
	iframeid=data.id+"s";
	Iframe=document.getElementById(iframeid);
	if(data.iconCls!="")
	{
		for(var i=0;i<iframeData.length;i++)
		{
			if(iframeData[i].children)
			{
				for(var j=0;j<iframeData[i].children.length;j++)
				{
					$('#'+iframeData[i].children[j].id).css("display","none");
				}
			}
		}
		if(data.id){
			$('#'+data.id).css("display","block");
			iframeid=data.id+"s";
			$('#'+data.id+"s").attr("src",data.attributes.url);
		//window.open(data.attributes.url+"?wiId="+wiId+"&procId="+proc.procId+"&activName="+proc.activName+"&time="+new Date(),'in');
		} 
	}
}
		//提交
		function getparticiPants(btn,data){
			//在核准环节  判断是否已经登簿  如果未登簿   提示请先登簿后提交     --如果登簿后不允许回退
			if(proc.activName=="核准"){
				//如果当前按扭为  “回退”先判断是否已经登簿  如果已经登簿 不允许回退
				if(btn.innerHTML.indexOf("回退")!="-1"){
					if(enumdata.PRE_SALE_RECORD == bus_type_id || enumdata.TIP_RECORD == bus_type_id || enumdata.JUDICIAL_TRANSFER == bus_type_id){
						//预售备案业务  己修改备案状态 不能回退 
						if(enumdata.PRE_SALE_RECORD == bus_type_id){//if(enumdata.PRESALE_BACKUP == getBusTypeId()){
							if(isRecorded()){
								top.$.messager.alert('提示', "已经修改备案状态  不能进行此操作!", 'info',null);
								return;
							}
						}
					}else{
						if(isRegisterSave()){
							top.$.messager.alert('提示',"已经登簿，不能进行此操作！",'info',function(){
							});
							return ;
						}
					}
				}
				//点击提交按扭时   判断是否已经登簿  如果未登簿 不允许提交
				if(btn.innerHTML.indexOf("提交")!="-1"){
					if(enumdata.PRE_SALE_RECORD == bus_type_id || enumdata.TIP_RECORD == bus_type_id || enumdata.JUDICIAL_TRANSFER == bus_type_id){
						//预售备案业务  未修改备案状态 不能提交
						if(enumdata.PRE_SALE_RECORD == bus_type_id){//if(enumdata.PRESALE_BACKUP == getBusTypeId()){
							if(!isRecorded()){
								top.$.messager.alert('提示', "请先修改备案状态  后再进行提交!", 'info',null);
								return;
							}
						}
					}else{
						if(!isRegisterSave()){
							top.$.messager.alert('提示', "请先登簿后再进行提交!", 'info',
									function() {
									});
							return ;
						}
					}
				}
			}
			var id;
			var result;
			var Iframe;
			var procdefId=proc.procdefId;			
			var activdefId=data.To;			 
			var resultdata=data;
			var wiId=proc.wiId;
			Iframe=document.getElementById(iframeid);
			if(iframeid&&Iframe.contentWindow.validate)
			{
				//Iframe=document.getElementById(iframeid);
				result= Iframe.contentWindow.validate();
				if(result.result)
				{
					for(var i=0;i<iframeData.length;i++)
					{
						if(iframeData[i].children)
						{
							for(var j=0;j<iframeData[i].children.length;j++)
							{
								id=iframeData[i].children[j].id+"s";
								var allframe=document.getElementById(id);
								result=allframe.contentWindow.validate();
								if(!result.result)
								{
									break;
								}
							}
						}
						if(!result.result)
						{
							break;
						}
					}
					if(result.result)
					{
						
						//Iframe.contentWindow.submit();
					
					if(proc.wiName=="归档"){			
						 	var end={
						 		url:ctx+"/workflow/workflow!WorkItemFinishedEndWithFacade.action",
						 		type:'post',
						 		async:false,
						 		data:{"wiId":wiId,"time":new Date()},
						 		dataType:"json",
						 		success:function(data){
						 			//alert(data);
						 			if(data.sign=="success"){	
						 			top.$.messager.alert('提示', '归档成功！', 'info',
									function() {	
								 				
						 				});			
						 			}
						 			else if(data.sign=="failed"){
						 				top.$.messager.alert('提示', '归档失败！', 'info',
									function() {	
								 				
						 				});	
						 				return;
						 			}
						 		}
						 	};$.ajax(end);
						 	openerWindow.location.reload();
						 	closeInTopWindow('open_app','destroy');
						 }
						 else{
							summit(activdefId,procdefId,resultdata);
									}
					}
					else
					{
						//if(id!=iframeid)
						//{
							top.$.messager.alert('提示',result.page_name+"中"+result.message,'info',function(){
							});	
						//}
					}
				}
				else
				{
					top.$.messager.alert('提示',result.message,'info',function(){
					});	
				}
			}
			else
			{
				 $.messager.confirm('保存确认',"当前没有表单或表单没有验证，是否提交？",function(r){  
					    if (r){
					    	summit(activdefId,procdefId,resultdata);
					    	
					    }else{
					    	 
					    }
				 });

			}
		}
		//弹出particiPants.jsp页面
	function summit(activdefId,procdefId,result){
		
		
		var sub={
				url:ctx+"/workflow/workflow!getParticipantsOfWorkItem.action",
				type:'post',
				data:{"activdefId":activdefId,"procdefId":procdefId,"time":new Date()},
				success:function(data){		
					//alert(JSON.stringify(data));	
					if (data) {
						var obj={};
						obj=$.extend(obj,result);
						obj=$.extend(obj,proc);
						obj.data=data;
						//alert(JSON.stringify(obj));
						openInTopWindow({	
							title:"选择接收人",
							id:"particiPants",
							//窗口宽
							width: 300,
							//窗口高
							height: 300,
							modal: true,	
							src:ctx+"/jsp/common/partici-pants.jsp",
							destroy:true,
							onLoad:	function(){
								//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
								//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
								this.openerWinssssdow = window;
								//this.document.getElementById('name').value='名称';
								//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
								this.args = {
									particpants: obj
								};			
								this.init(obj);
							}
						});									
					}else {							 							
							 
					}			
				}										
			};
			$.ajax(sub);
	}


	//保存iframe页面下面的数据
	function save()
	{	var result;
		var Iframe=document.getElementById(iframeid);
		if(Iframe.contentWindow.validate)
		{
			result=Iframe.contentWindow.validate(1);
			if(!result.result)
			{
				top.$.messager.alert('提示',result.message,'info',function(){
				});
			}
			else
			{
				Iframe.contentWindow.submit();
			}
			
		}
		
	}



	//获取地址栏参数
	function GetQueryString(name)
	{
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r!=null) return unescape(r[2]); return null;
	}
	
	
	//登簿
	function registerSave () {		
		
		
		var proc_id=proc.procId;
		//alert(lcslId);
		var result="failed";
		
		$.ajax({
			url:ctx+"/common/register!registerSave.action",
			data:{"proc_id":proc_id,"time":new Date()},
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			dataType:"json",
			async:false,
			success:function(data){
				//alert(data.result);
				//result=data.result;
				if(data.result=="success"){
					$("#btn_reg_save").linkbutton('disable');
					top.$.messager.alert('提示',"登簿成功！",'info',null);
					//return data.result;
				}else if(data.result=="exists"){
					top.$.messager.alert('提示',"已经登簿，请勿重新操作",'info',null);
				}
				//return data.result;
			}
		});
		//return result;
	}
	
	//登记簿预览功能
	function registerPreview () {	
		
		
		var objWindow = {
				//窗口元素的id
				id: 'wind_djbyl',
				//窗口iframe的src
				src: ctx+'/jsp/common/register-preview.jsp',
				//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
				destroy: true,
				//窗口标题
				title: '登记簿预览',
				//窗口宽
				width: 850,
				//窗口高
				height: 680,
				modal: true,
				//窗口中iframe的window对象的onLoad回调函数设置
				onLoad:	function(){
					proc.regcode=regcode;
					//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
					//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
					//this.openerWindow = window;
					
					//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
					this.args = {
						///userDataGrid: userDataGrid
					};
					this.init(proc);
				}
			};
		var bus_type=proc.procdefName;
		//所有权
		if(enumdata.ALL == bus_type_id){
			objWindow.src = ctx+'/jsp/common/register-preview.jsp';
		}
		//使用权
		else if(enumdata.USE == bus_type_id){
			objWindow.src = ctx+'/jsp/common/registerbook/registeruse.jsp';
		}
		//抵押
		else if(enumdata.MORT == bus_type_id){
			objWindow.src = ctx+'/jsp/common/registerbook/registermort.jsp';
		}
		//查封登记
		else if(enumdata.ATTACH == bus_type_id){
			objWindow.src = ctx+'/jsp/common/registerbook/registerattach.jsp';
		}
		//异议登记
		else if(enumdata.DEMURRER == bus_type_id){
			objWindow.src = ctx+'/jsp/common/registerbook/registerdemurrer.jsp';
		}
		//房地产证注销登记
		else if(enumdata.REALESTATE_CAN == bus_type_id){
			$.ajax({
				url:ctx+'/common/register!getReg_relationship.action',
				dataType : 'json',
				type : 'post',
				data : {"proc_id":proc_id,"time":new Date()},
				async:false,
				success : function(data) {
					if(enumdata.house == data.reg_unit_type){
						objWindow.src = ctx+'/jsp/common/register-preview.jsp';
					}else if(enumdata.parcel == data.reg_unit_type){
						objWindow.src = ctx+'/jsp/common/registerbook/registeruse.jsp';
					}
				}
			});
			//objWindow.src = ctx+'/common/register-preview.action';
		}else if(enumdata.CORRECTION == bus_type_id){
			$.ajax({
				url:ctx+'/common/register!getReg_relationship.action',
				dataType : 'json',
				type : 'post',
				data : {"proc_id":proc_id,"time":new Date()},
				async:false,
				success : function(data) {
					
					if(enumdata.parcel == data.reg_unit_type){
						objWindow.src = ctx+'/jsp/common/registerbook/registeruse.jsp';
					}else if(enumdata.house == data.reg_unit_type || enumdata.build == data.reg_unit_type){
						objWindow.src = ctx+'/jsp/common/register-preview.jsp';
					}
				}
			});
			
		}
		else if(enumdata.REISSUE == bus_type_id){
			$.ajax({
				url:ctx+'/common/register!getReg_relationship.action',
				dataType : 'json',
				type : 'post',
				data : {"proc_id":proc_id,"time":new Date()},
				async:false,
				success : function(data) {
					
					if(enumdata.parcel == data.reg_unit_type){
						objWindow.src = ctx+'/jsp/common/registerbook/registeruse.jsp';
					}else if(enumdata.house == data.reg_unit_type || enumdata.build == data.reg_unit_type){
						objWindow.src = ctx+'/jsp/common/register-preview.jsp';
					}
				}
			});
			
		}
		openInTopWindow(objWindow);
	};
	
	
		
	function isWin(obj){ 
		return /Window|global/.test({}.toString.call(obj))||obj==obj.document&&obj.document!=obj; 
	} 
	
	//判断当前业务是否属于备案     内部提示性备注  司法裁定过户   如果它属于则不显示登记簿预览  和登簿
	function getBusTypeParentId(){
		
		$.ajax({
			url:ctx+"/common/certificate!getBusTypeParentId.action",
			data:{"proc_id":proc.procId,"time":new Date()},
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			dataType:"json",
			async:false,
			success:function(data){
				//alert(data.bus_type_id);
				bus_type_id = data.bus_type_id;
				
				if(enumdata.PRE_SALE_RECORD == bus_type_id || enumdata.TIP_RECORD == bus_type_id || enumdata.JUDICIAL_TRANSFER == bus_type_id){
					if(proc.wiName=="核准"){
						//预售备案业务  显示修改备案状态 按扭
						if(enumdata.PRE_SALE_RECORD == bus_type_id){//if(enumdata.PRESALE_BACKUP == getBusTypeId()){
							$("#btn_presale_status").css("visibility","visible");
							if(!isRecorded()){
								$("#btn_presale_status").linkbutton('enable');
							}
						}
					}
				}else{
					if(proc.wiName=="核准"){
						
						
						$("#btn_regPrev").css("visibility","visible");
						$("#btn_reg_save").css("visibility","visible");
						//如果已经登簿则禁用登簿按扭
						if(isRegisterSave()){
							$("#btn_reg_save").linkbutton('disable');
						}
						
					}
					if(enumdata.MORT_CAN_REG == getBusTypeId() || enumdata.MAX_MORT_CAN_REG == getBusTypeId()){
						if(proc.wiName=="受理"){
							
							$("#btn_regPrev").css("visibility","visible");
							$("#btn_reg_save").css("visibility","visible");
							//如果已经登簿则禁用登簿按扭
							if(isRegisterSave()){
								$("#btn_reg_save").linkbutton('disable');
							}
							
						}
					}
				}
			}
			
		});
		
	}
	
	/**********************************************************************************
	*函数名称: getBusTypeId
	*功能说明: 获取当前业务类型ID 
	*参数说明: 
	*返 回 值: busTypeId 业务类型ID 
	*函数作者: Joyon
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/	
function getBusTypeId(){
	var busTypeId;
	$.ajax({
		url:ctx+"/common/certificate!getBusTypeId.action",
		data:{"proc_id":proc.procId,"time":new Date()},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		dataType:"json",
		async:false,
		success:function(data){
			busTypeId = data.busTypeId;
		}
	});
	
	//alert(busTypeId);
	return busTypeId;
}
/**********************************************************************************
*函数名称: isRegisterSave
*功能说明: 判断是否登簿 
*参数说明: 
*返 回 值: true/false  已经登簿/未登簿
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/	
function isRegisterSave(){
	var result = false;
	$.ajax({
		url:ctx+"/common/register!isRegisterSave.action",
		data:{"proc_id":proc.procId,"time":new Date()},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		dataType:"json",
		async:false,
		success:function(data){
			if(data.result=="1"){
				result = true;
			}
		}
		
	});
	
	return result;
}
/**********************************************************************************
*函数名称: isRecorded
*功能说明: 判断是否已经备案 
*参数说明: 
*返 回 值: true/false  已经登簿/未登簿
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/	
function isRecorded(){
	var result = false;
	$.ajax({
		url:ctx+"/backup/presale!isRecorded.action",
		data:{"proc_id":proc.procId,"time":new Date()},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		dataType:"json",
		async:false,
		success:function(data){
			if(data.result=="1"){
				result = true;
			}
		}
		
	});
	
	return result;
}

/**********************************************************************************
*函数名称: modPresaleStatus
*功能说明: 修改预售备案状态
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/	
function modPresaleStatus(){
	$.ajax({
		url:ctx+"/backup/presale!setPreSaleState.action",
		data:{"proc_id":proc.procId,"time":new Date()},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		dataType:"json",
		async:false,
		success:function(data){
			$("#btn_presale_status").linkbutton('disable');
			top.$.messager.alert('提示',"修改成功",'info',null);
			//刷新当前选择的子节点页面
			var selectedNode = $('#bustree').tree('getSelected');
			if(selectedNode){
				openChildPage(selectedNode);
			}
		},error:function(data){
			top.$.messager.alert('提示',"修改失败，系统内部错误",'error',null);
		}
		
	});
}

/**********************************************************************************
*函数名称: emptyIframe
*功能说明: 把iframe置空
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/	
function emptyIframe(){
	$('#iframe').empty();
}

