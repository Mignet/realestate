var ownershipBILst;
$(function(){
	ownershipBILst = $('#ownershipBILst').datagrid({
		title:'��Ȩ�Ǽ���Ϣ',
		//���������ַ
		url : ctx+'/bookmanage/book-manage!getHouseInfosBySearch.action?time='+new Date(),
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
			{ title : '�Ǽǵ�Ԫ����',     field : 'REG_UNIT_TYPE'   ,width:100 },
			{ title : '�ڵغ�',   field : 'PARCEL_CODE' ,width:100 },
			{ title : '���ز�����',   field : 'CER_NO'  ,width:100 },
			{ title : 'ת�Ʒ�ʽ',     field : 'REAL_USAGE' ,width:100 },
			{ title : '��������',   field : 'RIGHT_STATE'  ,width:100 },
			{ title : 'Ȩ��������',   field : 'R_COLL_NAMES'  ,width:100 },
			{ title : '֤������',   field : 'REG_CODE'  ,width:100 },
			{ hidden:'true',field : 'BOOK_CODE' },
			{ hidden:'true',field : 'WHERE_CODE'},
			{ title : '����',       field:'button',formatter:function(value,rec){return '<span><input type="button" value=" ��  �� " onclick=""/></span>';}}
		]],
		toolbar:'#printdiv',
		onClickCell:function(rowIndex, field, value){
			if(field=="button"){
			    $('#ownershipBILst').datagrid('selectRow',rowIndex);
				openBookDetail(this);
			}
		}
	 });
   });

    //�򿪲鿴�Ǽǲ�����
	function openBookDetail(button){
		var selectedrow = $('#ownershipBILst').datagrid('getSelected');
		var objWindow = {
				//����Ԫ�ص�id
				id: 'wind_djbyl',
				//����iframe��src
				src: ctx+'/bookmanage/book-manage!home.action?reg_unit_type='+selectedrow.REG_UNIT_TYPE+'&realestate_type='+enumdata.OWNERSHIPBI+'&time='+new Date(),
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
						userDataGrid: ownershipBILst
					};

					this.init(selectedrow);
				}
			};
		openInTopWindow(objWindow);
	};
	
	function submit1(){
		$('#ownershipBILst').datagrid('loadData',{total:0,rows:[]});
	    $('#ownershipBILst').datagrid('load',$('#simpleform').serializeJson());
	}
	function clear1(){
		$('#simpleform')[0].reset();
	}
	

	
