
function init(bus_type_id,rec_type_flag) {
	//alert(user);
    $("#ywlxid").val(bus_type_id);
    $("#rec_type_flag").val(rec_type_flag);
    
    
    var tableFormObj = {
    		errorCode: 700,
    		//��Ԫ�ر���������ͨ����Ϊ���У�
    		colnum: 2,
    		//���ؼ������п�
    		titleWidth: 120,
    		//���ؼ�������п�
    		cellWidth: 150,
    		//���ؼ�input��select��combo��������
    		inputWidth:	150,
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
    			name: 'rmc.cfig_rec_name',
    			id: 'mc',
    			type: 'validatebox',
    			options: {
    				required: true,
    				missingMessage: '������'
    			}
    		},{
    			tag: 'input',
    			title: '����',
    			name: 'rmc.cfig_rec_type',
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
    			name: 'rmc.cfig_rec_style',
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
    			name: 'rmc.cfig_rec_copy',
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
    				name: 'rmc.cfig_page',
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
    			name: 'rmc.cfig_person',
    			id: 'pzr',
    			type: 'validatebox',
                attributes:{
    				
    				value:user,
    				readonly:'readonly'
    			},
    			options: {
    				required: true,
    				missingMessage: '������'
    			}
    			},{
    			tag: 'input',
    			title: '����ʱ��',
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
    				top.$.messager.alert('�����û���ʾ',data.tipMessage,'info',function(){
    					args.userDataGrid.datagrid('reload');
    					closeInTopWindow('add_user_win');
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
    
   
	var form = $('#add_app_form').tableform(tableFormObj);
	
	
};

$("#pzsj").val(getTime());
$("#pzr").val(user);

function submit(){	
	$("pzsj").val(getTime());
	$('#add_app_form').submit();
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

