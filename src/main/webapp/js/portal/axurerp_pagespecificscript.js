﻿
var PageName = 'portal';
var PageId = '2079fdefd21e433a8f6d6f17abf5ce01'
var PageUrl = 'portal.jsp'
document.title = 'portal';
var PageNotes = 
{
"pageName":"portal",
"showNotesNames":"False"}
var $OnLoadVariable = '';

var $CSUM;

var hasQuery = false;
var query = window.location.hash.substring(1);
if (query.length > 0) hasQuery = true;
var vars = query.split("&");
for (var i = 0; i < vars.length; i++) {
    var pair = vars[i].split("=");
    if (pair[0].length > 0) eval("$" + pair[0] + " = decodeURIComponent(pair[1]);");
} 

if (hasQuery && $CSUM != 1) {
alert('Prototype Warning: The variable values were too long to pass to this page.\nIf you are using IE, using Firefox will support more data.');
}

function GetQuerystring() {
    return '#OnLoadVariable=' + encodeURIComponent($OnLoadVariable) + '&CSUM=1';
}

function PopulateVariables(value) {
    var d = new Date();
  value = value.replace(/\[\[OnLoadVariable\]\]/g, $OnLoadVariable);
  value = value.replace(/\[\[PageName\]\]/g, PageName);
  value = value.replace(/\[\[GenDay\]\]/g, '4');
  value = value.replace(/\[\[GenMonth\]\]/g, '8');
  value = value.replace(/\[\[GenMonthName\]\]/g, '八月');
  value = value.replace(/\[\[GenDayOfWeek\]\]/g, '星期一');
  value = value.replace(/\[\[GenYear\]\]/g, '2014');
  value = value.replace(/\[\[Day\]\]/g, d.getDate());
  value = value.replace(/\[\[Month\]\]/g, d.getMonth() + 1);
  value = value.replace(/\[\[MonthName\]\]/g, GetMonthString(d.getMonth()));
  value = value.replace(/\[\[DayOfWeek\]\]/g, GetDayString(d.getDay()));
  value = value.replace(/\[\[Year\]\]/g, d.getFullYear());
  return value;
}

function OnLoad(e) {

}

var u122 = document.getElementById('u122');
gv_vAlignTable['u122'] = 'top';
var u32 = document.getElementById('u32');

var u45 = document.getElementById('u45');

var u79 = document.getElementById('u79');

var u4 = document.getElementById('u4');
gv_vAlignTable['u4'] = 'center';
var u17 = document.getElementById('u17');

var u135 = document.getElementById('u135');
gv_vAlignTable['u135'] = 'top';
var u42 = document.getElementById('u42');

var u102 = document.getElementById('u102');
gv_vAlignTable['u102'] = 'top';
var u55 = document.getElementById('u55');
gv_vAlignTable['u55'] = 'top';
var u14 = document.getElementById('u14');
gv_vAlignTable['u14'] = 'center';
var u5 = document.getElementById('u5');

var u105 = document.getElementById('u105');
gv_vAlignTable['u105'] = 'top';

var u138 = document.getElementById('u138');
gv_vAlignTable['u138'] = 'top';
var u52 = document.getElementById('u52');
gv_vAlignTable['u52'] = 'top';
var u65 = document.getElementById('u65');
gv_vAlignTable['u65'] = 'center';
var u110 = document.getElementById('u110');
gv_vAlignTable['u110'] = 'top';
var u2 = document.getElementById('u2');

var u24 = document.getElementById('u24');
gv_vAlignTable['u24'] = 'center';
var u58 = document.getElementById('u58');

var u108 = document.getElementById('u108');
gv_vAlignTable['u108'] = 'top';
var u37 = document.getElementById('u37');

var u30 = document.getElementById('u30');
gv_vAlignTable['u30'] = 'center';
var u62 = document.getElementById('u62');

var u21 = document.getElementById('u21');

var u75 = document.getElementById('u75');

var u133 = document.getElementById('u133');
gv_vAlignTable['u133'] = 'top';
var u88 = document.getElementById('u88');
gv_vAlignTable['u88'] = 'top';
var u7 = document.getElementById('u7');

var u47 = document.getElementById('u47');

var u72 = document.getElementById('u72');

var u112 = document.getElementById('u112');
gv_vAlignTable['u112'] = 'top';
var u31 = document.getElementById('u31');
gv_vAlignTable['u31'] = 'top';
var u146 = document.getElementById('u146');
gv_vAlignTable['u146'] = 'top';
var u44 = document.getElementById('u44');
gv_vAlignTable['u44'] = 'center';
var u78 = document.getElementById('u78');
gv_vAlignTable['u78'] = 'center';
var u57 = document.getElementById('u57');

var u23 = document.getElementById('u23');

var u8 = document.getElementById('u8');
gv_vAlignTable['u8'] = 'center';
var u16 = document.getElementById('u16');

