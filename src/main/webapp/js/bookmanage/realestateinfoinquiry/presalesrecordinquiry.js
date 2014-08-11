var presalesLst;
$(function(){
	presalesLst = $('#presalesLst').datagrid({
		title:'Ԥ�۱�����Ϣ',
		//���������ַ
		url : ctx+'/bookmanage/book-manage!getAllPresaleInfosBySearch.action?time='+new Date(),
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
					{ title : '��Ŀ����',     field : 'PRO_NAME'   ,width:100 },
					{ title : 'Ԥ�����֤��',   field : 'PRE_LIC_NO' ,width:100 },
					{ title : 'Ԥ�ۺ�ͬ��',   field : 'PRE_CON_NO'  ,width:100},
					{ title : '��ͬ����',     field : 'ACON_DATE' ,width:100 },
					{ title : '��������',   field : 'AREC_DATE'  ,width:100 },
					{ title : 'Ԥ����',   field : 'PRESALER'  ,width:100 },
					{ title : 'Ԥ����',   field : 'PREBUYER'  ,width:100 },
					{ title : '�Ƿ��ѱ���',   field : 'REC_STATE'  ,width:100 },
					{ hidden:'true',field : 'WHERE_CODE' },
					{ hidden:'true',field : 'checked' }
					///{ title : '����',       field:'button',formatter:function(value,rec){return '<span><input type="button" value=" ��  �� " onclick=""/></span>';}}
		]],
		toolbar:'#printdiv',
		onClickCell:function(rowIndex, field, value){
			if(field=="button"){
			    $('#presalesLst').datagrid('selectRow',rowIndex);
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
	 
	$('#presale_print').click(function(){
		 var allItems = $('#presalesLst').datagrid("getRows");
		 var presaleIDs = [];
		 var j = 0;
		 $.each(allItems,function(indx,item){
			 if(item.checked == 'true'){
				 presaleIDs.push(item.PRE_REC_ID);
				 j++;
			 }
		 });
		 var proc = {'url':'/reportmanage/presale-report!report.action?pre_rec_id='+presaleIDs.join(",")}
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
		var selectedrow = $('#presalesLst').datagrid('getSelected');
		var objWindow = {
				//����Ԫ�ص�id
				id: 'wind_djbyl',
				//����iframe��src
				src: ctx+'/bookmanage/book-manage!home.action?reg_unit_type='+selectedrow.REG_UNIT_TYPE+'&time='+new Date(),
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
						userDataGrid: presalesLst
					};

					this.init(selectedrow);
				}
			};
		openInTopWindow(objWindow);
	};
	
	function submit1(){
		$('#presalesLst').datagrid('loadData',{total:0,rows:[]});
	    $('#presalesLst').datagrid('load',$('#simpleform').serializeJson());
	}
	function clear1(){
		$('#simpleform')[0].reset();
	}
	

	
