var rec_type_flag;
function init(user,bus_type_id,rec_type_flag) {
	this.rec_type_flag = rec_type_flag;
   $("#mc1").val(user.CFIG_REC_NAME);
   $("#lx1").val(user.CFIG_REC_TYPE);
   $("#zl1").val(user.CFIG_REC_STYLE);
   $("#fs1").val(user.CFIG_REC_COPY);
   $("#pzr1").val(user.CFIG_PERSON);
   $("#pzsj1").val(user.CFIG_DATE);
   $("#ywlxid").val(bus_type_id);
   $("#jjclpzbid").val(user.CFIG_RECEIVAL_ID);
   $("#ys").val(user.CFIG_PAGE);
   $("#rec_type_flag").val(rec_type_flag);
   if(rec_type_flag=='1'){
   	$("#lx").css("display","none");
   }

   var tableFormObj = {
			errorCode: 700,
			//表单元素表格的列数（通常设为两列）
			colnum: 2,
			//表单控件标题列宽
			titleWidth: 120,
			//表单控件输入框列宽
			cellWidth: 250,
			//表单控件input、select、combo等输入框宽
			inputWidth:	200,
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
				name: 'CFIG_REC_NAME',
				id: 'mc',
				type: 'validatebox',
				options: {
					required: true,
					missingMessage: '必填项'
				}
			},{
				tag: 'input',
				title: '类型',
				name: 'CFIG_REC_TYPE',
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
				name: 'CFIG_REC_STYLE',
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
				name: 'CFIG_REC_COPY',
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
					name: 'CFIG_PAGE',
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
				name: 'CFIG_PERSON',
				id: 'pzr',
				type: 'validatebox',
	            attributes:{
					
					value:user1,
					readonly:'readonly'
				},
				options: {
					required: true,
					missingMessage: '必填项'
				}
				},{
				tag: 'input',
				title: '配置时间',
				name: 'CFIG_DATE',
				id: 'pzsj',
	            attributes:{
					
					value:getTime(),
					readonly:'readonly'
				}
				}],
			url: ctx+"/common/configur!updateRecMaterialCon.action?time="+new Date(),
			dataType : 'json',
			success: function(data){
				if (data.success) {
					top.$.messager.alert('更新用户提示',data.tipMessage,'info',function(){
					  
						args.userDataGrid.datagrid('reload');
						 closeInTopWindow('edit_user_win');
					});				
				} else {
					top.$.messager.alert('更新用户错误',data.errorMessage,'error');
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
   
  
   
	var form = $('#edit_user_form').tableform(tableFormObj);
	form.form("load",user);
	
	//从服务端获取用户信息
	/**
	$.ajax({
		type: "POST",
		url:  '../appDelegate/getAppByID.run?djbh='+args.user.djbh,
		dataType:"json",
		success: function(user){
			//对象添加属性
			user.djbh=args.user.djbh;
			user.password = null;
			//载入表单
			form.form("load",user);
		}
	});

	*/
};

//保存信息
function submit(){
	   $("#mc1").val($("#mc").val());
	   
	   if(rec_type_flag=='0'){
		   $("#lx1").val($("#lx").combodict("getValue"));
	   }
	   
	   $("#zl1").val($("#zl").combodict("getValue"));
	   $("#fs1").val($("#fs").val());
	   $("#pzr1").val($("#pzr").val());
	   $("#pzsj1").val($("#pzsj").val());
       $('#edit_user_form').submit();
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
