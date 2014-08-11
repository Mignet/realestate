/*********************************************************************************
*文  件  名  称: user-weight.js
*功  能  描  述: 用户权重
*Copyright (c): 深圳道行天下软件技术有限公司
*创    建    人: Mignet
*创  建  日  期： 2014-03-01
*修  改  历  史：
*文  件  版  本： 1.0
**********************************************************************************/

/**********************************************************************************
*函数名称: JQuery DOM Ready(Shortcut)
*功能说明: 页面初始化
*函数作者: Mignet
*创建日期: 2014-03-01
*修改历史: 
***********************************************************************************/
		var genders = [
		    {gender:'00',name:'男'},
		    {gender:'01',name:'女'}
		];
		function genderFormatter(value){
			for(var i=0; i<genders.length; i++){
				if (genders[i].gender == value) return genders[i].name;
			}
			return value;
		}
		function pagerFilter(data){
			if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
				data = {
					total: data.length,
					rows: data
				}
			}
			var dg = $(this);
			var opts = dg.datagrid('options');
			var pager = dg.datagrid('getPager');
			pager.pagination({
				onSelectPage:function(pageNum, pageSize){
					opts.pageNumber = pageNum;
					opts.pageSize = pageSize;
					pager.pagination('refresh',{
						pageNumber:pageNum,
						pageSize:pageSize
					});
					dg.datagrid('loadData',data);
				}
			});
			if (!data.originalRows){
				data.originalRows = (data.rows);
			}
			var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
			var end = start + parseInt(opts.pageSize);
			data.rows = (data.originalRows.slice(start, end));
			return data;
		}
		var startFlag = false;
		$(function(){
			var lastIndex;
			$('#tt').datagrid({
				title:"编辑用户权重",
				loadFilter:pagerFilter,
				toolbar:[{
					text:'保存',
					iconCls:'icon-save',
					handler:function(){
						$('#tt').datagrid('endEdit', lastIndex);
						startFlag = false;
						var rows = $('#tt').datagrid('getChanges');
						 $.ajax({
						   		dataType:'json',
						   		url:ctx+"/sysmanage/user!saveWeights.action?time="+getDateStr(),
						   		type : 'post',
						   		//表单的序列化操作
						   		data:{"datas":$.toJSON(rows)},
						   		success:function(data){
								 	if(data){
								 		top.$.messager.alert('保存成功提示',"保存成功",'info',function(){
								 			$('#tt').datagrid('acceptChanges');
										});	
								 	}else {
										top.$.messager.alert('保存失败提示',"保存失败",'error');
									}
						   		},error:function(data){
						   			top.$.messager.alert('保存失败提示',"保存失败",'error');
						   		}
						   	});  
					}
				},'-',{
					text:'撤消',
					iconCls:'icon-undo',
					handler:function(){
						$('#tt').datagrid('rejectChanges');
					}
				},'-',{
					text:'加入测试列表',
					iconCls:'icon-accordion_down',
					handler:function(){
						$('#tt').datagrid('endEdit', lastIndex);
						startFlag = false;
						var selectedrow = $('#tt').datagrid('getSelected');
						if (selectedrow) {
							var index = $('#tt').datagrid('getRowIndex', selectedrow);
							$('#tt').datagrid('deleteRow',index);
							//alert(index);
							//$('#tt').datagrid('selectRow', 0);
							$('#tt1').datagrid('appendRow',{  
								user_id:selectedrow.user_id,
								account:selectedrow.account,
								user_name:selectedrow.user_name,
								gender:selectedrow.gender,
								weight:selectedrow.weight,
								status:selectedrow.status
				            });
						}
					}
				}],
				rownumbers:true,
				singleSelect:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:10,
				striped:true,
				columns:[[
				          	//{field:'ck',checkbox:true},
							{field:'user_id', title:'用户ID', width:100},
							{field:'account', title:'用户账户',width:100},
							{field:'user_name', title:'用户名称',width:100},
							{field:'gender', title:'性别', width:60, formatter:genderFormatter,editor:{
								type:'combobox',
								options:{
									valueField:'gender',
									textField:'name',
									data:genders,
									required:false
								}
							}},
							{field:'weight', title:'权重',width:120,align:'center',editor:{type:'slider',
								options:{
									showTip: false,
									rule:[0,'|',25,'|',50,'|',75,'|',100],
									tipFormatter: function(value){
										return value;
									}
								}
							}},
							{field:'status',title:'状态',width:50,align:'center',editor:{type:'checkbox',options:{on:'L',off:''}}}
				]],
				iconCls:'icon-edit',
				idField:'user_id',
				width:screen.availWidth/2,
//				url:ctx+'/sysmanage/user!getUserList.action',
				url:ctx+'/json/users.json',
				onBeforeLoad:function(){
					$(this).datagrid('rejectChanges');
				},
				onClickRow:function(rowIndex){
					//如果最后编辑的行不是当前行，结束它，开始当前行的编辑
					//如果是当前行，结束编辑
					if (lastIndex != rowIndex){
						$('#tt').datagrid('endEdit', lastIndex);
						$('#tt').datagrid('beginEdit', rowIndex);
						//如果标志未开始，开始它
						if(!startFlag){
							startFlag = true;
						}
					}else{
						if(startFlag){
							$('#tt').datagrid('endEdit', rowIndex);
						}else{
							$('#tt').datagrid('beginEdit', rowIndex);
						}
						startFlag = !startFlag;
					}
					lastIndex = rowIndex;
				}
			});
			$('#tt1').datagrid({
				title:"测试列表",
				iconCls:'icon-edit',
		        idField:'user_id',
//		        url:ctx+'/json/users.json',
				loadFilter:pagerFilter,
				rownumbers:true,
				width:screen.availWidth/2,
				singleSelect:true,
				autoRowHeight:true,
				pagination:true,
				pageSize:10,
				striped:true,
				columns:[[
				          //{field:'ck',checkbox:true},
				          {field:'user_id', title:'用户ID', width:100},
				          {field:'account', title:'用户账户',width:100},
				          {field:'user_name', title:'用户名称',width:100},
				          {field:'gender', title:'性别', width:60, formatter:genderFormatter},
				          {field:'weight', title:'权重',width:60,align:'right'},
				          {field:'status',title:'状态',width:60,align:'center'}
				          ]]
				          
			});
			var pager = $('#tt1').datagrid('getPager');	// get the pager of datagrid
			pager.pagination({
				buttons:[{
					iconCls:'icon-accordion_up',
					handler:function(){
						var selectedrow = $('#tt1').datagrid('getSelected');
						if (selectedrow) {
							var index = $('#tt1').datagrid('getRowIndex', selectedrow);
							//alert(index);
							//$('#tt1').datagrid('selectRow', 0);
							$('#tt1').datagrid('deleteRow',index);
							$('#tt').datagrid('appendRow',{
								user_id:selectedrow.user_id,
								account:selectedrow.account,
								user_name:selectedrow.user_name,
								gender:selectedrow.gender,
								weight:selectedrow.weight,
								status:selectedrow.status
				            });
						}
					}
				},{
					iconCls:'icon-right2',
					handler:function(){
						init();
					}
				}]
			});		
		});//初始化结束
