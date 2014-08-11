var userDataGrid;	
//var row;
//��ʼ������.		
$(function(){
	
	 userDataGrid = $('#table_user').datagrid({
		loadFilter:pagerFilter,//�÷�����enum-data.js��
		fit:true,
		//���������Դ
		url:'workflow!getworkflowList.action',
		//���ÿ�п���Զ���Ӧ����ܿ��
		fitColumns: true,
		//ȥ���߿�
		border : true,
		//�Ƿ��з�ҳ��
		pagination:true,
		//ÿҳ����
		pageSize:20,
		//�Ƿ�����������һ����ʾ�кŵ���
		rownumbers:true,
		//����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		//idField: 'user_id',
		//�������Ƿ�����ʾ��ͬ����ɫ					
		striped:true,
		//ֻ����ѡһ��
		singleSelect:true,
		//�Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		//checkOnSelect:true,
		//�Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		//selectOnCheck:true,
		//����������
		columns:[[
			//ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
			{field:'busno', title:'ҵ����',align :'center', width:60, sortable:true},
			{field:'procName', title:'ҵ������', align :'left',width:120},
			{field:'activName',	title:'��ǰ����',align :'center', width:30},
			{field:'busprop',	title:'ҵ������',align :'center', width:20},
			{field:'totallimit',	title:'������',align :'center', width:30},
			{field:'nodelimit',	title:'����ʱ��',align :'center', width:30},
			{field:'nodesurp',	title:'����ʣ��',align :'center', width:30},
			{field:'acctime',	title:'����ʱ��',align :'center', width:30},
			{field:'procstate',	title:'����״̬',align :'center', width:30},
			{field:'button',formatter:function(value,rec){return '<span><input  type="button" value="����" onclick=""/></span>';}}
		]],
		onClickRow : function() {
			//�����ʱ����༭������ɾ������ť
			//alert("onClickRow  event");
			//btn_test_flow()
			$('#user_edit').linkbutton('enable');
			$('#user_delete').linkbutton('enable');
		},
		onClickCell:function(rowIndex, field, value){
			//alert("onclickcell event"+rowIndex+field+value);
			if(field=="button"){
				$('#table_user').datagrid('selectRow',rowIndex);
				btn_bl(this);
			}
		},
		onLoadSuccess : function() {
			//������Ͻ��á��༭������ɾ������ť
			$('#user_edit').linkbutton('disable');
			$('#user_delete').linkbutton('disable');
		}
	});
	
	//ѡ������ĳһ�е����ݡ�
	function getSelected(func){
		var selectedrow = $('#table_user').datagrid('getSelected');
		
		if (selectedrow){
			row = selectedrow;
			//������غ���
			func.call(this,selectedrow);
		}
		else{
			
			$.messager.alert('��ʾ��','����ѡ�б���е�ĳһ��.');
		}
	};

	//����
	function doAdd() {		
		openInTopWindow({
			//����Ԫ�ص�id
			id: 'add_user_win',
			//����iframe��src
			src: '../user/add.jsp',
			//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
			destroy: true,
			//���ڱ���
			title: '�����û�',
			//���ڿ�
			width: 700,
			//���ڸ�
			height: 550,
			modal: true,
			//������iframe��window�����onLoad�ص���������
			onLoad:	function(){
				//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
				//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
				this.openerWindow = window;
				//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
				this.args = {
					userDataGrid: userDataGrid
				};
				this.init();
			}
		});
	};

	
	

	//˫�������ĳһ�еĴ������¼�
	function rowDblclick(rowIndex,row){
		var i = 0;
		var props = [];
		
		for(var p in row){
			props[i++]= p+' = '+row[p];
			
		}
		//alert(props.join(',\n'));
		//info(row);
	};
	
	//��������ʵ����ѯ
	function searchProcint(){
		//$('#searchform').form()
		//$('#dg_proctask').datagrid('load',{});

		var fields = $("#procinstSearchform").serializeArray();
		var o = {};
		jQuery.each( fields, function(i, field){
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
				
			}
		});
		//console.debug(o);
		$('#dg_procinst').datagrid('load',o);
		//console.info("form : "+ o.procName );
		
	};	
	
	$('#simpleform').form({
		dataType: 'json',
		url: 'workflow!getUrlSpecify.action',
		success: function(data){
			
			userDataGrid.datagrid('loadData',data);
		}
	});
	
	
	
});

function submit1(){
	$('#simpleform').submit();
}

function openTab(title,url) {
    var title = title||"";
    /*if ($('#tabs').tabs('exists', title)) {
        $('#tabs').tabs('select', title);
    } else {*/
		$('#tt').tabs('add', {
			title: title,
			content: '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%"></iframe>',
			closable: true
		});
    //}
}

function btn_bl(button){
		var selectedrow = $('#table_user').datagrid('getSelected');
					//alert(selectedrow[0].wiId+"++++");
					var objs={					
						url:"workflow!getUrlSpecify.action",
						type:'post',
						data:{"wiId":selectedrow.wiId,"time":new Date()},
						success:function(data){
							//alert(data);
							if(!data){
								alert("�õ�ַ�����ڣ�"+data);							
							}
							else if(data=='null'||data==null||data==''||data==undefined )
							{
								alert("��ַΪnull");
							}else if(data.indexOf("qualityinspection/inspection-index")!=-1){
							
//								if(data.indexOf(ctx)==-1){
//									data = ctx+data;
//								}
								if(data.indexOf(ctx+"/jsp")==-1){
									data = ctx+"/jsp"+data;
								}
								data="/dxtx_re/common/work-window!frame.action?procdefId="+selectedrow.procdefId+"&nodeid="+selectedrow.activdefId;
								parent.openTab('�칫ҳ��',data);
							}
							else
							{
								data="/dxtx_re/common/work-window!frame.action?procdefId="+selectedrow.procdefId+"&nodeid="+selectedrow.activdefId;
								parent.openTab('�칫ҳ��',data);
							}
						}		
					};
					
					$.ajax(objs);
		
}
