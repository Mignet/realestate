var printviewck;
$(function(){
	printviewck = $('#printview').click(function(){
		 var regcode = $('#regcode').val();
		 if(regcode){
			 var proc = {'url':'/reportmanage/barcode-print!report.action?regcode='+regcode};
			 var objWindow2 = {
						//����Ԫ�ص�id
						id: 'wind_djby2',
						//����iframe��src
						src: ctx+'/jsp/reportmanage/pdf.jsp?time='+new Date(),
						//�ر�ʱ�Ƿ����ٴ��ڡ������ٵĻ���ÿ�δ򿪴��ڶ������һ���´���Ԫ�ء�
						destroy: true,
						//���ڱ���
						title: '��ӡԤ��',
						//���ڿ�
						width: 800,
						//���ڸ�
						height: 598,
						modal: true,
						//������iframe��window�����onLoad�ص���������
						onLoad:	function(){
						   this.init(proc);
						}
					};
			   /* if(j == 0){
			    	$.messager.alert("��ʾ","�빴ѡ��Ҫ��ӡ�ļ�¼��","warning");
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