var u125 = document.getElementById('u125');
gv_vAlignTable['u125'] = 'top';
var u6 = document.getElementById('u6');
gv_vAlignTable['u6'] = 'center';
var u41 = document.getElementById('u41');

var u119 = document.getElementById('u119');
gv_vAlignTable['u119'] = 'top';
var u54 = document.getElementById('u54');
gv_vAlignTable['u54'] = 'center';
var u131 = document.getElementById('u131');
gv_vAlignTable['u131'] = 'top';
var u26 = document.getElementById('u26');
gv_vAlignTable['u26'] = 'center';
var u128 = document.getElementById('u128');
gv_vAlignTable['u128'] = 'top';
var u94 = document.getElementById('u94');
gv_vAlignTable['u94'] = 'top';
var u99 = document.getElementById('u99');
gv_vAlignTable['u99'] = 'top';
var u10 = document.getElementById('u10');
gv_vAlignTable['u10'] = 'center';
var u51 = document.getElementById('u51');

u51.style.cursor = 'pointer';
if (bIE) u51.attachEvent("onclick", Clicku51);
else u51.addEventListener("click", Clicku51, true);
function Clicku51(e)
{
windowEvent = e;


if (true) {

	NewWindow("#" + GetQuerystring(), "", "directories=0, height=600, location=0, menubar=0, resizable=1, scrollbars=1, status=0, toolbar=0, width=600", true, 600, 600);

}

}

var u77 = document.getElementById('u77');

var u144 = document.getElementById('u144');
gv_vAlignTable['u144'] = 'top';
var u82 = document.getElementById('u82');
gv_vAlignTable['u82'] = 'top';
var u36 = document.getElementById('u36');

var u101 = document.getElementById('u101');
gv_vAlignTable['u101'] = 'top';
var u95 = document.getElementById('u95');
gv_vAlignTable['u95'] = 'top';
var u61 = document.getElementById('u61');
gv_vAlignTable['u61'] = 'center';
var u143 = document.getElementById('u143');
gv_vAlignTable['u143'] = 'top';
var u74 = document.getElementById('u74');
gv_vAlignTable['u74'] = 'center';
var u114 = document.getElementById('u114');
gv_vAlignTable['u114'] = 'top';
var u20 = document.getElementById('u20');
gv_vAlignTable['u20'] = 'center';
var u33 = document.getElementById('u33');

var u104 = document.getElementById('u104');

var u92 = document.getElementById('u92');
gv_vAlignTable['u92'] = 'top';
var u46 = document.getElementById('u46');
gv_vAlignTable['u46'] = 'center';
var u34 = document.getElementById('u34');

var u71 = document.getElementById('u71');

var u98 = document.getElementById('u98');
gv_vAlignTable['u98'] = 'top';
var u127 = document.getElementById('u127');
gv_vAlignTable['u127'] = 'top';
var u43 = document.getElementById('u43');

var u56 = document.getElementById('u56');

var u142 = document.getElementById('u142');
gv_vAlignTable['u142'] = 'top';
var u106 = document.getElementById('u106');
gv_vAlignTable['u106'] = 'top';
var u53 = document.getElementById('u53');

var u40 = document.getElementById('u40');

var u139 = document.getElementById('u139');
gv_vAlignTable['u139'] = 'top';
var u87 = document.getElementById('u87');
gv_vAlignTable['u87'] = 'top';
var u38 = document.getElementById('u38');

var u109 = document.getElementById('u109');
gv_vAlignTable['u109'] = 'top';
var u121 = document.getElementById('u121');
gv_vAlignTable['u121'] = 'top';
var u67 = document.getElementById('u67');
gv_vAlignTable['u67'] = 'top';
var u116 = document.getElementById('u116');
gv_vAlignTable['u116'] = 'top';
var u9 = document.getElementById('u9');

var u84 = document.getElementById('u84');
gv_vAlignTable['u84'] = 'center';
var u50 = document.getElementById('u50');
gv_vAlignTable['u50'] = 'center';
var u97 = document.getElementById('u97');
gv_vAlignTable['u97'] = 'top';
var u63 = document.getElementById('u63');
gv_vAlignTable['u63'] = 'center';
var u124 = document.getElementById('u124');
gv_vAlignTable['u124'] = 'top';
var u130 = document.getElementById('u130');
gv_vAlignTable['u130'] = 'top';
var u76 = document.getElementById('u76');
gv_vAlignTable['u76'] = 'center';
var u134 = document.getElementById('u134');
gv_vAlignTable['u134'] = 'top';
var u81 = document.getElementById('u81');

u81.style.cursor = 'pointer';
if (bIE) u81.attachEvent("onclick", Clicku81);
else u81.addEventListener("click", Clicku81, true);
function Clicku81(e)
{
windowEvent = e;


if (true) {

	NewWindow("#" + GetQuerystring(), "", "directories=0, height=600, location=0, menubar=0, resizable=1, scrollbars=1, status=0, toolbar=0, width=600", true, 600, 600);

}

}

