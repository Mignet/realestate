//var djlx = proc.procdefName;

//var proc=this.parent.proc;
//var lcjd = proc.activName;

//var proc_id = proc.procId;

var proc_id = '1000016579';
var itemCanEdit = false;
var lastEditIndex = -1;
//var procNode = proc.activName;
var procNode = "����";
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
var products = [  
           {productid:'1',name:'ԭ��'},  
            {productid:'2',name:'��ӡ��'}, 
            
      ];  
        function productFormatter(value){  
        	value = "ԭ��";
           for(var i=0; i<products.length; i++){  
                if (products[i].productid == value); return products[i].name;  
           }  
           return value;  
        }  
        var cllx = [  
                        {productid:'1',name:'ѡ�Ӽ�'},  
                         {productid:'2',name:'�ؽӼ�'}, 
                         
                   ];  
         function cllxFormatter(value){  
        	 value = "�ؽӼ�";
                   for(var i=0; i<cllx.length; i++){  
                             if (cllx[i].productid == value); return cllx[i].name;  
                    }  
                        return value;  
     } 
//�������flag�������ж�ִ�и��»򱣴����

 //��ʼ��ʼ��       
$(function(){  
	         
               var lastIndex;  
           
            		
            	var jjcl =	{
            	title:'�Ӽ����ϱ�',
            	url:ctx+'/common/recmaterial!getRecmaterial.action?time='+new Date()+"&proc_id="+proc_id,
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
        					{field:'button',formatter:function(value,rec){return '<span><input  type="button" value="����" onclick=""/></span>';}}
        						
        				]],
        				
                toolbar:[{  
                	id:"rec_add",
                   text:'�½�',  
                    iconCls:'icon-add',  
                   handler:function(){  
                       $('#tt').datagrid('endEdit', lastIndex);  
                        $('#tt').datagrid('appendRow',{  
                        	RE_NAME:autoAdd(),  
                           RE_STYLE:'ԭ��',  
                           RE_TYPE:'�ؽӼ�',
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
            	   if(procNode == "����"){
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
                 	top.$.messager.confirm('��ʾ','ȷ��Ҫɾ����һ����',function(){
                 		  var index = $('#tt').datagrid('getRowIndex', row);  
                          $('#tt').datagrid('deleteRow', index);            		
                 	});
                  
                 }else{   
                 	top.$.messager.confirm('��ʾ','��ѡ��Ҫɾ�����У���');             	
                 }  
         	   
         };         



 jjclDataGrid = $('#tt').datagrid(jjcl);	
	
	
 
});  //��ʼ������
//��ȡϵͳ��ǰʱ�� 
  function getTime(){
	 
 	  var myDate = new Date();
 	 //alert(myDate);
 	//alert(JSON.stringify(myDate));
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
  function add_zero(temp)
  {
   if(temp<10) return "0"+temp;
   else return temp;
  }
//�����������
function autoAdd(){
	i++;
	return '�½�����'+i;
}
//��������
function doSave(){
	//$("#tt").datagrid('selectAll');
	//if(endEditing()){
		jjclDataGrid.datagrid('endEdit', lastEditIndex);
		lastEditIndex = -1;
		var inserted = jjclDataGrid.datagrid('getChanges', 'inserted');
		var deleted = jjclDataGrid.datagrid('getChanges', 'deleted');
		var updated = jjclDataGrid.datagrid('getChanges', 'updated');
		 var  rows =  $('#tt').datagrid('getData'); 
		//�����̨��������
		$.ajax({
			url : ctx+'/common/recmaterial!applyEdit.action?proc_id='+proc_id+"&time="+new Date(),
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
	//}
} 

var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){return true}
        if ($('#tt').datagrid('validateRow', editIndex)){
var name = $('#tt').datagrid('getEditor', {index:editIndex,field:'Name'});
            ////$(name.target).val()<span></span> ���޸ĺ�ģ�д�����󵽺�̨���޸ľ����ˡ� 
            $('#tt').datagrid('endEdit', editIndex);
            editIndex = undefined;
        return true;
        } else {
            return false;

        }

}

//�ص����ݶ�̬ˢ��
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
//�������̽ڵ��ж��Ǵ�ģ����ȡ���ݻ��Ǵӽ�����ϱ��ж�ȡ����
function showDatagrid(){
	if(!(lcjd == state1.string0)){
		  alert();
		  jjcl.url = 'jjclDelegate/getJjclList.run?lcslbid='+lcslbid;
		  jjclDataGrid = $('#tt').datagrid(jjcl);	
	}else{
		
		return;
	}				
}

//���Ϲ�����Ť�¼�
function��recmaterial_rel(){
	var selectedrow = $('#tt').datagrid('getSelected');
	selectedrow.RECEIVAL_ID;
	//alert(JSON.stringify(selectedrow));
	var url = ctx+"/common/recmaterial/recmaterial_rel_img.action?date="+new Date();
	selectedrow.proc_id = proc_id;
	window.showModalDialog(url,selectedrow,"dialogWidth=800px;dialogHeight=600px");
	//�򿪴���  obj����
	/*
	var obj={	
		id:"open_img",
		//src:ctx+"/common/recmaterial/recmaterial_rel_img.action?date="+new Date(),
		//���ڿ�
		width: 800,
		//���ڸ�
		title:'���Ϲ���ɨ�����Ϣ',
		height: 600,
		modal: true,	
		destroy:true,
		onLoad:	function(){
			//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
			//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
			//this.openerWindow = window;
			//this.document.getElementById('name').value='����';
			//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
			//this.init(selectedrow);
			this.init(selectedrow);
		}
	};
	obj.src=ctx+"/common/recmaterial/recmaterial_rel_img.action?date="+new Date();
	
	openInTopWindow(obj);	
	*/	
}



