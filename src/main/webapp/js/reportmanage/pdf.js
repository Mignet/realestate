/*$(function(){
	 var url= ctx + '/reportmanage/mortgage-report!houseRightMortgageSelectReport.action';
	 document.frames["#printed"].location=url;
});*/
function init(proc){
	var url = ctx + proc.url+'&time='+new Date();
	window.frames["printed"].location=url;
}

