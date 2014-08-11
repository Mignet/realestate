//var row;
//初始化加载.	
proc=this.parent.proc;
var proc_id = proc.procId;
//var proc_id = 1000016479;
$(function(){
	//禁用所有文本框
	$(":input").attr("disabled", "disabled");
	//初始化建筑物信息表
	
	$.ajax({
	    dataType: 'json',
		url:"../certificate!getBuildById.action?time="+new Date()+"&proc_id="+proc_id,
		//data:{"proc_id":proc_id,"time":new Date()},
		success:function(data){
			data=data.rows[0];
			//alert(JSON.stringify(data));
		 	if(data){
		 		$('#tab_build').form("load",data);
		 	}
		}
	});
	
	//创建分栋汇总表
	var userDataGrid = $('#table_fdhzb').datagrid({
		title:'分栋汇总表',
		//fit:true,
		//表格数据来源
		height:150,
		url:'../certificate!getBuildById.action?proc_id='+proc_id+"&time="+new Date(),
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
			{field:'building_name', title:'建筑物名称', width:70, sortable:true},
			{field:'build_no', title:'栋号', width:20, sortable:true},	
			{field:'layer_count', title:'总层数', width:20},						
			{field:'FWYT',	title:'用途', width:30},
			{field:'floor_area',	title:'总建筑面积', width:50},
			{field:'build_ld_area',	title:'基底面积', width:50},
			{field:'JDFTXS',title:'基底分摊系数', width:60},
			{field:'KGRQ',	title:'开工日期', width:50},
			{field:'compleion_date',	title:'竣工日期', width:50},
			{field:'JK',	title:'价款', width:50},
			{field:'BZ',	title:'备注', width:50}	
		]],
		onLoadSuccess:function(data){
			//alert("success");
		},
		onLoadError:function(){
			//alert("load data error");
		}
	});
	
	
	//创建分户汇总表
	var userDataGrid1 = $('#table_fhhzb').datagrid({
		title:'分户汇总表',
		//fit:true,
		//表格数据来源
		url:'../certificate!getHouseById.action?proc_id='+proc_id+"&time="+new Date(),
		//表格每列宽度自动适应表格总宽度
		height:150,
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
			{field:'build_no', title:'栋号', width:30, sortable:true},
			{field:'roomname', title:'房号', width:30, sortable:true},
			{field:'kfsmc', title:'开发商名称', width:100, sortable:true },
			{field:'parcel_code', title:' 宗地编号', width:50, },
			{field:'build_area', title:'建筑面积', width:50, },
			{field:'fentan_area', title:'分摊建筑面积', width:80, },
			{field:'taonei_area', title:'套内建筑面积', width:80, },
			{field:'ft_glebe_area', title:'分摊公用地面积', width:80, },
			{field:'FTJDMJ', title:'分摊基底面积', width:80, },
			{field:'ft_glebe_area', title:'用地面积', width:50, },
			{field:'flatlet_usage', title:'用途', width:50, },
			{field:'JK', title:'价款', width:50, },
			{field:'BZ', title:'备注', width:50, }			
		]]
	});
 
});//初始化结束

/**********************************************************************************
*函数名称: 页面校验方法
*功能说明: 验证页面上的非空  及数据格式   
*参数说明: 
*返 回 值: obj  result(true通过  false不通过) message(消息)  page_name(当前页面名字)
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function validate(){
	//返回结果对象
	var result ={
			result:true,
			message:'',
			page_name:'宗地信息表'
	}
	return result;
}
function submit(){
	return true;
}
