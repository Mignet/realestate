/*********************************************************************************
*��  ��  ��  ��: acc-holder.js
*��  ��  ��  ��: ����Ǽ������֮Ȩ���˱��
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Joyon
*��  ��  ��  �ڣ� 2014-03-01
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/
var holLst = ['060016','060027','060030','060017','060019','060018','060022','060029'];
var changeLastEditIndex_hol;
$(function() {
	 $('#table_holder_cbx').datagrid({
		title:'���ǰȨ������Ϣ',
		height:200,
		url:ctx+"/houseownership/correction!getNowHolder.action?time="+new Date()+"&proc_id="+proc_id,
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
		//pageSize : 10,
		// �Ƿ�����������һ����ʾ�кŵ���
		rownumbers : true,
		// ����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		//idField : 'jjclmc',
		// �������Ƿ�����ʾ��ͬ����ɫ
		striped : true,
		// ֻ����ѡһ��
		singleSelect : true,
		
		columns : [ [{
			           title : '�� ��',  
			           field : 'button',
			           formatter : function(value,rec)
			           {return '<span><input type="button" value="�� ��" onclick=""/></span>';},
			           width : 60
		            },{
		     			title : 'Ȩ����',
		     			field : 'HOL_NAME',
		     			width : 80
		     		}, {
		     			title : 'Ȩ��������',
		     			field : 'HOL_TYPE',
		     			width : 80
		     		}, {
		     			title : '֤������',
		     			field : 'CER_TYPE',
		     			width : 90
		     		}, {
		     			title : '���֤����/��֯��������',
		     			field : 'CER_NO',
		     			width : 150
		     		}, {
		     			title : '�ݶ�',
		     			field : 'POSITION',
		     			width : 40
		     		}, {
		     			title : '��ַ',
		     			field : 'ADDRESS',
		     			width : 160
		     		}, {
		     			title : '����',
		     			field : 'LEGAL_NAME',
		     			width : 80
		     		}, {
		     			title : 'Ȩ��������',
		     			field : 'HOL_REL',
		     			width : 90
		     		}, {
		     			hidden:'true',
		     			field : 'HOLDER_ID',
		     			width : 50
		     		}, {
		     			hidden:'true',
		     			field : 'REG_UNIT_ID',
		     			width : 50
		     		}, {
		     			hidden:'true',
		     			field : 'REG_UNIT_TYPE',
		     			width : 50
		     		}] ],
		     		onClickCell:function(rowIndex, field, value){
		    			if(field=="button"){
		    				$('#table_holder_cbx').datagrid('selectRow',rowIndex);
		    				holDetail();
		    			}
		    		},
		     		// ��ͷ����ӹ�������
		     		onClickRow : function() {},
		     		onLoadSuccess : function() {}
	});
	
	$('#div_change_holder_detail').datagrid({
		title:'�����Ȩ������Ϣ',
		height:200,
		url:ctx+"/houseownership/correction!getNowHolder.action?time="+new Date()+"&proc_id="+proc_id,
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
		             // ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
		             
		             // {field:'ck',checkbox:true},
		              {
		            	   title : '�� ��',  
				           field : 'button',
				           formatter : function(value,rec)
				           {return '<span><input type="button" value="�༭" onclick=""/></span>';},
				           width : 60
	                  },{
			     			title : 'Ȩ����',
			     			field : 'HOL_NAME',
			     			width : 80
			     		}, {
			     			title : 'Ȩ��������',
			     			field : 'HOL_TYPE',
			     			width : 80
			     		}, {
			     			title : '֤������',
			     			field : 'CER_TYPE',
			     			width : 90
			     		}, {
			     			title : '���֤����/��֯��������',
			     			field : 'CER_NO',
			     			width : 150
			     		}, {
			     			title : '�ݶ�',
			     			field : 'POSITION',
			     			width : 40
			     		}, {
			     			title : '��ַ',
			     			field : 'ADDRESS',
			     			width : 160
			     		}, {
			     			title : '����',
			     			field : 'LEGAL_NAME',
			     			width : 80
			     		}, {
			     			title : 'Ȩ��������',
			     			field : 'HOL_REL',
			     			width : 90
			     		}, {
			     			hidden:'true',
			     			field : 'HOLDER_ID',
			     			width : 50
			     		}, {
			     			hidden:'true',
			     			field : 'REG_UNIT_ID',
			     			width : 50
			     		}, {
			     			hidden:'true',
			     			field : 'REG_UNIT_TYPE',
			     			width : 50
			     		}, {
			     			hidden:'true',
			     			field : 'RIGHT_REL_ID',
			     			width : 50
			     		}]],
		             toolbar : [{
		            	 id : 'holder_save',
		            	 text : '����',
		            	 iconCls : 'icon-ok',
		            	 disabled : false,
		            	 handler : doSaveHol
		             },'+',{
		            	 id : 'holder_add',
		            	 text : '����',
		            	 iconCls : 'icon-add',
		            	 disabled : false,
		            	 handler : doAddHol
		             },'-', {
		     			id : 'holder_delete',
		    			text : 'ɾ��',
		    			iconCls : 'icon-remove',
		    			disabled : false,
		    			handler : doDelHol
		    		}],
		    		onClickCell:function(rowIndex, field, value){
		    			if(field=="button"){
		    				$('#div_change_holder_detail').datagrid('selectRow',rowIndex);
		    				holEdit();
		    			}
		    		},
		             // ��ͷ����ӹ�������
		             onClickRow : changeRowClick_hol,
		             onAfterEdit:function(index,row){     
		            	 row.editing = false;     
		            	 $('#div_change_holder_detail').datagrid('refreshRow', index);     
		             },
		             onLoadSuccess : function(data) {
		            	 //���ݼ��سɹ�ʱ  checkbox ���ݻ���
		            	 //alert(JSON.stringify(data.rows));
		            	 var rows = data.rows;
		            	 for(var i = 0;i<rows.length;i++){
		            		 for(var j=0;j<change_data_list.length;j++){
		            			 if(rows[i].CHANGE_CODE == change_data_list[j].value){
		            				 $("#"+change_data_list[j].value).attr("checked","checked");
		            			 }
		            			 
		            		 }
		            		 //����з�������   ����·�������value��ֵ
//		     				if(rows[i].CHANGE_CODE==change_data_item.HOUSE_ATTR){
//		     					new_house_attr.value=rows[i].NEW_DATA;
//		     				}
		            	 }
		            	 
		             }
	});
});

/**
 * ������ĺ������
 * ��div_change_holder_detail���������ɾ���ĵ�����Ӧ�õ���̨
 */
