//��ʼ������.	
//var proc=this.parent.proc;
var proc = 1;
//
//var proc_node = proc.activName;
//var proc_id = proc.procId;
var proc_id = 1000016843;


var houseDataGrid;
$(function() {

	houseDataGrid = $('#table_house').datagrid({
		title:'���ز���Ϣ',
		height:400,
		url:ctx+"/mortgage/morsetup!getHouseInfo.action?time="+new Date()+"&proc_id="+proc_id,
		// ���ÿ�п���Զ���Ӧ����ܿ��
		autoRowHeight : true,
		fitColumns : true,
		// ȥ���߿�
		border : true,
		striped : true,
		// �Ƿ��з�ҳ��
		pagination : true,
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
		
		columns : [ [
		     		// ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������

		     		// {field:'ck',checkbox:true},
		     		{
		     			title : '�ڵغ�',
		     			field : 'PARCEL_CODE',
		     			width:80
		     			
		     		}, {
		     			title : '�ڵ�����',
		     			field : 'LAND_ADDRESS',
		     			width:100
		     			
		     		},
		     		
		     		
		     		{
		     			title : '����������',
		     			field : 'BUILDING_NAME',
		     				width:100
		     		}, 
		     		{
		     			title : '����',
		     			field : 'BUILD_NO',
		     			width:50
		     			
		     		}, 
		     		{
		     			title : '����',
		     			field : 'ROOMNAME',
		     			width:50
		     			
		     		},  {
		     			title : '��Ŀ����',
		     			field : 'PRO_NAME',
		     			width:100
		     			
		     		},
		     		{
		     			title : '����',		     		
		     			field:'button',
		     			formatter:function(value,rec){return '<span><input  type="button" value="�Ǽǲ�Ԥ��" onclick=""/></span>';}
		     		}
		     		

		     		] ],
		     		// ��ͷ����ӹ�������
		     		onClickRow : function() {
		     		},
		     		onClickCell:function(rowIndex, field, value){
		    			//alert("onclickcell event"+rowIndex+field+value);
		    			if(field=="button"){
		    				$('#table_house').datagrid('selectRow',rowIndex);
		    				regpreview(this);
		    			}
		    		},
		     		onLoadSuccess : function() {
		     			
		     			
		     		}
	});
	

	
	
});

	// ����
	function regpreview() {
		var selectedrow = $('#table_house').datagrid('getSelected');

		openInTopWindow({
			// ����Ԫ�ص�id
			id : 'regbook_pre',
			// ����iframe��src
			src : ctx+'/jsp/common/registerbook/registermort.jsp?time='+new Date(),
			// �ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy : true,
			// ���ڱ���
			title : '�Ǽǲ�Ԥ��',
			// ���ڿ�
			width : 900,
			// ���ڸ�
			height : 600,
			modal : true,
			// ������iframe��window�����onLoad�ص���������
			onLoad : function() {
				// �˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
				// ��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
				this.openerWindow = window;
				// ����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
				this.args = {
					//userDataGrid : userDataGrid
				};
				this.init(proc);
			}
		});
	}


