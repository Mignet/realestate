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
			//��Ԫ�ر���������ͨ����Ϊ���У�
			colnum: 2,
			//���ؼ������п�
			titleWidth: 120,
			//���ؼ�������п�
			cellWidth: 250,
			//���ؼ�input��select��combo��������
			inputWidth:	200,
			//textarea�ؼ�������
			textareaWidth: 500,
			//textarea�ؼ�������
			textareaHeight: 90,
			//�Ƿ�����fieldset��ǩ
			fieldset:false	,
			//��Ԫ�ز������顣������ÿ�����󹹳�һ����Ԫ��
			inputs: [{
				tag: 'input',
				title: '����',
				name: 'CFIG_REC_NAME',
				id: 'mc',
				type: 'validatebox',
				options: {
					required: true,
					missingMessage: '������'
				}
			},{
				tag: 'input',
				title: '����',
				name: 'CFIG_REC_TYPE',
				id: 'lx',
				type: 'combodict',
				options: {
					code: '024',
					//url:'combo_tree_data.json'
					//required: true,
					//missingMessage: '������'
				}
			},{
				tag: 'input',
				title: '����',
				name: 'CFIG_REC_STYLE',
				id: 'zl',
			     type: 'combodict',
				options: {
					code: '025',
					//url:'combo_tree_data.json'
					//required: true,
					//missingMessage: '������'
				}
			},{
				tag: 'input',
				title: '����',
				name: 'CFIG_REC_COPY',
				id: 'fs',
				type: 'validatebox',
				options: {
					required: true,
					missingMessage: '������'
				}
				},
				
				{
					tag: 'input',
					title: 'ҳ��',
					name: 'CFIG_PAGE',
					id: 'ys',
					type: 'validatebox',
					options: {
						required: true,
						missingMessage: '������'
					}
					},
				
				
				{
				tag: 'input',
				title: '������',
				name: 'CFIG_PERSON',
				id: 'pzr',
				type: 'validatebox',
	            attributes:{
					
					value:user1,
					readonly:'readonly'
				},
				options: {
					required: true,
					missingMessage: '������'
				}
				},{
				tag: 'input',
				title: '����ʱ��',
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
					top.$.messager.alert('�����û���ʾ',data.tipMessage,'info',function(){
					  
						args.userDataGrid.datagrid('reload');
						 closeInTopWindow('edit_user_win');
					});				
				} else {
					top.$.messager.alert('�����û�����',data.errorMessage,'error');
				}
			}
			
			
			
		};
   
   if(rec_type_flag=='1'){
   	//�������Ƶ������һλ  Ȼ��ɾ�������һ��
   	var tmplx = tableFormObj.inputs[0];// = tableFormObj.inputs.splice(1,1);
   	tableFormObj.inputs[0]=tableFormObj.inputs[1];
   	tableFormObj.inputs[1]=tmplx;
   	tableFormObj.inputs.shift();
   	//a.splice(1,1);
   	//$("#lx").css("display","none");
   }
   
  
   
	var form = $('#edit_user_form').tableform(tableFormObj);
	form.form("load",user);
	
	//�ӷ���˻�ȡ�û���Ϣ
	/**
	$.ajax({
		type: "POST",
		url:  '../appDelegate/getAppByID.run?djbh='+args.user.djbh,
		dataType:"json",
		success: function(user){
			//�����������
			user.djbh=args.user.djbh;
			user.password = null;
			//�����
			form.form("load",user);
		}
	});

	*/
};

//������Ϣ
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


//��ȡϵͳ��ǰʱ��
function getTime(){
	  var myDate = new Date();
	  var year = myDate.getFullYear();
	  var month = myDate.getMonth()+1;
	  var date = myDate.getDate(); 
     var mytime=myDate.toLocaleTimeString();     //��ȡ��ǰʱ��	  
	var time = year+"-"+month+"-"+date+" "+mytime;
	return time;
	
	}
