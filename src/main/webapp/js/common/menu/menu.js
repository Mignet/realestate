$(function(){
	$('#productList').treegrid({
		//���������ַ
		url : ctx+'/common/configur!getMenuList.action?time='+new Date(),
		//��ʶ�ֶ�
		idField : 'menu_id',
		fit : true,
		border : false,
		animate:true,
		//�Ƿ�����������һ����ʾ�кŵ���
		rownumbers:true,
		fitColumns : true,
		pagination:true,
		//sortName:'menu_order',
		//sortOrder:'asc',
		remoteSort:false,
		//pageSize:20,
		treeField : 'menu_name',
		resizeHandle:'right',
		columns :[[       
			{ title : '�˵�����', field : 'menu_name', width : 80 },
			{ title : '���ӵ�ַ', field : 'url', width : 80 },
			{ title : '������', field : 'creator', width : 80 },
			{ title : '����ʱ��', field : 'create_date', width : 100 },
			{ title : '˳��', hidden:'true',field : 'menu_order', width : 100},
			{ hidden:'true',field : 'parent_id', width : 100 },
			{ hidden:'true',field : 'menu_id', width : 100 }
			
		]],
		toolbar : [{
			id : 'product_add',
			text : '����',
			iconCls : 'icon-add',
			handler : addMenu
		},'-',{
			id : 'product_edit',
			text : '�༭',
			disabled : true,
			iconCls : 'icon-edit',
			handler : editMenu
		},'-',{
			id : 'product_remove',
			text : 'ɾ��',
			disabled : true,
			iconCls : 'icon-remove',
			handler : removeMenu
		},'-',{
			id : 'upload',
			text : '����',
			disabled : true,
			iconCls : 'icon-upload',
			handler : MoveUp
		},'-',{
			id : 'download',
			text : '����',
			disabled : true,
			iconCls : 'icon-download',
			handler : MoveDown
		}
		
		
		
		],
		onClickRow : function(rowIndex, rowData) {
			//alert(JSON.stringify(rowIndex));
			//������ʱ����༭������ɾ������ť
			$('#product_edit').linkbutton('enable');
			$('#product_remove').linkbutton('enable');
			$('#upload').linkbutton('enable');
			$('#download').linkbutton('enable');
		}
	});
	
	/**
	 * ��Ӳ˵�
	 */
	function addMenu() {
		//��һ�����㴰��
		openInTopWindow({
			id : 'add_product_win',
			src : ctx+'/jsp/common/menu/addmenu.jsp',
			destroy : true,
			title : '�����˵�',
			width : 600,
			height : 350,
			modal: true,
			onLoad: function(){
				//���Ӵ�����ض���ֵ
				this.openerWindow = window;
				var productId = '-1';
				var parentNode = $('#productList').treegrid('getSelected');
				if (parentNode) {
					productId = parentNode.menu_id;
				}
				this.$('#parent_id').combotree('setValue', productId);
				this.init(productId);
			}
		}); 
	};
	
	/**
	 * �༭��Ʒ
	 */
	function editMenu() {
		//��ȡѡ����
		var selectedNode = $('#productList').treegrid('getSelected');
		//�򿪶��㴰��
		openInTopWindow({
			id : 'edit_product_win',
			src : ctx+'/jsp/common/menu/editmenu.jsp',
			destroy : true,
			title : '�༭�˵�:' + selectedNode.menu_name,
			width : 600,
			height : 350,
			modal: true,
			onLoad: function(){
				//���Ӵ�����ض���ֵ
				this.openerWindow = window;
				var _this = this;
						//���ػ�ȡ��������
						_this.$('#product_edit_form').form('load', selectedNode);
					//}
				//});
			}
		}); 
	};
	
	/**
	 * ɾ����Ʒ
	 */
	function removeMenu() {
		var selectedNode = $('#productList').treegrid('getSelected');
		top.$.messager.confirm('ȷ��', '��ȷ��Ҫɾ���˵�[' + selectedNode.menu_name + ']��', function(result){
			if (result) {
				$.ajax({
					url :ctx+"/common/configur!delMenu.action?time="+new Date(),
					data : {
						id : selectedNode.menu_id
					},
					dataType : 'json',
					type : 'post',
					success : function(data) {
						if (data.success) {
							if (selectedNode.parent_id == '-1') {
								$('#productList').treegrid('reload');
								top.$.messager.alert('��ʾ',data.tipMessage, 'info', function() {
									userDataGrid.datagrid('reload');
								});
							} else {
								$('#productList').treegrid('reload', selectedNode.parent_id);
							}
						} else {
							top.$.messager.alert('����', data.errorMessage, 'error', function(){});
						}
					}
				});
			}
		});
	};
});

