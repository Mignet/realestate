//var row;

//初始化加载.	
var regcode;
var serialNumber ={num1:"登记编号",num2:"归档号"};

var proc;
var dataGridData;
var lbhdataGrid;
var lbhtable;
var obj={};
//var housecode;
//检查是否可以受理该业务状态
var checkDataState;
var regunittypesign;
//确认受理
function start(){
	var objurl;
	  var selectedrow = $('#table_user_select').datagrid('getRows');
	  selectedrow.lastregcode="";
	  selectedrow =$.toJSON(selectedrow);
	  //var procName=proc.name+"("+selectedrow.proname+"--"+selectedrow.housecode+")";
	  var procdefId=proc.procdefid;
	  $.ajax({
		    dataType: 'json',
			url:"preaudit!doApply.action",
			data:{'serialName':serialNumber.num1,'procName':proc.name,'selectedrow':selectedrow,'procdefId':procdefId,"time":new Date()},
			success:function(data){
				//alert(JSON.stringify(data));
			 	if(data){
			 		var sysurl = data.workItem.urlSpecify;
			 	    var myurl;
			 	    if(sysurl.lastIndexOf('?') == -1)
			 	       myurl = sysurl + '?wiId='+data.workItem.wiId;
			 	    else
			 	       myurl = sysurl + '&wiId='+data.workItem.wiId;
			 		obj.regcode=data.regcode;
					obj=$.extend(obj,data.workItem);
					//objurl.src=data.workItem.urlSpecify+'?wiId='+data.workItem.wiId;
					openInTopWindow({	
							id:"open_app",
							//窗口宽
							width: 988,
							//窗口高
							title:'办公页面',
							height: 598,
							src:data.workItem.urlSpecify+'?wiId='+data.workItem.wiId,
							modal: true,	
							destroy:true,
							onLoad:	function(){
								//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
								//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
								this.openerWindow = window;
								//this.document.getElementById('name').value='名称';
								//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
								this.args = {
									user: obj
								};
								obj.proname="";
								this.init(obj);
							}
						});
			 	};
			}
		});

}

function addRegunit(){
	
	if(regunittypesign == enumdata.house){
		var data_ = lbhdataGrid.datagrid("getSelected");
		var data = {total:1,rows:[{REG_UNIT_TYPE:data_.reg_type,REG_UNIT_CODE:data_.code,REG_UNIT_NAME:data_.houselocation+data_.buildname+data_.buildnum+data_.roomno}]};
		//args.houseDataGrid.datagrid('loadData',data);
		//args.houseDataGrid.datagrid('load',{REG_UNIT_TYPE:data_.reg_type,REG_UNIT_CODE:data_.code,REG_UNIT_NAME:data_.houselocation+data_.buildname+data_.buildnum+data_.roomno});
//		args.houseDataGrid.datagrid('load',{REG_UNIT_TYPE:data_.reg_type,REG_UNIT_CODE:data_.code,REG_UNIT_NAME:data_.houselocation+data_.buildname+data_.buildnum+data_.roomno});
		
		args.houseDataGrid.datagrid('insertRow',{
			index: 0,	// index start with 0
			row: {
				REG_UNIT_TYPE:data_.reg_type,REG_UNIT_CODE:data_.code,REG_UNIT_NAME:data_.houselocation+data_.buildname+data_.buildnum+data_.roomno
			}
		});

		closeInTopWindow('add_regunit_win');
	}
	else if(regunittypesign == enumdata.parcel){
		
	}
	else if(regunittypesign == enumdata.build){
		
	}
}

