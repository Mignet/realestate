/*********************************************************************************
*��  ��  ��  ��: recmaterial.js
*��  ��  ��  ��: �Ӽ�����js
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Joyon
*��  ��  ��  �ڣ� 2014-03-01
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
//var proc=this.parent.proc;
var reg_code;
var proc_id = "1217";//proc.procId;
var itemCanEdit = false;
var lastEditIndex = -1;
var procNode ="����";// proc.activName;
var proc_node ="����"; //proc.activName;
var rec_type_flag = enumdata.REC_TYPE_FLAG.RECEIVAL;//��������   0����Ӽ�����   1���Ĳ����嵥
var state1 = {
		string0: "����",
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
*����˵��: js����ʱ����  
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/  
$(function(){  
               var lastIndex;  
            	var jjcl =	{
            	title:'�Ӽ����ϱ�',
            	url:ctx+'/common/recmaterial!getRecmaterial.action?rec_type_flag='+rec_type_flag+'&time='+new Date()+"&proc_id="+proc_id,
            	width:800,
            	height:410,
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
        					{field:'RE_PERSON',	title:'�Ӽ���', width:50},
        					{field:'RE_DATE',	title:'�Ӽ�����', width:80},
        					{field:'REC_TYPE_FLAG',hidden:true}
            	/*{field:'button',formatter:function(value,rec){return '<span><input  type="button" value="����" onclick=""/></span>';}}*/
        				]],
                toolbar:[{  
                	id:"rec_add",
                   text:'�½�',  
                    iconCls:'icon-add',  
                   handler:function(){  
                       $('#tt').datagrid('endEdit', lastIndex);  
                        $('#tt').datagrid('appendRow',{  
                        	RE_NAME:autoAdd(),  
                            RE_STYLE:'025001',  
                            RE_TYPE:'024001',
                            RE_PAGE:'1',
                            RE_COPY:'1',
                            RE_PERSON:user,  
                            RE_DATE:getTime(),
                            REC_TYPE_FLAG:rec_type_flag
                        });  
                       lastIndex = $('#tt').datagrid('getRows').length-1;  
                        $('#tt').datagrid('selectRow', lastIndex);  
                        $('#tt').datagrid('beginEdit', lastIndex);  
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
               },'-',{
            	   id:"rec_look",
            	   text:'�鿴����',
            	   iconCls:'icon-save',
            	   handler:doLook
               }],  
                onBeforeLoad:function(){  
                   $(this).datagrid('rejectChanges');  
               },  
               onClickRow:function(rowIndex){  
            	   if(procNode == "����"){
            		   if (lastIndex != rowIndex){  
                           $('#tt').datagrid('endEdit', lastIndex);  
                           $('#tt').datagrid('beginEdit', rowIndex);  
                       }  
                      lastIndex = rowIndex;  
            	   }else{
            		  
            	   }
                }
             /*  ,onClickCell:function(rowIndex, field, value){
					//alert("onclickcell event"+rowIndex+field+value);
                	if(procNode == state1.string0 || procNode == state1.string1){
						if(field=="button"){
							$('#tt').datagrid('selectRow',rowIndex);
							recmaterial_rel(this);
						}
                	}
				}*/
               ,onLoadSuccess:function(data){
                	if(procNode == "����"){
                		
                	}else{
                		
                		$('#rec_add').linkbutton('disable');
                		$('#rec_del').linkbutton('disable');
                		$('#rec_save').linkbutton('disable');
                	}
                }
              };  //����jjcl�Ķ���
 //ɾ����           
 function doDel(){
            	 var row = $('#tt').datagrid('getSelected');  
                 if (row){
                 	top.$.messager.confirm('��ʾ','ȷ��Ҫɾ����һ����',function(r){
                 		if(r){
	                 		  var index = $('#tt').datagrid('getRowIndex', row);  
	                          $('#tt').datagrid('deleteRow', index);     
                 		}
                 	});
                 }else{   
                 	top.$.messager.confirm('��ʾ','��ѡ��Ҫɾ�����У���');             	
                 }  
         };         
         jjclDataGrid = $('#tt').datagrid(jjcl);		//���ɽӼ�����datagrid
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
		 var  rows =  $('#tt').datagrid('getData'); 
		//�����̨��������
		$.ajax({
			url : ctx+'/common/recmaterial!applyEdit.action?proc_id='+proc_id+"&time="+new Date()+"&rec_type_flag="+rec_type_flag,
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
*��������: doLook()
*����˵��: �鿴����Ӱ���ļ�  
*����˵��: 
*�� �� ֵ: 
*��������: Sam
*��������: 2014-07-28
*�޸���ʷ: 
***********************************************************************************/
function doLook(){
	var folder = enumdata.SCAN_RECMATERIAL_FOLDER;
	var obj = {
			url: ctx + '/sysmanage/my-scanner!openFolderExpoler.action',
		       dataType:'json',
			   contentType:"application/x-www-form-urlencoded; charset=GBK",
			   data:{"proc_id":proc_id,"time":new Date()},
			   success:function(data){
				 //do nothing 
			   },
			   error:function(msg){
				   top.$.messager.alert('error', msg.errorMessage, 'info', function(){});  
			   }
	}
	$.ajax(obj);
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
        if ($('#tt').datagrid('validateRow', editIndex)){
        	var name = $('#tt').datagrid('getEditor', {index:editIndex,field:'Name'});
            $('#tt').datagrid('endEdit', editIndex);
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
function recmaterial_rel(){
	var selectedrow = $('#tt').datagrid('getSelected');
	selectedrow.RECEIVAL_ID;
	var url = ctx+"/jsp/common/recmaterial/recmaterial_rel_img.jsp?date="+new Date();
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
		var tmpData = $('#tt').datagrid("getRows");
		if(tmpData.length<=0){
			message = '�Ӽ����ϲ���Ϊ��!';
			 result.message=message;
			 result.result=false;
			 return result;
		}
		
		var tmpChangeData = $('#tt').datagrid("getChanges");
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
		var tmpChangeData = $('#tt').datagrid("getChanges");
		if(tmpChangeData.length>0){
			return true;
		}
	}
	//�����ȷ���  ҳ������δ�޸�  ����false
	return false;
}


