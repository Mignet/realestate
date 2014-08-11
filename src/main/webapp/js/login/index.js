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
//树形节点被选中的时候触发的事件
function showDoc(node){
	//当节点是叶子节点的时候
	if(node.id && !node.children){
		//触发事件的节点
		openTab(node.text,node.attributes.url);
	}
}


function loadSuccess(){
	//加载菜单
	/*$('#menu').frameMenu({
		//菜单宽度
		width: 200,
		统一菜单加载方式。可以是：
			tab: 选项卡打开
			window：新窗口打开
			frame：在一个frame中打开
			
		open_type: 'tab',
		//菜单数据源
		url:'../leftmenu/menu!getFormTreeJson.action?time='+getDateStr(),
		//url:'${ctx}/json/tree_data.json',
		//菜单打开参数
		param: '#tabs',
		//菜单加载成功后的回调函数
		onLoadSuccess:function(){
			//菜单加载后，在右侧tabs内容区域默认打开的页面			
			//$.fn.frameMenu.openMenus.tab('待办事项','../workflow/workflow.action','#tabs');
			$.fn.frameMenu.openMenus.tab('&nbsp;&nbsp;&nbsp;&nbsp;主页&nbsp;&nbsp;&nbsp;&nbsp;','./login!home.action?u=home','#tabs');
		}
	});*/
	$('#menu').tree({    
    	url:'../leftmenu/menu!getFormTreeJson.action?time='+getDateStr(),
    	onClick:function(node){
    		alert(node.text);  // alert node text property when clicked
    	},
    	onLoadSuccess:function(){
			//菜单加载后，在右侧tabs内容区域默认打开的页面			
			//$.fn.frameMenu.openMenus.tab('待办事项','../workflow/workflow.action','#tabs');
			$.fn.frameMenu.openMenus.tab('&nbsp;&nbsp;&nbsp;&nbsp;主页&nbsp;&nbsp;&nbsp;&nbsp;','./login!home.action?u=home','#tabs');
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
	if($(o).val() == '输入关键字搜索..'){
		$(o).val('');
		$(o).css('color','#333333');
	}else{
		if($(o).val() == ''){
			$(o).val('输入关键字搜索..');
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

//退出确认
function logoutConf(){
	$.messager.confirm('退出确认', '确认退出？', function(r){
		if (r) {
			logout();
		}
	});
}

//退出
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

