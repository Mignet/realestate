<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/base/taglibs.jsp"%>
<script type="text/javascript">
var ctx = '${ctx}';
var _rightabsolutePath = "${pageContext.request.contextPath}";
var initDom = function(pageid){
	if($chk(pageid)) {
   var req = new Request.HTML({url:_rightabsolutePath+'/base/commissionfilter.jsp?pageid='+pageid+'&datetimeps='+new Date(),
        method:'get',
        onSuccess: function(html) {
		   var All = $$('input[id$=Btn]');
			All.extend($$('a[id$=Tabs]'));
			All.extend($$('a[id$=Href]'));
			var Length = All.length;
			for(var I = 0; I < Length; I++)	
			{
				All[I].disabled=true;
			}	
	   		//alert("In..JS....");
        	var rights=this.response.text;
  	
        	if(rights==null ||  rights.replace(/\s/g,'')=="ERROR") {
        		//屏蔽      
        		//window.location.href=_rightabsolutePath+'/base/noright.jsp';
        	}else{
			var ob=rights.split(",");
			for(var i=0;i<ob.length;i++)
			{				
				
				var tmpstr=ob[i].replace(/\s/g,'');
				
				if(tmpstr.length>1 && $(tmpstr))
				{
					//$(tmpstr).disabled=false;
					for(var J = 0; J < Length; J++)	
					{
						if (All[J].id==tmpstr) {
							All[J].disabled=false;							
						}
					}
				}
				
			}
        	}
        	
        	try{
        		//权限控制回调函数
        		privilegeControlCallFun(pageid);
        	
        	}catch(e){}
        },
        onFailure: function() {
         	
        }
    }).send();        
}
}
//禁用营业主业务页面
function enabledBusihtmlmain(rijieflag){
	if(rijieflag=='true'){
		var allBtns = $$('a[id=nogotitle]');
		var len = allBtns.length;
		for(var i = 0; i < len; i++){
			allBtns[i].disabled = true;
		}
		try{
			$("moneyDIV").disabled = true;
			$("telltroubleBtn").disabled = true;
		}catch(e){}
		var allBtns1 = $$('a[id=custtousuoBtn]');
		var len1 = allBtns1.length;
		for(var i = 0; i < len1; i++){
			allBtns1[i].disabled = true;
		}
	}
}
//禁用商业客户主业务页面
function enabledBizhtmlmain(rijieflag){
	if(rijieflag=='true'){
		var allBtns = $$('input[id$=Btn]');
		var len = allBtns.length;
		for(var i = 0; i < len; i++){
			allBtns[i].disabled = true;
		}
	}
}
</script>

