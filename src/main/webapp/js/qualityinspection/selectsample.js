/*********************************************************************************
*文  件  名  称: selectsample.js
*功  能  描  述: 选择样本js
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: joyon
*创  建  日  期： 2014年4月15日 
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/

var sampleDataGrid;								//样本datagrid

/**********************************************************************************
*函数名称: 
*功能说明: 页面初始化
*函数作者: joyon
*创建日期: 2014年4月15日 11:46:54
*修改历史: 
***********************************************************************************/
window.onload = function(){
	//切换到springMVC框架中出错  数据出不来
//	$("#person_id").combouser();//combodeptuser();
//	$("#dept").combodept({
//		onSelect:function(data){
//			//alert($.toJSON(data));
//			//$("#PERSON_NAME").combodeptuser("setDeptId",data.id);
//		}
//	});
	
	sampleDataGrid = $('#table_business').datagrid({
			//分页功能
			loadFilter:pagerFilter,//该方法在enum-data.js中
			//fit:true,
			height:240,
			//表格数据来源
			//url:ctx+'/qualityinspection/qualityinspection!getCurMonthBusiness.action',
			//表格每列宽度自动适应表格总宽度
			fitColumns: true,
			//去掉边框
			border : true,
			//是否有翻页栏
			pagination:true,
			//每页行数
			pageSize:10,
			//是否在最左增加一列显示行号的列
			rownumbers:true,
			//主键值所在行。在使用复选框时必须设置此项。
			//idField: 'user_id',
			//表格的行是否交替显示不同背景色					
			striped:true,
			//只允许单选一行
			//singleSelect:true,
			//是否在点选表中一行时同时选中复选框
			//checkOnSelect:true,
			//是否在选中复选框时同时点选表中一行
			//selectOnCheck:true,
			//列属性设置
			columns:[[
				//每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
				
				{field:'ck',checkbox:true},
				{field:'bus_id',hidden:true},
				{field:'reg_code', title:'业务编号',align :'center', width:60, sortable:true},
				{field:'procName', title:'业务描述', align :'left',width:120},
				{field:'activeName',	title:'当前环节',align :'center', width:30},
				{field:'busprop',	title:'业务性质',align :'center', width:20},
				{field:'duration',	title:'总期限',align :'center', width:30,formatter:function(value){
					return value/60/24;
				}},
				{field:'nodelimit',	title:'环节时限',align :'center', width:30},
				{field:'REMAIN_DAYS',	title:'环节剩余',align :'center', width:30},
				{field:'CREATE_DATE',	title:'接收时间',align :'center', width:30},
				{field:'procstate',	title:'流程状态',align :'center', width:30},
				{field:'inspectioned',	title:'已抽检',align :'center', width:30}
			]],
			onClickRow : function() {
				//点击列时激活“编辑”、“删除”按钮
				//alert("onClickRow  event");
				//btn_test_flow()
				$('#user_edit').linkbutton('enable');
				$('#user_delete').linkbutton('enable');
			},
			onClickCell:function(rowIndex, field, value){
				//alert("onclickcell event"+rowIndex+field+value);
				if(field=="button"){
					$('#table_user').datagrid('selectRow',rowIndex);
					btn_bl(this);
				}
			},
			onLoadSuccess : function(data) {
				//alert(JSON.stringify(data));
				//加载完毕禁用“编辑”、“删除”按钮
				$('#user_edit').linkbutton('disable');
				$('#user_delete').linkbutton('disable');
			}
		});
}

/**********************************************************************************
*函数名称: saveSelectsamle
*功能说明: 保存选择的样本信息
*参数说明: 
*返 回 值: 直接给目标赋值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function saveSelectsamle(){
	var checkedSample = sampleDataGrid.datagrid("getChecked");
	if(checkedSample.length==0){
		location.href="./qualityinspection.jsp";
		return;
	}
	
	checkedSample = $.toJSON(checkedSample);
	$.ajax({
		url:ctx+"/qualityinspection/qualityinspection!saveChecksample.action",
		type:"post",
		dataType:"json",
		data:{"sample":checkedSample},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			if(data.result=="success"){
				location.href="./qualityinspection.action";
			}else{
				top.$.messager.alert("提示","后台处理出错，请联系管理员！");
			}
		}
	});
	//alert(JSON.stringify(checkedSample));
}
/**********************************************************************************
*函数名称: querySamle(){
*功能说明: 按条件筛选样本
*参数说明: 
*返 回 值: 直接给目标赋值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function querySamle(){
	sampleDataGrid.datagrid("loading");
//	var form_data = $("#m_search_form").serialize();
//	form_data = decodeURIComponent(form_data,true);  
	var form_data = $("#m_search_form").serializeObject();
	//top.$.messager.alert('提示',$.toJSON(form_data),'info',null);
	//top.$.messager.alert("提示",$.toJSON(form_data),null);
	//return ;
	$.ajax({
		url:ctx+"/qualityinspection/qualityinspection!querySamele.action",
		type:"post",
		dataType:"json",
		data:{"search_str":$.toJSON(form_data)},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			//alert($.toJSON(data));
			if(data){
				sampleDataGrid.datagrid("loadData",data);
				sampleDataGrid.datagrid("loaded");
			}
//			if(data.result=="success"){
//				//location.href="./qualityinspection.action";
//			}else{
//				top.$.messager.alert("提示","后台处理出错，请联系管理员！");
//			}
		}
	});
}

(function($){  
    $.fn.extend({  
        serializeObject:function(){  
            if(this.length>1){  
                return false;  
            }  
            var arr=this.serializeArray();  
            var obj=new Object;  
            $.each(arr,function(k,v){  
                obj[v.name]=v.value;  
            });  
            return obj;  
        }  
    });  
})(jQuery);  
