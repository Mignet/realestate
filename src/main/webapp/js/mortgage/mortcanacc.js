/*********************************************************************************
*文  件  名  称: mortcanacc.js
*功  能  描  述: 注销抵押权登记申请表js
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: panda
*创  建  日  期： 2014-02-28
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
var proc=this.parent.proc;
var proc_node = proc.activName;
var proc_id = proc.procId;
//var proc_id = 4;//1000016366;
//var proc_node = "初审";
var state1 = {
		string0: "受理",
		string1 : "初审",
		string2 : "复审",
		string3 : "审核",
		string4 : "核准",
		string5 : "初步审定",
		string6 : "公告审定",
		string7: "公告结果初审",
		string8 : "公告结果复审",		
		string9 : "缮证",
		string10 : "发证",
		string11: "归档",
		string12: "公告",
		string13:"拟定公告"
	};
var userDataGrid;
var mortgager;
var houseDataGrid;
/**********************************************************************************
*函数名称: 
*功能说明: 页面初始化
*函数作者: panda
*创建日期: 2014-02-28
*修改历史: 
***********************************************************************************/
$(function() {
	//设置注销登记日期
	var zxrq = $("#CAN_MORT_DATE").val();
	if( $.trim(zxrq).length==0){
		$("#CAN_MORT_DATE").val(getTime());
	}
	houseDataGrid = $('#table_house').datagrid({
		title:'房地产信息',
		height:240,
		url:ctx+"/mortgage/morsetup!getRegunitMess.action?time="+new Date()+"&proc_id="+proc_id,
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
		checkbox:'ck',
		// 是否在点选表中一行时同时选中复选框
		 //checkOnSelect:false,
		// 是否在选中复选框时同时点选表中一行
		 selectOnCheck:false,
		columns : [ [
		     		// 每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。

		     		 {field:'ck',checkbox:true},
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
		     			
		     		}, {
		     			hidden: true,
		     			field : 'TYPE',
		     			width:100
		     			
		     		}, {
		     			hidden: true,
		     			field : 'CODE',
		     			width:100
		     			
		     		},
		     		{
		     			title : '操作',		     		
		     			field:'button',
		     			formatter:function(value,rec){
		     				
		     				//return '<span><input class="search"  onclick=""/></span>';
		     				 var btn = '<a class="editcls" onclick="" href="javascript:void(0)">查看</a>';  
		                     return btn;		
		     			}
		     		}
		     		] ],
		     		// 表头，添加工具栏。
		     		onClickRow : function() {
		     		},
		     		onClickCell:function(rowIndex, field, value){
		     			if(field=="button"){
		    				$('#table_house').datagrid('selectRow',rowIndex);
		    				dowatch(this);
		    			}
		    		},onCheck:function(rowIndex,data){
		    			//alert(JSON.stringify(data));
		    		},
		     		onLoadSuccess : function(data) {
		     			 $('.editcls').linkbutton({text:'查看'});
		     		}
	});
    	mortgager = $('#table_transferor').datagrid({
		title:'抵押人',
		height:200,
		// 表格数据来源
		url :ctx+"/mortgage/morsetup!getMortCancelMortgager.action?time="+new Date()+"&proc_id="+proc_id+"&apptype=063003",
		// 表格每列宽度自动适应表格总宽度
		autoRowHeight : true,
		fitColumns : false,
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
		     		{
		            	hidden:  true,
		               	field : 'HOL_REL'
		                   },
		                   {
				            hidden:  true,
				            field : 'REG_UNIT_CODE'
				           },
		                   {
		           			hidden:  true,
		           			field : 'APPLICANT_ID'
		           		}, 
		      		{
		     			title : '申请人',
		     			field : 'APP_NAME'
		     			
		     		}, {
		     			title : '申请人类型',
		     			field : 'APP_TYPE',
		     			formatter :dicts.format.app_type_format
		     			
		     		}, {
		     			title : '证件类型',
		     			field : 'APP_CER_TYPE',
		     			formatter : dicts.format.app_cer_type_format
		     			
		     		}, {
		     			title : '证件编号',
		     			field : 'APP_CER_NO'
		     			
		     		}, {
		     			title : '份额',
		     			field : 'APP_PORT'
		     			
		     		}, {
		     			title : '地址',
		     			field : 'APP_ADDRESS'
		     			
		     		}, {
		     			title : '法定代表人',
		     			field : 'LEGAL_NAME'
		     		
		     		},  {
		     			title : '代理人',
		     			field : 'AGENT_NAME'
		     			
		     		}, 
		     		{
		     			title : '代理人证件类型',
		     			field : 'AGENT_CER_TYPE',
		     			formatter : dicts.format.app_cer_type_format
		     			
		     		},
		     		
		     		
		     		{
		     			title : '代理人证件号码',
		     			field : 'AGENT_CER'
		     			
		     		}, {
		     			title : '代理人联系电话',
		     			field : 'AGENT_TEL '
		     			
		     		}

		     		] ],
		     	// 表头，添加工具栏。
		    		toolbar : [ {
		    			id : 'mortgager_edit',
		    			text : '编辑',
		    			iconCls : 'icon-pencil',
		    			disabled : true,
		    			handler : mortgagerEdit
		    		}, '-', {
		    			id : 'mortgager_delete',
		    			text : '删除',
		    			iconCls : 'icon-remove',
		    			disabled : true,
		    			handler : mortgagerDel
		    		}],
		     		// 表头，添加工具栏。
		     		onClickRow : function() {
		     			$('#mortgager_edit').linkbutton('enable');
	     				$('#mortgager_delete').linkbutton('enable');
		     		},
		     		onLoadSuccess : function() {
		     			
		     			
		     		}
	});
   /**********************************************************************************
	*函数名称: mortgagerEdit
	*功能说明: 编辑抵押人
	*参数说明: 无
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/
	function mortgagerEdit() {
		var row = mortgager.datagrid('getSelected');
    // alert(JSON.stringify(row));
		openInTopWindow({
			// 窗口元素的id
			id : 'edit_user_win',
			// 窗口iframe的src
			src : ctx+'/jsp/common/applicant/editapplicant.jsp',
			// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy : true,
			// 窗口标题
			title : '编辑申请人',
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
					userDataGrid : mortgager
				};
				this.init(row);
			}
		});
	};

	/**********************************************************************************
	*函数名称: mortgagerDel
	*功能说明: 删除抵押人
	*参数说明: 无
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/
	function mortgagerDel() {
		var row = mortgager.datagrid('getSelected');
		top.$.messager.confirm('确认', '确定要删除申请人名称为[' + row.APP_NAME + ']？', function(
				result) {
			if (result) {
				$.ajax({
					url : ctx+"/houseownership/initialreg!deleteApplicant.action?time="+new Date(),
					type : 'post',
					data : {
						applicant_id : row.APPLICANT_ID
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							top.$.messager.alert('提示', data.tipMessage, 'info',
									function() {
								        //alert("删除之后刷新");
								     mortgager.datagrid('reload');
									});
						} else {
							top.$.messager.alert('提示', data.errorMessage, 'error');
						}
					}
				});
			}
		});
	}
	
	
	
	
	

	//创建申请人信息表
	 userDataGrid = $('#table_user').datagrid({
		//fit : true,
		title:'抵押权人',
		height:200,
		// 表格数据来源
		url :ctx+"/mortgage/morsetup!getChangeMortgagee.action?time="+new Date()+"&proc_id="+proc_id+"&apptype=063004",
		// 表格每列宽度自动适应表格总宽度
		autoRowHeight : true,
		fitColumns : false,
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
		// 是否在点选表中一行时同时选中复选框
		// checkOnSelect:true,
		// 是否在选中复选框时同时点选表中一行
		// selectOnCheck:true,
		// 列属性设置
		columns : [ [
		{
		            	hidden:  true,
		               	field : 'HOL_REL'
			
		                   },
		                   {
				            hidden:  true,
				            field : 'REG_UNIT_CODE'
					
				           },
		                   {
		           			hidden:  true,
		           			field : 'APPLICANT_ID'
		           			
		           		}, 
		      		{
		     			title : '申请人',
		     			field : 'APP_NAME'
		     			
		     		}, {
		     			title : '申请人类型',
		     			field : 'APP_TYPE',
		     			formatter : function(value) {
		    				if(value == '043001'){
		    					
		    					return '个人';
		    				};
		    				if(value == '043002'){
		    					
		    					return '单位';
		    				}
		    		
		    			}
		     			
		     		}, {
		     			title : '证件类型',
		     			field : 'APP_CER_TYPE',
		     			formatter : function(value) {
		    				if(value == '002001'){
		    					
		    					return '身份证';
		    				};
		    				if(value == '002002'){
		    					
		    					return '军官证';
		    				}
		    		
		    			}
		     			
		     		}, {
		     			title : '证件编号',
		     			field : 'APP_CER_NO'
		     			
		     		}, {
		     			title : '份额',
		     			field : 'APP_PORT'
		     			
		     		}, {
		     			title : '地址',
		     			field : 'APP_ADDRESS'
		     			
		     		}, {
		     			title : '法定代表人',
		     			field : 'LEGAL_NAME'
		     		
		     		},  {
		     			title : '代理人',
		     			field : 'AGENT_NAME'
		     			
		     		}, 
		     		{
		     			title : '代理人证件类型',
		     			field : 'AGENT_CER_TYPE',
		     			formatter : function(value) {
		    				if(value == '002001'){
		    					
		    					return '身份证';
		    				};
		    				if(value == '002002'){
		    					
		    					return '军官证';
		    				}
		    		
		    			}
		     			
		     		},
		     		
		     		
		     		{
		     			title : '代理人证件号码',
		     			field : 'AGENT_CER'
		     			
		     		}, {
		     			title : '代理人联系电话',
		     			field : 'AGENT_TEL '
		     			
		     		}

		] ],
		// 表头，添加工具栏。
		toolbar : [ {
			id : 'user_edit',
			text : '编辑',
			iconCls : 'icon-pencil',
			disabled : true,
			handler : mortgagerEdit
		}, '-', {
			id : 'user_delete',
			text : '删除',
			iconCls : 'icon-remove',
			disabled : true,
			handler : mortgagerDel
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
	
	 getPreRegMess();
	

	
	
});
/**********************************************************************************
*函数名称: dowatch
*功能说明: 查看登记单元详细信息
*参数说明: 无
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
	function dowatch(button){
		var row = $('#table_house').datagrid('getSelected');
		openInTopWindow({
			// 窗口元素的id
			id : 'add_user_win',
			// 窗口iframe的src
			//src : ctx+'/common/applicant/addapplicant.action?time='+new Date(),
			// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy : true,
			// 窗口标题
			title : '房地产信息',
			// 窗口宽
			width : 800,
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
					userDataGrid : userDataGrid
				};
				this.init(proc_id);
			}
		});
	}
	/**********************************************************************************
	*函数名称: doEdit
	*功能说明: 编辑抵押权人信息
	*参数说明: 无
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/
	function doEdit() {
		var row = userDataGrid.datagrid('getSelected');

		openInTopWindow({
			// 窗口元素的id
			id : 'edit_user_win',
			// 窗口iframe的src
			src : ctx+'/jsp/common/applicant/editapplicant.jsp',
			// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
			destroy : true,
			// 窗口标题
			title : '编辑申请人',
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
	};
	/**********************************************************************************
	*函数名称: doDelete
	*功能说明: 删除抵押权人信息
	*参数说明: 无
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/
	function doDelete() {
		var row = userDataGrid.datagrid('getSelected');
		top.$.messager.confirm('确认', '确定要删除申请人名称为[' + row.app_name + ']？', function(
				result) {
			if (result) {
				$.ajax({
					url : ctx+"/houseownership/initialreg!deleteApplicant.action?time="+new Date(),
					type : 'post',
					data : {
						applicant_id : row.applicant_id
					},
					dataType : 'json',
					success : function(data) {
						if (data.success) {
							top.$.messager.alert('提示', data.tipMessage, 'info',
									function() {
								        //alert("删除之后刷新");
										userDataGrid.datagrid('reload');
									});
						} else {
							top.$.messager.alert('提示', data.errorMessage, 'error');
						}
					}
				});
			}
		});
	}

	/**********************************************************************************
	*函数名称: submit
	*功能说明: 表单提交方法
	*参数说明: 无
	*返 回 值: 无
	*函数作者: panda
	*创建日期: 2014-03-01
	*修改历史: 
	***********************************************************************************/
