 var selectNode;
 
 var selectedLi;			//ѡ�е�li
 
 var selectedChar;			//ѡ�еĳ�����   �޸�ʱȡֵ
 
 var code_id;				//�������ID
 
 var ruleTree ;				//tree
 
 var editNodeText;			//�༭�ڵ���ı�  �����Ƿ�ı�  ���δ�ı� �򲻸��µ����ݿ�
$(function(){
	$('body').layout({
		fit:true
	});		
	
	
	
	
	initTree();
	
	
	
	initTabs();
	
	$("#btnCharAdd").click(function(){
		
//		var option = $('#identifier_tree').tree("getRoot");
//		var data = $('#identifier_tree').tree("getData",option.target);
//		alert($.toJSON(data));
//		//alert($.toJSON(option));
//		
//		return;
		var char = $.trim($("#iptChar").val());
		if(char.length==0){
			alert("�����ֲ���Ϊ�գ�");
			return ;
		}
		var showChar = "������["+char+"]";		//��ʾ�ַ�
		sortAppend('char',showChar,char);
//	    var node = t.tree('getSelected');  
//	    t.tree('append', {  
//	        data: [{  
//	        	id:"char",
//	            text: '������['+char+']'  
//	        }]  
//	    });  
		 
		 initLiClickEvent();
	});
	
	//�޸��²�
	$("#btnCharModify").click(function(){
		$(this).toggle();
		
		
		//alert(selectedChar);
		//1���������ı������еĳ������ı��Ƿ�δ�ı�  ��� �ı� ���޸�     ����  �����κβ���
		var iptCharValue = $("#iptChar").val();
		
		var selectInput = selectedChar.children("input")[0];   ///��ʱ���Ѿ�ת��Ϊdom����   Ҫע��ʹ�� 
		//selectInput = selectInput[0];
		
		var selectInputValue = selectInput.value;
		
		
		
		//ֵ ��ͬ  �����κ��޸�
		if(selectInputValue == iptCharValue){
			return ;
		}
		
		
		//�޸ĳ�����
		var tmpshow = "������["+iptCharValue+"]";
		
		
		$(selectInput).attr("show",tmpshow);
		selectInput.value=iptCharValue;
		
		var selectLabel = selectedChar.children("label")[0];
		//alert($.toJSON(selectLabel));
		selectLabel.innerHTML=tmpshow;
		
		
		
		
		
	});
	
	
	$("#btnDelSortli").click(function(){
		$(selectedLi).remove();
	});
	
	$("#input_squence").numberspinner({  
	    min: 1,  
	    max: 10,  
	    editable: false  ,
	    onSpinUp:function(){
	    	//alert($("#input_squence").numberspinner("getValue"));
	    	appendSquence($("#input_squence").numberspinner("getValue"));
	    },onSpinDown:function(){
	    	appendSquence($("#input_squence").numberspinner("getValue"));
	    }
	});
	
//	$("#btnEditRule").click(function(){
//		if(!selectNode){
//			alert("��ѡ���ĺ����ͣ�");
//			return;
//		}
//		$.ajax({
//			url:"../common/identifier!getIdentifierIdByName.action"+"?time="+new Date(),
//			type:"post",
//			contentType:"application/x-www-form-urlencoded; charset=GBK",
//			dataType:"json",
//			data:{"name":selectNode.text},
//			success:function(data){
//				
//				//alert(data);
//				identifier_id=data.identifier_id;
//				
//				openInTopWindow({
//					//����Ԫ�ص�id
//					id: 'edit_user_win',
//					//����iframe��src
//					src: ctx+'/jsp/common/identifier-edit.jsp?date='+new Date(),
//					//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
//					destroy: true,
//					//���ڱ���
//					title: '�༭�����Ϣ--' + selectNode.text,
//					//���ڿ�
//					width: 800,
//					//���ڸ�
//					height: 600,
//					modal: true,
//					//������iframe��window�����onLoad�ص���������
//					onLoad:	function(){
//						//�˴���������window����ֵΪ�򿪵��´���window�����openerWindow���ԡ�
//						//��ˣ����´����У���ͨ��openerWindow���Ե��ñ����ڣ��Ӷ�ʵ�ֶര�ڼ�Ľ�������ֵ��
//						this.openerWindow = window;
//						this.identifier_id=identifier_id;
//						//����������򿪴��ڶ����parenter�����У��Ӷ�ʵ�ִ��ڼ䴫�ݲ�������
//						this.args = {
//							node : selectNode
//							
//						};
//						this.init(selectNode);
//					}
//				});
//			  }
//			});
//			
//			
//		});
	
	
	initLiClickEvent();
		
}); 