//////////////////////////////////////////////////////////////////
		Raphael.fn.connection = function (obj1, obj2, line, bg) {
		    if (obj1.line && obj1.from && obj1.to) {
		        line = obj1;
		        obj1 = line.from;
		        obj2 = line.to;
		    }
		    var bb1 = obj1.getBBox(),
		        bb2 = obj2.getBBox(),
		        p = [{x: bb1.x + bb1.width / 2, y: bb1.y - 1},
		        {x: bb1.x + bb1.width / 2, y: bb1.y + bb1.height + 1},
		        {x: bb1.x - 1, y: bb1.y + bb1.height / 2},
		        {x: bb1.x + bb1.width + 1, y: bb1.y + bb1.height / 2},
		        {x: bb2.x + bb2.width / 2, y: bb2.y - 1},
		        {x: bb2.x + bb2.width / 2, y: bb2.y + bb2.height + 1},
		        {x: bb2.x - 1, y: bb2.y + bb2.height / 2},
		        {x: bb2.x + bb2.width + 1, y: bb2.y + bb2.height / 2}],
		        d = {}, dis = [];
		    for (var i = 0; i < 4; i++) {
		        for (var j = 4; j < 8; j++) {
		            var dx = Math.abs(p[i].x - p[j].x),
		                dy = Math.abs(p[i].y - p[j].y);
		            if ((i == j - 4) || (((i != 3 && j != 6) || p[i].x < p[j].x) && ((i != 2 && j != 7) || p[i].x > p[j].x) && ((i != 0 && j != 5) || p[i].y > p[j].y) && ((i != 1 && j != 4) || p[i].y < p[j].y))) {
		                dis.push(dx + dy);
		                d[dis[dis.length - 1]] = [i, j];
		            }
		        }
		    }
		    if (dis.length == 0) {
		        var res = [0, 4];
		    } else {
		        res = d[Math.min.apply(Math, dis)];
		    }
		    var x1 = p[res[0]].x,
		        y1 = p[res[0]].y,
		        x4 = p[res[1]].x,
		        y4 = p[res[1]].y;
		    dx = Math.max(Math.abs(x1 - x4) / 2, 10);
		    dy = Math.max(Math.abs(y1 - y4) / 2, 10);
		    var x2 = [x1, x1, x1 - dx, x1 + dx][res[0]].toFixed(3),
		        y2 = [y1 - dy, y1 + dy, y1, y1][res[0]].toFixed(3),
		        x3 = [0, 0, 0, 0, x4, x4, x4 - dx, x4 + dx][res[1]].toFixed(3),
		        y3 = [0, 0, 0, 0, y1 + dy, y1 - dy, y4, y4][res[1]].toFixed(3);
		    var path = ["M", x1.toFixed(3), y1.toFixed(3), "C", x2, y2, x3, y3, x4.toFixed(3), y4.toFixed(3)].join(",");
		    if (line && line.line) {
		        line.bg && line.bg.attr({path: path});
		        line.line.attr({path: path});
		    } else {
		        var color = typeof line == "string" ? line : "#000";
		        return {
		            bg: bg && bg.split && this.path(path).attr({stroke: bg.split("|")[0], fill: "none", "stroke-width": bg.split("|")[1] || 3}),
		            line: this.path(path).attr({stroke: color, fill: "none"}),
		            from: obj1,
		            to: obj2
		        };
		    }
		};

		var el;
		//window.onload = function () {
		    var dragger = function () {
		        this.ox = this.type == "rect" ? this.attr("x") : this.attr("cx");
		        this.oy = this.type == "rect" ? this.attr("y") : this.attr("cy");
		        this.animate({"fill-opacity": .2}, 500);
		    };
		    move = function (dx, dy) {
		        var att = this.type == "rect" ? {x: this.ox + dx, y: this.oy + dy} : {cx: this.ox + dx, cy: this.oy + dy};
		        this.attr(att);
		        for (var i = connections.length; i--;) {
		            r.connection(connections[i]);
		        }
		        r.safari();
		    };
		    up = function () {
		        this.animate({"fill-opacity": 0}, 500);
		    }
		//};
		var connections = [];
		var datas = [];
		var r;
		var offset = 100;
		var showTxts = [];
