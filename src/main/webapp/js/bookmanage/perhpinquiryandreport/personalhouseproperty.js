var perhpLst;
$(function(){
	perhpLst = $('#perhpLst').datagrid({
		title:'个人产权信息查询',
		//数据请求地址
		url : ctx+'/bookmanage/book-manage!getAllPrivateRealInfosBySearch.action?time='+new Date(),
		fit : true,
		//表格每列宽度自动适应表格总宽度
		fitColumns: true,
		//去掉边框
		border : false,
		//是否有翻页栏
		pagination:true,
		//pageNumber:1,
		//每页行数
		pageSize:10,
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
			{ title : '权利人',       field : 'HOL_NAME'   ,width:100 },
			{ title : '法定代表人',   field : 'LEGAL_NAME' ,width:100 },
			{ title : '证件类型',     field : 'HOL_CER_TYPE'  ,width:100 },
			{ title : '证件号码',     field : 'HOL_CER_NO' ,width:100 },
			{ title : '权利人性质',   field : 'AHOL_TYPE'  ,width:120 },
			{ title : '产权记录数',   field : 'HOUSERIGHT_COUNT'  ,width:120 },
			{ title : '预售记录数',   field : 'PRESALE_COUNT'  ,width:120 },
			//{ title : '政策性住房数', field : 'REAL_USAGE'  ,width:120 },
			//{ type:'hidden' field : 'HOLDER_ID'},
			{ title : '操作',       field:'button',formatter:function(value,rec){return '<span><input type="button" value="打印预览" onclick=""/></span>';}}
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

    /*//打开查看登记簿内容
	function openBookDetail(button){
		var selectedrow = $('#perhpLst').datagrid('getSelected');
		var objWindow = {
				//窗口元素的id
				id: 'wind_djbyl',
				//窗口iframe的src
				src: ctx+'/bookmanage/registerView.action?reg_unit_type='+selectedrow.REG_UNIT_TYPE,
				//关闭时是否销毁窗口。不销毁的话，每次打开窗口都会添加一个新窗口元素。
				destroy: true,
				//窗口标题
				title: '登记簿内容',
				//窗口宽
				width: 900,
				//窗口高
				height: 620,
				modal: true,
				//窗口中iframe的window对象的onLoad回调函数设置
				onLoad:	function(){
				    //proc.regcode=regcode;
					//此处将本窗口window对象赋值为打开的新窗口window对象的openerWindow属性。
					//因此，在新窗口中，可通过openerWindow属性调用本窗口，从而实现多窗口间的交互、传值。
					//this.openerWindow = window;
					
					//将参数传入打开窗口对象的parenter属性中，从而实现窗口间传递参数调用
					this.args = {
						userDataGrid: perhpLst
					};

					this.init(selectedrow);
				}
			};
		openInTopWindow(objWindow);
	};*/
    //打印个人产权信息
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
		    /*if(j == 0){
		    	$.messager.alert("提示","请勾选需要打印的记录！","warning");
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
	

	