var u118 = document.getElementById('u118');
gv_vAlignTable['u118'] = 'top';
var u3 = document.getElementById('u3');

var u60 = document.getElementById('u60');

var u64 = document.getElementById('u64');

var u73 = document.getElementById('u73');

var u113 = document.getElementById('u113');
gv_vAlignTable['u113'] = 'top';
var u91 = document.getElementById('u91');
gv_vAlignTable['u91'] = 'top';
var u19 = document.getElementById('u19');

var u70 = document.getElementById('u70');
gv_vAlignTable['u70'] = 'top';
var u140 = document.getElementById('u140');
gv_vAlignTable['u140'] = 'top';
var u117 = document.getElementById('u117');
gv_vAlignTable['u117'] = 'top';
var u141 = document.getElementById('u141');
gv_vAlignTable['u141'] = 'top';
var u89 = document.getElementById('u89');
gv_vAlignTable['u89'] = 'top';
var u68 = document.getElementById('u68');

var u136 = document.getElementById('u136');
gv_vAlignTable['u136'] = 'top';
var u29 = document.getElementById('u29');

var u132 = document.getElementById('u132');
gv_vAlignTable['u132'] = 'top';
var u103 = document.getElementById('u103');
gv_vAlignTable['u103'] = 'top';
var u129 = document.getElementById('u129');
gv_vAlignTable['u129'] = 'top';
var u86 = document.getElementById('u86');
gv_vAlignTable['u86'] = 'top';
var u11 = document.getElementById('u11');

u11.style.cursor = 'pointer';
if (bIE) u11.attachEvent("onclick", Clicku11);
else u11.addEventListener("click", Clicku11, true);
function Clicku11(e)
{
windowEvent = e;


if (true) {

	NewWindow("#" + GetQuerystring(), "", "directories=0, height=600, location=0, menubar=0, resizable=1, scrollbars=1, status=0, toolbar=0, width=600", true, 600, 600);

}

}

var u39 = document.getElementById('u39');

var u111 = document.getElementById('u111');
gv_vAlignTable['u111'] = 'top';
var u66 = document.getElementById('u66');

u66.style.cursor = 'pointer';
if (bIE) u66.attachEvent("onclick", Clicku66);
else u66.addEventListener("click", Clicku66, true);
function Clicku66(e)
{
windowEvent = e;


if (true) {

	NewWindow("#" + GetQuerystring(), "", "directories=0, height=600, location=0, menubar=0, resizable=1, scrollbars=1, status=0, toolbar=0, width=600", true, 600, 600);

}

}

var u0 = document.getElementById('u0');

var u145 = document.getElementById('u145');
gv_vAlignTable['u145'] = 'top';
var u25 = document.getElementById('u25');

var u83 = document.getElementById('u83');

var u96 = document.getElementById('u96');
gv_vAlignTable['u96'] = 'top';
var u15 = document.getElementById('u15');
gv_vAlignTable['u15'] = 'top';
var u49 = document.getElementById('u49');

var u120 = document.getElementById('u120');
gv_vAlignTable['u120'] = 'top';
var u80 = document.getElementById('u80');
gv_vAlignTable['u80'] = 'center';
var u1 = document.getElementById('u1');
gv_vAlignTable['u1'] = 'center';
var u85 = document.getElementById('u85');
gv_vAlignTable['u85'] = 'top';
var u93 = document.getElementById('u93');
gv_vAlignTable['u93'] = 'top';
var u12 = document.getElementById('u12');
gv_vAlignTable['u12'] = 'top';
var u115 = document.getElementById('u115');
gv_vAlignTable['u115'] = 'top';
var u59 = document.getElementById('u59');
gv_vAlignTable['u59'] = 'center';
var u137 = document.getElementById('u137');
gv_vAlignTable['u137'] = 'top';
var u90 = document.getElementById('u90');
gv_vAlignTable['u90'] = 'top';
var u18 = document.getElementById('u18');

var u100 = document.getElementById('u100');
gv_vAlignTable['u100'] = 'top';
var u123 = document.getElementById('u123');
gv_vAlignTable['u123'] = 'top';
var u48 = document.getElementById('u48');
gv_vAlignTable['u48'] = 'center';
var u22 = document.getElementById('u22');
gv_vAlignTable['u22'] = 'center';
var u13 = document.getElementById('u13');

var u107 = document.getElementById('u107');
gv_vAlignTable['u107'] = 'top';
var u35 = document.getElementById('u35');

var u69 = document.getElementById('u69');
gv_vAlignTable['u69'] = 'center';
var u28 = document.getElementById('u28');
gv_vAlignTable['u28'] = 'top';
var u126 = document.getElementById('u126');
gv_vAlignTable['u126'] = 'top';
if (window.OnLoad) OnLoad();
