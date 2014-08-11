$(function(){
	
	
	$('#dock').Fisheye({
		maxWidth: 30,
		items: 'a',
		itemsText: 'span',
		container: '.dock-container',
		itemWidth: 25,
		proximity: 20,
		alignment : 'left',
		valign: 'bottom',
		halign : 'center'
	});
	
	$('#litmenu').hover(function(){
		isOverMenu=true;
		hoverLitMenu();
	},function(){
		isOverMenu=false;
		$('#lit_menu').hover(function(){
			hideMenu=false;
		},function(){
			hideMenu=true;
			_closeMenu();
		});
		if(!isOverMenu && hideMenu){
			closeMenu();
		}
	});
	$('#pop li').bind('mouseover',function(){
		$('#pop').find('li').each(function(index, element) {
            $(element).css('background','#FFF');
        });
		$(this).css('background','#d0e6fb');
	}).bind('mouseout',function(){
		$(this).css('background','#FFF');
	});
	
	$("#btnHome").click(function(){
		window.open('http://192.168.102.20:5001/suplisweb/addresslist/addresslist.jsp');
	});
	
	/*$('#win').window({  
		url:'http://192.168.102.20:5001/suplis/feedback/js/floatDialogue/floatDiaNew.jsp',  
	    destroy:true,//点关闭时是否注销该窗口释放内容  
	    title:'反馈',
	    width:600,  
	    height:400,  
	    modal:true  
	});*/

	$("#btnFeedback").click(function(){
		//$('#win').window('open');
//		$('#win').window({  
//		    destroy:true,//点关闭时是否注销该窗口释放内容  
//		    title:'反馈',
//		    width:600,  
//		    height:400,  
//		    modal:true  
//		});
//		$('#win').window('refresh','http://192.168.102.20:5001/suplis/feedback/js/floatDialogue/floatDiaNew.jsp');  
//		window.open('http://192.168.102.20:5001//suplis/feedback/js/floatDialogue/floatDiaNew.jsp');
	});
	
	$("#btnKnow").click(function(){
		window.open('http://192.168.102.20:5001/suplisweb/zlzx/index.jsp');
	});
	
	$("#btnHouse").click(function(){
		window.open('http://192.168.103.6:7001/szgh/default.jsp');
	});
	
	$("#btnApi").click(function(){
		window.open('../sysmanage/monitor!home.action');
	});
	
	$("#btnChat").click(function(){
		window.open('http://bbs.szpl.gov/');
	});
	/*
	$("#btnLogout").click(function(){
		logoutConf();
	});
	*/
});

var isOverMenu=false,hideMenu=true;
function hoverLitMenu(){
	isOverMenu=false;
	$("#lit_menu").css("display","block");
	$("#lit_menu").css("left", $("#litmenu").offset().left);
	$("#lit_menu").css("top", $("#litmenu").offset().top+24);
	
	$("#lit_menu #litmc").height($("#lit_menu #pop li").length*$("#lit_menu #pop li").height()+$("#lit_menu #pop li").length*1+5);
	$("#lit_menu #pop").css("left",3);
	$("#lit_menu #pop").css("top",8);
}
function closeMenu(){
	setTimeout(_closeMenu,50);
}
function _closeMenu(){
	if(!isOverMenu){
		if(hideMenu){
			$("#lit_menu").css("display","none");
		}
	}
}
	
	
	
	


