var printviewck;
$(function(){
	printviewck = $('#printview').click(function(){
		 var regcode = $('#regcode').val();
		 if(regcode){
			 var proc = {'url':'/reportmanage/barcode-print!report.action?regcode='+regcode};
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
			   /* if(j == 0){
			    	$.messager.alert("提示","请勾选需要打印的记录！","warning");
			    	return;
			    }*/
				openInTopWindow(objWindow2);
		 }
	});
	$(document).keydown(function(event){ 
		var e = event || window.event; 
		var k = e.keyCode || e.which; 
		if(k == 13){ 
			printviewck.click();
		}
	}); 
	$('#regcode').focus();
});