//////////////////////////////////////////////////////////////////
		function init(){
			$('#holder').empty();
			var shapes = []; 
			connections = [];
			datas = [];
			showTxts = [];
			nums = [];ranges=[];
			sum = 0;weights = [];
			initFlag = false;
			 r = Raphael("holder",screen.availWidth/2, screen.availHeight);
			 datas = $('#tt1').datagrid('getRows');
			 if(datas.length==0){
				 alert("请先选择人员！");return;
			 }
			 //排除0用户
			 for(var i=0;i<datas.length;i++){
				 if(datas[i].weight <=0){
					 datas.splice(i,1);
				 }
			 }
			 //计算中间位置
			 var n = datas.length;
			 len = n;//用于计算自动分文
			 var mid = (n/2)*50; 
			 offset = 220 - mid;
			 offset = offset>0?offset:0;
			 for(var i=0;i<n;i++){
				 var o = r.ellipse(450, 50+offset+50*i, 60, 20);
				 shapes.push(o);
				 weights.push(datas[i].weight);
				 var t = r.text(580, 50+offset+50*i,'').attr({fill: "#000", "font-size": 16});
				 showTxts.push(t);
			 };
			for(var i=0;i<len;i++){
				sum += weights[i]|0;
				nums.push(0);
			};
			for(var i=0;i<len;i++){
				ranges[i] = ((weights[i]|0)/sum).toFixed(2);
				r.text(450, 50+offset+50*i,datas[i].user_name+"["+(ranges[i])+"]").attr({fill: "#000", "font-size": 16});
			}
			 var color = '#bf200f';
			    var send = r.ellipse(190, mid+20+offset, 30, 20).attr({fill: '#0f0', stroke: color, "fill-opacity": 1, "stroke-width": 2});
			    var sendtxt = r.text(190, mid+20+offset, "发送").attr({fill: "#000", "font-size": 16});
			    sendtxt.click(function(){
			    	//点击效果
			    	send.c = send.c || send.attr("fill");
			    	send.stop().animate({fill: "#0f0"}, 500);
			    	send.animate({
			    		//r: 5 * (Math.random() * 5 + 1),
			    		//stroke: Raphael.hsb(Math.random(), 1, 1),
			    		fill: '#ff0' 
			    	}, 200,dispatcher());
			    	
			    	//caculate();
			    	
			    });
			    color = '#ff200f';
			    var box = r.rect(250, mid+offset, 80, 40, 10).attr({fill: color, stroke: color, "fill-opacity": 0, "stroke-width": 2});
			    var boxtxt = r.text(250+40, mid+20+offset, "分文算法").attr({fill: "#000", "font-size": 16});
			    for (var i = 0, ii = shapes.length; i < ii; i++) {
			        var color = Raphael.getColor();
			        shapes[i].attr({fill: color, stroke: color, "fill-opacity": 0, "stroke-width": 2, cursor: "move"});
			        shapes[i].drag(move, dragger, up);
			    }
			    connections.push(r.connection(send, box, "#000", "#ff0"));
			    for (var i = 0; i < shapes.length; i++) {
			    	connections.push(r.connection(box, shapes[i], "#000", "#ff0"));
			    }
		}
