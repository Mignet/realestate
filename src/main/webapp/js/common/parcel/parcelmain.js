var parcelLst;
$(function(){
	parcelLst = $('#parcelLst').datagrid({
		title:'宗地信息',
		//数据请求地址
		url : ctx+'/bookmanage/book-manage!getParcelInfosBySearch.action?time='+new Date(),
		fit : true,
		//表格每列宽度自动适应表格总宽度
		fitColumns: true,
		//去掉边框
		border : false,
		//是否有翻页栏
		pagination:true,
		pageNumber:1,
		//每页行数
		pageSize:10,
		//是否在最左增加一列显示行号的列
		rownumbers:true,
		pagePosition:'bottom',
		//主键值所在行。在使用复选框时必须设置此项。
		idField: 'PARCEL_CODE',
		//表格的行是否交替显示不同背景色					
		striped:true,
		//只允许单选一行
		singleSelect:true,
		//是否在点选表中一行时同时选中复选框
		//checkOnSelect:true,
		//是否在选中复选框时同时点选表中一行
		//selectOnCheck:true,
		columns :[[ 
			{ title : '宗地号',     field : 'PARCEL_CODE'   ,width:80 },
			{ title : '宗地面积(㎡)',   field : 'PARCEL_AREA' ,width:80 },
			{ title : '房产证号',   field : 'CER_CODE'  ,width:80 },
			{ title : '权利人',     field : 'R_COLL_NAMES'        ,width:100 },
			{ title : '产权状态',   field : 'RIGHT_STATUS'  ,width:100 },
			{ title : '宗地用途',   field : 'REAL_REAL_USAGE'  ,width:100 },
			{ hidden:'true',field : 'REG_UNIT_TYPE',width:100},
			{ hidden:'true',field : 'BOOK_CODE',width:100 },
			{ hidden:'true',field : 'REG_CODE',width:100 },
			{ hidden:'true',field : 'WHERE_CODE',width:100 },
			{ title : '操作',       field:'button',formatter:function(value,rec){return '<span><input type="button" value="登记薄详情" onclick=""/></span>';}}
		]],
		onClickCell:function(rowIndex, field, value){
			if(field=="button"){
			    $('#parcelLst').datagrid('selectRow',rowIndex);
				openBookDetail(this);
			}
		}
	 });
	 
//	$('#simpleform').form({
//		url: ctx + '/bookmanage/book-manage!getParcelInfosBySearch.action?time='+new Date(),
//		success:function(data){
//		    
//		}
//	});
	
   });

    //打开查看登记簿内容
	function openBookDetail(button){
		var selectedrow = $('#parcelLst').datagrid('getSelected');
		var objWindow = {
				//窗口元素的id
				id: 'wind_djbyl',
				//窗口iframe的src
				src: ctx+'/bookmanage/book-manage!home.action?reg_unit_type='+selectedrow.REG_UNIT_TYPE,
				//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
				destroy: true,
				//窗口标题
				title: '登记簿内容',
				//窗口宽
				width: 800,
				//窗口高
				height: 598,
				modal: true,
				//窗口中iframe的window对象的onLoad回调函数设置
				onLoad:	function(){
				    //proc.regcode=regcode;
					//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
					//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
					//this.openerWindow = window;
					
					//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
					this.args = {
						userDataGrid: parcelLst
					};

					this.init(selectedrow);
				}
			};
//		var bus_type=proc.procdefName;
//		//所有权
//		if(busType.ALL == bus_type_id){
//			objWindow.src = ctx+'/common/register-preview.action';
//		}
//		//使用权
//		else if(busType.USE == bus_type_id){
//			objWindow.src = ctx+'/common/registerbook/registeruse.action';
//		}
//		//抵押
//		else if(busType.MORT == bus_type_id){
//			objWindow.src = ctx+'/common/registerbook/registermort.action';
//		}
//		//查封
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
		$('#parcelLst').datagrid('loadData',{total:0,rows:[]});
	    $('#parcelLst').datagrid('load',$('#simpleform').serializeJson());
	}
	function clear1(){
		$('#simpleform')[0].reset();
	}
	

	
