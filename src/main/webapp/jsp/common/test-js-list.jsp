<%@ page language='java' contentType='text/html; charset=GBK'
	pageEncoding='GBK'%>
<%@ include file="/base/taglibs.jsp"%>
<%@ include file="/base/prefix.jsp"%>
<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=GBK'>
<title>Insert title here</title>
</head>
<body>
<input type="button" onclick="testddd();" value="test">
<div id='gb'></div>
<s:include value="divshow-comment.jsp"></s:include>
<div id='_gbfeepage'>
<!-- <table width='98%' border='0' align='center'>
	<tr id='_gbfeepage_paymethod' style='display: none'>
		<td>Ӧ�ɽ�</td>
		<td id='_gbfeepage_paymethod_amount'>100Ԫ</td>
		<td>֧����ʽ��</td>
		<td><select id='_gbfeepage_paymethod_list'>
			<option value='01'>�ֽ�</option>
		</select></td>
		<td colspan='2'>&nbsp;</td>
	</tr>
	<tr id='_gbfeepage_pos' style='display: none'>
		<td>POS�ţ�</td>
		<td><input id='_gbfeepage_pos_input'></input></td>
		<td>ȷ�Ϻţ�</td>
		<td><input id='_gbfeepage_pos_inputre'></input></td>
		<td colspan='2'>&nbsp;</td>
	</tr>
	<tr id='_gbfeepage_check' style='display: none'>
		<td>֧Ʊ�ţ�</td>
		<td><input id='_gbfeepage_check_input'></input></td>
		<td>ȷ�Ϻţ�</td>
		<td><input id='_gbfeepage_check_inputre'></input></td>
		<td colspan='2'>&nbsp;</td>
	</tr>
	<tr id='_gbfeepage_invoice' style='display: none'>
		<td>��Ʊ���ͣ�</td>
		<td><select id='_gbfeepage_invoice_select'>
			<option value='01'>ǧλ</option>
		</select></td>
		<td>��Ʊ�ţ�</td>
		<td><input id='_gbfeepage_invoice_input'></input></td>
		<td>ȷ�Ϻţ�</td>
		<td><input id='_gbfeepage_invoice_inputre'></input></td>
	</tr>
	<tr id='_gbfeepage_receipt' style='display: none'>
		<td>�վ����ͣ�</td>
		<td><select id='_gbfeepage_receipt_select'>
			<option value='01'>ǧλ</option>
		</select></td>
		<td>�վݺţ�</td>
		<td><input id='_gbfeepage_receipt_input'></input></td>
		<td>ȷ�Ϻţ�</td>
		<td><input id='_gbfeepage_receipt_inputre'></input></td>
	</tr>
	<tr id='_gbfeepage_factamount' style='display: none'>
		<td>ʵ�գ�</td>
		<td><input id='_gbfeepage_factamount_input'></input></td>
		<td>���㣺</td>
		<td><input id='_gbfeepage_factamount_input1'></input></td>
		<td colspan='2'>&nbsp;</td>
	</tr>