var weights = [];
var len = 0;//用于计算自动分文
var sum = 0;//权重之和
var nums = [];//分配的值
var ranges = [];//概率的值
/**********************************************************************************
*函数名称: 竞价权重<br>
*功能说明: 
P[0] = 0.2, P[1] = 0.3, P[2] = 0.5
F[0] = 0.0, F[1] = 0.2, F[2] = 0.5, F[3] = 1.0
F称为累积分布函数，定义是F[k] = P[0] + P[1] + ... + P[k]
生成一个随机数p，如果F[k] <= p < F[k+1], 输出第k项
*函数作者: Mignet
*创建日期: 2014-3-30
*修改历史: 
***********************************************************************************/
function caculate(){
	//非均等概率下的随机数
	var P =new Array();
	var F =new Array();
	//alert(weights.length);
	F[0] = 0.0;
	for(i=0;i<len;i++)
	{
		P[i]=parseFloat(ranges[i]);
		F[i+1] = 0;
		for(var ii=0;ii<=i;ii++){
			F[i+1] += P[ii];
		}
		//alert(a_area_start[i]);
	}
	result=parseFloat(Math.random());
	//alert(result);
	for(i=0;i<len;i++)
	{
		if (result>=F[i] && result<F[i+1] )
		{
			//alert(a_Index+":"+datas[i].user_name);
			showTxts[i].attr('text',++nums[i]);
			//r.text(580, 50+offset+50*i,datas[i].user_name+":"+datas[i].weight).attr({fill: "#000", "font-size": 16});
		}
	}
}
var cur = 0;
var initFlag = false;
/**********************************************************************************
*函数名称: 发牌算法<br>
*功能说明: 概率数轮询
*	P[0] = 0.5, P[1] = 0.3, P[2] = 0.2
*	Math.round(parseFloat(ranges[P[cur].index])*K)
*	5,3,2
*	1->5
*函数作者: Mignet
*创建日期: 2014-3-30
*修改历史: 
***********************************************************************************/
var P = [];var K = 10;var sendFlag = false;
function dispatcher(){
	sendFlag = false;
	if(!initFlag){
		for(i=0;i<len;i++)
		{
			P[i]={'num':parseFloat(ranges[i]),'index':i};
		}
		P = BubbleSort(P);
		//scale factor
		K = 2/(P[len-1].num);
		initFlag = true;
	}
	var miss = 0;
	while(!sendFlag){
		//全满匣
		if(miss>=len){
			clearNums();
			cur = 0;
		}
		//未满匣
		if(nums[P[cur].index]<Math.round(parseFloat(ranges[P[cur].index])*K)){
			showTxts[P[cur].index].attr('text',++nums[P[cur].index]);
			sendFlag = true;
		}else{
			miss++;
		}
		cur++;
		if(cur>=len){
			cur = 0;
		};
	}
}
function clearNums(){
	for(i=0;i<len;i++)
	{
		nums[i] = 0;
		showTxts[i].attr('text','');
	}
}
/**********************************************************************************
*函数名称: BubbleSort
*@param array
*@return sorted_array
*功能说明: Bubble Sort By Property
*函数作者: dxtx
*创建日期: 2014-3-30
*修改历史: 
***********************************************************************************/
function BubbleSort(array) {
　　length = array.length;
　　for(i=0; i<=length-2; i++) {
　　　　for(j=length-1; j>=1; j--) {
　　　　　　if(array[j].num > array[j-1].num) {
　　　　　　　　temp = array[j];
　　　　　　　　array[j] = array[j-1];
　　　　　　　　array[j-1] = temp;
　　　　　　}
　　　　}
　　}
	return array;
}
