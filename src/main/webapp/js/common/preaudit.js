//var row;

//��ʼ������.	
var regcode;
var serialNumber ={num1:"�ǼǱ��",num2:"�鵵��"};

var proc;
var dataGridData;
var lbhdataGrid;
var lbhtable;
var obj={};
//var housecode;
//����Ƿ���������ҵ��״̬
var checkDataState;
var regunittypesign;
//ȷ������
function start(){
	var objurl;
	  var selectedrow = $('#table_user_select').datagrid('getRows');
	  selectedrow.lastregcode="";
	  selectedrow =$.toJSON(selectedrow);
	  //var procName=proc.name+"("+selectedrow.proname+"--"+selectedrow.housecode+")";
	  var procdefId=proc.procdefid;
	  $.ajax({
		    dataType: 'json',
			url:"preaudit!doApply.action",
			data:{'serialName':serialNumber.num1,'procName':proc.name,'selectedrow':selectedrow,'procdefId':procdefId,"time":new Date()},
			success:function(data){
				//alert(JSON.stringify(data));
			 	if(data){
			 		var sysurl = data.workItem.urlSpecify;
			 	    var myurl;
			 	    if(sysurl.lastIndexOf('?') == -1)
			 	       myurl = sysurl + '?wiId='+data.workItem.wiId;
			 	    else
			 	       myurl = sysurl + '&wiId='+data.workItem.wiId;
			 		obj.regcode=data.regcode;
					obj=$.extend(obj,data.workItem);
					//objurl.src=data.workItem.urlSpecify+'?wiId='+data.workItem.wiId;
					openInTopWindow({	
							id:"open_app",
							//���ڿ�
							width: 988,
							//���ڸ�
							title:'�칫ҳ��',
							height: 598,
							src:data.workItem.urlSpecify+'?wiId='+data.workItem.wiId,
							modal: true,	
							destroy:true,
							onLoad:	function(){
								//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
								//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
								this.openerWindow = window;
								//this.document.getElementById('name').value='����';
								//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
								this.args = {
									user: obj
								};
								obj.proname="";
								this.init(obj);
							}
						});
			 	};
			}
		});

}

function addRegunit(){
	
	if(regunittypesign == enumdata.house){
		var data_ = lbhdataGrid.datagrid("getSelected");
		var data = {total:1,rows:[{REG_UNIT_TYPE:data_.reg_type,REG_UNIT_CODE:data_.code,REG_UNIT_NAME:data_.houselocation+data_.buildname+data_.buildnum+data_.roomno}]};
		//args.houseDataGrid.datagrid('loadData',data);
		//args.houseDataGrid.datagrid('load',{REG_UNIT_TYPE:data_.reg_type,REG_UNIT_CODE:data_.code,REG_UNIT_NAME:data_.houselocation+data_.buildname+data_.buildnum+data_.roomno});
//		args.houseDataGrid.datagrid('load',{REG_UNIT_TYPE:data_.reg_type,REG_UNIT_CODE:data_.code,REG_UNIT_NAME:data_.houselocation+data_.buildname+data_.buildnum+data_.roomno});
		
		args.houseDataGrid.datagrid('insertRow',{
			index: 0,	// index start with 0
			row: {
				REG_UNIT_TYPE:data_.reg_type,REG_UNIT_CODE:data_.code,REG_UNIT_NAME:data_.houselocation+data_.buildname+data_.buildnum+data_.roomno
			}
		});

		closeInTopWindow('add_regunit_win');
	}
	else if(regunittypesign == enumdata.parcel){
		
	}
	else if(regunittypesign == enumdata.build){
		
	}
}

