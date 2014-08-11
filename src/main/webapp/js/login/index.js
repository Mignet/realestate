$(function(){
	//checkUser();
	$('body').layout({
		fit:true
	});
	/*if(window.ActiveXObject){
		var wsh = new window.ActiveXObject("WScript.Shell");  
		wsh.sendKeys("{F11}"); 
	}*/
});
Event.onResizend(function(){
	$('#tabs').tabs('resize');
});

function northLoad(){
	$('#text').bind('focus',function(){
		changeValue(this);
	}).bind('blur',function(){
		changeValue(this);
	});
	
	$('#sbut').bind('click',function(){
		alert(1);
	});
}
function openTab(title,url){
	var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';  
    $('#tabs').tabs('add',{  
        title:title,  
        content:content,  
        closable:true  
    });  
}
//���νڵ㱻ѡ�е�ʱ�򴥷����¼�
function showDoc(node){
	//���ڵ���Ҷ�ӽڵ��ʱ��
	if(node.id && !node.children){
		//�����¼��Ľڵ�
		openTab(node.text,node.attributes.url);
	}
}


function loadSuccess(){
	//���ز˵�
	/*$('#menu').frameMenu({
		//�˵����
		width: 200,
		ͳһ�˵����ط�ʽ�������ǣ�
			tab: ѡ���
			window���´��ڴ�
			frame����һ��frame�д�
			
		open_type: 'tab',
		//�˵�����Դ
		url:'../leftmenu/menu!getFormTreeJson.action?time='+getDateStr(),
		//url:'${ctx}/json/tree_data.json',
		//�˵��򿪲���
		param: '#tabs',
		//�˵����سɹ���Ļص�����
		onLoadSuccess:function(){
			//�˵����غ����Ҳ�tabs��������Ĭ�ϴ򿪵�ҳ��			
			//$.fn.frameMenu.openMenus.tab('��������','../workflow/workflow.action','#tabs');
			$.fn.frameMenu.openMenus.tab('&nbsp;&nbsp;&nbsp;&nbsp;��ҳ&nbsp;&nbsp;&nbsp;&nbsp;','./login!home.action?u=home','#tabs');
		}
	});*/
	$('#menu').tree({    
    	url:'../leftmenu/menu!getFormTreeJson.action?time='+getDateStr(),
    	onClick:function(node){
    		alert(node.text);  // alert node text property when clicked
    	},
    	onLoadSuccess:function(){
			//�˵����غ����Ҳ�tabs��������Ĭ�ϴ򿪵�ҳ��			
			//$.fn.frameMenu.openMenus.tab('��������','../workflow/workflow.action','#tabs');
			$.fn.frameMenu.openMenus.tab('&nbsp;&nbsp;&nbsp;&nbsp;��ҳ&nbsp;&nbsp;&nbsp;&nbsp;','./login!home.action?u=home','#tabs');
		}
    })
		
	$('#switch').toggle(function(){
		$(this).animate({left:2},400);
		$('#cleft').animate({'margin-left':-201},400,function(){
			$('#switch').css('background','url(${ctx}/images/index/right.png) no-repeat');
		});
		$('#cright').animate({'margin-left':2},400,function(){
			$('#tabs').tabs('resize');
		});
	},function(){
		$(this).animate({left:203},400);
		$('#cleft').animate({'margin-left':0},400,function(){
			$('#switch').css('background','url(${ctx}/images/index/left.png) no-repeat');
		});
		$('#cright').animate({'margin-left':203},400,function(){
			$('#tabs').tabs('resize');
		});
	});
}
function changeValue(o){
	if($(o).val() == '����ؼ�������..'){
		$(o).val('');
		$(o).css('color','#333333');
	}else{
		if($(o).val() == ''){
			$(o).val('����ؼ�������..');
			$(o).css('color','#cccccc');
		}
	}
}

function openTab(title,url) {
    var title = title||"";
    if ($('#tabs').tabs('exists', title)) {
        $('#tabs').tabs('select', title);
    } else {
		$('#tabs').tabs('add', {
			title: title,
			content: '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%"></iframe>',
			closable: true
		});
    }
}

function reloadTad(){
	var tab = $('#tabs').tabs('getSelected');
	if(tab){
		$('#tabs').tabs('update', {
			tab: tab,
			options:{}
		});
	}
}

function closeThis(){
	var value = $('#tabs').tabs('getSelected');
	if(value){
		if(value.panel('options').closable){
			$('#tabs').tabs('close',value.panel('options').title);
		}
	}
}

function closeOthers(){
	var tabs = $('#tabs').tabs('tabs');
	var tab = $('#tabs').tabs('getSelected');
	if(tabs && tab){
		var title = tab.panel('options').title;
		var tits = [];
		$.each(tabs,function(index,value){
			if(title != value.panel('options').title && value.panel('options').closable){
				tits.push(value.panel('options').title);
			}
		});
		$.each(tits,function(index,value){
			$('#tabs').tabs('close',value);
		});
	}
}

function closeAll(){
	var tabs = $('#tabs').tabs('tabs');
	if(tabs){
		var tits = [];
		$.each(tabs,function(index,value){
			if(value.panel('options').closable){
				tits.push(value.panel('options').title);
			}
		});
		$.each(tits,function(index,value){
			$('#tabs').tabs('close',value);
		});
	}
}

//�˳�ȷ��
function logoutConf(){
	$.messager.confirm('�˳�ȷ��', 'ȷ���˳���', function(r){
		if (r) {
			logout();
		}
	});
}

//�˳�
function logout(){
	
	$.ajax({
		url:"login!userLoginOut.action",
		type:"post",
		data:{"random":Math.random()},
		dataType:"json",
		success:function(data){
			window.location.assign("login!input.action?loginflag=0&timestamp="+getDateStr());
		}
	});
	
}

