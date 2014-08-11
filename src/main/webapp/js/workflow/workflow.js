var userDataGrid;	
//var row;
//初始化加载.		
$(function(){
	
	 userDataGrid = $('#table_user').datagrid({
		loadFilter:pagerFilter,//该方法在enum-data.js中
		fit:true,
		//表格数据来源
		url:'workflow!getworkflowList.action',
		//表格每列宽度自动适应表格总宽度
		fitColumns: true,
		//去掉边框
		border : true,
		//是否有翻页栏
		pagination:true,
		//每页行数
		pageSize:20,
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
			{field:'busno', title:'业务编号',align :'center', width:60, sortable:true},
			{field:'procName', title:'业务描述', align :'left',width:120},
			{field:'activName',	title:'当前环节',align :'center', width:30},
			{field:'busprop',	title:'业务性质',align :'center', width:20},
			{field:'totallimit',	title:'总期限',align :'center', width:30},
			{field:'nodelimit',	title:'环节时限',align :'center', width:30},
			{field:'nodesurp',	title:'环节剩余',align :'center', width:30},
			{field:'acctime',	title:'接收时间',align :'center', width:30},
			{field:'procstate',	title:'流程状态',align :'center', width:30},
			{field:'button',formatter:function(value,rec){return '<span><input  type="button" value="办理" onclick=""/></span>';}}
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
		onLoadSuccess : function() {
			//加载完毕禁用“编辑”、“删除”按钮
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
		}
	});
	
	//选择表格中某一行的数据。
	function getSelected(func){
		var selectedrow = $('#table_user').datagrid('getSelected');
		
		if (selectedrow){
			row = selectedrow;
			//调用相关函数
			func.call(this,selectedrow);
		}
		else{
			
			$.messager.alert('提示：','请点击选中表格中的某一行.');
		}
	};

	//新增
	function doAdd() {		
		openInTopWindow({
			//窗口元素的id
			id: 'add_user_win',
			//窗口iframe的src
			src: '../user/add.jsp',
			//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy: true,
			//窗口标题
			title: '新增用户',
			//窗口宽
			width: 700,
			//窗口高
			height: 550,
			modal: true,
			//窗口中iframe的window对象的onLoad回调函数设置
			onLoad:	function(){
				//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
				//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
				this.openerWindow = window;
				//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
				this.args = {
					userDataGrid: userDataGrid
				};
				this.init();
			}
		});
	};

	
	

	//双击表格中某一行的触发的事件
	function rowDblclick(rowIndex,row){
		var i = 0;
		var props = [];
		
		for(var p in row){
			props[i++]= p+' = '+row[p];
			
		}
		//alert(props.join(',\n'));
		//info(row);
	};
	
	//定义流程实例查询
	function searchProcint(){
		//$('#searchform').form()
		//$('#dg_proctask').datagrid('load',{});

		var fields = $("#procinstSearchform").serializeArray();
		var o = {};
		jQuery.each( fields, function(i, field){
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
				
			}
		});
		//console.debug(o);
		$('#dg_procinst').datagrid('load',o);
		//console.info("form : "+ o.procName );
		
	};	
	
	$('#simpleform').form({
		dataType: 'json',
		url: 'workflow!getUrlSpecify.action',
		success: function(data){
			
			userDataGrid.datagrid('loadData',data);
		}
	});
	
	
	
});

function submit1(){
	$('#simpleform').submit();
}

function openTab(title,url) {
    var title = title||"";
    /*if ($('#tabs').tabs('exists', title)) {
        $('#tabs').tabs('select', title);
    } else {*/
		$('#tt').tabs('add', {
			title: title,
			content: '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%"></iframe>',
			closable: true
		});
    //}
}

function btn_bl(button){
		var selectedrow = $('#table_user').datagrid('getSelected');
					//alert(selectedrow[0].wiId+"++++");
					var objs={					
						url:"workflow!getUrlSpecify.action",
						type:'post',
						data:{"wiId":selectedrow.wiId,"time":new Date()},
						success:function(data){
							//alert(data);
							if(!data){
								alert("该地址不存在！"+data);							
							}
							else if(data=='null'||data==null||data==''||data==undefined )
							{
								alert("地址为null");
							}else if(data.indexOf("qualityinspection/inspection-index")!=-1){
							
//								if(data.indexOf(ctx)==-1){
//									data = ctx+data;
//								}
								if(data.indexOf(ctx+"/jsp")==-1){
									data = ctx+"/jsp"+data;
								}
								data="/dxtx_re/common/work-window!frame.action?procdefId="+selectedrow.procdefId+"&nodeid="+selectedrow.activdefId;
								parent.openTab('办公页面',data);
							}
							else
							{
								data="/dxtx_re/common/work-window!frame.action?procdefId="+selectedrow.procdefId+"&nodeid="+selectedrow.activdefId;
								parent.openTab('办公页面',data);
							}
						}		
					};
					
					$.ajax(objs);
		
}