function MoveUp(){
	var row = $("#productList").treegrid('getSelected'); 
	if( row.menu_order=='1001'){
	top.$.messager.alert('��ʾ',"���ǵ�һ�У��޷�����", 'info', function() {
	});	
	return;	  
	}
	
	
	$.ajax({
		url:ctx+"/common/configur!menuMoveup.action?time="+new Date(),
		data:{"menu.parent_id":row.parent_id,"menu.menu_order":row.menu_order,"menu.menu_id":row.menu_id},
		dataType:"json",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			if(data){
				//$('#productList').treegrid('reload');
				if (row.parent_id == '-1') {
					$('#productList').treegrid('reload');
					//$('#productList').treegrid('select',row.menu_id);
					
				} else {
					$('#productList').treegrid('reload', row.parent_id);
				}
			}
		}
	});
	
	
}
function MoveDown(){
	
	var row = $("#productList").treegrid('getSelected'); 	
	$.ajax({
		url:ctx+"/common/configur!menuMovedown.action?time="+new Date(),
		data:{"menu.parent_id":row.parent_id,"menu.menu_order":row.menu_order,"menu.menu_id":row.menu_id},
		dataType:"json",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			if(data){
				if (row.parent_id == '-1') {
					$('#productList').treegrid('reload');
					
				} else {
					$('#productList').treegrid('reload', row.parent_id);
				}
			}
		}
	});
	
	
	
}
/**
function MoveUp() {
	//alert("����");
    var row = $("#productList").treegrid('getSelected'); 
   
    var index = $("#productList").treegrid('getRowIndex', row);
    //alert("row:"+JSON.stringify(row)+" index:"+index);
    mysort(index, 'up', 'productList');
     
}

function MoveDown() {
	//alert("����");
    var row = $("#productList").treegrid('getSelected');
    var index = $("#productList").treegrid('getRowIndex', row);
    mysort(index, 'down', 'productList');
     
}
 
 
function mysort(index, type, gridname) {
    if ("up" == type) {
        if (index != 0) {
            var toup = $('#' + gridname).treegrid('getData').rows[index];
            var todown = $('#' + gridname).treegrid('getData').rows[index - 1];
            $('#' + gridname).treegrid('getData').rows[index] = todown;
            $('#' + gridname).treegrid('getData').rows[index - 1] = toup;
            $('#' + gridname).treegrid('refreshRow', index);
            $('#' + gridname).treegrid('refreshRow', index - 1);
            $('#' + gridname).treegrid('selectRow', index - 1);
        }
    } else if ("down" == type) {
        var rows = $('#' + gridname).treegrid('getRows').length;
        if (index != rows - 1) {
            var todown = $('#' + gridname).treegrid('getData').rows[index];
            var toup = $('#' + gridname).treegrid('getData').rows[index + 1];
            $('#' + gridname).treegrid('getData').rows[index + 1] = todown;
            $('#' + gridname).treegrid('getData').rows[index] = toup;
            $('#' + gridname).treegrid('refreshRow', index);
            $('#' + gridname).treegrid('refreshRow', index + 1);
            $('#' + gridname).treegrid('selectRow', index + 1);
        }
    }
 
}
*/




