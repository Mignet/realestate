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

/**********************************************************************************
*函数名称: 
*功能说明: 页面初始化
*函数作者: joyon
*创建日期: 2014年4月15日 11:46:54
*修改历史: 
***********************************************************************************/
window.onload = function(){
	$("#person_id").combouser();  
//	$("#dept").combodept({
//		onSelect:function(data){
//			//alert($.toJSON(data));
//			//$("#PERSON_NAME").combodeptuser("setDeptId",data.id);
//		}
//	});
	
	//对话框
	 $('#div_dialog').dialog({  
		 title: '督办内容',  
	     width: 333,  
	     height: 300,  
	     cache: false,  
	     modal: true,  
	     closed:true,
         buttons:[{  
             text:'确定',  
             iconCls:'icon-ok',  
             handler:reminderSurebtnClick
         },{  
             text:'取消',  
             handler:function(){  
                 $('#div_dialog').dialog('close');  
             }  
         }]  
     }); 
  
	
	sampleDataGrid = $('#table_business').datagrid({
			//loadFilter:pagerFilter,
			//fit:true,
			height:320,
			//表格数据来源
			url:ctx+'/qualityinspection/qualityinspection!getRemindersBusiness.action',
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
				
				//{field:'ck',checkbox:true},
				{field:'BUS_ID',hidden:true},
				{field:'REG_CODE', title:'业务编号',align :'center', width:60, sortable:true},
				{field:'PROC_NAME', title:'业务描述', align :'left',width:120},
				{field:'activName',	title:'当前环节',align :'center', width:30},
				{field:'busprop',	title:'业务性质',align :'center', width:20},
				{field:'PI_DURATION',	title:'总期限',align :'center', width:30,formatter:function(value){
					return value/60/24;
				}},
				{field:'node_duration',	title:'环节时限',align :'center', width:30},
				{field:'node_remain',	title:'环节剩余',align :'center', width:30},
				{field:'node_start_date',	title:'接收时间',align :'center', width:60},
				{field:'procstate',	title:'流程状态',align :'center', width:30},
				{field:'user_name',	title:'承办人',align :'center', width:30},
				{field:'button',title:'操作', width:80,align :'center',formatter:function(value,rec){return '<span><input  type="button" name="btn_detail" value="详情" onclick="btn_click(this);"/><input name="btn_reminder"  type="button" value="督办" onclick="btn_click(this);"/></span>';}}
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
		location.href="./qualityinspection.action";
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
*函数名称: querySamle
*功能说明: 按条件筛选样本
*参数说明: 
*返 回 值: 直接给目标赋值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function querySamle(){
	var form_data = $("#m_search_form").serializeObject();
	//筛选 条件  
	var data ={"search_str":$.toJSON(form_data)};
	//按条件重新加载datagrid
	sampleDataGrid.datagrid("load",data);
}
/**********************************************************************************
*函数名称: btn_click
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
*函数名称: clickCell
*功能说明: 单元格点击方法  判断如果点击的是详情则打开流程查看详情  如果点击的是督办  则打开督办界面
*参数说明: 
*返 回 值: 直接给目标赋值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function clickCell(){
	//alert(datagrid_btn_name);
	if(datagrid_btn_name=='btn_detail'){
		openWorkflow();
	}else if(datagrid_btn_name =='btn_reminder'){
		openReminder();
	}
}
/**********************************************************************************
*函数名称: openWorkflow
*功能说明: 打开流程页面查看流程详情
*参数说明: 
*返 回 值: 直接给目标赋值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function openWorkflow(){
	var selectedrow = sampleDataGrid.datagrid('getSelected');
	
	//alert(selectedrow.wiId);
	//var selectedrow = $('#table_user').datagrid('getSelections');
	//alert(JSON.stringify(selectedrow)+'-----');
	//return;
	//打开窗口  obj对象
	var obj={	
		id:"open_app",
		//窗口宽
		width: 988,
		//窗口高
		title:'办公页面',
		height: 598,
		modal: true,	
		destroy:true,
		onLoad:	function(){
			//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
			//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
			this.openerWindow = window;
			//this.document.getElementById('name').value='名称';
			//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
			this.args = {
				user: selectedrow,
			};			
			this.init(selectedrow);
		}
	};				
	
	//s
	var url = selectedrow.url;
	//alert(data);
	if(!url){
		alert("该地址不存在！"+url);							
	}
	else if(url=='null'||url==null||url==''||url==undefined )
	{
		alert("地址为null");
	}else if(url.indexOf("qualityinspection/inspection-index")!=-1){
//		if(url.indexOf(ctx)==-1){
//			url = ctx+url;
//		}
		if(url.indexOf(ctx)==-1){
			url = ctx+url;
		}
		obj.width=1230;
		//obj.src=url;
		
		
		/*window.open(obj);*/
		//openInTopWindow(obj);	
		
		//return null;
	}
	else
	{													
		//window.showModalDialog(data+'?wiId='+selectedrow.wiId,'NEW','dialogHeight: 600px; dialogWidth: 1200px;');
		//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
		//obj.src=url;
		/*window.open(obj);*/
				
	}
	url = url.replace(ctx,ctx+"/jsp");
	url = url.replace(".action",".jsp");
	obj.src=url;
	//alert(obj.src);
	openInTopWindow(obj);	
	//this.init(selectedrow);
}
/**********************************************************************************
*函数名称: openReminder
*功能说明: 打开督办界面  进行督办操作
*参数说明: 
*返 回 值: 直接给目标赋值
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function openReminder(){
	
	$('#div_dialog').dialog('open');  
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
