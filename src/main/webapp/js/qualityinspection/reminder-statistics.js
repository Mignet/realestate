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
var datagrid_btn_name;							//按扭名  用来区分点击的是详情还是督办
var tmp_datagrid_data;							//临时datagriddata  用来解决当切换div时css和数据不渲染问题

/**********************************************************************************
*函数名称: 
*功能说明: 页面初始化
*函数作者: joyon
*创建日期: 2014年4月15日 11:46:54
*修改历史: 
***********************************************************************************/
window.onload = function(){
	$("#person_id").combouser();//combodeptuser();
	$("#dept").combodept({
		onSelect:function(data){
			//alert($.toJSON(data));
			//$("#PERSON_NAME").combodeptuser("setDeptId",data.id);
		}
	});
	//初始化表格
	initDatagrid();
	
	initReport();

}
/**********************************************************************************
*函数名称: initReport(){
*功能说明: 初始化统计图
*参数说明: 
*返 回 值: 直接给目标赋值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function initReport(){
	$("#div_report").empty();
	//初始化统计图
	 var url=ctx+"/common/print!printUrage.action?time="+new Date();
	  //alert(url);
	 // document.frames["printed"].location=url;
	 //var str='<object id="pdf1"  classid="clsid:CA8A9780-280D-11CF-A24D-444553540000"    width="100%"    height="560"  border="0"> <param   name="_Version"   value="65539"> <param   name="_ExtentX"   value="20108"> <param   name="_ExtentY"   value="10866"> <param   name="_StockProps" value="0"> <param    name="SRC"  value="'+url+'"      ></object> ';
	// var str = '<iframe  id="printed"  name="printed"  width="100%" height="580" src="'+url+'"></iframe>';
	 var str = '<image src="'+url+'" width="100%" height="600" />';
	 document.getElementById('div_report').innerHTML=str;
}

/**********************************************************************************
*函数名称: initDatagrid(){
*功能说明: 初始化表格
*参数说明: 
*返 回 值: 直接给目标赋值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function initDatagrid(){
	sampleDataGrid = $('#table_business').datagrid({
		//分页功能
		loadFilter:pagerFilter,//该方法在enum-data.js中
		//fit:true,
		height:320,
		//表格数据来源
		url:ctx+'/qualityinspection/qualityinspection!getUrgeStatistics.action',
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
		singleSelect:true,
		//是否在点选表中一行时同时选中复选框
		//checkOnSelect:true,
		//是否在选中复选框时同时点选表中一行
		//selectOnCheck:true,
		//列属性设置
		columns:[[
			//每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
			{field:'BUS_TYPE_NAME', title:'业务类型', align :'left',width:120},
			{field:'BUS_TYPE_NAME1', title:'所属区', align :'left',width:120},
			{field:'BUS_TYPE_NAME2', title:'科室', align :'left',width:120},
			{field:'ACC_COUNT', title:'受理数量', align :'left',width:120},
			{field:'APP_COUNT', title:'核准数量', align :'left',width:120},
			{field:'ARCH_COUNT', title:'归档数量', align :'left',width:120},
			{field:'RE_COUNT', title:'退文量', align :'left',width:120},
			{field:'TO_COUNT', title:'超时量', align :'left',width:120},
			//{field:'ck',checkbox:true},
//			{field:'bus_id',hidden:true},
//			{field:'reg_code', title:'业务编号',align :'center', width:60, sortable:true},
//			{field:'procName', title:'业务描述', align :'left',width:120},
//			{field:'activName',	title:'当前环节',align :'center', width:30},
//			{field:'busprop',	title:'业务性质',align :'center', width:20},
//			{field:'duration',	title:'总期限',align :'center', width:30,formatter:function(value){
//				return value/60/24;
//			}},
//			{field:'node_duration',	title:'环节时限',align :'center', width:30},
//			{field:'node_remain',	title:'环节剩余',align :'center', width:30},
//			{field:'node_start_date',	title:'接收时间',align :'center', width:30},
//			{field:'procstate',	title:'流程状态',align :'center', width:30},
//			{field:'user_name',	title:'承办人',align :'center', width:30},
//			{field:'button',title:'操作',align :'center',formatter:function(value,rec){return '<span><input  type="button" name="btn_detail" value="详情" onclick="btn_click(this);"/><input name="btn_reminder"  type="button" value="督办" onclick="btn_click(this);"/></span>';}}
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
				sampleDataGrid.datagrid('selectRow',rowIndex);
				//btn_click(this);
				clickCell();
			}
		},
		onLoadSuccess : function(data) {
			tmp_datagrid_data = data;
			//alert(JSON.stringify(data));
			//加载完毕禁用“编辑”、“删除”按钮
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
		}
	});
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
	//alert();
	$.ajax({
		url:ctx+"/qualityinspection/qualityinspection!getUrgeStatistics.action",
		type:"post",
		dataType:"json",
		data:{"search_str":$.toJSON(form_data)},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
//			alert($.toJSON(data));
			//top.$.messager.alert("提示",$.toJSON(data));
			
			
			if(data){
				tmp_datagrid_data = data;
				sampleDataGrid.datagrid("loadData",data);
				sampleDataGrid.datagrid("loaded");
			}
			
			initReport();
//			if(data.result=="success"){
//				//location.href="./qualityinspection.action";
//			}else{
//				top.$.messager.alert("提示","后台处理出错，请联系管理员！");
//			}
			
		}
	});
	
	sampleDataGrid.datagrid("loaded");
}
/**********************************************************************************
*函数名称: btn_click(){
*功能说明: 用来给按扭名赋值
*参数说明: 
*返 回 值: 直接给目标赋值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function btn_click(btn){
	datagrid_btn_name = btn.name;
	//alert(btn.name);
}
/**********************************************************************************
*函数名称: reminderSurebtnClick
*功能说明: 打开督办界面 确定按扭 点击事件
*参数说明: 
*返 回 值: 直接给目标赋值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function reminderSurebtnClick(){
	var selectedrow = sampleDataGrid.datagrid('getSelected');
	var bus_id = selectedrow.bus_id;
	var user_id = selectedrow.user_id;
	var message_type = $('input:radio[name="type"]:checked').val();
	var is_re = $('input:radio[name="is_re"]:checked').val();
	var message = $("#txt_message").text(); 
	message = "登记编号："+selectedrow.reg_code+" "+message;
	$.ajax({
		url:ctx+"/qualityinspection/qualityinspection!reminde.action",
		type:"post",
		dataType:"json",
		data:{"bus_id":bus_id,"user_id":user_id,"message_type":message_type,"message":message,"is_re":is_re},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			//alert($.toJSON(data));
			if(data.result){
				$('#div_dialog').dialog('close');  
				top.$.messager.alert("提示","督办成功！");
				
			}
		}
	});
	//alert(message_type+is_re+message);
}

function radioClick(radio){
	var show_type = radio.value;
//	alert(show_type);
	//统计表选中事件
	if(show_type == 'table'){
		//alert(show_type);
		$("#div_table").css("display","block");
		$("#div_report").css("display","none");
		sampleDataGrid.datagrid("loadData",tmp_datagrid_data);
	}else{
		//alert(show_type+"ddd");
		$("#div_table").css("display","none");
		$("#div_report").css("display","block");
	}

	//alert(radio.value);
}
/**********************************************************************************
*函数名称: reset
*功能说明: form数据重置
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function reset(){
	//$('#tab_reg_info').form('reset');
	$('#tab_reg_info')[0].reset()
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
