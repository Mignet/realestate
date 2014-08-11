//JS Current Date: YYYY-MM-DD HH:MI:SS
function getDateStr(){
	var now= new Date();   
	var year=now.getFullYear();
	var month=now.getMonth()+1;   
	var day=now.getDate();   
	var hour=now.getHours();   
	var minute=now.getMinutes();   
	var second=now.getSeconds();   
	var nowdate=year+"-"+month+"-"+day+"_"+hour+":"+minute+":"+second;
	return nowdate;
}
//get current Date time
function getCurTime(){
	var now= new Date();   
	var year=now.getFullYear();
	var month=now.getMonth()+1;   
	var day=now.getDate();   
	var hour=now.getHours();   
	var minute=now.getMinutes();   
	var second=now.getSeconds();   
	var nowdate=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
	return nowdate;
}
//JS Current Date: YYYY-MM-DD
function getCurrentDate(){
	var region = arguments;
	var now= new Date();   
	var year=now.getFullYear();
	var month=now.getMonth()+1;   
	var day=now.getDate();  
	return formatDate(year,month,day,region[0]);
}

function empty(v){
	switch (typeof v){ 
		case 'undefined' : return true; 
		case 'string' : if(trim(v).length == 0 || v == "null") return true; break; 
		case 'object' : 
		if(null === v) return true; 
		if(undefined !== v.length && v.length==0) return true; 
		for(var k in v){return false;} return true; 
		break; 
	} 
   return false; 
}

function subDatePart(date){
	if(!date)
		return getCurrentDate();
	if(date.length >= 10){
		return date.substring(0,10);
	}
}

function checkDate(date){
	var valdate;
	if(!date)
		valdate = new Date();
	else
		valdate = date;
	return valdate;
}

function formatDateII(date){
	var region = arguments;
	var valdate;
	if(!date)
		valdate = new Date();
	else
		valdate = date;
	var year=valdate.getFullYear();
	var month=valdate.getMonth()+1;   
	var day=valdate.getDate();
	if(empty(region[1]))
		return formatDate(year,month,day);
	else
		return formatDate(year,month,day,region[1]);
}

function formatDate(year,month,date){
	var region = arguments;
	if(year < 100){
		year = 1900 + year;
	}
	if(month < 10){
		month = "0" + month;
	}
	if(date < 10){
		date = "0" + date;
	}
	var retnmsg;
	//return (year + "-" + month + "-" + date);
	if(!empty(region[3]) && region[3] == 'China')
	 retnmsg = year + "\u5e74" + month + "\u6708" + date +"\u65e5";
	else
		retnmsg = year + "-" + month + "-" + date;
	return retnmsg;
}

function trim(str)
{
	for(var   i   =   0   ;   i<str.length   &&   str.charAt(i)=="   "   ;   i++   )   ;
	for(var   j   =str.length;   j>0   &&   str.charAt(j-1)=="   "   ;   j--)   ;
	if(i>j)   return   "";  
	return   str.substring(i,j);  
}
//��ȡurl�в�����Ӧ��ֵ
$.getUrlParam = function(name)
  {
	 var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if (r!=null){ 
    	 return unescape(r[2]);
     }
      return null;
}

/**********************************************************************************
*��������: convertDateFromNumToChinese
*����˵��: ת�������а���������Ϊ��������
*��    ����
*��������: Sam
*��������: 2014��5��1�� 
*�޸���ʷ: 
***********************************************************************************/
function convertDateFromNumToChinese(){
	var params = arguments;
	var sdate;
	var date;
	if(empty(params))
	   date = new Date();
	else if(typeof params[0] == 'object'){
		date = params[0];
	}else if(typeof params[0] == 'string'){
		try{
		  date = new Date(Date.parse(params[0].replace(/-/g,'/')));
		}catch(exception){
		  top.$.messager.alert('����', '��������ʽ������', 'error', function(){});
		  return;
		}
	}
	var year=date.getFullYear();
	var month=date.getMonth()+1;   
	var day=date.getDate();  
	if(year < 100){
		year = 1900 + year;
	}
	//�������飬���ÿ�µ���������һ�д�ƽ�꣬�ڶ��д�����
	var day_tabnew = [[0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31,],
	                  [0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, ]];
	var lp = year % 4 == 0 && year % 100 != 0 || year % 400 == 0 ? 1 : 0;//*�ж�yearΪ���껹��ƽ�꣬lp=0Ϊƽ�꣬��0Ϊ����*/
	if (day > day_tabnew[lp, month]){//ȡ����Ӧ���µ������������û�������������Ա�1
		top.$.messager.alert('����', '�������ڲ�����', 'error', function(){});
		return;
	}else{
		if(month < 10){
			month = "0" + month;
		}
		if(day < 10){
			day = "0" + day;
		}
		year = convertNumToChinese(year);
		month = convertNumToChinese(month);
		day = convertNumToChinese(day);
		sdate = year + "\u5e74" + month + "\u6708" + day +"\u65e5";
	}
	return sdate;
}

function convertNumToChinese(num){
	if(typeof num == 'number')
		num = new String(num);
	var first;
	var second;
	if(num.length == 2){
		first = num.substring(0,1);
		first = first.replace('0', '\u96f6');
		first = first.replace('1', '\u5341');
		first = first.replace('2', '\u4e8c\u5341');
		first = first.replace('3', '\u4e09\u5341');
		second = num.substring(1,2);
		second = second.replace('0', '');
		second = second.replace('1', '\u4e00');
		second = second.replace('2', '\u4e8c');
		second = second.replace('3', '\u4e09');
		second = second.replace('4', '\u56db');
		second = second.replace('5', '\u4e94');
		second = second.replace('6', '\u516d');
		second = second.replace('7', '\u4e03');
		second = second.replace('8', '\u516b');
		second = second.replace('9', '\u4e5d');
		num = first+second;
	}else if(num.length == 4){
		num = num.replace('0', '\u96f6');
		num = num.replace('1', '\u4e00');
		num = num.replace('2', '\u4e8c');
		num = num.replace('3', '\u4e09');
		num = num.replace('4', '\u56db');
		num = num.replace('5', '\u4e94');
		num = num.replace('6', '\u516d');
		num = num.replace('7', '\u4e03');
		num = num.replace('8', '\u516b');
		num = num.replace('9', '\u4e5d');
	}
	return num;
} 
