var delay_his_tab;
function init(proc){
	delay_his_tab = $('#delay_his_tab').datagrid({
		title:'延期历史记录',
		//数据请求地址
		url : ctx+'/workflow/workflow!loadDelayLst.action?sdelayi.bus_id='+proc.bus_id+'&sdelayi.delay_status='+proc.status+'&time='+new Date(),
		fit : true,
		//表格每列宽度自动适应表格总宽度
		fitColumns: true,
		//去掉边框
		border : false,
		//是否有翻页栏
		pagination:true,
		//pageNumber:1,
		pageList:[3,6,9,12],
		//每页行数
		pageSize:6,
		//是否在最左增加一列显示行号的列
		rownumbers:true,
		//pagePosition:'bottom',
		//主键值所在行。在使用复选框时必须设置此项。
		idField: 'delay_id',
		//表格的行是否交替显示不同背景色					
		striped:true,
		//只允许单选一行
		singleSelect:false,
		//是否在点选表中一行时同时选中复选框
		/*checkOnSelect:true,
		//是否在选中复选框时同时点选表中一行
		selectOnCheck:true,*/
		columns :[[ 
			{ title : '延期编号',     field : 'delay_no'   ,width:100 },
			{ title : '申请科室',   field : 'delay_app_part' ,width:80 },
			{ title : '业务描述',   field : 'bus_des'  ,width:100 },
			{ title : '延期申请人',     field : 'delay_app' ,width:100 },
			{ hidden:'true',field : 'delay_id'},
			{ hidden:'true',field : 'bus_id'}
		]],
		onDblClickRow:function(rowIndex, rowData){
			    $('#delay_his_tab').datagrid('selectRow',rowIndex);
			    alert(JSON.stringify(rowData));
		}
	 });
}
