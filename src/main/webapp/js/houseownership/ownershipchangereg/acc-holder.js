/*********************************************************************************
*文  件  名  称: acc-holder.js
*功  能  描  述: 变更登记申请表之权利人变更
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Joyon
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
var holLst = ['060016','060027','060030','060017','060019','060018','060022','060029'];
var changeLastEditIndex_hol;
$(function() {
	 $('#table_holder_cbx').datagrid({
		title:'变更前权利人信息',
		height:200,
		url:ctx+"/houseownership/correction!getNowHolder.action?time="+new Date()+"&proc_id="+proc_id,
		// 表格每列宽度自动适应表格总宽度
		autoRowHeight : true,
		fitColumns : false,
		// 去掉边框
		border : true,
		striped : true,
		// 是否有翻页栏
		pagination : false,
		// pagePosition:'top',
		// 每页行数
		//pageSize : 10,
		// 是否在最左增加一列显示行号的列
		rownumbers : true,
		// 主键值所在行。在使用复选框时必须设置此项。
		//idField : 'jjclmc',
		// 表格的行是否交替显示不同背景色
		striped : true,
		// 只允许单选一行
		singleSelect : true,
		
		columns : [ [{
			           title : '操 作',  
			           field : 'button',
			           formatter : function(value,rec)
			           {return '<span><input type="button" value="详 情" onclick=""/></span>';},
			           width : 60
		            },{
		     			title : '权利人',
		     			field : 'HOL_NAME',
		     			width : 80
		     		}, {
		     			title : '权利人类型',
		     			field : 'HOL_TYPE',
		     			width : 80
		     		}, {
		     			title : '证件类型',
		     			field : 'CER_TYPE',
		     			width : 90
		     		}, {
		     			title : '身份证号码/组织机构代码',
		     			field : 'CER_NO',
		     			width : 150
		     		}, {
		     			title : '份额',
		     			field : 'POSITION',
		     			width : 40
		     		}, {
		     			title : '地址',
		     			field : 'ADDRESS',
		     			width : 160
		     		}, {
		     			title : '法人',
		     			field : 'LEGAL_NAME',
		     			width : 80
		     		}, {
		     			title : '权利人种类',
		     			field : 'HOL_REL',
		     			width : 90
		     		}, {
		     			hidden:'true',
		     			field : 'HOLDER_ID',
		     			width : 50
		     		}, {
		     			hidden:'true',
		     			field : 'REG_UNIT_ID',
		     			width : 50
		     		}, {
		     			hidden:'true',
		     			field : 'REG_UNIT_TYPE',
		     			width : 50
		     		}] ],
		     		onClickCell:function(rowIndex, field, value){
		    			if(field=="button"){
		    				$('#table_holder_cbx').datagrid('selectRow',rowIndex);
		    				holDetail();
		    			}
		    		},
		     		// 表头，添加工具栏。
		     		onClickRow : function() {},
		     		onLoadSuccess : function() {}
	});
	
	$('#div_change_holder_detail').datagrid({
		title:'变更后权利人信息',
		height:200,
		url:ctx+"/houseownership/correction!getNowHolder.action?time="+new Date()+"&proc_id="+proc_id,
		// 表格每列宽度自动适应表格总宽度
		autoRowHeight : true,
		fitColumns : false,
		// 去掉边框
		border : true,
		striped : true,
		// 是否有翻页栏
		pagination : false,
		// pagePosition:'top',
		// 每页行数
		//pageSize : 10,
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
		            	   title : '操 作',  
				           field : 'button',
				           formatter : function(value,rec)
				           {return '<span><input type="button" value="编辑" onclick=""/></span>';},
				           width : 60
	                  },{
			     			title : '权利人',
			     			field : 'HOL_NAME',
			     			width : 80
			     		}, {
			     			title : '权利人类型',
			     			field : 'HOL_TYPE',
			     			width : 80
			     		}, {
			     			title : '证件类型',
			     			field : 'CER_TYPE',
			     			width : 90
			     		}, {
			     			title : '身份证号码/组织机构代码',
			     			field : 'CER_NO',
			     			width : 150
			     		}, {
			     			title : '份额',
			     			field : 'POSITION',
			     			width : 40
			     		}, {
			     			title : '地址',
			     			field : 'ADDRESS',
			     			width : 160
			     		}, {
			     			title : '法人',
			     			field : 'LEGAL_NAME',
			     			width : 80
			     		}, {
			     			title : '权利人种类',
			     			field : 'HOL_REL',
			     			width : 90
			     		}, {
			     			hidden:'true',
			     			field : 'HOLDER_ID',
			     			width : 50
			     		}, {
			     			hidden:'true',
			     			field : 'REG_UNIT_ID',
			     			width : 50
			     		}, {
			     			hidden:'true',
			     			field : 'REG_UNIT_TYPE',
			     			width : 50
			     		}, {
			     			hidden:'true',
			     			field : 'RIGHT_REL_ID',
			     			width : 50
			     		}]],
		             toolbar : [{
		            	 id : 'holder_save',
		            	 text : '保存',
		            	 iconCls : 'icon-ok',
		            	 disabled : false,
		            	 handler : doSaveHol
		             },'+',{
		            	 id : 'holder_add',
		            	 text : '增加',
		            	 iconCls : 'icon-add',
		            	 disabled : false,
		            	 handler : doAddHol
		             },'-', {
		     			id : 'holder_delete',
		    			text : '删除',
		    			iconCls : 'icon-remove',
		    			disabled : false,
		    			handler : doDelHol
		    		}],
		    		onClickCell:function(rowIndex, field, value){
		    			if(field=="button"){
		    				$('#div_change_holder_detail').datagrid('selectRow',rowIndex);
		    				holEdit();
		    			}
		    		},
		             // 表头，添加工具栏。
		             onClickRow : changeRowClick_hol,
		             onAfterEdit:function(index,row){     
		            	 row.editing = false;     
		            	 $('#div_change_holder_detail').datagrid('refreshRow', index);     
		             },
		             onLoadSuccess : function(data) {
		            	 //数据加载成功时  checkbox 数据回填
		            	 //alert(JSON.stringify(data.rows));
		            	 var rows = data.rows;
		            	 for(var i = 0;i<rows.length;i++){
		            		 for(var j=0;j<change_data_list.length;j++){
		            			 if(rows[i].CHANGE_CODE == change_data_list[j].value){
		            				 $("#"+change_data_list[j].value).attr("checked","checked");
		            			 }
		            			 
		            		 }
		            		 //如果有房屋属性   则把新房屋属性value赋值
//		     				if(rows[i].CHANGE_CODE==change_data_item.HOUSE_ATTR){
//		     					new_house_attr.value=rows[i].NEW_DATA;
//		     				}
		            	 }
		            	 
		             }
	});
});

/**
 * 保存更改后的数据
 * 将div_change_holder_detail表格中增、删、改的数据应用到后台
 */