function doDetail(node){
	$("#name").val(node.text);
}



/**********************************************************************************
*��������: append
*����˵��: ���������    ���浽���ݿ�
*��������: joyon
*��������: 2014��6��26�� 14:40:39
*�޸���ʷ: 
***********************************************************************************/
function append(){  
    var t = $('#identifier_tree');  
    var parentNode = t.tree('getSelected');  
   // alert(node.id);
    var parentId = parentNode.id;
    var ruleName = 'new item';
    
    
    $.ajax({
		url:ctx+"/common/identifier!createRule.action",
		//url:ctx+"/houseownership/shiftreg!getRegInfo.action,
		data:{"ruleName":ruleName,"parentId":parentId,"time":new Date()},
		dataType:"json",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			
			//alert($.toJSON(data));
			if(data.code_cfig_id){
				//��ʱδ���    ��ˢ�� ����    ��Ҫ ˢ��
				//var rootNode = ruleTree.tree('find', "new_item");
				
				//ruleTree.tree("reload");
				
				 t.tree('append', {  
				        parent: (parentNode?parentNode.target:null),  
				        data: [{  
				        	id:data.code_cfig_id,
				            text: 'new item'  
				        }]  
				    }); 
				
				
				var node = ruleTree.tree('find', data.code_cfig_id);
				
				
				//������������ID  Ϊϵͳ�Զ����ɵ�ID
//				if (node){
//					ruleTree.tree('update', {
//						target: node.target,
//						id: data.cfig_code_id
//					});
//				}
				//ѡ�������Ľڵ�
				ruleTree.tree('select', node.target);
				
				//var selected = ruleTree.tree('getSelected');
				
				//alert(selected.id+selected.text);
			}
		}
	});
}  


/**********************************************************************************
*��������: remove
*����˵��: �����ɾ��    ɾ�����ݿ�  ��ɾ������   δ���
*��������: joyon
*��������: 2014��6��26�� 14:40:39
*�޸���ʷ: 
***********************************************************************************/
function remove(){  
    var node = $('#identifier_tree').tree('getSelected');  
   
	top.$.messager.confirm("ɾ�����","�Ƿ�ɾ�����["+node.text+"]",function(r){
		if(r){
			$.ajax({
				url:ctx+"/common/identifier!removeRule.action",
				//url:ctx+"/houseownership/shiftreg!getRegInfo.action,
				data:{"code_id":code_id,"time":new Date()},
				dataType:"json",
				type:"post",
				contentType:"application/x-www-form-urlencoded; charset=GBK",
				success:function(data){
					if(data.sign=="success"){
						$('#identifier_tree').tree('remove', node.target);  
					}
					alert(data.msg);
				}
			});
		}
		
	});
    
    
    
}  

/**********************************************************************************
*��������: collapse
*����˵��: ������۵�
*��������: joyon
*��������: 2014��6��26�� 14:40:39
*�޸���ʷ: 
***********************************************************************************/
function collapse(){  
    var node = $('#identifier_tree').tree('getSelected');  
    $('#identifier_tree').tree('collapse',node.target);  
}  

/**********************************************************************************
*��������: expand
*����˵��: �����չ��
*��������: joyon
*��������: 2014��6��26�� 14:40:39
*�޸���ʷ: 
***********************************************************************************/
function expand(){  
    var node = $('#identifier_tree').tree('getSelected');  
    $('#identifier_tree').tree('expand',node.target);  
}  

