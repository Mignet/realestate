/*********************************************************************************
*��  ��  ��  ��: correction-notice.js
*��  ��  ��  ��: �Ӽ�����js
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Joyon
*��  ��  ��  �ڣ� 2014-03-01
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
var proc=this.parent.proc;
//var proc={
//		procId:"1000017642",
//		activName:"������������"
//};
var proc_id = proc.procId;
var itemCanEdit = false;
var lastEditIndex = -1;
var procNode = proc.activName;
var proc_node = proc.activName;
var rec_type_flag = enumdata.REC_TYPE_FLAG.CORRECTION;//��������   0����Ӽ�����   1���Ĳ����嵥  2��Ҫ��֤�Ĳ��� 
var state1 = {
		string0: "������������",
		string1 : "����",
		string2 : "����",
		string3 : "���",
		string4 : "��׼",
		string5 : "������",
		string6 : "������",
		string7: "����������",
		string8 : "����������",		
		string9 : "��֤",
		string10 : "��֤",
		string11: "�鵵",
		string12: "����",
		string13:"�ⶨ����"
	};

var i = 0;
var jjclDataGrid; 
//�������ؽӼ��������� �ͽӼ����ϸ��� �ֵ���
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
 
 /**********************************************************************************
 *��������: 
 *����˵��: ����ҳ��״̬
 *����˵��: 
 *�� �� ֵ: 
 *��������: Joyon
 *��������: 2014��5��28�� 19:32:23
 *�޸���ʷ: 
 ***********************************************************************************/  
 function setState(){
	 if(proc_node!="������������"){
		 $("input").attr("disabled","disabled");
	 }
 }