</table> -->
</div>
</body>
<script type='text/javascript'>
//js test
//namespace
var _gbcom = {};
_gbcom.feepage = (function(){
	var html = '';
	var defaultdataelement = ['id','name','sex'];
	function initpage(){
		html = "<table width='98%' border='0' align='center'>"+
			 	"<tr id='_gbfeepage_paymethod' style='display: none'> "+
				"<td>Ӧ�ɽ�</td>"+
				"<td id='_gbfeepage_paymethod_amount'>100Ԫ</td>"+
				"<td>֧����ʽ��</td>"+
				"<td><select id='_gbfeepage_paymethod_list'>"+
				"<option value='01'>�ֽ�</option> <option value='02'>��������</option>"+
				"</select></td>"+
				"<td colspan='2'>&nbsp;</td>"+
				"</tr>"+
				"<tr id='_gbfeepage_pos' style='display: none'>"+
				"<td>POS�ţ�</td>"+
				"<td><input id='_gbfeepage_pos_input'></input></td>"+
				"<td>ȷ�Ϻţ�</td>"+
				"<td><input id='_gbfeepage_pos_inputre'></input></td>"+
				"<td colspan='2'>&nbsp;</td>"+
				"</tr>"+
				"<tr id='_gbfeepage_check' style='display: none'>"+
				"<td>֧Ʊ�ţ�</td>"+
				"<td><input id='_gbfeepage_check_input'></input></td>"+
				"<td>ȷ�Ϻţ�</td>"+
				"<td><input id='_gbfeepage_check_inputre'></input></td>"+
				"<td colspan='2'>&nbsp;</td>"+
				"</tr>"+
				"<tr id='_gbfeepage_invoice' style='display: none'>"+
				"<td>��Ʊ���ͣ�</td>"+
				"<td><select id='_gbfeepage_invoice_select'>"+
				"<option value='01'>ǧλ</option>"+
				"</select></td>"+
				"<td>��Ʊ�ţ�</td>"+
				"<td><input id='_gbfeepage_invoice_input'></input></td>"+
				"<td>ȷ�Ϻţ�</td>"+
				"<td><input id='_gbfeepage_invoice_inputre'></input></td>"+
				"</tr>"+
				"<tr id='_gbfeepage_receipt' style='display: none'>"+
				"<td>�վ����ͣ�</td>"+
				"<td><select id='_gbfeepage_receipt_select'>"+
				"<option value='01'>ǧλ</option>"+
				"</select></td>"+
				"<td>�վݺţ�</td>"+
				"<td><input id='_gbfeepage_receipt_input'></input></td>"+
				"<td>ȷ�Ϻţ�</td>"+
				"<td><input id='_gbfeepage_receipt_inputre'></input></td>"+
				"</tr>"+
				"<tr id='_gbfeepage_factamount' style='display: none'>"+
				"<td>ʵ�գ�</td>"+
				"<td><input id='_gbfeepage_factamount_input'></input></td>"+
				"<td>���㣺</td>"+
				"<td><input id='_gbfeepage_factamount_input1'></input></td>"+
				"<td colspan='2'>&nbsp;</td>"+
				"</tr>"+
				"</table>" ;
		$("_gbfeepage").innerHTML = html;
	}
	function pageshow(){
		initpage();
		$$("tr[id^=_gbfeepage]").each(function (item){
			item.style.display = "";
		});
	}
	function setdefaultdataelement(defaultdataelement){
		defaultdataelement = defaultdataelement;
	}
	function dataset(datasorce){
		var input = "";
		var name = "vo.list";
		var value = "";
		var innerhtml = "";
		if(!(datasorce instanceof Array)){
			alert("data error");
			return ;
		}
		for(var i=0;i<datasorce.length;i++){
			for(var j=0;j<defaultdataelement.length;j++){
				input = name+"["+i+"]."+defaultdataelement[j];
				value = datasorce[i][defaultdataelement[j]];
				innerhtml = innerhtml +"<input type='hidden' name="+input+" value="+value+">"
			}
		}
		return innerhtml;
	}
	function paymethodevent(){
		eventadd({'id':'_gbfeepage_paymethod_list','eventtype':'change','fun':function(){
			var paymethod = this.value;
			var tr = CodeTOTr[paymethod];
			alert(tr);
		}});
	}
	var CodeTOTr = {'01':'cash','02':'bank','03':'pos','04':'check'};
	function eventadd(eventobj){
		$(eventobj['id']).addEvent(eventobj['eventtype'],eventobj['fun']);
	}
	return {'create':function() {
		return function() {
			//�հ��ڲ������ṩ���ⲿ����
			this.pageshow = pageshow;
			this.paymethodevent = paymethodevent;
			this.dataset = dataset;
			this.initialize.apply(this, arguments);
		}
	}};
})();
(function(){
	var NObject = _gbcom.feepage.create();
	NObject.prototype = {initialize:function (){
		//���ñհ��ڲ�����
		this.pageshow();
		this.paymethodevent();
	}};
	var NObjectstance = new NObject();
	var data = [{'id':'0123','name':'xy','sex':'��'},{'id':'0124','name':'xy','sex':'��'}];
	//alert(NObjectstance.dataset(data));
})();
function testddd(){
	//DivShowCommentFun.showdiv({'title':'����','url':'','height':'600','width':'800'});
	DVTeProjessBar.show($('gb'));
}
var DVTeProjessBar = (function (){
	 var bardefine = "<div id='dvteprojessbar' style='position:relative; border:1px #CCC solid; height:28px; width:500px; margin:0 auto; padding:1px'>  "+ 
	    			 "<div id='dvteprojessbarwidthid' style='background:#3F9; height:28px; width:0%'></div>"+
	    			 "<strong style='position:absolute; width:500px; top:7px; text-align:center; overflow:hidden'>���ڼ���....</strong>"+
	 				 "</div> ";
     function show(content,options){
         if(options){
			if(options['width'])
				$('dvteprojessbar').style.width=options['width']+'px';
			if(options['height'])
				$('dvteprojessbar').style.height=options['height']+'px';
         }
		 if(!content)
			return "";
		 content.innerHTML = bardefine;
		// document.write(bardefine);
     }
	 function projess(length){
		$('dvteprojessbarwidthid').style.width=length+'%';
     }
     return {'show':show,'projess':projess};
})();
</script>
</html>