/**********************************************************************************
*��������: initTree
*����˵��: ��ʼ����ǰ�����ṹ
*��������: joyon
*��������: 2014��6��26�� 14:40:33
*�޸���ʷ: 
***********************************************************************************/
function initTree(){
	ruleTree = $('#identifier_tree').tree({  
		url:ctx+"/identifier!getIdentifierTree.action",
	    animate: true, 
	    onClick:function(node){
	    	if(node.id)
	    	code_id = node.id;
	    	//alert(code_id);
	    	
	    	emptyUlTree();		//��չ�����
	    	
	    	$("#btnCharModify").css("display","none");
	    	
	    	//$('#form_rules').form('reset');
	    	//$('#form_rules')[0].reset();
	    	resetRules();
	    	
	    	getRule();			//��ȡ��ǰ����µĹ���
	    	//alert(node.id);
	    },
	    onBeforeEdit:function(node){
	    	editNodeText = $.trim(node.text);
	    },
	    onAfterEdit:function(node){
	    	//alert(node.id+node.text);
	    	//����༭ǰ�ͱ༭��û�ı� ��������ϵͳ
	    	if(editNodeText==$.trim(node.text)){
	    		return;
	    	}
	    	$.ajax({
	    		url:ctx+"/common/identifier!editRule.action",
	    		//url:ctx+"/houseownership/shiftreg!getRegInfo.action,
	    		data:{"code_id":node.id,"ruleName":node.text,"time":new Date()},
	    		dataType:"json",
	    		type:"post",
	    		contentType:"application/x-www-form-urlencoded; charset=GBK",
	    		success:function(data){
	    			
	    		}
	    	});
	    	
	    },
	    onCancelEdit:function(node){
	    	alert(node.id);
	    },
	    onDblClick:function(node){$(this).tree('beginEdit',node.target)}, 
	    onContextMenu: function(e,node){  
	        e.preventDefault();  
	        $(this).tree('select',node.target);  
	        $('#mm').menu('show',{  
	            left: e.pageX,  
	            top: e.pageY  
	        });  
	    }  
	});
	
}

/**********************************************************************************
*��������: initTabs
*����˵��: ��ʼ����ǰ��tabѡ�
*��������: joyon
*��������: 2014��6��26�� 14:40:39
*�޸���ʷ: 
***********************************************************************************/
function initTabs(){
	$('#div_tabs').tabs({  
	    border:false,  
	    onSelect:function(title){  
	    	if(title=='���ĳ��'){
	    		if(work_open_count==0){
	    			var Iframe=document.getElementById("work-iframe");
		    		if(Iframe.contentWindow.init){
		    			//alert(Iframe.contentWindow.init);
		    			Iframe.contentWindow.init(selectedProc);
		    		}
	    		}
	    		work_open_count++;
	    		
	    	}else if(title=="������"){
	    		if(work_open_examine==0){
	    			//examineInit();
	    		}
	    		$("#save").linkbutton('enable');
	    		work_open_examine++;
	    	}
	    }  
	}); 
}


/**********************************************************************************
*��������: initLiClickEvent
*����˵��: ��ʼ��li����¼�
*��������: joyon
*��������: 2014��6��26�� 14:40:39
*�޸���ʷ: 
***********************************************************************************/
function initLiClickEvent(){
	$('.nav ul li').click(function(){
		$(this).addClass('selected').siblings('li').removeClass('selected');
		selectedLi=$(this);
		
		//����ǳ����� ��Ŀ�������ѡ�   ����ֵ���ȥ   ��ʾ�޸İ�Ť
		if(this.id=="lichar"){
			selectedChar = $(this);
			
			var ruleValue =selectedLi.children("input")[0].value;
			$("#iptChar").val(ruleValue);
			//ѡ�г�����ѡ�
			$('#div_tabs').tabs('select', "������");
			
			//��ʾ��Ť
			$("#btnCharModify").css("display","inline-block");
		}else if(this.id=="lihousehold"){
			$('#div_tabs').tabs('select', "����");
		}else if(this.id=="liarea"){
			$('#div_tabs').tabs('select', "����");
		}else if(this.id=="liyear"){
			$('#div_tabs').tabs('select', "���");
		}else if(this.id=="lisquence"){
			$('#div_tabs').tabs('select', "��ˮ��");
		}
		
		//alert(this.id+ruleValue);
	});
	
}

/**********************************************************************************
*��������: upLi
*����˵��: li����
*��������: joyon
*��������: 2014��6��26�� 14:40:39
*�޸���ʷ: 
***********************************************************************************/
function upLi()
{
	 var onthis = $(selectedLi);
	 var getup = $(selectedLi).prev();
	 
	 if(getup){
		 
		 $(getup).before(onthis);
		 
	 }else{
		 alert("�������");
	 }
	 
}

