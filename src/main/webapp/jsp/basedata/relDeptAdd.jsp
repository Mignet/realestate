<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>新增建筑</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
        
        <jsp:include page="/plui2/head.jsp" />
        
<script>
/**  */
function init(user_id){
	$('#form').tableform({
		errorCode: 700,
		//表单元素表格的列数（通常设为两列）
		colnum: 1,
		//表单控件标题列宽
		titleWidth: 120,
		//表单控件输入框列宽
		cellWidth: 400,
		//表单控件input、select、combo等输入框宽
		inputWidth:	300,
		//textarea控件输入框宽
		textareaWidth: 300,
		//textarea控件输入框高
		textareaHeight: 90,
		//是否设置fieldset标签
		fieldset:false	,
		//表单元素参数数组。数组中每个对象构成一个表单元素
		inputs: [{
			tag: 'select',
			title: '关联部门',
			name: 'depts',
			id: 'depts',
			type: 'combodept',
			options: {
				required: true,
				missingMessage: '必填项',
				multiple: true,
				valueMergeField : 'deptIds',
				textMergeField : 'deptNames'
			}
		},{
			//表单控件标签名，可以是input、select、textarea等。
			tag: 'select',
			//控件输入框左侧标题
			title: '关联类型',
			//控件字段名
			name: 'type',
			//控件id，可选
			id: 'type',
			//控件类型（特用于plui表单控件combo系列。当没用到combo组件时不能设置此值）
			type: 'combodict',
			//combo控件生成参数。当没用到combo组件时不能设置此值
			options: {
				classCode: 'platt_dept_type',
				required: true,
				missingMessage: '此为必填项1'
			}
		},{
			tag: 'input',
			title: '关联职务',
			name: 'duty',
			id: 'duty'
		},{
			tag: 'select',
			title: '是否领导',
			name: 'is_leader',
			id: 'is_leader',
			type: 'combodict',
			options: {
				classCode: 'yes_or_no'
			}
		},{
			tag: 'select',
			title: '显示在通讯录',
			name: 'is_addresslist',
			id: 'is_addresslist',
			type: 'combodict',
			options: {
				classCode: 'yes_or_no'
			}
		},{
			tag: 'input',
			title: '排序',
			name: 'turn',
			id: 'turn',
			type: 'numberbox'
		},{
			tag: 'textarea',
			title: '关联备注',
			name: 'remark',
			id: 'remark'
		}],
		url: '../deptUserRelDelegate/saveDeptUserRelFromUser.run?user_id='+user_id,
		dataType: 'json',
		onSubmit: function(){
			var isValid = $(this).form('validate');
			
			return isValid;	// 返回false将停止form提交 
		},
		success:function(data){
			if (data.success) {
				top.$.messager.alert('新增部门关联提示','新增部门关联成功！','info',function(){
					closeInTopWindow('palt_relDeptAdd');
				});
			} else {
				top.$.messager.alert('新增部门关联错误',data.errorMessage,'error');
			}		
		}
	});
	/** 初始化表单
	var initdata = {'is_addresslist':'1','is_leader':'0'};
	$('#form').form("load",initdata);
	*/
};
</script>
    </head>
    <body>
    	<div class="plui-panel" data-options="fit:true,border:false">
	    	<form id="form" method="post"> 
	        </form>
	        <div style="text-align:center;">
               <a id="saveBtn" onclick="$('#form').submit();" class="plui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
               <a id="resetBtn" onclick="$('#form').form('clear');" class="plui-linkbutton" data-options="iconCls:'icon-reload'">重置</a>
               <a id="closeBtn" onclick="closeInTopWindow('palt_relDeptAdd');" class="plui-linkbutton" data-options="iconCls:'icon-undo'">关闭</a>
	        </div>
        </div>
    </body>
</html>
