var bus_type_id = 1001;
var activNames;
//初始化
function init(activName,procdefId) {
	//alert(activName);
	activNames = activName;
	var cyyDataGrid = $('#table_cyy').datagrid({
		fit:true,
		//表格数据来源
		url:ctx+'/common/configur!getComLanById.action?procdefId='+procdefId+'&temptype=053002&time='+new Date(),
		//表格每列宽度自动适应表格总宽度
		autoRowHeight:false,
		fitColumns: true,
		//去掉边框
		border : true,
		//是否有翻页栏
		pagination:false,
		//pagePosition:'top',
		//每页行数
		pageSize:20,
		//是否在最左增加一列显示行号的列
		rownumbers:true,
		//主键值所在行。在使用复选框时必须设置此项。
		idField: 'LANGUAGE_ID',
		//表格的行是否交替显示不同背景色					
		striped:true,
		nowarp: true,
		//只允许单选一行
		singleSelect:true,
		//是否在点选表中一行时同时选中复选框
		//checkOnSelect:true,
		//是否在选中复选框时同时点选表中一行
		//selectOnCheck:true,
		//列属性设置
		queryParams : {
			//djlx_id : '2'
		},
		columns:[[
			//每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
			{ title : '名称', field : 'LANGUAGE_NAME', width : 100 },
			{ title : '内容', field : 'LANGUAGE_CONTENT', width : 400 }
			
		]]
		});

	
	
};//初始化结束
//保存选中常用语

function  submit(){
	
	var row = $('#table_cyy').datagrid('getSelected');
	if(row == null){
		
		top.$.messager.alert('提示', '请选择一条记录！', 'info',
				function() {					
				});	
		return;
	}

	openerWindow.$("#ggnr").text(row.LANGUAGE_CONTENT);
	openerWindow.focus();
	closeInTopWindow('anmodel');

  }
