/*********************************************************************************
*文  件  名  称: check-manager.js
*功  能  描  述: 抽检管理
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
	//loadTargetInfo();
	
	$("#person_id").combouser();//combodeptuser();
	$("#dept").combodept({
		onSelect:function(data){
			//alert($.toJSON(data));
			//$("#PERSON_NAME").combodeptuser("setDeptId",data.id);
		}
	});
	
	sampleDataGrid = $('#table_business').datagrid({
		//分页功能
		loadFilter:pagerFilter,//该方法在enum-data.js中
		height:320,
		//表格数据来源
		url:ctx+'/qualityinspection/qualityinspection!getUncheckSample.action?search_str={"type":"check"}',
		//表格每列宽度自动适应表格总宽度
		fitColumns: true,
		//去掉边框
		border : true,
		//是否有翻页栏
		pagination:true,
		//每页行数
		//pageSize:10,
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
			
			{field:'BUS_ID',hidden:true},
			{field:'CHE_ID',hidden:true},
			{field:'ACTIVDEF_ID',hidden:true},
			{field:'URL_SPECIFY',hidden:true},
			{field:'PROCDEF_ID',hidden:true},
			{field:'PROC_ID',hidden:true},
			{field:'WI_ID',hidden:true},
			{field:'REG_CODE', title:'业务编号',align :'center', width:60},
			{field:'procName', title:'业务描述', align :'left',width:120,formatter:function(value){
				return "办文 抽检("+value+")";
			}},
			{field:'activeName',	title:'当前环节',align :'center', width:30},
			{field:'busprop',	title:'业务性质',align :'center', width:20},
			{field:'duration',	title:'总期限',align :'center', width:30,formatter:function(value){
				return value/60/24;
			}},
			{field:'nodelimit',	title:'环节时限',align :'center', width:30},
			{field:'REMAIN_DAYS',	title:'环节剩余',align :'center', width:30},
			{field:'CREATE_DATE',	title:'接收时间',align :'center', width:30},
			{field:'procstate',	title:'流程状态',align :'center', width:30},
			{field:'CHE_DATE',	title:'已抽检',align :'center', width:30,formatter:function(value){
				if($.trim(value).length==0){
					return "否";
				}else{
					return "是";
				}
			}},
			{field:'check',title:'操作',formatter:function(value,rec){return '<span><input  type="button" value="复检" onclick=""/></span>';}}
		]],
		onClickRow : function() {
			//点击列时激活“编辑”、“删除”按钮
			//alert("onClickRow  event");
			//btn_test_flow()
			$('#user_edit').linkbutton('enable');
			$('#user_delete').linkbutton('enable');
		},
		onClickCell:function(rowIndex, field, value){
			if(field=="check"){
				sampleDataGrid.datagrid('selectRow',rowIndex);
				checkSample(this);
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
*函数名称: loadTargetInfo
*功能说明: 加载月抽检目标信息
*参数说明: 
*返 回 值: 直接给目标赋值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function loadTargetInfo(){
	$.ajax({
		url:ctx+"/qualityinspection/qualityinspection!getTargetInfo.action",
		type:"post",
		dataType:"json",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			if(data){
				$("#month_target").val(data.month_target);
				$("#inspectioned").val(data.inspectioned);
			}
		}
	});
	
}
/**********************************************************************************
*函数名称: checkSample
*功能说明: 检查选中的业务
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function checkSample(){
	//alert("办理按扭事件");
	var selectedrow = sampleDataGrid.datagrid('getSelections');//捯解析jsonarray用
	selectedrow[0].type="checked";//标识复检
	//selectedrow[0].procId = selectedrow[0].PROC_ID;
	var selectedRowJson = $.toJSON(selectedrow);
	
	//alert(selectedRowJson);
	//return ;
	//alert(selectedrow.wiId);
	//var selectedrow = $('#table_user').datagrid('getSelections');
	//alert(JSON.stringify(selectedrow)+'-----');
	//打开窗口  obj对象
	var obj={	
			id:"open_app",
			src:ctx+"/jsp/qualityinspection/inspection-index.jsp",
			//窗口宽
			width: 1150,
			//窗口高
			title:'质量抽检页面',
			height: 598,
			modal: true,	
			destroy:true,
			onLoad:	function(){
				//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
				//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
				this.openerWindow = window;
				//this.document.getElementById('name').value='名称';
				//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
//				this.args = {
//					user: selectedrow
//					//userDataGrid: userDataGrid
//				};			
				this.init(selectedrow);
			}
		};	
//	selectedrow = {"activId":145813,"activName":"检查","activdefId":"manual3","activdefName":"检查","dayType":"workingDay","moduleId":1065,"moduleName":"质量督查","name":"检查","procId":1000018824,"procName":"办文抽检(最高额抵押权注销登记(安业馨园--20140314000000000018等))","procdefId":1290,"procdefName":"质量督查","startDate":"2014-04-23 17:37:25","state":"1","type":"push","urlSpecify":"/qualityinspection/inspection-index.action","urlType":"default","wiId":83139};
//	openInTopWindow(obj);
	//启动流程成功后再打开窗口
	$.ajax({
		url:ctx+"/qualityinspection/qualityinspection!startInspection.action",
		type:"post",
		dataType:"json",
		data:{"selectedrow":selectedRowJson},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		async:false,
		success:function(data){
			
			//启动流程成功后  把当前启动成功后的检查流程 传到子页面
			selectedrow = data.workItem;
			//alert($.toJSON(selectedrow));
			//打开检查流程窗口
			openInTopWindow(obj);	
			
			sampleDataGrid.datagrid("reload");
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
	form_data.type="check";
	//top.$.messager.alert('提示',$.toJSON(form_data),'info',null);
	//top.$.messager.alert("提示",$.toJSON(form_data),null);
	//return ;
	$.ajax({
		url:ctx+"/qualityinspection/qualityinspection!getUncheckSample.action",
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
