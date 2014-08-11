var perhpLst;
$(function(){
	perhpLst = $('#perhpLst').datagrid({
		title:'���˲�Ȩ��Ϣ��ѯ',
		//���������ַ
		url : ctx+'/bookmanage/book-manage!getAllPrivateRealInfosBySearch.action?time='+new Date(),
		fit : true,
		//���ÿ�п���Զ���Ӧ����ܿ��
		fitColumns: true,
		//ȥ���߿�
		border : false,
		//�Ƿ��з�ҳ��
		pagination:true,
		//pageNumber:1,
		//ÿҳ����
		pageSize:10,
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
			{ title : 'Ȩ����',       field : 'HOL_NAME'   ,width:100 },
			{ title : '����������',   field : 'LEGAL_NAME' ,width:100 },
			{ title : '֤������',     field : 'HOL_CER_TYPE'  ,width:100 },
			{ title : '֤������',     field : 'HOL_CER_NO' ,width:100 },
			{ title : 'Ȩ��������',   field : 'AHOL_TYPE'  ,width:120 },
			{ title : '��Ȩ��¼��',   field : 'HOUSERIGHT_COUNT'  ,width:120 },
			{ title : 'Ԥ�ۼ�¼��',   field : 'PRESALE_COUNT'  ,width:120 },
			//{ title : '������ס����', field : 'REAL_USAGE'  ,width:120 },
			//{ type:'hidden' field : 'HOLDER_ID'},
			{ title : '����',       field:'button',formatter:function(value,rec){return '<span><input type="button" value="��ӡԤ��" onclick=""/></span>';}}
		]],
		onClickCell:function(rowIndex, field, value){
			if(field=="button"){
			    $('#perhpLst').datagrid('selectRow',rowIndex);
				//openBookDetail(this);
			    printPersonalPropertyRight(this);
			}
		}
	 });
   });

    /*//�򿪲鿴�Ǽǲ�����
	function openBookDetail(button){
		var selectedrow = $('#perhpLst').datagrid('getSelected');
		var objWindow = {
				//����Ԫ�ص�id
				id: 'wind_djbyl',
				//����iframe��src
				src: ctx+'/bookmanage/registerView.action?reg_unit_type='+selectedrow.REG_UNIT_TYPE,
				//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
				destroy: true,
				//���ڱ���
				title: '�Ǽǲ�����',
				//���ڿ�
				width: 900,
				//���ڸ�
				height: 620,
				modal: true,
				//������iframe��window�����onLoad�ص���������
				onLoad:	function(){
				    //proc.regcode=regcode;
					//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
					//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
					//this.openerWindow = window;
					
					//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
					this.args = {
						userDataGrid: perhpLst
					};

					this.init(selectedrow);
				}
			};
		openInTopWindow(objWindow);
	};*/
    //��ӡ���˲�Ȩ��Ϣ
    function printPersonalPropertyRight(button){
    	 var selectedrow = $('#perhpLst').datagrid('getSelected');
		 /*var mortIDs = [];
		 var j = 0;
		 $.each(allItems,function(indx,item){
			 if(item.checked == 'true'){
				 mortIDs.push(item.MORT_ID);
				 j++;
			 }
		 });*/
		 var proc = {'url':'/reportmanage/private-real-report!report.action?holder_id='+selectedrow.HOLDER_ID}
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
		    /*if(j == 0){
		    	$.messager.alert("��ʾ","�빴ѡ��Ҫ��ӡ�ļ�¼��","warning");
		    	return;
		    }*/
			openInTopWindow(objWindow2);	
    }
    
	
	function submit1(){
		$('#perhpLst').datagrid('loadData',{total:0,rows:[]});
	    $('#perhpLst').datagrid('load',$('#simpleform').serializeJson());
	}
	function clear1(){
		$('#simpleform')[0].reset();
	}
	

	
