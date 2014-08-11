//init 入口函数
function init(){
	alert('tests');
}
var proc;
var proc_id;
var rec_type_flag ;
var houseDataGrid;
var userDataGrid;
var jjclDataGrid;
var lastIndex;  
var products = [  
                {productid:'025001',name:'原件'},  
                {productid:'025002',name:'复印件'}, 
             ];  
 function productFormatter(value){  
 	 var temp = "原件";
 	 if(value=='025001'){
 		 
 	 }if(value=='025002'){
 		 temp = "复印件";
 	 }
 	 return temp;
 }  
 var cllx = [  
     {productid:'024002',name:'选接件'},  
     {productid:'024001',name:'必接件'}, 
 ];  
  function cllxFormatter(value){  
 	 var temp = "必接件";
 	 if(value=='024001'){
 		 
 	 }if(value=='024002'){
 		 temp = "选接件";
     }
     return temp;  
  } 


//页面开始加载函数
$(function(){
	//定义房地产开始
	proc_id = '1000026187';
	rec_type_flag = '0';
	houseDataGrid = $('#table_house').datagrid({
		title:'登记单元',
		height:161,
		// 表格每列宽度自动适应表格总宽度
		autoRowHeight : true,
		fitColumns : true,
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
		     		{
		     			title : '登记单元类型',
		     			field : 'REG_UNIT_TYPE',formatter :dicts.format.reg_unit_type_format,
		     			width:140
		     		}, {
		     			title : '登记单元编号',
		     			field : 'REG_UNIT_CODE',
		     			width:140
		     			
		     		}, {
		     			title : '登记单元名称',
		     			field : 'REG_UNIT_NAME',
		     			width:140
		     			
		     		}
		     		] ],
		     		// 表头，添加工具栏。
		     		toolbar:'#optbtnI',
		     		onClickRow : function() {
		     		},
		     		onLoadSuccess : function() {
		     			
		     			
		     		}
	});//定义房地产结束
	
	//创建申请人信息表
	 userDataGrid = $('#table_user').datagrid({
		title:'申请人',
		height:171,
		// 表格数据来源
		url :ctx+"/houseownership/initialreg!getAppMessage.action?time="+new Date()+"&proc_id="+proc_id,
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
		columns : [ [{
	           title : '操 作',  
	           field : 'button',
	           formatter : function(value,rec)
	           {return '<span><input type="button" value="详 情" onclick=""/></span>';},
        },{
			title : '申请人类别',
			field : 'app_name'
			
		},{
			title : '申请人',
			field : 'app_name'
			
		}, {
			title : '申请人类型',
			field : 'app_type',formatter : dicts.format.app_type_format
			
		}, {
			title : '身份证号码/组织机构代码',
			field : 'app_cer_no'
			
		}, {
			title : '份额',
			field : 'app_port'
			
		}, {
			title : '地址',
			field : 'app_address'
			
		}, {
			title : '联系电话',
			field : 'app_tel'
		}, {
			title : '邮编',
			field : 'app_address'
			
		}, {
			title : '法定代表人',
			field : 'legal_name'
		
		}, {
			title : '法定代表人身份证号码',
			field : 'app_cer_no'
		
		},  {
			title : '代理人',
			field : 'agent_name'
			
		},{
			title : '代理人身份证号',
			field : 'agent_cer'
			
		}, {
			title : '电  话',
			field : 'agent_tel'
			
		}] ],
		// 表头，添加工具栏。
		toolbar:'#optbtnII',
		onClickRow : function() {
			//点击列时激活“编辑”、“删除”按钮 只在受理和初审环节激活
			/*if(proc_node==state1.string0){
				$('#user_edit').linkbutton('enable');
				$('#user_delete').linkbutton('enable');
			}*/
		},
		onClickCell:function(rowIndex, field, value){
			if(field=="button"){
			    $('#table_user').datagrid('selectRow',rowIndex);
			    appDetail(this);
			}
		},
		onLoadSuccess : function(data) {
			//加载完毕禁用“编辑”、“删除”按钮
			/*$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');*/
			
			//只有在受理环节中才对权利人变更进行记录  其它环节不允许权利人变更
		}

	});//创建申请人结束
	
	var jjcl =	{
        	title:'接件材料表',
        	url:ctx+'/common/recmaterial!getRecmaterial.action?rec_type_flag='+rec_type_flag+'&time='+new Date()+"&proc_id="+proc_id,
        	height:321,
        	fitColumns: true,
    		//去掉边框
    		border : true,
    		checkbox:'CK',
    		//是否有翻页栏
    		pagination:false,
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
    		// 是否在点选表中一行时同时选中复选框
    		checkOnSelect:false,
    		// 是否在选中复选框时同时点选表中一行
    		selectOnCheck:false,
    		// 列属性设置
    		columns:[[
    					//每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
    					{field:'RE_NAME', title:'接件材料名称', width:100, sortable:true,editor:'text'},	
    					{field:'RE_STYLE', title:'材料种类', width:50,formatter:productFormatter,editor:{    
    						type:'combobox',
    						options:{
    							 valueField:'productid',  
    							 textField:'name',  
    							 data:products,  
    							 required:true 
    						}
    					}},						
    					{field:'RE_TYPE',	title:'材料类型', width:50,formatter:cllxFormatter,editor:{
    						type:'combobox',
    						options:{
    							 valueField:'productid',  
    							 textField:'name',  
    							 data:cllx,  
    							 required:true 
    						}
    					}},
    						{field:'RE_PAGE',	title:'页数', width:50,editor:'text'},
    						{field:'RE_COPY',	title:'份数', width:50,editor:'text'},	
    				    {title:'收到',  field:'ck' ,checkbox:true},
    				    { hidden:'true',field : 'checked' },
    					{field:'REC_TYPE_FLAG',hidden:true}
    		]],
    		toolbar:'#optbtnIII',
            onBeforeLoad:function(){  
               $(this).datagrid('rejectChanges');  
           },  
           onClickRow:function(rowIndex){  
        	   /*if(procNode == "受理"){
        		   if (lastIndex != rowIndex){  
                       $('#table_seematerials').datagrid('endEdit', lastIndex);  
                       $('#table_seematerials').datagrid('beginEdit', rowIndex);  
                   }  
                  lastIndex = rowIndex;  
        	   }else{
        		  
        	   }*/
            },onLoadSuccess:function(data){
            	/*if(procNode == "受理"){
            		
            	}else{
            		
            		$('#rec_add').linkbutton('disable');
            		$('#rec_del').linkbutton('disable');
            		$('#rec_save').linkbutton('disable');
            	}*/
            },
    		onSelect:function(rowIndex,rowData){
    	    	rowData.checked = 'true';
    	    },
    	    onUnselect:function(rowIndex,rowData){
    	    	rowData.checked = '';
    	    },
    	    onSelectAll:function(rows){
    	    	$.each(rows,function(indx,item){
    	    		item.checked = 'true';
    	    	});
    	    },
    	    onUnselectAll:function(rows){
    	    	$.each(rows,function(indx,item){
    	    		item.checked = '';
    	    	});
    	    }
          };  //定义jjcl的对象
	  
	         jjclDataGrid = $('#table_seematerials').datagrid(jjcl);		//生成接件材料datagrid\
	         
	         //得到预登记mess
	         getPreRegMess();
});

