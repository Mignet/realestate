
function init(bus_type_id,rec_type_flag) {
	//alert(user);
    $("#ywlxid").val(bus_type_id);
    $("#rec_type_flag").val(rec_type_flag);
    
    
    var tableFormObj = {
    		errorCode: 700,
    		//表单元素表格的列数（通常设为两列）
    		colnum: 2,
    		//表单控件标题列宽
    		titleWidth: 120,
    		//表单控件输入框列宽
    		cellWidth: 150,
    		//表单控件input、select、combo等输入框宽
    		inputWidth:	150,
    		//textarea控件输入框宽
    		textareaWidth: 500,
    		//textarea控件输入框高
    		textareaHeight: 90,
    		//是否设置fieldset标签
    		fieldset:false	,
    		//表单元素参数数组。数组中每个对象构成一个表单元素
    		inputs: [{
    			tag: 'input',
    			title: '名称',
    			name: 'rmc.cfig_rec_name',
    			id: 'mc',
    			type: 'validatebox',
    			options: {
    				required: true,
    				missingMessage: '必填项'
    			}
    		},{
    			tag: 'input',
    			title: '类型',
    			name: 'rmc.cfig_rec_type',
    			id: 'lx',
    			type: 'combodict',
    			options: {
    				code: '024',
    				//url:'combo_tree_data.json'
    				//required: true,
    				//missingMessage: '必填项'
    			}
    		},{
    			tag: 'input',
    			title: '种类',
    			name: 'rmc.cfig_rec_style',
    			id: 'zl',
    		     type: 'combodict',
    			options: {
    				code: '025',
    				//url:'combo_tree_data.json'
    				//required: true,
    				//missingMessage: '必填项'
    			}
    		},{
    			tag: 'input',
    			title: '份数',
    			name: 'rmc.cfig_rec_copy',
    			id: 'fs',
    			type: 'validatebox',
    			options: {
    				required: true,
    				missingMessage: '必填项'
    			}
    			},
    			{
    				tag: 'input',
    				title: '页数',
    				name: 'rmc.cfig_page',
    				id: 'ys',
    				type: 'validatebox',
    				options: {
    					required: true,
    					missingMessage: '必填项'
    				}
    				},
    			
    			
    			{
    			tag: 'input',
    			title: '配置人',
    			name: 'rmc.cfig_person',
    			id: 'pzr',
    			type: 'validatebox',
                attributes:{
    				
    				value:user,
    				readonly:'readonly'
    			},
    			options: {
    				required: true,
    				missingMessage: '必填项'
    			}
    			},{
    			tag: 'input',
    			title: '配置时间',
    			name: 'rmc.cfig_date',
    			id: 'pzsj',
    			attributes:{
    				
    				value:getTime(),
    				readonly:'readonly'
    			}
    			}
    				
    		],
    		url: ctx+"/common/configur!saveRecMaterialCon.action",
    		dataType: 'json',
    		success: function(data){
    			if (data.success) {
    				top.$.messager.alert('新增用户提示',data.tipMessage,'info',function(){
    					args.userDataGrid.datagrid('reload');
    					closeInTopWindow('add_user_win');
    				});				
    			} else {
    				top.$.messager.alert('新增用户错误',data.errorMessage,'error');
    			}
    		}
    	};
    
    if(rec_type_flag=='1'){
    	//把类型移到数组第一位  然后删除数组第一上
    	var tmplx = tableFormObj.inputs[0];// = tableFormObj.inputs.splice(1,1);
    	tableFormObj.inputs[0]=tableFormObj.inputs[1];
    	tableFormObj.inputs[1]=tmplx;
    	tableFormObj.inputs.shift();
    	//a.splice(1,1);
    	//$("#lx").css("display","none");
    }
    
   
	var form = $('#add_app_form').tableform(tableFormObj);
	
	
};

$("#pzsj").val(getTime());
$("#pzr").val(user);

function submit(){	
	$("pzsj").val(getTime());
	$('#add_app_form').submit();
}
//获取系统当前时间
function getTime(){
	  var myDate = new Date();
	  var year = myDate.getFullYear();
	  var month = myDate.getMonth()+1;
	  var date = myDate.getDate(); 
     var mytime=myDate.toLocaleTimeString();     //获取当前时间	  
	var time = year+"-"+month+"-"+date+" "+mytime;
	return time;
	
	}