/**********************************************************************************
*��������: 
*����˵��: js����ʱ����  
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/  
$(function(){  
	//�����ԭ��ҳ��
	getMaterialreplenition();
               var lastIndex;  
            	var jjcl =	{
            	title:'�貹�������б�',
            	url:ctx+'/common/recmaterial!getCorrectionMaterialInfo.action?rec_type_flag='+rec_type_flag+'&time='+new Date()+"&proc_id="+proc_id,
            	width:600,
            	height:240,
            	fitColumns: true,
        		//ȥ���߿�
        		border : true,
        		//�Ƿ��з�ҳ��
        		pagination:true,
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
        		columns:[[
        					//ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
        					{field:'RE_NAME', title:'��������', width:100, sortable:true,editor:'text'},	
        					{field:'RE_STYLE',hidden:true, title:'��������', width:50,formatter:productFormatter,editor:{    
        						type:'combobox',
        						options:{
        							 valueField:'productid',  
        							 textField:'name',  
        							 data:products,  
        							 required:true 
        						}
        					}},						
        					{field:'RE_TYPE',hidden:true,	title:'��������', width:50,formatter:cllxFormatter,editor:{
        						type:'combobox',
        						options:{
        							 valueField:'productid',  
        							 textField:'name',  
        							 data:cllx,  
        							 required:true 
        						}
        					}},
        						{field:'RE_PAGE',	title:'ҳ��', width:50,editor:'text',hidden:true},
        						{field:'RE_COPY',	title:'����', width:50,editor:'text'},	
        					{field:'RE_PERSON',	title:'¼����', width:50,hidden:true},
        					{field:'RE_DATE',	title:'¼������', width:80,hidden:true},
        					{field:'REC_TYPE_FLAG',hidden:true}
        				]],
                toolbar:[{  
                	id:"rec_add",
                   text:'�½�',  
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
                   text:'ɾ��',  
                    iconCls:'icon-remove',  
                    handler:doDel 
               },'-',{  
            	    id:"rec_save",
                    text:'����',  
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
                	if(procNode == "����"){
                		
                	}else{
                		
                		//$('#rec_add').linkbutton('disable');
                		//$('#rec_del').linkbutton('disable');
                		//$('#rec_save').linkbutton('disable');
                	}
                }
              };  //����jjcl�Ķ���
			 //ɾ����           
			 function doDel(){
			            	 var row = $('#table_rec').datagrid('getSelected');  
			                 if (row){
			                 	top.$.messager.confirm('��ʾ','ȷ��Ҫɾ����һ����',function(r){
			                 		if(r){
				                 		  var index = $('#table_rec').datagrid('getRowIndex', row);  
				                          $('#table_rec').datagrid('deleteRow', index);     
			                 		}
			                 	});
			                 }else{   
			                 	top.$.messager.confirm('��ʾ','��ѡ��Ҫɾ�����У���');             	
			                 }  
			         };         
         jjclDataGrid = $('#table_rec').datagrid(jjcl);		//���ɽӼ�����datagrid
});  
//��ʼ������

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
*��������: autoAdd()
*����˵��: �����������  ��������ǰ�Ӳ�����ʱ�������
*����˵��: 
*�� �� ֵ: '�½�����'+i;
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/	
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

/**********************************************************************************
*��������: dataBack()
*����˵��: �ص����ݶ�̬ˢ��
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
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
				alert("�ص����ݳɹ�");
			}
		});
}
/**********************************************************************************
*��������: dataBack()
*����˵��: �Ӽ����ϸ�����Ť�¼�  ����ɨ�����������
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function��recmaterial_rel(){
	var selectedrow = $('#table_rec').datagrid('getSelected');
	selectedrow.RECEIVAL_ID;
	var url = ctx+"/common/recmaterial/recmaterial_rel_img.action?date="+new Date();
	selectedrow.proc_id = proc_id;
	window.showModalDialog(url,selectedrow,"dialogWidth=800px;dialogHeight=600px");
}
/**********************************************************************************
*��������: validate()
*����˵��: ����У�鷽��  ����ʱ��֤ �������ڷ���true
*����˵��: 
*�� �� ֵ: obj  result(trueͨ��  false��ͨ��) message(��Ϣ)  page_name(��ǰҳ������)
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function validate(){
	//���ؽ������
	var result ={
			result:true,
			message:'',
			page_name:'�Ӽ����ϱ�'
	}
	//��ʾ��Ϣ 
	var message;
	
	if(procNode == state1.string0){
		var tmpData = $('#table_rec').datagrid("getRows");
		if(tmpData.length<=0){
			message = '�Ӽ����ϲ���Ϊ��!';
			 result.message=message;
			 result.result=false;
			 return result;
		}
		
		var tmpChangeData = $('#table_rec').datagrid("getChanges");
		if(tmpChangeData.length>0){
			var flag = 0;  //����ȷ�� �Ƿ��û��Ѿ������������  δ���  ���������������     ����false
			message = '�Ӽ��������ݼ��޸� �����ȱ�����ٽ��в���!';
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
*��������: radioClick
*����˵��: ��ѡ����ʱ�������ݿ�
*����˵��: 
*�� �� ֵ: �޸ķ���true   δ�޸ķ���false
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function radioClick(radio){
	updateMaterialreplenition(radio.value);
}
/**********************************************************************************
*��������: radioClick
*����˵��: ���²����������ݿ�
*����˵��: 
*�� �� ֵ: �޸ķ���true   δ�޸ķ���false
*��������: Joyon
*��������: 2014��5��28�� 
*�޸���ʷ: 
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
*��������: getMaterialreplenition
*����˵��: ��ȡ��������  ��������Ҫ����ԭ��
*����˵��: 
*�� �� ֵ: �޸ķ���true   δ�޸ķ���false
*��������: Joyon
*��������: 2014��5��28�� 
*�޸���ʷ: 
***********************************************************************************/
function getMaterialreplenition(){
	$.ajax({
		url:ctx+"/common/recmaterial!geteMaterialreplenish.action",
		data:{"proc_id":proc.procId,"time":new Date()},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		dataType:"json",
		async:false,
		success:function(data){
			//����������ϱ���������    ���ݻ���
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
*��������: pageDataIsChange
*����˵��: �жϵ�ǰҳ�������Ƿ��Ѿ��޸�
*����˵��: 
*�� �� ֵ: �޸ķ���true   δ�޸ķ���false
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
function pageDataIsChange(){
	if(proc_node == state1.string0){
		var tmpChangeData = $('#table_rec').datagrid("getChanges");
		if(tmpChangeData.length>0){
			return true;
		}
	}
	//�����ȷ���  ҳ������δ�޸�  ����false
	return false;
}


