var easementBILst;
$(function(){
	easementBILst = $('#easementBILst').datagrid({
		title:'����Ȩ�Ǽ���Ϣ',
		//���������ַ
		url : ctx+'/bookmanage/book-manage!getAllEasementInfosBySearch.action?time='+new Date(),
		fit : true,
		//���ÿ�п���Զ���Ӧ����ܿ��
		fitColumns: true,
		//ȥ���߿�
		border : false,
		//�Ƿ��з�ҳ��
		pagination:true,
		//pageNumber:1,
		pageList:[3,6,9,12],
		//ÿҳ����
		pageSize:6,
		//�Ƿ�����������һ����ʾ�кŵ���
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
					{ title : '����',     field : 'ADDRESS' ,width:100 },
					{ title : '����Ȩ������',   field : 'HOLDER_NAME'  ,width:100 },
					{ title : '֤������',   field : 'HOLDER_NO'  ,width:100 },
					{ title : '�Ǽ�����',   field : 'AREG_DATE'  ,width:100 },
					{ hidden:'true',field : 'BOOK_CODE',width:100 },
					{ hidden:'true',field : 'REG_UNIT_TYPE',width:100 },
					{ hidden:'true',field : 'WHERE_CODE',width:100 },
					{ hidden:'true',field : 'checked',width:100 },
					{ title : '����',       field:'button',formatter:function(value,rec){return '<span><input type="button" value=" ��  �� " onclick=""/></span>';}}
				]],
				toolbar:'#printdiv',
		onClickCell:function(rowIndex, field, value){
			if(field=="button"){
			    $('#easementBILst').datagrid('selectRow',rowIndex);
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
	
	//����Ȩ�ǼǱ��������
	$('#easement_print').click(function(){
		 var allItems = $('#easementBILst').datagrid("getRows");
		 var easementIDs = [];
		 var j = 0;
		 $.each(allItems,function(indx,item){
			 if(item.checked == 'true'){
				 easementIDs.push('{"easementId":"'+item.EASEMENT_ID+'","reg_unit_type":"'+item.REG_UNIT_TYPE+'"}');
				 j++;
			 }
		 });
		 var proc = {'url':'/reportmanage/easement-report!report.action?items='+easementIDs.join(",")}
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
		   /* if(j == 0){
		    	$.messager.alert("��ʾ","�빴ѡ��Ҫ��ӡ�ļ�¼��","warning");
		    	return;
		    }*/
			openInTopWindow(objWindow2);
	});
	
   });

    //�򿪲鿴�Ǽǲ�����
	function openBookDetail(button){
		var selectedrow = $('#easementBILst').datagrid('getSelected');
		var objWindow = {
				//����Ԫ�ص�id
				id: 'wind_djbyl',
				//����iframe��src
				src: ctx+'/bookmanage/book-manage!home.action?reg_unit_type='+selectedrow.REG_UNIT_TYPE+'&realestate_type='+enumdata.EASEMENTBI+'&time='+new Date(),
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
						userDataGrid: easementBILst
					};

					this.init(selectedrow);
				}
			};
		openInTopWindow(objWindow);
	};
	
	function submit1(){
		$('#easementBILst').datagrid('loadData',{total:0,rows:[]});
	    $('#easementBILst').datagrid('load',$('#simpleform').serializeJson());
	}
	function clear1(){
		$('#simpleform')[0].reset();
	}
	

	