// 选择登记单元
function doSelectRegunit() {
	 openInTopWindow({
		 // 窗口元素的id
		 id : 'add_regunit_win',
		 // 窗口iframe的src
		 src : ctx+'/jsp/common/preaudit.jsp?time='+new Date(),
		 // 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
		 destroy : true,
		 // 窗口标题
		 title : '选择登记单元',
		 // 窗口宽
		 width : 840,
		 // 窗口高
		 height : 620,
		 modal : true,
		 // 窗口中iframe的window对象的onLoad回调函数设置
		 onLoad : function() {
			 // 此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
			 // 因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
			 this.openerWindow = window;
			 // 将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
			 this.args = {
					 houseDataGrid : houseDataGrid
			 };
			// this.init(proc);
			 this.init();
		 }
	 });
};

// 新增
function doUserAdd() {
	openInTopWindow({
		// 窗口元素的id
		id : 'add_user_win',
		// 窗口iframe的src
		src : ctx+'/jsp/common/applicant/addapplicant.jsp?time='+new Date(),
		// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
		destroy : true,
		// 窗口标题
		title : '新增申请人',
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
				userDataGrid : userDataGrid
			};
			this.init(proc_id);
		}
	});
};
// 删除
function doUserDelete() {
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

};
//删除行           
function doMaterialDel(){
	var row = $('#table_seematerials').datagrid('getSelected');  
	if (row){
		top.$.messager.confirm('提示','确定要删除这一行吗？',function(r){
			if(r){
				var index = $('#table_seematerials').datagrid('getRowIndex', row);  
				$('#table_seematerials').datagrid('deleteRow', index);     
			}
		});
	}else{   
		top.$.messager.confirm('提示','请选择要删除的行！！');             	
	}  
};         
function doMaterialAdd(){
	$('#table_seematerials').datagrid('endEdit', lastIndex);  
	$('#table_seematerials').datagrid('appendRow',{  
		RE_NAME:autoAdd(),  
		RE_STYLE:'025001',  
		RE_TYPE:'024001',
		RE_PAGE:'1',
		RE_COPY:'1',
		RE_PERSON:user,  
		RE_DATE:getTime(),
		REC_TYPE_FLAG:rec_type_flag
	});  
	lastIndex = $('#table_seematerials').datagrid('getRows').length-1;  
	$('#table_seematerials').datagrid('selectRow', lastIndex);  
	$('#table_seematerials').datagrid('beginEdit', lastIndex); 
};   

