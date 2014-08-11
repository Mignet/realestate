
	//主键值数组，记录复选框选中的行（包括翻页）主键值
	//window.seq_id_set = [];
var nodeid;
$(function(){
	//初始化产品树
	var productTree = $('#getProcName').tree({
		url : ctx+"/common/certificate!getBusTypeToTree.action",
		onLoadSuccess : function() {
			//$('#getProcName').tree('select', $('#getProcName').tree('find', '-1').target);
		},
		onSelect : function(node) {
			$('#table_user').datagrid('load',{'nodeid':node.id,'bus_typeid':node.iconCls});
			if(!node.children)
			{
				nodeid=node.id;
			}
			else
			{
				nodeid=null;
			}
			$(":checkbox").removeAttr("checked");
			selectRule();
		}
	});
	$.ajax({
		url:ctx+"/common/certificate!getBusType.action",
		//data:{"lcslbid":lcslId,"djbh":djbh,"procdefId":proc.procdefId,"time":new Date()},
		contentType:"application/x-www-form-urlencoded; charset=GBK",
		dataType:"json",
		async:false,
		success:function(data){
			for(var i=0;i<data.length;i++){
				(function(){
					var tempData=data[i];
					$("#preaudit").append("<input type='checkbox'  id='"+data[i].bustype+"preaudit"+"' value='"+data[i].busname+"' name='"+data[i].busname+
							"' /> <label for='"+data[i].bustype+"preaudit"+"'>"+data[i].busname+" </label> &nbsp&nbsp");
					var btnId=document.getElementById(data[i].bustype+"preaudit");
					$(btnId).linkbutton({  
						  iconCls: 'icon-search'  
					}); 
					$(btnId).click(function(){
						saveordelete(this.id,tempData);
					});
				})()
			}
			for(var i=0;i<data.length;i++){
				(function(){
					var tempData=data[i];
					$("#limit").append("<input type='checkbox'  id='"+data[i].bustype+"limit"+"' value='"+data[i].busname+"' name='"+data[i].busname+
							"' /> <label for='"+data[i].bustype+"limit"+"'>"+data[i].busname+" </label> &nbsp&nbsp");
					var btnId=document.getElementById(data[i].bustype+"limit");
					$(btnId).linkbutton({  
						  iconCls: 'icon-search'  
					}); 
					$(btnId).click(function(){
						saveordelete(this.id,tempData);
					});
				})()
			}
			for(var i=0;i<data.length;i++){
				(function(){
					var tempData=data[i];
					$("#message").append("<input type='checkbox'  id='"+data[i].bustype+"message"+"' value='"+data[i].busname+"' name='"+data[i].busname+
							"' /> <label for='"+data[i].bustype+"message"+"'>"+data[i].busname+" </label> &nbsp&nbsp");
					var btnId=document.getElementById(data[i].bustype+"message");
					$(btnId).linkbutton({  
						  iconCls: 'icon-search'  
					}); 
					$(btnId).click(function(){
						saveordelete(this.id,tempData);
					});
				})()
			}
		}
	});
	
	function selectRule()
	{
		$.ajax({
			url:ctx+"/common/preaudit!selectRule.action",
			data:{"bustype":nodeid},
			contentType:"application/x-www-form-urlencoded; charset=GBK",
			dataType:"json",
			async:false,
			success:function(data){
				//alert(JONS.stringify(data));
				for(var i=0;i<data.total;i++)
				{
					if(data.rows[i].RULE_TYPE=="064002")
					{
						var cbx=document.getElementById(data.rows[i].RULE+"limit");
						$(cbx).attr("checked","checked");
					}
					else if(data.rows[i].RULE_TYPE=="064001")
					{
						var cbx=document.getElementById(data.rows[i].RULE+"preaudit");
						$(cbx).attr("checked","checked");
					}
					else
					{
						var cbx=document.getElementById(data.rows[i].RULE+"message");
						$(cbx).attr("checked","checked");
					}
				}	
			}
		});
	}
	
	//保存和删除受理业务规则
	function saveordelete(id,tempData)
	{
		if(nodeid)
		{
			var rulename;
			var ruletype;
			if(id.indexOf("message")>0)
			{
				rulename="提示";
				ruletype="064003";
			}
			else if(id.indexOf("preaudit")>0)
			{
				rulename="前置条件";
				ruletype="064001";
			}
			else
			{
				rulename="限制条件";
				ruletype="064002";
			}
			$.ajax({
				url:ctx+"/common/preaudit!saveDeleteRule.action",
				data:{"ruletype":ruletype,"ruleid":tempData.bustype,"bustype":nodeid,"rulename":rulename},
				contentType:"application/x-www-form-urlencoded; charset=GBK",
				dataType:"json",
				async:false,
				success:function(data){
					
				}
			});
		}
		
	}
	
	
	
});