function doSaveHol() {
	top.$.messager.alert('提示', 'hello,boy', function(){});
};
/**
 * 增加一条权利人的数据
 * 将div_change_holder_detail表格中'增'的数据应用到后台
 */
function doAddHol() {
	var lastRowIdx = $('#div_change_holder_detail').datagrid('getRows').length-1;
	var row = $('#div_change_holder_detail').datagrid('selectRow',lastRowIdx);
	if(row.HOL_NAME){
		$('#div_change_holder_detail').datagrid('appendRow',
				{HOL_NAME:'',HOL_TYPE:'',CER_TYPE:'',CER_NO:'',POSITION:'',ADDRESS:'',LEGAL_NAME:'',HOL_REL:''});
		changeLastEditIndex_hol = $('#div_change_holder_detail').datagrid('getRows').length-1; 
		//编辑行时点击保存  会取不到数据  解决
		var rows = $('#div_change_holder_detail').datagrid('getRows');
	    for ( var i = 0; i < rows.length; i++) {
	    	 $('#div_change_holder_detail').datagrid('endEdit', i);
	    }
	    
	    $('#div_change_holder_detail').datagrid('selectRow', changeLastEditIndex_hol);  
	    $('#div_change_holder_detail').datagrid('beginEdit', changeLastEditIndex_hol);  
	}
};
/**
 * 删除一条权利人的数据
 * 将div_change_holder_detail表格中增、删、改的数据应用到后台
 */
function doDelHol() {
	var selectRow = $('#div_change_holder_detail').datagrid('getSelected');
	alert(selectRow);
};

/**********************************************************************************
*函数名称: 数据项筛选框点击事件
*功能说明: 复选框选中事件  选中时  添加新一行   未选中时   删除这一行
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-06-19
*修改历史: 
***********************************************************************************/
function cbxClick_hol(cbx){
	var cbx_id;
	var change_name;
	cbx_id = cbx.id;
	change_name = $('#lbl_'+cbx_id).text();
};

function holDetail(){
	var row = $('#table_holder_cbx').datagrid('getSelected');
	openInTopWindow({
		// 窗口元素的id
		id : 'detail_holder_win',
		// 窗口iframe的src
		src : ctx+'/jsp/common/holder/detailHolder.jsp',
		// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
		destroy : true,
		// 窗口标题
		title : '查看权利人信息',
		// 窗口宽
		width : 700,
		// 窗口高
		height : 400,
		modal : true,
		// 窗口中iframe的window对象的onLoad回调函数设置
		onLoad : function() {
			// 此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
			// 因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
			this.openerWindow = window;
			// 将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
			this.args = {
				user : row,
				userDataGrid : userDataGrid
			};
			this.init(row);
		}
	});
}
function holEdit(){
	var holarr = new Array();
	var row = $('#div_change_holder_detail').datagrid('getSelected');
	for(var i = 0; i < holLst.length; i++){
		if($('#'+holLst[i]).is(':checked')){
			holarr.push(holLst[i]);
		}
	} 
	if(row.RIGHT_REL_ID){
	openInTopWindow({
		// 窗口元素的id
		id : 'edit_holder_win',
		// 窗口iframe的src
		src : ctx+'/jsp/common/holder/editHolder.jsp',
		// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
		destroy : true,
		// 窗口标题
		title : '编辑权利人',
		// 窗口宽
		width : 700,
		// 窗口高
		height : 400,
		modal : true,
		// 窗口中iframe的window对象的onLoad回调函数设置
		onLoad : function() {
			// 此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
			// 因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
			this.openerWindow = window;
			// 将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
			this.args = {
					user : row,
					userDataGrid : userDataGrid,
					array:holarr
			};
			this.init(row,holarr);
		}
	});
	}
}

function changeRowClick_hol(rowIndex, rowData){
	//点击列时激活编辑状态    只在初审环节激活
	if(proc_node!= state1.string1){
		return;
	}
       
    if (changeLastEditIndex_hol != rowIndex) {
     		 $('#div_change_holder_detail').datagrid('endEdit', changeLastEditIndex_hol);
     		 $('#div_change_holder_detail').datagrid('beginEdit', rowIndex);
     		
    }
    changeLastEditIndex_hol = rowIndex;
    for(var i=0;i<holLst.length;i++){ 	
        setEditorCombobox(holLst[i],rowIndex);
    }
}


