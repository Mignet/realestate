var mortgageBILst;
$(function(){
	mortgageBILst = $('#mortgageBILst').datagrid({
		title:'��Ѻ�Ǽ���Ϣ',
		//���������ַ
		url : ctx+'/bookmanage/book-manage!getAllMortInfosBySearch.action?time='+new Date(),
		fit : true,
		//���ÿ�п���Զ���Ӧ����ܿ��
		fitColumns: true,
		//ȥ���߿�
		border : false,
		//�Ƿ��з�ҳ��
		pagination:true,
		//pageNumber:1,
		//ÿҳ����
		pageSize:6,
		//�Ƿ�����������һ����ʾ�кŵ���
		pageList:[3,6,9,12],
		rownumbers:true,
		//pagePosition:'bottom',
		//����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		idField: 'PARCEL_CODE',
		//�������Ƿ�����ʾ��ͬ����ɫ					
		striped:true,
		//ֻ����ѡһ��
		singleSelect:false,
		//�Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		checkOnSelect:true,
		//�Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		selectOnCheck:true,
		columns :[[ 
                    { field:'ck' ,checkbox:true},
					{ title : '�ǼǱ��',     field : 'REG_CODE'   ,width:80 },
					{ title : '�Ǽǵ�Ԫ����',   field : 'REG_TYPE' ,width:80 },
					{ title : '�Ǽǵ�Ԫ���',   field : 'REG_UNIT_CODE'  ,width:80 },
					{ title : '����',     field : 'ADDRESS' ,width:120},
					{ title : '��ѺȨ��',   field : 'MORTGAGEE'  ,width:100 },
					{ title : '��Ѻ����',   field : 'MORT_TYPE_NAME'  ,width:100 },
					{ title : '��Ѻ����',   field : 'AREG_DATE'  ,width:100 },
					{ hidden:'true',field : 'BOOK_CODE',sortable:true,width:100},
					{ hidden:'true',field : 'WHERE_CODE',width:100},
					{ hidden:'true',field : 'checked',width:100},
					{ title : '����',       field:'button',formatter:function(value,rec){return '<span><input type="button" value=" ��  �� " onclick=""/></span>';},width:80}
		]],
		toolbar:'#printdiv',
		onClickCell:function(rowIndex, field, value){
			if(field=="button"){
			    $('#mortgageBILst').datagrid('selectRow',rowIndex);
				openBookDetail(this);
			}
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
	 });
	
	 $('#mortgage_print').click(function(){
		 var allItems = $('#mortgageBILst').datagrid("getRows");
		 var mortIDs = [];
		 var j = 0;
		 $.each(allItems,function(indx,item){
			 if(item.checked == 'true'){
				 mortIDs.push(item.MORT_ID);
				 j++;
			 }
		 });
		 var proc = {'url':'/reportmanage/mortgage-report!report.action?mort_id='+mortIDs.join(",")}
		 var objWindow2 = {
					//����Ԫ�ص�id
					id: 'wind_djby2',
					//����iframe��src
					src: ctx+'/jsp/reportmanage/pdf.jsp?time='+new Date(),
					//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
					destroy: true,
					//���ڱ���
					title: '��ӡԤ��',
					//���ڿ�
					width: 800,
					//���ڸ�
					height: 598,
					modal: true,
					//������iframe��window�����onLoad�ص���������
					onLoad:	function(){
					   this.init(proc);
					}
				};
		    if(j == 0){
		    	$.messager.alert("��ʾ","�빴ѡ��Ҫ��ӡ�ļ�¼��","warning");
		    	return;
		    }
			openInTopWindow(objWindow2);
	 });
	
   });

    //�򿪲鿴�Ǽǲ�����
	function openBookDetail(button){
		var selectedrow = $('#mortgageBILst').datagrid('getSelected');
		var objWindow = {
				//����Ԫ�ص�id
				id: 'wind_djbyl',
				//����iframe��src
				src: ctx+'/bookmanage/book-manage!home.action?reg_unit_type='+selectedrow.REG_UNIT_TYPE+'&realestate_type='+enumdata.MORTGAGEBI+'&time='+new Date(),
				//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
				destroy: true,
				//���ڱ���
				title: '�Ǽǲ�����',
				//���ڿ�
				width: 800,
				//���ڸ�
				height: 598,
				modal: true,
				//������iframe��window�����onLoad�ص���������
				onLoad:	function(){
				    //proc.regcode=regcode;
					//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
					//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
					//this.openerWindow = window;
					
					//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
					this.args = {
						userDataGrid: mortgageBILst
					};

					this.init(selectedrow);
				}
			};
		openInTopWindow(objWindow);
	};
	
	function submit1(){
		$('#mortgageBILst').datagrid('loadData',{total:0,rows:[]});
	    $('#mortgageBILst').datagrid('load',$('#simpleform').serializeJson());
	}
	function clear1(){
		$('#simpleform')[0].reset();
	}
	
	/*function myprint(){
		var selectedrow = $('#mortgageBILst').datagrid('getChecked');
		
		console.info(JSON.stringify(selectedrow));
		alert(JSON.stringify(selectedrow));
		var objWindow2 = {
				//����Ԫ�ص�id
				id: 'wind_djby2',
				//����iframe��src
				src: ctx+'/reportmanage/pdf.action?time='+new Date(),
				//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
				destroy: true,
				//���ڱ���
				title: '��ӡԤ��',
				//���ڿ�
				width: 988,
				//���ڸ�
				height: 598,
				modal: true,
				//������iframe��window�����onLoad�ص���������
				onLoad:	function(){
				   
				}
			};
		openInTopWindow(objWindow2);
	};*/

	

	
