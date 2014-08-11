/*********************************************************************************
*文  件  名  称: correction-notice.js
*功  能  描  述: 接件材料js
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Joyon
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/
var proc=this.parent.proc;
//var proc={
//		procId:"1000017642",
//		activName:"补正材料申请"
//};
var proc_id = proc.procId;
var itemCanEdit = false;
var lastEditIndex = -1;
var procNode = proc.activName;
var proc_node = proc.activName;
var rec_type_flag = enumdata.REC_TYPE_FLAG.CORRECTION;//材料类型   0代表接件材料   1发文材料清单  2需要补证的材料 
var state1 = {
		string0: "补正材料申请",
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

var i = 0;
var jjclDataGrid; 
//用来返回接件材料类型 和接件材料各类 字典项
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
 
 /**********************************************************************************
 *函数名称: 
 *功能说明: 设置页面状态
 *参数说明: 
 *返 回 值: 
 *函数作者: Joyon
 *创建日期: 2014年5月28日 19:32:23
 *修改历史: 
 ***********************************************************************************/  
 function setState(){
	 if(proc_node!="补正材料申请"){
		 $("input").attr("disabled","disabled");
	 }
 }
/**********************************************************************************
*函数名称: 
*功能说明: js加载时运行  
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/  
$(function(){  
	//回填补正原因到页面
	getMaterialreplenition();
               var lastIndex;  
            	var jjcl =	{
            	title:'需补正材料列表',
            	url:ctx+'/common/recmaterial!getCorrectionMaterialInfo.action?rec_type_flag='+rec_type_flag+'&time='+new Date()+"&proc_id="+proc_id,
            	width:600,
            	height:240,
            	fitColumns: true,
        		//去掉边框
        		border : true,
        		//是否有翻页栏
        		pagination:true,
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
        		columns:[[
        					//每列的属性，field：行对象的列属性名；title：列标题；width：列宽；sortable：是否可排序。
        					{field:'RE_NAME', title:'材料名称', width:100, sortable:true,editor:'text'},	
        					{field:'RE_STYLE',hidden:true, title:'材料种类', width:50,formatter:productFormatter,editor:{    
        						type:'combobox',
        						options:{
        							 valueField:'productid',  
        							 textField:'name',  
        							 data:products,  
        							 required:true 
        						}
        					}},						
        					{field:'RE_TYPE',hidden:true,	title:'材料类型', width:50,formatter:cllxFormatter,editor:{
        						type:'combobox',
        						options:{
        							 valueField:'productid',  
        							 textField:'name',  
        							 data:cllx,  
        							 required:true 
        						}
        					}},
        						{field:'RE_PAGE',	title:'页数', width:50,editor:'text',hidden:true},
        						{field:'RE_COPY',	title:'份数', width:50,editor:'text'},	
        					{field:'RE_PERSON',	title:'录入人', width:50,hidden:true},
        					{field:'RE_DATE',	title:'录入日期', width:80,hidden:true},
        					{field:'REC_TYPE_FLAG',hidden:true}
        				]],
                toolbar:[{  
                	id:"rec_add",
                   text:'新建',  
                    iconCls:'icon-add',  
                   handler:function(){  
                       $('#table_rec').datagrid('endEdit', lastIndex);  
                        $('#table_rec').datagrid('appendRow',{  
                        	RE_NAME:autoAdd(),  
                            RE_STYLE:'025001',  
                            RE_TYPE:'024001',
                            RE_PAGE:'1',
                            RE_COPY:'1',
                            RE_PERSON:user,  
                            RE_DATE:getTime(),
                            REC_TYPE_FLAG:rec_type_flag
                        });  
                       lastIndex = $('#table_rec').datagrid('getRows').length-1;  
                        $('#table_rec').datagrid('selectRow', lastIndex);  
                        $('#table_rec').datagrid('beginEdit', lastIndex);  
                    }  
               },'-',{  
            	   id:"rec_del",
                   text:'删除',  
                    iconCls:'icon-remove',  
                    handler:doDel 
               },'-',{  
            	    id:"rec_save",
                    text:'保存',  
                    iconCls:'icon-save',  
                   handler:doSave 
               }],  
                onBeforeLoad:function(){  
                   $(this).datagrid('rejectChanges');  
               },  
               onClickRow:function(rowIndex){  
            	   if(procNode == state1.string0){
            		   if (lastIndex != rowIndex){  
                           $('#table_rec').datagrid('endEdit', lastIndex);  
                           $('#table_rec').datagrid('beginEdit', rowIndex);  
                       }  
                      lastIndex = rowIndex;  
            	   }else{
            		  
            	   }
                },onClickCell:function(rowIndex, field, value){
					//alert("onclickcell event"+rowIndex+field+value);
                	if(procNode == state1.string0 || procNode == state1.string1){
						if(field=="butable_recon"){
							$('#table_rec').datagrid('selectRow',rowIndex);
							recmaterial_rel(this);
						}
                	}
				},onLoadSuccess:function(data){
                	if(procNode == "受理"){
                		
                	}else{
                		
                		//$('#rec_add').linkbutton('disable');
                		//$('#rec_del').linkbutton('disable');
                		//$('#rec_save').linkbutton('disable');
                	}
                }
              };  //定义jjcl的对象
			 //删除行           
			 function doDel(){
			            	 var row = $('#table_rec').datagrid('getSelected');  
			                 if (row){
			                 	top.$.messager.confirm('提示','确定要删除这一行吗？',function(r){
			                 		if(r){
				                 		  var index = $('#table_rec').datagrid('getRowIndex', row);  
				                          $('#table_rec').datagrid('deleteRow', index);     
			                 		}
			                 	});
			                 }else{   
			                 	top.$.messager.confirm('提示','请选择要删除的行！！');             	
			                 }  
			         };         
         jjclDataGrid = $('#table_rec').datagrid(jjcl);		//生成接件材料datagrid
});  
//初始化结束

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
*函数名称: autoAdd()
*功能说明: 序号自增方法  在新增当前接伯材料时序号自增
*参数说明: 
*返 回 值: '新建材料'+i;
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/	
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

/**********************************************************************************
*函数名称: dataBack()
*功能说明: 回调数据动态刷新
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function dataBack(){
	 $.ajax({
			url:'jjclDelegate/getUserList.run?lcslbid='+lcslbid,
		    dataType: 'json',
		    type :'post',
			data:{
				djlx_id:jjclDataGrid.djlx_id,
			},
			success:function(data){
				jjclDataGrid.datagrid('load',data);
				alert("回调数据成功");
			}
		});
}
/**********************************************************************************
*函数名称: dataBack()
*功能说明: 接件材料附件按扭事件  弹出扫描件关联窗口
*参数说明: 
*返 回 值: 
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function　recmaterial_rel(){
	var selectedrow = $('#table_rec').datagrid('getSelected');
	selectedrow.RECEIVAL_ID;
	var url = ctx+"/common/recmaterial/recmaterial_rel_img.action?date="+new Date();
	selectedrow.proc_id = proc_id;
	window.showModalDialog(url,selectedrow,"dialogWidth=800px;dialogHeight=600px");
}
/**********************************************************************************
*函数名称: validate()
*功能说明: 公用校验方法  受理时验证 其它环节返回true
*参数说明: 
*返 回 值: obj  result(true通过  false不通过) message(消息)  page_name(当前页面名字)
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function validate(){
	//返回结果对象
	var result ={
			result:true,
			message:'',
			page_name:'接件材料表'
	}
	//提示消息 
	var message;
	
	if(procNode == state1.string0){
		var tmpData = $('#table_rec').datagrid("getRows");
		if(tmpData.length<=0){
			message = '接件材料不能为空!';
			 result.message=message;
			 result.result=false;
			 return result;
		}
		
		var tmpChangeData = $('#table_rec').datagrid("getChanges");
		if(tmpChangeData.length>0){
			var flag = 0;  //用来确认 是否用户已经点击放弃保存  未点击  代表是在外面调用     返回false
			message = '接件材料数据己修改 ！请先保存后再进行操作!';
			 if(flag){
				 
			 }else{
				 result.message=message;
				 result.result=false; 
			 }
			 return result;
		}
	}
	
	return result;
}

/**********************************************************************************
*函数名称: radioClick
*功能说明: 单选框点击时更新数据库
*参数说明: 
*返 回 值: 修改返回true   未修改返回false
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function radioClick(radio){
	updateMaterialreplenition(radio.value);
}
/**********************************************************************************
*函数名称: radioClick
*功能说明: 更新补正材料数据库
*参数说明: 
*返 回 值: 修改返回true   未修改返回false
*函数作者: Joyon
*创建日期: 2014年5月28日 
*修改历史: 
***********************************************************************************/
function updateMaterialreplenition(reason){
	$.ajax({
		url:ctx+"/common/recmaterial!updateMaterialreplenish.action",
		data:{"proc_id":proc.procId,"time":new Date(),"reason":reason},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		dataType:"json",
		async:false,
		success:function(data){
			
		},error:function(data){
			
		}
		
	});
}
/**********************************************************************************
*函数名称: getMaterialreplenition
*功能说明: 获取补正数据  并回填需要补正原因
*参数说明: 
*返 回 值: 修改返回true   未修改返回false
*函数作者: Joyon
*创建日期: 2014年5月28日 
*修改历史: 
***********************************************************************************/
function getMaterialreplenition(){
	$.ajax({
		url:ctx+"/common/recmaterial!geteMaterialreplenish.action",
		data:{"proc_id":proc.procId,"time":new Date()},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		dataType:"json",
		async:false,
		success:function(data){
			//如果补正材料表中有数据    数据回填
			if(data){
				//alert(JSON.stringify(data));
				var inputarr = $("input[name='rec_state']"); 
				for(var i=0;i<inputarr.length;i++){
					if(inputarr[i].value==data.repl_reson){
						inputarr[i].checked="checked";
						return;
					}
				}
				
			}
			
		},error:function(data){
			
		}
		
	});
}

function submit(){
	return true;
}
/**********************************************************************************
*函数名称: pageDataIsChange
*功能说明: 判断当前页面数据是否已经修改
*参数说明: 
*返 回 值: 修改返回true   未修改返回false
*函数作者: Joyon
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
function pageDataIsChange(){
	if(proc_node == state1.string0){
		var tmpChangeData = $('#table_rec').datagrid("getChanges");
		if(tmpChangeData.length>0){
			return true;
		}
	}
	//如果相等返回  页面数据未修改  返回false
	return false;
}


