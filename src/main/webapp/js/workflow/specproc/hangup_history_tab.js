var suspend_his_tab;
function init(proc){
	suspend_his_tab = $('#suspend_his_tab').datagrid({
		title:'挂起历史信息',
		//数据请求地址
		url : ctx+'/workflow/workflow!loadSuspendLst.action?ssuspendi.bus_id='+proc.bus_id+'&ssuspendi.sus_status='+proc.status+'&time='+new Date(),
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
		idField: 'sus_id',
		//表格的行是否交替显示不同背景色					
		striped:true,
		//只允许单选一行
		singleSelect:false,
		//是否在点选表中一行时同时选中复选框
		/*checkOnSelect:true,
		//是否在选中复选框时同时点选表中一行
		selectOnCheck:true,*/
		columns :[[ 
			{ title : '挂起编号',     field : 'sus_no'   ,width:100 },
			{ title : '申请科室',   field : 'app_off' ,width:80 },
			{ title : '业务描述',   field : 'bus_des'  ,width:100 },
			{ title : '挂起申请人',     field : 'sus_app' ,width:100 },
			{ hidden:'true',field : 'sus_id'},
			{ hidden:'true',field : 'bus_id'}
		]],
		onDblClickRow:function(rowIndex, rowData){
			    $('#suspend_his_tab').datagrid('selectRow',rowIndex);
			    alert(JSON.stringify(rowData));
		}
	 });
}
