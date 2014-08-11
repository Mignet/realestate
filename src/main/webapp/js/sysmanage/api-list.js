/*********************************************************************************
*文  件  名  称: api-list.js
*功  能  描  述: API 列表
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Mignet
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/

/**********************************************************************************
*函数名称: JQuery DOM Ready(Shortcut)
*功能说明: 页面初始化
*函数作者: Mignet
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
		var startFlag = false;
		$(function(){
			var lastIndex;
			$('#tt').datagrid({
				title:"服务API列表",
				toolbar:[{
					text:'保存',
					iconCls:'icon-save',
					handler:function(){
						$('#tt').datagrid('endEdit', lastIndex);
						startFlag = false;
						var rows = $('#tt').datagrid('getChanges');
						 $.ajax({
						   		dataType:'json',
						   		url:ctx+"/sysmanage/monitor!saveAPIs.action?time="+getDateStr(),
						   		type : 'post',
						   		//表单的序列化操作
						   		data:{"datas":$.toJSON(rows)},
						   		success:function(data){
								 	if(data){
								 		top.$.messager.alert('保存成功提示',"保存成功",'info',function(){
								 			$('#tt').datagrid('acceptChanges');
										});	
								 	}else {
										top.$.messager.alert('保存失败提示',"保存失败",'error');
									}
						   		},error:function(data){
						   			top.$.messager.alert('保存失败提示',"保存失败",'error');
						   		}
						   	});  
					}
				},'-',{
					text:'撤消',
					iconCls:'icon-undo',
					handler:function(){
						$('#tt').datagrid('rejectChanges');
					}
				}],
				rownumbers:true,
				width:screen.availWidth/2,
				singleSelect:true,
				autoRowHeight:false,
				pagination:true,
				pageSize:10,
				striped:true,
				columns:[[
				          	//{field:'ck',checkbox:true},
							{field:'sid', title:'服务ID', width:160},
							{field:'sname', title:'服务名称',width:160},
							{field:'regflag', title:'注册标志', width:60,formatter:regflagFormatter,editor:{
								type:'combobox',
								options:{
									valueField:'regflag',
									textField:'name',
									data:regflags,
									required:true
								}
							}},
							{field:'info',title:'信息',width:200,editor:'text'},
							{field:'remark',title:'备注',width:200,editor:'text'}
				]],
				iconCls:'icon-edit',
				idField:'sid',
				url:ctx+'/sysmanage/monitor!getAPIList.action',
				onBeforeLoad:function(){
					$('#tt').datagrid('unselectAll');
					$('#tt').datagrid('rejectChanges');
				},
				onClickRow:function(rowIndex){
					//如果最后编辑的行不是当前行，结束它，开始当前行的编辑
					//如果是当前行，结束编辑
					if (lastIndex != rowIndex){
						$('#tt').datagrid('endEdit', lastIndex);
						$('#tt').datagrid('beginEdit', rowIndex);
						//如果标志未开始，开始它
						if(!startFlag){
							startFlag = true;
						}
					}else{
						if(startFlag){
							$('#tt').datagrid('endEdit', rowIndex);
							var sid = $('#tt').datagrid('getRows')[rowIndex].sid;
							//显示API
							$('#ts').datagrid('load',{'sid':sid});
						}else{
							$('#tt').datagrid('beginEdit', rowIndex);
						}
						startFlag = !startFlag;
					}
					lastIndex = rowIndex;
				}
			});
			$('#tt').datagrid('hideColumn', 'sid');
			var lastIndex2;
			$('#ts').datagrid({
				title:"方法列表",
				toolbar:[{
					text:'保存',
					iconCls:'icon-save',
					handler:function(){
						$('#ts').datagrid('endEdit', lastIndex2);
						startFlag = false;
						var rows = $('#ts').datagrid('getChanges');
						$.ajax({
							dataType:'json',
							url:ctx+"/sysmanage/monitor!saveMethods.action?time="+getDateStr(),
							type : 'post',
							//表单的序列化操作
							data:{"datas":$.toJSON(rows)},
							success:function(data){
								if(data){
									top.$.messager.alert('保存成功提示',"保存成功",'info',function(){
										$('#ts').datagrid('acceptChanges');
									});	
								}else {
									top.$.messager.alert('保存失败提示',"保存失败",'error');
								}
							},error:function(data){
								top.$.messager.alert('保存失败提示',"保存失败",'error');
							}
						});  
					}
				},'-',{
					text:'撤消',
					iconCls:'icon-undo',
					handler:function(){
						$('#ts').datagrid('rejectChanges');
					}
				}],
				rownumbers:true,
				singleSelect:true,
				width:screen.availWidth/2,
				autoRowHeight:false,
				pagination:true,
				pageSize:10,
				striped:true,
				columns:[[
				          //{field:'ck',checkbox:true},
				          {field:'mid', title:'方法ID', width:140},
				          {field:'sid', title:'服务ID', width:140},
				          {field:'mname', title:'方法名称',width:160},
				          {field:'parameters',title:'参数',width:60,editor:'text'},
				          {field:'returntype',title:'返回值',width:60,editor:'text'},
				          {field:'inlineflag', title:'对内标志', width:60,formatter:inlineflagFormatter,editor:{
								type:'combobox',
								options:{
									valueField:'inlineflag',
									textField:'name',
									data:inlineflags,
									required:true
								}
							}},
				          {field:'info',title:'信息',width:200,editor:'text'},
				          {field:'remark',title:'备注',width:100,editor:'text'}
				          ]],
				          iconCls:'icon-edit',
				          idField:'mid',
				          url:ctx+'/sysmanage/monitor!getMethodsBySID.action',
				          onBeforeLoad:function(){
				        	  $('#ts').datagrid('unselectAll');
				        	  $('#ts').datagrid('rejectChanges');
				          },
				          onClickRow:function(rowIndex){
				        	  //如果最后编辑的行不是当前行，结束它，开始当前行的编辑
				        	  //如果是当前行，结束编辑
				        	  if (lastIndex2 != rowIndex){
				        		  $('#ts').datagrid('endEdit', lastIndex2);
				        		  $('#ts').datagrid('beginEdit', rowIndex);
				        		  //如果标志未开始，开始它
				        		  if(!startFlag){
				        			  startFlag = true;
				        		  }
				        	  }else{
				        		  if(startFlag){
				        			  $('#ts').datagrid('endEdit', rowIndex);
				        			  //显示API
				        		  }else{
				        			  $('#ts').datagrid('beginEdit', rowIndex);
				        		  }
				        		  startFlag = !startFlag;
				        	  }
				        	  lastIndex2 = rowIndex;
				          }
			});
			$('#ts').datagrid('hideColumn', 'mid');
			$('#ts').datagrid('hideColumn', 'sid');

		});//初始化结束
//////////////////////////////////////////////////////////////////
var inlineflags = [
	{inlineflag:'00',name:'对内'},
	{inlineflag:'01',name:'对外'}
];
function inlineflagFormatter(value) {
	for ( var i = 0; i < inlineflags.length; i++) {
		if (inlineflags[i].inlineflag == value)
			return inlineflags[i].name;
	}
	return value;
};
var regflags = [
                   {regflag:'00',name:'未注册'},
                   {regflag:'01',name:'已注册'},
                   {regflag:'99',name:'注销'}
                   ];
function regflagFormatter(value) {
	for ( var i = 0; i < regflags.length; i++) {
		if (regflags[i].regflag == value)
			return regflags[i].name;
	}
	return value;
}