//获取从受理前置窗口传递的登记信息
function getPreRegMess(){
	$.ajax({
		    dataType: 'json',
		    url:ctx+"/mortgage/morsetup!getRegInfo.action?time="+new Date()+"&proc_id="+proc_id,
			success:function(data){
			 	if(data){
			 		//alert(JSON.stringify(data));
			 		$("#REG_CODE").val(data.REG_CODE);
			 		$("#REG_STATION").combodict("setValue",data.REG_STATION);
			 		$("#BUS_DETAIL").val(data.PROC_NAME);
			 		$("#REG_TYPE").combodict("setValue",data.REG_TYPE);
			 		proc = data;
			 		/*if(data.REG_TYPE==enumdata.JUDREMARK||data.REG_TYPE==enumdata.UNREMARK)
			 		{
			 			$(".remark_tab").attr("disabled", "disabled");
			 			getRemark($("#REG_CODE").val());
			 		}*/
			 	}
				}
		});
	
}
/**********************************************************************************
*函数名称: autoAdd()
*功能说明: 序号自增方法  在新增当前接伯材料时序号自增
*参数说明: 
*返 回 值: '新建材料'+i;
*函数作者: Joyon
*创建日期: 2014-03-01s
*修改历史: 
***********************************************************************************/	
var i = 0;
function autoAdd(){
	i++;
	return '新建材料'+i;
}
/**********************************************************************************
*函数名称: doSave()
*功能说明: 保存修改后接件材料的数据
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function doSave(){
		//编辑行时点击保存  会取不到数据  解决
		endEdit();
		
		var inserted = jjclDataGrid.datagrid('getChanges', 'inserted');
		var deleted = jjclDataGrid.datagrid('getChanges', 'deleted');
		var updated = jjclDataGrid.datagrid('getChanges', 'updated');
		 var  rows =  $('#table_rec').datagrid('getData'); 
		//请求后台保存数据
		$.ajax({
			url : ctx+'/common/recmaterial!correctionMaterialApplyEdit.action?proc_id='+proc_id+"&time="+new Date()+"&rec_type_flag="+rec_type_flag,
			dataType : 'json',
			type : 'post',
			data : {
				//将数据拼装成JSON字符串传递到后台
				datas :$.toJSON({
					inserted : inserted,
					deleted : deleted,
					updated : updated,
					rows:rows.rows
				})
			},
			success : function(data) {
				if (data.success) {
					top.$.messager.alert('提示', '数据保存成功！', 'info', function(){
						//将表格置为不可编缉
						itemCanEdit = false;
						//撤销时启用“编辑按钮”
						$('#dictitem_edit').linkbutton('enable');
						//撤销时重新加载表格数据
						jjclDataGrid.datagrid('reload');
					});
				}
			}
		});
} 

function appDetail(){
	var row = $('#table_user').datagrid('getSelected');
	openInTopWindow({
		// 窗口元素的id
		id : 'edit_app_win',
		// 窗口iframe的src
		src : ctx+'/jsp/common/applicant/editApplicant.jsp',
		// 关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
		destroy : true,
		// 窗口标题
		title : '查看申请人信息',
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
/**********************************************************************************
*函数名称: getTime(func)
*功能说明: 获取系统当前时间 
*参数说明: 
*返 回 值: 当前时间字串
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/	
function getTime(){
 	  var myDate = new Date();
 	  var year = myDate.getFullYear();
 	  var month = add_zero(myDate.getMonth()+1);
 	  var date = add_zero(myDate.getDate()); 
      var mytime=myDate.getTime();     //获取当前时间	
  var hour = add_zero(myDate.getHours());       //获取当前小时数(0-23)
  var min = add_zero(myDate.getMinutes());     //获取当前分钟数(0-59)
  var sec = add_zero(myDate.getSeconds());   
  if(sec<10){
	  
  }
  var time = year+"-"+month+"-"+date+" "+hour+":"+min+":"+sec;
 	  return time;
}
/**********************************************************************************
*函数名称: add_zero()
*功能说明: 给当前时间函数添加0
*参数说明: 
*返 回 值: 加0后的字符串  只在当前时间函数中使用
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/	
function add_zero(temp)
{
  if(temp<10) 
	  return "0"+temp;
  else 
	  return temp;
}
/**********************************************************************************
*函数名称: endEdit()
*功能说明: 结束表格编辑状态  
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function endEdit(){
	var rows = jjclDataGrid.datagrid('getRows');
	for ( var i = 0; i < rows.length; i++) {
		jjclDataGrid.datagrid('endEdit', i);
	}
}
/**********************************************************************************
*函数名称: endEditing()
*功能说明: 结束表格编辑状态  
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){return true}
        if ($('#table_rec').datagrid('validateRow', editIndex)){
        	var name = $('#table_rec').datagrid('getEditor', {index:editIndex,field:'Name'});
            $('#table_rec').datagrid('endEdit', editIndex);
            editIndex = undefined;
        return true;
        } else {
            return false;
        }
}
function comfirmsave(){
	var url = "../print!printAcceptanceNotice.action?proc_id=1000026187"+'&time='+new Date();
	openTabII("打印",url)
	//window.frames["printframe"].location=url;
}

/**********************************************************************************
*函数名称: openTabII
*功能说明: 增加打开选项卡II
*函数作者: Sam
*创建日期: 2014年8月8日 
*修改历史: 
***********************************************************************************/
function openTabII(title,url){
	var content = '<iframe id="printframe" name="printframe" scrolling="yes" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';  
    $('#tabsII').tabs('add',{  
        title:title,  
        content:content,  
        closable:true  
    });  
}
/**********************************************************************************
*函数名称: openTabI
*功能说明: 增加打开选项卡I
*函数作者: Sam
*创建日期: 2014年8月8日 
*修改历史: 
***********************************************************************************/
function openTabI(title,url){
	var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';  
	$('#tabsI').tabs('add',{  
		title:title,  
		content:content,  
		closable:true  
	});  
}