function doSaveHol() {
	top.$.messager.alert('��ʾ', 'hello,boy', function(){});
};
/**
 * ����һ��Ȩ���˵�����
 * ��div_change_holder_detail�����'��'������Ӧ�õ���̨
 */
function doAddHol() {
	var lastRowIdx = $('#div_change_holder_detail').datagrid('getRows').length-1;
	var row = $('#div_change_holder_detail').datagrid('selectRow',lastRowIdx);
	if(row.HOL_NAME){
		$('#div_change_holder_detail').datagrid('appendRow',
				{HOL_NAME:'',HOL_TYPE:'',CER_TYPE:'',CER_NO:'',POSITION:'',ADDRESS:'',LEGAL_NAME:'',HOL_REL:''});
		changeLastEditIndex_hol = $('#div_change_holder_detail').datagrid('getRows').length-1; 
		//�༭��ʱ�������  ��ȡ��������  ���
		var rows = $('#div_change_holder_detail').datagrid('getRows');
	    for ( var i = 0; i < rows.length; i++) {
	    	 $('#div_change_holder_detail').datagrid('endEdit', i);
	    }
	    
	    $('#div_change_holder_detail').datagrid('selectRow', changeLastEditIndex_hol);  
	    $('#div_change_holder_detail').datagrid('beginEdit', changeLastEditIndex_hol);  
	}
};
/**
 * ɾ��һ��Ȩ���˵�����
 * ��div_change_holder_detail���������ɾ���ĵ�����Ӧ�õ���̨
 */
function doDelHol() {
	var selectRow = $('#div_change_holder_detail').datagrid('getSelected');
	alert(selectRow);
};

/**********************************************************************************
*��������: ������ɸѡ�����¼�
*����˵��: ��ѡ��ѡ���¼�  ѡ��ʱ  �����һ��   δѡ��ʱ   ɾ����һ��
*����˵��: 
*�� �� ֵ: 
*��������: Joyon
*��������: 2014-06-19
*�޸���ʷ: 
***********************************************************************************/
function cbxClick_hol(cbx){
	var cbx_id;
	var change_name;
	cbx_id = cbx.id;
	change_name = $('#lbl_'+cbx_id).text();
};

function holDetail(){
	var row = $('#table_holder_cbx').datagrid('getSelected');
	openInTopWindow({
		// ����Ԫ�ص�id
		id : 'detail_holder_win',
		// ����iframe��src
		src : ctx+'/jsp/common/holder/detailHolder.jsp',
		// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
		destroy : true,
		// ���ڱ���
		title : '�鿴Ȩ������Ϣ',
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
function holEdit(){
	var holarr = new Array();
	var row = $('#div_change_holder_detail').datagrid('getSelected');
	for(var i = 0; i < holLst.length; i++){
		if($('#'+holLst[i]).is(':checked')){
			holarr.push(holLst[i]);
		}
	} 
	if(row.RIGHT_REL_ID){
	openInTopWindow({
		// ����Ԫ�ص�id
		id : 'edit_holder_win',
		// ����iframe��src
		src : ctx+'/jsp/common/holder/editHolder.jsp',
		// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
		destroy : true,
		// ���ڱ���
		title : '�༭Ȩ����',
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
					userDataGrid : userDataGrid,
					array:holarr
			};
			this.init(row,holarr);
		}
	});
	}
}

function changeRowClick_hol(rowIndex, rowData){
	//�����ʱ����༭״̬    ֻ�ڳ��󻷽ڼ���
	if(proc_node!= state1.string1){
		return;
	}
       
    if (changeLastEditIndex_hol != rowIndex) {
     		 $('#div_change_holder_detail').datagrid('endEdit', changeLastEditIndex_hol);
     		 $('#div_change_holder_detail').datagrid('beginEdit', rowIndex);
     		
    }
    changeLastEditIndex_hol = rowIndex;
    for(var i=0;i<holLst.length;i++){ 	
        setEditorCombobox(holLst[i],rowIndex);
    }
}


