//初始化加载.	
//var proc=this.parent.proc;
var proc = 1;
//
//var proc_node = proc.activName;
//var proc_id = proc.procId;
var proc_id = 1000016843;


var houseDataGrid;
$(function() {

	houseDataGrid = $('#table_house').datagrid({
		title:'房地产信息',
		height:400,
		url:ctx+"/mortgage/morsetup!getHouseInfo.action?time="+new Date()+"&proc_id="+proc_id,
		// 表格每列宽度自动适应表格总宽度
		autoRowHeight : true,
		fitColumns : true,
		// 去掉边框
		border : true,
		striped : true,
		// 是否有翻页栏
		pagination : true,
		// pagePosition:'top',
		// 每页行数
		pageSize : 10,
		// 是否在最左增加一列显示行号的列
		rownumbers : true,
		// 主键值所在行。在使用复选框时必须设置此项。
		//idField : 'jjclmc',
		// 表格的行是否交替显示不同背景色
		striped : true,
		// 只允许单选一行
		singleSelect : true,
		
		columns : [ [
		     		// 每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。

		     		// {field:'ck',checkbox:true},
		     		{
		     			title : '宗地号',
		     			field : 'PARCEL_CODE',
		     			width:80
		     			
		     		}, {
		     			title : '宗地坐落',
		     			field : 'LAND_ADDRESS',
		     			width:100
		     			
		     		},
		     		
		     		
		     		{
		     			title : '建筑物名称',
		     			field : 'BUILDING_NAME',
		     				width:100
		     		}, 
		     		{
		     			title : '栋号',
		     			field : 'BUILD_NO',
		     			width:50
		     			
		     		}, 
		     		{
		     			title : '房号',
		     			field : 'ROOMNAME',
		     			width:50
		     			
		     		},  {
		     			title : '项目名称',
		     			field : 'PRO_NAME',
		     			width:100
		     			
		     		},
		     		{
		     			title : '操作',		     		
		     			field:'button',
		     			formatter:function(value,rec){return '<span><input  type="button" value="登记簿预览" onclick=""/></span>';}
		     		}
		     		

		     		] ],
		     		// 表头，添加工具栏。
		     		onClickRow : function() {
		     		},
		     		onClickCell:function(rowIndex, field, value){
		    			//alert("onclickcell event"+rowIndex+field+value);
		    			if(field=="button"){
		    				$('#table_house').datagrid('selectRow',rowIndex);
		    				regpreview(this);
		    			}
		    		},
		     		onLoadSuccess : function() {
		     			
		     			
		     		}
	});
	

	
	
});

	// 新增
	function regpreview() {
		var selectedrow = $('#table_house').datagrid('getSelected');

		openInTopWindow({
			// 窗口元素的id
			id : 'regbook_pre',
			// 窗口iframe的src
			src : ctx+'/jsp/common/registerbook/registermort.jsp?time='+new Date(),
			// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy : true,
			// 窗口标题
			title : '登记簿预览',
			// 窗口宽
			width : 900,
			// 窗口高
			height : 600,
			modal : true,
			// 窗口中iframe的window对象的onLoad回调函数设置
			onLoad : function() {
				// 此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
				// 因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
				this.openerWindow = window;
				// 将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
				this.args = {
					//userDataGrid : userDataGrid
				};
				this.init(proc);
			}
		});
	}


