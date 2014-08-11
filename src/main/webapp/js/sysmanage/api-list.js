/*********************************************************************************
*��  ��  ��  ��: api-list.js
*��  ��  ��  ��: API �б�
*Copyright (c): ���ڵ�����������������޹�˾
*��    ��    ��: Mignet
*��  ��  ��  �ڣ� 2014-03-01
*��  ��  ��  ʷ��
*��  ��  ��  ���� 1.0
**********************************************************************************/

/**********************************************************************************
*��������: JQuery DOM Ready(Shortcut)
*����˵��: ҳ���ʼ��
*��������: Mignet
*��������: 2014-03-01
*�޸���ʷ: 
***********************************************************************************/
		var startFlag = false;
		$(function(){
			var lastIndex;
			$('#tt').datagrid({
				title:"����API�б�",
				toolbar:[{
					text:'����',
					iconCls:'icon-save',
					handler:function(){
						$('#tt').datagrid('endEdit', lastIndex);
						startFlag = false;
						var rows = $('#tt').datagrid('getChanges');
						 $.ajax({
						   		dataType:'json',
						   		url:ctx+"/sysmanage/monitor!saveAPIs.action?time="+getDateStr(),
						   		type : 'post',
						   		//�������л�����
						   		data:{"datas":$.toJSON(rows)},
						   		success:function(data){
								 	if(data){
								 		top.$.messager.alert('����ɹ���ʾ',"����ɹ�",'info',function(){
								 			$('#tt').datagrid('acceptChanges');
										});	
								 	}else {
										top.$.messager.alert('����ʧ����ʾ',"����ʧ��",'error');
									}
						   		},error:function(data){
						   			top.$.messager.alert('����ʧ����ʾ',"����ʧ��",'error');
						   		}
						   	});  
					}
				},'-',{
					text:'����',
					iconCls:'icon-undo',
					handler:function(){
						$('#tt').datagrid('rejectChanges');
					}
				}],
				rownumbers:true,
				width:screen.availWidth/2,
				singleSelect:true,
				autoRowHeight:false,
				pagination:true,
				pageSize:10,
				striped:true,
				columns:[[
				          	//{field:'ck',checkbox:true},
							{field:'sid', title:'����ID', width:160},
							{field:'sname', title:'��������',width:160},
							{field:'regflag', title:'ע���־', width:60,formatter:regflagFormatter,editor:{
								type:'combobox',
								options:{
									valueField:'regflag',
									textField:'name',
									data:regflags,
									required:true
								}
							}},
							{field:'info',title:'��Ϣ',width:200,editor:'text'},
							{field:'remark',title:'��ע',width:200,editor:'text'}
				]],
				iconCls:'icon-edit',
				idField:'sid',
				url:ctx+'/sysmanage/monitor!getAPIList.action',
				onBeforeLoad:function(){
					$('#tt').datagrid('unselectAll');
					$('#tt').datagrid('rejectChanges');
				},
				onClickRow:function(rowIndex){
					//������༭���в��ǵ�ǰ�У�����������ʼ��ǰ�еı༭
					//����ǵ�ǰ�У������༭
					if (lastIndex != rowIndex){
						$('#tt').datagrid('endEdit', lastIndex);
						$('#tt').datagrid('beginEdit', rowIndex);
						//�����־δ��ʼ����ʼ��
						if(!startFlag){
							startFlag = true;
						}
					}else{
						if(startFlag){
							$('#tt').datagrid('endEdit', rowIndex);
							var sid = $('#tt').datagrid('getRows')[rowIndex].sid;
							//��ʾAPI
							$('#ts').datagrid('load',{'sid':sid});
						}else{
							$('#tt').datagrid('beginEdit', rowIndex);
						}
						startFlag = !startFlag;
					}
					lastIndex = rowIndex;
				}
			});
			$('#tt').datagrid('hideColumn', 'sid');
			var lastIndex2;
			$('#ts').datagrid({
				title:"�����б�",
				toolbar:[{
					text:'����',
					iconCls:'icon-save',
					handler:function(){
						$('#ts').datagrid('endEdit', lastIndex2);
						startFlag = false;
						var rows = $('#ts').datagrid('getChanges');
						$.ajax({
							dataType:'json',
							url:ctx+"/sysmanage/monitor!saveMethods.action?time="+getDateStr(),
							type : 'post',
							//�������л�����
							data:{"datas":$.toJSON(rows)},
							success:function(data){
								if(data){
									top.$.messager.alert('����ɹ���ʾ',"����ɹ�",'info',function(){
										$('#ts').datagrid('acceptChanges');
									});	
								}else {
									top.$.messager.alert('����ʧ����ʾ',"����ʧ��",'error');
								}
							},error:function(data){
								top.$.messager.alert('����ʧ����ʾ',"����ʧ��",'error');
							}
						});  
					}
				},'-',{
					text:'����',
					iconCls:'icon-undo',
					handler:function(){
						$('#ts').datagrid('rejectChanges');
					}
				}],
				rownumbers:true,
				singleSelect:true,
				width:screen.availWidth/2,
				autoRowHeight:false,
				pagination:true,
				pageSize:10,
				striped:true,
				columns:[[
				          //{field:'ck',checkbox:true},
				          {field:'mid', title:'����ID', width:140},
				          {field:'sid', title:'����ID', width:140},
				          {field:'mname', title:'��������',width:160},
				          {field:'parameters',title:'����',width:60,editor:'text'},
				          {field:'returntype',title:'����ֵ',width:60,editor:'text'},
				          {field:'inlineflag', title:'���ڱ�־', width:60,formatter:inlineflagFormatter,editor:{
								type:'combobox',
								options:{
									valueField:'inlineflag',
									textField:'name',
									data:inlineflags,
									required:true
								}
							}},
				          {field:'info',title:'��Ϣ',width:200,editor:'text'},
				          {field:'remark',title:'��ע',width:100,editor:'text'}
				          ]],
				          iconCls:'icon-edit',
				          idField:'mid',
				          url:ctx+'/sysmanage/monitor!getMethodsBySID.action',
				          onBeforeLoad:function(){
				        	  $('#ts').datagrid('unselectAll');
				        	  $('#ts').datagrid('rejectChanges');
				          },
				          onClickRow:function(rowIndex){
				        	  //������༭���в��ǵ�ǰ�У�����������ʼ��ǰ�еı༭
				        	  //����ǵ�ǰ�У������༭
				        	  if (lastIndex2 != rowIndex){
				        		  $('#ts').datagrid('endEdit', lastIndex2);
				        		  $('#ts').datagrid('beginEdit', rowIndex);
				        		  //�����־δ��ʼ����ʼ��
				        		  if(!startFlag){
				        			  startFlag = true;
				        		  }
				        	  }else{
				        		  if(startFlag){
				        			  $('#ts').datagrid('endEdit', rowIndex);
				        			  //��ʾAPI
				        		  }else{
				        			  $('#ts').datagrid('beginEdit', rowIndex);
				        		  }
				        		  startFlag = !startFlag;
				        	  }
				        	  lastIndex2 = rowIndex;
				          }
			});
			$('#ts').datagrid('hideColumn', 'mid');
			$('#ts').datagrid('hideColumn', 'sid');

		});//��ʼ������
//////////////////////////////////////////////////////////////////
var inlineflags = [
	{inlineflag:'00',name:'����'},
	{inlineflag:'01',name:'����'}
];
function inlineflagFormatter(value) {
	for ( var i = 0; i < inlineflags.length; i++) {
		if (inlineflags[i].inlineflag == value)
			return inlineflags[i].name;
	}
	return value;
};
var regflags = [
                   {regflag:'00',name:'δע��'},
                   {regflag:'01',name:'��ע��'},
                   {regflag:'99',name:'ע��'}
                   ];
function regflagFormatter(value) {
	for ( var i = 0; i < regflags.length; i++) {
		if (regflags[i].regflag == value)
			return regflags[i].name;
	}
	return value;
}
