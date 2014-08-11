<%@ page language="java" import="java.util.*" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>默认异常</title>
        <meta http-equiv="content-type" content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="">
        
       <%@ include file="/base/prefix.jsp"%>
                
        <script>
			$(function(){
				$('#form').tableform({
					url: '../../errorDelegate/throwSpecifiedException.run',
					dataType:'json',
					errorCode: 700,
					success: function(data){},
					//表单元素表格的列数（通常设为两列）
					colnum: 1,
					//表单控件标题列宽
					titleWidth: 120,
					//表单控件输入框列宽
					cellWidth: 300,
					//表单控件input、select、combo等输入框宽
					inputWidth:	200,
					//textarea控件输入框宽
					textareaWidth: 600,
					//textarea控件输入框高
					textareaHeight: 90,
					//是否设置fieldset标签
					fieldset:false	,
					//表单元素参数数组。数组中每个对象构成一个表单元素
					inputs: [{
						tag: 'input',
						title: '部门名称',
						name: 'dept_name',
						id: 'dept_name',
						type: 'validatebox',
						options: {
							required: true,
							missingMessage: '此为必填项0'
						}
					},{
						//表单控件标签名，可以是input、select、textarea等。
						tag: 'select',
						//控件输入框左侧标题
						title: '选择抛出异常',
						//控件字段名
						name: 'index',
						//控件id，可选
						id: 'index',
						//控件类型（特用于plui表单控件combo系列。当没用到combo组件时不能设置此值）
						type: 'combobox',
						//combo控件生成参数。当没用到combo组件时不能设置此值
						options: {
							width:202,
							height: 24,
							required: true,
							missingMessage: '此为必填项1',
							valueField: 'id',
							textField: 'name',
							data: [{
								id: 0,
								name: '异常0'
							},{
								id: 1,
								name: '异常1'
							},{
								id: 2,
								name: '异常2'
							},{
								id: 3,
								name: '异常3'
							},{
								id: -1,
								name: '默认异常'
							}]
						}
					}]
				});
			});
        </script>
    </head>
    <body>    	
		<form id="form"></form>
    </body>
</html>