/**********************************************************************************
*��������: downLi
*����˵��: li����
*��������: joyon
*��������: 2014��6��26�� 14:40:39
*�޸���ʷ: 
***********************************************************************************/
function downLi()
{
	 var onthis = selectedLi;
	 var getdown = selectedLi.next();
	 
	 if(getdown){
		 
		 $(getdown).after(onthis);
	 }else{
		 alert("������׶�");
	 }
	 
	
}

/**********************************************************************************
*��������: sortAppend
*����˵��: ��������������
*��������: joyon
*����˵��: id:ʶ����  text:��ʾ��text  rule:����
*��������: 2014��6��26�� 14:40:39
*�޸���ʷ: 
***********************************************************************************/
function sortAppend(id,text,rule){
	 var t = $('#ulTree');  
	 if(text){
		 t.append("<li id='li"+id+"'><label name='"+id+"'>"+text+"</label><input type='hidden' name='"+id+"' show='"+text+"'  value='"+rule+"'></input></li>");
	 }
}

/**********************************************************************************
*��������: getRule
*����˵��: ��ȡ����
*��������: joyon
*��������: 2014��6��26�� 14:40:39
*�޸���ʷ: 
***********************************************************************************/
function getRule(){
	$.ajax({
		url:ctx+"/common/identifier!getRule.action",
		//url:ctx+"/houseownership/shiftreg!getRegInfo.action,
		data:{"code_id":code_id,"time":new Date()},
		dataType:"json",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			if(data){
				if(data.rule_exper){
					var rule = eval(data.rule_exper);		//�����ݿ��й���ת��Ϊjson����
					
					ruleDataback(rule);			//�����ѡ�����
					
					for(var i=0;i<rule.length;i++){
						sortAppend(rule[i].id,rule[i].show,rule[i].text);
					}
					
					initLiClickEvent();
				}
				//alert($.toJSON(data));
			}
		}
	});
}

/**********************************************************************************
*��������: saveRule
*����˵��: �������
*��������: joyon
*����˵��: id:ʶ����  text:��ʾ��text
*��������: 2014��6��26�� 14:40:39
*�޸���ʷ: 
***********************************************************************************/
function saveRule(){
	var inputs = $("#ulTree input");
	var arr = new Array();
	for(var i = 0;i<inputs.length;i++){
		arr[i] = {};
		//alert(inputs[i].value+inputs[i].name);
		arr[i].id=inputs[i].name;
		arr[i].text=inputs[i].value;
		arr[i].show=inputs[i].show;
	}
	
	$.ajax({
		url:ctx+"/common/identifier!saveRule.action",
		//url:ctx+"/houseownership/shiftreg!getRegInfo.action,
		data:{"code_id":code_id,"rule":$.toJSON(arr),"time":new Date()},
		dataType:"json",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		success:function(data){
			if(data){
				alert("����ɹ���");
			}
		}
	});
	//alert($.toJSON(arr));
}

/**********************************************************************************
*��������: userYear
*����˵��: �������
*��������: joyon
*��������: 2014��6��26�� 14:40:39
*�޸���ʷ: 
***********************************************************************************/
function userYear(){
	
	//sortAppend();
}

/**********************************************************************************
*��������: appendYear
*����˵��: �������
*��������: joyon
*����˵��:rule:yy  yyyy ���������������λ  ������λ
*��������: 2014��6��26�� 14:40:39
*�޸���ʷ: 
***********************************************************************************/
function appendYear(rule){
	var tmpText = "���2λ";
	if(rule=='yyyy'){
		tmpText = "���4λ";
	}else if(rule=='yyyymmdd'){
		tmpText = "������yyyymmdd";
	}else if(rule=='yyyy-mm-dd'){
		tmpText = "������yyyy-mm-dd";
	}
	
	//����Ѿ�������� �����   �������
	var oldYear = $("#liyear");
	if(oldYear.length>0){
		//alert(oldYear);
		var yearInput = oldYear.children("input")[0];	
		yearInput.value=rule;
		$(yearInput).attr("show",tmpText);
		
		var yearLabel =  oldYear.children("label")[0];
		yearLabel.innerHTML=tmpText;
	}else{
		
		sortAppend("year",tmpText,rule);
		initLiClickEvent();
	}
}