function submit(){
	if(proc_node == state1.string0){
		saveRegInfo();		
	}	
}
/**********************************************************************************
*函数名称: saveRegInfo
*功能说明:  登记信息保存更新操作
*参数说明: 无
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function saveRegInfo() {
	//验证抵押人信息、抵押权人信息、注销原因是否为空
	  if(!checkMortgagee()){
		  return;
	  }
	  var CAN_MORT_DATE = $("#CAN_MORT_DATE").val();
	  var CAN_MORT_REASON = $("#CAN_MORT_REASON").val();
	  $.ajax({
	   		dataType:'json',
	   		url:ctx+"/mortgage/morsetup!saveMortMess.action?time="+new Date()+"&proc_id="+proc_id,
	   		contentType:"application/x-www-form-urlencoded; charset=GBK",
	   		//表单的序列化操作
	   		data:{"mort.can_mort_date":CAN_MORT_DATE,"mort.can_mort_reason":CAN_MORT_REASON},
	   		success:function(data){
			 	if(data){
			 		//alert(data);
			 		top.$.messager.alert('保存成功提示',"保存成功",'info',function(){
			 			getPreRegMess();
					});	
			 	}else {
					top.$.messager.alert('保存失败提示',"保存失败",'error');
				}
	   		}
	   	});  
	

}


/**********************************************************************************
*函数名称: getPreRegMess
*功能说明: 获取从受理前置窗口传递的登记信息
*参数说明: 无
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getPreRegMess(){
	$.ajax({
		    dataType: 'json',
			url:ctx+"/mortgage/morsetup!getMortcanMess.action?time="+new Date()+"&proc_id="+proc_id,
			success:function(data){
			 	if(data){
			 		//alert(JSON.stringify(data));
			 		$("#REG_CODE").val(data.REG_CODE);
			 		$("#REG_STATION").combodict("setValue",data.REG_STATION);
			 		$("#BUS_DETAIL").val(data.PROC_NAME);
			 		$("#REG_TYPE").combodict("setValue",data.REG_TYPE);
			 		if(data.CAN_MORT_REASON){
			 		$("#CAN_MORT_REASON").val(data.CAN_MORT_REASON);	
			 			
			 		}
			 		if(data.CAN_MORT_DATE){
			 			
			 			var qsrq = data.CAN_MORT_DATE;
			 			$("#CAN_MORT_DATE").val(qsrq);//qsrq.substr(0,qsrq.length-11));	
			 			
			 		}
			 	}
				}
		});
	
}
/**********************************************************************************
*函数名称: getTime
*功能说明: 获取当前系统时间
*参数说明: 无
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function getTime(){
	  var myDate = new Date();
	  var year = myDate.getFullYear();
	  var month = myDate.getMonth()+1;
	  var date = myDate.getDate(); 
     var mytime=myDate.toLocaleTimeString();     //获取当前时间	  
	var time = year+"-"+month+"-"+date+" "+mytime;
	return time;
	
}
/**********************************************************************************
*函数名称: checkMortgagee
*功能说明: 检查抵押权人是否已存在数据
*参数说明: 
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function checkMortgagee(){
	
	var rowlen  = userDataGrid.datagrid('getRows').length;
	 var  mortrowlen = mortgager.datagrid('getRows').length;
	if(rowlen == 0){
		top.$.messager.alert('提示', '抵押权人信息为空！', 'info',
				function() {
					
				});	
		return false;		
		
	}
	if(mortrowlen == 0){
		top.$.messager.alert('提示', '抵押人信息为空！', 'info',
				function() {
					
				});	
		return false;
		
		
	}
	var CAN_MORT_REASON = $("#CAN_MORT_REASON").val();
	if($.trim(CAN_MORT_REASON).length==0){
		top.$.messager.alert('提示', '请录入注销原因！', 'info',
				function(){
					
				});	
		return false;

	}
	return true;
}
/**********************************************************************************
*函数名称: validate
*功能说明: 注销抵押登记信息格式校验
*参数说明: 
*返 回 值: 无
*函数作者: panda
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function validate(){
	//校验抵押申请人和抵押权人是否为空
	if(!checkMortgagee()){
		return;
	};
	//注销原因非空校验
	var can_reason = new tt.Field("注销原因","CAN_MORT_REASON"); 
	tt.vf.req.add(can_reason);
	
	return tt.validate();
	
}










