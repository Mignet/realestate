//init ��ں���
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
                {productid:'025001',name:'ԭ��'},  
                {productid:'025002',name:'��ӡ��'}, 
             ];  
 function productFormatter(value){  
 	 var temp = "ԭ��";
 	 if(value=='025001'){
 		 
 	 }if(value=='025002'){
 		 temp = "��ӡ��";
 	 }
 	 return temp;
 }  
 var cllx = [  
     {productid:'024002',name:'ѡ�Ӽ�'},  
     {productid:'024001',name:'�ؽӼ�'}, 
 ];  
  function cllxFormatter(value){  
 	 var temp = "�ؽӼ�";
 	 if(value=='024001'){
 		 
 	 }if(value=='024002'){
 		 temp = "ѡ�Ӽ�";
     }
     return temp;  
  } 


//ҳ�濪ʼ���غ���
$(function(){
	//���巿�ز���ʼ
	proc_id = '1000026187';
	rec_type_flag = '0';
	houseDataGrid = $('#table_house').datagrid({
		title:'�Ǽǵ�Ԫ',
		height:161,
		// ���ÿ�п���Զ���Ӧ����ܿ��
		autoRowHeight : true,
		fitColumns : true,
		// ȥ���߿�
		border : true,
		striped : true,
		// �Ƿ��з�ҳ��
		pagination : false,
		// pagePosition:'top',
		// ÿҳ����
		//pageSize : 10,
		// �Ƿ�����������һ����ʾ�кŵ���
		rownumbers : true,
		// ����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		//idField : 'jjclmc',
		// �������Ƿ�����ʾ��ͬ����ɫ
		striped : true,
		// ֻ����ѡһ��
		singleSelect : true,
		
		columns : [ [
		     		{
		     			title : '�Ǽǵ�Ԫ����',
		     			field : 'REG_UNIT_TYPE',formatter :dicts.format.reg_unit_type_format,
		     			width:140
		     		}, {
		     			title : '�Ǽǵ�Ԫ���',
		     			field : 'REG_UNIT_CODE',
		     			width:140
		     			
		     		}, {
		     			title : '�Ǽǵ�Ԫ����',
		     			field : 'REG_UNIT_NAME',
		     			width:140
		     			
		     		}
		     		] ],
		     		// ��ͷ����ӹ�������
		     		toolbar:'#optbtnI',
		     		onClickRow : function() {
		     		},
		     		onLoadSuccess : function() {
		     			
		     			
		     		}
	});//���巿�ز�����
	
	//������������Ϣ��
	 userDataGrid = $('#table_user').datagrid({
		title:'������',
		height:171,
		// ���������Դ
		url :ctx+"/houseownership/initialreg!getAppMessage.action?time="+new Date()+"&proc_id="+proc_id,
		// ���ÿ�п���Զ���Ӧ����ܿ��
		autoRowHeight : true,
		fitColumns : false,
		// ȥ���߿�
		border : true,
		striped : true,
		// �Ƿ��з�ҳ��
		pagination : false,
		// pagePosition:'top',
		// ÿҳ����
		pageSize : 10,
		// �Ƿ�����������һ����ʾ�кŵ���
		rownumbers : true,
		// ����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		//idField : 'jjclmc',
		// �������Ƿ�����ʾ��ͬ����ɫ
		striped : true,
		// ֻ����ѡһ��
		singleSelect : true,
		// �Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		// checkOnSelect:true,
		// �Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		// selectOnCheck:true,
		// ����������
		columns : [ [{
	           title : '�� ��',  
	           field : 'button',
	           formatter : function(value,rec)
	           {return '<span><input type="button" value="�� ��" onclick=""/></span>';},
        },{
			title : '���������',
			field : 'app_name'
			
		},{
			title : '������',
			field : 'app_name'
			
		}, {
			title : '����������',
			field : 'app_type',formatter : dicts.format.app_type_format
			
		}, {
			title : '���֤����/��֯��������',
			field : 'app_cer_no'
			
		}, {
			title : '�ݶ�',
			field : 'app_port'
			
		}, {
			title : '��ַ',
			field : 'app_address'
			
		}, {
			title : '��ϵ�绰',
			field : 'app_tel'
		}, {
			title : '�ʱ�',
			field : 'app_address'
			
		}, {
			title : '����������',
			field : 'legal_name'
		
		}, {
			title : '�������������֤����',
			field : 'app_cer_no'
		
		},  {
			title : '������',
			field : 'agent_name'
			
		},{
			title : '���������֤��',
			field : 'agent_cer'
			
		}, {
			title : '��  ��',
			field : 'agent_tel'
			
		}] ],
		// ��ͷ����ӹ�������
		toolbar:'#optbtnII',
		onClickRow : function() {
			//�����ʱ����༭������ɾ������ť ֻ������ͳ��󻷽ڼ���
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
			//������Ͻ��á��༭������ɾ������ť
			/*$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');*/
			
			//ֻ�����������вŶ�Ȩ���˱�����м�¼  �������ڲ�����Ȩ���˱��
		}

	});//���������˽���
	
	var jjcl =	{
        	title:'�Ӽ����ϱ�',
        	url:ctx+'/common/recmaterial!getRecmaterial.action?rec_type_flag='+rec_type_flag+'&time='+new Date()+"&proc_id="+proc_id,
        	height:321,
        	fitColumns: true,
    		//ȥ���߿�
    		border : true,
    		checkbox:'CK',
    		//�Ƿ��з�ҳ��
    		pagination:false,
    		//ÿҳ����
    		pageSize:10,
    		//�Ƿ�����������һ����ʾ�кŵ���
    		rownumbers:true,
    		//����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
    		//idField: 'user_id',
    		//�������Ƿ�����ʾ��ͬ����ɫ					
    		striped:true,
    		//ֻ����ѡһ��
    		singleSelect:true,
    		// �Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
    		checkOnSelect:false,
    		// �Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
    		selectOnCheck:false,
    		// ����������
    		columns:[[
    					//ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
    					{field:'RE_NAME', title:'�Ӽ���������', width:100, sortable:true,editor:'text'},	
    					{field:'RE_STYLE', title:'��������', width:50,formatter:productFormatter,editor:{    
    						type:'combobox',
    						options:{
    							 valueField:'productid',  
    							 textField:'name',  
    							 data:products,  
    							 required:true 
    						}
    					}},						
    					{field:'RE_TYPE',	title:'��������', width:50,formatter:cllxFormatter,editor:{
    						type:'combobox',
    						options:{
    							 valueField:'productid',  
    							 textField:'name',  
    							 data:cllx,  
    							 required:true 
    						}
    					}},
    						{field:'RE_PAGE',	title:'ҳ��', width:50,editor:'text'},
    						{field:'RE_COPY',	title:'����', width:50,editor:'text'},	
    				    {title:'�յ�',  field:'ck' ,checkbox:true},
    				    { hidden:'true',field : 'checked' },
    					{field:'REC_TYPE_FLAG',hidden:true}
    		]],
    		toolbar:'#optbtnIII',
            onBeforeLoad:function(){  
               $(this).datagrid('rejectChanges');  
           },  
           onClickRow:function(rowIndex){  
        	   /*if(procNode == "����"){
        		   if (lastIndex != rowIndex){  
                       $('#table_seematerials').datagrid('endEdit', lastIndex);  
                       $('#table_seematerials').datagrid('beginEdit', rowIndex);  
                   }  
                  lastIndex = rowIndex;  
        	   }else{
        		  
        	   }*/
            },onLoadSuccess:function(data){
            	/*if(procNode == "����"){
            		
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
          };  //����jjcl�Ķ���
	  
	         jjclDataGrid = $('#table_seematerials').datagrid(jjcl);		//���ɽӼ�����datagrid\
	         
	         //�õ�Ԥ�Ǽ�mess
	         getPreRegMess();
});

// ѡ��Ǽǵ�Ԫ
function doSelectRegunit() {
	 openInTopWindow({
		 // ����Ԫ�ص�id
		 id : 'add_regunit_win',
		 // ����iframe��src
		 src : ctx+'/jsp/common/preaudit.jsp?time='+new Date(),
		 // �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
		 destroy : true,
		 // ���ڱ���
		 title : 'ѡ��Ǽǵ�Ԫ',
		 // ���ڿ�
		 width : 840,
		 // ���ڸ�
		 height : 620,
		 modal : true,
		 // ������iframe��window�����onLoad�ص���������
		 onLoad : function() {
			 // �˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
			 // ��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
			 this.openerWindow = window;
			 // ����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
			 this.args = {
					 houseDataGrid : houseDataGrid
			 };
			// this.init(proc);
			 this.init();
		 }
	 });
};

// ����
function doUserAdd() {
	openInTopWindow({
		// ����Ԫ�ص�id
		id : 'add_user_win',
		// ����iframe��src
		src : ctx+'/jsp/common/applicant/addapplicant.jsp?time='+new Date(),
		// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
		destroy : true,
		// ���ڱ���
		title : '����������',
		// ���ڿ�
		width : 700,
		// ���ڸ�
		height : 400,
		modal : true,
		// ������iframe��window�����onLoad�ص���������
		onLoad : function() {
			// �˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
			// ��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
			this.openerWindow = window;
			// ����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
			this.args = {
				userDataGrid : userDataGrid
			};
			this.init(proc_id);
		}
	});
};
// ɾ��
function doUserDelete() {
	var row = userDataGrid.datagrid('getSelected');
	top.$.messager.confirm('ȷ��', 'ȷ��Ҫɾ������������Ϊ[' + row.app_name + ']��', function(
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
						top.$.messager.alert('��ʾ', data.tipMessage, 'info',
								function() {
							        //alert("ɾ��֮��ˢ��");
									userDataGrid.datagrid('reload');
								});
					} else {
						top.$.messager.alert('��ʾ', data.errorMessage, 'error');
					}
				}
			});
		}
	});

};
//ɾ����           
function doMaterialDel(){
	var row = $('#table_seematerials').datagrid('getSelected');  
	if (row){
		top.$.messager.confirm('��ʾ','ȷ��Ҫɾ����һ����',function(r){
			if(r){
				var index = $('#table_seematerials').datagrid('getRowIndex', row);  
				$('#table_seematerials').datagrid('deleteRow', index);     
			}
		});
	}else{   
		top.$.messager.confirm('��ʾ','��ѡ��Ҫɾ�����У���');             	
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

//��ȡ������ǰ�ô��ڴ��ݵĵǼ���Ϣ
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
*��������: autoAdd()
*����˵��: �����������  ��������ǰ�Ӳ�����ʱ�������
*����˵��: 
*�� �� ֵ: '�½�����'+i;
*��������: Joyon
*��������: 2014-03-01s
*�޸���ʷ: 
***********************************************************************************/	
var i = 0;
function autoAdd(){
	i++;
	return '�½�����'+i;
}
/**********************************************************************************
*��������: doSave()
*����˵��: �����޸ĺ�Ӽ����ϵ�����
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function doSave(){
		//�༭��ʱ�������  ��ȡ��������  ���
		endEdit();
		
		var inserted = jjclDataGrid.datagrid('getChanges', 'inserted');
		var deleted = jjclDataGrid.datagrid('getChanges', 'deleted');
		var updated = jjclDataGrid.datagrid('getChanges', 'updated');
		 var  rows =  $('#table_rec').datagrid('getData'); 
		//�����̨��������
		$.ajax({
			url : ctx+'/common/recmaterial!correctionMaterialApplyEdit.action?proc_id='+proc_id+"&time="+new Date()+"&rec_type_flag="+rec_type_flag,
			dataType : 'json',
			type : 'post',
			data : {
				//������ƴװ��JSON�ַ������ݵ���̨
				datas :$.toJSON({
					inserted : inserted,
					deleted : deleted,
					updated : updated,
					rows:rows.rows
				})
			},
			success : function(data) {
				if (data.success) {
					top.$.messager.alert('��ʾ', '���ݱ���ɹ���', 'info', function(){
						//�������Ϊ���ɱ༩
						itemCanEdit = false;
						//����ʱ���á��༭��ť��
						$('#dictitem_edit').linkbutton('enable');
						//����ʱ���¼��ر������
						jjclDataGrid.datagrid('reload');
					});
				}
			}
		});
} 

function appDetail(){
	var row = $('#table_user').datagrid('getSelected');
	openInTopWindow({
		// ����Ԫ�ص�id
		id : 'edit_app_win',
		// ����iframe��src
		src : ctx+'/jsp/common/applicant/editApplicant.jsp',
		// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
		destroy : true,
		// ���ڱ���
		title : '�鿴��������Ϣ',
		// ���ڿ�
		width : 700,
		// ���ڸ�
		height : 400,
		modal : true,
		// ������iframe��window�����onLoad�ص���������
		onLoad : function() {
			// �˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
			// ��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
			this.openerWindow = window;
			// ����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
			this.args = {
				user : row,
				userDataGrid : userDataGrid
			};
			this.init(row);
		}
	});
}
/**********************************************************************************
*��������: getTime(func)
*����˵��: ��ȡϵͳ��ǰʱ�� 
*����˵��: 
*�� �� ֵ: ��ǰʱ���ִ�
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/	
function getTime(){
 	  var myDate = new Date();
 	  var year = myDate.getFullYear();
 	  var month = add_zero(myDate.getMonth()+1);
 	  var date = add_zero(myDate.getDate()); 
      var mytime=myDate.getTime();     //��ȡ��ǰʱ��	
  var hour = add_zero(myDate.getHours());       //��ȡ��ǰСʱ��(0-23)
  var min = add_zero(myDate.getMinutes());     //��ȡ��ǰ������(0-59)
  var sec = add_zero(myDate.getSeconds());   
  if(sec<10){
	  
  }
  var time = year+"-"+month+"-"+date+" "+hour+":"+min+":"+sec;
 	  return time;
}
/**********************************************************************************
*��������: add_zero()
*����˵��: ����ǰʱ�亯�����0
*����˵��: 
*�� �� ֵ: ��0����ַ���  ֻ�ڵ�ǰʱ�亯����ʹ��
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/	
function add_zero(temp)
{
  if(temp<10) 
	  return "0"+temp;
  else 
	  return temp;
}
/**********************************************************************************
*��������: endEdit()
*����˵��: �������༭״̬  
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function endEdit(){
	var rows = jjclDataGrid.datagrid('getRows');
	for ( var i = 0; i < rows.length; i++) {
		jjclDataGrid.datagrid('endEdit', i);
	}
}
/**********************************************************************************
*��������: endEditing()
*����˵��: �������༭״̬  
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
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
	openTabII("��ӡ",url)
	//window.frames["printframe"].location=url;
}

/**********************************************************************************
*��������: openTabII
*����˵��: ���Ӵ�ѡ�II
*��������: Sam
*��������: 2014��8��8�� 
*�޸���ʷ: 
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
*��������: openTabI
*����˵��: ���Ӵ�ѡ�I
*��������: Sam
*��������: 2014��8��8�� 
*�޸���ʷ: 
***********************************************************************************/
function openTabI(title,url){
	var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';  
	$('#tabsI').tabs('add',{  
		title:title,  
		content:content,  
		closable:true  
	});  
}