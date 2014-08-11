
		
//var row;
//初始化加载.	
var bus_type_id ;
var rec_type_flag = 0;//材料类型   0代表接件材料   1发文材料清单
var selectedNode;//选中的左侧菜单业务类型
$(function(){

   	var userDataGrid = $('#table_user').datagrid({
		fit:true,
		//表格数据来源
		url:ctx+'/common/configur!getRMaterialCon.action?rec_type_flag='+rec_type_flag+'&time='+new Date(),
		//表格每列宽度自动适应表格总宽度
		autoRowHeight:false,
		fitColumns: true,
		//去掉边框
		border : false,
		//是否有翻页栏
		pagination:true,
		//pagePosition:'top',
		//每页行数
		pageSize:20,
		//是否在最左增加一列显示行号的列
		rownumbers:true,
		//主键值所在行。在使用复选框时必须设置此项。
		idField: 'jjclmc',
		//表格的行是否交替显示不同背景色					
		striped:true,
		//只允许单选一行
		singleSelect:true,
		//是否在点选表中一行时同时选中复选框
		//checkOnSelect:true,
		//是否在选中复选框时同时点选表中一行
		//selectOnCheck:true,
		//列属性设置
		queryParams : {
			bus_type_id : '-1'
		},
		columns:[[
			//每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
			
			 {field:'REC_TYPE_FLAG',hidden:true},
			{ title : '名称', field : 'CFIG_REC_NAME', width : 100 },
			{ title : '类型', field : 'CFIG_REC_TYPE', width : 30 ,
				 formatter : function(value){
					 if(value == '024001'){
						 return '必接件';
					 }
					 if( value == '024002'){
						 
						 return '选接件';
					 }
				 }
			},
			{ title : '种类', field : 'CFIG_REC_STYLE', width : 30, 
				 formatter : function(value){
					 if(value == '025001'){
						 return '原件';
					 }
					 if( value == '025002'){
						 
						 return '复印件';
					 }
				 }
			
			},
			{ title : '份数', field : 'CFIG_REC_COPY', width : 30 },
			{ title : '页数', field : 'CFIG_PAGE', width : 30 },
			{ title : '配置人', field : 'CFIG_PERSON', width : 30 },
			{ title : '配置时间', field : 'CFIG_DATE', width : 80 }
		]],
		//表头，添加工具栏。
		toolbar : [{
			id : 'user_add',
			text:'新增',
			iconCls:'icon-add',
			handler:doAdd			
		},'-',{
			id : 'user_edit',
			text:'编辑',
			iconCls:'icon-pencil',
			disabled : true,
			handler:doEdit
		}
		,'-',{
			id : 'user_delete',
			text:'删除',
			iconCls:'icon-remove',
			disabled : true,
			handler : doDelete
		},'->',{
			xtype : 'searchbox',
			prompt : '接件材料名称',
			width : 200,
			searcher : function(value) {
				userDataGrid.datagrid('load', {
					searchStr : value
				});
			}
		}],
		onClickRow : function() {
			//点击列时激活“编辑”、“删除”按钮
			$('#user_edit').linkbutton('enable');
			$('#user_delete').linkbutton('enable');
		},
		onLoadSuccess : function() {
			//加载完毕禁用“编辑”、“删除”按钮
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
		}
		
		
		
		
	});
	//初始化产品树
	var productTree = $('#productTree').tree({
		url : ctx+'/common/certificate!getBusTypeToTree.action?time='+new Date(),
		onLoadSuccess : function() {
			//$('#productTree').tree('select', $('#productTree').tree('find', '-1').target);
		},
		onSelect : function(node) {
		    
			bus_type_id = node.id;
			selectedNode = node;
			$('#table_user').datagrid('load', {
				bus_type_id : node.id
				
			});
		
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
		//alert("#djlx_id").val();
		if(selectedNode == null){
			alert("请选择业务类型");
			return;
		}
		openInTopWindow({
			//窗口元素的id
			id: 'add_user_win',
			//窗口iframe的src
			src:ctx+'/jsp/common/recmaterial/addmaterial.jsp',
			//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy: true,
			//窗口标题
			title: '新增接件材料',
			//窗口宽
			width: 700,
			//窗口高
			height: 400,
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
				this.init(bus_type_id,rec_type_flag);
			}
		});
	};

	//编辑
	function doEdit() {
		var row = userDataGrid.datagrid('getSelected');
		
		openInTopWindow({
			//窗口元素的id
			id: 'edit_user_win',
			//窗口iframe的src
			src: ctx+'/jsp/common/recmaterial/editmaterial.jsp',
			//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy: true,
			//窗口标题
			title: '编辑接件材料信息',
			//窗口宽
			width: 700,
			//窗口高
			height: 400,
			modal: true,
			//窗口中iframe的window对象的onLoad回调函数设置
			onLoad:	function(){
				//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
				//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
				this.openerWindow = window;
				//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
				this.args = {
					user : row,
					userDataGrid : userDataGrid
				};
				this.init(row,bus_type_id,rec_type_flag);
			}
		});
	};
	
	//删除
	function doDelete(){
		var row = userDataGrid.datagrid('getSelected');
		top.$.messager.confirm('确认', '确定要删除材料名称为[' + row.CFIG_REC_NAME + ']？', function(result){
			if (result) {
				$.ajax({
					url : ctx+"/common/configur!delRecMaterialCon.action?time="+new Date(),
					type : 'post',
					data : {
						cfig_receival_id : row.CFIG_RECEIVAL_ID
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							top.$.messager.alert('提示', '材料删除成功！', 'info', function() {
								userDataGrid.datagrid('reload');
							});
						} else {
							top.$.messager.alert('提示', '材料删除错误！', 'error');
						}
					}
				});
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
		alert(props.join(',\n'));
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
		url: 'appDelegate/getUserList.run',
		success: function(data){
			userDataGrid.datagrid('loadData',data);
		}
	});
	
});

function submit1(){
	$('#simpleform').submit();
}
