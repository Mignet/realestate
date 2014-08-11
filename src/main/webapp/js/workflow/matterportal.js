

var userDataGrid;   
$(function(){
	var objs={					
			url:ctx+"/common/certificate!getMatterDetail.action",
			type:'post',
			success:function(data){
				data=JSON.parse(data)
				if(data)
				{
					var house = [];
					var land = [];
					//海域
					var seas = [];
					//林地
					var lawn = [];
					//草地
					var lawn = [];
					for(var i=0;i<data.length;i++)
					{
						if(data[i].parent_id=="0101")
						{
							land.push(data[i]);
						}
						if(data[i].parent_id=="0102")
						{
							house.push(data[i]);
						}
						if(data[i].parent_id=="0103")
						{
							lawn.push(data[i]);
						}
						if(data[i].parent_id=="0104")
						{
							lawn.push(data[i]);
						}
						if(data[i].parent_id=="0105")
						{
							seas.push(data[i]);
						}
					}
					var landall=0;
					var landhexiang=0;
					var landother=0;
					for(var a=0;a<land.length;a++)
					{
						if(land[a].pro_type=="120001")
						{
							if(landall==0)
							{
								$("#land_all1").append("<li><a href='#' onclick='openTab("+'"'+land[a].name+'"'+")' id='"+land[a].proc_id+"'>"+land[a].name+"</li></a>");
								landall=1;
							}
							else
							{
								$("#land_all2").append("<li><a href='#' onclick='openTab("+'"'+land[a].name+'"'+")' id='"+land[a].proc_id+"'>"+land[a].name+"</li></a>");
								landall=0;
							}
						}
						else if(land[a].pro_type=="120002")
						{
							if(landhexiang==0)
							{
								$("#land_hexiang1").append("<li><a href='#' onclick='openTab("+'"'+land[a].name+'"'+")' id='"+land[a].proc_id+"'>"+land[a].name+"</li></a>");
								landhexiang=1;
							}
							else
							{
								$("#land_hexiang2").append("<li><a href='#' onclick='openTab("+'"'+land[a].name+'"'+")' id='"+land[a].proc_id+"'>"+land[a].name+"</li></a>");
								landhexiang=0;
							}
						}
						else
						{
							if(landother==0)
							{
								$("#land_other1").append("<li><a href='#' onclick='openTab("+'"'+land[a].name+'"'+")' id='"+land[a].proc_id+"'>"+land[a].name+"</li></a>");
								landother=1;
							}
							else
							{
								$("#land_other2").append("<li><a href='#' onclick='openTab("+'"'+land[a].name+'"'+")' id='"+land[a].proc_id+"'>"+land[a].name+"</li></a>");
								landother=0;
							}
						}
					}
					var houseall=0;
					var househexiang=0;
					var houseother=0;
					for(var b=0;b<house.length;b++)
					{
						if(house[b].pro_type=="120001")
						{
							if(houseall==0)
							{
								$("#house_all1").append("<li><a href='#' onclick='openTab("+'"'+house[b].name+'"'+")' id='"+house[b].proc_id+"'>"+house[b].name+"</li></a>");
								houseall=1;
							}
							else
							{
								$("#house_all2").append("<li><a href='#' onclick='openTab("+'"'+house[b].name+'"'+")' id='"+house[b].proc_id+"'>"+house[b].name+"</li></a>");
								houseall=0;
							}
						}
						else if(house[b].pro_type=="120002")
						{
							if(househexiang==0)
							{
								$("#house_hexiang1").append("<li><a href='#' onclick='openTab("+'"'+house[b].name+'"'+")' id='"+house[b].proc_id+"'>"+house[b].name+"</li></a>");
								househexiang=1;
							}
							else
							{
								$("#house_hexiang2").append("<li><a href='#' onclick='openTab("+'"'+house[b].name+'"'+")' id='"+house[b].proc_id+"'>"+house[b].name+"</li></a>");
								househexiang=0;
							}
						}
						else
						{
							if(houseother==0)
							{
								$("#house_other1").append("<li><a href='#' onclick='openTab("+'"'+house[b].name+'"'+")' id='"+house[b].proc_id+"'>"+house[b].name+"</li></a>");
								houseother=1;
							}
							else
							{
								$("#house_other2").append("<li><a href='#' onclick='openTab("+'"'+house[b].name+'"'+")' id='"+house[b].proc_id+"'>"+house[b].name+"</li></a>");
								houseother=0;
							}
						}
					}
					var holtall=0;
					var holthexiang=0;
					var holtother=0;
					for(var c=0;c<holt.length;c++)
					{
						if(holt[c].pro_type=="120001")
						{
							if(holtall==0)
							{
								$("#holt_all1").append("<li><a href='#' onclick='openTab("+'"'+holt[c].name+'"'+")' id='"+holt[c].proc_id+"'>"+holt[c].name+"</li></a>");
								holtall=1;
							}
							else
							{
								$("#holt_all2").append("<li><a href='#' onclick='openTab("+'"'+holt[c].name+'"'+")' id='"+holt[c].proc_id+"'>"+holt[c].name+"</li></a>");
								holtall=0;
							}
						}
						else if(holt[c].pro_type=="120002")
						{
							if(holthexiang==0)
							{
								$("#holt_hexiang1").append("<li><a href='#' onclick='openTab("+'"'+holt[c].name+'"'+")' id='"+holt[c].proc_id+"'>"+holt[c].name+"</li></a>");
								holthexiang=1;
							}
							else
							{
								$("#holt_hexiang2").append("<li><a href='#' onclick='openTab("+'"'+holt[c].name+'"'+")' id='"+holt[c].proc_id+"'>"+holt[c].name+"</li></a>");
								holthexiang=0;
							}
						}
						else
						{
							if(holtother==0)
							{
								$("#holt_other1").append("<li><a href='#' onclick='openTab("+'"'+holt[c].name+'"'+")' id='"+holt[c].proc_id+"'>"+holt[c].name+"</li></a>");
								holtother=1;
							}
							else
							{
								$("#holt_other2").append("<li><a href='#' onclick='openTab("+'"'+holt[c].name+'"'+")' id='"+holt[c].proc_id+"'>"+holt[c].name+"</li></a>");
								holtother=0;
							}
						}
					}
					var lawnall=0;
					var lawnhexiang=0;
					var lawnother=0;
					for(var d=0;d<lawn.length;d++)
					{
						if(lawn[d].pro_type=="120001")
						{
							if(lawnall==0)
							{
								$("#lawn_all1").append("<li><a href='#' onclick='openTab("+'"'+lawn[d].name+'"'+")' id='"+lawn[d].proc_id+"'>"+lawn[d].name+"</li></a>");
								lawnall=1;
							}
							else
							{
								$("#lawn_all2").append("<li><a href='#' onclick='openTab("+'"'+lawn[d].name+'"'+")' id='"+lawn[d].proc_id+"'>"+lawn[d].name+"</li></a>");
								lawnall=0;
							}
						}
						else if(lawn[d].pro_type=="120002")
						{
							if(lawnhexiang==0)
							{
								$("#lawn_hexiang1").append("<li><a href='#' onclick='openTab("+'"'+lawn[d].name+'"'+")' id='"+lawn[d].proc_id+"'>"+lawn[d].name+"</li></a>");
								lawnhexiang=1;
							}
							else
							{
								$("#lawn_hexiang2").append("<li><a href='#' onclick='openTab("+'"'+lawn[d].name+'"'+")' id='"+lawn[d].proc_id+"'>"+lawn[d].name+"</li></a>");
								lawnhexiang=0;
							}
						}
						else
						{
							if(lawnother==0)
							{
								$("#lawn_other1").append("<li><a href='#' onclick='openTab("+'"'+lawn[d].name+'"'+")' id='"+lawn[d].proc_id+"'>"+lawn[d].name+"</li></a>");
								lawnother=1;
							}
							else
							{
								$("#lawn_other2").append("<li><a href='#' onclick='openTab("+'"'+lawn[d].name+'"'+")' id='"+lawn[d].proc_id+"'>"+lawn[d].name+"</li></a>");
								lawnother=0;
							}
						}
					}
					var seasall=0;
					var seashexiang=0;
					var seasother=0;
					for(var e=0;e<seas.length;e++)
					{
						if(seas[e].pro_type=="120001")
						{
							if(seasall==0)
							{
								$("#seas_all1").append("<li><a href='#' onclick='openTab("+'"'+seas[e].name+'"'+")' id='"+seas[e].proc_id+"'>"+seas[e].name+"</li></a>");
								seasall=1;
							}
							else
							{
								$("#seas_all2").append("<li><a href='#' onclick='openTab("+'"'+seas[e].name+'"'+")' id='"+seas[e].proc_id+"'>"+seas[e].name+"</li></a>");
								seasall=0;
							}
						}
						else if(seas[e].pro_type=="120002")
						{
							if(seashexiang==0)
							{
								$("#seas_hexiang1").append("<li><a href='#' onclick='openTab("+'"'+seas[e].name+'"'+")' id='"+seas[e].proc_id+"'>"+seas[e].name+"</li></a>");
								seashexiang=1;
							}
							else
							{
								$("#seas_hexiang2").append("<li><a href='#' onclick='openTab("+'"'+seas[e].name+'"'+")' onclick='openTab('"+seas[e].name+"')' id='"+seas[e].proc_id+"'>"+seas[e].name+"</li></a>");
								seashexiang=0;
							}
						}
						else
						{
							if(seasother==0)
							{
								$("#seas_other1").append("<li><a href='#' onclick='openTab("+'"'+seas[e].name+'"'+")' id='"+seas[e].proc_id+"'>"+seas[e].name+"</li></a>");
								seasother=1;
							}
							else
							{
								$("#seas_other2").append("<li><a href='#' onclick='openTab("+'"'+seas[e].name+'"'+")' id='"+seas[e].proc_id+"'>"+seas[e].name+"</li></a>");
								seasother=0;
							}
						}
					}
				}
					
			}
		};
		$.ajax(objs);
	
});


function openTab(name)
{
	parent.openTab(name,'../jsp/login/biz.jsp');
}