function init(user){
	$("#reg_type").combodict({value:enumdata.house,onChange: selectChange});
	regunittypesign = enumdata.house ;
	/*proc=user;
	if(proc){
		$("#bustype").val(proc.name);		
	}*/
	getHouse();
	//getSelectData();
}

	
	//����������¼�
	function selectChange()
	{
		
		if($("#reg_type").combodict("getValue")== enumdata.house)
		{
			$("#house").css("display","block");
			$("#land").css("display","none");
			$('#divHouse').panel('open');
			$('#divLand').panel('close');
			$('#divBuild').panel('close');
			$('#divRegCode').panel('close');
			getHouse();
			regunittypesign = enumdata.house;
		}
		else if($("#reg_type").combodict("getValue")== enumdata.parcel)
		{
			$("#house").css("display","none");
			$("#land").css("display","block");
			$("#build").css("display","none");
			$('#divHouse').panel('close');
			$('#divLand').panel('open');
			$('#divBuild').panel('close');
			$('#divRegCode').panel('close');
			getLand();
			regunittypesign = enumdata.parcel;
		}
		else if($("#reg_type").combodict("getValue")== enumdata.build)
		{
			$("#house").css("display","block");
			$("#land").css("display","none");
			$('#divHouse').panel('close');
			$('#divLand').panel('close');
			$('#divBuild').panel('open');
			$('#divRegCode').panel('close');
			getBuild();
			regunittypesign = enumdata.build;
		}
	}
	
	
	function getHouse() {
			//����ǰ���������				
				dataGridData={
			//fit : true,
			title:'�Ǽǵ�Ԫ��Ϣ',
			height:240,
			// ���������Դ
			url : 'preaudit!getHouseInfo.action',
			// ���ÿ�п���Զ���Ӧ����ܿ��
			autoRowHeight : false,
			fitColumns : true,
			// ȥ���߿�
			border : true,
			idField: 'code',
			striped : true,
			// �Ƿ��з�ҳ��
			pagination : true,
			checkbox:'CK',
			// pagePosition:'top',
			// ÿҳ����
			pageSize : 10,
			// �Ƿ�����������һ����ʾ�кŵ���
			rownumbers : true,
			// ����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
			//idField : 'jjclmc',
			// �������Ƿ�����ʾ��ͬ����ɫ
			striped : true,
			// ֻ����ѡһ��
			singleSelect : false,
			// �Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
			 checkOnSelect:false,
			// �Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
			 selectOnCheck:false,
			// ����������
			onClickRow: onCRHouse,
			columns : [ [
			// ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
			{field:'ck',checkbox:true},
			{
				title : '�Ǽǵ�Ԫ����',
				field : 'reg_type',
				width : 100,
				hidden:true
				
			},
			{
				field : 'houselocation',
				width : 100,
				hidden:true
				
			},
			{
				field : 'roomno',
				width : 100,
				hidden:true
				
			},
			{
				title : '�Ǽǵ�Ԫ',
				field : 'reg_typename',
				width : 60,
				align:'center'
			},
			{
				title : '�Ǽǵ�Ԫ����',
				field : 'code',
				width : 100,
				align:'center'
			},
			{
				title : '¥��',
				field : 'buildname',
				width : 80,
				align:'center'
			},
			{
				title : '����',
				field : 'buildnum',
				width : 30,
				align:'center'
			},
			{
				title : '�ڵغ�',
				field : 'parcelcode',
				width : 80,
				align:'center'
			}, {
				title : 'ʵ����;',
				field : 'usage',
				width : 50,
				align:'center'
			}, {
				title : '������Ŀ',
				field : 'proname',
				width : 80,
				align:'center'
			}, {
				title : '�������(�O)',
				field : 'buildarea',
				width : 60,
				align:'center'
			}
			]],
			
			/*//��ѡ�и�ѡ��ļ�¼����ֵ��������seq_id_set
			onCheck: function(index,row){
				$.ajax({
					dataType: 'json',
					url:"register!checkBusData.action",
					data:{"bustype":proc.procdefid,"regtype":row.reg_type,"reg_unit_code":row.code,"time":new Date()},
					success:function(data){
						checkDataState=data.state;
					 		//alert($("#accept").attr("disabled"));
					 		$("#discription").empty();	
					 		if(data.state=="false")
					 		{					 			
					 			$("#discription").append(data.statemes);
					 			//$("#accept").linkbutton("disable");
					 		}
					 		else
					 		{
								$('#table_user_house').datagrid('selectRow',index);				
								var selectedRow = $('#table_user_house').datagrid('getSelected');
								$('#table_user_house').datagrid("deleteRow",index);
								$('#table_user_select').datagrid("appendRow",selectedRow);
					 			$("#discription").append(data.statemes);
					 		}
					 		
					 	}					
				});
				if(checkDataState=="true")
				{
					$('#table_user_house').datagrid('selectRow',index);				
					var selectedRow = $('#table_user_house').datagrid('getSelected');
					$('#table_user_house').datagrid("deleteRow",index);
					$('#table_user_select').datagrid("appendRow",selectedRow);
		 			$("#discription").append(data.statemes);
				}
				
			},*/
			onClickCell:function(rowIndex, field, value){
				
			},
			//��ȡ��ѡ�и�ѡ��ļ�¼����ֵ������seq_id_setɾ��
			onUncheck: function(index,row){
				//selectRow(index,row);
				//seq_id_set.splice(seq_id_set.indexOf(row.OFFICE_ID),1);
			},
			//��ȫѡʱ����������ֵ��������seq_id_set
			onCheckAll: function(rows){
				/*var rows=$('#table_user_house').datagrid("getRows");
				for (var i=0;i<rows.length;i){
					$('#table_user_house').datagrid("deleteRow",$('#table_user_house').datagrid("getRowIndex",rows[0]));
					$('#table_user_select').datagrid("appendRow",rows[0]);
				}*/
			},
			//��ȡ��ȫѡʱ����������ֵ������seq_id_set��ɾ��
			onUncheckAll: function(rows){
				for (var i=0;i<rows.length;i++){
					for (var i=0;i<rows.length;i++){
						//selectRow(index,row[i]);
					}
				}
			},
		};		
				function onCRHouse(rowIndex, rowData){
					//alert(rowData);
					/*$.ajax({
						dataType: 'json',
						url:"register!checkBusData.action",
						data:{"bustype":proc.procdefid,"regtype":rowData.reg_type,"reg_unit_code":rowData.housecode,"time":new Date()},
						success:function(data){
						 		//alert($("#accept").attr("disabled"));
						 		$("#discription").empty();	
						 		if(data.state=="false")
						 		{					 			
						 			$("#discription").append(data.statemes);
						 			$("#accept").linkbutton("disable");
						 		}
						 		else
						 		{
						 			$("#discription").append(data.statemes);
						 			//$("#accept").linkbutton("disable");
						 		}
						 	}					
					});*/
				}
				lbhdataGrid = $('#table_user_house').datagrid(dataGridData);
	}
	
	
	
	//ѡ���¼�
	function selectRow()
	{
		$.ajax({
			url : ctx+"/common/certificate!insertNode.action?time="+new Date(),
			type : 'post',
			data : {"nodeid":nodeid,"bus_typeid":row.BUS_TYPE_ID,"officeid":row.OFFICE_ID},
			dataType : 'json',
			success : function(data) {
			
			}			
		});
	}
	

	
	function getBuild() {
		//����ǰ���������				
			dataGridData={
		//fit : true,
		title:'�Ǽǵ�Ԫ��Ϣ',
		height:240,
		// ���������Դ
		url : 'preaudit!getBuildInfo.action',
		// ���ÿ�п���Զ���Ӧ����ܿ��
		autoRowHeight : false,
		fitColumns : true,
		// ȥ���߿�
		border : true,
		idField: 'reg_type',
		striped : true,
		// �Ƿ��з�ҳ��
		pagination : true,
		checkbox:'CK',
		// pagePosition:'top',
		// ÿҳ����
		pageSize : 10,
		// �Ƿ�����������һ����ʾ�кŵ���
		rownumbers : true,
		// ����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		//idField : 'jjclmc',
		// �������Ƿ�����ʾ��ͬ����ɫ
		striped : true,
		// ֻ����ѡһ��
		singleSelect : false,
		// �Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		 checkOnSelect:false,
		// �Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		 selectOnCheck:false,
		// ����������
		//onClickRow: onCRBuild,
		columns : [ [
		// ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
		{field:'ck',checkbox:true},
		{
			title : '�Ǽǵ�Ԫ����',
			field : 'reg_type',
			width : 100,
			hidden:true
			
		},
		{
			title : '�Ǽǵ�Ԫ',
			field : 'reg_typename',
			width : 60,
			align:'center'
		},
		{
			title : '�Ǽǵ�Ԫ����',
			field : 'code',
			width : 80,
			align:'center'
		},
		{
			title : '¥��',
			field : 'buildname',
			width : 80,
			align:'center'
		},
		{
			title : '����',
			field : 'buildnum',
			width : 30,
			align:'center'
		},/*{
			title : '���������',
			field : 'code',
			width : 40,
			align:'center'
		}, */
		{
			title : '�ڵغ�',
			field : 'parcelcode',
			width : 80,
			align:'center'
		}, {
			title : 'ʵ����;',
			field : 'usage',
			width : 50,
			align:'center'
		}, {
			title : '������Ŀ',
			field : 'proname',
			width : 80,
			align:'center'
		}, {
			title : '�������(�O)',
			field : 'buildarea',
			width : 60,
			align:'center'
		}
		]],
		//��ѡ�и�ѡ��ļ�¼����ֵ��������seq_id_set
		onCheck: function(index,row){
			/*$.ajax({
				dataType: 'json',
				url:"register!checkBusData.action",
				data:{"bustype":proc.procdefid,"regtype":row.reg_type,"reg_unit_code":row.code,"time":new Date()},
				success:function(data){
					checkDataState=data.state;
				 		//alert($("#accept").attr("disabled"));
				 		$("#discription").empty();	
				 		if(data.state=="false")
				 		{					 			
				 			$("#discription").append(data.statemes);
				 			//$("#accept").linkbutton("disable");
				 		}
				 		else
				 		{
				 			$('#table_user_build').datagrid('selectRow',index);
							var selectedRow = $('#table_user_build').datagrid('getSelected');
							$('#table_user_build').datagrid("deleteRow",index);
							$('#table_user_select').datagrid("appendRow",selectedRow);
				 			$("#discription").append(data.statemes);
				 		}
				 		
				 	}					
			});
			//selectRow(index,row);
*/			
			
		},onClickCell:function(rowIndex, field, value){
			
		},
		//��ȡ��ѡ�и�ѡ��ļ�¼����ֵ������seq_id_setɾ��
		onUncheck: function(index,row){
			//selectRow(index,row);
			//seq_id_set.splice(seq_id_set.indexOf(row.OFFICE_ID),1);
		},
		//��ȫѡʱ����������ֵ��������seq_id_set
		onCheckAll: function(rows){
				var rows=$('#table_user_build').datagrid("getRows");
				for (var i=0;i<rows.length;i){
					$('#table_user_build').datagrid("deleteRow",$('#table_user_build').datagrid("getRowIndex",rows[0]));
					$('#table_user_select').datagrid("appendRow",rows[0]);
				}
		},
		//��ȡ��ȫѡʱ����������ֵ������seq_id_set��ɾ��
		onUncheckAll: function(rows){
			for (var i=0;i<rows.length;i++){
				for (var i=0;i<rows.length;i++){
					//selectRow(index,row[i]);
				}
			}
		},

	};		
			lbhdataGrid = $('#table_user_build').datagrid(dataGridData);
}
	
	
	function getSelectData() {
		//����ǰ���������				
			dataGridData={
		//fit : true,
		title:'�Ǽǵ�Ԫѡ���б�',
		height:240,
		// ���������Դ
		//url : 'preaudit!getBuildInfo.action',
		// ���ÿ�п���Զ���Ӧ����ܿ��
		autoRowHeight : false,
		fitColumns : true,
		// ȥ���߿�
		border : true,
		idField: 'reg_type',
		striped : true,
		// �Ƿ��з�ҳ��
		pagination : true,
		checkbox:'CK',
		// pagePosition:'top',
		// ÿҳ����
		pageSize : 10,
		// �Ƿ�����������һ����ʾ�кŵ���
		rownumbers : true,
		// ����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
		//idField : 'jjclmc',
		// �������Ƿ�����ʾ��ͬ����ɫ
		striped : true,
		// ֻ����ѡһ��
		singleSelect : false,
		// �Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
		 checkOnSelect:false,
		// �Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
		 selectOnCheck:false,
		// ����������
		//onClickRow: onCRBuild,
		columns : [ [
		// ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������
		{field:'ck',checkbox:true},
		{
			title : '�Ǽǵ�Ԫ����',
			field : 'reg_type',
			width : 100,
			hidden:true
			
		},
		{
			title : '�Ǽǵ�Ԫ',
			field : 'reg_typename',
			width : 40,
			align:'center'
		},
		{
			title : '�Ǽǵ�Ԫ����',
			field : 'code',
			width : 100,
			align:'center'
		}, {
			title : 'ʵ����;',
			field : 'usage',
			width : 50,
			align:'center'
		}, {
			title : '������Ŀ',
			field : 'proname',
			width : 80,
			align:'center'
		}, {
			title : '���(�O)',
			field : 'buildarea',
			width : 40,
			align:'center'
		}
		]],
		//��ѡ�и�ѡ��ļ�¼����ֵ��������seq_id_set
		onCheck: function(index,row){
			//selectRow(index,row);
			var table;
			table= getGridType(row.reg_type);
			$('#table_user_select').datagrid('selectRow',index);
			var selectedRow = $('#table_user_select').datagrid('getSelected');
			$('#table_user_select').datagrid("deleteRow",index);
			$("#"+table).datagrid("appendRow",selectedRow);
			
		},onClickCell:function(rowIndex, field, value){
			
		},
		//��ȡ��ѡ�и�ѡ��ļ�¼����ֵ������seq_id_setɾ��
		onUncheck: function(index,row){
			//selectRow(index,row);
			//seq_id_set.splice(seq_id_set.indexOf(row.OFFICE_ID),1);
		},
		//��ȫѡʱ����������ֵ��������seq_id_set
		onCheckAll: function(rows){
			/*var rows=$('#table_user_select').datagrid("getRows");
			for (var i=0;i<rows.length;i){
				var table;
				table= getGridType(rows[0].reg_type);
				$('#table_user_select').datagrid('selectRow',$('#table_user_select').datagrid("getRowIndex",rows[0]));
				$('#table_user_select').datagrid("deleteRow",$('#table_user_select').datagrid("getRowIndex",rows[0]));
				$("#"+table).datagrid("appendRow",rows[0]);
			}*/
		},
		//��ȡ��ȫѡʱ����������ֵ������seq_id_set��ɾ��
		onUncheckAll: function(rows){
			for (var i=0;i<rows.length;i++){
				for (var i=0;i<rows.length;i++){
					//selectRow(index,row[i]);
				}
			}
		},

	};		
			lbhdataGrid = $('#table_user_select').datagrid(dataGridData);
}
	function getGridType(table)
	{
		if(table=='009001')
		{
			table="table_user_house";
			lbhtable = "#table_user_house";
		}
		else if(table=='009002')
		{
			table="table_user_build";
			lbhtable = "#table_user_build";
		}
		else if(table=='009003')
		{
			table="table_user_land";
			lbhtable = "#table_user_land";
		}
		return table;
	}
	
	function getLand() {
			//����ǰ���������				
			dataGridData={
			//fit : true,
			title:'�Ǽǵ�Ԫ��Ϣ',
			height:240,
			// ���������Դ
			url : 'preaudit!getLandInfo.action',
			// ���ÿ�п���Զ���Ӧ����ܿ��
			autoRowHeight : false,
			fitColumns : true,
			// ȥ���߿�
			border : true,
			idField: 'reg_type',
			striped : true,
			// �Ƿ��з�ҳ��
			pagination : true,
			checkbox:'CK',
			// pagePosition:'top',
			// ÿҳ����
			pageSize : 10,
			// �Ƿ�����������һ����ʾ�кŵ���
			rownumbers : true,
			// ����ֵ�����С���ʹ�ø�ѡ��ʱ�������ô��
			//idField : 'jjclmc',
			// �������Ƿ�����ʾ��ͬ����ɫ
			striped : true,
			// ֻ����ѡһ��
			singleSelect : true,
			// �Ƿ��ڵ�ѡ����һ��ʱͬʱѡ�и�ѡ��
			 checkOnSelect:true,
			// �Ƿ���ѡ�и�ѡ��ʱͬʱ��ѡ����һ��
			 selectOnCheck:true,
			// ����������
			//onClickRow: onCRLand,
			columns : [ [
			// ÿ�е����ԣ�field���ж��������������title���б��⣻width���п�sortable���Ƿ������

			// {field:'ck',checkbox:true},
			{field:'ck',checkbox:true},
			{
				title : '�Ǽǵ�Ԫ����',
				field : 'reg_type',
				width : 100,
				hidden:true,
				align:'center'
				
			},			
			{
				title : '�Ǽǵ�Ԫ',
				field : 'reg_typename',
				width : 60,
				align:'center'
			},
			{
				title : '�Ǽǵ�Ԫ����',
				field : 'code',
				width : 80,
				align:'center'
			},
			{
				title : '������Ŀ',
				field : 'proname',
				width : 80,
				align:'center'
			},
			{
				title : '��;',
				field : 'usage',
				width : 100,
				align:'center'
			},
			{
				title : '�ڵ����(�O)',
				field : 'parcelarea',
				width : 100,
				align:'center'
			}
			, {
				title : '����',
				field : 'location',
				width : 300,
				align:'center'
			}
			]],
			
			//��ѡ�и�ѡ��ļ�¼����ֵ��������seq_id_set
			onCheck: function(index,row){
				/*$.ajax({
					dataType: 'json',
					url:"register!checkBusData.action",
					data:{"bustype":proc.procdefid,"regtype":row.reg_type,"reg_unit_code":row.code,"time":new Date()},
					success:function(data){
						checkDataState=data.state;
					 		//alert($("#accept").attr("disabled"));
					 		$("#discription").empty();	
					 		if(data.state=="false")
					 		{					 			
					 			$("#discription").append(data.statemes);
					 			//$("#accept").linkbutton("disable");
					 		}
					 		else
					 		{
					 			$('#table_user_land').datagrid('selectRow',index);
								var selectedRow = $('#table_user_land').datagrid('getSelected');
								$('#table_user_land').datagrid("deleteRow",index);
								$('#table_user_select').datagrid("appendRow",selectedRow);
					 			$("#discription").append(data.statemes);
					 		}
					 		
					 	}					
				});*/
				//selectRow(index,row);
				
				
			},onClickCell:function(rowIndex, field, value){
				
			},
			//��ȡ��ѡ�и�ѡ��ļ�¼����ֵ������seq_id_setɾ��
			onUncheck: function(index,row){
				//selectRow(index,row);
				//seq_id_set.splice(seq_id_set.indexOf(row.OFFICE_ID),1);
			},
			//��ȫѡʱ����������ֵ��������seq_id_set
			onCheckAll: function(rows){
				/*var rows=$('#table_user_land').datagrid("getRows");
				for (var i=0;i<rows.length;i){
					$('#table_user_land').datagrid("deleteRow",$('#table_user_land').datagrid("getRowIndex",rows[0]));
					$('#table_user_select').datagrid("appendRow",rows[0]);
				}*/
			},
			//��ȡ��ȫѡʱ����������ֵ������seq_id_set��ɾ��
			onUncheckAll: function(rows){
				for (var i=0;i<rows.length;i++){
					for (var i=0;i<rows.length;i++){
						//selectRow(index,row[i]);
					}
				}
			},

		};
		lbhdataGrid = $('#table_user_land').datagrid(dataGridData);
	}
	
	
	
	// ѡ������ĳһ�е����ݡ�
	function getSelected(func) {
		var selectedrow = $('#table_user').datagrid('getSelected');

		if (selectedrow) {
			row = selectedrow;
			// ������غ���
			func.call(this, selectedrow);
		} else {

			$.messager.alert('��ʾ��', '����ѡ�б���е�ĳһ��.');
		}
	}
	//��ѯ������Ϣ
	function searchStr(){
//		if($("#selecttype").val()=="0")
//		{
		    $('#table_user_house').datagrid('load',{'parcelcode':$("#parcelcode").val(),'houselocation':$("#houselocation").val(),'proname':$("#proname").val(),'housecode':$("#housecode").val()});
//		}
//		else
//		{
//			$('#table_user1').datagrid('load',{'parcelcode':$("#parcelcode1").val(),'location':$("#location").val()});
//		}
	} 


	

	