function init(user){
	$("#reg_type").combodict({value:enumdata.house,onChange: selectChange});
	regunittypesign = enumdata.house ;
	/*proc=user;
	if(proc){
		$("#bustype").val(proc.name);		
	}*/
	getHouse();
	//getSelectData();
}

	
	//点击下拉框事件
	function selectChange()
	{
		
		if($("#reg_type").combodict("getValue")== enumdata.house)
		{
			$("#house").css("display","block");
			$("#land").css("display","none");
			$('#divHouse').panel('open');
			$('#divLand').panel('close');
			$('#divBuild').panel('close');
			$('#divRegCode').panel('close');
			getHouse();
			regunittypesign = enumdata.house;
		}
		else if($("#reg_type").combodict("getValue")== enumdata.parcel)
		{
			$("#house").css("display","none");
			$("#land").css("display","block");
			$("#build").css("display","none");
			$('#divHouse').panel('close');
			$('#divLand').panel('open');
			$('#divBuild').panel('close');
			$('#divRegCode').panel('close');
			getLand();
			regunittypesign = enumdata.parcel;
		}
		else if($("#reg_type").combodict("getValue")== enumdata.build)
		{
			$("#house").css("display","block");
			$("#land").css("display","none");
			$('#divHouse').panel('close');
			$('#divLand').panel('close');
			$('#divBuild').panel('open');
			$('#divRegCode').panel('close');
			getBuild();
			regunittypesign = enumdata.build;
		}
	}
	
	
	function getHouse() {
			//创建前置受理界面				
				dataGridData={
			//fit : true,
			title:'登记单元信息',
			height:240,
			// 表格数据来源
			url : 'preaudit!getHouseInfo.action',
			// 表格每列宽度自动适应表格总宽度
			autoRowHeight : false,
			fitColumns : true,
			// 去掉边框
			border : true,
			idField: 'code',
			striped : true,
			// 是否有翻页栏
			pagination : true,
			checkbox:'CK',
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
			singleSelect : false,
			// 是否在点选表中一行时同时选中复选框
			 checkOnSelect:false,
			// 是否在选中复选框时同时点选表中一行
			 selectOnCheck:false,
			// 列属性设置
			onClickRow: onCRHouse,
			columns : [ [
			// 每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
			{field:'ck',checkbox:true},
			{
				title : '登记单元类型',
				field : 'reg_type',
				width : 100,
				hidden:true
				
			},
			{
				field : 'houselocation',
				width : 100,
				hidden:true
				
			},
			{
				field : 'roomno',
				width : 100,
				hidden:true
				
			},
			{
				title : '登记单元',
				field : 'reg_typename',
				width : 60,
				align:'center'
			},
			{
				title : '登记单元编码',
				field : 'code',
				width : 100,
				align:'center'
			},
			{
				title : '楼名',
				field : 'buildname',
				width : 80,
				align:'center'
			},
			{
				title : '栋号',
				field : 'buildnum',
				width : 30,
				align:'center'
			},
			{
				title : '宗地号',
				field : 'parcelcode',
				width : 80,
				align:'center'
			}, {
				title : '实际用途',
				field : 'usage',
				width : 50,
				align:'center'
			}, {
				title : '所属项目',
				field : 'proname',
				width : 80,
				align:'center'
			}, {
				title : '建筑面积(㎡)',
				field : 'buildarea',
				width : 60,
				align:'center'
			}
			]],
			
			/*//将选中复选框的记录主键值记入数组seq_id_set
			onCheck: function(index,row){
				$.ajax({
					dataType: 'json',
					url:"register!checkBusData.action",
					data:{"bustype":proc.procdefid,"regtype":row.reg_type,"reg_unit_code":row.code,"time":new Date()},
					success:function(data){
						checkDataState=data.state;
					 		//alert($("#accept").attr("disabled"));
					 		$("#discription").empty();	
					 		if(data.state=="false")
					 		{					 			
					 			$("#discription").append(data.statemes);
					 			//$("#accept").linkbutton("disable");
					 		}
					 		else
					 		{
								$('#table_user_house').datagrid('selectRow',index);				
								var selectedRow = $('#table_user_house').datagrid('getSelected');
								$('#table_user_house').datagrid("deleteRow",index);
								$('#table_user_select').datagrid("appendRow",selectedRow);
					 			$("#discription").append(data.statemes);
					 		}
					 		
					 	}					
				});
				if(checkDataState=="true")
				{
					$('#table_user_house').datagrid('selectRow',index);				
					var selectedRow = $('#table_user_house').datagrid('getSelected');
					$('#table_user_house').datagrid("deleteRow",index);
					$('#table_user_select').datagrid("appendRow",selectedRow);
		 			$("#discription").append(data.statemes);
				}
				
			},*/
			onClickCell:function(rowIndex, field, value){
				
			},
			//将取消选中复选框的记录主键值从数组seq_id_set删除
			onUncheck: function(index,row){
				//selectRow(index,row);
				//seq_id_set.splice(seq_id_set.indexOf(row.OFFICE_ID),1);
			},
			//当全选时将所有主键值加入数组seq_id_set
			onCheckAll: function(rows){
				/*var rows=$('#table_user_house').datagrid("getRows");
				for (var i=0;i<rows.length;i){
					$('#table_user_house').datagrid("deleteRow",$('#table_user_house').datagrid("getRowIndex",rows[0]));
					$('#table_user_select').datagrid("appendRow",rows[0]);
				}*/
			},
			//当取消全选时将所有主键值从数组seq_id_set中删除
			onUncheckAll: function(rows){
				for (var i=0;i<rows.length;i++){
					for (var i=0;i<rows.length;i++){
						//selectRow(index,row[i]);
					}
				}
			},
		};		
				function onCRHouse(rowIndex, rowData){
					//alert(rowData);
					/*$.ajax({
						dataType: 'json',
						url:"register!checkBusData.action",
						data:{"bustype":proc.procdefid,"regtype":rowData.reg_type,"reg_unit_code":rowData.housecode,"time":new Date()},
						success:function(data){
						 		//alert($("#accept").attr("disabled"));
						 		$("#discription").empty();	
						 		if(data.state=="false")
						 		{					 			
						 			$("#discription").append(data.statemes);
						 			$("#accept").linkbutton("disable");
						 		}
						 		else
						 		{
						 			$("#discription").append(data.statemes);
						 			//$("#accept").linkbutton("disable");
						 		}
						 	}					
					});*/
				}
				lbhdataGrid = $('#table_user_house').datagrid(dataGridData);
	}
	
	
	
	//选中事件
	function selectRow()
	{
		$.ajax({
			url : ctx+"/common/certificate!insertNode.action?time="+new Date(),
			type : 'post',
			data : {"nodeid":nodeid,"bus_typeid":row.BUS_TYPE_ID,"officeid":row.OFFICE_ID},
			dataType : 'json',
			success : function(data) {
			
			}			
		});
	}
	

	
	function getBuild() {
		//创建前置受理界面				
			dataGridData={
		//fit : true,
		title:'登记单元信息',
		height:240,
		// 表格数据来源
		url : 'preaudit!getBuildInfo.action',
		// 表格每列宽度自动适应表格总宽度
		autoRowHeight : false,
		fitColumns : true,
		// 去掉边框
		border : true,
		idField: 'reg_type',
		striped : true,
		// 是否有翻页栏
		pagination : true,
		checkbox:'CK',
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
		singleSelect : false,
		// 是否在点选表中一行时同时选中复选框
		 checkOnSelect:false,
		// 是否在选中复选框时同时点选表中一行
		 selectOnCheck:false,
		// 列属性设置
		//onClickRow: onCRBuild,
		columns : [ [
		// 每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
		{field:'ck',checkbox:true},
		{
			title : '登记单元类型',
			field : 'reg_type',
			width : 100,
			hidden:true
			
		},
		{
			title : '登记单元',
			field : 'reg_typename',
			width : 60,
			align:'center'
		},
		{
			title : '登记单元编码',
			field : 'code',
			width : 80,
			align:'center'
		},
		{
			title : '楼名',
			field : 'buildname',
			width : 80,
			align:'center'
		},
		{
			title : '栋号',
			field : 'buildnum',
			width : 30,
			align:'center'
		},/*{
			title : '建筑物编码',
			field : 'code',
			width : 40,
			align:'center'
		}, */
		{
			title : '宗地号',
			field : 'parcelcode',
			width : 80,
			align:'center'
		}, {
			title : '实际用途',
			field : 'usage',
			width : 50,
			align:'center'
		}, {
			title : '所属项目',
			field : 'proname',
			width : 80,
			align:'center'
		}, {
			title : '建筑面积(㎡)',
			field : 'buildarea',
			width : 60,
			align:'center'
		}
		]],
		//将选中复选框的记录主键值记入数组seq_id_set
		onCheck: function(index,row){
			/*$.ajax({
				dataType: 'json',
				url:"register!checkBusData.action",
				data:{"bustype":proc.procdefid,"regtype":row.reg_type,"reg_unit_code":row.code,"time":new Date()},
				success:function(data){
					checkDataState=data.state;
				 		//alert($("#accept").attr("disabled"));
				 		$("#discription").empty();	
				 		if(data.state=="false")
				 		{					 			
				 			$("#discription").append(data.statemes);
				 			//$("#accept").linkbutton("disable");
				 		}
				 		else
				 		{
				 			$('#table_user_build').datagrid('selectRow',index);
							var selectedRow = $('#table_user_build').datagrid('getSelected');
							$('#table_user_build').datagrid("deleteRow",index);
							$('#table_user_select').datagrid("appendRow",selectedRow);
				 			$("#discription").append(data.statemes);
				 		}
				 		
				 	}					
			});
			//selectRow(index,row);
*/			
			
		},onClickCell:function(rowIndex, field, value){
			
		},
		//将取消选中复选框的记录主键值从数组seq_id_set删除
		onUncheck: function(index,row){
			//selectRow(index,row);
			//seq_id_set.splice(seq_id_set.indexOf(row.OFFICE_ID),1);
		},
		//当全选时将所有主键值加入数组seq_id_set
		onCheckAll: function(rows){
				var rows=$('#table_user_build').datagrid("getRows");
				for (var i=0;i<rows.length;i){
					$('#table_user_build').datagrid("deleteRow",$('#table_user_build').datagrid("getRowIndex",rows[0]));
					$('#table_user_select').datagrid("appendRow",rows[0]);
				}
		},
		//当取消全选时将所有主键值从数组seq_id_set中删除
		onUncheckAll: function(rows){
			for (var i=0;i<rows.length;i++){
				for (var i=0;i<rows.length;i++){
					//selectRow(index,row[i]);
				}
			}
		},

	};		
			lbhdataGrid = $('#table_user_build').datagrid(dataGridData);
}
	
	
	function getSelectData() {
		//创建前置受理界面				
			dataGridData={
		//fit : true,
		title:'登记单元选中列表',
		height:240,
		// 表格数据来源
		//url : 'preaudit!getBuildInfo.action',
		// 表格每列宽度自动适应表格总宽度
		autoRowHeight : false,
		fitColumns : true,
		// 去掉边框
		border : true,
		idField: 'reg_type',
		striped : true,
		// 是否有翻页栏
		pagination : true,
		checkbox:'CK',
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
		singleSelect : false,
		// 是否在点选表中一行时同时选中复选框
		 checkOnSelect:false,
		// 是否在选中复选框时同时点选表中一行
		 selectOnCheck:false,
		// 列属性设置
		//onClickRow: onCRBuild,
		columns : [ [
		// 每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
		{field:'ck',checkbox:true},
		{
			title : '登记单元类型',
			field : 'reg_type',
			width : 100,
			hidden:true
			
		},
		{
			title : '登记单元',
			field : 'reg_typename',
			width : 40,
			align:'center'
		},
		{
			title : '登记单元编码',
			field : 'code',
			width : 100,
			align:'center'
		}, {
			title : '实际用途',
			field : 'usage',
			width : 50,
			align:'center'
		}, {
			title : '所属项目',
			field : 'proname',
			width : 80,
			align:'center'
		}, {
			title : '面积(㎡)',
			field : 'buildarea',
			width : 40,
			align:'center'
		}
		]],
		//将选中复选框的记录主键值记入数组seq_id_set
		onCheck: function(index,row){
			//selectRow(index,row);
			var table;
			table= getGridType(row.reg_type);
			$('#table_user_select').datagrid('selectRow',index);
			var selectedRow = $('#table_user_select').datagrid('getSelected');
			$('#table_user_select').datagrid("deleteRow",index);
			$("#"+table).datagrid("appendRow",selectedRow);
			
		},onClickCell:function(rowIndex, field, value){
			
		},
		//将取消选中复选框的记录主键值从数组seq_id_set删除
		onUncheck: function(index,row){
			//selectRow(index,row);
			//seq_id_set.splice(seq_id_set.indexOf(row.OFFICE_ID),1);
		},
		//当全选时将所有主键值加入数组seq_id_set
		onCheckAll: function(rows){
			/*var rows=$('#table_user_select').datagrid("getRows");
			for (var i=0;i<rows.length;i){
				var table;
				table= getGridType(rows[0].reg_type);
				$('#table_user_select').datagrid('selectRow',$('#table_user_select').datagrid("getRowIndex",rows[0]));
				$('#table_user_select').datagrid("deleteRow",$('#table_user_select').datagrid("getRowIndex",rows[0]));
				$("#"+table).datagrid("appendRow",rows[0]);
			}*/
		},
		//当取消全选时将所有主键值从数组seq_id_set中删除
		onUncheckAll: function(rows){
			for (var i=0;i<rows.length;i++){
				for (var i=0;i<rows.length;i++){
					//selectRow(index,row[i]);
				}
			}
		},

	};		
			lbhdataGrid = $('#table_user_select').datagrid(dataGridData);
}
	function getGridType(table)
	{
		if(table=='009001')
		{
			table="table_user_house";
			lbhtable = "#table_user_house";
		}
		else if(table=='009002')
		{
			table="table_user_build";
			lbhtable = "#table_user_build";
		}
		else if(table=='009003')
		{
			table="table_user_land";
			lbhtable = "#table_user_land";
		}
		return table;
	}
	
	function getLand() {
			//创建前置受理界面				
			dataGridData={
			//fit : true,
			title:'登记单元信息',
			height:240,
			// 表格数据来源
			url : 'preaudit!getLandInfo.action',
			// 表格每列宽度自动适应表格总宽度
			autoRowHeight : false,
			fitColumns : true,
			// 去掉边框
			border : true,
			idField: 'reg_type',
			striped : true,
			// 是否有翻页栏
			pagination : true,
			checkbox:'CK',
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
			// 是否在点选表中一行时同时选中复选框
			 checkOnSelect:true,
			// 是否在选中复选框时同时点选表中一行
			 selectOnCheck:true,
			// 列属性设置
			//onClickRow: onCRLand,
			columns : [ [
			// 每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。

			// {field:'ck',checkbox:true},
			{field:'ck',checkbox:true},
			{
				title : '登记单元类型',
				field : 'reg_type',
				width : 100,
				hidden:true,
				align:'center'
				
			},			
			{
				title : '登记单元',
				field : 'reg_typename',
				width : 60,
				align:'center'
			},
			{
				title : '登记单元编码',
				field : 'code',
				width : 80,
				align:'center'
			},
			{
				title : '所属项目',
				field : 'proname',
				width : 80,
				align:'center'
			},
			{
				title : '用途',
				field : 'usage',
				width : 100,
				align:'center'
			},
			{
				title : '宗地面积(㎡)',
				field : 'parcelarea',
				width : 100,
				align:'center'
			}
			, {
				title : '坐落',
				field : 'location',
				width : 300,
				align:'center'
			}
			]],
			
			//将选中复选框的记录主键值记入数组seq_id_set
			onCheck: function(index,row){
				/*$.ajax({
					dataType: 'json',
					url:"register!checkBusData.action",
					data:{"bustype":proc.procdefid,"regtype":row.reg_type,"reg_unit_code":row.code,"time":new Date()},
					success:function(data){
						checkDataState=data.state;
					 		//alert($("#accept").attr("disabled"));
					 		$("#discription").empty();	
					 		if(data.state=="false")
					 		{					 			
					 			$("#discription").append(data.statemes);
					 			//$("#accept").linkbutton("disable");
					 		}
					 		else
					 		{
					 			$('#table_user_land').datagrid('selectRow',index);
								var selectedRow = $('#table_user_land').datagrid('getSelected');
								$('#table_user_land').datagrid("deleteRow",index);
								$('#table_user_select').datagrid("appendRow",selectedRow);
					 			$("#discription").append(data.statemes);
					 		}
					 		
					 	}					
				});*/
				//selectRow(index,row);
				
				
			},onClickCell:function(rowIndex, field, value){
				
			},
			//将取消选中复选框的记录主键值从数组seq_id_set删除
			onUncheck: function(index,row){
				//selectRow(index,row);
				//seq_id_set.splice(seq_id_set.indexOf(row.OFFICE_ID),1);
			},
			//当全选时将所有主键值加入数组seq_id_set
			onCheckAll: function(rows){
				/*var rows=$('#table_user_land').datagrid("getRows");
				for (var i=0;i<rows.length;i){
					$('#table_user_land').datagrid("deleteRow",$('#table_user_land').datagrid("getRowIndex",rows[0]));
					$('#table_user_select').datagrid("appendRow",rows[0]);
				}*/
			},
			//当取消全选时将所有主键值从数组seq_id_set中删除
			onUncheckAll: function(rows){
				for (var i=0;i<rows.length;i++){
					for (var i=0;i<rows.length;i++){
						//selectRow(index,row[i]);
					}
				}
			},

		};
		lbhdataGrid = $('#table_user_land').datagrid(dataGridData);
	}
	
	
	
	// 选择表格中某一行的数据。
	function getSelected(func) {
		var selectedrow = $('#table_user').datagrid('getSelected');

		if (selectedrow) {
			row = selectedrow;
			// 调用相关函数
			func.call(this, selectedrow);
		} else {

			$.messager.alert('提示：', '请点击选中表格中的某一行.');
		}
	}
	//查询房屋信息
	function searchStr(){
//		if($("#selecttype").val()=="0")
//		{
		    $('#table_user_house').datagrid('load',{'parcelcode':$("#parcelcode").val(),'houselocation':$("#houselocation").val(),'proname':$("#proname").val(),'housecode':$("#housecode").val()});
//		}
//		else
//		{
//			$('#table_user1').datagrid('load',{'parcelcode':$("#parcelcode1").val(),'location':$("#location").val()});
//		}
	} 


	

	