/**********************************************************************************
*��������: appendSquence
*����˵��: �����ˮ�Ź���
*��������: joyon
*����˵��: length:��ˮ�ų���
*��������: 2014��6��26�� 14:40:39
*�޸���ʷ: 
***********************************************************************************/
function appendSquence(length){
	var tmpText = "��ˮ��("+length+")λ";
	
	//����Ѿ�����ˮ���� �����޸���ˮ��    ��������
	var oldSquence = $("#lisquence");
	if(oldSquence.length>0){
//		oldSquence.remove();
		//�޸�����inputֵ  
		var squenceInput = oldSquence.children('input')[0];
		squenceInput.value=length;
		$(squenceInput).attr("show",tmpText);
		
		//�޸���ʾlabelֵ 
		var squenceLabel = oldSquence.children('label')[0];
		squenceLabel.innerHTML=tmpText;
		
		
	}else{
		sortAppend("squence",tmpText,length);
		initLiClickEvent();
	}
}


/**********************************************************************************
*��������: userArea
*����˵��: ����Ƿ�ʹ������
*��������: joyon
*����˵��: cbx����ѡ��
*��������: 2014��6��26�� 14:40:39
*�޸���ʷ: 
***********************************************************************************/
function userArea(cbx){
	
	if(cbx.checked){
		
		//��������������κβ���
		var oldArea = $("#liarea");
		//alert(oldArea);
		if(oldArea)
			oldArea.remove();
		
		//û����append  �������¼�
		
		sortAppend("area","��","area");
		initLiClickEvent();
	}else{
		var oldArea = $("#liarea");
		if(oldArea)
			oldArea.remove();
	}
}

/**********************************************************************************
*��������: userHousehold
*����˵��: ����Ƿ�ʹ�û���
*��������: joyon
*����˵��: cbx����ѡ��
*��������: 2014��6��26�� 14:40:39
*�޸���ʷ: 
***********************************************************************************/
function userHousehold(cbx){
	
	if(cbx.checked){
		
		//��������������κβ���
		var oldHousehold = $("#lihousehold");
		//alert(oldArea);
		if(oldHousehold)
			oldHousehold.remove();
		
		//û����append  �������¼�
		
		sortAppend("household","����","household");
		initLiClickEvent();
	}else{
		var oldHousehold = $("#lihousehold");
		if(oldHousehold)
			oldHousehold.remove();
	}
}

/**********************************************************************************
*��������: emptyUlTree
*����˵��: ��չ�����
*��������: joyon
*��������: 2014��6��30�� 09:32:54
*�޸���ʷ: 
***********************************************************************************/
function  emptyUlTree(){
	$("#ulTree").empty();
}

/**********************************************************************************
*��������: ruleDataback
*����˵��: �������ݻ���  
*��������: joyon
*��������: 2014��6��30�� 09:58:37
*�޸���ʷ: 
***********************************************************************************/
function ruleDataback(data){
	//alert($.toJSON(data));
	//code_id
	for(var i=0;i<data.length;i++){
		if(data[i].id=="household"){
			$("#iptHousehold").attr("checked","checked");
		}else if(data[i].id=="area"){
			$("#iptArea").attr("checked","checked");
		}else if(data[i].id=="year"){
			if(data[i].text=="yy"){
				$("#ra_y2").attr("checked","checked");
			}else if(data[i].text=="yyyy"){
				$("#ra_y4").attr("checked","checked");
			}else if(data[i].text=='yyyymmdd'){
				$("#ra_ymd").attr("checked","checked");
			}else if(data[i].text=='yyyy-mm-dd'){
				$("#ra_ymd1").attr("checked","checked");
			}
		}else if(data[i].id=="squence"){
			$("#input_squence").numberspinner("setValue",data[i].text);
		}else if(data[i].id=="char"){
			
		}
	}
}

function resetRules(){
	$("#iptHousehold").removeAttr("checked","checked");
	
	$("#iptArea").removeAttr("checked","checked");
	
	$("#input_squence").numberspinner("clear");
	
	$("#iptChar").val("");
	
	$("input[name='ra_year']").removeAttr("checked","checked");
}


