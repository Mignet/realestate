/*********************************************************************************
*文  件  名  称: cfig-quality-target.js
*功  能  描  述: 质量跟踪指标信息配置js选择样本js
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: joyon
*创  建  日  期： 2014年4月15日 
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
var cfig_datagrid;							//主datagrid
var lastEditIndex=-1;						//可编辑的行
/**********************************************************************************
*函数名称: 
*功能说明: 页面初始化
*函数作者: joyon
*创建日期: 2014年4月15日 11:46:54
*修改历史: 
***********************************************************************************/
window.onload = function(){
	initDatagrid();
}

function initDatagrid(){
	cfig_datagrid = $("#datagrid-div").datagrid({
		title : '质量跟踪指标信息',
		fit : true,
		border : false,
		singleSelect : true,
		fitColumns : true,
		//是否在最左增加一列显示行号的列
		rownumbers:true,
		url:ctx+'/qualityinspection/qualityinspection!getChkQualitytargetinfo.action',
		pagination : true,
		columns : [[
			{ title : '指标项', field : 'qua_index', width : 100,editor:'text' },
			{  field : 'qua_tar_id', hidden:true}
		]],
		toolbar : [{
			id : 'add',
			text : '新增',
			iconCls : 'icon-add',
			handler : add
		},'-',{
			id : 'delete',
			text : '删除',
			iconCls : 'icon-remove',
			disabled : true,
			handler : function() {
				//删除字典类型
				var selectedRow = cfig_datagrid.datagrid('getSelected');
				top.$.messager.confirm('确认', '分类和数据项将一并删除<br/>确定要删除[' + selectedRow.qua_index + ']？', function(result){
					if (result) {
						$.ajax({
							url:ctx+'/qualityinspection/qualityinspection!deleteChkQualitytarget.action',
							dataType : 'json',
							data : {
								qua_tar_id : selectedRow.qua_tar_id
							},
							type : 'post',
							success : function(data) {
								if (data.result=="success") {
									top.$.messager.alert('提示', '数据删除成功！', 'info', function(){
										cfig_datagrid.datagrid('reload');
									});
								}
							}
						});
					}
				});
			}
		},'-',{
			id : 'save',
			text : '保存',
			iconCls : 'icon-save',
			handler : save
		},'->',{
			//组件类型 - ==> plui-searchbox
			xtype : 'searchbox',
			prompt : '输入质量跟指标',
			searcher : function(value, name) {
				var queryParams = {
					searchStr : value
				};
				cfig_datagrid.datagrid('load', queryParams);
			}
		}],
		onLoadSuccess : function(data) {
				//alert(JSON.stringify(data));
		},
		onClickRow : function(rowIndex, rowData) {
			//结束前面编辑行   
			cfig_datagrid.datagrid('endEdit', lastEditIndex);
			
			lastEditIndex = rowIndex;
			$('#update').linkbutton('enable');
			$('#delete').linkbutton('enable');
			//把当前行设为可编辑状态
			cfig_datagrid.datagrid('beginEdit', lastEditIndex);
		}
	});
}
/**********************************************************************************
*函数名称: add
*功能说明: 新增质量跟踪目标信息
*参数说明: 
*返 回 值: 直接给目标赋值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function add() {
	//var dict = cfig_datagrid.datagrid('getSelected');
	cfig_datagrid.datagrid('appendRow',{  
		index:''  
    });
	//将新增的行置为可编缉状态
	var rowlen  = cfig_datagrid.datagrid('getRows').length;
	cfig_datagrid.datagrid('endEdit', lastEditIndex);
	cfig_datagrid.datagrid('beginEdit', rowlen - 1);
	lastEditIndex = rowlen - 1;
};
/**********************************************************************************
*函数名称: save
*功能说明: 保存质量跟踪目标信息
*参数说明: 
*返 回 值: 直接给目标赋值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function save(){
	cfig_datagrid.datagrid('endEdit', lastEditIndex);
	cfig_datagrid.datagrid("unselectAll");
	lastEditIndex = -1;
	var inserted = cfig_datagrid.datagrid('getChanges', 'inserted');
	var deleted = cfig_datagrid.datagrid('getChanges', 'deleted');
	var updated = cfig_datagrid.datagrid('getChanges', 'updated');
	//请求后台保存数据
	$.ajax({
		url:ctx+'/qualityinspection/qualityinspection!applyChkQualitytargetEdit.action',
		dataType : 'json',
		type : 'post',
		data : {
			//将数据拼装成JSON字符串传递到后台
			datas :$.toJSON({
				inserted : inserted,
				deleted : deleted,
				updated : updated
			})
		},
		success : function(data) {
			if (data.success) {
				top.$.messager.alert('提示', '数据保存成功！', 'info', function(){
					//将表格置为不可编缉
					//撤销时启用“编辑按钮”
					$('#dictitem_edit').linkbutton('enable');
					//撤销时重新加载表格数据
					cfig_datagrid.datagrid('reload');
				});
			}else{
				top.$.messager.alert('提示', '数据保存失败！', 'info', function(){});
			}
		}
	});
}
