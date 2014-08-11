var remarksBILst;
$(function(){
	remarksBILst = $('#remarksBILst').datagrid({
		title:'��ע�Ǽ���Ϣ',
		//���������ַ
		url : ctx+'/bookmanage/book-manage!getParcelInfosBySearch.action?time='+new Date(),
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
			{ title : '��ע���',     field : 'REG_CODE'   ,width:100 },
			{ title : '�Ǽǵ�Ԫ����',   field : 'REG_UNIT_TYPE' ,width:80 },
			{ title : '�ڵغ�',   field : 'PARCEL_CODE'  ,width:100 },
			{ title : '���ز�����',     field : 'CER_NO'        ,width:100 },
			{ title : '¼����',   field : 'R_COLL_NAMES'  ,width:100 },
			{ title : '��ִ����',   field : 'BOOK_CODE'  ,width:100 },
			{ title : 'ִ�л���',   field : 'REAL_USAGE'  ,width:100 },
			{ title : '�Ǽ�����',   field : 'RIGHT_STATE'  ,width:100 },
			{ title : '¼������',   field : 'PARCEL_AREA'  ,width:100 },
			{ hidden:'true',field : 'WHERE_CODE'},
			/*{ title : '����',       field:'button',formatter:function(value,rec){return '<span><input type="button" value="�ǼǱ�����" onclick=""/></span>';}}*/
		]],
		toolbar:'#printdiv',
		onClickCell:function(rowIndex, field, value){
			if(field=="button"){
			    $('#remarksBILst').datagrid('selectRow',rowIndex);
				openBookDetail(this);
			}
		}
	 });
	//����ǼǱ��������
	$('#remark_print').click(function(){
		 var allItems = $('#remarksBILst').datagrid("getSelected");
		 var demurrerIDs = [];
		 var j = 0;
//		 $.each(allItems,function(indx,item){
//			 if(item.checked == 'true'){
//				 demurrerIDs.push('{"demurrerId":"'+item.DEMURRER_ID+'","reg_unit_type":"'+item.REG_UNIT_TYPE+'"}');
//				 j++;
//			 }
//		 });
		// var proc = {'url':'/reportmanage/demurrer-report!report.action?items='+demurrerIDs.join(",")}
		 var objWindow2 = {
					//����Ԫ�ص�id
					id: 'wind_djby2',
					//����iframe��src
					src: ctx+'/jsp/workflow/spec-work-window!home.action?jds_sqs_tw='+enumdata.BACKLANGUAGESQS+'&time='+new Date(),
					//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
					destroy: true,
					//���ڱ���
					title: '__',
					//���ڿ�
					width: 800,
					//���ڸ�
					height: 598,
					modal: true,
					//������iframe��window�����onLoad�ص���������
					onLoad:	function(){
					   var items = {'showhide2':'true','bus_id':'437','reg_code':'dxtx201405061033'};
					   this.init(items);
					}
				};
//		    if(j == 0){
//		    	$.messager.alert("��ʾ","�빴ѡ��Ҫ��ӡ�ļ�¼��","warning");
//		    	return;
//		    }
			openInTopWindow(objWindow2);
	});
   });

    //�򿪲鿴�Ǽǲ�����
	function openBookDetail(button){
		var selectedrow = $('#remarksBILst').datagrid('getSelected');
		var objWindow = {
				//����Ԫ�ص�id
				id: 'wind_djbyl',
				//����iframe��src
				src: ctx+'/bookmanage/book-manage!home.action?reg_unit_type='+selectedrow.REG_UNIT_TYPE,
				//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
				destroy: true,
				//���ڱ���
				title: '�Ǽǲ�����',
				//���ڿ�
				width: 988,
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
						userDataGrid: remarksBILst
					};

					this.init(selectedrow);
				}
			};
		openInTopWindow(objWindow);
	};
	
	function submit1(){
		$('#remarksBILst').datagrid('loadData',{total:0,rows:[]});
	    $('#remarksBILst').datagrid('load',$('#simpleform').serializeJson());
	}
	function clear1(){
		$('#simpleform')[0].reset();
	}
	

	
