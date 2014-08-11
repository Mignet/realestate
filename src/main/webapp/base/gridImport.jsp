<script type="text/javascript" src="${ctx }/js/common/grid/gt_msg_cn.js"></script>
<script type="text/javascript" src="${ctx }/js/common/grid/gt_grid_all.js"></script>
<link rel="stylesheet" rev="stylesheet" href="${ctx }/js/common/grid/gt_grid.css" type="text/css" media="all" title="001" />
<link rel="alternate stylesheet" type="text/css" href="${ctx }/js/common/grid/gt_gridhappy.css" title="002" />
<link rel="alternate stylesheet" type="text/css" href="${ctx }/js/common/grid/gray_grid.css" title="003" />
<script type="text/javascript" src="${ctx}/js/common/styleswitcherpage.js"></script>
<script type="text/javascript">
function getGirdQueryParams(queryInput){
	var inputObject = {};
	$$('#queryInput input[type=text]').each(function(item,index){
		inputObject[item.getProperty('name')] = item.get('value').trim();
	})
	$$('#queryInput input[type=hidden]').each(function(item,index){
		inputObject[item.getProperty('name')] = item.get('value').trim();
	})
	$$('#queryInput select').each(function(item,index){
		inputObject[item.getProperty('name')] = item.get('value');
	})
	return inputObject;
}
//query input
function queryGird(mygrid,queryInput){
	var input = getGirdQueryParams(queryInput);
	mygrid.parameters = input; 
	mygrid.load();
	mygrid.render();
}

//all query input is null
function checkAllNull(queryInput) {
    var allsize=0;
	var nullsize=0;
	$$('#queryInput input[type=text]').each(function(item,index){
		allsize++;
		if(item.get('value').trim().length==0)nullsize ++;
	})
	if(allsize==nullsize)return true;
	return false;
	
}

/**
 * get radio value
 */
function getGirdRadioSelectValue(radioname){
	var value = '';
	var valueobj = document.getElementsByName(radioname);
	for(var i = 0; i < valueobj.length; i++){
		if(valueobj[i].checked){
			value = valueobj[i].value;
		}
	}
	return value;
}
</script>
