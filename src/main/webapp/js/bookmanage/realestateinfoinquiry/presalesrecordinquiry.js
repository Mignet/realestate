var presalesLst;
$(function(){
	presalesLst = $('#presalesLst').datagrid({
		title:'预售备案信息',
		//数据请求地址
		url : ctx+'/bookmanage/book-manage!getAllPresaleInfosBySearch.action?time='+new Date(),
		fit : true,
		//表格每列宽度自动适应表格总宽度
		fitColumns: true,
		//去掉边框
		border : false,
		//是否有翻页栏
		pagination:true,
		//pageNumber:1,
		pageList:[3,6,9,12],
		//每页行数
		pageSize:6,
		//是否在最左增加一列显示行号的列
		rownumbers:true,
		//pagePosition:'bottom',
		//主键值所在行。在使用复选框时必须设置此项。
		idField: 'PARCEL_CODE',
		//表格的行是否交替显示不同背景色					
		striped:true,
		//只允许单选一行
		singleSelect:false,
		//是否在点选表中一行时同时选中复选框
		checkOnSelect:true,
		//是否在选中复选框时同时点选表中一行
		selectOnCheck:true,
		columns :[[ 
		            { field:'ck' ,checkbox:true},
					{ title : '项目名称',     field : 'PRO_NAME'   ,width:100 },
					{ title : '预售许可证号',   field : 'PRE_LIC_NO' ,width:100 },
					{ title : '预售合同号',   field : 'PRE_CON_NO'  ,width:100},
					{ title : '合同日期',     field : 'ACON_DATE' ,width:100 },
					{ title : '备案日期',   field : 'AREC_DATE'  ,width:100 },
					{ title : '预售人',   field : 'PRESALER'  ,width:100 },
					{ title : '预购人',   field : 'PREBUYER'  ,width:100 },
					{ title : '是否已备案',   field : 'REC_STATE'  ,width:100 },
					{ hidden:'true',field : 'WHERE_CODE' },
					{ hidden:'true',field : 'checked' }
					///{ title : '操作',       field:'button',formatter:function(value,rec){return '<span><input type="button" value=" 详  情 " onclick=""/></span>';}}
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
					//窗口元素的id
					id: 'wind_djby2',
					//窗口iframe的src
					src: ctx+'/jsp/reportmanage/pdf.jsp?time='+new Date(),
					//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
					destroy: true,
					//窗口标题
					title: '打印预览',
					//窗口宽
					width: 800,
					//窗口高
					height: 598,
					modal: true,
					//窗口中iframe的window对象的onLoad回调函数设置
					onLoad:	function(){
					   this.init(proc);
					}
				};
		    if(j == 0){
		    	$.messager.alert("提示","请勾选需要打印的记录！","warning");
		    	return;
		    }
			openInTopWindow(objWindow2);
	 });
   });

    //打开查看登记簿内容
	function openBookDetail(button){
		var selectedrow = $('#presalesLst').datagrid('getSelected');
		var objWindow = {
				//窗口元素的id
				id: 'wind_djbyl',
				//窗口iframe的src
				src: ctx+'/bookmanage/book-manage!home.action?reg_unit_type='+selectedrow.REG_UNIT_TYPE+'&time='+new Date(),
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
	

	
