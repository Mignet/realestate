
//���մ� identifier.jsp�������� ѡ�нڵ������
var nodeData;

var bhObject;

//���մӺ�̨���صĹ�������
var ruleData;

//��ǰ��� �ı��  id  ������Ωһ ��
var identifier_id;

//��ǰ��ŵ���ϸ��Ϣ  Ωһ ��
var identifier;

//��ǰ������rule_id;
var rule_id;

//��ǰѡ���li  ��Ҫ���������ƶ�
var selectedLi;

//��ǰѡ��ı������
var selectedIdentifier;

window.onload=function(){
		init();
}

//�����ڵ��õı��س��軯����  ��������ѡ������ڵ������
function init(){
	//nodeData=node;
	//alert("identifierEdit init method...");
	//$('#editmain').tabs();
	//var test=JSON.stringify(node);
	//alert(test);
	//$("#name").val(node.text);
	//$("#ruleDetail").form();
	$("#mainLayoutDiv").layout({
		fit:true
	});
	
	$("#syMain").layout({
		fit:true
	});	
	
	$("#syLayout1").layout({
		fit:true
	});	
	
	$('#identifier_tree').tree({  
		url:ctx+"/json/identifierMenuTree.json",
		//url:ctx+"/leftmenu/tree!getTreeJson.action",
		onClick: function(node){
			//alert(node.text);  // alert node text property when clicked
			selectedIdentifier=node;
			
			//��ȡ���������Ϣ
			getSyList();
			
			alert(JSON.stringify(node));
			//doDetail(node);
			
		}
	});
	
	//���ɱ�Ź���ѡ�
	$('#tt').tabs({  
	    border:false,  
	    onSelect:function(title){  
	       	// alert(title+' is selected'); 
	       // $(this).css('display','block').siblings('div').css('display','none');
	    }  
	});  
	//getIdentifierIdByName(nodeData.text);
	
	//��ȡ����������Ϣ
	getRuleSortList();
	
	
	
	
	$("#btnSyAdd").click(function(){
		if(rule_id){
			rule_id=null;
			$("#rule").val("");
			return;
		}
		var tempRule=$.trim($("#rule").val());
		//����Ϊ��ʱ
		if(tempRule.length==0){
			alert("������Ϊ��");
			return;
		}
		alert("createRule...");
		
		createSyByRule();
	});
	
	$("#btnSyDelete").click(function(){
		if(rule_id==null){
			alert("��ѡ������");
			return;
		}
		deleteSyByRuleId();
	});
	
	$("#btnSumbit").click(function(){
		doRuleSave();
		
	});
	
	$("#cbYear").click(function(){
		checkYear();
	});
	
	$("#bh_longth").numberspinner({  
	    min: 4,  
	    max: 10,  
	    editable: false  
	});
	
	//�޸ı�ų���ʱ���÷���
	$("#bh_longth").change(function(){
		//numberspinner('setValue',4);
		var bh_longth=7;
		modifyBhLongth(bh_longth);
	});
	//��ȡ�Ƿ�ʹ�����
	//getCheckYear();
	//��ȡ������еĳ���
	//getBhLongth();
	
	
}

//��ȡ���˳�������List
function getRuleSortList(){
	$.ajax({
		type:"POST",
		url:"../common/identifier!getRuleSortList.action"+"?time="+new Date(),
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"name":nodeData.text},
		dataType:"json",
		
		success :function(data){
			data=data.rows;
			//alert("method:getSyList  data:"+JSON.stringify(data)); 
			//ata=JSON.parse(data);
			//alert(data.length+data[0].rule_name);
			
			//��ȡ������Ϣ�ɹ���  ��ӵ�ul��   ����rule_id  ��rule_name��ӽ�����
			$("#ulSort").empty();
			for(var i=0;i<data.length;i++){
				
				$("#ulSort").append("<ol id='"+data[i].SORT+"' value='"+data[i].RULE_TYPE+"'>"+data[i].RULE_TYPE+"</ol>");
				
			}
			
		
			
			//���˳�����ul�ĵ���¼�
			$('.ulSy ul ol').click(function(){
				rule_id='';
				$(this).addClass('selected').siblings('ol').removeClass('selected');
				//alert($(this).text());
				//alert($(this).attr("id"));
				//rule_id=$(this).attr("id");
				//getRuleDetail(rule_id);
				selectedLi=$(this);
			});
		}
		
	});
}

