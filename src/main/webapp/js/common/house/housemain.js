var houseLst;
$(function(){
	houseLst = $('#houseLst').datagrid({
		title:'������Ϣ',
		//���������ַ
		url : ctx+'/bookmanage/book-manage!getHouseInfosBySearch.action?time='+new Date(),
		fit : true,
		//���ÿ�п���Զ���Ӧ����ܿ��
		fitColumns: true,
		//ȥ���߿�
		border : false,
		//�Ƿ��з�ҳ��
		pagination:true,
		//ÿҳ����
		pageSize:10,
		//�Ƿ�����������һ����ʾ�кŵ���
		rownumbers:true,
		//����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		idField: 'book_code',
		//�������Ƿ�����ʾ��ͬ����ɫ					
		striped:true,
		//ֻ����ѡһ��
		singleSelect:true,
		//�Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		//checkOnSelect:true,
		//�Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		//selectOnCheck:true,
		columns :[[ 
			{ title : '�ڵر��',     field : 'PARCEL_CODE'   ,width:80},
			{ title : '���ݱ���',     field : 'HOUSE_CODE'   ,width:80},
			{ title : '¥��',         field : 'BUILDING_NAME'   ,width:100},
			{ title : '����',         field : 'BUILD_NO'   ,width:80},
			{ title : '����',         field : 'UNIT_NO'   ,width:80},
			{ title : '���ز�֤��',   field : 'CER_CODE'       ,width:80},
			{ title : 'Ȩ��������',   field : 'R_COLL_NAMES' ,width:80},
			{ title : '��Ȩ״̬',     field : 'RIGHT_STATUS'  ,width:100},
			{ hidden:'true',         field : 'REG_UNIT_TYPE',width:100},
			{ hidden:'true',         field : 'BOOK_CODE',width:100},
			{ hidden:'true',         field : 'REG_CODE',width:100},
			{ hidden:'true',         field : 'WHERE_CODE',width:100},
			{ title : '����',        field:'button',formatter:function(value,rec){return '<span><input type="button" value="�ǼǱ�����" onclick=""/></span>';},width:100}
		]],
		onClickCell:function(rowIndex, field, value){
			if(field=="button"){
			    $('#houseLst').datagrid('selectRow',rowIndex);
				openBookDetail(this);
			}
		}
	 });
    });

    //�򿪲鿴�Ǽǲ�����
	function openBookDetail(button){
		var selectedrow = $('#houseLst').datagrid('getSelected');
		var objWindow = {
				//����Ԫ�ص�id
				id: 'wind_djby2',
				//����iframe��src
				src: ctx+'/bookmanage/book-manage!home.action?reg_unit_type='+selectedrow.REG_UNIT_TYPE,
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
					/*this.args = {
						userDataGrid: houseLst
					};*/
					this.init(selectedrow);
				}
			};
//		var bus_type=proc.procdefName;
//		//����Ȩ
//		if(busType.ALL == bus_type_id){
//			objWindow.src = ctx+'/common/register-preview.action';
//		}
//		//ʹ��Ȩ
//		else if(busType.USE == bus_type_id){
//			objWindow.src = ctx+'/common/registerbook/registeruse.action';
//		}
//		//��Ѻ
//		else if(busType.MORT == bus_type_id){
//			objWindow.src = ctx+'/common/registerbook/registermort.action';
//		}
//		//���
//		else if(busType.ATTACH == bus_type_id){
//			objWindow.src = ctx+'/common/registerbook/registerattach.action';
//		}else if(busType.CORRECTION == bus_type_id){
//			$.ajax({
//				url:ctx+'/common/register!getReg_relationship.action',
//				dataType : 'json',
//				type : 'post',
//				data : {"proc_id":proc_id,"time":new Date()},
//				async:false,
//				success : function(data) {
//					if(reg_unit_type.HOUSE == data.reg_unit_type){
//						objWindow.src = ctx+'/common/register-preview.action';
//					}else if(reg_unit_type.PARCEL == data.reg_unit_type){
//						objWindow.src = ctx+'/common/registerbook/registeruse.action';
//					}
//				}
//			});
//			
//		}
		openInTopWindow(objWindow);
	};
	
	function submit1(){
		    // $('#simpleform').submit();
		    $('#houseLst').datagrid('loadData',{total:0,rows:[]});
		    $('#houseLst').datagrid('load',$('#simpleform').serializeJson());
	}
	function clear1(){
		$('#simpleform')[0].reset();
	}

	
