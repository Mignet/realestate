//var djlx = proc.procdefName;

//var proc=this.parent.proc;
//var lcjd = proc.activName;

//var proc_id = proc.procId;

var proc_id = '1000016579';
var itemCanEdit = false;
var lastEditIndex = -1;
//var procNode = proc.activName;
var procNode = "受理";
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

var i = 0;
var jjclDataGrid; 
var products = [  
           {productid:'1',name:'原件'},  
            {productid:'2',name:'复印件'}, 
            
      ];  
        function productFormatter(value){  
        	value = "原件";
           for(var i=0; i<products.length; i++){  
                if (products[i].productid == value); return products[i].name;  
           }  
           return value;  
        }  
        var cllx = [  
                        {productid:'1',name:'选接件'},  
                         {productid:'2',name:'必接件'}, 
                         
                   ];  
         function cllxFormatter(value){  
        	 value = "必接件";
                   for(var i=0; i<cllx.length; i++){  
                             if (cllx[i].productid == value); return cllx[i].name;  
                    }  
                        return value;  
     } 
//定义变量flag，用来判断执行更新或保存操作

 //开始初始化       
$(function(){  
	         
               var lastIndex;  
           
            		
            	var jjcl =	{
            	title:'接件材料表',
            	url:ctx+'/common/recmaterial!getRecmaterial.action?time='+new Date()+"&proc_id="+proc_id,
            	width:800,
            	height:410,
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
        					{field:'RE_PERSON',	title:'接件人', width:50},
        					{field:'RE_DATE',	title:'接件日期', width:80},
        					{field:'button',formatter:function(value,rec){return '<span><input  type="button" value="关联" onclick=""/></span>';}}
        						
        				]],
        				
                toolbar:[{  
                	id:"rec_add",
                   text:'新建',  
                    iconCls:'icon-add',  
                   handler:function(){  
                       $('#tt').datagrid('endEdit', lastIndex);  
                        $('#tt').datagrid('appendRow',{  
                        	RE_NAME:autoAdd(),  
                           RE_STYLE:'原件',  
                           RE_TYPE:'必接件',
                           RE_PAGE:'1',
                           RE_COPY:'1',
                            RE_PERSON:user,  
                            RE_DATE:getTime()
                        });  
                       lastIndex = $('#tt').datagrid('getRows').length-1;  
                        $('#tt').datagrid('selectRow', lastIndex);  
                        $('#tt').datagrid('beginEdit', lastIndex);  
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
            	   if(procNode == "受理"){
            		   if (lastIndex != rowIndex){  
                           $('#tt').datagrid('endEdit', lastIndex);  
                           $('#tt').datagrid('beginEdit', rowIndex);  
                       }  
                      lastIndex = rowIndex;  
            	   }else{
            		  
            	   }
                   
                },onClickCell:function(rowIndex, field, value){
					//alert("onclickcell event"+rowIndex+field+value);
					if(field=="button"){
						$('#tt').datagrid('selectRow',rowIndex);
						recmaterial_rel(this);
					}
				},onLoadSuccess:function(data){
					
                	if(procNode == "受理"){
                		
                	}else{
                		
                		$('#rec_add').linkbutton('disable');
                		$('#rec_del').linkbutton('disable');
                		$('#rec_save').linkbutton('disable');
                		
                		
                	}
                }
              
              };  //定义jjcl的对象
 //删除行           
 function doDel(){
            	 var row = $('#tt').datagrid('getSelected');  
                 if (row){
                 	top.$.messager.confirm('提示','确定要删除这一行吗？',function(){
                 		  var index = $('#tt').datagrid('getRowIndex', row);  
                          $('#tt').datagrid('deleteRow', index);            		
                 	});
                  
                 }else{   
                 	top.$.messager.confirm('提示','请选择要删除的行！！');             	
                 }  
         	   
         };         



 jjclDataGrid = $('#tt').datagrid(jjcl);	
	
	
 
});  //初始化结束
//获取系统当前时间 
  function getTime(){
	 
 	  var myDate = new Date();
 	 //alert(myDate);
 	//alert(JSON.stringify(myDate));
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
  function add_zero(temp)
  {
   if(temp<10) return "0"+temp;
   else return temp;
  }
//序号自增方法
function autoAdd(){
	i++;
	return '新建材料'+i;
}
//保存数据
function doSave(){
	//$("#tt").datagrid('selectAll');
	//if(endEditing()){
		jjclDataGrid.datagrid('endEdit', lastEditIndex);
		lastEditIndex = -1;
		var inserted = jjclDataGrid.datagrid('getChanges', 'inserted');
		var deleted = jjclDataGrid.datagrid('getChanges', 'deleted');
		var updated = jjclDataGrid.datagrid('getChanges', 'updated');
		 var  rows =  $('#tt').datagrid('getData'); 
		//请求后台保存数据
		$.ajax({
			url : ctx+'/common/recmaterial!applyEdit.action?proc_id='+proc_id+"&time="+new Date(),
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
	//}
} 

var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){return true}
        if ($('#tt').datagrid('validateRow', editIndex)){
var name = $('#tt').datagrid('getEditor', {index:editIndex,field:'Name'});
            ////$(name.target).val()<span></span> 是修改后的，写个请求到后台，修改就行了。 
            $('#tt').datagrid('endEdit', editIndex);
            editIndex = undefined;
        return true;
        } else {
            return false;

        }

}

//回调数据动态刷新
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
//根据流程节点判断是从模版表读取数据还是从借鉴材料表中读取数据
function showDatagrid(){
	if(!(lcjd == state1.string0)){
		  alert();
		  jjcl.url = 'jjclDelegate/getJjclList.run?lcslbid='+lcslbid;
		  jjclDataGrid = $('#tt').datagrid(jjcl);	
	}else{
		
		return;
	}				
}

//材料关联按扭事件
function　recmaterial_rel(){
	var selectedrow = $('#tt').datagrid('getSelected');
	selectedrow.RECEIVAL_ID;
	//alert(JSON.stringify(selectedrow));
	var url = ctx+"/common/recmaterial/recmaterial_rel_img.action?date="+new Date();
	selectedrow.proc_id = proc_id;
	window.showModalDialog(url,selectedrow,"dialogWidth=800px;dialogHeight=600px");
	//打开窗口  obj对象
	/*
	var obj={	
		id:"open_img",
		//src:ctx+"/common/recmaterial/recmaterial_rel_img.action?date="+new Date(),
		//窗口宽
		width: 800,
		//窗口高
		title:'材料关联扫描件信息',
		height: 600,
		modal: true,	
		destroy:true,
		onLoad:	function(){
			//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
			//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
			//this.openerWindow = window;
			//this.document.getElementById('name').value='名称';
			//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
			//this.init(selectedrow);
			this.init(selectedrow);
		}
	};
	obj.src=ctx+"/common/recmaterial/recmaterial_rel_img.action?date="+new Date();
	
	openInTopWindow(obj);	
	*/	
}