//ͨ���ĺ�����ȡ�ø��ĺ�����Ĺ���   �����ul   �ٰ����ݲ���ӵ�ul����
function getSyList(){
	
	//���ul�µ�����li
	
	//ͨ���ĺ�����ȡ�ø��ĺ�����Ĺ���  ����ӵ�ul����
	$.ajax({
		type:"POST",
		url:"../common/identifier!getRuleByRuleNameAndIdentifierId.action"+"?time="+new Date(),
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"name":nodeData.text,"rule_name":"����"},
		dataType:"json",
		
		success :function(data){
			data=data.rows;
			//alert("method:getSyList  data:"+JSON.stringify(data)); 
			//alert("method:getSyList  data:"+data);
			//data=JSON.parse(data);
			//alert(data.length+data[0].rule_name);
			
			//��ȡ������Ϣ�ɹ���  ��ӵ�ul��   ����rule_id  ��rule_name��ӽ�����
			for(var i=0;i<data.length;i++){
				$("#ulSy").append("<li id='"+data[i].RULE_ID+"'>"+data[i].RULE+"</li>");
				
			}
			
			//li����¼�   ��������ǰ���li ���selected class ���ƶ�ͬ��li�� selected class
			$('.nav ul li').click(function(){
				rule_id='';
				$(this).addClass('selected').siblings('li').removeClass('selected');
				//alert($(this).text());
				//alert($(this).attr("id"));
				rule_id=$(this).attr("id");
				getRuleDetail(rule_id);
			});
			
			
		}
		
	});
}

//��ȡ�����ϸ��Ϣ  ͨ���������
function getIdentifierByName(){
	
	
	
	
	
}

//��ȡ���ID ͨ���������
function getIdentifierIdByName(name){
	
	$.ajax({
		url:"idenitifierDelegate/getIdentifierIdByName.run",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		dataType:"json",
		data:{"name":name},
		success:function(data){
			identifier_id=data.id;
			//alert("getIdentifierIdByName:"+identifier_id);
		}
		
	});
}
//������������  ����ͨ��������ڱ�ű��ȡ��� ID  Ȼ��ͨ�����id�ڱ�Ź�����ﴴ������  Ȼ���ڱ�Ź���������в������������Ϣ         
function createSyByRule(){
	
	
	
	var ruleType="����";
	var name=nodeData.text;
	
	
	
			
			
			
			var rule=$("#rule").val();
			//alert(identifier+identifier.id);
			alert("createSyByRule������  name:"+name+" rule:"+rule);
			$.ajax({
				url:"../common/identifier!createRuleByIdentifierId.action"+"?time="+new Date(),
				type:"post",
				dataType:"json",
				contentType:"application/x-www-form-urlencoded; charset=GBK",
				data:{"rule":rule,"identifier_id":identifier_id,"rule_type":ruleType},
				success:function(data){
					
							alert("createSy success function:"+JSON.stringify(data));
							$(".nav ul li").remove();
							
							//��ȡ������Ϣ
							getSyList();
							
							//��ȡ����������Ϣ
							getRuleSortList();
						
				}
				
			});
	
	

	
	
	
	
}


//ɾ������ͨ��  rule_id
function deleteSyByRuleId(){
	var ruleType="����";
	alert("deleteSyByRuleId....");
	$.ajax({
		url:"../common/identifier!deleteRuleByRuleId.action"+"?time="+new Date(),
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"rule_id":rule_id,"identifier_id":identifier_id,"rule_type":ruleType},
		success:function(data){
			$(".nav ul li").remove();
			getSyList();
		}
		
	});
}

//�½�������  
function createCyz(){
	
}

function deleteCyz(){
	
}

function doRuleSave(){
	
	//ͨ��ѡ��ı������ ��ȡ�����Ϣ
	var name=nodeData.text;
	alert("getIdentifierByName  name:"+name);
	
			$.ajax({
				url:"../common/identifier!save.action"+"?time="+new Date(),
				type:"post",
				contentType:"application/x-www-form-urlencoded; charset=GBK",
				data:{"identifier_id":identifier_id},
				success:function(data){
					alert('Ok');
			
					
				}
			});
	
	
}


//��ȡ������ϸ��Ϣ  �������  rule_id
function getRuleDetail(rule_id){
	$.ajax({
		url:"../common/identifier!getRuleById.action"+"?time="+new Date(),
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		dataType:"json",
		data:{"rule_id":rule_id},
		success:function(data){
			$("#testLabel").text(JSON.stringify(data));
			
			//alert(JSON.stringify(data));
			$("#rule_name").val(data.RULE_NAME);
			$("#rule").val(data.RULE);
			//alert("getRuleDetail success");
		}
	});
	
}

//li����
function UpLi()
{
	 var onthis = $(selectedLi);
	 var getup = $(selectedLi).prev();
	 
	 //���ѡ�����һ����ֵ  Ҳ���Ƿ��ֵ   ���������   �������κβ���
	 if(getup.attr("value")){
		 
		//�ѵ�ǰ��id�������id����
		 var tempId=selectedLi.attr("id");
		 selectedLi.attr("id",selectedLi.prev().attr("id"));
		 selectedLi.prev().attr("id",tempId);
		 alert(selectedLi.attr("value")+selectedLi.attr("id"));
		 
		 
		 $.ajax({
			 url:"../common/identifier!changeRuleSort.action"+"?time="+new Date(),
				type:"post",
				contentType:"application/x-www-form-urlencoded; charset=GBK",
				data:{"selectedType":selectedLi.attr("value"),"selectedSort":selectedLi.attr("id"),"otherType":selectedLi.prev().attr("value"),"otherSort":selectedLi.prev().attr("id"),"identifier_id":identifier_id},
				success:function(data){
					
				}
		 });
		 $(getup).before(onthis);
		 
	 }else{
		 alert("�������");
	 }
	 
}

//li����
function DownLi()
{
	 var onthis = selectedLi;
	 var getdown = selectedLi.next();
	 
	 if(getdown.attr("value")){
		//�ѵ�ǰ��id�������id����
		 var tempId=selectedLi.attr("id");
		 selectedLi.attr("id",selectedLi.next().attr("id"));
		 selectedLi.next().attr("id",tempId);
		 alert(selectedLi.attr("value")+selectedLi.attr("id"));
		 
		 $.ajax({
			 url:"../common/identifier!changeRuleSort.action"+"?time="+new Date(),
				type:"post",
				contentType:"application/x-www-form-urlencoded; charset=GBK",
				data:{"selectedType":selectedLi.attr("value"),"selectedSort":selectedLi.attr("id"),"otherType":selectedLi.next().attr("value"),"otherSort":selectedLi.next().attr("id"),"identifier_id":identifier_id},
				success:function(data){
					
				}
		 });
		 
		 $(getdown).after(onthis);
	 }else{
		 alert("������׶�");
	 }
	 
	
}
//liɾ��
function RemoveLi(){
 	$(selectedLi).remove();
}


//��ȡ��������ĳ���  
function getBhLongth(){
	$.ajax({
		url:"idenitifierDelegate/getSeqNumberLength.run",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"identifier_id":identifier_id},
		dataType:"json",
		success:function(data){
			if(data){
				$("#bh_longth").numberspinner('setValue',data.rule);
				return;
			}
			$("#bh_longth").numberspinner('setValue',4);
			
		}
	});
	
}

//�޸ı�ų��� 
function modifyBhLongth(bh_longth){
	
	$.ajax({
		url:"idenitifierDelegate/getSeqNumberLength.run",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"identifier_id":identifier_id,"bh_longth":bh_longth},
		dataType:"json",
		success:function(data){
			
			
		}
	});
}

//��ȡ�Ƿ�ʹ�����
function getCheckYear(){
	$.ajax({
		url:"idenitifierDelegate/isUserYear.run",
		type:"post",
		dataType:"json ",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"identifier_id":identifier_id,"random":Math.random()},
		success:function(data){
			// alert(data.rule);
			if(data.rule){
				
				var isCheck=data.rule;
				if(isCheck=="isCheck"){
					$("#cbYear").attr("checked",true);
				}else{
					$("#cbYear").attr("checked",false);
				}
			}
			
		}
	});
}

//�޸��Ƿ�ʹ�����
function modifyYear(isCheck){
	//alert(identifier_id);
	$.ajax({
		url:"idenitifierDelegate/isUserYear.run",
		type:"post",
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		data:{"isCheck":isCheck,"identifier_id":identifier_id,"random":Math.random()},
		success:function(data){
			data.isCheck;
		}
	});
}
//����checkbox�¼�  isCheck��ѡ��  unCheck�ǲ�ѡ��
function checkYear(){
	var isCheck='unCheck';
	//alert("checkYear()");
	var isCheck=$("#cbYear").attr("checked");
	if(isCheck){
		//���ѡ�����������Ч
		//alert("checked");
		isCheck='isCheck';
	}else{
		isCheck='unCheck';
	}
	
	modifyYear(isCheck);
}

